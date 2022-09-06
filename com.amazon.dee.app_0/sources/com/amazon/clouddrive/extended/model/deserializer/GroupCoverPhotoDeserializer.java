package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupCoverPhoto;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GroupCoverPhotoDeserializer implements JsonDeserializer<GroupCoverPhoto> {
    public static final JsonDeserializer<GroupCoverPhoto> INSTANCE = new GroupCoverPhotoDeserializer();
    private final GroupCoverPhotoFieldDeserializer mFieldHandler = new GroupCoverPhotoFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GroupCoverPhotoFieldDeserializer implements JsonFieldDeserializer<GroupCoverPhoto> {
        GroupCoverPhotoFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GroupCoverPhoto) obj));
        }

        public <U extends GroupCoverPhoto> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (AlexaDeviceBackgroundImageBridge.KEY_NODE_ID.equals(str)) {
                u.setNodeId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY.equals(str)) {
                u.setOwnerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("cropBox".equals(str)) {
                u.setCropBox(CropBoxDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"url".equals(str)) {
                return false;
            } else {
                u.setUrl(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GroupCoverPhotoDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GroupCoverPhoto mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GroupCoverPhoto groupCoverPhoto = new GroupCoverPhoto();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) groupCoverPhoto)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return groupCoverPhoto;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
