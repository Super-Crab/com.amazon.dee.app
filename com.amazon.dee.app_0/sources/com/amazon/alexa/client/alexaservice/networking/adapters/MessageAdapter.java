package com.amazon.alexa.client.alexaservice.networking.adapters;

import com.amazon.alexa.GwO;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.Map;
/* loaded from: classes6.dex */
public class MessageAdapter implements JsonDeserializer<Message> {
    public final Map<Namespace, GwO> zZm;

    public MessageAdapter(Map<Namespace, GwO> map) {
        this.zZm = map;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public Message mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        Payload payload;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject.has("directive")) {
            asJsonObject = asJsonObject.getAsJsonObject("directive");
        }
        Header header = (Header) jsonDeserializationContext.deserialize(asJsonObject.getAsJsonObject("header"), Header.class);
        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("payload");
        GwO gwO = this.zZm.get(header.getNamespace());
        if (gwO != null) {
            Class<? extends Payload> zZm = gwO.zZm(header.getName());
            if (RawStringPayload.class.equals(zZm)) {
                payload = RawStringPayload.create(asJsonObject2.toString());
            } else {
                payload = (Payload) jsonDeserializationContext.deserialize(asJsonObject2, zZm);
            }
            return Message.create(header, payload);
        }
        return Message.create(header, RawStringPayload.create(asJsonObject2.toString()));
    }
}
