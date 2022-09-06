package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.io.SignerOutputStream;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.util.io.TeeOutputStream;
/* loaded from: classes5.dex */
class BcVerifyingStreamSigner implements TlsStreamSigner {
    private final TeeOutputStream output;
    private final Signer signer;
    private final Signer verifier;

    BcVerifyingStreamSigner(Signer signer, Signer signer2) {
        SignerOutputStream signerOutputStream = new SignerOutputStream(signer);
        SignerOutputStream signerOutputStream2 = new SignerOutputStream(signer2);
        this.signer = signer;
        this.verifier = signer2;
        this.output = new TeeOutputStream(signerOutputStream, signerOutputStream2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsStreamSigner
    public OutputStream getOutputStream() throws IOException {
        return this.output;
    }

    @Override // org.bouncycastle.tls.crypto.TlsStreamSigner
    public byte[] getSignature() throws IOException {
        try {
            byte[] generateSignature = this.signer.generateSignature();
            if (!this.verifier.verifySignature(generateSignature)) {
                throw new TlsFatalAlert((short) 80);
            }
            return generateSignature;
        } catch (CryptoException e) {
            throw new TlsFatalAlert((short) 80, (Throwable) e);
        }
    }
}
