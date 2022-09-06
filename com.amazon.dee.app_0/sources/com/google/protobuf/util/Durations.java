package com.google.protobuf.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.google.protobuf.Duration;
import java.text.ParseException;
import java.util.Comparator;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.threeten.bp.Year;
/* loaded from: classes3.dex */
public final class Durations {
    static final long DURATION_SECONDS_MAX = 315576000000L;
    static final long DURATION_SECONDS_MIN = -315576000000L;
    public static final Duration MIN_VALUE = Duration.newBuilder().setSeconds(-315576000000L).setNanos(Year.MIN_VALUE).mo10084build();
    public static final Duration MAX_VALUE = Duration.newBuilder().setSeconds(315576000000L).setNanos(Year.MAX_VALUE).mo10084build();
    private static final Comparator<Duration> COMPARATOR = new Comparator<Duration>() { // from class: com.google.protobuf.util.Durations.1
        @Override // java.util.Comparator
        public int compare(Duration duration, Duration duration2) {
            Durations.checkValid(duration);
            Durations.checkValid(duration2);
            int compare = Long.compare(duration.getSeconds(), duration2.getSeconds());
            return compare != 0 ? compare : Integer.compare(duration.getNanos(), duration2.getNanos());
        }
    };

    private Durations() {
    }

    public static Duration add(Duration duration, Duration duration2) {
        checkValid(duration);
        checkValid(duration2);
        return normalizedDuration(LongMath.checkedAdd(duration.getSeconds(), duration2.getSeconds()), IntMath.checkedAdd(duration.getNanos(), duration2.getNanos()));
    }

    public static Duration checkValid(Duration duration) {
        long seconds = duration.getSeconds();
        int nanos = duration.getNanos();
        if (isValid(seconds, nanos)) {
            return duration;
        }
        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", Long.valueOf(seconds), Integer.valueOf(nanos)));
    }

    public static Comparator<Duration> comparator() {
        return COMPARATOR;
    }

    public static int compare(Duration duration, Duration duration2) {
        return COMPARATOR.compare(duration, duration2);
    }

    public static Duration fromMicros(long j) {
        return normalizedDuration(j / 1000000, (int) ((j % 1000000) * 1000));
    }

    public static Duration fromMillis(long j) {
        return normalizedDuration(j / 1000, (int) ((j % 1000) * 1000000));
    }

    public static Duration fromNanos(long j) {
        return normalizedDuration(j / C.NANOS_PER_SECOND, (int) (j % C.NANOS_PER_SECOND));
    }

    public static Duration fromSeconds(long j) {
        return normalizedDuration(j, 0);
    }

    public static boolean isValid(long j, int i) {
        if (j >= -315576000000L && j <= 315576000000L) {
            long j2 = i;
            if (j2 >= -999999999 && j2 < C.NANOS_PER_SECOND) {
                int i2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i2 >= 0 && i >= 0) {
                    return true;
                }
                if (i2 <= 0 && i <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValid(Duration duration) {
        return isValid(duration.getSeconds(), duration.getNanos());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Duration normalizedDuration(long j, int i) {
        long j2 = i;
        if (j2 <= -1000000000 || j2 >= C.NANOS_PER_SECOND) {
            j = LongMath.checkedAdd(j, j2 / C.NANOS_PER_SECOND);
            i = (int) (j2 % C.NANOS_PER_SECOND);
        }
        if (j > 0 && i < 0) {
            i = (int) (i + C.NANOS_PER_SECOND);
            j--;
        }
        if (j < 0 && i > 0) {
            i = (int) (i - C.NANOS_PER_SECOND);
            j++;
        }
        return checkValid(Duration.newBuilder().setSeconds(j).setNanos(i).mo10084build());
    }

    public static Duration parse(String str) throws ParseException {
        boolean z;
        String str2;
        if (str.isEmpty() || str.charAt(str.length() - 1) != 's') {
            throw new ParseException(GeneratedOutlineSupport1.outline72("Invalid duration string: ", str), 0);
        }
        if (str.charAt(0) == '-') {
            str = str.substring(1);
            z = true;
        } else {
            z = false;
        }
        String outline51 = GeneratedOutlineSupport1.outline51(str, 1, 0);
        int indexOf = outline51.indexOf(46);
        if (indexOf != -1) {
            str2 = outline51.substring(indexOf + 1);
            outline51 = outline51.substring(0, indexOf);
        } else {
            str2 = "";
        }
        long parseLong = Long.parseLong(outline51);
        int parseNanos = str2.isEmpty() ? 0 : Timestamps.parseNanos(str2);
        if (parseLong < 0) {
            throw new ParseException(GeneratedOutlineSupport1.outline72("Invalid duration string: ", str), 0);
        }
        if (z) {
            parseLong = -parseLong;
            parseNanos = -parseNanos;
        }
        try {
            return normalizedDuration(parseLong, parseNanos);
        } catch (IllegalArgumentException unused) {
            throw new ParseException("Duration value is out of range.", 0);
        }
    }

    public static Duration subtract(Duration duration, Duration duration2) {
        checkValid(duration);
        checkValid(duration2);
        return normalizedDuration(LongMath.checkedSubtract(duration.getSeconds(), duration2.getSeconds()), IntMath.checkedSubtract(duration.getNanos(), duration2.getNanos()));
    }

    public static long toMicros(Duration duration) {
        checkValid(duration);
        return LongMath.checkedAdd(LongMath.checkedMultiply(duration.getSeconds(), 1000000L), duration.getNanos() / 1000);
    }

    public static long toMillis(Duration duration) {
        checkValid(duration);
        return LongMath.checkedAdd(LongMath.checkedMultiply(duration.getSeconds(), 1000L), duration.getNanos() / 1000000);
    }

    public static long toNanos(Duration duration) {
        checkValid(duration);
        return LongMath.checkedAdd(LongMath.checkedMultiply(duration.getSeconds(), C.NANOS_PER_SECOND), duration.getNanos());
    }

    public static long toSeconds(Duration duration) {
        return checkValid(duration).getSeconds();
    }

    public static String toString(Duration duration) {
        checkValid(duration);
        long seconds = duration.getSeconds();
        int nanos = duration.getNanos();
        StringBuilder sb = new StringBuilder();
        if (seconds < 0 || nanos < 0) {
            sb.append(ProcessIdUtil.DEFAULT_PROCESSID);
            seconds = -seconds;
            nanos = -nanos;
        }
        sb.append(seconds);
        if (nanos != 0) {
            sb.append(".");
            sb.append(Timestamps.formatNanos(nanos));
        }
        sb.append("s");
        return sb.toString();
    }
}
