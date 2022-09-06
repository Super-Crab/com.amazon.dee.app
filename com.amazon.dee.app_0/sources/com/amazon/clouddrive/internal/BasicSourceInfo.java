package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.model.SourceInfo;
/* loaded from: classes11.dex */
public class BasicSourceInfo {
    private String mDeviceId;
    private String mSourceId;

    public BasicSourceInfo(SourceInfo sourceInfo) {
        this.mSourceId = sourceInfo.getSourceId();
        this.mDeviceId = sourceInfo.getDevice().getDeviceId();
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public String getSourceId() {
        return this.mSourceId;
    }

    public BasicSourceInfo(String str, String str2) {
        this.mSourceId = str;
        this.mDeviceId = str2;
    }
}
