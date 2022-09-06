package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.GetNodeRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetNodeRequestSerializer implements JsonSerializer<GetNodeRequest> {
    public static final JsonSerializer<GetNodeRequest> INSTANCE = new GetNodeRequestSerializer();
    private final GetNodeRequestFieldSerializer mFieldSerializer = new GetNodeRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class GetNodeRequestFieldSerializer implements JsonFieldSerializer<GetNodeRequest> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetNodeRequestFieldSerializer) ((GetNodeRequest) obj), jsonGenerator);
        }

        public <U extends GetNodeRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("id");
            SimpleSerializers.serializeString(u.getId(), jsonGenerator);
            jsonGenerator.writeFieldName("fields");
            SimpleSerializers.serializeString(u.getFields(), jsonGenerator);
            jsonGenerator.writeFieldName("assetMapping");
            SimpleSerializers.serializeString(u.getAssetMapping(), jsonGenerator);
            jsonGenerator.writeFieldName("tempLink");
            SimpleSerializers.serializeBoolean(u.getTempLink(), jsonGenerator);
        }
    }

    private GetNodeRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetNodeRequest getNodeRequest, JsonGenerator jsonGenerator) throws IOException {
        if (getNodeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((GetNodeRequestFieldSerializer) getNodeRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
