package org.bouncycastle.jsse.provider;

import org.bouncycastle.jsse.BCSSLConnection;
import org.bouncycastle.tls.TlsContext;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ProvSSLConnection implements BCSSLConnection {
    protected final ProvSSLSession session;
    protected final TlsContext tlsContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLConnection(TlsContext tlsContext, ProvSSLSession provSSLSession) {
        this.tlsContext = tlsContext;
        this.session = provSSLSession;
    }

    @Override // org.bouncycastle.jsse.BCSSLConnection
    public String getApplicationProtocol() {
        return JsseUtils.getApplicationProtocol(this.tlsContext.getSecurityParametersConnection());
    }

    @Override // org.bouncycastle.jsse.BCSSLConnection
    public byte[] getChannelBinding(String str) {
        TlsContext tlsContext;
        int i;
        if (str.equals("tls-server-end-point")) {
            tlsContext = this.tlsContext;
            i = 0;
        } else if (!str.equals("tls-unique")) {
            throw new UnsupportedOperationException();
        } else {
            tlsContext = this.tlsContext;
            i = 1;
        }
        return tlsContext.exportChannelBinding(i);
    }

    @Override // org.bouncycastle.jsse.BCSSLConnection
    /* renamed from: getSession  reason: collision with other method in class */
    public ProvSSLSession mo12855getSession() {
        return this.session;
    }
}
