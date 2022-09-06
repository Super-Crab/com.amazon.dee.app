package com.amazon.alexa.device.setup.echo.bouncycastle.jce.interfaces;

import java.security.PublicKey;
/* loaded from: classes.dex */
public interface MQVPublicKey extends PublicKey {
    PublicKey getEphemeralKey();

    PublicKey getStaticKey();
}
