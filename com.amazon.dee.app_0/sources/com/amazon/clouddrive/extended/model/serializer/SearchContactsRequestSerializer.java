package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.SearchContactsRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleListSerializers;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SearchContactsRequestSerializer implements JsonSerializer<SearchContactsRequest> {
    public static final JsonSerializer<SearchContactsRequest> INSTANCE = new SearchContactsRequestSerializer();
    private final SearchContactsRequestFieldSerializer mFieldSerializer = new SearchContactsRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SearchContactsRequestFieldSerializer implements JsonFieldSerializer<SearchContactsRequest> {
        SearchContactsRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((SearchContactsRequestFieldSerializer) ((SearchContactsRequest) obj), jsonGenerator);
        }

        public <U extends SearchContactsRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            if (u instanceof SearchContactsRequest) {
                if (u.getTerm() != null) {
                    jsonGenerator.writeFieldName("term");
                    SimpleSerializers.serializeString(u.getTerm(), jsonGenerator);
                }
                if (u.getNextToken() != null) {
                    jsonGenerator.writeFieldName("nextToken");
                    SimpleSerializers.serializeString(u.getNextToken(), jsonGenerator);
                }
                if (u.getMaxResults() != null) {
                    jsonGenerator.writeFieldName("maxResults");
                    SimpleSerializers.serializeInteger(u.getMaxResults(), jsonGenerator);
                }
                if (u.getKindFilter() != null) {
                    jsonGenerator.writeFieldName("kindFilter");
                    SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getKindFilter(), jsonGenerator);
                }
                if (u.getGroupFilter() != null) {
                    jsonGenerator.writeFieldName("groupFilter");
                    SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getGroupFilter(), jsonGenerator);
                }
                if (u.getMemberFilter() != null) {
                    jsonGenerator.writeFieldName("memberFilter");
                    SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getMemberFilter(), jsonGenerator);
                }
                if (u.getInviteFilter() == null) {
                    return;
                }
                jsonGenerator.writeFieldName("inviteFilter");
                SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(u.getInviteFilter(), jsonGenerator);
            }
        }
    }

    private SearchContactsRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(SearchContactsRequest searchContactsRequest, JsonGenerator jsonGenerator) throws IOException {
        if (searchContactsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((SearchContactsRequestFieldSerializer) searchContactsRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
