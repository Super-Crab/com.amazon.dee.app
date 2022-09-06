package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.BulkAddRemoveRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.NodeIdListSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
class BulkAddRemoveRequestFieldSerializer implements JsonFieldSerializer<BulkAddRemoveRequest> {
    @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
    public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
        serializeFields((BulkAddRemoveRequestFieldSerializer) ((BulkAddRemoveRequest) obj), jsonGenerator);
    }

    public <U extends BulkAddRemoveRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeFieldName("value");
        NodeIdListSerializer.INSTANCE.serialize(u.getNodeIds(), jsonGenerator);
        jsonGenerator.writeFieldName("op");
        SimpleSerializers.serializeString(u.getOp(), jsonGenerator);
        jsonGenerator.writeFieldName("resourceVersion");
        SimpleSerializers.serializeString(u.getResourceVersion(), jsonGenerator);
    }
}
