package org.bouncycastle.pqc.jcajce.provider;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
/* loaded from: classes5.dex */
public class LMS {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.lms.";

    /* loaded from: classes5.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "KeyFactory.LMS", "org.bouncycastle.pqc.jcajce.provider.lms.LMSKeyFactorySpi", "Alg.Alias.KeyFactory."), PKCSObjectIdentifiers.id_alg_hss_lms_hashsig, configurableProvider, "LMS");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "KeyPairGenerator.LMS", "org.bouncycastle.pqc.jcajce.provider.lms.LMSKeyPairGeneratorSpi", "Alg.Alias.KeyPairGenerator."), PKCSObjectIdentifiers.id_alg_hss_lms_hashsig, configurableProvider, "LMS");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline121(configurableProvider, "Signature.LMS", "org.bouncycastle.pqc.jcajce.provider.lms.LMSSignatureSpi$generic", "Alg.Alias.Signature."), PKCSObjectIdentifiers.id_alg_hss_lms_hashsig, configurableProvider, "LMS");
        }
    }
}
