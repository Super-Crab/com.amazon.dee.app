package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKeyTransAuthenticatedRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKeyTransRecipient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
/* loaded from: classes.dex */
public class KeyTransRecipientInformation extends RecipientInformation {
    private final KeyTransRecipientInfo info;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyTransRecipientInformation(KeyTransRecipientInfo keyTransRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(keyTransRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.info = keyTransRecipientInfo;
        RecipientIdentifier recipientIdentifier = keyTransRecipientInfo.getRecipientIdentifier();
        if (recipientIdentifier.isTagged()) {
            this.rid = new KeyTransRecipientId(ASN1OctetString.getInstance(recipientIdentifier.getId()).getOctets());
            return;
        }
        IssuerAndSerialNumber issuerAndSerialNumber = IssuerAndSerialNumber.getInstance(recipientIdentifier.getId());
        this.rid = new KeyTransRecipientId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    public CMSTypedStream getContentStream(Key key, String str) throws CMSException, NoSuchProviderException {
        return getContentStream(key, CMSUtils.getProvider(str));
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    protected RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException {
        return ((KeyTransRecipient) recipient).getRecipientOperator(this.keyEncAlg, this.messageAlgorithm, this.info.getEncryptedKey().getOctets());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    public CMSTypedStream getContentStream(Key key, Provider provider) throws CMSException {
        JceKeyTransRecipient jceKeyTransAuthenticatedRecipient;
        try {
            if (this.secureReadable instanceof CMSEnvelopedHelper.CMSEnvelopedSecureReadable) {
                jceKeyTransAuthenticatedRecipient = new JceKeyTransEnvelopedRecipient((PrivateKey) key);
            } else {
                jceKeyTransAuthenticatedRecipient = new JceKeyTransAuthenticatedRecipient((PrivateKey) key);
            }
            if (provider != null) {
                jceKeyTransAuthenticatedRecipient.setProvider(provider);
                if (provider.getName().equalsIgnoreCase("SunJCE")) {
                    jceKeyTransAuthenticatedRecipient.setContentProvider((String) null);
                }
            }
            return getContentStream(jceKeyTransAuthenticatedRecipient);
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("encoding error: ")), e);
        }
    }
}
