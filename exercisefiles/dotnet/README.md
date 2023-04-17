
# Goal

The goal is to create a simple WebAPI using .NET 6.0 and Docker with the help of GitHub Copilot.
Follow the instructions below and try to use Copilot as much as possible.
Try different things and see what Copilot can do for you, like generating a Dockerfile or a class, add comments, etc.


Remember:

Make sure copilot is configure and enabled for the current laguage, just chekc the status bar on the bottom right corner of VS Code.

## Instructions

### Create dotnet WebAPI project

- Create a new NET project using 

```  powershell
dotnet new webapi
```

- Create a new file **User.cs** in the Models folder, and instruct Copilot to generate a class for you.

- Add a new file **UserController.cs** in the Controllers folder that inherits from ControllerBase, and instruct Copilot to generate a controller for you.

- Add ApiController and Route attributes to the class.

- Add a new file **IUserService** in the Abstractions folder, and instruct Copilot to generate an interface for you.

- Run the app using (if you are working with Github Codespace you may need to remove https redirection from Startup.cs)

```  powershell
dotnet run
```

- Implement the interface IUserService in **UserService.cs** in the Services folder and add some comment so Copilot be able generate the implementation for you.

- Instruct Copilot to generate a List for Users and the Add and Get Methods using the list.

- Go to **Program.cs** a inject the **IUserService** before building the app.

``` csharp
builder.Services.AddSingleton<IUserService, UserService>();
```

- Run the app using

```  powershell
dotnet run
```
> If you run into and "No server certificate was specified..." error, run the following command
``` powershell
dotnet dev-certs https
```

- Forward port if needed

- Navigate to your address /swagger. Example: https://leomicheloni-supreme-space-invention-q74pg569452ggq-5164.preview.app.github.dev/swagger/index.html

### Put the application into a Docker container

- Publish the app and put it in a folder called _publish_

``` dotnet
dotnet publish -c Release -o publish
```
- Using existing Dockerfile put the app in a container and run it (add more instructions or start to write code and let Copilot complete it for you)


- Build the image and run the app on port 8080

``` powershell
docker build -t dotnetapp .
docker run -d -p 8080:80 --name dotnetapp dotnetapp
```