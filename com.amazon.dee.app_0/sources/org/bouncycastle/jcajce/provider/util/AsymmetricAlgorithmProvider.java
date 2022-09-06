package org.bouncycastle.jcajce.provider.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
/* loaded from: classes4.dex */
public abstract class AsymmetricAlgorithmProvider extends AlgorithmProvider {
    /* JADX INFO: Access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, String str3, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String outline75 = GeneratedOutlineSupport1.outline75(str, "WITH", str2);
        String outline752 = GeneratedOutlineSupport1.outline75(str, JsonPOJOBuilder.DEFAULT_WITH_PREFIX, str2);
        String outline753 = GeneratedOutlineSupport1.outline75(str, "With", str2);
        String outline754 = GeneratedOutlineSupport1.outline75(str, "/", str2);
        configurableProvider.addAlgorithm("Signature." + outline75, str3);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + outline752, outline75);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + outline753, outline75);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + outline754, outline75);
        StringBuilder sb = new StringBuilder();
        sb.append("Alg.Alias.Signature.");
        GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline119(sb, aSN1ObjectIdentifier, configurableProvider, outline75, "Alg.Alias.Signature.OID."), aSN1ObjectIdentifier, configurableProvider, outline75);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        configurableProvider.addAlgorithm("Signature." + str, str2);
        StringBuilder sb = new StringBuilder();
        sb.append("Alg.Alias.Signature.");
        GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline119(sb, aSN1ObjectIdentifier, configurableProvider, str, "Alg.Alias.Signature.OID."), aSN1ObjectIdentifier, configurableProvider, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerOid(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        configurableProvider.addAlgorithm("Alg.Alias.KeyFactory." + aSN1ObjectIdentifier, str);
        StringBuilder sb = new StringBuilder();
        sb.append("Alg.Alias.KeyPairGenerator.");
        GeneratedOutlineSupport1.outline183(sb, aSN1ObjectIdentifier, configurableProvider, str);
        configurableProvider.addKeyInfoConverter(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerOidAlgorithmParameterGenerator(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + aSN1ObjectIdentifier, str);
        StringBuilder sb = new StringBuilder();
        sb.append("Alg.Alias.AlgorithmParameters.");
        GeneratedOutlineSupport1.outline183(sb, aSN1ObjectIdentifier, configurableProvider, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerOidAlgorithmParameters(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + aSN1ObjectIdentifier, str);
    }
}
