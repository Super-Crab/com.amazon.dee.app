package com.amazon.clouddrive.model.serializer;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class NodeIdListSerializer implements JsonSerializer<List<String>> {
    public static final JsonSerializer<List<String>> INSTANCE = new NodeIdListSerializer();

    private NodeIdListSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(List<String> list, JsonGenerator jsonGenerator) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        for (String str : list) {
            SimpleSerializers.serializeString(str, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }
}
