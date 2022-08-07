package com.info.movii;

import java.io.Serializable;

public class MainTitles implements Serializable {

    private int mainTitleId;
    private String mainTitleName;

    public MainTitles() {
    }

    public MainTitles(int mainTitleId, String mainTitleName) {
        this.mainTitleId = mainTitleId;
        this.mainTitleName = mainTitleName;
    }

    public int getMainTitleId() {
        return mainTitleId;
    }

    public void setMainTitleId(int mainTitleId) {
        this.mainTitleId = mainTitleId;
    }

    public String getMainTitleName() {
        return mainTitleName;
    }

    public void setMainTitleName(String mainTitleName) {
        this.mainTitleName = mainTitleName;
    }
}

