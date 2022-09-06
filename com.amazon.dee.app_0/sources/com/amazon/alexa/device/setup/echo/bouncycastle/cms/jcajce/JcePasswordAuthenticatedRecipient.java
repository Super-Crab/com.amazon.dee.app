package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSException;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientOperator;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.io.MacOutputStream;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.MacCalculator;
import java.io.OutputStream;
import java.security.Key;
import javax.crypto.Mac;
/* loaded from: classes.dex */
public class JcePasswordAuthenticatedRecipient extends JcePasswordRecipient {
    public JcePasswordAuthenticatedRecipient(char[] cArr) {
        super(cArr);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.PasswordRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) throws CMSException {
        final Key extractSecretKey = extractSecretKey(algorithmIdentifier, algorithmIdentifier2, bArr, bArr2);
        final Mac createContentMac = this.helper.createContentMac(extractSecretKey, algorithmIdentifier2);
        return new RecipientOperator(new MacCalculator() { // from class: com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JcePasswordAuthenticatedRecipient.1
            @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.MacCalculator
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier2;
            }

            @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.MacCalculator
            public GenericKey getKey() {
                return new GenericKey(extractSecretKey);
            }

            @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.MacCalculator
            public byte[] getMac() {
                return createContentMac.doFinal();
            }

            @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.MacCalculator
            public OutputStream getOutputStream() {
                return new MacOutputStream(createContentMac);
            }
        });
    }
}
