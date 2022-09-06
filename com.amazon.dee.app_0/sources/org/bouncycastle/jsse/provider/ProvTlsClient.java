package org.bouncycastle.jsse.provider;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.tls.AbstractTlsClient;
import org.bouncycastle.tls.CertificateRequest;
import org.bouncycastle.tls.CertificateStatusRequest;
import org.bouncycastle.tls.CertificateStatusRequestItemV2;
import org.bouncycastle.tls.DefaultTlsClient;
import org.bouncycastle.tls.OCSPStatusRequest;
import org.bouncycastle.tls.ProtocolName;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.ServerName;
import org.bouncycastle.tls.SignatureAlgorithm;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsAuthentication;
import org.bouncycastle.tls.TlsCredentials;
import org.bouncycastle.tls.TlsDHGroupVerifier;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsServerCertificate;
import org.bouncycastle.tls.TlsSession;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.TrustedAuthority;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.IPAddress;
import org.bouncycastle.util.encoders.Hex;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ProvTlsClient extends DefaultTlsClient implements ProvTlsPeer {
    protected boolean handshakeComplete;
    protected final JsseSecurityParameters jsseSecurityParameters;
    protected final ProvTlsManager manager;
    protected final ProvSSLParameters sslParameters;
    protected ProvSSLSession sslSession;
    private static final Logger LOG = Logger.getLogger(ProvTlsClient.class.getName());
    private static final boolean provEnableSNIExtension = PropertyUtils.getBooleanSystemProperty("jsse.enableSNIExtension", true);
    private static final boolean provClientEnableStatusRequest = PropertyUtils.getBooleanSystemProperty("jdk.tls.client.enableStatusRequestExtension", true);
    private static final boolean provClientEnableTrustedCAKeys = PropertyUtils.getBooleanSystemProperty("org.bouncycastle.jsse.client.enableTrustedCAKeysExtension", false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvTlsClient(ProvTlsManager provTlsManager, ProvSSLParameters provSSLParameters) {
        super(provTlsManager.getContextData().getCrypto());
        this.jsseSecurityParameters = new JsseSecurityParameters();
        this.sslSession = null;
        this.handshakeComplete = false;
        this.manager = provTlsManager;
        this.sslParameters = provSSLParameters.copyForConnection();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean allowLegacyResumption() {
        return JsseUtils.allowLegacyResumption();
    }

    @Override // org.bouncycastle.tls.TlsClient
    public TlsAuthentication getAuthentication() throws IOException {
        return new TlsAuthentication() { // from class: org.bouncycastle.jsse.provider.ProvTlsClient.1
            @Override // org.bouncycastle.tls.TlsAuthentication
            public TlsCredentials getClientCredentials(CertificateRequest certificateRequest) throws IOException {
                ContextData contextData = ProvTlsClient.this.manager.getContextData();
                SecurityParameters securityParametersHandshake = ((AbstractTlsClient) ProvTlsClient.this).context.getSecurityParametersHandshake();
                ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
                boolean isTLSv13 = TlsUtils.isTLSv13(negotiatedVersion);
                Vector<SignatureAndHashAlgorithm> serverSigAlgs = securityParametersHandshake.getServerSigAlgs();
                Vector<SignatureAndHashAlgorithm> serverSigAlgsCert = securityParametersHandshake.getServerSigAlgsCert();
                ProvTlsClient.this.jsseSecurityParameters.peerSigSchemes = contextData.getSignatureSchemes(serverSigAlgs);
                JsseSecurityParameters jsseSecurityParameters = ProvTlsClient.this.jsseSecurityParameters;
                jsseSecurityParameters.peerSigSchemesCert = serverSigAlgs == serverSigAlgsCert ? jsseSecurityParameters.peerSigSchemes : contextData.getSignatureSchemes(serverSigAlgsCert);
                if (DummyX509KeyManager.INSTANCE == contextData.getX509KeyManager()) {
                    return null;
                }
                X500Principal[] x500Principals = JsseUtils.toX500Principals(certificateRequest.getCertificateAuthorities());
                byte[] certificateRequestContext = certificateRequest.getCertificateRequestContext();
                boolean z = true;
                if (isTLSv13 != (certificateRequestContext != null)) {
                    throw new TlsFatalAlert((short) 80);
                }
                short[] certificateTypes = certificateRequest.getCertificateTypes();
                if (certificateTypes != null) {
                    z = false;
                }
                if (isTLSv13 != z) {
                    throw new TlsFatalAlert((short) 80);
                }
                return isTLSv13 ? ProvTlsClient.this.selectClientCredentials13(x500Principals, certificateRequestContext) : TlsUtils.isSignatureAlgorithmsExtensionAllowed(negotiatedVersion) ? ProvTlsClient.this.selectClientCredentials12(x500Principals, certificateTypes) : ProvTlsClient.this.selectClientCredentialsLegacy(x500Principals, certificateTypes);
            }

            @Override // org.bouncycastle.tls.TlsAuthentication
            public void notifyServerCertificate(TlsServerCertificate tlsServerCertificate) throws IOException {
                if (tlsServerCertificate == null || tlsServerCertificate.getCertificate() == null || tlsServerCertificate.getCertificate().isEmpty()) {
                    throw new TlsFatalAlert((short) 40);
                }
                X509Certificate[] x509CertificateChain = JsseUtils.getX509CertificateChain(ProvTlsClient.this.mo12857getCrypto(), tlsServerCertificate.getCertificate());
                String authTypeServer = JsseUtils.getAuthTypeServer(((AbstractTlsClient) ProvTlsClient.this).context.getSecurityParametersHandshake().getKeyExchangeAlgorithm());
                ProvTlsClient.this.jsseSecurityParameters.statusResponses = JsseUtils.getStatusResponses(tlsServerCertificate.getCertificateStatus());
                ProvTlsClient.this.manager.checkServerTrusted(x509CertificateChain, authTypeServer);
            }
        };
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector<X500Name> getCertificateAuthorities() {
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected CertificateStatusRequest getCertificateStatusRequest() {
        if (!provClientEnableStatusRequest) {
            return null;
        }
        return new CertificateStatusRequest((short) 1, new OCSPStatusRequest(null, null));
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    /* renamed from: getCrypto  reason: collision with other method in class */
    public JcaTlsCrypto mo12857getCrypto() {
        return this.manager.getContextData().getCrypto();
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public TlsDHGroupVerifier getDHGroupVerifier() {
        return new ProvDHGroupVerifier();
    }

    protected String[] getKeyTypesLegacy(short[] sArr) throws IOException {
        String[] strArr = new String[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            strArr[i] = JsseUtils.getKeyTypeLegacyClient(sArr[i]);
        }
        return strArr;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector<CertificateStatusRequestItemV2> getMultiCertStatusRequest() {
        if (!provClientEnableStatusRequest) {
            return null;
        }
        OCSPStatusRequest oCSPStatusRequest = new OCSPStatusRequest(null, null);
        Vector<CertificateStatusRequestItemV2> vector = new Vector<>(2);
        vector.add(new CertificateStatusRequestItemV2((short) 2, oCSPStatusRequest));
        vector.add(new CertificateStatusRequestItemV2((short) 1, oCSPStatusRequest));
        return vector;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector<ProtocolName> getProtocolNames() {
        return JsseUtils.getProtocolNames(this.sslParameters.getApplicationProtocols());
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector<ServerName> getSNIServerNames() {
        String peerHostSNI;
        if (provEnableSNIExtension) {
            List<BCSNIServerName> serverNames = this.sslParameters.getServerNames();
            if (serverNames == null && (peerHostSNI = this.manager.getPeerHostSNI()) != null && peerHostSNI.indexOf(46) > 0 && !IPAddress.isValid(peerHostSNI)) {
                try {
                    serverNames = Collections.singletonList(new BCSNIHostName(peerHostSNI));
                } catch (RuntimeException unused) {
                    Logger logger = LOG;
                    logger.fine("Failed to add peer host as default SNI host_name: " + peerHostSNI);
                }
            }
            if (serverNames == null || serverNames.isEmpty()) {
                return null;
            }
            Vector<ServerName> vector = new Vector<>(serverNames.size());
            for (BCSNIServerName bCSNIServerName : serverNames) {
                vector.add(new ServerName((short) bCSNIServerName.getType(), bCSNIServerName.getEncoded()));
            }
            return vector;
        }
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public TlsSession getSessionToResume() {
        TlsSession tlsSession;
        ProvSSLSession sessionToResume = this.sslParameters.getSessionToResume();
        if (sessionToResume == null) {
            sessionToResume = this.manager.getContextData().getClientSessionContext().getSessionImpl(this.manager.getPeerHost(), this.manager.getPeerPort());
        }
        if (sessionToResume != null && (tlsSession = sessionToResume.getTlsSession()) != null && isResumable(sessionToResume)) {
            this.sslSession = sessionToResume;
            return tlsSession;
        } else if (!this.manager.getEnableSessionCreation()) {
            throw new IllegalStateException("No resumable sessions and session creation is disabled");
        } else {
            return null;
        }
    }

    @Override // org.bouncycastle.tls.DefaultTlsClient, org.bouncycastle.tls.AbstractTlsPeer
    protected int[] getSupportedCipherSuites() {
        return this.manager.getContextData().getContext().getActiveCipherSuites(mo12857getCrypto(), this.sslParameters, getProtocolVersions());
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector<Integer> getSupportedGroups(Vector vector) {
        return NamedGroupInfo.getSupportedGroupsLocalClient(this.jsseSecurityParameters.namedGroups);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector<SignatureAndHashAlgorithm> getSupportedSignatureAlgorithms() {
        List<SignatureSchemeInfo> activeCertsSignatureSchemes = this.manager.getContextData().getActiveCertsSignatureSchemes(false, this.sslParameters, getProtocolVersions(), this.jsseSecurityParameters.namedGroups);
        JsseSecurityParameters jsseSecurityParameters = this.jsseSecurityParameters;
        jsseSecurityParameters.localSigSchemes = activeCertsSignatureSchemes;
        jsseSecurityParameters.localSigSchemesCert = activeCertsSignatureSchemes;
        return SignatureSchemeInfo.getSignatureAndHashAlgorithms(jsseSecurityParameters.localSigSchemes);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector<SignatureAndHashAlgorithm> getSupportedSignatureAlgorithmsCert() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.AbstractTlsPeer
    public ProtocolVersion[] getSupportedVersions() {
        return this.manager.getContextData().getContext().getActiveProtocolVersions(this.sslParameters);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector<TrustedAuthority> getTrustedCAIndication() {
        Vector<X500Name> certificateAuthorities;
        if (!provClientEnableTrustedCAKeys || (certificateAuthorities = JsseUtils.getCertificateAuthorities(this.manager.getContextData().getX509TrustManager())) == null) {
            return null;
        }
        Vector<TrustedAuthority> vector = new Vector<>(certificateAuthorities.size());
        Iterator<X500Name> it2 = certificateAuthorities.iterator();
        while (it2.hasNext()) {
            vector.add(new TrustedAuthority((short) 2, it2.next()));
        }
        return vector;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsPeer
    public synchronized boolean isHandshakeComplete() {
        return this.handshakeComplete;
    }

    protected boolean isResumable(ProvSSLSession provSSLSession) {
        JsseSessionParameters jsseSessionParameters = provSSLSession.getJsseSessionParameters();
        String endpointIdentificationAlgorithm = this.sslParameters.getEndpointIdentificationAlgorithm();
        if (endpointIdentificationAlgorithm != null) {
            String identificationProtocol = jsseSessionParameters.getIdentificationProtocol();
            if (endpointIdentificationAlgorithm.equalsIgnoreCase(identificationProtocol)) {
                return true;
            }
            Logger logger = LOG;
            logger.finest("Session not resumed - endpoint ID algorithm mismatch; requested: " + endpointIdentificationAlgorithm + ", session: " + identificationProtocol);
            return false;
        }
        return true;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyAlertRaised(short s, short s2, String str, Throwable th) {
        super.notifyAlertRaised(s, s2, str, th);
        Level level = s == 1 ? Level.FINE : s2 == 80 ? Level.WARNING : Level.INFO;
        if (LOG.isLoggable(level)) {
            String alertLogMessage = JsseUtils.getAlertLogMessage("Client raised", s, s2);
            if (str != null) {
                alertLogMessage = GeneratedOutlineSupport1.outline75(alertLogMessage, RealTimeTextConstants.COLON_SPACE, str);
            }
            LOG.log(level, alertLogMessage, th);
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyAlertReceived(short s, short s2) {
        super.notifyAlertReceived(s, s2);
        Level level = s == 1 ? Level.FINE : Level.INFO;
        if (LOG.isLoggable(level)) {
            LOG.log(level, JsseUtils.getAlertLogMessage("Client received", s, s2));
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyHandshakeBeginning() throws IOException {
        super.notifyHandshakeBeginning();
        ContextData contextData = this.manager.getContextData();
        ProtocolVersion[] protocolVersions = getProtocolVersions();
        this.jsseSecurityParameters.namedGroups = contextData.getNamedGroups(this.sslParameters, protocolVersions);
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public synchronized void notifyHandshakeComplete() throws IOException {
        super.notifyHandshakeComplete();
        this.handshakeComplete = true;
        TlsSession session = this.context.getSession();
        if (this.sslSession == null || this.sslSession.getTlsSession() != session) {
            this.sslSession = this.manager.getContextData().getClientSessionContext().reportSession(this.manager.getPeerHost(), this.manager.getPeerPort(), session, new JsseSessionParameters(this.sslParameters.getEndpointIdentificationAlgorithm()));
        }
        this.manager.notifyHandshakeComplete(new ProvSSLConnection(this.context, this.sslSession));
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifySecureRenegotiation(boolean z) throws IOException {
        if (z || PropertyUtils.getBooleanSystemProperty("sun.security.ssl.allowLegacyHelloMessages", true)) {
            return;
        }
        throw new TlsFatalAlert((short) 40);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void notifySelectedCipherSuite(int i) {
        String validateNegotiatedCipherSuite = this.manager.getContextData().getContext().validateNegotiatedCipherSuite(this.sslParameters, i);
        Logger logger = LOG;
        logger.fine("Client notified of selected cipher suite: " + validateNegotiatedCipherSuite);
        super.notifySelectedCipherSuite(i);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void notifyServerVersion(ProtocolVersion protocolVersion) throws IOException {
        String validateNegotiatedProtocol = this.manager.getContextData().getContext().validateNegotiatedProtocol(this.sslParameters, protocolVersion);
        Logger logger = LOG;
        logger.fine("Client notified of selected protocol version: " + validateNegotiatedProtocol);
        super.notifyServerVersion(protocolVersion);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void notifySessionID(byte[] bArr) {
        ProvSSLSession provSSLSession;
        boolean z = bArr != null && bArr.length > 0 && (provSSLSession = this.sslSession) != null && Arrays.areEqual(bArr, provSSLSession.getId());
        if (z) {
            Logger logger = LOG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Server resumed session: ");
            outline107.append(Hex.toHexString(bArr));
            logger.fine(outline107.toString());
        } else {
            if (bArr == null || bArr.length < 1) {
                LOG.fine("Server did not specify a session ID");
            } else {
                Logger logger2 = LOG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Server specified new session: ");
                outline1072.append(Hex.toHexString(bArr));
                logger2.fine(outline1072.toString());
            }
            if (!this.manager.getEnableSessionCreation()) {
                throw new IllegalStateException("Server did not resume session and session creation is disabled");
            }
        }
        ProvSSLSessionContext clientSessionContext = this.manager.getContextData().getClientSessionContext();
        String peerHost = this.manager.getPeerHost();
        int peerPort = this.manager.getPeerPort();
        SecurityParameters securityParametersHandshake = this.context.getSecurityParametersHandshake();
        this.manager.notifyHandshakeSession(!z ? new ProvSSLSessionHandshake(clientSessionContext, peerHost, peerPort, securityParametersHandshake, this.jsseSecurityParameters) : new ProvSSLSessionResumed(clientSessionContext, peerHost, peerPort, securityParametersHandshake, this.jsseSecurityParameters, this.sslSession.getTlsSession(), this.sslSession.getJsseSessionParameters()));
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean requiresExtendedMasterSecret() {
        return !JsseUtils.allowLegacyMasterSecret();
    }

    protected TlsCredentials selectClientCredentials12(Principal[] principalArr, short[] sArr) throws IOException {
        short clientCertificateType;
        HashSet hashSet = new HashSet();
        for (SignatureSchemeInfo signatureSchemeInfo : this.jsseSecurityParameters.peerSigSchemes) {
            String keyType = JsseUtils.getKeyType(signatureSchemeInfo);
            if (!hashSet.contains(keyType) && (clientCertificateType = SignatureAlgorithm.getClientCertificateType(signatureSchemeInfo.getSignatureAlgorithm())) >= 0 && Arrays.contains(sArr, clientCertificateType) && this.jsseSecurityParameters.localSigSchemes.contains(signatureSchemeInfo)) {
                BCX509Key chooseClientKey = this.manager.chooseClientKey(new String[]{keyType}, principalArr);
                if (chooseClientKey != null) {
                    if (LOG.isLoggable(Level.FINE)) {
                        Logger logger = LOG;
                        logger.fine("Client (1.2) selected credentials for signature scheme '" + signatureSchemeInfo + "' (keyType '" + keyType + "'), with private key algorithm '" + JsseUtils.getPrivateKeyAlgorithm(chooseClientKey.getPrivateKey()) + "'");
                    }
                    return JsseUtils.createCredentialedSigner(this.context, mo12857getCrypto(), chooseClientKey, signatureSchemeInfo.getSignatureAndHashAlgorithm());
                }
                if (LOG.isLoggable(Level.FINER)) {
                    Logger logger2 = LOG;
                    logger2.finer("Client (1.2) found no credentials for signature scheme '" + signatureSchemeInfo + "' (keyType '" + keyType + "')");
                }
                hashSet.add(keyType);
            }
        }
        LOG.fine("Client (1.2) did not select any credentials");
        return null;
    }

    protected TlsCredentials selectClientCredentials13(Principal[] principalArr, byte[] bArr) throws IOException {
        HashSet hashSet = new HashSet();
        for (SignatureSchemeInfo signatureSchemeInfo : this.jsseSecurityParameters.peerSigSchemes) {
            if (signatureSchemeInfo.isSupported13() && this.jsseSecurityParameters.localSigSchemes.contains(signatureSchemeInfo)) {
                String keyType = JsseUtils.getKeyType(signatureSchemeInfo);
                if (!hashSet.contains(keyType)) {
                    BCX509Key chooseClientKey = this.manager.chooseClientKey(new String[]{keyType}, principalArr);
                    if (chooseClientKey != null) {
                        if (LOG.isLoggable(Level.FINE)) {
                            Logger logger = LOG;
                            logger.fine("Client (1.3) selected credentials for signature scheme '" + signatureSchemeInfo + "' (keyType '" + keyType + "'), with private key algorithm '" + JsseUtils.getPrivateKeyAlgorithm(chooseClientKey.getPrivateKey()) + "'");
                        }
                        return JsseUtils.createCredentialedSigner13(this.context, mo12857getCrypto(), chooseClientKey, signatureSchemeInfo.getSignatureAndHashAlgorithm(), bArr);
                    }
                    if (LOG.isLoggable(Level.FINER)) {
                        Logger logger2 = LOG;
                        logger2.finer("Client (1.3) found no credentials for signature scheme '" + signatureSchemeInfo + "' (keyType '" + keyType + "')");
                    }
                    hashSet.add(keyType);
                } else {
                    continue;
                }
            }
        }
        LOG.fine("Client (1.3) did not select any credentials");
        return null;
    }

    protected TlsCredentials selectClientCredentialsLegacy(Principal[] principalArr, short[] sArr) throws IOException {
        BCX509Key chooseClientKey = this.manager.chooseClientKey(getKeyTypesLegacy(sArr), principalArr);
        if (chooseClientKey == null) {
            return null;
        }
        return JsseUtils.createCredentialedSigner(this.context, mo12857getCrypto(), chooseClientKey, null);
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean shouldUseExtendedMasterSecret() {
        return JsseUtils.useExtendedMasterSecret();
    }
}
