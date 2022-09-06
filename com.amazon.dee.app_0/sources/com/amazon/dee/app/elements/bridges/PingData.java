package com.amazon.dee.app.elements.bridges;

import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = "Ping")
/* loaded from: classes12.dex */
public class PingData {
    private String id;
    private boolean timedOut;
    private long timestamp;

    public PingData(String str, long j, boolean z) {
        this.id = str;
        this.timestamp = j;
        this.timedOut = z;
    }

    public String getId() {
        return this.id;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public boolean isTimedOut() {
        return this.timedOut;
    }

    public void setTimedOut(boolean z) {
        this.timedOut = z;
    }
}
