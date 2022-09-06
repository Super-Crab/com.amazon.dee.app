package com.amazon.communication;
/* loaded from: classes12.dex */
public interface BandwidthToolByteAccountant {
    public static final String SYSTEM_SERVICE_KEY = "com.amazon.communication.BandwidthToolByteAccountant";

    boolean accountBytesReceived(int i, long j);

    boolean accountBytesTransmitted(int i, long j);
}
