package com.amazonaws.util.json;

import com.amazonaws.com.google.gson.JsonDeserializationContext;
import com.amazonaws.com.google.gson.JsonDeserializer;
import com.amazonaws.com.google.gson.JsonElement;
import com.amazonaws.com.google.gson.JsonParseException;
import com.amazonaws.com.google.gson.JsonPrimitive;
import com.amazonaws.com.google.gson.JsonSerializationContext;
import com.amazonaws.com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/* loaded from: classes.dex */
public class DateDeserializer implements JsonDeserializer<Date>, JsonSerializer<Date> {
    private final List<String> dateFormats;
    private final SimpleDateFormat mIso8601DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private SimpleDateFormat mSimpleDateFormat;

    public DateDeserializer(String[] strArr) {
        this.dateFormats = Arrays.asList(strArr);
    }

    @Override // com.amazonaws.com.google.gson.JsonDeserializer
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        String asString = jsonElement.getAsString();
        for (String str : this.dateFormats) {
            try {
                Date date = new Date();
                this.mSimpleDateFormat = new SimpleDateFormat(str);
                date.setTime(this.mSimpleDateFormat.parse(asString).getTime());
                return date;
            } catch (ParseException unused) {
            }
        }
        try {
            return DateFormat.getDateInstance(2).parse(asString);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    @Override // com.amazonaws.com.google.gson.JsonSerializer
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonPrimitive jsonPrimitive;
        synchronized (this.mIso8601DateFormat) {
            jsonPrimitive = new JsonPrimitive(this.mIso8601DateFormat.format(date));
        }
        return jsonPrimitive;
    }
}
