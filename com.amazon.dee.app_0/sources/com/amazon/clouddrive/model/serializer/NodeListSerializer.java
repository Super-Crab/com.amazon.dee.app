package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.Node;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class NodeListSerializer implements JsonSerializer<List<Node>> {
    public static final JsonSerializer<List<Node>> INSTANCE = new NodeListSerializer();

    private NodeListSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(List<Node> list, JsonGenerator jsonGenerator) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        for (Node node : list) {
            NodeSerializer.INSTANCE.serialize(node, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }
}
