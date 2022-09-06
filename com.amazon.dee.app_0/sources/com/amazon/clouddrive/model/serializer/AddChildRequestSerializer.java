package com.amazon.clouddrive.model.serializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.model.AddChildToParentRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class AddChildRequestSerializer implements JsonSerializer<AddChildToParentRequest> {
    public static final JsonSerializer<AddChildToParentRequest> INSTANCE = new AddChildRequestSerializer();
    private final AddChildRequestFieldSerializer mFieldSerializer = new AddChildRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class AddChildRequestFieldSerializer implements JsonFieldSerializer<AddChildToParentRequest> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((AddChildRequestFieldSerializer) ((AddChildToParentRequest) obj), jsonGenerator);
        }

        public <U extends AddChildToParentRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName(Message.SERIALIZED_NAME_PARENT_ID);
            SimpleSerializers.serializeString(u.getParentId(), jsonGenerator);
            jsonGenerator.writeFieldName("childId");
            SimpleSerializers.serializeString(u.getChildId(), jsonGenerator);
        }
    }

    private AddChildRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(AddChildToParentRequest addChildToParentRequest, JsonGenerator jsonGenerator) throws IOException {
        if (addChildToParentRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((AddChildRequestFieldSerializer) addChildToParentRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
