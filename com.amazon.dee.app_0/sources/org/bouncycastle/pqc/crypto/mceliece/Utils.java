package org.bouncycastle.pqc.crypto.mceliece;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
/* loaded from: classes5.dex */
class Utils {
    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Digest getDigest(String str) {
        if (str.equals("SHA-1")) {
            return new SHA1Digest();
        }
        if (str.equals(McElieceCCA2KeyGenParameterSpec.SHA224)) {
            return new SHA224Digest();
        }
        if (str.equals("SHA-256")) {
            return new SHA256Digest();
        }
        if (str.equals("SHA-384")) {
            return new SHA384Digest();
        }
        if (!str.equals("SHA-512")) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("unrecognised digest algorithm: ", str));
        }
        return new SHA512Digest();
    }
}
