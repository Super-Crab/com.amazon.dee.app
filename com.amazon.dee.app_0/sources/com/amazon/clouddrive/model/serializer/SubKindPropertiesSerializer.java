package com.amazon.clouddrive.model.serializer;

import java.io.IOException;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SubKindPropertiesSerializer implements JsonSerializer<Map<String, Map<String, String>>> {
    public static final JsonSerializer<Map<String, Map<String, String>>> INSTANCE = new SubKindPropertiesSerializer();

    private SubKindPropertiesSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(Map<String, Map<String, String>> map, JsonGenerator jsonGenerator) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            SubKindPropertyMapSerializer.INSTANCE.serialize(entry.getValue(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
