package com.rak_developer.haritmartkotlin.test;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class TravelTypeResponse implements Serializable {

    @SerializedName("flag")
    private String flag;

    @SerializedName("data")
    private List<DataItem> data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return
                "TravelTypeResponse{" +
                        "flag = '" + flag + '\'' +
                        ",data = '" + data + '\'' +
                        "}";
    }
}