package org.bouncycastle.cms;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSEnvelopedHelper;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public abstract class RecipientInformation {
    private AuthAttributesProvider additionalData;
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

    public byte[] getContent(Recipient recipient) throws CMSException {
        try {
            return CMSUtils.streamToByteArray(getContentStream(recipient).getContentStream());
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to parse internal stream: ")), e);
        }
    }

    public byte[] getContentDigest() {
        CMSSecureReadable cMSSecureReadable = this.secureReadable;
        if (cMSSecureReadable instanceof CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable) {
            return ((CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable) cMSSecureReadable).getDigest();
        }
        return null;
    }

    public CMSTypedStream getContentStream(Recipient recipient) throws CMSException, IOException {
        this.operator = getRecipientOperator(recipient);
        AuthAttributesProvider authAttributesProvider = this.additionalData;
        if (authAttributesProvider != null) {
            if (!authAttributesProvider.isAead()) {
                return new CMSTypedStream(this.secureReadable.getInputStream());
            }
            this.operator.getAADStream().write(this.additionalData.getAuthAttributes().getEncoded("DER"));
            return new CMSTypedStream(this.operator.getInputStream(this.secureReadable.getInputStream()));
        }
        return new CMSTypedStream(this.operator.getInputStream(this.secureReadable.getInputStream()));
    }

    public String getKeyEncryptionAlgOID() {
        return this.keyEncAlg.getAlgorithm().getId();
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

    public byte[] getMac() {
        if (this.resultMac == null && this.operator.isMacBased()) {
            AuthAttributesProvider authAttributesProvider = this.additionalData;
            if (authAttributesProvider != null) {
                try {
                    Streams.drain(this.operator.getInputStream(new ByteArrayInputStream(authAttributesProvider.getAuthAttributes().getEncoded("DER"))));
                } catch (IOException e) {
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to drain input: ")));
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
}
