package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.DTLSReliableHandshake;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class DTLSServerProtocol extends DTLSProtocol {
    protected boolean verifyRequests = true;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes5.dex */
    public static class ServerHandshakeState {
        TlsServer server = null;
        TlsServerContextImpl serverContext = null;
        TlsSession tlsSession = null;
        SessionParameters sessionParameters = null;
        TlsSecret sessionMasterSecret = null;
        SessionParameters.Builder sessionParametersBuilder = null;
        int[] offeredCipherSuites = null;
        Hashtable clientExtensions = null;
        Hashtable serverExtensions = null;
        boolean offeredExtendedMasterSecret = false;
        boolean resumedSession = false;
        boolean expectSessionTicket = false;
        TlsKeyExchange keyExchange = null;
        TlsCredentials serverCredentials = null;
        CertificateRequest certificateRequest = null;
        TlsHeartbeat heartbeat = null;
        short heartbeatPolicy = 2;

        protected ServerHandshakeState() {
        }
    }

    protected void abortServerHandshake(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer, short s) {
        dTLSRecordLayer.fail(s);
        invalidateSession(serverHandshakeState);
    }

    public DTLSTransport accept(TlsServer tlsServer, DatagramTransport datagramTransport) throws IOException {
        return accept(tlsServer, datagramTransport, null);
    }

    public DTLSTransport accept(TlsServer tlsServer, DatagramTransport datagramTransport, DTLSRequest dTLSRequest) throws IOException {
        if (tlsServer != null) {
            if (datagramTransport == null) {
                throw new IllegalArgumentException("'transport' cannot be null");
            }
            ServerHandshakeState serverHandshakeState = new ServerHandshakeState();
            serverHandshakeState.server = tlsServer;
            serverHandshakeState.serverContext = new TlsServerContextImpl(tlsServer.mo12857getCrypto());
            tlsServer.init(serverHandshakeState.serverContext);
            serverHandshakeState.serverContext.handshakeBeginning(tlsServer);
            SecurityParameters securityParametersHandshake = serverHandshakeState.serverContext.getSecurityParametersHandshake();
            securityParametersHandshake.extendedPadding = tlsServer.shouldUseExtendedPadding();
            DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(serverHandshakeState.serverContext, serverHandshakeState.server, datagramTransport);
            tlsServer.notifyCloseHandle(dTLSRecordLayer);
            try {
                try {
                    try {
                        try {
                            return serverHandshake(serverHandshakeState, dTLSRecordLayer, dTLSRequest);
                        } catch (RuntimeException e) {
                            abortServerHandshake(serverHandshakeState, dTLSRecordLayer, (short) 80);
                            throw new TlsFatalAlert((short) 80, (Throwable) e);
                        }
                    } catch (TlsFatalAlert e2) {
                        abortServerHandshake(serverHandshakeState, dTLSRecordLayer, e2.getAlertDescription());
                        throw e2;
                    }
                } catch (IOException e3) {
                    abortServerHandshake(serverHandshakeState, dTLSRecordLayer, (short) 80);
                    throw e3;
                }
            } finally {
                securityParametersHandshake.clear();
            }
        }
        throw new IllegalArgumentException("'server' cannot be null");
    }

    protected boolean expectCertificateVerifyMessage(ServerHandshakeState serverHandshakeState) {
        Certificate peerCertificate;
        if (serverHandshakeState.certificateRequest == null || (peerCertificate = serverHandshakeState.serverContext.getSecurityParametersHandshake().getPeerCertificate()) == null || peerCertificate.isEmpty()) {
            return false;
        }
        TlsKeyExchange tlsKeyExchange = serverHandshakeState.keyExchange;
        return tlsKeyExchange == null || tlsKeyExchange.requiresCertificateVerify();
    }

    protected byte[] generateCertificateRequest(ServerHandshakeState serverHandshakeState, CertificateRequest certificateRequest) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateRequest.encode(serverHandshakeState.serverContext, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateCertificateStatus(ServerHandshakeState serverHandshakeState, CertificateStatus certificateStatus) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateStatus.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateNewSessionTicket(ServerHandshakeState serverHandshakeState, NewSessionTicket newSessionTicket) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        newSessionTicket.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateServerHello(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer) throws IOException {
        ProtocolVersion protocolVersion;
        TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.serverContext;
        SecurityParameters securityParametersHandshake = tlsServerContextImpl.getSecurityParametersHandshake();
        ProtocolVersion serverVersion = serverHandshakeState.server.getServerVersion();
        if (ProtocolVersion.contains(tlsServerContextImpl.getClientSupportedVersions(), serverVersion)) {
            securityParametersHandshake.negotiatedVersion = serverVersion;
            TlsUtils.negotiatedVersionDTLSServer(tlsServerContextImpl);
            boolean z = false;
            securityParametersHandshake.serverRandom = TlsProtocol.createRandomBlock(ProtocolVersion.DTLSv12.isEqualOrLaterVersionOf(serverVersion) && serverHandshakeState.server.shouldUseGMTUnixTime(), tlsServerContextImpl);
            if (!serverVersion.equals(ProtocolVersion.getLatestDTLS(serverHandshakeState.server.getProtocolVersions()))) {
                TlsUtils.writeDowngradeMarker(serverVersion, securityParametersHandshake.getServerRandom());
            }
            int validateSelectedCipherSuite = DTLSProtocol.validateSelectedCipherSuite(serverHandshakeState.server.getSelectedCipherSuite(), (short) 80);
            if (!TlsUtils.isValidCipherSuiteSelection(serverHandshakeState.offeredCipherSuites, validateSelectedCipherSuite) || !TlsUtils.isValidVersionForCipherSuite(validateSelectedCipherSuite, securityParametersHandshake.getNegotiatedVersion())) {
                throw new TlsFatalAlert((short) 80);
            }
            TlsUtils.negotiatedCipherSuite(securityParametersHandshake, validateSelectedCipherSuite);
            serverHandshakeState.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(serverHandshakeState.server.getServerExtensions());
            if (serverVersion.isLaterVersionOf(ProtocolVersion.DTLSv12)) {
                ProtocolVersion protocolVersion2 = ProtocolVersion.DTLSv12;
                TlsExtensionsUtils.addSupportedVersionsExtensionServer(serverHandshakeState.serverExtensions, serverVersion);
                protocolVersion = protocolVersion2;
            } else {
                protocolVersion = serverVersion;
            }
            if (securityParametersHandshake.isSecureRenegotiation()) {
                if (TlsUtils.getExtensionData(serverHandshakeState.serverExtensions, TlsProtocol.EXT_RenegotiationInfo) == null) {
                    serverHandshakeState.serverExtensions.put(TlsProtocol.EXT_RenegotiationInfo, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
                }
            }
            if (TlsUtils.isTLSv13(serverVersion)) {
                securityParametersHandshake.extendedMasterSecret = true;
            } else {
                securityParametersHandshake.extendedMasterSecret = serverHandshakeState.offeredExtendedMasterSecret && serverHandshakeState.server.shouldUseExtendedMasterSecret();
                if (securityParametersHandshake.isExtendedMasterSecret()) {
                    TlsExtensionsUtils.addExtendedMasterSecretExtension(serverHandshakeState.serverExtensions);
                } else if (serverHandshakeState.server.requiresExtendedMasterSecret()) {
                    throw new TlsFatalAlert((short) 40);
                } else {
                    if (serverHandshakeState.resumedSession && !serverHandshakeState.server.allowLegacyResumption()) {
                        throw new TlsFatalAlert((short) 80);
                    }
                }
            }
            if (serverHandshakeState.heartbeat != null || 1 == serverHandshakeState.heartbeatPolicy) {
                TlsExtensionsUtils.addHeartbeatExtension(serverHandshakeState.serverExtensions, new HeartbeatExtension(serverHandshakeState.heartbeatPolicy));
            }
            securityParametersHandshake.applicationProtocol = TlsExtensionsUtils.getALPNExtensionServer(serverHandshakeState.serverExtensions);
            securityParametersHandshake.applicationProtocolSet = true;
            if (!serverHandshakeState.serverExtensions.isEmpty()) {
                securityParametersHandshake.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(serverHandshakeState.serverExtensions);
                securityParametersHandshake.maxFragmentLength = DTLSProtocol.evaluateMaxFragmentLengthExtension(serverHandshakeState.resumedSession, serverHandshakeState.clientExtensions, serverHandshakeState.serverExtensions, (short) 80);
                securityParametersHandshake.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(serverHandshakeState.serverExtensions);
                if (!serverHandshakeState.resumedSession) {
                    if (TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.serverExtensions, TlsExtensionsUtils.EXT_status_request_v2, (short) 80)) {
                        securityParametersHandshake.statusRequestVersion = 2;
                    } else if (TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.serverExtensions, TlsExtensionsUtils.EXT_status_request, (short) 80)) {
                        securityParametersHandshake.statusRequestVersion = 1;
                    }
                }
                if (!serverHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.serverExtensions, TlsProtocol.EXT_SessionTicket, (short) 80)) {
                    z = true;
                }
                serverHandshakeState.expectSessionTicket = z;
            }
            DTLSProtocol.applyMaxFragmentLengthExtension(dTLSRecordLayer, securityParametersHandshake.getMaxFragmentLength());
            ServerHello serverHello = new ServerHello(protocolVersion, securityParametersHandshake.getServerRandom(), serverHandshakeState.tlsSession.getSessionID(), securityParametersHandshake.getCipherSuite(), serverHandshakeState.serverExtensions);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            serverHello.encode(serverHandshakeState.serverContext, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        throw new TlsFatalAlert((short) 80);
    }

    public boolean getVerifyRequests() {
        return this.verifyRequests;
    }

    protected void invalidateSession(ServerHandshakeState serverHandshakeState) {
        TlsSecret tlsSecret = serverHandshakeState.sessionMasterSecret;
        if (tlsSecret != null) {
            tlsSecret.destroy();
            serverHandshakeState.sessionMasterSecret = null;
        }
        SessionParameters sessionParameters = serverHandshakeState.sessionParameters;
        if (sessionParameters != null) {
            sessionParameters.clear();
            serverHandshakeState.sessionParameters = null;
        }
        TlsSession tlsSession = serverHandshakeState.tlsSession;
        if (tlsSession != null) {
            tlsSession.invalidate();
            serverHandshakeState.tlsSession = null;
        }
    }

    protected void notifyClientCertificate(ServerHandshakeState serverHandshakeState, Certificate certificate) throws IOException {
        if (serverHandshakeState.certificateRequest != null) {
            TlsUtils.processClientCertificate(serverHandshakeState.serverContext, certificate, serverHandshakeState.keyExchange, serverHandshakeState.server);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void processCertificateVerify(ServerHandshakeState serverHandshakeState, byte[] bArr, TlsHandshakeHash tlsHandshakeHash) throws IOException {
        if (serverHandshakeState.certificateRequest != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.serverContext;
            DigitallySigned parse = DigitallySigned.parse(tlsServerContextImpl, byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            TlsUtils.verifyCertificateVerifyClient(tlsServerContextImpl, serverHandshakeState.certificateRequest, parse, tlsHandshakeHash);
            return;
        }
        throw new IllegalStateException();
    }

    protected void processClientCertificate(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Certificate parse = Certificate.parse(serverHandshakeState.serverContext, byteArrayInputStream, null);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        notifyClientCertificate(serverHandshakeState, parse);
    }

    protected void processClientHello(ServerHandshakeState serverHandshakeState, ClientHello clientHello) throws IOException {
        ProtocolVersion version = clientHello.getVersion();
        serverHandshakeState.offeredCipherSuites = clientHello.getCipherSuites();
        serverHandshakeState.clientExtensions = clientHello.getExtensions();
        TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.serverContext;
        SecurityParameters securityParametersHandshake = tlsServerContextImpl.getSecurityParametersHandshake();
        if (version.isDTLS()) {
            tlsServerContextImpl.setRSAPreMasterSecretVersion(version);
            tlsServerContextImpl.setClientSupportedVersions(TlsExtensionsUtils.getSupportedVersionsExtensionClient(serverHandshakeState.clientExtensions));
            if (tlsServerContextImpl.getClientSupportedVersions() == null) {
                if (version.isLaterVersionOf(ProtocolVersion.DTLSv12)) {
                    version = ProtocolVersion.DTLSv12;
                }
                tlsServerContextImpl.setClientSupportedVersions(version.downTo(ProtocolVersion.DTLSv10));
            } else {
                version = ProtocolVersion.getLatestDTLS(tlsServerContextImpl.getClientSupportedVersions());
            }
            if (!ProtocolVersion.SERVER_EARLIEST_SUPPORTED_DTLS.isEqualOrEarlierVersionOf(version)) {
                throw new TlsFatalAlert((short) 70);
            }
            tlsServerContextImpl.setClientVersion(version);
            serverHandshakeState.server.notifyClientVersion(tlsServerContextImpl.getClientVersion());
            securityParametersHandshake.clientRandom = clientHello.getRandom();
            serverHandshakeState.server.notifyFallback(Arrays.contains(serverHandshakeState.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV));
            serverHandshakeState.server.notifyOfferedCipherSuites(serverHandshakeState.offeredCipherSuites);
            if (Arrays.contains(serverHandshakeState.offeredCipherSuites, 255)) {
                securityParametersHandshake.secureRenegotiation = true;
            }
            byte[] extensionData = TlsUtils.getExtensionData(serverHandshakeState.clientExtensions, TlsProtocol.EXT_RenegotiationInfo);
            if (extensionData != null) {
                securityParametersHandshake.secureRenegotiation = true;
                if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                    throw new TlsFatalAlert((short) 40);
                }
            }
            serverHandshakeState.server.notifySecureRenegotiation(securityParametersHandshake.isSecureRenegotiation());
            serverHandshakeState.offeredExtendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(serverHandshakeState.clientExtensions);
            Hashtable hashtable = serverHandshakeState.clientExtensions;
            if (hashtable == null) {
                return;
            }
            TlsExtensionsUtils.getPaddingExtension(hashtable);
            securityParametersHandshake.clientServerNames = TlsExtensionsUtils.getServerNameExtensionClient(serverHandshakeState.clientExtensions);
            if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(version)) {
                TlsUtils.establishClientSigAlgs(securityParametersHandshake, serverHandshakeState.clientExtensions);
            }
            securityParametersHandshake.clientSupportedGroups = TlsExtensionsUtils.getSupportedGroupsExtension(serverHandshakeState.clientExtensions);
            HeartbeatExtension heartbeatExtension = TlsExtensionsUtils.getHeartbeatExtension(serverHandshakeState.clientExtensions);
            if (heartbeatExtension != null) {
                if (1 == heartbeatExtension.getMode()) {
                    serverHandshakeState.heartbeat = serverHandshakeState.server.getHeartbeat();
                }
                serverHandshakeState.heartbeatPolicy = serverHandshakeState.server.getHeartbeatPolicy();
            }
            serverHandshakeState.server.processClientExtensions(serverHandshakeState.clientExtensions);
            return;
        }
        throw new TlsFatalAlert((short) 47);
    }

    protected void processClientHello(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        processClientHello(serverHandshakeState, ClientHello.parse(new ByteArrayInputStream(bArr), NullOutputStream.INSTANCE));
    }

    protected void processClientKeyExchange(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        serverHandshakeState.keyExchange.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected void processClientSupplementalData(ServerHandshakeState serverHandshakeState, byte[] bArr) throws IOException {
        serverHandshakeState.server.processClientSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(bArr)));
    }

    protected DTLSTransport serverHandshake(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer, DTLSRequest dTLSRequest) throws IOException {
        Certificate certificate;
        CertificateStatus certificateStatus;
        SecurityParameters securityParametersHandshake = serverHandshakeState.serverContext.getSecurityParametersHandshake();
        DTLSReliableHandshake dTLSReliableHandshake = new DTLSReliableHandshake(serverHandshakeState.serverContext, dTLSRecordLayer, serverHandshakeState.server.getHandshakeTimeoutMillis(), dTLSRequest);
        if (dTLSRequest == null) {
            DTLSReliableHandshake.Message receiveMessage = dTLSReliableHandshake.receiveMessage();
            if (receiveMessage.getType() != 1) {
                throw new TlsFatalAlert((short) 10);
            }
            processClientHello(serverHandshakeState, receiveMessage.getBody());
        } else {
            processClientHello(serverHandshakeState, dTLSRequest.getClientHello());
        }
        invalidateSession(serverHandshakeState);
        securityParametersHandshake.sessionID = TlsUtils.EMPTY_BYTES;
        serverHandshakeState.tlsSession = TlsUtils.importSession(securityParametersHandshake.getSessionID(), null);
        serverHandshakeState.sessionParameters = null;
        serverHandshakeState.sessionMasterSecret = null;
        byte[] generateServerHello = generateServerHello(serverHandshakeState, dTLSRecordLayer);
        ProtocolVersion serverVersion = serverHandshakeState.serverContext.getServerVersion();
        dTLSRecordLayer.setReadVersion(serverVersion);
        dTLSRecordLayer.setWriteVersion(serverVersion);
        dTLSReliableHandshake.sendMessage((short) 2, generateServerHello);
        dTLSReliableHandshake.getHandshakeHash().notifyPRFDetermined();
        Vector serverSupplementalData = serverHandshakeState.server.getServerSupplementalData();
        if (serverSupplementalData != null) {
            dTLSReliableHandshake.sendMessage((short) 23, DTLSProtocol.generateSupplementalData(serverSupplementalData));
        }
        serverHandshakeState.keyExchange = TlsUtils.initKeyExchangeServer(serverHandshakeState.serverContext, serverHandshakeState.server);
        serverHandshakeState.serverCredentials = TlsUtils.establishServerCredentials(serverHandshakeState.server);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsCredentials tlsCredentials = serverHandshakeState.serverCredentials;
        if (tlsCredentials == null) {
            serverHandshakeState.keyExchange.skipServerCredentials();
            certificate = null;
        } else {
            serverHandshakeState.keyExchange.processServerCredentials(tlsCredentials);
            certificate = serverHandshakeState.serverCredentials.getCertificate();
            DTLSProtocol.sendCertificateMessage(serverHandshakeState.serverContext, dTLSReliableHandshake, certificate, byteArrayOutputStream);
        }
        securityParametersHandshake.tlsServerEndPoint = byteArrayOutputStream.toByteArray();
        boolean z = false;
        if (certificate == null || certificate.isEmpty()) {
            securityParametersHandshake.statusRequestVersion = 0;
        }
        if (securityParametersHandshake.getStatusRequestVersion() > 0 && (certificateStatus = serverHandshakeState.server.getCertificateStatus()) != null) {
            dTLSReliableHandshake.sendMessage((short) 22, generateCertificateStatus(serverHandshakeState, certificateStatus));
        }
        byte[] generateServerKeyExchange = serverHandshakeState.keyExchange.generateServerKeyExchange();
        if (generateServerKeyExchange != null) {
            dTLSReliableHandshake.sendMessage((short) 12, generateServerKeyExchange);
        }
        if (serverHandshakeState.serverCredentials != null) {
            serverHandshakeState.certificateRequest = serverHandshakeState.server.getCertificateRequest();
            if (serverHandshakeState.certificateRequest != null) {
                if (TlsUtils.isTLSv12(serverHandshakeState.serverContext) != (serverHandshakeState.certificateRequest.getSupportedSignatureAlgorithms() != null)) {
                    throw new TlsFatalAlert((short) 80);
                }
                serverHandshakeState.certificateRequest = TlsUtils.validateCertificateRequest(serverHandshakeState.certificateRequest, serverHandshakeState.keyExchange);
                TlsUtils.establishServerSigAlgs(securityParametersHandshake, serverHandshakeState.certificateRequest);
                TlsUtils.trackHashAlgorithms(dTLSReliableHandshake.getHandshakeHash(), securityParametersHandshake.getServerSigAlgs());
                dTLSReliableHandshake.sendMessage((short) 13, generateCertificateRequest(serverHandshakeState, serverHandshakeState.certificateRequest));
            } else if (!serverHandshakeState.keyExchange.requiresCertificateVerify()) {
                throw new TlsFatalAlert((short) 80);
            }
        }
        dTLSReliableHandshake.sendMessage((short) 14, TlsUtils.EMPTY_BYTES);
        TlsUtils.sealHandshakeHash(serverHandshakeState.serverContext, dTLSReliableHandshake.getHandshakeHash(), false);
        DTLSReliableHandshake.Message receiveMessage2 = dTLSReliableHandshake.receiveMessage();
        if (receiveMessage2.getType() == 23) {
            processClientSupplementalData(serverHandshakeState, receiveMessage2.getBody());
            receiveMessage2 = dTLSReliableHandshake.receiveMessage();
        } else {
            serverHandshakeState.server.processClientSupplementalData(null);
        }
        if (serverHandshakeState.certificateRequest == null) {
            serverHandshakeState.keyExchange.skipClientCredentials();
        } else if (receiveMessage2.getType() == 11) {
            processClientCertificate(serverHandshakeState, receiveMessage2.getBody());
            receiveMessage2 = dTLSReliableHandshake.receiveMessage();
        } else if (TlsUtils.isTLSv12(serverHandshakeState.serverContext)) {
            throw new TlsFatalAlert((short) 10);
        } else {
            notifyClientCertificate(serverHandshakeState, Certificate.EMPTY_CHAIN);
        }
        if (receiveMessage2.getType() == 16) {
            processClientKeyExchange(serverHandshakeState, receiveMessage2.getBody());
            securityParametersHandshake.sessionHash = TlsUtils.getCurrentPRFHash(dTLSReliableHandshake.getHandshakeHash());
            TlsProtocol.establishMasterSecret(serverHandshakeState.serverContext, serverHandshakeState.keyExchange);
            dTLSRecordLayer.initPendingEpoch(TlsUtils.initCipher(serverHandshakeState.serverContext));
            TlsHandshakeHash prepareToFinish = dTLSReliableHandshake.prepareToFinish();
            if (expectCertificateVerifyMessage(serverHandshakeState)) {
                processCertificateVerify(serverHandshakeState, dTLSReliableHandshake.receiveMessageBody((short) 15), prepareToFinish);
            }
            securityParametersHandshake.peerVerifyData = TlsUtils.calculateVerifyData(serverHandshakeState.serverContext, dTLSReliableHandshake.getHandshakeHash(), false);
            processFinished(dTLSReliableHandshake.receiveMessageBody((short) 20), securityParametersHandshake.getPeerVerifyData());
            if (serverHandshakeState.expectSessionTicket) {
                dTLSReliableHandshake.sendMessage((short) 4, generateNewSessionTicket(serverHandshakeState, serverHandshakeState.server.getNewSessionTicket()));
            }
            securityParametersHandshake.localVerifyData = TlsUtils.calculateVerifyData(serverHandshakeState.serverContext, dTLSReliableHandshake.getHandshakeHash(), true);
            dTLSReliableHandshake.sendMessage((short) 20, securityParametersHandshake.getLocalVerifyData());
            dTLSReliableHandshake.finish();
            serverHandshakeState.sessionMasterSecret = securityParametersHandshake.getMasterSecret();
            serverHandshakeState.sessionParameters = new SessionParameters.Builder().setCipherSuite(securityParametersHandshake.getCipherSuite()).setCompressionAlgorithm(securityParametersHandshake.getCompressionAlgorithm()).setExtendedMasterSecret(securityParametersHandshake.isExtendedMasterSecret()).setLocalCertificate(securityParametersHandshake.getLocalCertificate()).setMasterSecret(serverHandshakeState.serverContext.getCrypto().adoptSecret(serverHandshakeState.sessionMasterSecret)).setNegotiatedVersion(securityParametersHandshake.getNegotiatedVersion()).setPeerCertificate(securityParametersHandshake.getPeerCertificate()).setPSKIdentity(securityParametersHandshake.getPSKIdentity()).setSRPIdentity(securityParametersHandshake.getSRPIdentity()).setServerExtensions(serverHandshakeState.serverExtensions).build();
            serverHandshakeState.tlsSession = TlsUtils.importSession(serverHandshakeState.tlsSession.getSessionID(), serverHandshakeState.sessionParameters);
            securityParametersHandshake.tlsUnique = securityParametersHandshake.getPeerVerifyData();
            serverHandshakeState.serverContext.handshakeComplete(serverHandshakeState.server, serverHandshakeState.tlsSession);
            TlsHeartbeat tlsHeartbeat = serverHandshakeState.heartbeat;
            if (1 == serverHandshakeState.heartbeatPolicy) {
                z = true;
            }
            dTLSRecordLayer.initHeartbeat(tlsHeartbeat, z);
            return new DTLSTransport(dTLSRecordLayer);
        }
        throw new TlsFatalAlert((short) 10);
    }

    public void setVerifyRequests(boolean z) {
        this.verifyRequests = z;
    }
}
