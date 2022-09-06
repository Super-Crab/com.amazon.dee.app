package org.bouncycastle.openpgp.operator.jcajce;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHKey;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cryptlib.CryptlibObjectIdentifiers;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.bcpg.ECDHPublicBCPGKey;
import org.bouncycastle.bcpg.PublicKeyPacket;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.operator.PGPDataDecryptor;
import org.bouncycastle.openpgp.operator.PGPPad;
import org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.RFC6637Utils;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class JcePublicKeyDataDecryptorFactoryBuilder {
    private static final int X25519_KEY_SIZE = 32;
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private OperatorHelper contentHelper = new OperatorHelper(new DefaultJcaJceHelper());
    private JcaPGPKeyConverter keyConverter = new JcaPGPKeyConverter();
    private JcaKeyFingerprintCalculator fingerprintCalculator = new JcaKeyFingerprintCalculator();

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] decryptSessionData(int i, PrivateKey privateKey, byte[][] bArr) throws PGPException {
        Cipher createPublicKeyCipher = this.helper.createPublicKeyCipher(i);
        try {
            createPublicKeyCipher.init(2, privateKey);
            if (i == 2 || i == 1) {
                byte[] bArr2 = bArr[0];
                createPublicKeyCipher.update(bArr2, 2, bArr2.length - 2);
            } else {
                int bitLength = (((DHKey) privateKey).getParams().getP().bitLength() + 7) / 8;
                byte[] bArr3 = new byte[bitLength];
                byte[] bArr4 = bArr[0];
                if (bArr4.length - 2 > bitLength) {
                    createPublicKeyCipher.update(bArr4, 3, bArr4.length - 3);
                } else {
                    System.arraycopy(bArr4, 2, bArr3, bArr3.length - (bArr4.length - 2), bArr4.length - 2);
                    createPublicKeyCipher.update(bArr3);
                }
                byte[] bArr5 = bArr[1];
                for (int i2 = 0; i2 != bArr3.length; i2++) {
                    bArr3[i2] = 0;
                }
                if (bArr5.length - 2 > bitLength) {
                    createPublicKeyCipher.update(bArr5, 3, bArr5.length - 3);
                } else {
                    System.arraycopy(bArr5, 2, bArr3, bArr3.length - (bArr5.length - 2), bArr5.length - 2);
                    createPublicKeyCipher.update(bArr3);
                }
            }
            try {
                return createPublicKeyCipher.doFinal();
            } catch (Exception e) {
                throw new PGPException("exception decrypting session data", e);
            }
        } catch (InvalidKeyException e2) {
            throw new PGPException("error setting asymmetric cipher", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] decryptSessionData(JcaPGPKeyConverter jcaPGPKeyConverter, PGPPrivateKey pGPPrivateKey, byte[][] bArr) throws PGPException {
        KeyAgreement createKeyAgreement;
        PublicKey publicKey;
        PublicKeyPacket publicKeyPacket = pGPPrivateKey.getPublicKeyPacket();
        ECDHPublicBCPGKey eCDHPublicBCPGKey = (ECDHPublicBCPGKey) publicKeyPacket.getKey();
        byte[] bArr2 = bArr[0];
        int i = ((((bArr2[0] & 255) << 8) + (bArr2[1] & 255)) + 7) / 8;
        int i2 = i + 2;
        int i3 = i2 + 1;
        if (i3 <= bArr2.length) {
            byte[] bArr3 = new byte[i];
            System.arraycopy(bArr2, 2, bArr3, 0, i);
            int i4 = bArr2[i2] & 255;
            if (i3 + i4 > bArr2.length) {
                throw new PGPException("encoded length out of range");
            }
            byte[] bArr4 = new byte[i4];
            System.arraycopy(bArr2, i3, bArr4, 0, i4);
            try {
                if (eCDHPublicBCPGKey.getCurveOID().equals((ASN1Primitive) CryptlibObjectIdentifiers.curvey25519)) {
                    createKeyAgreement = this.helper.createKeyAgreement(RFC6637Utils.getXDHAlgorithm(publicKeyPacket));
                    KeyFactory createKeyFactory = this.helper.createKeyFactory("XDH");
                    if (bArr3.length != 33 || 64 != bArr3[0]) {
                        throw new IllegalArgumentException("Invalid Curve25519 public key");
                    }
                    publicKey = createKeyFactory.generatePublic(new X509EncodedKeySpec(new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_X25519), Arrays.copyOfRange(bArr3, 1, bArr3.length)).getEncoded()));
                } else {
                    ECPoint decodePoint = ECNamedCurveTable.getByOID(eCDHPublicBCPGKey.getCurveOID()).getCurve().decodePoint(bArr3);
                    createKeyAgreement = this.helper.createKeyAgreement(RFC6637Utils.getAgreementAlgorithm(publicKeyPacket));
                    publicKey = jcaPGPKeyConverter.getPublicKey(new PGPPublicKey(new PublicKeyPacket(18, new Date(), new ECDHPublicBCPGKey(eCDHPublicBCPGKey.getCurveOID(), decodePoint, eCDHPublicBCPGKey.getHashAlgorithm(), eCDHPublicBCPGKey.getSymmetricKeyAlgorithm())), this.fingerprintCalculator));
                }
                createKeyAgreement.init(jcaPGPKeyConverter.getPrivateKey(pGPPrivateKey), new UserKeyingMaterialSpec(RFC6637Utils.createUserKeyingMaterial(publicKeyPacket, this.fingerprintCalculator)));
                createKeyAgreement.doPhase(publicKey, true);
                SecretKey generateSecret = createKeyAgreement.generateSecret(RFC6637Utils.getKeyEncryptionOID(eCDHPublicBCPGKey.getSymmetricKeyAlgorithm()).getId());
                Cipher createKeyWrapper = this.helper.createKeyWrapper(eCDHPublicBCPGKey.getSymmetricKeyAlgorithm());
                createKeyWrapper.init(4, generateSecret);
                return PGPPad.unpadSessionData(createKeyWrapper.unwrap(bArr4, MetricsConfiguration.SESSION_ID, 3).getEncoded());
            } catch (IOException e) {
                throw new PGPException("error setting asymmetric cipher", e);
            } catch (InvalidAlgorithmParameterException e2) {
                throw new PGPException("error setting asymmetric cipher", e2);
            } catch (InvalidKeyException e3) {
                throw new PGPException("error setting asymmetric cipher", e3);
            } catch (NoSuchAlgorithmException e4) {
                throw new PGPException("error setting asymmetric cipher", e4);
            } catch (GeneralSecurityException e5) {
                throw new PGPException("error setting asymmetric cipher", e5);
            }
        }
        throw new PGPException("encoded length out of range");
    }

    public PublicKeyDataDecryptorFactory build(final PrivateKey privateKey) {
        return new PublicKeyDataDecryptorFactory() { // from class: org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder.1
            @Override // org.bouncycastle.openpgp.operator.PGPDataDecryptorFactory
            public PGPDataDecryptor createDataDecryptor(boolean z, int i, byte[] bArr) throws PGPException {
                return JcePublicKeyDataDecryptorFactoryBuilder.this.contentHelper.createDataDecryptor(z, i, bArr);
            }

            @Override // org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory
            public byte[] recoverSessionData(int i, byte[][] bArr) throws PGPException {
                if (i != 18) {
                    return JcePublicKeyDataDecryptorFactoryBuilder.this.decryptSessionData(i, privateKey, bArr);
                }
                throw new PGPException("ECDH requires use of PGPPrivateKey for decryption");
            }
        };
    }

    public PublicKeyDataDecryptorFactory build(final PGPPrivateKey pGPPrivateKey) {
        return new PublicKeyDataDecryptorFactory() { // from class: org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder.2
            @Override // org.bouncycastle.openpgp.operator.PGPDataDecryptorFactory
            public PGPDataDecryptor createDataDecryptor(boolean z, int i, byte[] bArr) throws PGPException {
                return JcePublicKeyDataDecryptorFactoryBuilder.this.contentHelper.createDataDecryptor(z, i, bArr);
            }

            @Override // org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory
            public byte[] recoverSessionData(int i, byte[][] bArr) throws PGPException {
                if (i == 18) {
                    JcePublicKeyDataDecryptorFactoryBuilder jcePublicKeyDataDecryptorFactoryBuilder = JcePublicKeyDataDecryptorFactoryBuilder.this;
                    return jcePublicKeyDataDecryptorFactoryBuilder.decryptSessionData(jcePublicKeyDataDecryptorFactoryBuilder.keyConverter, pGPPrivateKey, bArr);
                }
                JcePublicKeyDataDecryptorFactoryBuilder jcePublicKeyDataDecryptorFactoryBuilder2 = JcePublicKeyDataDecryptorFactoryBuilder.this;
                return jcePublicKeyDataDecryptorFactoryBuilder2.decryptSessionData(i, jcePublicKeyDataDecryptorFactoryBuilder2.keyConverter.getPrivateKey(pGPPrivateKey), bArr);
            }
        };
    }

    public JcePublicKeyDataDecryptorFactoryBuilder setContentProvider(String str) {
        this.contentHelper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcePublicKeyDataDecryptorFactoryBuilder setContentProvider(Provider provider) {
        this.contentHelper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePublicKeyDataDecryptorFactoryBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        this.keyConverter.setProvider(str);
        this.contentHelper = this.helper;
        return this;
    }

    public JcePublicKeyDataDecryptorFactoryBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        this.keyConverter.setProvider(provider);
        this.contentHelper = this.helper;
        return this;
    }
}
