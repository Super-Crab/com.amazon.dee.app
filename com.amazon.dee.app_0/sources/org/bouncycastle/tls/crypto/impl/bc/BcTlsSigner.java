package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsSigner;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes5.dex */
public abstract class BcTlsSigner implements TlsSigner {
    protected final BcTlsCrypto crypto;
    protected final AsymmetricKeyParameter privateKey;

    /* JADX INFO: Access modifiers changed from: protected */
    public BcTlsSigner(BcTlsCrypto bcTlsCrypto, AsymmetricKeyParameter asymmetricKeyParameter) {
        if (bcTlsCrypto != null) {
            if (asymmetricKeyParameter == null) {
                throw new NullPointerException("'privateKey' cannot be null");
            }
            if (!asymmetricKeyParameter.isPrivate()) {
                throw new IllegalArgumentException("'privateKey' must be private");
            }
            this.crypto = bcTlsCrypto;
            this.privateKey = asymmetricKeyParameter;
            return;
        }
        throw new NullPointerException("'crypto' cannot be null");
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return null;
    }
}
