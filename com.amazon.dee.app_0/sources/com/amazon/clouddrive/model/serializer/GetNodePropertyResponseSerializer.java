package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.GetNodePropertyResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetNodePropertyResponseSerializer implements JsonSerializer<GetNodePropertyResponse> {
    public static final JsonSerializer<GetNodePropertyResponse> INSTANCE = new GetNodePropertyResponseSerializer();
    private final GetNodePropertyResponseFieldSerializer mFieldSerializer = new GetNodePropertyResponseFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetNodePropertyResponseFieldSerializer implements JsonFieldSerializer<GetNodePropertyResponse> {
        GetNodePropertyResponseFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetNodePropertyResponseFieldSerializer) ((GetNodePropertyResponse) obj), jsonGenerator);
        }

        public <U extends GetNodePropertyResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("value");
            SimpleSerializers.serializeString(u.getValue(), jsonGenerator);
            jsonGenerator.writeFieldName("key");
            SimpleSerializers.serializeString(u.getKey(), jsonGenerator);
        }
    }

    private GetNodePropertyResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetNodePropertyResponse getNodePropertyResponse, JsonGenerator jsonGenerator) throws IOException {
        if (getNodePropertyResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((GetNodePropertyResponseFieldSerializer) getNodePropertyResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
