package org.webrtc;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes5.dex */
public class Logging {
    private static volatile boolean loggingEnabled;
    private static final Logger fallbackLogger = createFallbackLogger();
    private static volatile NativeLibStatus nativeLibStatus = NativeLibStatus.UNINITIALIZED;

    /* renamed from: org.webrtc.Logging$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$Logging$Severity = new int[Severity.values().length];

        static {
            try {
                $SwitchMap$org$webrtc$Logging$Severity[Severity.LS_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$Logging$Severity[Severity.LS_WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$webrtc$Logging$Severity[Severity.LS_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum NativeLibStatus {
        UNINITIALIZED,
        LOADED,
        FAILED
    }

    /* loaded from: classes5.dex */
    public enum Severity {
        LS_SENSITIVE,
        LS_VERBOSE,
        LS_INFO,
        LS_WARNING,
        LS_ERROR,
        LS_NONE
    }

    @Deprecated
    /* loaded from: classes5.dex */
    public enum TraceLevel {
        TRACE_NONE(0),
        TRACE_STATEINFO(1),
        TRACE_WARNING(2),
        TRACE_ERROR(4),
        TRACE_CRITICAL(8),
        TRACE_APICALL(16),
        TRACE_DEFAULT(255),
        TRACE_MODULECALL(32),
        TRACE_MEMORY(256),
        TRACE_TIMER(512),
        TRACE_STREAM(1024),
        TRACE_DEBUG(2048),
        TRACE_INFO(4096),
        TRACE_TERSEINFO(8192),
        TRACE_ALL(65535);
        
        public final int level;

        TraceLevel(int i) {
            this.level = i;
        }
    }

    private static Logger createFallbackLogger() {
        Logger logger = Logger.getLogger("org.webrtc.Logging");
        logger.setLevel(Level.ALL);
        return logger;
    }

    public static void d(String str, String str2) {
        log(Severity.LS_INFO, str, str2);
    }

    public static void e(String str, String str2) {
        log(Severity.LS_ERROR, str, str2);
    }

    public static void enableLogThreads() {
        if (!loadNativeLibrary()) {
            fallbackLogger.log(Level.WARNING, "Cannot enable log thread because native lib not loaded.");
        } else {
            nativeEnableLogThreads();
        }
    }

    public static void enableLogTimeStamps() {
        if (!loadNativeLibrary()) {
            fallbackLogger.log(Level.WARNING, "Cannot enable log timestamps because native lib not loaded.");
        } else {
            nativeEnableLogTimeStamps();
        }
    }

    public static synchronized void enableLogToDebugOutput(Severity severity) {
        synchronized (Logging.class) {
            if (!loadNativeLibrary()) {
                fallbackLogger.log(Level.WARNING, "Cannot enable logging because native lib not loaded.");
                return;
            }
            nativeEnableLogToDebugOutput(severity.ordinal());
            loggingEnabled = true;
        }
    }

    @Deprecated
    public static void enableTracing(String str, EnumSet<TraceLevel> enumSet) {
    }

    private static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        ThrowableExtension.printStackTrace(th, new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private static boolean loadNativeLibrary() {
        if (nativeLibStatus == NativeLibStatus.UNINITIALIZED) {
            try {
                System.loadLibrary("jingle_peerconnection_so");
                nativeLibStatus = NativeLibStatus.LOADED;
            } catch (UnsatisfiedLinkError e) {
                nativeLibStatus = NativeLibStatus.FAILED;
                fallbackLogger.log(Level.WARNING, "Failed to load jingle_peerconnection_so: ", (Throwable) e);
            }
        }
        return nativeLibStatus == NativeLibStatus.LOADED;
    }

    public static void log(Severity severity, String str, String str2) {
        Level level;
        if (loggingEnabled) {
            nativeLog(severity.ordinal(), str, str2);
            return;
        }
        int ordinal = severity.ordinal();
        if (ordinal == 2) {
            level = Level.INFO;
        } else if (ordinal == 3) {
            level = Level.WARNING;
        } else if (ordinal != 4) {
            level = Level.FINE;
        } else {
            level = Level.SEVERE;
        }
        Logger logger = fallbackLogger;
        logger.log(level, str + RealTimeTextConstants.COLON_SPACE + str2);
    }

    private static native void nativeEnableLogThreads();

    private static native void nativeEnableLogTimeStamps();

    private static native void nativeEnableLogToDebugOutput(int i);

    private static native void nativeLog(int i, String str, String str2);

    public static void v(String str, String str2) {
        log(Severity.LS_VERBOSE, str, str2);
    }

    public static void w(String str, String str2) {
        log(Severity.LS_WARNING, str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        log(Severity.LS_ERROR, str, str2);
        log(Severity.LS_ERROR, str, th.toString());
        log(Severity.LS_ERROR, str, getStackTraceString(th));
    }

    public static void w(String str, String str2, Throwable th) {
        log(Severity.LS_WARNING, str, str2);
        log(Severity.LS_WARNING, str, th.toString());
        log(Severity.LS_WARNING, str, getStackTraceString(th));
    }
}
