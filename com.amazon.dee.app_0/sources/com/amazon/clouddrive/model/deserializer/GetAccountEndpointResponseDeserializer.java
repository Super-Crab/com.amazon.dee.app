package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetAccountEndpointResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetAccountEndpointResponseDeserializer implements JsonDeserializer<GetAccountEndpointResponse> {
    public static final JsonDeserializer<GetAccountEndpointResponse> INSTANCE = new GetAccountEndpointResponseDeserializer();
    private final GetAccountEndpointResponseFieldDeserializer mFieldHandler = new GetAccountEndpointResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetAccountEndpointResponseFieldDeserializer implements JsonFieldDeserializer<GetAccountEndpointResponse> {
        GetAccountEndpointResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetAccountEndpointResponse) obj));
        }

        public <U extends GetAccountEndpointResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("contentUrl".equals(str)) {
                u.setContentUrl(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("marketplaceAtSignup".equals(str)) {
                u.setMarketplaceAtSignup(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("countryAtSignup".equals(str)) {
                u.setCountryAtSignup(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("customerExists".equals(str)) {
                u.setCustomerExists(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser));
                return true;
            } else if ("metadataUrl".equals(str)) {
                u.setMetadataUrl(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("region".equals(str)) {
                u.setRegion(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"retailUrl".equals(str)) {
                return false;
            } else {
                u.setRetailUrl(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GetAccountEndpointResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetAccountEndpointResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetAccountEndpointResponse getAccountEndpointResponse = new GetAccountEndpointResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getAccountEndpointResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getAccountEndpointResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
