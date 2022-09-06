package org.bouncycastle.jsse.provider;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.TlsClientProtocol;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ProvTlsClientProtocol extends TlsClientProtocol {
    private final Closeable closeable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvTlsClientProtocol(InputStream inputStream, OutputStream outputStream, Closeable closeable) {
        super(inputStream, outputStream);
        this.closeable = closeable;
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected void closeConnection() throws IOException {
        this.closeable.close();
    }
}
