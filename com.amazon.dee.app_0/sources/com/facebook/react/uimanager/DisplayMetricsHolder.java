package com.facebook.react.uimanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class DisplayMetricsHolder {
    @Nullable
    private static DisplayMetrics sScreenDisplayMetrics;
    @Nullable
    private static DisplayMetrics sWindowDisplayMetrics;

    public static Map<String, Map<String, Object>> getDisplayMetricsMap(double d) {
        Assertions.assertCondition((sWindowDisplayMetrics == null || sScreenDisplayMetrics == null) ? false : true, "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics");
        HashMap hashMap = new HashMap();
        hashMap.put("windowPhysicalPixels", getPhysicalPixelsMap(sWindowDisplayMetrics, d));
        hashMap.put("screenPhysicalPixels", getPhysicalPixelsMap(sScreenDisplayMetrics, d));
        return hashMap;
    }

    public static WritableNativeMap getDisplayMetricsNativeMap(double d) {
        Assertions.assertCondition((sWindowDisplayMetrics == null || sScreenDisplayMetrics == null) ? false : true, "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics");
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putMap("windowPhysicalPixels", getPhysicalPixelsNativeMap(sWindowDisplayMetrics, d));
        writableNativeMap.putMap("screenPhysicalPixels", getPhysicalPixelsNativeMap(sScreenDisplayMetrics, d));
        return writableNativeMap;
    }

    private static Map<String, Object> getPhysicalPixelsMap(DisplayMetrics displayMetrics, double d) {
        HashMap hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(displayMetrics.widthPixels));
        hashMap.put("height", Integer.valueOf(displayMetrics.heightPixels));
        hashMap.put(ModelTransformer.KEY_BATTERY_SCALE, Float.valueOf(displayMetrics.density));
        hashMap.put("fontScale", Double.valueOf(d));
        hashMap.put("densityDpi", Integer.valueOf(displayMetrics.densityDpi));
        return hashMap;
    }

    private static WritableNativeMap getPhysicalPixelsNativeMap(DisplayMetrics displayMetrics, double d) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("width", displayMetrics.widthPixels);
        writableNativeMap.putInt("height", displayMetrics.heightPixels);
        writableNativeMap.putDouble(ModelTransformer.KEY_BATTERY_SCALE, displayMetrics.density);
        writableNativeMap.putDouble("fontScale", d);
        writableNativeMap.putDouble("densityDpi", displayMetrics.densityDpi);
        return writableNativeMap;
    }

    public static DisplayMetrics getScreenDisplayMetrics() {
        return sScreenDisplayMetrics;
    }

    @Deprecated
    public static DisplayMetrics getWindowDisplayMetrics() {
        return sWindowDisplayMetrics;
    }

    public static void initDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setWindowDisplayMetrics(displayMetrics);
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        displayMetrics2.setTo(displayMetrics);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Assertions.assertNotNull(windowManager, "WindowManager is null!");
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics2);
        setScreenDisplayMetrics(displayMetrics2);
    }

    public static void initDisplayMetricsIfNotInitialized(Context context) {
        if (getScreenDisplayMetrics() != null) {
            return;
        }
        initDisplayMetrics(context);
    }

    public static void setScreenDisplayMetrics(DisplayMetrics displayMetrics) {
        sScreenDisplayMetrics = displayMetrics;
    }

    public static void setWindowDisplayMetrics(DisplayMetrics displayMetrics) {
        sWindowDisplayMetrics = displayMetrics;
    }
}
