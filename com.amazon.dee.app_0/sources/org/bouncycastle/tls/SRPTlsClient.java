package org.bouncycastle.tls;

import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.tls.crypto.TlsCrypto;
/* loaded from: classes5.dex */
public class SRPTlsClient extends AbstractTlsClient {
    private static final int[] DEFAULT_CIPHER_SUITES = {CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA};
    protected TlsSRPIdentity srpIdentity;

    public SRPTlsClient(TlsCrypto tlsCrypto, TlsSRPIdentity tlsSRPIdentity) {
        super(tlsCrypto);
        this.srpIdentity = tlsSRPIdentity;
    }

    public SRPTlsClient(TlsCrypto tlsCrypto, byte[] bArr, byte[] bArr2) {
        this(tlsCrypto, new BasicTlsSRPIdentity(bArr, bArr2));
    }

    @Override // org.bouncycastle.tls.TlsClient
    public TlsAuthentication getAuthentication() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public Hashtable getClientExtensions() throws IOException {
        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(super.getClientExtensions());
        TlsSRPUtils.addSRPExtension(ensureExtensionsInitialised, this.srpIdentity.getSRPIdentity());
        return ensureExtensionsInitialised;
    }

    public ProtocolVersion getClientVersion() {
        return ProtocolVersion.TLSv12;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public TlsSRPIdentity getSRPIdentity() {
        return this.srpIdentity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.AbstractTlsPeer
    public int[] getSupportedCipherSuites() {
        return TlsUtils.getSupportedCipherSuites(mo12857getCrypto(), DEFAULT_CIPHER_SUITES);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void processServerExtensions(Hashtable hashtable) throws IOException {
        if (TlsUtils.hasExpectedEmptyExtensionData(hashtable, TlsSRPUtils.EXT_SRP, (short) 47) || !requireSRPServerExtension()) {
            super.processServerExtensions(hashtable);
            return;
        }
        throw new TlsFatalAlert((short) 47);
    }

    protected boolean requireSRPServerExtension() {
        return false;
    }
}
