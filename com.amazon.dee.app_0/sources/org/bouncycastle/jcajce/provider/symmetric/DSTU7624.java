package org.bouncycastle.jcajce.provider.symmetric;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.ua.UAObjectIdentifiers;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.engines.DSTU7624Engine;
import org.bouncycastle.crypto.engines.DSTU7624WrapEngine;
import org.bouncycastle.crypto.macs.KGMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.modes.KCCMBlockCipher;
import org.bouncycastle.crypto.modes.KCTRBlockCipher;
import org.bouncycastle.crypto.modes.KGCMBlockCipher;
import org.bouncycastle.crypto.modes.OFBBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
/* loaded from: classes4.dex */
public class DSTU7624 {

    /* loaded from: classes4.dex */
    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        private final int ivLength;

        public AlgParamGen(int i) {
            this.ivLength = i / 8;
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[this.ivLength];
            if (this.random == null) {
                this.random = CryptoServicesRegistrar.getSecureRandom();
            }
            this.random.nextBytes(bArr);
            try {
                AlgorithmParameters createParametersInstance = createParametersInstance("DSTU7624");
                createParametersInstance.init(new IvParameterSpec(bArr));
                return createParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DSTU7624 parameter generation.");
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParamGen128 extends AlgParamGen {
        public AlgParamGen128() {
            super(128);
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParamGen256 extends AlgParamGen {
        public AlgParamGen256() {
            super(256);
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParamGen512 extends AlgParamGen {
        public AlgParamGen512() {
            super(512);
        }
    }

    /* loaded from: classes4.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "DSTU7624 IV";
        }
    }

    /* loaded from: classes4.dex */
    public static class CBC128 extends BaseBlockCipher {
        public CBC128() {
            super(new CBCBlockCipher(new DSTU7624Engine(128)), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class CBC256 extends BaseBlockCipher {
        public CBC256() {
            super(new CBCBlockCipher(new DSTU7624Engine(256)), 256);
        }
    }

    /* loaded from: classes4.dex */
    public static class CBC512 extends BaseBlockCipher {
        public CBC512() {
            super(new CBCBlockCipher(new DSTU7624Engine(512)), 512);
        }
    }

    /* loaded from: classes4.dex */
    public static class CCM128 extends BaseBlockCipher {
        public CCM128() {
            super(new KCCMBlockCipher(new DSTU7624Engine(128)));
        }
    }

    /* loaded from: classes4.dex */
    public static class CCM256 extends BaseBlockCipher {
        public CCM256() {
            super(new KCCMBlockCipher(new DSTU7624Engine(256)));
        }
    }

    /* loaded from: classes4.dex */
    public static class CCM512 extends BaseBlockCipher {
        public CCM512() {
            super(new KCCMBlockCipher(new DSTU7624Engine(512)));
        }
    }

    /* loaded from: classes4.dex */
    public static class CFB128 extends BaseBlockCipher {
        public CFB128() {
            super(new BufferedBlockCipher(new CFBBlockCipher(new DSTU7624Engine(128), 128)), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class CFB256 extends BaseBlockCipher {
        public CFB256() {
            super(new BufferedBlockCipher(new CFBBlockCipher(new DSTU7624Engine(256), 256)), 256);
        }
    }

    /* loaded from: classes4.dex */
    public static class CFB512 extends BaseBlockCipher {
        public CFB512() {
            super(new BufferedBlockCipher(new CFBBlockCipher(new DSTU7624Engine(512), 512)), 512);
        }
    }

    /* loaded from: classes4.dex */
    public static class CTR128 extends BaseBlockCipher {
        public CTR128() {
            super(new BufferedBlockCipher(new KCTRBlockCipher(new DSTU7624Engine(128))), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class CTR256 extends BaseBlockCipher {
        public CTR256() {
            super(new BufferedBlockCipher(new KCTRBlockCipher(new DSTU7624Engine(256))), 256);
        }
    }

    /* loaded from: classes4.dex */
    public static class CTR512 extends BaseBlockCipher {
        public CTR512() {
            super(new BufferedBlockCipher(new KCTRBlockCipher(new DSTU7624Engine(512))), 512);
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.DSTU7624.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public BlockCipher get() {
                    return new DSTU7624Engine(128);
                }
            });
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB128 extends BaseBlockCipher {
        public ECB128() {
            super(new DSTU7624Engine(128));
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB256 extends BaseBlockCipher {
        public ECB256() {
            super(new DSTU7624Engine(256));
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB512 extends BaseBlockCipher {
        public ECB512() {
            super(new DSTU7624Engine(512));
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB_128 extends BaseBlockCipher {
        public ECB_128() {
            super(new DSTU7624Engine(128));
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB_256 extends BaseBlockCipher {
        public ECB_256() {
            super(new DSTU7624Engine(256));
        }
    }

    /* loaded from: classes4.dex */
    public static class ECB_512 extends BaseBlockCipher {
        public ECB_512() {
            super(new DSTU7624Engine(512));
        }
    }

    /* loaded from: classes4.dex */
    public static class GCM128 extends BaseBlockCipher {
        public GCM128() {
            super(new KGCMBlockCipher(new DSTU7624Engine(128)));
        }
    }

    /* loaded from: classes4.dex */
    public static class GCM256 extends BaseBlockCipher {
        public GCM256() {
            super(new KGCMBlockCipher(new DSTU7624Engine(256)));
        }
    }

    /* loaded from: classes4.dex */
    public static class GCM512 extends BaseBlockCipher {
        public GCM512() {
            super(new KGCMBlockCipher(new DSTU7624Engine(512)));
        }
    }

    /* loaded from: classes4.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new KGMac(new KGCMBlockCipher(new DSTU7624Engine(128)), 128));
        }
    }

    /* loaded from: classes4.dex */
    public static class GMAC128 extends BaseMac {
        public GMAC128() {
            super(new KGMac(new KGCMBlockCipher(new DSTU7624Engine(128)), 128));
        }
    }

    /* loaded from: classes4.dex */
    public static class GMAC256 extends BaseMac {
        public GMAC256() {
            super(new KGMac(new KGCMBlockCipher(new DSTU7624Engine(256)), 256));
        }
    }

    /* loaded from: classes4.dex */
    public static class GMAC512 extends BaseMac {
        public GMAC512() {
            super(new KGMac(new KGCMBlockCipher(new DSTU7624Engine(512)), 512));
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i) {
            super("DSTU7624", i, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen128 extends KeyGen {
        public KeyGen128() {
            super(128);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen256 extends KeyGen {
        public KeyGen256() {
            super(256);
        }
    }

    /* loaded from: classes4.dex */
    public static class KeyGen512 extends KeyGen {
        public KeyGen512() {
            super(512);
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends SymmetricAlgorithmProvider {
        private static final String PREFIX = DSTU7624.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$AlgParams", configurableProvider, "AlgorithmParameters.DSTU7624");
            configurableProvider.addAlgorithm("AlgorithmParameters", UAObjectIdentifiers.dstu7624cbc_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$AlgParams"));
            configurableProvider.addAlgorithm("AlgorithmParameters", UAObjectIdentifiers.dstu7624cbc_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$AlgParams"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "AlgorithmParameters", UAObjectIdentifiers.dstu7624cbc_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$AlgParams")), PREFIX, "$AlgParamGen128", configurableProvider, "AlgorithmParameterGenerator.DSTU7624");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator", UAObjectIdentifiers.dstu7624cbc_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$AlgParamGen128"));
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator", UAObjectIdentifiers.dstu7624cbc_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$AlgParamGen256"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline123(configurableProvider, "AlgorithmParameterGenerator", UAObjectIdentifiers.dstu7624cbc_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$AlgParamGen512")), PREFIX, "$ECB_128", configurableProvider, "Cipher.DSTU7624"), PREFIX, "$ECB_128", configurableProvider, "Cipher.DSTU7624-128"), PREFIX, "$ECB_256", configurableProvider, "Cipher.DSTU7624-256"), PREFIX, "$ECB_512", configurableProvider, "Cipher.DSTU7624-512");
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ecb_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$ECB128"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ecb_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$ECB256"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ecb_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$ECB512"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624cbc_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC128"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624cbc_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC256"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624cbc_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CBC512"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ofb_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$OFB128"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ofb_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$OFB256"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ofb_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$OFB512"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624cfb_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CFB128"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624cfb_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CFB256"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624cfb_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CFB512"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ctr_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CTR128"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ctr_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CTR256"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ctr_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CTR512"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ccm_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CCM128"));
            configurableProvider.addAlgorithm("Cipher", UAObjectIdentifiers.dstu7624ccm_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CCM256"));
            GeneratedOutlineSupport1.outline182(GeneratedOutlineSupport1.outline123(configurableProvider, "Cipher", UAObjectIdentifiers.dstu7624ccm_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$CCM512")), PREFIX, "$Wrap", configurableProvider, "Cipher.DSTU7624KW");
            StringBuilder outline117 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline120(configurableProvider, "Alg.Alias.Cipher.DSTU7624WRAP", "DSTU7624KW"), PREFIX, "$Wrap128", configurableProvider, "Cipher.DSTU7624-128KW");
            outline117.append("Alg.Alias.Cipher.");
            outline117.append(UAObjectIdentifiers.dstu7624kw_128.getId());
            configurableProvider.addAlgorithm(outline117.toString(), "DSTU7624-128KW");
            StringBuilder outline1172 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline120(configurableProvider, "Alg.Alias.Cipher.DSTU7624-128WRAP", "DSTU7624-128KW"), PREFIX, "$Wrap256", configurableProvider, "Cipher.DSTU7624-256KW");
            outline1172.append("Alg.Alias.Cipher.");
            outline1172.append(UAObjectIdentifiers.dstu7624kw_256.getId());
            configurableProvider.addAlgorithm(outline1172.toString(), "DSTU7624-256KW");
            StringBuilder outline1173 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline120(configurableProvider, "Alg.Alias.Cipher.DSTU7624-256WRAP", "DSTU7624-256KW"), PREFIX, "$Wrap512", configurableProvider, "Cipher.DSTU7624-512KW");
            outline1173.append("Alg.Alias.Cipher.");
            outline1173.append(UAObjectIdentifiers.dstu7624kw_512.getId());
            configurableProvider.addAlgorithm(outline1173.toString(), "DSTU7624-512KW");
            StringBuilder outline1174 = GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline117(GeneratedOutlineSupport1.outline120(configurableProvider, "Alg.Alias.Cipher.DSTU7624-512WRAP", "DSTU7624-512KW"), PREFIX, "$GMAC", configurableProvider, "Mac.DSTU7624GMAC"), PREFIX, "$GMAC128", configurableProvider, "Mac.DSTU7624-128GMAC");
            outline1174.append("Alg.Alias.Mac.");
            outline1174.append(UAObjectIdentifiers.dstu7624gmac_128.getId());
            configurableProvider.addAlgorithm(outline1174.toString(), "DSTU7624-128GMAC");
            StringBuilder outline1175 = GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$GMAC256", configurableProvider, "Mac.DSTU7624-256GMAC");
            outline1175.append("Alg.Alias.Mac.");
            outline1175.append(UAObjectIdentifiers.dstu7624gmac_256.getId());
            configurableProvider.addAlgorithm(outline1175.toString(), "DSTU7624-256GMAC");
            StringBuilder outline1176 = GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$GMAC512", configurableProvider, "Mac.DSTU7624-512GMAC");
            outline1176.append("Alg.Alias.Mac.");
            outline1176.append(UAObjectIdentifiers.dstu7624gmac_512.getId());
            configurableProvider.addAlgorithm(outline1176.toString(), "DSTU7624-512GMAC");
            GeneratedOutlineSupport1.outline182(new StringBuilder(), PREFIX, "$KeyGen", configurableProvider, "KeyGenerator.DSTU7624");
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624kw_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624kw_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624kw_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen512"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ecb_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ecb_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ecb_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen512"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624cbc_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624cbc_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624cbc_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen512"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ofb_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ofb_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ofb_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen512"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624cfb_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624cfb_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624cfb_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen512"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ctr_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ctr_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ctr_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen512"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ccm_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ccm_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624ccm_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen512"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624gmac_128, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen128"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624gmac_256, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen256"));
            configurableProvider.addAlgorithm("KeyGenerator", UAObjectIdentifiers.dstu7624gmac_512, GeneratedOutlineSupport1.outline91(new StringBuilder(), PREFIX, "$KeyGen512"));
        }
    }

    /* loaded from: classes4.dex */
    public static class OFB128 extends BaseBlockCipher {
        public OFB128() {
            super(new BufferedBlockCipher(new OFBBlockCipher(new DSTU7624Engine(128), 128)), 128);
        }
    }

    /* loaded from: classes4.dex */
    public static class OFB256 extends BaseBlockCipher {
        public OFB256() {
            super(new BufferedBlockCipher(new OFBBlockCipher(new DSTU7624Engine(256), 256)), 256);
        }
    }

    /* loaded from: classes4.dex */
    public static class OFB512 extends BaseBlockCipher {
        public OFB512() {
            super(new BufferedBlockCipher(new OFBBlockCipher(new DSTU7624Engine(512), 512)), 512);
        }
    }

    /* loaded from: classes4.dex */
    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new DSTU7624WrapEngine(128));
        }
    }

    /* loaded from: classes4.dex */
    public static class Wrap128 extends BaseWrapCipher {
        public Wrap128() {
            super(new DSTU7624WrapEngine(128));
        }
    }

    /* loaded from: classes4.dex */
    public static class Wrap256 extends BaseWrapCipher {
        public Wrap256() {
            super(new DSTU7624WrapEngine(256));
        }
    }

    /* loaded from: classes4.dex */
    public static class Wrap512 extends BaseWrapCipher {
        public Wrap512() {
            super(new DSTU7624WrapEngine(512));
        }
    }

    private DSTU7624() {
    }
}
