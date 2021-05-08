package com.galvanize.ajkiwigames;

public class GameData {
    private int id;
    private String name;
    private String genre;
    private String[] photoUrls;
    private String publisher;
    private String releaseDate;
    private String[] platforms;
    private String rating;
    private double price;

    public GameData() {
    }

    public GameData(int id, String name) {
        this.name = name;
        this.id = id;
        this.genre = "";
        this.photoUrls = new String[0];
        this.publisher = "";
        this.releaseDate = "";
        this.platforms = new String[0];
        this.rating = "";
        this.price = 0.00;
    }

    public GameData(int id, String name, String genre, String[] photoUrls, String publisher, String releaseDate, String[] platforms, String rating, double price) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.photoUrls = photoUrls;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.platforms = platforms;
        this.rating = rating;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
