package com.amazon.dee.app.services.useragent;

import android.os.Build;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class UserAgent {
    public static final String USER_AGENT_HEADER = "User-Agent";

    private UserAgent() {
    }

    private static String formatUserAgentEntry(String str, String str2) {
        return GeneratedOutlineSupport1.outline77("[", str, Config.Compare.EQUAL_TO, str2, "]");
    }

    public static String get() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PitanguiBridge/2.2.466191.0-");
        outline107.append(formatUserAgentEntry("PLATFORM", "Android"));
        outline107.append(formatUserAgentEntry("MANUFACTURER", Build.MANUFACTURER));
        outline107.append(formatUserAgentEntry("RELEASE", Build.VERSION.RELEASE));
        outline107.append(formatUserAgentEntry("BRAND", Build.BRAND));
        outline107.append(formatUserAgentEntry("SDK", String.valueOf(Build.VERSION.SDK_INT)));
        outline107.append(formatUserAgentEntry("MODEL", Build.MODEL));
        return outline107.toString();
    }
}
