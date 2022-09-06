package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupCollection;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.clouddrive.model.deserializer.SimpleListDeserializers;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GroupCollectionDeserializer implements JsonDeserializer<GroupCollection> {
    public static final JsonDeserializer<GroupCollection> INSTANCE = new GroupCollectionDeserializer();
    private final GroupCollectionFieldDeserializer mFieldHandler = new GroupCollectionFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GroupCollectionFieldDeserializer implements JsonFieldDeserializer<GroupCollection> {
        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GroupCollection) obj));
        }

        public <U extends GroupCollection> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME.equals(str)) {
                u.setKind(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("collectionId".equals(str)) {
                u.setCollectionId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("groupId".equals(str)) {
                u.setGroupId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY.equals(str)) {
                u.setOwnerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("caption".equals(str)) {
                u.setCaption(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("name".equals(str)) {
                u.setName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("dateAdded".equals(str)) {
                u.setDateAdded(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("contentAggregations".equals(str)) {
                u.setContentAggregations(ContentAggregationsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"eventIds".equals(str)) {
                return false;
            } else {
                u.setEventIds(SimpleListDeserializers.STRING_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private GroupCollectionDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GroupCollection mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GroupCollection groupCollection = new GroupCollection();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) groupCollection)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return groupCollection;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
