package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.ListNodePropertiesResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ListNodePropertiesResponseSerializer implements JsonSerializer<ListNodePropertiesResponse> {
    public static final JsonSerializer<ListNodePropertiesResponse> INSTANCE = new ListNodePropertiesResponseSerializer();
    private final ListNodePropertiesResponseFieldSerializer mFieldSerializer = new ListNodePropertiesResponseFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ListNodePropertiesResponseFieldSerializer implements JsonFieldSerializer<ListNodePropertiesResponse> {
        ListNodePropertiesResponseFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ListNodePropertiesResponseFieldSerializer) ((ListNodePropertiesResponse) obj), jsonGenerator);
        }

        public <U extends ListNodePropertiesResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("data");
            PropertyMapSerializer.INSTANCE.serialize(u.getData(), jsonGenerator);
            jsonGenerator.writeFieldName("count");
            SimpleSerializers.serializePrimitiveLong(u.getCount(), jsonGenerator);
        }
    }

    private ListNodePropertiesResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ListNodePropertiesResponse listNodePropertiesResponse, JsonGenerator jsonGenerator) throws IOException {
        if (listNodePropertiesResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ListNodePropertiesResponseFieldSerializer) listNodePropertiesResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
