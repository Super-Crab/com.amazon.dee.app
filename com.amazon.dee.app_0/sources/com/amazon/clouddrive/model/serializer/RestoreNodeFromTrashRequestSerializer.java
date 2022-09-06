package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.RestoreNodeFromTrashRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class RestoreNodeFromTrashRequestSerializer implements JsonSerializer<RestoreNodeFromTrashRequest> {
    public static final JsonSerializer<RestoreNodeFromTrashRequest> INSTANCE = new RestoreNodeFromTrashRequestSerializer();
    private final RestoreNodeFromTrashRequestFieldSerializer mFieldSerializer = new RestoreNodeFromTrashRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class RestoreNodeFromTrashRequestFieldSerializer implements JsonFieldSerializer<RestoreNodeFromTrashRequest> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((RestoreNodeFromTrashRequestFieldSerializer) ((RestoreNodeFromTrashRequest) obj), jsonGenerator);
        }

        public <U extends RestoreNodeFromTrashRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("id");
            SimpleSerializers.serializeString(u.getId(), jsonGenerator);
        }
    }

    private RestoreNodeFromTrashRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(RestoreNodeFromTrashRequest restoreNodeFromTrashRequest, JsonGenerator jsonGenerator) throws IOException {
        if (restoreNodeFromTrashRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((RestoreNodeFromTrashRequestFieldSerializer) restoreNodeFromTrashRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
