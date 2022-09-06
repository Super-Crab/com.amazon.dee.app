package com.amazon.alexa.client.alexaservice.networking.adapters;

import amazon.speech.model.DirectiveIntent;
import com.amazon.alexa.client.core.messages.CorrelationToken;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.PayloadVersion;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class MessageHeaderAdapter implements JsonSerializer<Header>, JsonDeserializer<Header> {
    @Override // com.google.gson.JsonSerializer
    /* renamed from: zZm */
    public JsonElement serialize(Header header, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("namespace", header.getNamespace().getValue());
        jsonObject.addProperty("name", header.getName().getValue());
        jsonObject.addProperty("messageId", header.getMessageIdentifier().getValue());
        if (header.hasDialogRequestIdentifier()) {
            jsonObject.addProperty("dialogRequestId", header.getDialogRequestIdentifier().getValue());
        }
        if (header.hasCorrelationToken()) {
            jsonObject.addProperty(DirectiveIntent.INTENT_KEY_CORRELATION_TOKEN, header.getCorrelationToken().getValue());
        }
        if (header.hasPayloadVersion()) {
            jsonObject.addProperty("payloadVersion", header.getPayloadVersion().getValue());
        }
        if (header.hasAdditionalFields()) {
            for (Map.Entry<String, JsonElement> entry : header.getAdditionalFields().entrySet()) {
                jsonObject.add(entry.getKey(), entry.getValue());
            }
        }
        return jsonObject;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public Header mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        HashMap hashMap = new HashMap();
        Header.Builder builder = Header.builder();
        for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            char c = 65535;
            switch (key.hashCode()) {
                case -1440013438:
                    if (key.equals("messageId")) {
                        c = 2;
                        break;
                    }
                    break;
                case -905341078:
                    if (key.equals("payloadVersion")) {
                        c = 5;
                        break;
                    }
                    break;
                case -708237406:
                    if (key.equals("dialogRequestId")) {
                        c = 3;
                        break;
                    }
                    break;
                case -523741801:
                    if (key.equals(DirectiveIntent.INTENT_KEY_CORRELATION_TOKEN)) {
                        c = 4;
                        break;
                    }
                    break;
                case 3373707:
                    if (key.equals("name")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1252218203:
                    if (key.equals("namespace")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                builder.setNamespace(Namespace.create(value.getAsString()));
            } else if (c == 1) {
                builder.setName(Name.create(value.getAsString()));
            } else if (c == 2) {
                builder.setMessageIdentifier(MessageIdentifier.create(value.getAsString()));
            } else if (c == 3) {
                builder.setDialogRequestIdentifier(DialogRequestIdentifier.create(value.getAsString()));
            } else if (c == 4) {
                builder.setCorrelationToken(CorrelationToken.create(value.getAsString()));
            } else if (c != 5) {
                hashMap.put(key, value);
            } else {
                builder.setPayloadVersion(PayloadVersion.create(value.getAsString()));
            }
        }
        if (!hashMap.isEmpty()) {
            builder.setAdditionalFields(hashMap);
        }
        return builder.build();
    }
}
