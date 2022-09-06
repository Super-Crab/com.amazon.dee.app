package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.QueryFilter;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class QueryFilterSerializer implements JsonSerializer<QueryFilter> {
    public static final JsonSerializer<QueryFilter> INSTANCE = new QueryFilterSerializer();

    private QueryFilterSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(QueryFilter queryFilter, JsonGenerator jsonGenerator) throws IOException {
        if (queryFilter == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeFieldName(queryFilter.getFilterType());
        SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(queryFilter.getValues(), jsonGenerator);
    }
}
