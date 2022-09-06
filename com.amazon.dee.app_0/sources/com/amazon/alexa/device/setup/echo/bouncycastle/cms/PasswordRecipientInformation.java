package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.PasswordRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.pkcs.PBKDF2Params;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceAlgorithmIdentifierConverter;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JcePasswordAuthenticatedRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JcePasswordEnvelopedRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JcePasswordRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.PBEParametersGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.params.KeyParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class PasswordRecipientInformation extends RecipientInformation {
    private final PasswordRecipientInfo info;
    static Map KEYSIZES = new HashMap();
    static Map BLOCKSIZES = new HashMap();

    static {
        BLOCKSIZES.put(CMSAlgorithm.DES_EDE3_CBC, new Integer(8));
        BLOCKSIZES.put(CMSAlgorithm.AES128_CBC, new Integer(16));
        BLOCKSIZES.put(CMSAlgorithm.AES192_CBC, new Integer(16));
        BLOCKSIZES.put(CMSAlgorithm.AES256_CBC, new Integer(16));
        KEYSIZES.put(CMSAlgorithm.DES_EDE3_CBC, new Integer(192));
        KEYSIZES.put(CMSAlgorithm.AES128_CBC, new Integer(128));
        KEYSIZES.put(CMSAlgorithm.AES192_CBC, new Integer(192));
        KEYSIZES.put(CMSAlgorithm.AES256_CBC, new Integer(256));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PasswordRecipientInformation(PasswordRecipientInfo passwordRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(passwordRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.info = passwordRecipientInfo;
        this.rid = new PasswordRecipientId();
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    public CMSTypedStream getContentStream(Key key, String str) throws CMSException, NoSuchProviderException {
        return getContentStream(key, CMSUtils.getProvider(str));
    }

    public String getKeyDerivationAlgOID() {
        if (this.info.getKeyDerivationAlgorithm() != null) {
            return this.info.getKeyDerivationAlgorithm().getAlgorithm().getId();
        }
        return null;
    }

    public AlgorithmParameters getKeyDerivationAlgParameters(String str) throws NoSuchProviderException {
        return getKeyDerivationAlgParameters(CMSUtils.getProvider(str));
    }

    public byte[] getKeyDerivationAlgParams() {
        ASN1Encodable parameters;
        try {
            if (this.info.getKeyDerivationAlgorithm() != null && (parameters = this.info.getKeyDerivationAlgorithm().getParameters()) != null) {
                return parameters.toASN1Primitive().getEncoded();
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline68("exception getting encryption parameters ", e));
        }
    }

    public AlgorithmIdentifier getKeyDerivationAlgorithm() {
        return this.info.getKeyDerivationAlgorithm();
    }

    protected byte[] getPasswordBytes(int i, char[] cArr) {
        if (i == 0) {
            return PBEParametersGenerator.PKCS5PasswordToBytes(cArr);
        }
        return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(cArr);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    protected RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException, IOException {
        PasswordRecipient passwordRecipient = (PasswordRecipient) recipient;
        AlgorithmIdentifier algorithmIdentifier = AlgorithmIdentifier.getInstance(AlgorithmIdentifier.getInstance(this.info.getKeyEncryptionAlgorithm()).getParameters());
        byte[] passwordBytes = getPasswordBytes(passwordRecipient.getPasswordConversionScheme(), passwordRecipient.getPassword());
        PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(this.info.getKeyDerivationAlgorithm().getParameters());
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator();
        pKCS5S2ParametersGenerator.init(passwordBytes, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue());
        return passwordRecipient.getRecipientOperator(algorithmIdentifier, this.messageAlgorithm, ((KeyParameter) pKCS5S2ParametersGenerator.generateDerivedParameters(((Integer) KEYSIZES.get(algorithmIdentifier.getAlgorithm())).intValue())).getKey(), this.info.getEncryptedKey().getOctets());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    public CMSTypedStream getContentStream(Key key, Provider provider) throws CMSException {
        JcePasswordRecipient jcePasswordAuthenticatedRecipient;
        try {
            CMSPBEKey cMSPBEKey = (CMSPBEKey) key;
            if (this.secureReadable instanceof CMSEnvelopedHelper.CMSEnvelopedSecureReadable) {
                jcePasswordAuthenticatedRecipient = new JcePasswordEnvelopedRecipient(cMSPBEKey.getPassword());
            } else {
                jcePasswordAuthenticatedRecipient = new JcePasswordAuthenticatedRecipient(cMSPBEKey.getPassword());
            }
            jcePasswordAuthenticatedRecipient.setPasswordConversionScheme(cMSPBEKey instanceof PKCS5Scheme2UTF8PBEKey ? 1 : 0);
            if (provider != null) {
                jcePasswordAuthenticatedRecipient.setProvider(provider);
            }
            return getContentStream(jcePasswordAuthenticatedRecipient);
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("encoding error: ")), e);
        }
    }

    public AlgorithmParameters getKeyDerivationAlgParameters(Provider provider) {
        try {
            return new JceAlgorithmIdentifierConverter().setProvider(provider).getAlgorithmParameters(this.info.getKeyDerivationAlgorithm());
        } catch (Exception e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline68("exception getting encryption parameters ", e));
        }
    }
}
