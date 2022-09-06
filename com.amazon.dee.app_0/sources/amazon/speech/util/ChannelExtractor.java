package amazon.speech.util;

import amazon.speech.util.DebugUtil;
/* loaded from: classes.dex */
public class ChannelExtractor {
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.SIM, ChannelExtractor.class);

    public static byte[] extractChannel(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (i <= 0) {
            Log.e(TAG, "Total number of channels cannot be negative or zero");
            return new byte[0];
        } else if (i2 < 0) {
            Log.e(TAG, "The requested channel index cannot be negative");
            return new byte[0];
        } else if (i2 >= i) {
            Log.e(TAG, "The requested channel index cannot exceed total number of channels");
            return new byte[0];
        } else if (bArr.length % i != 0) {
            Log.e(TAG, "Byte buffer size should be multiples of the total number of channels");
            return new byte[0];
        } else {
            byte[] bArr2 = new byte[bArr.length / i];
            while (i2 < bArr.length) {
                bArr2[i3] = bArr[i2];
                i2 += i;
                i3++;
            }
            return bArr2;
        }
    }
}
