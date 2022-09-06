package com.amazon.alexa.drive.smart.device.guard.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Identifier {
    @SerializedName("identifier")
    private String identifier;
    @SerializedName("type")
    private String type;

    public String getIdentifier() {
        return this.identifier;
    }

    public String getType() {
        return this.type;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
