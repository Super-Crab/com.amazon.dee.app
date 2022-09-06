package org.threeten.bp.format;

import com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfigImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.SimpleDateTimeTextProvider;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;
import org.threeten.bp.temporal.WeekFields;
import org.threeten.bp.zone.ZoneRulesProvider;
/* loaded from: classes5.dex */
public final class DateTimeFormatterBuilder {
    static final Comparator<String> LENGTH_SORT;
    private DateTimeFormatterBuilder active;
    private final boolean optional;
    private char padNextChar;
    private int padNextWidth;
    private final DateTimeFormatterBuilder parent;
    private final List<DateTimePrinterParser> printerParsers;
    private int valueParserIndex;
    private static final TemporalQuery<ZoneId> QUERY_REGION_ONLY = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        /* renamed from: queryFrom  reason: collision with other method in class */
        public ZoneId mo13072queryFrom(TemporalAccessor temporalAccessor) {
            ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zoneId());
            if (zoneId == null || (zoneId instanceof ZoneOffset)) {
                return null;
            }
            return zoneId;
        }
    };
    private static final Map<Character, TemporalField> FIELD_MAP = new HashMap();

    /* renamed from: org.threeten.bp.format.DateTimeFormatterBuilder$4  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$format$SignStyle = new int[SignStyle.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$format$SignStyle[SignStyle.EXCEEDS_PAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$format$SignStyle[SignStyle.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$format$SignStyle[SignStyle.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$format$SignStyle[SignStyle.NOT_NEGATIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class CharLiteralPrinterParser implements DateTimePrinterParser {
        private final char literal;

        CharLiteralPrinterParser(char c) {
            this.literal = c;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i == charSequence.length()) {
                return ~i;
            }
            return !dateTimeParseContext.charEquals(this.literal, charSequence.charAt(i)) ? ~i : i + 1;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.literal);
            return true;
        }

        public String toString() {
            if (this.literal == '\'') {
                return "''";
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("'");
            outline107.append(this.literal);
            outline107.append("'");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class ChronoPrinterParser implements DateTimePrinterParser {
        private final TextStyle textStyle;

        ChronoPrinterParser(TextStyle textStyle) {
            this.textStyle = textStyle;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i >= 0 && i <= charSequence.length()) {
                Chronology chronology = null;
                int i2 = -1;
                for (Chronology chronology2 : Chronology.getAvailableChronologies()) {
                    String id = chronology2.getId();
                    int length = id.length();
                    if (length > i2 && dateTimeParseContext.subSequenceEquals(charSequence, i, id, 0, length)) {
                        chronology = chronology2;
                        i2 = length;
                    }
                }
                if (chronology == null) {
                    return ~i;
                }
                dateTimeParseContext.setParsed(chronology);
                return i + i2;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Chronology chronology = (Chronology) dateTimePrintContext.getValue(TemporalQueries.chronology());
            if (chronology == null) {
                return false;
            }
            if (this.textStyle == null) {
                sb.append(chronology.getId());
                return true;
            }
            try {
                sb.append(ResourceBundle.getBundle("org.threeten.bp.format.ChronologyText", dateTimePrintContext.getLocale(), DateTimeFormatterBuilder.class.getClassLoader()).getString(chronology.getId()));
                return true;
            } catch (MissingResourceException unused) {
                sb.append(chronology.getId());
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class CompositePrinterParser implements DateTimePrinterParser {
        private final boolean optional;
        private final DateTimePrinterParser[] printerParsers;

        CompositePrinterParser(List<DateTimePrinterParser> list, boolean z) {
            this((DateTimePrinterParser[]) list.toArray(new DateTimePrinterParser[list.size()]), z);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (this.optional) {
                dateTimeParseContext.startOptional();
                int i2 = i;
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    i2 = dateTimePrinterParser.parse(dateTimeParseContext, charSequence, i2);
                    if (i2 < 0) {
                        dateTimeParseContext.endOptional(false);
                        return i;
                    }
                }
                dateTimeParseContext.endOptional(true);
                return i2;
            }
            for (DateTimePrinterParser dateTimePrinterParser2 : this.printerParsers) {
                i = dateTimePrinterParser2.parse(dateTimeParseContext, charSequence, i);
                if (i < 0) {
                    break;
                }
            }
            return i;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (this.optional) {
                dateTimePrintContext.startOptional();
            }
            try {
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    if (!dateTimePrinterParser.print(dateTimePrintContext, sb)) {
                        sb.setLength(length);
                        return true;
                    }
                }
                if (this.optional) {
                    dateTimePrintContext.endOptional();
                }
                return true;
            } finally {
                if (this.optional) {
                    dateTimePrintContext.endOptional();
                }
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.printerParsers != null) {
                sb.append(this.optional ? "[" : "(");
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    sb.append(dateTimePrinterParser);
                }
                sb.append(this.optional ? "]" : ")");
            }
            return sb.toString();
        }

        public CompositePrinterParser withOptional(boolean z) {
            return z == this.optional ? this : new CompositePrinterParser(this.printerParsers, z);
        }

        CompositePrinterParser(DateTimePrinterParser[] dateTimePrinterParserArr, boolean z) {
            this.printerParsers = dateTimePrinterParserArr;
            this.optional = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public interface DateTimePrinterParser {
        int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i);

        boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb);
    }

    /* loaded from: classes5.dex */
    static class DefaultingParser implements DateTimePrinterParser {
        private final TemporalField field;
        private final long value;

        DefaultingParser(TemporalField temporalField, long j) {
            this.field = temporalField;
            this.value = j;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (dateTimeParseContext.getParsed(this.field) == null) {
                dateTimeParseContext.setParsedField(this.field, this.value, i, i);
            }
            return i;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class FractionPrinterParser implements DateTimePrinterParser {
        private final boolean decimalPoint;
        private final TemporalField field;
        private final int maxWidth;
        private final int minWidth;

        FractionPrinterParser(TemporalField temporalField, int i, int i2, boolean z) {
            Jdk8Methods.requireNonNull(temporalField, "field");
            if (temporalField.range().isFixed()) {
                if (i < 0 || i > 9) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Minimum width must be from 0 to 9 inclusive but was ", i));
                }
                if (i2 < 1 || i2 > 9) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Maximum width must be from 1 to 9 inclusive but was ", i2));
                }
                if (i2 >= i) {
                    this.field = temporalField;
                    this.minWidth = i;
                    this.maxWidth = i2;
                    this.decimalPoint = z;
                    return;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline53("Maximum width must exceed or equal the minimum width but ", i2, " < ", i));
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline82("Field must have a fixed set of values: ", temporalField));
        }

        private long convertFromFraction(BigDecimal bigDecimal) {
            ValueRange range = this.field.range();
            BigDecimal valueOf = BigDecimal.valueOf(range.getMinimum());
            return bigDecimal.multiply(BigDecimal.valueOf(range.getMaximum()).subtract(valueOf).add(BigDecimal.ONE)).setScale(0, RoundingMode.FLOOR).add(valueOf).longValueExact();
        }

        private BigDecimal convertToFraction(long j) {
            ValueRange range = this.field.range();
            range.checkValidValue(j, this.field);
            BigDecimal valueOf = BigDecimal.valueOf(range.getMinimum());
            BigDecimal divide = BigDecimal.valueOf(j).subtract(valueOf).divide(BigDecimal.valueOf(range.getMaximum()).subtract(valueOf).add(BigDecimal.ONE), 9, RoundingMode.FLOOR);
            return divide.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : divide.stripTrailingZeros();
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int i3 = dateTimeParseContext.isStrict() ? this.minWidth : 0;
            int i4 = dateTimeParseContext.isStrict() ? this.maxWidth : 9;
            int length = charSequence.length();
            if (i == length) {
                return i3 > 0 ? ~i : i;
            }
            if (this.decimalPoint) {
                if (charSequence.charAt(i) != dateTimeParseContext.getSymbols().getDecimalSeparator()) {
                    return i3 > 0 ? ~i : i;
                }
                i++;
            }
            int i5 = i;
            int i6 = i3 + i5;
            if (i6 > length) {
                return ~i5;
            }
            int min = Math.min(i4 + i5, length);
            int i7 = 0;
            int i8 = i5;
            while (true) {
                if (i8 >= min) {
                    i2 = i8;
                    break;
                }
                int i9 = i8 + 1;
                int convertToDigit = dateTimeParseContext.getSymbols().convertToDigit(charSequence.charAt(i8));
                if (convertToDigit >= 0) {
                    i7 = (i7 * 10) + convertToDigit;
                    i8 = i9;
                } else if (i9 < i6) {
                    return ~i5;
                } else {
                    i2 = i9 - 1;
                }
            }
            return dateTimeParseContext.setParsedField(this.field, convertFromFraction(new BigDecimal(i7).movePointLeft(i2 - i5)), i5, i2);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            DecimalStyle symbols = dateTimePrintContext.getSymbols();
            BigDecimal convertToFraction = convertToFraction(value.longValue());
            if (convertToFraction.scale() == 0) {
                if (this.minWidth <= 0) {
                    return true;
                }
                if (this.decimalPoint) {
                    sb.append(symbols.getDecimalSeparator());
                }
                for (int i = 0; i < this.minWidth; i++) {
                    sb.append(symbols.getZeroDigit());
                }
                return true;
            }
            String convertNumberToI18N = symbols.convertNumberToI18N(convertToFraction.setScale(Math.min(Math.max(convertToFraction.scale(), this.minWidth), this.maxWidth), RoundingMode.FLOOR).toPlainString().substring(2));
            if (this.decimalPoint) {
                sb.append(symbols.getDecimalSeparator());
            }
            sb.append(convertNumberToI18N);
            return true;
        }

        public String toString() {
            String str = this.decimalPoint ? ",DecimalPoint" : "";
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Fraction(");
            outline107.append(this.field);
            outline107.append(",");
            outline107.append(this.minWidth);
            outline107.append(",");
            outline107.append(this.maxWidth);
            outline107.append(str);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class InstantPrinterParser implements DateTimePrinterParser {
        private static final long SECONDS_0000_TO_1970 = 62167219200L;
        private static final long SECONDS_PER_10000_YEARS = 315569520000L;
        private final int fractionalDigits;

        InstantPrinterParser(int i) {
            this.fractionalDigits = i;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            DateTimeParseContext copy = dateTimeParseContext.copy();
            int i3 = this.fractionalDigits;
            int i4 = 0;
            if (i3 < 0) {
                i3 = 0;
            }
            int i5 = this.fractionalDigits;
            if (i5 < 0) {
                i5 = 9;
            }
            int parse = new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral('T').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(JsonReaderKt.COLON).appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral(JsonReaderKt.COLON).appendValue(ChronoField.SECOND_OF_MINUTE, 2).appendFraction(ChronoField.NANO_OF_SECOND, i3, i5, true).appendLiteral(Matrix.MATRIX_TYPE_ZERO).toFormatter().toPrinterParser(false).parse(copy, charSequence, i);
            if (parse < 0) {
                return parse;
            }
            long longValue = copy.getParsed(ChronoField.YEAR).longValue();
            int intValue = copy.getParsed(ChronoField.MONTH_OF_YEAR).intValue();
            int intValue2 = copy.getParsed(ChronoField.DAY_OF_MONTH).intValue();
            int intValue3 = copy.getParsed(ChronoField.HOUR_OF_DAY).intValue();
            int intValue4 = copy.getParsed(ChronoField.MINUTE_OF_HOUR).intValue();
            Long parsed = copy.getParsed(ChronoField.SECOND_OF_MINUTE);
            Long parsed2 = copy.getParsed(ChronoField.NANO_OF_SECOND);
            int intValue5 = parsed != null ? parsed.intValue() : 0;
            int intValue6 = parsed2 != null ? parsed2.intValue() : 0;
            int i6 = ((int) longValue) % 10000;
            try {
                if (intValue3 == 24 && intValue4 == 0 && intValue5 == 0 && intValue6 == 0) {
                    intValue3 = 0;
                    i4 = 1;
                } else if (intValue3 == 23 && intValue4 == 59 && intValue5 == 60) {
                    dateTimeParseContext.setParsedLeapSecond();
                    i2 = 59;
                    return dateTimeParseContext.setParsedField(ChronoField.NANO_OF_SECOND, intValue6, i, dateTimeParseContext.setParsedField(ChronoField.INSTANT_SECONDS, LocalDateTime.of(i6, intValue, intValue2, intValue3, intValue4, i2, 0).plusDays(i4).toEpochSecond(ZoneOffset.UTC) + Jdk8Methods.safeMultiply(longValue / 10000, (long) SECONDS_PER_10000_YEARS), i, parse));
                }
                return dateTimeParseContext.setParsedField(ChronoField.NANO_OF_SECOND, intValue6, i, dateTimeParseContext.setParsedField(ChronoField.INSTANT_SECONDS, LocalDateTime.of(i6, intValue, intValue2, intValue3, intValue4, i2, 0).plusDays(i4).toEpochSecond(ZoneOffset.UTC) + Jdk8Methods.safeMultiply(longValue / 10000, (long) SECONDS_PER_10000_YEARS), i, parse));
            } catch (RuntimeException unused) {
                return ~i;
            }
            i2 = intValue5;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.INSTANT_SECONDS);
            Long l = 0L;
            if (dateTimePrintContext.getTemporal().isSupported(ChronoField.NANO_OF_SECOND)) {
                l = Long.valueOf(dateTimePrintContext.getTemporal().getLong(ChronoField.NANO_OF_SECOND));
            }
            int i = 0;
            if (value == null) {
                return false;
            }
            long longValue = value.longValue();
            int checkValidIntValue = ChronoField.NANO_OF_SECOND.checkValidIntValue(l.longValue());
            if (longValue >= -62167219200L) {
                long j = (longValue - SECONDS_PER_10000_YEARS) + SECONDS_0000_TO_1970;
                long floorDiv = Jdk8Methods.floorDiv(j, (long) SECONDS_PER_10000_YEARS) + 1;
                LocalDateTime ofEpochSecond = LocalDateTime.ofEpochSecond(Jdk8Methods.floorMod(j, (long) SECONDS_PER_10000_YEARS) - SECONDS_0000_TO_1970, 0, ZoneOffset.UTC);
                if (floorDiv > 0) {
                    sb.append('+');
                    sb.append(floorDiv);
                }
                sb.append(ofEpochSecond);
                if (ofEpochSecond.getSecond() == 0) {
                    sb.append(":00");
                }
            } else {
                long j2 = longValue + SECONDS_0000_TO_1970;
                long j3 = j2 / SECONDS_PER_10000_YEARS;
                long j4 = j2 % SECONDS_PER_10000_YEARS;
                LocalDateTime ofEpochSecond2 = LocalDateTime.ofEpochSecond(j4 - SECONDS_0000_TO_1970, 0, ZoneOffset.UTC);
                int length = sb.length();
                sb.append(ofEpochSecond2);
                if (ofEpochSecond2.getSecond() == 0) {
                    sb.append(":00");
                }
                if (j3 < 0) {
                    if (ofEpochSecond2.getYear() == -10000) {
                        sb.replace(length, length + 2, Long.toString(j3 - 1));
                    } else if (j4 == 0) {
                        sb.insert(length, j3);
                    } else {
                        sb.insert(length + 1, Math.abs(j3));
                    }
                }
            }
            int i2 = this.fractionalDigits;
            if (i2 == -2) {
                if (checkValidIntValue != 0) {
                    sb.append('.');
                    if (checkValidIntValue % TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES == 0) {
                        sb.append(Integer.toString((checkValidIntValue / TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES) + 1000).substring(1));
                    } else if (checkValidIntValue % 1000 == 0) {
                        sb.append(Integer.toString((checkValidIntValue / 1000) + TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES).substring(1));
                    } else {
                        sb.append(Integer.toString(checkValidIntValue + 1000000000).substring(1));
                    }
                }
            } else if (i2 > 0 || (i2 == -1 && checkValidIntValue > 0)) {
                sb.append('.');
                int i3 = 100000000;
                while (true) {
                    if ((this.fractionalDigits != -1 || checkValidIntValue <= 0) && i >= this.fractionalDigits) {
                        break;
                    }
                    int i4 = checkValidIntValue / i3;
                    sb.append((char) (i4 + 48));
                    checkValidIntValue -= i4 * i3;
                    i3 /= 10;
                    i++;
                }
            }
            sb.append(Matrix.MATRIX_TYPE_ZERO);
            return true;
        }

        public String toString() {
            return "Instant()";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class LocalizedOffsetPrinterParser implements DateTimePrinterParser {
        private final TextStyle style;

        public LocalizedOffsetPrinterParser(TextStyle textStyle) {
            this.style = textStyle;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int i3;
            char charAt;
            if (!dateTimeParseContext.subSequenceEquals(charSequence, i, "GMT", 0, 3)) {
                return ~i;
            }
            int i4 = i + 3;
            if (this.style == TextStyle.FULL) {
                return new OffsetIdPrinterParser("", "+HH:MM:ss").parse(dateTimeParseContext, charSequence, i4);
            }
            int length = charSequence.length();
            if (i4 == length) {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, 0L, i4, i4);
            }
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 != '+' && charAt2 != '-') {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, 0L, i4, i4);
            }
            int i5 = charAt2 == '-' ? -1 : 1;
            if (i4 == length) {
                return ~i4;
            }
            int i6 = i4 + 1;
            char charAt3 = charSequence.charAt(i6);
            if (charAt3 < '0' || charAt3 > '9') {
                return ~i6;
            }
            int i7 = i6 + 1;
            int i8 = charAt3 - '0';
            if (i7 != length && (charAt = charSequence.charAt(i7)) >= '0' && charAt <= '9') {
                i8 = (i8 * 10) + (charAt - '0');
                if (i8 > 23) {
                    return ~i7;
                }
                i7++;
            }
            int i9 = i7;
            if (i9 == length || charSequence.charAt(i9) != ':') {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, i5 * 3600 * i8, i9, i9);
            }
            int i10 = i9 + 1;
            int i11 = length - 2;
            if (i10 > i11) {
                return ~i10;
            }
            char charAt4 = charSequence.charAt(i10);
            if (charAt4 < '0' || charAt4 > '9') {
                return ~i10;
            }
            int i12 = i10 + 1;
            int i13 = charAt4 - '0';
            char charAt5 = charSequence.charAt(i12);
            if (charAt5 < '0' || charAt5 > '9') {
                return ~i12;
            }
            int i14 = i12 + 1;
            if ((charAt5 - '0') + (i13 * 10) > 59) {
                return ~i14;
            }
            if (i14 == length || charSequence.charAt(i14) != ':') {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, GeneratedOutlineSupport1.outline4(i2, 60, i8 * 3600, i5), i14, i14);
            }
            int i15 = i14 + 1;
            if (i15 > i11) {
                return ~i15;
            }
            char charAt6 = charSequence.charAt(i15);
            if (charAt6 < '0' || charAt6 > '9') {
                return ~i15;
            }
            int i16 = i15 + 1;
            int i17 = charAt6 - '0';
            char charAt7 = charSequence.charAt(i16);
            if (charAt7 < '0' || charAt7 > '9') {
                return ~i16;
            }
            int i18 = i16 + 1;
            return (charAt7 - '0') + (i17 * 10) > 59 ? ~i18 : dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, ((i2 * 60) + (i8 * 3600) + i3) * i5, i18, i18);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.OFFSET_SECONDS);
            if (value == null) {
                return false;
            }
            sb.append("GMT");
            if (this.style == TextStyle.FULL) {
                return new OffsetIdPrinterParser("", "+HH:MM:ss").print(dateTimePrintContext, sb);
            }
            int safeToInt = Jdk8Methods.safeToInt(value.longValue());
            if (safeToInt == 0) {
                return true;
            }
            int abs = Math.abs((safeToInt / 3600) % 100);
            int abs2 = Math.abs((safeToInt / 60) % 60);
            int abs3 = Math.abs(safeToInt % 60);
            sb.append(safeToInt < 0 ? ProcessIdUtil.DEFAULT_PROCESSID : "+");
            sb.append(abs);
            if (abs2 <= 0 && abs3 <= 0) {
                return true;
            }
            sb.append(":");
            sb.append((char) ((abs2 / 10) + 48));
            sb.append((char) ((abs2 % 10) + 48));
            if (abs3 <= 0) {
                return true;
            }
            sb.append(":");
            sb.append((char) ((abs3 / 10) + 48));
            sb.append((char) ((abs3 % 10) + 48));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class LocalizedPrinterParser implements DateTimePrinterParser {
        private final FormatStyle dateStyle;
        private final FormatStyle timeStyle;

        LocalizedPrinterParser(FormatStyle formatStyle, FormatStyle formatStyle2) {
            this.dateStyle = formatStyle;
            this.timeStyle = formatStyle2;
        }

        private DateTimeFormatter formatter(Locale locale, Chronology chronology) {
            return DateTimeFormatStyleProvider.getInstance().getFormatter(this.dateStyle, this.timeStyle, chronology, locale);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            return formatter(dateTimeParseContext.getLocale(), dateTimeParseContext.getEffectiveChronology()).toPrinterParser(false).parse(dateTimeParseContext, charSequence, i);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return formatter(dateTimePrintContext.getLocale(), Chronology.from(dateTimePrintContext.getTemporal())).toPrinterParser(false).print(dateTimePrintContext, sb);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Localized(");
            FormatStyle formatStyle = this.dateStyle;
            if (formatStyle == null) {
                formatStyle = "";
            }
            outline107.append(formatStyle);
            outline107.append(",");
            FormatStyle formatStyle2 = this.timeStyle;
            if (formatStyle2 == null) {
                formatStyle2 = "";
            }
            return GeneratedOutlineSupport1.outline88(outline107, formatStyle2, ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class NumberPrinterParser implements DateTimePrinterParser {
        static final int[] EXCEED_POINTS = {0, 10, 100, 1000, 10000, 100000, TracePublisherServiceConfigImpl.TOTAL_TRACES_CAPACITY_IN_BYTES, 10000000, 100000000, 1000000000};
        final TemporalField field;
        final int maxWidth;
        final int minWidth;
        final SignStyle signStyle;
        final int subsequentWidth;

        long getValue(DateTimePrintContext dateTimePrintContext, long j) {
            return j;
        }

        boolean isFixedWidth(DateTimeParseContext dateTimeParseContext) {
            int i = this.subsequentWidth;
            return i == -1 || (i > 0 && this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE);
        }

        /* JADX WARN: Removed duplicated region for block: B:110:0x015f  */
        /* JADX WARN: Removed duplicated region for block: B:115:0x017d  */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int parse(org.threeten.bp.format.DateTimeParseContext r20, java.lang.CharSequence r21, int r22) {
            /*
                Method dump skipped, instructions count: 391
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser.parse(org.threeten.bp.format.DateTimeParseContext, java.lang.CharSequence, int):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0076, code lost:
            if (r4 != 4) goto L20;
         */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean print(org.threeten.bp.format.DateTimePrintContext r11, java.lang.StringBuilder r12) {
            /*
                r10 = this;
                org.threeten.bp.temporal.TemporalField r0 = r10.field
                java.lang.Long r0 = r11.getValue(r0)
                r1 = 0
                if (r0 != 0) goto La
                return r1
            La:
                long r2 = r0.longValue()
                long r2 = r10.getValue(r11, r2)
                org.threeten.bp.format.DecimalStyle r11 = r11.getSymbols()
                r4 = -9223372036854775808
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r0 != 0) goto L1f
                java.lang.String r0 = "9223372036854775808"
                goto L27
            L1f:
                long r4 = java.lang.Math.abs(r2)
                java.lang.String r0 = java.lang.Long.toString(r4)
            L27:
                int r4 = r0.length()
                int r5 = r10.maxWidth
                java.lang.String r6 = " cannot be printed as the value "
                java.lang.String r7 = "Field "
                if (r4 > r5) goto Lb5
                java.lang.String r0 = r11.convertNumberToI18N(r0)
                r4 = 0
                int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                r5 = 4
                r8 = 1
                if (r4 < 0) goto L69
                org.threeten.bp.format.SignStyle r4 = r10.signStyle
                int r4 = r4.ordinal()
                if (r4 == r8) goto L61
                if (r4 == r5) goto L4a
                goto L9e
            L4a:
                int r4 = r10.minWidth
                r5 = 19
                if (r4 >= r5) goto L9e
                int[] r5 = org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser.EXCEED_POINTS
                r4 = r5[r4]
                long r4 = (long) r4
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 < 0) goto L9e
                char r2 = r11.getPositiveSign()
                r12.append(r2)
                goto L9e
            L61:
                char r2 = r11.getPositiveSign()
                r12.append(r2)
                goto L9e
            L69:
                org.threeten.bp.format.SignStyle r4 = r10.signStyle
                int r4 = r4.ordinal()
                if (r4 == 0) goto L97
                if (r4 == r8) goto L97
                r9 = 3
                if (r4 == r9) goto L79
                if (r4 == r5) goto L97
                goto L9e
            L79:
                org.threeten.bp.DateTimeException r11 = new org.threeten.bp.DateTimeException
                java.lang.StringBuilder r12 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r7)
                org.threeten.bp.temporal.TemporalField r0 = r10.field
                r12.append(r0)
                r12.append(r6)
                r12.append(r2)
                java.lang.String r0 = " cannot be negative according to the SignStyle"
                r12.append(r0)
                java.lang.String r12 = r12.toString()
                r11.<init>(r12)
                throw r11
            L97:
                char r2 = r11.getNegativeSign()
                r12.append(r2)
            L9e:
                int r2 = r10.minWidth
                int r3 = r0.length()
                int r2 = r2 - r3
                if (r1 >= r2) goto Lb1
                char r2 = r11.getZeroDigit()
                r12.append(r2)
                int r1 = r1 + 1
                goto L9e
            Lb1:
                r12.append(r0)
                return r8
            Lb5:
                org.threeten.bp.DateTimeException r11 = new org.threeten.bp.DateTimeException
                java.lang.StringBuilder r12 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r7)
                org.threeten.bp.temporal.TemporalField r0 = r10.field
                r12.append(r0)
                r12.append(r6)
                r12.append(r2)
                java.lang.String r0 = " exceeds the maximum print width of "
                r12.append(r0)
                int r0 = r10.maxWidth
                r12.append(r0)
                java.lang.String r12 = r12.toString()
                r11.<init>(r12)
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser.print(org.threeten.bp.format.DateTimePrintContext, java.lang.StringBuilder):boolean");
        }

        int setValue(DateTimeParseContext dateTimeParseContext, long j, int i, int i2) {
            return dateTimeParseContext.setParsedField(this.field, j, i, i2);
        }

        public String toString() {
            if (this.minWidth == 1 && this.maxWidth == 19 && this.signStyle == SignStyle.NORMAL) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Value(");
                outline107.append(this.field);
                outline107.append(")");
                return outline107.toString();
            } else if (this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Value(");
                outline1072.append(this.field);
                outline1072.append(",");
                return GeneratedOutlineSupport1.outline86(outline1072, this.minWidth, ")");
            } else {
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Value(");
                outline1073.append(this.field);
                outline1073.append(",");
                outline1073.append(this.minWidth);
                outline1073.append(",");
                outline1073.append(this.maxWidth);
                outline1073.append(",");
                outline1073.append(this.signStyle);
                outline1073.append(")");
                return outline1073.toString();
            }
        }

        NumberPrinterParser withFixedWidth() {
            return this.subsequentWidth == -1 ? this : new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, -1);
        }

        /* renamed from: withSubsequentWidth */
        NumberPrinterParser mo13065withSubsequentWidth(int i) {
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, this.subsequentWidth + i);
        }

        NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle) {
            this.field = temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle;
            this.subsequentWidth = 0;
        }

        private NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle, int i3) {
            this.field = temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle;
            this.subsequentWidth = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class OffsetIdPrinterParser implements DateTimePrinterParser {
        private final String noOffsetText;
        private final int type;
        static final String[] PATTERNS = {"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS"};
        static final OffsetIdPrinterParser INSTANCE_ID = new OffsetIdPrinterParser("Z", "+HH:MM:ss");

        OffsetIdPrinterParser(String str, String str2) {
            Jdk8Methods.requireNonNull(str, "noOffsetText");
            Jdk8Methods.requireNonNull(str2, "pattern");
            this.noOffsetText = str;
            this.type = checkPattern(str2);
        }

        private int checkPattern(String str) {
            int i = 0;
            while (true) {
                String[] strArr = PATTERNS;
                if (i < strArr.length) {
                    if (strArr[i].equals(str)) {
                        return i;
                    }
                    i++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid zone offset pattern: ", str));
                }
            }
        }

        private boolean parseNumber(int[] iArr, int i, CharSequence charSequence, boolean z) {
            int i2;
            int i3 = this.type;
            if ((i3 + 3) / 2 < i) {
                return false;
            }
            int i4 = iArr[0];
            if (i3 % 2 != 0 || i <= 1) {
                i2 = i4;
            } else {
                i2 = i4 + 1;
                if (i2 > charSequence.length() || charSequence.charAt(i4) != ':') {
                    return z;
                }
            }
            if (i2 + 2 > charSequence.length()) {
                return z;
            }
            int i5 = i2 + 1;
            char charAt = charSequence.charAt(i2);
            int i6 = i5 + 1;
            char charAt2 = charSequence.charAt(i5);
            if (charAt >= '0' && charAt <= '9' && charAt2 >= '0' && charAt2 <= '9') {
                int i7 = (charAt2 - '0') + ((charAt - '0') * 10);
                if (i7 >= 0 && i7 <= 59) {
                    iArr[i] = i7;
                    iArr[0] = i6;
                    return false;
                }
            }
            return z;
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x0080  */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int parse(org.threeten.bp.format.DateTimeParseContext r16, java.lang.CharSequence r17, int r18) {
            /*
                r15 = this;
                r0 = r15
                r7 = r17
                r8 = r18
                int r1 = r17.length()
                java.lang.String r2 = r0.noOffsetText
                int r9 = r2.length()
                if (r9 != 0) goto L22
                if (r8 != r1) goto L45
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                r1 = r16
                r5 = r18
                r6 = r18
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            L22:
                if (r8 != r1) goto L26
                int r1 = ~r8
                return r1
            L26:
                java.lang.String r4 = r0.noOffsetText
                r5 = 0
                r1 = r16
                r2 = r17
                r3 = r18
                r6 = r9
                boolean r1 = r1.subSequenceEquals(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L45
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                int r6 = r8 + r9
                r1 = r16
                r5 = r18
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            L45:
                char r1 = r17.charAt(r18)
                r2 = 43
                r3 = 45
                if (r1 == r2) goto L51
                if (r1 != r3) goto La2
            L51:
                r2 = 1
                if (r1 != r3) goto L56
                r1 = -1
                goto L57
            L56:
                r1 = r2
            L57:
                r3 = 4
                int[] r3 = new int[r3]
                int r4 = r8 + 1
                r5 = 0
                r3[r5] = r4
                boolean r4 = r15.parseNumber(r3, r2, r7, r2)
                r6 = 2
                r10 = 3
                if (r4 != 0) goto L7d
                int r4 = r0.type
                if (r4 < r10) goto L6d
                r4 = r2
                goto L6e
            L6d:
                r4 = r5
            L6e:
                boolean r4 = r15.parseNumber(r3, r6, r7, r4)
                if (r4 != 0) goto L7d
                boolean r4 = r15.parseNumber(r3, r10, r7, r5)
                if (r4 == 0) goto L7b
                goto L7d
            L7b:
                r4 = r5
                goto L7e
            L7d:
                r4 = r2
            L7e:
                if (r4 != 0) goto La2
                long r11 = (long) r1
                r1 = r3[r2]
                long r1 = (long) r1
                r13 = 3600(0xe10, double:1.7786E-320)
                long r1 = r1 * r13
                r4 = r3[r6]
                long r6 = (long) r4
                r13 = 60
                long r6 = r6 * r13
                long r6 = r6 + r1
                r1 = r3[r10]
                long r1 = (long) r1
                long r6 = r6 + r1
                long r6 = r6 * r11
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r9 = r3[r5]
                r1 = r16
                r3 = r6
                r5 = r18
                r6 = r9
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            La2:
                if (r9 != 0) goto Lb3
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                int r6 = r8 + r9
                r1 = r16
                r5 = r18
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            Lb3:
                int r1 = ~r8
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.OffsetIdPrinterParser.parse(org.threeten.bp.format.DateTimeParseContext, java.lang.CharSequence, int):int");
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.OFFSET_SECONDS);
            if (value == null) {
                return false;
            }
            int safeToInt = Jdk8Methods.safeToInt(value.longValue());
            if (safeToInt == 0) {
                sb.append(this.noOffsetText);
            } else {
                int abs = Math.abs((safeToInt / 3600) % 100);
                int abs2 = Math.abs((safeToInt / 60) % 60);
                int abs3 = Math.abs(safeToInt % 60);
                int length = sb.length();
                sb.append(safeToInt < 0 ? ProcessIdUtil.DEFAULT_PROCESSID : "+");
                sb.append((char) ((abs / 10) + 48));
                sb.append((char) ((abs % 10) + 48));
                int i = this.type;
                if (i >= 3 || (i >= 1 && abs2 > 0)) {
                    String str = ":";
                    sb.append(this.type % 2 == 0 ? str : "");
                    sb.append((char) ((abs2 / 10) + 48));
                    sb.append((char) ((abs2 % 10) + 48));
                    abs += abs2;
                    int i2 = this.type;
                    if (i2 >= 7 || (i2 >= 5 && abs3 > 0)) {
                        if (this.type % 2 != 0) {
                            str = "";
                        }
                        sb.append(str);
                        sb.append((char) ((abs3 / 10) + 48));
                        sb.append((char) ((abs3 % 10) + 48));
                        abs += abs3;
                    }
                }
                if (abs == 0) {
                    sb.setLength(length);
                    sb.append(this.noOffsetText);
                }
            }
            return true;
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline93(GeneratedOutlineSupport1.outline107("Offset("), PATTERNS[this.type], ",'", this.noOffsetText.replace("'", "''"), "')");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class PadPrinterParserDecorator implements DateTimePrinterParser {
        private final char padChar;
        private final int padWidth;
        private final DateTimePrinterParser printerParser;

        PadPrinterParserDecorator(DateTimePrinterParser dateTimePrinterParser, int i, char c) {
            this.printerParser = dateTimePrinterParser;
            this.padWidth = i;
            this.padChar = c;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            boolean isStrict = dateTimeParseContext.isStrict();
            boolean isCaseSensitive = dateTimeParseContext.isCaseSensitive();
            if (i <= charSequence.length()) {
                if (i == charSequence.length()) {
                    return ~i;
                }
                int i2 = this.padWidth + i;
                if (i2 > charSequence.length()) {
                    if (isStrict) {
                        return ~i;
                    }
                    i2 = charSequence.length();
                }
                int i3 = i;
                while (i3 < i2) {
                    if (!isCaseSensitive) {
                        if (!dateTimeParseContext.charEquals(charSequence.charAt(i3), this.padChar)) {
                            break;
                        }
                        i3++;
                    } else if (charSequence.charAt(i3) != this.padChar) {
                        break;
                    } else {
                        i3++;
                    }
                }
                int parse = this.printerParser.parse(dateTimeParseContext, charSequence.subSequence(0, i2), i3);
                return (parse == i2 || !isStrict) ? parse : ~(i + i3);
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (!this.printerParser.print(dateTimePrintContext, sb)) {
                return false;
            }
            int length2 = sb.length() - length;
            if (length2 <= this.padWidth) {
                for (int i = 0; i < this.padWidth - length2; i++) {
                    sb.insert(length, this.padChar);
                }
                return true;
            }
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Cannot print as output of ", length2, " characters exceeds pad width of ");
            outline109.append(this.padWidth);
            throw new DateTimeException(outline109.toString());
        }

        public String toString() {
            String sb;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Pad(");
            outline107.append(this.printerParser);
            outline107.append(",");
            outline107.append(this.padWidth);
            if (this.padChar == ' ') {
                sb = ")";
            } else {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(",'");
                outline1072.append(this.padChar);
                outline1072.append("')");
                sb = outline1072.toString();
            }
            outline107.append(sb);
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class ReducedPrinterParser extends NumberPrinterParser {
        static final LocalDate BASE_DATE = LocalDate.of(2000, 1, 1);
        private final ChronoLocalDate baseDate;
        private final int baseValue;

        ReducedPrinterParser(TemporalField temporalField, int i, int i2, int i3, ChronoLocalDate chronoLocalDate) {
            super(temporalField, i, i2, SignStyle.NOT_NEGATIVE);
            if (i < 1 || i > 10) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("The width must be from 1 to 10 inclusive but was ", i));
            }
            if (i2 < 1 || i2 > 10) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("The maxWidth must be from 1 to 10 inclusive but was ", i2));
            }
            if (i2 >= i) {
                if (chronoLocalDate == null) {
                    long j = i3;
                    if (temporalField.range().isValidValue(j)) {
                        if (j + NumberPrinterParser.EXCEED_POINTS[i] > 2147483647L) {
                            throw new DateTimeException("Unable to add printer-parser as the range exceeds the capacity of an int");
                        }
                    } else {
                        throw new IllegalArgumentException("The base value must be within the range of the field");
                    }
                }
                this.baseValue = i3;
                this.baseDate = chronoLocalDate;
                return;
            }
            throw new IllegalArgumentException("The maxWidth must be greater than the width");
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        long getValue(DateTimePrintContext dateTimePrintContext, long j) {
            long abs = Math.abs(j);
            int i = this.baseValue;
            if (this.baseDate != null) {
                i = Chronology.from(dateTimePrintContext.getTemporal()).mo13045date(this.baseDate).get(this.field);
            }
            if (j >= i) {
                int[] iArr = NumberPrinterParser.EXCEED_POINTS;
                int i2 = this.minWidth;
                if (j < i + iArr[i2]) {
                    return abs % iArr[i2];
                }
            }
            return abs % NumberPrinterParser.EXCEED_POINTS[this.maxWidth];
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        boolean isFixedWidth(DateTimeParseContext dateTimeParseContext) {
            if (!dateTimeParseContext.isStrict()) {
                return false;
            }
            return super.isFixedWidth(dateTimeParseContext);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        public int setValue(DateTimeParseContext dateTimeParseContext, long j, int i, int i2) {
            int i3 = this.baseValue;
            if (this.baseDate != null) {
                i3 = dateTimeParseContext.getEffectiveChronology().mo13045date(this.baseDate).get(this.field);
                dateTimeParseContext.addChronologyChangedParser(this, j, i, i2);
            }
            int i4 = i2 - i;
            int i5 = this.minWidth;
            if (i4 == i5 && j >= 0) {
                long j2 = NumberPrinterParser.EXCEED_POINTS[i5];
                long j3 = i3;
                long j4 = j3 - (j3 % j2);
                j = i3 > 0 ? j4 + j : j4 - j;
                if (j < j3) {
                    j += j2;
                }
            }
            return dateTimeParseContext.setParsedField(this.field, j, i, i2);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReducedValue(");
            outline107.append(this.field);
            outline107.append(",");
            outline107.append(this.minWidth);
            outline107.append(",");
            outline107.append(this.maxWidth);
            outline107.append(",");
            Object obj = this.baseDate;
            if (obj == null) {
                obj = Integer.valueOf(this.baseValue);
            }
            outline107.append(obj);
            outline107.append(")");
            return outline107.toString();
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        NumberPrinterParser withFixedWidth() {
            return this.subsequentWidth == -1 ? this : new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, -1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        /* renamed from: withSubsequentWidth  reason: collision with other method in class */
        public ReducedPrinterParser mo13065withSubsequentWidth(int i) {
            return new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, this.subsequentWidth + i);
        }

        private ReducedPrinterParser(TemporalField temporalField, int i, int i2, int i3, ChronoLocalDate chronoLocalDate, int i4) {
            super(temporalField, i, i2, SignStyle.NOT_NEGATIVE, i4);
            this.baseValue = i3;
            this.baseDate = chronoLocalDate;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public enum SettingsParser implements DateTimePrinterParser {
        SENSITIVE,
        INSENSITIVE,
        STRICT,
        LENIENT;

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int ordinal = ordinal();
            if (ordinal == 0) {
                dateTimeParseContext.setCaseSensitive(true);
            } else if (ordinal == 1) {
                dateTimeParseContext.setCaseSensitive(false);
            } else if (ordinal == 2) {
                dateTimeParseContext.setStrict(true);
            } else if (ordinal == 3) {
                dateTimeParseContext.setStrict(false);
            }
            return i;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return true;
        }

        @Override // java.lang.Enum
        public String toString() {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return "ParseCaseSensitive(false)";
                }
                if (ordinal == 2) {
                    return "ParseStrict(true)";
                }
                if (ordinal != 3) {
                    throw new IllegalStateException("Unreachable");
                }
                return "ParseStrict(false)";
            }
            return "ParseCaseSensitive(true)";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class StringLiteralPrinterParser implements DateTimePrinterParser {
        private final String literal;

        StringLiteralPrinterParser(String str) {
            this.literal = str;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i <= charSequence.length() && i >= 0) {
                String str = this.literal;
                return !dateTimeParseContext.subSequenceEquals(charSequence, i, str, 0, str.length()) ? ~i : this.literal.length() + i;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.literal);
            return true;
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline75("'", this.literal.replace("'", "''"), "'");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class TextPrinterParser implements DateTimePrinterParser {
        private final TemporalField field;
        private volatile NumberPrinterParser numberPrinterParser;
        private final DateTimeTextProvider provider;
        private final TextStyle textStyle;

        TextPrinterParser(TemporalField temporalField, TextStyle textStyle, DateTimeTextProvider dateTimeTextProvider) {
            this.field = temporalField;
            this.textStyle = textStyle;
            this.provider = dateTimeTextProvider;
        }

        private NumberPrinterParser numberPrinterParser() {
            if (this.numberPrinterParser == null) {
                this.numberPrinterParser = new NumberPrinterParser(this.field, 1, 19, SignStyle.NORMAL);
            }
            return this.numberPrinterParser;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int length = charSequence.length();
            if (i >= 0 && i <= length) {
                Iterator<Map.Entry<String, Long>> textIterator = this.provider.getTextIterator(this.field, dateTimeParseContext.isStrict() ? this.textStyle : null, dateTimeParseContext.getLocale());
                if (textIterator != null) {
                    while (textIterator.hasNext()) {
                        Map.Entry<String, Long> next = textIterator.next();
                        String key = next.getKey();
                        if (dateTimeParseContext.subSequenceEquals(key, 0, charSequence, i, key.length())) {
                            return dateTimeParseContext.setParsedField(this.field, next.getValue().longValue(), i, key.length() + i);
                        }
                    }
                    if (dateTimeParseContext.isStrict()) {
                        return ~i;
                    }
                }
                return numberPrinterParser().parse(dateTimeParseContext, charSequence, i);
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            String text = this.provider.getText(this.field, value.longValue(), this.textStyle, dateTimePrintContext.getLocale());
            if (text == null) {
                return numberPrinterParser().print(dateTimePrintContext, sb);
            }
            sb.append(text);
            return true;
        }

        public String toString() {
            if (this.textStyle == TextStyle.FULL) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Text(");
                outline107.append(this.field);
                outline107.append(")");
                return outline107.toString();
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Text(");
            outline1072.append(this.field);
            outline1072.append(",");
            outline1072.append(this.textStyle);
            outline1072.append(")");
            return outline1072.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class WeekFieldsPrinterParser implements DateTimePrinterParser {
        private final int count;
        private final char letter;

        public WeekFieldsPrinterParser(char c, int i) {
            this.letter = c;
            this.count = i;
        }

        private DateTimePrinterParser evaluate(WeekFields weekFields) {
            char c = this.letter;
            if (c != 'W') {
                if (c == 'Y') {
                    if (this.count == 2) {
                        return new ReducedPrinterParser(weekFields.weekBasedYear(), 2, 2, 0, ReducedPrinterParser.BASE_DATE);
                    }
                    TemporalField weekBasedYear = weekFields.weekBasedYear();
                    int i = this.count;
                    return new NumberPrinterParser(weekBasedYear, i, 19, i < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD, -1);
                } else if (c == 'c') {
                    return new NumberPrinterParser(weekFields.dayOfWeek(), this.count, 2, SignStyle.NOT_NEGATIVE);
                } else {
                    if (c == 'e') {
                        return new NumberPrinterParser(weekFields.dayOfWeek(), this.count, 2, SignStyle.NOT_NEGATIVE);
                    }
                    if (c == 'w') {
                        return new NumberPrinterParser(weekFields.weekOfWeekBasedYear(), this.count, 2, SignStyle.NOT_NEGATIVE);
                    }
                    return null;
                }
            }
            return new NumberPrinterParser(weekFields.weekOfMonth(), 1, 2, SignStyle.NOT_NEGATIVE);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            return evaluate(WeekFields.of(dateTimeParseContext.getLocale())).parse(dateTimeParseContext, charSequence, i);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return evaluate(WeekFields.of(dateTimePrintContext.getLocale())).print(dateTimePrintContext, sb);
        }

        public String toString() {
            StringBuilder outline105 = GeneratedOutlineSupport1.outline105(30, "Localized(");
            char c = this.letter;
            if (c == 'Y') {
                int i = this.count;
                if (i == 1) {
                    outline105.append("WeekBasedYear");
                } else if (i == 2) {
                    outline105.append("ReducedValue(WeekBasedYear,2,2,2000-01-01)");
                } else {
                    outline105.append("WeekBasedYear,");
                    GeneratedOutlineSupport1.outline175(outline105, this.count, ",", 19, ",");
                    outline105.append(this.count < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD);
                }
            } else {
                if (c == 'c' || c == 'e') {
                    outline105.append("DayOfWeek");
                } else if (c == 'w') {
                    outline105.append("WeekOfWeekBasedYear");
                } else if (c == 'W') {
                    outline105.append("WeekOfMonth");
                }
                outline105.append(",");
                outline105.append(this.count);
            }
            outline105.append(")");
            return outline105.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class ZoneIdPrinterParser implements DateTimePrinterParser {
        private static volatile Map.Entry<Integer, SubstringTree> cachedSubstringTree;
        private final String description;
        private final TemporalQuery<ZoneId> query;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public static final class SubstringTree {
            final int length;
            private final Map<CharSequence, SubstringTree> substringMap;
            private final Map<String, SubstringTree> substringMapCI;

            /* JADX INFO: Access modifiers changed from: private */
            public void add(String str) {
                int length = str.length();
                int i = this.length;
                if (length == i) {
                    this.substringMap.put(str, null);
                    this.substringMapCI.put(str.toLowerCase(Locale.ENGLISH), null);
                } else if (length <= i) {
                } else {
                    String substring = str.substring(0, i);
                    SubstringTree substringTree = this.substringMap.get(substring);
                    if (substringTree == null) {
                        substringTree = new SubstringTree(length);
                        this.substringMap.put(substring, substringTree);
                        this.substringMapCI.put(substring.toLowerCase(Locale.ENGLISH), substringTree);
                    }
                    substringTree.add(str);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public SubstringTree get(CharSequence charSequence, boolean z) {
                if (z) {
                    return this.substringMap.get(charSequence);
                }
                return this.substringMapCI.get(charSequence.toString().toLowerCase(Locale.ENGLISH));
            }

            private SubstringTree(int i) {
                this.substringMap = new HashMap();
                this.substringMapCI = new HashMap();
                this.length = i;
            }
        }

        ZoneIdPrinterParser(TemporalQuery<ZoneId> temporalQuery, String str) {
            this.query = temporalQuery;
            this.description = str;
        }

        private ZoneId convertToZone(Set<String> set, String str, boolean z) {
            if (str == null) {
                return null;
            }
            if (z) {
                if (!set.contains(str)) {
                    return null;
                }
                return ZoneId.of(str);
            }
            for (String str2 : set) {
                if (str2.equalsIgnoreCase(str)) {
                    return ZoneId.of(str2);
                }
            }
            return null;
        }

        private int parsePrefixedOffset(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i, int i2) {
            String upperCase = charSequence.subSequence(i, i2).toString().toUpperCase();
            DateTimeParseContext copy = dateTimeParseContext.copy();
            if (i2 < charSequence.length() && dateTimeParseContext.charEquals(charSequence.charAt(i2), Matrix.MATRIX_TYPE_ZERO)) {
                dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.UTC));
                return i2;
            }
            int parse = OffsetIdPrinterParser.INSTANCE_ID.parse(copy, charSequence, i2);
            if (parse < 0) {
                dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.UTC));
                return i2;
            }
            dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.ofTotalSeconds((int) copy.getParsed(ChronoField.OFFSET_SECONDS).longValue())));
            return parse;
        }

        private static SubstringTree prepareParser(Set<String> set) {
            ArrayList<String> arrayList = new ArrayList(set);
            Collections.sort(arrayList, DateTimeFormatterBuilder.LENGTH_SORT);
            SubstringTree substringTree = new SubstringTree(((String) arrayList.get(0)).length());
            for (String str : arrayList) {
                substringTree.add(str);
            }
            return substringTree;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int length = charSequence.length();
            if (i <= length) {
                if (i == length) {
                    return ~i;
                }
                char charAt = charSequence.charAt(i);
                if (charAt != '+' && charAt != '-') {
                    int i3 = i + 2;
                    if (length >= i3) {
                        char charAt2 = charSequence.charAt(i + 1);
                        if (dateTimeParseContext.charEquals(charAt, Matrix.MATRIX_TYPE_RANDOM_UT) && dateTimeParseContext.charEquals(charAt2, 'T')) {
                            int i4 = i + 3;
                            if (length >= i4 && dateTimeParseContext.charEquals(charSequence.charAt(i3), 'C')) {
                                return parsePrefixedOffset(dateTimeParseContext, charSequence, i, i4);
                            }
                            return parsePrefixedOffset(dateTimeParseContext, charSequence, i, i3);
                        } else if (dateTimeParseContext.charEquals(charAt, 'G') && length >= (i2 = i + 3) && dateTimeParseContext.charEquals(charAt2, 'M') && dateTimeParseContext.charEquals(charSequence.charAt(i3), 'T')) {
                            return parsePrefixedOffset(dateTimeParseContext, charSequence, i, i2);
                        }
                    }
                    Set<String> availableZoneIds = ZoneRulesProvider.getAvailableZoneIds();
                    int size = availableZoneIds.size();
                    Map.Entry<Integer, SubstringTree> entry = cachedSubstringTree;
                    if (entry == null || entry.getKey().intValue() != size) {
                        synchronized (this) {
                            entry = cachedSubstringTree;
                            if (entry == null || entry.getKey().intValue() != size) {
                                entry = new AbstractMap.SimpleImmutableEntry<>(Integer.valueOf(size), prepareParser(availableZoneIds));
                                cachedSubstringTree = entry;
                            }
                        }
                    }
                    SubstringTree value = entry.getValue();
                    String str = null;
                    String str2 = null;
                    while (value != null) {
                        int i5 = value.length + i;
                        if (i5 > length) {
                            break;
                        }
                        String charSequence2 = charSequence.subSequence(i, i5).toString();
                        value = value.get(charSequence2, dateTimeParseContext.isCaseSensitive());
                        str2 = str;
                        str = charSequence2;
                    }
                    ZoneId convertToZone = convertToZone(availableZoneIds, str, dateTimeParseContext.isCaseSensitive());
                    if (convertToZone == null) {
                        convertToZone = convertToZone(availableZoneIds, str2, dateTimeParseContext.isCaseSensitive());
                        if (convertToZone == null) {
                            if (!dateTimeParseContext.charEquals(charAt, Matrix.MATRIX_TYPE_ZERO)) {
                                return ~i;
                            }
                            dateTimeParseContext.setParsed(ZoneOffset.UTC);
                            return i + 1;
                        }
                        str = str2;
                    }
                    dateTimeParseContext.setParsed(convertToZone);
                    return str.length() + i;
                }
                DateTimeParseContext copy = dateTimeParseContext.copy();
                int parse = OffsetIdPrinterParser.INSTANCE_ID.parse(copy, charSequence, i);
                if (parse < 0) {
                    return parse;
                }
                dateTimeParseContext.setParsed(ZoneOffset.ofTotalSeconds((int) copy.getParsed(ChronoField.OFFSET_SECONDS).longValue()));
                return parse;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            ZoneId zoneId = (ZoneId) dateTimePrintContext.getValue(this.query);
            if (zoneId == null) {
                return false;
            }
            sb.append(zoneId.getId());
            return true;
        }

        public String toString() {
            return this.description;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class ZoneTextPrinterParser implements DateTimePrinterParser {
        private static final Comparator<String> LENGTH_COMPARATOR = new Comparator<String>() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.ZoneTextPrinterParser.1
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                int length = str2.length() - str.length();
                return length == 0 ? str.compareTo(str2) : length;
            }
        };
        private final TextStyle textStyle;

        ZoneTextPrinterParser(TextStyle textStyle) {
            this.textStyle = (TextStyle) Jdk8Methods.requireNonNull(textStyle, "textStyle");
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            TreeMap treeMap = new TreeMap(LENGTH_COMPARATOR);
            for (String str : ZoneId.getAvailableZoneIds()) {
                treeMap.put(str, str);
                TimeZone timeZone = TimeZone.getTimeZone(str);
                int i2 = this.textStyle.asNormal() == TextStyle.FULL ? 1 : 0;
                String displayName = timeZone.getDisplayName(false, i2, dateTimeParseContext.getLocale());
                if (str.startsWith("Etc/") || (!displayName.startsWith("GMT+") && !displayName.startsWith("GMT+"))) {
                    treeMap.put(displayName, str);
                }
                String displayName2 = timeZone.getDisplayName(true, i2, dateTimeParseContext.getLocale());
                if (str.startsWith("Etc/") || (!displayName2.startsWith("GMT+") && !displayName2.startsWith("GMT+"))) {
                    treeMap.put(displayName2, str);
                }
            }
            for (Map.Entry entry : treeMap.entrySet()) {
                String str2 = (String) entry.getKey();
                if (dateTimeParseContext.subSequenceEquals(charSequence, i, str2, 0, str2.length())) {
                    dateTimeParseContext.setParsed(ZoneId.of((String) entry.getValue()));
                    return str2.length() + i;
                }
            }
            return ~i;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            boolean z;
            ZoneId zoneId = (ZoneId) dateTimePrintContext.getValue(TemporalQueries.zoneId());
            int i = 0;
            if (zoneId == null) {
                return false;
            }
            if (zoneId.normalized() instanceof ZoneOffset) {
                sb.append(zoneId.getId());
                return true;
            }
            TemporalAccessor temporal = dateTimePrintContext.getTemporal();
            if (temporal.isSupported(ChronoField.INSTANT_SECONDS)) {
                z = zoneId.getRules().isDaylightSavings(Instant.ofEpochSecond(temporal.getLong(ChronoField.INSTANT_SECONDS)));
            } else {
                z = false;
            }
            TimeZone timeZone = TimeZone.getTimeZone(zoneId.getId());
            if (this.textStyle.asNormal() == TextStyle.FULL) {
                i = 1;
            }
            sb.append(timeZone.getDisplayName(z, i, dateTimePrintContext.getLocale()));
            return true;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ZoneText(");
            outline107.append(this.textStyle);
            outline107.append(")");
            return outline107.toString();
        }
    }

    static {
        FIELD_MAP.put('G', ChronoField.ERA);
        FIELD_MAP.put('y', ChronoField.YEAR_OF_ERA);
        FIELD_MAP.put('u', ChronoField.YEAR);
        FIELD_MAP.put('Q', IsoFields.QUARTER_OF_YEAR);
        FIELD_MAP.put('q', IsoFields.QUARTER_OF_YEAR);
        FIELD_MAP.put('M', ChronoField.MONTH_OF_YEAR);
        FIELD_MAP.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT), ChronoField.MONTH_OF_YEAR);
        FIELD_MAP.put('D', ChronoField.DAY_OF_YEAR);
        FIELD_MAP.put('d', ChronoField.DAY_OF_MONTH);
        FIELD_MAP.put('F', ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        FIELD_MAP.put('E', ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put('c', ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put('e', ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put('a', ChronoField.AMPM_OF_DAY);
        FIELD_MAP.put('H', ChronoField.HOUR_OF_DAY);
        FIELD_MAP.put('k', ChronoField.CLOCK_HOUR_OF_DAY);
        FIELD_MAP.put('K', ChronoField.HOUR_OF_AMPM);
        FIELD_MAP.put('h', ChronoField.CLOCK_HOUR_OF_AMPM);
        FIELD_MAP.put('m', ChronoField.MINUTE_OF_HOUR);
        FIELD_MAP.put('s', ChronoField.SECOND_OF_MINUTE);
        FIELD_MAP.put('S', ChronoField.NANO_OF_SECOND);
        FIELD_MAP.put('A', ChronoField.MILLI_OF_DAY);
        FIELD_MAP.put('n', ChronoField.NANO_OF_SECOND);
        FIELD_MAP.put('N', ChronoField.NANO_OF_DAY);
        LENGTH_SORT = new Comparator<String>() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.3
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                return str.length() == str2.length() ? str.compareTo(str2) : str.length() - str2.length();
            }
        };
    }

    public DateTimeFormatterBuilder() {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = null;
        this.optional = false;
    }

    private int appendInternal(DateTimePrinterParser dateTimePrinterParser) {
        Jdk8Methods.requireNonNull(dateTimePrinterParser, "pp");
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        int i = dateTimeFormatterBuilder.padNextWidth;
        if (i > 0) {
            PadPrinterParserDecorator padPrinterParserDecorator = new PadPrinterParserDecorator(dateTimePrinterParser, i, dateTimeFormatterBuilder.padNextChar);
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            dateTimeFormatterBuilder2.padNextWidth = 0;
            dateTimeFormatterBuilder2.padNextChar = (char) 0;
            dateTimePrinterParser = padPrinterParserDecorator;
        }
        this.active.printerParsers.add(dateTimePrinterParser);
        DateTimeFormatterBuilder dateTimeFormatterBuilder3 = this.active;
        dateTimeFormatterBuilder3.valueParserIndex = -1;
        return dateTimeFormatterBuilder3.printerParsers.size() - 1;
    }

    public static String getLocalizedDateTimePattern(FormatStyle formatStyle, FormatStyle formatStyle2, Chronology chronology, Locale locale) {
        DateFormat timeInstance;
        Jdk8Methods.requireNonNull(locale, "locale");
        Jdk8Methods.requireNonNull(chronology, "chrono");
        if (formatStyle == null && formatStyle2 == null) {
            throw new IllegalArgumentException("Either dateStyle or timeStyle must be non-null");
        }
        if (formatStyle == null) {
            timeInstance = DateFormat.getTimeInstance(formatStyle2.ordinal(), locale);
        } else if (formatStyle2 != null) {
            timeInstance = DateFormat.getDateTimeInstance(formatStyle.ordinal(), formatStyle2.ordinal(), locale);
        } else {
            timeInstance = DateFormat.getDateInstance(formatStyle.ordinal(), locale);
        }
        if (timeInstance instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) timeInstance).toPattern();
        }
        throw new IllegalArgumentException("Unable to determine pattern");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void parseField(char c, int i, TemporalField temporalField) {
        if (c != 'Q') {
            if (c == 'S') {
                appendFraction(ChronoField.NANO_OF_SECOND, i, i, false);
                return;
            } else if (c == 'a') {
                if (i == 1) {
                    appendText(temporalField, TextStyle.SHORT);
                    return;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", c));
            } else {
                if (c != 'h' && c != 'k' && c != 'm') {
                    if (c != 'q') {
                        if (c != 's') {
                            if (c == 'u' || c == 'y') {
                                if (i == 2) {
                                    appendValueReduced(temporalField, 2, 2, ReducedPrinterParser.BASE_DATE);
                                    return;
                                } else if (i < 4) {
                                    appendValue(temporalField, i, 19, SignStyle.NORMAL);
                                    return;
                                } else {
                                    appendValue(temporalField, i, 19, SignStyle.EXCEEDS_PAD);
                                    return;
                                }
                            }
                            switch (c) {
                                case 'D':
                                    if (i == 1) {
                                        appendValue(temporalField);
                                        return;
                                    } else if (i <= 3) {
                                        appendValue(temporalField, i);
                                        return;
                                    } else {
                                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", c));
                                    }
                                case 'E':
                                case 'G':
                                    if (i == 1 || i == 2 || i == 3) {
                                        appendText(temporalField, TextStyle.SHORT);
                                        return;
                                    } else if (i == 4) {
                                        appendText(temporalField, TextStyle.FULL);
                                        return;
                                    } else if (i == 5) {
                                        appendText(temporalField, TextStyle.NARROW);
                                        return;
                                    } else {
                                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", c));
                                    }
                                case 'F':
                                    if (i == 1) {
                                        appendValue(temporalField);
                                        return;
                                    }
                                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", c));
                                case 'H':
                                    break;
                                default:
                                    switch (c) {
                                        case 'K':
                                            break;
                                        case 'L':
                                            break;
                                        case 'M':
                                            break;
                                        default:
                                            switch (c) {
                                                case 'c':
                                                    if (i == 1) {
                                                        appendInternal(new WeekFieldsPrinterParser('c', i));
                                                        return;
                                                    } else if (i == 2) {
                                                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Invalid number of pattern letters: ", c));
                                                    } else {
                                                        if (i == 3) {
                                                            appendText(temporalField, TextStyle.SHORT_STANDALONE);
                                                            return;
                                                        } else if (i == 4) {
                                                            appendText(temporalField, TextStyle.FULL_STANDALONE);
                                                            return;
                                                        } else if (i == 5) {
                                                            appendText(temporalField, TextStyle.NARROW_STANDALONE);
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", c));
                                                        }
                                                    }
                                                case 'd':
                                                    break;
                                                case 'e':
                                                    if (i == 1 || i == 2) {
                                                        appendInternal(new WeekFieldsPrinterParser('e', i));
                                                        return;
                                                    } else if (i == 3) {
                                                        appendText(temporalField, TextStyle.SHORT);
                                                        return;
                                                    } else if (i == 4) {
                                                        appendText(temporalField, TextStyle.FULL);
                                                        return;
                                                    } else if (i == 5) {
                                                        appendText(temporalField, TextStyle.NARROW);
                                                        return;
                                                    } else {
                                                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", c));
                                                    }
                                                default:
                                                    if (i == 1) {
                                                        appendValue(temporalField);
                                                        return;
                                                    } else {
                                                        appendValue(temporalField, i);
                                                        return;
                                                    }
                                            }
                                    }
                            }
                        }
                    }
                    if (i == 1) {
                        appendValue(temporalField);
                        return;
                    } else if (i == 2) {
                        appendValue(temporalField, 2);
                        return;
                    } else if (i == 3) {
                        appendText(temporalField, TextStyle.SHORT_STANDALONE);
                        return;
                    } else if (i == 4) {
                        appendText(temporalField, TextStyle.FULL_STANDALONE);
                        return;
                    } else if (i == 5) {
                        appendText(temporalField, TextStyle.NARROW_STANDALONE);
                        return;
                    } else {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", c));
                    }
                }
                if (i == 1) {
                    appendValue(temporalField);
                    return;
                } else if (i == 2) {
                    appendValue(temporalField, i);
                    return;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", c));
                }
            }
        }
        if (i == 1) {
            appendValue(temporalField);
        } else if (i == 2) {
            appendValue(temporalField, 2);
        } else if (i == 3) {
            appendText(temporalField, TextStyle.SHORT);
        } else if (i == 4) {
            appendText(temporalField, TextStyle.FULL);
        } else if (i == 5) {
            appendText(temporalField, TextStyle.NARROW);
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", c));
        }
    }

    private void parsePattern(String str) {
        int i;
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
                int i3 = i2 + 1;
                while (i3 < str.length() && str.charAt(i3) == charAt) {
                    i3++;
                }
                int i4 = i3 - i2;
                if (charAt == 'p') {
                    if (i3 >= str.length() || (((charAt = str.charAt(i3)) < 'A' || charAt > 'Z') && (charAt < 'a' || charAt > 'z'))) {
                        i = i4;
                        i4 = 0;
                    } else {
                        int i5 = i3 + 1;
                        while (i5 < str.length() && str.charAt(i5) == charAt) {
                            i5++;
                        }
                        i = i5 - i3;
                        i3 = i5;
                    }
                    if (i4 != 0) {
                        padNext(i4);
                        i4 = i;
                    } else {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Pad letter 'p' must be followed by valid pad pattern: ", str));
                    }
                }
                TemporalField temporalField = FIELD_MAP.get(Character.valueOf(charAt));
                if (temporalField != null) {
                    parseField(charAt, i4, temporalField);
                } else if (charAt == 'z') {
                    if (i4 > 4) {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", charAt));
                    }
                    if (i4 == 4) {
                        appendZoneText(TextStyle.FULL);
                    } else {
                        appendZoneText(TextStyle.SHORT);
                    }
                } else if (charAt != 'V') {
                    String str2 = "+0000";
                    if (charAt == 'Z') {
                        if (i4 < 4) {
                            appendOffset("+HHMM", str2);
                        } else if (i4 == 4) {
                            appendLocalizedOffset(TextStyle.FULL);
                        } else if (i4 == 5) {
                            appendOffset("+HH:MM:ss", "Z");
                        } else {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", charAt));
                        }
                    } else if (charAt == 'O') {
                        if (i4 == 1) {
                            appendLocalizedOffset(TextStyle.SHORT);
                        } else if (i4 == 4) {
                            appendLocalizedOffset(TextStyle.FULL);
                        } else {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Pattern letter count must be 1 or 4: ", charAt));
                        }
                    } else if (charAt == 'X') {
                        if (i4 > 5) {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", charAt));
                        }
                        appendOffset(OffsetIdPrinterParser.PATTERNS[i4 + (i4 == 1 ? 0 : 1)], "Z");
                    } else if (charAt == 'x') {
                        if (i4 > 5) {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", charAt));
                        }
                        if (i4 == 1) {
                            str2 = "+00";
                        } else if (i4 % 2 != 0) {
                            str2 = "+00:00";
                        }
                        appendOffset(OffsetIdPrinterParser.PATTERNS[i4 + (i4 == 1 ? 0 : 1)], str2);
                    } else if (charAt == 'W') {
                        if (i4 <= 1) {
                            appendInternal(new WeekFieldsPrinterParser('W', i4));
                        } else {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", charAt));
                        }
                    } else if (charAt == 'w') {
                        if (i4 <= 2) {
                            appendInternal(new WeekFieldsPrinterParser('w', i4));
                        } else {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Too many pattern letters: ", charAt));
                        }
                    } else if (charAt == 'Y') {
                        appendInternal(new WeekFieldsPrinterParser('Y', i4));
                    } else {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Unknown pattern letter: ", charAt));
                    }
                } else if (i4 == 2) {
                    appendZoneId();
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Pattern letter count must be 2: ", charAt));
                }
                i2 = i3 - 1;
            } else if (charAt == '\'') {
                int i6 = i2 + 1;
                int i7 = i6;
                while (i7 < str.length()) {
                    if (str.charAt(i7) == '\'') {
                        int i8 = i7 + 1;
                        if (i8 >= str.length() || str.charAt(i8) != '\'') {
                            break;
                        }
                        i7 = i8;
                    }
                    i7++;
                }
                if (i7 < str.length()) {
                    String substring = str.substring(i6, i7);
                    if (substring.length() == 0) {
                        appendLiteral(Chars.QUOTE);
                    } else {
                        appendLiteral(substring.replace("''", "'"));
                    }
                    i2 = i7;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Pattern ends with an incomplete string literal: ", str));
                }
            } else if (charAt == '[') {
                optionalStart();
            } else if (charAt == ']') {
                if (this.active.parent != null) {
                    optionalEnd();
                } else {
                    throw new IllegalArgumentException("Pattern invalid as it contains ] without previous [");
                }
            } else if (charAt != '{' && charAt != '}' && charAt != '#') {
                appendLiteral(charAt);
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline48("Pattern includes reserved character: '", charAt, "'"));
            }
            i2++;
        }
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        appendInternal(dateTimeFormatter.toPrinterParser(false));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyId() {
        appendInternal(new ChronoPrinterParser(null));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyText(TextStyle textStyle) {
        Jdk8Methods.requireNonNull(textStyle, "textStyle");
        appendInternal(new ChronoPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendFraction(TemporalField temporalField, int i, int i2, boolean z) {
        appendInternal(new FractionPrinterParser(temporalField, i, i2, z));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant() {
        appendInternal(new InstantPrinterParser(-2));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        appendInternal(new CharLiteralPrinterParser(c));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalized(FormatStyle formatStyle, FormatStyle formatStyle2) {
        if (formatStyle == null && formatStyle2 == null) {
            throw new IllegalArgumentException("Either the date or time style must be non-null");
        }
        appendInternal(new LocalizedPrinterParser(formatStyle, formatStyle2));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalizedOffset(TextStyle textStyle) {
        Jdk8Methods.requireNonNull(textStyle, TtmlNode.TAG_STYLE);
        if (textStyle != TextStyle.FULL && textStyle != TextStyle.SHORT) {
            throw new IllegalArgumentException("Style must be either full or short");
        }
        appendInternal(new LocalizedOffsetPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendOffset(String str, String str2) {
        appendInternal(new OffsetIdPrinterParser(str2, str));
        return this;
    }

    public DateTimeFormatterBuilder appendOffsetId() {
        appendInternal(OffsetIdPrinterParser.INSTANCE_ID);
        return this;
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        appendInternal(dateTimeFormatter.toPrinterParser(true));
        return this;
    }

    public DateTimeFormatterBuilder appendPattern(String str) {
        Jdk8Methods.requireNonNull(str, "pattern");
        parsePattern(str);
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField) {
        return appendText(temporalField, TextStyle.FULL);
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        appendValue(new NumberPrinterParser(temporalField, 1, 19, SignStyle.NORMAL));
        return this;
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField temporalField, int i, int i2, int i3) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        appendValue(new ReducedPrinterParser(temporalField, i, i2, i3, null));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneId() {
        appendInternal(new ZoneIdPrinterParser(TemporalQueries.zoneId(), "ZoneId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneOrOffsetId() {
        appendInternal(new ZoneIdPrinterParser(TemporalQueries.zone(), "ZoneOrOffsetId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneRegionId() {
        appendInternal(new ZoneIdPrinterParser(QUERY_REGION_ONLY, "ZoneRegionId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle) {
        appendInternal(new ZoneTextPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder optionalEnd() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        if (dateTimeFormatterBuilder.parent != null) {
            if (dateTimeFormatterBuilder.printerParsers.size() > 0) {
                DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
                CompositePrinterParser compositePrinterParser = new CompositePrinterParser(dateTimeFormatterBuilder2.printerParsers, dateTimeFormatterBuilder2.optional);
                this.active = this.active.parent;
                appendInternal(compositePrinterParser);
            } else {
                this.active = this.active.parent;
            }
            return this;
        }
        throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
    }

    public DateTimeFormatterBuilder optionalStart() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        dateTimeFormatterBuilder.valueParserIndex = -1;
        this.active = new DateTimeFormatterBuilder(dateTimeFormatterBuilder, true);
        return this;
    }

    public DateTimeFormatterBuilder padNext(int i) {
        return padNext(i, Chars.SPACE);
    }

    public DateTimeFormatterBuilder parseCaseInsensitive() {
        appendInternal(SettingsParser.INSENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseCaseSensitive() {
        appendInternal(SettingsParser.SENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseDefaulting(TemporalField temporalField, long j) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        appendInternal(new DefaultingParser(temporalField, j));
        return this;
    }

    public DateTimeFormatterBuilder parseLenient() {
        appendInternal(SettingsParser.LENIENT);
        return this;
    }

    public DateTimeFormatterBuilder parseStrict() {
        appendInternal(SettingsParser.STRICT);
        return this;
    }

    public DateTimeFormatter toFormatter() {
        return toFormatter(Locale.getDefault());
    }

    public DateTimeFormatterBuilder appendInstant(int i) {
        if (i >= -1 && i <= 9) {
            appendInternal(new InstantPrinterParser(i));
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Invalid fractional digits: ", i));
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        Jdk8Methods.requireNonNull(str, "literal");
        if (str.length() > 0) {
            if (str.length() == 1) {
                appendInternal(new CharLiteralPrinterParser(str.charAt(0)));
            } else {
                appendInternal(new StringLiteralPrinterParser(str));
            }
        }
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField, TextStyle textStyle) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(textStyle, "textStyle");
        appendInternal(new TextPrinterParser(temporalField, textStyle, DateTimeTextProvider.getInstance()));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle, Set<ZoneId> set) {
        Jdk8Methods.requireNonNull(set, "preferredZones");
        appendInternal(new ZoneTextPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder padNext(int i, char c) {
        if (i >= 1) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
            dateTimeFormatterBuilder.padNextWidth = i;
            dateTimeFormatterBuilder.padNextChar = c;
            dateTimeFormatterBuilder.valueParserIndex = -1;
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("The pad width must be at least one but was ", i));
    }

    public DateTimeFormatter toFormatter(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        while (this.active.parent != null) {
            optionalEnd();
        }
        return new DateTimeFormatter(new CompositePrinterParser(this.printerParsers, false), locale, DecimalStyle.STANDARD, ResolverStyle.SMART, null, null, null);
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField, int i) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        if (i >= 1 && i <= 19) {
            appendValue(new NumberPrinterParser(temporalField, i, i, SignStyle.NOT_NEGATIVE));
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("The width must be from 1 to 19 inclusive but was ", i));
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField temporalField, int i, int i2, ChronoLocalDate chronoLocalDate) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(chronoLocalDate, "baseDate");
        appendValue(new ReducedPrinterParser(temporalField, i, i2, 0, chronoLocalDate));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField, Map<Long, String> map) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(map, "textLookup");
        final SimpleDateTimeTextProvider.LocaleStore localeStore = new SimpleDateTimeTextProvider.LocaleStore(Collections.singletonMap(TextStyle.FULL, new LinkedHashMap(map)));
        appendInternal(new TextPrinterParser(temporalField, TextStyle.FULL, new DateTimeTextProvider() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.2
            @Override // org.threeten.bp.format.DateTimeTextProvider
            public String getText(TemporalField temporalField2, long j, TextStyle textStyle, Locale locale) {
                return localeStore.getText(j, textStyle);
            }

            @Override // org.threeten.bp.format.DateTimeTextProvider
            public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField temporalField2, TextStyle textStyle, Locale locale) {
                return localeStore.getTextIterator(textStyle);
            }
        }));
        return this;
    }

    private DateTimeFormatterBuilder(DateTimeFormatterBuilder dateTimeFormatterBuilder, boolean z) {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = dateTimeFormatterBuilder;
        this.optional = z;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField, int i, int i2, SignStyle signStyle) {
        if (i == i2 && signStyle == SignStyle.NOT_NEGATIVE) {
            return appendValue(temporalField, i2);
        }
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(signStyle, "signStyle");
        if (i < 1 || i > 19) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("The minimum width must be from 1 to 19 inclusive but was ", i));
        }
        if (i2 < 1 || i2 > 19) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("The maximum width must be from 1 to 19 inclusive but was ", i2));
        }
        if (i2 >= i) {
            appendValue(new NumberPrinterParser(temporalField, i, i2, signStyle));
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline53("The maximum width must exceed or equal the minimum width but ", i2, " < ", i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimeFormatter toFormatter(ResolverStyle resolverStyle) {
        return toFormatter().withResolverStyle(resolverStyle);
    }

    private DateTimeFormatterBuilder appendValue(NumberPrinterParser numberPrinterParser) {
        NumberPrinterParser withFixedWidth;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        int i = dateTimeFormatterBuilder.valueParserIndex;
        if (i >= 0 && (dateTimeFormatterBuilder.printerParsers.get(i) instanceof NumberPrinterParser)) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            int i2 = dateTimeFormatterBuilder2.valueParserIndex;
            NumberPrinterParser numberPrinterParser2 = (NumberPrinterParser) dateTimeFormatterBuilder2.printerParsers.get(i2);
            int i3 = numberPrinterParser.minWidth;
            int i4 = numberPrinterParser.maxWidth;
            if (i3 == i4 && numberPrinterParser.signStyle == SignStyle.NOT_NEGATIVE) {
                withFixedWidth = numberPrinterParser2.mo13065withSubsequentWidth(i4);
                appendInternal(numberPrinterParser.withFixedWidth());
                this.active.valueParserIndex = i2;
            } else {
                withFixedWidth = numberPrinterParser2.withFixedWidth();
                this.active.valueParserIndex = appendInternal(numberPrinterParser);
            }
            this.active.printerParsers.set(i2, withFixedWidth);
        } else {
            this.active.valueParserIndex = appendInternal(numberPrinterParser);
        }
        return this;
    }
}
