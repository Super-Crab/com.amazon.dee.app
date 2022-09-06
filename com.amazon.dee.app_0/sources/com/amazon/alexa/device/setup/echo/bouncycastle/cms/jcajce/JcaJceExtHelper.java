package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.SymmetricKeyUnwrapper;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import java.security.PrivateKey;
import javax.crypto.SecretKey;
/* loaded from: classes.dex */
public interface JcaJceExtHelper extends JcaJceHelper {
    JceAsymmetricKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey);

    SymmetricKeyUnwrapper createSymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey);
}
