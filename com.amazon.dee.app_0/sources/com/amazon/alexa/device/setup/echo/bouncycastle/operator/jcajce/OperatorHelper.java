package com.amazon.alexa.device.setup.echo.bouncycastle.operator.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERNull;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.cert.X509CertificateHolder;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.OperatorCreationException;
import com.amazon.whispercloak.KeyUtils;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.amazonaws.services.s3.internal.crypto.S3KeyWrapScheme;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.PSSParameterSpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes.dex */
class OperatorHelper {
    private final JcaJceHelper helper;
    private static final Map oids = new HashMap();
    private static final Map asymmetricWrapperAlgNames = new HashMap();
    private static final Map symmetricWrapperAlgNames = new HashMap();
    private static final Map symmetricKeyAlgNames = new HashMap();

    /* loaded from: classes.dex */
    private static class OpCertificateException extends CertificateException {
        private final Throwable cause;

        public OpCertificateException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    static {
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"), "SHA1WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512WITHRSA");
        oids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3411WITHGOST3410");
        oids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "GOST3411WITHECGOST3410");
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"), "MD5WITHRSA");
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"), "MD2WITHRSA");
        oids.put(new ASN1ObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512WITHECDSA");
        oids.put(OIWObjectIdentifiers.sha1WithRSA, "SHA1WITHRSA");
        oids.put(OIWObjectIdentifiers.dsaWithSHA1, "SHA1WITHDSA");
        oids.put(NISTObjectIdentifiers.dsa_with_sha224, "SHA224WITHDSA");
        oids.put(NISTObjectIdentifiers.dsa_with_sha256, "SHA256WITHDSA");
        asymmetricWrapperAlgNames.put(PKCSObjectIdentifiers.rsaEncryption, "RSA/ECB/PKCS1Padding");
        symmetricWrapperAlgNames.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap, "DESEDEWrap");
        symmetricWrapperAlgNames.put(PKCSObjectIdentifiers.id_alg_CMSRC2wrap, "RC2Wrap");
        symmetricWrapperAlgNames.put(NISTObjectIdentifiers.id_aes128_wrap, S3KeyWrapScheme.AES_WRAP);
        symmetricWrapperAlgNames.put(NISTObjectIdentifiers.id_aes192_wrap, S3KeyWrapScheme.AES_WRAP);
        symmetricWrapperAlgNames.put(NISTObjectIdentifiers.id_aes256_wrap, S3KeyWrapScheme.AES_WRAP);
        symmetricWrapperAlgNames.put(NTTObjectIdentifiers.id_camellia128_wrap, "CamelliaWrap");
        symmetricWrapperAlgNames.put(NTTObjectIdentifiers.id_camellia192_wrap, "CamelliaWrap");
        symmetricWrapperAlgNames.put(NTTObjectIdentifiers.id_camellia256_wrap, "CamelliaWrap");
        symmetricWrapperAlgNames.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap, "SEEDWrap");
        symmetricWrapperAlgNames.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESede");
        symmetricKeyAlgNames.put(NISTObjectIdentifiers.aes, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        symmetricKeyAlgNames.put(NISTObjectIdentifiers.id_aes128_CBC, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        symmetricKeyAlgNames.put(NISTObjectIdentifiers.id_aes192_CBC, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        symmetricKeyAlgNames.put(NISTObjectIdentifiers.id_aes256_CBC, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        symmetricKeyAlgNames.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESede");
        symmetricKeyAlgNames.put(PKCSObjectIdentifiers.RC2_CBC, "RC2");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OperatorHelper(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    private static String getDigestAlgName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCSObjectIdentifiers.md5.equals(aSN1ObjectIdentifier) ? MessageDigestAlgorithms.MD5 : OIWObjectIdentifiers.idSHA1.equals(aSN1ObjectIdentifier) ? "SHA1" : NISTObjectIdentifiers.id_sha224.equals(aSN1ObjectIdentifier) ? "SHA224" : NISTObjectIdentifiers.id_sha256.equals(aSN1ObjectIdentifier) ? "SHA256" : NISTObjectIdentifiers.id_sha384.equals(aSN1ObjectIdentifier) ? "SHA384" : NISTObjectIdentifiers.id_sha512.equals(aSN1ObjectIdentifier) ? "SHA512" : TeleTrusTObjectIdentifiers.ripemd128.equals(aSN1ObjectIdentifier) ? "RIPEMD128" : TeleTrusTObjectIdentifiers.ripemd160.equals(aSN1ObjectIdentifier) ? "RIPEMD160" : TeleTrusTObjectIdentifiers.ripemd256.equals(aSN1ObjectIdentifier) ? "RIPEMD256" : CryptoProObjectIdentifiers.gostR3411.equals(aSN1ObjectIdentifier) ? "GOST3411" : aSN1ObjectIdentifier.getId();
    }

    private static String getSignatureName(AlgorithmIdentifier algorithmIdentifier) {
        ASN1Encodable parameters = algorithmIdentifier.getParameters();
        if (parameters != null && !DERNull.INSTANCE.equals(parameters) && algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
            return GeneratedOutlineSupport1.outline91(new StringBuilder(), getDigestAlgName(RSASSAPSSparams.getInstance(parameters).getHashAlgorithm().getAlgorithm()), "WITHRSAANDMGF1");
        } else if (oids.containsKey(algorithmIdentifier.getAlgorithm())) {
            return (String) oids.get(algorithmIdentifier.getAlgorithm());
        } else {
            return algorithmIdentifier.getAlgorithm().getId();
        }
    }

    public X509Certificate convertCertificate(X509CertificateHolder x509CertificateHolder) throws CertificateException {
        try {
            return (X509Certificate) this.helper.createCertificateFactory(KeyUtils.X509_CERITIFATE_FACTORY).generateCertificate(new ByteArrayInputStream(x509CertificateHolder.getEncoded()));
        } catch (IOException e) {
            throw new OpCertificateException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("cannot get encoded form of certificate: ")), e);
        } catch (NoSuchAlgorithmException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot create certificate factory: ");
            outline107.append(e2.getMessage());
            throw new OpCertificateException(outline107.toString(), e2);
        } catch (NoSuchProviderException e3) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("cannot find factory provider: ");
            outline1072.append(e3.getMessage());
            throw new OpCertificateException(outline1072.toString(), e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher createAsymmetricWrapper(ASN1ObjectIdentifier aSN1ObjectIdentifier, Map map) throws OperatorCreationException {
        String str = null;
        try {
            if (!map.isEmpty()) {
                str = (String) map.get(aSN1ObjectIdentifier);
            }
            if (str == null) {
                str = (String) asymmetricWrapperAlgNames.get(aSN1ObjectIdentifier);
            }
            if (str != null) {
                try {
                    return this.helper.createCipher(str);
                } catch (NoSuchAlgorithmException unused) {
                    if (str.equals("RSA/ECB/PKCS1Padding")) {
                        try {
                            return this.helper.createCipher("RSA/NONE/PKCS1Padding");
                        } catch (NoSuchAlgorithmException unused2) {
                        }
                    }
                }
            }
            return this.helper.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot create cipher: ")), e);
        }
    }

    MessageDigest createDigest(AlgorithmIdentifier algorithmIdentifier) throws GeneralSecurityException {
        try {
            return this.helper.createDigest(getDigestAlgName(algorithmIdentifier.getAlgorithm()));
        } catch (NoSuchAlgorithmException e) {
            if (oids.get(algorithmIdentifier.getAlgorithm()) != null) {
                return this.helper.createDigest((String) oids.get(algorithmIdentifier.getAlgorithm()));
            }
            throw e;
        }
    }

    public Signature createRawSignature(AlgorithmIdentifier algorithmIdentifier) {
        try {
            String signatureName = getSignatureName(algorithmIdentifier);
            String str = "NONE" + signatureName.substring(signatureName.indexOf("WITH"));
            Signature createSignature = this.helper.createSignature(str);
            if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
                AlgorithmParameters createAlgorithmParameters = this.helper.createAlgorithmParameters(str);
                createAlgorithmParameters.init(algorithmIdentifier.getParameters().toASN1Primitive().getEncoded(), "ASN.1");
                createSignature.setParameter((PSSParameterSpec) createAlgorithmParameters.getParameterSpec(PSSParameterSpec.class));
            }
            return createSignature;
        } catch (Exception unused) {
            return null;
        }
    }

    Signature createSignature(AlgorithmIdentifier algorithmIdentifier) throws GeneralSecurityException {
        try {
            return this.helper.createSignature(getSignatureName(algorithmIdentifier));
        } catch (NoSuchAlgorithmException e) {
            if (oids.get(algorithmIdentifier.getAlgorithm()) != null) {
                return this.helper.createSignature((String) oids.get(algorithmIdentifier.getAlgorithm()));
            }
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher createSymmetricWrapper(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws OperatorCreationException {
        try {
            String str = (String) symmetricWrapperAlgNames.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.helper.createCipher(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.helper.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot create cipher: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getKeyAlgorithmName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) symmetricKeyAlgNames.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }
}
