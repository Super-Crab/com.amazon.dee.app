package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.ListNodesInTrashResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ListTrashResponseSerializer implements JsonSerializer<ListNodesInTrashResponse> {
    public static final JsonSerializer<ListNodesInTrashResponse> INSTANCE = new ListTrashResponseSerializer();
    private final ListTrashResponseFieldSerializer mFieldSerializer = new ListTrashResponseFieldSerializer();

    /* loaded from: classes11.dex */
    public static class ListTrashResponseFieldSerializer implements JsonFieldSerializer<ListNodesInTrashResponse> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ListTrashResponseFieldSerializer) ((ListNodesInTrashResponse) obj), jsonGenerator);
        }

        public <U extends ListNodesInTrashResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("data");
            NodeListSerializer.INSTANCE.serialize(u.getData(), jsonGenerator);
        }
    }

    private ListTrashResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ListNodesInTrashResponse listNodesInTrashResponse, JsonGenerator jsonGenerator) throws IOException {
        if (listNodesInTrashResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ListTrashResponseFieldSerializer) listNodesInTrashResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
