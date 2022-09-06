package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERNull;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KeyAgreeRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.OriginatorIdentifierOrKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.OriginatorPublicKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes.dex */
public abstract class KeyAgreeRecipientInfoGenerator implements RecipientInfoGenerator {
    private final ASN1ObjectIdentifier keyAgreementOID;
    private final ASN1ObjectIdentifier keyEncryptionOID;
    private final SubjectPublicKeyInfo originatorKeyInfo;

    /* JADX INFO: Access modifiers changed from: protected */
    public KeyAgreeRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        this.originatorKeyInfo = subjectPublicKeyInfo;
        this.keyAgreementOID = aSN1ObjectIdentifier;
        this.keyEncryptionOID = aSN1ObjectIdentifier2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OriginatorPublicKey createOriginatorPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        return new OriginatorPublicKey(new AlgorithmIdentifier(subjectPublicKeyInfo.getAlgorithmId().getAlgorithm(), (ASN1Encodable) DERNull.INSTANCE), subjectPublicKeyInfo.getPublicKeyData().getBytes());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInfoGenerator
    public RecipientInfo generate(GenericKey genericKey) throws CMSException {
        OriginatorIdentifierOrKey originatorIdentifierOrKey = new OriginatorIdentifierOrKey(createOriginatorPublicKey(this.originatorKeyInfo));
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.keyEncryptionOID);
        aSN1EncodableVector.add(DERNull.INSTANCE);
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(this.keyEncryptionOID, (ASN1Encodable) DERNull.INSTANCE);
        AlgorithmIdentifier algorithmIdentifier2 = new AlgorithmIdentifier(this.keyAgreementOID, (ASN1Encodable) algorithmIdentifier);
        ASN1Sequence generateRecipientEncryptedKeys = generateRecipientEncryptedKeys(algorithmIdentifier2, algorithmIdentifier, genericKey);
        ASN1Encodable userKeyingMaterial = getUserKeyingMaterial(algorithmIdentifier2);
        if (userKeyingMaterial != null) {
            try {
                return new RecipientInfo(new KeyAgreeRecipientInfo(originatorIdentifierOrKey, new DEROctetString(userKeyingMaterial), algorithmIdentifier2, generateRecipientEncryptedKeys));
            } catch (IOException e) {
                throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to encode userKeyingMaterial: ")), e);
            }
        }
        return new RecipientInfo(new KeyAgreeRecipientInfo(originatorIdentifierOrKey, null, algorithmIdentifier2, generateRecipientEncryptedKeys));
    }

    protected abstract ASN1Sequence generateRecipientEncryptedKeys(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, GenericKey genericKey) throws CMSException;

    protected abstract ASN1Encodable getUserKeyingMaterial(AlgorithmIdentifier algorithmIdentifier) throws CMSException;
}
