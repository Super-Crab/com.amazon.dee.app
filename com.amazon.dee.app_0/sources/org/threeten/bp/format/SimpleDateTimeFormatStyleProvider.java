package org.threeten.bp.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.chrono.Chronology;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class SimpleDateTimeFormatStyleProvider extends DateTimeFormatStyleProvider {
    private static final ConcurrentMap<String, Object> FORMATTER_CACHE = new ConcurrentHashMap(16, 0.75f, 2);

    private int convertStyle(FormatStyle formatStyle) {
        return formatStyle.ordinal();
    }

    @Override // org.threeten.bp.format.DateTimeFormatStyleProvider
    public Locale[] getAvailableLocales() {
        return DateFormat.getAvailableLocales();
    }

    @Override // org.threeten.bp.format.DateTimeFormatStyleProvider
    public DateTimeFormatter getFormatter(FormatStyle formatStyle, FormatStyle formatStyle2, Chronology chronology, Locale locale) {
        DateFormat timeInstance;
        if (formatStyle == null && formatStyle2 == null) {
            throw new IllegalArgumentException("Date and Time style must not both be null");
        }
        String str = chronology.getId() + '|' + locale.toString() + '|' + formatStyle + formatStyle2;
        Object obj = FORMATTER_CACHE.get(str);
        if (obj != null) {
            if (!obj.equals("")) {
                return (DateTimeFormatter) obj;
            }
            throw new IllegalArgumentException("Unable to convert DateFormat to DateTimeFormatter");
        }
        if (formatStyle == null) {
            timeInstance = DateFormat.getTimeInstance(convertStyle(formatStyle2), locale);
        } else if (formatStyle2 != null) {
            timeInstance = DateFormat.getDateTimeInstance(convertStyle(formatStyle), convertStyle(formatStyle2), locale);
        } else {
            timeInstance = DateFormat.getDateInstance(convertStyle(formatStyle), locale);
        }
        if (timeInstance instanceof SimpleDateFormat) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(((SimpleDateFormat) timeInstance).toPattern()).toFormatter(locale);
            FORMATTER_CACHE.putIfAbsent(str, formatter);
            return formatter;
        }
        FORMATTER_CACHE.putIfAbsent(str, "");
        throw new IllegalArgumentException("Unable to convert DateFormat to DateTimeFormatter");
    }
}
