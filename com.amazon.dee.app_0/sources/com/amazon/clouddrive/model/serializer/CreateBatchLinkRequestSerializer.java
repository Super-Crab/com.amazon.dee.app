package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.CreateBatchLinkRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class CreateBatchLinkRequestSerializer implements JsonSerializer<CreateBatchLinkRequest> {
    public static final JsonSerializer<CreateBatchLinkRequest> INSTANCE = new CreateBatchLinkRequestSerializer();
    private final BatchNodeRequestFieldSerializer mFieldSerializer = new BatchNodeRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class BatchNodeRequestFieldSerializer implements JsonFieldSerializer<CreateBatchLinkRequest> {
        BatchNodeRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((BatchNodeRequestFieldSerializer) ((CreateBatchLinkRequest) obj), jsonGenerator);
        }

        public <U extends CreateBatchLinkRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getNodeIds() != null) {
                jsonGenerator.writeFieldName("nodeIds");
                SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getNodeIds(), jsonGenerator);
            }
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(CreateBatchLinkRequest createBatchLinkRequest, JsonGenerator jsonGenerator) throws IOException {
        if (createBatchLinkRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((BatchNodeRequestFieldSerializer) createBatchLinkRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
