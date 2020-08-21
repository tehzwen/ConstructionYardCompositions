package com.constuctionyard.constructionyard;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import java.util.*;

@RestController
public class ReportController {

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public List<Report> getReports(@RequestParam(required = false, defaultValue="0") int id) {
        List<Report> reports;
        if (id != 0) {
            MySQLConnector db = new MySQLConnector(System.getenv("MYSQL_DB"), System.getenv("MYSQL_PORT"), System.getenv("MYSQL_DATABASE"), System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASSWORD"));
            reports = db.GetReport(id);
        } else {
            MySQLConnector db = new MySQLConnector(System.getenv("MYSQL_DB"), System.getenv("MYSQL_PORT"), System.getenv("MYSQL_DATABASE"), System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASSWORD"));
            reports = db.GetReports();
        }
        return reports;
    }

    @RequestMapping(value = "/reports", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        MySQLConnector db = new MySQLConnector(System.getenv("MYSQL_DB"), System.getenv("MYSQL_PORT"), System.getenv("MYSQL_DATABASE"), System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASSWORD"));
        int id = db.CreateReport(report);
        report.setID(id);
        //need to get the returned id before returning this.
        return new ResponseEntity<>(report, null, HttpStatus.CREATED);
    }
}