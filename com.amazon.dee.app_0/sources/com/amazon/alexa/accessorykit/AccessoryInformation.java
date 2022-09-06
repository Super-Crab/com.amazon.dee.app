package com.amazon.alexa.accessorykit;
/* loaded from: classes6.dex */
public final class AccessoryInformation {
    private final int mCondition;
    private final boolean mConnected;
    private final String mDeviceType;
    private final String mIdentifier;
    private final String mName;
    private final String mSerialNumber;
    private final int mTransport;

    public AccessoryInformation(String str, String str2, String str3, String str4, int i, int i2, boolean z) {
        this.mName = str;
        this.mDeviceType = str2;
        this.mSerialNumber = str3;
        this.mIdentifier = str4;
        this.mCondition = i;
        this.mTransport = i2;
        this.mConnected = z;
    }

    public int getCondition() {
        return this.mCondition;
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String getName() {
        return this.mName;
    }

    public String getSerialNumber() {
        return this.mSerialNumber;
    }

    public int getTransport() {
        return this.mTransport;
    }

    public boolean isConnected() {
        return this.mConnected;
    }
}
