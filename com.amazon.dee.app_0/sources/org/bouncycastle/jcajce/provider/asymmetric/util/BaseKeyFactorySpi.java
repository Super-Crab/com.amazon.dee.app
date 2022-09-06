package org.bouncycastle.jcajce.provider.asymmetric.util;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
/* loaded from: classes4.dex */
public abstract class BaseKeyFactorySpi extends KeyFactorySpi implements AsymmetricKeyInfoConverter {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.security.KeyFactorySpi
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            try {
                return generatePrivate(PrivateKeyInfo.getInstance(((PKCS8EncodedKeySpec) keySpec).getEncoded()));
            } catch (Exception e) {
                throw new InvalidKeySpecException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("encoded key spec not recognized: ")));
            }
        }
        throw new InvalidKeySpecException("key spec not recognized");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.security.KeyFactorySpi
    public PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof X509EncodedKeySpec) {
            try {
                return generatePublic(SubjectPublicKeyInfo.getInstance(((X509EncodedKeySpec) keySpec).getEncoded()));
            } catch (Exception e) {
                throw new InvalidKeySpecException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("encoded key spec not recognized: ")));
            }
        }
        throw new InvalidKeySpecException("key spec not recognized");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.security.KeyFactorySpi
    public KeySpec engineGetKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        if (!cls.isAssignableFrom(PKCS8EncodedKeySpec.class) || !key.getFormat().equals("PKCS#8")) {
            if (cls.isAssignableFrom(X509EncodedKeySpec.class) && key.getFormat().equals(KeyUtils.X509_CERITIFATE_FACTORY)) {
                return new X509EncodedKeySpec(key.getEncoded());
            }
            throw new InvalidKeySpecException("not implemented yet " + key + " " + cls);
        }
        return new PKCS8EncodedKeySpec(key.getEncoded());
    }
}
