package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KeyAgreeRecipientIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KeyAgreeRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.OriginatorIdentifierOrKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.OriginatorPublicKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientEncryptedKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKeyAgreeAuthenticatedRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKeyAgreeEnvelopedRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKeyAgreeRecipient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.List;
/* loaded from: classes.dex */
public class KeyAgreeRecipientInformation extends RecipientInformation {
    private final ASN1OctetString encryptedKey;
    private final KeyAgreeRecipientInfo info;

    KeyAgreeRecipientInformation(KeyAgreeRecipientInfo keyAgreeRecipientInfo, RecipientId recipientId, ASN1OctetString aSN1OctetString, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(keyAgreeRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.info = keyAgreeRecipientInfo;
        this.rid = recipientId;
        this.encryptedKey = aSN1OctetString;
    }

    private SubjectPublicKeyInfo getPublicKeyInfoFromOriginatorId(OriginatorId originatorId) throws CMSException {
        throw new CMSException("No support for 'originator' as IssuerAndSerialNumber or SubjectKeyIdentifier");
    }

    private SubjectPublicKeyInfo getPublicKeyInfoFromOriginatorPublicKey(AlgorithmIdentifier algorithmIdentifier, OriginatorPublicKey originatorPublicKey) {
        return new SubjectPublicKeyInfo(algorithmIdentifier, originatorPublicKey.getPublicKey().getBytes());
    }

    private SubjectPublicKeyInfo getSenderPublicKeyInfo(AlgorithmIdentifier algorithmIdentifier, OriginatorIdentifierOrKey originatorIdentifierOrKey) throws CMSException, IOException {
        OriginatorId originatorId;
        OriginatorPublicKey originatorKey = originatorIdentifierOrKey.getOriginatorKey();
        if (originatorKey != null) {
            return getPublicKeyInfoFromOriginatorPublicKey(algorithmIdentifier, originatorKey);
        }
        IssuerAndSerialNumber issuerAndSerialNumber = originatorIdentifierOrKey.getIssuerAndSerialNumber();
        if (issuerAndSerialNumber != null) {
            originatorId = new OriginatorId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
        } else {
            originatorId = new OriginatorId(originatorIdentifierOrKey.getSubjectKeyIdentifier().getKeyIdentifier());
        }
        return getPublicKeyInfoFromOriginatorId(originatorId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void readRecipientInfo(List list, KeyAgreeRecipientInfo keyAgreeRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        KeyAgreeRecipientId keyAgreeRecipientId;
        ASN1Sequence recipientEncryptedKeys = keyAgreeRecipientInfo.getRecipientEncryptedKeys();
        for (int i = 0; i < recipientEncryptedKeys.size(); i++) {
            RecipientEncryptedKey recipientEncryptedKey = RecipientEncryptedKey.getInstance(recipientEncryptedKeys.getObjectAt(i));
            KeyAgreeRecipientIdentifier identifier = recipientEncryptedKey.getIdentifier();
            IssuerAndSerialNumber issuerAndSerialNumber = identifier.getIssuerAndSerialNumber();
            if (issuerAndSerialNumber != null) {
                keyAgreeRecipientId = new KeyAgreeRecipientId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
            } else {
                keyAgreeRecipientId = new KeyAgreeRecipientId(identifier.getRKeyID().getSubjectKeyIdentifier().getOctets());
            }
            list.add(new KeyAgreeRecipientInformation(keyAgreeRecipientInfo, keyAgreeRecipientId, recipientEncryptedKey.getEncryptedKey(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider));
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    public CMSTypedStream getContentStream(Key key, String str) throws CMSException, NoSuchProviderException {
        return getContentStream(key, CMSUtils.getProvider(str));
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    protected RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException, IOException {
        KeyAgreeRecipient keyAgreeRecipient = (KeyAgreeRecipient) recipient;
        return keyAgreeRecipient.getRecipientOperator(this.keyEncAlg, this.messageAlgorithm, getSenderPublicKeyInfo(keyAgreeRecipient.getPrivateKeyAlgorithmIdentifier(), this.info.getOriginator()), this.info.getUserKeyingMaterial(), this.encryptedKey.getOctets());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    public CMSTypedStream getContentStream(Key key, Provider provider) throws CMSException {
        JceKeyAgreeRecipient jceKeyAgreeAuthenticatedRecipient;
        try {
            if (this.secureReadable instanceof CMSEnvelopedHelper.CMSEnvelopedSecureReadable) {
                jceKeyAgreeAuthenticatedRecipient = new JceKeyAgreeEnvelopedRecipient((PrivateKey) key);
            } else {
                jceKeyAgreeAuthenticatedRecipient = new JceKeyAgreeAuthenticatedRecipient((PrivateKey) key);
            }
            if (provider != null) {
                jceKeyAgreeAuthenticatedRecipient.setProvider(provider);
                if (provider.getName().equalsIgnoreCase("SunJCE")) {
                    jceKeyAgreeAuthenticatedRecipient.setContentProvider((String) null);
                }
            }
            return getContentStream(jceKeyAgreeAuthenticatedRecipient);
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("encoding error: ")), e);
        }
    }
}
