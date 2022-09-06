package com.amazon.alexa.device.setup.echo.bouncycastle.operator;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes.dex */
public abstract class SymmetricKeyUnwrapper implements KeyUnwrapper {
    private final AlgorithmIdentifier algorithmId;

    /* JADX INFO: Access modifiers changed from: protected */
    public SymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier) {
        this.algorithmId = algorithmIdentifier;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.operator.KeyUnwrapper
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.algorithmId;
    }
}
