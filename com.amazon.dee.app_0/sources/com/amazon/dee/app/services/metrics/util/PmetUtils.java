package com.amazon.dee.app.services.metrics.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class PmetUtils {
    private static final String PIVOT_ATTRIBUTE_NULL_VALUE = "NULL";
    private static final String PIVOT_ATTRIBUTE_SEPARATOR = ":";

    private PmetUtils() {
    }

    public static Map<String, String> computePmetPivots(@Nullable String str, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        if (TextUtils.isEmpty(str)) {
            str = "NULL";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "NULL";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "NULL";
        }
        HashMap outline134 = GeneratedOutlineSupport1.outline134("AppVersion", str3, "CountryCode", str2);
        outline134.put("MarketplaceIDCode", str);
        outline134.put("OSType", str4);
        outline134.put("AppVersion:MarketplaceIDCode", GeneratedOutlineSupport1.outline92(new StringBuilder(), str3, ":", str));
        outline134.put("AppVersion:CountryCode", str3 + ":" + str2);
        outline134.put("CountryCode:MarketplaceIDCode", str2 + ":" + str);
        outline134.put("AppVersion:OSType", GeneratedOutlineSupport1.outline92(new StringBuilder(), str3, ":", str4));
        outline134.put(AlexaMetricsConstants.EventConstants.OS_TYPE_COUNTRY_CODE, str4 + ":" + str2);
        outline134.put(AlexaMetricsConstants.EventConstants.OS_TYPE_MARKETPLACE_ID_CODE, str4 + ":" + str);
        StringBuilder sb = new StringBuilder();
        GeneratedOutlineSupport1.outline181(sb, str3, ":", str2, ":");
        sb.append(str);
        outline134.put("AppVersion:CountryCode:MarketplaceIDCode", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str3);
        outline134.put(AlexaMetricsConstants.EventConstants.APP_VERSION_OS_TYPE_COUNTRY_CODE, GeneratedOutlineSupport1.outline93(sb2, ":", str4, ":", str2));
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str3);
        sb3.append(":");
        outline134.put(AlexaMetricsConstants.EventConstants.APP_VERSION_OS_TYPE_MARKETPLACE_ID_CODE, GeneratedOutlineSupport1.outline92(sb3, str4, ":", str));
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str4);
        sb4.append(":");
        outline134.put(AlexaMetricsConstants.EventConstants.OS_TYPE_COUNTRY_CODE_MARKETPLACE_ID_CODE, GeneratedOutlineSupport1.outline92(sb4, str2, ":", str));
        StringBuilder sb5 = new StringBuilder();
        sb5.append(str3);
        sb5.append(":");
        GeneratedOutlineSupport1.outline181(sb5, str4, ":", str2, ":");
        sb5.append(str);
        outline134.put(AlexaMetricsConstants.EventConstants.APP_VERSION_OS_TYPE_COUNTRY_CODE_MARKETPLACE_ID_CODE, sb5.toString());
        return outline134;
    }
}
