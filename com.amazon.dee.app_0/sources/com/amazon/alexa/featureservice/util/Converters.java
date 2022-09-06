package com.amazon.alexa.featureservice.util;

import androidx.room.TypeConverter;
import java.util.Date;
/* loaded from: classes7.dex */
public final class Converters {
    private Converters() {
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(date.getTime());
    }

    @TypeConverter
    public static Date fromTimestamp(Long l) {
        if (l == null) {
            return null;
        }
        return new Date(l.longValue());
    }
}
