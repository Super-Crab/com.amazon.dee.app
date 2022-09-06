package org.bouncycastle.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.util.Integers;
/* loaded from: classes5.dex */
public abstract class AbstractTlsClient extends AbstractTlsPeer implements TlsClient {
    protected int[] cipherSuites;
    protected TlsClientContext context;
    protected ProtocolVersion[] protocolVersions;
    protected Vector supportedGroups;
    protected Vector supportedSignatureAlgorithms;
    protected Vector supportedSignatureAlgorithmsCert;

    public AbstractTlsClient(TlsCrypto tlsCrypto) {
        super(tlsCrypto);
    }

    protected boolean allowUnexpectedServerExtension(Integer num, byte[] bArr) throws IOException {
        int intValue = num.intValue();
        if (intValue == 10) {
            TlsExtensionsUtils.readSupportedGroupsExtension(bArr);
            return true;
        } else if (intValue != 11) {
            return false;
        } else {
            TlsExtensionsUtils.readSupportedPointFormatsExtension(bArr);
            return true;
        }
    }

    protected void checkForUnexpectedServerExtension(Hashtable hashtable, Integer num) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, num);
        if (extensionData == null || allowUnexpectedServerExtension(num, extensionData)) {
            return;
        }
        throw new TlsFatalAlert((short) 47);
    }

    protected Vector getCertificateAuthorities() {
        return null;
    }

    protected CertificateStatusRequest getCertificateStatusRequest() {
        return new CertificateStatusRequest((short) 1, new OCSPStatusRequest(null, null));
    }

    @Override // org.bouncycastle.tls.TlsPeer
    public int[] getCipherSuites() {
        return this.cipherSuites;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public Hashtable getClientExtensions() throws IOException {
        Vector certificateAuthorities;
        Hashtable hashtable = new Hashtable();
        boolean z = false;
        boolean z2 = false;
        for (ProtocolVersion protocolVersion : getProtocolVersions()) {
            if (TlsUtils.isTLSv13(protocolVersion)) {
                z = true;
            } else {
                z2 = true;
            }
        }
        Vector protocolNames = getProtocolNames();
        if (protocolNames != null) {
            TlsExtensionsUtils.addALPNExtensionClient(hashtable, protocolNames);
        }
        Vector sNIServerNames = getSNIServerNames();
        if (sNIServerNames != null) {
            TlsExtensionsUtils.addServerNameExtensionClient(hashtable, sNIServerNames);
        }
        CertificateStatusRequest certificateStatusRequest = getCertificateStatusRequest();
        if (certificateStatusRequest != null) {
            TlsExtensionsUtils.addStatusRequestExtension(hashtable, certificateStatusRequest);
        }
        if (z && (certificateAuthorities = getCertificateAuthorities()) != null) {
            TlsExtensionsUtils.addCertificateAuthoritiesExtension(hashtable, certificateAuthorities);
        }
        if (z2) {
            TlsExtensionsUtils.addEncryptThenMACExtension(hashtable);
            Vector multiCertStatusRequest = getMultiCertStatusRequest();
            if (multiCertStatusRequest != null) {
                TlsExtensionsUtils.addStatusRequestV2Extension(hashtable, multiCertStatusRequest);
            }
            Vector trustedCAIndication = getTrustedCAIndication();
            if (trustedCAIndication != null) {
                TlsExtensionsUtils.addTrustedCAKeysExtensionClient(hashtable, trustedCAIndication);
            }
        }
        if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.context.getClientVersion())) {
            Vector supportedSignatureAlgorithms = getSupportedSignatureAlgorithms();
            if (supportedSignatureAlgorithms != null && !supportedSignatureAlgorithms.isEmpty()) {
                this.supportedSignatureAlgorithms = supportedSignatureAlgorithms;
                TlsExtensionsUtils.addSignatureAlgorithmsExtension(hashtable, supportedSignatureAlgorithms);
            }
            Vector supportedSignatureAlgorithmsCert = getSupportedSignatureAlgorithmsCert();
            if (supportedSignatureAlgorithmsCert != null && !supportedSignatureAlgorithmsCert.isEmpty()) {
                this.supportedSignatureAlgorithmsCert = supportedSignatureAlgorithmsCert;
                TlsExtensionsUtils.addSignatureAlgorithmsCertExtension(hashtable, supportedSignatureAlgorithmsCert);
            }
        }
        Vector namedGroupRoles = getNamedGroupRoles();
        Vector supportedGroups = getSupportedGroups(namedGroupRoles);
        if (supportedGroups != null && !supportedGroups.isEmpty()) {
            this.supportedGroups = supportedGroups;
            TlsExtensionsUtils.addSupportedGroupsExtension(hashtable, supportedGroups);
        }
        if (z2 && (namedGroupRoles.contains(Integers.valueOf(2)) || namedGroupRoles.contains(Integers.valueOf(3)))) {
            TlsExtensionsUtils.addSupportedPointFormatsExtension(hashtable, new short[]{0});
        }
        return hashtable;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public Vector getClientSupplementalData() throws IOException {
        return null;
    }

    public TlsDHGroupVerifier getDHGroupVerifier() {
        return new DefaultTlsDHGroupVerifier();
    }

    @Override // org.bouncycastle.tls.TlsClient
    public Vector getEarlyKeyShareGroups() {
        Object elementAt;
        Vector vector = this.supportedGroups;
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        int i = 29;
        if (!this.supportedGroups.contains(Integers.valueOf(29))) {
            i = 23;
            if (!this.supportedGroups.contains(Integers.valueOf(23))) {
                elementAt = this.supportedGroups.elementAt(0);
                return TlsUtils.vectorOfOne(elementAt);
            }
        }
        elementAt = Integers.valueOf(i);
        return TlsUtils.vectorOfOne(elementAt);
    }

    protected Vector getMultiCertStatusRequest() {
        return null;
    }

    protected Vector getNamedGroupRoles() {
        Vector namedGroupRoles = TlsUtils.getNamedGroupRoles(getCipherSuites());
        Vector vector = this.supportedSignatureAlgorithms;
        Vector vector2 = this.supportedSignatureAlgorithmsCert;
        if (vector == null || TlsUtils.containsAnySignatureAlgorithm(vector, (short) 3) || (vector2 != null && TlsUtils.containsAnySignatureAlgorithm(vector2, (short) 3))) {
            TlsUtils.addToSet(namedGroupRoles, 3);
        }
        return namedGroupRoles;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public TlsPSKIdentity getPSKIdentity() throws IOException {
        return null;
    }

    protected Vector getProtocolNames() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsPeer
    public ProtocolVersion[] getProtocolVersions() {
        return this.protocolVersions;
    }

    protected Vector getSNIServerNames() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public TlsSRPConfigVerifier getSRPConfigVerifier() {
        return new DefaultTlsSRPConfigVerifier();
    }

    @Override // org.bouncycastle.tls.TlsClient
    public TlsSRPIdentity getSRPIdentity() throws IOException {
        return null;
    }

    public TlsSession getSessionToResume() {
        return null;
    }

    protected Vector getSupportedGroups(Vector vector) {
        TlsCrypto mo12857getCrypto = mo12857getCrypto();
        Vector vector2 = new Vector();
        if (vector.contains(Integers.valueOf(2))) {
            TlsUtils.addIfSupported(vector2, mo12857getCrypto, new int[]{29, 30});
        }
        if (vector.contains(Integers.valueOf(2)) || vector.contains(Integers.valueOf(3))) {
            TlsUtils.addIfSupported(vector2, mo12857getCrypto, new int[]{23, 24});
        }
        if (vector.contains(Integers.valueOf(1))) {
            TlsUtils.addIfSupported(vector2, mo12857getCrypto, new int[]{256, 257, 258});
        }
        return vector2;
    }

    protected Vector getSupportedSignatureAlgorithms() {
        return TlsUtils.getDefaultSupportedSignatureAlgorithms(this.context);
    }

    protected Vector getSupportedSignatureAlgorithmsCert() {
        return null;
    }

    protected Vector getTrustedCAIndication() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public void init(TlsClientContext tlsClientContext) {
        this.context = tlsClientContext;
        this.protocolVersions = getSupportedVersions();
        this.cipherSuites = getSupportedCipherSuites();
    }

    @Override // org.bouncycastle.tls.TlsClient
    public boolean isFallback() {
        return false;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyHandshakeBeginning() throws IOException {
        super.notifyHandshakeBeginning();
        this.supportedGroups = null;
        this.supportedSignatureAlgorithms = null;
        this.supportedSignatureAlgorithmsCert = null;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public void notifyNewSessionTicket(NewSessionTicket newSessionTicket) throws IOException {
    }

    public void notifySelectedCipherSuite(int i) {
    }

    public void notifyServerVersion(ProtocolVersion protocolVersion) throws IOException {
    }

    public void notifySessionID(byte[] bArr) {
    }

    @Override // org.bouncycastle.tls.TlsClient
    public void processServerExtensions(Hashtable hashtable) throws IOException {
        if (hashtable == null) {
            return;
        }
        checkForUnexpectedServerExtension(hashtable, TlsExtensionsUtils.EXT_signature_algorithms);
        checkForUnexpectedServerExtension(hashtable, TlsExtensionsUtils.EXT_signature_algorithms_cert);
        checkForUnexpectedServerExtension(hashtable, TlsExtensionsUtils.EXT_supported_groups);
        if (TlsECCUtils.isECCCipherSuite(this.context.getSecurityParametersHandshake().getCipherSuite())) {
            TlsExtensionsUtils.getSupportedPointFormatsExtension(hashtable);
        } else {
            checkForUnexpectedServerExtension(hashtable, TlsExtensionsUtils.EXT_ec_point_formats);
        }
        checkForUnexpectedServerExtension(hashtable, TlsExtensionsUtils.EXT_padding);
    }

    @Override // org.bouncycastle.tls.TlsClient
    public void processServerSupplementalData(Vector vector) throws IOException {
        if (vector == null) {
            return;
        }
        throw new TlsFatalAlert((short) 10);
    }
}
