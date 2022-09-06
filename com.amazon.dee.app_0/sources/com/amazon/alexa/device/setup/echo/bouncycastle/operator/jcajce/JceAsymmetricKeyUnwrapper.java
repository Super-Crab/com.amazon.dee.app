package com.amazon.alexa.device.setup.echo.bouncycastle.operator.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.DefaultJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.NamedJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.ProviderJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.AsymmetricKeyUnwrapper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.OperatorException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.ProviderException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public class JceAsymmetricKeyUnwrapper extends AsymmetricKeyUnwrapper {
    private final Map extraMappings;
    private OperatorHelper helper;
    private final PrivateKey privKey;

    public JceAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        super(algorithmIdentifier);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.privKey = privateKey;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.KeyUnwrapper
    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) throws OperatorException {
        Key key = null;
        try {
            Cipher createAsymmetricWrapper = this.helper.createAsymmetricWrapper(getAlgorithmIdentifier().getAlgorithm(), this.extraMappings);
            try {
                createAsymmetricWrapper.init(4, this.privKey);
                key = createAsymmetricWrapper.unwrap(bArr, this.helper.getKeyAlgorithmName(algorithmIdentifier.getAlgorithm()), 3);
            } catch (IllegalStateException | UnsupportedOperationException | GeneralSecurityException | ProviderException unused) {
            }
            if (key == null) {
                createAsymmetricWrapper.init(2, this.privKey);
                key = new SecretKeySpec(createAsymmetricWrapper.doFinal(bArr), algorithmIdentifier.getAlgorithm().getId());
            }
            return new GenericKey(key);
        } catch (InvalidKeyException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("key invalid: ");
            outline107.append(e.getMessage());
            throw new OperatorException(outline107.toString(), e);
        } catch (BadPaddingException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("bad padding: ");
            outline1072.append(e2.getMessage());
            throw new OperatorException(outline1072.toString(), e2);
        } catch (IllegalBlockSizeException e3) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("illegal blocksize: ");
            outline1073.append(e3.getMessage());
            throw new OperatorException(outline1073.toString(), e3);
        }
    }

    public JceAsymmetricKeyUnwrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }
}
