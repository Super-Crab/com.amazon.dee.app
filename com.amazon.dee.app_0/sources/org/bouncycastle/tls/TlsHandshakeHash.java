package org.bouncycastle.tls;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsHash;
/* loaded from: classes5.dex */
public interface TlsHandshakeHash extends TlsHash {
    void copyBufferTo(OutputStream outputStream) throws IOException;

    void forceBuffering();

    TlsHash forkPRFHash();

    byte[] getFinalHash(short s);

    void notifyPRFDetermined();

    void sealHashAlgorithms();

    TlsHandshakeHash stopTracking();

    void trackHashAlgorithm(short s);
}
