package com.amazon.alexa.sharing.repo.models.acms.payload;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
/* loaded from: classes10.dex */
public class MessagePayloadGsonAdapter<T> implements JsonDeserializer<T>, JsonSerializer<T> {
    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public T mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Type type2;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject.has("text")) {
            type2 = TextMessagePayload.class;
        } else if (asJsonObject.has("sharingMessage")) {
            type2 = SharingMessagePayload.class;
        } else {
            throw new JsonParseException("TypeAdapter Encountered unknown ACMS Payload");
        }
        return (T) jsonDeserializationContext.deserialize(asJsonObject, type2);
    }

    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(t);
    }
}
