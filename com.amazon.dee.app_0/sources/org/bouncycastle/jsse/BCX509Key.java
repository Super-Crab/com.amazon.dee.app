package org.bouncycastle.jsse;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
/* loaded from: classes4.dex */
public interface BCX509Key {
    X509Certificate[] getCertificateChain();

    PrivateKey getPrivateKey();
}
