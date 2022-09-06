package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.GetNodePropertyRequest;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetNodePropertyRequestSerializer implements JsonSerializer<GetNodePropertyRequest> {
    public static final JsonSerializer<GetNodePropertyRequest> INSTANCE = new GetNodePropertyRequestSerializer();
    private final GetNodePropertyRequestFieldSerializer mFieldSerializer = new GetNodePropertyRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetNodePropertyRequestFieldSerializer implements JsonFieldSerializer<GetNodePropertyRequest> {
        GetNodePropertyRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetNodePropertyRequestFieldSerializer) ((GetNodePropertyRequest) obj), jsonGenerator);
        }

        public <U extends GetNodePropertyRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("key");
            SimpleSerializers.serializeString(u.getKey(), jsonGenerator);
            jsonGenerator.writeFieldName("id");
            SimpleSerializers.serializeString(u.getId(), jsonGenerator);
            jsonGenerator.writeFieldName(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
            SimpleSerializers.serializeString(u.getOwner(), jsonGenerator);
        }
    }

    private GetNodePropertyRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetNodePropertyRequest getNodePropertyRequest, JsonGenerator jsonGenerator) throws IOException {
        if (getNodePropertyRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((GetNodePropertyRequestFieldSerializer) getNodePropertyRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
