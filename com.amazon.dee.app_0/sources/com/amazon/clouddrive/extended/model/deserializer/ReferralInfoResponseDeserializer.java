package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ReferralInfoResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ReferralInfoResponseDeserializer implements JsonDeserializer<ReferralInfoResponse> {
    public static final JsonDeserializer<ReferralInfoResponse> INSTANCE = new ReferralInfoResponseDeserializer();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ReferralInfoResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ReferralInfoResponse.ReferralInfoResponseBuilder builder = ReferralInfoResponse.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("referralToken".equals(currentName)) {
                            builder.referralToken(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("referralUrl".equals(currentName)) {
                            builder.referralUrl(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("title".equals(currentName)) {
                            builder.title(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("subtitle".equals(currentName)) {
                            builder.subtitle(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("legalTitle".equals(currentName)) {
                            builder.legalTitle(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("legalPath".equals(currentName)) {
                            builder.legalPath(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("shareEmailSubject".equals(currentName)) {
                            builder.shareEmailSubject(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("shareMessageBody".equals(currentName)) {
                            builder.shareMessageBody(SimpleDeserializers.deserializeString(jsonParser));
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return builder.build();
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
