package org.bouncycastle.cert.crmf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.cert.CertIOException;
/* loaded from: classes4.dex */
class CRMFUtil {
    CRMFUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addExtension(ExtensionsGenerator extensionsGenerator, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) throws CertIOException {
        try {
            extensionsGenerator.addExtension(aSN1ObjectIdentifier, z, aSN1Encodable);
        } catch (IOException e) {
            throw new CertIOException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("cannot encode extension: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void derEncodeToStream(ASN1Object aSN1Object, OutputStream outputStream) {
        try {
            aSN1Object.encodeTo(outputStream, "DER");
            outputStream.close();
        } catch (IOException e) {
            throw new CRMFRuntimeException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to DER encode object: ")), e);
        }
    }
}
