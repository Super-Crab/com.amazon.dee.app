package com.amazon.deviceevents.com.google.gson.internal.bind;

import com.amazon.deviceevents.com.google.gson.Gson;
import com.amazon.deviceevents.com.google.gson.JsonSyntaxException;
import com.amazon.deviceevents.com.google.gson.TypeAdapter;
import com.amazon.deviceevents.com.google.gson.TypeAdapterFactory;
import com.amazon.deviceevents.com.google.gson.reflect.TypeToken;
import com.amazon.deviceevents.com.google.gson.stream.JsonReader;
import com.amazon.deviceevents.com.google.gson.stream.JsonToken;
import com.amazon.deviceevents.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes12.dex */
public final class TimeTypeAdapter extends TypeAdapter<Time> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() { // from class: com.amazon.deviceevents.com.google.gson.internal.bind.TimeTypeAdapter.1
        @Override // com.amazon.deviceevents.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Time.class) {
                return new TimeTypeAdapter();
            }
            return null;
        }
    };
    private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");

    @Override // com.amazon.deviceevents.com.google.gson.TypeAdapter
    /* renamed from: read  reason: collision with other method in class */
    public synchronized Time mo4010read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            return new Time(this.format.parse(jsonReader.nextString()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override // com.amazon.deviceevents.com.google.gson.TypeAdapter
    public synchronized void write(JsonWriter jsonWriter, Time time) throws IOException {
        jsonWriter.value(time == null ? null : this.format.format((Date) time));
    }
}