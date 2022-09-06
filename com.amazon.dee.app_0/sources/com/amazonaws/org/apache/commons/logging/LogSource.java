package com.amazonaws.org.apache.commons.logging;

import com.amazonaws.org.apache.commons.logging.impl.LogFactoryImpl;
import com.amazonaws.org.apache.commons.logging.impl.NoOpLog;
import java.lang.reflect.Constructor;
import java.util.Hashtable;
/* loaded from: classes13.dex */
public class LogSource {
    protected static boolean jdk14IsAvailable;
    protected static boolean log4jIsAvailable;
    protected static Hashtable logs = new Hashtable();
    protected static Constructor logImplctor = null;

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0020 -> B:38:0x0022). Please submit an issue!!! */
    static {
        log4jIsAvailable = false;
        jdk14IsAvailable = false;
        String str = null;
        try {
            if (Class.forName("org.apache.log4j.Logger") != null) {
                log4jIsAvailable = true;
            } else {
                log4jIsAvailable = false;
            }
        } catch (Throwable unused) {
            log4jIsAvailable = false;
        }
        try {
            if (Class.forName("java.util.logging.Logger") != null && Class.forName("com.amazonaws.org.apache.commons.logging.impl.Jdk14Logger") != null) {
                jdk14IsAvailable = true;
            } else {
                jdk14IsAvailable = false;
            }
        } catch (Throwable unused2) {
            jdk14IsAvailable = false;
        }
        try {
            str = System.getProperty("com.amazonaws.org.apache.commons.logging.log");
            if (str == null) {
                str = System.getProperty(LogFactoryImpl.LOG_PROPERTY);
            }
        } catch (Throwable unused3) {
        }
        try {
            if (str != null) {
                setLogImplementation(str);
            } else if (log4jIsAvailable) {
                setLogImplementation("com.amazonaws.org.apache.commons.logging.impl.Log4JLogger");
            } else if (jdk14IsAvailable) {
                setLogImplementation("com.amazonaws.org.apache.commons.logging.impl.Jdk14Logger");
            } else {
                setLogImplementation("com.amazonaws.org.apache.commons.logging.impl.NoOpLog");
            }
        } catch (Throwable unused4) {
        }
    }

    private LogSource() {
    }

    public static Log getInstance(String str) {
        Log log = (Log) logs.get(str);
        if (log == null) {
            Log makeNewLogInstance = makeNewLogInstance(str);
            logs.put(str, makeNewLogInstance);
            return makeNewLogInstance;
        }
        return log;
    }

    public static String[] getLogNames() {
        return (String[]) logs.keySet().toArray(new String[logs.size()]);
    }

    public static Log makeNewLogInstance(String str) {
        Log log;
        try {
            log = (Log) logImplctor.newInstance(str);
        } catch (Throwable unused) {
            log = null;
        }
        return log == null ? new NoOpLog(str) : log;
    }

    public static void setLogImplementation(String str) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException {
        try {
            logImplctor = Class.forName(str).getConstructor("".getClass());
        } catch (Throwable unused) {
            logImplctor = null;
        }
    }

    public static Log getInstance(Class cls) {
        return getInstance(cls.getName());
    }

    public static void setLogImplementation(Class cls) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException {
        logImplctor = cls.getConstructor("".getClass());
    }
}
