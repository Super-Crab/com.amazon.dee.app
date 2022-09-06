package org.bouncycastle.tls.crypto.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes5.dex */
public abstract class AbstractTlsCrypto implements TlsCrypto {
    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret adoptSecret(TlsSecret tlsSecret) {
        if (tlsSecret instanceof AbstractTlsSecret) {
            return createSecret(((AbstractTlsSecret) tlsSecret).copyData());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unrecognized TlsSecret - cannot copy data: ");
        outline107.append(tlsSecret.getClass().getName());
        throw new IllegalArgumentException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract TlsEncryptor createEncryptor(TlsCertificate tlsCertificate) throws IOException;
}
