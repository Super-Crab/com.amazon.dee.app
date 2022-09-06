package com.amazon.alexa.drive.smart.device.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes7.dex */
public class SmartDevice {
    public static final int TYPE_GUARD = 1;
    public static final int TYPE_LOCK = 2;
    public static final int TYPE_UNKNOWN = 0;
    private int deviceType;
    private String endpointId;
    private boolean locked;
    private String tag;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface SmartDeviceType {
    }

    public SmartDevice(int i) {
        this.deviceType = i;
    }

    public String getEndpointId() {
        return this.endpointId;
    }

    public int getSmartDeviceType() {
        return this.deviceType;
    }

    public String getTag() {
        return this.tag;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    public void setEndpointId(String str) {
        this.endpointId = str;
    }

    public void setLocked(boolean z) {
        this.locked = z;
    }

    public void setTag(String str) {
        this.tag = str;
    }
}
