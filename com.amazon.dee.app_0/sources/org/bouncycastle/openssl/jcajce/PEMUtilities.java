package org.bouncycastle.openssl.jcajce;

import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.openssl.EncryptionException;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.util.Integers;
/* loaded from: classes5.dex */
class PEMUtilities {
    private static final Map KEYSIZES = new HashMap();
    private static final Set PKCS5_SCHEME_1 = new HashSet();
    private static final Set PKCS5_SCHEME_2 = new HashSet();
    private static final Map PRFS = new HashMap();
    private static final Map PRFS_SALT = new HashMap();

    static {
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC);
        PKCS5_SCHEME_1.add(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC);
        PKCS5_SCHEME_2.add(PKCSObjectIdentifiers.id_PBES2);
        PKCS5_SCHEME_2.add(PKCSObjectIdentifiers.des_EDE3_CBC);
        PKCS5_SCHEME_2.add(NISTObjectIdentifiers.id_aes128_CBC);
        PKCS5_SCHEME_2.add(NISTObjectIdentifiers.id_aes192_CBC);
        PKCS5_SCHEME_2.add(NISTObjectIdentifiers.id_aes256_CBC);
        KEYSIZES.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), Integers.valueOf(192));
        KEYSIZES.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), Integers.valueOf(128));
        KEYSIZES.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), Integers.valueOf(192));
        KEYSIZES.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), Integers.valueOf(256));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4.getId(), Integers.valueOf(128));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4, Integers.valueOf(40));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC, Integers.valueOf(128));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, Integers.valueOf(192));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, Integers.valueOf(128));
        KEYSIZES.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, Integers.valueOf(40));
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA1, "PBKDF2withHMACSHA1");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA256, "PBKDF2withHMACSHA256");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA512, "PBKDF2withHMACSHA512");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA224, "PBKDF2withHMACSHA224");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA384, "PBKDF2withHMACSHA384");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, "PBKDF2withHMACSHA3-224");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, "PBKDF2withHMACSHA3-256");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, "PBKDF2withHMACSHA3-384");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, "PBKDF2withHMACSHA3-512");
        PRFS.put(CryptoProObjectIdentifiers.gostR3411Hmac, "PBKDF2withHMACGOST3411");
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA1, Integers.valueOf(20));
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA256, Integers.valueOf(32));
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA512, Integers.valueOf(64));
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA224, Integers.valueOf(28));
        PRFS_SALT.put(PKCSObjectIdentifiers.id_hmacWithSHA384, Integers.valueOf(48));
        PRFS_SALT.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, Integers.valueOf(28));
        PRFS_SALT.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, Integers.valueOf(32));
        PRFS_SALT.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, Integers.valueOf(48));
        PRFS_SALT.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, Integers.valueOf(64));
        PRFS_SALT.put(CryptoProObjectIdentifiers.gostR3411Hmac, Integers.valueOf(32));
    }

    PEMUtilities() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] crypt(boolean z, JcaJceHelper jcaJceHelper, byte[] bArr, char[] cArr, String str, byte[] bArr2) throws PEMException {
        String str2;
        String str3;
        String str4;
        String str5;
        byte[] bArr3;
        SecretKey key;
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        if (str.endsWith("-CFB")) {
            str2 = "CFB";
            str3 = "NoPadding";
        } else {
            str2 = "CBC";
            str3 = "PKCS5Padding";
        }
        if (str.endsWith("-ECB") || "DES-EDE".equals(str) || "DES-EDE3".equals(str)) {
            ivParameterSpec = null;
            str2 = "ECB";
        }
        RC2ParameterSpec rC2ParameterSpec = ivParameterSpec;
        if (str.endsWith("-OFB")) {
            str5 = "OFB";
            str4 = "NoPadding";
        } else {
            str4 = str3;
            str5 = str2;
        }
        boolean startsWith = str.startsWith("DES-EDE");
        String str6 = JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM;
        int i = 1;
        if (startsWith) {
            key = getKey(jcaJceHelper, cArr, "DESede", 24, bArr2, !str.startsWith("DES-EDE3"));
            str6 = "DESede";
        } else if (str.startsWith("DES-")) {
            str6 = "DES";
            key = getKey(jcaJceHelper, cArr, str6, 8, bArr2);
        } else if (str.startsWith("BF-")) {
            str6 = "Blowfish";
            key = getKey(jcaJceHelper, cArr, str6, 16, bArr2);
        } else {
            int i2 = 128;
            if (str.startsWith("RC2-")) {
                str6 = "RC2";
                if (str.startsWith("RC2-40-")) {
                    i2 = 40;
                } else if (str.startsWith("RC2-64-")) {
                    i2 = 64;
                }
                key = getKey(jcaJceHelper, cArr, str6, i2 / 8, bArr2);
                rC2ParameterSpec = rC2ParameterSpec == null ? new RC2ParameterSpec(i2) : new RC2ParameterSpec(i2, bArr2);
            } else if (!str.startsWith("AES-")) {
                throw new EncryptionException("unknown encryption with private key");
            } else {
                if (bArr2.length > 8) {
                    bArr3 = new byte[8];
                    System.arraycopy(bArr2, 0, bArr3, 0, 8);
                } else {
                    bArr3 = bArr2;
                }
                if (!str.startsWith("AES-128-")) {
                    if (str.startsWith("AES-192-")) {
                        i2 = 192;
                    } else if (!str.startsWith("AES-256-")) {
                        throw new EncryptionException("unknown AES encryption with private key");
                    } else {
                        i2 = 256;
                    }
                }
                key = getKey(jcaJceHelper, cArr, str6, i2 / 8, bArr3);
            }
        }
        try {
            Cipher createCipher = jcaJceHelper.createCipher(GeneratedOutlineSupport1.outline77(str6, "/", str5, "/", str4));
            if (!z) {
                i = 2;
            }
            if (rC2ParameterSpec == null) {
                createCipher.init(i, key);
            } else {
                createCipher.init(i, key, rC2ParameterSpec);
            }
            return createCipher.doFinal(bArr);
        } catch (Exception e) {
            throw new EncryptionException("exception using cipher - please check password and data.", e);
        }
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(JcaJceHelper jcaJceHelper, String str, char[] cArr, byte[] bArr, int i) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        return new SecretKeySpec(jcaJceHelper.createSecretKeyFactory("PBKDF2with8BIT").generateSecret(new PBEKeySpec(cArr, bArr, i, getKeySize(str))).getEncoded(), str);
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(JcaJceHelper jcaJceHelper, String str, char[] cArr, byte[] bArr, int i, AlgorithmIdentifier algorithmIdentifier) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        String str2 = (String) PRFS.get(algorithmIdentifier.getAlgorithm());
        if (str2 != null) {
            return new SecretKeySpec(jcaJceHelper.createSecretKeyFactory(str2).generateSecret(new PBEKeySpec(cArr, bArr, i, getKeySize(str))).getEncoded(), str);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown PRF in PKCS#2: ");
        outline107.append(algorithmIdentifier.getAlgorithm());
        throw new NoSuchAlgorithmException(outline107.toString());
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArr, String str, int i, byte[] bArr) throws PEMException {
        return getKey(jcaJceHelper, cArr, str, i, bArr, false);
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArr, String str, int i, byte[] bArr, boolean z) throws PEMException {
        try {
            byte[] encoded = jcaJceHelper.createSecretKeyFactory("PBKDF-OpenSSL").generateSecret(new PBEKeySpec(cArr, bArr, 1, i * 8)).getEncoded();
            if (z && encoded.length >= 24) {
                System.arraycopy(encoded, 0, encoded, 16, 8);
            }
            return new SecretKeySpec(encoded, str);
        } catch (GeneralSecurityException e) {
            throw new PEMException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("Unable to create OpenSSL PBDKF: ")), e);
        }
    }

    static int getKeySize(String str) {
        if (KEYSIZES.containsKey(str)) {
            return ((Integer) KEYSIZES.get(str)).intValue();
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline72("no key size for algorithm: ", str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getSaltSize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (PRFS_SALT.containsKey(aSN1ObjectIdentifier)) {
            return ((Integer) PRFS_SALT.get(aSN1ObjectIdentifier)).intValue();
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline79("no salt size for algorithm: ", aSN1ObjectIdentifier));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isHmacSHA1(AlgorithmIdentifier algorithmIdentifier) {
        return algorithmIdentifier == null || algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) PKCSObjectIdentifiers.id_hmacWithSHA1);
    }

    public static boolean isPKCS12(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.getId().startsWith(PKCSObjectIdentifiers.pkcs_12PbeIds.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isPKCS5Scheme1(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_1.contains(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isPKCS5Scheme2(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_2.contains(aSN1ObjectIdentifier);
    }
}
