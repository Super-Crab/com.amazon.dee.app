package com.amazonaws.mobileconnectors.iot;
/* loaded from: classes13.dex */
class PEMObject {
    private final String beginMarker;
    private final byte[] derBytes;

    public PEMObject(String str, byte[] bArr) {
        this.beginMarker = str;
        this.derBytes = (byte[]) bArr.clone();
    }

    public String getBeginMarker() {
        return this.beginMarker;
    }

    public byte[] getDerBytes() {
        return (byte[]) this.derBytes.clone();
    }

    public PEMObjectType getPEMObjectType() {
        return PEMObjectType.fromBeginMarker(this.beginMarker);
    }
}
