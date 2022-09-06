package com.dp.utils;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
/* loaded from: classes2.dex */
public final class KeyEncoder {
    private static final String KEYGEN_ALGORITHM = "RSA";

    private KeyEncoder() {
    }

    public static PrivateKey decodeRsaPrivateKey(String str) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str)));
    }

    public static String encodeKey(Key key) {
        Key generatePrivate;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            if (key instanceof PublicKey) {
                generatePrivate = keyFactory.generatePublic(new X509EncodedKeySpec(key.getEncoded()));
            } else if (key instanceof PrivateKey) {
                generatePrivate = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(key.getEncoded()));
            } else {
                throw new IllegalArgumentException("key is of unknown class type, must be PublicKey or PrivateKey");
            }
            if (isBinaryFormat(generatePrivate.getFormat())) {
                return Base64.encodeString(generatePrivate.getEncoded());
            }
            try {
                return new String(generatePrivate.getEncoded(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new IllegalArgumentException("Charset UTF-8 not supported when encoding text key as string", e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        } catch (InvalidKeySpecException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static String getEncoding(Key key) {
        String format = key.getFormat();
        return isBinaryFormat(format) ? GeneratedOutlineSupport1.outline72("B64/", format) : format;
    }

    private static boolean isBinaryFormat(String str) {
        if (!KeyUtils.X509_CERITIFATE_FACTORY.equals(str) && !"PKCS#8".equals(str)) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unrecognized key foramt ", str));
        }
        return true;
    }
}
