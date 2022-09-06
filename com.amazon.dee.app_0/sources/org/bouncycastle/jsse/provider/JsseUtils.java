package org.bouncycastle.jsse.provider;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
import org.bouncycastle.tls.AlertDescription;
import org.bouncycastle.tls.AlertLevel;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.CertificateEntry;
import org.bouncycastle.tls.CertificateStatus;
import org.bouncycastle.tls.ProtocolName;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.ServerName;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsContext;
import org.bouncycastle.tls.TlsCredentialedDecryptor;
import org.bouncycastle.tls.TlsCredentialedSigner;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.TrustedAuthority;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaDefaultTlsCredentialedSigner;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCertificate;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.tls.crypto.impl.jcajce.JceDefaultTlsCredentialedDecryptor;
/* loaded from: classes4.dex */
abstract class JsseUtils {
    private static final boolean provTlsAllowLegacyMasterSecret = PropertyUtils.getBooleanSystemProperty("jdk.tls.allowLegacyMasterSecret", true);
    private static final boolean provTlsAllowLegacyResumption = PropertyUtils.getBooleanSystemProperty("jdk.tls.allowLegacyResumption", false);
    private static final boolean provTlsUseExtendedMasterSecret = PropertyUtils.getBooleanSystemProperty("jdk.tls.useExtendedMasterSecret", true);
    static final Set<BCCryptoPrimitive> KEY_AGREEMENT_CRYPTO_PRIMITIVES_BC = Collections.unmodifiableSet(EnumSet.of(BCCryptoPrimitive.KEY_AGREEMENT));
    static final Set<BCCryptoPrimitive> KEY_ENCAPSULATION_CRYPTO_PRIMITIVES_BC = Collections.unmodifiableSet(EnumSet.of(BCCryptoPrimitive.KEY_ENCAPSULATION));
    static final Set<BCCryptoPrimitive> SIGNATURE_CRYPTO_PRIMITIVES_BC = Collections.unmodifiableSet(EnumSet.of(BCCryptoPrimitive.SIGNATURE));
    protected static X509Certificate[] EMPTY_CHAIN = new X509Certificate[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class BCUnknownServerName extends BCSNIServerName {
        /* JADX INFO: Access modifiers changed from: package-private */
        public BCUnknownServerName(int i, byte[] bArr) {
            super(i, bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean allowLegacyMasterSecret() {
        return provTlsAllowLegacyMasterSecret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean allowLegacyResumption() {
        return provTlsAllowLegacyResumption;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T[] clone(T[] tArr) {
        if (tArr == null) {
            return null;
        }
        return (T[]) ((Object[]) tArr.clone());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean contains(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> boolean containsNull(T[] tArr) {
        for (T t : tArr) {
            if (t == null) {
                return true;
            }
        }
        return false;
    }

    static BCSNIServerName convertSNIServerName(ServerName serverName) {
        short nameType = serverName.getNameType();
        byte[] nameData = serverName.getNameData();
        return nameType != 0 ? new BCUnknownServerName(nameType, nameData) : new BCSNIHostName(nameData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<BCSNIServerName> convertSNIServerNames(Vector<ServerName> vector) {
        if (vector == null || vector.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(vector.size());
        Enumeration<ServerName> elements = vector.elements();
        while (elements.hasMoreElements()) {
            arrayList.add(convertSNIServerName(elements.nextElement()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    static String[] copyOf(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, Math.min(strArr.length, i));
        return strArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedDecryptor createCredentialedDecryptor(JcaTlsCrypto jcaTlsCrypto, BCX509Key bCX509Key) {
        return new JceDefaultTlsCredentialedDecryptor(jcaTlsCrypto, getCertificateMessage(jcaTlsCrypto, bCX509Key.getCertificateChain()), bCX509Key.getPrivateKey());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner createCredentialedSigner(TlsContext tlsContext, JcaTlsCrypto jcaTlsCrypto, BCX509Key bCX509Key, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return new JcaDefaultTlsCredentialedSigner(new TlsCryptoParameters(tlsContext), jcaTlsCrypto, bCX509Key.getPrivateKey(), getCertificateMessage(jcaTlsCrypto, bCX509Key.getCertificateChain()), signatureAndHashAlgorithm);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner createCredentialedSigner13(TlsContext tlsContext, JcaTlsCrypto jcaTlsCrypto, BCX509Key bCX509Key, SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        return new JcaDefaultTlsCredentialedSigner(new TlsCryptoParameters(tlsContext), jcaTlsCrypto, bCX509Key.getPrivateKey(), getCertificateMessage13(jcaTlsCrypto, bCX509Key.getCertificateChain(), bArr), signatureAndHashAlgorithm);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x000e, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.bouncycastle.jsse.BCSNIServerName findMatchingSNIServerName(java.util.Vector<org.bouncycastle.tls.ServerName> r5, java.util.Collection<org.bouncycastle.jsse.BCSNIMatcher> r6) {
        /*
            boolean r0 = r5.isEmpty()
            if (r0 != 0) goto L40
            java.util.List r5 = convertSNIServerNames(r5)
            java.util.Iterator r6 = r6.iterator()
        Le:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L40
            java.lang.Object r0 = r6.next()
            org.bouncycastle.jsse.BCSNIMatcher r0 = (org.bouncycastle.jsse.BCSNIMatcher) r0
            if (r0 == 0) goto Le
            int r1 = r0.getType()
            java.util.Iterator r2 = r5.iterator()
        L24:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto Le
            java.lang.Object r3 = r2.next()
            org.bouncycastle.jsse.BCSNIServerName r3 = (org.bouncycastle.jsse.BCSNIServerName) r3
            if (r3 == 0) goto L24
            int r4 = r3.getType()
            if (r4 == r1) goto L39
            goto L24
        L39:
            boolean r0 = r0.matches(r3)
            if (r0 == 0) goto Le
            return r3
        L40:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jsse.provider.JsseUtils.findMatchingSNIServerName(java.util.Vector, java.util.Collection):org.bouncycastle.jsse.BCSNIServerName");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getAlertLogMessage(String str, short s, short s2) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, " ");
        outline113.append(AlertLevel.getText(s));
        outline113.append(" ");
        outline113.append(AlertDescription.getText(s2));
        outline113.append(" alert");
        return outline113.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getApplicationProtocol(SecurityParameters securityParameters) {
        if (securityParameters == null || !securityParameters.isApplicationProtocolSet()) {
            return null;
        }
        ProtocolName applicationProtocol = securityParameters.getApplicationProtocol();
        return applicationProtocol == null ? "" : applicationProtocol.getUtf8Decoding();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getAuthTypeClient(short s) {
        switch (s) {
            case 1:
                return KeyUtils.ALGORITHM_RSA;
            case 2:
                return "DSA";
            case 3:
                return KeyUtils.ALGORITHM_EC;
            case 4:
            case 5:
            case 6:
                return KeyUtils.ALGORITHM_RSA;
            case 7:
                return EdDSAParameterSpec.Ed25519;
            case 8:
                return EdDSAParameterSpec.Ed448;
            case 9:
            case 10:
            case 11:
                return "RSASSA-PSS";
            default:
                throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getAuthTypeServer(int i) {
        if (i != 0) {
            if (i == 1) {
                return "KE:RSA";
            }
            if (i == 3) {
                return "DHE_DSS";
            }
            if (i == 5) {
                return "DHE_RSA";
            }
            if (i == 7) {
                return "DH_DSS";
            }
            if (i == 9) {
                return "DH_RSA";
            }
            if (i == 22) {
                return "SRP_DSS";
            }
            if (i == 23) {
                return "SRP_RSA";
            }
            switch (i) {
                case 16:
                    return "ECDH_ECDSA";
                case 17:
                    return "ECDHE_ECDSA";
                case 18:
                    return "ECDH_RSA";
                case 19:
                    return "ECDHE_RSA";
                default:
                    throw new IllegalArgumentException();
            }
        }
        return "UNKNOWN";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Vector<X500Name> getCertificateAuthorities(BCX509ExtendedTrustManager bCX509ExtendedTrustManager) {
        X509Certificate[] acceptedIssuers;
        HashSet<X500Principal> hashSet = new HashSet();
        for (X509Certificate x509Certificate : bCX509ExtendedTrustManager.getAcceptedIssuers()) {
            if (x509Certificate.getBasicConstraints() >= 0) {
                hashSet.add(x509Certificate.getSubjectX500Principal());
            }
        }
        if (hashSet.isEmpty()) {
            return null;
        }
        Vector<X500Name> vector = new Vector<>(hashSet.size());
        for (X500Principal x500Principal : hashSet) {
            vector.add(X500Name.getInstance(x500Principal.getEncoded()));
        }
        return vector;
    }

    static Certificate getCertificateMessage(JcaTlsCrypto jcaTlsCrypto, X509Certificate[] x509CertificateArr) {
        if (x509CertificateArr == null || x509CertificateArr.length < 1) {
            throw new IllegalArgumentException();
        }
        TlsCertificate[] tlsCertificateArr = new TlsCertificate[x509CertificateArr.length];
        for (int i = 0; i < x509CertificateArr.length; i++) {
            tlsCertificateArr[i] = new JcaTlsCertificate(jcaTlsCrypto, x509CertificateArr[i]);
        }
        return new Certificate(tlsCertificateArr);
    }

    static Certificate getCertificateMessage13(JcaTlsCrypto jcaTlsCrypto, X509Certificate[] x509CertificateArr, byte[] bArr) {
        if (x509CertificateArr == null || x509CertificateArr.length < 1) {
            throw new IllegalArgumentException();
        }
        CertificateEntry[] certificateEntryArr = new CertificateEntry[x509CertificateArr.length];
        for (int i = 0; i < x509CertificateArr.length; i++) {
            certificateEntryArr[i] = new CertificateEntry(new JcaTlsCertificate(jcaTlsCrypto, x509CertificateArr[i]), null);
        }
        return new Certificate(bArr, certificateEntryArr);
    }

    static X509Certificate getEndEntity(JcaTlsCrypto jcaTlsCrypto, Certificate certificate) throws IOException {
        if (certificate == null || certificate.isEmpty()) {
            return null;
        }
        return getX509Certificate(jcaTlsCrypto, certificate.getCertificateAt(0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getJcaSignatureAlgorithmBC(String str, String str2) {
        return !str.endsWith("withRSAandMGF1") ? str : GeneratedOutlineSupport1.outline75(str, ":", str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getKeyAlgorithm(Key key) {
        return key instanceof PrivateKey ? getPrivateKeyAlgorithm((PrivateKey) key) : key instanceof PublicKey ? getPublicKeyAlgorithm((PublicKey) key) : key.getAlgorithm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getKeyType(SignatureSchemeInfo signatureSchemeInfo) {
        return signatureSchemeInfo.getKeyAlgorithm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getKeyTypeLegacyClient(short s) {
        if (s != 1) {
            if (s == 2) {
                return "DSA";
            }
            if (s != 64) {
                throw new IllegalArgumentException();
            }
            return KeyUtils.ALGORITHM_EC;
        }
        return KeyUtils.ALGORITHM_RSA;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getKeyTypeLegacyServer(int i) {
        return getAuthTypeServer(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getPrivateKeyAlgorithm(PrivateKey privateKey) {
        String algorithm = privateKey.getAlgorithm();
        if (KeyUtils.ALGORITHM_RSA.equalsIgnoreCase(algorithm)) {
            if (PKCSObjectIdentifiers.id_RSASSA_PSS.equals((ASN1Primitive) PrivateKeyInfo.getInstance(privateKey.getEncoded()).getPrivateKeyAlgorithm().getAlgorithm())) {
                return "RSASSA-PSS";
            }
        }
        return algorithm;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> getProtocolNames(Vector<ProtocolName> vector) {
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(vector.size());
        Iterator<ProtocolName> it2 = vector.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().getUtf8Decoding());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Vector<ProtocolName> getProtocolNames(String[] strArr) {
        if (strArr == null || strArr.length < 1) {
            return null;
        }
        Vector<ProtocolName> vector = new Vector<>(strArr.length);
        for (String str : strArr) {
            vector.add(ProtocolName.asUtf8Encoding(str));
        }
        return vector;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getPublicKeyAlgorithm(PublicKey publicKey) {
        String algorithm = publicKey.getAlgorithm();
        if (KeyUtils.ALGORITHM_RSA.equalsIgnoreCase(algorithm)) {
            if (PKCSObjectIdentifiers.id_RSASSA_PSS.equals((ASN1Primitive) SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getAlgorithm().getAlgorithm())) {
                return "RSASSA-PSS";
            }
        }
        return algorithm;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        if ((r1 instanceof org.bouncycastle.jsse.BCSNIHostName) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
        return (org.bouncycastle.jsse.BCSNIHostName) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
        return new org.bouncycastle.jsse.BCSNIHostName(r1.getEncoded());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.bouncycastle.jsse.BCSNIHostName getSNIHostName(java.util.List<org.bouncycastle.jsse.BCSNIServerName> r3) {
        /*
            r0 = 0
            if (r3 == 0) goto L2c
            java.util.Iterator r3 = r3.iterator()
        L7:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L2c
            java.lang.Object r1 = r3.next()
            org.bouncycastle.jsse.BCSNIServerName r1 = (org.bouncycastle.jsse.BCSNIServerName) r1
            if (r1 == 0) goto L7
            int r2 = r1.getType()
            if (r2 != 0) goto L7
            boolean r3 = r1 instanceof org.bouncycastle.jsse.BCSNIHostName
            if (r3 == 0) goto L22
            org.bouncycastle.jsse.BCSNIHostName r1 = (org.bouncycastle.jsse.BCSNIHostName) r1
            return r1
        L22:
            org.bouncycastle.jsse.BCSNIHostName r3 = new org.bouncycastle.jsse.BCSNIHostName     // Catch: java.lang.RuntimeException -> L2c
            byte[] r1 = r1.getEncoded()     // Catch: java.lang.RuntimeException -> L2c
            r3.<init>(r1)     // Catch: java.lang.RuntimeException -> L2c
            return r3
        L2c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jsse.provider.JsseUtils.getSNIHostName(java.util.List):org.bouncycastle.jsse.BCSNIHostName");
    }

    static byte[] getStatusResponse(OCSPResponse oCSPResponse) throws IOException {
        return oCSPResponse == null ? TlsUtils.EMPTY_BYTES : oCSPResponse.getEncoded("DER");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<byte[]> getStatusResponses(CertificateStatus certificateStatus) throws IOException {
        if (certificateStatus != null) {
            short statusType = certificateStatus.getStatusType();
            if (statusType == 1) {
                return Collections.singletonList(getStatusResponse(certificateStatus.getOCSPResponse()));
            }
            if (statusType != 2) {
                return null;
            }
            Vector oCSPResponseList = certificateStatus.getOCSPResponseList();
            int size = oCSPResponseList.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(getStatusResponse((OCSPResponse) oCSPResponseList.elementAt(i)));
            }
            return Collections.unmodifiableList(arrayList);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Principal getSubject(JcaTlsCrypto jcaTlsCrypto, Certificate certificate) {
        if (certificate == null || certificate.isEmpty()) {
            return null;
        }
        try {
            return getX509Certificate(jcaTlsCrypto, certificate.getCertificateAt(0)).getSubjectX500Principal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Principal[] getTrustedIssuers(Vector<TrustedAuthority> vector) throws IOException {
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        int size = vector.size();
        X500Principal[] x500PrincipalArr = new X500Principal[size];
        for (int i = 0; i < size; i++) {
            TrustedAuthority trustedAuthority = vector.get(i);
            if (2 != trustedAuthority.getIdentifierType()) {
                return null;
            }
            x500PrincipalArr[i] = toX500Principal(trustedAuthority.getX509Name());
        }
        return x500PrincipalArr;
    }

    static X509Certificate getX509Certificate(JcaTlsCrypto jcaTlsCrypto, TlsCertificate tlsCertificate) throws IOException {
        return JcaTlsCertificate.convert(jcaTlsCrypto, tlsCertificate).getX509Certificate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509Certificate[] getX509CertificateChain(JcaTlsCrypto jcaTlsCrypto, Certificate certificate) {
        if (certificate == null || certificate.isEmpty()) {
            return EMPTY_CHAIN;
        }
        try {
            X509Certificate[] x509CertificateArr = new X509Certificate[certificate.getLength()];
            for (int i = 0; i < x509CertificateArr.length; i++) {
                x509CertificateArr[i] = JcaTlsCertificate.convert(jcaTlsCrypto, certificate.getCertificateAt(i)).getX509Certificate();
            }
            return x509CertificateArr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509Certificate[] getX509CertificateChain(java.security.cert.Certificate[] certificateArr) {
        if (certificateArr == null) {
            return null;
        }
        if (certificateArr instanceof X509Certificate[]) {
            if (!containsNull(certificateArr)) {
                return (X509Certificate[]) certificateArr;
            }
            return null;
        }
        X509Certificate[] x509CertificateArr = new X509Certificate[certificateArr.length];
        for (int i = 0; i < certificateArr.length; i++) {
            java.security.cert.Certificate certificate = certificateArr[i];
            if (!(certificate instanceof X509Certificate)) {
                return null;
            }
            x509CertificateArr[i] = (X509Certificate) certificate;
        }
        return x509CertificateArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isNameSpecified(String str) {
        return str != null && str.length() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTLSv12(String str) {
        ProtocolVersion protocolVersion = ProvSSLContextSpi.getProtocolVersion(str);
        return protocolVersion != null && TlsUtils.isTLSv12(protocolVersion);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isUsableKeyForServer(short s, PrivateKey privateKey) {
        String str;
        String privateKeyAlgorithm = getPrivateKeyAlgorithm(privateKey);
        switch (s) {
            case 1:
                return KeyUtils.ALGORITHM_RSA.equalsIgnoreCase(privateKeyAlgorithm);
            case 2:
                return (privateKey instanceof DSAPrivateKey) || "DSA".equalsIgnoreCase(privateKeyAlgorithm);
            case 3:
                return (privateKey instanceof ECPrivateKey) || KeyUtils.ALGORITHM_EC.equalsIgnoreCase(privateKeyAlgorithm);
            case 4:
            case 5:
            case 6:
                return KeyUtils.ALGORITHM_RSA.equalsIgnoreCase(privateKeyAlgorithm);
            case 7:
                str = EdDSAParameterSpec.Ed25519;
                break;
            case 8:
                str = EdDSAParameterSpec.Ed448;
                break;
            case 9:
            case 10:
            case 11:
                str = "RSASSA-PSS";
                break;
            default:
                return false;
        }
        return str.equalsIgnoreCase(privateKeyAlgorithm);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isUsableKeyForServerLegacy(int i, PrivateKey privateKey) {
        if (i != 1) {
            if (i != 3 && i != 5 && i != 17 && i != 19) {
                return false;
            }
            return isUsableKeyForServer(TlsUtils.getLegacySignatureAlgorithmServer(i), privateKey);
        }
        return KeyUtils.ALGORITHM_RSA.equalsIgnoreCase(getPrivateKeyAlgorithm(privateKey));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] resize(String[] strArr, int i) {
        return i < strArr.length ? copyOf(strArr, i) : strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String stripDoubleQuotes(String str) {
        return stripOuterChars(str, '\"', '\"');
    }

    private static String stripOuterChars(String str, char c, char c2) {
        int length;
        return (str == null || (length = str.length() - 1) <= 0 || str.charAt(0) != c || str.charAt(length) != c2) ? str : str.substring(1, length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String stripSquareBrackets(String str) {
        return stripOuterChars(str, JsonReaderKt.BEGIN_LIST, JsonReaderKt.END_LIST);
    }

    static X500Principal toX500Principal(X500Name x500Name) throws IOException {
        if (x500Name == null) {
            return null;
        }
        return new X500Principal(x500Name.getEncoded("DER"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Principal[] toX500Principals(Vector<X500Name> vector) throws IOException {
        if (vector == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = vector.size();
        for (int i = 0; i < size; i++) {
            X500Principal x500Principal = toX500Principal(vector.get(i));
            if (x500Principal != null) {
                linkedHashSet.add(x500Principal);
            }
        }
        return (X500Principal[]) linkedHashSet.toArray(new X500Principal[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean useExtendedMasterSecret() {
        return provTlsUseExtendedMasterSecret;
    }
}
