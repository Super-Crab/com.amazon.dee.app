package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.ListNodesResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ListNodeResponseSerializer implements JsonSerializer<ListNodesResponse> {
    public static final JsonSerializer<ListNodesResponse> INSTANCE = new ListNodeResponseSerializer();
    private final ListNodeResponseFieldSerializer mFieldSerializer = new ListNodeResponseFieldSerializer();

    /* loaded from: classes11.dex */
    public static class ListNodeResponseFieldSerializer implements JsonFieldSerializer<ListNodesResponse> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ListNodeResponseFieldSerializer) ((ListNodesResponse) obj), jsonGenerator);
        }

        public <U extends ListNodesResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("data");
            NodeListSerializer.INSTANCE.serialize(u.getData(), jsonGenerator);
        }
    }

    private ListNodeResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ListNodesResponse listNodesResponse, JsonGenerator jsonGenerator) throws IOException {
        if (listNodesResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ListNodeResponseFieldSerializer) listNodesResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
