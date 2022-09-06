package com.amazon.alexa.device.setup.echo.bouncycastle.operator;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes.dex */
public abstract class AsymmetricKeyWrapper implements KeyWrapper {
    private final AlgorithmIdentifier algorithmId;

    /* JADX INFO: Access modifiers changed from: protected */
    public AsymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier) {
        this.algorithmId = algorithmIdentifier;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.KeyWrapper
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.algorithmId;
    }
}
