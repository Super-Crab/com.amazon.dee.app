package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.GroupCoverPhoto;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GroupCoverPhotoSerializer implements JsonSerializer<GroupCoverPhoto> {
    public static final JsonSerializer<GroupCoverPhoto> INSTANCE = new GroupCoverPhotoSerializer();

    private GroupCoverPhotoSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GroupCoverPhoto groupCoverPhoto, JsonGenerator jsonGenerator) throws IOException {
        if (groupCoverPhoto == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        if (groupCoverPhoto.getNodeId() != null) {
            jsonGenerator.writeFieldName(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID);
            SimpleSerializers.serializeString(groupCoverPhoto.getNodeId(), jsonGenerator);
        }
        if (groupCoverPhoto.getOwnerId() != null) {
            jsonGenerator.writeFieldName(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY);
            SimpleSerializers.serializeString(groupCoverPhoto.getOwnerId(), jsonGenerator);
        }
        if (groupCoverPhoto.getCropBox() != null) {
            jsonGenerator.writeFieldName("cropBox");
            CropBoxSerializer.INSTANCE.serialize(groupCoverPhoto.getCropBox(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
