package com.amazon.alexa.smarthomecameras.util;

import android.util.Log;
/* loaded from: classes10.dex */
public final class CamerasLogger {
    private static final String CAMERAS_TAG = "cameras";
    public static final String LVC_TAG = "LVC";

    public static final void logInfo(String str, String str2) {
        Log.i("[cameras]", "[" + str + "] " + str2);
    }
}
