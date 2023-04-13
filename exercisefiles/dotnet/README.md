- Create a new NET project using 

```  powershell
dotnet new webapi
 ```

- Create a new file User.cs in the Models folder, and instruct Copilot to generate a class for you.

- A a new file UserController.cs in the Controllers folder that inherits from ControllerBase, and instruct Copilot to generate a controller for you.
- Add ApiController and Route attributes to the class.

- A a new file IUserService in the Abstractions folder, and instruct Copilot to generate an interface for you.

- Run the app using

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
