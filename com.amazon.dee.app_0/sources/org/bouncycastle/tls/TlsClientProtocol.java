package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* loaded from: classes5.dex */
public class TlsClientProtocol extends TlsProtocol {
    protected TlsAuthentication authentication;
    protected CertificateRequest certificateRequest;
    protected CertificateStatus certificateStatus;
    protected Hashtable clientAgreements;
    protected ClientHello clientHello;
    protected TlsKeyExchange keyExchange;
    protected TlsClient tlsClient;
    TlsClientContextImpl tlsClientContext;

    public TlsClientProtocol() {
        this.tlsClient = null;
        this.tlsClientContext = null;
        this.clientAgreements = null;
        this.clientHello = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    public TlsClientProtocol(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
        this.tlsClient = null;
        this.tlsClientContext = null;
        this.clientAgreements = null;
        this.clientHello = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.TlsProtocol
    public void beginHandshake() throws IOException {
        SessionParameters exportSessionParameters;
        super.beginHandshake();
        TlsSession sessionToResume = this.tlsClient.getSessionToResume();
        if (sessionToResume != null && sessionToResume.isResumable() && (exportSessionParameters = sessionToResume.exportSessionParameters()) != null && (exportSessionParameters.isExtendedMasterSecret() || (!this.tlsClient.requiresExtendedMasterSecret() && this.tlsClient.allowLegacyResumption()))) {
            TlsSecret masterSecret = exportSessionParameters.getMasterSecret();
            synchronized (masterSecret) {
                if (masterSecret.isAlive()) {
                    this.tlsSession = sessionToResume;
                    this.sessionParameters = exportSessionParameters;
                    this.sessionMasterSecret = this.tlsClientContext.getCrypto().adoptSecret(masterSecret);
                }
            }
        }
        sendClientHello();
        this.connection_state = (short) 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.TlsProtocol
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.clientAgreements = null;
        this.clientHello = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    public void connect(TlsClient tlsClient) throws IOException {
        if (tlsClient != null) {
            if (this.tlsClient != null) {
                throw new IllegalStateException("'connect' can only be called once");
            }
            this.tlsClient = tlsClient;
            this.tlsClientContext = new TlsClientContextImpl(tlsClient.mo12857getCrypto());
            tlsClient.init(this.tlsClientContext);
            this.recordStream.init(this.tlsClientContext);
            tlsClient.notifyCloseHandle(this);
            beginHandshake();
            if (!this.blocking) {
                return;
            }
            blockForHandshake();
            return;
        }
        throw new IllegalArgumentException("'tlsClient' cannot be null");
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected TlsContext getContext() {
        return this.tlsClientContext;
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    AbstractTlsContext getContextAdmin() {
        return this.tlsClientContext;
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected TlsPeer getPeer() {
        return this.tlsClient;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void handle13HandshakeMessage(short r9, org.bouncycastle.tls.HandshakeMessageInput r10) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.TlsClientProtocol.handle13HandshakeMessage(short, org.bouncycastle.tls.HandshakeMessageInput):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0126  */
    @Override // org.bouncycastle.tls.TlsProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void handleHandshakeMessage(short r12, org.bouncycastle.tls.HandshakeMessageInput r13) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 664
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.TlsClientProtocol.handleHandshakeMessage(short, org.bouncycastle.tls.HandshakeMessageInput):void");
    }

    protected void handleServerCertificate() throws IOException {
        TlsUtils.processServerCertificate(this.tlsClientContext, this.certificateStatus, this.keyExchange, this.authentication, this.clientExtensions, this.serverExtensions);
    }

    protected void handleSupplementalData(Vector vector) throws IOException {
        this.tlsClient.processServerSupplementalData(vector);
        this.connection_state = (short) 6;
        this.keyExchange = TlsUtils.initKeyExchangeClient(this.tlsClientContext, this.tlsClient);
    }

    protected void process13HelloRetryRequest(ServerHello serverHello) throws IOException {
        this.recordStream.setWriteVersion(ProtocolVersion.TLSv12);
        SecurityParameters securityParametersHandshake = this.tlsClientContext.getSecurityParametersHandshake();
        ProtocolVersion version = serverHello.getVersion();
        byte[] sessionID = serverHello.getSessionID();
        int cipherSuite = serverHello.getCipherSuite();
        if (!ProtocolVersion.TLSv12.equals(version) || !Arrays.areEqual(this.clientHello.getSessionID(), sessionID) || !TlsUtils.isValidCipherSuiteSelection(this.clientHello.getCipherSuites(), cipherSuite)) {
            throw new TlsFatalAlert((short) 47);
        }
        Hashtable extensions = serverHello.getExtensions();
        if (extensions == null) {
            throw new TlsFatalAlert((short) 47);
        }
        ProtocolVersion supportedVersionsExtensionServer = TlsExtensionsUtils.getSupportedVersionsExtensionServer(extensions);
        if (supportedVersionsExtensionServer == null) {
            throw new TlsFatalAlert(AlertDescription.missing_extension);
        }
        if (!ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(supportedVersionsExtensionServer) || !ProtocolVersion.contains(this.tlsClientContext.getClientSupportedVersions(), supportedVersionsExtensionServer) || !TlsUtils.isValidVersionForCipherSuite(cipherSuite, supportedVersionsExtensionServer)) {
            throw new TlsFatalAlert((short) 47);
        }
        int keyShareHelloRetryRequest = TlsExtensionsUtils.getKeyShareHelloRetryRequest(extensions);
        if (!TlsUtils.isValidKeyShareSelection(supportedVersionsExtensionServer, securityParametersHandshake.getClientSupportedGroups(), this.clientAgreements, keyShareHelloRetryRequest)) {
            throw new TlsFatalAlert((short) 47);
        }
        byte[] cookieExtension = TlsExtensionsUtils.getCookieExtension(extensions);
        securityParametersHandshake.negotiatedVersion = supportedVersionsExtensionServer;
        TlsUtils.negotiatedVersionTLSClient(this.tlsClientContext, this.tlsClient);
        this.resumedSession = false;
        byte[] bArr = TlsUtils.EMPTY_BYTES;
        securityParametersHandshake.sessionID = bArr;
        this.tlsClient.notifySessionID(bArr);
        TlsUtils.negotiatedCipherSuite(securityParametersHandshake, cipherSuite);
        this.tlsClient.notifySelectedCipherSuite(cipherSuite);
        this.clientAgreements = null;
        this.retryCookie = cookieExtension;
        this.retryGroup = keyShareHelloRetryRequest;
    }

    protected void process13ServerHello(ServerHello serverHello, boolean z) throws IOException {
        SecurityParameters securityParametersHandshake = this.tlsClientContext.getSecurityParametersHandshake();
        ProtocolVersion version = serverHello.getVersion();
        byte[] sessionID = serverHello.getSessionID();
        int cipherSuite = serverHello.getCipherSuite();
        if (!ProtocolVersion.TLSv12.equals(version) || !Arrays.areEqual(this.clientHello.getSessionID(), sessionID)) {
            throw new TlsFatalAlert((short) 47);
        }
        Hashtable extensions = serverHello.getExtensions();
        if (extensions == null) {
            throw new TlsFatalAlert((short) 47);
        }
        if (z) {
            ProtocolVersion supportedVersionsExtensionServer = TlsExtensionsUtils.getSupportedVersionsExtensionServer(extensions);
            if (supportedVersionsExtensionServer == null) {
                throw new TlsFatalAlert(AlertDescription.missing_extension);
            }
            if (!securityParametersHandshake.getNegotiatedVersion().equals(supportedVersionsExtensionServer) || securityParametersHandshake.getCipherSuite() != cipherSuite) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if (!TlsUtils.isValidCipherSuiteSelection(this.clientHello.getCipherSuites(), cipherSuite) || !TlsUtils.isValidVersionForCipherSuite(cipherSuite, securityParametersHandshake.getNegotiatedVersion())) {
            throw new TlsFatalAlert((short) 47);
        } else {
            this.resumedSession = false;
            byte[] bArr = TlsUtils.EMPTY_BYTES;
            securityParametersHandshake.sessionID = bArr;
            this.tlsClient.notifySessionID(bArr);
            TlsUtils.negotiatedCipherSuite(securityParametersHandshake, cipherSuite);
            this.tlsClient.notifySelectedCipherSuite(cipherSuite);
        }
        this.clientHello = null;
        securityParametersHandshake.serverRandom = serverHello.getRandom();
        securityParametersHandshake.secureRenegotiation = false;
        securityParametersHandshake.extendedMasterSecret = true;
        KeyShareEntry keyShareServerHello = TlsExtensionsUtils.getKeyShareServerHello(extensions);
        if (keyShareServerHello == null) {
            throw new TlsFatalAlert((short) 47);
        }
        TlsAgreement tlsAgreement = (TlsAgreement) this.clientAgreements.get(Integers.valueOf(keyShareServerHello.getNamedGroup()));
        if (tlsAgreement == null) {
            throw new TlsFatalAlert((short) 47);
        }
        this.clientAgreements = null;
        tlsAgreement.receivePeerValue(keyShareServerHello.getKeyExchange());
        securityParametersHandshake.sharedSecret = tlsAgreement.calculateSecret();
        TlsUtils.establish13PhaseSecrets(this.tlsClientContext);
        invalidateSession();
        this.tlsSession = TlsUtils.importSession(securityParametersHandshake.getSessionID(), null);
        this.sessionParameters = null;
        this.sessionMasterSecret = null;
    }

    protected void process13ServerHelloCoda(ServerHello serverHello, boolean z) throws IOException {
        TlsUtils.establish13PhaseHandshake(this.tlsClientContext, TlsUtils.getCurrentPRFHash(this.handshakeHash), this.recordStream);
        if (!z) {
            this.recordStream.setIgnoreChangeCipherSpec(true);
            sendChangeCipherSpecMessage();
        }
        this.recordStream.enablePendingCipherWrite();
        this.recordStream.enablePendingCipherRead(false);
    }

    protected void processServerHello(ServerHello serverHello) throws IOException {
        TlsSession tlsSession;
        Hashtable extensions = serverHello.getExtensions();
        ProtocolVersion version = serverHello.getVersion();
        ProtocolVersion supportedVersionsExtensionServer = TlsExtensionsUtils.getSupportedVersionsExtensionServer(extensions);
        if (supportedVersionsExtensionServer != null) {
            if (!ProtocolVersion.TLSv12.equals(version) || !ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(supportedVersionsExtensionServer)) {
                throw new TlsFatalAlert((short) 47);
            }
            version = supportedVersionsExtensionServer;
        }
        SecurityParameters securityParametersHandshake = this.tlsClientContext.getSecurityParametersHandshake();
        if (ProtocolVersion.contains(this.tlsClientContext.getClientSupportedVersions(), version)) {
            this.recordStream.setWriteVersion(version.isLaterVersionOf(ProtocolVersion.TLSv12) ? ProtocolVersion.TLSv12 : version);
            securityParametersHandshake.negotiatedVersion = version;
            TlsUtils.negotiatedVersionTLSClient(this.tlsClientContext, this.tlsClient);
            boolean z = false;
            if (ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(version)) {
                process13ServerHello(serverHello, false);
                return;
            }
            int[] cipherSuites = this.clientHello.getCipherSuites();
            this.clientHello = null;
            this.retryCookie = null;
            this.retryGroup = -1;
            securityParametersHandshake.serverRandom = serverHello.getRandom();
            if (!this.tlsClientContext.getClientVersion().equals(version)) {
                TlsUtils.checkDowngradeMarker(version, securityParametersHandshake.getServerRandom());
            }
            byte[] sessionID = serverHello.getSessionID();
            securityParametersHandshake.sessionID = sessionID;
            this.tlsClient.notifySessionID(sessionID);
            this.resumedSession = sessionID.length > 0 && (tlsSession = this.tlsSession) != null && Arrays.areEqual(sessionID, tlsSession.getSessionID());
            int cipherSuite = serverHello.getCipherSuite();
            if (!TlsUtils.isValidCipherSuiteSelection(cipherSuites, cipherSuite) || !TlsUtils.isValidVersionForCipherSuite(cipherSuite, securityParametersHandshake.getNegotiatedVersion())) {
                throw new TlsFatalAlert((short) 47);
            }
            TlsUtils.negotiatedCipherSuite(securityParametersHandshake, cipherSuite);
            this.tlsClient.notifySelectedCipherSuite(cipherSuite);
            this.serverExtensions = extensions;
            Hashtable hashtable = this.serverExtensions;
            if (hashtable != null) {
                Enumeration keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    Integer num = (Integer) keys.nextElement();
                    if (!num.equals(TlsProtocol.EXT_RenegotiationInfo) && TlsUtils.getExtensionData(this.clientExtensions, num) == null) {
                        throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                    }
                }
            }
            byte[] extensionData = TlsUtils.getExtensionData(this.serverExtensions, TlsProtocol.EXT_RenegotiationInfo);
            if (extensionData == null) {
                securityParametersHandshake.secureRenegotiation = false;
            } else {
                securityParametersHandshake.secureRenegotiation = true;
                if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                    throw new TlsFatalAlert((short) 40);
                }
            }
            this.tlsClient.notifySecureRenegotiation(securityParametersHandshake.isSecureRenegotiation());
            boolean hasExtendedMasterSecretExtension = TlsExtensionsUtils.hasExtendedMasterSecretExtension(this.serverExtensions);
            if (hasExtendedMasterSecretExtension) {
                if (version.isSSL() || (!this.resumedSession && !this.tlsClient.shouldUseExtendedMasterSecret())) {
                    throw new TlsFatalAlert((short) 40);
                }
            } else if (this.tlsClient.requiresExtendedMasterSecret() || (this.resumedSession && !this.tlsClient.allowLegacyResumption())) {
                throw new TlsFatalAlert((short) 40);
            }
            securityParametersHandshake.extendedMasterSecret = hasExtendedMasterSecretExtension;
            securityParametersHandshake.applicationProtocol = TlsExtensionsUtils.getALPNExtensionServer(this.serverExtensions);
            securityParametersHandshake.applicationProtocolSet = true;
            Hashtable hashtable2 = this.clientExtensions;
            Hashtable hashtable3 = this.serverExtensions;
            if (this.resumedSession) {
                if (securityParametersHandshake.getCipherSuite() != this.sessionParameters.getCipherSuite() || this.sessionParameters.getCompressionAlgorithm() != 0 || !version.equals(this.sessionParameters.getNegotiatedVersion())) {
                    throw new TlsFatalAlert((short) 47);
                }
                hashtable3 = this.sessionParameters.readServerExtensions();
                hashtable2 = null;
            }
            if (hashtable3 != null && !hashtable3.isEmpty()) {
                boolean hasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable3);
                if (hasEncryptThenMACExtension && !TlsUtils.isBlockCipherSuite(securityParametersHandshake.getCipherSuite())) {
                    throw new TlsFatalAlert((short) 47);
                }
                securityParametersHandshake.encryptThenMAC = hasEncryptThenMACExtension;
                securityParametersHandshake.maxFragmentLength = processMaxFragmentLengthExtension(hashtable2, hashtable3, (short) 47);
                securityParametersHandshake.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable3);
                if (!this.resumedSession) {
                    if (TlsUtils.hasExpectedEmptyExtensionData(hashtable3, TlsExtensionsUtils.EXT_status_request_v2, (short) 47)) {
                        securityParametersHandshake.statusRequestVersion = 2;
                    } else if (TlsUtils.hasExpectedEmptyExtensionData(hashtable3, TlsExtensionsUtils.EXT_status_request, (short) 47)) {
                        securityParametersHandshake.statusRequestVersion = 1;
                    }
                }
                if (!this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(hashtable3, TlsProtocol.EXT_SessionTicket, (short) 47)) {
                    z = true;
                }
                this.expectSessionTicket = z;
            }
            if (hashtable2 != null) {
                this.tlsClient.processServerExtensions(hashtable3);
            }
            applyMaxFragmentLengthExtension();
            if (this.resumedSession) {
                securityParametersHandshake.masterSecret = this.sessionMasterSecret;
                this.recordStream.setPendingCipher(TlsUtils.initCipher(this.tlsClientContext));
                return;
            }
            invalidateSession();
            this.tlsSession = TlsUtils.importSession(securityParametersHandshake.getSessionID(), null);
            this.sessionParameters = null;
            this.sessionMasterSecret = null;
            return;
        }
        throw new TlsFatalAlert((short) 70);
    }

    protected void receive13CertificateRequest(ByteArrayInputStream byteArrayInputStream, boolean z) throws IOException {
        CertificateRequest parse = CertificateRequest.parse(this.tlsClientContext, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (!z) {
            if (!parse.hasCertificateRequestContext(TlsUtils.EMPTY_BYTES)) {
                throw new TlsFatalAlert((short) 47);
            }
            this.certificateRequest = parse;
            TlsUtils.establishServerSigAlgs(this.tlsClientContext.getSecurityParametersHandshake(), parse);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void receive13EncryptedExtensions(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] readOpaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        this.serverExtensions = TlsProtocol.readExtensionsData(readOpaque16);
        this.tlsClient.processServerExtensions(this.serverExtensions);
    }

    protected void receive13NewSessionTicket(ByteArrayInputStream byteArrayInputStream) throws IOException {
        TlsUtils.readUint32(byteArrayInputStream);
        TlsUtils.readUint32(byteArrayInputStream);
        TlsUtils.readOpaque8(byteArrayInputStream);
        TlsUtils.readOpaque16(byteArrayInputStream);
        TlsUtils.readOpaque16(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected void receive13ServerCertificate(ByteArrayInputStream byteArrayInputStream) throws IOException {
        this.authentication = TlsUtils.receiveServerCertificate(this.tlsClientContext, this.tlsClient, byteArrayInputStream);
        handleServerCertificate();
    }

    protected void receive13ServerCertificateVerify(ByteArrayInputStream byteArrayInputStream) throws IOException {
        Certificate peerCertificate = this.tlsClientContext.getSecurityParametersHandshake().getPeerCertificate();
        if (peerCertificate == null || peerCertificate.isEmpty()) {
            throw new TlsFatalAlert((short) 80);
        }
        DigitallySigned parse = DigitallySigned.parse(this.tlsClientContext, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        TlsUtils.verify13CertificateVerifyServer(this.tlsClientContext, parse, this.handshakeHash);
    }

    protected void receive13ServerFinished(ByteArrayInputStream byteArrayInputStream) throws IOException {
        processFinishedMessage(byteArrayInputStream);
    }

    protected void receive13ServerKeyUpdate(ByteArrayInputStream byteArrayInputStream) throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    protected void receiveCertificateRequest(ByteArrayInputStream byteArrayInputStream) throws IOException {
        if (this.authentication != null) {
            CertificateRequest parse = CertificateRequest.parse(this.tlsClientContext, byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            this.certificateRequest = TlsUtils.validateCertificateRequest(parse, this.keyExchange);
            return;
        }
        throw new TlsFatalAlert((short) 40);
    }

    protected void receiveNewSessionTicket(ByteArrayInputStream byteArrayInputStream) throws IOException {
        NewSessionTicket parse = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        this.tlsClient.notifyNewSessionTicket(parse);
    }

    protected ServerHello receiveServerHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        return ServerHello.parse(byteArrayInputStream);
    }

    protected void send13ClientHelloRetry() throws IOException {
        Hashtable extensions = this.clientHello.getExtensions();
        extensions.remove(TlsExtensionsUtils.EXT_cookie);
        extensions.remove(TlsExtensionsUtils.EXT_early_data);
        extensions.remove(TlsExtensionsUtils.EXT_key_share);
        byte[] bArr = this.retryCookie;
        if (bArr != null) {
            TlsExtensionsUtils.addCookieExtension(extensions, bArr);
            this.retryCookie = null;
        }
        int i = this.retryGroup;
        if (i >= 0) {
            this.clientAgreements = TlsUtils.addKeyShareToClientHelloRetry(this.tlsClientContext, extensions, i);
            this.recordStream.setIgnoreChangeCipherSpec(true);
            sendChangeCipherSpecMessage();
            sendClientHelloMessage();
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void sendCertificateVerifyMessage(DigitallySigned digitallySigned) throws IOException {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 15);
        digitallySigned.encode(handshakeMessageOutput);
        handshakeMessageOutput.send(this);
    }

    protected void sendClientHello() throws IOException {
        RecordStream recordStream;
        ProtocolVersion protocolVersion;
        ProtocolVersion protocolVersion2;
        byte[] bArr;
        SessionParameters sessionParameters;
        SecurityParameters securityParametersHandshake = this.tlsClientContext.getSecurityParametersHandshake();
        this.tlsClientContext.setClientSupportedVersions(this.tlsClient.getProtocolVersions());
        if (ProtocolVersion.contains(this.tlsClientContext.getClientSupportedVersions(), ProtocolVersion.SSLv3)) {
            recordStream = this.recordStream;
            protocolVersion = ProtocolVersion.SSLv3;
        } else {
            recordStream = this.recordStream;
            protocolVersion = ProtocolVersion.TLSv10;
        }
        recordStream.setWriteVersion(protocolVersion);
        ProtocolVersion latestTLS = ProtocolVersion.getLatestTLS(this.tlsClientContext.getClientSupportedVersions());
        if (ProtocolVersion.isSupportedTLSVersionClient(latestTLS)) {
            this.tlsClientContext.setClientVersion(latestTLS);
            boolean isEqualOrEarlierVersionOf = ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(latestTLS);
            byte[] sessionID = TlsUtils.getSessionID(this.tlsSession);
            boolean isFallback = this.tlsClient.isFallback();
            int[] cipherSuites = this.tlsClient.getCipherSuites();
            if (sessionID.length > 0 && (sessionParameters = this.sessionParameters) != null && (!Arrays.contains(cipherSuites, sessionParameters.getCipherSuite()) || this.sessionParameters.getCompressionAlgorithm() != 0)) {
                sessionID = TlsUtils.EMPTY_BYTES;
            }
            this.clientExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.tlsClient.getClientExtensions());
            if (isEqualOrEarlierVersionOf) {
                ProtocolVersion protocolVersion3 = ProtocolVersion.TLSv12;
                TlsExtensionsUtils.addSupportedVersionsExtensionClient(this.clientExtensions, this.tlsClientContext.getClientSupportedVersions());
                if (sessionID.length < 1) {
                    sessionID = this.tlsClientContext.getNonceGenerator().generateNonce(32);
                }
                bArr = sessionID;
                protocolVersion2 = protocolVersion3;
            } else {
                protocolVersion2 = latestTLS;
                bArr = sessionID;
            }
            this.tlsClientContext.setRSAPreMasterSecretVersion(protocolVersion2);
            securityParametersHandshake.clientServerNames = TlsExtensionsUtils.getServerNameExtensionClient(this.clientExtensions);
            if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(latestTLS)) {
                TlsUtils.establishClientSigAlgs(securityParametersHandshake, this.clientExtensions);
            }
            securityParametersHandshake.clientSupportedGroups = TlsExtensionsUtils.getSupportedGroupsExtension(this.clientExtensions);
            this.clientAgreements = TlsUtils.addEarlyKeySharesToClientHello(this.tlsClientContext, this.tlsClient, this.clientExtensions);
            if (TlsUtils.isExtendedMasterSecretOptionalTLS(this.tlsClientContext.getClientSupportedVersions()) && this.tlsClient.shouldUseExtendedMasterSecret()) {
                TlsExtensionsUtils.addExtendedMasterSecretExtension(this.clientExtensions);
            } else if (!isEqualOrEarlierVersionOf && this.tlsClient.requiresExtendedMasterSecret()) {
                throw new TlsFatalAlert((short) 80);
            }
            boolean z = false;
            securityParametersHandshake.clientRandom = TlsProtocol.createRandomBlock(!isEqualOrEarlierVersionOf && this.tlsClient.shouldUseGMTUnixTime(), this.tlsClientContext);
            if (TlsUtils.getExtensionData(this.clientExtensions, TlsProtocol.EXT_RenegotiationInfo) == null) {
                z = true;
            }
            boolean z2 = !Arrays.contains(cipherSuites, 255);
            if (z && z2) {
                cipherSuites = Arrays.append(cipherSuites, 255);
            }
            this.clientHello = new ClientHello(protocolVersion2, securityParametersHandshake.getClientRandom(), bArr, null, (!isFallback || Arrays.contains(cipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV)) ? cipherSuites : Arrays.append(cipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV), this.clientExtensions);
            sendClientHelloMessage();
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void sendClientHelloMessage() throws IOException {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 1);
        this.clientHello.encode(this.tlsClientContext, handshakeMessageOutput);
        handshakeMessageOutput.send(this);
    }

    protected void sendClientKeyExchange() throws IOException {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 16);
        this.keyExchange.generateClientKeyExchange(handshakeMessageOutput);
        handshakeMessageOutput.send(this);
    }

    protected void skip13CertificateRequest() throws IOException {
        this.certificateRequest = null;
    }

    protected void skip13ServerCertificate() throws IOException {
        this.authentication = null;
        throw new TlsFatalAlert((short) 10);
    }
}
