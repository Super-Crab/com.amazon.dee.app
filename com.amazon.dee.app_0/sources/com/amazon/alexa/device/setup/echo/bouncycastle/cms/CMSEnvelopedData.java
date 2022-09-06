package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Set;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.AttributeTable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.ContentInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.EncryptedContentInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.EnvelopedData;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceAlgorithmIdentifierConverter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.NoSuchProviderException;
import java.security.Provider;
/* loaded from: classes.dex */
public class CMSEnvelopedData {
    ContentInfo contentInfo;
    private final AlgorithmIdentifier encAlg;
    private OriginatorInformation originatorInfo;
    RecipientInformationStore recipientInfoStore;
    private final ASN1Set unprotectedAttributes;

    public CMSEnvelopedData(byte[] bArr) throws CMSException {
        this(CMSUtils.readContentInfo(bArr));
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm() {
        return this.encAlg;
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public byte[] getEncoded() throws IOException {
        return this.contentInfo.getEncoded();
    }

    public String getEncryptionAlgOID() {
        return this.encAlg.getAlgorithm().getId();
    }

    public byte[] getEncryptionAlgParams() {
        try {
            return encodeObj(this.encAlg.getParameters());
        } catch (Exception e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline68("exception getting encryption parameters ", e));
        }
    }

    public AlgorithmParameters getEncryptionAlgorithmParameters(String str) throws CMSException, NoSuchProviderException {
        return new JceAlgorithmIdentifierConverter().setProvider(str).getAlgorithmParameters(this.encAlg);
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.originatorInfo;
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.recipientInfoStore;
    }

    public AttributeTable getUnprotectedAttributes() {
        ASN1Set aSN1Set = this.unprotectedAttributes;
        if (aSN1Set == null) {
            return null;
        }
        return new AttributeTable(aSN1Set);
    }

    public ContentInfo toASN1Structure() {
        return this.contentInfo;
    }

    public CMSEnvelopedData(InputStream inputStream) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream));
    }

    public AlgorithmParameters getEncryptionAlgorithmParameters(Provider provider) throws CMSException {
        return new JceAlgorithmIdentifierConverter().setProvider(provider).getAlgorithmParameters(this.encAlg);
    }

    public CMSEnvelopedData(ContentInfo contentInfo) throws CMSException {
        this.contentInfo = contentInfo;
        try {
            EnvelopedData envelopedData = EnvelopedData.getInstance(contentInfo.getContent());
            if (envelopedData.getOriginatorInfo() != null) {
                this.originatorInfo = new OriginatorInformation(envelopedData.getOriginatorInfo());
            }
            ASN1Set recipientInfos = envelopedData.getRecipientInfos();
            EncryptedContentInfo encryptedContentInfo = envelopedData.getEncryptedContentInfo();
            this.encAlg = encryptedContentInfo.getContentEncryptionAlgorithm();
            this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, this.encAlg, new CMSEnvelopedHelper.CMSEnvelopedSecureReadable(this.encAlg, new CMSProcessableByteArray(encryptedContentInfo.getEncryptedContent().getOctets())));
            this.unprotectedAttributes = envelopedData.getUnprotectedAttrs();
        } catch (ClassCastException e) {
            throw new CMSException("Malformed content.", e);
        } catch (IllegalArgumentException e2) {
            throw new CMSException("Malformed content.", e2);
        }
    }
}
