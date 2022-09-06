package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceAlgorithmIdentifierConverter;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.io.Streams;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchProviderException;
import java.security.Provider;
/* loaded from: classes.dex */
public abstract class RecipientInformation {
    private final AuthAttributesProvider additionalData;
    protected AlgorithmIdentifier keyEncAlg;
    protected AlgorithmIdentifier messageAlgorithm;
    private RecipientOperator operator;
    private byte[] resultMac;
    protected RecipientId rid;
    protected CMSSecureReadable secureReadable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecipientInformation(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        this.keyEncAlg = algorithmIdentifier;
        this.messageAlgorithm = algorithmIdentifier2;
        this.secureReadable = cMSSecureReadable;
        this.additionalData = authAttributesProvider;
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public byte[] getContent(Key key, String str) throws CMSException, NoSuchProviderException {
        return getContent(key, CMSUtils.getProvider(str));
    }

    public byte[] getContentDigest() {
        CMSSecureReadable cMSSecureReadable = this.secureReadable;
        if (cMSSecureReadable instanceof CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable) {
            return ((CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable) cMSSecureReadable).getDigest();
        }
        return null;
    }

    public CMSTypedStream getContentStream(Key key, String str) throws CMSException, NoSuchProviderException {
        return getContentStream(key, CMSUtils.getProvider(str));
    }

    public abstract CMSTypedStream getContentStream(Key key, Provider provider) throws CMSException;

    public String getKeyEncryptionAlgOID() {
        return this.keyEncAlg.getObjectId().getId();
    }

    public byte[] getKeyEncryptionAlgParams() {
        try {
            return encodeObj(this.keyEncAlg.getParameters());
        } catch (Exception e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline68("exception getting encryption parameters ", e));
        }
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncAlg;
    }

    public AlgorithmParameters getKeyEncryptionAlgorithmParameters(String str) throws CMSException, NoSuchProviderException {
        return new JceAlgorithmIdentifierConverter().setProvider(str).getAlgorithmParameters(this.keyEncAlg);
    }

    public byte[] getMac() {
        if (this.resultMac == null && this.operator.isMacBased()) {
            AuthAttributesProvider authAttributesProvider = this.additionalData;
            if (authAttributesProvider != null) {
                try {
                    Streams.drain(this.operator.getInputStream(new ByteArrayInputStream(authAttributesProvider.getAuthAttributes().getEncoded("DER"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.resultMac = this.operator.getMac();
        }
        return this.resultMac;
    }

    public RecipientId getRID() {
        return this.rid;
    }

    protected abstract RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException, IOException;

    public byte[] getContent(Key key, Provider provider) throws CMSException {
        try {
            return CMSUtils.streamToByteArray(getContentStream(key, provider).getContentStream());
        } catch (IOException e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline65("unable to parse internal stream: ", e));
        }
    }

    public CMSTypedStream getContentStream(Recipient recipient) throws CMSException, IOException {
        this.operator = getRecipientOperator(recipient);
        if (this.additionalData != null) {
            return new CMSTypedStream(this.secureReadable.getInputStream());
        }
        return new CMSTypedStream(this.operator.getInputStream(this.secureReadable.getInputStream()));
    }

    public AlgorithmParameters getKeyEncryptionAlgorithmParameters(Provider provider) throws CMSException {
        return new JceAlgorithmIdentifierConverter().setProvider(provider).getAlgorithmParameters(this.keyEncAlg);
    }

    public byte[] getContent(Recipient recipient) throws CMSException {
        try {
            return CMSUtils.streamToByteArray(getContentStream(recipient).getContentStream());
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to parse internal stream: ")), e);
        }
    }
}
