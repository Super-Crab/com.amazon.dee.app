package org.bouncycastle.jsse.provider;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.jsse.BCSNIMatcher;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.tls.AlertDescription;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.CertificateRequest;
import org.bouncycastle.tls.CertificateStatus;
import org.bouncycastle.tls.DefaultTlsServer;
import org.bouncycastle.tls.ProtocolName;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsCredentials;
import org.bouncycastle.tls.TlsExtensionsUtils;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsSession;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ProvTlsServer extends DefaultTlsServer implements ProvTlsPeer {
    private static final boolean provServerEnableStatusRequest = false;
    protected TlsCredentials credentials;
    protected boolean handshakeComplete;
    protected final JsseSecurityParameters jsseSecurityParameters;
    protected Set<String> keyManagerMissCache;
    protected final ProvTlsManager manager;
    protected BCSNIServerName matchedSNIServerName;
    protected final ProvSSLParameters sslParameters;
    protected ProvSSLSession sslSession;
    private static final Logger LOG = Logger.getLogger(ProvTlsServer.class.getName());
    private static final int provEphemeralDHKeySize = PropertyUtils.getIntegerSystemProperty("jdk.tls.ephemeralDHKeySize", 2048, 1024, 8192);
    private static final boolean provServerEnableTrustedCAKeys = PropertyUtils.getBooleanSystemProperty("org.bouncycastle.jsse.server.enableTrustedCAKeysExtension", false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvTlsServer(ProvTlsManager provTlsManager, ProvSSLParameters provSSLParameters) throws SSLException {
        super(provTlsManager.getContextData().getCrypto());
        this.jsseSecurityParameters = new JsseSecurityParameters();
        this.sslSession = null;
        this.matchedSNIServerName = null;
        this.keyManagerMissCache = null;
        this.credentials = null;
        this.handshakeComplete = false;
        this.manager = provTlsManager;
        this.sslParameters = provSSLParameters.copyForConnection();
        if (provTlsManager.getEnableSessionCreation()) {
            return;
        }
        throw new SSLException("Session resumption not implemented yet and session creation is disabled");
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean allowCertificateStatus() {
        return false;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean allowLegacyResumption() {
        return JsseUtils.allowLegacyResumption();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean allowMultiCertStatus() {
        return false;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean allowTrustedCAIndication() {
        return this.jsseSecurityParameters.trustedIssuers != null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public CertificateRequest getCertificateRequest() throws IOException {
        Vector<SignatureAndHashAlgorithm> vector = null;
        if (!isClientAuthEnabled()) {
            return null;
        }
        ContextData contextData = this.manager.getContextData();
        ProtocolVersion serverVersion = this.context.getServerVersion();
        List<SignatureSchemeInfo> activeCertsSignatureSchemes = contextData.getActiveCertsSignatureSchemes(true, this.sslParameters, new ProtocolVersion[]{serverVersion}, this.jsseSecurityParameters.namedGroups);
        JsseSecurityParameters jsseSecurityParameters = this.jsseSecurityParameters;
        jsseSecurityParameters.localSigSchemes = activeCertsSignatureSchemes;
        jsseSecurityParameters.localSigSchemesCert = activeCertsSignatureSchemes;
        Vector<SignatureAndHashAlgorithm> signatureAndHashAlgorithms = SignatureSchemeInfo.getSignatureAndHashAlgorithms(jsseSecurityParameters.localSigSchemes);
        Vector<X500Name> certificateAuthorities = JsseUtils.getCertificateAuthorities(contextData.getX509TrustManager());
        if (!TlsUtils.isTLSv13(serverVersion)) {
            return new CertificateRequest(new short[]{64, 1, 2}, signatureAndHashAlgorithms, certificateAuthorities);
        }
        byte[] bArr = TlsUtils.EMPTY_BYTES;
        JsseSecurityParameters jsseSecurityParameters2 = this.jsseSecurityParameters;
        List<SignatureSchemeInfo> list = jsseSecurityParameters2.localSigSchemes;
        List<SignatureSchemeInfo> list2 = jsseSecurityParameters2.localSigSchemesCert;
        if (list != list2) {
            vector = SignatureSchemeInfo.getSignatureAndHashAlgorithms(list2);
        }
        return new CertificateRequest(bArr, signatureAndHashAlgorithms, vector, certificateAuthorities);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public CertificateStatus getCertificateStatus() throws IOException {
        return null;
    }

    @Override // org.bouncycastle.tls.DefaultTlsServer, org.bouncycastle.tls.TlsServer
    public TlsCredentials getCredentials() throws IOException {
        return this.credentials;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    /* renamed from: getCrypto  reason: collision with other method in class */
    public JcaTlsCrypto mo12857getCrypto() {
        return this.manager.getContextData().getCrypto();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int getMaximumNegotiableCurveBits() {
        return NamedGroupInfo.getMaximumBitsServerECDH(this.jsseSecurityParameters.namedGroups);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int getMaximumNegotiableFiniteFieldBits() {
        int maximumBitsServerFFDHE = NamedGroupInfo.getMaximumBitsServerFFDHE(this.jsseSecurityParameters.namedGroups);
        if (maximumBitsServerFFDHE >= provEphemeralDHKeySize) {
            return maximumBitsServerFFDHE;
        }
        return 0;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected Vector<ProtocolName> getProtocolNames() {
        return JsseUtils.getProtocolNames(this.sslParameters.getApplicationProtocols());
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public int getSelectedCipherSuite() throws IOException {
        ContextData contextData = this.manager.getContextData();
        SecurityParameters securityParametersHandshake = this.context.getSecurityParametersHandshake();
        ProvSSLSessionContext serverSessionContext = contextData.getServerSessionContext();
        String peerHost = this.manager.getPeerHost();
        int peerPort = this.manager.getPeerPort();
        ProvSSLSession provSSLSession = this.sslSession;
        this.manager.notifyHandshakeSession(provSSLSession == null ? new ProvSSLSessionHandshake(serverSessionContext, peerHost, peerPort, securityParametersHandshake, this.jsseSecurityParameters) : new ProvSSLSessionResumed(serverSessionContext, peerHost, peerPort, securityParametersHandshake, this.jsseSecurityParameters, provSSLSession.getTlsSession(), this.sslSession.getJsseSessionParameters()));
        NamedGroupInfo.notifyPeer(this.jsseSecurityParameters.namedGroups, securityParametersHandshake.getClientSupportedGroups());
        Vector<SignatureAndHashAlgorithm> clientSigAlgs = securityParametersHandshake.getClientSigAlgs();
        Vector<SignatureAndHashAlgorithm> clientSigAlgsCert = securityParametersHandshake.getClientSigAlgsCert();
        this.jsseSecurityParameters.peerSigSchemes = contextData.getSignatureSchemes(clientSigAlgs);
        JsseSecurityParameters jsseSecurityParameters = this.jsseSecurityParameters;
        jsseSecurityParameters.peerSigSchemesCert = clientSigAlgs == clientSigAlgsCert ? jsseSecurityParameters.peerSigSchemes : contextData.getSignatureSchemes(clientSigAlgsCert);
        if (DummyX509KeyManager.INSTANCE != contextData.getX509KeyManager()) {
            this.keyManagerMissCache = new HashSet();
            int selectedCipherSuite = super.getSelectedCipherSuite();
            this.keyManagerMissCache = null;
            String validateNegotiatedCipherSuite = this.manager.getContextData().getContext().validateNegotiatedCipherSuite(this.sslParameters, selectedCipherSuite);
            Logger logger = LOG;
            logger.fine("Server selected cipher suite: " + validateNegotiatedCipherSuite);
            return selectedCipherSuite;
        }
        throw new TlsFatalAlert((short) 40);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public Hashtable<Integer, byte[]> getServerExtensions() throws IOException {
        super.getServerExtensions();
        if (this.matchedSNIServerName != null) {
            TlsExtensionsUtils.addServerNameExtensionServer(checkServerExtensions());
        }
        return this.serverExtensions;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public ProtocolVersion getServerVersion() throws IOException {
        ProtocolVersion serverVersion = super.getServerVersion();
        String validateNegotiatedProtocol = this.manager.getContextData().getContext().validateNegotiatedProtocol(this.sslParameters, serverVersion);
        Logger logger = LOG;
        logger.fine("Server selected protocol version: " + validateNegotiatedProtocol);
        return serverVersion;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public TlsSession getSessionToResume(byte[] bArr) {
        TlsSession tlsSession;
        ProvSSLSession sessionImpl = this.manager.getContextData().getServerSessionContext().getSessionImpl(bArr);
        if (sessionImpl != null && (tlsSession = sessionImpl.getTlsSession()) != null && isResumable(sessionImpl)) {
            this.sslSession = sessionImpl;
            return tlsSession;
        } else if (!this.manager.getEnableSessionCreation()) {
            throw new IllegalStateException("No resumable sessions and session creation is disabled");
        } else {
            return null;
        }
    }

    @Override // org.bouncycastle.tls.DefaultTlsServer, org.bouncycastle.tls.AbstractTlsPeer
    protected int[] getSupportedCipherSuites() {
        return this.manager.getContextData().getContext().getActiveCipherSuites(mo12857getCrypto(), this.sslParameters, getProtocolVersions());
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public int[] getSupportedGroups() throws IOException {
        this.jsseSecurityParameters.namedGroups = this.manager.getContextData().getNamedGroups(this.sslParameters, new ProtocolVersion[]{this.context.getServerVersion()});
        return NamedGroupInfo.getSupportedGroupsLocalServer(this.jsseSecurityParameters.namedGroups);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.AbstractTlsPeer
    public ProtocolVersion[] getSupportedVersions() {
        return this.manager.getContextData().getContext().getActiveProtocolVersions(this.sslParameters);
    }

    protected boolean isClientAuthEnabled() {
        return this.sslParameters.getNeedClientAuth() || this.sslParameters.getWantClientAuth();
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsPeer
    public synchronized boolean isHandshakeComplete() {
        return this.handshakeComplete;
    }

    protected boolean isResumable(ProvSSLSession provSSLSession) {
        return false;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyAlertRaised(short s, short s2, String str, Throwable th) {
        Level level = s == 1 ? Level.FINE : s2 == 80 ? Level.WARNING : Level.INFO;
        if (LOG.isLoggable(level)) {
            String alertLogMessage = JsseUtils.getAlertLogMessage("Server raised", s, s2);
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
            LOG.log(level, JsseUtils.getAlertLogMessage("Server received", s, s2));
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public void notifyClientCertificate(Certificate certificate) throws IOException {
        if (isClientAuthEnabled()) {
            if (certificate == null || certificate.isEmpty()) {
                if (!this.sslParameters.getNeedClientAuth()) {
                    return;
                }
                throw new TlsFatalAlert(TlsUtils.isTLSv13(this.context) ? AlertDescription.certificate_required : (short) 40);
            }
            X509Certificate[] x509CertificateChain = JsseUtils.getX509CertificateChain(mo12857getCrypto(), certificate);
            TlsCertificate certificateAt = certificate.getCertificateAt(0);
            short s = 7;
            if (!certificateAt.supportsSignatureAlgorithm((short) 7)) {
                s = certificateAt.supportsSignatureAlgorithm((short) 8) ? (short) 8 : certificateAt.getLegacySignatureAlgorithm();
            }
            if (s < 0) {
                throw new TlsFatalAlert((short) 43);
            }
            this.manager.checkClientTrusted(x509CertificateChain, JsseUtils.getAuthTypeClient(s));
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public synchronized void notifyHandshakeComplete() throws IOException {
        super.notifyHandshakeComplete();
        this.handshakeComplete = true;
        TlsSession session = this.context.getSession();
        if (this.sslSession == null || this.sslSession.getTlsSession() != session) {
            this.sslSession = this.manager.getContextData().getServerSessionContext().reportSession(this.manager.getPeerHost(), this.manager.getPeerPort(), session, new JsseSessionParameters(this.sslParameters.getEndpointIdentificationAlgorithm()));
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

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean preferLocalCipherSuites() {
        return this.sslParameters.getUseCipherSuitesOrder();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public void processClientExtensions(Hashtable hashtable) throws IOException {
        Logger logger;
        String str;
        super.processClientExtensions(hashtable);
        Vector clientServerNames = this.context.getSecurityParametersHandshake().getClientServerNames();
        if (clientServerNames != null) {
            Collection<BCSNIMatcher> sNIMatchers = this.sslParameters.getSNIMatchers();
            if (sNIMatchers == null || sNIMatchers.isEmpty()) {
                logger = LOG;
                str = "Server ignored SNI (no matchers specified)";
            } else {
                this.matchedSNIServerName = JsseUtils.findMatchingSNIServerName(clientServerNames, sNIMatchers);
                if (this.matchedSNIServerName == null) {
                    throw new TlsFatalAlert(AlertDescription.unrecognized_name);
                }
                logger = LOG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Server accepted SNI: ");
                outline107.append(this.matchedSNIServerName);
                str = outline107.toString();
            }
            logger.fine(str);
        }
        if (provServerEnableTrustedCAKeys) {
            Vector vector = this.trustedCAKeys;
            this.jsseSecurityParameters.trustedIssuers = JsseUtils.getTrustedIssuers(vector);
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean requiresExtendedMasterSecret() {
        return !JsseUtils.allowLegacyMasterSecret();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean selectCipherSuite(int i) throws IOException {
        TlsCredentials selectCredentials = selectCredentials(this.jsseSecurityParameters.trustedIssuers, i);
        if (selectCredentials != null) {
            boolean selectCipherSuite = super.selectCipherSuite(i);
            if (selectCipherSuite) {
                this.credentials = selectCredentials;
            }
            return selectCipherSuite;
        }
        String cipherSuiteName = ProvSSLContextSpi.getCipherSuiteName(i);
        Logger logger = LOG;
        logger.finer("Server found no credentials for cipher suite: " + cipherSuiteName);
        return false;
    }

    protected TlsCredentials selectCredentials(Principal[] principalArr, int i) throws IOException {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(i);
        if (keyExchangeAlgorithm != 0) {
            if (keyExchangeAlgorithm != 1 && keyExchangeAlgorithm != 3 && keyExchangeAlgorithm != 5 && keyExchangeAlgorithm != 17 && keyExchangeAlgorithm != 19) {
                return null;
            }
            return (1 == keyExchangeAlgorithm || !TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.context.getServerVersion())) ? selectServerCredentialsLegacy(principalArr, keyExchangeAlgorithm) : selectServerCredentials12(principalArr, keyExchangeAlgorithm);
        }
        return selectServerCredentials13(principalArr, TlsUtils.EMPTY_BYTES);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int selectDH(int i) {
        return NamedGroupInfo.selectServerFFDHE(this.jsseSecurityParameters.namedGroups, Math.max(i, provEphemeralDHKeySize));
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int selectDHDefault(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int selectECDH(int i) {
        return NamedGroupInfo.selectServerECDH(this.jsseSecurityParameters.namedGroups, i);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int selectECDHDefault(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected ProtocolName selectProtocolName() throws IOException {
        if (this.sslParameters.getEngineAPSelector() == null && this.sslParameters.getSocketAPSelector() == null) {
            return super.selectProtocolName();
        }
        List<String> protocolNames = JsseUtils.getProtocolNames(this.clientProtocolNames);
        String selectApplicationProtocol = this.manager.selectApplicationProtocol(Collections.unmodifiableList(protocolNames));
        if (selectApplicationProtocol == null) {
            throw new TlsFatalAlert(AlertDescription.no_application_protocol);
        }
        if (selectApplicationProtocol.length() < 1) {
            return null;
        }
        if (!protocolNames.contains(selectApplicationProtocol)) {
            throw new TlsFatalAlert(AlertDescription.no_application_protocol);
        }
        return ProtocolName.asUtf8Encoding(selectApplicationProtocol);
    }

    protected TlsCredentials selectServerCredentials12(Principal[] principalArr, int i) throws IOException {
        BCAlgorithmConstraints algorithmConstraints = this.sslParameters.getAlgorithmConstraints();
        boolean isTLSv13 = TlsUtils.isTLSv13(this.context);
        boolean z = !isTLSv13;
        short legacySignatureAlgorithmServer = TlsUtils.getLegacySignatureAlgorithmServer(i);
        for (SignatureSchemeInfo signatureSchemeInfo : this.jsseSecurityParameters.peerSigSchemes) {
            if (TlsUtils.isValidSignatureSchemeForServerKeyExchange(signatureSchemeInfo.getSignatureScheme(), i)) {
                short signatureAlgorithm = signatureSchemeInfo.getSignatureAlgorithm();
                String keyTypeLegacyServer = legacySignatureAlgorithmServer == signatureAlgorithm ? JsseUtils.getKeyTypeLegacyServer(i) : JsseUtils.getKeyType(signatureSchemeInfo);
                if (!this.keyManagerMissCache.contains(keyTypeLegacyServer) && signatureSchemeInfo.isActive(algorithmConstraints, z, isTLSv13, this.jsseSecurityParameters.namedGroups)) {
                    BCX509Key chooseServerKey = this.manager.chooseServerKey(keyTypeLegacyServer, principalArr);
                    if (chooseServerKey != null && JsseUtils.isUsableKeyForServer(signatureAlgorithm, chooseServerKey.getPrivateKey())) {
                        if (LOG.isLoggable(Level.FINE)) {
                            LOG.fine("Server (1.2) selected credentials for signature scheme '" + signatureSchemeInfo + "' (keyType '" + keyTypeLegacyServer + "'), with private key algorithm '" + JsseUtils.getPrivateKeyAlgorithm(chooseServerKey.getPrivateKey()) + "'");
                        }
                        return JsseUtils.createCredentialedSigner(this.context, mo12857getCrypto(), chooseServerKey, signatureSchemeInfo.getSignatureAndHashAlgorithm());
                    }
                    if (LOG.isLoggable(Level.FINER)) {
                        LOG.finer("Server (1.2) found no credentials for signature scheme '" + signatureSchemeInfo + "' (keyType '" + keyTypeLegacyServer + "')");
                    }
                    this.keyManagerMissCache.add(keyTypeLegacyServer);
                }
            }
        }
        LOG.fine("Server (1.2) did not select any credentials");
        return null;
    }

    protected TlsCredentials selectServerCredentials13(Principal[] principalArr, byte[] bArr) throws IOException {
        BCAlgorithmConstraints algorithmConstraints = this.sslParameters.getAlgorithmConstraints();
        for (SignatureSchemeInfo signatureSchemeInfo : this.jsseSecurityParameters.peerSigSchemes) {
            String keyType = JsseUtils.getKeyType(signatureSchemeInfo);
            if (!this.keyManagerMissCache.contains(keyType) && signatureSchemeInfo.isActive(algorithmConstraints, false, true, this.jsseSecurityParameters.namedGroups)) {
                BCX509Key chooseServerKey = this.manager.chooseServerKey(keyType, principalArr);
                if (chooseServerKey != null && JsseUtils.isUsableKeyForServer(signatureSchemeInfo.getSignatureAlgorithm(), chooseServerKey.getPrivateKey())) {
                    if (LOG.isLoggable(Level.FINE)) {
                        Logger logger = LOG;
                        logger.fine("Server (1.3) selected credentials for signature scheme '" + signatureSchemeInfo + "' (keyType '" + keyType + "'), with private key algorithm '" + JsseUtils.getPrivateKeyAlgorithm(chooseServerKey.getPrivateKey()) + "'");
                    }
                    return JsseUtils.createCredentialedSigner13(this.context, mo12857getCrypto(), chooseServerKey, signatureSchemeInfo.getSignatureAndHashAlgorithm(), bArr);
                }
                if (LOG.isLoggable(Level.FINER)) {
                    Logger logger2 = LOG;
                    logger2.finer("Server (1.3) found no credentials for signature scheme '" + signatureSchemeInfo + "' (keyType '" + keyType + "')");
                }
                this.keyManagerMissCache.add(keyType);
            }
        }
        LOG.fine("Server (1.3) did not select any credentials");
        return null;
    }

    protected TlsCredentials selectServerCredentialsLegacy(Principal[] principalArr, int i) throws IOException {
        String keyTypeLegacyServer = JsseUtils.getKeyTypeLegacyServer(i);
        if (this.keyManagerMissCache.contains(keyTypeLegacyServer)) {
            return null;
        }
        BCX509Key chooseServerKey = this.manager.chooseServerKey(keyTypeLegacyServer, principalArr);
        if (chooseServerKey != null && JsseUtils.isUsableKeyForServerLegacy(i, chooseServerKey.getPrivateKey())) {
            return 1 == i ? JsseUtils.createCredentialedDecryptor(mo12857getCrypto(), chooseServerKey) : JsseUtils.createCredentialedSigner(this.context, mo12857getCrypto(), chooseServerKey, null);
        }
        this.keyManagerMissCache.add(keyTypeLegacyServer);
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean shouldSelectProtocolNameEarly() {
        return this.sslParameters.getEngineAPSelector() == null && this.sslParameters.getSocketAPSelector() == null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean shouldUseExtendedMasterSecret() {
        return JsseUtils.useExtendedMasterSecret();
    }
}
