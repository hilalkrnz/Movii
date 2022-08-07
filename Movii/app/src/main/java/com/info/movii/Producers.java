package com.info.movii;

import java.io.Serializable;

public class Producers implements Serializable {
    private int producerID ;
    private String producerName;

    public Producers() {
    }

    public Producers(int producerID, String producerName) {
        this.producerID = producerID;
        this.producerName = producerName;
    }

    public int getProducerID() {
        return producerID;
    }

    public void setProducerID(int producerID) {
        this.producerID = producerID;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
}
