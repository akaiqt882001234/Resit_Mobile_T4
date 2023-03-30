package com.example.ggresit;

public class ModelTrip {

    private String id,name,date,dest,risk,desc;
    //create constructor

    public ModelTrip(String id, String name, String date, String dest, String risk, String desc) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.dest = dest;
        this.risk = risk;
        this.desc = desc;
    }

    //create getter and setter method

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
