package com.amazon.deecomms.nativemodules.model;
/* loaded from: classes12.dex */
public class MediaMetadata {
    private int duration;
    private String fileName;

    public MediaMetadata(String str, int i) {
        this.fileName = str;
        this.duration = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }
}
