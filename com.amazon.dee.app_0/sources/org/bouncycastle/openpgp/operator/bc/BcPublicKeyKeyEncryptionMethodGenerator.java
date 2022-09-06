package org.bouncycastle.openpgp.operator.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cryptlib.CryptlibObjectIdentifiers;
import org.bouncycastle.bcpg.ECDHPublicBCPGKey;
import org.bouncycastle.bcpg.MPInteger;
import org.bouncycastle.bcpg.PublicKeyPacket;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.agreement.X25519Agreement;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.operator.PGPPad;
import org.bouncycastle.openpgp.operator.PublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.openpgp.operator.RFC6637Utils;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes5.dex */
public class BcPublicKeyKeyEncryptionMethodGenerator extends PublicKeyKeyEncryptionMethodGenerator {
    private static final byte X_HDR = 64;
    private BcPGPKeyConverter keyConverter;
    private SecureRandom random;

    public BcPublicKeyKeyEncryptionMethodGenerator(PGPPublicKey pGPPublicKey) {
        super(pGPPublicKey);
        this.keyConverter = new BcPGPKeyConverter();
    }

    private byte[] encryptSessionInfo(ECDHPublicBCPGKey eCDHPublicBCPGKey, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws IOException, PGPException {
        KeyParameter keyParameter = new KeyParameter(new RFC6637KDFCalculator(new BcPGPDigestCalculatorProvider().get(eCDHPublicBCPGKey.getHashAlgorithm()), eCDHPublicBCPGKey.getSymmetricKeyAlgorithm()).createKey(bArr2, bArr3));
        byte[] padSessionData = PGPPad.padSessionData(bArr, this.sessionKeyObfuscation);
        Wrapper createWrapper = BcImplProvider.createWrapper(eCDHPublicBCPGKey.getSymmetricKeyAlgorithm());
        createWrapper.init(true, new ParametersWithRandom(keyParameter, this.random));
        byte[] wrap = createWrapper.wrap(padSessionData, 0, padSessionData.length);
        byte[] encoded = new MPInteger(new BigInteger(1, bArr4)).getEncoded();
        byte[] bArr5 = new byte[encoded.length + 1 + wrap.length];
        System.arraycopy(encoded, 0, bArr5, 0, encoded.length);
        bArr5[encoded.length] = (byte) wrap.length;
        System.arraycopy(wrap, 0, bArr5, encoded.length + 1, wrap.length);
        return bArr5;
    }

    @Override // org.bouncycastle.openpgp.operator.PublicKeyKeyEncryptionMethodGenerator
    protected byte[] encryptSessionInfo(PGPPublicKey pGPPublicKey, byte[] bArr) throws PGPException {
        try {
            AsymmetricKeyParameter publicKey = this.keyConverter.getPublicKey(pGPPublicKey);
            if (pGPPublicKey.getAlgorithm() != 18) {
                AsymmetricBlockCipher createPublicKeyCipher = BcImplProvider.createPublicKeyCipher(pGPPublicKey.getAlgorithm());
                createPublicKeyCipher.init(true, new ParametersWithRandom(publicKey, this.random));
                return createPublicKeyCipher.processBlock(bArr, 0, bArr.length);
            }
            PublicKeyPacket publicKeyPacket = pGPPublicKey.getPublicKeyPacket();
            ECDHPublicBCPGKey eCDHPublicBCPGKey = (ECDHPublicBCPGKey) publicKeyPacket.getKey();
            byte[] createUserKeyingMaterial = RFC6637Utils.createUserKeyingMaterial(publicKeyPacket, new BcKeyFingerprintCalculator());
            if (!eCDHPublicBCPGKey.getCurveOID().equals((ASN1Primitive) CryptlibObjectIdentifiers.curvey25519)) {
                ECDomainParameters parameters = ((ECPublicKeyParameters) publicKey).getParameters();
                ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
                eCKeyPairGenerator.init(new ECKeyGenerationParameters(parameters, this.random));
                AsymmetricCipherKeyPair generateKeyPair = eCKeyPairGenerator.generateKeyPair();
                ECDHBasicAgreement eCDHBasicAgreement = new ECDHBasicAgreement();
                eCDHBasicAgreement.init(generateKeyPair.getPrivate());
                return encryptSessionInfo(eCDHPublicBCPGKey, bArr, BigIntegers.asUnsignedByteArray(eCDHBasicAgreement.getFieldSize(), eCDHBasicAgreement.calculateAgreement(publicKey)), createUserKeyingMaterial, ((ECPublicKeyParameters) generateKeyPair.getPublic()).getQ().getEncoded(false));
            }
            X25519KeyPairGenerator x25519KeyPairGenerator = new X25519KeyPairGenerator();
            x25519KeyPairGenerator.init(new X25519KeyGenerationParameters(this.random));
            AsymmetricCipherKeyPair generateKeyPair2 = x25519KeyPairGenerator.generateKeyPair();
            X25519Agreement x25519Agreement = new X25519Agreement();
            x25519Agreement.init(generateKeyPair2.getPrivate());
            byte[] bArr2 = new byte[x25519Agreement.getAgreementSize()];
            x25519Agreement.calculateAgreement(publicKey, bArr2, 0);
            byte[] bArr3 = new byte[33];
            bArr3[0] = 64;
            ((X25519PublicKeyParameters) generateKeyPair2.getPublic()).encode(bArr3, 1);
            return encryptSessionInfo(eCDHPublicBCPGKey, bArr, bArr2, createUserKeyingMaterial, bArr3);
        } catch (IOException e) {
            throw new PGPException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception encrypting session info: ")), e);
        } catch (InvalidCipherTextException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception encrypting session info: ");
            outline107.append(e2.getMessage());
            throw new PGPException(outline107.toString(), e2);
        }
    }

    public BcPublicKeyKeyEncryptionMethodGenerator setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
