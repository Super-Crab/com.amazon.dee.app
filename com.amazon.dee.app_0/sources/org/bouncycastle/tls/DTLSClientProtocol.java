package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class DTLSClientProtocol extends DTLSProtocol {

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes5.dex */
    public static class ClientHandshakeState {
        TlsClient client = null;
        TlsClientContextImpl clientContext = null;
        TlsSession tlsSession = null;
        SessionParameters sessionParameters = null;
        TlsSecret sessionMasterSecret = null;
        SessionParameters.Builder sessionParametersBuilder = null;
        int[] offeredCipherSuites = null;
        Hashtable clientExtensions = null;
        Hashtable serverExtensions = null;
        boolean resumedSession = false;
        boolean expectSessionTicket = false;
        Hashtable clientAgreements = null;
        TlsKeyExchange keyExchange = null;
        TlsAuthentication authentication = null;
        CertificateStatus certificateStatus = null;
        CertificateRequest certificateRequest = null;
        TlsCredentials clientCredentials = null;
        TlsHeartbeat heartbeat = null;
        short heartbeatPolicy = 2;

        protected ClientHandshakeState() {
        }
    }

    protected static byte[] patchClientHelloWithCookie(byte[] bArr, byte[] bArr2) throws IOException {
        int readUint8 = TlsUtils.readUint8(bArr, 34) + 35;
        int i = readUint8 + 1;
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, readUint8);
        TlsUtils.checkUint8(bArr2.length);
        TlsUtils.writeUint8(bArr2.length, bArr3, readUint8);
        System.arraycopy(bArr2, 0, bArr3, i, bArr2.length);
        System.arraycopy(bArr, i, bArr3, bArr2.length + i, bArr.length - i);
        return bArr3;
    }

    protected void abortClientHandshake(ClientHandshakeState clientHandshakeState, DTLSRecordLayer dTLSRecordLayer, short s) {
        dTLSRecordLayer.fail(s);
        invalidateSession(clientHandshakeState);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x030b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected org.bouncycastle.tls.DTLSTransport clientHandshake(org.bouncycastle.tls.DTLSClientProtocol.ClientHandshakeState r20, org.bouncycastle.tls.DTLSRecordLayer r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 812
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.DTLSClientProtocol.clientHandshake(org.bouncycastle.tls.DTLSClientProtocol$ClientHandshakeState, org.bouncycastle.tls.DTLSRecordLayer):org.bouncycastle.tls.DTLSTransport");
    }

    public DTLSTransport connect(TlsClient tlsClient, DatagramTransport datagramTransport) throws IOException {
        SessionParameters exportSessionParameters;
        if (tlsClient != null) {
            if (datagramTransport == null) {
                throw new IllegalArgumentException("'transport' cannot be null");
            }
            ClientHandshakeState clientHandshakeState = new ClientHandshakeState();
            clientHandshakeState.client = tlsClient;
            clientHandshakeState.clientContext = new TlsClientContextImpl(tlsClient.mo12857getCrypto());
            tlsClient.init(clientHandshakeState.clientContext);
            clientHandshakeState.clientContext.handshakeBeginning(tlsClient);
            SecurityParameters securityParametersHandshake = clientHandshakeState.clientContext.getSecurityParametersHandshake();
            securityParametersHandshake.extendedPadding = tlsClient.shouldUseExtendedPadding();
            TlsSession sessionToResume = clientHandshakeState.client.getSessionToResume();
            if (sessionToResume != null && sessionToResume.isResumable() && (exportSessionParameters = sessionToResume.exportSessionParameters()) != null && (exportSessionParameters.isExtendedMasterSecret() || (!clientHandshakeState.client.requiresExtendedMasterSecret() && clientHandshakeState.client.allowLegacyResumption()))) {
                TlsSecret masterSecret = exportSessionParameters.getMasterSecret();
                synchronized (masterSecret) {
                    if (masterSecret.isAlive()) {
                        clientHandshakeState.tlsSession = sessionToResume;
                        clientHandshakeState.sessionParameters = exportSessionParameters;
                        clientHandshakeState.sessionMasterSecret = clientHandshakeState.clientContext.getCrypto().adoptSecret(masterSecret);
                    }
                }
            }
            DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(clientHandshakeState.clientContext, clientHandshakeState.client, datagramTransport);
            tlsClient.notifyCloseHandle(dTLSRecordLayer);
            try {
                try {
                    return clientHandshake(clientHandshakeState, dTLSRecordLayer);
                } catch (RuntimeException e) {
                    abortClientHandshake(clientHandshakeState, dTLSRecordLayer, (short) 80);
                    throw new TlsFatalAlert((short) 80, (Throwable) e);
                } catch (TlsFatalAlert e2) {
                    abortClientHandshake(clientHandshakeState, dTLSRecordLayer, e2.getAlertDescription());
                    throw e2;
                } catch (IOException e3) {
                    abortClientHandshake(clientHandshakeState, dTLSRecordLayer, (short) 80);
                    throw e3;
                }
            } finally {
                securityParametersHandshake.clear();
            }
        }
        throw new IllegalArgumentException("'client' cannot be null");
    }

    protected byte[] generateCertificateVerify(ClientHandshakeState clientHandshakeState, DigitallySigned digitallySigned) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        digitallySigned.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] generateClientHello(ClientHandshakeState clientHandshakeState) throws IOException {
        ProtocolVersion protocolVersion;
        SessionParameters sessionParameters;
        TlsClientContextImpl tlsClientContextImpl = clientHandshakeState.clientContext;
        SecurityParameters securityParametersHandshake = tlsClientContextImpl.getSecurityParametersHandshake();
        tlsClientContextImpl.setClientSupportedVersions(clientHandshakeState.client.getProtocolVersions());
        ProtocolVersion latestDTLS = ProtocolVersion.getLatestDTLS(tlsClientContextImpl.getClientSupportedVersions());
        if (ProtocolVersion.isSupportedDTLSVersionClient(latestDTLS)) {
            tlsClientContextImpl.setClientVersion(latestDTLS);
            byte[] sessionID = TlsUtils.getSessionID(clientHandshakeState.tlsSession);
            boolean isFallback = clientHandshakeState.client.isFallback();
            clientHandshakeState.offeredCipherSuites = clientHandshakeState.client.getCipherSuites();
            if (sessionID.length > 0 && (sessionParameters = clientHandshakeState.sessionParameters) != null && (!Arrays.contains(clientHandshakeState.offeredCipherSuites, sessionParameters.getCipherSuite()) || clientHandshakeState.sessionParameters.getCompressionAlgorithm() != 0)) {
                sessionID = TlsUtils.EMPTY_BYTES;
            }
            byte[] bArr = sessionID;
            clientHandshakeState.clientExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(clientHandshakeState.client.getClientExtensions());
            if (latestDTLS.isLaterVersionOf(ProtocolVersion.DTLSv12)) {
                ProtocolVersion protocolVersion2 = ProtocolVersion.DTLSv12;
                TlsExtensionsUtils.addSupportedVersionsExtensionClient(clientHandshakeState.clientExtensions, tlsClientContextImpl.getClientSupportedVersions());
                protocolVersion = protocolVersion2;
            } else {
                protocolVersion = latestDTLS;
            }
            tlsClientContextImpl.setRSAPreMasterSecretVersion(protocolVersion);
            securityParametersHandshake.clientServerNames = TlsExtensionsUtils.getServerNameExtensionClient(clientHandshakeState.clientExtensions);
            if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(latestDTLS)) {
                TlsUtils.establishClientSigAlgs(securityParametersHandshake, clientHandshakeState.clientExtensions);
            }
            securityParametersHandshake.clientSupportedGroups = TlsExtensionsUtils.getSupportedGroupsExtension(clientHandshakeState.clientExtensions);
            clientHandshakeState.clientAgreements = TlsUtils.addEarlyKeySharesToClientHello(clientHandshakeState.clientContext, clientHandshakeState.client, clientHandshakeState.clientExtensions);
            if (TlsUtils.isExtendedMasterSecretOptionalDTLS(tlsClientContextImpl.getClientSupportedVersions()) && clientHandshakeState.client.shouldUseExtendedMasterSecret()) {
                TlsExtensionsUtils.addExtendedMasterSecretExtension(clientHandshakeState.clientExtensions);
            } else if (!TlsUtils.isTLSv13(latestDTLS) && clientHandshakeState.client.requiresExtendedMasterSecret()) {
                throw new TlsFatalAlert((short) 80);
            }
            boolean isEqualOrLaterVersionOf = ProtocolVersion.DTLSv12.isEqualOrLaterVersionOf(latestDTLS);
            boolean z = false;
            securityParametersHandshake.clientRandom = TlsProtocol.createRandomBlock(isEqualOrLaterVersionOf && clientHandshakeState.client.shouldUseGMTUnixTime(), clientHandshakeState.clientContext);
            if (TlsUtils.getExtensionData(clientHandshakeState.clientExtensions, TlsProtocol.EXT_RenegotiationInfo) == null) {
                z = true;
            }
            boolean z2 = !Arrays.contains(clientHandshakeState.offeredCipherSuites, 255);
            if (z && z2) {
                clientHandshakeState.offeredCipherSuites = Arrays.append(clientHandshakeState.offeredCipherSuites, 255);
            }
            if (isFallback && !Arrays.contains(clientHandshakeState.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV)) {
                clientHandshakeState.offeredCipherSuites = Arrays.append(clientHandshakeState.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV);
            }
            clientHandshakeState.heartbeat = clientHandshakeState.client.getHeartbeat();
            clientHandshakeState.heartbeatPolicy = clientHandshakeState.client.getHeartbeatPolicy();
            if (clientHandshakeState.heartbeat != null || 1 == clientHandshakeState.heartbeatPolicy) {
                TlsExtensionsUtils.addHeartbeatExtension(clientHandshakeState.clientExtensions, new HeartbeatExtension(clientHandshakeState.heartbeatPolicy));
            }
            ClientHello clientHello = new ClientHello(protocolVersion, securityParametersHandshake.getClientRandom(), bArr, TlsUtils.EMPTY_BYTES, clientHandshakeState.offeredCipherSuites, clientHandshakeState.clientExtensions);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            clientHello.encode(clientHandshakeState.clientContext, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected byte[] generateClientKeyExchange(ClientHandshakeState clientHandshakeState) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        clientHandshakeState.keyExchange.generateClientKeyExchange(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected void invalidateSession(ClientHandshakeState clientHandshakeState) {
        TlsSecret tlsSecret = clientHandshakeState.sessionMasterSecret;
        if (tlsSecret != null) {
            tlsSecret.destroy();
            clientHandshakeState.sessionMasterSecret = null;
        }
        SessionParameters sessionParameters = clientHandshakeState.sessionParameters;
        if (sessionParameters != null) {
            sessionParameters.clear();
            clientHandshakeState.sessionParameters = null;
        }
        TlsSession tlsSession = clientHandshakeState.tlsSession;
        if (tlsSession != null) {
            tlsSession.invalidate();
            clientHandshakeState.tlsSession = null;
        }
    }

    protected void processCertificateRequest(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        if (clientHandshakeState.authentication != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            CertificateRequest parse = CertificateRequest.parse(clientHandshakeState.clientContext, byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            clientHandshakeState.certificateRequest = TlsUtils.validateCertificateRequest(parse, clientHandshakeState.keyExchange);
            return;
        }
        throw new TlsFatalAlert((short) 40);
    }

    protected void processCertificateStatus(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.certificateStatus = CertificateStatus.parse(clientHandshakeState.clientContext, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected byte[] processHelloVerifyRequest(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(byteArrayInputStream, 0, ProtocolVersion.DTLSv12.isEqualOrEarlierVersionOf(readVersion) ? 255 : 32);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        if (readVersion.isEqualOrEarlierVersionOf(clientHandshakeState.clientContext.getClientVersion())) {
            return readOpaque8;
        }
        throw new TlsFatalAlert((short) 47);
    }

    protected void processNewSessionTicket(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        NewSessionTicket parse = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        clientHandshakeState.client.notifyNewSessionTicket(parse);
    }

    protected void processServerCertificate(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        clientHandshakeState.authentication = TlsUtils.receiveServerCertificate(clientHandshakeState.clientContext, clientHandshakeState.client, new ByteArrayInputStream(bArr));
    }

    protected void processServerHello(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        TlsSession tlsSession;
        ServerHello parse = ServerHello.parse(new ByteArrayInputStream(bArr));
        ProtocolVersion version = parse.getVersion();
        clientHandshakeState.serverExtensions = parse.getExtensions();
        SecurityParameters securityParametersHandshake = clientHandshakeState.clientContext.getSecurityParametersHandshake();
        reportServerVersion(clientHandshakeState, version);
        securityParametersHandshake.serverRandom = parse.getRandom();
        if (!clientHandshakeState.clientContext.getClientVersion().equals(version)) {
            TlsUtils.checkDowngradeMarker(version, securityParametersHandshake.getServerRandom());
        }
        byte[] sessionID = parse.getSessionID();
        securityParametersHandshake.sessionID = sessionID;
        clientHandshakeState.client.notifySessionID(sessionID);
        boolean z = false;
        clientHandshakeState.resumedSession = sessionID.length > 0 && (tlsSession = clientHandshakeState.tlsSession) != null && Arrays.areEqual(sessionID, tlsSession.getSessionID());
        int validateSelectedCipherSuite = DTLSProtocol.validateSelectedCipherSuite(parse.getCipherSuite(), (short) 47);
        if (!TlsUtils.isValidCipherSuiteSelection(clientHandshakeState.offeredCipherSuites, validateSelectedCipherSuite) || !TlsUtils.isValidVersionForCipherSuite(validateSelectedCipherSuite, securityParametersHandshake.getNegotiatedVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        TlsUtils.negotiatedCipherSuite(securityParametersHandshake, validateSelectedCipherSuite);
        clientHandshakeState.client.notifySelectedCipherSuite(validateSelectedCipherSuite);
        if (TlsUtils.isTLSv13(version)) {
            securityParametersHandshake.extendedMasterSecret = true;
        } else {
            boolean hasExtendedMasterSecretExtension = TlsExtensionsUtils.hasExtendedMasterSecretExtension(clientHandshakeState.serverExtensions);
            if (hasExtendedMasterSecretExtension) {
                if (!clientHandshakeState.resumedSession && !clientHandshakeState.client.shouldUseExtendedMasterSecret()) {
                    throw new TlsFatalAlert((short) 40);
                }
            } else if (clientHandshakeState.client.requiresExtendedMasterSecret() || (clientHandshakeState.resumedSession && !clientHandshakeState.client.allowLegacyResumption())) {
                throw new TlsFatalAlert((short) 40);
            }
            securityParametersHandshake.extendedMasterSecret = hasExtendedMasterSecretExtension;
        }
        Hashtable hashtable = clientHandshakeState.serverExtensions;
        if (hashtable != null) {
            Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                Integer num = (Integer) keys.nextElement();
                if (!num.equals(TlsProtocol.EXT_RenegotiationInfo)) {
                    if (TlsUtils.getExtensionData(clientHandshakeState.clientExtensions, num) == null) {
                        throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                    }
                    boolean z2 = clientHandshakeState.resumedSession;
                }
            }
        }
        byte[] extensionData = TlsUtils.getExtensionData(clientHandshakeState.serverExtensions, TlsProtocol.EXT_RenegotiationInfo);
        if (extensionData != null) {
            securityParametersHandshake.secureRenegotiation = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        clientHandshakeState.client.notifySecureRenegotiation(securityParametersHandshake.isSecureRenegotiation());
        securityParametersHandshake.applicationProtocol = TlsExtensionsUtils.getALPNExtensionServer(clientHandshakeState.serverExtensions);
        securityParametersHandshake.applicationProtocolSet = true;
        HeartbeatExtension heartbeatExtension = TlsExtensionsUtils.getHeartbeatExtension(clientHandshakeState.serverExtensions);
        if (heartbeatExtension == null) {
            clientHandshakeState.heartbeat = null;
            clientHandshakeState.heartbeatPolicy = (short) 2;
        } else if (1 != heartbeatExtension.getMode()) {
            clientHandshakeState.heartbeat = null;
        }
        Hashtable hashtable2 = clientHandshakeState.clientExtensions;
        Hashtable hashtable3 = clientHandshakeState.serverExtensions;
        if (clientHandshakeState.resumedSession) {
            if (securityParametersHandshake.getCipherSuite() != clientHandshakeState.sessionParameters.getCipherSuite() || clientHandshakeState.sessionParameters.getCompressionAlgorithm() != 0 || !version.equals(clientHandshakeState.sessionParameters.getNegotiatedVersion())) {
                throw new TlsFatalAlert((short) 47);
            }
            hashtable3 = clientHandshakeState.sessionParameters.readServerExtensions();
            hashtable2 = null;
        }
        if (hashtable3 != null && !hashtable3.isEmpty()) {
            boolean hasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable3);
            if (hasEncryptThenMACExtension && !TlsUtils.isBlockCipherSuite(securityParametersHandshake.getCipherSuite())) {
                throw new TlsFatalAlert((short) 47);
            }
            securityParametersHandshake.encryptThenMAC = hasEncryptThenMACExtension;
            securityParametersHandshake.maxFragmentLength = DTLSProtocol.evaluateMaxFragmentLengthExtension(clientHandshakeState.resumedSession, hashtable2, hashtable3, (short) 47);
            securityParametersHandshake.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable3);
            if (!clientHandshakeState.resumedSession) {
                if (TlsUtils.hasExpectedEmptyExtensionData(hashtable3, TlsExtensionsUtils.EXT_status_request_v2, (short) 47)) {
                    securityParametersHandshake.statusRequestVersion = 2;
                } else if (TlsUtils.hasExpectedEmptyExtensionData(hashtable3, TlsExtensionsUtils.EXT_status_request, (short) 47)) {
                    securityParametersHandshake.statusRequestVersion = 1;
                }
            }
            if (!clientHandshakeState.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(hashtable3, TlsProtocol.EXT_SessionTicket, (short) 47)) {
                z = true;
            }
            clientHandshakeState.expectSessionTicket = z;
        }
        if (hashtable2 == null) {
            return;
        }
        clientHandshakeState.client.processServerExtensions(hashtable3);
    }

    protected void processServerKeyExchange(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.keyExchange.processServerKeyExchange(byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
    }

    protected void processServerSupplementalData(ClientHandshakeState clientHandshakeState, byte[] bArr) throws IOException {
        clientHandshakeState.client.processServerSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(bArr)));
    }

    protected void reportServerVersion(ClientHandshakeState clientHandshakeState, ProtocolVersion protocolVersion) throws IOException {
        TlsClientContextImpl tlsClientContextImpl = clientHandshakeState.clientContext;
        SecurityParameters securityParametersHandshake = tlsClientContextImpl.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (negotiatedVersion != null) {
            if (!negotiatedVersion.equals(protocolVersion)) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if (!ProtocolVersion.contains(tlsClientContextImpl.getClientSupportedVersions(), protocolVersion)) {
            throw new TlsFatalAlert((short) 70);
        } else {
            securityParametersHandshake.negotiatedVersion = protocolVersion;
            TlsUtils.negotiatedVersionDTLSClient(clientHandshakeState.clientContext, clientHandshakeState.client);
        }
    }
}
