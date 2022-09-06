package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KEKIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.KEKRecipientInfoGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.jcajce.JceSymmetricKeyWrapper;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
/* loaded from: classes.dex */
public class JceKEKRecipientInfoGenerator extends KEKRecipientInfoGenerator {
    public JceKEKRecipientInfoGenerator(KEKIdentifier kEKIdentifier, SecretKey secretKey) {
        super(kEKIdentifier, new JceSymmetricKeyWrapper(secretKey));
    }

    public JceKEKRecipientInfoGenerator setProvider(Provider provider) {
        ((JceSymmetricKeyWrapper) this.wrapper).setProvider(provider);
        return this;
    }

    public JceKEKRecipientInfoGenerator setSecureRandom(SecureRandom secureRandom) {
        ((JceSymmetricKeyWrapper) this.wrapper).setSecureRandom(secureRandom);
        return this;
    }

    public JceKEKRecipientInfoGenerator(byte[] bArr, SecretKey secretKey) {
        this(new KEKIdentifier(bArr, null, null), secretKey);
    }

    public JceKEKRecipientInfoGenerator setProvider(String str) {
        ((JceSymmetricKeyWrapper) this.wrapper).setProvider(str);
        return this;
    }
}
