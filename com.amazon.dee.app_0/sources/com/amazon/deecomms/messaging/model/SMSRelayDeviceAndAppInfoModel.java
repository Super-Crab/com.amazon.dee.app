package com.amazon.deecomms.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class SMSRelayDeviceAndAppInfoModel {
    @JsonProperty("androidDeviceModel")
    private String androidDeviceModel;
    @JsonProperty("androidVersion")
    private String androidVersion;
    @JsonProperty("appVersion")
    private String appVersion;
    @JsonProperty("deviceLocale")
    private String deviceLocale;
    @JsonProperty("deviceLocaleCountry")
    private String deviceLocaleCountry;
    @JsonProperty("networkCarrier")
    private String networkCarrier;
    @JsonProperty("networkType")
    private String networkType;
    @JsonProperty("pfm")
    private String pfm;

    public String getAndroidDeviceModel() {
        return this.androidDeviceModel;
    }

    public String getAndroidVersion() {
        return this.androidVersion;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getDeviceLocale() {
        return this.deviceLocale;
    }

    public String getDeviceLocaleCountry() {
        return this.deviceLocaleCountry;
    }

    public String getNetworkCarrier() {
        return this.networkCarrier;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public String getPfm() {
        return this.pfm;
    }

    public void setAndroidDeviceModel(String str) {
        this.androidDeviceModel = str;
    }

    public void setAndroidVersion(String str) {
        this.androidVersion = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setDeviceLocale(String str) {
        this.deviceLocale = str;
    }

    public void setDeviceLocaleCountry(String str) {
        this.deviceLocaleCountry = str;
    }

    public void setNetworkCarrier(String str) {
        this.networkCarrier = str;
    }

    public void setNetworkType(String str) {
        this.networkType = str;
    }

    public void setPfm(String str) {
        this.pfm = str;
    }
}
