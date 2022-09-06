package com.amazon.alexa.voice.platform;

import android.os.Build;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class ABIRetriever {
    private static final String TAG = "ABIRetriever";

    public String getMostPreferredABI() {
        String str = Build.SUPPORTED_ABIS[0];
        GeneratedOutlineSupport1.outline163(" Architecture ", str, TAG);
        return str;
    }
}
