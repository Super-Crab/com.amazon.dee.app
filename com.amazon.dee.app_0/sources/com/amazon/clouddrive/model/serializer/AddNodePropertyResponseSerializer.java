package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.AddNodePropertyResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class AddNodePropertyResponseSerializer implements JsonSerializer<AddNodePropertyResponse> {
    public static final JsonSerializer<AddNodePropertyResponse> INSTANCE = new AddNodePropertyResponseSerializer();
    private final AddNodePropertyResponseFieldSerializer mFieldSerializer = new AddNodePropertyResponseFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class AddNodePropertyResponseFieldSerializer implements JsonFieldSerializer<AddNodePropertyResponse> {
        AddNodePropertyResponseFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((AddNodePropertyResponseFieldSerializer) ((AddNodePropertyResponse) obj), jsonGenerator);
        }

        public <U extends AddNodePropertyResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("value");
            SimpleSerializers.serializeString(u.getValue(), jsonGenerator);
            jsonGenerator.writeFieldName("location");
            SimpleSerializers.serializeString(u.getLocation(), jsonGenerator);
            jsonGenerator.writeFieldName("key");
            SimpleSerializers.serializeString(u.getKey(), jsonGenerator);
        }
    }

    private AddNodePropertyResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(AddNodePropertyResponse addNodePropertyResponse, JsonGenerator jsonGenerator) throws IOException {
        if (addNodePropertyResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((AddNodePropertyResponseFieldSerializer) addNodePropertyResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
