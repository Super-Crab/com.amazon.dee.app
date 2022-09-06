package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.model.DeleteNodePropertyRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PurgePropertyRequestSerializer implements JsonSerializer<DeleteNodePropertyRequest> {
    public static final JsonSerializer<DeleteNodePropertyRequest> INSTANCE = new PurgePropertyRequestSerializer();
    private final PurgePropertyRequestFieldSerializer mFieldSerializer = new PurgePropertyRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class PurgePropertyRequestFieldSerializer implements JsonFieldSerializer<DeleteNodePropertyRequest> {
        PurgePropertyRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((PurgePropertyRequestFieldSerializer) ((DeleteNodePropertyRequest) obj), jsonGenerator);
        }

        public <U extends DeleteNodePropertyRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("key");
            SimpleSerializers.serializeString(u.getKey(), jsonGenerator);
            jsonGenerator.writeFieldName("id");
            SimpleSerializers.serializeString(u.getId(), jsonGenerator);
            jsonGenerator.writeFieldName(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
            SimpleSerializers.serializeString(u.getOwner(), jsonGenerator);
        }
    }

    private PurgePropertyRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(DeleteNodePropertyRequest deleteNodePropertyRequest, JsonGenerator jsonGenerator) throws IOException {
        if (deleteNodePropertyRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((PurgePropertyRequestFieldSerializer) deleteNodePropertyRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
