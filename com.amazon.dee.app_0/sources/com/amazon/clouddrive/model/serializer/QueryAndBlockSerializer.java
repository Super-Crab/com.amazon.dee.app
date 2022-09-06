package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.QueryAndBlock;
import com.amazon.clouddrive.model.QueryFilter;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class QueryAndBlockSerializer implements JsonSerializer<QueryAndBlock> {
    public static final JsonSerializer<QueryAndBlock> INSTANCE = new QueryAndBlockSerializer();

    private QueryAndBlockSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(QueryAndBlock queryAndBlock, JsonGenerator jsonGenerator) throws IOException {
        if (queryAndBlock == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        if (queryAndBlock.getFilters() == null) {
            jsonGenerator.writeNull();
        } else {
            for (QueryFilter queryFilter : queryAndBlock.getFilters()) {
                QueryFilterSerializer.INSTANCE.serialize(queryFilter, jsonGenerator);
            }
        }
        jsonGenerator.writeEndObject();
    }
}
