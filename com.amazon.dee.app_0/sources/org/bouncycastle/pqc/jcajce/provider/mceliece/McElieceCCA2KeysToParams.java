package org.bouncycastle.pqc.jcajce.provider.mceliece;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes5.dex */
public class McElieceCCA2KeysToParams {
    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof BCMcElieceCCA2PrivateKey) {
            return ((BCMcElieceCCA2PrivateKey) privateKey).getKeyParams();
        }
        throw new InvalidKeyException("can't identify McElieceCCA2 private key.");
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof BCMcElieceCCA2PublicKey) {
            return ((BCMcElieceCCA2PublicKey) publicKey).getKeyParams();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("can't identify McElieceCCA2 public key: ");
        outline107.append(publicKey.getClass().getName());
        throw new InvalidKeyException(outline107.toString());
    }
}
