package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.EditableNodeExtended;
import com.amazon.clouddrive.extended.model.SetNodeRequest;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.serializer.EditableNodeSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SetNodeRequestSerializer implements JsonSerializer<SetNodeRequest> {
    public static final JsonSerializer<SetNodeRequest> INSTANCE = new SetNodeRequestSerializer();
    private final SetNodeRequestFieldSerializer mFieldSerializer = new SetNodeRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SetNodeRequestFieldSerializer extends EditableNodeSerializer.EditableNodeFieldSerializer {
        SetNodeRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((SetNodeRequestFieldSerializer) ((IEditableNode) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer
        public <U extends IEditableNode> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((SetNodeRequestFieldSerializer) u, jsonGenerator);
            if (u instanceof EditableNodeExtended) {
                jsonGenerator.writeFieldName("restricted");
                SimpleSerializers.serializeBoolean(((EditableNodeExtended) u).isRestricted(), jsonGenerator);
            }
        }
    }

    private SetNodeRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(SetNodeRequest setNodeRequest, JsonGenerator jsonGenerator) throws IOException {
        if (setNodeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((SetNodeRequestFieldSerializer) setNodeRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
