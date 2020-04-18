FROM node:latest
WORKDIR /usr/app
COPY package.json .
RUN apt-get update
RUN npm install
RUN npm install -g nodemon
WORKDIR /usr/app/src
COPY ./src .
EXPOSE 8080
CMD [ "nodemon", "server.js" ]