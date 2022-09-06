package com.amazon.whisperbridge.ble;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class BleException extends Exception {
    private static final String TAG = BleException.class.getSimpleName();

    public BleException(String str) {
        super(str);
        String str2 = TAG;
        WJLog.e(str2, "BleException thrown: " + str);
    }

    public BleException(String str, Throwable th) {
        super(str, th);
        String str2 = TAG;
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("BleException thrown: ", str, "\ncause: ");
        outline115.append(th.toString());
        WJLog.e(str2, outline115.toString());
    }
}
