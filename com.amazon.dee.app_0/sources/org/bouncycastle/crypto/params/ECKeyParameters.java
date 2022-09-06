package org.bouncycastle.crypto.params;
/* loaded from: classes4.dex */
public class ECKeyParameters extends AsymmetricKeyParameter {
    private final ECDomainParameters parameters;

    /* JADX INFO: Access modifiers changed from: protected */
    public ECKeyParameters(boolean z, ECDomainParameters eCDomainParameters) {
        super(z);
        if (eCDomainParameters != null) {
            this.parameters = eCDomainParameters;
            return;
        }
        throw new NullPointerException("'parameters' cannot be null");
    }

    public ECDomainParameters getParameters() {
        return this.parameters;
    }
}
