package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.SourceInfoExtendedResponse;
import com.amazon.clouddrive.model.deserializer.BaseDeviceInfoDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.MediaTypeUsageMapDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.clouddrive.model.deserializer.SourceStatusDeserializer;
import com.amazon.clouddrive.model.deserializer.VersionHistoryMapDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class SourceInfoExtendedResponseDeserializer implements JsonDeserializer<SourceInfoExtendedResponse> {
    public static final JsonDeserializer<SourceInfoExtendedResponse> INSTANCE = new SourceInfoExtendedResponseDeserializer();

    private SourceInfoExtendedResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public SourceInfoExtendedResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            SourceInfoExtendedResponse sourceInfoExtendedResponse = new SourceInfoExtendedResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("sourceUsage".equals(currentName)) {
                            sourceInfoExtendedResponse.setSourceUsage(MediaTypeUsageMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("device".equals(currentName)) {
                            sourceInfoExtendedResponse.setDevice(BaseDeviceInfoDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("sourceStatus".equals(currentName)) {
                            sourceInfoExtendedResponse.setSourceStatus(SourceStatusDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("lastModifiedTime".equals(currentName)) {
                            sourceInfoExtendedResponse.setLastModifiedTime(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("sourceApplicationName".equals(currentName)) {
                            sourceInfoExtendedResponse.setSourceApplicationName(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("creationTime".equals(currentName)) {
                            sourceInfoExtendedResponse.setCreationTime(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("sourceId".equals(currentName)) {
                            sourceInfoExtendedResponse.setSourceId(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("sourceVersionHistory".equals(currentName)) {
                            sourceInfoExtendedResponse.setSourceVersionHistory(VersionHistoryMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("lastSeenTime".equals(currentName)) {
                            sourceInfoExtendedResponse.setLastSeenTime(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("pushEndpoint".equals(currentName)) {
                            sourceInfoExtendedResponse.setPushEndpoint(SimpleDeserializers.deserializeBoolean(jsonParser));
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
            return sourceInfoExtendedResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
