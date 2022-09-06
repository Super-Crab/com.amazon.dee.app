package com.amazon.whispercloak;
/* loaded from: classes13.dex */
public class Payload {
    private byte[] mAad;
    private byte[] mData;

    public Payload(byte[] bArr, byte[] bArr2) {
        this.mData = bArr;
        this.mAad = bArr2;
    }

    public byte[] getAad() {
        return this.mAad;
    }

    public byte[] getData() {
        return this.mData;
    }
}
