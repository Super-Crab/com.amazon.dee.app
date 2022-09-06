package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.extended.model.Reaction;
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
public class ReactionDeserializer implements JsonDeserializer<Reaction> {
    public static final JsonDeserializer<Reaction> INSTANCE = new ReactionDeserializer();
    private final ReactionFieldDeserializer mFieldHandler = new ReactionFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ReactionFieldDeserializer implements JsonFieldDeserializer<Reaction.Builder> {
        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Reaction.Builder) obj));
        }

        public <U extends Reaction.Builder> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("groupId".equals(str)) {
                u.withGroupId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("reactionId".equals(str)) {
                u.withReactionId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME.equals(str)) {
                u.withKind(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("body".equals(str)) {
                u.withBody(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("topic".equals(str)) {
                u.withTopic(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("author".equals(str)) {
                u.withAuthor(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("version".equals(str)) {
                u.withVersion(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if (Message.SERIALIZED_NAME_MODIFIED_DATE.equals(str)) {
                u.withModifiedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("createdDate".equals(str)) {
                u.withCreatedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("authorProfile".equals(str)) {
                u.withAuthorProfile(ProfileDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("topicInfo".equals(str)) {
                u.withTopicInfo(TopicInfoDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("coverPhotos".equals(str)) {
                u.withCoverPhotos(GroupCoverPhotoListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("topicOwner".equals(str)) {
                u.withTopicOwner(ProfileDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"topicContentAggregations".equals(str)) {
                return false;
            } else {
                u.withTopicContentAggregations(ContentAggregationsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public Reaction mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            Reaction.Builder builder = new Reaction.Builder();
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
            return builder.mo2999build();
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
