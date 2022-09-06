package com.amazon.alexa.redesign.entity.ranking;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class Rule {
    @SerializedName("contentProvider")
    private String contentProvider;
    @SerializedName("contentType")
    private String contentType;
    private int index = -1;
    @SerializedName("priority")
    private int priority;
    @SerializedName("sectionOrdering")
    private String sectionOrdering;

    public String getContentProvider() {
        return this.contentProvider;
    }

    public String getContentType() {
        return this.contentType;
    }

    public int getIndex() {
        return this.index;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getSectionOrdering() {
        return this.sectionOrdering;
    }

    public void setIndex(int i) {
        this.index = i;
    }
}
