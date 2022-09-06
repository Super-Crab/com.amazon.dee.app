package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.extended.model.BatchCreateInviteResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
class BatchCreateInviteResponseInviteListDeserializer extends ListDeserializer<BatchCreateInviteResponse.Invite> {
    public static final JsonDeserializer<List<BatchCreateInviteResponse.Invite>> INSTANCE = new BatchCreateInviteResponseInviteListDeserializer(InviteDeserializer.INSTANCE);

    /* loaded from: classes11.dex */
    static class InviteDeserializer implements JsonDeserializer<BatchCreateInviteResponse.Invite> {
        public static final JsonDeserializer<BatchCreateInviteResponse.Invite> INSTANCE = new InviteDeserializer();
        private static final JsonFieldDeserializer<BatchCreateInviteResponse.Invite.InviteBuilder> FIELD_DESERIALIZER = new FieldDeserializer();

        /* loaded from: classes11.dex */
        private static class FieldDeserializer implements JsonFieldDeserializer<BatchCreateInviteResponse.Invite.InviteBuilder> {
            private FieldDeserializer() {
            }

            @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
            public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
                return handleField(jsonParser, str, (String) ((BatchCreateInviteResponse.Invite.InviteBuilder) obj));
            }

            public <U extends BatchCreateInviteResponse.Invite.InviteBuilder> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
                if ("groupId".equals(str)) {
                    u.groupId(SimpleDeserializers.deserializeString(jsonParser));
                    return true;
                } else if ("invitedBy".equals(str)) {
                    u.invitedBy(SimpleDeserializers.deserializeString(jsonParser));
                    return true;
                } else if ("inviteId".equals(str)) {
                    u.inviteId(SimpleDeserializers.deserializeString(jsonParser));
                    return true;
                } else if ("name".equals(str)) {
                    u.name(SimpleDeserializers.deserializeString(jsonParser));
                    return true;
                } else if ("inviteAddress".equals(str)) {
                    u.address(InviteAddressDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                    return true;
                } else if ("status".equals(str)) {
                    u.status(BatchCreateInviteResponseInviteStatusDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                    return true;
                } else if ("sendAttemptCount".equals(str)) {
                    u.sendAttemptCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser));
                    return true;
                } else if ("createdDate".equals(str)) {
                    u.createdDate(SimpleDeserializers.deserializeString(jsonParser));
                    return true;
                } else if (Message.SERIALIZED_NAME_MODIFIED_DATE.equals(str)) {
                    u.modifiedDate(SimpleDeserializers.deserializeString(jsonParser));
                    return true;
                } else if (Message.SERIALIZED_NAME_SENT_DATE.equals(str)) {
                    u.sentDate(SimpleDeserializers.deserializeString(jsonParser));
                    return true;
                } else if (!"version".equals(str)) {
                    return false;
                } else {
                    u.version(SimpleDeserializers.deserializeLong(jsonParser).longValue());
                    return true;
                }
            }
        }

        InviteDeserializer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
        /* renamed from: deserialize */
        public BatchCreateInviteResponse.Invite mo3229deserialize(JsonParser jsonParser) throws IOException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NULL) {
                return null;
            }
            if (currentToken == JsonToken.START_OBJECT) {
                BatchCreateInviteResponse.Invite.InviteBuilder builder = BatchCreateInviteResponse.Invite.builder();
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

    private BatchCreateInviteResponseInviteListDeserializer(JsonDeserializer<BatchCreateInviteResponse.Invite> jsonDeserializer) {
        super(jsonDeserializer);
    }
}
