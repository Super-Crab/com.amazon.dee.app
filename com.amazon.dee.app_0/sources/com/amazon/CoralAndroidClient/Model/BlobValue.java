package com.amazon.CoralAndroidClient.Model;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.nio.ByteBuffer;
import java.util.Base64;
/* loaded from: classes.dex */
public class BlobValue implements Value {
    public byte[] mData;

    public BlobValue() {
        this.mData = new byte[0];
    }

    public byte[] getValue() {
        return this.mData;
    }

    public void setValue(String str) throws NativeException {
        if (str != null && str.length() != 0) {
            try {
                this.mData = Base64.getDecoder().decode(str);
                return;
            } catch (IllegalArgumentException e) {
                throw new NativeException("invalid base64 encoding", e);
            }
        }
        this.mData = new byte[0];
    }

    public String toBase64() {
        byte[] bArr = this.mData;
        return (bArr == null || bArr.length == 0) ? "" : Base64.getEncoder().encodeToString(this.mData);
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return toBase64();
    }

    public BlobValue(ByteBuffer byteBuffer) {
        this.mData = new byte[0];
        setValue(byteBuffer);
    }

    public void setValue(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            setValue(byteBuffer.array());
        }
    }

    public void setValue(byte[] bArr) {
        if (bArr == null) {
            this.mData = new byte[0];
            return;
        }
        this.mData = new byte[bArr.length];
        System.arraycopy(bArr, 0, this.mData, 0, bArr.length);
    }
}
