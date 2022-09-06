package org.bouncycastle.cert.cmp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Object;
/* loaded from: classes4.dex */
class CMPUtil {
    CMPUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void derEncodeToStream(ASN1Object aSN1Object, OutputStream outputStream) {
        try {
            aSN1Object.encodeTo(outputStream, "DER");
            outputStream.close();
        } catch (IOException e) {
            throw new CMPRuntimeException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to DER encode object: ")), e);
        }
    }
}
