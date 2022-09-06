package com.amazon.alexa.growth.coachmark;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.Locale;
/* loaded from: classes8.dex */
public final class Utils {
    private Utils() {
    }

    public static void dispatchEvent(View view, String str, WritableMap writableMap) {
        ((RCTEventEmitter) ((ReactContext) view.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), str, writableMap);
    }

    public static String generateMetricEventName(String[] strArr) {
        return joinStrings(strArr, ".");
    }

    public static Activity getActivityFromView(View view) {
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        throw new IllegalStateException("The anchorView's Context is not an Activity.");
    }

    public static Locale getCurrentLocale(Configuration configuration) {
        int i = Build.VERSION.SDK_INT;
        return configuration.getLocales().get(0);
    }

    public static boolean isDebuggable(Context context) {
        return (context == null || context.getApplicationContext() == null || (context.getApplicationInfo().flags & 2) == 0) ? false : true;
    }

    public static boolean isLightMode(Context context) {
        int i = context.getResources().getConfiguration().uiMode & 48;
        return i == 16 || i != 32;
    }

    public static boolean isScreenReaderEnabled(Context context) {
        try {
            AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
            if (accessibilityManager != null && accessibilityManager.isEnabled()) {
                return !accessibilityManager.getEnabledAccessibilityServiceList(1).isEmpty();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static String joinStrings(String[] strArr, String str) {
        if (strArr == null || strArr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
            if (i != strArr.length - 1) {
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
