package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.QueryFilter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class QueryFilterDeserializer implements JsonDeserializer<QueryFilter> {
    public static final JsonDeserializer<QueryFilter> INSTANCE = new QueryFilterDeserializer();

    private QueryFilterDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public QueryFilter mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
            QueryFilter queryFilter = new QueryFilter(jsonParser.getCurrentName());
            jsonParser.nextToken();
            if (!jsonParser.isClosed()) {
                queryFilter.setValues(SimpleListDeserializers.STRING_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return queryFilter;
            }
            throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
    }
}
