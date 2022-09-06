package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.BulkGetFaceClusterRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleListSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class BulkGetFaceClusterRequestSerializer implements JsonSerializer<BulkGetFaceClusterRequest> {
    public static final JsonSerializer<BulkGetFaceClusterRequest> INSTANCE = new BulkGetFaceClusterRequestSerializer();
    private final BulkFaceClusterRequestFieldSerializer mFieldSerializer = new BulkFaceClusterRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class BulkFaceClusterRequestFieldSerializer implements JsonFieldSerializer<BulkGetFaceClusterRequest> {
        BulkFaceClusterRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((BulkFaceClusterRequestFieldSerializer) ((BulkGetFaceClusterRequest) obj), jsonGenerator);
        }

        public <U extends BulkGetFaceClusterRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("clusterIdList");
            SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getClusterIdList(), jsonGenerator);
        }
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(BulkGetFaceClusterRequest bulkGetFaceClusterRequest, JsonGenerator jsonGenerator) throws IOException {
        if (bulkGetFaceClusterRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((BulkFaceClusterRequestFieldSerializer) bulkGetFaceClusterRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
