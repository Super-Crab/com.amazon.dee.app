package com.amazon.deecomms.common.util;

import android.content.Context;
import net.danlew.android.joda.JodaTimeAndroid;
/* loaded from: classes12.dex */
public abstract class JodaTimeInitializer {
    private static volatile boolean jodaTimeInitialized = false;
    private static final Object lockObject = new Object();

    public JodaTimeInitializer(Context context) {
        if (jodaTimeInitialized) {
            return;
        }
        synchronized (lockObject) {
            if (jodaTimeInitialized) {
                return;
            }
            JodaTimeAndroid.init(context);
            jodaTimeInitialized = true;
        }
    }
}
