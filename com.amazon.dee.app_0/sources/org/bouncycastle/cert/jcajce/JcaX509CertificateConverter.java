package org.bouncycastle.cert.jcajce;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes4.dex */
public class JcaX509CertificateConverter {
    private CertHelper helper;

    /* loaded from: classes4.dex */
    private class ExCertificateException extends CertificateException {
        private Throwable cause;

        public ExCertificateException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    /* loaded from: classes4.dex */
    private class ExCertificateParsingException extends CertificateParsingException {
        private Throwable cause;

        public ExCertificateParsingException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    public JcaX509CertificateConverter() {
        this.helper = new DefaultCertHelper();
        this.helper = new DefaultCertHelper();
    }

    public X509Certificate getCertificate(X509CertificateHolder x509CertificateHolder) throws CertificateException {
        try {
            return (X509Certificate) this.helper.getCertificateFactory(KeyUtils.X509_CERITIFATE_FACTORY).generateCertificate(new ByteArrayInputStream(x509CertificateHolder.getEncoded()));
        } catch (IOException e) {
            throw new ExCertificateParsingException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception parsing certificate: ")), e);
        } catch (NoSuchProviderException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot find required provider:");
            outline107.append(e2.getMessage());
            throw new ExCertificateException(outline107.toString(), e2);
        }
    }

    public JcaX509CertificateConverter setProvider(String str) {
        this.helper = new NamedCertHelper(str);
        return this;
    }

    public JcaX509CertificateConverter setProvider(Provider provider) {
        this.helper = new ProviderCertHelper(provider);
        return this;
    }
}
