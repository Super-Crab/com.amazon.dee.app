package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes4.dex */
public class SM2KeyExchangePublicParameters implements CipherParameters {
    private final ECPublicKeyParameters ephemeralPublicKey;
    private final ECPublicKeyParameters staticPublicKey;

    public SM2KeyExchangePublicParameters(ECPublicKeyParameters eCPublicKeyParameters, ECPublicKeyParameters eCPublicKeyParameters2) {
        if (eCPublicKeyParameters != null) {
            if (eCPublicKeyParameters2 == null) {
                throw new NullPointerException("ephemeralPublicKey cannot be null");
            }
            if (!eCPublicKeyParameters.getParameters().equals(eCPublicKeyParameters2.getParameters())) {
                throw new IllegalArgumentException("Static and ephemeral public keys have different domain parameters");
            }
            this.staticPublicKey = eCPublicKeyParameters;
            this.ephemeralPublicKey = eCPublicKeyParameters2;
            return;
        }
        throw new NullPointerException("staticPublicKey cannot be null");
    }

    public ECPublicKeyParameters getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }

    public ECPublicKeyParameters getStaticPublicKey() {
        return this.staticPublicKey;
    }
}
