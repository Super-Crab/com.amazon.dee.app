package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.BulkGetReactionSummariesRequest;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleListSerializers;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class BulkGetReactionSummariesRequestSerializer implements JsonSerializer<BulkGetReactionSummariesRequest> {
    public static JsonSerializer<BulkGetReactionSummariesRequest> INSTANCE = new BulkGetReactionSummariesRequestSerializer();

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(BulkGetReactionSummariesRequest bulkGetReactionSummariesRequest, JsonGenerator jsonGenerator) throws IOException {
        if (bulkGetReactionSummariesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        List<String> topics = bulkGetReactionSummariesRequest.getTopics();
        if (topics != null) {
            jsonGenerator.writeFieldName("topics");
            SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(topics, jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
