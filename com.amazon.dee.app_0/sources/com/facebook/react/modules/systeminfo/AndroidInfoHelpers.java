package com.facebook.react.modules.systeminfo;

import android.content.Context;
import android.os.Build;
import com.facebook.react.R;
import java.util.Locale;
/* loaded from: classes2.dex */
public class AndroidInfoHelpers {
    public static final String DEVICE_LOCALHOST = "localhost";
    public static final String EMULATOR_LOCALHOST = "10.0.2.2";
    public static final String GENYMOTION_LOCALHOST = "10.0.3.2";
    public static final String METRO_HOST_PROP_NAME = "metro.host";
    private static final String TAG = "AndroidInfoHelpers";
    private static String metroHostPropValue;

    public static String getAdbReverseTcpCommand(Integer num) {
        return "adb reverse tcp:" + num + " tcp:" + num;
    }

    private static Integer getDevServerPort(Context context) {
        return Integer.valueOf(context.getResources().getInteger(R.integer.react_native_dev_server_port));
    }

    public static String getFriendlyDeviceName() {
        if (isRunningOnGenymotion()) {
            return Build.MODEL;
        }
        return Build.MODEL + " - " + Build.VERSION.RELEASE + " - API " + Build.VERSION.SDK_INT;
    }

    public static String getInspectorProxyHost(Context context) {
        return getServerIpAddress(getInspectorProxyPort(context).intValue());
    }

    private static Integer getInspectorProxyPort(Context context) {
        return Integer.valueOf(context.getResources().getInteger(R.integer.react_native_dev_server_port));
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0066, code lost:
        if (r2 == null) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static synchronized java.lang.String getMetroHostPropValue() {
        /*
            java.lang.Class<com.facebook.react.modules.systeminfo.AndroidInfoHelpers> r0 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.class
            monitor-enter(r0)
            java.lang.String r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L79
            if (r1 == 0) goto Lb
            java.lang.String r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L79
            monitor-exit(r0)
            return r1
        Lb:
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            java.lang.String r3 = "/system/bin/getprop"
            java.lang.String r4 = "metro.host"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L6d
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L6d
            java.io.InputStream r5 = r2.getInputStream()     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L6d
            java.lang.String r6 = "UTF-8"
            java.nio.charset.Charset r6 = java.nio.charset.Charset.forName(r6)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L6d
            r4.<init>(r5, r6)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L6d
            r3.<init>(r4)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L6d
            java.lang.String r1 = ""
        L32:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L48
            if (r4 == 0) goto L3a
            r1 = r4
            goto L32
        L3a:
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r1     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L48
            r3.close()     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L79
        L3f:
            r2.destroy()     // Catch: java.lang.Throwable -> L79
            goto L69
        L43:
            r1 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L6e
        L48:
            r1 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L56
        L4d:
            r3 = move-exception
            goto L56
        L4f:
            r2 = move-exception
            r3 = r2
            r2 = r1
            goto L6e
        L53:
            r2 = move-exception
            r3 = r2
            r2 = r1
        L56:
            java.lang.String r4 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.TAG     // Catch: java.lang.Throwable -> L6d
            java.lang.String r5 = "Failed to query for metro.host prop:"
            com.facebook.common.logging.FLog.w(r4, r5, r3)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = ""
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r3     // Catch: java.lang.Throwable -> L6d
            if (r1 == 0) goto L66
            r1.close()     // Catch: java.lang.Exception -> L66 java.lang.Throwable -> L79
        L66:
            if (r2 == 0) goto L69
            goto L3f
        L69:
            java.lang.String r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L79
            monitor-exit(r0)
            return r1
        L6d:
            r3 = move-exception
        L6e:
            if (r1 == 0) goto L73
            r1.close()     // Catch: java.lang.Exception -> L73 java.lang.Throwable -> L79
        L73:
            if (r2 == 0) goto L78
            r2.destroy()     // Catch: java.lang.Throwable -> L79
        L78:
            throw r3     // Catch: java.lang.Throwable -> L79
        L79:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.systeminfo.AndroidInfoHelpers.getMetroHostPropValue():java.lang.String");
    }

    public static String getServerHost(Integer num) {
        return getServerIpAddress(num.intValue());
    }

    private static String getServerIpAddress(int i) {
        String metroHostPropValue2 = getMetroHostPropValue();
        if (metroHostPropValue2.equals("")) {
            if (isRunningOnGenymotion()) {
                metroHostPropValue2 = GENYMOTION_LOCALHOST;
            } else {
                metroHostPropValue2 = isRunningOnStockEmulator() ? EMULATOR_LOCALHOST : DEVICE_LOCALHOST;
            }
        }
        return String.format(Locale.US, "%s:%d", metroHostPropValue2, Integer.valueOf(i));
    }

    private static boolean isRunningOnGenymotion() {
        return Build.FINGERPRINT.contains("vbox");
    }

    private static boolean isRunningOnStockEmulator() {
        return Build.FINGERPRINT.contains("generic");
    }

    public static String getAdbReverseTcpCommand(Context context) {
        return getAdbReverseTcpCommand(getDevServerPort(context));
    }

    public static String getServerHost(Context context) {
        return getServerIpAddress(getDevServerPort(context).intValue());
    }
}
