package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.AddChildToParentExtendedRequest;
import com.amazon.clouddrive.model.AddChildToParentRequest;
import com.amazon.clouddrive.model.serializer.AddChildRequestSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class AddChildExtendedRequestSerializer implements JsonSerializer<AddChildToParentExtendedRequest> {
    public static final JsonSerializer<AddChildToParentExtendedRequest> INSTANCE = new AddChildExtendedRequestSerializer();
    private final AddChildExtendedRequestFieldSerializer mFieldSerializer = new AddChildExtendedRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class AddChildExtendedRequestFieldSerializer extends AddChildRequestSerializer.AddChildRequestFieldSerializer {
        AddChildExtendedRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.AddChildRequestSerializer.AddChildRequestFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((AddChildExtendedRequestFieldSerializer) ((AddChildToParentRequest) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.model.serializer.AddChildRequestSerializer.AddChildRequestFieldSerializer
        public <U extends AddChildToParentRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((AddChildExtendedRequestFieldSerializer) u, jsonGenerator);
            if (u instanceof AddChildToParentExtendedRequest) {
                AddChildToParentExtendedRequest addChildToParentExtendedRequest = (AddChildToParentExtendedRequest) u;
                if (addChildToParentExtendedRequest.getChildOwnerId() == null) {
                    return;
                }
                jsonGenerator.writeFieldName("childOwnerId");
                SimpleSerializers.serializeString(addChildToParentExtendedRequest.getChildOwnerId(), jsonGenerator);
            }
        }
    }

    private AddChildExtendedRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(AddChildToParentExtendedRequest addChildToParentExtendedRequest, JsonGenerator jsonGenerator) throws IOException {
        if (addChildToParentExtendedRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((AddChildExtendedRequestFieldSerializer) addChildToParentExtendedRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
