package com.sg.springmvc.models;

public class Journal {
    private int id;
    private String journal;
    private String publisher;
    private String latest_publisher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLatest_publisher() {
        return latest_publisher;
    }

    public void setLatest_publisher(String latest_publisher) {
        this.latest_publisher = latest_publisher;
    }
}
