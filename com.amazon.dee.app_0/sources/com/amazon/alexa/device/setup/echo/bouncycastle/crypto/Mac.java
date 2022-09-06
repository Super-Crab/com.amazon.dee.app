package com.amazon.alexa.device.setup.echo.bouncycastle.crypto;
/* loaded from: classes.dex */
public interface Mac {
    int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException;

    String getAlgorithmName();

    int getMacSize();

    void init(CipherParameters cipherParameters) throws IllegalArgumentException;

    void reset();

    void update(byte b) throws IllegalStateException;

    void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException;
}
