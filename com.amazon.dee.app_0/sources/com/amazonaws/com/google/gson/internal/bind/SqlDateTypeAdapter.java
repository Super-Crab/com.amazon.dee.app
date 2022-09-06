package com.amazonaws.com.google.gson.internal.bind;

import com.amazonaws.com.google.gson.Gson;
import com.amazonaws.com.google.gson.JsonSyntaxException;
import com.amazonaws.com.google.gson.TypeAdapter;
import com.amazonaws.com.google.gson.TypeAdapterFactory;
import com.amazonaws.com.google.gson.reflect.TypeToken;
import com.amazonaws.com.google.gson.stream.JsonReader;
import com.amazonaws.com.google.gson.stream.JsonToken;
import com.amazonaws.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/* loaded from: classes13.dex */
public final class SqlDateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() { // from class: com.amazonaws.com.google.gson.internal.bind.SqlDateTypeAdapter.1
        @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Date.class) {
                return new SqlDateTypeAdapter();
            }
            return null;
        }
    };
    private final DateFormat format = new SimpleDateFormat("MMM d, yyyy");

    @Override // com.amazonaws.com.google.gson.TypeAdapter
    /* renamed from: read  reason: collision with other method in class */
    public synchronized Date mo6687read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new Date(this.format.parse(jsonReader.nextString()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override // com.amazonaws.com.google.gson.TypeAdapter
    public synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        jsonWriter.value(date == null ? null : this.format.format((java.util.Date) date));
    }
}
