package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.SearchAggregation;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class SearchAggregationDeserializer extends AbstractDeserializer<SearchAggregation, SearchAggregation> {
    public static final JsonDeserializer<SearchAggregation> INSTANCE = new SearchAggregationDeserializer();

    /* loaded from: classes11.dex */
    static class SearchAggregationFieldDeserializer implements JsonFieldDeserializer<SearchAggregation> {
        SearchAggregationFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((SearchAggregation) obj));
        }

        public <U extends SearchAggregation> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("match".equals(str)) {
                u.setMatch(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("count".equals(str)) {
                u.setCount(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if (!"searchData".equals(str)) {
                return false;
            } else {
                u.setMetadata(SearchMetadataDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private SearchAggregationDeserializer() {
        super(new SearchAggregationFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public SearchAggregation mo3156createValue() {
        return new SearchAggregation();
    }
}
