package org.bouncycastle.openpgp.operator.jcajce;

import com.amazon.whispercloak.KeyUtils;
import com.amazonaws.services.s3.internal.crypto.S3KeyWrapScheme;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.jcajce.io.CipherInputStream;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PGPDataDecryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class OperatorHelper {
    private JcaJceHelper helper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OperatorHelper(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    private Signature createSignature(String str) throws PGPException {
        try {
            return this.helper.createSignature(str);
        } catch (GeneralSecurityException e) {
            throw new PGPException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot create signature: ")), e);
        }
    }

    public AlgorithmParameters createAlgorithmParameters(String str) throws NoSuchProviderException, NoSuchAlgorithmException {
        return this.helper.createAlgorithmParameters(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher createCipher(String str) throws PGPException {
        try {
            return this.helper.createCipher(str);
        } catch (GeneralSecurityException e) {
            throw new PGPException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot create cipher: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PGPDataDecryptor createDataDecryptor(boolean z, int i, byte[] bArr) throws PGPException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, PGPUtil.getSymmetricCipherName(i));
            final Cipher createStreamCipher = createStreamCipher(i, z);
            if (z) {
                createStreamCipher.init(2, secretKeySpec, new IvParameterSpec(new byte[createStreamCipher.getBlockSize()]));
            } else {
                createStreamCipher.init(2, secretKeySpec);
            }
            return new PGPDataDecryptor() { // from class: org.bouncycastle.openpgp.operator.jcajce.OperatorHelper.1
                @Override // org.bouncycastle.openpgp.operator.PGPDataDecryptor
                public int getBlockSize() {
                    return createStreamCipher.getBlockSize();
                }

                @Override // org.bouncycastle.openpgp.operator.PGPDataDecryptor
                public InputStream getInputStream(InputStream inputStream) {
                    return new CipherInputStream(inputStream, createStreamCipher);
                }

                @Override // org.bouncycastle.openpgp.operator.PGPDataDecryptor
                public PGPDigestCalculator getIntegrityCalculator() {
                    return new SHA1PGPDigestCalculator();
                }
            };
        } catch (PGPException e) {
            throw e;
        } catch (Exception e2) {
            throw new PGPException("Exception creating cipher", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageDigest createDigest(int i) throws GeneralSecurityException, PGPException {
        String digestName = getDigestName(i);
        try {
            return this.helper.createMessageDigest(digestName);
        } catch (NoSuchAlgorithmException e) {
            if (i < 8 || i > 11) {
                throw e;
            }
            JcaJceHelper jcaJceHelper = this.helper;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SHA");
            outline107.append(digestName.substring(4));
            return jcaJceHelper.createMessageDigest(outline107.toString());
        }
    }

    public KeyAgreement createKeyAgreement(String str) throws GeneralSecurityException {
        return this.helper.createKeyAgreement(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyFactory createKeyFactory(String str) throws GeneralSecurityException, PGPException {
        return this.helper.createKeyFactory(str);
    }

    public KeyPairGenerator createKeyPairGenerator(String str) throws GeneralSecurityException {
        return this.helper.createKeyPairGenerator(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher createKeyWrapper(int i) throws PGPException {
        try {
            switch (i) {
                case 7:
                case 8:
                case 9:
                    return this.helper.createCipher(S3KeyWrapScheme.AES_WRAP);
                case 10:
                default:
                    throw new PGPException("unknown wrap algorithm: " + i);
                case 11:
                case 12:
                case 13:
                    return this.helper.createCipher("CamelliaWrap");
            }
        } catch (GeneralSecurityException e) {
            throw new PGPException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot create cipher: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher createPublicKeyCipher(int i) throws PGPException {
        String str;
        if (i == 1 || i == 2) {
            str = "RSA/ECB/PKCS1Padding";
        } else {
            if (i != 16) {
                if (i == 17) {
                    throw new PGPException("Can't use DSA for encryption.");
                }
                if (i == 19) {
                    throw new PGPException("Can't use ECDSA for encryption.");
                }
                if (i != 20) {
                    if (i == 22) {
                        throw new PGPException("Can't use EDDSA for encryption.");
                    }
                    throw new PGPException(GeneratedOutlineSupport1.outline49("unknown asymmetric algorithm: ", i));
                }
            }
            str = "ElGamal/ECB/PKCS1Padding";
        }
        return createCipher(str);
    }

    public Signature createSignature(int i, int i2) throws PGPException {
        String str;
        String str2;
        if (i == 1 || i == 3) {
            str = KeyUtils.ALGORITHM_RSA;
        } else if (i == 22) {
            str2 = EdDSAParameterSpec.Ed25519;
            return createSignature(str2);
        } else {
            if (i != 16) {
                if (i == 17) {
                    str = "DSA";
                } else if (i == 19) {
                    str = "ECDSA";
                } else if (i != 20) {
                    throw new PGPException(GeneratedOutlineSupport1.outline49("unknown algorithm tag in signature:", i));
                }
            }
            str = "ElGamal";
        }
        str2 = PGPUtil.getDigestName(i2) + JsonPOJOBuilder.DEFAULT_WITH_PREFIX + str;
        return createSignature(str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher createStreamCipher(int i, boolean z) throws PGPException {
        String str = z ? "CFB" : "OpenPGPCFB";
        return createCipher(PGPUtil.getSymmetricCipherName(i) + "/" + str + "/NoPadding");
    }

    String getDigestName(int i) throws PGPException {
        switch (i) {
            case 1:
                return MessageDigestAlgorithms.MD5;
            case 2:
                return "SHA-1";
            case 3:
                return "RIPEMD160";
            case 4:
            case 7:
            default:
                throw new PGPException(GeneratedOutlineSupport1.outline49("unknown hash algorithm tag in getDigestName: ", i));
            case 5:
                return MessageDigestAlgorithms.MD2;
            case 6:
                return "TIGER";
            case 8:
                return "SHA-256";
            case 9:
                return "SHA-384";
            case 10:
                return "SHA-512";
            case 11:
                return McElieceCCA2KeyGenParameterSpec.SHA224;
        }
    }
}
