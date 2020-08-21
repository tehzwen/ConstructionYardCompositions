package com.constuctionyard.constructionyard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Report {
    @JsonProperty("text")
    private String text;

    @JsonProperty("id")
    private int id;

    public Report() {
        this.text = "";
    }

    @JsonCreator
    public Report(@JsonProperty("text") String text) {
        this.text = text;
    }

    //@JsonValue
    public void setText(String newText) {
        this.text = newText;
    }

    //@JsonValue
    public String getText() {
        return this.text;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }
}