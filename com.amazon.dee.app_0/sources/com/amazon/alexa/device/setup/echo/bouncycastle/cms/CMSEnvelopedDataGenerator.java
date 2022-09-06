package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.BEROctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.BERSet;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSet;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.ContentInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.EncryptedContentInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.EnvelopedData;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.OutputEncryptor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.HashMap;
/* loaded from: classes.dex */
public class CMSEnvelopedDataGenerator extends CMSEnvelopedGenerator {
    public CMSEnvelopedDataGenerator() {
    }

    private CMSEnvelopedData doGenerate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        if (this.oldRecipientInfoGenerators.isEmpty()) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                OutputStream outputStream = outputEncryptor.getOutputStream(byteArrayOutputStream);
                cMSTypedData.write(outputStream);
                outputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                AlgorithmIdentifier algorithmIdentifier = outputEncryptor.getAlgorithmIdentifier();
                BEROctetString bEROctetString = new BEROctetString(byteArray);
                GenericKey key = outputEncryptor.getKey();
                for (RecipientInfoGenerator recipientInfoGenerator : this.recipientInfoGenerators) {
                    aSN1EncodableVector.add(recipientInfoGenerator.generate(key));
                }
                EncryptedContentInfo encryptedContentInfo = new EncryptedContentInfo(cMSTypedData.getContentType(), algorithmIdentifier, bEROctetString);
                BERSet bERSet = null;
                CMSAttributeTableGenerator cMSAttributeTableGenerator = this.unprotectedAttributeGenerator;
                if (cMSAttributeTableGenerator != null) {
                    bERSet = new BERSet(cMSAttributeTableGenerator.getAttributes(new HashMap()).toASN1EncodableVector());
                }
                return new CMSEnvelopedData(new ContentInfo(CMSObjectIdentifiers.envelopedData, new EnvelopedData(this.originatorInfo, new DERSet(aSN1EncodableVector), encryptedContentInfo, bERSet)));
            } catch (IOException unused) {
                throw new CMSException("");
            }
        }
        throw new IllegalStateException("can only use addRecipientGenerator() with this method");
    }

    private CMSEnvelopedData generate(final CMSProcessable cMSProcessable, String str, int i, Provider provider, Provider provider2) throws NoSuchAlgorithmException, CMSException {
        JceCMSContentEncryptorBuilder jceCMSContentEncryptorBuilder;
        convertOldRecipients(this.rand, provider2);
        if (i != -1) {
            jceCMSContentEncryptorBuilder = new JceCMSContentEncryptorBuilder(new ASN1ObjectIdentifier(str), i);
        } else {
            jceCMSContentEncryptorBuilder = new JceCMSContentEncryptorBuilder(new ASN1ObjectIdentifier(str));
        }
        jceCMSContentEncryptorBuilder.setProvider(provider);
        jceCMSContentEncryptorBuilder.setSecureRandom(this.rand);
        return doGenerate(new CMSTypedData() { // from class: com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedDataGenerator.1
            @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSProcessable
            public Object getContent() {
                return cMSProcessable;
            }

            @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSTypedData
            public ASN1ObjectIdentifier getContentType() {
                return CMSObjectIdentifiers.data;
            }

            @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSProcessable
            public void write(OutputStream outputStream) throws IOException, CMSException {
                cMSProcessable.write(outputStream);
            }
        }, jceCMSContentEncryptorBuilder.build());
    }

    public CMSEnvelopedDataGenerator(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public CMSEnvelopedData generate(CMSProcessable cMSProcessable, String str, String str2) throws NoSuchAlgorithmException, NoSuchProviderException, CMSException {
        return generate(cMSProcessable, str, CMSUtils.getProvider(str2));
    }

    public CMSEnvelopedData generate(CMSProcessable cMSProcessable, String str, Provider provider) throws NoSuchAlgorithmException, CMSException {
        return generate(cMSProcessable, str, -1, CMSEnvelopedHelper.INSTANCE.createSymmetricKeyGenerator(str, provider).getProvider(), provider);
    }

    public CMSEnvelopedData generate(CMSProcessable cMSProcessable, String str, int i, String str2) throws NoSuchAlgorithmException, NoSuchProviderException, CMSException {
        return generate(cMSProcessable, str, i, CMSUtils.getProvider(str2));
    }

    public CMSEnvelopedData generate(CMSProcessable cMSProcessable, String str, int i, Provider provider) throws NoSuchAlgorithmException, NoSuchProviderException, CMSException {
        return generate(cMSProcessable, str, i, CMSEnvelopedHelper.INSTANCE.createSymmetricKeyGenerator(str, provider).getProvider(), provider);
    }

    public CMSEnvelopedData generate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        return doGenerate(cMSTypedData, outputEncryptor);
    }
}
