package com.example.manganovellist.models;

public class Manga {
    public int id,status;
    public String name,writer,type,rate;

    public Manga(int id, int status, String name, String writer, String type, String rate) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.writer = writer;
        this.type = type;
        this.rate = rate;
    }

    public Manga() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
