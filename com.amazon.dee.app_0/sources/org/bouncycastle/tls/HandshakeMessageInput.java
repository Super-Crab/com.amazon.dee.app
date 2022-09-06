package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import org.bouncycastle.tls.crypto.TlsHash;
/* loaded from: classes5.dex */
class HandshakeMessageInput extends ByteArrayInputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HandshakeMessageInput(byte[] bArr, int i, int i2) {
        super(bArr, i, i2);
    }

    @Override // java.io.ByteArrayInputStream, java.io.InputStream
    public void mark(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.ByteArrayInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateHash(TlsHash tlsHash) {
        byte[] bArr = ((ByteArrayInputStream) this).buf;
        int i = ((ByteArrayInputStream) this).mark;
        tlsHash.update(bArr, i, ((ByteArrayInputStream) this).count - i);
    }
}
