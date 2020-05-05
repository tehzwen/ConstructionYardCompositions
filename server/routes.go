package main

import (
	"encoding/json"
	"net/http"

	"github.com/gorilla/mux"

	"./db"
)

type Report struct {
	ID   int    `json:"id"`
	Text string `json:"text"`
}

func GetReport(w http.ResponseWriter, r *http.Request, db db.MyDB) {
	vars := mux.Vars(r)
	resReport := Report{}
	err := db.Connection.QueryRow("SELECT * FROM reports WHERE id="+vars["id"]).Scan(&resReport.ID, &resReport.Text)
	if err != nil {
		panic(err.Error())
	}

	json.NewEncoder(w).Encode(resReport)
}

func GetReports(w http.ResponseWriter, r *http.Request, db db.MyDB) {
	results, err := db.Connection.Query("SELECT * FROM reports")
	if err != nil {
		panic(err.Error())
	}

	total := []Report{}

	for results.Next() {
		tempReport := Report{}
		err = results.Scan(&tempReport.ID, &tempReport.Text)
		if err != nil {
			panic(err.Error())
		}

		total = append(total, tempReport)
	}
	json.NewEncoder(w).Encode(total)
}

func AddReport(w http.ResponseWriter, r *http.Request, db db.MyDB) {
	req := Report{}
	err := json.NewDecoder(r.Body).Decode(&req)
	if err != nil {
		panic(err.Error())
	}
	res, err := db.Connection.Exec("INSERT INTO reports( report ) VALUES('" + req.Text + "')")

	if err != nil {
		panic(err.Error())
	}
	id, err := res.LastInsertId()
	if err != nil {
		panic(err.Error())
	}

	req.ID = int(id)
	json.NewEncoder(w).Encode(req)
}
