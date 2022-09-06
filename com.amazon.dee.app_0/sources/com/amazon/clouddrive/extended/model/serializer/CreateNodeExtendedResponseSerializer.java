package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.CreateNodeExtendedResponse;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CreateNodeExtendedResponseSerializer implements JsonSerializer<CreateNodeExtendedResponse> {
    public static final JsonSerializer<CreateNodeExtendedResponse> INSTANCE = new CreateNodeExtendedResponseSerializer();
    private final CreateNodeExtendedFieldSerializer mFieldSerializer = new CreateNodeExtendedFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CreateNodeExtendedFieldSerializer extends NodeExtendedFieldSerializer {
        CreateNodeExtendedFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.extended.model.serializer.NodeExtendedFieldSerializer, com.amazon.clouddrive.model.serializer.NodeSerializer.NodeFieldSerializer, com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((CreateNodeExtendedFieldSerializer) ((IEditableNode) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.extended.model.serializer.NodeExtendedFieldSerializer, com.amazon.clouddrive.model.serializer.NodeSerializer.NodeFieldSerializer, com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer
        public <U extends IEditableNode> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((CreateNodeExtendedFieldSerializer) u, jsonGenerator);
            if (u instanceof CreateNodeExtendedResponse) {
                jsonGenerator.writeFieldName("location");
                SimpleSerializers.serializeString(((CreateNodeExtendedResponse) u).getLocation(), jsonGenerator);
            }
        }
    }

    private CreateNodeExtendedResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(CreateNodeExtendedResponse createNodeExtendedResponse, JsonGenerator jsonGenerator) throws IOException {
        if (createNodeExtendedResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((CreateNodeExtendedFieldSerializer) createNodeExtendedResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
