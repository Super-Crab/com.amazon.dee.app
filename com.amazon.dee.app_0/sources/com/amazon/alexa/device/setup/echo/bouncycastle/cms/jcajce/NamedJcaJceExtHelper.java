package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.NamedJcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.SymmetricKeyUnwrapper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.jcajce.JceSymmetricKeyUnwrapper;
import java.security.PrivateKey;
import javax.crypto.SecretKey;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class NamedJcaJceExtHelper extends NamedJcaJceHelper implements JcaJceExtHelper {
    public NamedJcaJceExtHelper(String str) {
        super(str);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JcaJceExtHelper
    public JceAsymmetricKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        return new JceAsymmetricKeyUnwrapper(algorithmIdentifier, privateKey).setProvider(this.providerName);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JcaJceExtHelper
    public SymmetricKeyUnwrapper createSymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey) {
        return new JceSymmetricKeyUnwrapper(algorithmIdentifier, secretKey).setProvider(this.providerName);
    }
}
