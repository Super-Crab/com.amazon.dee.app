package com.amazon.alexa.drive.smart.device.lock.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Data {
    @SerializedName("listEndpoints")
    private ListEndpoints listEndpoints;

    public ListEndpoints getListEndpoints() {
        return this.listEndpoints;
    }

    public void setListEndpoints(ListEndpoints listEndpoints) {
        this.listEndpoints = listEndpoints;
    }
}
