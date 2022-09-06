package org.bouncycastle.openpgp.operator.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.Signer;
/* loaded from: classes5.dex */
class SignerOutputStream extends OutputStream {
    private Signer sig;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignerOutputStream(Signer signer) {
        this.sig = signer;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.sig.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.sig.update(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.sig.update(bArr, i, i2);
    }
}
