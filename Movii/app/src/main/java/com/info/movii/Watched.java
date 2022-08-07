package com.info.movii;


import java.io.Serializable;

public class Watched implements Serializable {

    private int watchedId;
    private String watchedName;
    private String watchedImage;
    private Categories category;
    private Producers producer;

    public Watched() {
    }

    public Watched(int watchedId, String watchedName, String watchedImage, Categories category, Producers producer) {
        this.watchedId = watchedId;
        this.watchedName = watchedName;
        this.watchedImage = watchedImage;
        this.category = category;
        this.producer = producer;
    }

    public int getWatchedId() {
        return watchedId;
    }

    public void setWatchedId(int watchedId) {
        this.watchedId = watchedId;
    }

    public String getWatchedName() {
        return watchedName;
    }

    public void setWatchedName(String watchedName) {
        this.watchedName = watchedName;
    }

    public String getWatchedImage() {
        return watchedImage;
    }

    public void setWatchedImage(String watchedImage) {
        this.watchedImage = watchedImage;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Producers getProducer() {
        return producer;
    }

    public void setProducer(Producers producer) {
        this.producer = producer;
    }
}

