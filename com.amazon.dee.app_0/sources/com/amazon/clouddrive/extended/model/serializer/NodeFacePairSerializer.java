package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.NodeFacePair;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class NodeFacePairSerializer implements JsonSerializer<NodeFacePair> {
    public static final JsonSerializer<NodeFacePair> INSTANCE = new NodeFacePairSerializer();
    private final NodeFacePairFieldSerializer mFieldSerializer = new NodeFacePairFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NodeFacePairFieldSerializer implements JsonFieldSerializer<NodeFacePair> {
        NodeFacePairFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((NodeFacePairFieldSerializer) ((NodeFacePair) obj), jsonGenerator);
        }

        public <U extends NodeFacePair> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("faceId");
            SimpleSerializers.serializeString(u.getFaceId(), jsonGenerator);
            jsonGenerator.writeFieldName(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID);
            SimpleSerializers.serializeString(u.getNodeId(), jsonGenerator);
        }
    }

    private NodeFacePairSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(NodeFacePair nodeFacePair, JsonGenerator jsonGenerator) throws IOException {
        if (nodeFacePair == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((NodeFacePairFieldSerializer) nodeFacePair, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
