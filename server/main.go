package main

import (
	"fmt"
	"net/http"
	"os"
	"strconv"

	"./db"
	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
)

const port = 8080

func main() {
	r := mux.NewRouter()

	//fmt.Println("HERE! ", os.Getenv("MYSQL_DB"))

	// Handle CORS
	headers := handlers.AllowedHeaders([]string{"X-Requested-With", "Content-Type", "Authorization"})
	methods := handlers.AllowedMethods([]string{"GET", "POST", "PUT", "DELETE"})
	origins := handlers.AllowedOrigins([]string{"*"})

	mysqlConnection := db.Init(os.Getenv("MYSQL_USER") + ":" + os.Getenv("MYSQL_PASSWORD") + "@tcp(" + os.Getenv("MYSQL_DB") + ":" + os.Getenv("MYSQL_PORT") + ")/" + os.Getenv("MYSQL_DATABASE"))

	r.HandleFunc("/reports", func(w http.ResponseWriter, r *http.Request) {
		GetReports(w, r, mysqlConnection)
	}).Methods("GET")

	r.HandleFunc("/reports", func(w http.ResponseWriter, r *http.Request) {
		AddReport(w, r, mysqlConnection)
	}).Methods("POST")

	r.HandleFunc("/reports/{id}", func(w http.ResponseWriter, r *http.Request) {
		GetReport(w, r, mysqlConnection)
	}).Methods("GET")

	fmt.Println("Now serving on port " + strconv.Itoa(port))
	http.ListenAndServe(":"+strconv.Itoa(port), handlers.CORS(headers, methods, origins)(r))
}
