package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSException;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientOperator;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.InputDecryptor;
import java.io.InputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
/* loaded from: classes.dex */
public class JceKEKEnvelopedRecipient extends JceKEKRecipient {
    public JceKEKEnvelopedRecipient(SecretKey secretKey) {
        super(secretKey);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.KEKRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CMSException {
        final Cipher createContentCipher = this.contentHelper.createContentCipher(extractSecretKey(algorithmIdentifier, algorithmIdentifier2, bArr), algorithmIdentifier2);
        return new RecipientOperator(new InputDecryptor() { // from class: com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKEKEnvelopedRecipient.1
            @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.InputDecryptor
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier2;
            }

            @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.InputDecryptor
            public InputStream getInputStream(InputStream inputStream) {
                return new CipherInputStream(inputStream, createContentCipher);
            }
        });
    }
}
