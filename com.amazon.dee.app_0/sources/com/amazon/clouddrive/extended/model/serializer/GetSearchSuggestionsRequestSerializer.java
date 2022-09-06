package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.clouddrive.extended.model.GetSearchSuggestionsRequest;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import com.amazon.deecomms.common.network.AppUrl;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetSearchSuggestionsRequestSerializer implements JsonSerializer<GetSearchSuggestionsRequest> {
    public static final JsonSerializer<GetSearchSuggestionsRequest> INSTANCE = new GetSearchSuggestionsRequestSerializer();
    private final SearchSuggestionsRequestFieldSerializer mFieldSerializer = new SearchSuggestionsRequestFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SearchSuggestionsRequestFieldSerializer implements JsonFieldSerializer<GetSearchSuggestionsRequest> {
        SearchSuggestionsRequestFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((SearchSuggestionsRequestFieldSerializer) ((GetSearchSuggestionsRequest) obj), jsonGenerator);
        }

        public <U extends GetSearchSuggestionsRequest> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("queryString");
            SimpleSerializers.serializeString(u.getQueryString(), jsonGenerator);
            if (u.getLimit() != null) {
                jsonGenerator.writeFieldName(MetricsUtil.LegacyMetricTypes.LIMIT);
                SimpleSerializers.serializeLong(u.getLimit(), jsonGenerator);
            }
            if (u.getFilters() != null && u.getFilters().length() > 0) {
                jsonGenerator.writeFieldName("filters");
                SimpleSerializers.serializeString(u.getFilters(), jsonGenerator);
            }
            if (u.getSort() == null || u.getSort().length() <= 0) {
                return;
            }
            jsonGenerator.writeFieldName(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER);
            SimpleSerializers.serializeString(u.getSort(), jsonGenerator);
        }
    }

    private GetSearchSuggestionsRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetSearchSuggestionsRequest getSearchSuggestionsRequest, JsonGenerator jsonGenerator) throws IOException {
        if (getSearchSuggestionsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((SearchSuggestionsRequestFieldSerializer) getSearchSuggestionsRequest, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
