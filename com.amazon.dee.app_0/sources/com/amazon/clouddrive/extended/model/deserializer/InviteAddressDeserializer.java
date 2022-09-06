package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.InviteAddress;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class InviteAddressDeserializer implements JsonDeserializer<InviteAddress> {
    public static final JsonDeserializer<InviteAddress> INSTANCE = new InviteAddressDeserializer();
    private static final JsonFieldDeserializer<InviteAddress.InviteAddressBuilder> FIELD_DESERIALIZER = new FieldDeserializer();

    /* loaded from: classes11.dex */
    private static class FieldDeserializer implements JsonFieldDeserializer<InviteAddress.InviteAddressBuilder> {
        private FieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((InviteAddress.InviteAddressBuilder) obj));
        }

        public <U extends InviteAddress.InviteAddressBuilder> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("type".equals(str)) {
                u.type(InviteAddress.Type.valueOfOrDefault(SimpleDeserializers.deserializeString(jsonParser), InviteAddress.Type.UNKNOWN));
                return true;
            } else if (!"target".equals(str)) {
                return false;
            } else {
                u.target(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public InviteAddress mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            InviteAddress.InviteAddressBuilder builder = InviteAddress.builder();
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
