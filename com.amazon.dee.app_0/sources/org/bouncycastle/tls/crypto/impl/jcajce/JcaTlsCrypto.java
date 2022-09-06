package org.bouncycastle.tls.crypto.impl.jcajce;

import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.amazonaws.services.s3.internal.Constants;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.HashAlgorithm;
import org.bouncycastle.tls.NamedGroup;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.TlsDHUtils;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.SRP6Group;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCryptoException;
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
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.impl.AbstractTlsCrypto;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipher;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
import org.bouncycastle.tls.crypto.impl.TlsBlockCipher;
import org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl;
import org.bouncycastle.tls.crypto.impl.TlsEncryptor;
import org.bouncycastle.tls.crypto.impl.TlsImplUtils;
import org.bouncycastle.tls.crypto.impl.TlsNullCipher;
import org.bouncycastle.tls.crypto.impl.jcajce.srp.SRP6Client;
import org.bouncycastle.tls.crypto.impl.jcajce.srp.SRP6Server;
import org.bouncycastle.tls.crypto.impl.jcajce.srp.SRP6VerifierGenerator;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* loaded from: classes5.dex */
public class JcaTlsCrypto extends AbstractTlsCrypto {
    private final SecureRandom entropySource;
    private final JcaJceHelper helper;
    private final SecureRandom nonceEntropySource;
    private final Hashtable supportedEncryptionAlgorithms = new Hashtable();
    private final Hashtable supportedNamedGroups = new Hashtable();
    private final Hashtable supportedOther = new Hashtable();

    /* JADX INFO: Access modifiers changed from: protected */
    public JcaTlsCrypto(JcaJceHelper jcaJceHelper, SecureRandom secureRandom, SecureRandom secureRandom2) {
        this.helper = jcaJceHelper;
        this.entropySource = secureRandom;
        this.nonceEntropySource = secureRandom2;
    }

    private TlsBlockCipher createAESCipher(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException, GeneralSecurityException {
        return new TlsBlockCipher(this, tlsCryptoParameters, createCBCBlockOperator(tlsCryptoParameters, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, true, i), createCBCBlockOperator(tlsCryptoParameters, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, false, i), createMAC(tlsCryptoParameters, i2), createMAC(tlsCryptoParameters, i2), i);
    }

    private TlsBlockCipher createARIACipher(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException, GeneralSecurityException {
        return new TlsBlockCipher(this, tlsCryptoParameters, createCBCBlockOperator(tlsCryptoParameters, "ARIA", true, i), createCBCBlockOperator(tlsCryptoParameters, "ARIA", false, i), createMAC(tlsCryptoParameters, i2), createMAC(tlsCryptoParameters, i2), i);
    }

    private TlsBlockCipherImpl createCBCBlockOperator(TlsCryptoParameters tlsCryptoParameters, String str, boolean z, int i) throws GeneralSecurityException {
        String outline72 = GeneratedOutlineSupport1.outline72(str, "/CBC/NoPadding");
        return TlsImplUtils.isTLSv11(tlsCryptoParameters) ? createBlockCipher(outline72, str, i, z) : createBlockCipherWithCBCImplicitIV(outline72, str, i, z);
    }

    private TlsBlockCipher createCamelliaCipher(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException, GeneralSecurityException {
        return new TlsBlockCipher(this, tlsCryptoParameters, createCBCBlockOperator(tlsCryptoParameters, "Camellia", true, i), createCBCBlockOperator(tlsCryptoParameters, "Camellia", false, i), createMAC(tlsCryptoParameters, i2), createMAC(tlsCryptoParameters, i2), i);
    }

    private TlsCipher createChaCha20Poly1305(TlsCryptoParameters tlsCryptoParameters) throws IOException, GeneralSecurityException {
        return new TlsAEADCipher(tlsCryptoParameters, new JceChaCha20Poly1305(this.helper, true), new JceChaCha20Poly1305(this.helper, false), 32, 16, 2);
    }

    private TlsAEADCipher createCipher_AES_CCM(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException, GeneralSecurityException {
        return new TlsAEADCipher(tlsCryptoParameters, createAEADCipher("AES/CCM/NoPadding", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, i, true), createAEADCipher("AES/CCM/NoPadding", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, i, false), i, i2, 1);
    }

    private TlsAEADCipher createCipher_AES_GCM(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException, GeneralSecurityException {
        return new TlsAEADCipher(tlsCryptoParameters, createAEADCipher("AES/GCM/NoPadding", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, i, true), createAEADCipher("AES/GCM/NoPadding", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, i, false), i, i2, 3);
    }

    private TlsAEADCipher createCipher_ARIA_GCM(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException, GeneralSecurityException {
        return new TlsAEADCipher(tlsCryptoParameters, createAEADCipher("ARIA/GCM/NoPadding", "ARIA", i, true), createAEADCipher("ARIA/GCM/NoPadding", "ARIA", i, false), i, i2, 3);
    }

    private TlsAEADCipher createCipher_Camellia_GCM(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException, GeneralSecurityException {
        return new TlsAEADCipher(tlsCryptoParameters, createAEADCipher("Camellia/GCM/NoPadding", "Camellia", i, true), createAEADCipher("Camellia/GCM/NoPadding", "Camellia", i, false), i, i2, 3);
    }

    private TlsBlockCipher createDESedeCipher(TlsCryptoParameters tlsCryptoParameters, int i) throws IOException, GeneralSecurityException {
        return new TlsBlockCipher(this, tlsCryptoParameters, createCBCBlockOperator(tlsCryptoParameters, "DESede", true, 24), createCBCBlockOperator(tlsCryptoParameters, "DESede", false, 24), createMAC(tlsCryptoParameters, i), createMAC(tlsCryptoParameters, i), 24);
    }

    private TlsBlockCipher createSEEDCipher(TlsCryptoParameters tlsCryptoParameters, int i) throws IOException, GeneralSecurityException {
        return new TlsBlockCipher(this, tlsCryptoParameters, createCBCBlockOperator(tlsCryptoParameters, "SEED", true, 16), createCBCBlockOperator(tlsCryptoParameters, "SEED", false, 16), createMAC(tlsCryptoParameters, i), createMAC(tlsCryptoParameters, i), 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JceTlsSecret adoptLocalSecret(byte[] bArr) {
        return new JceTlsSecret(this, bArr);
    }

    public byte[] calculateKeyAgreement(String str, PrivateKey privateKey, PublicKey publicKey, String str2) throws GeneralSecurityException {
        KeyAgreement createKeyAgreement = this.helper.createKeyAgreement(str);
        createKeyAgreement.init(privateKey);
        createKeyAgreement.doPhase(publicKey, true);
        try {
            return createKeyAgreement.generateSecret(str2).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            if (!XDHParameterSpec.X25519.equals(str) && !XDHParameterSpec.X448.equals(str)) {
                throw e;
            }
            return createKeyAgreement.generateSecret();
        }
    }

    protected TlsAEADCipherImpl createAEADCipher(String str, String str2, int i, boolean z) throws GeneralSecurityException {
        return new JceAEADCipherImpl(this.helper, str, str2, i, z);
    }

    protected TlsBlockCipherImpl createBlockCipher(String str, String str2, int i, boolean z) throws GeneralSecurityException {
        return new JceBlockCipherImpl(this.helper.createCipher(str), str2, i, z);
    }

    protected TlsBlockCipherImpl createBlockCipherWithCBCImplicitIV(String str, String str2, int i, boolean z) throws GeneralSecurityException {
        return new JceBlockCipherWithCBCImplicitIVImpl(this.helper.createCipher(str), str2, z);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsCertificate createCertificate(byte[] bArr) throws IOException {
        return new JcaTlsCertificate(this, bArr);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsCipher createCipher(TlsCryptoParameters tlsCryptoParameters, int i, int i2) throws IOException {
        try {
            if (i == 0) {
                return createNullCipher(tlsCryptoParameters, i2);
            }
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
        } catch (GeneralSecurityException e) {
            throw new TlsCryptoException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot create cipher: ")), e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsDHDomain createDHDomain(TlsDHConfig tlsDHConfig) {
        return new JceTlsDHDomain(this, tlsDHConfig);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsECDomain createECDomain(TlsECConfig tlsECConfig) {
        int namedGroup = tlsECConfig.getNamedGroup();
        return namedGroup != 29 ? namedGroup != 30 ? new JceTlsECDomain(this, tlsECConfig) : new JceX448Domain(this) : new JceX25519Domain(this);
    }

    @Override // org.bouncycastle.tls.crypto.impl.AbstractTlsCrypto
    public TlsEncryptor createEncryptor(TlsCertificate tlsCertificate) throws IOException {
        JcaTlsCertificate convert = JcaTlsCertificate.convert(this, tlsCertificate);
        convert.validateKeyUsageBit(2);
        final PublicKey pubKeyRSA = convert.getPubKeyRSA();
        return new TlsEncryptor() { // from class: org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto.4
            @Override // org.bouncycastle.tls.crypto.impl.TlsEncryptor
            public byte[] encrypt(byte[] bArr, int i, int i2) throws IOException {
                try {
                    Cipher createRSAEncryptionCipher = JcaTlsCrypto.this.createRSAEncryptionCipher();
                    try {
                        createRSAEncryptionCipher.init(3, pubKeyRSA, JcaTlsCrypto.this.getSecureRandom());
                        return createRSAEncryptionCipher.wrap(new SecretKeySpec(bArr, i, i2, SSLSocketFactoryFactory.DEFAULT_PROTOCOL));
                    } catch (Exception e) {
                        try {
                            createRSAEncryptionCipher.init(1, pubKeyRSA, JcaTlsCrypto.this.getSecureRandom());
                            return createRSAEncryptionCipher.doFinal(bArr, i, i2);
                        } catch (Exception unused) {
                            throw new TlsFatalAlert((short) 80, (Throwable) e);
                        }
                    }
                } catch (GeneralSecurityException e2) {
                    throw new TlsFatalAlert((short) 80, (Throwable) e2);
                }
            }
        };
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsHMAC createHMAC(int i) {
        return createHMAC(TlsUtils.getHashAlgorithmForHMACAlgorithm(i));
    }

    protected TlsHMAC createHMAC(String str) {
        try {
            return new JceTlsHMAC(this.helper.createMac(str), str);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline72("cannot create HMAC: ", str), e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsHMAC createHMAC(short s) {
        return createHMAC(getHMACAlgorithmName(s));
    }

    protected TlsHMAC createHMAC_SSL(int i) throws GeneralSecurityException, IOException {
        if (i != 1) {
            if (i == 2) {
                return new JcaSSL3HMAC(createHash(getDigestName((short) 2)), 20, 64);
            }
            if (i == 3) {
                return new JcaSSL3HMAC(createHash(getDigestName((short) 4)), 32, 64);
            }
            if (i == 4) {
                return new JcaSSL3HMAC(createHash(getDigestName((short) 5)), 48, 128);
            }
            if (i != 5) {
                throw new TlsFatalAlert((short) 80);
            }
            return new JcaSSL3HMAC(createHash(getDigestName((short) 6)), 64, 128);
        }
        return new JcaSSL3HMAC(createHash(getDigestName((short) 1)), 16, 64);
    }

    protected TlsHash createHash(String str) throws GeneralSecurityException {
        return new JcaTlsHash(this.helper.createDigest(str));
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsHash createHash(short s) {
        try {
            return createHash(getDigestName(s));
        } catch (GeneralSecurityException e) {
            throw Exceptions.illegalArgumentException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("unable to create message digest:")), e);
        }
    }

    protected TlsHMAC createMAC(TlsCryptoParameters tlsCryptoParameters, int i) throws GeneralSecurityException, IOException {
        return TlsImplUtils.isSSL(tlsCryptoParameters) ? createHMAC_SSL(i) : createHMAC(i);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsNonceGenerator createNonceGenerator(byte[] bArr) {
        return new JcaNonceGenerator(this.nonceEntropySource, bArr);
    }

    protected TlsNullCipher createNullCipher(TlsCryptoParameters tlsCryptoParameters, int i) throws IOException, GeneralSecurityException {
        return new TlsNullCipher(tlsCryptoParameters, createMAC(tlsCryptoParameters, i), createMAC(tlsCryptoParameters, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher createRSAEncryptionCipher() throws GeneralSecurityException {
        try {
            return getHelper().createCipher("RSA/NONE/PKCS1Padding");
        } catch (GeneralSecurityException unused) {
            return getHelper().createCipher("RSA/ECB/PKCS1Padding");
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSRP6Client createSRP6Client(TlsSRPConfig tlsSRPConfig) {
        final SRP6Client sRP6Client = new SRP6Client();
        BigInteger[] explicitNG = tlsSRPConfig.getExplicitNG();
        sRP6Client.init(new SRP6Group(explicitNG[0], explicitNG[1]), createHash((short) 2), getSecureRandom());
        return new TlsSRP6Client() { // from class: org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto.1
            @Override // org.bouncycastle.tls.crypto.TlsSRP6Client
            public BigInteger calculateSecret(BigInteger bigInteger) throws TlsFatalAlert {
                try {
                    return sRP6Client.calculateSecret(bigInteger);
                } catch (IllegalArgumentException e) {
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
        sRP6Server.init(new SRP6Group(explicitNG[0], explicitNG[1]), bigInteger, createHash((short) 2), getSecureRandom());
        return new TlsSRP6Server() { // from class: org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto.2
            @Override // org.bouncycastle.tls.crypto.TlsSRP6Server
            public BigInteger calculateSecret(BigInteger bigInteger2) throws IOException {
                try {
                    return sRP6Server.calculateSecret(bigInteger2);
                } catch (IllegalArgumentException e) {
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
        sRP6VerifierGenerator.init(explicitNG[0], explicitNG[1], createHash((short) 2));
        return new TlsSRP6VerifierGenerator() { // from class: org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto.3
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

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamSigner createStreamSigner(String str, AlgorithmParameterSpec algorithmParameterSpec, PrivateKey privateKey, boolean z) throws IOException {
        try {
            Signature createSignature = getHelper().createSignature(str);
            if (algorithmParameterSpec != null) {
                createSignature.setParameter(algorithmParameterSpec);
            }
            createSignature.initSign(privateKey, z ? getSecureRandom() : null);
            return new JcaTlsStreamSigner(createSignature);
        } catch (GeneralSecurityException e) {
            throw new TlsFatalAlert((short) 80, (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamSigner createStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, PrivateKey privateKey, boolean z) throws IOException {
        return createStreamSigner(JcaUtils.getJcaAlgorithmName(signatureAndHashAlgorithm), null, privateKey, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamVerifier createStreamVerifier(String str, AlgorithmParameterSpec algorithmParameterSpec, byte[] bArr, PublicKey publicKey) throws IOException {
        try {
            Signature createSignature = getHelper().createSignature(str);
            if (algorithmParameterSpec != null) {
                createSignature.setParameter(algorithmParameterSpec);
            }
            createSignature.initVerify(publicKey);
            return new JcaTlsStreamVerifier(createSignature, bArr);
        } catch (GeneralSecurityException e) {
            throw new TlsFatalAlert((short) 80, (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamVerifier createStreamVerifier(DigitallySigned digitallySigned, PublicKey publicKey) throws IOException {
        return createStreamVerifier(JcaUtils.getJcaAlgorithmName(digitallySigned.getAlgorithm()), null, digitallySigned.getSignature(), publicKey);
    }

    protected TlsStreamSigner createVerifyingStreamSigner(String str, AlgorithmParameterSpec algorithmParameterSpec, PrivateKey privateKey, boolean z, PublicKey publicKey) throws IOException {
        try {
            Signature createSignature = getHelper().createSignature(str);
            Signature createSignature2 = getHelper().createSignature(str);
            if (algorithmParameterSpec != null) {
                createSignature.setParameter(algorithmParameterSpec);
                createSignature2.setParameter(algorithmParameterSpec);
            }
            createSignature.initSign(privateKey, z ? getSecureRandom() : null);
            createSignature2.initVerify(publicKey);
            return new JcaVerifyingStreamSigner(createSignature, createSignature2);
        } catch (GeneralSecurityException e) {
            throw new TlsFatalAlert((short) 80, (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamSigner createVerifyingStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, PrivateKey privateKey, boolean z, PublicKey publicKey) throws IOException {
        return createVerifyingStreamSigner(JcaUtils.getJcaAlgorithmName(signatureAndHashAlgorithm), null, privateKey, z, publicKey);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret generateRSAPreMasterSecret(ProtocolVersion protocolVersion) {
        byte[] bArr = new byte[48];
        getSecureRandom().nextBytes(bArr);
        TlsUtils.writeVersion(protocolVersion, bArr, 0);
        return adoptLocalSecret(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getDigestName(short s) {
        switch (s) {
            case 1:
                return MessageDigestAlgorithms.MD5;
            case 2:
                return "SHA-1";
            case 3:
                return McElieceCCA2KeyGenParameterSpec.SHA224;
            case 4:
                return "SHA-256";
            case 5:
                return "SHA-384";
            case 6:
                return "SHA-512";
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid HashAlgorithm: ");
                outline107.append(HashAlgorithm.getText(s));
                throw new IllegalArgumentException(outline107.toString());
        }
    }

    public String getHMACAlgorithmName(short s) {
        switch (s) {
            case 1:
                return "HmacMD5";
            case 2:
                return Constants.HMAC_SHA1_ALGORITHM;
            case 3:
                return "HmacSHA224";
            case 4:
                return "HmacSHA256";
            case 5:
                return "HmacSHA384";
            case 6:
                return "HmacSHA512";
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid HashAlgorithm: ");
                outline107.append(HashAlgorithm.getText(s));
                throw new IllegalArgumentException(outline107.toString());
        }
    }

    public JcaJceHelper getHelper() {
        return this.helper;
    }

    public AlgorithmParameters getNamedGroupAlgorithmParameters(int i) throws GeneralSecurityException {
        if (NamedGroup.refersToAnXDHCurve(i)) {
            if (i == 29 || i == 30) {
                return null;
            }
        } else if (NamedGroup.refersToAnECDSACurve(i)) {
            return ECUtil.getAlgorithmParameters(this, NamedGroup.getName(i));
        } else {
            if (NamedGroup.refersToASpecificFiniteField(i)) {
                return DHUtil.getAlgorithmParameters(this, TlsDHUtils.getNamedDHGroup(i));
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NamedGroup not supported: ");
        outline107.append(NamedGroup.getText(i));
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public SecureRandom getSecureRandom() {
        return this.entropySource;
    }

    public AlgorithmParameters getSignatureSchemeAlgorithmParameters(int i) throws GeneralSecurityException {
        switch (i) {
            case 2052:
            case 2053:
            case SignatureScheme.rsa_pss_rsae_sha512 /* 2054 */:
            case SignatureScheme.rsa_pss_pss_sha256 /* 2057 */:
            case SignatureScheme.rsa_pss_pss_sha384 /* 2058 */:
            case SignatureScheme.rsa_pss_pss_sha512 /* 2059 */:
                short rSAPSSHashAlgorithm = SignatureScheme.getRSAPSSHashAlgorithm(i);
                String digestName = getDigestName(rSAPSSHashAlgorithm);
                String outline91 = GeneratedOutlineSupport1.outline91(new StringBuilder(), RSAUtil.getDigestSigAlgName(digestName), "WITHRSAANDMGF1");
                AlgorithmParameterSpec pSSParameterSpec = RSAUtil.getPSSParameterSpec(rSAPSSHashAlgorithm, digestName, getHelper());
                Signature createSignature = getHelper().createSignature(outline91);
                createSignature.setParameter(pSSParameterSpec);
                return createSignature.getParameters();
            case SignatureScheme.ed25519 /* 2055 */:
            case SignatureScheme.ed448 /* 2056 */:
            default:
                return null;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasAllRawSignatureAlgorithms() {
        return !JcaUtils.isSunMSCAPIProviderActive() && !hasSignatureAlgorithm((short) 7) && !hasSignatureAlgorithm((short) 8);
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
        Integer valueOf = Integers.valueOf(i);
        synchronized (this.supportedEncryptionAlgorithms) {
            Boolean bool = (Boolean) this.supportedEncryptionAlgorithms.get(valueOf);
            if (bool != null) {
                return bool.booleanValue();
            }
            Boolean isSupportedEncryptionAlgorithm = isSupportedEncryptionAlgorithm(i);
            if (isSupportedEncryptionAlgorithm == null) {
                return false;
            }
            synchronized (this.supportedEncryptionAlgorithms) {
                Boolean bool2 = (Boolean) this.supportedEncryptionAlgorithms.put(valueOf, isSupportedEncryptionAlgorithm);
                if (bool2 != null && isSupportedEncryptionAlgorithm != bool2) {
                    this.supportedEncryptionAlgorithms.put(valueOf, bool2);
                    isSupportedEncryptionAlgorithm = bool2;
                }
            }
            return isSupportedEncryptionAlgorithm.booleanValue();
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
        Integer valueOf = Integers.valueOf(i);
        synchronized (this.supportedNamedGroups) {
            Boolean bool = (Boolean) this.supportedNamedGroups.get(valueOf);
            if (bool != null) {
                return bool.booleanValue();
            }
            Boolean isSupportedNamedGroup = isSupportedNamedGroup(i);
            if (isSupportedNamedGroup == null) {
                return false;
            }
            synchronized (this.supportedNamedGroups) {
                Boolean bool2 = (Boolean) this.supportedNamedGroups.put(valueOf, isSupportedNamedGroup);
                if (bool2 != null && isSupportedNamedGroup != bool2) {
                    this.supportedNamedGroups.put(valueOf, bool2);
                    isSupportedNamedGroup = bool2;
                }
            }
            return isSupportedNamedGroup.booleanValue();
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasRSAEncryption() {
        Boolean bool;
        synchronized (this.supportedOther) {
            Boolean bool2 = (Boolean) this.supportedOther.get("KE_RSA");
            if (bool2 != null) {
                return bool2.booleanValue();
            }
            try {
                createRSAEncryptionCipher();
                bool = Boolean.TRUE;
            } catch (GeneralSecurityException unused) {
                bool = Boolean.FALSE;
            }
            synchronized (this.supportedOther) {
                Boolean bool3 = (Boolean) this.supportedOther.put("KE_RSA", bool);
                if (bool3 != null && bool != bool3) {
                    this.supportedOther.put("KE_RSA", bool3);
                    bool = bool3;
                }
            }
            return bool.booleanValue();
        }
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
        if (signatureAndHashAlgorithm.getHash() != 3 || !JcaUtils.isSunMSCAPIProviderActive()) {
            return hasSignatureAlgorithm(signatureAndHashAlgorithm.getSignature());
        }
        return false;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasSignatureScheme(int i) {
        if ((i >>> 8) != 3 || !JcaUtils.isSunMSCAPIProviderActive()) {
            return hasSignatureAlgorithm((short) (i & 255));
        }
        return false;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret hkdfInit(short s) {
        return adoptLocalSecret(new byte[HashAlgorithm.getOutputSize(s)]);
    }

    protected Boolean isSupportedEncryptionAlgorithm(int i) {
        try {
            switch (i) {
                case 0:
                    return Boolean.TRUE;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return Boolean.FALSE;
                case 7:
                    this.helper.createCipher("DESede/CBC/NoPadding");
                    return Boolean.TRUE;
                case 8:
                case 9:
                    this.helper.createCipher("AES/CBC/NoPadding");
                    return Boolean.TRUE;
                case 10:
                case 11:
                    this.helper.createCipher("AES/GCM/NoPadding");
                    return Boolean.TRUE;
                case 12:
                case 13:
                    this.helper.createCipher("Camellia/CBC/NoPadding");
                    return Boolean.TRUE;
                case 14:
                    this.helper.createCipher("SEED/CBC/NoPadding");
                    return Boolean.TRUE;
                case 15:
                case 16:
                case 17:
                case 18:
                    this.helper.createCipher("AES/CCM/NoPadding");
                    return Boolean.TRUE;
                case 19:
                case 20:
                    this.helper.createCipher("Camellia/GCM/NoPadding");
                    return Boolean.TRUE;
                case 21:
                    this.helper.createCipher("ChaCha7539");
                    this.helper.createMac("Poly1305");
                    return Boolean.TRUE;
                case 22:
                case 23:
                    this.helper.createCipher("ARIA/CBC/NoPadding");
                    return Boolean.TRUE;
                case 24:
                case 25:
                    this.helper.createCipher("ARIA/GCM/NoPadding");
                    return Boolean.TRUE;
                default:
                    return null;
            }
        } catch (GeneralSecurityException unused) {
            return Boolean.FALSE;
        }
    }

    protected Boolean isSupportedNamedGroup(int i) {
        try {
            if (!NamedGroup.refersToAnXDHCurve(i)) {
                if (NamedGroup.refersToAnECDSACurve(i)) {
                    return Boolean.valueOf(ECUtil.isCurveSupported(this, NamedGroup.getName(i)));
                }
                if (!NamedGroup.refersToASpecificFiniteField(i)) {
                    return null;
                }
                return Boolean.TRUE;
            } else if (i == 29) {
                this.helper.createKeyAgreement(XDHParameterSpec.X25519);
                return Boolean.TRUE;
            } else if (i != 30) {
                return null;
            } else {
                this.helper.createKeyAgreement(XDHParameterSpec.X448);
                return Boolean.TRUE;
            }
        } catch (GeneralSecurityException unused) {
            return Boolean.FALSE;
        }
    }
}
