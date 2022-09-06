package com.amazon.deecomms.media.model;
/* loaded from: classes12.dex */
public abstract class MediaContentBase {
    private long clientId;
    private String contentType;
    private String mediaId;

    public long getClientId() {
        return this.clientId;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public void setClientId(long j) {
        this.clientId = j;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }
}
