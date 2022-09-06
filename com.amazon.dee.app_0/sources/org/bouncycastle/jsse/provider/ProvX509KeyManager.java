package org.bouncycastle.jsse.provider;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.SoftReference;
import java.net.Socket;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
/* loaded from: classes4.dex */
class ProvX509KeyManager extends BCX509ExtendedKeyManager {
    private final List<KeyStore.Builder> builders;
    private final JcaJceHelper helper;
    private static final Logger LOG = Logger.getLogger(ProvX509KeyManager.class.getName());
    private static final boolean provKeyManagerCheckEKU = PropertyUtils.getBooleanSystemProperty("org.bouncycastle.jsse.keyManager.checkEKU", true);
    private static final Map<String, PublicKeyFilter> FILTERS_CLIENT = createFiltersClient();
    private static final Map<String, PublicKeyFilter> FILTERS_SERVER = createFiltersServer();
    private final AtomicLong versions = new AtomicLong();
    private final Map<String, SoftReference<KeyStore.PrivateKeyEntry>> cachedEntries = Collections.synchronizedMap(new LinkedHashMap<String, SoftReference<KeyStore.PrivateKeyEntry>>(16, 0.75f, true) { // from class: org.bouncycastle.jsse.provider.ProvX509KeyManager.1
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, SoftReference<KeyStore.PrivateKeyEntry>> entry) {
            return size() > 16;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class Match implements Comparable<Match> {
        static final Match NOTHING = new Match(Quality.NONE, -1, null, null, null);
        final int builderIndex;
        final X509Certificate[] cachedCertificateChain;
        final KeyStore cachedKeyStore;
        final String localAlias;
        final Quality quality;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public enum Quality {
            OK,
            RSA_MULTI_USE,
            MISMATCH_SNI,
            EXPIRED,
            NONE
        }

        Match(Quality quality, int i, String str, KeyStore keyStore, X509Certificate[] x509CertificateArr) {
            this.quality = quality;
            this.builderIndex = i;
            this.localAlias = str;
            this.cachedKeyStore = keyStore;
            this.cachedCertificateChain = x509CertificateArr;
        }

        @Override // java.lang.Comparable
        public int compareTo(Match match) {
            return this.quality.compareTo(match.quality);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class PublicKeyFilter {
        final String algorithm;
        final Class<? extends PublicKey> clazz;
        final int keyUsageBit;

        PublicKeyFilter(String str, Class<? extends PublicKey> cls, int i) {
            this.algorithm = str;
            this.clazz = cls;
            this.keyUsageBit = i;
        }

        private boolean appliesTo(PublicKey publicKey) {
            Class<? extends PublicKey> cls;
            String str = this.algorithm;
            return (str != null && str.equalsIgnoreCase(JsseUtils.getPublicKeyAlgorithm(publicKey))) || ((cls = this.clazz) != null && cls.isInstance(publicKey));
        }

        boolean accepts(PublicKey publicKey, boolean[] zArr, BCAlgorithmConstraints bCAlgorithmConstraints) {
            return appliesTo(publicKey) && ProvAlgorithmChecker.permitsKeyUsage(publicKey, zArr, this.keyUsageBit, bCAlgorithmConstraints);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvX509KeyManager(JcaJceHelper jcaJceHelper, List<KeyStore.Builder> list) {
        this.helper = jcaJceHelper;
        this.builders = list;
    }

    private static void addFilter(Map<String, PublicKeyFilter> map, int i, String str, Class<? extends PublicKey> cls, String... strArr) {
        PublicKeyFilter publicKeyFilter = new PublicKeyFilter(str, cls, i);
        for (String str2 : strArr) {
            if (map.put(str2.toUpperCase(Locale.ENGLISH), publicKeyFilter) != null) {
                throw new IllegalStateException("Duplicate keys in filters");
            }
        }
    }

    private static void addFilter(Map<String, PublicKeyFilter> map, Class<? extends PublicKey> cls, String... strArr) {
        addFilter(map, 0, null, cls, strArr);
    }

    private static void addFilter(Map<String, PublicKeyFilter> map, String str) {
        addFilter(map, 0, str, null, str);
    }

    private static void addFilterLegacyServer(Map<String, PublicKeyFilter> map, int i, String str, Class<? extends PublicKey> cls, int... iArr) {
        addFilter(map, i, str, cls, getKeyTypesLegacyServer(iArr));
    }

    private static void addFilterLegacyServer(Map<String, PublicKeyFilter> map, int i, String str, int... iArr) {
        addFilterLegacyServer(map, i, str, null, iArr);
    }

    private static void addFilterLegacyServer(Map<String, PublicKeyFilter> map, Class<? extends PublicKey> cls, int... iArr) {
        addFilterLegacyServer(map, 0, null, cls, iArr);
    }

    private static void addFilterLegacyServer(Map<String, PublicKeyFilter> map, String str, int... iArr) {
        addFilterLegacyServer(map, 0, str, iArr);
    }

    private static List<Match> addToAllMatches(List<Match> list, List<Match> list2) {
        if (list2 == null || list2.isEmpty()) {
            return list;
        }
        if (list == null) {
            return list2;
        }
        list.addAll(list2);
        return list;
    }

    private static List<Match> addToMatches(List<Match> list, Match match) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(match);
        return list;
    }

    private String chooseAlias(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        Match bestMatch = getBestMatch(list, principalArr, transportData, z);
        if (Match.NOTHING == bestMatch) {
            LOG.fine("No matching key found");
            return null;
        }
        String alias = getAlias(bestMatch, getNextVersionSuffix());
        Logger logger = LOG;
        logger.fine("Found matching key, returning alias: " + alias);
        return alias;
    }

    private BCX509Key chooseKeyBC(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        Match bestMatch = getBestMatch(list, principalArr, transportData, z);
        if (Match.NOTHING != bestMatch) {
            try {
                BCX509Key createKeyBC = createKeyBC(bestMatch.builderIndex, bestMatch.localAlias, bestMatch.cachedKeyStore, bestMatch.cachedCertificateChain);
                if (createKeyBC != null) {
                    Logger logger = LOG;
                    logger.fine("Found matching key, from alias: " + bestMatch.builderIndex + "." + bestMatch.localAlias);
                    return createKeyBC;
                }
            } catch (Exception unused) {
            }
        }
        LOG.fine("No matching key found");
        return null;
    }

    private static Map<String, PublicKeyFilter> createFiltersClient() {
        HashMap hashMap = new HashMap();
        addFilter(hashMap, EdDSAParameterSpec.Ed25519);
        addFilter(hashMap, EdDSAParameterSpec.Ed448);
        addFilter(hashMap, KeyUtils.ALGORITHM_RSA);
        addFilter(hashMap, "RSASSA-PSS");
        addFilter(hashMap, DSAPublicKey.class, "DSA");
        addFilter(hashMap, ECPublicKey.class, KeyUtils.ALGORITHM_EC);
        return Collections.unmodifiableMap(hashMap);
    }

    private static Map<String, PublicKeyFilter> createFiltersServer() {
        HashMap hashMap = new HashMap();
        addFilter(hashMap, EdDSAParameterSpec.Ed25519);
        addFilter(hashMap, EdDSAParameterSpec.Ed448);
        addFilter(hashMap, KeyUtils.ALGORITHM_RSA);
        addFilter(hashMap, "RSASSA-PSS");
        addFilterLegacyServer(hashMap, DSAPublicKey.class, 3, 22);
        addFilterLegacyServer(hashMap, ECPublicKey.class, 17);
        addFilterLegacyServer(hashMap, KeyUtils.ALGORITHM_RSA, 5, 19, 23);
        addFilterLegacyServer(hashMap, 2, KeyUtils.ALGORITHM_RSA, 1);
        return Collections.unmodifiableMap(hashMap);
    }

    private BCX509Key createKeyBC(int i, String str, KeyStore keyStore, X509Certificate[] x509CertificateArr) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore.ProtectionParameter protectionParameter = this.builders.get(i).getProtectionParameter(str);
        if (protectionParameter instanceof KeyStore.PasswordProtection) {
            KeyStore.PasswordProtection passwordProtection = (KeyStore.PasswordProtection) protectionParameter;
            if (passwordProtection.getProtectionAlgorithm() != null) {
                return null;
            }
            Key key = keyStore.getKey(str, passwordProtection.getPassword());
            if (!(key instanceof PrivateKey)) {
                return null;
            }
            return new ProvX509Key((PrivateKey) key, x509CertificateArr);
        }
        return null;
    }

    private static String getAlias(Match match, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(match.builderIndex);
        sb.append(".");
        return GeneratedOutlineSupport1.outline91(sb, match.localAlias, str);
    }

    private static String[] getAliases(List<Match> list, String str) {
        String[] strArr = new String[list.size()];
        int i = 0;
        for (Match match : list) {
            strArr[i] = getAlias(match, str);
            i++;
        }
        return strArr;
    }

    private String[] getAliases(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        if (this.builders.isEmpty() || list.isEmpty()) {
            return null;
        }
        Set<Principal> uniquePrincipals = getUniquePrincipals(principalArr);
        BCAlgorithmConstraints algorithmConstraints = TransportData.getAlgorithmConstraints(transportData, true);
        Date date = new Date();
        String requestedHostName = getRequestedHostName(transportData, z);
        int size = this.builders.size();
        List<Match> list2 = null;
        for (int i = 0; i < size; i++) {
            List<Match> list3 = list2;
            try {
                list2 = addToAllMatches(list3, getAliasesFromBuilder(i, list, uniquePrincipals, algorithmConstraints, z, date, requestedHostName));
            } catch (Exception unused) {
                list2 = list3;
            }
        }
        List<Match> list4 = list2;
        if (list4 == null || list4.isEmpty()) {
            return null;
        }
        Collections.sort(list4);
        return getAliases(list4, getNextVersionSuffix());
    }

    private List<Match> getAliasesFromBuilder(int i, List<String> list, Set<Principal> set, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, Date date, String str) throws Exception {
        KeyStore.Builder builder = this.builders.get(i);
        KeyStore keyStore = builder.getKeyStore();
        Enumeration<String> aliases = keyStore.aliases();
        List<Match> list2 = null;
        while (aliases.hasMoreElements()) {
            List<Match> list3 = list2;
            Match potentialMatch = getPotentialMatch(i, builder, keyStore, aliases.nextElement(), Match.Quality.NONE, list, set, bCAlgorithmConstraints, z, date, str);
            list2 = potentialMatch != null ? addToMatches(list3, potentialMatch) : list3;
        }
        return list2;
    }

    private Match getBestMatch(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        Match match = Match.NOTHING;
        if (this.builders.isEmpty() || list.isEmpty()) {
            return match;
        }
        Set<Principal> uniquePrincipals = getUniquePrincipals(principalArr);
        BCAlgorithmConstraints algorithmConstraints = TransportData.getAlgorithmConstraints(transportData, true);
        Date date = new Date();
        String requestedHostName = getRequestedHostName(transportData, z);
        int size = this.builders.size();
        Match match2 = match;
        for (int i = 0; i < size; i++) {
            try {
                Match bestMatchFromBuilder = getBestMatchFromBuilder(i, list, uniquePrincipals, algorithmConstraints, z, date, requestedHostName);
                if (bestMatchFromBuilder.compareTo(match2) < 0) {
                    try {
                        if (Match.Quality.OK == bestMatchFromBuilder.quality) {
                            return bestMatchFromBuilder;
                        }
                    } catch (Exception unused) {
                    }
                    match2 = bestMatchFromBuilder;
                } else {
                    continue;
                }
            } catch (Exception unused2) {
            }
        }
        return match2;
    }

    private Match getBestMatchFromBuilder(int i, List<String> list, Set<Principal> set, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, Date date, String str) throws Exception {
        KeyStore.Builder builder = this.builders.get(i);
        KeyStore keyStore = builder.getKeyStore();
        Match match = Match.NOTHING;
        Enumeration<String> aliases = keyStore.aliases();
        Match match2 = match;
        while (aliases.hasMoreElements()) {
            Match match3 = match2;
            match2 = getPotentialMatch(i, builder, keyStore, aliases.nextElement(), match2.quality, list, set, bCAlgorithmConstraints, z, date, str);
            if (match2 == null) {
                match2 = match3;
            } else if (Match.Quality.OK == match2.quality) {
                break;
            }
        }
        return match2;
    }

    private static Match.Quality getCertificateQuality(X509Certificate x509Certificate, Date date, String str) {
        try {
            x509Certificate.checkValidity(date);
            if (str != null) {
                try {
                    ProvX509TrustManager.checkEndpointID(str, x509Certificate, "HTTPS");
                } catch (CertificateException unused) {
                    return Match.Quality.MISMATCH_SNI;
                }
            }
            if (KeyUtils.ALGORITHM_RSA.equalsIgnoreCase(JsseUtils.getPublicKeyAlgorithm(x509Certificate.getPublicKey()))) {
                boolean[] keyUsage = x509Certificate.getKeyUsage();
                if (ProvAlgorithmChecker.supportsKeyUsage(keyUsage, 0) && ProvAlgorithmChecker.supportsKeyUsage(keyUsage, 2)) {
                    return Match.Quality.RSA_MULTI_USE;
                }
            }
            return Match.Quality.OK;
        } catch (CertificateException unused2) {
            return Match.Quality.EXPIRED;
        }
    }

    private static List<String> getKeyTypes(String... strArr) {
        if (strArr != null && strArr.length > 0) {
            ArrayList arrayList = new ArrayList(strArr.length);
            for (String str : strArr) {
                if (str != null) {
                    arrayList.add(str.toUpperCase(Locale.ENGLISH));
                }
            }
            if (!arrayList.isEmpty()) {
                return Collections.unmodifiableList(arrayList);
            }
        }
        return Collections.emptyList();
    }

    private static String[] getKeyTypesLegacyServer(int... iArr) {
        int length = iArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = JsseUtils.getKeyTypeLegacyServer(iArr[i]);
        }
        return strArr;
    }

    private String getNextVersionSuffix() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(".");
        outline107.append(this.versions.incrementAndGet());
        return outline107.toString();
    }

    private Match getPotentialMatch(int i, KeyStore.Builder builder, KeyStore keyStore, String str, Match.Quality quality, List<String> list, Set<Principal> set, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, Date date, String str2) throws Exception {
        if (keyStore.isKeyEntry(str)) {
            X509Certificate[] x509CertificateChain = JsseUtils.getX509CertificateChain(keyStore.getCertificateChain(str));
            if (!isSuitableChain(x509CertificateChain, list, set, bCAlgorithmConstraints, z)) {
                return null;
            }
            Match.Quality certificateQuality = getCertificateQuality(x509CertificateChain[0], date, str2);
            if (certificateQuality.compareTo(quality) >= 0) {
                return null;
            }
            return new Match(certificateQuality, i, str, keyStore, x509CertificateChain);
        }
        return null;
    }

    private KeyStore.PrivateKeyEntry getPrivateKeyEntry(String str) {
        KeyStore.PrivateKeyEntry privateKeyEntry;
        if (str == null) {
            return null;
        }
        SoftReference<KeyStore.PrivateKeyEntry> softReference = this.cachedEntries.get(str);
        if (softReference != null && (privateKeyEntry = softReference.get()) != null) {
            return privateKeyEntry;
        }
        KeyStore.PrivateKeyEntry loadPrivateKeyEntry = loadPrivateKeyEntry(str);
        if (loadPrivateKeyEntry != null) {
            this.cachedEntries.put(str, new SoftReference<>(loadPrivateKeyEntry));
        }
        return loadPrivateKeyEntry;
    }

    private static String getRequestedHostName(TransportData transportData, boolean z) {
        BCExtendedSSLSession handshakeSession;
        BCSNIHostName sNIHostName;
        if (transportData == null || !z || (handshakeSession = transportData.getHandshakeSession()) == null || (sNIHostName = JsseUtils.getSNIHostName(handshakeSession.getRequestedServerNames())) == null) {
            return null;
        }
        return sNIHostName.getAsciiName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyPurposeId getRequiredExtendedKeyUsage(boolean z) {
        if (!provKeyManagerCheckEKU) {
            return null;
        }
        return z ? KeyPurposeId.id_kp_serverAuth : KeyPurposeId.id_kp_clientAuth;
    }

    private static Set<Principal> getUniquePrincipals(Principal[] principalArr) {
        if (principalArr == null) {
            return null;
        }
        if (principalArr.length > 0) {
            HashSet hashSet = new HashSet();
            for (Principal principal : principalArr) {
                if (principal != null) {
                    hashSet.add(principal);
                }
            }
            if (!hashSet.isEmpty()) {
                return Collections.unmodifiableSet(hashSet);
            }
        }
        return Collections.emptySet();
    }

    private boolean isSuitableChain(X509Certificate[] x509CertificateArr, List<String> list, Set<Principal> set, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z) {
        if (x509CertificateArr != null && x509CertificateArr.length >= 1 && isSuitableChainForIssuers(x509CertificateArr, set) && isSuitableEECert(x509CertificateArr[0], list, bCAlgorithmConstraints, z)) {
            try {
                ProvAlgorithmChecker.checkChain(this.helper, bCAlgorithmConstraints, Collections.emptySet(), x509CertificateArr, getRequiredExtendedKeyUsage(z), -1);
                return true;
            } catch (CertPathValidatorException unused) {
            }
        }
        return false;
    }

    private static boolean isSuitableChainForIssuers(X509Certificate[] x509CertificateArr, Set<Principal> set) {
        if (set == null || set.isEmpty()) {
            return true;
        }
        int length = x509CertificateArr.length;
        do {
            length--;
            if (length < 0) {
                X509Certificate x509Certificate = x509CertificateArr[0];
                return x509Certificate.getBasicConstraints() >= 0 && set.contains(x509Certificate.getSubjectX500Principal());
            }
        } while (!set.contains(x509CertificateArr[length].getIssuerX500Principal()));
        return true;
    }

    private static boolean isSuitableEECert(X509Certificate x509Certificate, List<String> list, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z) {
        Map<String, PublicKeyFilter> map = z ? FILTERS_SERVER : FILTERS_CLIENT;
        PublicKey publicKey = x509Certificate.getPublicKey();
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        for (String str : list) {
            PublicKeyFilter publicKeyFilter = map.get(str);
            if (publicKeyFilter != null && publicKeyFilter.accepts(publicKey, keyUsage, bCAlgorithmConstraints)) {
                return true;
            }
        }
        return false;
    }

    private KeyStore.PrivateKeyEntry loadPrivateKeyEntry(String str) {
        int i;
        int indexOf;
        int parseInt;
        try {
            int indexOf2 = str.indexOf(46, 0);
            if (indexOf2 <= 0 || (indexOf = str.indexOf(46, (i = indexOf2 + 1))) <= i || (parseInt = Integer.parseInt(str.substring(0, indexOf2))) < 0 || parseInt >= this.builders.size()) {
                return null;
            }
            KeyStore.Builder builder = this.builders.get(parseInt);
            String substring = str.substring(i, indexOf);
            KeyStore.Entry entry = builder.getKeyStore().getEntry(substring, builder.getProtectionParameter(substring));
            if (!(entry instanceof KeyStore.PrivateKeyEntry)) {
                return null;
            }
            return (KeyStore.PrivateKeyEntry) entry;
        } catch (Exception e) {
            Logger logger = LOG;
            Level level = Level.FINER;
            logger.log(level, "Failed to load PrivateKeyEntry: " + str, (Throwable) e);
            return null;
        }
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        return chooseAlias(getKeyTypes(strArr), principalArr, TransportData.from(socket), false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key chooseClientKeyBC(String[] strArr, Principal[] principalArr, Socket socket) {
        return chooseKeyBC(getKeyTypes(strArr), principalArr, TransportData.from(socket), false);
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineClientAlias(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return chooseAlias(getKeyTypes(strArr), principalArr, TransportData.from(sSLEngine), false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key chooseEngineClientKeyBC(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return chooseKeyBC(getKeyTypes(strArr), principalArr, TransportData.from(sSLEngine), false);
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineServerAlias(String str, Principal[] principalArr, SSLEngine sSLEngine) {
        return chooseAlias(getKeyTypes(str), principalArr, TransportData.from(sSLEngine), true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key chooseEngineServerKeyBC(String str, Principal[] principalArr, SSLEngine sSLEngine) {
        return chooseKeyBC(getKeyTypes(str), principalArr, TransportData.from(sSLEngine), true);
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        return chooseAlias(getKeyTypes(str), principalArr, TransportData.from(socket), true);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key chooseServerKeyBC(String str, Principal[] principalArr, Socket socket) {
        return chooseKeyBC(getKeyTypes(str), principalArr, TransportData.from(socket), true);
    }

    @Override // javax.net.ssl.X509KeyManager
    public X509Certificate[] getCertificateChain(String str) {
        KeyStore.PrivateKeyEntry privateKeyEntry = getPrivateKeyEntry(str);
        if (privateKeyEntry == null) {
            return null;
        }
        return (X509Certificate[]) privateKeyEntry.getCertificateChain();
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getClientAliases(String str, Principal[] principalArr) {
        return getAliases(getKeyTypes(str), principalArr, null, false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key getKeyBC(String str) {
        PrivateKey privateKey;
        X509Certificate[] x509CertificateChain;
        KeyStore.PrivateKeyEntry privateKeyEntry = getPrivateKeyEntry(str);
        if (privateKeyEntry == null || (privateKey = privateKeyEntry.getPrivateKey()) == null || (x509CertificateChain = JsseUtils.getX509CertificateChain(privateKeyEntry.getCertificateChain())) == null || x509CertificateChain.length < 1) {
            return null;
        }
        return new ProvX509Key(privateKey, x509CertificateChain);
    }

    @Override // javax.net.ssl.X509KeyManager
    public PrivateKey getPrivateKey(String str) {
        KeyStore.PrivateKeyEntry privateKeyEntry = getPrivateKeyEntry(str);
        if (privateKeyEntry == null) {
            return null;
        }
        return privateKeyEntry.getPrivateKey();
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getServerAliases(String str, Principal[] principalArr) {
        return getAliases(getKeyTypes(str), principalArr, null, true);
    }
}
