var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// Register a HttpClient so IHttpClientFactory can be used to create HttpClient instances.
builder.Services.AddHttpClient();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

// Hello World Get endpoint
app.MapGet("/", () => "Hello World!");

// Endpoint daysdetweendates. Reads startdate and enddate from the query string and returns the number of days between the dates
app.MapGet("/daysbetweendates", (DateTime startdate, DateTime enddate) => (enddate - startdate).TotalDays.ToString());

// Endpoint validatephonenumber. Reads phonenumber from the query string and returns true if the number is valid using regex
app.MapGet("/validatephonenumber", (string phonenumber) => Regex.IsMatch(phonenumber, @"^(\+[0-9]{9})$").ToString());

// Endpopoint validatespanishdni. Reads dni from the query string and returns true if the dni is valid. Implement inline.
app.MapGet("/validatespanishdni", (string dni) =>
{
    if (dni.Length != 9) return false;
    var dniLetters = "TRWAGMYFPDXBNJZSQVHLCKE";
    var dniNumber = int.Parse(dni.Substring(0, 8));
    var dniLetter = dniLetters[dniNumber % 23];
    return dniLetter == dni[8];
});

// Endpoint color. 
// Reads color from the query string.
// Read colors.json into a Coloresarray and iterated to find the correct color
// Return the Hex code.
// Implement inline.
app.MapGet("/color", (string color) =>
{
    var colors = JsonSerializer.Deserialize<Color[]>(File.ReadAllText("colors.json"));
    return colors.First(c => c.Name == color).Code.HEX;
});

// tellmeajoke endpoint. Calls jokeapi and returns a sinlgle joke. 
// Deserialize to dynamic.
// Make sure to use IHttpClientFactory to create the HttpClient instance.
app.MapGet("/tellmeajoke", async (IHttpClientFactory httpClientFactory) =>
{
    var client = httpClientFactory.CreateClient();
    var response = await client.GetAsync("https://v2.jokeapi.dev/joke/Any");
    var content = await response.Content.ReadAsStringAsync();
    return JsonSerializer.Deserialize<dynamic>(content);
});

// moviesbydirector endpoint. 
// Calls omdbapi with an apikey specified in code and returns a list of movies 
// for the director specified in the query string. Deserialize to dynamic. 
// Make sure to use IHttpClientFactory to create the HttpClient instance.
app.MapGet("/moviesbydirector", async (string director, IHttpClientFactory httpClientFactory) =>
{
    var client = httpClientFactory.CreateClient();
    var response = await client.GetAsync($"http://www.omdbapi.com/?apikey=4e3b711b&r=json&s={director}");
    var content = await response.Content.ReadAsStringAsync();
    return JsonSerializer.Deserialize<dynamic>(content);
});

// parseurl endpoint. Reads url from the query string and returns the protocol, host and path in a json object. Implement inline.
app.MapGet("/parseurl", (string url) =>
{
    var uri = new Uri(url);
    return new { Protocol = uri.Scheme, Host = uri.Host, Path = uri.AbsolutePath };
});

// listfiles endpoint. Read contents of the current directory and return a list of files. Implement inline.
app.MapGet("/listfiles", () =>
{
    var files = Directory.GetFileSystemEntries(Directory.GetCurrentDirectory());
    return JsonSerializer.Serialize(files);
});

// calculate memory usage endpoint. Return the current memory usage of the process in GB. Implement inline.
app.MapGet("/calculatememoryusage", () =>
{
    var process = Process.GetCurrentProcess();
    return $"{process.WorkingSet64 / 1024 / 1024 / 1024} GB";
});

// random european country endpoint. Return a random european country from an array. Implement inline.
app.MapGet("/randomeuropeancountry", () =>
{
    var countries = new[] { "Spain", "France", "Germany", "Italy", "Portugal", "Sweden", "Norway", "Denmark", "Finland", "Iceland", "Ireland", "United Kingdom", "Greece", "Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Estonia", "Hungary", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Romania", "Slovakia", "Slovenia" };
    return countries[new Random().Next(0, countries.Length)];
});

app.Run();

// Needed to be able to access this type from the MinimalAPI.Tests project.
public partial class Program
{ }
