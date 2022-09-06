package org.bouncycastle.jsse.provider;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.whispercloak.KeyUtils;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCryptoProvider;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class BouncyCastleJsseProvider extends Provider {
    private static final String PROVIDER_INFO = "Bouncy Castle JSSE Provider Version 1.0.11";
    public static final String PROVIDER_NAME = "BCJSSE";
    static final boolean PROVIDER_TLS13_ENABLED = false;
    private static final double PROVIDER_VERSION = 1.0011d;
    private static final Map<Map<String, String>, Map<String, String>> attributeMaps = new HashMap();
    private Map<String, EngineCreator> creatorMap;
    private final boolean isInFipsMode;
    private Map<String, BcJsseService> serviceMap;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class BcJsseService extends Provider.Service {
        private final EngineCreator creator;

        public BcJsseService(Provider provider, String str, String str2, String str3, List<String> list, Map<String, String> map, EngineCreator engineCreator) {
            super(provider, str, str2, str3, list, map);
            this.creator = engineCreator;
        }

        @Override // java.security.Provider.Service
        public Object newInstance(Object obj) throws NoSuchAlgorithmException {
            try {
                Object createInstance = this.creator.createInstance(obj);
                if (createInstance != null) {
                    return createInstance;
                }
                throw new NoSuchAlgorithmException("No such algorithm in FIPS approved mode: " + getAlgorithm());
            } catch (NoSuchAlgorithmException e) {
                throw e;
            } catch (Exception e2) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to invoke creator for ");
                outline107.append(getAlgorithm());
                outline107.append(RealTimeTextConstants.COLON_SPACE);
                outline107.append(e2.getMessage());
                throw new NoSuchAlgorithmException(outline107.toString(), e2);
            }
        }
    }

    public BouncyCastleJsseProvider() {
        this(false);
    }

    public BouncyCastleJsseProvider(String str) {
        super(PROVIDER_NAME, PROVIDER_VERSION, PROVIDER_INFO);
        this.serviceMap = new HashMap();
        this.creatorMap = new HashMap();
        String trim = str.trim();
        int indexOf = trim.indexOf(58);
        boolean z = false;
        if (indexOf >= 0) {
            String trim2 = trim.substring(0, indexOf).trim();
            trim = trim.substring(indexOf + 1).trim();
            z = trim2.equalsIgnoreCase("fips");
        }
        try {
            this.isInFipsMode = configure(z, createCryptoProvider(trim));
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("unable to set up JcaTlsCryptoProvider: ")), e);
        }
    }

    public BouncyCastleJsseProvider(Provider provider) {
        this(false, provider);
    }

    public BouncyCastleJsseProvider(boolean z) {
        super(PROVIDER_NAME, PROVIDER_VERSION, PROVIDER_INFO);
        this.serviceMap = new HashMap();
        this.creatorMap = new HashMap();
        this.isInFipsMode = configure(z, new JcaTlsCryptoProvider());
    }

    public BouncyCastleJsseProvider(boolean z, Provider provider) {
        super(PROVIDER_NAME, PROVIDER_VERSION, PROVIDER_INFO);
        this.serviceMap = new HashMap();
        this.creatorMap = new HashMap();
        this.isInFipsMode = configure(z, new JcaTlsCryptoProvider().setProvider(provider));
    }

    public BouncyCastleJsseProvider(boolean z, JcaTlsCryptoProvider jcaTlsCryptoProvider) {
        super(PROVIDER_NAME, PROVIDER_VERSION, PROVIDER_INFO);
        this.serviceMap = new HashMap();
        this.creatorMap = new HashMap();
        this.isInFipsMode = configure(z, jcaTlsCryptoProvider);
    }

    private boolean configure(final boolean z, final JcaTlsCryptoProvider jcaTlsCryptoProvider) {
        addAlgorithmImplementation("KeyManagerFactory.X.509", "org.bouncycastle.jsse.provider.KeyManagerFactory", new EngineCreator() { // from class: org.bouncycastle.jsse.provider.BouncyCastleJsseProvider.1
            @Override // org.bouncycastle.jsse.provider.EngineCreator
            public Object createInstance(Object obj) {
                return new ProvKeyManagerFactorySpi(jcaTlsCryptoProvider.getHelper());
            }
        });
        addAlias("Alg.Alias.KeyManagerFactory.X509", KeyUtils.X509_CERITIFATE_FACTORY);
        addAlias("Alg.Alias.KeyManagerFactory.PKIX", KeyUtils.X509_CERITIFATE_FACTORY);
        addAlgorithmImplementation("TrustManagerFactory.PKIX", "org.bouncycastle.jsse.provider.TrustManagerFactory", new EngineCreator() { // from class: org.bouncycastle.jsse.provider.BouncyCastleJsseProvider.2
            @Override // org.bouncycastle.jsse.provider.EngineCreator
            public Object createInstance(Object obj) {
                return new ProvTrustManagerFactorySpi(jcaTlsCryptoProvider.getHelper());
            }
        });
        addAlias("Alg.Alias.TrustManagerFactory.X.509", "PKIX");
        addAlias("Alg.Alias.TrustManagerFactory.X509", "PKIX");
        addAlgorithmImplementation("SSLContext.TLS", "org.bouncycastle.jsse.provider.SSLContext.TLS", new EngineCreator() { // from class: org.bouncycastle.jsse.provider.BouncyCastleJsseProvider.3
            @Override // org.bouncycastle.jsse.provider.EngineCreator
            public Object createInstance(Object obj) {
                return new ProvSSLContextSpi(z, jcaTlsCryptoProvider, null);
            }
        });
        addAlgorithmImplementation("SSLContext.TLSV1", "org.bouncycastle.jsse.provider.SSLContext.TLSv1", new EngineCreator() { // from class: org.bouncycastle.jsse.provider.BouncyCastleJsseProvider.4
            @Override // org.bouncycastle.jsse.provider.EngineCreator
            public Object createInstance(Object obj) {
                return new ProvSSLContextSpi(z, jcaTlsCryptoProvider, new String[]{"TLSv1"});
            }
        });
        addAlgorithmImplementation("SSLContext.TLSV1.1", "org.bouncycastle.jsse.provider.SSLContext.TLSv1_1", new EngineCreator() { // from class: org.bouncycastle.jsse.provider.BouncyCastleJsseProvider.5
            @Override // org.bouncycastle.jsse.provider.EngineCreator
            public Object createInstance(Object obj) {
                return new ProvSSLContextSpi(z, jcaTlsCryptoProvider, new String[]{"TLSv1.1", "TLSv1"});
            }
        });
        addAlgorithmImplementation("SSLContext.TLSV1.2", "org.bouncycastle.jsse.provider.SSLContext.TLSv1_2", new EngineCreator() { // from class: org.bouncycastle.jsse.provider.BouncyCastleJsseProvider.6
            @Override // org.bouncycastle.jsse.provider.EngineCreator
            public Object createInstance(Object obj) {
                return new ProvSSLContextSpi(z, jcaTlsCryptoProvider, new String[]{"TLSv1.2", "TLSv1.1", "TLSv1"});
            }
        });
        addAlgorithmImplementation("SSLContext.DEFAULT", "org.bouncycastle.jsse.provider.SSLContext.Default", new EngineCreator() { // from class: org.bouncycastle.jsse.provider.BouncyCastleJsseProvider.8
            @Override // org.bouncycastle.jsse.provider.EngineCreator
            public Object createInstance(Object obj) throws GeneralSecurityException {
                return new DefaultSSLContextSpi(z, jcaTlsCryptoProvider);
            }
        });
        addAlias("Alg.Alias.SSLContext.SSL", SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
        addAlias("Alg.Alias.SSLContext.SSLV3", "TLSV1");
        return z;
    }

    private JcaTlsCryptoProvider createCryptoProvider(String str) throws GeneralSecurityException {
        if (str.equalsIgnoreCase("default")) {
            return new JcaTlsCryptoProvider();
        }
        Provider provider = Security.getProvider(str);
        if (provider != null) {
            return new JcaTlsCryptoProvider().setProvider(provider);
        }
        try {
            Object newInstance = Class.forName(str).newInstance();
            if (newInstance instanceof JcaTlsCryptoProvider) {
                return (JcaTlsCryptoProvider) newInstance;
            }
            if (newInstance instanceof Provider) {
                return new JcaTlsCryptoProvider().setProvider((Provider) newInstance);
            }
            throw new IllegalArgumentException("unrecognized class: " + str);
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("unable to find Provider/JcaTlsCryptoProvider class: ", str));
        } catch (IllegalAccessException e) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("unable to create Provider/JcaTlsCryptoProvider class '", str, "': ");
            outline115.append(e.getMessage());
            throw new IllegalArgumentException(outline115.toString(), e);
        } catch (InstantiationException e2) {
            StringBuilder outline1152 = GeneratedOutlineSupport1.outline115("unable to create Provider/JcaTlsCryptoProvider class '", str, "': ");
            outline1152.append(e2.getMessage());
            throw new IllegalArgumentException(outline1152.toString(), e2);
        }
    }

    private static Map<String, String> getAttributeMap(Map<String, String> map) {
        Map<String, String> map2 = attributeMaps.get(map);
        if (map2 != null) {
            return map2;
        }
        attributeMaps.put(map, map);
        return map;
    }

    void addAlgorithmImplementation(String str, String str2, EngineCreator engineCreator) {
        if (!containsKey(str)) {
            addAttribute(str, "ImplementedIn", ExifInterface.TAG_SOFTWARE);
            put(str, str2);
            this.creatorMap.put(str2, engineCreator);
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline75("duplicate provider key (", str, ") found"));
    }

    void addAlias(String str, String str2) {
        if (!containsKey(str)) {
            put(str, str2);
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline75("duplicate provider key (", str, ") found"));
    }

    void addAttribute(String str, String str2, String str3) {
        String outline75 = GeneratedOutlineSupport1.outline75(str, " ", str2);
        if (!containsKey(outline75)) {
            put(outline75, str3);
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline75("duplicate provider attribute key (", outline75, ") found"));
    }

    public Provider configure(String str) {
        return new BouncyCastleJsseProvider(str);
    }

    @Override // java.security.Provider
    public final synchronized Provider.Service getService(String str, String str2) {
        BcJsseService bcJsseService;
        String upperCase = Strings.toUpperCase(str2);
        BcJsseService bcJsseService2 = this.serviceMap.get(str + "." + upperCase);
        if (bcJsseService2 == null) {
            String str3 = "Alg.Alias." + str + ".";
            String str4 = (String) get(str3 + upperCase);
            if (str4 == null) {
                str4 = upperCase;
            }
            String str5 = (String) get(str + "." + str4);
            if (str5 == null) {
                return null;
            }
            String str6 = str + "." + upperCase + " ";
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            for (Object obj : keySet()) {
                String str7 = (String) obj;
                if (str7.startsWith(str3) && get(obj).equals(str2)) {
                    arrayList.add(str7.substring(str3.length()));
                }
                if (str7.startsWith(str6)) {
                    hashMap.put(str7.substring(str6.length()), (String) get(str7));
                }
            }
            bcJsseService = new BcJsseService(this, str, upperCase, str5, arrayList, getAttributeMap(hashMap), this.creatorMap.get(str5));
            this.serviceMap.put(str + "." + upperCase, bcJsseService);
        } else {
            bcJsseService = bcJsseService2;
        }
        return bcJsseService;
    }

    @Override // java.security.Provider
    public final synchronized Set<Provider.Service> getServices() {
        HashSet hashSet;
        Set<Provider.Service> services = super.getServices();
        hashSet = new HashSet();
        for (Provider.Service service : services) {
            hashSet.add(getService(service.getType(), service.getAlgorithm()));
        }
        return hashSet;
    }

    public boolean isFipsMode() {
        return this.isInFipsMode;
    }
}
