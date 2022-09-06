package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.AsymmetricKeyWrapper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.OperatorException;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public abstract class KeyTransRecipientInfoGenerator implements RecipientInfoGenerator {
    private IssuerAndSerialNumber issuerAndSerial;
    private byte[] subjectKeyIdentifier;
    protected final AsymmetricKeyWrapper wrapper;

    /* JADX INFO: Access modifiers changed from: protected */
    public KeyTransRecipientInfoGenerator(IssuerAndSerialNumber issuerAndSerialNumber, AsymmetricKeyWrapper asymmetricKeyWrapper) {
        this.issuerAndSerial = issuerAndSerialNumber;
        this.wrapper = asymmetricKeyWrapper;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInfoGenerator
    public final RecipientInfo generate(GenericKey genericKey) throws CMSException {
        RecipientIdentifier recipientIdentifier;
        try {
            byte[] generateWrappedKey = this.wrapper.generateWrappedKey(genericKey);
            IssuerAndSerialNumber issuerAndSerialNumber = this.issuerAndSerial;
            if (issuerAndSerialNumber != null) {
                recipientIdentifier = new RecipientIdentifier(issuerAndSerialNumber);
            } else {
                recipientIdentifier = new RecipientIdentifier((ASN1OctetString) new DEROctetString(this.subjectKeyIdentifier));
            }
            return new RecipientInfo(new KeyTransRecipientInfo(recipientIdentifier, this.wrapper.getAlgorithmIdentifier(), new DEROctetString(generateWrappedKey)));
        } catch (OperatorException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception wrapping content key: ");
            outline107.append(e.getMessage());
            throw new CMSException(outline107.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KeyTransRecipientInfoGenerator(byte[] bArr, AsymmetricKeyWrapper asymmetricKeyWrapper) {
        this.subjectKeyIdentifier = bArr;
        this.wrapper = asymmetricKeyWrapper;
    }
}
