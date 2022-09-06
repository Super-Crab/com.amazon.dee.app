package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.ListNodesRequest;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ListNodeRequestSerializer implements JsonSerializer<ListNodesRequest> {
    public static final JsonSerializer<ListNodesRequest> INSTANCE = new ListNodeRequestSerializer();
    private final ListNodeRequestFieldSerializer mFieldSerializer = new ListNodeRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ListNodeRequestFieldSerializer implements JsonFieldSerializer<ListNodesRequest> {
        ListNodeRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ListNodeRequestFieldSerializer) ((ListNodesRequest) obj), jsonGenerator);
        }

        public <U extends ListNodesRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("tempLink");
            SimpleSerializers.serializeBoolean(u.getTempLink(), jsonGenerator);
            jsonGenerator.writeFieldName("assetMapping");
            SimpleSerializers.serializeString(u.getAssetMapping(), jsonGenerator);
            if (u.getDedupeContext() == null || u.getDedupeContext().length() <= 0) {
                return;
            }
            jsonGenerator.writeFieldName("dedupeContext");
            SimpleSerializers.serializeString(u.getDedupeContext(), jsonGenerator);
        }
    }

    private ListNodeRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ListNodesRequest listNodesRequest, JsonGenerator jsonGenerator) throws IOException {
        if (listNodesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ListNodeRequestFieldSerializer) listNodesRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
