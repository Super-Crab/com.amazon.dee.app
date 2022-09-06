package org.bouncycastle.jcajce.provider.symmetric;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.ChaCha7539Engine;
import org.bouncycastle.crypto.engines.ChaChaEngine;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.modes.ChaCha20Poly1305;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseStreamCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
/* loaded from: classes4.dex */
public final class ChaCha {

    /* loaded from: classes4.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "ChaCha7539 IV";
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParamsCC1305 extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "ChaCha20-Poly1305 IV";
        }
    }

    /* loaded from: classes4.dex */
    public static class Base extends BaseStreamCipher {
        public Base() {
            super(new ChaChaEngine(), 8);
        }
    }

    /* loaded from: classes4.dex */
    public static class Base7539 extends BaseStreamCipher {
        public Base7539() {
            super(new ChaCha7539Engine(), 12);
        }
    }

    /* loaded from: classes4.dex */
    public static class BaseCC20P1305 extends BaseBlockCipher {
        public BaseCC20P1305() {
            super((AEADCipher) new ChaCha20Poly1305(), true, 12);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("ChaCha", 128, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen7539 extends BaseKeyGenerator {
        public KeyGen7539() {
            super("ChaCha7539", 256, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = ChaCha.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$Base", configurableProvider, "Cipher.CHACHA"), PREFIX, "$KeyGen", configurableProvider, "KeyGenerator.CHACHA"), PREFIX, "$Base7539", configurableProvider, "Cipher.CHACHA7539"), PREFIX, "$KeyGen7539", configurableProvider, "KeyGenerator.CHACHA7539"), PREFIX, "$AlgParams", configurableProvider, "AlgorithmParameters.CHACHA7539");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.CHACHA20", "CHACHA7539");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.CHACHA20", "CHACHA7539");
            StringBuilder outline122 = GeneratedOutlineSupport1.outline122(configurableProvider, "Alg.Alias.AlgorithmParameters.CHACHA20", "CHACHA7539", "Alg.Alias.KeyGenerator.CHACHA20-POLY1305", "CHACHA7539");
            outline122.append("Alg.Alias.KeyGenerator.");
            StringBuilder outline117 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline118(outline122, PKCSObjectIdentifiers.id_alg_AEADChaCha20Poly1305, configurableProvider, "CHACHA7539"), PREFIX, "$BaseCC20P1305", configurableProvider, "Cipher.CHACHA20-POLY1305"), PREFIX, "$AlgParamsCC1305", configurableProvider, "AlgorithmParameters.CHACHA20-POLY1305");
            outline117.append("Alg.Alias.Cipher.");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline117, PKCSObjectIdentifiers.id_alg_AEADChaCha20Poly1305, configurableProvider, "CHACHA20-POLY1305", "Alg.Alias.AlgorithmParameters."), PKCSObjectIdentifiers.id_alg_AEADChaCha20Poly1305, configurableProvider, "CHACHA20-POLY1305", "Alg.Alias.Cipher.OID."), PKCSObjectIdentifiers.id_alg_AEADChaCha20Poly1305, configurableProvider, "CHACHA20-POLY1305", "Alg.Alias.AlgorithmParameters.OID."), PKCSObjectIdentifiers.id_alg_AEADChaCha20Poly1305, configurableProvider, "CHACHA20-POLY1305");
        }
    }

    private ChaCha() {
    }
}
