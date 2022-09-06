package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.extended.model.Group;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GroupDeserializer implements JsonDeserializer<Group> {
    public static final JsonDeserializer<Group> INSTANCE = new GroupDeserializer();
    private final GroupFieldDeserializer mFieldHandler = new GroupFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GroupFieldDeserializer implements JsonFieldDeserializer<Group> {
        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Group) obj));
        }

        public <U extends Group> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("coverNodeId".equals(str)) {
                u.setCoverNodeId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("coverPhoto".equals(str)) {
                u.setCoverPhoto(GroupCoverPhotoDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("createdBy".equals(str)) {
                u.setCreatedBy(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("createdDate".equals(str)) {
                u.setCreatedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("description".equals(str)) {
                u.setDescription(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("featuredMembers".equals(str)) {
                u.setFeaturedMembers(FeaturedMemberListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
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
            } else if ("name".equals(str)) {
                u.setName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("nameType".equals(str)) {
                u.setNameType(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("callerProfile".equals(str)) {
                u.setCallerProfile(FeaturedMemberDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("unseenContentAggregations".equals(str)) {
                u.setUnseenContentAggregations(ContentAggregationsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("version".equals(str)) {
                u.setVersion(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("contentAggregations".equals(str)) {
                u.setContentAggregations(ContentAggregationsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("memberCount".equals(str)) {
                u.setMemberCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser));
                return true;
            } else if ("addedBy".equals(str)) {
                u.setAddedBy(ProfileDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("lastViewedDate".equals(str)) {
                u.setLastViewedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("inviteCallToAction".equals(str)) {
                u.setInviteCallToAction(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("inviteDescription".equals(str)) {
                u.setInviteDescription(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("groupPreferences".equals(str)) {
                u.setGroupPreferences(GroupRestrictionDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("referralInfo".equals(str)) {
                u.setReferralInfo(ReferralInfoResponseDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME.equals(str)) {
                return false;
            } else {
                u.setKind(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GroupDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public Group mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            Group group = new Group();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) group)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return group;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
