package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.MediaTypeUploadStatus;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class MediaTypeUploadStatusDeserializer implements JsonDeserializer<MediaTypeUploadStatus> {
    public static final JsonDeserializer<MediaTypeUploadStatus> INSTANCE = new MediaTypeUploadStatusDeserializer();

    private MediaTypeUploadStatusDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public MediaTypeUploadStatus mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            MediaTypeUploadStatus mediaTypeUploadStatus = new MediaTypeUploadStatus();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("uploadQueueSize".equals(currentName)) {
                            mediaTypeUploadStatus.setUploadQueueSize(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                        } else if ("isAutoSaveActive".equals(currentName)) {
                            mediaTypeUploadStatus.setIsAutoSaveActive(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser));
                        } else if ("backlogSize".equals(currentName)) {
                            mediaTypeUploadStatus.setBacklogSize(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                        } else {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return mediaTypeUploadStatus;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
