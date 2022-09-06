package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.PostNodeRequest;
import com.amazon.clouddrive.model.serializer.EditableNodeSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PostNodeRequestSerializer implements JsonSerializer<PostNodeRequest> {
    public static final JsonSerializer<PostNodeRequest> INSTANCE = new PostNodeRequestSerializer();
    private final PostNodeRequestFieldSerializer mFieldSerializer = new PostNodeRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class PostNodeRequestFieldSerializer extends EditableNodeSerializer.EditableNodeFieldSerializer {
        PostNodeRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((PostNodeRequestFieldSerializer) ((IEditableNode) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer
        public <U extends IEditableNode> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((PostNodeRequestFieldSerializer) u, jsonGenerator);
            if (u instanceof PostNodeRequest) {
                jsonGenerator.writeFieldName("conflictResolution");
                SimpleSerializers.serializeString(((PostNodeRequest) u).getConflictResolution(), jsonGenerator);
            }
        }
    }

    private PostNodeRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(PostNodeRequest postNodeRequest, JsonGenerator jsonGenerator) throws IOException {
        if (postNodeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((PostNodeRequestFieldSerializer) postNodeRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
