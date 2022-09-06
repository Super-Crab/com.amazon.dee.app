package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetApplicationAccessRulesResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetApplicationAccessRulesResponseDeserializer implements JsonDeserializer<GetApplicationAccessRulesResponse> {
    public static final JsonDeserializer<GetApplicationAccessRulesResponse> INSTANCE = new GetApplicationAccessRulesResponseDeserializer();
    private final GetApplicationAccessRulesResponseFieldDeserializer mFieldHandler = new GetApplicationAccessRulesResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetApplicationAccessRulesResponseFieldDeserializer implements JsonFieldDeserializer<GetApplicationAccessRulesResponse> {
        GetApplicationAccessRulesResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetApplicationAccessRulesResponse) obj));
        }

        public <U extends GetApplicationAccessRulesResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (str.equals("accessRules")) {
                u.setAccessRules(new ListDeserializer(AccessRuleDeserializer.INSTANCE).mo3229deserialize(jsonParser));
                return true;
            } else if (!str.equals("statusCode")) {
                return false;
            } else {
                u.setStatusCode(SimpleDeserializers.deserializePrimitiveInt(jsonParser));
                return false;
            }
        }
    }

    private GetApplicationAccessRulesResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetApplicationAccessRulesResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetApplicationAccessRulesResponse getApplicationAccessRulesResponse = new GetApplicationAccessRulesResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getApplicationAccessRulesResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getApplicationAccessRulesResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
