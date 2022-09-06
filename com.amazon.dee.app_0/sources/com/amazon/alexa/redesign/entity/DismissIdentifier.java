package com.amazon.alexa.redesign.entity;

import android.util.Log;
/* loaded from: classes10.dex */
public class DismissIdentifier {
    private String dismissId;
    private long timestamp;

    public DismissIdentifier(long j, String str) {
        this.timestamp = j;
        this.dismissId = str;
    }

    public String getDismissDataStoreId() {
        return this.timestamp + "_" + this.dismissId;
    }

    public String getDismissId() {
        return this.dismissId;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public DismissIdentifier(String str) {
        String str2;
        String[] split = str.split("_", 2);
        long j = -1;
        if (split.length > 1) {
            str2 = split[1];
            try {
                j = Long.parseLong(split[0]);
            } catch (NumberFormatException e) {
                Log.e("DismissIdentifier", "Cannot parse timestamp from dismiss data store id " + str, e);
            }
        } else {
            str2 = "";
        }
        this.timestamp = j;
        this.dismissId = str2;
    }
}
