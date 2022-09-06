package com.sun.mail.util.logging;

import java.lang.reflect.UndeclaredThrowableException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
/* loaded from: classes3.dex */
public class CollectorFormatter extends Formatter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long INIT_TIME = System.currentTimeMillis();
    private final Comparator<? super LogRecord> comparator;
    private long count;
    private final String fmt;
    private final Formatter formatter;
    private LogRecord last;
    private long thrown;
    private long generation = 1;
    private long minMillis = INIT_TIME;
    private long maxMillis = Long.MIN_VALUE;

    public CollectorFormatter() {
        String name = CollectorFormatter.class.getName();
        this.fmt = initFormat(name);
        this.formatter = initFormatter(name);
        this.comparator = initComparator(name);
    }

    private synchronized void accept(LogRecord logRecord) {
        long millis = logRecord.getMillis();
        long j = this.count + 1;
        this.count = j;
        if (j != 1) {
            this.minMillis = Math.min(this.minMillis, millis);
        } else {
            this.minMillis = millis;
        }
        this.maxMillis = Math.max(this.maxMillis, millis);
        if (logRecord.getThrown() != null) {
            this.thrown++;
        }
    }

    private synchronized boolean acceptAndUpdate(LogRecord logRecord, LogRecord logRecord2) {
        if (logRecord == this.last) {
            accept(logRecord2);
            this.last = logRecord2;
            return true;
        }
        return false;
    }

    private String formatRecord(Handler handler, boolean z) {
        LogRecord logRecord;
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long currentTimeMillis;
        String formatMessage;
        String str;
        String str2;
        MessageFormat messageFormat;
        long j6;
        ResourceBundle resourceBundle;
        synchronized (this) {
            logRecord = this.last;
            j = this.count;
            j2 = this.generation;
            j3 = this.thrown;
            j4 = this.minMillis;
            j5 = this.maxMillis;
            currentTimeMillis = System.currentTimeMillis();
            if (j == 0) {
                j5 = currentTimeMillis;
            }
            if (z) {
                reset(j5);
            }
        }
        Formatter formatter = this.formatter;
        if (formatter != null) {
            synchronized (formatter) {
                str = formatter.getHead(handler);
                formatMessage = logRecord != null ? formatter.format(logRecord) : "";
                str2 = formatter.getTail(handler);
            }
        } else {
            formatMessage = logRecord != null ? formatMessage(logRecord) : "";
            str = "";
            str2 = "";
        }
        String str3 = formatMessage;
        String str4 = str2;
        String str5 = str;
        Locale locale = null;
        if (logRecord != null && (resourceBundle = logRecord.getResourceBundle()) != null) {
            locale = resourceBundle.getLocale();
        }
        if (locale == null) {
            messageFormat = new MessageFormat(this.fmt);
            j6 = currentTimeMillis;
        } else {
            j6 = currentTimeMillis;
            messageFormat = new MessageFormat(this.fmt, locale);
        }
        return messageFormat.format(new Object[]{finish(str5), finish(str3), finish(str4), Long.valueOf(j), Long.valueOf(j - 1), Long.valueOf(j3), Long.valueOf(j - j3), Long.valueOf(j4), Long.valueOf(j5), Long.valueOf(j5 - j4), Long.valueOf(INIT_TIME), Long.valueOf(j6), Long.valueOf(j6 - INIT_TIME), Long.valueOf(j2)});
    }

    private Comparator<? super LogRecord> initComparator(String str) {
        String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".comparator"));
        String fromLogManager2 = LogManagerProperties.fromLogManager(str.concat(".comparator.reverse"));
        if (fromLogManager != null) {
            try {
                if (fromLogManager.length() != 0) {
                    if (!"null".equalsIgnoreCase(fromLogManager)) {
                        Comparator<? super LogRecord> newComparator = LogManagerProperties.newComparator(fromLogManager);
                        return Boolean.parseBoolean(fromLogManager2) ? LogManagerProperties.reverseOrder(newComparator) : newComparator;
                    } else if (fromLogManager2 != null) {
                        throw new IllegalArgumentException("No comparator to reverse.");
                    } else {
                        return null;
                    }
                }
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new UndeclaredThrowableException(e2);
            }
        }
        if (fromLogManager2 == null) {
            return (Comparator) Comparator.class.cast(SeverityComparator.getInstance());
        }
        throw new IllegalArgumentException("No comparator to reverse.");
    }

    private String initFormat(String str) {
        String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".format"));
        return (fromLogManager == null || fromLogManager.length() == 0) ? "{0}{1}{2}{4,choice,-1#|0#|0<... {4,number,integer} more}\n" : fromLogManager;
    }

    private Formatter initFormatter(String str) {
        String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".formatter"));
        if (fromLogManager != null && fromLogManager.length() != 0) {
            if ("null".equalsIgnoreCase(fromLogManager)) {
                return null;
            }
            try {
                return LogManagerProperties.newFormatter(fromLogManager);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new UndeclaredThrowableException(e2);
            }
        }
        return (Formatter) Formatter.class.cast(new CompactFormatter());
    }

    private synchronized LogRecord peek() {
        return this.last;
    }

    private synchronized void reset(long j) {
        if (this.last != null) {
            this.last = null;
            this.generation++;
        }
        this.count = 0L;
        this.thrown = 0L;
        this.minMillis = j;
        this.maxMillis = Long.MIN_VALUE;
    }

    protected LogRecord apply(LogRecord logRecord, LogRecord logRecord2) {
        if (logRecord != null && logRecord2 != null) {
            Comparator<? super LogRecord> comparator = this.comparator;
            return (comparator == null || comparator.compare(logRecord, logRecord2) < 0) ? logRecord2 : logRecord;
        }
        throw new NullPointerException();
    }

    protected String finish(String str) {
        return str.trim();
    }

    @Override // java.util.logging.Formatter
    public String format(LogRecord logRecord) {
        boolean z;
        if (logRecord != null) {
            do {
                LogRecord peek = peek();
                LogRecord apply = apply(peek != null ? peek : logRecord, logRecord);
                if (peek != apply) {
                    apply.getSourceMethodName();
                    z = acceptAndUpdate(peek, apply);
                    continue;
                } else {
                    z = true;
                    accept(logRecord);
                    continue;
                }
            } while (!z);
            return "";
        }
        throw new NullPointerException();
    }

    @Override // java.util.logging.Formatter
    public String getTail(Handler handler) {
        return formatRecord(handler, true);
    }

    public String toString() {
        try {
            return formatRecord(null, false);
        } catch (RuntimeException unused) {
            return super.toString();
        }
    }

    public CollectorFormatter(String str) {
        String name = CollectorFormatter.class.getName();
        this.fmt = str == null ? initFormat(name) : str;
        this.formatter = initFormatter(name);
        this.comparator = initComparator(name);
    }

    public CollectorFormatter(String str, Formatter formatter, Comparator<? super LogRecord> comparator) {
        this.fmt = str == null ? initFormat(CollectorFormatter.class.getName()) : str;
        this.formatter = formatter;
        this.comparator = comparator;
    }
}
