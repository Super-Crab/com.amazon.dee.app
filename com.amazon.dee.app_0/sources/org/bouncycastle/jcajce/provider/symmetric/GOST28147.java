package org.bouncycastle.jcajce.provider.symmetric;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.engines.CryptoProWrapEngine;
import org.bouncycastle.crypto.engines.GOST28147Engine;
import org.bouncycastle.crypto.engines.GOST28147WrapEngine;
import org.bouncycastle.crypto.macs.GOST28147Mac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.GCFBBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public final class GOST28147 {
    private static Map<ASN1ObjectIdentifier, String> oidMappings = new HashMap();
    private static Map<String, ASN1ObjectIdentifier> nameMappings = new HashMap();

    /* loaded from: classes4.dex */
    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        byte[] iv = new byte[8];
        byte[] sBox = GOST28147Engine.getSBox("E-A");

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() {
            if (this.random == null) {
                this.random = CryptoServicesRegistrar.getSecureRandom();
            }
            this.random.nextBytes(this.iv);
            try {
                AlgorithmParameters createParametersInstance = createParametersInstance("GOST28147");
                createParametersInstance.init(new GOST28147ParameterSpec(this.sBox, this.iv));
                return createParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
                this.sBox = ((GOST28147ParameterSpec) algorithmParameterSpec).getSBox();
                return;
            }
            throw new InvalidAlgorithmParameterException("parameter spec not supported");
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParams extends BaseAlgParams {
        private byte[] iv;
        private ASN1ObjectIdentifier sBox = CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet;

        @Override // org.bouncycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams, java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.iv = ((IvParameterSpec) algorithmParameterSpec).getIV();
            } else if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
            } else {
                this.iv = ((GOST28147ParameterSpec) algorithmParameterSpec).getIV();
                try {
                    this.sBox = BaseAlgParams.getSBoxOID(((GOST28147ParameterSpec) algorithmParameterSpec).getSBox());
                } catch (IllegalArgumentException e) {
                    throw new InvalidParameterSpecException(e.getMessage());
                }
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "GOST 28147 IV Parameters";
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams, org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.iv);
            }
            if (cls != GOST28147ParameterSpec.class && cls != AlgorithmParameterSpec.class) {
                throw new InvalidParameterSpecException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("AlgorithmParameterSpec not recognized: ")));
            }
            return new GOST28147ParameterSpec(this.sBox, this.iv);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams
        protected byte[] localGetEncoded() throws IOException {
            return new GOST28147Parameters(this.iv, this.sBox).getEncoded();
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.GOST28147.BaseAlgParams
        protected void localInit(byte[] bArr) throws IOException {
            ASN1Primitive fromByteArray = ASN1Primitive.fromByteArray(bArr);
            if (fromByteArray instanceof ASN1OctetString) {
                this.iv = ASN1OctetString.getInstance(fromByteArray).getOctets();
            } else if (!(fromByteArray instanceof ASN1Sequence)) {
                throw new IOException("Unable to recognize parameters");
            } else {
                GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(fromByteArray);
                this.sBox = gOST28147Parameters.getEncryptionParamSet();
                this.iv = gOST28147Parameters.getIV();
            }
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class BaseAlgParams extends BaseAlgorithmParameters {
        private byte[] iv;
        private ASN1ObjectIdentifier sBox = CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet;

        protected static ASN1ObjectIdentifier getSBoxOID(String str) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = str != null ? (ASN1ObjectIdentifier) GOST28147.nameMappings.get(Strings.toUpperCase(str)) : null;
            if (aSN1ObjectIdentifier != null) {
                return aSN1ObjectIdentifier;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unknown SBOX name: ", str));
        }

        protected static ASN1ObjectIdentifier getSBoxOID(byte[] bArr) {
            return getSBoxOID(GOST28147Engine.getSBoxName(bArr));
        }

        @Override // java.security.AlgorithmParametersSpi
        protected final byte[] engineGetEncoded() throws IOException {
            return engineGetEncoded("ASN.1");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected final byte[] engineGetEncoded(String str) throws IOException {
            if (isASN1FormatString(str)) {
                return localGetEncoded();
            }
            throw new IOException(GeneratedOutlineSupport1.outline72("Unknown parameter format: ", str));
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.iv = ((IvParameterSpec) algorithmParameterSpec).getIV();
            } else if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
            } else {
                this.iv = ((GOST28147ParameterSpec) algorithmParameterSpec).getIV();
                try {
                    this.sBox = getSBoxOID(((GOST28147ParameterSpec) algorithmParameterSpec).getSBox());
                } catch (IllegalArgumentException e) {
                    throw new InvalidParameterSpecException(e.getMessage());
                }
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected final void engineInit(byte[] bArr) throws IOException {
            engineInit(bArr, "ASN.1");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected final void engineInit(byte[] bArr, String str) throws IOException {
            if (bArr != null) {
                if (!isASN1FormatString(str)) {
                    throw new IOException(GeneratedOutlineSupport1.outline72("Unknown parameter format: ", str));
                }
                try {
                    localInit(bArr);
                    return;
                } catch (IOException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new IOException(GeneratedOutlineSupport1.outline41(e2, GeneratedOutlineSupport1.outline107("Parameter parsing failed: ")));
                }
            }
            throw new NullPointerException("Encoded parameters cannot be null");
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.iv);
            }
            if (cls != GOST28147ParameterSpec.class && cls != AlgorithmParameterSpec.class) {
                throw new InvalidParameterSpecException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("AlgorithmParameterSpec not recognized: ")));
            }
            return new GOST28147ParameterSpec(this.sBox, this.iv);
        }

        protected byte[] localGetEncoded() throws IOException {
            return new GOST28147Parameters(this.iv, this.sBox).getEncoded();
        }

        abstract void localInit(byte[] bArr) throws IOException;
    }

    /* loaded from: classes4.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new GOST28147Engine()), 64);
        }
    }

    /* loaded from: classes4.dex */
    public static class CryptoProWrap extends BaseWrapCipher {
        public CryptoProWrap() {
            super(new CryptoProWrapEngine());
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new GOST28147Engine());
        }
    }

    /* loaded from: classes4.dex */
    public static class GCFB extends BaseBlockCipher {
        public GCFB() {
            super(new BufferedBlockCipher(new GCFBBlockCipher(new GOST28147Engine())), 64);
        }
    }

    /* loaded from: classes4.dex */
    public static class GostWrap extends BaseWrapCipher {
        public GostWrap() {
            super(new GOST28147WrapEngine());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i) {
            super("GOST28147", i, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mac extends BaseMac {
        public Mac() {
            super(new GOST28147Mac());
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = GOST28147.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$ECB", configurableProvider, "Cipher.GOST28147");
            StringBuilder outline122 = GeneratedOutlineSupport1.outline122(configurableProvider, "Alg.Alias.Cipher.GOST", "GOST28147", "Alg.Alias.Cipher.GOST-28147", "GOST28147");
            outline122.append("Cipher.");
            outline122.append(CryptoProObjectIdentifiers.gostR28147_gcfb);
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$GCFB", configurableProvider, outline122.toString()), PREFIX, "$KeyGen", configurableProvider, "KeyGenerator.GOST28147");
            StringBuilder outline1222 = GeneratedOutlineSupport1.outline122(configurableProvider, "Alg.Alias.KeyGenerator.GOST", "GOST28147", "Alg.Alias.KeyGenerator.GOST-28147", "GOST28147");
            outline1222.append("Alg.Alias.KeyGenerator.");
            StringBuilder outline117 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline118(outline1222, CryptoProObjectIdentifiers.gostR28147_gcfb, configurableProvider, "GOST28147"), PREFIX, "$AlgParams", configurableProvider, "AlgorithmParameters.GOST28147"), PREFIX, "$AlgParamGen", configurableProvider, "AlgorithmParameterGenerator.GOST28147");
            outline117.append("Alg.Alias.AlgorithmParameters.");
            StringBuilder outline119 = GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline117, CryptoProObjectIdentifiers.gostR28147_gcfb, configurableProvider, "GOST28147", "Alg.Alias.AlgorithmParameterGenerator."), CryptoProObjectIdentifiers.gostR28147_gcfb, configurableProvider, "GOST28147", "Cipher.");
            outline119.append(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap);
            StringBuilder outline1172 = GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$CryptoProWrap", configurableProvider, outline119.toString());
            outline1172.append("Cipher.");
            outline1172.append(CryptoProObjectIdentifiers.id_Gost28147_89_None_KeyWrap);
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$GostWrap", configurableProvider, outline1172.toString()), PREFIX, "$Mac", configurableProvider, "Mac.GOST28147MAC");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.GOST28147", "GOST28147MAC");
        }
    }

    static {
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_TestParamSet, "E-TEST");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, "E-A");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet, "E-B");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet, "E-C");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet, "E-D");
        oidMappings.put(RosstandartObjectIdentifiers.id_tc26_gost_28147_param_Z, "PARAM-Z");
        nameMappings.put("E-A", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet);
        nameMappings.put("E-B", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet);
        nameMappings.put("E-C", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet);
        nameMappings.put("E-D", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet);
        nameMappings.put("PARAM-Z", RosstandartObjectIdentifiers.id_tc26_gost_28147_param_Z);
    }

    private GOST28147() {
    }
}
