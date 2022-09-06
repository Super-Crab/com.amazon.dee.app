package com.sun.mail.util.logging;

import java.io.Serializable;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.LogRecord;
/* loaded from: classes3.dex */
public class SeverityComparator implements Comparator<LogRecord>, Serializable {
    private static final SeverityComparator INSTANCE = new SeverityComparator();
    private static final long serialVersionUID = -2620442245251791965L;

    private int compare(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SeverityComparator getInstance() {
        return INSTANCE;
    }

    private static String toString(Object obj, Object obj2) {
        return obj + ", " + obj2;
    }

    public Throwable apply(Throwable th) {
        Throwable th2 = null;
        Throwable th3 = null;
        int i = 0;
        Throwable th4 = th;
        while (true) {
            if (th == null) {
                th = th4;
                break;
            }
            if (isNormal(th)) {
                th2 = th;
            }
            if (th2 == null && (th instanceof Error)) {
                th3 = th;
            }
            i++;
            if (i == 65536) {
                break;
            }
            th4 = th;
            th = th.getCause();
        }
        return th3 != null ? th3 : th2 != null ? th2 : th;
    }

    public final int applyThenCompare(Throwable th, Throwable th2) {
        if (th == th2) {
            return 0;
        }
        return compareThrowable(apply(th), apply(th2));
    }

    public int compareThrowable(Throwable th, Throwable th2) {
        if (th == th2) {
            return 0;
        }
        if (th == null) {
            return isNormal(th2) ? 1 : -1;
        } else if (th2 == null) {
            return isNormal(th) ? -1 : 1;
        } else if (th.getClass() == th2.getClass()) {
            return 0;
        } else {
            if (isNormal(th)) {
                return isNormal(th2) ? 0 : -1;
            } else if (isNormal(th2)) {
                return 1;
            } else {
                if (th instanceof Error) {
                    return !(th2 instanceof Error) ? 1 : 0;
                }
                if (!(th instanceof RuntimeException)) {
                    return ((th2 instanceof Error) || (th2 instanceof RuntimeException)) ? -1 : 0;
                } else if (th2 instanceof Error) {
                    return -1;
                } else {
                    return th2 instanceof RuntimeException ? 0 : 1;
                }
            }
        }
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == SeverityComparator.class;
    }

    public int hashCode() {
        return SeverityComparator.class.hashCode() * 31;
    }

    public boolean isNormal(Throwable th) {
        if (th == null) {
            return false;
        }
        for (Class<?> cls = th.getClass(); cls != Throwable.class; cls = cls.getSuperclass()) {
            if (Error.class.isAssignableFrom(cls)) {
                if (cls.getName().equals("java.lang.ThreadDeath")) {
                    return true;
                }
            } else if (cls.getName().contains("Interrupt")) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Comparator
    public int compare(LogRecord logRecord, LogRecord logRecord2) {
        if (logRecord == null || logRecord2 == null) {
            throw new NullPointerException(toString(logRecord, logRecord2));
        }
        if (logRecord == logRecord2) {
            return 0;
        }
        int compare = compare(logRecord.getLevel(), logRecord2.getLevel());
        if (compare != 0) {
            return compare;
        }
        int applyThenCompare = applyThenCompare(logRecord.getThrown(), logRecord2.getThrown());
        if (applyThenCompare != 0) {
            return applyThenCompare;
        }
        int compare2 = compare(logRecord.getSequenceNumber(), logRecord2.getSequenceNumber());
        return compare2 == 0 ? compare(logRecord.getMillis(), logRecord2.getMillis()) : compare2;
    }

    private int compare(Level level, Level level2) {
        if (level == level2) {
            return 0;
        }
        return compare(level.intValue(), level2.intValue());
    }
}
