package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetAccountInfoResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetAccountInfoResponseDeserializer implements JsonDeserializer<GetAccountInfoResponse> {
    public static final JsonDeserializer<GetAccountInfoResponse> INSTANCE = new GetAccountInfoResponseDeserializer();
    private final GetAccountInfoResponseFieldDeserializer mFieldHandler = new GetAccountInfoResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetAccountInfoResponseFieldDeserializer implements JsonFieldDeserializer<GetAccountInfoResponse> {
        GetAccountInfoResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetAccountInfoResponse) obj));
        }

        public <U extends GetAccountInfoResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("termsOfUse".equals(str)) {
                u.setTermsOfUse(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"status".equals(str)) {
                return false;
            } else {
                u.setStatus(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GetAccountInfoResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetAccountInfoResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetAccountInfoResponse getAccountInfoResponse = new GetAccountInfoResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getAccountInfoResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getAccountInfoResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
