package com.amazon.alexa.presence.detection;
/* loaded from: classes9.dex */
public class BeaconDetection {
    private String beaconPayload;
    private String detectionTime;
    private int relativeReceivedSignalStrength;

    public BeaconDetection(String str, int i, String str2) {
        this.beaconPayload = str;
        this.relativeReceivedSignalStrength = i;
        this.detectionTime = str2;
    }

    public String getBeaconPayload() {
        return this.beaconPayload;
    }

    public String getDetectionTime() {
        return this.detectionTime;
    }

    public int getRelativeReceivedSignalStrength() {
        return this.relativeReceivedSignalStrength;
    }
}
