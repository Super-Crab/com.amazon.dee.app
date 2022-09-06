package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.SearchAggregation;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class SearchKeyResponseAggregationsDeserializer extends AbstractDeserializer<Map<String, List<SearchAggregation>>, Map<String, List<SearchAggregation>>> {
    public static final JsonDeserializer<Map<String, List<SearchAggregation>>> INSTANCE = new SearchKeyResponseAggregationsDeserializer();
    private static final JsonDeserializer<List<SearchAggregation>> AGGREGATION_LIST_DESERIALIZER = new ListDeserializer(SearchAggregationDeserializer.INSTANCE);

    /* loaded from: classes11.dex */
    static class SearchKeyResponseAggregationsFieldDeserializer implements JsonFieldDeserializer<Map<String, List<SearchAggregation>>> {
        SearchKeyResponseAggregationsFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Map) obj));
        }

        public <U extends Map<String, List<SearchAggregation>>> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            u.put(str, SearchKeyResponseAggregationsDeserializer.AGGREGATION_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
            return true;
        }
    }

    private SearchKeyResponseAggregationsDeserializer() {
        super(new SearchKeyResponseAggregationsFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue  reason: collision with other method in class */
    public Map<String, List<SearchAggregation>> mo3156createValue() {
        return new HashMap();
    }
}
