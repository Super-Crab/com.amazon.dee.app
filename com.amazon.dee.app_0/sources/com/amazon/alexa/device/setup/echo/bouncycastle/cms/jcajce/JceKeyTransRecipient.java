package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSException;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.KeyTransRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.OperatorException;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class JceKeyTransRecipient implements KeyTransRecipient {
    private final PrivateKey recipientKey;
    protected EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    protected EnvelopedDataHelper contentHelper = this.helper;
    protected Map extraMappings = new HashMap();

    public JceKeyTransRecipient(PrivateKey privateKey) {
        this.recipientKey = privateKey;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CMSException {
        JceAsymmetricKeyUnwrapper createAsymmetricUnwrapper = this.helper.createAsymmetricUnwrapper(algorithmIdentifier, this.recipientKey);
        if (!this.extraMappings.isEmpty()) {
            for (ASN1ObjectIdentifier aSN1ObjectIdentifier : this.extraMappings.keySet()) {
                createAsymmetricUnwrapper.setAlgorithmMapping(aSN1ObjectIdentifier, (String) this.extraMappings.get(aSN1ObjectIdentifier));
            }
        }
        try {
            return this.helper.getJceKey(algorithmIdentifier2.getAlgorithm(), createAsymmetricUnwrapper.generateUnwrappedKey(algorithmIdentifier2, bArr));
        } catch (OperatorException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception unwrapping key: ");
            outline107.append(e.getMessage());
            throw new CMSException(outline107.toString(), e);
        }
    }

    public JceKeyTransRecipient setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceKeyTransRecipient setContentProvider(Provider provider) {
        this.contentHelper = CMSUtils.createContentHelper(provider);
        return this;
    }

    public JceKeyTransRecipient setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        this.contentHelper = this.helper;
        return this;
    }

    public JceKeyTransRecipient setContentProvider(String str) {
        this.contentHelper = CMSUtils.createContentHelper(str);
        return this;
    }

    public JceKeyTransRecipient setProvider(String str) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        this.contentHelper = this.helper;
        return this;
    }
}
