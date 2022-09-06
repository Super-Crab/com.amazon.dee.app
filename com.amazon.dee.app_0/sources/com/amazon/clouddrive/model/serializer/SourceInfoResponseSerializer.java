package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.SourceInfoResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SourceInfoResponseSerializer implements JsonSerializer<SourceInfoResponse> {
    public static final JsonSerializer<SourceInfoResponse> INSTANCE = new SourceInfoResponseSerializer();

    private SourceInfoResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(SourceInfoResponse sourceInfoResponse, JsonGenerator jsonGenerator) throws IOException {
        if (sourceInfoResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("sourceUsage");
        MediaTypeUsageMapSerializer.INSTANCE.serialize(sourceInfoResponse.getSourceUsage(), jsonGenerator);
        jsonGenerator.writeFieldName("device");
        BaseDeviceInfoSerializer.INSTANCE.serialize(sourceInfoResponse.getDevice(), jsonGenerator);
        jsonGenerator.writeFieldName("sourceStatus");
        SourceStatusSerializer.INSTANCE.serialize(sourceInfoResponse.getSourceStatus(), jsonGenerator);
        jsonGenerator.writeFieldName("lastModifiedTime");
        SimpleSerializers.serializeString(sourceInfoResponse.getLastModifiedTime(), jsonGenerator);
        jsonGenerator.writeFieldName("sourceApplicationName");
        SimpleSerializers.serializeString(sourceInfoResponse.getSourceApplicationName(), jsonGenerator);
        jsonGenerator.writeFieldName("creationTime");
        SimpleSerializers.serializeString(sourceInfoResponse.getCreationTime(), jsonGenerator);
        jsonGenerator.writeFieldName("sourceId");
        SimpleSerializers.serializeString(sourceInfoResponse.getSourceId(), jsonGenerator);
        jsonGenerator.writeFieldName("sourceVersionHistory");
        VersionHistoryMapSerializer.INSTANCE.serialize(sourceInfoResponse.getSourceVersionHistory(), jsonGenerator);
        jsonGenerator.writeFieldName("lastSeenTime");
        SimpleSerializers.serializeString(sourceInfoResponse.getLastSeenTime(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
