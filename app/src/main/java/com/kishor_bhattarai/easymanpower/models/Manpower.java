package com.kishor_bhattarai.easymanpower.models;

public class Manpower {

    String _id, name, desc, imageName, price;


    public Manpower(String _id, String name, String desc, String imageName, String price) {
        this._id = _id;
        this.name = name;
        this.desc = desc;
        this.imageName = imageName;
        this.price = price;
    }

    public Manpower(String name, String desc, String imageName, String price) {
        this.name = name;
        this.desc = desc;
        this.imageName = imageName;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", imageName='" + imageName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
