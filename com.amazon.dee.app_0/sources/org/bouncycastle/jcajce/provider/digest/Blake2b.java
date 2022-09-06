package org.bouncycastle.jcajce.provider.digest;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
/* loaded from: classes4.dex */
public class Blake2b {

    /* loaded from: classes4.dex */
    public static class Blake2b160 extends BCMessageDigest implements Cloneable {
        public Blake2b160() {
            super(new Blake2bDigest(160));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Blake2b160 blake2b160 = (Blake2b160) super.clone();
            blake2b160.digest = new Blake2bDigest((Blake2bDigest) this.digest);
            return blake2b160;
        }
    }

    /* loaded from: classes4.dex */
    public static class Blake2b256 extends BCMessageDigest implements Cloneable {
        public Blake2b256() {
            super(new Blake2bDigest(256));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Blake2b256 blake2b256 = (Blake2b256) super.clone();
            blake2b256.digest = new Blake2bDigest((Blake2bDigest) this.digest);
            return blake2b256;
        }
    }

    /* loaded from: classes4.dex */
    public static class Blake2b384 extends BCMessageDigest implements Cloneable {
        public Blake2b384() {
            super(new Blake2bDigest((int) BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Blake2b384 blake2b384 = (Blake2b384) super.clone();
            blake2b384.digest = new Blake2bDigest((Blake2bDigest) this.digest);
            return blake2b384;
        }
    }

    /* loaded from: classes4.dex */
    public static class Blake2b512 extends BCMessageDigest implements Cloneable {
        public Blake2b512() {
            super(new Blake2bDigest(512));
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Blake2b512 blake2b512 = (Blake2b512) super.clone();
            blake2b512.digest = new Blake2bDigest((Blake2bDigest) this.digest);
            return blake2b512;
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends DigestAlgorithmProvider {
        private static final String PREFIX = Blake2b.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder outline117 = GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$Blake2b512", configurableProvider, "MessageDigest.BLAKE2B-512");
            outline117.append("Alg.Alias.MessageDigest.");
            StringBuilder outline1172 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline118(outline117, MiscObjectIdentifiers.id_blake2b512, configurableProvider, "BLAKE2B-512"), PREFIX, "$Blake2b384", configurableProvider, "MessageDigest.BLAKE2B-384");
            outline1172.append("Alg.Alias.MessageDigest.");
            StringBuilder outline1173 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline118(outline1172, MiscObjectIdentifiers.id_blake2b384, configurableProvider, "BLAKE2B-384"), PREFIX, "$Blake2b256", configurableProvider, "MessageDigest.BLAKE2B-256");
            outline1173.append("Alg.Alias.MessageDigest.");
            StringBuilder outline1174 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline118(outline1173, MiscObjectIdentifiers.id_blake2b256, configurableProvider, "BLAKE2B-256"), PREFIX, "$Blake2b160", configurableProvider, "MessageDigest.BLAKE2B-160");
            outline1174.append("Alg.Alias.MessageDigest.");
            GeneratedOutlineSupport1.outline183(outline1174, MiscObjectIdentifiers.id_blake2b160, configurableProvider, "BLAKE2B-160");
        }
    }

    private Blake2b() {
    }
}
