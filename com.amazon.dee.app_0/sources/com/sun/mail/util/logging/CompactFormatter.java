package com.sun.mail.util.logging;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.util.Collections;
import java.util.Date;
import java.util.Formattable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import org.apache.logging.log4j.util.Chars;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes3.dex */
public class CompactFormatter extends Formatter {
    private final String fmt;

    /* loaded from: classes3.dex */
    private class Alternate implements Formattable {
        private final String left;
        private final String right;

        Alternate(String str, String str2) {
            this.left = String.valueOf(str);
            this.right = String.valueOf(str2);
        }

        private String pad(int i, String str, int i2) {
            int length = i2 - str.length();
            StringBuilder sb = new StringBuilder(i2);
            int i3 = 0;
            if ((i & 1) == 1) {
                while (i3 < length) {
                    sb.append(Chars.SPACE);
                    i3++;
                }
                sb.append(str);
            } else {
                sb.append(str);
                while (i3 < length) {
                    sb.append(Chars.SPACE);
                    i3++;
                }
            }
            return sb.toString();
        }

        @Override // java.util.Formattable
        public void formatTo(java.util.Formatter formatter, int i, int i2, int i3) {
            String str = this.left;
            String str2 = this.right;
            if ((i & 2) == 2) {
                str = str.toUpperCase(formatter.locale());
                str2 = str2.toUpperCase(formatter.locale());
            }
            if ((i & 4) == 4) {
                str = CompactFormatter.this.toAlternate(str);
                str2 = CompactFormatter.this.toAlternate(str2);
            }
            if (i3 <= 0) {
                i3 = Integer.MAX_VALUE;
            }
            int min = Math.min(str.length(), i3);
            if (min > (i3 >> 1)) {
                min = Math.max(min - str2.length(), min >> 1);
            }
            if (min > 0) {
                if (min > str.length() && Character.isHighSurrogate(str.charAt(min - 1))) {
                    min--;
                }
                str = str.substring(0, min);
            }
            String substring = str2.substring(0, Math.min(i3 - min, str2.length()));
            if (i2 > 0) {
                int i4 = i2 >> 1;
                if (str.length() < i4) {
                    str = pad(i, str, i4);
                }
                if (substring.length() < i4) {
                    substring = pad(i, substring, i4);
                }
            }
            Object[] array = Collections.emptySet().toArray();
            formatter.format(str, array);
            if (str.length() != 0 && substring.length() != 0) {
                formatter.format(AccessoryMetricsConstants.DELIMITER, array);
            }
            formatter.format(substring, array);
        }
    }

    public CompactFormatter() {
        this.fmt = initFormat(CompactFormatter.class.getName());
    }

    private boolean defaultIgnore(StackTraceElement stackTraceElement) {
        return isSynthetic(stackTraceElement) || isStaticUtility(stackTraceElement) || isReflection(stackTraceElement);
    }

    private String findAndFormat(StackTraceElement[] stackTraceElementArr) {
        String str;
        int length = stackTraceElementArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str = "";
                break;
            }
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            if (!ignore(stackTraceElement)) {
                str = formatStackTraceElement(stackTraceElement);
                break;
            }
            i++;
        }
        if (isNullOrSpaces(str)) {
            for (StackTraceElement stackTraceElement2 : stackTraceElementArr) {
                if (!defaultIgnore(stackTraceElement2)) {
                    return formatStackTraceElement(stackTraceElement2);
                }
            }
            return str;
        }
        return str;
    }

    private String formatStackTraceElement(StackTraceElement stackTraceElement) {
        String stackTraceElement2;
        String simpleClassName = simpleClassName(stackTraceElement.getClassName());
        if (simpleClassName != null) {
            stackTraceElement2 = stackTraceElement.toString().replace(stackTraceElement.getClassName(), simpleClassName);
        } else {
            stackTraceElement2 = stackTraceElement.toString();
        }
        String simpleFileName = simpleFileName(stackTraceElement.getFileName());
        return (simpleFileName == null || !stackTraceElement2.startsWith(simpleFileName)) ? stackTraceElement2 : stackTraceElement2.replace(stackTraceElement.getFileName(), "");
    }

    private String formatToString(Throwable th) {
        return simpleClassName(apply(th).getClass()) + RealTimeTextConstants.COLON_SPACE + formatMessage(th);
    }

    private String initFormat(String str) {
        String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".format"));
        return isNullOrSpaces(fromLogManager) ? "%7$#.160s%n" : fromLogManager;
    }

    private static boolean isNullOrSpaces(String str) {
        return str == null || str.trim().length() == 0;
    }

    private boolean isReflection(StackTraceElement stackTraceElement) {
        try {
            return LogManagerProperties.isReflectionClass(stackTraceElement.getClassName());
        } catch (RuntimeException | Exception | LinkageError unused) {
            return stackTraceElement.getClassName().startsWith("java.lang.reflect.") || stackTraceElement.getClassName().startsWith("sun.reflect.");
        }
    }

    private boolean isStaticUtility(StackTraceElement stackTraceElement) {
        try {
            return LogManagerProperties.isStaticUtilityClass(stackTraceElement.getClassName());
        } catch (RuntimeException | Exception | LinkageError unused) {
            String className = stackTraceElement.getClassName();
            return (className.endsWith("s") && !className.endsWith("es")) || className.contains("Util") || className.endsWith("Throwables");
        }
    }

    private boolean isSynthetic(StackTraceElement stackTraceElement) {
        return stackTraceElement.getMethodName().indexOf(36) > -1;
    }

    private boolean isUnknown(StackTraceElement stackTraceElement) {
        return stackTraceElement.getLineNumber() < 0;
    }

    private static String replaceClassName(String str, Throwable th) {
        if (!isNullOrSpaces(str)) {
            int i = 0;
            while (th != null) {
                Class<?> cls = th.getClass();
                str = str.replace(cls.getName(), simpleClassName(cls));
                i++;
                if (i == 65536) {
                    break;
                }
                th = th.getCause();
            }
        }
        return str;
    }

    private static String simpleClassName(Class<?> cls) {
        try {
            return cls.getSimpleName();
        } catch (InternalError unused) {
            return simpleClassName(cls.getName());
        }
    }

    private static String simpleFileName(String str) {
        int lastIndexOf;
        return (str == null || (lastIndexOf = str.lastIndexOf(46)) <= -1) ? str : str.substring(0, lastIndexOf);
    }

    protected Throwable apply(Throwable th) {
        return SeverityComparator.getInstance().apply(th);
    }

    @Override // java.util.logging.Formatter
    public String format(LogRecord logRecord) {
        ResourceBundle resourceBundle = logRecord.getResourceBundle();
        Locale locale = resourceBundle == null ? null : resourceBundle.getLocale();
        String formatMessage = formatMessage(logRecord);
        String formatThrown = formatThrown(logRecord);
        String formatError = formatError(logRecord);
        Object[] objArr = {new Date(logRecord.getMillis()), formatSource(logRecord), formatLoggerName(logRecord), formatLevel(logRecord), formatMessage, formatThrown, new Alternate(formatMessage, formatThrown), new Alternate(formatThrown, formatMessage), Long.valueOf(logRecord.getSequenceNumber()), formatThreadID(logRecord), formatError, new Alternate(formatMessage, formatError), new Alternate(formatError, formatMessage), formatBackTrace(logRecord), logRecord.getResourceBundleName(), logRecord.getMessage()};
        if (locale == null) {
            return String.format(this.fmt, objArr);
        }
        return String.format(locale, this.fmt, objArr);
    }

    public String formatBackTrace(LogRecord logRecord) {
        Throwable thrown = logRecord.getThrown();
        if (thrown != null) {
            String findAndFormat = findAndFormat(apply(thrown).getStackTrace());
            if (!isNullOrSpaces(findAndFormat)) {
                return findAndFormat;
            }
            int i = 0;
            while (thrown != null) {
                findAndFormat = findAndFormat(thrown.getStackTrace());
                if (!isNullOrSpaces(findAndFormat) || (i = i + 1) == 65536) {
                    return findAndFormat;
                }
                thrown = thrown.getCause();
            }
            return findAndFormat;
        }
        return "";
    }

    public String formatError(LogRecord logRecord) {
        Throwable thrown = logRecord.getThrown();
        return thrown != null ? formatToString(thrown) : "";
    }

    public String formatLevel(LogRecord logRecord) {
        return logRecord.getLevel().getLocalizedName();
    }

    public String formatLoggerName(LogRecord logRecord) {
        return simpleClassName(logRecord.getLoggerName());
    }

    @Override // java.util.logging.Formatter
    public String formatMessage(LogRecord logRecord) {
        return replaceClassName(replaceClassName(super.formatMessage(logRecord), logRecord.getThrown()), logRecord.getParameters());
    }

    public String formatSource(LogRecord logRecord) {
        String sourceClassName = logRecord.getSourceClassName();
        if (sourceClassName != null) {
            if (logRecord.getSourceMethodName() != null) {
                return simpleClassName(sourceClassName) + " " + logRecord.getSourceMethodName();
            }
            return simpleClassName(sourceClassName);
        }
        return simpleClassName(logRecord.getLoggerName());
    }

    public Number formatThreadID(LogRecord logRecord) {
        return Long.valueOf(logRecord.getThreadID() & BodyPartID.bodyIdMax);
    }

    public String formatThrown(LogRecord logRecord) {
        Throwable thrown = logRecord.getThrown();
        String str = "";
        if (thrown != null) {
            String formatBackTrace = formatBackTrace(logRecord);
            StringBuilder sb = new StringBuilder();
            sb.append(formatToString(thrown));
            if (!isNullOrSpaces(formatBackTrace)) {
                str = Chars.SPACE + formatBackTrace;
            }
            sb.append(str);
            return sb.toString();
        }
        return str;
    }

    protected boolean ignore(StackTraceElement stackTraceElement) {
        return isUnknown(stackTraceElement) || defaultIgnore(stackTraceElement);
    }

    protected String toAlternate(String str) {
        if (str != null) {
            return str.replaceAll("[\\x00-\\x1F\\x7F]+", "");
        }
        return null;
    }

    private static String simpleClassName(String str) {
        int lastIndexOf;
        return (str == null || (lastIndexOf = str.lastIndexOf(46)) <= -1) ? str : str.substring(lastIndexOf + 1);
    }

    public CompactFormatter(String str) {
        this.fmt = str == null ? initFormat(CompactFormatter.class.getName()) : str;
    }

    public String formatMessage(Throwable th) {
        return th != null ? replaceClassName(apply(th).getMessage(), th) : "";
    }

    private static String replaceClassName(String str, Object[] objArr) {
        if (!isNullOrSpaces(str) && objArr != null) {
            for (Object obj : objArr) {
                if (obj != null) {
                    Class<?> cls = obj.getClass();
                    str = str.replace(cls.getName(), simpleClassName(cls));
                }
            }
        }
        return str;
    }
}
