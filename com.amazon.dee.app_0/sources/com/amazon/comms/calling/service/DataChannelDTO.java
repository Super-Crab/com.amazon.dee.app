package com.amazon.comms.calling.service;
/* loaded from: classes11.dex */
public class DataChannelDTO {
    private final boolean binary;
    private final byte[] data;
    private final String label;

    public DataChannelDTO(String str, byte[] bArr, boolean z) {
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
