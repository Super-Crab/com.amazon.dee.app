package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.amazon.clouddrive.extended.model.NodeExtended;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.LabelListDeserializer;
import com.amazon.clouddrive.model.deserializer.NodeDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class NodeExtendedDeserializer implements JsonDeserializer<NodeExtended> {
    public static final JsonDeserializer<NodeExtended> INSTANCE = new NodeExtendedDeserializer();
    private final NodeExtendedFieldDeserializer mFieldHandler = new NodeExtendedFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class NodeExtendedFieldDeserializer extends NodeDeserializer.NodeFieldDeserializer {
        @Override // com.amazon.clouddrive.model.deserializer.NodeDeserializer.NodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.EditableNodeDeserializer.EditableNodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((IEditableNode) obj));
        }

        @Override // com.amazon.clouddrive.model.deserializer.NodeDeserializer.NodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.EditableNodeDeserializer.EditableNodeFieldDeserializer
        public <U extends IEditableNode> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (super.handleField(jsonParser, str, (String) u)) {
                return true;
            }
            if (!(u instanceof NodeExtended)) {
                return false;
            }
            if ("restricted".equals(str)) {
                ((NodeExtended) u).setRestricted(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if ("shareId".equals(str)) {
                ((NodeExtended) u).setShareId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("shareURL".equals(str)) {
                ((NodeExtended) u).setShareURL(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("familyId".equals(str)) {
                ((NodeExtended) u).setFamilyId(SimpleDeserializers.deserializeString(jsonParser));
                return false;
            } else if (PhotoSearchCategory.THINGS.equals(str)) {
                ((NodeExtended) u).setThingTags(LabelListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return false;
            } else if (MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY.equals(str)) {
                ((NodeExtended) u).setOwnerId(SimpleDeserializers.deserializeString(jsonParser));
                return false;
            } else if ("xAccntParents".equals(str)) {
                ((NodeExtended) u).setXAccntParents(NodeIdOwnerListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return false;
            } else if ("xAccntParentMap".equals(str)) {
                ((NodeExtended) u).setXAccntParentsMap(KindNodeIdOwnerListMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return false;
            } else if (!"groupPermissions".equals(str)) {
                return false;
            } else {
                ((NodeExtended) u).setGroupPermissions(GroupPermissionListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return false;
            }
        }
    }

    private NodeExtendedDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public NodeExtended mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            NodeExtended nodeExtended = new NodeExtended();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) nodeExtended)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return nodeExtended;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
