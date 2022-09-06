package com.amazon.alexa.device.setup.echo.bouncycastle.operator;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import java.io.InputStream;
/* loaded from: classes.dex */
public interface InputDecryptor {
    AlgorithmIdentifier getAlgorithmIdentifier();

    InputStream getInputStream(InputStream inputStream);
}
