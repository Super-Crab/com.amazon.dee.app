package com.amazon.alexa.device.setup.echo.bouncycastle.operator.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.DefaultJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.NamedJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.ProviderJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.AsymmetricKeyWrapper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.OperatorException;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.ProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
/* loaded from: classes.dex */
public class JceAsymmetricKeyWrapper extends AsymmetricKeyWrapper {
    private final Map extraMappings;
    private OperatorHelper helper;
    private final PublicKey publicKey;
    private SecureRandom random;

    public JceAsymmetricKeyWrapper(PublicKey publicKey) {
        super(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getAlgorithm());
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.publicKey = publicKey;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        byte[] bArr;
        Cipher createAsymmetricWrapper = this.helper.createAsymmetricWrapper(getAlgorithmIdentifier().getAlgorithm(), this.extraMappings);
        try {
            createAsymmetricWrapper.init(3, this.publicKey, this.random);
            bArr = createAsymmetricWrapper.wrap(OperatorUtils.getJceKey(genericKey));
        } catch (IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException unused) {
            bArr = null;
        }
        if (bArr == null) {
            try {
                createAsymmetricWrapper.init(1, this.publicKey, this.random);
                return createAsymmetricWrapper.doFinal(OperatorUtils.getJceKey(genericKey).getEncoded());
            } catch (GeneralSecurityException e) {
                throw new OperatorException("unable to encrypt contents key", e);
            }
        }
        return bArr;
    }

    public JceAsymmetricKeyWrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceAsymmetricKeyWrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JceAsymmetricKeyWrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JceAsymmetricKeyWrapper(X509Certificate x509Certificate) {
        this(x509Certificate.getPublicKey());
    }
}
