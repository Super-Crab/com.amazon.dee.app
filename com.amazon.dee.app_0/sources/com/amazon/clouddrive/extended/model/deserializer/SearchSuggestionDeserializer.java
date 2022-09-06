package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.SearchSuggestion;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class SearchSuggestionDeserializer extends AbstractDeserializer<SearchSuggestion, SearchSuggestion> {
    public static final JsonDeserializer<SearchSuggestion> INSTANCE = new SearchSuggestionDeserializer();

    /* loaded from: classes11.dex */
    static class SearchSuggestionFieldDeserializer implements JsonFieldDeserializer<SearchSuggestion> {
        SearchSuggestionFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((SearchSuggestion) obj));
        }

        public <U extends SearchSuggestion> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("term".equals(str)) {
                u.setSuggestionKeyword(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("count".equals(str)) {
                u.setCount(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("category".equals(str)) {
                u.setSuggestionCategory(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"searchData".equals(str)) {
                return false;
            } else {
                u.setMetadata(SearchMetadataDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private SearchSuggestionDeserializer() {
        super(new SearchSuggestionFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public SearchSuggestion mo3156createValue() {
        return new SearchSuggestion();
    }
}
