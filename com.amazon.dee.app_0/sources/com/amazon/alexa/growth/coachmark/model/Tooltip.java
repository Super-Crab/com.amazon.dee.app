package com.amazon.alexa.growth.coachmark.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Tooltip {
    @SerializedName("id")
    private String id;
    @SerializedName("text")
    private String text;

    public Tooltip(String str, String str2) {
        this.id = str;
        this.text = str2;
    }

    public String getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }
}
