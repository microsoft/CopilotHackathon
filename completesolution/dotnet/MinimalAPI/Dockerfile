# create dotnet 7 image for the current project

# Use the official .NET 7 SDK image as the base image
FROM mcr.microsoft.com/dotnet/aspnet:7.0 AS build

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . .

# Build the project and restore the packages
RUN dotnet restore

# Publish the project to a folder called "out"
RUN dotnet publish -c Release -o out

# Use the official .NET 7 runtime image as the base image
FROM mcr.microsoft.com/dotnet/runtime:7.0 AS runtime

# Set the working directory to /app
WORKDIR /app

# Copy the published output from the build image to the runtime image
COPY --from=build /app/out ./

# Set the entry point to the application
ENTRYPOINT ["dotnet", "/app/MinimalAPI.dll"]
