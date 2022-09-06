package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BatchCreateInviteResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
class BatchCreateInviteResponseInviteStatusDeserializer implements JsonDeserializer<BatchCreateInviteResponse.Invite.Status> {
    public static final JsonDeserializer<BatchCreateInviteResponse.Invite.Status> INSTANCE = new BatchCreateInviteResponseInviteStatusDeserializer();
    private static final JsonFieldDeserializer<BatchCreateInviteResponse.Invite.Status.StatusBuilder> FIELD_DESERIALIZER = new FieldDeserializer();

    /* loaded from: classes11.dex */
    private static class FieldDeserializer implements JsonFieldDeserializer<BatchCreateInviteResponse.Invite.Status.StatusBuilder> {
        private FieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((BatchCreateInviteResponse.Invite.Status.StatusBuilder) obj));
        }

        public <U extends BatchCreateInviteResponse.Invite.Status.StatusBuilder> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("state".equals(str)) {
                u.state(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
            if ("reasonCode".equals(str)) {
                u.reasonCode(SimpleDeserializers.deserializeString(jsonParser));
            }
            if (!"message".equals(str)) {
                return false;
            }
            u.message(SimpleDeserializers.deserializeString(jsonParser));
            return false;
        }
    }

    private BatchCreateInviteResponseInviteStatusDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public BatchCreateInviteResponse.Invite.Status mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            BatchCreateInviteResponse.Invite.Status.StatusBuilder builder = BatchCreateInviteResponse.Invite.Status.builder();
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
