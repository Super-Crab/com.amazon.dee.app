package org.bouncycastle.cert.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.Provider;
import java.security.cert.CertificateException;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509ContentVerifierProviderBuilder;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
/* loaded from: classes4.dex */
public class JcaX509ContentVerifierProviderBuilder implements X509ContentVerifierProviderBuilder {
    private JcaContentVerifierProviderBuilder builder = new JcaContentVerifierProviderBuilder();

    @Override // org.bouncycastle.cert.X509ContentVerifierProviderBuilder
    public ContentVerifierProvider build(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        return this.builder.build(subjectPublicKeyInfo);
    }

    @Override // org.bouncycastle.cert.X509ContentVerifierProviderBuilder
    public ContentVerifierProvider build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        try {
            return this.builder.build(x509CertificateHolder);
        } catch (CertificateException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to process certificate: ");
            outline107.append(e.getMessage());
            throw new OperatorCreationException(outline107.toString(), e);
        }
    }

    public JcaX509ContentVerifierProviderBuilder setProvider(String str) {
        this.builder.setProvider(str);
        return this;
    }

    public JcaX509ContentVerifierProviderBuilder setProvider(Provider provider) {
        this.builder.setProvider(provider);
        return this;
    }
}
