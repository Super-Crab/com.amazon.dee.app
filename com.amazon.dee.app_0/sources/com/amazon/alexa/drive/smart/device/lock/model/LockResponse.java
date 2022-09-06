package com.amazon.alexa.drive.smart.device.lock.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class LockResponse {
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
