package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.PBEParametersGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.params.KeyParameter;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
/* loaded from: classes.dex */
public class PKCS5Scheme2UTF8PBEKey extends CMSPBEKey {
    public PKCS5Scheme2UTF8PBEKey(char[] cArr, byte[] bArr, int i) {
        super(cArr, bArr, i);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSPBEKey
    byte[] getEncoded(String str) {
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator();
        pKCS5S2ParametersGenerator.init(PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(getPassword()), getSalt(), getIterationCount());
        return ((KeyParameter) pKCS5S2ParametersGenerator.generateDerivedParameters(CMSEnvelopedHelper.INSTANCE.getKeySize(str))).getKey();
    }

    public PKCS5Scheme2UTF8PBEKey(char[] cArr, AlgorithmParameters algorithmParameters) throws InvalidAlgorithmParameterException {
        super(cArr, CMSPBEKey.getParamSpec(algorithmParameters));
    }
}
