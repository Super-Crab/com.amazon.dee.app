package com.amazon.alexa.device.setup.echo.bouncycastle.crypto.params;

import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.CipherParameters;
/* loaded from: classes.dex */
public class KeyParameter implements CipherParameters {
    private final byte[] key;

    public KeyParameter(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public byte[] getKey() {
        return this.key;
    }

    public KeyParameter(byte[] bArr, int i, int i2) {
        this.key = new byte[i2];
        System.arraycopy(bArr, i, this.key, 0, i2);
    }
}
