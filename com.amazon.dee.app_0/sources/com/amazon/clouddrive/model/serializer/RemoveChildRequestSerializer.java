package com.amazon.clouddrive.model.serializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.model.RemoveChildFromParentRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class RemoveChildRequestSerializer implements JsonSerializer<RemoveChildFromParentRequest> {
    public static final JsonSerializer<RemoveChildFromParentRequest> INSTANCE = new RemoveChildRequestSerializer();
    private final RemoveChildRequestFieldSerializer mFieldSerializer = new RemoveChildRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class RemoveChildRequestFieldSerializer implements JsonFieldSerializer<RemoveChildFromParentRequest> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((RemoveChildRequestFieldSerializer) ((RemoveChildFromParentRequest) obj), jsonGenerator);
        }

        public <U extends RemoveChildFromParentRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName(Message.SERIALIZED_NAME_PARENT_ID);
            SimpleSerializers.serializeString(u.getParentId(), jsonGenerator);
            jsonGenerator.writeFieldName("childId");
            SimpleSerializers.serializeString(u.getChildId(), jsonGenerator);
        }
    }

    private RemoveChildRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(RemoveChildFromParentRequest removeChildFromParentRequest, JsonGenerator jsonGenerator) throws IOException {
        if (removeChildFromParentRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((RemoveChildRequestFieldSerializer) removeChildFromParentRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
