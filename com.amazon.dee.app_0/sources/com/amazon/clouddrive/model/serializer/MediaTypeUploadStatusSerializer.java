package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.MediaTypeUploadStatus;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class MediaTypeUploadStatusSerializer implements JsonSerializer<MediaTypeUploadStatus> {
    public static final JsonSerializer<MediaTypeUploadStatus> INSTANCE = new MediaTypeUploadStatusSerializer();

    private MediaTypeUploadStatusSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(MediaTypeUploadStatus mediaTypeUploadStatus, JsonGenerator jsonGenerator) throws IOException {
        if (mediaTypeUploadStatus == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("uploadQueueSize");
        SimpleSerializers.serializePrimitiveLong(mediaTypeUploadStatus.getUploadQueueSize(), jsonGenerator);
        jsonGenerator.writeFieldName("isAutoSaveActive");
        SimpleSerializers.serializePrimitiveBoolean(mediaTypeUploadStatus.isIsAutoSaveActive(), jsonGenerator);
        jsonGenerator.writeFieldName("backlogSize");
        SimpleSerializers.serializePrimitiveLong(mediaTypeUploadStatus.getBacklogSize(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
