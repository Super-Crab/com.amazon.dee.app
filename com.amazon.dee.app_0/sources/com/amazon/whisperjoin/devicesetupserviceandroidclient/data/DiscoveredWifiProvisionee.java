package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DiscoveredWifiProvisionee implements Serializable {
    private String mEncodedSsid;
    private String mMacAddress;
    private String mRssi;

    public DiscoveredWifiProvisionee(String str, String str2, String str3) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("encodedSsid cannot be null");
            }
            if (str3 != null) {
                this.mMacAddress = str;
                this.mEncodedSsid = str2;
                this.mRssi = str3;
                return;
            }
            throw new IllegalArgumentException("rssi cannot be null");
        }
        throw new IllegalArgumentException("macAddress cannot be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DiscoveredWifiProvisionee.class != obj.getClass()) {
            return false;
        }
        DiscoveredWifiProvisionee discoveredWifiProvisionee = (DiscoveredWifiProvisionee) obj;
        return this.mMacAddress.equals(discoveredWifiProvisionee.getMacAddress()) && this.mEncodedSsid.equals(discoveredWifiProvisionee.getEncodedSsid()) && this.mRssi.equals(discoveredWifiProvisionee.getRssi());
    }

    public String getEncodedSsid() {
        return this.mEncodedSsid;
    }

    public String getMacAddress() {
        return this.mMacAddress;
    }

    public String getRssi() {
        return this.mRssi;
    }

    public int hashCode() {
        return Objects.hashCode(this.mMacAddress, this.mEncodedSsid, this.mRssi);
    }

    public void setEncodedSsid(String str) {
        this.mEncodedSsid = str;
    }

    public void setMacAddress(String str) {
        this.mMacAddress = str;
    }

    public void setRssi(String str) {
        this.mRssi = str;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("macAddress", this.mMacAddress).add("encodedSsid", this.mEncodedSsid).add("rssi", this.mRssi).toString();
    }
}
