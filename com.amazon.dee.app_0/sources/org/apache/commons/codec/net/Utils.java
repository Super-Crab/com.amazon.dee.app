package org.apache.commons.codec.net;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.commons.codec.DecoderException;
/* loaded from: classes4.dex */
class Utils {
    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int digit16(byte b) throws DecoderException {
        int digit = Character.digit((char) b, 16);
        if (digit != -1) {
            return digit;
        }
        throw new DecoderException(GeneratedOutlineSupport1.outline49("Invalid URL encoding: not a valid digit (radix 16): ", b));
    }
}
