package com.amazon.comms.util;

import com.amazon.comms.log.CommsLogger;
import com.dee.app.metrics.MetricsConstants;
import com.google.common.base.Strings;
/* loaded from: classes12.dex */
public class SystemProperty {
    private static final CommsLogger log = CommsLogger.getLogger(SystemProperty.class);

    public static int getIntSystemProperty(String str, int i) {
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getDeclaredMethod("getInt", String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i));
            return invoke != null ? ((Integer) invoke).intValue() : i;
        } catch (Exception e) {
            log.e("Unable to get system property: %s , using default value = %d ", str, Integer.valueOf(i));
            log.e("Got exception while reading system property.", e);
            return i;
        }
    }

    public static String getSystemProperty(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod(MetricsConstants.Method.CACHE_GET, String.class).invoke(null, str);
        } catch (Exception e) {
            log.e("unable to read system property: Exception ", e);
            str3 = null;
        }
        return Strings.isNullOrEmpty(str3) ? str2 : str3;
    }
}
