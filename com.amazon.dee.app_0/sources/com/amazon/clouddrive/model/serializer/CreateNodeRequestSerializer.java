package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.CreateNodeRequest;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.serializer.EditableNodeSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CreateNodeRequestSerializer implements JsonSerializer<CreateNodeRequest> {
    public static final JsonSerializer<CreateNodeRequest> INSTANCE = new CreateNodeRequestSerializer();
    private final CreateNodeRequestFieldSerializer mFieldSerializer = new CreateNodeRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CreateNodeRequestFieldSerializer extends EditableNodeSerializer.EditableNodeFieldSerializer {
        CreateNodeRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((CreateNodeRequestFieldSerializer) ((IEditableNode) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer
        public <U extends IEditableNode> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((CreateNodeRequestFieldSerializer) u, jsonGenerator);
            if (u instanceof CreateNodeRequest) {
                jsonGenerator.writeFieldName("conflictResolution");
                SimpleSerializers.serializeString(((CreateNodeRequest) u).getConflictResolution(), jsonGenerator);
            }
        }
    }

    private CreateNodeRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(CreateNodeRequest createNodeRequest, JsonGenerator jsonGenerator) throws IOException {
        if (createNodeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((CreateNodeRequestFieldSerializer) createNodeRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
