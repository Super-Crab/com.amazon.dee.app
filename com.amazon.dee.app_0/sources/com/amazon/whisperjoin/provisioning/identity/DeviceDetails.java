package com.amazon.whisperjoin.provisioning.identity;
/* loaded from: classes13.dex */
public class DeviceDetails {
    public final String firmwareRevision;
    public final String hardwareRevision;
    public final String manufacturer;
    public final String modelNumber;
    public final String serialNumber;

    public DeviceDetails(String str, String str2, String str3, String str4, String str5) {
        this.manufacturer = str;
        this.serialNumber = str2;
        this.modelNumber = str3;
        this.hardwareRevision = str4;
        this.firmwareRevision = str5;
    }
}
