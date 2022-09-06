package org.bouncycastle.jcajce.provider.digest;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
/* loaded from: classes4.dex */
public class SHA1 {

    /* loaded from: classes4.dex */
    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new SHA1Digest());
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Digest digest = (Digest) super.clone();
            digest.digest = new SHA1Digest((SHA1Digest) this.digest);
            return digest;
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new SHA1Digest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACSHA1", 160, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = SHA1.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$Digest", configurableProvider, "MessageDigest.SHA-1");
            StringBuilder outline122 = GeneratedOutlineSupport1.outline122(configurableProvider, "Alg.Alias.MessageDigest.SHA1", "SHA-1", "Alg.Alias.MessageDigest.SHA", "SHA-1");
            outline122.append("Alg.Alias.MessageDigest.");
            addHMACAlgorithm(configurableProvider, "SHA1", GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline118(outline122, OIWObjectIdentifiers.idSHA1, configurableProvider, "SHA-1"), PREFIX, "$HashMac"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator"));
            addHMACAlias(configurableProvider, "SHA1", PKCSObjectIdentifiers.id_hmacWithSHA1);
            addHMACAlias(configurableProvider, "SHA1", IANAObjectIdentifiers.hmacSHA1);
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$SHA1Mac", configurableProvider, "Mac.PBEWITHHMACSHA"), PREFIX, "$SHA1Mac", configurableProvider, "Mac.PBEWITHHMACSHA1");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline118(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline121(configurableProvider, "Alg.Alias.SecretKeyFactory.PBEWITHHMACSHA", "PBEWITHHMACSHA1", "Alg.Alias.SecretKeyFactory."), OIWObjectIdentifiers.idSHA1, configurableProvider, "PBEWITHHMACSHA1", "Alg.Alias.Mac."), OIWObjectIdentifiers.idSHA1, configurableProvider, "PBEWITHHMACSHA"), PREFIX, "$PBEWithMacKeyFactory", configurableProvider, "SecretKeyFactory.PBEWITHHMACSHA1");
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithMacKeyFactory extends PBESecretKeyFactory {
        public PBEWithMacKeyFactory() {
            super("PBEwithHmacSHA", null, false, 2, 1, 160, 0);
        }
    }

    /* loaded from: classes4.dex */
    public static class SHA1Mac extends BaseMac {
        public SHA1Mac() {
            super(new HMac(new SHA1Digest()));
        }
    }

    private SHA1() {
    }
}
