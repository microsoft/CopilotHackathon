## Create dotnet WebAPI project
- Create a new NET project using 

```  powershell
dotnet new webapi
 ```

- Create a new file User.cs in the Models folder, and instruct Copilot to generate a class for you.

- A a new file UserController.cs in the Controllers folder that inherits from ControllerBase, and instruct Copilot to generate a controller for you.
- Add ApiController and Route attributes to the class.

- A a new file IUserService in the Abstractions folder, and instruct Copilot to generate an interface for you.

- Run the app using (if you are working with Github Codespace remove https redirection from Startup.cs)

```  powershell
dotnet run
```

- Implement the interface IUserService in UserService.cs in the Services folder and instruct Copilot to generate the implementation for you.
- Instruct Copilot to generate a List for Users and the Add and Get Methods using the list.

- Go to Program.cs a inject the IUserService before building the app.

``` csharp
builder.Services.AddSingleton<IUserService, UserService>();
```

- Run the app using

```  powershell
dotnet run
```

## Docker
- Publish the app using
``` dotnet
dotnet publish -c Release -o publish
```
- Using a Dockerfile put the app in a container and run it
- Instruct Copilot to generate a Dockerfile for you using
``` dockerfile
# Use the aspnetcore 6.0 runtime image as the base image

# Copy publish forler to image

# Run app
```

``` powershell
docker build -t dotnetapp .
docker run -d -p 8080:80 --name dotnetapp dotnetapp
```