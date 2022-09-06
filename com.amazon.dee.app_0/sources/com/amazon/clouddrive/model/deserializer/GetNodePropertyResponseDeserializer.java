package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetNodePropertyResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetNodePropertyResponseDeserializer implements JsonDeserializer<GetNodePropertyResponse> {
    public static final JsonDeserializer<GetNodePropertyResponse> INSTANCE = new GetNodePropertyResponseDeserializer();
    private final GetNodePropertyResponseFieldDeserializer mFieldHandler = new GetNodePropertyResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetNodePropertyResponseFieldDeserializer implements JsonFieldDeserializer<GetNodePropertyResponse> {
        GetNodePropertyResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetNodePropertyResponse) obj));
        }

        public <U extends GetNodePropertyResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("value".equals(str)) {
                u.setValue(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"key".equals(str)) {
                return false;
            } else {
                u.setKey(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GetNodePropertyResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetNodePropertyResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetNodePropertyResponse getNodePropertyResponse = new GetNodePropertyResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getNodePropertyResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getNodePropertyResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
