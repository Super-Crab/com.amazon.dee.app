package com.amazon.clouddrive.model.serializer;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.clouddrive.model.GetChangesRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetChangesRequestSerializer implements JsonSerializer<GetChangesRequest> {
    public static final JsonSerializer<GetChangesRequest> INSTANCE = new GetChangesRequestSerializer();
    private final GetChangesRequestFieldSerializer mFieldSerializer = new GetChangesRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class GetChangesRequestFieldSerializer implements JsonFieldSerializer<GetChangesRequest> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetChangesRequestFieldSerializer) ((GetChangesRequest) obj), jsonGenerator);
        }

        public <U extends GetChangesRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("includePurged");
            SimpleSerializers.serializeString(u.getIncludePurged(), jsonGenerator);
            jsonGenerator.writeFieldName("chunkSize");
            SimpleSerializers.serializeInteger(u.getChunkSize(), jsonGenerator);
            jsonGenerator.writeFieldName("checkpoint");
            SimpleSerializers.serializeString(u.getCheckpoint(), jsonGenerator);
            jsonGenerator.writeFieldName("maxNodes");
            SimpleSerializers.serializeInteger(u.getMaxNodes(), jsonGenerator);
            String num = u.getLimit() != null ? u.getLimit().toString() : null;
            jsonGenerator.writeFieldName(MetricsUtil.LegacyMetricTypes.LIMIT);
            SimpleSerializers.serializeString(num, jsonGenerator);
        }
    }

    private GetChangesRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetChangesRequest getChangesRequest, JsonGenerator jsonGenerator) throws IOException {
        if (getChangesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((GetChangesRequestFieldSerializer) getChangesRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
