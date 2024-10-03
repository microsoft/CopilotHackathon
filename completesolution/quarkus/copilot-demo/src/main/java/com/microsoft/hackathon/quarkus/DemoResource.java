package com.microsoft.hackathon.quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.quarkus.fs.util.ZipUtils;





/* 
* The Demo resource should be mapped to the root path.
* 
* Create a GET operation to return the value of a key passed as query parameter in the request. 
* 
* If the key is not passed, return "key not passed".
* If the key is passed, return "hello <key>".
* 
*/

@Path("/")
public class DemoResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("key") String key) {
        if (key == null) {
            return "key not passed";
        } else {
            return "hello " + key;
        }
    }

    // New operation under /diffdates that calculates the difference between two dates. The operation should receive two dates as parameter in format dd-MM-yyyy and return the difference in days.
    
    @GET
    @Path("/diffdates")
    @Produces(MediaType.TEXT_PLAIN)
    public String diffdates(@QueryParam("date1") String date1, @QueryParam("date2") String date2) {
        Objects.requireNonNull(date1, "date1 must not be null");
        Objects.requireNonNull(date2, "date2 must not be null");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date1Obj = dateFormat.parse(date1);
            Date date2Obj = dateFormat.parse(date2);
            long diffMillis = Math.abs(date1Obj.getTime() - date2Obj.getTime());
            long diffDays = diffMillis / (24 * 60 * 60 * 1000);
            return String.valueOf(diffDays);
        } catch (Exception e) {
            return "invalid date format";
        }
    }

    // Validate the format of a spanish phone number (+34 prefix, then 9 digits, starting with 6, 7 or 9). The operation should receive a phone number as parameter and return true if the format is correct, false otherwise. 

    @GET
    @Path("/validatephone")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean validatephone(@QueryParam("phone") String phone) {
        Objects.requireNonNull(phone, "phone must not be null");
        if (!phone.matches("\\+34[679]\\d{8}")) {
            return false;
        }

        return true;
    }

    // Validate the format of a spanish DNI (8 digits and 1 letter). The operation should receive a DNI as parameter and return true if the format is correct, false otherwise. 

    @GET
    @Path("/validatedni")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean validatedni(@QueryParam("dni") String dni) {
        Objects.requireNonNull(dni, "dni must not be null");

        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        return true;
    }

    // Based on existing colors.json file under resources, given the name of the color as path parameter, return the hexadecimal code. If the color is not found, return 404

    @GET
    @Path("/hexcolor")
    @Produces(MediaType.TEXT_PLAIN)
    public Response color(@QueryParam("name") String name) {
        Objects.requireNonNull(name, "name must not be null");
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("colors.json");
            JsonNode root = mapper.readTree(inputStream);
            for (JsonNode color : root) {
                if (color.get("color").asText().equals(name)) {
                    ObjectNode code = (ObjectNode) color.get("code");
                    return Response.ok(code.get("hex").asText()).build();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return http 404
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    // Create a new operation that call the API https://api.chucknorris.io/jokes/random and return the joke.

    @GET
    @Path("/chucknorris")
    @Produces(MediaType.TEXT_PLAIN)
    public String chucknorris() {
        // create a new instance of the Resteasy client
        Client client = ClientBuilder.newClient();
        // create a new target
        WebTarget target = client.target("https://api.chucknorris.io/jokes/random");
        // send the request and get the response
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        // parse the response
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.readEntity(String.class));
            return root.get("value").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }    

    // Given a url as query parameter, parse it and return the protocol, host, port, path and query parameters. The response should be in Json format.

    @GET
    @Path("/parseurl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response parseurl(@QueryParam("url") String url) {
        Objects.requireNonNull(url, "url must not be null");
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL urlNode = new URL(url);
            JsonNode root = mapper.createObjectNode();
            ((ObjectNode) root).put("protocol", urlNode.getProtocol());
            ((ObjectNode) root).put("host", urlNode.getHost());
            ((ObjectNode) root).put("port", urlNode.getPort());
            ((ObjectNode) root).put("path", urlNode.getPath());
            ((ObjectNode) root).put("query", urlNode.getQuery());
            return Response.ok(root).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return http 500
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    // List files and folders under a given path. The path should be a query parameter. The response should be in Json format.

    @GET
    @Path("/listfiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listfiles(@QueryParam("path") String path) {
        Objects.requireNonNull(path, "path must not be null");
        if (path.contains("..") || path.contains("/") || path.contains("\\")) {
            throw new IllegalArgumentException("Invalid path");
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<JsonNode> fileList = new ArrayList<>();
            Files.walk(Paths.get(path))
                    .forEach(file -> {
                        ObjectNode fileNode = mapper.createObjectNode();
                        fileNode.put("path", file.toString());
                        fileNode.put("isDirectory", file.toFile().isDirectory());
                        fileList.add(fileNode);
                    });

            return Response.ok(fileList).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return http 500
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    // Given the path of a file and count the number of occurrence of a provided word. The path and the word should be query parameters. The response should be in Json format.

    @GET
    @Path("/countword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countWord(@QueryParam("path") String path, @QueryParam("word") String word) {
        Objects.requireNonNull(path, "path must not be null");
        Objects.requireNonNull(word, "word must not be null");
        if (path.contains("..") || path.contains("/") || path.contains("\\")) {
            throw new IllegalArgumentException("Invalid path");
        }
        java.nio.file.Path filePath = Paths.get(path);
        String content;
        int count = 0;
        try {
            content = Files.readString(filePath);
            String[] words = content.split("\\s+");
            for (String w : words) {
                if (w.equals(word)) {
                    count++;
                }
            }
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("count", count);
        return Response.ok(responseNode).build();
    }

    // Create a zip file with the content of a given folder. The path of the folder should be a query parameter.

    @GET
    @Path("/zipfolder")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response zipFolder(@QueryParam("path") String path) {
        Objects.requireNonNull(path, "path must not be null");
        if (path.contains("..") || path.contains("/") || path.contains("\\")) {
            throw new IllegalArgumentException("Invalid path");
        }
        java.nio.file.Path folderPath = Paths.get(path);
        File folder = folderPath.toFile();
        if (!folder.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (!folder.isDirectory()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            // create a zip file from the folder
            java.nio.file.Path zipPath = folderPath.getParent().resolve(folderPath.getFileName() + ".zip");
            ZipUtils.zip(folderPath, zipPath);
            // return the zip file
            InputStream inputStream = new FileInputStream(zipPath.toFile());
            Response.ResponseBuilder response = Response.ok(inputStream);
            response.type("application/zip");
            response.header("Content-Disposition", "attachment; filename=\"" + folderPath.getFileName() + ".zip\"");
            // remove the zip file
            Files.delete(zipPath);
            return response.build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}




