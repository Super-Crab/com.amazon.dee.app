package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.extended.model.GetGroupShareInfoResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetGroupShareInfoResponseDeserializer implements JsonDeserializer<GetGroupShareInfoResponse> {
    public static final JsonDeserializer<GetGroupShareInfoResponse> INSTANCE = new GetGroupShareInfoResponseDeserializer();
    private final GetGroupShareInfoResponseFieldDeserializer mFieldHandler = new GetGroupShareInfoResponseFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetGroupShareInfoResponseFieldDeserializer implements JsonFieldDeserializer<GetGroupShareInfoResponse.GetGroupShareInfoResponseBuilder> {
        GetGroupShareInfoResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetGroupShareInfoResponse.GetGroupShareInfoResponseBuilder) obj));
        }

        public <U extends GetGroupShareInfoResponse.GetGroupShareInfoResponseBuilder> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("coverPhoto".equals(str)) {
                u.coverPhoto(GroupCoverPhotoDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("createdDate".equals(str)) {
                u.createdDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("featuredMembers".equals(str)) {
                u.featuredMembers(FeaturedMemberListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("groupId".equals(str)) {
                u.groupId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (Message.SERIALIZED_NAME_MODIFIED_DATE.equals(str)) {
                u.modifiedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("name".equals(str)) {
                u.name(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("nameType".equals(str)) {
                u.nameType(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("version".equals(str)) {
                u.version(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("contentAggregations".equals(str)) {
                u.contentAggregations(ContentAggregationsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("memberCount".equals(str)) {
                u.memberCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser));
                return false;
            } else if ("hasMembership".equals(str)) {
                u.hasMembership(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if ("privacyPreferences".equals(str)) {
                u.privacyPreferences(GroupPrivacyPreferencesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"ownerProfile".equals(str)) {
                return false;
            } else {
                u.ownerProfile(ProfileDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private GetGroupShareInfoResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetGroupShareInfoResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetGroupShareInfoResponse.GetGroupShareInfoResponseBuilder builder = GetGroupShareInfoResponse.builder();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) builder)) {
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
