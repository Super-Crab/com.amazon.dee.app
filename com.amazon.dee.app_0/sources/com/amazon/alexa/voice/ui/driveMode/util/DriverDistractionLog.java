package com.amazon.alexa.voice.ui.driveMode.util;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public class DriverDistractionLog {
    public static final String ALEXA_TOUCH = "Touch";
    public static final String TAG = "DriverDistractionLog";
    private static final String TIME_STAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String VOX_POI_PACKAGE_NAME = "VOX POI";

    private static String getDate(long j) {
        return new SimpleDateFormat(TIME_STAMP_FORMAT, Locale.US).format(Long.valueOf(j));
    }

    public void logTouch(String str) {
        String date = getDate(System.currentTimeMillis());
        String str2 = TAG;
        Log.i(str2, date + JsonReaderKt.COMMA + "Touch" + JsonReaderKt.COMMA + VOX_POI_PACKAGE_NAME + JsonReaderKt.COMMA + str);
    }
}
