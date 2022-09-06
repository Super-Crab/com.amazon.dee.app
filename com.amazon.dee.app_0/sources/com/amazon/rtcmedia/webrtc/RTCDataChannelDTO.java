package com.amazon.rtcmedia.webrtc;
/* loaded from: classes13.dex */
public class RTCDataChannelDTO {
    private final boolean binary;
    private final byte[] data;
    private final String label;

    public RTCDataChannelDTO(String str, byte[] bArr, boolean z) {
        this.label = str;
        this.data = (byte[]) bArr.clone();
        this.binary = z;
    }

    public byte[] getData() {
        return (byte[]) this.data.clone();
    }

    public String getLabel() {
        return this.label;
    }

    public boolean isBinary() {
        return this.binary;
    }
}
