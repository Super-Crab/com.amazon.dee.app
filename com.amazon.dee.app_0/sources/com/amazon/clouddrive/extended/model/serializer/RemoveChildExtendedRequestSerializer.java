package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.RemoveChildFromParentExtendedRequest;
import com.amazon.clouddrive.model.RemoveChildFromParentRequest;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.RemoveChildRequestSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class RemoveChildExtendedRequestSerializer implements JsonSerializer<RemoveChildFromParentExtendedRequest> {
    public static final JsonSerializer<RemoveChildFromParentExtendedRequest> INSTANCE = new RemoveChildExtendedRequestSerializer();
    private final RemoveChildExtendedRequestFieldSerializer mFieldSerializer = new RemoveChildExtendedRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class RemoveChildExtendedRequestFieldSerializer extends RemoveChildRequestSerializer.RemoveChildRequestFieldSerializer {
        RemoveChildExtendedRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.RemoveChildRequestSerializer.RemoveChildRequestFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((RemoveChildExtendedRequestFieldSerializer) ((RemoveChildFromParentRequest) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.model.serializer.RemoveChildRequestSerializer.RemoveChildRequestFieldSerializer
        public <U extends RemoveChildFromParentRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((RemoveChildExtendedRequestFieldSerializer) u, jsonGenerator);
            if (u instanceof RemoveChildFromParentExtendedRequest) {
                RemoveChildFromParentExtendedRequest removeChildFromParentExtendedRequest = (RemoveChildFromParentExtendedRequest) u;
                if (removeChildFromParentExtendedRequest.getChildOwnerId() == null) {
                    return;
                }
                jsonGenerator.writeFieldName("childOwnerId");
                SimpleSerializers.serializeString(removeChildFromParentExtendedRequest.getChildOwnerId(), jsonGenerator);
            }
        }
    }

    private RemoveChildExtendedRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(RemoveChildFromParentExtendedRequest removeChildFromParentExtendedRequest, JsonGenerator jsonGenerator) throws IOException {
        if (removeChildFromParentExtendedRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((RemoveChildExtendedRequestFieldSerializer) removeChildFromParentExtendedRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
