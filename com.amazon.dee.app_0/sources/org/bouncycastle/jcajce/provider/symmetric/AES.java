package org.bouncycastle.jcajce.provider.symmetric;

import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.stats.CodePackage;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.cms.CCMParameters;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.AESWrapEngine;
import org.bouncycastle.crypto.engines.AESWrapPadEngine;
import org.bouncycastle.crypto.engines.RFC3211WrapEngine;
import org.bouncycastle.crypto.engines.RFC5649WrapEngine;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;
import org.bouncycastle.crypto.macs.CMac;
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
import org.bouncycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;
/* loaded from: classes4.dex */
public final class AES {
    private static final Map<String, String> generalAesAttributes = new HashMap();

    /* loaded from: classes4.dex */
    public static class AESCCMMAC extends BaseMac {

        /* loaded from: classes4.dex */
        private static class CCMMac implements Mac {
            private final CCMBlockCipher ccm;
            private int macLength;

            private CCMMac() {
                this.ccm = new CCMBlockCipher(new AESEngine());
                this.macLength = 8;
            }

            @Override // org.bouncycastle.crypto.Mac
            public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
                try {
                    return this.ccm.doFinal(bArr, 0);
                } catch (InvalidCipherTextException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception on doFinal(): ");
                    outline107.append(e.toString());
                    throw new IllegalStateException(outline107.toString());
                }
            }

            @Override // org.bouncycastle.crypto.Mac
            public String getAlgorithmName() {
                return this.ccm.getAlgorithmName() + "Mac";
            }

            @Override // org.bouncycastle.crypto.Mac
            public int getMacSize() {
                return this.macLength;
            }

            @Override // org.bouncycastle.crypto.Mac
            public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
                this.ccm.init(true, cipherParameters);
                this.macLength = this.ccm.getMac().length;
            }

            @Override // org.bouncycastle.crypto.Mac
            public void reset() {
                this.ccm.reset();
            }

            @Override // org.bouncycastle.crypto.Mac
            public void update(byte b) throws IllegalStateException {
                this.ccm.processAADByte(b);
            }

            @Override // org.bouncycastle.crypto.Mac
            public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
                this.ccm.processAADBytes(bArr, i, i2);
            }
        }

        public AESCCMMAC() {
            super(new CCMMac());
        }
    }

    /* loaded from: classes4.dex */
    public static class AESCMAC extends BaseMac {
        public AESCMAC() {
            super(new CMac(new AESEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class AESGMAC extends BaseMac {
        public AESGMAC() {
            super(new GMac(new GCMBlockCipher(new AESEngine())));
        }
    }

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
                AlgorithmParameters createParametersInstance = createParametersInstance(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
                createParametersInstance.init(new IvParameterSpec(bArr));
                return createParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for AES parameter generation.");
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParamGenCCM extends BaseAlgorithmParameterGenerator {
        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[12];
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(bArr);
            try {
                AlgorithmParameters createParametersInstance = createParametersInstance("CCM");
                createParametersInstance.init(new CCMParameters(bArr, 12).getEncoded());
                return createParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for AES parameter generation.");
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParamGenGCM extends BaseAlgorithmParameterGenerator {
        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[12];
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(bArr);
            try {
                AlgorithmParameters createParametersInstance = createParametersInstance(CodePackage.GCM);
                createParametersInstance.init(new GCMParameters(bArr, 16).getEncoded());
                return createParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for AES parameter generation.");
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "AES IV";
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
            super(new CBCBlockCipher(new AESEngine()), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class CCM extends BaseBlockCipher {
        public CCM() {
            super((AEADBlockCipher) new CCMBlockCipher(new AESEngine()), false, 12);
        }
    }

    /* loaded from: classes4.dex */
    public static class CFB extends BaseBlockCipher {
        public CFB() {
            super(new BufferedBlockCipher(new CFBBlockCipher(new AESEngine(), 128)), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.AES.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public BlockCipher get() {
                    return new AESEngine();
                }
            });
        }
    }

    /* loaded from: classes4.dex */
    public static class GCM extends BaseBlockCipher {
        public GCM() {
            super(new GCMBlockCipher(new AESEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, null);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(192);
        }

        public KeyGen(int i) {
            super(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, i, new CipherKeyGenerator());
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
        private static final String PREFIX = AES.class.getName();
        private static final String wrongAES128 = "2.16.840.1.101.3.4.2";
        private static final String wrongAES192 = "2.16.840.1.101.3.4.22";
        private static final String wrongAES256 = "2.16.840.1.101.3.4.42";

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$AlgParams", configurableProvider, "AlgorithmParameters.AES");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.2.16.840.1.101.3.4.2", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            StringBuilder outline122 = GeneratedOutlineSupport1.outline122(configurableProvider, "Alg.Alias.AlgorithmParameters.2.16.840.1.101.3.4.22", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, "Alg.Alias.AlgorithmParameters.2.16.840.1.101.3.4.42", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            outline122.append("Alg.Alias.AlgorithmParameters.");
            StringBuilder outline117 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline118(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline122, NISTObjectIdentifiers.id_aes128_CBC, configurableProvider, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, "Alg.Alias.AlgorithmParameters."), NISTObjectIdentifiers.id_aes192_CBC, configurableProvider, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, "Alg.Alias.AlgorithmParameters."), NISTObjectIdentifiers.id_aes256_CBC, configurableProvider, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM), PREFIX, "$AlgParamsGCM", configurableProvider, "AlgorithmParameters.GCM");
            outline117.append("Alg.Alias.AlgorithmParameters.");
            StringBuilder outline1172 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline118(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline117, NISTObjectIdentifiers.id_aes128_GCM, configurableProvider, CodePackage.GCM, "Alg.Alias.AlgorithmParameters."), NISTObjectIdentifiers.id_aes192_GCM, configurableProvider, CodePackage.GCM, "Alg.Alias.AlgorithmParameters."), NISTObjectIdentifiers.id_aes256_GCM, configurableProvider, CodePackage.GCM), PREFIX, "$AlgParamsCCM", configurableProvider, "AlgorithmParameters.CCM");
            outline1172.append("Alg.Alias.AlgorithmParameters.");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline118(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline1172, NISTObjectIdentifiers.id_aes128_CCM, configurableProvider, "CCM", "Alg.Alias.AlgorithmParameters."), NISTObjectIdentifiers.id_aes192_CCM, configurableProvider, "CCM", "Alg.Alias.AlgorithmParameters."), NISTObjectIdentifiers.id_aes256_CCM, configurableProvider, "CCM"), PREFIX, "$AlgParamGen", configurableProvider, "AlgorithmParameterGenerator.AES");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.2.16.840.1.101.3.4.2", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            StringBuilder outline1222 = GeneratedOutlineSupport1.outline122(configurableProvider, "Alg.Alias.AlgorithmParameterGenerator.2.16.840.1.101.3.4.22", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, "Alg.Alias.AlgorithmParameterGenerator.2.16.840.1.101.3.4.42", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            outline1222.append("Alg.Alias.AlgorithmParameterGenerator.");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline1222, NISTObjectIdentifiers.id_aes128_CBC, configurableProvider, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, "Alg.Alias.AlgorithmParameterGenerator."), NISTObjectIdentifiers.id_aes192_CBC, configurableProvider, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, "Alg.Alias.AlgorithmParameterGenerator."), NISTObjectIdentifiers.id_aes256_CBC, configurableProvider, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            configurableProvider.addAttributes("Cipher.AES", AES.generalAesAttributes);
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$ECB", configurableProvider, "Cipher.AES");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.2.16.840.1.101.3.4.2", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.2.16.840.1.101.3.4.22", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.2.16.840.1.101.3.4.42", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes128_ECB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$ECB"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes192_ECB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$ECB"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes256_ECB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$ECB"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes128_CBC, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes192_CBC, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes256_CBC, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes128_OFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$OFB"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes192_OFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$OFB"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes256_OFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$OFB"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes128_CFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CFB"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes192_CFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CFB"));
            configurableProvider.addAlgorithm("Cipher", NISTObjectIdentifiers.id_aes256_CFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CFB"));
            configurableProvider.addAttributes("Cipher.AESWRAP", AES.generalAesAttributes);
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$Wrap", configurableProvider, "Cipher.AESWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes128_wrap, "AESWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes192_wrap, "AESWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes256_wrap, "AESWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.AESKW", "AESWRAP");
            configurableProvider.addAttributes("Cipher.AESWRAPPAD", AES.generalAesAttributes);
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$WrapPad", configurableProvider, "Cipher.AESWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes128_wrap_pad, "AESWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes192_wrap_pad, "AESWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes256_wrap_pad, "AESWRAPPAD");
            StringBuilder outline1173 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline120(configurableProvider, "Alg.Alias.Cipher.AESKWP", "AESWRAPPAD"), PREFIX, "$RFC3211Wrap", configurableProvider, "Cipher.AESRFC3211WRAP"), PREFIX, "$RFC5649Wrap", configurableProvider, "Cipher.AESRFC5649WRAP"), PREFIX, "$AlgParamGenCCM", configurableProvider, "AlgorithmParameterGenerator.CCM");
            outline1173.append("Alg.Alias.AlgorithmParameterGenerator.");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline1173, NISTObjectIdentifiers.id_aes128_CCM, configurableProvider, "CCM", "Alg.Alias.AlgorithmParameterGenerator."), NISTObjectIdentifiers.id_aes192_CCM, configurableProvider, "CCM", "Alg.Alias.AlgorithmParameterGenerator."), NISTObjectIdentifiers.id_aes256_CCM, configurableProvider, "CCM");
            configurableProvider.addAttributes("Cipher.CCM", AES.generalAesAttributes);
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$CCM", configurableProvider, "Cipher.CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes128_CCM, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes192_CCM, "CCM");
            StringBuilder outline1174 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes256_CCM, "CCM"), PREFIX, "$AlgParamGenGCM", configurableProvider, "AlgorithmParameterGenerator.GCM");
            outline1174.append("Alg.Alias.AlgorithmParameterGenerator.");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline1174, NISTObjectIdentifiers.id_aes128_GCM, configurableProvider, CodePackage.GCM, "Alg.Alias.AlgorithmParameterGenerator."), NISTObjectIdentifiers.id_aes192_GCM, configurableProvider, CodePackage.GCM, "Alg.Alias.AlgorithmParameterGenerator."), NISTObjectIdentifiers.id_aes256_GCM, configurableProvider, CodePackage.GCM);
            configurableProvider.addAttributes("Cipher.GCM", AES.generalAesAttributes);
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$GCM", configurableProvider, "Cipher.GCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes128_GCM, CodePackage.GCM);
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes192_GCM, CodePackage.GCM);
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.Cipher", NISTObjectIdentifiers.id_aes256_GCM, CodePackage.GCM), PREFIX, "$KeyGen", configurableProvider, "KeyGenerator.AES"), PREFIX, "$KeyGen128", configurableProvider, "KeyGenerator.2.16.840.1.101.3.4.2"), PREFIX, "$KeyGen192", configurableProvider, "KeyGenerator.2.16.840.1.101.3.4.22"), PREFIX, "$KeyGen256", configurableProvider, "KeyGenerator.2.16.840.1.101.3.4.42");
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes128_ECB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes128_CBC, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes128_OFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes128_CFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes192_ECB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes192_CBC, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes192_OFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes192_CFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes256_ECB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes256_CBC, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes256_OFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "KeyGenerator", NISTObjectIdentifiers.id_aes256_CFB, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256")), PREFIX, "$KeyGen", configurableProvider, "KeyGenerator.AESWRAP");
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes128_wrap, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes192_wrap, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes256_wrap, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes128_GCM, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes192_GCM, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes256_GCM, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes128_CCM, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes192_CCM, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "KeyGenerator", NISTObjectIdentifiers.id_aes256_CCM, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256")), PREFIX, "$KeyGen", configurableProvider, "KeyGenerator.AESWRAPPAD");
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes128_wrap_pad, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", NISTObjectIdentifiers.id_aes192_wrap_pad, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen192"));
            StringBuilder outline1175 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "KeyGenerator", NISTObjectIdentifiers.id_aes256_wrap_pad, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256")), PREFIX, "$AESCMAC", configurableProvider, "Mac.AESCMAC"), PREFIX, "$AESCCMMAC", configurableProvider, "Mac.AESCCMMAC");
            outline1175.append("Alg.Alias.Mac.");
            outline1175.append(NISTObjectIdentifiers.id_aes128_CCM.getId());
            configurableProvider.addAlgorithm(outline1175.toString(), "AESCCMMAC");
            configurableProvider.addAlgorithm("Alg.Alias.Mac." + NISTObjectIdentifiers.id_aes192_CCM.getId(), "AESCCMMAC");
            configurableProvider.addAlgorithm("Alg.Alias.Mac." + NISTObjectIdentifiers.id_aes256_CCM.getId(), "AESCCMMAC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes128_cbc, "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes192_cbc, "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes256_cbc, "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes128_cbc, "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes192_cbc, "PBEWITHSHA256AND192BITAES-CBC-BC");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "Alg.Alias.Cipher", BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc, "PBEWITHSHA256AND256BITAES-CBC-BC"), PREFIX, "$PBEWithSHA1AESCBC128", configurableProvider, "Cipher.PBEWITHSHAAND128BITAES-CBC-BC"), PREFIX, "$PBEWithSHA1AESCBC192", configurableProvider, "Cipher.PBEWITHSHAAND192BITAES-CBC-BC"), PREFIX, "$PBEWithSHA1AESCBC256", configurableProvider, "Cipher.PBEWITHSHAAND256BITAES-CBC-BC"), PREFIX, "$PBEWithSHA256AESCBC128", configurableProvider, "Cipher.PBEWITHSHA256AND128BITAES-CBC-BC"), PREFIX, "$PBEWithSHA256AESCBC192", configurableProvider, "Cipher.PBEWITHSHA256AND192BITAES-CBC-BC"), PREFIX, "$PBEWithSHA256AESCBC256", configurableProvider, "Cipher.PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHAAND128BITAES-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHAAND192BITAES-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHAAND256BITAES-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND128BITAES-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND192BITAES-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND256BITAES-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-1AND128BITAES-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-1AND192BITAES-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-1AND256BITAES-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-256AND128BITAES-CBC-BC", "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-256AND192BITAES-CBC-BC", "PBEWITHSHA256AND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-256AND256BITAES-CBC-BC", "PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA256AND128BITAES-BC", "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA256AND192BITAES-BC", "PBEWITHSHA256AND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA256AND256BITAES-BC", "PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA-256AND128BITAES-BC", "PBEWITHSHA256AND128BITAES-CBC-BC");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline122(configurableProvider, "Alg.Alias.Cipher.PBEWITHSHA-256AND192BITAES-BC", "PBEWITHSHA256AND192BITAES-CBC-BC", "Alg.Alias.Cipher.PBEWITHSHA-256AND256BITAES-BC", "PBEWITHSHA256AND256BITAES-CBC-BC"), PREFIX, "$PBEWithAESCBC", configurableProvider, "Cipher.PBEWITHMD5AND128BITAES-CBC-OPENSSL"), PREFIX, "$PBEWithAESCBC", configurableProvider, "Cipher.PBEWITHMD5AND192BITAES-CBC-OPENSSL"), PREFIX, "$PBEWithAESCBC", configurableProvider, "Cipher.PBEWITHMD5AND256BITAES-CBC-OPENSSL"), PREFIX, "$KeyFactory", configurableProvider, "SecretKeyFactory.AES");
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "SecretKeyFactory", NISTObjectIdentifiers.aes, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyFactory")), PREFIX, "$PBEWithMD5And128BitAESCBCOpenSSL", configurableProvider, "SecretKeyFactory.PBEWITHMD5AND128BITAES-CBC-OPENSSL"), PREFIX, "$PBEWithMD5And192BitAESCBCOpenSSL", configurableProvider, "SecretKeyFactory.PBEWITHMD5AND192BITAES-CBC-OPENSSL"), PREFIX, "$PBEWithMD5And256BitAESCBCOpenSSL", configurableProvider, "SecretKeyFactory.PBEWITHMD5AND256BITAES-CBC-OPENSSL"), PREFIX, "$PBEWithSHAAnd128BitAESBC", configurableProvider, "SecretKeyFactory.PBEWITHSHAAND128BITAES-CBC-BC"), PREFIX, "$PBEWithSHAAnd192BitAESBC", configurableProvider, "SecretKeyFactory.PBEWITHSHAAND192BITAES-CBC-BC"), PREFIX, "$PBEWithSHAAnd256BitAESBC", configurableProvider, "SecretKeyFactory.PBEWITHSHAAND256BITAES-CBC-BC"), PREFIX, "$PBEWithSHA256And128BitAESBC", configurableProvider, "SecretKeyFactory.PBEWITHSHA256AND128BITAES-CBC-BC"), PREFIX, "$PBEWithSHA256And192BitAESBC", configurableProvider, "SecretKeyFactory.PBEWITHSHA256AND192BITAES-CBC-BC"), PREFIX, "$PBEWithSHA256And256BitAESBC", configurableProvider, "SecretKeyFactory.PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND128BITAES-CBC-BC", "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND192BITAES-CBC-BC", "PBEWITHSHA256AND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND256BITAES-CBC-BC", "PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND128BITAES-BC", "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND192BITAES-BC", "PBEWITHSHA256AND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND256BITAES-BC", "PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes128_cbc, "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes192_cbc, "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes256_cbc, "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes128_cbc, "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes192_cbc, "PBEWITHSHA256AND192BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory", BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc, "PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND192BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND256BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND128BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND192BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND256BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND128BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND192BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND256BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND128BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND192BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND256BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND128BITAES-CBC-BC", "PKCS12PBE");
            StringBuilder outline1223 = GeneratedOutlineSupport1.outline122(configurableProvider, "Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND192BITAES-CBC-BC", "PKCS12PBE", "Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND256BITAES-CBC-BC", "PKCS12PBE");
            outline1223.append("Alg.Alias.AlgorithmParameters.");
            outline1223.append(BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes128_cbc.getId());
            configurableProvider.addAlgorithm(outline1223.toString(), "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes192_cbc.getId(), "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes256_cbc.getId(), "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes128_cbc.getId(), "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes192_cbc.getId(), "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc.getId(), "PKCS12PBE");
            addGMacAlgorithm(configurableProvider, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$AESGMAC"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            addPoly1305Algorithm(configurableProvider, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Poly1305"), GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$Poly1305KeyGen"));
        }
    }

    /* loaded from: classes4.dex */
    public static class OFB extends BaseBlockCipher {
        public OFB() {
            super(new BufferedBlockCipher(new OFBBlockCipher(new AESEngine(), 128)), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithAESCBC extends BaseBlockCipher {
        public PBEWithAESCBC() {
            super(new CBCBlockCipher(new AESEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithMD5And128BitAESCBCOpenSSL extends PBESecretKeyFactory {
        public PBEWithMD5And128BitAESCBCOpenSSL() {
            super("PBEWithMD5And128BitAES-CBC-OpenSSL", null, true, 3, 0, 128, 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithMD5And192BitAESCBCOpenSSL extends PBESecretKeyFactory {
        public PBEWithMD5And192BitAESCBCOpenSSL() {
            super("PBEWithMD5And192BitAES-CBC-OpenSSL", null, true, 3, 0, 192, 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithMD5And256BitAESCBCOpenSSL extends PBESecretKeyFactory {
        public PBEWithMD5And256BitAESCBCOpenSSL() {
            super("PBEWithMD5And256BitAES-CBC-OpenSSL", null, true, 3, 0, 256, 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHA1AESCBC128 extends BaseBlockCipher {
        public PBEWithSHA1AESCBC128() {
            super(new CBCBlockCipher(new AESEngine()), 2, 1, 128, 16);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHA1AESCBC192 extends BaseBlockCipher {
        public PBEWithSHA1AESCBC192() {
            super(new CBCBlockCipher(new AESEngine()), 2, 1, 192, 16);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHA1AESCBC256 extends BaseBlockCipher {
        public PBEWithSHA1AESCBC256() {
            super(new CBCBlockCipher(new AESEngine()), 2, 1, 256, 16);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHA256AESCBC128 extends BaseBlockCipher {
        public PBEWithSHA256AESCBC128() {
            super(new CBCBlockCipher(new AESEngine()), 2, 4, 128, 16);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHA256AESCBC192 extends BaseBlockCipher {
        public PBEWithSHA256AESCBC192() {
            super(new CBCBlockCipher(new AESEngine()), 2, 4, 192, 16);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHA256AESCBC256 extends BaseBlockCipher {
        public PBEWithSHA256AESCBC256() {
            super(new CBCBlockCipher(new AESEngine()), 2, 4, 256, 16);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHA256And128BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHA256And128BitAESBC() {
            super("PBEWithSHA256And128BitAES-CBC-BC", null, true, 2, 4, 128, 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHA256And192BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHA256And192BitAESBC() {
            super("PBEWithSHA256And192BitAES-CBC-BC", null, true, 2, 4, 192, 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHA256And256BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHA256And256BitAESBC() {
            super("PBEWithSHA256And256BitAES-CBC-BC", null, true, 2, 4, 256, 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHAAnd128BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHAAnd128BitAESBC() {
            super("PBEWithSHA1And128BitAES-CBC-BC", null, true, 2, 1, 128, 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHAAnd192BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHAAnd192BitAESBC() {
            super("PBEWithSHA1And192BitAES-CBC-BC", null, true, 2, 1, 192, 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class PBEWithSHAAnd256BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHAAnd256BitAESBC() {
            super("PBEWithSHA1And256BitAES-CBC-BC", null, true, 2, 1, 256, 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.crypto.macs.Poly1305(new AESEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-AES", 256, new Poly1305KeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class RFC3211Wrap extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new RFC3211WrapEngine(new AESEngine()), 16);
        }
    }

    /* loaded from: classes4.dex */
    public static class RFC5649Wrap extends BaseWrapCipher {
        public RFC5649Wrap() {
            super(new RFC5649WrapEngine(new AESEngine()));
        }
    }

    /* loaded from: classes4.dex */
    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new AESWrapEngine());
        }
    }

    /* loaded from: classes4.dex */
    public static class WrapPad extends BaseWrapCipher {
        public WrapPad() {
            super(new AESWrapPadEngine());
        }
    }

    static {
        generalAesAttributes.put("SupportedKeyClasses", "javax.crypto.SecretKey");
        generalAesAttributes.put("SupportedKeyFormats", "RAW");
    }

    private AES() {
    }
}
