package com.amazon.alexa.device.setup.echo.bouncycastle.cert.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.Certificate;
import com.amazon.alexa.device.setup.echo.bouncycastle.cert.X509CertificateHolder;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
/* loaded from: classes.dex */
public class JcaX509CertificateHolder extends X509CertificateHolder {
    public JcaX509CertificateHolder(X509Certificate x509Certificate) throws CertificateEncodingException {
        super(Certificate.getInstance(x509Certificate.getEncoded()));
    }
}
