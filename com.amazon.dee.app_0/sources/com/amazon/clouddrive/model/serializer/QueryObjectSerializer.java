package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.QueryObject;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class QueryObjectSerializer implements JsonSerializer<QueryObject> {
    public static final JsonSerializer<QueryObject> INSTANCE = new QueryObjectSerializer();

    private QueryObjectSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(QueryObject queryObject, JsonGenerator jsonGenerator) throws IOException {
        if (queryObject == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("excludedIds");
        ObjectIdListSerializer.INSTANCE.serialize(queryObject.getExcludedIds(), jsonGenerator);
        jsonGenerator.writeFieldName("include");
        QueryAndBlockListSerializer.INSTANCE.serialize(queryObject.getInclude(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
