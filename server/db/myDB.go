package db

import (
	"database/sql"
	"fmt"

	_ "github.com/go-sql-driver/mysql"
)

type MyDB struct {
	Connection *sql.DB
}

func Init(connstring string) MyDB {
	db, err := sql.Open("mysql", connstring)
	if err != nil {
		panic(err)
	}
	fmt.Println("Connected to DB! ", connstring)
	d := MyDB{}
	d.Connection = db
	return d
}
