package com.amazon.alexa.drive.entertainment.model;

import java.io.Serializable;
/* loaded from: classes7.dex */
public class MediaErrorPayload implements Serializable {
    private String mSubtitle;
    private String mTitle;

    public MediaErrorPayload(String str, String str2) {
        this.mTitle = str;
        this.mSubtitle = str2;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getTitle() {
        return this.mTitle;
    }
}
