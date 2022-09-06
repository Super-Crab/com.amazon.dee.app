package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetSplashResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetSplashResponseDeserializer implements JsonDeserializer<GetSplashResponse> {
    public static final JsonDeserializer<GetSplashResponse> INSTANCE = new GetSplashResponseDeserializer();
    private final GetSplashResponseFieldDeserializer mFieldHandler = new GetSplashResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetSplashResponseFieldDeserializer implements JsonFieldDeserializer<GetSplashResponse> {
        GetSplashResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetSplashResponse) obj));
        }

        public <U extends GetSplashResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("splashText".equals(str)) {
                u.setSplashText(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("splashId".equals(str)) {
                u.setSplashId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"url".equals(str)) {
                return false;
            } else {
                u.setUrl(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GetSplashResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetSplashResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetSplashResponse getSplashResponse = new GetSplashResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getSplashResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getSplashResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
