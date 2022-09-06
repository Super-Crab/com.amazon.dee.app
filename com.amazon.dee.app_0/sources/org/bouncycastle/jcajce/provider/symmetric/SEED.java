package org.bouncycastle.jcajce.provider.symmetric;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.engines.SEEDEngine;
import org.bouncycastle.crypto.engines.SEEDWrapEngine;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.macs.GMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
/* loaded from: classes4.dex */
public final class SEED {

    /* loaded from: classes4.dex */
    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[16];
            if (this.random == null) {
                this.random = CryptoServicesRegistrar.getSecureRandom();
            }
            this.random.nextBytes(bArr);
            try {
                AlgorithmParameters createParametersInstance = createParametersInstance("SEED");
                createParametersInstance.init(new IvParameterSpec(bArr));
                return createParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for SEED parameter generation.");
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "SEED IV";
        }
    }

    /* loaded from: classes4.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new SEEDEngine()), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class CMAC extends BaseMac {
        public CMAC() {
            super(new CMac(new SEEDEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.SEED.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public BlockCipher get() {
                    return new SEEDEngine();
                }
            });
        }
    }

    /* loaded from: classes4.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new GMac(new GCMBlockCipher(new SEEDEngine())));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("SEED", null);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("SEED", 128, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends SymmetricAlgorithmProvider {
        private static final String PREFIX = SEED.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder outline117 = GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$AlgParams", configurableProvider, "AlgorithmParameters.SEED");
            outline117.append("Alg.Alias.AlgorithmParameters.");
            StringBuilder outline1172 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline118(outline117, KISAObjectIdentifiers.id_seedCBC, configurableProvider, "SEED"), PREFIX, "$AlgParamGen", configurableProvider, "AlgorithmParameterGenerator.SEED");
            outline1172.append("Alg.Alias.AlgorithmParameterGenerator.");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline118(outline1172, KISAObjectIdentifiers.id_seedCBC, configurableProvider, "SEED"), PREFIX, "$ECB", configurableProvider, "Cipher.SEED");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "Cipher", KISAObjectIdentifiers.id_seedCBC, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC")), PREFIX, "$Wrap", configurableProvider, "Cipher.SEEDWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap, "SEEDWRAP");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline120(configurableProvider, "Alg.Alias.Cipher.SEEDKW", "SEEDWRAP"), PREFIX, "$KeyGen", configurableProvider, "KeyGenerator.SEED");
            configurableProvider.addAlgorithm("KeyGenerator", KISAObjectIdentifiers.id_seedCBC, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "KeyGenerator", KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen")), PREFIX, "$KeyFactory", configurableProvider, "SecretKeyFactory.SEED");
            addCMacAlgorithm(configurableProvider, "SEED", GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.SecretKeyFactory", KISAObjectIdentifiers.id_seedCBC, "SEED"), PREFIX, "$CMAC"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen"));
            addGMacAlgorithm(configurableProvider, "SEED", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$GMAC"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen"));
            addPoly1305Algorithm(configurableProvider, "SEED", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Poly1305"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Poly1305KeyGen"));
        }
    }

    /* loaded from: classes4.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.crypto.macs.Poly1305(new SEEDEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-SEED", 256, new Poly1305KeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new SEEDWrapEngine());
        }
    }

    private SEED() {
    }
}
