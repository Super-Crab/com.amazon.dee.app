package org.bouncycastle.jsse.provider;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.Socket;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
/* loaded from: classes4.dex */
class ProvX509KeyManagerSimple extends BCX509ExtendedKeyManager {
    private final Map<String, Credential> credentials;
    private final JcaJceHelper helper;
    private static final Logger LOG = Logger.getLogger(ProvX509KeyManagerSimple.class.getName());
    private static final Map<String, PublicKeyFilter> FILTERS_CLIENT = createFiltersClient();
    private static final Map<String, PublicKeyFilter> FILTERS_SERVER = createFiltersServer();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class Credential {
        private final String alias;
        private final X509Certificate[] certificateChain;
        private final PrivateKey privateKey;

        Credential(String str, PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.alias = str;
            this.privateKey = privateKey;
            this.certificateChain = x509CertificateArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class Match implements Comparable<Match> {
        static final Match NOTHING = new Match(Quality.NONE, null);
        final Credential credential;
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

        Match(Quality quality, Credential credential) {
            this.quality = quality;
            this.credential = credential;
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
    public ProvX509KeyManagerSimple(JcaJceHelper jcaJceHelper, KeyStore keyStore, char[] cArr) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        this.helper = jcaJceHelper;
        this.credentials = loadCredentials(keyStore, cArr);
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
        String alias = getAlias(bestMatch);
        Logger logger = LOG;
        logger.fine("Found matching key, returning alias: " + alias);
        return alias;
    }

    private BCX509Key chooseKeyBC(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        BCX509Key createKeyBC;
        Match bestMatch = getBestMatch(list, principalArr, transportData, z);
        if (Match.NOTHING == bestMatch || (createKeyBC = createKeyBC(bestMatch.credential)) == null) {
            LOG.fine("No matching key found");
            return null;
        }
        Logger logger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Found matching key, from alias: ");
        outline107.append(getAlias(bestMatch));
        logger.fine(outline107.toString());
        return createKeyBC;
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

    private BCX509Key createKeyBC(Credential credential) {
        if (credential == null) {
            return null;
        }
        return new ProvX509Key(credential.privateKey, credential.certificateChain);
    }

    private static String getAlias(Match match) {
        return match.credential.alias;
    }

    private static String[] getAliases(List<Match> list) {
        String[] strArr = new String[list.size()];
        int i = 0;
        for (Match match : list) {
            strArr[i] = getAlias(match);
            i++;
        }
        return strArr;
    }

    private String[] getAliases(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        List<Match> list2;
        if (!this.credentials.isEmpty() && !list.isEmpty()) {
            try {
                list2 = getAliasesFromCredentials(list, getUniquePrincipals(principalArr), TransportData.getAlgorithmConstraints(transportData, true), z, new Date(), getRequestedHostName(transportData, z));
            } catch (Exception unused) {
                list2 = null;
            }
            if (list2 != null && !list2.isEmpty()) {
                Collections.sort(list2);
                return getAliases(list2);
            }
        }
        return null;
    }

    private List<Match> getAliasesFromCredentials(List<String> list, Set<Principal> set, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, Date date, String str) throws Exception {
        Match potentialMatch;
        List<Match> list2 = null;
        while (true) {
            List<Match> list3 = list2;
            for (Credential credential : this.credentials.values()) {
                potentialMatch = getPotentialMatch(credential, Match.Quality.NONE, list, set, bCAlgorithmConstraints, z, date, str);
                if (potentialMatch != null) {
                    break;
                }
            }
            return list3;
            list2 = addToMatches(list3, potentialMatch);
        }
    }

    private Match getBestMatch(List<String> list, Principal[] principalArr, TransportData transportData, boolean z) {
        if (!this.credentials.isEmpty() && !list.isEmpty()) {
            try {
                return getBestMatchFromCredentials(list, getUniquePrincipals(principalArr), TransportData.getAlgorithmConstraints(transportData, true), z, new Date(), getRequestedHostName(transportData, z));
            } catch (Exception unused) {
            }
        }
        return Match.NOTHING;
    }

    private Match getBestMatchFromCredentials(List<String> list, Set<Principal> set, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, Date date, String str) throws Exception {
        Match match = Match.NOTHING;
        for (Credential credential : this.credentials.values()) {
            Match potentialMatch = getPotentialMatch(credential, match.quality, list, set, bCAlgorithmConstraints, z, date, str);
            if (potentialMatch != null) {
                if (Match.Quality.OK == potentialMatch.quality) {
                    return potentialMatch;
                }
                match = potentialMatch;
            }
        }
        return match;
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

    private Credential getCredential(String str) {
        if (str == null) {
            return null;
        }
        return this.credentials.get(str);
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

    private Match getPotentialMatch(Credential credential, Match.Quality quality, List<String> list, Set<Principal> set, BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, Date date, String str) throws Exception {
        X509Certificate[] x509CertificateArr = credential.certificateChain;
        if (isSuitableChain(x509CertificateArr, list, set, bCAlgorithmConstraints, z)) {
            Match.Quality certificateQuality = getCertificateQuality(x509CertificateArr[0], date, str);
            if (certificateQuality.compareTo(quality) >= 0) {
                return null;
            }
            return new Match(certificateQuality, credential);
        }
        return null;
    }

    private static String getRequestedHostName(TransportData transportData, boolean z) {
        BCExtendedSSLSession handshakeSession;
        BCSNIHostName sNIHostName;
        if (transportData == null || !z || (handshakeSession = transportData.getHandshakeSession()) == null || (sNIHostName = JsseUtils.getSNIHostName(handshakeSession.getRequestedServerNames())) == null) {
            return null;
        }
        return sNIHostName.getAsciiName();
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
                ProvAlgorithmChecker.checkChain(this.helper, bCAlgorithmConstraints, Collections.emptySet(), x509CertificateArr, ProvX509KeyManager.getRequiredExtendedKeyUsage(z), -1);
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

    private static Map<String, Credential> loadCredentials(KeyStore keyStore, char[] cArr) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        PrivateKey privateKey;
        X509Certificate[] x509CertificateChain;
        HashMap hashMap = new HashMap(4);
        if (keyStore != null) {
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String nextElement = aliases.nextElement();
                if (keyStore.entryInstanceOf(nextElement, KeyStore.PrivateKeyEntry.class) && (privateKey = (PrivateKey) keyStore.getKey(nextElement, cArr)) != null && (x509CertificateChain = JsseUtils.getX509CertificateChain(keyStore.getCertificateChain(nextElement))) != null && x509CertificateChain.length >= 1) {
                    hashMap.put(nextElement, new Credential(nextElement, privateKey, x509CertificateChain));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
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
        Credential credential = getCredential(str);
        if (credential == null) {
            return null;
        }
        return (X509Certificate[]) credential.certificateChain.clone();
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getClientAliases(String str, Principal[] principalArr) {
        return getAliases(getKeyTypes(str), principalArr, null, false);
    }

    @Override // org.bouncycastle.jsse.BCX509ExtendedKeyManager
    public BCX509Key getKeyBC(String str) {
        return createKeyBC(getCredential(str));
    }

    @Override // javax.net.ssl.X509KeyManager
    public PrivateKey getPrivateKey(String str) {
        Credential credential = getCredential(str);
        if (credential == null) {
            return null;
        }
        return credential.privateKey;
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getServerAliases(String str, Principal[] principalArr) {
        return getAliases(getKeyTypes(str), principalArr, null, true);
    }
}
