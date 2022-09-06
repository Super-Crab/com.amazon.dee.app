package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.MoveChildRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class MoveChildRequestSerializer implements JsonSerializer<MoveChildRequest> {
    public static final JsonSerializer<MoveChildRequest> INSTANCE = new MoveChildRequestSerializer();
    private final MoveChildRequestFieldSerializer mFieldSerializer = new MoveChildRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class MoveChildRequestFieldSerializer implements JsonFieldSerializer<MoveChildRequest> {
        MoveChildRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((MoveChildRequestFieldSerializer) ((MoveChildRequest) obj), jsonGenerator);
        }

        public <U extends MoveChildRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("childId");
            SimpleSerializers.serializeString(u.getChildId(), jsonGenerator);
            jsonGenerator.writeFieldName("fromParent");
            SimpleSerializers.serializeString(u.getFromParentId(), jsonGenerator);
            jsonGenerator.writeFieldName("toParent");
            SimpleSerializers.serializeString(u.getToParentId(), jsonGenerator);
        }
    }

    private MoveChildRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(MoveChildRequest moveChildRequest, JsonGenerator jsonGenerator) throws IOException {
        if (moveChildRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((MoveChildRequestFieldSerializer) moveChildRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
