package org.joda.time;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;
/* loaded from: classes5.dex */
public final class Seconds extends BaseSingleFieldPeriod {
    private static final long serialVersionUID = 87525275727380862L;
    public static final Seconds ZERO = new Seconds(0);
    public static final Seconds ONE = new Seconds(1);
    public static final Seconds TWO = new Seconds(2);
    public static final Seconds THREE = new Seconds(3);
    public static final Seconds MAX_VALUE = new Seconds(Integer.MAX_VALUE);
    public static final Seconds MIN_VALUE = new Seconds(Integer.MIN_VALUE);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.seconds());

    private Seconds(int i) {
        super(i);
    }

    public static Seconds parseSeconds(String str) {
        return str == null ? ZERO : seconds(PARSER.parsePeriod(str).getSeconds());
    }

    private Object readResolve() {
        return seconds(getValue());
    }

    public static Seconds seconds(int i) {
        return i != Integer.MIN_VALUE ? i != Integer.MAX_VALUE ? i != 0 ? i != 1 ? i != 2 ? i != 3 ? new Seconds(i) : THREE : TWO : ONE : ZERO : MAX_VALUE : MIN_VALUE;
    }

    public static Seconds secondsBetween(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        return seconds(BaseSingleFieldPeriod.between(readableInstant, readableInstant2, DurationFieldType.seconds()));
    }

    public static Seconds secondsBetween(ReadablePartial readablePartial, ReadablePartial readablePartial2) {
        return seconds((!(readablePartial instanceof LocalTime) || !(readablePartial2 instanceof LocalTime)) ? BaseSingleFieldPeriod.between(readablePartial, readablePartial2, ZERO) : DateTimeUtils.getChronology(readablePartial.getChronology()).seconds().getDifference(((LocalTime) readablePartial2).getLocalMillis(), ((LocalTime) readablePartial).getLocalMillis()));
    }

    public static Seconds secondsIn(ReadableInterval readableInterval) {
        return readableInterval == null ? ZERO : seconds(BaseSingleFieldPeriod.between(readableInterval.getStart(), readableInterval.getEnd(), DurationFieldType.seconds()));
    }

    public static Seconds standardSecondsIn(ReadablePeriod readablePeriod) {
        return seconds(BaseSingleFieldPeriod.standardPeriodIn(readablePeriod, 1000L));
    }

    public Seconds dividedBy(int i) {
        return i == 1 ? this : seconds(getValue() / i);
    }

    @Override // org.joda.time.base.BaseSingleFieldPeriod
    public DurationFieldType getFieldType() {
        return DurationFieldType.seconds();
    }

    @Override // org.joda.time.base.BaseSingleFieldPeriod, org.joda.time.ReadablePeriod
    public PeriodType getPeriodType() {
        return PeriodType.seconds();
    }

    public int getSeconds() {
        return getValue();
    }

    public boolean isGreaterThan(Seconds seconds) {
        return seconds == null ? getValue() > 0 : getValue() > seconds.getValue();
    }

    public boolean isLessThan(Seconds seconds) {
        return seconds == null ? getValue() < 0 : getValue() < seconds.getValue();
    }

    public Seconds minus(int i) {
        return plus(FieldUtils.safeNegate(i));
    }

    public Seconds minus(Seconds seconds) {
        return seconds == null ? this : minus(seconds.getValue());
    }

    public Seconds multipliedBy(int i) {
        return seconds(FieldUtils.safeMultiply(getValue(), i));
    }

    public Seconds negated() {
        return seconds(FieldUtils.safeNegate(getValue()));
    }

    public Seconds plus(int i) {
        return i == 0 ? this : seconds(FieldUtils.safeAdd(getValue(), i));
    }

    public Seconds plus(Seconds seconds) {
        return seconds == null ? this : plus(seconds.getValue());
    }

    public Days toStandardDays() {
        return Days.days(getValue() / DateTimeConstants.SECONDS_PER_DAY);
    }

    public Duration toStandardDuration() {
        return new Duration(getValue() * 1000);
    }

    public Hours toStandardHours() {
        return Hours.hours(getValue() / 3600);
    }

    public Minutes toStandardMinutes() {
        return Minutes.minutes(getValue() / 60);
    }

    public Weeks toStandardWeeks() {
        return Weeks.weeks(getValue() / DateTimeConstants.SECONDS_PER_WEEK);
    }

    @Override // org.joda.time.ReadablePeriod
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PT");
        outline107.append(String.valueOf(getValue()));
        outline107.append(ExifInterface.LATITUDE_SOUTH);
        return outline107.toString();
    }
}
