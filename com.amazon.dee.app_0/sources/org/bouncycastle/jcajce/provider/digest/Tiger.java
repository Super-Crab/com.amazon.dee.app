package org.bouncycastle.jcajce.provider.digest;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.digests.TigerDigest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
/* loaded from: classes4.dex */
public class Tiger {

    /* loaded from: classes4.dex */
    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new TigerDigest());
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Digest digest = (Digest) super.clone();
            digest.digest = new TigerDigest((TigerDigest) this.digest);
            return digest;
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new TigerDigest()));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACTIGER", 192, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = Tiger.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            addHMACAlgorithm(configurableProvider, "TIGER", GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$Digest", configurableProvider, "MessageDigest.TIGER"), PREFIX, "$Digest", configurableProvider, "MessageDigest.Tiger"), PREFIX, "$HashMac"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator"));
            addHMACAlias(configurableProvider, "TIGER", IANAObjectIdentifiers.hmacTIGER);
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$PBEWithMacKeyFactory", configurableProvider, "SecretKeyFactory.PBEWITHHMACTIGER");
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithHashMac extends BaseMac {
        public PBEWithHashMac() {
            super(new HMac(new TigerDigest()), 2, 3, 192);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithMacKeyFactory extends PBESecretKeyFactory {
        public PBEWithMacKeyFactory() {
            super("PBEwithHmacTiger", null, false, 2, 3, 192, 0);
        }
    }

    /* loaded from: classes4.dex */
    public static class TigerHmac extends BaseMac {
        public TigerHmac() {
            super(new HMac(new TigerDigest()));
        }
    }

    private Tiger() {
    }
}
