package com.amazon.alexa.mode.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes9.dex */
public class Headunit {
    @SerializedName("headunitType")
    @Expose
    private String headunitName;
    @SerializedName("regularExpression")
    @Expose
    private String regularExpression;

    public String getHeadunitName() {
        return this.headunitName;
    }

    public String getRegularExpression() {
        return this.regularExpression;
    }

    public void setHeadunitName(String str) {
        this.headunitName = str;
    }

    public void setRegularExpression(String str) {
        this.regularExpression = str;
    }
}
