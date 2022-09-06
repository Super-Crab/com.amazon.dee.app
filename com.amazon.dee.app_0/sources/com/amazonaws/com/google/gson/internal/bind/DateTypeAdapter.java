package com.amazonaws.com.google.gson.internal.bind;

import com.amazon.dee.sdk.iotsoftap.Constants;
import com.amazonaws.com.google.gson.Gson;
import com.amazonaws.com.google.gson.JsonSyntaxException;
import com.amazonaws.com.google.gson.TypeAdapter;
import com.amazonaws.com.google.gson.TypeAdapterFactory;
import com.amazonaws.com.google.gson.reflect.TypeToken;
import com.amazonaws.com.google.gson.stream.JsonReader;
import com.amazonaws.com.google.gson.stream.JsonToken;
import com.amazonaws.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes13.dex */
public final class DateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() { // from class: com.amazonaws.com.google.gson.internal.bind.DateTypeAdapter.1
        @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Date.class) {
                return new DateTypeAdapter();
            }
            return null;
        }
    };
    private final DateFormat enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat localFormat = DateFormat.getDateTimeInstance(2, 2);
    private final DateFormat iso8601Format = buildIso8601Format();

    private static DateFormat buildIso8601Format() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        return simpleDateFormat;
    }

    private synchronized Date deserializeToDate(String str) {
        try {
            try {
                try {
                } catch (ParseException unused) {
                    return this.iso8601Format.parse(str);
                }
            } catch (ParseException e) {
                throw new JsonSyntaxException(str, e);
            }
        } catch (ParseException unused2) {
            return this.enUsFormat.parse(str);
        }
        return this.localFormat.parse(str);
    }

    @Override // com.amazonaws.com.google.gson.TypeAdapter
    /* renamed from: read  reason: collision with other method in class */
    public Date mo6687read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return deserializeToDate(jsonReader.nextString());
    }

    @Override // com.amazonaws.com.google.gson.TypeAdapter
    public synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(this.enUsFormat.format(date));
        }
    }
}
