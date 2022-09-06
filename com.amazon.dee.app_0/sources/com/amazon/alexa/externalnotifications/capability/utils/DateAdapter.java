package com.amazon.alexa.externalnotifications.capability.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes7.dex */
public class DateAdapter extends TypeAdapter<Date> {
    private static final String ISO_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @Override // com.google.gson.TypeAdapter
    /* renamed from: read  reason: collision with other method in class */
    public Date mo8353read(JsonReader jsonReader) throws IOException {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).parse(jsonReader.nextString());
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        jsonWriter.value(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).format(date));
    }
}
