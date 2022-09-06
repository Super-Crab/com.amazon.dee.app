package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.SourceStatus;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SourceStatusSerializer implements JsonSerializer<SourceStatus> {
    public static final JsonSerializer<SourceStatus> INSTANCE = new SourceStatusSerializer();

    private SourceStatusSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(SourceStatus sourceStatus, JsonGenerator jsonGenerator) throws IOException {
        if (sourceStatus == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("uploadStatus");
        MediaTypeUploadStatusMapSerializer.INSTANCE.serialize(sourceStatus.getUploadStatus(), jsonGenerator);
        jsonGenerator.writeFieldName("isUploading");
        SimpleSerializers.serializePrimitiveBoolean(sourceStatus.isIsUploading(), jsonGenerator);
        jsonGenerator.writeFieldName("cacheSize");
        SimpleSerializers.serializePrimitiveLong(sourceStatus.getCacheSize(), jsonGenerator);
        jsonGenerator.writeFieldName("isDownloading");
        SimpleSerializers.serializePrimitiveBoolean(sourceStatus.isIsDownloading(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
