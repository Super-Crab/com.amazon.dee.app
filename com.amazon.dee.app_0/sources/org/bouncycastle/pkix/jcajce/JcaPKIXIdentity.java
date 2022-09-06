package org.bouncycastle.pkix.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.pkix.PKIXIdentity;
/* loaded from: classes5.dex */
public class JcaPKIXIdentity extends PKIXIdentity {
    private final X509Certificate[] certs;
    private final PrivateKey privKey;

    public JcaPKIXIdentity(PrivateKey privateKey, X509Certificate x509Certificate) {
        this(privateKey, new X509Certificate[]{x509Certificate});
    }

    public JcaPKIXIdentity(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
        super(getPrivateKeyInfo(privateKey), getCertificates(x509CertificateArr));
        this.privKey = privateKey;
        this.certs = new X509Certificate[x509CertificateArr.length];
        System.arraycopy(x509CertificateArr, 0, this.certs, 0, x509CertificateArr.length);
    }

    private static X509CertificateHolder[] getCertificates(X509Certificate[] x509CertificateArr) {
        X509CertificateHolder[] x509CertificateHolderArr = new X509CertificateHolder[x509CertificateArr.length];
        for (int i = 0; i != x509CertificateHolderArr.length; i++) {
            try {
                x509CertificateHolderArr[i] = new JcaX509CertificateHolder(x509CertificateArr[i]);
            } catch (CertificateEncodingException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to process certificates: ");
                outline107.append(e.getMessage());
                throw new IllegalArgumentException(outline107.toString());
            }
        }
        return x509CertificateHolderArr;
    }

    private static PrivateKeyInfo getPrivateKeyInfo(PrivateKey privateKey) {
        try {
            return PrivateKeyInfo.getInstance(privateKey.getEncoded());
        } catch (Exception unused) {
            return null;
        }
    }

    public PrivateKey getPrivateKey() {
        return this.privKey;
    }

    public X509Certificate getX509Certificate() {
        return this.certs[0];
    }

    public X509Certificate[] getX509CertificateChain() {
        X509Certificate[] x509CertificateArr = this.certs;
        X509Certificate[] x509CertificateArr2 = new X509Certificate[x509CertificateArr.length];
        System.arraycopy(x509CertificateArr, 0, x509CertificateArr2, 0, x509CertificateArr2.length);
        return x509CertificateArr2;
    }
}
