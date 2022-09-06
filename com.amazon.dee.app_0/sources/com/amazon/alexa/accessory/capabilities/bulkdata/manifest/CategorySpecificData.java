package com.amazon.alexa.accessory.capabilities.bulkdata.manifest;

import com.google.protobuf.ByteString;
/* loaded from: classes.dex */
public class CategorySpecificData {
    private final ByteString categorySpecificData;

    public CategorySpecificData(byte[] bArr) {
        this.categorySpecificData = ByteString.copyFrom(bArr);
    }

    public ByteString getByteString() {
        return this.categorySpecificData;
    }

    public byte[] getBytes() {
        return this.categorySpecificData.toByteArray();
    }
}
