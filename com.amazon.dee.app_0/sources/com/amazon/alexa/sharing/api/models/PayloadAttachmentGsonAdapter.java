package com.amazon.alexa.sharing.api.models;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
/* loaded from: classes10.dex */
public class PayloadAttachmentGsonAdapter<T> implements JsonDeserializer<T>, JsonSerializer<T> {
    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public T mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Type type2;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject.has("type")) {
            String asString = asJsonObject.get("type").getAsString();
            if ("text".equals(asString)) {
                type2 = PayloadAttachmentText.class;
            } else if ("url".equals(asString)) {
                type2 = PayloadAttachmentUrl.class;
            } else if (PayloadAttachmentEmoji.TYPE.equals(asString)) {
                type2 = PayloadAttachmentEmoji.class;
            } else {
                throw new JsonParseException(GeneratedOutlineSupport1.outline72("TypeAdapter - unknown type in Payload: ", asString));
            }
            return (T) jsonDeserializationContext.deserialize(asJsonObject, type2);
        }
        throw new JsonParseException("TypeAdapter - no type was found in jsonObject");
    }

    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(t);
    }
}
