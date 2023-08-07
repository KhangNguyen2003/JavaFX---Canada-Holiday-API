package com.example.holidayapi;

public class Holiday {
    private String name;
    private String description;
    private String date;
    private String type;

    public Holiday(String name, String description, String date, String type) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
