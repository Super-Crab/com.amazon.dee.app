package org.bouncycastle.jsse.provider;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLPermission;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLSessionContext;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class ProvSSLSessionBase extends BCExtendedSSLSession {
    protected final long creationTime;
    protected final JcaTlsCrypto crypto;
    protected final SSLSession exportSSLSession;
    protected final boolean isFips;
    protected long lastAccessedTime;
    protected final String peerHost;
    protected final int peerPort;
    protected ProvSSLSessionContext sslSessionContext;
    protected final Map<String, Object> valueMap = GeneratedOutlineSupport1.outline136();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSessionBase(ProvSSLSessionContext provSSLSessionContext, String str, int i) {
        this.sslSessionContext = provSSLSessionContext;
        this.isFips = provSSLSessionContext == null ? false : provSSLSessionContext.getSSLContext().isFips();
        this.crypto = provSSLSessionContext == null ? null : provSSLSessionContext.getCrypto();
        this.peerHost = str;
        this.peerPort = i;
        this.creationTime = System.currentTimeMillis();
        this.exportSSLSession = SSLSessionUtil.exportSSLSession(this);
        this.lastAccessedTime = this.creationTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void accessedAt(long j) {
        this.lastAccessedTime = Math.max(this.lastAccessedTime, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProvSSLSessionBase) {
            return Arrays.areEqual(getIDArray(), ((ProvSSLSessionBase) obj).getIDArray());
        }
        return false;
    }

    @Override // javax.net.ssl.SSLSession
    public int getApplicationBufferSize() {
        return 16384;
    }

    @Override // javax.net.ssl.SSLSession
    public String getCipherSuite() {
        return ProvSSLContextSpi.getCipherSuiteName(getCipherSuiteTLS());
    }

    protected abstract int getCipherSuiteTLS();

    @Override // javax.net.ssl.SSLSession
    public long getCreationTime() {
        return this.creationTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SSLSession getExportSSLSession() {
        return this.exportSSLSession;
    }

    protected abstract byte[] getIDArray();

    @Override // javax.net.ssl.SSLSession
    public byte[] getId() {
        byte[] iDArray = getIDArray();
        return TlsUtils.isNullOrEmpty(iDArray) ? TlsUtils.EMPTY_BYTES : (byte[]) iDArray.clone();
    }

    protected abstract JsseSecurityParameters getJsseSecurityParameters();

    protected abstract JsseSessionParameters getJsseSessionParameters();

    @Override // javax.net.ssl.SSLSession
    public long getLastAccessedTime() {
        return this.lastAccessedTime;
    }

    protected abstract Certificate getLocalCertificateTLS();

    @Override // javax.net.ssl.SSLSession
    public java.security.cert.Certificate[] getLocalCertificates() {
        X509Certificate[] x509CertificateChain;
        JcaTlsCrypto jcaTlsCrypto = this.crypto;
        if (jcaTlsCrypto == null || (x509CertificateChain = JsseUtils.getX509CertificateChain(jcaTlsCrypto, getLocalCertificateTLS())) == null || x509CertificateChain.length <= 0) {
            return null;
        }
        return x509CertificateChain;
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getLocalPrincipal() {
        JcaTlsCrypto jcaTlsCrypto = this.crypto;
        if (jcaTlsCrypto != null) {
            return JsseUtils.getSubject(jcaTlsCrypto, getLocalCertificateTLS());
        }
        return null;
    }

    @Override // javax.net.ssl.SSLSession
    public int getPacketBufferSize() {
        return 18443;
    }

    @Override // javax.net.ssl.SSLSession
    public javax.security.cert.X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
        if (!this.isFips) {
            java.security.cert.Certificate[] peerCertificates = getPeerCertificates();
            try {
                javax.security.cert.X509Certificate[] x509CertificateArr = new javax.security.cert.X509Certificate[peerCertificates.length];
                for (int i = 0; i < peerCertificates.length; i++) {
                    x509CertificateArr[i] = javax.security.cert.X509Certificate.getInstance(peerCertificates[i].getEncoded());
                }
                return x509CertificateArr;
            } catch (Exception e) {
                throw new SSLPeerUnverifiedException(e.getMessage());
            }
        }
        throw new UnsupportedOperationException();
    }

    protected abstract Certificate getPeerCertificateTLS();

    @Override // javax.net.ssl.SSLSession
    public java.security.cert.Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
        X509Certificate[] x509CertificateChain;
        JcaTlsCrypto jcaTlsCrypto = this.crypto;
        if (jcaTlsCrypto == null || (x509CertificateChain = JsseUtils.getX509CertificateChain(jcaTlsCrypto, getPeerCertificateTLS())) == null || x509CertificateChain.length <= 0) {
            throw new SSLPeerUnverifiedException("No peer identity established");
        }
        return x509CertificateChain;
    }

    @Override // javax.net.ssl.SSLSession
    public String getPeerHost() {
        return this.peerHost;
    }

    @Override // javax.net.ssl.SSLSession
    public int getPeerPort() {
        return this.peerPort;
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        X500Principal subject;
        JcaTlsCrypto jcaTlsCrypto = this.crypto;
        if (jcaTlsCrypto == null || (subject = JsseUtils.getSubject(jcaTlsCrypto, getPeerCertificateTLS())) == null) {
            throw new SSLPeerUnverifiedException("No peer identity established");
        }
        return subject;
    }

    @Override // javax.net.ssl.SSLSession
    public String getProtocol() {
        return ProvSSLContextSpi.getProtocolVersionName(getProtocolTLS());
    }

    protected abstract ProtocolVersion getProtocolTLS();

    @Override // javax.net.ssl.SSLSession
    public SSLSessionContext getSessionContext() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new SSLPermission("getSSLSessionContext"));
        }
        return this.sslSessionContext;
    }

    @Override // javax.net.ssl.SSLSession
    public Object getValue(String str) {
        return this.valueMap.get(str);
    }

    @Override // javax.net.ssl.SSLSession
    public String[] getValueNames() {
        String[] strArr;
        synchronized (this.valueMap) {
            strArr = (String[]) this.valueMap.keySet().toArray(new String[this.valueMap.size()]);
        }
        return strArr;
    }

    public int hashCode() {
        return Arrays.hashCode(getIDArray());
    }

    @Override // javax.net.ssl.SSLSession
    public synchronized void invalidate() {
        if (this.sslSessionContext != null) {
            this.sslSessionContext.removeSession(getIDArray());
            this.sslSessionContext = null;
        }
    }

    @Override // javax.net.ssl.SSLSession
    public synchronized boolean isValid() {
        return this.sslSessionContext == null ? false : !TlsUtils.isNullOrEmpty(getIDArray());
    }

    protected void notifyBound(String str, Object obj) {
        if (obj instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener) obj).valueBound(new SSLSessionBindingEvent(this, str));
        }
    }

    protected void notifyUnbound(String str, Object obj) {
        if (obj instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener) obj).valueUnbound(new SSLSessionBindingEvent(this, str));
        }
    }

    @Override // javax.net.ssl.SSLSession
    public void putValue(String str, Object obj) {
        notifyUnbound(str, this.valueMap.put(str, obj));
        notifyBound(str, obj);
    }

    @Override // javax.net.ssl.SSLSession
    public void removeValue(String str) {
        notifyUnbound(str, this.valueMap.remove(str));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session(");
        outline107.append(getCreationTime());
        outline107.append(AccessoryMetricsConstants.DELIMITER);
        return GeneratedOutlineSupport1.outline91(outline107, getCipherSuite(), ")");
    }
}
