package org.apache.logging.log4j.util;

import java.io.File;
import java.io.IOException;
/* loaded from: classes4.dex */
public class ProcessIdUtil {
    public static final String DEFAULT_PROCESSID = "-";

    public static String getProcessId() {
        try {
            try {
                return ((String) Class.forName("java.lang.management.RuntimeMXBean").getDeclaredMethod("getName", new Class[0]).invoke(Class.forName("java.lang.management.ManagementFactory").getDeclaredMethod("getRuntimeMXBean", new Class[0]).invoke(null, new Object[0]), new Object[0])).split("@")[0];
            } catch (IOException unused) {
                return DEFAULT_PROCESSID;
            }
        } catch (Exception unused2) {
            return new File("/proc/self").getCanonicalFile().getName();
        }
    }
}
