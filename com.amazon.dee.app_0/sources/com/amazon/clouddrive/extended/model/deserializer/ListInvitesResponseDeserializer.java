package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ListInvitesResponse;
import com.amazon.clouddrive.extended.model.deserializer.BatchCreateInviteResponseInviteListDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ListInvitesResponseDeserializer implements JsonDeserializer<ListInvitesResponse> {
    public static final JsonDeserializer<ListInvitesResponse> INSTANCE = new ListInvitesResponseDeserializer();
    private final ListInvitesFieldDeserializer fieldDeserializer = new ListInvitesFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class ListInvitesFieldDeserializer implements JsonFieldDeserializer<ListInvitesResponse.ListInvitesResponseBuilder> {
        private ListInvitesFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ListInvitesResponse.ListInvitesResponseBuilder) obj));
        }

        public <U extends ListInvitesResponse.ListInvitesResponseBuilder> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("count".equals(str)) {
                u.count(SimpleDeserializers.deserializeInteger(jsonParser).intValue());
                return true;
            } else if ("nextToken".equals(str)) {
                u.nextToken(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"invites".equals(str)) {
                return false;
            } else {
                u.invites(new ListDeserializer(BatchCreateInviteResponseInviteListDeserializer.InviteDeserializer.INSTANCE).mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    protected ListInvitesResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ListInvitesResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ListInvitesResponse.ListInvitesResponseBuilder builder = ListInvitesResponse.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.fieldDeserializer.handleField(jsonParser, currentName, (String) builder)) {
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
