package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.BaseBatchNodeRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleListSerializers;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class BatchNodeRequestSerializer implements JsonSerializer<BaseBatchNodeRequest> {
    public static final JsonSerializer<BaseBatchNodeRequest> INSTANCE = new BatchNodeRequestSerializer();
    private final BatchNodeRequestFieldSerializer mFieldSerializer = new BatchNodeRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class BatchNodeRequestFieldSerializer implements JsonFieldSerializer<BaseBatchNodeRequest> {
        BatchNodeRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((BatchNodeRequestFieldSerializer) ((BaseBatchNodeRequest) obj), jsonGenerator);
        }

        public <U extends BaseBatchNodeRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getNodeIds() != null) {
                jsonGenerator.writeFieldName("nodeIds");
                SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getNodeIds(), jsonGenerator);
            }
            if (u.getGroupIds() != null) {
                jsonGenerator.writeFieldName("groupIds");
                SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getGroupIds(), jsonGenerator);
            }
            if (u.getEventKey() != null) {
                jsonGenerator.writeFieldName("eventKey");
                SimpleSerializers.serializeString(u.getEventKey(), jsonGenerator);
            }
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(BaseBatchNodeRequest baseBatchNodeRequest, JsonGenerator jsonGenerator) throws IOException {
        if (baseBatchNodeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((BatchNodeRequestFieldSerializer) baseBatchNodeRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
