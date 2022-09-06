package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes5.dex */
class DigestInputBuffer extends ByteArrayOutputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public void copyTo(OutputStream outputStream) throws IOException {
        Streams.pipeAll(new ByteArrayInputStream(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count), outputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateDigest(TlsHash tlsHash) {
        tlsHash.update(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
    }
}
