package org.bouncycastle.tls.crypto;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes5.dex */
public interface TlsStreamSigner {
    OutputStream getOutputStream() throws IOException;

    byte[] getSignature() throws IOException;
}
