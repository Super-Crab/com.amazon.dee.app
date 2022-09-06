package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.CoverObject;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CoverObjectDeserializer implements JsonDeserializer<CoverObject> {
    public static final JsonDeserializer<CoverObject> INSTANCE = new CoverObjectDeserializer();

    private CoverObjectDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CoverObject mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CoverObject coverObject = new CoverObject();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("id".equals(currentName)) {
                            coverObject.setId(SimpleDeserializers.deserializeString(jsonParser));
                        } else if (MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY.equals(currentName)) {
                            coverObject.setOwnerId(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("tempLink".equals(currentName)) {
                            coverObject.setTempLink(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("isDefault".equals(currentName)) {
                            coverObject.setDefault(SimpleDeserializers.deserializeBoolean(jsonParser));
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
            return coverObject;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
