package org.apache.logging.log4j.util;
/* loaded from: classes4.dex */
public final class Constants {
    public static final boolean ENABLE_THREADLOCALS;
    public static final boolean IS_WEB_APP = PropertiesUtil.getProperties().getBooleanProperty("log4j2.is.webapp", isClassAvailable("javax.servlet.Servlet"));
    public static final int JAVA_MAJOR_VERSION;
    public static final String LOG4J2_DEBUG = "log4j2.debug";
    public static final int MAX_REUSABLE_MESSAGE_SIZE;

    static {
        boolean z = true;
        if (IS_WEB_APP || !PropertiesUtil.getProperties().getBooleanProperty("log4j2.enable.threadlocals", true)) {
            z = false;
        }
        ENABLE_THREADLOCALS = z;
        JAVA_MAJOR_VERSION = getMajorVersion();
        MAX_REUSABLE_MESSAGE_SIZE = size("log4j.maxReusableMsgSize", 518);
    }

    private Constants() {
    }

    private static int getMajorVersion() {
        return getMajorVersion(System.getProperty("java.version"));
    }

    private static boolean isClassAvailable(String str) {
        try {
            return LoaderUtil.loadClass(str) != null;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static int size(String str, int i) {
        return PropertiesUtil.getProperties().getIntegerProperty(str, i);
    }

    static int getMajorVersion(String str) {
        String[] split = str.split("-|\\.");
        try {
            int parseInt = Integer.parseInt(split[0]);
            return parseInt != 1 ? parseInt : Integer.parseInt(split[1]);
        } catch (Exception unused) {
            return 0;
        }
    }
}
