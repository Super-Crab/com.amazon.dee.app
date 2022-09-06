package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.UpdateGroupRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class UpdateGroupRequestSerializer implements JsonSerializer<UpdateGroupRequest> {
    public static final JsonSerializer<UpdateGroupRequest> INSTANCE = new UpdateGroupRequestSerializer();
    private final UpdateGroupRequestFieldSerializer mFieldSerializer = new UpdateGroupRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class UpdateGroupRequestFieldSerializer implements JsonFieldSerializer<UpdateGroupRequest> {
        UpdateGroupRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((UpdateGroupRequestFieldSerializer) ((UpdateGroupRequest) obj), jsonGenerator);
        }

        public <U extends UpdateGroupRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getName() != null) {
                jsonGenerator.writeFieldName("name");
                SimpleSerializers.serializeString(u.getName(), jsonGenerator);
            }
            if (u.getDescription() != null) {
                jsonGenerator.writeFieldName("description");
                SimpleSerializers.serializeString(u.getDescription(), jsonGenerator);
            }
            if (u.getCoverNodeId() != null) {
                jsonGenerator.writeFieldName("coverNodeId");
                SimpleSerializers.serializeString(u.getCoverNodeId(), jsonGenerator);
            }
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(UpdateGroupRequest updateGroupRequest, JsonGenerator jsonGenerator) throws IOException {
        if (updateGroupRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((UpdateGroupRequestFieldSerializer) updateGroupRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
