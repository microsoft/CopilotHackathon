package com.microsoft.hackathon.copilotdemo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/* 
* Create a GET operation to return the value of a key passed as query parameter. 
* If the key is not passed, return "key not passed".
* If the key is passed, return "hello <key>".
* 
*/

@RestController
public class DemoController {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello(@RequestParam(name = "key", required = false) String key) {
        if (key == null) {
            return "key not passed";
        }
        return "hello " + key;
    }   

    // New operation under /diffdates that calculates the difference between two dates. The operation should receive two dates as parameter in format dd-MM-yyyy and return the difference in days. 
    @GetMapping(value = "/diffdates", produces = MediaType.TEXT_PLAIN_VALUE)
    public String diffdates(@RequestParam(name = "date1", required = false) String date1, @RequestParam(name = "date2", required = false) String date2) throws ParseException {
        if (date1 == null || date2 == null) {
            return "date not passed";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date1Obj = sdf.parse(date1);
        Date date2Obj = sdf.parse(date2);
        long diffInMillies = Math.abs(date2Obj.getTime() - date1Obj.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return "difference in days: " + diff;
    }

    // Validate the format of a spanish phone number (+34 prefix, then 9 digits, starting with 6, 7 or 9). The operation should receive a phone number as parameter and return true if the format is correct, false otherwise. 
    @GetMapping(value = "/validatephone", produces = MediaType.TEXT_PLAIN_VALUE)
    public boolean validatephone(@RequestParam(name = "phone", required = false) String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        String regex = "^\\+34[679]\\d{8}$";
        return phone.matches(regex);
    }

    // Validate the format of a spanish DNI (8 digits and 1 letter). The operation should receive a DNI as parameter and return true if the format is correct, false otherwise. 
    @GetMapping(value = "/validatedni", produces = MediaType.TEXT_PLAIN_VALUE)
    public boolean validatedni(@RequestParam(name = "dni", required = false) String dni) {
        if (dni == null || dni.isEmpty()) {
            return false;
        }
        String regex = "^\\d{8}[A-Z]$";
        return dni.matches(regex);
    }

    //Based on existing colors.json file under resources, given the name of the color as path parameter, return the hexadecimal code. If the color is not found, return 404
    @GetMapping(value = "/color/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> color(@PathVariable("name") String name) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("colors.json");
        ObjectMapper objectMapper = new ObjectMapper();
        // create JsonNode from mapper
        JsonNode rootNode = objectMapper.readTree(inputStream);
        for (JsonNode color : rootNode) {
            // if color name is found, return the hex code
            if (color.get("color").asText().equals(name)) {
                return new ResponseEntity<String>(color.get("code").get("hex").asText(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Color not found", HttpStatus.NOT_FOUND);
    }
    
    // new operation that call the API https://api.chucknorris.io/jokes/random and return the joke
    @GetMapping(value = "/joke", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getJoke() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.chucknorris.io/jokes/random";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        // parse response to get the value
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(response.getBody());
            return rootNode.get("value").asText();
        } catch (IOException e) {
           return new String("Error getting joke");
        }
    }


    // Given a url as query parameter, parse it and return the protocol, host, port, path and query parameters. The response should be in Json format.
    @GetMapping(value = "/parseurl", produces = MediaType.APPLICATION_JSON_VALUE)
    public String parseurl(@RequestParam(name = "url", required = false) String url) throws MalformedURLException {
        if (url == null || url.isEmpty()) {
            return "url not passed";
        }
        URL urlObj = new URL(url);
        String protocol = urlObj.getProtocol();
        String host = urlObj.getHost();
        int port = urlObj.getPort();
        String path = urlObj.getPath();
        String query = urlObj.getQuery();
        return "{ \"protocol\": \"" + protocol + "\", \"host\": \"" + host + "\", \"port\": \"" + port + "\", \"path\": \"" + path + "\", \"query\": \"" + query + "\" }";
    }

    // List files and folders under a given path. The path should be a query parameter. The response should be in Json format.
    @GetMapping(value = "/list-files", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listFiles(@RequestParam(name = "path") String pathString) {
        try {
            File path = new File(pathString);
            if (!path.exists()) {
                return ResponseEntity.notFound().build();
            }
            if (!path.isDirectory()) {
                return ResponseEntity.badRequest().body("Path is not a directory");
            }
            File[] files = path.listFiles();
            JSONArray jsonArray = new JSONArray();
            for (File file : files) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", file.getName());
                jsonObject.put("isDirectory", file.isDirectory());
                jsonArray.put(jsonObject);
            }
            return ResponseEntity.ok(jsonArray.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error listing files");
        }
    }

    // Given the path of a file and count the number of occurrence of a provided word. The path and the word should be query parameters. The response should be in Json format.
    @GetMapping(value = "/count-word", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> countWord(@RequestParam(name = "path") String pathString, @RequestParam(name = "word") String word) {
        try {
            File file = new File(pathString);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }
            if (!file.isFile()) {
                return ResponseEntity.badRequest().body("Path is not a file");
            }
            int count = 0;
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    if (scanner.next().equals(word)) {
                        count++;
                    }
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", count);
            return ResponseEntity.ok(jsonObject.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error counting word");
        }
    }

    // Create a zip file with the content of a given folder. The path of the folder should be a query parameter.
    @GetMapping(value = "/zip-folder", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> zipFolder(@RequestParam(name = "path") String pathString) {
        try {
            File folder = new File(pathString);
            if (!folder.exists()) {
                return ResponseEntity.notFound().build();
            }
            if (!folder.isDirectory()) {
                return ResponseEntity.badRequest().body(null);
            }
            String zipFileName = folder.getName() + ".zip";
            File zipFile = new File(zipFileName);
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zipFolder(folder, folder.getName(), zos);
            zos.close();
            fos.close();
            Path path = Paths.get(zipFileName);
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zipFileName);
            return ResponseEntity.ok().headers(headers).contentLength(zipFile.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private void zipFolder(File folder, String parentFolder, ZipOutputStream zos) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                zipFolder(file, parentFolder + "/" + file.getName(), zos);
            } else {
                ZipEntry zipEntry = new ZipEntry(parentFolder + "/" + file.getName());
                zos.putNextEntry(zipEntry);
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                fis.close();
            }
        }
    }

}
