package com.amazon.alexa.device.setup.echo.bouncycastle.crypto.params;

import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.CipherParameters;
/* loaded from: classes.dex */
public class ParametersWithIV implements CipherParameters {
    private final byte[] iv;
    private final CipherParameters parameters;

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public byte[] getIV() {
        return this.iv;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr, int i, int i2) {
        this.iv = new byte[i2];
        this.parameters = cipherParameters;
        System.arraycopy(bArr, i, this.iv, 0, i2);
    }
}
