package com.amazon.alexa.mode.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes9.dex */
public class Manufacture {
    @SerializedName("devices")
    @Expose
    private List<AmaDevice> devices;
    @SerializedName("manufacture")
    @Expose
    private String manufacture;

    public List<AmaDevice> getDevices() {
        return this.devices;
    }

    public String getManufacture() {
        return this.manufacture;
    }

    public void setDevices(List<AmaDevice> list) {
        this.devices = list;
    }

    public void setManufacture(String str) {
        this.manufacture = str;
    }
}
