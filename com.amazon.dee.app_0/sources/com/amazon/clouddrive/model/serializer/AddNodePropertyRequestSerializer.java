package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.AddNodePropertyRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class AddNodePropertyRequestSerializer implements JsonSerializer<AddNodePropertyRequest> {
    public static final JsonSerializer<AddNodePropertyRequest> INSTANCE = new AddNodePropertyRequestSerializer();
    private final AddNodePropertyRequestFieldSerializer mFieldSerializer = new AddNodePropertyRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class AddNodePropertyRequestFieldSerializer implements JsonFieldSerializer<AddNodePropertyRequest> {
        AddNodePropertyRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((AddNodePropertyRequestFieldSerializer) ((AddNodePropertyRequest) obj), jsonGenerator);
        }

        public <U extends AddNodePropertyRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("value");
            SimpleSerializers.serializeString(u.getValue(), jsonGenerator);
        }
    }

    private AddNodePropertyRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(AddNodePropertyRequest addNodePropertyRequest, JsonGenerator jsonGenerator) throws IOException {
        if (addNodePropertyRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((AddNodePropertyRequestFieldSerializer) addNodePropertyRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
