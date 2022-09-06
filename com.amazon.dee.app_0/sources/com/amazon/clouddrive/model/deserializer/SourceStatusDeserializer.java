package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.SourceStatus;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class SourceStatusDeserializer implements JsonDeserializer<SourceStatus> {
    public static final JsonDeserializer<SourceStatus> INSTANCE = new SourceStatusDeserializer();

    private SourceStatusDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public SourceStatus mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            SourceStatus sourceStatus = new SourceStatus();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("uploadStatus".equals(currentName)) {
                            sourceStatus.setUploadStatus(MediaTypeUploadStatusMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("isUploading".equals(currentName)) {
                            sourceStatus.setIsUploading(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser));
                        } else if ("cacheSize".equals(currentName)) {
                            sourceStatus.setCacheSize(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                        } else if ("isDownloading".equals(currentName)) {
                            sourceStatus.setIsDownloading(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser));
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
            return sourceStatus;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
