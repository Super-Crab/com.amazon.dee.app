package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.extended.model.FeaturedMember;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.Scopes;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class FeaturedMemberDeserializer implements JsonDeserializer<FeaturedMember> {
    public static final JsonDeserializer<FeaturedMember> INSTANCE = new FeaturedMemberDeserializer();
    private final FeaturedMemberFieldDeserializer mFieldHandler = new FeaturedMemberFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class FeaturedMemberFieldDeserializer implements JsonFieldDeserializer<FeaturedMember> {
        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((FeaturedMember) obj));
        }

        public <U extends FeaturedMember> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("createdBy".equals(str)) {
                u.setCreatedBy(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("createdDate".equals(str)) {
                u.setCreatedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("customerId".equals(str)) {
                u.setCustomerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("groupId".equals(str)) {
                u.setGroupId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("modifiedBy".equals(str)) {
                u.setModifiedBy(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (Message.SERIALIZED_NAME_MODIFIED_DATE.equals(str)) {
                u.setModifiedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("lastViewedDate".equals(str)) {
                u.setLastViewedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("lastViewStatus".equals(str)) {
                u.setLastViewStatus(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (Scopes.PROFILE.equals(str)) {
                u.setProfile(ProfileDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("role".equals(str)) {
                u.setRole(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"version".equals(str)) {
                return false;
            } else {
                u.setVersion(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            }
        }
    }

    private FeaturedMemberDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public FeaturedMember mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            FeaturedMember featuredMember = new FeaturedMember();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) featuredMember)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return featuredMember;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
