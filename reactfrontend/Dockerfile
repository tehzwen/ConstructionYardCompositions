FROM node:latest
WORKDIR /usr/app
COPY package.json .
RUN apt-get update
RUN npm install
WORKDIR /usr/app/public
COPY ./public .
WORKDIR /usr/app/src
COPY ./src .
EXPOSE 3000
CMD [ "npm", "start" ]