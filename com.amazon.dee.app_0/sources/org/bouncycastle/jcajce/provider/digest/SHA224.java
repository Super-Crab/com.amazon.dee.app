package org.bouncycastle.jcajce.provider.digest;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
/* loaded from: classes4.dex */
public class SHA224 {

    /* loaded from: classes4.dex */
    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new SHA224Digest());
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Digest digest = (Digest) super.clone();
            digest.digest = new SHA224Digest((SHA224Digest) this.digest);
            return digest;
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new SHA224Digest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACSHA224", 224, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = SHA224.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$Digest", configurableProvider, "MessageDigest.SHA-224");
            addHMACAlgorithm(configurableProvider, "SHA224", GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline118(GeneratedOutlineSupport1.outline121(configurableProvider, "Alg.Alias.MessageDigest.SHA224", McElieceCCA2KeyGenParameterSpec.SHA224, "Alg.Alias.MessageDigest."), NISTObjectIdentifiers.id_sha224, configurableProvider, McElieceCCA2KeyGenParameterSpec.SHA224), PREFIX, "$HashMac", configurableProvider, "Mac.PBEWITHHMACSHA224"), PREFIX, "$HashMac"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator"));
            addHMACAlias(configurableProvider, "SHA224", PKCSObjectIdentifiers.id_hmacWithSHA224);
        }
    }

    private SHA224() {
    }
}
