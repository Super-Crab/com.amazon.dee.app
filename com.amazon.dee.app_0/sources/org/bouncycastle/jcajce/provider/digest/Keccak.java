package org.bouncycastle.jcajce.provider.digest;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.digests.KeccakDigest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
/* loaded from: classes4.dex */
public class Keccak {

    /* loaded from: classes4.dex */
    public static class Digest224 extends DigestKeccak {
        public Digest224() {
            super(224);
        }
    }

    /* loaded from: classes4.dex */
    public static class Digest256 extends DigestKeccak {
        public Digest256() {
            super(256);
        }
    }

    /* loaded from: classes4.dex */
    public static class Digest288 extends DigestKeccak {
        public Digest288() {
            super(288);
        }
    }

    /* loaded from: classes4.dex */
    public static class Digest384 extends DigestKeccak {
        public Digest384() {
            super(BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT);
        }
    }

    /* loaded from: classes4.dex */
    public static class Digest512 extends DigestKeccak {
        public Digest512() {
            super(512);
        }
    }

    /* loaded from: classes4.dex */
    public static class DigestKeccak extends BCMessageDigest implements Cloneable {
        public DigestKeccak(int i) {
            super(new KeccakDigest(i));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            BCMessageDigest bCMessageDigest = (BCMessageDigest) super.clone();
            bCMessageDigest.digest = new KeccakDigest((KeccakDigest) this.digest);
            return bCMessageDigest;
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac224 extends BaseMac {
        public HashMac224() {
            super(new HMac(new KeccakDigest(224)));
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac256 extends BaseMac {
        public HashMac256() {
            super(new HMac(new KeccakDigest(256)));
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac288 extends BaseMac {
        public HashMac288() {
            super(new HMac(new KeccakDigest(288)));
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac384 extends BaseMac {
        public HashMac384() {
            super(new HMac(new KeccakDigest((int) BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT)));
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac512 extends BaseMac {
        public HashMac512() {
            super(new HMac(new KeccakDigest(512)));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator224 extends BaseKeyGenerator {
        public KeyGenerator224() {
            super("HMACKECCAK224", 224, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator256 extends BaseKeyGenerator {
        public KeyGenerator256() {
            super("HMACKECCAK256", 256, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator288 extends BaseKeyGenerator {
        public KeyGenerator288() {
            super("HMACKECCAK288", 288, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator384 extends BaseKeyGenerator {
        public KeyGenerator384() {
            super("HMACKECCAK384", BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator512 extends BaseKeyGenerator {
        public KeyGenerator512() {
            super("HMACKECCAK512", 512, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = Keccak.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            addHMACAlgorithm(configurableProvider, "KECCAK224", GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$Digest224", configurableProvider, "MessageDigest.KECCAK-224"), PREFIX, "$Digest288", configurableProvider, "MessageDigest.KECCAK-288"), PREFIX, "$Digest256", configurableProvider, "MessageDigest.KECCAK-256"), PREFIX, "$Digest384", configurableProvider, "MessageDigest.KECCAK-384"), PREFIX, "$Digest512", configurableProvider, "MessageDigest.KECCAK-512"), PREFIX, "$HashMac224"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator224"));
            addHMACAlgorithm(configurableProvider, "KECCAK256", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$HashMac256"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator256"));
            addHMACAlgorithm(configurableProvider, "KECCAK288", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$HashMac288"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator288"));
            addHMACAlgorithm(configurableProvider, "KECCAK384", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$HashMac384"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator384"));
            addHMACAlgorithm(configurableProvider, "KECCAK512", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$HashMac512"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator512"));
        }
    }

    private Keccak() {
    }
}
