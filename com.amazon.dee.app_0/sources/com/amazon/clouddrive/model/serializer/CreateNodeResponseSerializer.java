package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.CreateNodeResponse;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.serializer.NodeSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CreateNodeResponseSerializer implements JsonSerializer<CreateNodeResponse> {
    public static final JsonSerializer<CreateNodeResponse> INSTANCE = new CreateNodeResponseSerializer();
    private CreateNodeResponseFieldSerializer mFieldSerializer = new CreateNodeResponseFieldSerializer();

    /* loaded from: classes11.dex */
    public static class CreateNodeResponseFieldSerializer extends NodeSerializer.NodeFieldSerializer {
        @Override // com.amazon.clouddrive.model.serializer.NodeSerializer.NodeFieldSerializer, com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((CreateNodeResponseFieldSerializer) ((IEditableNode) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.model.serializer.NodeSerializer.NodeFieldSerializer, com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer
        public <U extends IEditableNode> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((CreateNodeResponseFieldSerializer) u, jsonGenerator);
            if (u instanceof CreateNodeResponse) {
                jsonGenerator.writeFieldName("location");
                SimpleSerializers.serializeString(((CreateNodeResponse) u).getLocation(), jsonGenerator);
            }
        }
    }

    private CreateNodeResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(CreateNodeResponse createNodeResponse, JsonGenerator jsonGenerator) throws IOException {
        if (createNodeResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((CreateNodeResponseFieldSerializer) createNodeResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
