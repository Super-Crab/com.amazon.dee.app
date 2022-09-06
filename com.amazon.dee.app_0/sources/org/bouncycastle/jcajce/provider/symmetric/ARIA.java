package org.bouncycastle.jcajce.provider.symmetric;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.stats.CodePackage;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.cms.CCMParameters;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.asn1.nsri.NSRIObjectIdentifiers;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.engines.ARIAEngine;
import org.bouncycastle.crypto.engines.ARIAWrapEngine;
import org.bouncycastle.crypto.engines.ARIAWrapPadEngine;
import org.bouncycastle.crypto.engines.RFC3211WrapEngine;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;
import org.bouncycastle.crypto.macs.GMac;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CCMBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.modes.OFBBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.GcmSpecUtil;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;
/* loaded from: classes4.dex */
public final class ARIA {

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
                AlgorithmParameters createParametersInstance = createParametersInstance("ARIA");
                createParametersInstance.init(new IvParameterSpec(bArr));
                return createParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for ARIA parameter generation.");
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "ARIA IV";
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParamsCCM extends BaseAlgorithmParameters {
        private CCMParameters ccmParams;

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() throws IOException {
            return this.ccmParams.getEncoded();
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) throws IOException {
            if (isASN1FormatString(str)) {
                return this.ccmParams.getEncoded();
            }
            throw new IOException("unknown format specified");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (GcmSpecUtil.isGcmSpec(algorithmParameterSpec)) {
                this.ccmParams = CCMParameters.getInstance(GcmSpecUtil.extractGcmParameters(algorithmParameterSpec));
            } else if (algorithmParameterSpec instanceof AEADParameterSpec) {
                AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
                this.ccmParams = new CCMParameters(aEADParameterSpec.getNonce(), aEADParameterSpec.getMacSizeInBits() / 8);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlgorithmParameterSpec class not recognized: ");
                outline107.append(algorithmParameterSpec.getClass().getName());
                throw new InvalidParameterSpecException(outline107.toString());
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) throws IOException {
            this.ccmParams = CCMParameters.getInstance(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) throws IOException {
            if (isASN1FormatString(str)) {
                this.ccmParams = CCMParameters.getInstance(bArr);
                return;
            }
            throw new IOException("unknown format specified");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "CCM";
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == AlgorithmParameterSpec.class || GcmSpecUtil.isGcmSpec(cls)) {
                return GcmSpecUtil.gcmSpecExists() ? GcmSpecUtil.extractGcmSpec(this.ccmParams.toASN1Primitive()) : new AEADParameterSpec(this.ccmParams.getNonce(), this.ccmParams.getIcvLen() * 8);
            } else if (cls == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.ccmParams.getNonce(), this.ccmParams.getIcvLen() * 8);
            } else {
                if (cls != IvParameterSpec.class) {
                    throw new InvalidParameterSpecException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("AlgorithmParameterSpec not recognized: ")));
                }
                return new IvParameterSpec(this.ccmParams.getNonce());
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParamsGCM extends BaseAlgorithmParameters {
        private GCMParameters gcmParams;

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() throws IOException {
            return this.gcmParams.getEncoded();
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) throws IOException {
            if (isASN1FormatString(str)) {
                return this.gcmParams.getEncoded();
            }
            throw new IOException("unknown format specified");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (GcmSpecUtil.isGcmSpec(algorithmParameterSpec)) {
                this.gcmParams = GcmSpecUtil.extractGcmParameters(algorithmParameterSpec);
            } else if (algorithmParameterSpec instanceof AEADParameterSpec) {
                AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
                this.gcmParams = new GCMParameters(aEADParameterSpec.getNonce(), aEADParameterSpec.getMacSizeInBits() / 8);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlgorithmParameterSpec class not recognized: ");
                outline107.append(algorithmParameterSpec.getClass().getName());
                throw new InvalidParameterSpecException(outline107.toString());
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) throws IOException {
            this.gcmParams = GCMParameters.getInstance(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) throws IOException {
            if (isASN1FormatString(str)) {
                this.gcmParams = GCMParameters.getInstance(bArr);
                return;
            }
            throw new IOException("unknown format specified");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return CodePackage.GCM;
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == AlgorithmParameterSpec.class || GcmSpecUtil.isGcmSpec(cls)) {
                return GcmSpecUtil.gcmSpecExists() ? GcmSpecUtil.extractGcmSpec(this.gcmParams.toASN1Primitive()) : new AEADParameterSpec(this.gcmParams.getNonce(), this.gcmParams.getIcvLen() * 8);
            } else if (cls == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.gcmParams.getNonce(), this.gcmParams.getIcvLen() * 8);
            } else {
                if (cls != IvParameterSpec.class) {
                    throw new InvalidParameterSpecException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("AlgorithmParameterSpec not recognized: ")));
                }
                return new IvParameterSpec(this.gcmParams.getNonce());
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new ARIAEngine()), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class CCM extends BaseBlockCipher {
        public CCM() {
            super((AEADBlockCipher) new CCMBlockCipher(new ARIAEngine()), false, 12);
        }
    }

    /* loaded from: classes4.dex */
    public static class CFB extends BaseBlockCipher {
        public CFB() {
            super(new BufferedBlockCipher(new CFBBlockCipher(new ARIAEngine(), 128)), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.ARIA.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public BlockCipher get() {
                    return new ARIAEngine();
                }
            });
        }
    }

    /* loaded from: classes4.dex */
    public static class GCM extends BaseBlockCipher {
        public GCM() {
            super(new GCMBlockCipher(new ARIAEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new GMac(new GCMBlockCipher(new ARIAEngine())));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("ARIA", null);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i) {
            super("ARIA", i, new CipherKeyGenerator());
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
        private static final String PREFIX = ARIA.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$AlgParams", configurableProvider, "AlgorithmParameters.ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NSRIObjectIdentifiers.id_aria128_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NSRIObjectIdentifiers.id_aria192_cbc, "ARIA");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.AlgorithmParameters", NSRIObjectIdentifiers.id_aria256_cbc, "ARIA"), PREFIX, "$AlgParamGen", configurableProvider, "AlgorithmParameterGenerator.ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria128_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria192_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria256_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria128_ofb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria192_ofb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria256_ofb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria128_cfb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria192_cfb, "ARIA");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria256_cfb, "ARIA"), PREFIX, "$ECB", configurableProvider, "Cipher.ARIA");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_ecb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$ECB"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_ecb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$ECB"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_ecb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$ECB"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_cfb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CFB"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_cfb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CFB"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_cfb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CFB"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_ofb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$OFB"));
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_ofb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$OFB"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "Cipher", NSRIObjectIdentifiers.id_aria256_ofb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$OFB")), PREFIX, "$RFC3211Wrap", configurableProvider, "Cipher.ARIARFC3211WRAP"), PREFIX, "$Wrap", configurableProvider, "Cipher.ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_kw, "ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_kw, "ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_kw, "ARIAWRAP");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline120(configurableProvider, "Alg.Alias.Cipher.ARIAKW", "ARIAWRAP"), PREFIX, "$WrapPad", configurableProvider, "Cipher.ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_kwp, "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_kwp, "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_kwp, "ARIAWRAPPAD");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline120(configurableProvider, "Alg.Alias.Cipher.ARIAKWP", "ARIAWRAPPAD"), PREFIX, "$KeyGen", configurableProvider, "KeyGenerator.ARIA");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_kw, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_kw, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_kw, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_kwp, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_kwp, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_kwp, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_ecb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_ecb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_ecb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_cbc, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_cfb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_cfb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_cfb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_ofb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_ofb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_ofb, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_ccm, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_ccm, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_ccm, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_gcm, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_gcm, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "KeyGenerator", NSRIObjectIdentifiers.id_aria256_gcm, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256")), PREFIX, "$KeyFactory", configurableProvider, "SecretKeyFactory.ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", NSRIObjectIdentifiers.id_aria128_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", NSRIObjectIdentifiers.id_aria192_cbc, "ARIA");
            StringBuilder outline117 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.SecretKeyFactory", NSRIObjectIdentifiers.id_aria256_cbc, "ARIA"), PREFIX, "$AlgParamGen", configurableProvider, "AlgorithmParameterGenerator.ARIACCM");
            outline117.append("Alg.Alias.AlgorithmParameterGenerator.");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline118(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline117, NSRIObjectIdentifiers.id_aria128_ccm, configurableProvider, "ARIACCM", "Alg.Alias.AlgorithmParameterGenerator."), NSRIObjectIdentifiers.id_aria192_ccm, configurableProvider, "ARIACCM", "Alg.Alias.AlgorithmParameterGenerator."), NSRIObjectIdentifiers.id_aria256_ccm, configurableProvider, "ARIACCM"), PREFIX, "$CCM", configurableProvider, "Cipher.ARIACCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_ccm, "CCM");
            StringBuilder outline1172 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_ccm, "CCM"), PREFIX, "$AlgParamGen", configurableProvider, "AlgorithmParameterGenerator.ARIAGCM");
            outline1172.append("Alg.Alias.AlgorithmParameterGenerator.");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline118(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline1172, NSRIObjectIdentifiers.id_aria128_gcm, configurableProvider, "ARIAGCM", "Alg.Alias.AlgorithmParameterGenerator."), NSRIObjectIdentifiers.id_aria192_gcm, configurableProvider, "ARIAGCM", "Alg.Alias.AlgorithmParameterGenerator."), NSRIObjectIdentifiers.id_aria256_gcm, configurableProvider, "ARIAGCM"), PREFIX, "$GCM", configurableProvider, "Cipher.ARIAGCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_gcm, "ARIAGCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_gcm, "ARIAGCM");
            addGMacAlgorithm(configurableProvider, "ARIA", GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_gcm, "ARIAGCM"), PREFIX, "$GMAC"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen"));
            addPoly1305Algorithm(configurableProvider, "ARIA", GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Poly1305"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Poly1305KeyGen"));
        }
    }

    /* loaded from: classes4.dex */
    public static class OFB extends BaseBlockCipher {
        public OFB() {
            super(new BufferedBlockCipher(new OFBBlockCipher(new ARIAEngine(), 128)), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.crypto.macs.Poly1305(new ARIAEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-ARIA", 256, new Poly1305KeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class RFC3211Wrap extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new RFC3211WrapEngine(new ARIAEngine()), 16);
        }
    }

    /* loaded from: classes4.dex */
    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new ARIAWrapEngine());
        }
    }

    /* loaded from: classes4.dex */
    public static class WrapPad extends BaseWrapCipher {
        public WrapPad() {
            super(new ARIAWrapPadEngine());
        }
    }

    private ARIA() {
    }
}
