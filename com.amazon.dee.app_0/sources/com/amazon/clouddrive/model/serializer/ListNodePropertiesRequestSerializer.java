package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.ListNodePropertiesRequest;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ListNodePropertiesRequestSerializer implements JsonSerializer<ListNodePropertiesRequest> {
    public static final JsonSerializer<ListNodePropertiesRequest> INSTANCE = new ListNodePropertiesRequestSerializer();
    private final ListNodePropertiesRequestFieldSerializer mFieldSerializer = new ListNodePropertiesRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ListNodePropertiesRequestFieldSerializer implements JsonFieldSerializer<ListNodePropertiesRequest> {
        ListNodePropertiesRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ListNodePropertiesRequestFieldSerializer) ((ListNodePropertiesRequest) obj), jsonGenerator);
        }

        public <U extends ListNodePropertiesRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
            SimpleSerializers.serializeString(u.getOwner(), jsonGenerator);
            jsonGenerator.writeFieldName("id");
            SimpleSerializers.serializeString(u.getId(), jsonGenerator);
        }
    }

    private ListNodePropertiesRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ListNodePropertiesRequest listNodePropertiesRequest, JsonGenerator jsonGenerator) throws IOException {
        if (listNodePropertiesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ListNodePropertiesRequestFieldSerializer) listNodePropertiesRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
