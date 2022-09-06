package com.amazon.alexa.presence.models;
/* loaded from: classes9.dex */
public class DetectedBeaconDetails {
    private final String detectionTimestamp;
    private final String rawPayload;
    private final int relativeReceivedSignalStrength;

    public DetectedBeaconDetails(String str, String str2, int i) {
        this.detectionTimestamp = str;
        this.rawPayload = str2;
        this.relativeReceivedSignalStrength = i;
    }

    public String getDetectionTimestamp() {
        return this.detectionTimestamp;
    }

    public String getRawPayload() {
        return this.rawPayload;
    }

    public int getRelativeReceivedSignalStrength() {
        return this.relativeReceivedSignalStrength;
    }
}
