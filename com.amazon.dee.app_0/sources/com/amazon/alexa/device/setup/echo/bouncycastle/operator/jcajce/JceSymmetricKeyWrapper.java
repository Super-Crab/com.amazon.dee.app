package com.amazon.alexa.device.setup.echo.bouncycastle.operator.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERInteger;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERNull;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.DefaultJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.NamedJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.ProviderJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.OperatorException;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.SymmetricKeyWrapper;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
/* loaded from: classes.dex */
public class JceSymmetricKeyWrapper extends SymmetricKeyWrapper {
    private OperatorHelper helper;
    private SecureRandom random;
    private final SecretKey wrappingKey;

    public JceSymmetricKeyWrapper(SecretKey secretKey) {
        super(determineKeyEncAlg(secretKey));
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.wrappingKey = secretKey;
    }

    private static AlgorithmIdentifier determineKeyEncAlg(SecretKey secretKey) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2;
        String algorithm = secretKey.getAlgorithm();
        if (algorithm.startsWith("DES")) {
            return new AlgorithmIdentifier(new DERObjectIdentifier("1.2.840.113549.1.9.16.3.6"), new DERNull());
        }
        if (algorithm.startsWith("RC2")) {
            return new AlgorithmIdentifier(new DERObjectIdentifier("1.2.840.113549.1.9.16.3.7"), new DERInteger(58));
        }
        if (algorithm.startsWith(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM)) {
            int length = secretKey.getEncoded().length * 8;
            if (length == 128) {
                aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_aes128_wrap;
            } else if (length == 192) {
                aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_aes192_wrap;
            } else if (length == 256) {
                aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_aes256_wrap;
            } else {
                throw new IllegalArgumentException("illegal keysize in AES");
            }
            return new AlgorithmIdentifier((DERObjectIdentifier) aSN1ObjectIdentifier2);
        } else if (algorithm.startsWith("SEED")) {
            return new AlgorithmIdentifier(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap);
        } else {
            if (algorithm.startsWith("Camellia")) {
                int length2 = secretKey.getEncoded().length * 8;
                if (length2 == 128) {
                    aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia128_wrap;
                } else if (length2 == 192) {
                    aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia192_wrap;
                } else if (length2 == 256) {
                    aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia256_wrap;
                } else {
                    throw new IllegalArgumentException("illegal keysize in Camellia");
                }
                return new AlgorithmIdentifier((DERObjectIdentifier) aSN1ObjectIdentifier);
            }
            throw new IllegalArgumentException("unknown algorithm");
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        Key jceKey = OperatorUtils.getJceKey(genericKey);
        Cipher createSymmetricWrapper = this.helper.createSymmetricWrapper(getAlgorithmIdentifier().getAlgorithm());
        try {
            createSymmetricWrapper.init(3, this.wrappingKey, this.random);
            return createSymmetricWrapper.wrap(jceKey);
        } catch (GeneralSecurityException e) {
            throw new OperatorException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot wrap key: ")), e);
        }
    }

    public JceSymmetricKeyWrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceSymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JceSymmetricKeyWrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }
}
