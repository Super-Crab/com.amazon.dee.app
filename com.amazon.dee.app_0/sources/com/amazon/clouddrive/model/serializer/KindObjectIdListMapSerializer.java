package com.amazon.clouddrive.model.serializer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class KindObjectIdListMapSerializer implements JsonSerializer<Map<String, List<String>>> {
    public static final JsonSerializer<Map<String, List<String>>> INSTANCE = new KindObjectIdListMapSerializer();

    private KindObjectIdListMapSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(Map<String, List<String>> map, JsonGenerator jsonGenerator) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            NodeIdListSerializer.INSTANCE.serialize(entry.getValue(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
