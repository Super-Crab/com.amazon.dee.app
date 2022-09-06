package org.bouncycastle.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsECConfig;
/* loaded from: classes5.dex */
public abstract class AbstractTlsServer extends AbstractTlsPeer implements TlsServer {
    protected CertificateStatusRequest certificateStatusRequest;
    protected int[] cipherSuites;
    protected Hashtable clientExtensions;
    protected Vector clientProtocolNames;
    protected boolean clientSentECPointFormats;
    protected TlsServerContext context;
    protected boolean encryptThenMACOffered;
    protected short maxFragmentLengthOffered;
    protected int[] offeredCipherSuites;
    protected ProtocolVersion[] protocolVersions;
    protected int selectedCipherSuite;
    protected ProtocolName selectedProtocolName;
    protected Hashtable serverExtensions;
    protected Vector statusRequestV2;
    protected boolean truncatedHMacOffered;
    protected Vector trustedCAKeys;

    public AbstractTlsServer(TlsCrypto tlsCrypto) {
        super(tlsCrypto);
    }

    protected boolean allowCertificateStatus() {
        return true;
    }

    protected boolean allowEncryptThenMAC() {
        return true;
    }

    protected boolean allowMultiCertStatus() {
        return false;
    }

    protected boolean allowTruncatedHMac() {
        return false;
    }

    protected boolean allowTrustedCAIndication() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Hashtable checkServerExtensions() {
        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
        this.serverExtensions = ensureExtensionsInitialised;
        return ensureExtensionsInitialised;
    }

    public CertificateRequest getCertificateRequest() throws IOException {
        return null;
    }

    public CertificateStatus getCertificateStatus() throws IOException {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsPeer
    public int[] getCipherSuites() {
        return this.cipherSuites;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsDHConfig getDHConfig() throws IOException {
        return TlsDHUtils.createNamedDHConfig(this.context, selectDH(TlsDHUtils.getMinimumFiniteFieldBits(this.selectedCipherSuite)));
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsECConfig getECDHConfig() throws IOException {
        return TlsECCUtils.createNamedECConfig(this.context, selectECDH(TlsECCUtils.getMinimumCurveBits(this.selectedCipherSuite)));
    }

    protected int getMaximumNegotiableCurveBits() {
        int[] clientSupportedGroups = this.context.getSecurityParametersHandshake().getClientSupportedGroups();
        if (clientSupportedGroups == null) {
            return NamedGroup.getMaximumCurveBits();
        }
        int i = 0;
        for (int i2 : clientSupportedGroups) {
            i = Math.max(i, NamedGroup.getCurveBits(i2));
        }
        return i;
    }

    protected int getMaximumNegotiableFiniteFieldBits() {
        int[] clientSupportedGroups = this.context.getSecurityParametersHandshake().getClientSupportedGroups();
        if (clientSupportedGroups == null) {
            return NamedGroup.getMaximumFiniteFieldBits();
        }
        int i = 0;
        for (int i2 : clientSupportedGroups) {
            i = Math.max(i, NamedGroup.getFiniteFieldBits(i2));
        }
        return i;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public NewSessionTicket getNewSessionTicket() throws IOException {
        return new NewSessionTicket(0L, TlsUtils.EMPTY_BYTES);
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsPSKIdentityManager getPSKIdentityManager() throws IOException {
        return null;
    }

    protected Vector getProtocolNames() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsPeer
    public ProtocolVersion[] getProtocolVersions() {
        return this.protocolVersions;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsSRPLoginParameters getSRPLoginParameters() throws IOException {
        return null;
    }

    public int getSelectedCipherSuite() throws IOException {
        int[] commonCipherSuites;
        Vector usableSignatureAlgorithms = TlsUtils.getUsableSignatureAlgorithms(this.context.getSecurityParametersHandshake().getClientSigAlgs());
        int maximumNegotiableCurveBits = getMaximumNegotiableCurveBits();
        int maximumNegotiableFiniteFieldBits = getMaximumNegotiableFiniteFieldBits();
        for (int i : TlsUtils.getCommonCipherSuites(this.offeredCipherSuites, getCipherSuites(), preferLocalCipherSuites())) {
            if (isSelectableCipherSuite(i, maximumNegotiableCurveBits, maximumNegotiableFiniteFieldBits, usableSignatureAlgorithms) && selectCipherSuite(i)) {
                return i;
            }
        }
        throw new TlsFatalAlert((short) 40);
    }

    public Hashtable getServerExtensions() throws IOException {
        Hashtable checkServerExtensions;
        Integer num;
        Vector vector;
        if (!shouldSelectProtocolNameEarly() && (vector = this.clientProtocolNames) != null && !vector.isEmpty()) {
            this.selectedProtocolName = selectProtocolName();
        }
        if (this.selectedProtocolName != null) {
            TlsExtensionsUtils.addALPNExtensionServer(checkServerExtensions(), this.selectedProtocolName);
        }
        if (this.encryptThenMACOffered && allowEncryptThenMAC() && TlsUtils.isBlockCipherSuite(this.selectedCipherSuite)) {
            TlsExtensionsUtils.addEncryptThenMACExtension(checkServerExtensions());
        }
        short s = this.maxFragmentLengthOffered;
        if (s >= 0 && MaxFragmentLength.isValid(s)) {
            TlsExtensionsUtils.addMaxFragmentLengthExtension(checkServerExtensions(), this.maxFragmentLengthOffered);
        }
        if (this.truncatedHMacOffered && allowTruncatedHMac()) {
            TlsExtensionsUtils.addTruncatedHMacExtension(checkServerExtensions());
        }
        if (this.clientSentECPointFormats && TlsECCUtils.isECCCipherSuite(this.selectedCipherSuite)) {
            TlsExtensionsUtils.addSupportedPointFormatsExtension(checkServerExtensions(), new short[]{0});
        }
        if (this.statusRequestV2 == null || !allowMultiCertStatus()) {
            if (this.certificateStatusRequest != null && allowCertificateStatus()) {
                checkServerExtensions = checkServerExtensions();
                num = TlsExtensionsUtils.EXT_status_request;
            }
            if (this.trustedCAKeys != null && allowTrustedCAIndication()) {
                TlsExtensionsUtils.addTrustedCAKeysExtensionServer(checkServerExtensions());
            }
            return this.serverExtensions;
        }
        checkServerExtensions = checkServerExtensions();
        num = TlsExtensionsUtils.EXT_status_request_v2;
        TlsExtensionsUtils.addEmptyExtensionData(checkServerExtensions, num);
        if (this.trustedCAKeys != null) {
            TlsExtensionsUtils.addTrustedCAKeysExtensionServer(checkServerExtensions());
        }
        return this.serverExtensions;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public Vector getServerSupplementalData() throws IOException {
        return null;
    }

    public ProtocolVersion getServerVersion() throws IOException {
        ProtocolVersion[] clientSupportedVersions;
        ProtocolVersion[] protocolVersions = getProtocolVersions();
        for (ProtocolVersion protocolVersion : this.context.getClientSupportedVersions()) {
            if (ProtocolVersion.contains(protocolVersions, protocolVersion)) {
                return protocolVersion;
            }
        }
        throw new TlsFatalAlert((short) 70);
    }

    public TlsSession getSessionToResume(byte[] bArr) {
        return null;
    }

    public int[] getSupportedGroups() throws IOException {
        return new int[]{29, 30, 23, 24, 256, 257, 258};
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void init(TlsServerContext tlsServerContext) {
        this.context = tlsServerContext;
        this.protocolVersions = getSupportedVersions();
        this.cipherSuites = getSupportedCipherSuites();
    }

    protected boolean isSelectableCipherSuite(int i, int i2, int i3, Vector vector) {
        return TlsUtils.isValidVersionForCipherSuite(i, this.context.getServerVersion()) && i2 >= TlsECCUtils.getMinimumCurveBits(i) && i3 >= TlsDHUtils.getMinimumFiniteFieldBits(i) && TlsUtils.isValidCipherSuiteForSignatureAlgorithms(i, vector);
    }

    public void notifyClientCertificate(Certificate certificate) throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void notifyClientVersion(ProtocolVersion protocolVersion) throws IOException {
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void notifyFallback(boolean z) throws IOException {
        ProtocolVersion latestDTLS;
        if (z) {
            ProtocolVersion[] protocolVersions = getProtocolVersions();
            ProtocolVersion clientVersion = this.context.getClientVersion();
            if (clientVersion.isTLS()) {
                latestDTLS = ProtocolVersion.getLatestTLS(protocolVersions);
            } else if (!clientVersion.isDTLS()) {
                throw new TlsFatalAlert((short) 80);
            } else {
                latestDTLS = ProtocolVersion.getLatestDTLS(protocolVersions);
            }
            if (latestDTLS != null && latestDTLS.isLaterVersionOf(clientVersion)) {
                throw new TlsFatalAlert((short) 86);
            }
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyHandshakeBeginning() throws IOException {
        super.notifyHandshakeBeginning();
        this.offeredCipherSuites = null;
        this.clientExtensions = null;
        this.encryptThenMACOffered = false;
        this.maxFragmentLengthOffered = (short) 0;
        this.truncatedHMacOffered = false;
        this.clientSentECPointFormats = false;
        this.certificateStatusRequest = null;
        this.selectedCipherSuite = -1;
        this.selectedProtocolName = null;
        this.serverExtensions = null;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void notifyOfferedCipherSuites(int[] iArr) throws IOException {
        this.offeredCipherSuites = iArr;
    }

    protected boolean preferLocalCipherSuites() {
        return false;
    }

    public void processClientExtensions(Hashtable hashtable) throws IOException {
        Vector vector;
        this.clientExtensions = hashtable;
        if (hashtable != null) {
            this.clientProtocolNames = TlsExtensionsUtils.getALPNExtensionClient(hashtable);
            if (shouldSelectProtocolNameEarly() && (vector = this.clientProtocolNames) != null && !vector.isEmpty()) {
                this.selectedProtocolName = selectProtocolName();
            }
            this.encryptThenMACOffered = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable);
            this.maxFragmentLengthOffered = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable);
            short s = this.maxFragmentLengthOffered;
            if (s >= 0 && !MaxFragmentLength.isValid(s)) {
                throw new TlsFatalAlert((short) 47);
            }
            this.truncatedHMacOffered = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable);
            this.clientSentECPointFormats = TlsExtensionsUtils.getSupportedPointFormatsExtension(hashtable) != null;
            this.certificateStatusRequest = TlsExtensionsUtils.getStatusRequestExtension(hashtable);
            this.statusRequestV2 = TlsExtensionsUtils.getStatusRequestV2Extension(hashtable);
            this.trustedCAKeys = TlsExtensionsUtils.getTrustedCAKeysExtensionClient(hashtable);
        }
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void processClientSupplementalData(Vector vector) throws IOException {
        if (vector == null) {
            return;
        }
        throw new TlsFatalAlert((short) 10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean selectCipherSuite(int i) throws IOException {
        this.selectedCipherSuite = i;
        return true;
    }

    protected int selectDH(int i) {
        int[] clientSupportedGroups = this.context.getSecurityParametersHandshake().getClientSupportedGroups();
        if (clientSupportedGroups == null) {
            return selectDHDefault(i);
        }
        for (int i2 : clientSupportedGroups) {
            if (NamedGroup.getFiniteFieldBits(i2) >= i) {
                return i2;
            }
        }
        return -1;
    }

    protected int selectDHDefault(int i) {
        if (i <= 2048) {
            return 256;
        }
        if (i <= 3072) {
            return 257;
        }
        if (i <= 4096) {
            return 258;
        }
        if (i <= 6144) {
            return 259;
        }
        return i <= 8192 ? 260 : -1;
    }

    protected int selectECDH(int i) {
        int[] clientSupportedGroups = this.context.getSecurityParametersHandshake().getClientSupportedGroups();
        if (clientSupportedGroups == null) {
            return selectECDHDefault(i);
        }
        for (int i2 : clientSupportedGroups) {
            if (NamedGroup.getCurveBits(i2) >= i) {
                return i2;
            }
        }
        return -1;
    }

    protected int selectECDHDefault(int i) {
        if (i <= 256) {
            return 23;
        }
        if (i <= 384) {
            return 24;
        }
        return i <= 521 ? 25 : -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProtocolName selectProtocolName() throws IOException {
        Vector protocolNames = getProtocolNames();
        if (protocolNames == null || protocolNames.isEmpty()) {
            return null;
        }
        ProtocolName selectProtocolName = selectProtocolName(this.clientProtocolNames, protocolNames);
        if (selectProtocolName == null) {
            throw new TlsFatalAlert(AlertDescription.no_application_protocol);
        }
        return selectProtocolName;
    }

    protected ProtocolName selectProtocolName(Vector vector, Vector vector2) {
        for (int i = 0; i < vector2.size(); i++) {
            ProtocolName protocolName = (ProtocolName) vector2.elementAt(i);
            if (vector.contains(protocolName)) {
                return protocolName;
            }
        }
        return null;
    }

    protected boolean shouldSelectProtocolNameEarly() {
        return true;
    }
}
