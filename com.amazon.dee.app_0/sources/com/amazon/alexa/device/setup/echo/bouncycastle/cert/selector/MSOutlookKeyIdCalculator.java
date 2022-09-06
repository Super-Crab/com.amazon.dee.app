package com.amazon.alexa.device.setup.echo.bouncycastle.cert.selector;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.digests.SHA1Digest;
import java.io.IOException;
/* loaded from: classes.dex */
class MSOutlookKeyIdCalculator {
    MSOutlookKeyIdCalculator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] calculateKeyId(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] bArr = new byte[sHA1Digest.getDigestSize()];
        try {
            byte[] encoded = subjectPublicKeyInfo.getEncoded("DER");
            sHA1Digest.update(encoded, 0, encoded.length);
            sHA1Digest.doFinal(bArr, 0);
            return bArr;
        } catch (IOException unused) {
            return new byte[0];
        }
    }
}
