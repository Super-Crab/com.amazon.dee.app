package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.PatchGroupCollectionRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class PatchGroupCollectionRequestSerializer implements JsonSerializer<PatchGroupCollectionRequest> {
    public static final JsonSerializer<PatchGroupCollectionRequest> INSTANCE = new PatchGroupCollectionRequestSerializer();
    private final PatchGroupCollectionRequestFieldSerializer mFieldSerializer = new PatchGroupCollectionRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class PatchGroupCollectionRequestFieldSerializer implements JsonFieldSerializer<PatchGroupCollectionRequest> {
        PatchGroupCollectionRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((PatchGroupCollectionRequestFieldSerializer) ((PatchGroupCollectionRequest) obj), jsonGenerator);
        }

        public <U extends PatchGroupCollectionRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getName() != null) {
                jsonGenerator.writeFieldName("name");
                SimpleSerializers.serializeString(u.getName(), jsonGenerator);
            }
            if (u.getCaption() != null) {
                jsonGenerator.writeFieldName("caption");
                SimpleSerializers.serializeString(u.getCaption(), jsonGenerator);
            }
            if (u.getLang() != null) {
                jsonGenerator.writeFieldName("lang");
                SimpleSerializers.serializeString(u.getLang(), jsonGenerator);
            }
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(PatchGroupCollectionRequest patchGroupCollectionRequest, JsonGenerator jsonGenerator) throws IOException {
        if (patchGroupCollectionRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((PatchGroupCollectionRequestFieldSerializer) patchGroupCollectionRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
