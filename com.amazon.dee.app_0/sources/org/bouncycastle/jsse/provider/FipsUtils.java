package org.bouncycastle.jsse.provider;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.tls.SignatureScheme;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class FipsUtils {
    private static final boolean provAllowGCMCiphers = false;
    private static final boolean provAllowRSAKeyExchange = PropertyUtils.getBooleanSystemProperty("org.bouncycastle.jsse.fips.allowRSAKeyExchange", true);
    private static final Set<String> FIPS_SUPPORTED_CIPHERSUITES = createFipsSupportedCipherSuites();
    private static final Set<String> FIPS_SUPPORTED_PROTOCOLS = createFipsSupportedProtocols();

    FipsUtils() {
    }

    private static Set<String> createFipsSupportedCipherSuites() {
        HashSet hashSet = new HashSet();
        hashSet.add("TLS_AES_128_CCM_8_SHA256");
        hashSet.add("TLS_AES_128_CCM_SHA256");
        hashSet.add("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
        hashSet.add("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
        hashSet.add("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");
        GeneratedOutlineSupport1.outline187(hashSet, "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_128_CCM");
        GeneratedOutlineSupport1.outline187(hashSet, "TLS_DHE_RSA_WITH_AES_128_CCM_8", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_256_CCM");
        GeneratedOutlineSupport1.outline187(hashSet, "TLS_DHE_RSA_WITH_AES_256_CCM_8", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_CCM");
        GeneratedOutlineSupport1.outline187(hashSet, "TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_256_CCM");
        GeneratedOutlineSupport1.outline187(hashSet, "TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
        hashSet.add("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
        if (provAllowRSAKeyExchange) {
            GeneratedOutlineSupport1.outline187(hashSet, "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA256", "TLS_RSA_WITH_AES_128_CCM", "TLS_RSA_WITH_AES_128_CCM_8");
            GeneratedOutlineSupport1.outline187(hashSet, "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA256", "TLS_RSA_WITH_AES_256_CCM", "TLS_RSA_WITH_AES_256_CCM_8");
        }
        return Collections.unmodifiableSet(hashSet);
    }

    private static Set<String> createFipsSupportedProtocols() {
        HashSet hashSet = new HashSet();
        hashSet.add("TLSv1");
        hashSet.add("TLSv1.1");
        hashSet.add("TLSv1.2");
        hashSet.add("TLSv1.3");
        return Collections.unmodifiableSet(hashSet);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFipsCipherSuite(String str) {
        return str != null && FIPS_SUPPORTED_CIPHERSUITES.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFipsNamedGroup(int i) {
        switch (i) {
            case 23:
            case 24:
            case 25:
                return true;
            default:
                switch (i) {
                    case 256:
                    case 257:
                    case 258:
                    case 259:
                    case 260:
                        return true;
                    default:
                        return false;
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFipsProtocol(String str) {
        return str != null && FIPS_SUPPORTED_PROTOCOLS.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFipsSignatureScheme(int i) {
        switch (i) {
            case 513:
            case 514:
            case 515:
            case 769:
            case 770:
            case 771:
            case 1025:
            case 1026:
            case 1027:
            case 1281:
            case 1283:
            case 1537:
            case 1539:
            case 2052:
            case 2053:
            case SignatureScheme.rsa_pss_rsae_sha512 /* 2054 */:
            case SignatureScheme.rsa_pss_pss_sha256 /* 2057 */:
            case SignatureScheme.rsa_pss_pss_sha384 /* 2058 */:
            case SignatureScheme.rsa_pss_pss_sha512 /* 2059 */:
                return true;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeNonFipsCipherSuites(Collection<String> collection) {
        collection.retainAll(FIPS_SUPPORTED_CIPHERSUITES);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeNonFipsProtocols(Collection<String> collection) {
        collection.retainAll(FIPS_SUPPORTED_PROTOCOLS);
    }
}
