var builder = WebApplication.CreateBuilder(args);
var app     = builder.Build();

List<Author> authors = new List<Author>
{
    new Author { Id = 1, Name = "Author One" },
    new Author { Id = 2, Name = "Author Two" }
};

app.MapGet("/authors", () => authors);

app.MapGet("/authors/{id}", (int id) =>
{
    authors.FirstOrDefault(author => author.Id == id);
});

app.MapPost("/authors", (Author author) =>
{
    authors.Add(author);

    return Results.Created($"/authors/{author.Id}", author);
});

app.MapPut("/authors/{id}", (int id, Author updatedAuthor) =>
{
    var author = authors.FirstOrDefault(author => author.Id == id);

    if (author == null)
    {
        return Results.NotFound();
    }

    author.Id   = updatedAuthor.Id;
    author.Name = updatedAuthor.Name;

    return Results.Ok(author);
});

app.MapDelete("/authors/{id}", (int id) =>
{
    var author = authors.FirstOrDefault(author => author.Id == id);

    if (author == null)
    {
        return Results.NotFound();
    }

    authors.Remove(author);

    return Results.Ok();
});

app.Run();
