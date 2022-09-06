package com.amazon.alexa.device.setup.echo.bouncycastle.jce.spec;

import com.amazon.alexa.device.setup.echo.bouncycastle.jce.interfaces.MQVPrivateKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
/* loaded from: classes.dex */
public class MQVPrivateKeySpec implements KeySpec, MQVPrivateKey {
    private final PrivateKey ephemeralPrivateKey;
    private final PublicKey ephemeralPublicKey;
    private final PrivateKey staticPrivateKey;

    public MQVPrivateKeySpec(PrivateKey privateKey, PrivateKey privateKey2) {
        this(privateKey, privateKey2, null);
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "ECMQV";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return null;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jce.interfaces.MQVPrivateKey
    public PrivateKey getEphemeralPrivateKey() {
        return this.ephemeralPrivateKey;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jce.interfaces.MQVPrivateKey
    public PublicKey getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }

    @Override // java.security.Key
    public String getFormat() {
        return null;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jce.interfaces.MQVPrivateKey
    public PrivateKey getStaticPrivateKey() {
        return this.staticPrivateKey;
    }

    public MQVPrivateKeySpec(PrivateKey privateKey, PrivateKey privateKey2, PublicKey publicKey) {
        this.staticPrivateKey = privateKey;
        this.ephemeralPrivateKey = privateKey2;
        this.ephemeralPublicKey = publicKey;
    }
}
