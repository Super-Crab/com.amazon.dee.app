package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.CreateContactBlockResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CreateContactBlockResponseDeserializer implements JsonDeserializer<CreateContactBlockResponse> {
    public static JsonDeserializer<CreateContactBlockResponse> INSTANCE = new CreateContactBlockResponseDeserializer();
    private static final JsonFieldDeserializer<CreateContactBlockResponse.CreateContactBlockResponseBuilder> FIELD_DESERIALIZER = new CreateContactBlockResponseFieldDeserializer();

    /* loaded from: classes11.dex */
    private static class CreateContactBlockResponseFieldDeserializer implements JsonFieldDeserializer<CreateContactBlockResponse.CreateContactBlockResponseBuilder> {
        private CreateContactBlockResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((CreateContactBlockResponse.CreateContactBlockResponseBuilder) obj));
        }

        public <U extends CreateContactBlockResponse.CreateContactBlockResponseBuilder> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("customerId".equals(str)) {
                u.customerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("blockType".equals(str)) {
                u.blockType(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"name".equals(str)) {
                return false;
            } else {
                u.name(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private CreateContactBlockResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CreateContactBlockResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CreateContactBlockResponse.CreateContactBlockResponseBuilder builder = CreateContactBlockResponse.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!FIELD_DESERIALIZER.handleField(jsonParser, currentName, builder)) {
                            jsonParser.skipChildren();
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
