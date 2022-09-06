package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.MediaTypeUploadStatus;
import java.io.IOException;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class MediaTypeUploadStatusMapSerializer implements JsonSerializer<Map<String, MediaTypeUploadStatus>> {
    public static final JsonSerializer<Map<String, MediaTypeUploadStatus>> INSTANCE = new MediaTypeUploadStatusMapSerializer();

    private MediaTypeUploadStatusMapSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(Map<String, MediaTypeUploadStatus> map, JsonGenerator jsonGenerator) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, MediaTypeUploadStatus> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            MediaTypeUploadStatusSerializer.INSTANCE.serialize(entry.getValue(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
