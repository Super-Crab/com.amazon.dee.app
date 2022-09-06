package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.ListChildrenRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ListChildrenRequestSerializer implements JsonSerializer<ListChildrenRequest> {
    public static final JsonSerializer<ListChildrenRequest> INSTANCE = new ListChildrenRequestSerializer();
    private final ListChildrenRequestFieldSerializer mFieldSerializer = new ListChildrenRequestFieldSerializer();

    /* loaded from: classes11.dex */
    public static class ListChildrenRequestFieldSerializer implements JsonFieldSerializer<ListChildrenRequest> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ListChildrenRequestFieldSerializer) ((ListChildrenRequest) obj), jsonGenerator);
        }

        public <U extends ListChildrenRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("assetMapping");
            SimpleSerializers.serializeString(u.getAssetMapping(), jsonGenerator);
            jsonGenerator.writeFieldName("id");
            SimpleSerializers.serializeString(u.getId(), jsonGenerator);
            jsonGenerator.writeFieldName("tempLink");
            SimpleSerializers.serializeBoolean(u.getTempLink(), jsonGenerator);
        }
    }

    private ListChildrenRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ListChildrenRequest listChildrenRequest, JsonGenerator jsonGenerator) throws IOException {
        if (listChildrenRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ListChildrenRequestFieldSerializer) listChildrenRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
