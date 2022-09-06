package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.SearchKeyRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SearchKeyRequestSerializer implements JsonSerializer<SearchKeyRequest> {
    public static final JsonSerializer<SearchKeyRequest> INSTANCE = new SearchKeyRequestSerializer();
    private final SearchKeyRequestFieldSerializer mFieldSerializer = new SearchKeyRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SearchKeyRequestFieldSerializer implements JsonFieldSerializer<SearchKeyRequest> {
        SearchKeyRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((SearchKeyRequestFieldSerializer) ((SearchKeyRequest) obj), jsonGenerator);
        }

        public <U extends SearchKeyRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("tempLink");
            SimpleSerializers.serializeBoolean(u.getTempLink(), jsonGenerator);
            jsonGenerator.writeFieldName("assetMapping");
            SimpleSerializers.serializeString(u.getAssetMapping(), jsonGenerator);
            jsonGenerator.writeFieldName("groupByForTime");
            SimpleSerializers.serializeString(u.getGroupByForTime(), jsonGenerator);
            if (u.getDedupeContext() == null || u.getDedupeContext().length() <= 0) {
                return;
            }
            jsonGenerator.writeFieldName("dedupeContext");
            SimpleSerializers.serializeString(u.getDedupeContext(), jsonGenerator);
        }
    }

    private SearchKeyRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(SearchKeyRequest searchKeyRequest, JsonGenerator jsonGenerator) throws IOException {
        if (searchKeyRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((SearchKeyRequestFieldSerializer) searchKeyRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
