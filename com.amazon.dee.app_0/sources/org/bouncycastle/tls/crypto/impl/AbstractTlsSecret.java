package org.bouncycastle.tls.crypto.impl;

import java.io.IOException;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public abstract class AbstractTlsSecret implements TlsSecret {
    protected byte[] data;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractTlsSecret(byte[] bArr) {
        this.data = bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkAlive() {
        if (this.data != null) {
            return;
        }
        throw new IllegalStateException("Secret has already been extracted or destroyed");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized byte[] copyData() {
        return Arrays.clone(this.data);
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized void destroy() {
        if (this.data != null) {
            Arrays.fill(this.data, (byte) 0);
            this.data = null;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized byte[] encrypt(TlsCertificate tlsCertificate) throws IOException {
        checkAlive();
        return getCrypto().createEncryptor(tlsCertificate).encrypt(this.data, 0, this.data.length);
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized byte[] extract() {
        byte[] bArr;
        checkAlive();
        bArr = this.data;
        this.data = null;
        return bArr;
    }

    protected abstract AbstractTlsCrypto getCrypto();

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized boolean isAlive() {
        return this.data != null;
    }
}
