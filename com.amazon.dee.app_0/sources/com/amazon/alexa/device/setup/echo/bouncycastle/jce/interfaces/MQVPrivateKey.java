package com.amazon.alexa.device.setup.echo.bouncycastle.jce.interfaces;

import java.security.PrivateKey;
import java.security.PublicKey;
/* loaded from: classes.dex */
public interface MQVPrivateKey extends PrivateKey {
    PrivateKey getEphemeralPrivateKey();

    PublicKey getEphemeralPublicKey();

    PrivateKey getStaticPrivateKey();
}
