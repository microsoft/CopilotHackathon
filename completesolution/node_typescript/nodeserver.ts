import http from "http"
import url from "url"
import fs from "fs"
import escape from "escape-html"
import axios from "axios"
import zlib from "zlib"
import readline from "readline"

const server = http.createServer((req, res) => {
  if (!req.url) {
    res.end("Invalid request")
    return
  }

  const parsedUrl = url.parse(req.url, true)
  const queryData = parsedUrl.query

  if (req.url.startsWith("/DaysBetweenDates")) {
    const date1 = queryData.date1 as string
    const date2 = queryData.date2 as string

    const date1_ms = Date.parse(date1)
    const date2_ms = Date.parse(date2)

    const difference_ms = date2_ms - date1_ms

    res.end(Math.round(difference_ms / 86400000) + " days")
  } else if (req.url.startsWith("/Validatephonenumber")) {
    const phoneNumber = queryData.phoneNumber as string

    const regex = /^(\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}$/

    if (regex.test(phoneNumber)) {
      res.end("valid")
    } else {
      res.end("invalid")
    }
  } else if (req.url.startsWith("/ValidateSpanishDNI")) {
    const dni = queryData.dni as string

    const dniLetter = dni.charAt(dni.length - 1)
    const dniNumber = parseInt(dni.substring(0, dni.length - 1), 10)
    const dniLetterCalc = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(dniNumber % 23)

    if (dniLetter === dniLetterCalc) {
      res.end("valid")
    } else {
      res.end("invalid")
    }
  } else if (req.url.startsWith("/ReturnColorCode")) {
    const colors = fs.readFileSync("colors.json", "utf-8")
    const colorsObj = JSON.parse(colors)

    const color = queryData.color as string
    let colorFound = "not found"

    for (let i = 0; i < colorsObj.length; i++) {
      if (colorsObj[i].color === color) {
        colorFound = colorsObj[i].code.hex
        break
      }
    }

    res.end(colorFound)
  } else if (req.url.startsWith("/SendEmail")) {
    // Implementation for SendEmail
  } else if (req.url.startsWith("/TellMeAJoke")) {
    axios
      .get("https://official-joke-api.appspot.com/random_joke")
      .then((response) => {
        res.end(response.data.setup + " " + response.data.punchline)
      })
      .catch((error) => {
        console.log(error)
      })
  } else if (req.url.startsWith("/MoviesByDirector")) {
    const director = queryData.director as string

    axios
      .get(`http://www.omdbapi.com/?apikey=XXXXXXX&s=${director}`)
      .then((response) => {
        let movies = ""
        for (let i = 0; i < response.data.Search.length; i++) {
          movies += response.data.Search[i].Title + ", "
        }
        res.end(movies)
      })
      .catch((error) => {
        console.log(error)
      })
  } else if (req.url.startsWith("/ParseUrl")) {
    const someUrl = queryData.someurl as string
    const urlObj = new URL(someUrl)

    const host = urlObj.host

    res.end("host: " + host)
  } else if (req.url.startsWith("/ListFiles")) {
    const currentDir = __dirname
    const files = fs.readdirSync(currentDir)

    res.end(escape(files.toString()))
  } else if (req.url.startsWith("/GetFullTextFile")) {
    const text = fs.readFileSync("sample.txt", "utf-8")
    const lines = text.split("\r")

    let linesFound = ""
    for (let i = 0; i < lines.length; i++) {
      if (lines[i].includes("Fusce")) {
        linesFound += lines[i] + ", "
      }
    }

    res.end(linesFound)
  } else if (req.url.startsWith("/GetLineByLinefromtTextFile")) {
    const lineReader = readline.createInterface({
      input: fs.createReadStream("sample.txt"),
    })

    const promise = new Promise<string[]>((resolve, reject) => {
      const lines: string[] = []
      lineReader.on("line", (line: string) => {
        if (line.includes("Fusce")) {
          lines.push(line)
        }
      })
      lineReader.on("close", () => {
        resolve(lines)
      })
    })

    promise.then((lines) => {
      res.end(lines.toString())
    })
  } else if (req.url.startsWith("/CalculateMemoryConsumption")) {
    const memory = process.memoryUsage().heapUsed / 1024 / 1024

    res.end(memory.toFixed(2) + " GB")
  } else if (req.url.startsWith("/MakeZipFile")) {
    const gzip = zlib.createGzip()
    const input = fs.createReadStream("sample.txt")
    const output = fs.createWriteStream("sample.gz")

    input.pipe(gzip).pipe(output)

    res.end("sample.gz created")
  } else if (req.url.startsWith("/RandomEuropeanCountry")) {
    const countries = [
      { country: "Italy", iso: "IT" },
      { country: "France", iso: "FR" },
      { country: "Spain", iso: "ES" },
      { country: "Germany", iso: "DE" },
      { country: "United Kingdom", iso: "GB" },
      { country: "Greece", iso: "GR" },
      { country: "Portugal", iso: "PT" },
      { country: "Romania", iso: "RO" },
      { country: "Bulgaria", iso: "BG" },
      { country: "Croatia", iso: "HR" },
      { country: "Czech Republic", iso: "CZ" },
      { country: "Denmark", iso: "DK" },
      { country: "Estonia", iso: "EE" },
      { country: "Finland", iso: "FI" },
      { country: "Hungary", iso: "HU" },
      { country: "Ireland", iso: "IE" },
      { country: "Latvia", iso: "LV" },
      { country: "Lithuania", iso: "LT" },
      { country: "Luxembourg", iso: "LU" },
      { country: "Malta", iso: "MT" },
      { country: "Netherlands", iso: "NL" },
      { country: "Poland", iso: "PL" },
      { country: "Slovakia", iso: "SK" },
      { country: "Slovenia", iso: "SI" },
      { country: "Sweden", iso: "SE" },
      { country: "Belgium", iso: "BE" },
      { country: "Austria", iso: "AT" },
      { country: "Switzerland", iso: "CH" },
      { country: "Cyprus", iso: "CY" },
      { country: "Iceland", iso: "IS" },
      { country: "Norway", iso: "NO" },
      { country: "Albania", iso: "AL" },
      { country: "Andorra", iso: "AD" },
      { country: "Armenia", iso: "AM" },
      { country: "Azerbaijan", iso: "AZ" },
      { country: "Belarus", iso: "BY" },
      { country: "Bosnia and Herzegovina", iso: "BA" },
      { country: "Georgia", iso: "GE" },
      { country: "Kazakhstan", iso: "KZ" },
      { country: "Kosovo", iso: "XK" },
      { country: "Liechtenstein", iso: "LI" },
      { country: "Macedonia", iso: "MK" },
      { country: "Moldova", iso: "MD" },
      { country: "Monaco", iso: "MC" },
      { country: "Montenegro", iso: "ME" },
      { country: "Russia", iso: "RU" },
      { country: "San Marino", iso: "SM" },
      { country: "Serbia", iso: "RS" },
      { country: "Turkey", iso: "TR" },
      { country: "Ukraine", iso: "UA" },
      { country: "Vatican City", iso: "VA" },
    ]

    const randomCountry = countries[Math.floor(Math.random() * countries.length)]

    res.end(randomCountry.country + " " + randomCountry.iso)
  } else if (req.url.startsWith("/Get")) {
    const key = queryData.key as string

    if (!key) {
      res.end("key not passed")
    } else {
      res.end("hello " + escape(key))
    }
  } else {
    res.end("Called method not found")
  }
})

server.listen(3000, () => {
  console.log("server is listening on port 3000")
})
