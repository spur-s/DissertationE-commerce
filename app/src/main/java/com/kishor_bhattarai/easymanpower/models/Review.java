package com.kishor_bhattarai.easymanpower.models;

public class Review {

    private int rating;
    private String review;
    private String itemname;

    public Review(int rating, String review, String itemname) {

        this.rating = rating;
        this.review = review;
        this.itemname = itemname;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getitemname() {
        return itemname;
    }

    public void setitemname(String itemname) {
        this.itemname = itemname;
    }

}
