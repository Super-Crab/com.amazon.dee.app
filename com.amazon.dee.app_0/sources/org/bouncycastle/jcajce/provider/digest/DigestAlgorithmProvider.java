package org.bouncycastle.jcajce.provider.digest;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
/* loaded from: classes4.dex */
abstract class DigestAlgorithmProvider extends AlgorithmProvider {
    /* JADX INFO: Access modifiers changed from: protected */
    public void addHMACAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, String str3) {
        String outline72 = GeneratedOutlineSupport1.outline72("HMAC", str);
        configurableProvider.addAlgorithm("Mac." + outline72, str2);
        configurableProvider.addAlgorithm("Alg.Alias.Mac.HMAC-" + str, outline72);
        configurableProvider.addAlgorithm("Alg.Alias.Mac.HMAC/" + str, outline72);
        configurableProvider.addAlgorithm("KeyGenerator." + outline72, str3);
        configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.HMAC-" + str, outline72);
        configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.HMAC/" + str, outline72);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addHMACAlias(ConfigurableProvider configurableProvider, String str, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String outline72 = GeneratedOutlineSupport1.outline72("HMAC", str);
        configurableProvider.addAlgorithm("Alg.Alias.Mac." + aSN1ObjectIdentifier, outline72);
        StringBuilder sb = new StringBuilder();
        sb.append("Alg.Alias.KeyGenerator.");
        GeneratedOutlineSupport1.outline183(sb, aSN1ObjectIdentifier, configurableProvider, outline72);
    }
}
