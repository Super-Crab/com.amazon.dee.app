package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.QueryAndBlock;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class QueryAndBlockDeserializer implements JsonDeserializer<QueryAndBlock> {
    public static final JsonDeserializer<QueryAndBlock> INSTANCE = new QueryAndBlockDeserializer();

    private QueryAndBlockDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public QueryAndBlock mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            QueryAndBlock queryAndBlock = new QueryAndBlock();
            ArrayList arrayList = new ArrayList();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (!jsonParser.isClosed()) {
                    arrayList.add(QueryFilterDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                } else {
                    throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                }
            }
            queryAndBlock.setFilters(arrayList);
            return queryAndBlock;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
