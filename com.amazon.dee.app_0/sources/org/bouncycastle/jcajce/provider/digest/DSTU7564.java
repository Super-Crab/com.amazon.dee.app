package org.bouncycastle.jcajce.provider.digest;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import org.bouncycastle.asn1.ua.UAObjectIdentifiers;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.digests.DSTU7564Digest;
import org.bouncycastle.crypto.macs.DSTU7564Mac;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
/* loaded from: classes4.dex */
public class DSTU7564 {

    /* loaded from: classes4.dex */
    public static class Digest256 extends DigestDSTU7564 {
        public Digest256() {
            super(256);
        }
    }

    /* loaded from: classes4.dex */
    public static class Digest384 extends DigestDSTU7564 {
        public Digest384() {
            super(BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT);
        }
    }

    /* loaded from: classes4.dex */
    public static class Digest512 extends DigestDSTU7564 {
        public Digest512() {
            super(512);
        }
    }

    /* loaded from: classes4.dex */
    public static class DigestDSTU7564 extends BCMessageDigest implements Cloneable {
        public DigestDSTU7564(int i) {
            super(new DSTU7564Digest(i));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            BCMessageDigest bCMessageDigest = (BCMessageDigest) super.clone();
            bCMessageDigest.digest = new DSTU7564Digest((DSTU7564Digest) this.digest);
            return bCMessageDigest;
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac256 extends BaseMac {
        public HashMac256() {
            super(new DSTU7564Mac(256));
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac384 extends BaseMac {
        public HashMac384() {
            super(new DSTU7564Mac(BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT));
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac512 extends BaseMac {
        public HashMac512() {
            super(new DSTU7564Mac(512));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator256 extends BaseKeyGenerator {
        public KeyGenerator256() {
            super("HMACDSTU7564-256", 256, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator384 extends BaseKeyGenerator {
        public KeyGenerator384() {
            super("HMACDSTU7564-384", BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator512 extends BaseKeyGenerator {
        public KeyGenerator512() {
            super("HMACDSTU7564-512", 512, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = DSTU7564.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$Digest256", configurableProvider, "MessageDigest.DSTU7564-256"), PREFIX, "$Digest384", configurableProvider, "MessageDigest.DSTU7564-384"), PREFIX, "$Digest512", configurableProvider, "MessageDigest.DSTU7564-512");
            configurableProvider.addAlgorithm("MessageDigest", UAObjectIdentifiers.dstu7564digest_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Digest256"));
            configurableProvider.addAlgorithm("MessageDigest", UAObjectIdentifiers.dstu7564digest_384, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Digest384"));
            addHMACAlgorithm(configurableProvider, "DSTU7564-256", GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline123(configurableProvider, "MessageDigest", UAObjectIdentifiers.dstu7564digest_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Digest512")), PREFIX, "$HashMac256"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator256"));
            addHMACAlgorithm(configurableProvider, "DSTU7564-384", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$HashMac384"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator384"));
            addHMACAlgorithm(configurableProvider, "DSTU7564-512", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$HashMac512"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator512"));
            addHMACAlias(configurableProvider, "DSTU7564-256", UAObjectIdentifiers.dstu7564mac_256);
            addHMACAlias(configurableProvider, "DSTU7564-384", UAObjectIdentifiers.dstu7564mac_384);
            addHMACAlias(configurableProvider, "DSTU7564-512", UAObjectIdentifiers.dstu7564mac_512);
        }
    }

    private DSTU7564() {
    }
}
