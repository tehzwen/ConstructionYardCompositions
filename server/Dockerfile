FROM golang:1.14.2
RUN apt-get update
RUN apt-get install git
WORKDIR /usr/app
COPY . .
RUN go get github.com/gorilla/handlers
RUN go get github.com/gorilla/mux
RUN go get github.com/go-sql-driver/mysql
RUN go get -u github.com/cosmtrek/air
EXPOSE 8080
RUN touch .air.conf
CMD ["air"]