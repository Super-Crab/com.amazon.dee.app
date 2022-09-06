package com.amazon.alexa.drive.smart.device.lock.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class FriendlyNameObject {
    @SerializedName("value")
    private Value value;

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
