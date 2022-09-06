package org.bouncycastle.openpgp.operator.jcajce;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cryptlib.CryptlibObjectIdentifiers;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.bcpg.ECDHPublicBCPGKey;
import org.bouncycastle.bcpg.MPInteger;
import org.bouncycastle.bcpg.PublicKeyPacket;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PGPPad;
import org.bouncycastle.openpgp.operator.PublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.openpgp.operator.RFC6637Utils;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class JcePublicKeyKeyEncryptionMethodGenerator extends PublicKeyKeyEncryptionMethodGenerator {
    private static final byte X_HDR = 64;
    private OperatorHelper helper;
    private JcaPGPKeyConverter keyConverter;
    private SecureRandom random;

    public JcePublicKeyKeyEncryptionMethodGenerator(PGPPublicKey pGPPublicKey) {
        super(pGPPublicKey);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.keyConverter = new JcaPGPKeyConverter();
    }

    private byte[] encryptSessionInfo(ECDHPublicBCPGKey eCDHPublicBCPGKey, byte[] bArr, Key key, byte[] bArr2) throws GeneralSecurityException, IOException, PGPException {
        byte[] padSessionData = PGPPad.padSessionData(bArr, this.sessionKeyObfuscation);
        Cipher createKeyWrapper = this.helper.createKeyWrapper(eCDHPublicBCPGKey.getSymmetricKeyAlgorithm());
        createKeyWrapper.init(3, key, this.random);
        byte[] wrap = createKeyWrapper.wrap(new SecretKeySpec(padSessionData, PGPUtil.getSymmetricCipherName(bArr[0])));
        byte[] encoded = new MPInteger(new BigInteger(1, bArr2)).getEncoded();
        byte[] bArr3 = new byte[encoded.length + 1 + wrap.length];
        System.arraycopy(encoded, 0, bArr3, 0, encoded.length);
        bArr3[encoded.length] = (byte) wrap.length;
        System.arraycopy(wrap, 0, bArr3, encoded.length + 1, wrap.length);
        return bArr3;
    }

    @Override // org.bouncycastle.openpgp.operator.PublicKeyKeyEncryptionMethodGenerator
    protected byte[] encryptSessionInfo(PGPPublicKey pGPPublicKey, byte[] bArr) throws PGPException {
        try {
            PublicKey publicKey = this.keyConverter.getPublicKey(pGPPublicKey);
            if (pGPPublicKey.getAlgorithm() != 18) {
                Cipher createPublicKeyCipher = this.helper.createPublicKeyCipher(pGPPublicKey.getAlgorithm());
                createPublicKeyCipher.init(1, publicKey, this.random);
                return createPublicKeyCipher.doFinal(bArr);
            }
            PublicKeyPacket publicKeyPacket = pGPPublicKey.getPublicKeyPacket();
            ECDHPublicBCPGKey eCDHPublicBCPGKey = (ECDHPublicBCPGKey) publicKeyPacket.getKey();
            UserKeyingMaterialSpec userKeyingMaterialSpec = new UserKeyingMaterialSpec(RFC6637Utils.createUserKeyingMaterial(publicKeyPacket, new JcaKeyFingerprintCalculator()));
            String id = RFC6637Utils.getKeyEncryptionOID(eCDHPublicBCPGKey.getSymmetricKeyAlgorithm()).getId();
            if (eCDHPublicBCPGKey.getCurveOID().equals((ASN1Primitive) CryptlibObjectIdentifiers.curvey25519)) {
                KeyPairGenerator createKeyPairGenerator = this.helper.createKeyPairGenerator(XDHParameterSpec.X25519);
                createKeyPairGenerator.initialize(255, this.random);
                KeyPair generateKeyPair = createKeyPairGenerator.generateKeyPair();
                KeyAgreement createKeyAgreement = this.helper.createKeyAgreement(RFC6637Utils.getXDHAlgorithm(publicKeyPacket));
                createKeyAgreement.init(generateKeyPair.getPrivate(), userKeyingMaterialSpec);
                createKeyAgreement.doPhase(publicKey, true);
                return encryptSessionInfo(eCDHPublicBCPGKey, bArr, createKeyAgreement.generateSecret(id), Arrays.prepend(SubjectPublicKeyInfo.getInstance(generateKeyPair.getPublic().getEncoded()).getPublicKeyData().getBytes(), (byte) 64));
            }
            AlgorithmParameters createAlgorithmParameters = this.helper.createAlgorithmParameters(KeyUtils.ALGORITHM_EC);
            createAlgorithmParameters.init(new X962Parameters(eCDHPublicBCPGKey.getCurveOID()).getEncoded());
            KeyPairGenerator createKeyPairGenerator2 = this.helper.createKeyPairGenerator(KeyUtils.ALGORITHM_EC);
            createKeyPairGenerator2.initialize(createAlgorithmParameters.getParameterSpec(AlgorithmParameterSpec.class), this.random);
            KeyPair generateKeyPair2 = createKeyPairGenerator2.generateKeyPair();
            KeyAgreement createKeyAgreement2 = this.helper.createKeyAgreement(RFC6637Utils.getAgreementAlgorithm(publicKeyPacket));
            createKeyAgreement2.init(generateKeyPair2.getPrivate(), userKeyingMaterialSpec);
            createKeyAgreement2.doPhase(publicKey, true);
            SecretKey generateSecret = createKeyAgreement2.generateSecret(id);
            byte[] bytes = SubjectPublicKeyInfo.getInstance(generateKeyPair2.getPublic().getEncoded()).getPublicKeyData().getBytes();
            if (bytes == null || bytes.length < 1 || bytes[0] != 4) {
                bytes = JcaJcePGPUtil.getX9Parameters(eCDHPublicBCPGKey.getCurveOID()).getCurve().decodePoint(bytes).getEncoded(false);
            }
            return encryptSessionInfo(eCDHPublicBCPGKey, bArr, generateSecret, bytes);
        } catch (IOException e) {
            throw new PGPException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to encode MPI: ")), e);
        } catch (InvalidKeyException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("key invalid: ");
            outline107.append(e2.getMessage());
            throw new PGPException(outline107.toString(), e2);
        } catch (BadPaddingException e3) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("bad padding: ");
            outline1072.append(e3.getMessage());
            throw new PGPException(outline1072.toString(), e3);
        } catch (IllegalBlockSizeException e4) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("illegal block size: ");
            outline1073.append(e4.getMessage());
            throw new PGPException(outline1073.toString(), e4);
        } catch (GeneralSecurityException e5) {
            throw new PGPException(GeneratedOutlineSupport1.outline99(e5, GeneratedOutlineSupport1.outline107("unable to set up ephemeral keys: ")), e5);
        }
    }

    public JcePublicKeyKeyEncryptionMethodGenerator setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        this.keyConverter.setProvider(str);
        return this;
    }

    public JcePublicKeyKeyEncryptionMethodGenerator setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        this.keyConverter.setProvider(provider);
        return this;
    }

    public JcePublicKeyKeyEncryptionMethodGenerator setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
