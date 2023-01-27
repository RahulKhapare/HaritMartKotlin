package com.rak_developer.haritmartkotlin.test;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class DataItem implements Serializable {

    @SerializedName("type_travel_description")
    private String typeTravelDescription;

    @SerializedName("is_active")
    private boolean isActive;

    @SerializedName("type_travel_id")
    private int typeTravelId;

    public String getTypeTravelDescription() {
        return typeTravelDescription;
    }

    public void setTypeTravelDescription(String typeTravelDescription) {
        this.typeTravelDescription = typeTravelDescription;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getTypeTravelId() {
        return typeTravelId;
    }

    public void setTypeTravelId(int typeTravelId) {
        this.typeTravelId = typeTravelId;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "type_travel_description = '" + typeTravelDescription + '\'' +
                        ",is_active = '" + isActive + '\'' +
                        ",type_travel_id = '" + typeTravelId + '\'' +
                        "}";
    }
}