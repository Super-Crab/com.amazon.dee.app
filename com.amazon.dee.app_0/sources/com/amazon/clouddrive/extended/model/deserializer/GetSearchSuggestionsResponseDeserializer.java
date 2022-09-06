package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetSearchSuggestionsResponse;
import com.amazon.clouddrive.extended.model.SearchSuggestion;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetSearchSuggestionsResponseDeserializer implements JsonDeserializer<GetSearchSuggestionsResponse> {
    public static final JsonDeserializer<GetSearchSuggestionsResponse> INSTANCE = new GetSearchSuggestionsResponseDeserializer();
    private static final JsonDeserializer<List<SearchSuggestion>> SUGGESTION_LIST_DESERIALIZER = new ListDeserializer(SearchSuggestionDeserializer.INSTANCE);
    private final SearchSuggestionsResponseFieldDeserializer mFieldHandler = new SearchSuggestionsResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SearchSuggestionsResponseFieldDeserializer implements JsonFieldDeserializer<GetSearchSuggestionsResponse> {
        SearchSuggestionsResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetSearchSuggestionsResponse) obj));
        }

        public <U extends GetSearchSuggestionsResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("suggestions".equals(str)) {
                u.setSearchSuggestions((List) GetSearchSuggestionsResponseDeserializer.SUGGESTION_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            }
            return false;
        }
    }

    private GetSearchSuggestionsResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetSearchSuggestionsResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetSearchSuggestionsResponse getSearchSuggestionsResponse = new GetSearchSuggestionsResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getSearchSuggestionsResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getSearchSuggestionsResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
