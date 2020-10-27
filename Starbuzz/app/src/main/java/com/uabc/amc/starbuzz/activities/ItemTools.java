package com.uabc.amc.starbuzz.activities;

public abstract class ItemTools {
    private String name;
    private String description;
    private int imageResourceId;

    protected ItemTools(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
