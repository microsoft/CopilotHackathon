# Create a dockerfile with node image
FROM node:latest

# Create a directory to hold the application code inside the image, this will be the working directory for your application
RUN mkdir -p /usr/src/app

# Set the working directory to /usr/src/app
WORKDIR /usr/src/app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install npm 
RUN npm install

# Copy the current directory contents into the container at /usr/src/app
COPY . .

# Make port 3000 available to the world outside this container
EXPOSE 3000

# Run Nodeserver.js when the container launches
CMD ["node", "nodeserver.js"]

# Write a docker comand to build the image and tag it as mynodeapp
#docker build -t mynodeapp .

# Write command to run docker in port 4000
#docker run -p 4000:3000 -d <image id>
