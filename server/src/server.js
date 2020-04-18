const express = require('express');
const app = express();
const fs = require('fs');
const http = require('http').createServer(app);
const bodyParser = require('body-parser');
const cors = require('cors');
const mysql = require('mysql');
const Logger = require('./logger');

// Constants
const PORT = 8080;

const con = mysql.createConnection({
    host: process.env.MYSQL_DB,
    user: process.env.MYSQL_USER,
    password: process.env.MYSQL_PASSWORD,
    port: process.env.MYSQL_PORT,
    database: process.env.MYSQL_DATABASE
});

const appLogging = new Logger();

app.use(bodyParser.json());       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
    extended: true
}));

app.use(cors({
    exposedHeaders: ['Content-Disposition']
}));

app.get("/reports", (req, res) => {
    let query = "SELECT * FROM reports;";
    con.query(query, (err, results) => {
        if (err) {
            appLogging.logError("/reports", "GET", req);
            res.send({
                status: 500,
                err
            });
        } else {
            appLogging.logRequest("/reports", "GET", req);
            appLogging.logDatabase(results, query);
            res.send({
                status: 200,
                results
            })
        }
    })
});

app.post("/reports", (req, res) => {
    //check for body fields
    if (req.body.text) {
        let query = "INSERT INTO reports(report) values('" + req.body.text + "');";
        con.query(query, (err, results) => {
            if (err) {
                appLogging.logError("/reports", "POST", req);
                res.status(500).send({
                    status: 500,
                    err
                });
            } else {
                appLogging.logRequest("/reports", "POST", req);
                appLogging.logDatabase(results, query);
                res.send({
                    status: 200,
                    results
                })
            }
        });
    } else {
        appLogging.logError("/reports", "POST", req);
        res.status(500).send({
            status: 500,
            error: 'Missing body.text'
        });
    }
});

http.listen(PORT, () => {
    console.log("Listening on port " + PORT);
});