package com.amazon.alexa.device.setup.echo.bouncycastle.jce.spec;

import com.amazon.alexa.device.setup.echo.bouncycastle.jce.interfaces.MQVPublicKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
/* loaded from: classes.dex */
public class MQVPublicKeySpec implements KeySpec, MQVPublicKey {
    private final PublicKey ephemeralKey;
    private final PublicKey staticKey;

    public MQVPublicKeySpec(PublicKey publicKey, PublicKey publicKey2) {
        this.staticKey = publicKey;
        this.ephemeralKey = publicKey2;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "ECMQV";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return null;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jce.interfaces.MQVPublicKey
    public PublicKey getEphemeralKey() {
        return this.ephemeralKey;
    }

    @Override // java.security.Key
    public String getFormat() {
        return null;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jce.interfaces.MQVPublicKey
    public PublicKey getStaticKey() {
        return this.staticKey;
    }
}
