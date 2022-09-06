package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.amazon.clouddrive.extended.model.NodeFace;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class NodeFaceDeserializer extends AbstractDeserializer<NodeFace, NodeFace> {
    public static final JsonDeserializer<NodeFace> INSTANCE = new NodeFaceDeserializer();

    /* loaded from: classes11.dex */
    static class NodeFaceFieldDeserializer implements JsonFieldDeserializer<NodeFace> {
        NodeFaceFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((NodeFace) obj));
        }

        public <U extends NodeFace> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("faceId".equals(str)) {
                u.setFaceId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (AlexaDeviceBackgroundImageBridge.KEY_NODE_ID.equals(str)) {
                u.setNodeId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (SpeakerVerificationProfileProvider.COLUMN_PERSON_ID.equals(str)) {
                u.setPersonId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("personName".equals(str)) {
                u.setPersonName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"boundingBox".equals(str)) {
                return false;
            } else {
                u.setBoundingBox(FaceBoundingBoxDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private NodeFaceDeserializer() {
        super(new NodeFaceFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public NodeFace mo3156createValue() {
        return new NodeFace();
    }
}
