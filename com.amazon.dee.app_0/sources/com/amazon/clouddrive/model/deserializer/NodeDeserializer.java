package com.amazon.clouddrive.model.deserializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.INode;
import com.amazon.clouddrive.model.Node;
import com.amazon.clouddrive.model.deserializer.EditableNodeDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class NodeDeserializer implements JsonDeserializer<Node> {
    public static final JsonDeserializer<Node> INSTANCE = new NodeDeserializer();
    private final NodeFieldDeserializer mFieldHandler = new NodeFieldDeserializer();

    /* loaded from: classes11.dex */
    public static class NodeFieldDeserializer extends EditableNodeDeserializer.EditableNodeFieldDeserializer {
        @Override // com.amazon.clouddrive.model.deserializer.EditableNodeDeserializer.EditableNodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((IEditableNode) obj));
        }

        @Override // com.amazon.clouddrive.model.deserializer.EditableNodeDeserializer.EditableNodeFieldDeserializer
        public <U extends IEditableNode> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (super.handleField(jsonParser, str, (String) u)) {
                return true;
            }
            if (!(u instanceof INode)) {
                return false;
            }
            if ("version".equals(str)) {
                ((INode) u).setVersion(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("eTagResponse".equals(str)) {
                ((INode) u).setETagResponse(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("assets".equals(str)) {
                ((INode) u).setAssets(AssetsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("isShared".equals(str)) {
                ((INode) u).setIsShared(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if ("isRoot".equals(str)) {
                ((INode) u).setIsRoot(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if ("eTagRequest".equals(str)) {
                ((INode) u).setETagRequest(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("exclusivelyTrashed".equals(str)) {
                ((INode) u).setExclusivelyTrashed(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if ("createdDate".equals(str)) {
                ((INode) u).setCreatedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("recursivelyTrashed".equals(str)) {
                ((INode) u).setRecursivelyTrashed(SimpleDeserializers.deserializeBoolean(jsonParser));
                return true;
            } else if (Message.SERIALIZED_NAME_MODIFIED_DATE.equals(str)) {
                ((INode) u).setModifiedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("createdBy".equals(str)) {
                ((INode) u).setCreatedBy(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("tempLink".equals(str)) {
                ((INode) u).setTempLink(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("protectedFolder".equals(str)) {
                ((INode) u).setIsProtectedFolder(SimpleDeserializers.deserializeBoolean(jsonParser));
                return false;
            } else if (!"accessRuleIds".equals(str)) {
                return false;
            } else {
                ((INode) u).setAccessRuleIds(SimpleListDeserializers.STRING_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return false;
            }
        }
    }

    private NodeDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public Node mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            Node node = new Node();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) node)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return node;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
