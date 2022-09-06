package com.amazon.whispercloak.signature;

import android.util.Log;
import com.amazon.whispercloak.KeyUtils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/* loaded from: classes13.dex */
public class SignatureVerifier {
    private static final String TAG = "com.amazon.whispercloak.signature.SignatureVerifier";
    private final KeyUtils mKeyUtils;

    public SignatureVerifier() {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
        this.mKeyUtils = new KeyUtils();
    }

    public boolean isValidSignature(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, InvalidKeyException, SignatureException {
        PublicKey ecPublicKeyFromDerEncodedByteArray = this.mKeyUtils.getEcPublicKeyFromDerEncodedByteArray(bArr2);
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initVerify(ecPublicKeyFromDerEncodedByteArray);
            signature.update(bArr);
            return signature.verify(bArr3);
        } catch (InvalidKeyException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        } catch (SignatureException e2) {
            Log.e(TAG, e2.getMessage());
            throw e2;
        }
    }
}
