#user dotnet runtime
FROM microsoft/dotnet:2.1-aspnetcore-runtime AS base
WORKDIR /app
# Copy publish forler to image
COPY ./publish .
# Run app
ENTRYPOINT ["dotnet", "dotnet.dll"]