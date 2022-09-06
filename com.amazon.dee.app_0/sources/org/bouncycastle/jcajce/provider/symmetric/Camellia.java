package org.bouncycastle.jcajce.provider.symmetric;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.engines.CamelliaWrapEngine;
import org.bouncycastle.crypto.engines.RFC3211WrapEngine;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;
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
public final class Camellia {

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
                AlgorithmParameters createParametersInstance = createParametersInstance("Camellia");
                createParametersInstance.init(new IvParameterSpec(bArr));
                return createParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for Camellia parameter generation.");
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "Camellia IV";
        }
    }

    /* loaded from: classes4.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new CamelliaEngine()), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.Camellia.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public BlockCipher get() {
                    return new CamelliaEngine();
                }
            });
        }
    }

    /* loaded from: classes4.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new GMac(new GCMBlockCipher(new CamelliaEngine())));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("Camellia", null);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i) {
            super("Camellia", i, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen128 extends KeyGen {
        public KeyGen128() {
            super(128);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen192 extends KeyGen {
        public KeyGen192() {
            super(192);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen256 extends KeyGen {
        public KeyGen256() {
            super(256);
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends SymmetricAlgorithmProvider {
        private static final String PREFIX = Camellia.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$AlgParams", configurableProvider, "AlgorithmParameters.CAMELLIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NTTObjectIdentifiers.id_camellia128_cbc, "CAMELLIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NTTObjectIdentifiers.id_camellia192_cbc, "CAMELLIA");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.AlgorithmParameters", NTTObjectIdentifiers.id_camellia256_cbc, "CAMELLIA"), PREFIX, "$AlgParamGen", configurableProvider, "AlgorithmParameterGenerator.CAMELLIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NTTObjectIdentifiers.id_camellia128_cbc, "CAMELLIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NTTObjectIdentifiers.id_camellia192_cbc, "CAMELLIA");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.AlgorithmParameterGenerator", NTTObjectIdentifiers.id_camellia256_cbc, "CAMELLIA"), PREFIX, "$ECB", configurableProvider, "Cipher.CAMELLIA");
            configurableProvider.addAlgorithm("Cipher", NTTObjectIdentifiers.id_camellia128_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC"));
            configurableProvider.addAlgorithm("Cipher", NTTObjectIdentifiers.id_camellia192_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "Cipher", NTTObjectIdentifiers.id_camellia256_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC")), PREFIX, "$RFC3211Wrap", configurableProvider, "Cipher.CAMELLIARFC3211WRAP"), PREFIX, "$Wrap", configurableProvider, "Cipher.CAMELLIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NTTObjectIdentifiers.id_camellia128_wrap, "CAMELLIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NTTObjectIdentifiers.id_camellia192_wrap, "CAMELLIAWRAP");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.Cipher", NTTObjectIdentifiers.id_camellia256_wrap, "CAMELLIAWRAP"), PREFIX, "$KeyFactory", configurableProvider, "SecretKeyFactory.CAMELLIA");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", NTTObjectIdentifiers.id_camellia128_cbc, "CAMELLIA");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", NTTObjectIdentifiers.id_camellia192_cbc, "CAMELLIA");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.SecretKeyFactory", NTTObjectIdentifiers.id_camellia256_cbc, "CAMELLIA"), PREFIX, "$KeyGen", configurableProvider, "KeyGenerator.CAMELLIA");
            configurableProvider.addAlgorithm("KeyGenerator", NTTObjectIdentifiers.id_camellia128_wrap, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NTTObjectIdentifiers.id_camellia192_wrap, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NTTObjectIdentifiers.id_camellia256_wrap, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NTTObjectIdentifiers.id_camellia128_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NTTObjectIdentifiers.id_camellia192_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            addGMacAlgorithm(configurableProvider, "CAMELLIA", GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline123(configurableProvider, "KeyGenerator", NTTObjectIdentifiers.id_camellia256_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256")), PREFIX, "$GMAC"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen"));
            addPoly1305Algorithm(configurableProvider, "CAMELLIA", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Poly1305"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Poly1305KeyGen"));
        }
    }

    /* loaded from: classes4.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.crypto.macs.Poly1305(new CamelliaEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-Camellia", 256, new Poly1305KeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class RFC3211Wrap extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new RFC3211WrapEngine(new CamelliaEngine()), 16);
        }
    }

    /* loaded from: classes4.dex */
    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new CamelliaWrapEngine());
        }
    }

    private Camellia() {
    }
}
