package org.bouncycastle.tls;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes5.dex */
public abstract class TlsRSAUtils {
    private TlsRSAUtils() {
    }

    public static TlsSecret generateEncryptedPreMasterSecret(TlsContext tlsContext, TlsCertificate tlsCertificate, OutputStream outputStream) throws IOException {
        TlsSecret generateRSAPreMasterSecret = tlsContext.getCrypto().generateRSAPreMasterSecret(tlsContext.getRSAPreMasterSecretVersion());
        TlsUtils.writeEncryptedPMS(tlsContext, generateRSAPreMasterSecret.encrypt(tlsCertificate), outputStream);
        return generateRSAPreMasterSecret;
    }
}
