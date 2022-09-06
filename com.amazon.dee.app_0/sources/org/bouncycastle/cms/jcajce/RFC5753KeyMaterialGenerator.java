package org.bouncycastle.cms.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.asn1.cms.ecc.ECCCMSSharedInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
class RFC5753KeyMaterialGenerator implements KeyMaterialGenerator {
    @Override // org.bouncycastle.cms.jcajce.KeyMaterialGenerator
    public byte[] generateKDFMaterial(AlgorithmIdentifier algorithmIdentifier, int i, byte[] bArr) {
        try {
            return new ECCCMSSharedInfo(algorithmIdentifier, bArr, Pack.intToBigEndian(i)).getEncoded("DER");
        } catch (IOException e) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline65("Unable to create KDF material: ", e));
        }
    }
}
