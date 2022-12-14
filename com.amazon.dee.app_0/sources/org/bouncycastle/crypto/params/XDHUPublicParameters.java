package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes4.dex */
public class XDHUPublicParameters implements CipherParameters {
    private AsymmetricKeyParameter ephemeralPublicKey;
    private AsymmetricKeyParameter staticPublicKey;

    public XDHUPublicParameters(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricKeyParameter asymmetricKeyParameter2) {
        if (asymmetricKeyParameter != null) {
            if (!(asymmetricKeyParameter instanceof X448PublicKeyParameters) && !(asymmetricKeyParameter instanceof X25519PublicKeyParameters)) {
                throw new IllegalArgumentException("only X25519 and X448 paramaters can be used");
            }
            if (asymmetricKeyParameter2 == null) {
                throw new NullPointerException("ephemeralPublicKey cannot be null");
            }
            if (!asymmetricKeyParameter.getClass().isAssignableFrom(asymmetricKeyParameter2.getClass())) {
                throw new IllegalArgumentException("static and ephemeral public keys have different domain parameters");
            }
            this.staticPublicKey = asymmetricKeyParameter;
            this.ephemeralPublicKey = asymmetricKeyParameter2;
            return;
        }
        throw new NullPointerException("staticPublicKey cannot be null");
    }

    public AsymmetricKeyParameter getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }

    public AsymmetricKeyParameter getStaticPublicKey() {
        return this.staticPublicKey;
    }
}
