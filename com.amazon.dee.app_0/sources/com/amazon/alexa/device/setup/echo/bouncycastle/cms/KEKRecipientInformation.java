package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KEKRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKEKAuthenticatedRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKEKEnvelopedRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKEKRecipient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchProviderException;
import java.security.Provider;
import javax.crypto.SecretKey;
/* loaded from: classes.dex */
public class KEKRecipientInformation extends RecipientInformation {
    private final KEKRecipientInfo info;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KEKRecipientInformation(KEKRecipientInfo kEKRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(kEKRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.info = kEKRecipientInfo;
        this.rid = new KEKRecipientId(kEKRecipientInfo.getKekid().getKeyIdentifier().getOctets());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    public CMSTypedStream getContentStream(Key key, String str) throws CMSException, NoSuchProviderException {
        return getContentStream(key, CMSUtils.getProvider(str));
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    protected RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException, IOException {
        return ((KEKRecipient) recipient).getRecipientOperator(this.keyEncAlg, this.messageAlgorithm, this.info.getEncryptedKey().getOctets());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInformation
    public CMSTypedStream getContentStream(Key key, Provider provider) throws CMSException {
        JceKEKRecipient jceKEKAuthenticatedRecipient;
        try {
            if (this.secureReadable instanceof CMSEnvelopedHelper.CMSEnvelopedSecureReadable) {
                jceKEKAuthenticatedRecipient = new JceKEKEnvelopedRecipient((SecretKey) key);
            } else {
                jceKEKAuthenticatedRecipient = new JceKEKAuthenticatedRecipient((SecretKey) key);
            }
            if (provider != null) {
                jceKEKAuthenticatedRecipient.setProvider(provider);
            }
            return getContentStream(jceKEKAuthenticatedRecipient);
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("encoding error: ")), e);
        }
    }
}
