package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSException;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.KEKRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.OperatorException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.Key;
import java.security.Provider;
import javax.crypto.SecretKey;
/* loaded from: classes.dex */
public abstract class JceKEKRecipient implements KEKRecipient {
    private final SecretKey recipientKey;
    protected EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    protected EnvelopedDataHelper contentHelper = this.helper;

    public JceKEKRecipient(SecretKey secretKey) {
        this.recipientKey = secretKey;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CMSException {
        try {
            return this.helper.getJceKey(algorithmIdentifier2.getAlgorithm(), this.helper.createSymmetricUnwrapper(algorithmIdentifier, this.recipientKey).generateUnwrappedKey(algorithmIdentifier2, bArr));
        } catch (OperatorException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception unwrapping key: ");
            outline107.append(e.getMessage());
            throw new CMSException(outline107.toString(), e);
        }
    }

    public JceKEKRecipient setContentProvider(Provider provider) {
        this.contentHelper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKEKRecipient setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        this.contentHelper = this.helper;
        return this;
    }

    public JceKEKRecipient setContentProvider(String str) {
        this.contentHelper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        return this;
    }

    public JceKEKRecipient setProvider(String str) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        this.contentHelper = this.helper;
        return this;
    }
}
