package com.uludag.can.spacexdroid.models;

public class Rocket {
    public final String id;
    public final String name;
    public final String country;
    public final String description;

    public Rocket(String id, String name, String country, String description) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.description = description;
    }
}
