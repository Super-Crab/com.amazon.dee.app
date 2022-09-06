package org.bouncycastle.jsse.provider;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCryptoProvider;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ProvSSLContextSpi extends SSLContextSpi {
    private static final String PROPERTY_CLIENT_PROTOCOLS = "jdk.tls.client.protocols";
    private static final String PROPERTY_SERVER_PROTOCOLS = "jdk.tls.server.protocols";
    private ContextData contextData = null;
    protected final JcaTlsCryptoProvider cryptoProvider;
    protected final String[] defaultCipherSuites;
    protected final String[] defaultProtocolsClient;
    protected final String[] defaultProtocolsServer;
    protected final boolean isInFipsMode;
    protected final Map<String, CipherSuiteInfo> supportedCipherSuites;
    protected final Map<String, ProtocolVersion> supportedProtocols;
    private static final Logger LOG = Logger.getLogger(ProvSSLContextSpi.class.getName());
    private static final Set<BCCryptoPrimitive> TLS_CRYPTO_PRIMITIVES_BC = JsseUtils.KEY_AGREEMENT_CRYPTO_PRIMITIVES_BC;
    private static final Map<String, CipherSuiteInfo> SUPPORTED_CIPHERSUITE_MAP = createSupportedCipherSuiteMap();
    private static final Map<String, CipherSuiteInfo> SUPPORTED_CIPHERSUITE_MAP_FIPS = createSupportedCipherSuiteMapFips(SUPPORTED_CIPHERSUITE_MAP);
    private static final Map<String, ProtocolVersion> SUPPORTED_PROTOCOL_MAP = createSupportedProtocolMap();
    private static final Map<String, ProtocolVersion> SUPPORTED_PROTOCOL_MAP_FIPS = createSupportedProtocolMapFips(SUPPORTED_PROTOCOL_MAP);
    private static final List<String> DEFAULT_CIPHERSUITE_LIST = createDefaultCipherSuiteList(SUPPORTED_CIPHERSUITE_MAP.keySet());
    private static final List<String> DEFAULT_CIPHERSUITE_LIST_FIPS = createDefaultCipherSuiteListFips(DEFAULT_CIPHERSUITE_LIST);
    private static final String[] DEFAULT_ENABLED_PROTOCOLS = {"TLSv1.2", "TLSv1.1", "TLSv1"};

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLContextSpi(boolean z, JcaTlsCryptoProvider jcaTlsCryptoProvider, String[] strArr) {
        this.isInFipsMode = z;
        this.cryptoProvider = jcaTlsCryptoProvider;
        this.supportedCipherSuites = z ? SUPPORTED_CIPHERSUITE_MAP_FIPS : SUPPORTED_CIPHERSUITE_MAP;
        this.supportedProtocols = z ? SUPPORTED_PROTOCOL_MAP_FIPS : SUPPORTED_PROTOCOL_MAP;
        this.defaultCipherSuites = getDefaultEnabledCipherSuites(z);
        this.defaultProtocolsClient = getDefaultEnabledProtocolsClient(this.supportedProtocols, strArr);
        this.defaultProtocolsServer = getDefaultEnabledProtocolsServer(this.supportedProtocols);
    }

    private static void addCipherSuite(Map<String, CipherSuiteInfo> map, String str, int i) {
        addCipherSuite(map, str, i, false);
    }

    private static void addCipherSuite(Map<String, CipherSuiteInfo> map, String str, int i, boolean z) {
        if (map.put(str, CipherSuiteInfo.forCipherSuite(i, str, z)) == null) {
            return;
        }
        throw new IllegalStateException("Duplicate names in supported-cipher-suites");
    }

    private static void addCipherSuite13(Map<String, CipherSuiteInfo> map, String str, int i) {
        addCipherSuite(map, str, i, true);
    }

    private static List<String> createDefaultCipherSuiteList(Set<String> set) {
        ArrayList outline129 = GeneratedOutlineSupport1.outline129("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
        outline129.add("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
        outline129.add("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
        outline129.add("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
        outline129.add("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
        outline129.add("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        outline129.add("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
        outline129.add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
        outline129.add("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
        outline129.add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
        outline129.add("TLS_RSA_WITH_AES_256_GCM_SHA384");
        outline129.add("TLS_RSA_WITH_AES_128_GCM_SHA256");
        outline129.add("TLS_RSA_WITH_AES_256_CBC_SHA256");
        outline129.add("TLS_RSA_WITH_AES_128_CBC_SHA256");
        outline129.add("TLS_RSA_WITH_AES_256_CBC_SHA");
        outline129.add("TLS_RSA_WITH_AES_128_CBC_SHA");
        outline129.retainAll(set);
        outline129.trimToSize();
        return Collections.unmodifiableList(outline129);
    }

    private static List<String> createDefaultCipherSuiteListFips(List<String> list) {
        ArrayList arrayList = new ArrayList(list);
        FipsUtils.removeNonFipsCipherSuites(arrayList);
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    private static Map<String, CipherSuiteInfo> createSupportedCipherSuiteMap() {
        TreeMap treeMap = new TreeMap();
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256", 189);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256", 195);
        addCipherSuite(treeMap, "TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_CCM", CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_CCM_8", CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_CCM", CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_CCM_8", CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256", 190);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_CCM", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_CCM", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_ECDSA_WITH_NULL_SHA", CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256);
        addCipherSuite(treeMap, "TLS_ECDHE_RSA_WITH_NULL_SHA", CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA);
        addCipherSuite(treeMap, "TLS_RSA_WITH_3DES_EDE_CBC_SHA", 10);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_CBC_SHA", 47);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_CCM", CipherSuite.TLS_RSA_WITH_AES_128_CCM);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_CCM_8", CipherSuite.TLS_RSA_WITH_AES_128_CCM_8);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_CBC_SHA", 53);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_CCM", CipherSuite.TLS_RSA_WITH_AES_256_CCM);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_CCM_8", CipherSuite.TLS_RSA_WITH_AES_256_CCM_8);
        addCipherSuite(treeMap, "TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
        addCipherSuite(treeMap, "TLS_RSA_WITH_ARIA_128_CBC_SHA256", CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256);
        addCipherSuite(treeMap, "TLS_RSA_WITH_ARIA_128_GCM_SHA256", CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_RSA_WITH_ARIA_256_CBC_SHA384", CipherSuite.TLS_RSA_WITH_ARIA_256_CBC_SHA384);
        addCipherSuite(treeMap, "TLS_RSA_WITH_ARIA_256_GCM_SHA384", CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256", 186);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256", CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_256_CBC_SHA256", 192);
        addCipherSuite(treeMap, "TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384", CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384);
        addCipherSuite(treeMap, "TLS_RSA_WITH_NULL_SHA", 2);
        addCipherSuite(treeMap, "TLS_RSA_WITH_NULL_SHA256", 59);
        return Collections.unmodifiableMap(treeMap);
    }

    private static Map<String, CipherSuiteInfo> createSupportedCipherSuiteMapFips(Map<String, CipherSuiteInfo> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        FipsUtils.removeNonFipsCipherSuites(linkedHashMap.keySet());
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static Map<String, ProtocolVersion> createSupportedProtocolMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("TLSv1.2", ProtocolVersion.TLSv12);
        linkedHashMap.put("TLSv1.1", ProtocolVersion.TLSv11);
        linkedHashMap.put("TLSv1", ProtocolVersion.TLSv10);
        linkedHashMap.put("SSLv3", ProtocolVersion.SSLv3);
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static Map<String, ProtocolVersion> createSupportedProtocolMapFips(Map<String, ProtocolVersion> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        FipsUtils.removeNonFipsProtocols(linkedHashMap.keySet());
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static String[] getArray(Collection<String> collection) {
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CipherSuiteInfo getCipherSuiteInfo(String str) {
        return SUPPORTED_CIPHERSUITE_MAP.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getCipherSuiteName(int i) {
        if (i == 0) {
            return "SSL_NULL_WITH_NULL_NULL";
        }
        if (!TlsUtils.isValidUint16(i)) {
            return null;
        }
        for (CipherSuiteInfo cipherSuiteInfo : SUPPORTED_CIPHERSUITE_MAP.values()) {
            if (cipherSuiteInfo.getCipherSuite() == i) {
                return cipherSuiteInfo.getName();
            }
        }
        return null;
    }

    private static String[] getDefaultEnabledCipherSuites(boolean z) {
        List<String> list = z ? DEFAULT_CIPHERSUITE_LIST_FIPS : DEFAULT_CIPHERSUITE_LIST;
        String[] strArr = new String[list.size()];
        int i = 0;
        for (String str : list) {
            if (ProvAlgorithmConstraints.DEFAULT.permits(TLS_CRYPTO_PRIMITIVES_BC, str, null)) {
                strArr[i] = str;
                i++;
            }
        }
        return JsseUtils.resize(strArr, i);
    }

    private static String[] getDefaultEnabledProtocolCandidates(String[] strArr, String str) {
        if (strArr != null) {
            return strArr;
        }
        String[] jdkTlsProtocols = getJdkTlsProtocols(str);
        return jdkTlsProtocols != null ? jdkTlsProtocols : DEFAULT_ENABLED_PROTOCOLS;
    }

    private static String[] getDefaultEnabledProtocols(Map<String, ProtocolVersion> map, String[] strArr, String str) {
        String[] defaultEnabledProtocolCandidates = getDefaultEnabledProtocolCandidates(strArr, str);
        String[] strArr2 = new String[defaultEnabledProtocolCandidates.length];
        int i = 0;
        for (String str2 : defaultEnabledProtocolCandidates) {
            if (map.containsKey(str2) && ProvAlgorithmConstraints.DEFAULT_TLS_ONLY.permits(TLS_CRYPTO_PRIMITIVES_BC, str2, null)) {
                strArr2[i] = str2;
                i++;
            }
        }
        return JsseUtils.resize(strArr2, i);
    }

    private static String[] getDefaultEnabledProtocolsClient(Map<String, ProtocolVersion> map, String[] strArr) {
        return getDefaultEnabledProtocols(map, strArr, PROPERTY_CLIENT_PROTOCOLS);
    }

    private static String[] getDefaultEnabledProtocolsServer(Map<String, ProtocolVersion> map) {
        return getDefaultEnabledProtocols(map, null, PROPERTY_SERVER_PROTOCOLS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyManager[] getDefaultKeyManagers() throws Exception {
        KeyStoreConfig defaultKeyStore = ProvKeyManagerFactorySpi.getDefaultKeyStore();
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(defaultKeyStore.keyStore, defaultKeyStore.password);
        return keyManagerFactory.getKeyManagers();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrustManager[] getDefaultTrustManagers() throws Exception {
        KeyStore defaultTrustStore = ProvTrustManagerFactorySpi.getDefaultTrustStore();
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(defaultTrustStore);
        return trustManagerFactory.getTrustManagers();
    }

    private static String[] getJdkTlsProtocols(String str) {
        String[] stringArraySystemProperty = PropertyUtils.getStringArraySystemProperty(str);
        if (stringArraySystemProperty == null) {
            return null;
        }
        String[] strArr = new String[stringArraySystemProperty.length];
        int i = 0;
        for (String str2 : stringArraySystemProperty) {
            if (!SUPPORTED_PROTOCOL_MAP.containsKey(str2)) {
                LOG.warning("'" + str + "' contains unsupported protocol: " + str2);
            } else if (!JsseUtils.contains(strArr, str2)) {
                strArr[i] = str2;
                i++;
            }
        }
        if (i >= 1) {
            return JsseUtils.resize(strArr, i);
        }
        LOG.severe("'" + str + "' contained no supported protocol values (ignoring)");
        return null;
    }

    private static String[] getKeysArray(Map<String, ?> map) {
        return getArray(map.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProtocolVersion getProtocolVersion(String str) {
        return SUPPORTED_PROTOCOL_MAP.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getProtocolVersionName(ProtocolVersion protocolVersion) {
        if (protocolVersion != null) {
            for (Map.Entry<String, ProtocolVersion> entry : SUPPORTED_PROTOCOL_MAP.entrySet()) {
                if (entry.getValue().equals(protocolVersion)) {
                    return entry.getKey();
                }
            }
            return "NONE";
        }
        return "NONE";
    }

    private String[] implGetDefaultCipherSuites(boolean z) {
        return this.defaultCipherSuites;
    }

    private String[] implGetDefaultProtocols(boolean z) {
        return z ? this.defaultProtocolsClient : this.defaultProtocolsServer;
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected synchronized SSLEngine engineCreateSSLEngine() {
        return SSLEngineUtil.create(getContextData());
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected synchronized SSLEngine engineCreateSSLEngine(String str, int i) {
        return SSLEngineUtil.create(getContextData(), str, i);
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected synchronized SSLSessionContext engineGetClientSessionContext() {
        return getContextData().getClientSessionContext();
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected SSLParameters engineGetDefaultSSLParameters() {
        getContextData();
        return SSLParametersUtil.getSSLParameters(getDefaultSSLParameters(true));
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected synchronized SSLSessionContext engineGetServerSessionContext() {
        return getContextData().getServerSessionContext();
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected SSLServerSocketFactory engineGetServerSocketFactory() {
        return new ProvSSLServerSocketFactory(getContextData());
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected SSLSocketFactory engineGetSocketFactory() {
        return new ProvSSLSocketFactory(getContextData());
    }

    @Override // javax.net.ssl.SSLContextSpi
    protected SSLParameters engineGetSupportedSSLParameters() {
        getContextData();
        return SSLParametersUtil.getSSLParameters(getSupportedSSLParameters(true));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.net.ssl.SSLContextSpi
    public synchronized void engineInit(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) throws KeyManagementException {
        this.contextData = null;
        JcaTlsCrypto mo12866create = this.cryptoProvider.mo12866create(secureRandom);
        BCX509ExtendedKeyManager selectX509KeyManager = selectX509KeyManager(mo12866create.getHelper(), keyManagerArr);
        BCX509ExtendedTrustManager selectX509TrustManager = selectX509TrustManager(mo12866create.getHelper(), trustManagerArr);
        mo12866create.getSecureRandom().nextInt();
        this.contextData = new ContextData(this, mo12866create, selectX509KeyManager, selectX509TrustManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] getActiveCipherSuites(JcaTlsCrypto jcaTlsCrypto, ProvSSLParameters provSSLParameters, ProtocolVersion[] protocolVersionArr) {
        String[] cipherSuitesArray = provSSLParameters.getCipherSuitesArray();
        BCAlgorithmConstraints algorithmConstraints = provSSLParameters.getAlgorithmConstraints();
        int[] iArr = new int[cipherSuitesArray.length];
        int i = 0;
        for (String str : cipherSuitesArray) {
            CipherSuiteInfo cipherSuiteInfo = this.supportedCipherSuites.get(str);
            if (cipherSuiteInfo != null && algorithmConstraints.permits(TLS_CRYPTO_PRIMITIVES_BC, str, null)) {
                iArr[i] = cipherSuiteInfo.getCipherSuite();
                i++;
            }
        }
        int[] supportedCipherSuites = TlsUtils.getSupportedCipherSuites(jcaTlsCrypto, iArr, i);
        if (supportedCipherSuites.length >= 1) {
            return supportedCipherSuites;
        }
        throw new IllegalStateException("No usable cipher suites enabled");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProtocolVersion[] getActiveProtocolVersions(ProvSSLParameters provSSLParameters) {
        String[] protocolsArray = provSSLParameters.getProtocolsArray();
        BCAlgorithmConstraints algorithmConstraints = provSSLParameters.getAlgorithmConstraints();
        TreeSet treeSet = new TreeSet(new Comparator<ProtocolVersion>() { // from class: org.bouncycastle.jsse.provider.ProvSSLContextSpi.1
            @Override // java.util.Comparator
            public int compare(ProtocolVersion protocolVersion, ProtocolVersion protocolVersion2) {
                if (protocolVersion.isLaterVersionOf(protocolVersion2)) {
                    return -1;
                }
                return protocolVersion2.isLaterVersionOf(protocolVersion) ? 1 : 0;
            }
        });
        for (String str : protocolsArray) {
            ProtocolVersion protocolVersion = this.supportedProtocols.get(str);
            if (protocolVersion != null && algorithmConstraints.permits(TLS_CRYPTO_PRIMITIVES_BC, str, null)) {
                treeSet.add(protocolVersion);
            }
        }
        if (!treeSet.isEmpty()) {
            return (ProtocolVersion[]) treeSet.toArray(new ProtocolVersion[treeSet.size()]);
        }
        throw new IllegalStateException("No usable protocols enabled");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized ContextData getContextData() {
        if (this.contextData == null) {
            throw new IllegalStateException("SSLContext has not been initialized.");
        }
        return this.contextData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getDefaultCipherSuites(boolean z) {
        return (String[]) implGetDefaultCipherSuites(z).clone();
    }

    String[] getDefaultProtocols(boolean z) {
        return (String[]) implGetDefaultProtocols(z).clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLParameters getDefaultSSLParameters(boolean z) {
        return new ProvSSLParameters(this, implGetDefaultCipherSuites(z), implGetDefaultProtocols(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getSupportedCipherSuites() {
        return getKeysArray(this.supportedCipherSuites);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getSupportedCipherSuites(String[] strArr) {
        if (strArr != null) {
            ArrayList arrayList = new ArrayList(strArr.length);
            for (String str : strArr) {
                if (str == null || str.length() < 1) {
                    throw new IllegalArgumentException("'cipherSuites' cannot contain null or empty string elements");
                }
                if (this.supportedCipherSuites.containsKey(str)) {
                    arrayList.add(str);
                }
            }
            return getArray(arrayList);
        }
        throw new NullPointerException("'cipherSuites' cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getSupportedProtocols() {
        return getKeysArray(this.supportedProtocols);
    }

    ProvSSLParameters getSupportedSSLParameters(boolean z) {
        return new ProvSSLParameters(this, getSupportedCipherSuites(), getSupportedProtocols());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFips() {
        return this.isInFipsMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSupportedProtocols(String[] strArr) {
        if (strArr == null) {
            return false;
        }
        for (String str : strArr) {
            if (str == null || !this.supportedProtocols.containsKey(str)) {
                return false;
            }
        }
        return true;
    }

    protected BCX509ExtendedKeyManager selectX509KeyManager(JcaJceHelper jcaJceHelper, KeyManager[] keyManagerArr) throws KeyManagementException {
        if (keyManagerArr != null) {
            for (KeyManager keyManager : keyManagerArr) {
                if (keyManager instanceof X509KeyManager) {
                    return X509KeyManagerUtil.importX509KeyManager(jcaJceHelper, (X509KeyManager) keyManager);
                }
            }
        }
        return DummyX509KeyManager.INSTANCE;
    }

    protected BCX509ExtendedTrustManager selectX509TrustManager(JcaJceHelper jcaJceHelper, TrustManager[] trustManagerArr) throws KeyManagementException {
        if (trustManagerArr == null) {
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                trustManagerArr = trustManagerFactory.getTrustManagers();
            } catch (Exception e) {
                LOG.log(Level.WARNING, "Failed to load default trust managers", (Throwable) e);
            }
        }
        if (trustManagerArr != null) {
            for (TrustManager trustManager : trustManagerArr) {
                if (trustManager instanceof X509TrustManager) {
                    return X509TrustManagerUtil.importX509TrustManager(jcaJceHelper, (X509TrustManager) trustManager);
                }
            }
        }
        return DummyX509TrustManager.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateDefaultSSLParameters(ProvSSLParameters provSSLParameters, boolean z) {
        if (provSSLParameters.getCipherSuitesArray() == implGetDefaultCipherSuites(!z)) {
            provSSLParameters.setCipherSuitesArray(implGetDefaultCipherSuites(z));
        }
        if (provSSLParameters.getProtocolsArray() == implGetDefaultProtocols(!z)) {
            provSSLParameters.setProtocolsArray(implGetDefaultProtocols(z));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String validateNegotiatedCipherSuite(ProvSSLParameters provSSLParameters, int i) {
        String cipherSuiteName = getCipherSuiteName(i);
        if (cipherSuiteName == null || !JsseUtils.contains(provSSLParameters.getCipherSuitesArray(), cipherSuiteName) || !provSSLParameters.getAlgorithmConstraints().permits(TLS_CRYPTO_PRIMITIVES_BC, cipherSuiteName, null) || !this.supportedCipherSuites.containsKey(cipherSuiteName) || (this.isInFipsMode && !FipsUtils.isFipsCipherSuite(cipherSuiteName))) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("SSL connection negotiated unsupported ciphersuite: ", i));
        }
        return cipherSuiteName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String validateNegotiatedProtocol(ProvSSLParameters provSSLParameters, ProtocolVersion protocolVersion) {
        String protocolVersionName = getProtocolVersionName(protocolVersion);
        if (protocolVersionName == null || !JsseUtils.contains(provSSLParameters.getProtocolsArray(), protocolVersionName) || !provSSLParameters.getAlgorithmConstraints().permits(TLS_CRYPTO_PRIMITIVES_BC, protocolVersionName, null) || !this.supportedProtocols.containsKey(protocolVersionName) || (this.isInFipsMode && !FipsUtils.isFipsProtocol(protocolVersionName))) {
            throw new IllegalStateException("SSL connection negotiated unsupported protocol: " + protocolVersion);
        }
        return protocolVersionName;
    }
}
