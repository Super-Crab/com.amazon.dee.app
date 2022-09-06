package com.amazon.whisperjoin.common.sharedtypes.reporting;
/* loaded from: classes13.dex */
public class ReportingSesssion {
    private int mSequenceNumber = 1;
    private final String mUrl;

    public ReportingSesssion(String str) {
        this.mUrl = str;
    }

    public int getNextSequenceNumber() {
        int i = this.mSequenceNumber;
        this.mSequenceNumber = i + 1;
        return i;
    }

    public String getUrl() {
        return this.mUrl;
    }
}
