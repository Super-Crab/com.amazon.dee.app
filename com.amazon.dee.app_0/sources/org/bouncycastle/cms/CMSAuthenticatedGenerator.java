package org.bouncycastle.cms;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class CMSAuthenticatedGenerator extends CMSEnvelopedGenerator {
    protected CMSAttributeTableGenerator authGen;
    protected CMSAttributeTableGenerator unauthGen;

    /* JADX INFO: Access modifiers changed from: protected */
    public Map getBaseParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("contentType", aSN1ObjectIdentifier);
        hashMap.put("digestAlgID", algorithmIdentifier);
        hashMap.put("digest", Arrays.clone(bArr));
        hashMap.put(CMSAttributeTableGenerator.MAC_ALGORITHM_IDENTIFIER, algorithmIdentifier2);
        return hashMap;
    }

    public void setAuthenticatedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.authGen = cMSAttributeTableGenerator;
    }

    public void setUnauthenticatedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.unauthGen = cMSAttributeTableGenerator;
    }
}
