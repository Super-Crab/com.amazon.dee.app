package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.CreateGroupRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CreateGroupRequestSerializer implements JsonSerializer<CreateGroupRequest> {
    public static final JsonSerializer<CreateGroupRequest> INSTANCE = new CreateGroupRequestSerializer();
    private final CreateGroupRequestFieldSerializer mFieldSerializer = new CreateGroupRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CreateGroupRequestFieldSerializer implements JsonFieldSerializer<CreateGroupRequest> {
        CreateGroupRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((CreateGroupRequestFieldSerializer) ((CreateGroupRequest) obj), jsonGenerator);
        }

        public <U extends CreateGroupRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getName() != null) {
                jsonGenerator.writeFieldName("name");
                SimpleSerializers.serializeString(u.getName(), jsonGenerator);
            }
            if (u.getDescription() != null) {
                jsonGenerator.writeFieldName("description");
                SimpleSerializers.serializeString(u.getDescription(), jsonGenerator);
            }
            if (u.getLang() != null) {
                jsonGenerator.writeFieldName("lang");
                SimpleSerializers.serializeString(u.getLang(), jsonGenerator);
            }
            if (u.getKind() != null) {
                jsonGenerator.writeFieldName(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME);
                SimpleSerializers.serializeString(u.getKind(), jsonGenerator);
            }
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(CreateGroupRequest createGroupRequest, JsonGenerator jsonGenerator) throws IOException {
        if (createGroupRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((CreateGroupRequestFieldSerializer) createGroupRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
