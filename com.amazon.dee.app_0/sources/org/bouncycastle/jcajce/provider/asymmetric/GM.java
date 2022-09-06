package org.bouncycastle.jcajce.provider.asymmetric;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
/* loaded from: classes4.dex */
public class GM {
    private static final String PREFIX = "org.bouncycastle.jcajce.provider.asymmetric.ec.";
    private static final Map<String, String> generalSm2Attributes = new HashMap();

    /* loaded from: classes4.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Signature.SHA256WITHSM2", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMSignatureSpi$sha256WithSM2", "Alg.Alias.Signature."), GMObjectIdentifiers.sm2sign_with_sha256, configurableProvider, "SHA256WITHSM2");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Signature.SM3WITHSM2", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMSignatureSpi$sm3WithSM2", "Alg.Alias.Signature."), GMObjectIdentifiers.sm2sign_with_sm3, configurableProvider, "SM3WITHSM2");
            StringBuilder outline122 = GeneratedOutlineSupport1.outline122(configurableProvider, "Cipher.SM2", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2", "Alg.Alias.Cipher.SM2WITHSM3", "SM2");
            outline122.append("Alg.Alias.Cipher.");
            GeneratedOutlineSupport1.outline183(outline122, GMObjectIdentifiers.sm2encrypt_with_sm3, configurableProvider, "SM2");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHBLAKE2B", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withBlake2b", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_blake2b512, configurableProvider, "SM2WITHBLAKE2B");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHBLAKE2S", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withBlake2s", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_blake2s256, configurableProvider, "SM2WITHBLAKE2S");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHWHIRLPOOL", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withWhirlpool", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_whirlpool, configurableProvider, "SM2WITHWHIRLPOOL");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHMD5", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withMD5", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_md5, configurableProvider, "SM2WITHMD5");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHRIPEMD160", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withRMD", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_rmd160, configurableProvider, "SM2WITHRIPEMD160");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHSHA1", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha1", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_sha1, configurableProvider, "SM2WITHSHA1");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHSHA224", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha224", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_sha224, configurableProvider, "SM2WITHSHA224");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHSHA256", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha256", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_sha256, configurableProvider, "SM2WITHSHA256");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHSHA384", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha384", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_sha384, configurableProvider, "SM2WITHSHA384");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Cipher.SM2WITHSHA512", "org.bouncycastle.jcajce.provider.asymmetric.ec.GMCipherSpi$SM2withSha512", "Alg.Alias.Cipher."), GMObjectIdentifiers.sm2encrypt_with_sha512, configurableProvider, "SM2WITHSHA512");
        }
    }

    static {
        generalSm2Attributes.put("SupportedKeyClasses", "java.security.interfaces.ECPublicKey|java.security.interfaces.ECPrivateKey");
        generalSm2Attributes.put("SupportedKeyFormats", "PKCS#8|X.509");
    }
}
