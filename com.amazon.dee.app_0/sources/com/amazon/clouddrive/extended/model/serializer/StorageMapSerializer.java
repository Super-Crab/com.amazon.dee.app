package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class StorageMapSerializer implements JsonSerializer<Map<String, Long>> {
    public static final JsonSerializer<Map<String, Long>> INSTANCE = new StorageMapSerializer();

    private StorageMapSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(Map<String, Long> map, JsonGenerator jsonGenerator) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            SimpleSerializers.serializeLong(entry.getValue(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
