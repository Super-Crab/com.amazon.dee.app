package com.google.protobuf.util;

import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;
import java.math.BigInteger;
import java.text.ParseException;
@Deprecated
/* loaded from: classes3.dex */
public final class TimeUtil {
    public static final long DURATION_SECONDS_MAX = 315576000000L;
    public static final long DURATION_SECONDS_MIN = -315576000000L;
    private static final long NANOS_PER_SECOND = 1000000000;
    private static final BigInteger NANOS_PER_SECOND_BIG_INTEGER = new BigInteger(String.valueOf(1000000000L));
    public static final long TIMESTAMP_SECONDS_MAX = 253402300799L;
    public static final long TIMESTAMP_SECONDS_MIN = -62135596800L;

    private TimeUtil() {
    }

    @Deprecated
    public static Duration add(Duration duration, Duration duration2) {
        return Durations.add(duration, duration2);
    }

    @Deprecated
    public static Timestamp add(Timestamp timestamp, Duration duration) {
        return Timestamps.add(timestamp, duration);
    }

    private static Duration createDurationFromBigInteger(BigInteger bigInteger) {
        return normalizedDuration(bigInteger.divide(new BigInteger(String.valueOf(1000000000L))).longValue(), bigInteger.remainder(new BigInteger(String.valueOf(1000000000L))).intValue());
    }

    @Deprecated
    public static Duration createDurationFromMicros(long j) {
        return Durations.fromMicros(j);
    }

    @Deprecated
    public static Duration createDurationFromMillis(long j) {
        return Durations.fromMillis(j);
    }

    @Deprecated
    public static Duration createDurationFromNanos(long j) {
        return Durations.fromNanos(j);
    }

    @Deprecated
    public static Timestamp createTimestampFromMicros(long j) {
        return Timestamps.fromMicros(j);
    }

    @Deprecated
    public static Timestamp createTimestampFromMillis(long j) {
        return Timestamps.fromMillis(j);
    }

    @Deprecated
    public static Timestamp createTimestampFromNanos(long j) {
        return Timestamps.fromNanos(j);
    }

    @Deprecated
    public static Duration distance(Timestamp timestamp, Timestamp timestamp2) {
        return Timestamps.between(timestamp, timestamp2);
    }

    public static long divide(Duration duration, Duration duration2) {
        return toBigInteger(duration).divide(toBigInteger(duration2)).longValue();
    }

    public static Duration divide(Duration duration, double d) {
        return multiply(duration, 1.0d / d);
    }

    public static Duration divide(Duration duration, long j) {
        return createDurationFromBigInteger(toBigInteger(duration).divide(toBigInteger(j)));
    }

    @Deprecated
    public static Timestamp getCurrentTime() {
        return Timestamps.fromMillis(System.currentTimeMillis());
    }

    @Deprecated
    public static Timestamp getEpoch() {
        return Timestamp.getDefaultInstance();
    }

    public static Duration multiply(Duration duration, double d) {
        double nanos = ((duration.getNanos() * d) / 1.0E9d) + (duration.getSeconds() * d);
        if (nanos < -9.223372036854776E18d || nanos > 9.223372036854776E18d) {
            throw new IllegalArgumentException("Result is out of valid range.");
        }
        long j = (long) nanos;
        return normalizedDuration(j, (int) ((nanos - j) * 1.0E9d));
    }

    public static Duration multiply(Duration duration, long j) {
        return createDurationFromBigInteger(toBigInteger(duration).multiply(toBigInteger(j)));
    }

    private static Duration normalizedDuration(long j, int i) {
        long j2 = i;
        if (j2 <= -1000000000 || j2 >= 1000000000) {
            j += j2 / 1000000000;
            i = (int) (j2 % 1000000000);
        }
        if (j > 0 && i < 0) {
            i = (int) (i + 1000000000);
            j--;
        }
        if (j < 0 && i > 0) {
            i = (int) (i - 1000000000);
            j++;
        }
        if (j < DURATION_SECONDS_MIN || j > DURATION_SECONDS_MAX) {
            throw new IllegalArgumentException("Duration is out of valid range.");
        }
        return Duration.newBuilder().setSeconds(j).setNanos(i).mo10084build();
    }

    @Deprecated
    public static Duration parseDuration(String str) throws ParseException {
        return Durations.parse(str);
    }

    @Deprecated
    public static Timestamp parseTimestamp(String str) throws ParseException {
        return Timestamps.parse(str);
    }

    public static Duration remainder(Duration duration, Duration duration2) {
        return createDurationFromBigInteger(toBigInteger(duration).remainder(toBigInteger(duration2)));
    }

    @Deprecated
    public static Duration subtract(Duration duration, Duration duration2) {
        return Durations.subtract(duration, duration2);
    }

    @Deprecated
    public static Timestamp subtract(Timestamp timestamp, Duration duration) {
        return Timestamps.subtract(timestamp, duration);
    }

    private static BigInteger toBigInteger(long j) {
        return new BigInteger(String.valueOf(j));
    }

    private static BigInteger toBigInteger(Duration duration) {
        return toBigInteger(duration.getSeconds()).multiply(NANOS_PER_SECOND_BIG_INTEGER).add(toBigInteger(duration.getNanos()));
    }

    @Deprecated
    public static long toMicros(Duration duration) {
        return Durations.toMicros(duration);
    }

    @Deprecated
    public static long toMicros(Timestamp timestamp) {
        return Timestamps.toMicros(timestamp);
    }

    @Deprecated
    public static long toMillis(Duration duration) {
        return Durations.toMillis(duration);
    }

    @Deprecated
    public static long toMillis(Timestamp timestamp) {
        return Timestamps.toMillis(timestamp);
    }

    @Deprecated
    public static long toNanos(Duration duration) {
        return Durations.toNanos(duration);
    }

    @Deprecated
    public static long toNanos(Timestamp timestamp) {
        return Timestamps.toNanos(timestamp);
    }

    @Deprecated
    public static String toString(Duration duration) {
        return Durations.toString(duration);
    }

    @Deprecated
    public static String toString(Timestamp timestamp) {
        return Timestamps.toString(timestamp);
    }
}
