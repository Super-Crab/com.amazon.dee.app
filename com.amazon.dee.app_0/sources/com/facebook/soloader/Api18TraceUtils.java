package com.facebook.soloader;

import android.annotation.TargetApi;
import android.os.Trace;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(18)
/* loaded from: classes2.dex */
public class Api18TraceUtils {
    private static final int MAX_SECTION_NAME_LENGTH = 127;

    Api18TraceUtils() {
    }

    public static void beginTraceSection(String str, @Nullable String str2, String str3) {
        String outline75 = GeneratedOutlineSupport1.outline75(str, str2, str3);
        if (outline75.length() > 127 && str2 != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(str2.substring(0, (127 - str.length()) - str3.length()));
            outline107.append(str3);
            outline75 = outline107.toString();
        }
        Trace.beginSection(outline75);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
