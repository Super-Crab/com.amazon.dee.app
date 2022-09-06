package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.BulkTrashRestoreNodesRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class BulkTrashRestoreNodesRequestSerializer implements JsonSerializer<BulkTrashRestoreNodesRequest> {
    public static final JsonSerializer<BulkTrashRestoreNodesRequest> INSTANCE = new BulkTrashRestoreNodesRequestSerializer();
    private BulkAddRemoveRequestFieldSerializer mFieldSerializer = new BulkAddRemoveRequestFieldSerializer();
    private BulkTrashRestoreNodesRequestFieldSerializer mTrashRestoreFieldSerializer = new BulkTrashRestoreNodesRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class BulkTrashRestoreNodesRequestFieldSerializer implements JsonFieldSerializer<BulkTrashRestoreNodesRequest> {
        BulkTrashRestoreNodesRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((BulkTrashRestoreNodesRequestFieldSerializer) ((BulkTrashRestoreNodesRequest) obj), jsonGenerator);
        }

        public <U extends BulkTrashRestoreNodesRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u.getDedupeContext() == null || u.getDedupeContext().length() <= 0) {
                return;
            }
            jsonGenerator.writeFieldName("dedupeContext");
            SimpleSerializers.serializeString(u.getDedupeContext(), jsonGenerator);
        }
    }

    private BulkTrashRestoreNodesRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(BulkTrashRestoreNodesRequest bulkTrashRestoreNodesRequest, JsonGenerator jsonGenerator) throws IOException {
        if (bulkTrashRestoreNodesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((BulkAddRemoveRequestFieldSerializer) bulkTrashRestoreNodesRequest, jsonGenerator);
        this.mTrashRestoreFieldSerializer.serializeFields((BulkTrashRestoreNodesRequestFieldSerializer) bulkTrashRestoreNodesRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
