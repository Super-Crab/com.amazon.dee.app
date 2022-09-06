package com.amazon.alexa.rangecontroller.lib.model.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.typeadapters.UtcDateTypeAdapter;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.lang.reflect.Type;
import java.util.Date;
import org.apache.commons.net.ntp.TimeStamp;
/* loaded from: classes9.dex */
public enum RcSerializer {
    INSTANCE;
    
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().registerTypeAdapter(TimeStamp.class, new JsonDeserializer<TimeStamp>() { // from class: com.amazon.alexa.rangecontroller.lib.model.serialization.type.changereport.TimeStampDeserializer
        @Override // com.google.gson.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public TimeStamp mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return TimeStamp.getNtpTime(jsonElement.getAsLong());
        }
    }).registerTypeAdapter(TimeStamp.class, new JsonSerializer<TimeStamp>() { // from class: com.amazon.alexa.rangecontroller.lib.model.serialization.type.changereport.TimeStampSerializer
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(TimeStamp timeStamp, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive((Number) Long.valueOf(timeStamp.getTime()));
        }
    }).registerTypeAdapter(Date.class, new UtcDateTypeAdapter()).create();

    @CheckForNull
    public <T> T fromJson(@Nullable JsonElement jsonElement, @NonNull Class<T> cls) {
        return (T) GSON.fromJson(jsonElement, (Class<Object>) cls);
    }

    @CheckForNull
    public <T> String toJson(@Nullable T t) {
        if (t == null) {
            return null;
        }
        return GSON.toJson(t);
    }

    @CheckForNull
    public <T> T fromJson(@Nullable String str, @NonNull Type type) {
        return (T) GSON.fromJson(str, type);
    }
}
