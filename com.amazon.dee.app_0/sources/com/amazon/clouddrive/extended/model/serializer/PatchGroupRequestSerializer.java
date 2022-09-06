package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.PatchGroupRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PatchGroupRequestSerializer implements JsonSerializer<PatchGroupRequest> {
    public static final JsonSerializer<PatchGroupRequest> INSTANCE = new PatchGroupRequestSerializer();
    private final PatchGroupRequestFieldSerializer mFieldSerializer = new PatchGroupRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class PatchGroupRequestFieldSerializer implements JsonFieldSerializer<PatchGroupRequest> {
        PatchGroupRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((PatchGroupRequestFieldSerializer) ((PatchGroupRequest) obj), jsonGenerator);
        }

        public <U extends PatchGroupRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getName() != null) {
                jsonGenerator.writeFieldName("name");
                SimpleSerializers.serializeString(u.getName(), jsonGenerator);
            }
            if (u.getCoverNode() != null) {
                jsonGenerator.writeFieldName("coverNode");
                GroupCoverPhotoSerializer.INSTANCE.serialize(u.getCoverNode(), jsonGenerator);
            }
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(PatchGroupRequest patchGroupRequest, JsonGenerator jsonGenerator) throws IOException {
        if (patchGroupRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((PatchGroupRequestFieldSerializer) patchGroupRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
