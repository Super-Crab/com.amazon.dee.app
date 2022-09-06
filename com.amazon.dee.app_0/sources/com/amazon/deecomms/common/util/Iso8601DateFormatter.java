package com.amazon.deecomms.common.util;

import androidx.annotation.NonNull;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes12.dex */
public class Iso8601DateFormatter {
    private final DateFormat ISO_8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US) { // from class: com.amazon.deecomms.common.util.Iso8601DateFormatter.1
        @Override // java.text.SimpleDateFormat, java.text.DateFormat
        public StringBuffer format(@NonNull Date date, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
            StringBuffer format = super.format(date, stringBuffer, fieldPosition);
            int length = format.length() - 2;
            if (format.charAt(length) != ':') {
                format.insert(length, ":");
            }
            return format;
        }
    };

    public String formatDateTime(long j) {
        return this.ISO_8601_DATE_FORMAT.format(Long.valueOf(j));
    }
}
