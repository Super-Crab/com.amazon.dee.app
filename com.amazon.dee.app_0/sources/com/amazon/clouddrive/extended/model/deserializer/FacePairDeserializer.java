package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.FacePair;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class FacePairDeserializer extends AbstractDeserializer<FacePair, FacePair> {
    public static final JsonDeserializer<FacePair> INSTANCE = new FacePairDeserializer();

    /* loaded from: classes11.dex */
    static class FacePairFieldDeserializer implements JsonFieldDeserializer<FacePair> {
        FacePairFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((FacePair) obj));
        }

        public <U extends FacePair> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("face".equals(str)) {
                u.setFace(FaceDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("cdsClusterId".equals(str)) {
                u.setClusterId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("faceId".equals(str)) {
                u.setFaceId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("templink".equals(str)) {
                u.setTemplink(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (AlexaDeviceBackgroundImageBridge.KEY_NODE_ID.equals(str)) {
                u.setNodeId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY.equals(str)) {
                u.setNodeOwnerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("originalWidth".equals(str)) {
                u.setOriginalWidth(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if (!"originalHeight".equals(str)) {
                return false;
            } else {
                u.setOriginalHeight(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            }
        }
    }

    private FacePairDeserializer() {
        super(new FacePairFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public FacePair mo3156createValue() {
        return new FacePair();
    }
}
