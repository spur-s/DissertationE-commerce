package com.kishor_bhattarai.easymanpower.models;

public class ReviewResponse {

    private String _id;
    private String rating;
    private String review;
    private String itemname;

    public ReviewResponse(String _id, String rating, String review) {
        this._id = _id;
        this.rating = rating;
        this.review = review;
        this.itemname = itemname;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
