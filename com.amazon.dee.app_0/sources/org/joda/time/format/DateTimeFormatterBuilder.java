package org.joda.time.format;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlinx.serialization.json.internal.JsonReaderKt;
import okio.Utf8;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadablePartial;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;
/* loaded from: classes5.dex */
public class DateTimeFormatterBuilder {
    private ArrayList<Object> iElementPairs = new ArrayList<>();
    private Object iFormatter;

    /* loaded from: classes5.dex */
    static class CharacterLiteral implements InternalPrinter, InternalParser {
        private final char iValue;

        CharacterLiteral(char c) {
            this.iValue = c;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return 1;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return 1;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            char upperCase;
            char upperCase2;
            if (i >= charSequence.length()) {
                return ~i;
            }
            char charAt = charSequence.charAt(i);
            char c = this.iValue;
            return (charAt == c || (upperCase = Character.toUpperCase(charAt)) == (upperCase2 = Character.toUpperCase(c)) || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2)) ? i + 1 : ~i;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class Composite implements InternalPrinter, InternalParser {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;
        private final int iPrintedLengthEstimate;
        private final InternalPrinter[] iPrinters;

        Composite(List<Object> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.contains(null) || arrayList.isEmpty()) {
                this.iPrinters = null;
                this.iPrintedLengthEstimate = 0;
            } else {
                int size = arrayList.size();
                this.iPrinters = new InternalPrinter[size];
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    InternalPrinter internalPrinter = (InternalPrinter) arrayList.get(i2);
                    i += internalPrinter.estimatePrintedLength();
                    this.iPrinters[i2] = internalPrinter;
                }
                this.iPrintedLengthEstimate = i;
            }
            if (arrayList2.contains(null) || arrayList2.isEmpty()) {
                this.iParsers = null;
                this.iParsedLengthEstimate = 0;
                return;
            }
            int size2 = arrayList2.size();
            this.iParsers = new InternalParser[size2];
            int i3 = 0;
            for (int i4 = 0; i4 < size2; i4++) {
                InternalParser internalParser = (InternalParser) arrayList2.get(i4);
                i3 += internalParser.estimateParsedLength();
                this.iParsers[i4] = internalParser;
            }
            this.iParsedLengthEstimate = i3;
        }

        private void addArrayToList(List<Object> list, Object[] objArr) {
            if (objArr != null) {
                for (Object obj : objArr) {
                    list.add(obj);
                }
            }
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof Composite) {
                    addArrayToList(list2, ((Composite) obj).iPrinters);
                } else {
                    list2.add(obj);
                }
                Object obj2 = list.get(i + 1);
                if (obj2 instanceof Composite) {
                    addArrayToList(list3, ((Composite) obj2).iParsers);
                } else {
                    list3.add(obj2);
                }
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iPrintedLengthEstimate;
        }

        boolean isParser() {
            return this.iParsers != null;
        }

        boolean isPrinter() {
            return this.iPrinters != null;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            InternalParser[] internalParserArr = this.iParsers;
            if (internalParserArr != null) {
                int length = internalParserArr.length;
                for (int i2 = 0; i2 < length && i >= 0; i2++) {
                    i = internalParserArr[i2].parseInto(dateTimeParserBucket, charSequence, i);
                }
                return i;
            }
            throw new UnsupportedOperationException();
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr != null) {
                Locale locale2 = locale == null ? Locale.getDefault() : locale;
                for (InternalPrinter internalPrinter : internalPrinterArr) {
                    internalPrinter.printTo(appendable, j, chronology, i, dateTimeZone, locale2);
                }
                return;
            }
            throw new UnsupportedOperationException();
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr != null) {
                if (locale == null) {
                    locale = Locale.getDefault();
                }
                for (InternalPrinter internalPrinter : internalPrinterArr) {
                    internalPrinter.printTo(appendable, readablePartial, locale);
                }
                return;
            }
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes5.dex */
    static class FixedNumber extends PaddedNumber {
        protected FixedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z, i);
        }

        @Override // org.joda.time.format.DateTimeFormatterBuilder.NumberFormatter, org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            char charAt;
            int parseInto = super.parseInto(dateTimeParserBucket, charSequence, i);
            if (parseInto >= 0 && parseInto != (i2 = this.iMaxParsedDigits + i)) {
                if (this.iSigned && ((charAt = charSequence.charAt(i)) == '-' || charAt == '+')) {
                    i2++;
                }
                return parseInto > i2 ? ~(i2 + 1) : parseInto < i2 ? ~parseInto : parseInto;
            }
            return parseInto;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class Fraction implements InternalPrinter, InternalParser {
        private final DateTimeFieldType iFieldType;
        protected int iMaxDigits;
        protected int iMinDigits;

        protected Fraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
            this.iFieldType = dateTimeFieldType;
            int i3 = i2 <= 18 ? i2 : 18;
            this.iMinDigits = i;
            this.iMaxDigits = i3;
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0079 A[LOOP:0: B:3:0x000a->B:27:0x0079, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x006c A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private long[] getFractionData(long r7, org.joda.time.DateTimeField r9) {
            /*
                r6 = this;
                org.joda.time.DurationField r9 = r9.getDurationField()
                long r0 = r9.getUnitMillis()
                int r9 = r6.iMaxDigits
            La:
                switch(r9) {
                    case 1: goto L63;
                    case 2: goto L60;
                    case 3: goto L5d;
                    case 4: goto L5a;
                    case 5: goto L56;
                    case 6: goto L52;
                    case 7: goto L4e;
                    case 8: goto L4a;
                    case 9: goto L46;
                    case 10: goto L40;
                    case 11: goto L3a;
                    case 12: goto L34;
                    case 13: goto L2e;
                    case 14: goto L28;
                    case 15: goto L22;
                    case 16: goto L1c;
                    case 17: goto L16;
                    case 18: goto L10;
                    default: goto Ld;
                }
            Ld:
                r2 = 1
                goto L65
            L10:
                r2 = 1000000000000000000(0xde0b6b3a7640000, double:7.832953389245686E-242)
                goto L65
            L16:
                r2 = 100000000000000000(0x16345785d8a0000, double:5.620395787888205E-302)
                goto L65
            L1c:
                r2 = 10000000000000000(0x2386f26fc10000, double:5.431165199810528E-308)
                goto L65
            L22:
                r2 = 1000000000000000(0x38d7ea4c68000, double:4.940656458412465E-309)
                goto L65
            L28:
                r2 = 100000000000000(0x5af3107a4000, double:4.94065645841247E-310)
                goto L65
            L2e:
                r2 = 10000000000000(0x9184e72a000, double:4.9406564584125E-311)
                goto L65
            L34:
                r2 = 1000000000000(0xe8d4a51000, double:4.94065645841E-312)
                goto L65
            L3a:
                r2 = 100000000000(0x174876e800, double:4.9406564584E-313)
                goto L65
            L40:
                r2 = 10000000000(0x2540be400, double:4.9406564584E-314)
                goto L65
            L46:
                r2 = 1000000000(0x3b9aca00, double:4.94065646E-315)
                goto L65
            L4a:
                r2 = 100000000(0x5f5e100, double:4.94065646E-316)
                goto L65
            L4e:
                r2 = 10000000(0x989680, double:4.9406565E-317)
                goto L65
            L52:
                r2 = 1000000(0xf4240, double:4.940656E-318)
                goto L65
            L56:
                r2 = 100000(0x186a0, double:4.94066E-319)
                goto L65
            L5a:
                r2 = 10000(0x2710, double:4.9407E-320)
                goto L65
            L5d:
                r2 = 1000(0x3e8, double:4.94E-321)
                goto L65
            L60:
                r2 = 100
                goto L65
            L63:
                r2 = 10
            L65:
                long r4 = r0 * r2
                long r4 = r4 / r2
                int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r4 != 0) goto L79
                r4 = 2
                long[] r4 = new long[r4]
                r5 = 0
                long r7 = r7 * r2
                long r7 = r7 / r0
                r4[r5] = r7
                r7 = 1
                long r8 = (long) r9
                r4[r7] = r8
                return r4
            L79:
                int r9 = r9 + (-1)
                goto La
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatterBuilder.Fraction.getFractionData(long, org.joda.time.DateTimeField):long[]");
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iMaxDigits;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iMaxDigits;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            DateTimeField field = this.iFieldType.getField(dateTimeParserBucket.getChronology());
            int min = Math.min(this.iMaxDigits, charSequence.length() - i);
            long unitMillis = field.getDurationField().getUnitMillis() * 10;
            long j = 0;
            int i2 = 0;
            while (i2 < min) {
                char charAt = charSequence.charAt(i + i2);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i2++;
                unitMillis /= 10;
                j += (charAt - '0') * unitMillis;
            }
            long j2 = j / 10;
            if (i2 != 0 && j2 <= 2147483647L) {
                dateTimeParserBucket.saveField(new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, field.getDurationField()), (int) j2);
                return i + i2;
            }
            return ~i;
        }

        protected void printTo(Appendable appendable, long j, Chronology chronology) throws IOException {
            DateTimeField field = this.iFieldType.getField(chronology);
            int i = this.iMinDigits;
            try {
                long remainder = field.remainder(j);
                if (remainder != 0) {
                    long[] fractionData = getFractionData(remainder, field);
                    long j2 = fractionData[0];
                    int i2 = (int) fractionData[1];
                    String num = (2147483647L & j2) == j2 ? Integer.toString((int) j2) : Long.toString(j2);
                    int length = num.length();
                    while (length < i2) {
                        appendable.append('0');
                        i--;
                        i2--;
                    }
                    if (i < i2) {
                        while (i < i2 && length > 1 && num.charAt(length - 1) == '0') {
                            i2--;
                            length--;
                        }
                        if (length < num.length()) {
                            for (int i3 = 0; i3 < length; i3++) {
                                appendable.append(num.charAt(i3));
                            }
                            return;
                        }
                    }
                    appendable.append(num);
                    return;
                }
                while (true) {
                    i--;
                    if (i < 0) {
                        return;
                    }
                    appendable.append('0');
                }
            } catch (RuntimeException unused) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, i);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            printTo(appendable, j, chronology);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            printTo(appendable, readablePartial.getChronology().set(readablePartial, 0L), readablePartial.getChronology());
        }
    }

    /* loaded from: classes5.dex */
    static class MatchingParser implements InternalParser {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;

        MatchingParser(InternalParser[] internalParserArr) {
            int estimateParsedLength;
            this.iParsers = internalParserArr;
            int length = internalParserArr.length;
            int i = 0;
            while (true) {
                length--;
                if (length < 0) {
                    this.iParsedLengthEstimate = i;
                    return;
                }
                InternalParser internalParser = internalParserArr[length];
                if (internalParser != null && (estimateParsedLength = internalParser.estimateParsedLength()) > i) {
                    i = estimateParsedLength;
                }
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            int i3;
            InternalParser[] internalParserArr = this.iParsers;
            int length = internalParserArr.length;
            Object saveState = dateTimeParserBucket.saveState();
            boolean z = false;
            int i4 = i;
            int i5 = i4;
            Object obj = null;
            int i6 = 0;
            while (true) {
                if (i6 >= length) {
                    break;
                }
                InternalParser internalParser = internalParserArr[i6];
                if (internalParser != null) {
                    int parseInto = internalParser.parseInto(dateTimeParserBucket, charSequence, i);
                    if (parseInto >= i) {
                        if (parseInto <= i4) {
                            continue;
                        } else if (parseInto >= charSequence.length() || (i3 = i6 + 1) >= length || internalParserArr[i3] == null) {
                            break;
                        } else {
                            obj = dateTimeParserBucket.saveState();
                            i4 = parseInto;
                        }
                    } else if (parseInto < 0 && (i2 = ~parseInto) > i5) {
                        i5 = i2;
                    }
                    dateTimeParserBucket.restoreState(saveState);
                    i6++;
                } else if (i4 <= i) {
                    return i;
                } else {
                    z = true;
                }
            }
            if (i4 > i || (i4 == i && z)) {
                if (obj != null) {
                    dateTimeParserBucket.restoreState(obj);
                }
                return i4;
            }
            return ~i5;
        }
    }

    /* loaded from: classes5.dex */
    static abstract class NumberFormatter implements InternalPrinter, InternalParser {
        protected final DateTimeFieldType iFieldType;
        protected final int iMaxParsedDigits;
        protected final boolean iSigned;

        NumberFormatter(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iMaxParsedDigits = i;
            this.iSigned = z;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iMaxParsedDigits;
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x0051, code lost:
            if (r6 <= '9') goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0054, code lost:
            r14 = r14 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x005a, code lost:
            return ~r2;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int parseInto(org.joda.time.format.DateTimeParserBucket r12, java.lang.CharSequence r13, int r14) {
            /*
                r11 = this;
                int r0 = r11.iMaxParsedDigits
                int r1 = r13.length()
                int r1 = r1 - r14
                int r0 = java.lang.Math.min(r0, r1)
                r1 = 0
                r2 = r14
                r14 = r1
                r3 = r14
            Lf:
                r4 = 48
                if (r14 >= r0) goto L57
                int r5 = r2 + r14
                char r6 = r13.charAt(r5)
                r7 = 57
                if (r14 != 0) goto L4f
                r8 = 45
                if (r6 == r8) goto L25
                r9 = 43
                if (r6 != r9) goto L4f
            L25:
                boolean r9 = r11.iSigned
                if (r9 == 0) goto L4f
                if (r6 != r8) goto L2d
                r3 = 1
                goto L2e
            L2d:
                r3 = r1
            L2e:
                int r6 = r14 + 1
                if (r6 >= r0) goto L57
                int r5 = r5 + 1
                char r5 = r13.charAt(r5)
                if (r5 < r4) goto L57
                if (r5 <= r7) goto L3d
                goto L57
            L3d:
                if (r3 == 0) goto L41
                r14 = r6
                goto L43
            L41:
                int r2 = r2 + 1
            L43:
                int r0 = r0 + 1
                int r4 = r13.length()
                int r4 = r4 - r2
                int r0 = java.lang.Math.min(r0, r4)
                goto Lf
            L4f:
                if (r6 < r4) goto L57
                if (r6 <= r7) goto L54
                goto L57
            L54:
                int r14 = r14 + 1
                goto Lf
            L57:
                if (r14 != 0) goto L5b
                int r12 = ~r2
                return r12
            L5b:
                r0 = 9
                if (r14 < r0) goto L6d
                int r14 = r14 + r2
                java.lang.CharSequence r13 = r13.subSequence(r2, r14)
                java.lang.String r13 = r13.toString()
                int r13 = java.lang.Integer.parseInt(r13)
                goto L93
            L6d:
                if (r3 == 0) goto L72
                int r0 = r2 + 1
                goto L73
            L72:
                r0 = r2
            L73:
                int r1 = r0 + 1
                char r0 = r13.charAt(r0)     // Catch: java.lang.StringIndexOutOfBoundsException -> L99
                int r0 = r0 - r4
                int r14 = r14 + r2
            L7b:
                if (r1 >= r14) goto L8e
                int r2 = r0 << 3
                int r0 = r0 << 1
                int r2 = r2 + r0
                int r0 = r1 + 1
                char r1 = r13.charAt(r1)
                int r1 = r1 + r2
                int r1 = r1 - r4
                r10 = r1
                r1 = r0
                r0 = r10
                goto L7b
            L8e:
                if (r3 == 0) goto L92
                int r13 = -r0
                goto L93
            L92:
                r13 = r0
            L93:
                org.joda.time.DateTimeFieldType r0 = r11.iFieldType
                r12.saveField(r0, r13)
                return r14
            L99:
                int r12 = ~r2
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatterBuilder.NumberFormatter.parseInto(org.joda.time.format.DateTimeParserBucket, java.lang.CharSequence, int):int");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class PaddedNumber extends NumberFormatter {
        protected final int iMinPrintedDigits;

        protected PaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z, int i2) {
            super(dateTimeFieldType, i, z);
            this.iMinPrintedDigits = i2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendPaddedInteger(appendable, this.iFieldType.getField(chronology).get(j), this.iMinPrintedDigits);
            } catch (RuntimeException unused) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendPaddedInteger(appendable, readablePartial.get(this.iFieldType), this.iMinPrintedDigits);
                    return;
                } catch (RuntimeException unused) {
                }
            }
            DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
        }
    }

    /* loaded from: classes5.dex */
    static class StringLiteral implements InternalPrinter, InternalParser {
        private final String iValue;

        StringLiteral(String str) {
            this.iValue = str;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iValue.length();
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iValue.length();
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            return DateTimeFormatterBuilder.csStartsWithIgnoreCase(charSequence, i, this.iValue) ? this.iValue.length() + i : ~i;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class TextField implements InternalPrinter, InternalParser {
        private static Map<Locale, Map<DateTimeFieldType, Object[]>> cParseCache = new ConcurrentHashMap();
        private final DateTimeFieldType iFieldType;
        private final boolean iShort;

        TextField(DateTimeFieldType dateTimeFieldType, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iShort = z;
        }

        private String print(long j, Chronology chronology, Locale locale) {
            DateTimeField field = this.iFieldType.getField(chronology);
            return this.iShort ? field.getAsShortText(j, locale) : field.getAsText(j, locale);
        }

        private String print(ReadablePartial readablePartial, Locale locale) {
            if (readablePartial.isSupported(this.iFieldType)) {
                DateTimeField field = this.iFieldType.getField(readablePartial.getChronology());
                return this.iShort ? field.getAsShortText(readablePartial, locale) : field.getAsText(readablePartial, locale);
            }
            return "�";
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iShort ? 6 : 20;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Map map;
            int intValue;
            Locale locale = dateTimeParserBucket.getLocale();
            Map<DateTimeFieldType, Object[]> map2 = cParseCache.get(locale);
            if (map2 == null) {
                map2 = new ConcurrentHashMap<>();
                cParseCache.put(locale, map2);
            }
            Object[] objArr = map2.get(this.iFieldType);
            if (objArr == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(32);
                MutableDateTime.Property property = new MutableDateTime(0L, DateTimeZone.UTC).property(this.iFieldType);
                int minimumValueOverall = property.getMinimumValueOverall();
                int maximumValueOverall = property.getMaximumValueOverall();
                if (maximumValueOverall - minimumValueOverall > 32) {
                    return ~i;
                }
                intValue = property.getMaximumTextLength(locale);
                while (minimumValueOverall <= maximumValueOverall) {
                    property.set(minimumValueOverall);
                    concurrentHashMap.put(property.getAsShortText(locale), Boolean.TRUE);
                    concurrentHashMap.put(property.getAsShortText(locale).toLowerCase(locale), Boolean.TRUE);
                    concurrentHashMap.put(property.getAsShortText(locale).toUpperCase(locale), Boolean.TRUE);
                    concurrentHashMap.put(property.getAsText(locale), Boolean.TRUE);
                    concurrentHashMap.put(property.getAsText(locale).toLowerCase(locale), Boolean.TRUE);
                    concurrentHashMap.put(property.getAsText(locale).toUpperCase(locale), Boolean.TRUE);
                    minimumValueOverall++;
                }
                if ("en".equals(locale.getLanguage()) && this.iFieldType == DateTimeFieldType.era()) {
                    concurrentHashMap.put("BCE", Boolean.TRUE);
                    concurrentHashMap.put("bce", Boolean.TRUE);
                    concurrentHashMap.put("CE", Boolean.TRUE);
                    concurrentHashMap.put("ce", Boolean.TRUE);
                    intValue = 3;
                }
                map2.put(this.iFieldType, new Object[]{concurrentHashMap, Integer.valueOf(intValue)});
                map = concurrentHashMap;
            } else {
                map = (Map) objArr[0];
                intValue = ((Integer) objArr[1]).intValue();
            }
            for (int min = Math.min(charSequence.length(), intValue + i); min > i; min--) {
                String charSequence2 = charSequence.subSequence(i, min).toString();
                if (map.containsKey(charSequence2)) {
                    dateTimeParserBucket.saveField(this.iFieldType, charSequence2, locale);
                    return min;
                }
            }
            return ~i;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                appendable.append(print(j, chronology, locale));
            } catch (RuntimeException unused) {
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            try {
                appendable.append(print(readablePartial, locale));
            } catch (RuntimeException unused) {
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
            }
        }
    }

    /* loaded from: classes5.dex */
    enum TimeZoneId implements InternalPrinter, InternalParser {
        INSTANCE;
        
        static final Set<String> ALL_IDS = DateTimeZone.getAvailableIDs();
        static final int MAX_LENGTH;

        static {
            int i = 0;
            for (String str : ALL_IDS) {
                i = Math.max(i, str.length());
            }
            MAX_LENGTH = i;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return MAX_LENGTH;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return MAX_LENGTH;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            String str = null;
            for (String str2 : ALL_IDS) {
                if (DateTimeFormatterBuilder.csStartsWith(charSequence, i, str2) && (str == null || str2.length() > str.length())) {
                    str = str2;
                }
            }
            if (str != null) {
                dateTimeParserBucket.setZone(DateTimeZone.forID(str));
                return str.length() + i;
            }
            return ~i;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(dateTimeZone != null ? dateTimeZone.getID() : "");
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }
    }

    /* loaded from: classes5.dex */
    static class TimeZoneName implements InternalPrinter, InternalParser {
        static final int LONG_NAME = 0;
        static final int SHORT_NAME = 1;
        private final Map<String, DateTimeZone> iParseLookup;
        private final int iType;

        TimeZoneName(int i, Map<String, DateTimeZone> map) {
            this.iType = i;
            this.iParseLookup = map;
        }

        private String print(long j, DateTimeZone dateTimeZone, Locale locale) {
            if (dateTimeZone == null) {
                return "";
            }
            int i = this.iType;
            return i != 0 ? i != 1 ? "" : dateTimeZone.getShortName(j, locale) : dateTimeZone.getName(j, locale);
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Map<String, DateTimeZone> map = this.iParseLookup;
            if (map == null) {
                map = DateTimeUtils.getDefaultTimeZoneNames();
            }
            String str = null;
            for (String str2 : map.keySet()) {
                if (DateTimeFormatterBuilder.csStartsWith(charSequence, i, str2) && (str == null || str2.length() > str.length())) {
                    str = str2;
                }
            }
            if (str != null) {
                dateTimeParserBucket.setZone(map.get(str));
                return str.length() + i;
            }
            return ~i;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(print(j - i, dateTimeZone, locale));
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class TimeZoneOffset implements InternalPrinter, InternalParser {
        private final int iMaxFields;
        private final int iMinFields;
        private final boolean iShowSeparators;
        private final String iZeroOffsetParseText;
        private final String iZeroOffsetPrintText;

        TimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
            this.iZeroOffsetPrintText = str;
            this.iZeroOffsetParseText = str2;
            this.iShowSeparators = z;
            if (i <= 0 || i2 < i) {
                throw new IllegalArgumentException();
            }
            int i3 = 4;
            if (i > 4) {
                i2 = 4;
            } else {
                i3 = i;
            }
            this.iMinFields = i3;
            this.iMaxFields = i2;
        }

        private int digitCount(CharSequence charSequence, int i, int i2) {
            int i3 = 0;
            for (int min = Math.min(charSequence.length() - i, i2); min > 0; min--) {
                char charAt = charSequence.charAt(i + i3);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i3++;
            }
            return i3;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            int i = this.iMinFields;
            int i2 = (i + 1) << 1;
            if (this.iShowSeparators) {
                i2 += i - 1;
            }
            String str = this.iZeroOffsetPrintText;
            return (str == null || str.length() <= i2) ? i2 : this.iZeroOffsetPrintText.length();
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x007f, code lost:
            if (r6 <= '9') goto L36;
         */
        @Override // org.joda.time.format.InternalParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int parseInto(org.joda.time.format.DateTimeParserBucket r12, java.lang.CharSequence r13, int r14) {
            /*
                Method dump skipped, instructions count: 294
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatterBuilder.TimeZoneOffset.parseInto(org.joda.time.format.DateTimeParserBucket, java.lang.CharSequence, int):int");
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            String str;
            if (dateTimeZone == null) {
                return;
            }
            if (i == 0 && (str = this.iZeroOffsetPrintText) != null) {
                appendable.append(str);
                return;
            }
            if (i >= 0) {
                appendable.append('+');
            } else {
                appendable.append('-');
                i = -i;
            }
            int i2 = i / 3600000;
            FormatUtils.appendPaddedInteger(appendable, i2, 2);
            if (this.iMaxFields == 1) {
                return;
            }
            int i3 = i - (i2 * 3600000);
            if (i3 == 0 && this.iMinFields <= 1) {
                return;
            }
            int i4 = i3 / 60000;
            if (this.iShowSeparators) {
                appendable.append(JsonReaderKt.COLON);
            }
            FormatUtils.appendPaddedInteger(appendable, i4, 2);
            if (this.iMaxFields == 2) {
                return;
            }
            int i5 = i3 - (i4 * 60000);
            if (i5 == 0 && this.iMinFields <= 2) {
                return;
            }
            int i6 = i5 / 1000;
            if (this.iShowSeparators) {
                appendable.append(JsonReaderKt.COLON);
            }
            FormatUtils.appendPaddedInteger(appendable, i6, 2);
            if (this.iMaxFields == 3) {
                return;
            }
            int i7 = i5 - (i6 * 1000);
            if (i7 == 0 && this.iMinFields <= 3) {
                return;
            }
            if (this.iShowSeparators) {
                appendable.append('.');
            }
            FormatUtils.appendPaddedInteger(appendable, i7, 3);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class TwoDigitYear implements InternalPrinter, InternalParser {
        private final boolean iLenientParse;
        private final int iPivot;
        private final DateTimeFieldType iType;

        TwoDigitYear(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iType = dateTimeFieldType;
            this.iPivot = i;
            this.iLenientParse = z;
        }

        private int getTwoDigitYear(long j, Chronology chronology) {
            try {
                int i = this.iType.getField(chronology).get(j);
                if (i < 0) {
                    i = -i;
                }
                return i % 100;
            } catch (RuntimeException unused) {
                return -1;
            }
        }

        private int getTwoDigitYear(ReadablePartial readablePartial) {
            if (readablePartial.isSupported(this.iType)) {
                try {
                    int i = readablePartial.get(this.iType);
                    if (i < 0) {
                        i = -i;
                    }
                    return i % 100;
                } catch (RuntimeException unused) {
                    return -1;
                }
            }
            return -1;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iLenientParse ? 4 : 2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return 2;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            int i3;
            int length = charSequence.length() - i;
            if (this.iLenientParse) {
                int i4 = i;
                int i5 = 0;
                boolean z = false;
                boolean z2 = false;
                while (i5 < length) {
                    char charAt = charSequence.charAt(i4 + i5);
                    if (i5 != 0 || (charAt != '-' && charAt != '+')) {
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i5++;
                    } else {
                        z2 = charAt == '-';
                        if (z2) {
                            i5++;
                        } else {
                            i4++;
                            length--;
                        }
                        z = true;
                    }
                }
                if (i5 == 0) {
                    return ~i4;
                }
                if (z || i5 != 2) {
                    if (i5 >= 9) {
                        i2 = i5 + i4;
                        i3 = Integer.parseInt(charSequence.subSequence(i4, i2).toString());
                    } else {
                        int i6 = z2 ? i4 + 1 : i4;
                        int i7 = i6 + 1;
                        try {
                            int charAt2 = charSequence.charAt(i6) - '0';
                            i2 = i5 + i4;
                            while (i7 < i2) {
                                int charAt3 = (charSequence.charAt(i7) + ((charAt2 << 3) + (charAt2 << 1))) - 48;
                                i7++;
                                charAt2 = charAt3;
                            }
                            i3 = z2 ? -charAt2 : charAt2;
                        } catch (StringIndexOutOfBoundsException unused) {
                            return ~i4;
                        }
                    }
                    dateTimeParserBucket.saveField(this.iType, i3);
                    return i2;
                }
                i = i4;
            } else if (Math.min(2, length) < 2) {
                return ~i;
            }
            char charAt4 = charSequence.charAt(i);
            if (charAt4 < '0' || charAt4 > '9') {
                return ~i;
            }
            int i8 = charAt4 - '0';
            char charAt5 = charSequence.charAt(i + 1);
            if (charAt5 < '0' || charAt5 > '9') {
                return ~i;
            }
            int i9 = (((i8 << 3) + (i8 << 1)) + charAt5) - 48;
            int i10 = this.iPivot;
            if (dateTimeParserBucket.getPivotYear() != null) {
                i10 = dateTimeParserBucket.getPivotYear().intValue();
            }
            int i11 = i10 - 50;
            int i12 = 100;
            int i13 = i11 >= 0 ? i11 % 100 : ((i11 + 1) % 100) + 99;
            if (i9 >= i13) {
                i12 = 0;
            }
            dateTimeParserBucket.saveField(this.iType, ((i11 + i12) - i13) + i9);
            return i + 2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(j, chronology);
            if (twoDigitYear >= 0) {
                FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
                return;
            }
            appendable.append(Utf8.REPLACEMENT_CHARACTER);
            appendable.append(Utf8.REPLACEMENT_CHARACTER);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(readablePartial);
            if (twoDigitYear >= 0) {
                FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
                return;
            }
            appendable.append(Utf8.REPLACEMENT_CHARACTER);
            appendable.append(Utf8.REPLACEMENT_CHARACTER);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class UnpaddedNumber extends NumberFormatter {
        protected UnpaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z);
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendUnpaddedInteger(appendable, this.iFieldType.getField(chronology).get(j));
            } catch (RuntimeException unused) {
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendUnpaddedInteger(appendable, readablePartial.get(this.iFieldType));
                    return;
                } catch (RuntimeException unused) {
                }
            }
            appendable.append(Utf8.REPLACEMENT_CHARACTER);
        }
    }

    private DateTimeFormatterBuilder append0(Object obj) {
        this.iFormatter = null;
        this.iElementPairs.add(obj);
        this.iElementPairs.add(obj);
        return this;
    }

    private DateTimeFormatterBuilder append0(InternalPrinter internalPrinter, InternalParser internalParser) {
        this.iFormatter = null;
        this.iElementPairs.add(internalPrinter);
        this.iElementPairs.add(internalParser);
        return this;
    }

    static void appendUnknownString(Appendable appendable, int i) throws IOException {
        while (true) {
            i--;
            if (i >= 0) {
                appendable.append(Utf8.REPLACEMENT_CHARACTER);
            } else {
                return;
            }
        }
    }

    private void checkParser(DateTimeParser dateTimeParser) {
        if (dateTimeParser != null) {
            return;
        }
        throw new IllegalArgumentException("No parser supplied");
    }

    private void checkPrinter(DateTimePrinter dateTimePrinter) {
        if (dateTimePrinter != null) {
            return;
        }
        throw new IllegalArgumentException("No printer supplied");
    }

    static boolean csStartsWith(CharSequence charSequence, int i, String str) {
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (charSequence.charAt(i + i2) != str.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    static boolean csStartsWithIgnoreCase(CharSequence charSequence, int i, String str) {
        char upperCase;
        char upperCase2;
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = charSequence.charAt(i + i2);
            char charAt2 = str.charAt(i2);
            if (charAt != charAt2 && (upperCase = Character.toUpperCase(charAt)) != (upperCase2 = Character.toUpperCase(charAt2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                return false;
            }
        }
        return true;
    }

    private Object getFormatter() {
        Object obj = this.iFormatter;
        if (obj == null) {
            if (this.iElementPairs.size() == 2) {
                Object obj2 = this.iElementPairs.get(0);
                Object obj3 = this.iElementPairs.get(1);
                if (obj2 == null) {
                    obj = obj3;
                } else if (obj2 == obj3 || obj3 == null) {
                    obj = obj2;
                }
            }
            if (obj == null) {
                obj = new Composite(this.iElementPairs);
            }
            this.iFormatter = obj;
        }
        return obj;
    }

    private boolean isFormatter(Object obj) {
        return isPrinter(obj) || isParser(obj);
    }

    private boolean isParser(Object obj) {
        if (obj instanceof InternalParser) {
            if (!(obj instanceof Composite)) {
                return true;
            }
            return ((Composite) obj).isParser();
        }
        return false;
    }

    private boolean isPrinter(Object obj) {
        if (obj instanceof InternalPrinter) {
            if (!(obj instanceof Composite)) {
                return true;
            }
            return ((Composite) obj).isPrinter();
        }
        return false;
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter != null) {
            return append0(dateTimeFormatter.getPrinter0(), dateTimeFormatter.getParser0());
        }
        throw new IllegalArgumentException("No formatter supplied");
    }

    public DateTimeFormatterBuilder append(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, DateTimeParserInternalParser.of(dateTimeParser));
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter) {
        checkPrinter(dateTimePrinter);
        return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), null);
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser) {
        checkPrinter(dateTimePrinter);
        checkParser(dateTimeParser);
        return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), DateTimeParserInternalParser.of(dateTimeParser));
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser[] dateTimeParserArr) {
        InternalPrinter of;
        InternalParser matchingParser;
        if (dateTimePrinter != null) {
            checkPrinter(dateTimePrinter);
        }
        if (dateTimeParserArr != null) {
            int length = dateTimeParserArr.length;
            int i = 0;
            if (length != 1) {
                InternalParser[] internalParserArr = new InternalParser[length];
                while (i < length - 1) {
                    InternalParser of2 = DateTimeParserInternalParser.of(dateTimeParserArr[i]);
                    internalParserArr[i] = of2;
                    if (of2 == null) {
                        throw new IllegalArgumentException("Incomplete parser array");
                    }
                    i++;
                }
                internalParserArr[i] = DateTimeParserInternalParser.of(dateTimeParserArr[i]);
                of = DateTimePrinterInternalPrinter.of(dateTimePrinter);
                matchingParser = new MatchingParser(internalParserArr);
            } else if (dateTimeParserArr[0] == null) {
                throw new IllegalArgumentException("No parser supplied");
            } else {
                of = DateTimePrinterInternalPrinter.of(dateTimePrinter);
                matchingParser = DateTimeParserInternalParser.of(dateTimeParserArr[0]);
            }
            return append0(of, matchingParser);
        }
        throw new IllegalArgumentException("No parsers supplied");
    }

    public DateTimeFormatterBuilder appendCenturyOfEra(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendClockhourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfMonth(int i) {
        return appendDecimal(DateTimeFieldType.dayOfMonth(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfWeek(int i) {
        return appendDecimal(DateTimeFieldType.dayOfWeek(), i, 1);
    }

    public DateTimeFormatterBuilder appendDayOfWeekShortText() {
        return appendShortText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendDayOfWeekText() {
        return appendText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendDayOfYear(int i) {
        return appendDecimal(DateTimeFieldType.dayOfYear(), i, 3);
    }

    public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType != null) {
            if (i2 < i) {
                i2 = i;
            }
            if (i < 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            return i <= 1 ? append0(new UnpaddedNumber(dateTimeFieldType, i2, false)) : append0(new PaddedNumber(dateTimeFieldType, i2, false, i));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendEraText() {
        return appendText(DateTimeFieldType.era());
    }

    public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType != null) {
            if (i <= 0) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Illegal number of digits: ", i));
            }
            return append0(new FixedNumber(dateTimeFieldType, i, false));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendFixedSignedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType != null) {
            if (i <= 0) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Illegal number of digits: ", i));
            }
            return append0(new FixedNumber(dateTimeFieldType, i, true));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendFraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType != null) {
            if (i2 < i) {
                i2 = i;
            }
            if (i >= 0 && i2 > 0) {
                return append0(new Fraction(dateTimeFieldType, i, i2));
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendFractionOfDay(int i, int i2) {
        return appendFraction(DateTimeFieldType.dayOfYear(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfHour(int i, int i2) {
        return appendFraction(DateTimeFieldType.hourOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfMinute(int i, int i2) {
        return appendFraction(DateTimeFieldType.minuteOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfSecond(int i, int i2) {
        return appendFraction(DateTimeFieldType.secondOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendHalfdayOfDayText() {
        return appendText(DateTimeFieldType.halfdayOfDay());
    }

    public DateTimeFormatterBuilder appendHourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.hourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.hourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        return append0(new CharacterLiteral(c));
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        if (str != null) {
            int length = str.length();
            if (length == 0) {
                return this;
            }
            return append0(length != 1 ? new StringLiteral(str) : new CharacterLiteral(str.charAt(0)));
        }
        throw new IllegalArgumentException("Literal must not be null");
    }

    public DateTimeFormatterBuilder appendMillisOfDay(int i) {
        return appendDecimal(DateTimeFieldType.millisOfDay(), i, 8);
    }

    public DateTimeFormatterBuilder appendMillisOfSecond(int i) {
        return appendDecimal(DateTimeFieldType.millisOfSecond(), i, 3);
    }

    public DateTimeFormatterBuilder appendMinuteOfDay(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfDay(), i, 4);
    }

    public DateTimeFormatterBuilder appendMinuteOfHour(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfHour(), i, 2);
    }

    public DateTimeFormatterBuilder appendMonthOfYear(int i) {
        return appendDecimal(DateTimeFieldType.monthOfYear(), i, 2);
    }

    public DateTimeFormatterBuilder appendMonthOfYearShortText() {
        return appendShortText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendMonthOfYearText() {
        return appendText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, new MatchingParser(new InternalParser[]{DateTimeParserInternalParser.of(dateTimeParser), null}));
    }

    public DateTimeFormatterBuilder appendPattern(String str) {
        DateTimeFormat.appendPatternTo(this, str);
        return this;
    }

    public DateTimeFormatterBuilder appendSecondOfDay(int i) {
        return appendDecimal(DateTimeFieldType.secondOfDay(), i, 5);
    }

    public DateTimeFormatterBuilder appendSecondOfMinute(int i) {
        return appendDecimal(DateTimeFieldType.secondOfMinute(), i, 2);
    }

    public DateTimeFormatterBuilder appendShortText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return append0(new TextField(dateTimeFieldType, true));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType != null) {
            if (i2 < i) {
                i2 = i;
            }
            if (i < 0 || i2 <= 0) {
                throw new IllegalArgumentException();
            }
            return i <= 1 ? append0(new UnpaddedNumber(dateTimeFieldType, i2, true)) : append0(new PaddedNumber(dateTimeFieldType, i2, true, i));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return append0(new TextField(dateTimeFieldType, false));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendTimeZoneId() {
        TimeZoneId timeZoneId = TimeZoneId.INSTANCE;
        return append0(timeZoneId, timeZoneId);
    }

    public DateTimeFormatterBuilder appendTimeZoneName() {
        return append0(new TimeZoneName(0, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneName(Map<String, DateTimeZone> map) {
        TimeZoneName timeZoneName = new TimeZoneName(0, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str2, z, i, i2));
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str, z, i, i2));
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName() {
        return append0(new TimeZoneName(1, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName(Map<String, DateTimeZone> map) {
        TimeZoneName timeZoneName = new TimeZoneName(1, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i) {
        return appendTwoDigitWeekyear(i, false);
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), i, z));
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i) {
        return appendTwoDigitYear(i, false);
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.year(), i, z));
    }

    public DateTimeFormatterBuilder appendWeekOfWeekyear(int i) {
        return appendDecimal(DateTimeFieldType.weekOfWeekyear(), i, 2);
    }

    public DateTimeFormatterBuilder appendWeekyear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.weekyear(), i, i2);
    }

    public DateTimeFormatterBuilder appendYear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.year(), i, i2);
    }

    public DateTimeFormatterBuilder appendYearOfCentury(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfCentury(), i, i2);
    }

    public DateTimeFormatterBuilder appendYearOfEra(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfEra(), i, i2);
    }

    public boolean canBuildFormatter() {
        return isFormatter(getFormatter());
    }

    public boolean canBuildParser() {
        return isParser(getFormatter());
    }

    public boolean canBuildPrinter() {
        return isPrinter(getFormatter());
    }

    public void clear() {
        this.iFormatter = null;
        this.iElementPairs.clear();
    }

    public DateTimeFormatter toFormatter() {
        Object formatter = getFormatter();
        InternalParser internalParser = null;
        InternalPrinter internalPrinter = isPrinter(formatter) ? (InternalPrinter) formatter : null;
        if (isParser(formatter)) {
            internalParser = (InternalParser) formatter;
        }
        if (internalPrinter == null && internalParser == null) {
            throw new UnsupportedOperationException("Both printing and parsing not supported");
        }
        return new DateTimeFormatter(internalPrinter, internalParser);
    }

    public DateTimeParser toParser() {
        Object formatter = getFormatter();
        if (isParser(formatter)) {
            return InternalParserDateTimeParser.of((InternalParser) formatter);
        }
        throw new UnsupportedOperationException("Parsing is not supported");
    }

    public DateTimePrinter toPrinter() {
        Object formatter = getFormatter();
        if (isPrinter(formatter)) {
            return InternalPrinterDateTimePrinter.of((InternalPrinter) formatter);
        }
        throw new UnsupportedOperationException("Printing is not supported");
    }
}
