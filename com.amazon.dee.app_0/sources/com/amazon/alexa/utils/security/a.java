package com.amazon.alexa.utils.security;

import android.content.pm.ApplicationInfo;
import android.content.pm.Signature;
import com.amazon.whispercloak.KeyUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
/* loaded from: classes10.dex */
class a {
    private static final String a = "a";

    private byte[] a(byte[] bArr) throws NoSuchAlgorithmException {
        if (bArr != null) {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        }
        return null;
    }

    private String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    private Certificate c(byte[] bArr) throws CertificateException, IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Certificate generateCertificate = CertificateFactory.getInstance(KeyUtils.X509_CERITIFATE_FACTORY).generateCertificate(byteArrayInputStream);
        byteArrayInputStream.close();
        return generateCertificate;
    }

    public String a(Signature signature) throws IOException, CertificateException, NoSuchAlgorithmException {
        return b(a(c(signature.toByteArray()).getEncoded()));
    }

    public boolean a(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 2) != 0;
    }
}
