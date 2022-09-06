package org.bouncycastle.openpgp.operator.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cryptlib.CryptlibObjectIdentifiers;
import org.bouncycastle.bcpg.ECDHPublicBCPGKey;
import org.bouncycastle.crypto.BufferedAsymmetricBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.agreement.X25519Agreement;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.operator.PGPDataDecryptor;
import org.bouncycastle.openpgp.operator.PGPPad;
import org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.RFC6637Utils;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes5.dex */
public class BcPublicKeyDataDecryptorFactory implements PublicKeyDataDecryptorFactory {
    private static final BcPGPKeyConverter KEY_CONVERTER = new BcPGPKeyConverter();
    private final PGPPrivateKey pgpPrivKey;

    public BcPublicKeyDataDecryptorFactory(PGPPrivateKey pGPPrivateKey) {
        this.pgpPrivKey = pGPPrivateKey;
    }

    @Override // org.bouncycastle.openpgp.operator.PGPDataDecryptorFactory
    public PGPDataDecryptor createDataDecryptor(boolean z, int i, byte[] bArr) throws PGPException {
        return BcUtil.createDataDecryptor(z, BcImplProvider.createBlockCipher(i), bArr);
    }

    @Override // org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory
    public byte[] recoverSessionData(int i, byte[][] bArr) throws PGPException {
        byte[] asUnsignedByteArray;
        try {
            AsymmetricKeyParameter privateKey = KEY_CONVERTER.getPrivateKey(this.pgpPrivKey);
            if (i != 18) {
                BufferedAsymmetricBlockCipher bufferedAsymmetricBlockCipher = new BufferedAsymmetricBlockCipher(BcImplProvider.createPublicKeyCipher(i));
                bufferedAsymmetricBlockCipher.init(false, privateKey);
                if (i != 2 && i != 1) {
                    int bitLength = (((ElGamalPrivateKeyParameters) privateKey).getParameters().getP().bitLength() + 7) / 8;
                    byte[] bArr2 = new byte[bitLength];
                    byte[] bArr3 = bArr[0];
                    if (bArr3.length - 2 > bitLength) {
                        bufferedAsymmetricBlockCipher.processBytes(bArr3, 3, bArr3.length - 3);
                    } else {
                        System.arraycopy(bArr3, 2, bArr2, bArr2.length - (bArr3.length - 2), bArr3.length - 2);
                        bufferedAsymmetricBlockCipher.processBytes(bArr2, 0, bArr2.length);
                    }
                    byte[] bArr4 = bArr[1];
                    for (int i2 = 0; i2 != bArr2.length; i2++) {
                        bArr2[i2] = 0;
                    }
                    if (bArr4.length - 2 > bitLength) {
                        bufferedAsymmetricBlockCipher.processBytes(bArr4, 3, bArr4.length - 3);
                    } else {
                        System.arraycopy(bArr4, 2, bArr2, bArr2.length - (bArr4.length - 2), bArr4.length - 2);
                        bufferedAsymmetricBlockCipher.processBytes(bArr2, 0, bArr2.length);
                    }
                    return bufferedAsymmetricBlockCipher.doFinal();
                }
                byte[] bArr5 = bArr[0];
                bufferedAsymmetricBlockCipher.processBytes(bArr5, 2, bArr5.length - 2);
                return bufferedAsymmetricBlockCipher.doFinal();
            }
            ECDHPublicBCPGKey eCDHPublicBCPGKey = (ECDHPublicBCPGKey) this.pgpPrivKey.getPublicKeyPacket().getKey();
            byte[] bArr6 = bArr[0];
            int i3 = ((((bArr6[0] & 255) << 8) + (bArr6[1] & 255)) + 7) / 8;
            int i4 = i3 + 2;
            int i5 = i4 + 1;
            if (i5 > bArr6.length) {
                throw new PGPException("encoded length out of range");
            }
            byte[] bArr7 = new byte[i3];
            System.arraycopy(bArr6, 2, bArr7, 0, i3);
            int i6 = bArr6[i4] & 255;
            if (i5 + i6 > bArr6.length) {
                throw new PGPException("encoded length out of range");
            }
            byte[] bArr8 = new byte[i6];
            System.arraycopy(bArr6, i5, bArr8, 0, i6);
            if (!eCDHPublicBCPGKey.getCurveOID().equals((ASN1Primitive) CryptlibObjectIdentifiers.curvey25519)) {
                ECDomainParameters parameters = ((ECPrivateKeyParameters) privateKey).getParameters();
                ECPublicKeyParameters eCPublicKeyParameters = new ECPublicKeyParameters(parameters.getCurve().decodePoint(bArr7), parameters);
                ECDHBasicAgreement eCDHBasicAgreement = new ECDHBasicAgreement();
                eCDHBasicAgreement.init(privateKey);
                asUnsignedByteArray = BigIntegers.asUnsignedByteArray(eCDHBasicAgreement.getFieldSize(), eCDHBasicAgreement.calculateAgreement(eCPublicKeyParameters));
            } else if (bArr7.length != 33 || 64 != bArr7[0]) {
                throw new IllegalArgumentException("Invalid Curve25519 public key");
            } else {
                X25519PublicKeyParameters x25519PublicKeyParameters = new X25519PublicKeyParameters(bArr7, 1);
                X25519Agreement x25519Agreement = new X25519Agreement();
                x25519Agreement.init(privateKey);
                asUnsignedByteArray = new byte[x25519Agreement.getAgreementSize()];
                x25519Agreement.calculateAgreement(x25519PublicKeyParameters, asUnsignedByteArray, 0);
            }
            KeyParameter keyParameter = new KeyParameter(new RFC6637KDFCalculator(new BcPGPDigestCalculatorProvider().get(eCDHPublicBCPGKey.getHashAlgorithm()), eCDHPublicBCPGKey.getSymmetricKeyAlgorithm()).createKey(asUnsignedByteArray, RFC6637Utils.createUserKeyingMaterial(this.pgpPrivKey.getPublicKeyPacket(), new BcKeyFingerprintCalculator())));
            Wrapper createWrapper = BcImplProvider.createWrapper(eCDHPublicBCPGKey.getSymmetricKeyAlgorithm());
            createWrapper.init(false, keyParameter);
            return PGPPad.unpadSessionData(createWrapper.unwrap(bArr8, 0, bArr8.length));
        } catch (IOException e) {
            throw new PGPException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception creating user keying material: ")), e);
        } catch (InvalidCipherTextException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception decrypting session info: ");
            outline107.append(e2.getMessage());
            throw new PGPException(outline107.toString(), e2);
        }
    }
}
