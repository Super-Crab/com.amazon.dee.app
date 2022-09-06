package com.amazon.clouddrive.model.serializer;

import java.io.IOException;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PropertyMapSerializer implements JsonSerializer<Map<String, String>> {
    public static final JsonSerializer<Map<String, String>> INSTANCE = new PropertyMapSerializer();

    private PropertyMapSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(Map<String, String> map, JsonGenerator jsonGenerator) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            SimpleSerializers.serializeString(entry.getValue(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
