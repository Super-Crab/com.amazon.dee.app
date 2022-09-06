package org.bouncycastle.jcajce.provider.digest;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
/* loaded from: classes4.dex */
public class SHA3 {

    /* loaded from: classes4.dex */
    public static class Digest224 extends DigestSHA3 {
        public Digest224() {
            super(224);
        }
    }

    /* loaded from: classes4.dex */
    public static class Digest256 extends DigestSHA3 {
        public Digest256() {
            super(256);
        }
    }

    /* loaded from: classes4.dex */
    public static class Digest384 extends DigestSHA3 {
        public Digest384() {
            super(BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT);
        }
    }

    /* loaded from: classes4.dex */
    public static class Digest512 extends DigestSHA3 {
        public Digest512() {
            super(512);
        }
    }

    /* loaded from: classes4.dex */
    public static class DigestSHA3 extends BCMessageDigest implements Cloneable {
        public DigestSHA3(int i) {
            super(new SHA3Digest(i));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            BCMessageDigest bCMessageDigest = (BCMessageDigest) super.clone();
            bCMessageDigest.digest = new SHA3Digest((SHA3Digest) this.digest);
            return bCMessageDigest;
        }
    }

    /* loaded from: classes4.dex */
    public static class DigestSHAKE extends BCMessageDigest implements Cloneable {
        public DigestSHAKE(int i, int i2) {
            super(new SHAKEDigest(i), i2);
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            BCMessageDigest bCMessageDigest = (BCMessageDigest) super.clone();
            bCMessageDigest.digest = new SHAKEDigest((SHAKEDigest) this.digest);
            return bCMessageDigest;
        }

        @Override // org.bouncycastle.jcajce.provider.digest.BCMessageDigest, java.security.MessageDigestSpi
        public byte[] engineDigest() {
            int i = this.digestSize;
            byte[] bArr = new byte[i];
            ((Xof) this.digest).doFinal(bArr, 0, i);
            return bArr;
        }
    }

    /* loaded from: classes4.dex */
    public static class DigestShake128_256 extends DigestSHAKE {
        public DigestShake128_256() {
            super(128, 256);
        }
    }

    /* loaded from: classes4.dex */
    public static class DigestShake256_512 extends DigestSHAKE {
        public DigestShake256_512() {
            super(256, 512);
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac224 extends HashMacSHA3 {
        public HashMac224() {
            super(224);
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac256 extends HashMacSHA3 {
        public HashMac256() {
            super(256);
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac384 extends HashMacSHA3 {
        public HashMac384() {
            super(BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT);
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMac512 extends HashMacSHA3 {
        public HashMac512() {
            super(512);
        }
    }

    /* loaded from: classes4.dex */
    public static class HashMacSHA3 extends BaseMac {
        public HashMacSHA3(int i) {
            super(new HMac(new SHA3Digest(i)));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator224 extends KeyGeneratorSHA3 {
        public KeyGenerator224() {
            super(224);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator256 extends KeyGeneratorSHA3 {
        public KeyGenerator256() {
            super(256);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator384 extends KeyGeneratorSHA3 {
        public KeyGenerator384() {
            super(BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGenerator512 extends KeyGeneratorSHA3 {
        public KeyGenerator512() {
            super(512);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGeneratorSHA3 extends BaseKeyGenerator {
        public KeyGeneratorSHA3(int i) {
            super(GeneratedOutlineSupport1.outline49("HMACSHA3-", i), i, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = SHA3.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$Digest224", configurableProvider, "MessageDigest.SHA3-224"), PREFIX, "$Digest256", configurableProvider, "MessageDigest.SHA3-256"), PREFIX, "$Digest384", configurableProvider, "MessageDigest.SHA3-384"), PREFIX, "$Digest512", configurableProvider, "MessageDigest.SHA3-512");
            configurableProvider.addAlgorithm("MessageDigest", NISTObjectIdentifiers.id_sha3_224, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Digest224"));
            configurableProvider.addAlgorithm("MessageDigest", NISTObjectIdentifiers.id_sha3_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Digest256"));
            configurableProvider.addAlgorithm("MessageDigest", NISTObjectIdentifiers.id_sha3_384, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Digest384"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "MessageDigest", NISTObjectIdentifiers.id_sha3_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Digest512")), PREFIX, "$DigestShake256_512", configurableProvider, "MessageDigest.SHAKE256-512"), PREFIX, "$DigestShake128_256", configurableProvider, "MessageDigest.SHAKE128-256");
            configurableProvider.addAlgorithm("MessageDigest", NISTObjectIdentifiers.id_shake256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$DigestShake256_512"));
            configurableProvider.addAlgorithm("MessageDigest", NISTObjectIdentifiers.id_shake128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$DigestShake128_256"));
            addHMACAlgorithm(configurableProvider, "SHA3-224", GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline122(configurableProvider, "Alg.Alias.MessageDigest.SHAKE256", "SHAKE256-512", "Alg.Alias.MessageDigest.SHAKE128", "SHAKE128-256"), PREFIX, "$HashMac224"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator224"));
            addHMACAlias(configurableProvider, "SHA3-224", NISTObjectIdentifiers.id_hmacWithSHA3_224);
            addHMACAlgorithm(configurableProvider, "SHA3-256", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$HashMac256"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator256"));
            addHMACAlias(configurableProvider, "SHA3-256", NISTObjectIdentifiers.id_hmacWithSHA3_256);
            addHMACAlgorithm(configurableProvider, "SHA3-384", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$HashMac384"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator384"));
            addHMACAlias(configurableProvider, "SHA3-384", NISTObjectIdentifiers.id_hmacWithSHA3_384);
            addHMACAlgorithm(configurableProvider, "SHA3-512", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$HashMac512"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGenerator512"));
            addHMACAlias(configurableProvider, "SHA3-512", NISTObjectIdentifiers.id_hmacWithSHA3_512);
        }
    }

    private SHA3() {
    }
}
