package org.bouncycastle.tls.crypto.impl.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.bouncycastle.crypto.agreement.srp.SRP6Server;
import org.bouncycastle.crypto.agreement.srp.SRP6VerifierGenerator;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.ARIAEngine;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.engines.SEEDEngine;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CCMBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.SRP6GroupParameters;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.tls.HashAlgorithm;
import org.bouncycastle.tls.NamedGroup;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsDHDomain;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsECDomain;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.tls.crypto.TlsNonceGenerator;
import org.bouncycastle.tls.crypto.TlsSRP6Client;
import org.bouncycastle.tls.crypto.TlsSRP6Server;
import org.bouncycastle.tls.crypto.TlsSRP6VerifierGenerator;
import org.bouncycastle.tls.crypto.TlsSRPConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.impl.AbstractTlsCrypto;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipher;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
import org.bouncycastle.tls.crypto.impl.TlsBlockCipher;
import org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl;
import org.bouncycastle.tls.crypto.impl.TlsEncryptor;
import org.bouncycastle.tls.crypto.impl.TlsImplUtils;
import org.bouncycastle.tls.crypto.impl.TlsNullCipher;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class BcTlsCrypto extends AbstractTlsCrypto {
    private final SecureRandom entropySource;

    /* loaded from: classes5.dex */
    public class AeadOperator implements TlsAEADCipherImpl {
        private final AEADBlockCipher cipher;
        private final boolean isEncrypting;
        private KeyParameter key;

        public AeadOperator(AEADBlockCipher aEADBlockCipher, boolean z) {
            this.cipher = aEADBlockCipher;
            this.isEncrypting = z;
        }

        @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
        public int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, byte[] bArr3, int i3) throws IOException {
            int processBytes = this.cipher.processBytes(bArr, i, i2, bArr3, i3);
            int length = bArr2.length;
            if (length > 0) {
                if (!this.isEncrypting) {
                    throw new TlsFatalAlert((short) 80);
                }
                processBytes += this.cipher.processBytes(bArr2, 0, length, bArr3, i3 + processBytes);
            }
            try {
                return processBytes + this.cipher.doFinal(bArr3, i3 + processBytes);
            } catch (InvalidCipherTextException e) {
                throw new RuntimeCryptoException(e.toString());
            }
        }

        @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
        public int getOutputSize(int i) {
            return this.cipher.getOutputSize(i);
        }

        @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
        public void init(byte[] bArr, int i, byte[] bArr2) {
            this.cipher.init(this.isEncrypting, new AEADParameters(this.key, i * 8, bArr, bArr2));
        }

        @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
        public void setKey(byte[] bArr, int i, int i2) {
            this.key = new KeyParameter(bArr, i, i2);
        }
    }

    /* loaded from: classes5.dex */
    private static class BcTlsHash implements TlsHash {
        private final Digest digest;
        private final short hashAlgorithm;

        BcTlsHash(short s, Digest digest) {
            this.hashAlgorithm = s;
            this.digest = digest;
        }

        @Override // org.bouncycastle.tls.crypto.TlsHash
        public byte[] calculateHash() {
            byte[] bArr = new byte[this.digest.getDigestSize()];
            this.digest.doFinal(bArr, 0);
            return bArr;
        }

        @Override // org.bouncycastle.tls.crypto.TlsHash
        public Object clone() {
            short s = this.hashAlgorithm;
            return new BcTlsHash(s, BcTlsCrypto.cloneDigest(s, this.digest));
        }

        @Override // org.bouncycastle.tls.crypto.TlsHash
        public void reset() {
            this.digest.reset();
        }

        @Override // org.bouncycastle.tls.crypto.TlsHash
        public void update(byte[] bArr, int i, int i2) {
            this.digest.update(bArr, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class BlockOperator implements TlsBlockCipherImpl {
        private final BlockCipher cipher;
        private final boolean isEncrypting;
        private KeyParameter key;

        BlockOperator(BlockCipher blockCipher, boolean z) {
            this.cipher = blockCipher;
            this.isEncrypting = z;
        }

        @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
        public int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            int blockSize = this.cipher.getBlockSize();
            for (int i4 = 0; i4 < i2; i4 += blockSize) {
                this.cipher.processBlock(bArr, i + i4, bArr2, i3 + i4);
            }
            return i2;
        }

        @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
        public int getBlockSize() {
            return this.cipher.getBlockSize();
        }

        @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
        public void init(byte[] bArr, int i, int i2) {
            this.cipher.init(this.isEncrypting, new ParametersWithIV(null, bArr, i, i2));
        }

        @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
        public void setKey(byte[] bArr, int i, int i2) {
            this.key = new KeyParameter(bArr, i, i2);
            BlockCipher blockCipher = this.cipher;
            blockCipher.init(this.isEncrypting, new ParametersWithIV(this.key, new byte[blockCipher.getBlockSize()]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class HMacOperator implements TlsHMAC {
        private final HMac hmac;

        HMacOperator(Digest digest) {
            this.hmac = new HMac(digest);
        }

        @Override // org.bouncycastle.tls.crypto.TlsMAC
        public void calculateMAC(byte[] bArr, int i) {
            this.hmac.doFinal(bArr, i);
        }

        @Override // org.bouncycastle.tls.crypto.TlsMAC
        public byte[] calculateMAC() {
            byte[] bArr = new byte[this.hmac.getMacSize()];
            this.hmac.doFinal(bArr, 0);
            return bArr;
        }

        @Override // org.bouncycastle.tls.crypto.TlsHMAC
        public int getInternalBlockSize() {
            return ((ExtendedDigest) this.hmac.getUnderlyingDigest()).getByteLength();
        }

        @Override // org.bouncycastle.tls.crypto.TlsMAC
        public int getMacLength() {
            return this.hmac.getMacSize();
        }

        @Override // org.bouncycastle.tls.crypto.TlsMAC
        public void reset() {
            this.hmac.reset();
        }

        @Override // org.bouncycastle.tls.crypto.TlsMAC
        public void setKey(byte[] bArr, int i, int i2) {
            this.hmac.init(new KeyParameter(bArr, i, i2));
        }

        @Override // org.bouncycastle.tls.crypto.TlsMAC
        public void update(byte[] bArr, int i, int i2) {
            this.hmac.update(bArr, i, i2);
        }
    }

    public BcTlsCrypto(SecureRandom secureRandom) {
        this.entropySource = secureRandom;
    }

    public static Digest cloneDigest(short s, Digest digest) {
        switch (s) {
            case 1:
                return new MD5Digest((MD5Digest) digest);
            case 2:
                return new SHA1Digest((SHA1Digest) digest);
            case 3:
                return new SHA224Digest((SHA224Digest) digest);
            case 4:
                return new SHA256Digest((SHA256Digest) digest);
            case 5:
                return new SHA384Digest((SHA384Digest) digest);
            case 6:
                return new SHA512Digest((SHA512Digest) digest);
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid HashAlgorithm: ");
                outline107.append(HashAlgorithm.getText(s));
                throw new IllegalArgumentException(outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BcTlsSecret adoptLocalSecret(byte[] bArr) {
        return new BcTlsSecret(this, bArr);
    }

    protected AEADBlockCipher createAEADBlockCipher_AES_CCM() {
        return new CCMBlockCipher(createAESEngine());
    }

    protected AEADBlockCipher createAEADBlockCipher_AES_GCM() {
        return new GCMBlockCipher(createAESEngine());
    }

    protected AEADBlockCipher createAEADBlockCipher_ARIA_GCM() {
        return new GCMBlockCipher(createARIAEngine());
    }

    protected AEADBlockCipher createAEADBlockCipher_Camellia_GCM() {
        return new GCMBlockCipher(createCamelliaEngine());
    }

    protected BlockCipher createAESBlockCipher() {
        return new CBCBlockCipher(createAESEngine());
    }

    protected TlsCipher createAESCipher(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException {
        return new TlsBlockCipher(this, tlsCryptoParameters, new BlockOperator(createAESBlockCipher(), true), new BlockOperator(createAESBlockCipher(), false), createMAC(tlsCryptoParameters, i2), createMAC(tlsCryptoParameters, i2), i);
    }

    protected BlockCipher createAESEngine() {
        return new AESEngine();
    }

    protected BlockCipher createARIABlockCipher() {
        return new CBCBlockCipher(createARIAEngine());
    }

    protected TlsCipher createARIACipher(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException {
        return new TlsBlockCipher(this, tlsCryptoParameters, new BlockOperator(createARIABlockCipher(), true), new BlockOperator(createARIABlockCipher(), false), createMAC(tlsCryptoParameters, i2), createMAC(tlsCryptoParameters, i2), i);
    }

    protected BlockCipher createARIAEngine() {
        return new ARIAEngine();
    }

    protected BlockCipher createCamelliaBlockCipher() {
        return new CBCBlockCipher(createCamelliaEngine());
    }

    protected TlsCipher createCamelliaCipher(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException {
        return new TlsBlockCipher(this, tlsCryptoParameters, new BlockOperator(createCamelliaBlockCipher(), true), new BlockOperator(createCamelliaBlockCipher(), false), createMAC(tlsCryptoParameters, i2), createMAC(tlsCryptoParameters, i2), i);
    }

    protected BlockCipher createCamelliaEngine() {
        return new CamelliaEngine();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsCertificate createCertificate(byte[] bArr) throws IOException {
        return new BcTlsCertificate(this, bArr);
    }

    protected TlsCipher createChaCha20Poly1305(TlsCryptoParameters tlsCryptoParameters) throws IOException {
        return new TlsAEADCipher(tlsCryptoParameters, new BcChaCha20Poly1305(true), new BcChaCha20Poly1305(false), 32, 16, 2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsCipher createCipher(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException {
        if (i != 0) {
            switch (i) {
                case 7:
                    return createDESedeCipher(tlsCryptoParameters, i2);
                case 8:
                    return createAESCipher(tlsCryptoParameters, 16, i2);
                case 9:
                    return createAESCipher(tlsCryptoParameters, 32, i2);
                case 10:
                    return createCipher_AES_GCM(tlsCryptoParameters, 16, 16);
                case 11:
                    return createCipher_AES_GCM(tlsCryptoParameters, 32, 16);
                case 12:
                    return createCamelliaCipher(tlsCryptoParameters, 16, i2);
                case 13:
                    return createCamelliaCipher(tlsCryptoParameters, 32, i2);
                case 14:
                    return createSEEDCipher(tlsCryptoParameters, i2);
                case 15:
                    return createCipher_AES_CCM(tlsCryptoParameters, 16, 16);
                case 16:
                    return createCipher_AES_CCM(tlsCryptoParameters, 16, 8);
                case 17:
                    return createCipher_AES_CCM(tlsCryptoParameters, 32, 16);
                case 18:
                    return createCipher_AES_CCM(tlsCryptoParameters, 32, 8);
                case 19:
                    return createCipher_Camellia_GCM(tlsCryptoParameters, 16, 16);
                case 20:
                    return createCipher_Camellia_GCM(tlsCryptoParameters, 32, 16);
                case 21:
                    return createChaCha20Poly1305(tlsCryptoParameters);
                case 22:
                    return createARIACipher(tlsCryptoParameters, 16, i2);
                case 23:
                    return createARIACipher(tlsCryptoParameters, 32, i2);
                case 24:
                    return createCipher_ARIA_GCM(tlsCryptoParameters, 16, 16);
                case 25:
                    return createCipher_ARIA_GCM(tlsCryptoParameters, 32, 16);
                default:
                    throw new TlsFatalAlert((short) 80);
            }
        }
        return createNullCipher(tlsCryptoParameters, i2);
    }

    protected TlsAEADCipher createCipher_AES_CCM(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException {
        return new TlsAEADCipher(tlsCryptoParameters, new AeadOperator(createAEADBlockCipher_AES_CCM(), true), new AeadOperator(createAEADBlockCipher_AES_CCM(), false), i, i2, 1);
    }

    protected TlsAEADCipher createCipher_AES_GCM(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException {
        return new TlsAEADCipher(tlsCryptoParameters, new AeadOperator(createAEADBlockCipher_AES_GCM(), true), new AeadOperator(createAEADBlockCipher_AES_GCM(), false), i, i2, 3);
    }

    protected TlsAEADCipher createCipher_ARIA_GCM(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException {
        return new TlsAEADCipher(tlsCryptoParameters, new AeadOperator(createAEADBlockCipher_ARIA_GCM(), true), new AeadOperator(createAEADBlockCipher_ARIA_GCM(), false), i, i2, 3);
    }

    protected TlsAEADCipher createCipher_Camellia_GCM(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException {
        return new TlsAEADCipher(tlsCryptoParameters, new AeadOperator(createAEADBlockCipher_Camellia_GCM(), true), new AeadOperator(createAEADBlockCipher_Camellia_GCM(), false), i, i2, 3);
    }

    protected BlockCipher createDESedeBlockCipher() {
        return new CBCBlockCipher(new DESedeEngine());
    }

    protected TlsBlockCipher createDESedeCipher(TlsCryptoParameters tlsCryptoParameters, int i) throws IOException {
        return new TlsBlockCipher(this, tlsCryptoParameters, new BlockOperator(createDESedeBlockCipher(), true), new BlockOperator(createDESedeBlockCipher(), false), createMAC(tlsCryptoParameters, i), createMAC(tlsCryptoParameters, i), 24);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsDHDomain createDHDomain(TlsDHConfig tlsDHConfig) {
        return new BcTlsDHDomain(this, tlsDHConfig);
    }

    public Digest createDigest(short s) {
        switch (s) {
            case 0:
                return new NullDigest();
            case 1:
                return new MD5Digest();
            case 2:
                return new SHA1Digest();
            case 3:
                return new SHA224Digest();
            case 4:
                return new SHA256Digest();
            case 5:
                return new SHA384Digest();
            case 6:
                return new SHA512Digest();
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid HashAlgorithm: ");
                outline107.append(HashAlgorithm.getText(s));
                throw new IllegalArgumentException(outline107.toString());
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsECDomain createECDomain(TlsECConfig tlsECConfig) {
        int namedGroup = tlsECConfig.getNamedGroup();
        return namedGroup != 29 ? namedGroup != 30 ? new BcTlsECDomain(this, tlsECConfig) : new BcX448Domain(this) : new BcX25519Domain(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.crypto.impl.AbstractTlsCrypto
    public TlsEncryptor createEncryptor(TlsCertificate tlsCertificate) throws IOException {
        BcTlsCertificate convert = BcTlsCertificate.convert(this, tlsCertificate);
        convert.validateKeyUsage(32);
        final RSAKeyParameters pubKeyRSA = convert.getPubKeyRSA();
        return new TlsEncryptor() { // from class: org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto.1
            @Override // org.bouncycastle.tls.crypto.impl.TlsEncryptor
            public byte[] encrypt(byte[] bArr, int i, int i2) throws IOException {
                try {
                    PKCS1Encoding pKCS1Encoding = new PKCS1Encoding(new RSABlindedEngine());
                    pKCS1Encoding.init(true, new ParametersWithRandom(pubKeyRSA, BcTlsCrypto.this.getSecureRandom()));
                    return pKCS1Encoding.processBlock(bArr, i, i2);
                } catch (InvalidCipherTextException e) {
                    throw new TlsFatalAlert((short) 80, (Throwable) e);
                }
            }
        };
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsHMAC createHMAC(int i) {
        return createHMAC(TlsUtils.getHashAlgorithmForHMACAlgorithm(i));
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsHMAC createHMAC(short s) {
        return new HMacOperator(createDigest(s));
    }

    protected TlsHMAC createHMAC_SSL(int i) throws IOException {
        if (i != 1) {
            if (i == 2) {
                return new BcSSL3HMAC(createDigest((short) 2));
            }
            if (i == 3) {
                return new BcSSL3HMAC(createDigest((short) 4));
            }
            if (i == 4) {
                return new BcSSL3HMAC(createDigest((short) 5));
            }
            if (i != 5) {
                throw new TlsFatalAlert((short) 80);
            }
            return new BcSSL3HMAC(createDigest((short) 6));
        }
        return new BcSSL3HMAC(createDigest((short) 1));
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsHash createHash(short s) {
        return new BcTlsHash(s, createDigest(s));
    }

    protected TlsHMAC createMAC(TlsCryptoParameters tlsCryptoParameters, int i) throws IOException {
        return TlsImplUtils.isSSL(tlsCryptoParameters) ? createHMAC_SSL(i) : createHMAC(i);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsNonceGenerator createNonceGenerator(byte[] bArr) {
        final DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(createDigest((short) 4));
        if (bArr != null && bArr.length > 0) {
            digestRandomGenerator.addSeedMaterial(bArr);
        }
        byte[] bArr2 = new byte[createDigest((short) 4).getDigestSize()];
        this.entropySource.nextBytes(bArr2);
        digestRandomGenerator.addSeedMaterial(bArr2);
        return new TlsNonceGenerator() { // from class: org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto.2
            @Override // org.bouncycastle.tls.crypto.TlsNonceGenerator
            public byte[] generateNonce(int i) {
                byte[] bArr3 = new byte[i];
                digestRandomGenerator.nextBytes(bArr3);
                return bArr3;
            }
        };
    }

    protected TlsNullCipher createNullCipher(TlsCryptoParameters tlsCryptoParameters, int i) throws IOException {
        return new TlsNullCipher(tlsCryptoParameters, createMAC(tlsCryptoParameters, i), createMAC(tlsCryptoParameters, i));
    }

    protected StreamCipher createRC4StreamCipher() {
        return new RC4Engine();
    }

    protected BlockCipher createSEEDBlockCipher() {
        return new CBCBlockCipher(new SEEDEngine());
    }

    protected TlsBlockCipher createSEEDCipher(TlsCryptoParameters tlsCryptoParameters, int i) throws IOException {
        return new TlsBlockCipher(this, tlsCryptoParameters, new BlockOperator(createSEEDBlockCipher(), true), new BlockOperator(createSEEDBlockCipher(), false), createMAC(tlsCryptoParameters, i), createMAC(tlsCryptoParameters, i), 16);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSRP6Client createSRP6Client(TlsSRPConfig tlsSRPConfig) {
        final SRP6Client sRP6Client = new SRP6Client();
        BigInteger[] explicitNG = tlsSRPConfig.getExplicitNG();
        sRP6Client.init(new SRP6GroupParameters(explicitNG[0], explicitNG[1]), new SHA1Digest(), getSecureRandom());
        return new TlsSRP6Client() { // from class: org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto.3
            @Override // org.bouncycastle.tls.crypto.TlsSRP6Client
            public BigInteger calculateSecret(BigInteger bigInteger) throws TlsFatalAlert {
                try {
                    return sRP6Client.calculateSecret(bigInteger);
                } catch (CryptoException e) {
                    throw new TlsFatalAlert((short) 47, (Throwable) e);
                }
            }

            @Override // org.bouncycastle.tls.crypto.TlsSRP6Client
            public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
                return sRP6Client.generateClientCredentials(bArr, bArr2, bArr3);
            }
        };
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSRP6Server createSRP6Server(TlsSRPConfig tlsSRPConfig, BigInteger bigInteger) {
        final SRP6Server sRP6Server = new SRP6Server();
        BigInteger[] explicitNG = tlsSRPConfig.getExplicitNG();
        sRP6Server.init(new SRP6GroupParameters(explicitNG[0], explicitNG[1]), bigInteger, new SHA1Digest(), getSecureRandom());
        return new TlsSRP6Server() { // from class: org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto.4
            @Override // org.bouncycastle.tls.crypto.TlsSRP6Server
            public BigInteger calculateSecret(BigInteger bigInteger2) throws IOException {
                try {
                    return sRP6Server.calculateSecret(bigInteger2);
                } catch (CryptoException e) {
                    throw new TlsFatalAlert((short) 47, (Throwable) e);
                }
            }

            @Override // org.bouncycastle.tls.crypto.TlsSRP6Server
            public BigInteger generateServerCredentials() {
                return sRP6Server.generateServerCredentials();
            }
        };
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSRP6VerifierGenerator createSRP6VerifierGenerator(TlsSRPConfig tlsSRPConfig) {
        BigInteger[] explicitNG = tlsSRPConfig.getExplicitNG();
        final SRP6VerifierGenerator sRP6VerifierGenerator = new SRP6VerifierGenerator();
        sRP6VerifierGenerator.init(explicitNG[0], explicitNG[1], new SHA1Digest());
        return new TlsSRP6VerifierGenerator() { // from class: org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto.5
            @Override // org.bouncycastle.tls.crypto.TlsSRP6VerifierGenerator
            public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
                return sRP6VerifierGenerator.generateVerifier(bArr, bArr2, bArr3);
            }
        };
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret createSecret(byte[] bArr) {
        return adoptLocalSecret(Arrays.clone(bArr));
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret generateRSAPreMasterSecret(ProtocolVersion protocolVersion) {
        byte[] bArr = new byte[48];
        this.entropySource.nextBytes(bArr);
        TlsUtils.writeVersion(protocolVersion, bArr, 0);
        return adoptLocalSecret(bArr);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public SecureRandom getSecureRandom() {
        return this.entropySource;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasAllRawSignatureAlgorithms() {
        return !hasSignatureAlgorithm((short) 7) && !hasSignatureAlgorithm((short) 8);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasDHAgreement() {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasECDHAgreement() {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasEncryptionAlgorithm(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return false;
            default:
                return true;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasHashAlgorithm(short s) {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasMacAlgorithm(int i) {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasNamedGroup(int i) {
        return NamedGroup.refersToASpecificGroup(i);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasRSAEncryption() {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasSRPAuthentication() {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasSignatureAlgorithm(short s) {
        switch (s) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return true;
            default:
                return false;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasSignatureAndHashAlgorithm(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return hasSignatureAlgorithm(signatureAndHashAlgorithm.getSignature());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasSignatureScheme(int i) {
        return hasSignatureAlgorithm((short) (i & 255));
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret hkdfInit(short s) {
        return adoptLocalSecret(new byte[HashAlgorithm.getOutputSize(s)]);
    }
}
