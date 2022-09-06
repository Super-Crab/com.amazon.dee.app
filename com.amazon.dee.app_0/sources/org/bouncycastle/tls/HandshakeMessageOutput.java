package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class HandshakeMessageOutput extends ByteArrayOutputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HandshakeMessageOutput(short s) throws IOException {
        this(s, 60);
    }

    HandshakeMessageOutput(short s, int i) throws IOException {
        super(i + 4);
        TlsUtils.checkUint8(s);
        TlsUtils.writeUint8(s, (OutputStream) this);
        ((ByteArrayOutputStream) this).count += 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void send(TlsProtocol tlsProtocol, short s, byte[] bArr) throws IOException {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput(s, bArr.length);
        handshakeMessageOutput.write(bArr);
        handshakeMessageOutput.send(tlsProtocol);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void send(TlsProtocol tlsProtocol) throws IOException {
        int i = ((ByteArrayOutputStream) this).count - 4;
        TlsUtils.checkUint24(i);
        TlsUtils.writeUint24(i, ((ByteArrayOutputStream) this).buf, 1);
        tlsProtocol.writeHandshakeMessage(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
        ((ByteArrayOutputStream) this).buf = null;
    }
}
