package org.bouncycastle.jcajce.provider.asymmetric;

import com.amazon.whispercloak.KeyUtils;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
/* loaded from: classes4.dex */
public class X509 {

    /* loaded from: classes4.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.X.509", "org.bouncycastle.jcajce.provider.asymmetric.x509.KeyFactory");
            configurableProvider.addAlgorithm("Alg.Alias.KeyFactory.X509", KeyUtils.X509_CERITIFATE_FACTORY);
            configurableProvider.addAlgorithm("CertificateFactory.X.509", "org.bouncycastle.jcajce.provider.asymmetric.x509.CertificateFactory");
            configurableProvider.addAlgorithm("Alg.Alias.CertificateFactory.X509", KeyUtils.X509_CERITIFATE_FACTORY);
        }
    }
}
