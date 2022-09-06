package org.bouncycastle.tls.crypto.impl.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Signature;
import java.security.SignatureException;
import org.bouncycastle.jcajce.io.OutputStreamFactory;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.util.io.TeeOutputStream;
/* loaded from: classes5.dex */
class JcaVerifyingStreamSigner implements TlsStreamSigner {
    private final OutputStream output;
    private final Signature signer;
    private final Signature verifier;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JcaVerifyingStreamSigner(Signature signature, Signature signature2) {
        OutputStream createStream = OutputStreamFactory.createStream(signature);
        OutputStream createStream2 = OutputStreamFactory.createStream(signature2);
        this.signer = signature;
        this.verifier = signature2;
        this.output = new TeeOutputStream(createStream, createStream2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsStreamSigner
    public OutputStream getOutputStream() throws IOException {
        return this.output;
    }

    @Override // org.bouncycastle.tls.crypto.TlsStreamSigner
    public byte[] getSignature() throws IOException {
        try {
            byte[] sign = this.signer.sign();
            if (!this.verifier.verify(sign)) {
                throw new TlsFatalAlert((short) 80);
            }
            return sign;
        } catch (SignatureException e) {
            throw new TlsFatalAlert((short) 80, (Throwable) e);
        }
    }
}
