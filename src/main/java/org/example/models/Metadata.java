package org.example.models;

public class Metadata {
    private String id;
    private String type;
    private Double rating;
    private Integer reviews;

    public Metadata() {}

    public Metadata(String id, String type, double rating, int reviews) {
        this.id = id;
        this.type = type;
        this.rating = rating;
        this.reviews = reviews;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public Double getRating() { return rating; }
    public Integer getReviews() { return reviews; }
}

