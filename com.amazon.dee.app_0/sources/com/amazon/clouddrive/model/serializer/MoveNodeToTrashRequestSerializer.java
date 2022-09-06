package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.MoveNodeToTrashRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class MoveNodeToTrashRequestSerializer implements JsonSerializer<MoveNodeToTrashRequest> {
    public static final JsonSerializer<MoveNodeToTrashRequest> INSTANCE = new MoveNodeToTrashRequestSerializer();
    private final MoveNodeToTrashRequestFieldSerializer mFieldSerializer = new MoveNodeToTrashRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class MoveNodeToTrashRequestFieldSerializer implements JsonFieldSerializer<MoveNodeToTrashRequest> {
        MoveNodeToTrashRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((MoveNodeToTrashRequestFieldSerializer) ((MoveNodeToTrashRequest) obj), jsonGenerator);
        }

        public <U extends MoveNodeToTrashRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getDedupeContext() == null || u.getDedupeContext().length() <= 0) {
                return;
            }
            jsonGenerator.writeFieldName("dedupeContext");
            SimpleSerializers.serializeString(u.getDedupeContext(), jsonGenerator);
        }
    }

    private MoveNodeToTrashRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(MoveNodeToTrashRequest moveNodeToTrashRequest, JsonGenerator jsonGenerator) throws IOException {
        if (moveNodeToTrashRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((MoveNodeToTrashRequestFieldSerializer) moveNodeToTrashRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
