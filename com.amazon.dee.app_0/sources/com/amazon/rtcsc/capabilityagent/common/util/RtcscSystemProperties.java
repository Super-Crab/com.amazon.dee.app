package com.amazon.rtcsc.capabilityagent.common.util;
/* loaded from: classes13.dex */
public final class RtcscSystemProperties {
    private static final String GETPROP_EXECUTABLE_PATH = "/system/bin/getprop";
    private static final String TAG = "RtcscSystemProperties";

    private RtcscSystemProperties() {
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String read(java.lang.String r11) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "RtcscSystemProperties"
            r2 = 0
            r3 = 1
            r4 = 0
            java.lang.ProcessBuilder r5 = new java.lang.ProcessBuilder     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.lang.String[] r6 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.lang.String r7 = "/system/bin/getprop"
            r6[r4] = r7     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            r6[r3] = r11     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.lang.ProcessBuilder r5 = r5.command(r6)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.lang.ProcessBuilder r5 = r5.redirectErrorStream(r3)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.lang.Process r5 = r5.start()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L64
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L64
            java.io.InputStream r8 = r5.getInputStream()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L64
            java.nio.charset.Charset r9 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L64
            r7.<init>(r8, r9)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L64
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L64
            java.lang.String r2 = r6.readLine()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            if (r2 != 0) goto L3a
            r2 = r0
        L3a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            r7.<init>()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            java.lang.String r8 = "read System Property: "
            r7.append(r8)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            r7.append(r11)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            java.lang.String r8 = "="
            r7.append(r8)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            r7.append(r2)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            com.amazon.rtcsc.capabilityagent.common.util.RtcscLogger.d(r1, r7, r8)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L94
            r6.close()     // Catch: java.io.IOException -> L5b
        L5b:
            r5.destroy()
            return r2
        L5f:
            r2 = move-exception
            goto L71
        L61:
            r11 = move-exception
            r6 = r2
            goto L95
        L64:
            r6 = move-exception
            r10 = r6
            r6 = r2
            r2 = r10
            goto L71
        L69:
            r11 = move-exception
            r5 = r2
            r6 = r5
            goto L95
        L6d:
            r5 = move-exception
            r6 = r2
            r2 = r5
            r5 = r6
        L71:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L94
            r7.<init>()     // Catch: java.lang.Throwable -> L94
            java.lang.String r8 = "Failed to read System Property "
            r7.append(r8)     // Catch: java.lang.Throwable -> L94
            r7.append(r11)     // Catch: java.lang.Throwable -> L94
            java.lang.String r11 = r7.toString()     // Catch: java.lang.Throwable -> L94
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L94
            r3[r4] = r2     // Catch: java.lang.Throwable -> L94
            com.amazon.rtcsc.capabilityagent.common.util.RtcscLogger.e(r1, r11, r3)     // Catch: java.lang.Throwable -> L94
            if (r6 == 0) goto L8e
            r6.close()     // Catch: java.io.IOException -> L8e
        L8e:
            if (r5 == 0) goto L93
            r5.destroy()
        L93:
            return r0
        L94:
            r11 = move-exception
        L95:
            if (r6 == 0) goto L9a
            r6.close()     // Catch: java.io.IOException -> L9a
        L9a:
            if (r5 == 0) goto L9f
            r5.destroy()
        L9f:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.rtcsc.capabilityagent.common.util.RtcscSystemProperties.read(java.lang.String):java.lang.String");
    }
}
