package com.amazon.alexa.location.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes9.dex */
public final class WriteToFile {
    private static final String TAG = "WriteToFile";
    private static final DateFormat ISO_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
    private static final DateFormat FILENAME_FORMATTER = new SimpleDateFormat("'geofence-alexa-'yyyyMMdd'.txt'", Locale.US);
    private static final Gson gson = new Gson();
    private static boolean dumpToStdout = false;
    @NonNull
    private static Clock clock = new SystemClock();

    private WriteToFile() {
    }

    private static void appendBundle(StringBuilder sb, Bundle bundle) {
        if (bundle == null) {
            sb.append("null");
            return;
        }
        sb.append(JsonReaderKt.BEGIN_OBJ);
        String str = "";
        for (String str2 : bundle.keySet()) {
            GeneratedOutlineSupport1.outline180(sb, str, str2, RealTimeTextConstants.COLON_SPACE);
            Object obj = bundle.get(str2);
            if (obj instanceof String) {
                sb.append('\"');
                sb.append(obj);
                sb.append('\"');
            } else if (obj instanceof Bundle) {
                appendBundle(sb, (Bundle) obj);
            } else {
                sb.append(obj);
            }
            str = ", ";
        }
        sb.append(JsonReaderKt.END_OBJ);
    }

    public static void appendLogForDebugBuild(@NonNull Context context, String str) {
    }

    public static void dumpAsJson(@NonNull Context context, String str, Object obj) {
    }

    public static void dumpIntent(@NonNull Context context, String str, Intent intent) {
    }

    public static void setClock(@NonNull Clock clock2) {
        clock = clock2;
    }

    public static void setDumpToStdout(boolean z) {
        dumpToStdout = z;
    }
}
