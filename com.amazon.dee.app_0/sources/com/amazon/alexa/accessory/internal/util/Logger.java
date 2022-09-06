package com.amazon.alexa.accessory.internal.util;

import android.util.Log;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class Logger {
    private static final String TAG = "AlexaAccessory";
    private static volatile AccessoriesLogger accessoriesLogger;
    private static Processor currentProcessor;
    private static Level level = Level.valueOf("DEBUG");

    /* loaded from: classes.dex */
    public enum Level {
        ERROR,
        INFO,
        DEBUG,
        VERBOSE
    }

    /* loaded from: classes.dex */
    public interface Processor {
        void process(String str);

        void process(String str, Throwable th);
    }

    static {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Logger.level initialy set to ");
        outline107.append(level);
        Log.w(TAG, outline107.toString());
        accessoriesLogger = new DefaultAccessoriesLogger();
    }

    private Logger() {
        throw new IllegalStateException("No instances!");
    }

    public static void binary(String str, int i) {
        if (shouldLog(Level.VERBOSE)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(Integer.toBinaryString(i));
            internalProcess(outline107.toString());
            AccessoriesLogger accessoriesLogger2 = accessoriesLogger;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(str);
            outline1072.append(Integer.toBinaryString(i));
            accessoriesLogger2.v(TAG, outline1072.toString());
        }
    }

    public static void d(String str, byte[] bArr, int i, int i2) {
        if (shouldLog(Level.DEBUG)) {
            d(StringUtils.safeFormat(Locale.US, "%s (%d bytes)", str, Integer.valueOf(i2)));
        }
    }

    public static void debugWithTag(String str, String str2) {
        if (shouldLog(Level.DEBUG)) {
            internalProcess(str2);
            accessoriesLogger.d(str, str2);
        }
    }

    public static void e(String str, Object... objArr) {
        if (shouldLog(Level.ERROR)) {
            e(StringUtils.safeFormat(Locale.US, str, objArr));
        }
    }

    public static void errorWithTag(String str, String str2) {
        if (shouldLog(Level.ERROR)) {
            internalProcess(str2);
            accessoriesLogger.e(str, str2);
        }
    }

    public static Level getLevel() {
        return level;
    }

    public static Processor getProcessor() {
        return currentProcessor;
    }

    public static void i(String str, Object... objArr) {
        if (shouldLog(Level.INFO)) {
            i(StringUtils.safeFormat(Locale.US, str, objArr));
        }
    }

    public static void infoWithTag(String str, String str2) {
        if (shouldLog(Level.INFO)) {
            internalProcess(str2);
            accessoriesLogger.i(str, str2);
        }
    }

    private static void internalProcess(String str) {
        Processor processor = currentProcessor;
        if (processor != null) {
            processor.process(str);
        }
    }

    public static void setAccessoriesLogger(AccessoriesLogger accessoriesLogger2) {
        Preconditions.notNull(accessoriesLogger2, "logger");
        accessoriesLogger = accessoriesLogger2;
    }

    public static void setLevel(Level level2) {
        Preconditions.notNull(level2, ModelTransformer.KEY_BATTERY_LEVEL);
        level = level2;
    }

    public static void setProcessor(Processor processor) {
        currentProcessor = processor;
    }

    public static boolean shouldLog(Level level2) {
        return level.ordinal() >= level2.ordinal();
    }

    public static void v(String str, byte[] bArr) {
        v(str, bArr, 0, bArr.length);
    }

    public static void w(String str, Object... objArr) {
        if (shouldLog(Level.INFO)) {
            w(StringUtils.safeFormat(Locale.US, str, objArr));
        }
    }

    public static void warnWithTag(String str, String str2) {
        if (shouldLog(Level.INFO)) {
            internalProcess(str2);
            accessoriesLogger.w(str, str2);
        }
    }

    private static void internalProcess(String str, Throwable th) {
        Processor processor = currentProcessor;
        if (processor != null) {
            processor.process(str, th);
        }
    }

    public static void v(String str, Object... objArr) {
        if (shouldLog(Level.VERBOSE)) {
            v(StringUtils.safeFormat(Locale.US, str, objArr));
        }
    }

    public static void d(String str, byte[] bArr) {
        d(str, bArr, 0, bArr.length);
    }

    public static void e(String str, Throwable th, Object... objArr) {
        if (shouldLog(Level.ERROR)) {
            e(StringUtils.safeFormat(Locale.US, str, objArr), th);
        }
    }

    public static void i(String str) {
        if (shouldLog(Level.INFO)) {
            internalProcess(str);
            accessoriesLogger.i(TAG, str);
        }
    }

    public static void w(String str) {
        if (shouldLog(Level.INFO)) {
            internalProcess(str);
            accessoriesLogger.w(TAG, str);
        }
    }

    public static void d(String str, Object... objArr) {
        if (shouldLog(Level.DEBUG)) {
            d(StringUtils.safeFormat(Locale.US, str, objArr));
        }
    }

    public static void debugWithTag(String str, String str2, Object... objArr) {
        if (shouldLog(Level.DEBUG)) {
            debugWithTag(str, StringUtils.safeFormat(Locale.US, str2, objArr));
        }
    }

    public static void errorWithTag(String str, String str2, Object... objArr) {
        if (shouldLog(Level.ERROR)) {
            errorWithTag(str, StringUtils.safeFormat(Locale.US, str2, objArr));
        }
    }

    public static void infoWithTag(String str, String str2, Object... objArr) {
        if (shouldLog(Level.INFO)) {
            infoWithTag(str, StringUtils.safeFormat(Locale.US, str2, objArr));
        }
    }

    public static void v(String str, byte[] bArr, int i, int i2) {
        if (shouldLog(Level.VERBOSE)) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(" (");
            sb.append(i2);
            sb.append(" bytes): ");
            for (int i3 = 0; i3 < i2; i3++) {
                if (i3 > 0) {
                    sb.append(Chars.SPACE);
                }
                sb.append(StringUtils.safeFormat(Locale.US, "%02x", Integer.valueOf(bArr[i + i3] & 255)));
            }
            internalProcess(sb.toString());
            accessoriesLogger.v(TAG, sb.toString());
        }
    }

    public static void warnWithTag(String str, String str2, Object... objArr) {
        if (shouldLog(Level.INFO)) {
            warnWithTag(str, StringUtils.safeFormat(Locale.US, str2, objArr));
        }
    }

    public static void d(String str, Throwable th, Object... objArr) {
        if (shouldLog(Level.DEBUG)) {
            d(StringUtils.safeFormat(Locale.US, str, objArr), th);
        }
    }

    public static void debugWithTag(String str, String str2, Throwable th) {
        if (shouldLog(Level.DEBUG)) {
            internalProcess(str2, th);
            accessoriesLogger.d(str, str2, th);
        }
    }

    public static void e(String str, Throwable th) {
        if (shouldLog(Level.ERROR)) {
            internalProcess(str, th);
            accessoriesLogger.e(TAG, str, th);
        }
    }

    public static void errorWithTag(String str, String str2, Throwable th) {
        if (shouldLog(Level.ERROR)) {
            internalProcess(str2, th);
            accessoriesLogger.e(str, str2, th);
        }
    }

    public static void d(String str, Throwable th) {
        if (shouldLog(Level.DEBUG)) {
            internalProcess(str, th);
            accessoriesLogger.d(TAG, str, th);
        }
    }

    public static void debugWithTag(String str, String str2, Throwable th, Object... objArr) {
        if (shouldLog(Level.DEBUG)) {
            debugWithTag(str, StringUtils.safeFormat(Locale.US, str2, objArr), th);
        }
    }

    public static void e(String str) {
        if (shouldLog(Level.ERROR)) {
            internalProcess(str);
            accessoriesLogger.e(TAG, str);
        }
    }

    public static void errorWithTag(String str, String str2, Throwable th, Object... objArr) {
        if (shouldLog(Level.ERROR)) {
            errorWithTag(str, StringUtils.safeFormat(Locale.US, str2, objArr), th);
        }
    }

    public static void d(String str) {
        if (shouldLog(Level.DEBUG)) {
            internalProcess(str);
            accessoriesLogger.d(TAG, str);
        }
    }

    public static void v(String str, Throwable th, Object... objArr) {
        if (shouldLog(Level.VERBOSE)) {
            v(StringUtils.safeFormat(Locale.US, str, objArr), th);
        }
    }

    public static void v(String str, Throwable th) {
        if (shouldLog(Level.VERBOSE)) {
            internalProcess(str, th);
            accessoriesLogger.v(TAG, str, th);
        }
    }

    public static void v(String str) {
        if (shouldLog(Level.VERBOSE)) {
            internalProcess(str);
            accessoriesLogger.v(TAG, str);
        }
    }
}
