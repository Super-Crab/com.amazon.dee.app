package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class TlsServerProtocol extends TlsProtocol {
    protected CertificateRequest certificateRequest;
    protected TlsKeyExchange keyExchange;
    protected int[] offeredCipherSuites;
    protected TlsServer tlsServer;
    TlsServerContextImpl tlsServerContext;

    public TlsServerProtocol() {
        this.tlsServer = null;
        this.tlsServerContext = null;
        this.offeredCipherSuites = null;
        this.keyExchange = null;
        this.certificateRequest = null;
    }

    public TlsServerProtocol(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
        this.tlsServer = null;
        this.tlsServerContext = null;
        this.offeredCipherSuites = null;
        this.keyExchange = null;
        this.certificateRequest = null;
    }

    public void accept(TlsServer tlsServer) throws IOException {
        if (tlsServer != null) {
            if (this.tlsServer != null) {
                throw new IllegalStateException("'accept' can only be called once");
            }
            this.tlsServer = tlsServer;
            this.tlsServerContext = new TlsServerContextImpl(tlsServer.mo12857getCrypto());
            this.tlsServer.init(this.tlsServerContext);
            this.recordStream.init(this.tlsServerContext);
            tlsServer.notifyCloseHandle(this);
            beginHandshake();
            if (!this.blocking) {
                return;
            }
            blockForHandshake();
            return;
        }
        throw new IllegalArgumentException("'tlsServer' cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.TlsProtocol
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.offeredCipherSuites = null;
        this.keyExchange = null;
        this.certificateRequest = null;
    }

    protected boolean expectCertificateVerifyMessage() {
        Certificate peerCertificate;
        if (this.certificateRequest == null || (peerCertificate = this.tlsServerContext.getSecurityParametersHandshake().getPeerCertificate()) == null || peerCertificate.isEmpty()) {
            return false;
        }
        TlsKeyExchange tlsKeyExchange = this.keyExchange;
        return tlsKeyExchange == null || tlsKeyExchange.requiresCertificateVerify();
    }

    protected ServerHello generate13HelloRetryRequest(ClientHello clientHello) throws IOException {
        if (this.retryGroup >= 0) {
            SecurityParameters securityParametersHandshake = this.tlsServerContext.getSecurityParametersHandshake();
            ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
            Hashtable hashtable = new Hashtable();
            TlsExtensionsUtils.addSupportedVersionsExtensionServer(hashtable, negotiatedVersion);
            int i = this.retryGroup;
            if (i >= 0) {
                TlsExtensionsUtils.addKeyShareHelloRetryRequest(hashtable, i);
            }
            byte[] bArr = this.retryCookie;
            if (bArr != null) {
                TlsExtensionsUtils.addCookieExtension(hashtable, bArr);
            }
            return new ServerHello(clientHello.getSessionID(), securityParametersHandshake.getCipherSuite(), hashtable);
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected ServerHello generate13ServerHello(ClientHello clientHello, boolean z) throws IOException {
        KeyShareEntry keyShareEntry;
        TlsAgreement createDH;
        SecurityParameters securityParametersHandshake = this.tlsServerContext.getSecurityParametersHandshake();
        byte[] sessionID = clientHello.getSessionID();
        Hashtable extensions = clientHello.getExtensions();
        if (extensions != null) {
            ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
            TlsCrypto crypto = this.tlsServerContext.getCrypto();
            Vector keyShareClientHello = TlsExtensionsUtils.getKeyShareClientHello(extensions);
            if (!z) {
                this.clientExtensions = extensions;
                securityParametersHandshake.secureRenegotiation = false;
                TlsExtensionsUtils.getPaddingExtension(extensions);
                securityParametersHandshake.clientServerNames = TlsExtensionsUtils.getServerNameExtensionClient(extensions);
                TlsUtils.establishClientSigAlgs(securityParametersHandshake, extensions);
                if (securityParametersHandshake.getClientSigAlgs() == null) {
                    throw new TlsFatalAlert(AlertDescription.missing_extension);
                }
                this.tlsServer.processClientExtensions(extensions);
                invalidateSession();
                securityParametersHandshake.sessionID = TlsUtils.EMPTY_BYTES;
                this.tlsSession = TlsUtils.importSession(securityParametersHandshake.getSessionID(), null);
                this.sessionParameters = null;
                this.sessionMasterSecret = null;
                TlsUtils.negotiatedVersionTLSServer(this.tlsServerContext);
                securityParametersHandshake.serverRandom = TlsProtocol.createRandomBlock(false, this.tlsServerContext);
                if (!negotiatedVersion.equals(ProtocolVersion.getLatestTLS(this.tlsServer.getProtocolVersions()))) {
                    TlsUtils.writeDowngradeMarker(negotiatedVersion, securityParametersHandshake.getServerRandom());
                }
                int selectedCipherSuite = this.tlsServer.getSelectedCipherSuite();
                if (!TlsUtils.isValidCipherSuiteSelection(this.offeredCipherSuites, selectedCipherSuite) || !TlsUtils.isValidVersionForCipherSuite(selectedCipherSuite, negotiatedVersion)) {
                    throw new TlsFatalAlert((short) 80);
                }
                TlsUtils.negotiatedCipherSuite(securityParametersHandshake, selectedCipherSuite);
                int[] clientSupportedGroups = securityParametersHandshake.getClientSupportedGroups();
                int[] serverSupportedGroups = securityParametersHandshake.getServerSupportedGroups();
                KeyShareEntry selectKeyShare = TlsUtils.selectKeyShare(crypto, negotiatedVersion, keyShareClientHello, clientSupportedGroups, serverSupportedGroups);
                if (selectKeyShare == null) {
                    this.retryGroup = TlsUtils.selectKeyShareGroup(crypto, negotiatedVersion, clientSupportedGroups, serverSupportedGroups);
                    if (this.retryGroup < 0) {
                        throw new TlsFatalAlert((short) 40);
                    }
                    this.retryCookie = this.tlsServerContext.getNonceGenerator().generateNonce(16);
                    return generate13HelloRetryRequest(clientHello);
                }
                selectKeyShare.getNamedGroup();
                int i = serverSupportedGroups[0];
                keyShareEntry = selectKeyShare;
            } else if (this.retryGroup < 0) {
                throw new TlsFatalAlert((short) 80);
            } else {
                if (!Arrays.areEqual(this.retryCookie, TlsExtensionsUtils.getCookieExtension(extensions))) {
                    throw new TlsFatalAlert((short) 47);
                }
                this.retryCookie = null;
                keyShareEntry = TlsUtils.selectKeyShare(keyShareClientHello, this.retryGroup);
                if (keyShareEntry == null) {
                    throw new TlsFatalAlert((short) 47);
                }
            }
            Hashtable hashtable = new Hashtable();
            Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(this.tlsServer.getServerExtensions());
            ProtocolVersion protocolVersion = ProtocolVersion.TLSv12;
            TlsExtensionsUtils.addSupportedVersionsExtensionServer(hashtable, negotiatedVersion);
            securityParametersHandshake.extendedMasterSecret = true;
            securityParametersHandshake.applicationProtocol = TlsExtensionsUtils.getALPNExtensionServer(ensureExtensionsInitialised);
            securityParametersHandshake.applicationProtocolSet = true;
            ensureExtensionsInitialised.isEmpty();
            securityParametersHandshake.encryptThenMAC = false;
            securityParametersHandshake.truncatedHMac = false;
            securityParametersHandshake.statusRequestVersion = 1;
            this.expectSessionTicket = false;
            int namedGroup = keyShareEntry.getNamedGroup();
            if (NamedGroup.refersToASpecificCurve(namedGroup)) {
                createDH = crypto.createECDomain(new TlsECConfig(namedGroup)).createECDH();
            } else if (!NamedGroup.refersToASpecificFiniteField(namedGroup)) {
                throw new TlsFatalAlert((short) 80);
            } else {
                createDH = crypto.createDHDomain(new TlsDHConfig(namedGroup, true)).createDH();
            }
            TlsExtensionsUtils.addKeyShareServerHello(hashtable, new KeyShareEntry(namedGroup, createDH.generateEphemeral()));
            createDH.receivePeerValue(keyShareEntry.getKeyExchange());
            securityParametersHandshake.sharedSecret = createDH.calculateSecret();
            TlsUtils.establish13PhaseSecrets(this.tlsServerContext);
            this.serverExtensions = ensureExtensionsInitialised;
            return new ServerHello(protocolVersion, securityParametersHandshake.getServerRandom(), sessionID, securityParametersHandshake.getCipherSuite(), hashtable);
        }
        throw new TlsFatalAlert(AlertDescription.missing_extension);
    }

    protected ServerHello generateServerHello(ClientHello clientHello) throws IOException {
        ProtocolVersion latestTLS;
        ProtocolVersion version = clientHello.getVersion();
        if (version.isTLS()) {
            this.offeredCipherSuites = clientHello.getCipherSuites();
            SecurityParameters securityParametersHandshake = this.tlsServerContext.getSecurityParametersHandshake();
            this.tlsServerContext.setClientSupportedVersions(TlsExtensionsUtils.getSupportedVersionsExtensionClient(clientHello.getExtensions()));
            if (this.tlsServerContext.getClientSupportedVersions() == null) {
                latestTLS = version.isLaterVersionOf(ProtocolVersion.TLSv12) ? ProtocolVersion.TLSv12 : version;
                this.tlsServerContext.setClientSupportedVersions(latestTLS.downTo(ProtocolVersion.SSLv3));
            } else {
                latestTLS = ProtocolVersion.getLatestTLS(this.tlsServerContext.getClientSupportedVersions());
            }
            this.recordStream.setWriteVersion(latestTLS);
            if (!ProtocolVersion.SERVER_EARLIEST_SUPPORTED_TLS.isEqualOrEarlierVersionOf(latestTLS)) {
                throw new TlsFatalAlert((short) 70);
            }
            this.tlsServerContext.setClientVersion(latestTLS);
            this.tlsServer.notifyClientVersion(this.tlsServerContext.getClientVersion());
            securityParametersHandshake.clientRandom = clientHello.getRandom();
            this.tlsServer.notifyFallback(Arrays.contains(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV));
            this.tlsServer.notifyOfferedCipherSuites(this.offeredCipherSuites);
            ProtocolVersion serverVersion = this.tlsServer.getServerVersion();
            if (!ProtocolVersion.contains(this.tlsServerContext.getClientSupportedVersions(), serverVersion)) {
                throw new TlsFatalAlert((short) 80);
            }
            securityParametersHandshake.negotiatedVersion = serverVersion;
            securityParametersHandshake.clientSupportedGroups = TlsExtensionsUtils.getSupportedGroupsExtension(clientHello.getExtensions());
            securityParametersHandshake.serverSupportedGroups = this.tlsServer.getSupportedGroups();
            boolean z = false;
            if (ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(serverVersion)) {
                this.recordStream.setIgnoreChangeCipherSpec(true);
                this.recordStream.setWriteVersion(ProtocolVersion.TLSv12);
                return generate13ServerHello(clientHello, false);
            }
            this.recordStream.setWriteVersion(serverVersion);
            this.clientExtensions = clientHello.getExtensions();
            byte[] extensionData = TlsUtils.getExtensionData(this.clientExtensions, TlsProtocol.EXT_RenegotiationInfo);
            if (Arrays.contains(this.offeredCipherSuites, 255)) {
                securityParametersHandshake.secureRenegotiation = true;
            }
            if (extensionData != null) {
                securityParametersHandshake.secureRenegotiation = true;
                if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                    throw new TlsFatalAlert((short) 40);
                }
            }
            this.tlsServer.notifySecureRenegotiation(securityParametersHandshake.isSecureRenegotiation());
            boolean hasExtendedMasterSecretExtension = TlsExtensionsUtils.hasExtendedMasterSecretExtension(this.clientExtensions);
            Hashtable hashtable = this.clientExtensions;
            if (hashtable != null) {
                TlsExtensionsUtils.getPaddingExtension(hashtable);
                securityParametersHandshake.clientServerNames = TlsExtensionsUtils.getServerNameExtensionClient(this.clientExtensions);
                if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(latestTLS)) {
                    TlsUtils.establishClientSigAlgs(securityParametersHandshake, this.clientExtensions);
                }
                securityParametersHandshake.clientSupportedGroups = TlsExtensionsUtils.getSupportedGroupsExtension(this.clientExtensions);
                this.tlsServer.processClientExtensions(this.clientExtensions);
            }
            invalidateSession();
            securityParametersHandshake.sessionID = TlsUtils.EMPTY_BYTES;
            this.tlsSession = TlsUtils.importSession(securityParametersHandshake.getSessionID(), null);
            this.sessionParameters = null;
            this.sessionMasterSecret = null;
            TlsUtils.negotiatedVersionTLSServer(this.tlsServerContext);
            securityParametersHandshake.serverRandom = TlsProtocol.createRandomBlock(this.tlsServer.shouldUseGMTUnixTime(), this.tlsServerContext);
            if (!serverVersion.equals(ProtocolVersion.getLatestTLS(this.tlsServer.getProtocolVersions()))) {
                TlsUtils.writeDowngradeMarker(serverVersion, securityParametersHandshake.getServerRandom());
            }
            int selectedCipherSuite = this.tlsServer.getSelectedCipherSuite();
            if (!TlsUtils.isValidCipherSuiteSelection(this.offeredCipherSuites, selectedCipherSuite) || !TlsUtils.isValidVersionForCipherSuite(selectedCipherSuite, serverVersion)) {
                throw new TlsFatalAlert((short) 80);
            }
            TlsUtils.negotiatedCipherSuite(securityParametersHandshake, selectedCipherSuite);
            this.tlsServerContext.setRSAPreMasterSecretVersion(version);
            this.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.tlsServer.getServerExtensions());
            if (securityParametersHandshake.isSecureRenegotiation()) {
                if (TlsUtils.getExtensionData(this.serverExtensions, TlsProtocol.EXT_RenegotiationInfo) == null) {
                    this.serverExtensions.put(TlsProtocol.EXT_RenegotiationInfo, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
                }
            }
            securityParametersHandshake.extendedMasterSecret = hasExtendedMasterSecretExtension && !serverVersion.isSSL() && this.tlsServer.shouldUseExtendedMasterSecret();
            if (securityParametersHandshake.isExtendedMasterSecret()) {
                TlsExtensionsUtils.addExtendedMasterSecretExtension(this.serverExtensions);
            } else if (this.tlsServer.requiresExtendedMasterSecret()) {
                throw new TlsFatalAlert((short) 40);
            } else {
                if (this.resumedSession && !this.tlsServer.allowLegacyResumption()) {
                    throw new TlsFatalAlert((short) 80);
                }
            }
            securityParametersHandshake.applicationProtocol = TlsExtensionsUtils.getALPNExtensionServer(this.serverExtensions);
            securityParametersHandshake.applicationProtocolSet = true;
            if (!this.serverExtensions.isEmpty()) {
                securityParametersHandshake.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(this.serverExtensions);
                securityParametersHandshake.maxFragmentLength = processMaxFragmentLengthExtension(this.clientExtensions, this.serverExtensions, (short) 80);
                securityParametersHandshake.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(this.serverExtensions);
                if (!this.resumedSession) {
                    if (TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsExtensionsUtils.EXT_status_request_v2, (short) 80)) {
                        securityParametersHandshake.statusRequestVersion = 2;
                    } else if (TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsExtensionsUtils.EXT_status_request, (short) 80)) {
                        securityParametersHandshake.statusRequestVersion = 1;
                    }
                }
                if (!this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsProtocol.EXT_SessionTicket, (short) 80)) {
                    z = true;
                }
                this.expectSessionTicket = z;
            }
            applyMaxFragmentLengthExtension();
            return new ServerHello(serverVersion, securityParametersHandshake.getServerRandom(), this.tlsSession.getSessionID(), securityParametersHandshake.getCipherSuite(), this.serverExtensions);
        }
        throw new TlsFatalAlert((short) 47);
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected TlsContext getContext() {
        return this.tlsServerContext;
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    AbstractTlsContext getContextAdmin() {
        return this.tlsServerContext;
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected TlsPeer getPeer() {
        return this.tlsServer;
    }

    protected void handle13HandshakeMessage(short s, HandshakeMessageInput handshakeMessageInput) throws IOException {
        if (isTLSv13ConnectionState()) {
            if (this.resumedSession) {
                throw new TlsFatalAlert((short) 80);
            }
            if (s == 1) {
                short s2 = this.connection_state;
                if (s2 == 0) {
                    throw new TlsFatalAlert((short) 80);
                }
                if (s2 != 2) {
                    throw new TlsFatalAlert((short) 10);
                }
                ClientHello receiveClientHelloMessage = receiveClientHelloMessage(handshakeMessageInput);
                this.connection_state = (short) 3;
                ServerHello generate13ServerHello = generate13ServerHello(receiveClientHelloMessage, true);
                sendServerHelloMessage(generate13ServerHello);
                this.connection_state = (short) 4;
                send13ServerHelloCoda(generate13ServerHello, true);
                return;
            } else if (s == 11) {
                if (this.connection_state != 20) {
                    throw new TlsFatalAlert((short) 10);
                }
                receive13ClientCertificate(handshakeMessageInput);
                this.connection_state = (short) 15;
                return;
            } else if (s == 15) {
                if (this.connection_state != 15) {
                    throw new TlsFatalAlert((short) 10);
                }
                receive13ClientCertificateVerify(handshakeMessageInput);
                handshakeMessageInput.updateHash(this.handshakeHash);
                this.connection_state = (short) 17;
                return;
            } else if (s != 20) {
                if (s != 24) {
                    throw new TlsFatalAlert((short) 10);
                }
                if (this.connection_state != 21) {
                    throw new TlsFatalAlert((short) 10);
                }
                receive13ClientKeyUpdate(handshakeMessageInput);
                return;
            } else {
                short s3 = this.connection_state;
                if (s3 != 15) {
                    if (s3 != 17) {
                        if (s3 != 20) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        skip13ClientCertificate();
                    }
                    receive13ClientFinished(handshakeMessageInput);
                    this.connection_state = (short) 18;
                    this.recordStream.setIgnoreChangeCipherSpec(false);
                    this.recordStream.enablePendingCipherRead(false);
                    completeHandshake();
                    return;
                }
                skip13ClientCertificateVerify();
                receive13ClientFinished(handshakeMessageInput);
                this.connection_state = (short) 18;
                this.recordStream.setIgnoreChangeCipherSpec(false);
                this.recordStream.enablePendingCipherRead(false);
                completeHandshake();
                return;
            }
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r0 != 14) goto L15;
     */
    @Override // org.bouncycastle.tls.TlsProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleAlertWarningMessage(short r3) throws java.io.IOException {
        /*
            r2 = this;
            r0 = 41
            if (r0 != r3) goto L2b
            org.bouncycastle.tls.CertificateRequest r0 = r2.certificateRequest
            if (r0 == 0) goto L2b
            org.bouncycastle.tls.TlsServerContextImpl r0 = r2.tlsServerContext
            boolean r0 = org.bouncycastle.tls.TlsUtils.isSSL(r0)
            if (r0 == 0) goto L2b
            short r0 = r2.connection_state
            r1 = 12
            if (r0 == r1) goto L1b
            r1 = 14
            if (r0 == r1) goto L21
            goto L2b
        L1b:
            org.bouncycastle.tls.TlsServer r3 = r2.tlsServer
            r0 = 0
            r3.processClientSupplementalData(r0)
        L21:
            org.bouncycastle.tls.Certificate r3 = org.bouncycastle.tls.Certificate.EMPTY_CHAIN
            r2.notifyClientCertificate(r3)
            r3 = 15
            r2.connection_state = r3
            return
        L2b:
            super.handleAlertWarningMessage(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.TlsServerProtocol.handleAlertWarningMessage(short):void");
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected void handleHandshakeMessage(short s, HandshakeMessageInput handshakeMessageInput) throws IOException {
        CertificateStatus certificateStatus;
        SecurityParameters securityParametersHandshake = this.tlsServerContext.getSecurityParametersHandshake();
        boolean z = true;
        if (this.connection_state > 1 && TlsUtils.isTLSv13(securityParametersHandshake.getNegotiatedVersion())) {
            handle13HandshakeMessage(s, handshakeMessageInput);
        } else if (!isLegacyConnectionState()) {
            throw new TlsFatalAlert((short) 80);
        } else {
            if (this.resumedSession) {
                throw new TlsFatalAlert((short) 80);
            }
            Certificate certificate = null;
            if (s != 1) {
                if (s == 11) {
                    short s2 = this.connection_state;
                    if (s2 == 12) {
                        this.tlsServer.processClientSupplementalData(null);
                    } else if (s2 != 14) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    if (this.certificateRequest == null) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    receiveCertificateMessage(handshakeMessageInput);
                    this.connection_state = (short) 15;
                    return;
                } else if (s == 20) {
                    short s3 = this.connection_state;
                    if (s3 != 16) {
                        if (s3 != 17) {
                            throw new TlsFatalAlert((short) 10);
                        }
                    } else if (expectCertificateVerifyMessage()) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    processFinishedMessage(handshakeMessageInput);
                    handshakeMessageInput.updateHash(this.handshakeHash);
                    this.connection_state = (short) 18;
                    if (this.expectSessionTicket) {
                        sendNewSessionTicketMessage(this.tlsServer.getNewSessionTicket());
                        this.connection_state = (short) 19;
                    }
                    sendChangeCipherSpec();
                    sendFinishedMessage();
                    this.connection_state = (short) 20;
                    completeHandshake();
                    return;
                } else if (s == 23) {
                    if (this.connection_state != 12) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    this.tlsServer.processClientSupplementalData(TlsProtocol.readSupplementalDataMessage(handshakeMessageInput));
                    this.connection_state = (short) 14;
                    return;
                } else if (s == 15) {
                    if (this.connection_state != 16) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    if (!expectCertificateVerifyMessage()) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    receiveCertificateVerifyMessage(handshakeMessageInput);
                    handshakeMessageInput.updateHash(this.handshakeHash);
                    this.connection_state = (short) 17;
                    return;
                } else if (s != 16) {
                    throw new TlsFatalAlert((short) 10);
                } else {
                    short s4 = this.connection_state;
                    if (s4 == 12) {
                        this.tlsServer.processClientSupplementalData(null);
                    } else if (s4 != 14) {
                        if (s4 != 15) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        receiveClientKeyExchangeMessage(handshakeMessageInput);
                        this.connection_state = (short) 16;
                        return;
                    }
                    if (this.certificateRequest == null) {
                        this.keyExchange.skipClientCredentials();
                    } else if (TlsUtils.isTLSv12(this.tlsServerContext)) {
                        throw new TlsFatalAlert((short) 10);
                    } else {
                        if (TlsUtils.isSSL(this.tlsServerContext)) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        notifyClientCertificate(Certificate.EMPTY_CHAIN);
                    }
                    receiveClientKeyExchangeMessage(handshakeMessageInput);
                    this.connection_state = (short) 16;
                    return;
                }
            }
            short s5 = this.connection_state;
            if (s5 != 0) {
                if (s5 != 21) {
                    throw new TlsFatalAlert((short) 10);
                }
                refuseRenegotiation();
                return;
            }
            ClientHello receiveClientHelloMessage = receiveClientHelloMessage(handshakeMessageInput);
            this.connection_state = (short) 1;
            ServerHello generateServerHello = generateServerHello(receiveClientHelloMessage);
            this.handshakeHash.notifyPRFDetermined();
            if (TlsUtils.isTLSv13(securityParametersHandshake.getNegotiatedVersion())) {
                if (generateServerHello.isHelloRetryRequest()) {
                    TlsUtils.adjustTranscriptForRetry(this.handshakeHash);
                    sendServerHelloMessage(generateServerHello);
                    this.connection_state = (short) 2;
                    sendChangeCipherSpecMessage();
                    return;
                }
                sendServerHelloMessage(generateServerHello);
                this.connection_state = (short) 4;
                sendChangeCipherSpecMessage();
                send13ServerHelloCoda(generateServerHello, false);
                return;
            }
            sendServerHelloMessage(generateServerHello);
            this.connection_state = (short) 4;
            Vector serverSupplementalData = this.tlsServer.getServerSupplementalData();
            if (serverSupplementalData != null) {
                sendSupplementalDataMessage(serverSupplementalData);
                this.connection_state = (short) 6;
            }
            this.keyExchange = TlsUtils.initKeyExchangeServer(this.tlsServerContext, this.tlsServer);
            TlsCredentials establishServerCredentials = TlsUtils.establishServerCredentials(this.tlsServer);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (establishServerCredentials == null) {
                this.keyExchange.skipServerCredentials();
            } else {
                this.keyExchange.processServerCredentials(establishServerCredentials);
                certificate = establishServerCredentials.getCertificate();
                sendCertificateMessage(certificate, byteArrayOutputStream);
                this.connection_state = (short) 7;
            }
            securityParametersHandshake.tlsServerEndPoint = byteArrayOutputStream.toByteArray();
            if (certificate == null || certificate.isEmpty()) {
                securityParametersHandshake.statusRequestVersion = 0;
            }
            if (securityParametersHandshake.getStatusRequestVersion() > 0 && (certificateStatus = this.tlsServer.getCertificateStatus()) != null) {
                sendCertificateStatusMessage(certificateStatus);
                this.connection_state = (short) 8;
            }
            byte[] generateServerKeyExchange = this.keyExchange.generateServerKeyExchange();
            if (generateServerKeyExchange != null) {
                sendServerKeyExchangeMessage(generateServerKeyExchange);
                this.connection_state = (short) 10;
            }
            if (establishServerCredentials != null) {
                this.certificateRequest = this.tlsServer.getCertificateRequest();
                if (this.certificateRequest != null) {
                    boolean isTLSv12 = TlsUtils.isTLSv12(this.tlsServerContext);
                    if (this.certificateRequest.getSupportedSignatureAlgorithms() == null) {
                        z = false;
                    }
                    if (isTLSv12 != z) {
                        throw new TlsFatalAlert((short) 80);
                    }
                    this.certificateRequest = TlsUtils.validateCertificateRequest(this.certificateRequest, this.keyExchange);
                    TlsUtils.establishServerSigAlgs(securityParametersHandshake, this.certificateRequest);
                    TlsUtils.trackHashAlgorithms(this.handshakeHash, securityParametersHandshake.getServerSigAlgs());
                    sendCertificateRequestMessage(this.certificateRequest);
                    this.connection_state = (short) 11;
                } else if (!this.keyExchange.requiresCertificateVerify()) {
                    throw new TlsFatalAlert((short) 80);
                }
            }
            sendServerHelloDoneMessage();
            this.connection_state = (short) 12;
            TlsUtils.sealHandshakeHash(this.tlsServerContext, this.handshakeHash, false);
        }
    }

    protected void notifyClientCertificate(Certificate certificate) throws IOException {
        if (this.certificateRequest != null) {
            TlsUtils.processClientCertificate(this.tlsServerContext, certificate, this.keyExchange, this.tlsServer);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void receive13ClientCertificate(ByteArrayInputStream byteArrayInputStream) throws IOException {
        Certificate parse = Certificate.parse(this.tlsServerContext, byteArrayInputStream, null);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        notifyClientCertificate(parse);
    }

    protected void receive13ClientCertificateVerify(ByteArrayInputStream byteArrayInputStream) throws IOException {
        Certificate peerCertificate = this.tlsServerContext.getSecurityParametersHandshake().getPeerCertificate();
        if (peerCertificate == null || peerCertificate.isEmpty()) {
            throw new TlsFatalAlert((short) 80);
        }
        DigitallySigned parse = DigitallySigned.parse(this.tlsServerContext, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        TlsUtils.verify13CertificateVerifyClient(this.tlsServerContext, this.certificateRequest, parse, this.handshakeHash);
    }

    protected void receive13ClientFinished(ByteArrayInputStream byteArrayInputStream) throws IOException {
        processFinishedMessage(byteArrayInputStream);
    }

    protected void receive13ClientKeyUpdate(ByteArrayInputStream byteArrayInputStream) throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    protected void receiveCertificateMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        Certificate parse = Certificate.parse(this.tlsServerContext, byteArrayInputStream, null);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        notifyClientCertificate(parse);
    }

    protected void receiveCertificateVerifyMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        DigitallySigned parse = DigitallySigned.parse(this.tlsServerContext, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        TlsUtils.verifyCertificateVerifyClient(this.tlsServerContext, this.certificateRequest, parse, this.handshakeHash);
        this.handshakeHash = this.handshakeHash.stopTracking();
    }

    protected ClientHello receiveClientHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        return ClientHello.parse(byteArrayInputStream, null);
    }

    protected void receiveClientKeyExchangeMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        this.keyExchange.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        boolean isSSL = TlsUtils.isSSL(this.tlsServerContext);
        if (isSSL) {
            TlsProtocol.establishMasterSecret(this.tlsServerContext, this.keyExchange);
        }
        this.tlsServerContext.getSecurityParametersHandshake().sessionHash = TlsUtils.getCurrentPRFHash(this.handshakeHash);
        if (!isSSL) {
            TlsProtocol.establishMasterSecret(this.tlsServerContext, this.keyExchange);
        }
        this.recordStream.setPendingCipher(TlsUtils.initCipher(this.tlsServerContext));
        if (!expectCertificateVerifyMessage()) {
            this.handshakeHash = this.handshakeHash.stopTracking();
        }
    }

    protected void send13EncryptedExtensionsMessage(Hashtable hashtable) throws IOException {
        byte[] writeExtensionsData = TlsProtocol.writeExtensionsData(hashtable);
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 8);
        TlsUtils.writeOpaque16(writeExtensionsData, handshakeMessageOutput);
        handshakeMessageOutput.send(this);
    }

    protected void send13ServerHelloCoda(ServerHello serverHello, boolean z) throws IOException {
        SecurityParameters securityParametersHandshake = this.tlsServerContext.getSecurityParametersHandshake();
        TlsUtils.establish13PhaseHandshake(this.tlsServerContext, TlsUtils.getCurrentPRFHash(this.handshakeHash), this.recordStream);
        this.recordStream.enablePendingCipherWrite();
        this.recordStream.enablePendingCipherRead(true);
        send13EncryptedExtensionsMessage(this.serverExtensions);
        this.connection_state = (short) 5;
        this.certificateRequest = this.tlsServer.getCertificateRequest();
        CertificateRequest certificateRequest = this.certificateRequest;
        if (certificateRequest != null) {
            if (!certificateRequest.hasCertificateRequestContext(TlsUtils.EMPTY_BYTES)) {
                throw new TlsFatalAlert((short) 80);
            }
            TlsUtils.establishServerSigAlgs(securityParametersHandshake, this.certificateRequest);
            sendCertificateRequestMessage(this.certificateRequest);
            this.connection_state = (short) 11;
        }
        TlsCredentialedSigner establish13ServerCredentials = TlsUtils.establish13ServerCredentials(this.tlsServer);
        if (establish13ServerCredentials != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            send13CertificateMessage(establish13ServerCredentials.getCertificate(), byteArrayOutputStream);
            securityParametersHandshake.tlsServerEndPoint = byteArrayOutputStream.toByteArray();
            securityParametersHandshake.statusRequestVersion = 1;
            this.connection_state = (short) 7;
            send13CertificateVerifyMessage(TlsUtils.generate13CertificateVerify(this.tlsServerContext, establish13ServerCredentials, this.handshakeHash));
            this.connection_state = (short) 17;
            send13FinishedMessage();
            this.connection_state = (short) 20;
            TlsUtils.establish13PhaseApplication(this.tlsServerContext, TlsUtils.getCurrentPRFHash(this.handshakeHash), this.recordStream);
            this.recordStream.enablePendingCipherWrite();
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void sendCertificateRequestMessage(CertificateRequest certificateRequest) throws IOException {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 13);
        certificateRequest.encode(this.tlsServerContext, handshakeMessageOutput);
        handshakeMessageOutput.send(this);
    }

    protected void sendCertificateStatusMessage(CertificateStatus certificateStatus) throws IOException {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 22);
        certificateStatus.encode(handshakeMessageOutput);
        handshakeMessageOutput.send(this);
    }

    protected void sendHelloRequestMessage() throws IOException {
        HandshakeMessageOutput.send(this, (short) 0, TlsUtils.EMPTY_BYTES);
    }

    protected void sendNewSessionTicketMessage(NewSessionTicket newSessionTicket) throws IOException {
        if (newSessionTicket != null) {
            HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 4);
            newSessionTicket.encode(handshakeMessageOutput);
            handshakeMessageOutput.send(this);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void sendServerHelloDoneMessage() throws IOException {
        HandshakeMessageOutput.send(this, (short) 14, TlsUtils.EMPTY_BYTES);
    }

    protected void sendServerHelloMessage(ServerHello serverHello) throws IOException {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 2);
        serverHello.encode(this.tlsServerContext, handshakeMessageOutput);
        handshakeMessageOutput.send(this);
    }

    protected void sendServerKeyExchangeMessage(byte[] bArr) throws IOException {
        HandshakeMessageOutput.send(this, (short) 12, bArr);
    }

    protected void skip13ClientCertificate() throws IOException {
        if (this.certificateRequest == null) {
            return;
        }
        throw new TlsFatalAlert((short) 10);
    }

    protected void skip13ClientCertificateVerify() throws IOException {
        if (!expectCertificateVerifyMessage()) {
            return;
        }
        throw new TlsFatalAlert((short) 10);
    }
}
