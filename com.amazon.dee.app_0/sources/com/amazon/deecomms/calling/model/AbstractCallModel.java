package com.amazon.deecomms.calling.model;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class AbstractCallModel {
    @JsonProperty("callId")
    private String callId;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("deviceMake")
    private String deviceMake;
    @JsonProperty(ArcusConstants.InputAttribute.DEVICE_MODEL)
    private String deviceModel;
    @JsonProperty("deviceScreenSize")
    private double deviceScreenSize;
    @JsonProperty("localTime")
    private String localTime;
    @JsonProperty(MetricsConfiguration.PLATFORM)
    private String platform;

    public String getCallId() {
        return this.callId;
    }

    public long getCreationDate() {
        return this.creationDate;
    }

    public String getDeviceMake() {
        return this.deviceMake;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public double getDeviceScreenSize() {
        return this.deviceScreenSize;
    }

    public String getLocalTime() {
        return this.localTime;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public void setCreationDate(long j) {
        this.creationDate = j;
    }

    public void setDeviceMake(String str) {
        this.deviceMake = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setDeviceScreenSize(double d) {
        this.deviceScreenSize = d;
    }

    public void setLocalTime(String str) {
        this.localTime = str;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }
}
