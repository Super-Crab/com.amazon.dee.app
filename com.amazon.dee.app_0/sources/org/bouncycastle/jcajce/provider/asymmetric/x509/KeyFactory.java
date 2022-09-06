package org.bouncycastle.jcajce.provider.asymmetric.x509;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/* loaded from: classes4.dex */
public class KeyFactory extends KeyFactorySpi {
    @Override // java.security.KeyFactorySpi
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (!(keySpec instanceof PKCS8EncodedKeySpec)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown KeySpec type: ");
            outline107.append(keySpec.getClass().getName());
            throw new InvalidKeySpecException(outline107.toString());
        }
        try {
            PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(((PKCS8EncodedKeySpec) keySpec).getEncoded());
            PrivateKey privateKey = BouncyCastleProvider.getPrivateKey(privateKeyInfo);
            if (privateKey != null) {
                return privateKey;
            }
            throw new InvalidKeySpecException("no factory found for OID: " + privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
        } catch (Exception e) {
            throw new InvalidKeySpecException(e.toString());
        }
    }

    @Override // java.security.KeyFactorySpi
    protected PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (!(keySpec instanceof X509EncodedKeySpec)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown KeySpec type: ");
            outline107.append(keySpec.getClass().getName());
            throw new InvalidKeySpecException(outline107.toString());
        }
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(((X509EncodedKeySpec) keySpec).getEncoded());
            PublicKey publicKey = BouncyCastleProvider.getPublicKey(subjectPublicKeyInfo);
            if (publicKey != null) {
                return publicKey;
            }
            throw new InvalidKeySpecException("no factory found for OID: " + subjectPublicKeyInfo.getAlgorithm().getAlgorithm());
        } catch (Exception e) {
            throw new InvalidKeySpecException(e.toString());
        }
    }

    @Override // java.security.KeyFactorySpi
    protected KeySpec engineGetKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        if (!cls.isAssignableFrom(PKCS8EncodedKeySpec.class) || !key.getFormat().equals("PKCS#8")) {
            if (cls.isAssignableFrom(X509EncodedKeySpec.class) && key.getFormat().equals(KeyUtils.X509_CERITIFATE_FACTORY)) {
                return new X509EncodedKeySpec(key.getEncoded());
            }
            throw new InvalidKeySpecException("not implemented yet " + key + " " + cls);
        }
        return new PKCS8EncodedKeySpec(key.getEncoded());
    }

    @Override // java.security.KeyFactorySpi
    protected Key engineTranslateKey(Key key) throws InvalidKeyException {
        throw new InvalidKeyException("not implemented yet " + key);
    }
}
