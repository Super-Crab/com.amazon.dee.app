package org.bouncycastle.jsse.provider;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreParameters;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
/* loaded from: classes4.dex */
class ProvX509TrustManager extends BCX509ExtendedTrustManager {
    private final X509TrustManager exportX509TrustManager;
    private final JcaJceHelper helper;
    private final PKIXBuilderParameters pkixParametersTemplate;
    private final Set<X509Certificate> trustedCerts;
    private static final Logger LOG = Logger.getLogger(ProvX509TrustManager.class.getName());
    private static final boolean provCheckRevocation = PropertyUtils.getBooleanSystemProperty("com.sun.net.ssl.checkRevocation", false);
    private static final boolean provTrustManagerCheckEKU = PropertyUtils.getBooleanSystemProperty("org.bouncycastle.jsse.trustManager.checkEKU", true);
    private static final Map<String, Integer> keyUsagesServer = createKeyUsagesServer();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvX509TrustManager(JcaJceHelper jcaJceHelper, PKIXParameters pKIXParameters) throws InvalidAlgorithmParameterException {
        this.helper = jcaJceHelper;
        this.trustedCerts = getTrustedCerts(pKIXParameters.getTrustAnchors());
        if (this.trustedCerts.isEmpty()) {
            this.pkixParametersTemplate = null;
        } else if (pKIXParameters instanceof PKIXBuilderParameters) {
            this.pkixParametersTemplate = (PKIXBuilderParameters) pKIXParameters.clone();
            this.pkixParametersTemplate.setTargetCertConstraints(null);
        } else {
            this.pkixParametersTemplate = new PKIXBuilderParameters(pKIXParameters.getTrustAnchors(), (CertSelector) null);
            this.pkixParametersTemplate.setAnyPolicyInhibited(pKIXParameters.isAnyPolicyInhibited());
            this.pkixParametersTemplate.setCertPathCheckers(pKIXParameters.getCertPathCheckers());
            this.pkixParametersTemplate.setCertStores(pKIXParameters.getCertStores());
            this.pkixParametersTemplate.setDate(pKIXParameters.getDate());
            this.pkixParametersTemplate.setExplicitPolicyRequired(pKIXParameters.isExplicitPolicyRequired());
            this.pkixParametersTemplate.setInitialPolicies(pKIXParameters.getInitialPolicies());
            this.pkixParametersTemplate.setPolicyMappingInhibited(pKIXParameters.isPolicyMappingInhibited());
            this.pkixParametersTemplate.setPolicyQualifiersRejected(pKIXParameters.getPolicyQualifiersRejected());
            this.pkixParametersTemplate.setRevocationEnabled(pKIXParameters.isRevocationEnabled());
            this.pkixParametersTemplate.setSigProvider(pKIXParameters.getSigProvider());
        }
        this.exportX509TrustManager = X509TrustManagerUtil.exportX509TrustManager(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvX509TrustManager(JcaJceHelper jcaJceHelper, Set<TrustAnchor> set) throws InvalidAlgorithmParameterException {
        this.helper = jcaJceHelper;
        this.trustedCerts = getTrustedCerts(set);
        if (this.trustedCerts.isEmpty()) {
            this.pkixParametersTemplate = null;
        } else {
            this.pkixParametersTemplate = new PKIXBuilderParameters(set, (CertSelector) null);
            this.pkixParametersTemplate.setRevocationEnabled(provCheckRevocation);
        }
        this.exportX509TrustManager = X509TrustManagerUtil.exportX509TrustManager(this);
    }

    private static void addKeyUsageServer(Map<String, Integer> map, int i, int... iArr) {
        for (int i2 : iArr) {
            if (map.put(JsseUtils.getAuthTypeServer(i2), Integer.valueOf(i)) != null) {
                throw new IllegalStateException("Duplicate keys in server key usages");
            }
        }
    }

    private static void addStatusResponses(CertPathBuilder certPathBuilder, PKIXBuilderParameters pKIXBuilderParameters, X509Certificate[] x509CertificateArr, List<byte[]> list) {
        HashMap hashMap = new HashMap();
        int min = Math.min(x509CertificateArr.length, list.size());
        for (int i = 0; i < min; i++) {
            byte[] bArr = list.get(i);
            if (bArr != null && bArr.length > 0) {
                X509Certificate x509Certificate = x509CertificateArr[i];
                if (!hashMap.containsKey(x509Certificate)) {
                    hashMap.put(x509Certificate, bArr);
                }
            }
        }
        if (!hashMap.isEmpty()) {
            try {
                PKIXUtil.addStatusResponses(certPathBuilder, pKIXBuilderParameters, hashMap);
            } catch (RuntimeException e) {
                LOG.log(Level.FINE, "Failed to add status responses for revocation checking", (Throwable) e);
            }
        }
    }

    private X509Certificate[] buildCertPath(X509Certificate[] x509CertificateArr, BCAlgorithmConstraints bCAlgorithmConstraints, List<byte[]> list) throws GeneralSecurityException {
        CertStore certStore;
        CertPathBuilder certPathBuilder;
        X509Certificate x509Certificate = x509CertificateArr[0];
        if (this.trustedCerts.contains(x509Certificate)) {
            return new X509Certificate[]{x509Certificate};
        }
        Provider provider = this.helper.createCertificateFactory(KeyUtils.X509_CERITIFATE_FACTORY).getProvider();
        CertStoreParameters certStoreParameters = getCertStoreParameters(x509Certificate, x509CertificateArr);
        try {
            certStore = CertStore.getInstance("Collection", certStoreParameters, provider);
        } catch (GeneralSecurityException unused) {
            certStore = CertStore.getInstance("Collection", certStoreParameters);
        }
        X509CertSelector x509CertSelector = new X509CertSelector();
        x509CertSelector.setCertificate(x509Certificate);
        try {
            certPathBuilder = CertPathBuilder.getInstance("PKIX", provider);
        } catch (NoSuchAlgorithmException unused2) {
            certPathBuilder = CertPathBuilder.getInstance("PKIX");
        }
        PKIXBuilderParameters pKIXBuilderParameters = (PKIXBuilderParameters) this.pkixParametersTemplate.clone();
        pKIXBuilderParameters.addCertPathChecker(new ProvAlgorithmChecker(this.helper, bCAlgorithmConstraints));
        pKIXBuilderParameters.addCertStore(certStore);
        pKIXBuilderParameters.setTargetCertConstraints(x509CertSelector);
        if (!list.isEmpty()) {
            addStatusResponses(certPathBuilder, pKIXBuilderParameters, x509CertificateArr, list);
        }
        PKIXCertPathBuilderResult pKIXCertPathBuilderResult = (PKIXCertPathBuilderResult) certPathBuilder.build(pKIXBuilderParameters);
        return getTrustedChain(pKIXCertPathBuilderResult.getCertPath(), pKIXCertPathBuilderResult.getTrustAnchor());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkEndpointID(String str, X509Certificate x509Certificate, String str2) throws CertificateException {
        boolean z;
        String stripSquareBrackets = JsseUtils.stripSquareBrackets(str);
        if (str2.equalsIgnoreCase("HTTPS")) {
            z = true;
        } else if (!str2.equalsIgnoreCase("LDAP") && !str2.equalsIgnoreCase("LDAPS")) {
            throw new CertificateException(GeneratedOutlineSupport1.outline72("Unknown endpoint ID algorithm: ", str2));
        } else {
            z = false;
        }
        HostnameUtil.checkHostname(stripSquareBrackets, x509Certificate, z);
    }

    private static void checkEndpointID(X509Certificate x509Certificate, String str, boolean z, BCExtendedSSLSession bCExtendedSSLSession) throws CertificateException {
        BCSNIHostName sNIHostName;
        String peerHost = bCExtendedSSLSession.getPeerHost();
        if (z && (sNIHostName = JsseUtils.getSNIHostName(bCExtendedSSLSession.getRequestedServerNames())) != null) {
            String asciiName = sNIHostName.getAsciiName();
            if (!asciiName.equalsIgnoreCase(peerHost)) {
                try {
                    checkEndpointID(asciiName, x509Certificate, str);
                    return;
                } catch (CertificateException e) {
                    Logger logger = LOG;
                    Level level = Level.FINE;
                    logger.log(level, "Server's endpoint ID did not match the SNI host_name: " + asciiName, (Throwable) e);
                }
            }
        }
        checkEndpointID(peerHost, x509Certificate, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkExtendedTrust(X509Certificate[] x509CertificateArr, String str, TransportData transportData, boolean z) throws CertificateException {
        String endpointIdentificationAlgorithm;
        if (transportData == null || (endpointIdentificationAlgorithm = transportData.getParameters().getEndpointIdentificationAlgorithm()) == null || endpointIdentificationAlgorithm.length() <= 0) {
            return;
        }
        BCExtendedSSLSession handshakeSession = transportData.getHandshakeSession();
        if (handshakeSession == null) {
            throw new CertificateException("No handshake session");
        }
        checkEndpointID(x509CertificateArr[0], endpointIdentificationAlgorithm, z, handshakeSession);
    }

    private void checkTrusted(X509Certificate[] x509CertificateArr, String str, TransportData transportData, boolean z) throws CertificateException {
        if (x509CertificateArr == null || x509CertificateArr.length < 1) {
            throw new IllegalArgumentException("'chain' must be a chain of at least one certificate");
        }
        if (str == null || str.length() < 1) {
            throw new IllegalArgumentException("'authType' must be a non-null, non-empty string");
        }
        if (this.pkixParametersTemplate == null) {
            throw new CertificateException("Unable to build a CertPath: no PKIXBuilderParameters available");
        }
        checkExtendedTrust(validateChain(x509CertificateArr, str, transportData, z), str, transportData, z);
    }

    private static Map<String, Integer> createKeyUsagesServer() {
        HashMap hashMap = new HashMap();
        addKeyUsageServer(hashMap, 0, 3, 5, 17, 19, 0);
        addKeyUsageServer(hashMap, 2, 1);
        addKeyUsageServer(hashMap, 4, 7, 9, 16, 18);
        return Collections.unmodifiableMap(hashMap);
    }

    private CertStoreParameters getCertStoreParameters(X509Certificate x509Certificate, X509Certificate[] x509CertificateArr) {
        ArrayList arrayList = new ArrayList(x509CertificateArr.length);
        arrayList.add(x509Certificate);
        for (int i = 1; i < x509CertificateArr.length; i++) {
            if (!this.trustedCerts.contains(x509CertificateArr[i])) {
                arrayList.add(x509CertificateArr[i]);
            }
        }
        return new CollectionCertStoreParameters(Collections.unmodifiableCollection(arrayList));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyPurposeId getRequiredExtendedKeyUsage(boolean z) {
        if (!provTrustManagerCheckEKU) {
            return null;
        }
        return z ? KeyPurposeId.id_kp_serverAuth : KeyPurposeId.id_kp_clientAuth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getRequiredKeyUsage(boolean z, String str) throws CertificateException {
        if (!z) {
            return 0;
        }
        Integer num = keyUsagesServer.get(str);
        if (num == null) {
            throw new CertificateException(GeneratedOutlineSupport1.outline72("Unsupported server authType: ", str));
        }
        return num.intValue();
    }

    private static X509Certificate getTrustedCert(TrustAnchor trustAnchor) throws CertificateException {
        X509Certificate trustedCert = trustAnchor.getTrustedCert();
        if (trustedCert != null) {
            return trustedCert;
        }
        throw new CertificateException("No certificate for TrustAnchor");
    }

    private static Set<X509Certificate> getTrustedCerts(Set<TrustAnchor> set) {
        X509Certificate trustedCert;
        HashSet hashSet = new HashSet(set.size());
        for (TrustAnchor trustAnchor : set) {
            if (trustAnchor != null && (trustedCert = trustAnchor.getTrustedCert()) != null) {
                hashSet.add(trustedCert);
            }
        }
        return hashSet;
    }

    private static X509Certificate[] getTrustedChain(CertPath certPath, TrustAnchor trustAnchor) throws CertificateException {
        List<? extends Certificate> certificates = certPath.getCertificates();
        X509Certificate[] x509CertificateArr = new X509Certificate[certificates.size() + 1];
        certificates.toArray(x509CertificateArr);
        x509CertificateArr[x509CertificateArr.length - 1] = getTrustedCert(trustAnchor);
        return x509CertificateArr;
    }

    private X509Certificate[] validateChain(X509Certificate[] x509CertificateArr, String str, TransportData transportData, boolean z) throws CertificateException {
        try {
            BCAlgorithmConstraints algorithmConstraints = TransportData.getAlgorithmConstraints(transportData, false);
            X509Certificate[] buildCertPath = buildCertPath(x509CertificateArr, algorithmConstraints, TransportData.getStatusResponses(transportData));
            ProvAlgorithmChecker.checkCertPathExtras(this.helper, algorithmConstraints, buildCertPath, getRequiredExtendedKeyUsage(z), getRequiredKeyUsage(z, str));
            return buildCertPath;
        } catch (GeneralSecurityException e) {
            throw new CertificateException("Unable to construct a valid chain", e);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        checkTrusted(x509CertificateArr, str, null, false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        checkTrusted(x509CertificateArr, str, TransportData.from(socket), false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        checkTrusted(x509CertificateArr, str, TransportData.from(sSLEngine), false);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        checkTrusted(x509CertificateArr, str, null, true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        checkTrusted(x509CertificateArr, str, TransportData.from(socket), true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        checkTrusted(x509CertificateArr, str, TransportData.from(sSLEngine), true);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        Set<X509Certificate> set = this.trustedCerts;
        return (X509Certificate[]) set.toArray(new X509Certificate[set.size()]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public X509TrustManager getExportX509TrustManager() {
        return this.exportX509TrustManager;
    }
}
