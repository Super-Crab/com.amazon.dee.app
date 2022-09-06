package org.bouncycastle.cert.jcajce;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import org.bouncycastle.cert.X509CRLHolder;
/* loaded from: classes4.dex */
public class JcaX509CRLConverter {
    private CertHelper helper;

    /* loaded from: classes4.dex */
    private class ExCRLException extends CRLException {
        private Throwable cause;

        public ExCRLException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    public JcaX509CRLConverter() {
        this.helper = new DefaultCertHelper();
        this.helper = new DefaultCertHelper();
    }

    public X509CRL getCRL(X509CRLHolder x509CRLHolder) throws CRLException {
        try {
            return (X509CRL) this.helper.getCertificateFactory(KeyUtils.X509_CERITIFATE_FACTORY).generateCRL(new ByteArrayInputStream(x509CRLHolder.getEncoded()));
        } catch (IOException e) {
            throw new ExCRLException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception parsing certificate: ")), e);
        } catch (NoSuchProviderException e2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot find required provider:");
            outline107.append(e2.getMessage());
            throw new ExCRLException(outline107.toString(), e2);
        } catch (CertificateException e3) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("cannot create factory: ");
            outline1072.append(e3.getMessage());
            throw new ExCRLException(outline1072.toString(), e3);
        }
    }

    public JcaX509CRLConverter setProvider(String str) {
        this.helper = new NamedCertHelper(str);
        return this;
    }

    public JcaX509CRLConverter setProvider(Provider provider) {
        this.helper = new ProviderCertHelper(provider);
        return this;
    }
}
