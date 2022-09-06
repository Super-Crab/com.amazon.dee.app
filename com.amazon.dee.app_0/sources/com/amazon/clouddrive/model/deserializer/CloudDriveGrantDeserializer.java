package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.CloudDriveGrant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CloudDriveGrantDeserializer implements JsonDeserializer<CloudDriveGrant> {
    public static final JsonDeserializer<CloudDriveGrant> INSTANCE = new CloudDriveGrantDeserializer();
    private final CloudDriveGrantFieldDeserializer mFieldHandler = new CloudDriveGrantFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CloudDriveGrantFieldDeserializer implements JsonFieldDeserializer<CloudDriveGrant> {
        CloudDriveGrantFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((CloudDriveGrant) obj));
        }

        public <U extends CloudDriveGrant> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("grantStorage".equals(str)) {
                u.setGrantStorage(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("grantId".equals(str)) {
                u.setGrantId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("isStackable".equals(str)) {
                u.setStackable(SimpleDeserializers.deserializeBoolean(jsonParser).booleanValue());
                return true;
            } else if (!"expiration".equals(str)) {
                return false;
            } else {
                u.setExpiration(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private CloudDriveGrantDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CloudDriveGrant mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CloudDriveGrant cloudDriveGrant = new CloudDriveGrant();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) cloudDriveGrant)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return cloudDriveGrant;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
