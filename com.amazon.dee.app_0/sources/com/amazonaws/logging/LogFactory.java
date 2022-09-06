package com.amazonaws.logging;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class LogFactory {
    private static final String APACHE_COMMONS_LOGGING_LOGFACTORY = "com.amazonaws.org.apache.commons.logging.LogFactory";
    private static final String TAG = "LogFactory";
    private static Map<String, Log> logMap = new HashMap();

    private static boolean checkApacheCommonsLoggingExists() {
        try {
            Class.forName("com.amazonaws.org.apache.commons.logging.LogFactory");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Exception e) {
            android.util.Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public static synchronized Log getLog(Class cls) {
        Log log;
        synchronized (LogFactory.class) {
            log = getLog(cls.getSimpleName());
        }
        return log;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002f A[Catch: all -> 0x003b, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000d, B:8:0x0013, B:9:0x0018, B:15:0x0025, B:17:0x002f), top: B:25:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized com.amazonaws.logging.Log getLog(java.lang.String r6) {
        /*
            java.lang.Class<com.amazonaws.logging.LogFactory> r0 = com.amazonaws.logging.LogFactory.class
            monitor-enter(r0)
            java.util.Map<java.lang.String, com.amazonaws.logging.Log> r1 = com.amazonaws.logging.LogFactory.logMap     // Catch: java.lang.Throwable -> L3b
            java.lang.Object r1 = r1.get(r6)     // Catch: java.lang.Throwable -> L3b
            com.amazonaws.logging.Log r1 = (com.amazonaws.logging.Log) r1     // Catch: java.lang.Throwable -> L3b
            if (r1 != 0) goto L39
            boolean r2 = checkApacheCommonsLoggingExists()     // Catch: java.lang.Throwable -> L3b
            if (r2 == 0) goto L2d
            com.amazonaws.logging.ApacheCommonsLogging r2 = new com.amazonaws.logging.ApacheCommonsLogging     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L3b
            r2.<init>(r6)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L3b
            java.util.Map<java.lang.String, com.amazonaws.logging.Log> r1 = com.amazonaws.logging.LogFactory.logMap     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L3b
            r1.put(r6, r2)     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L3b
        L1d:
            r1 = r2
            goto L2d
        L1f:
            r1 = move-exception
            goto L25
        L21:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L25:
            java.lang.String r3 = com.amazonaws.logging.LogFactory.TAG     // Catch: java.lang.Throwable -> L3b
            java.lang.String r4 = "Could not create log from org.apache.commons.logging.LogFactory"
            android.util.Log.w(r3, r4, r1)     // Catch: java.lang.Throwable -> L3b
            goto L1d
        L2d:
            if (r1 != 0) goto L39
            com.amazonaws.logging.AndroidLog r1 = new com.amazonaws.logging.AndroidLog     // Catch: java.lang.Throwable -> L3b
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L3b
            java.util.Map<java.lang.String, com.amazonaws.logging.Log> r2 = com.amazonaws.logging.LogFactory.logMap     // Catch: java.lang.Throwable -> L3b
            r2.put(r6, r1)     // Catch: java.lang.Throwable -> L3b
        L39:
            monitor-exit(r0)
            return r1
        L3b:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.logging.LogFactory.getLog(java.lang.String):com.amazonaws.logging.Log");
    }
}
