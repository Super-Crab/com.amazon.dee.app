package com.amazon.alexa.presence.models;

import java.util.List;
/* loaded from: classes9.dex */
public class ResolveBeaconsRequest {
    private final List<DetectedBeaconDetails> beacons;
    private final boolean detailedResponse;
    private final List<MobileDeviceAttributes> mobileDeviceAttributes;
    private final String personId;
    private final String requestTimestamp;

    public ResolveBeaconsRequest(List<DetectedBeaconDetails> list, List<MobileDeviceAttributes> list2, String str, boolean z, String str2) {
        this.beacons = list;
        this.mobileDeviceAttributes = list2;
        this.requestTimestamp = str;
        this.detailedResponse = z;
        this.personId = str2;
    }

    public List<DetectedBeaconDetails> getBeacons() {
        return this.beacons;
    }

    public List<MobileDeviceAttributes> getMobileDeviceAttributes() {
        return this.mobileDeviceAttributes;
    }

    public String getPersonId() {
        return this.personId;
    }

    public String getRequestTimestamp() {
        return this.requestTimestamp;
    }

    public boolean isDetailedResponse() {
        return this.detailedResponse;
    }
}
