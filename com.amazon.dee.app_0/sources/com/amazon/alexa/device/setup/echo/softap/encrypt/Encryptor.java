package com.amazon.alexa.device.setup.echo.softap.encrypt;

import android.util.Base64;
import android.util.Log;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSAlgorithm;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedDataGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSProcessableByteArray;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import com.amazon.device.setup.thrift.PKCS7Type;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
/* loaded from: classes6.dex */
public final class Encryptor {
    private static final String CERTIFICATE_TYPE = "X.509";
    private static final String TAG = "Encryptor";

    private Encryptor() {
    }

    public static PKCS7Type encrypt(String str, String str2) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str2.getBytes("UTF-8"));
            CMSEnvelopedDataGenerator cMSEnvelopedDataGenerator = new CMSEnvelopedDataGenerator();
            cMSEnvelopedDataGenerator.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream)));
            String encodeToString = Base64.encodeToString(cMSEnvelopedDataGenerator.generate(new CMSProcessableByteArray(str.getBytes("UTF-8")), new JceCMSContentEncryptorBuilder(CMSAlgorithm.AES256_CBC).build()).getEncoded(), 0);
            return new PKCS7Type("-----BEGIN PKCS7-----\n" + encodeToString + "-----END PKCS7-----\n");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return new PKCS7Type();
        }
    }
}
