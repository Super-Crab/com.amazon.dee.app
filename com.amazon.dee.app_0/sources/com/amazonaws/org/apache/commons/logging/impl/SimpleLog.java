package com.amazonaws.org.apache.commons.logging.impl;

import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceMessageType;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.mobilytics.event.LogLevel;
import com.amazonaws.org.apache.commons.logging.Log;
import com.amazonaws.org.apache.commons.logging.LogConfigurationException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
/* loaded from: classes13.dex */
public class SimpleLog implements Log, Serializable {
    protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_DEBUG = 2;
    public static final int LOG_LEVEL_ERROR = 5;
    public static final int LOG_LEVEL_FATAL = 6;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_OFF = 7;
    public static final int LOG_LEVEL_TRACE = 1;
    public static final int LOG_LEVEL_WARN = 4;
    static /* synthetic */ Class class$java$lang$Thread = null;
    static /* synthetic */ Class class$org$apache$commons$logging$impl$SimpleLog = null;
    protected static DateFormat dateFormatter = null;
    protected static String dateTimeFormat = null;
    protected static boolean showDateTime = false;
    protected static boolean showLogName = false;
    protected static boolean showShortName = false;
    protected static final Properties simpleLogProps = new Properties();
    protected static final String systemPrefix = "com.amazonaws.org.apache.commons.logging.simplelog.";
    protected int currentLogLevel;
    protected String logName;
    private String shortLogName = null;

    static {
        showLogName = false;
        showShortName = true;
        showDateTime = false;
        dateTimeFormat = DEFAULT_DATE_TIME_FORMAT;
        dateFormatter = null;
        InputStream resourceAsStream = getResourceAsStream("simplelog.properties");
        if (resourceAsStream != null) {
            try {
                simpleLogProps.load(resourceAsStream);
                resourceAsStream.close();
            } catch (IOException unused) {
            }
        }
        showLogName = getBooleanProperty("com.amazonaws.org.apache.commons.logging.simplelog.showlogname", showLogName);
        showShortName = getBooleanProperty("com.amazonaws.org.apache.commons.logging.simplelog.showShortLogname", showShortName);
        showDateTime = getBooleanProperty("com.amazonaws.org.apache.commons.logging.simplelog.showdatetime", showDateTime);
        if (showDateTime) {
            dateTimeFormat = getStringProperty("com.amazonaws.org.apache.commons.logging.simplelog.dateTimeFormat", dateTimeFormat);
            try {
                dateFormatter = new SimpleDateFormat(dateTimeFormat);
            } catch (IllegalArgumentException unused2) {
                dateTimeFormat = DEFAULT_DATE_TIME_FORMAT;
                dateFormatter = new SimpleDateFormat(dateTimeFormat);
            }
        }
    }

    public SimpleLog(String str) {
        this.logName = null;
        this.logName = str;
        setLevel(3);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("com.amazonaws.org.apache.commons.logging.simplelog.log.");
        stringBuffer.append(this.logName);
        String stringProperty = getStringProperty(stringBuffer.toString());
        int lastIndexOf = String.valueOf(str).lastIndexOf(".");
        while (stringProperty == null && lastIndexOf > -1) {
            str = str.substring(0, lastIndexOf);
            stringProperty = getStringProperty(GeneratedOutlineSupport1.outline71("com.amazonaws.org.apache.commons.logging.simplelog.log.", str));
            lastIndexOf = String.valueOf(str).lastIndexOf(".");
        }
        stringProperty = stringProperty == null ? getStringProperty("com.amazonaws.org.apache.commons.logging.simplelog.defaultlog") : stringProperty;
        if ("all".equalsIgnoreCase(stringProperty)) {
            setLevel(0);
        } else if (TracePublisherServiceMessageType.TRACE_MESSAGE_KEY.equalsIgnoreCase(stringProperty)) {
            setLevel(1);
        } else if ("debug".equalsIgnoreCase(stringProperty)) {
            setLevel(2);
        } else if (LogLevel.INFO.equalsIgnoreCase(stringProperty)) {
            setLevel(3);
        } else if (LogLevel.WARN.equalsIgnoreCase(stringProperty)) {
            setLevel(4);
        } else if ("error".equalsIgnoreCase(stringProperty)) {
            setLevel(5);
        } else if ("fatal".equalsIgnoreCase(stringProperty)) {
            setLevel(6);
        } else if (!"off".equalsIgnoreCase(stringProperty)) {
        } else {
            setLevel(7);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static boolean getBooleanProperty(String str, boolean z) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? z : "true".equalsIgnoreCase(stringProperty);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ClassLoader getContextClassLoader() {
        Class cls;
        ClassLoader classLoader = null;
        try {
            if (class$java$lang$Thread == null) {
                cls = class$("java.lang.Thread");
                class$java$lang$Thread = cls;
            } else {
                cls = class$java$lang$Thread;
            }
            try {
                classLoader = (ClassLoader) cls.getMethod("getContextClassLoader", null).invoke(Thread.currentThread(), null);
            } catch (InvocationTargetException e) {
                if (!(e.getTargetException() instanceof SecurityException)) {
                    throw new LogConfigurationException("Unexpected InvocationTargetException", e.getTargetException());
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        }
        if (classLoader == null) {
            Class cls2 = class$org$apache$commons$logging$impl$SimpleLog;
            if (cls2 == null) {
                cls2 = class$("com.amazonaws.org.apache.commons.logging.impl.SimpleLog");
                class$org$apache$commons$logging$impl$SimpleLog = cls2;
            }
            return cls2.getClassLoader();
        }
        return classLoader;
    }

    private static InputStream getResourceAsStream(final String str) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.amazonaws.org.apache.commons.logging.impl.SimpleLog.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                ClassLoader contextClassLoader = SimpleLog.getContextClassLoader();
                if (contextClassLoader != null) {
                    return contextClassLoader.getResourceAsStream(str);
                }
                return ClassLoader.getSystemResourceAsStream(str);
            }
        });
    }

    private static String getStringProperty(String str) {
        String str2;
        try {
            str2 = System.getProperty(str);
        } catch (SecurityException unused) {
            str2 = null;
        }
        return str2 == null ? simpleLogProps.getProperty(str) : str2;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void debug(Object obj) {
        if (isLevelEnabled(2)) {
            log(2, obj, null);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void error(Object obj) {
        if (isLevelEnabled(5)) {
            log(5, obj, null);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void fatal(Object obj) {
        if (isLevelEnabled(6)) {
            log(6, obj, null);
        }
    }

    public int getLevel() {
        return this.currentLogLevel;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void info(Object obj) {
        if (isLevelEnabled(3)) {
            log(3, obj, null);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isDebugEnabled() {
        return isLevelEnabled(2);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isErrorEnabled() {
        return isLevelEnabled(5);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isFatalEnabled() {
        return isLevelEnabled(6);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isInfoEnabled() {
        return isLevelEnabled(3);
    }

    protected boolean isLevelEnabled(int i) {
        return i >= this.currentLogLevel;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isTraceEnabled() {
        return isLevelEnabled(1);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isWarnEnabled() {
        return isLevelEnabled(4);
    }

    protected void log(int i, Object obj, Throwable th) {
        String format;
        StringBuffer stringBuffer = new StringBuffer();
        if (showDateTime) {
            Date date = new Date();
            synchronized (dateFormatter) {
                format = dateFormatter.format(date);
            }
            stringBuffer.append(format);
            stringBuffer.append(" ");
        }
        switch (i) {
            case 1:
                stringBuffer.append("[TRACE] ");
                break;
            case 2:
                stringBuffer.append("[DEBUG] ");
                break;
            case 3:
                stringBuffer.append("[INFO] ");
                break;
            case 4:
                stringBuffer.append("[WARN] ");
                break;
            case 5:
                stringBuffer.append("[ERROR] ");
                break;
            case 6:
                stringBuffer.append("[FATAL] ");
                break;
        }
        if (showShortName) {
            if (this.shortLogName == null) {
                String str = this.logName;
                this.shortLogName = str.substring(str.lastIndexOf(".") + 1);
                String str2 = this.shortLogName;
                this.shortLogName = str2.substring(str2.lastIndexOf("/") + 1);
            }
            stringBuffer.append(String.valueOf(this.shortLogName));
            stringBuffer.append(" - ");
        } else if (showLogName) {
            stringBuffer.append(String.valueOf(this.logName));
            stringBuffer.append(" - ");
        }
        stringBuffer.append(String.valueOf(obj));
        if (th != null) {
            stringBuffer.append(" <");
            stringBuffer.append(th.toString());
            stringBuffer.append(Config.Compare.GREATER_THAN);
            StringWriter stringWriter = new StringWriter(1024);
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            stringBuffer.append(stringWriter.toString());
        }
        write(stringBuffer);
    }

    public void setLevel(int i) {
        this.currentLogLevel = i;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void trace(Object obj) {
        if (isLevelEnabled(1)) {
            log(1, obj, null);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void warn(Object obj) {
        if (isLevelEnabled(4)) {
            log(4, obj, null);
        }
    }

    protected void write(StringBuffer stringBuffer) {
        System.err.println(stringBuffer.toString());
    }

    private static String getStringProperty(String str, String str2) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? str2 : stringProperty;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void debug(Object obj, Throwable th) {
        if (isLevelEnabled(2)) {
            log(2, obj, th);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void error(Object obj, Throwable th) {
        if (isLevelEnabled(5)) {
            log(5, obj, th);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void fatal(Object obj, Throwable th) {
        if (isLevelEnabled(6)) {
            log(6, obj, th);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void info(Object obj, Throwable th) {
        if (isLevelEnabled(3)) {
            log(3, obj, th);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void trace(Object obj, Throwable th) {
        if (isLevelEnabled(1)) {
            log(1, obj, th);
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final void warn(Object obj, Throwable th) {
        if (isLevelEnabled(4)) {
            log(4, obj, th);
        }
    }
}
