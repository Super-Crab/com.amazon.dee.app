package org.joda.time.format;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class DateTimeParserInternalParser implements InternalParser {
    private final DateTimeParser underlying;

    private DateTimeParserInternalParser(DateTimeParser dateTimeParser) {
        this.underlying = dateTimeParser;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InternalParser of(DateTimeParser dateTimeParser) {
        if (dateTimeParser instanceof InternalParserDateTimeParser) {
            return (InternalParser) dateTimeParser;
        }
        if (dateTimeParser != null) {
            return new DateTimeParserInternalParser(dateTimeParser);
        }
        return null;
    }

    @Override // org.joda.time.format.InternalParser
    public int estimateParsedLength() {
        return this.underlying.estimateParsedLength();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateTimeParser getUnderlying() {
        return this.underlying;
    }

    @Override // org.joda.time.format.InternalParser
    public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
        return this.underlying.parseInto(dateTimeParserBucket, charSequence.toString(), i);
    }
}
