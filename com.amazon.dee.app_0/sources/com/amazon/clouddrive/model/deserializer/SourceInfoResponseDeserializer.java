package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.SourceInfoResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class SourceInfoResponseDeserializer implements JsonDeserializer<SourceInfoResponse> {
    public static final JsonDeserializer<SourceInfoResponse> INSTANCE = new SourceInfoResponseDeserializer();

    private SourceInfoResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public SourceInfoResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            SourceInfoResponse sourceInfoResponse = new SourceInfoResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("sourceUsage".equals(currentName)) {
                            sourceInfoResponse.setSourceUsage(MediaTypeUsageMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("device".equals(currentName)) {
                            sourceInfoResponse.setDevice(BaseDeviceInfoDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("sourceStatus".equals(currentName)) {
                            sourceInfoResponse.setSourceStatus(SourceStatusDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("lastModifiedTime".equals(currentName)) {
                            sourceInfoResponse.setLastModifiedTime(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("sourceApplicationName".equals(currentName)) {
                            sourceInfoResponse.setSourceApplicationName(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("creationTime".equals(currentName)) {
                            sourceInfoResponse.setCreationTime(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("sourceId".equals(currentName)) {
                            sourceInfoResponse.setSourceId(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("sourceVersionHistory".equals(currentName)) {
                            sourceInfoResponse.setSourceVersionHistory(VersionHistoryMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("lastSeenTime".equals(currentName)) {
                            sourceInfoResponse.setLastSeenTime(SimpleDeserializers.deserializeString(jsonParser));
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
            return sourceInfoResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
