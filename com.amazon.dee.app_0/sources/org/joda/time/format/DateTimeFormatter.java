package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
/* loaded from: classes5.dex */
public class DateTimeFormatter {
    private final Chronology iChrono;
    private final int iDefaultYear;
    private final Locale iLocale;
    private final boolean iOffsetParsed;
    private final InternalParser iParser;
    private final Integer iPivotYear;
    private final InternalPrinter iPrinter;
    private final DateTimeZone iZone;

    public DateTimeFormatter(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser) {
        this(DateTimePrinterInternalPrinter.of(dateTimePrinter), DateTimeParserInternalParser.of(dateTimeParser));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimeFormatter(InternalPrinter internalPrinter, InternalParser internalParser) {
        this.iPrinter = internalPrinter;
        this.iParser = internalParser;
        this.iLocale = null;
        this.iOffsetParsed = false;
        this.iChrono = null;
        this.iZone = null;
        this.iPivotYear = null;
        this.iDefaultYear = 2000;
    }

    private DateTimeFormatter(InternalPrinter internalPrinter, InternalParser internalParser, Locale locale, boolean z, Chronology chronology, DateTimeZone dateTimeZone, Integer num, int i) {
        this.iPrinter = internalPrinter;
        this.iParser = internalParser;
        this.iLocale = locale;
        this.iOffsetParsed = z;
        this.iChrono = chronology;
        this.iZone = dateTimeZone;
        this.iPivotYear = num;
        this.iDefaultYear = i;
    }

    private void printTo(Appendable appendable, long j, Chronology chronology) throws IOException {
        InternalPrinter requirePrinter = requirePrinter();
        Chronology selectChronology = selectChronology(chronology);
        DateTimeZone zone = selectChronology.getZone();
        int offset = zone.getOffset(j);
        long j2 = offset;
        long j3 = j + j2;
        if ((j ^ j3) < 0 && (j2 ^ j) >= 0) {
            zone = DateTimeZone.UTC;
            offset = 0;
            j3 = j;
        }
        requirePrinter.printTo(appendable, j3, selectChronology.withUTC(), offset, zone, this.iLocale);
    }

    private InternalParser requireParser() {
        InternalParser internalParser = this.iParser;
        if (internalParser != null) {
            return internalParser;
        }
        throw new UnsupportedOperationException("Parsing not supported");
    }

    private InternalPrinter requirePrinter() {
        InternalPrinter internalPrinter = this.iPrinter;
        if (internalPrinter != null) {
            return internalPrinter;
        }
        throw new UnsupportedOperationException("Printing not supported");
    }

    private Chronology selectChronology(Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        Chronology chronology3 = this.iChrono;
        if (chronology3 != null) {
            chronology2 = chronology3;
        }
        DateTimeZone dateTimeZone = this.iZone;
        return dateTimeZone != null ? chronology2.withZone(dateTimeZone) : chronology2;
    }

    @Deprecated
    public Chronology getChronolgy() {
        return this.iChrono;
    }

    public Chronology getChronology() {
        return this.iChrono;
    }

    public int getDefaultYear() {
        return this.iDefaultYear;
    }

    public Locale getLocale() {
        return this.iLocale;
    }

    public DateTimeParser getParser() {
        return InternalParserDateTimeParser.of(this.iParser);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalParser getParser0() {
        return this.iParser;
    }

    public Integer getPivotYear() {
        return this.iPivotYear;
    }

    public DateTimePrinter getPrinter() {
        return InternalPrinterDateTimePrinter.of(this.iPrinter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalPrinter getPrinter0() {
        return this.iPrinter;
    }

    public DateTimeZone getZone() {
        return this.iZone;
    }

    public boolean isOffsetParsed() {
        return this.iOffsetParsed;
    }

    public boolean isParser() {
        return this.iParser != null;
    }

    public boolean isPrinter() {
        return this.iPrinter != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.joda.time.DateTime parseDateTime(java.lang.String r11) {
        /*
            r10 = this;
            org.joda.time.format.InternalParser r0 = r10.requireParser()
            r1 = 0
            org.joda.time.Chronology r1 = r10.selectChronology(r1)
            org.joda.time.format.DateTimeParserBucket r9 = new org.joda.time.format.DateTimeParserBucket
            java.util.Locale r6 = r10.iLocale
            java.lang.Integer r7 = r10.iPivotYear
            int r8 = r10.iDefaultYear
            r3 = 0
            r2 = r9
            r5 = r1
            r2.<init>(r3, r5, r6, r7, r8)
            r2 = 0
            int r0 = r0.parseInto(r9, r11, r2)
            if (r0 < 0) goto L5d
            int r2 = r11.length()
            if (r0 < r2) goto L5e
            r0 = 1
            long r2 = r9.computeMillis(r0, r11)
            boolean r11 = r10.iOffsetParsed
            if (r11 == 0) goto L41
            java.lang.Integer r11 = r9.getOffsetInteger()
            if (r11 == 0) goto L41
            java.lang.Integer r11 = r9.getOffsetInteger()
            int r11 = r11.intValue()
            org.joda.time.DateTimeZone r11 = org.joda.time.DateTimeZone.forOffsetMillis(r11)
            goto L4b
        L41:
            org.joda.time.DateTimeZone r11 = r9.getZone()
            if (r11 == 0) goto L4f
            org.joda.time.DateTimeZone r11 = r9.getZone()
        L4b:
            org.joda.time.Chronology r1 = r1.withZone(r11)
        L4f:
            org.joda.time.DateTime r11 = new org.joda.time.DateTime
            r11.<init>(r2, r1)
            org.joda.time.DateTimeZone r0 = r10.iZone
            if (r0 == 0) goto L5c
            org.joda.time.DateTime r11 = r11.withZone(r0)
        L5c:
            return r11
        L5d:
            int r0 = ~r0
        L5e:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r11 = org.joda.time.format.FormatUtils.createErrorMessage(r11, r0)
            r1.<init>(r11)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatter.parseDateTime(java.lang.String):org.joda.time.DateTime");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int parseInto(org.joda.time.ReadWritableInstant r13, java.lang.String r14, int r15) {
        /*
            r12 = this;
            org.joda.time.format.InternalParser r0 = r12.requireParser()
            if (r13 == 0) goto L70
            long r1 = r13.getMillis()
            org.joda.time.Chronology r3 = r13.getChronology()
            org.joda.time.Chronology r4 = org.joda.time.DateTimeUtils.getChronology(r3)
            org.joda.time.DateTimeField r4 = r4.year()
            int r11 = r4.get(r1)
            org.joda.time.DateTimeZone r4 = r3.getZone()
            int r4 = r4.getOffset(r1)
            long r4 = (long) r4
            long r6 = r1 + r4
            org.joda.time.Chronology r1 = r12.selectChronology(r3)
            org.joda.time.format.DateTimeParserBucket r2 = new org.joda.time.format.DateTimeParserBucket
            java.util.Locale r9 = r12.iLocale
            java.lang.Integer r10 = r12.iPivotYear
            r5 = r2
            r8 = r1
            r5.<init>(r6, r8, r9, r10, r11)
            int r15 = r0.parseInto(r2, r14, r15)
            r0 = 0
            long r3 = r2.computeMillis(r0, r14)
            r13.setMillis(r3)
            boolean r14 = r12.iOffsetParsed
            if (r14 == 0) goto L57
            java.lang.Integer r14 = r2.getOffsetInteger()
            if (r14 == 0) goto L57
            java.lang.Integer r14 = r2.getOffsetInteger()
            int r14 = r14.intValue()
            org.joda.time.DateTimeZone r14 = org.joda.time.DateTimeZone.forOffsetMillis(r14)
            goto L61
        L57:
            org.joda.time.DateTimeZone r14 = r2.getZone()
            if (r14 == 0) goto L65
            org.joda.time.DateTimeZone r14 = r2.getZone()
        L61:
            org.joda.time.Chronology r1 = r1.withZone(r14)
        L65:
            r13.setChronology(r1)
            org.joda.time.DateTimeZone r14 = r12.iZone
            if (r14 == 0) goto L6f
            r13.setZone(r14)
        L6f:
            return r15
        L70:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Instant must not be null"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatter.parseInto(org.joda.time.ReadWritableInstant, java.lang.String, int):int");
    }

    public LocalDate parseLocalDate(String str) {
        return parseLocalDateTime(str).toLocalDate();
    }

    public LocalDateTime parseLocalDateTime(String str) {
        DateTimeZone zone;
        InternalParser requireParser = requireParser();
        Chronology withUTC = selectChronology(null).withUTC();
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0L, withUTC, this.iLocale, this.iPivotYear, this.iDefaultYear);
        int parseInto = requireParser.parseInto(dateTimeParserBucket, str, 0);
        if (parseInto < 0) {
            parseInto = ~parseInto;
        } else if (parseInto >= str.length()) {
            long computeMillis = dateTimeParserBucket.computeMillis(true, str);
            if (dateTimeParserBucket.getOffsetInteger() == null) {
                if (dateTimeParserBucket.getZone() != null) {
                    zone = dateTimeParserBucket.getZone();
                }
                return new LocalDateTime(computeMillis, withUTC);
            }
            zone = DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffsetInteger().intValue());
            withUTC = withUTC.withZone(zone);
            return new LocalDateTime(computeMillis, withUTC);
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, parseInto));
    }

    public LocalTime parseLocalTime(String str) {
        return parseLocalDateTime(str).toLocalTime();
    }

    public long parseMillis(String str) {
        return new DateTimeParserBucket(0L, selectChronology(this.iChrono), this.iLocale, this.iPivotYear, this.iDefaultYear).doParseMillis(requireParser(), str);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.joda.time.MutableDateTime parseMutableDateTime(java.lang.String r11) {
        /*
            r10 = this;
            org.joda.time.format.InternalParser r0 = r10.requireParser()
            r1 = 0
            org.joda.time.Chronology r1 = r10.selectChronology(r1)
            org.joda.time.format.DateTimeParserBucket r9 = new org.joda.time.format.DateTimeParserBucket
            java.util.Locale r6 = r10.iLocale
            java.lang.Integer r7 = r10.iPivotYear
            int r8 = r10.iDefaultYear
            r3 = 0
            r2 = r9
            r5 = r1
            r2.<init>(r3, r5, r6, r7, r8)
            r2 = 0
            int r0 = r0.parseInto(r9, r11, r2)
            if (r0 < 0) goto L5c
            int r2 = r11.length()
            if (r0 < r2) goto L5d
            r0 = 1
            long r2 = r9.computeMillis(r0, r11)
            boolean r11 = r10.iOffsetParsed
            if (r11 == 0) goto L41
            java.lang.Integer r11 = r9.getOffsetInteger()
            if (r11 == 0) goto L41
            java.lang.Integer r11 = r9.getOffsetInteger()
            int r11 = r11.intValue()
            org.joda.time.DateTimeZone r11 = org.joda.time.DateTimeZone.forOffsetMillis(r11)
            goto L4b
        L41:
            org.joda.time.DateTimeZone r11 = r9.getZone()
            if (r11 == 0) goto L4f
            org.joda.time.DateTimeZone r11 = r9.getZone()
        L4b:
            org.joda.time.Chronology r1 = r1.withZone(r11)
        L4f:
            org.joda.time.MutableDateTime r11 = new org.joda.time.MutableDateTime
            r11.<init>(r2, r1)
            org.joda.time.DateTimeZone r0 = r10.iZone
            if (r0 == 0) goto L5b
            r11.setZone(r0)
        L5b:
            return r11
        L5c:
            int r0 = ~r0
        L5d:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r11 = org.joda.time.format.FormatUtils.createErrorMessage(r11, r0)
            r1.<init>(r11)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatter.parseMutableDateTime(java.lang.String):org.joda.time.MutableDateTime");
    }

    public String print(long j) {
        StringBuilder sb = new StringBuilder(requirePrinter().estimatePrintedLength());
        try {
            printTo(sb, j);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public String print(ReadableInstant readableInstant) {
        StringBuilder sb = new StringBuilder(requirePrinter().estimatePrintedLength());
        try {
            printTo(sb, readableInstant);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public String print(ReadablePartial readablePartial) {
        StringBuilder sb = new StringBuilder(requirePrinter().estimatePrintedLength());
        try {
            printTo(sb, readablePartial);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public void printTo(Writer writer, long j) throws IOException {
        printTo((Appendable) writer, j);
    }

    public void printTo(Writer writer, ReadableInstant readableInstant) throws IOException {
        printTo((Appendable) writer, readableInstant);
    }

    public void printTo(Writer writer, ReadablePartial readablePartial) throws IOException {
        printTo((Appendable) writer, readablePartial);
    }

    public void printTo(Appendable appendable, long j) throws IOException {
        printTo(appendable, j, null);
    }

    public void printTo(Appendable appendable, ReadableInstant readableInstant) throws IOException {
        printTo(appendable, DateTimeUtils.getInstantMillis(readableInstant), DateTimeUtils.getInstantChronology(readableInstant));
    }

    public void printTo(Appendable appendable, ReadablePartial readablePartial) throws IOException {
        InternalPrinter requirePrinter = requirePrinter();
        if (readablePartial != null) {
            requirePrinter.printTo(appendable, readablePartial, this.iLocale);
            return;
        }
        throw new IllegalArgumentException("The partial must not be null");
    }

    public void printTo(StringBuffer stringBuffer, long j) {
        try {
            printTo((Appendable) stringBuffer, j);
        } catch (IOException unused) {
        }
    }

    public void printTo(StringBuffer stringBuffer, ReadableInstant readableInstant) {
        try {
            printTo((Appendable) stringBuffer, readableInstant);
        } catch (IOException unused) {
        }
    }

    public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial) {
        try {
            printTo((Appendable) stringBuffer, readablePartial);
        } catch (IOException unused) {
        }
    }

    public DateTimeFormatter withChronology(Chronology chronology) {
        return this.iChrono == chronology ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, chronology, this.iZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withDefaultYear(int i) {
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear, i);
    }

    public DateTimeFormatter withLocale(Locale locale) {
        return (locale == getLocale() || (locale != null && locale.equals(getLocale()))) ? this : new DateTimeFormatter(this.iPrinter, this.iParser, locale, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withOffsetParsed() {
        return this.iOffsetParsed ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, true, this.iChrono, null, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withPivotYear(int i) {
        return withPivotYear(Integer.valueOf(i));
    }

    public DateTimeFormatter withPivotYear(Integer num) {
        Integer num2 = this.iPivotYear;
        return (num2 == num || (num2 != null && num2.equals(num))) ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, this.iChrono, this.iZone, num, this.iDefaultYear);
    }

    public DateTimeFormatter withZone(DateTimeZone dateTimeZone) {
        return this.iZone == dateTimeZone ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, false, this.iChrono, dateTimeZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withZoneUTC() {
        return withZone(DateTimeZone.UTC);
    }
}
