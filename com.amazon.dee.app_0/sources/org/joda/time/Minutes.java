package org.joda.time;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;
/* loaded from: classes5.dex */
public final class Minutes extends BaseSingleFieldPeriod {
    private static final long serialVersionUID = 87525275727380863L;
    public static final Minutes ZERO = new Minutes(0);
    public static final Minutes ONE = new Minutes(1);
    public static final Minutes TWO = new Minutes(2);
    public static final Minutes THREE = new Minutes(3);
    public static final Minutes MAX_VALUE = new Minutes(Integer.MAX_VALUE);
    public static final Minutes MIN_VALUE = new Minutes(Integer.MIN_VALUE);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.minutes());

    private Minutes(int i) {
        super(i);
    }

    public static Minutes minutes(int i) {
        return i != Integer.MIN_VALUE ? i != Integer.MAX_VALUE ? i != 0 ? i != 1 ? i != 2 ? i != 3 ? new Minutes(i) : THREE : TWO : ONE : ZERO : MAX_VALUE : MIN_VALUE;
    }

    public static Minutes minutesBetween(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        return minutes(BaseSingleFieldPeriod.between(readableInstant, readableInstant2, DurationFieldType.minutes()));
    }

    public static Minutes minutesBetween(ReadablePartial readablePartial, ReadablePartial readablePartial2) {
        return minutes((!(readablePartial instanceof LocalTime) || !(readablePartial2 instanceof LocalTime)) ? BaseSingleFieldPeriod.between(readablePartial, readablePartial2, ZERO) : DateTimeUtils.getChronology(readablePartial.getChronology()).minutes().getDifference(((LocalTime) readablePartial2).getLocalMillis(), ((LocalTime) readablePartial).getLocalMillis()));
    }

    public static Minutes minutesIn(ReadableInterval readableInterval) {
        return readableInterval == null ? ZERO : minutes(BaseSingleFieldPeriod.between(readableInterval.getStart(), readableInterval.getEnd(), DurationFieldType.minutes()));
    }

    public static Minutes parseMinutes(String str) {
        return str == null ? ZERO : minutes(PARSER.parsePeriod(str).getMinutes());
    }

    private Object readResolve() {
        return minutes(getValue());
    }

    public static Minutes standardMinutesIn(ReadablePeriod readablePeriod) {
        return minutes(BaseSingleFieldPeriod.standardPeriodIn(readablePeriod, 60000L));
    }

    public Minutes dividedBy(int i) {
        return i == 1 ? this : minutes(getValue() / i);
    }

    @Override // org.joda.time.base.BaseSingleFieldPeriod
    public DurationFieldType getFieldType() {
        return DurationFieldType.minutes();
    }

    public int getMinutes() {
        return getValue();
    }

    @Override // org.joda.time.base.BaseSingleFieldPeriod, org.joda.time.ReadablePeriod
    public PeriodType getPeriodType() {
        return PeriodType.minutes();
    }

    public boolean isGreaterThan(Minutes minutes) {
        return minutes == null ? getValue() > 0 : getValue() > minutes.getValue();
    }

    public boolean isLessThan(Minutes minutes) {
        return minutes == null ? getValue() < 0 : getValue() < minutes.getValue();
    }

    public Minutes minus(int i) {
        return plus(FieldUtils.safeNegate(i));
    }

    public Minutes minus(Minutes minutes) {
        return minutes == null ? this : minus(minutes.getValue());
    }

    public Minutes multipliedBy(int i) {
        return minutes(FieldUtils.safeMultiply(getValue(), i));
    }

    public Minutes negated() {
        return minutes(FieldUtils.safeNegate(getValue()));
    }

    public Minutes plus(int i) {
        return i == 0 ? this : minutes(FieldUtils.safeAdd(getValue(), i));
    }

    public Minutes plus(Minutes minutes) {
        return minutes == null ? this : plus(minutes.getValue());
    }

    public Days toStandardDays() {
        return Days.days(getValue() / DateTimeConstants.MINUTES_PER_DAY);
    }

    public Duration toStandardDuration() {
        return new Duration(getValue() * 60000);
    }

    public Hours toStandardHours() {
        return Hours.hours(getValue() / 60);
    }

    public Seconds toStandardSeconds() {
        return Seconds.seconds(FieldUtils.safeMultiply(getValue(), 60));
    }

    public Weeks toStandardWeeks() {
        return Weeks.weeks(getValue() / DateTimeConstants.MINUTES_PER_WEEK);
    }

    @Override // org.joda.time.ReadablePeriod
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PT");
        outline107.append(String.valueOf(getValue()));
        outline107.append("M");
        return outline107.toString();
    }
}
