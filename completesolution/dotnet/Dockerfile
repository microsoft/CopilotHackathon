#use the aspnetcore 6.0 runtime image as the base image
FROM mcr.microsoft.com/dotnet/aspnet:6.0
WORKDIR /app
# Copy publish forler to image
COPY ./publish .
# Run app
ENTRYPOINT ["dotnet", "dotnet.dll"]