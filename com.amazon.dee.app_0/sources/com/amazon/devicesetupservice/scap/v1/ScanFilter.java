package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ScanFilter implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.ScanFilter");
    private String serviceData;
    private String serviceDataMask;
    private String serviceUuid;

    public boolean equals(Object obj) {
        if (!(obj instanceof ScanFilter)) {
            return false;
        }
        ScanFilter scanFilter = (ScanFilter) obj;
        return Helper.equals(this.serviceDataMask, scanFilter.serviceDataMask) && Helper.equals(this.serviceUuid, scanFilter.serviceUuid) && Helper.equals(this.serviceData, scanFilter.serviceData);
    }

    public String getServiceData() {
        return this.serviceData;
    }

    public String getServiceDataMask() {
        return this.serviceDataMask;
    }

    public String getServiceUuid() {
        return this.serviceUuid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.serviceDataMask, this.serviceUuid, this.serviceData);
    }

    public void setServiceData(String str) {
        this.serviceData = str;
    }

    public void setServiceDataMask(String str) {
        this.serviceDataMask = str;
    }

    public void setServiceUuid(String str) {
        this.serviceUuid = str;
    }
}
