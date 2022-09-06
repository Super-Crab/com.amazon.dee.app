package com.amazon.alexa.client.alexaservice.networking.adapters;

import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.CapabilityInterface;
import com.amazon.alexa.client.core.capabilities.CapabilityType;
import com.amazon.alexa.igK;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.deecomms.calling.incallcommands.constants.CommsFocusConstants;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public class CapabilityAdapter extends TypeAdapter<Capability> {
    public final JsonObject BIo(JsonReader jsonReader) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            JsonElement zZm = zZm(jsonReader);
            if (zZm != null) {
                jsonObject.add(nextName, zZm);
            }
        }
        jsonReader.endObject();
        return jsonObject;
    }

    @Override // com.google.gson.TypeAdapter
    /* renamed from: zZm */
    public void write(JsonWriter jsonWriter, Capability capability) throws IOException {
        JsonWriter value = jsonWriter.beginObject().name("type").value(capability.getType().getValue()).name(CommsFocusConstants.INTERFACE).value(capability.getInterface().getValue()).name("version").value(capability.getVersion());
        if (capability.getConfigurations() != null) {
            value.name("configurations");
            value.jsonValue(capability.getConfigurations().toString());
        }
        value.endObject();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public Capability mo8353read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        CapabilityType capabilityType = null;
        CapabilityInterface capabilityInterface = null;
        String str = null;
        JsonObject jsonObject = null;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != -214226371) {
                if (hashCode != 3575610) {
                    if (hashCode != 351608024) {
                        if (hashCode == 502623545 && nextName.equals(CommsFocusConstants.INTERFACE)) {
                            c = 0;
                        }
                    } else if (nextName.equals("version")) {
                        c = 2;
                    }
                } else if (nextName.equals("type")) {
                    c = 1;
                }
            } else if (nextName.equals("configurations")) {
                c = 3;
            }
            if (c == 0) {
                capabilityInterface = CapabilityInterface.create(jsonReader.nextString());
            } else if (c == 1) {
                capabilityType = CapabilityType.create(jsonReader.nextString());
            } else if (c == 2) {
                str = jsonReader.nextString();
            } else if (c != 3) {
                jsonReader.skipValue();
            } else {
                jsonObject = BIo(jsonReader);
            }
        }
        jsonReader.endObject();
        Preconditions.notNull(str, "Version == null");
        Preconditions.notNull(capabilityType, "Capability Type == null");
        Preconditions.notNull(capabilityInterface, "Capability Interface == null");
        return Capability.create(capabilityType, capabilityInterface, str, jsonObject);
    }

    public final JsonElement zZm(JsonReader jsonReader) throws IOException {
        switch (igK.zZm[jsonReader.peek().ordinal()]) {
            case 1:
                return new JsonPrimitive(jsonReader.nextString());
            case 2:
                return new JsonPrimitive((Number) Double.valueOf(jsonReader.nextDouble()));
            case 3:
                return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
            case 4:
                JsonArray jsonArray = new JsonArray();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    JsonElement zZm = zZm(jsonReader);
                    if (zZm != null) {
                        jsonArray.add(zZm);
                    }
                }
                jsonReader.endArray();
                return jsonArray;
            case 5:
                return BIo(jsonReader);
            case 6:
                throw new JsonParseException("Unexpected JSON name");
            case 7:
                throw new JsonParseException("Unexpected JSON end object");
            case 8:
                throw new JsonParseException("Unexpected JSON end document");
            case 9:
                throw new JsonParseException("Unexpected JSON end array");
            default:
                jsonReader.skipValue();
                return null;
        }
    }
}
