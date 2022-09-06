package org.bouncycastle.jsse.provider;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.AlgorithmParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Logger;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
import org.bouncycastle.jsse.provider.NamedGroupInfo;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class SignatureSchemeInfo {
    private static final String PROPERTY_CLIENT_SIGNATURE_SCHEMES = "jdk.tls.client.SignatureSchemes";
    private static final String PROPERTY_SERVER_SIGNATURE_SCHEMES = "jdk.tls.server.SignatureSchemes";
    static final int historical_dsa_sha1 = 514;
    static final int historical_dsa_sha224 = 770;
    static final int historical_dsa_sha256 = 1026;
    static final int historical_ecdsa_sha224 = 771;
    static final int historical_rsa_md5 = 257;
    static final int historical_rsa_sha224 = 769;
    private final AlgorithmParameters algorithmParameters;
    private final All all;
    private final boolean disabled13;
    private final boolean enabled;
    private final NamedGroupInfo namedGroupInfo;
    private static final Logger LOG = Logger.getLogger(SignatureSchemeInfo.class.getName());
    private static final int[] CANDIDATES_DEFAULT = createCandidatesDefault();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum All {
        ed25519(SignatureScheme.ed25519, EdDSAParameterSpec.Ed25519, EdDSAParameterSpec.Ed25519),
        ed448(SignatureScheme.ed448, EdDSAParameterSpec.Ed448, EdDSAParameterSpec.Ed448),
        ecdsa_secp256r1_sha256(1027, "SHA256withECDSA", KeyUtils.ALGORITHM_EC),
        ecdsa_secp384r1_sha384(1283, "SHA384withECDSA", KeyUtils.ALGORITHM_EC),
        ecdsa_secp521r1_sha512(1539, "SHA512withECDSA", KeyUtils.ALGORITHM_EC),
        rsa_pss_rsae_sha256(2052, "SHA256withRSAandMGF1", KeyUtils.ALGORITHM_RSA),
        rsa_pss_rsae_sha384(2053, "SHA384withRSAandMGF1", KeyUtils.ALGORITHM_RSA),
        rsa_pss_rsae_sha512(SignatureScheme.rsa_pss_rsae_sha512, "SHA512withRSAandMGF1", KeyUtils.ALGORITHM_RSA),
        rsa_pss_pss_sha256(SignatureScheme.rsa_pss_pss_sha256, "SHA256withRSAandMGF1", "RSASSA-PSS"),
        rsa_pss_pss_sha384(SignatureScheme.rsa_pss_pss_sha384, "SHA384withRSAandMGF1", "RSASSA-PSS"),
        rsa_pss_pss_sha512(SignatureScheme.rsa_pss_pss_sha512, "SHA512withRSAandMGF1", "RSASSA-PSS"),
        rsa_pkcs1_sha256(1025, "SHA256withRSA", KeyUtils.ALGORITHM_RSA, true),
        rsa_pkcs1_sha384(1281, "SHA384withRSA", KeyUtils.ALGORITHM_RSA, true),
        rsa_pkcs1_sha512(1537, "SHA512withRSA", KeyUtils.ALGORITHM_RSA, true),
        dsa_sha256(1026, "dsa_sha256", "SHA256withDSA", "DSA"),
        ecdsa_sha224(771, "ecdsa_sha224", "SHA224withECDSA", KeyUtils.ALGORITHM_EC),
        rsa_sha224(769, "rsa_sha224", "SHA224withRSA", KeyUtils.ALGORITHM_RSA),
        dsa_sha224(770, "dsa_sha224", "SHA224withDSA", "DSA"),
        ecdsa_sha1(515, "SHA1withECDSA", KeyUtils.ALGORITHM_EC, true),
        rsa_pkcs1_sha1(513, "SHA1withRSA", KeyUtils.ALGORITHM_RSA, true),
        dsa_sha1(514, "dsa_sha1", "SHA1withDSA", "DSA"),
        rsa_md5(257, "rsa_md5", "MD5withRSA", KeyUtils.ALGORITHM_RSA);
        
        private final String jcaSignatureAlgorithm;
        private final String jcaSignatureAlgorithmBC;
        private final String keyAlgorithm;
        private final String name;
        private final int namedGroup13;
        private final int signatureScheme;
        private final boolean supported13;
        private final boolean supportedCerts13;
        private final String text;

        All(int i, String str, String str2) {
            this(i, str, str2, true, true, SignatureScheme.getNamedGroup(i));
        }

        All(int i, String str, String str2, String str3) {
            this(i, str, str2, str3, false, false, -1);
        }

        All(int i, String str, String str2, String str3, boolean z, boolean z2, int i2) {
            this.signatureScheme = i;
            this.name = str;
            this.text = GeneratedOutlineSupport1.outline33(i, GeneratedOutlineSupport1.outline113(str, "(0x"), ")");
            this.jcaSignatureAlgorithm = str2;
            this.jcaSignatureAlgorithmBC = JsseUtils.getJcaSignatureAlgorithmBC(str2, str3);
            this.keyAlgorithm = str3;
            this.supported13 = z;
            this.supportedCerts13 = z2;
            this.namedGroup13 = i2;
        }

        All(int i, String str, String str2, boolean z) {
            this(i, str, str2, false, z, -1);
        }

        All(int i, String str, String str2, boolean z, boolean z2, int i2) {
            this(i, SignatureScheme.getName(i), str, str2, z, z2, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class PerContext {
        private final int[] candidatesClient;
        private final int[] candidatesServer;
        private final Map<Integer, SignatureSchemeInfo> index;

        PerContext(Map<Integer, SignatureSchemeInfo> map, int[] iArr, int[] iArr2) {
            this.index = map;
            this.candidatesClient = iArr;
            this.candidatesServer = iArr2;
        }
    }

    SignatureSchemeInfo(All all, AlgorithmParameters algorithmParameters, NamedGroupInfo namedGroupInfo, boolean z, boolean z2) {
        this.all = all;
        this.algorithmParameters = algorithmParameters;
        this.namedGroupInfo = namedGroupInfo;
        this.enabled = z;
        this.disabled13 = z2;
    }

    private static void addSignatureScheme(boolean z, JcaTlsCrypto jcaTlsCrypto, NamedGroupInfo.PerContext perContext, Map<Integer, SignatureSchemeInfo> map, All all) {
        boolean z2;
        NamedGroupInfo namedGroupInfo;
        boolean z3;
        int i = all.signatureScheme;
        if (!z || FipsUtils.isFipsSignatureScheme(i)) {
            int i2 = all.namedGroup13;
            AlgorithmParameters algorithmParameters = null;
            if (i2 >= 0) {
                NamedGroupInfo namedGroup = NamedGroupInfo.getNamedGroup(perContext, i2);
                if (namedGroup == null || !namedGroup.isEnabled()) {
                    namedGroupInfo = namedGroup;
                    z2 = true;
                } else {
                    namedGroupInfo = namedGroup;
                    z2 = false;
                }
            } else {
                z2 = false;
                namedGroupInfo = null;
            }
            boolean hasSignatureScheme = jcaTlsCrypto.hasSignatureScheme(i);
            if (hasSignatureScheme) {
                try {
                    algorithmParameters = jcaTlsCrypto.getSignatureSchemeAlgorithmParameters(i);
                } catch (Exception unused) {
                    z3 = false;
                }
            }
            z3 = hasSignatureScheme;
            if (map.put(Integer.valueOf(i), new SignatureSchemeInfo(all, algorithmParameters, namedGroupInfo, z3, z2)) != null) {
                throw new IllegalStateException("Duplicate entries for SignatureSchemeInfo");
            }
        }
    }

    private static int[] createCandidates(Map<Integer, SignatureSchemeInfo> map, String str) {
        Logger logger;
        StringBuilder sb;
        String str2;
        String[] stringArraySystemProperty = PropertyUtils.getStringArraySystemProperty(str);
        if (stringArraySystemProperty == null) {
            return CANDIDATES_DEFAULT;
        }
        int[] iArr = new int[stringArraySystemProperty.length];
        int i = 0;
        for (String str3 : stringArraySystemProperty) {
            int signatureSchemeByName = getSignatureSchemeByName(str3);
            if (signatureSchemeByName < 0) {
                logger = LOG;
                sb = new StringBuilder();
                sb.append("'");
                sb.append(str);
                str2 = "' contains unrecognised SignatureScheme: ";
            } else {
                SignatureSchemeInfo signatureSchemeInfo = map.get(Integer.valueOf(signatureSchemeByName));
                if (signatureSchemeInfo == null) {
                    logger = LOG;
                    sb = new StringBuilder();
                    sb.append("'");
                    sb.append(str);
                    str2 = "' contains unsupported SignatureScheme: ";
                } else if (!signatureSchemeInfo.isEnabled()) {
                    logger = LOG;
                    sb = new StringBuilder();
                    sb.append("'");
                    sb.append(str);
                    str2 = "' contains disabled SignatureScheme: ";
                } else {
                    iArr[i] = signatureSchemeByName;
                    i++;
                }
            }
            sb.append(str2);
            sb.append(str3);
            logger.warning(sb.toString());
        }
        if (i < iArr.length) {
            iArr = Arrays.copyOf(iArr, i);
        }
        if (iArr.length < 1) {
            LOG.severe("'" + str + "' contained no usable SignatureScheme values");
        }
        return iArr;
    }

    private static int[] createCandidatesDefault() {
        All[] values = All.values();
        int[] iArr = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            iArr[i] = values[i].signatureScheme;
        }
        return iArr;
    }

    private static Map<Integer, SignatureSchemeInfo> createIndex(boolean z, JcaTlsCrypto jcaTlsCrypto, NamedGroupInfo.PerContext perContext) {
        TreeMap treeMap = new TreeMap();
        for (All all : All.values()) {
            addSignatureScheme(z, jcaTlsCrypto, perContext, treeMap, all);
        }
        return treeMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PerContext createPerContext(boolean z, JcaTlsCrypto jcaTlsCrypto, NamedGroupInfo.PerContext perContext) {
        Map<Integer, SignatureSchemeInfo> createIndex = createIndex(z, jcaTlsCrypto, perContext);
        return new PerContext(createIndex, createCandidates(createIndex, PROPERTY_CLIENT_SIGNATURE_SCHEMES), createCandidates(createIndex, PROPERTY_SERVER_SIGNATURE_SCHEMES));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<SignatureSchemeInfo> getActiveCertsSignatureSchemes(PerContext perContext, boolean z, ProvSSLParameters provSSLParameters, ProtocolVersion[] protocolVersionArr, NamedGroupInfo.PerConnection perConnection) {
        ProtocolVersion latestTLS = ProtocolVersion.getLatestTLS(protocolVersionArr);
        if (!TlsUtils.isSignatureAlgorithmsExtensionAllowed(latestTLS)) {
            return null;
        }
        int[] iArr = z ? perContext.candidatesServer : perContext.candidatesClient;
        ProtocolVersion earliestTLS = ProtocolVersion.getEarliestTLS(protocolVersionArr);
        BCAlgorithmConstraints algorithmConstraints = provSSLParameters.getAlgorithmConstraints();
        boolean isTLSv13 = TlsUtils.isTLSv13(latestTLS);
        boolean z2 = !TlsUtils.isTLSv13(earliestTLS);
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            SignatureSchemeInfo signatureSchemeInfo = (SignatureSchemeInfo) perContext.index.get(Integers.valueOf(i));
            if (signatureSchemeInfo != null && signatureSchemeInfo.isActiveCerts(algorithmConstraints, z2, isTLSv13, perConnection)) {
                arrayList.add(signatureSchemeInfo);
            }
        }
        if (arrayList.isEmpty()) {
            return Collections.emptyList();
        }
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] getJcaSignatureAlgorithms(Collection<SignatureSchemeInfo> collection) {
        if (collection == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        for (SignatureSchemeInfo signatureSchemeInfo : collection) {
            arrayList.add(signatureSchemeInfo.getJcaSignatureAlgorithm());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] getJcaSignatureAlgorithmsBC(Collection<SignatureSchemeInfo> collection) {
        if (collection == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        for (SignatureSchemeInfo signatureSchemeInfo : collection) {
            arrayList.add(signatureSchemeInfo.getJcaSignatureAlgorithmBC());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(int i) {
        if (TlsUtils.isValidUint16(i)) {
            return SignatureAndHashAlgorithm.getInstance(SignatureScheme.getHashAlgorithm(i), SignatureScheme.getSignatureAlgorithm(i));
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Vector<SignatureAndHashAlgorithm> getSignatureAndHashAlgorithms(List<SignatureSchemeInfo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Vector<SignatureAndHashAlgorithm> vector = new Vector<>(list.size());
        for (SignatureSchemeInfo signatureSchemeInfo : list) {
            if (signatureSchemeInfo != null) {
                vector.add(signatureSchemeInfo.getSignatureAndHashAlgorithm());
            }
        }
        if (vector.isEmpty()) {
            return null;
        }
        vector.trimToSize();
        return vector;
    }

    static int getSignatureScheme(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm != null) {
            short hash = signatureAndHashAlgorithm.getHash();
            return (signatureAndHashAlgorithm.getSignature() & 255) | ((hash & 255) << 8);
        }
        throw new NullPointerException();
    }

    private static int getSignatureSchemeByName(String str) {
        All[] values;
        for (All all : All.values()) {
            if (all.name.equalsIgnoreCase(str)) {
                return all.signatureScheme;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<SignatureSchemeInfo> getSignatureSchemes(PerContext perContext, Vector<SignatureAndHashAlgorithm> vector) {
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        int size = vector.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            SignatureAndHashAlgorithm elementAt = vector.elementAt(i);
            if (elementAt != null) {
                SignatureSchemeInfo signatureSchemeInfo = (SignatureSchemeInfo) perContext.index.get(Integer.valueOf(getSignatureScheme(elementAt)));
                if (signatureSchemeInfo != null) {
                    arrayList.add(signatureSchemeInfo);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    private static boolean isECDSA(int i) {
        return i == 515 || i == 771 || i == 1027 || i == 1283 || i == 1539;
    }

    private boolean isNamedGroupOK(boolean z, boolean z2, NamedGroupInfo.PerConnection perConnection) {
        NamedGroupInfo namedGroupInfo = this.namedGroupInfo;
        if (namedGroupInfo != null) {
            return (z2 && NamedGroupInfo.hasLocal(perConnection, namedGroupInfo.getNamedGroup())) || (z && NamedGroupInfo.hasAnyECDSALocal(perConnection));
        } else if (!z2 && !z) {
            return false;
        } else {
            return !isECDSA(this.all.signatureScheme) || NamedGroupInfo.hasAnyECDSALocal(perConnection);
        }
    }

    private boolean isPermittedBy(BCAlgorithmConstraints bCAlgorithmConstraints) {
        Set<BCCryptoPrimitive> set = JsseUtils.SIGNATURE_CRYPTO_PRIMITIVES_BC;
        return bCAlgorithmConstraints.permits(set, this.all.name, null) && bCAlgorithmConstraints.permits(set, this.all.keyAlgorithm, null) && bCAlgorithmConstraints.permits(set, this.all.jcaSignatureAlgorithm, this.algorithmParameters);
    }

    short getHashAlgorithm() {
        return SignatureScheme.getHashAlgorithm(this.all.signatureScheme);
    }

    String getJcaSignatureAlgorithm() {
        return this.all.jcaSignatureAlgorithm;
    }

    String getJcaSignatureAlgorithmBC() {
        return this.all.jcaSignatureAlgorithmBC;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getKeyAlgorithm() {
        return this.all.keyAlgorithm;
    }

    String getName() {
        return this.all.name;
    }

    NamedGroupInfo getNamedGroupInfo() {
        return this.namedGroupInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public short getSignatureAlgorithm() {
        return SignatureScheme.getSignatureAlgorithm(this.all.signatureScheme);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignatureAndHashAlgorithm getSignatureAndHashAlgorithm() {
        return getSignatureAndHashAlgorithm(this.all.signatureScheme);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSignatureScheme() {
        return this.all.signatureScheme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isActive(BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, boolean z2, NamedGroupInfo.PerConnection perConnection) {
        if (this.enabled) {
            if (isNamedGroupOK(z, z2 && isSupported13(), perConnection) && isPermittedBy(bCAlgorithmConstraints)) {
                return true;
            }
        }
        return false;
    }

    boolean isActiveCerts(BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, boolean z2, NamedGroupInfo.PerConnection perConnection) {
        if (this.enabled) {
            if (isNamedGroupOK(z, z2 && isSupportedCerts13(), perConnection) && isPermittedBy(bCAlgorithmConstraints)) {
                return true;
            }
        }
        return false;
    }

    boolean isEnabled() {
        return this.enabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSupported13() {
        return !this.disabled13 && this.all.supported13;
    }

    boolean isSupportedCerts13() {
        return !this.disabled13 && this.all.supportedCerts13;
    }

    public String toString() {
        return this.all.text;
    }
}
