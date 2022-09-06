package com.amazonaws.mobileconnectors.iot;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
/* loaded from: classes13.dex */
final class RSA {
    private static final String RSA = "RSA";

    private RSA() {
    }

    private static RSAPrivateCrtKeySpec newRSAPrivateCrtKeySpec(byte[] bArr) throws IOException {
        Asn1Object read = new DerParser(bArr).read();
        if (read.getType() == 16) {
            DerParser parser = read.getParser();
            parser.read();
            return new RSAPrivateCrtKeySpec(parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger(), parser.read().getInteger());
        }
        throw new IllegalArgumentException("Invalid DER: not a sequence");
    }

    public static PrivateKey privateKeyFromPKCS1(byte[] bArr) throws InvalidKeySpecException {
        try {
            return KeyFactory.getInstance("RSA").generatePrivate(newRSAPrivateCrtKeySpec(bArr));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException(e2);
        }
    }
}
