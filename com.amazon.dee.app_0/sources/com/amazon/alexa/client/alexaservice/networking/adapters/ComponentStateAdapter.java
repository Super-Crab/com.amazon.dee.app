package com.amazon.alexa.client.alexaservice.networking.adapters;

import com.amazon.alexa.GwO;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map;
/* loaded from: classes6.dex */
public class ComponentStateAdapter implements JsonDeserializer<ComponentState> {
    public final Map<Namespace, GwO> zZm;

    public ComponentStateAdapter(Map<Namespace, GwO> map) {
        this.zZm = map;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public ComponentState mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ComponentStatePayload componentStatePayload;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        ComponentStateHeader componentStateHeader = (ComponentStateHeader) jsonDeserializationContext.deserialize(asJsonObject.getAsJsonObject("header"), ComponentStateHeader.class);
        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("payload");
        GwO gwO = this.zZm.get(componentStateHeader.BIo());
        if (gwO != null) {
            Class<? extends Payload> zZm = gwO.zZm(componentStateHeader.zZm());
            if (RawStringPayload.class.equals(zZm)) {
                componentStatePayload = RawStringPayload.create(asJsonObject2.toString());
            } else {
                componentStatePayload = (ComponentStatePayload) jsonDeserializationContext.deserialize(asJsonObject2, zZm);
            }
            return ComponentState.create(componentStateHeader, componentStatePayload);
        }
        return ComponentState.create(componentStateHeader, RawStringPayload.create(asJsonObject2.toString()));
    }
}
