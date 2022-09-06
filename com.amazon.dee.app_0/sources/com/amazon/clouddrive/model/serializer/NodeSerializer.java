package com.amazon.clouddrive.model.serializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.INode;
import com.amazon.clouddrive.model.Node;
import com.amazon.clouddrive.model.serializer.EditableNodeSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class NodeSerializer implements JsonSerializer<Node> {
    public static final JsonSerializer<Node> INSTANCE = new NodeSerializer();
    private final NodeFieldSerializer mFieldSerializer = new NodeFieldSerializer();

    /* loaded from: classes11.dex */
    public static class NodeFieldSerializer extends EditableNodeSerializer.EditableNodeFieldSerializer {
        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((NodeFieldSerializer) ((IEditableNode) obj), jsonGenerator);
        }

        @Override // com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer
        public <U extends IEditableNode> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            super.serializeFields((NodeFieldSerializer) u, jsonGenerator);
            if (u instanceof INode) {
                INode iNode = (INode) u;
                jsonGenerator.writeFieldName("version");
                SimpleSerializers.serializePrimitiveLong(iNode.getVersion(), jsonGenerator);
                jsonGenerator.writeFieldName("eTagResponse");
                SimpleSerializers.serializeString(iNode.getETagResponse(), jsonGenerator);
                jsonGenerator.writeFieldName("assets");
                AssetsSerializer.INSTANCE.serialize(iNode.getAssets(), jsonGenerator);
                jsonGenerator.writeFieldName("isShared");
                SimpleSerializers.serializeBoolean(iNode.isShared(), jsonGenerator);
                jsonGenerator.writeFieldName("isRoot");
                SimpleSerializers.serializeBoolean(iNode.isRoot(), jsonGenerator);
                jsonGenerator.writeFieldName("eTagRequest");
                SimpleSerializers.serializeString(iNode.getETagRequest(), jsonGenerator);
                jsonGenerator.writeFieldName("exclusivelyTrashed");
                SimpleSerializers.serializeBoolean(iNode.isExclusivelyTrashed(), jsonGenerator);
                jsonGenerator.writeFieldName("createdDate");
                SimpleSerializers.serializeString(iNode.getCreatedDate(), jsonGenerator);
                jsonGenerator.writeFieldName("recursivelyTrashed");
                SimpleSerializers.serializeBoolean(iNode.isRecursivelyTrashed(), jsonGenerator);
                jsonGenerator.writeFieldName(Message.SERIALIZED_NAME_MODIFIED_DATE);
                SimpleSerializers.serializeString(iNode.getModifiedDate(), jsonGenerator);
                jsonGenerator.writeFieldName("createdBy");
                SimpleSerializers.serializeString(iNode.getCreatedBy(), jsonGenerator);
                jsonGenerator.writeFieldName("tempLink");
                SimpleSerializers.serializeString(iNode.getTempLink(), jsonGenerator);
                jsonGenerator.writeFieldName("protectedFolder");
                SimpleSerializers.serializeBoolean(iNode.isProtectedFolder(), jsonGenerator);
                jsonGenerator.writeFieldName("accessRuleIds");
                SimpleListSerializers.STRING_LIST_SERIALIZER.serialize(iNode.getAccessRuleIds(), jsonGenerator);
            }
        }
    }

    private NodeSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(Node node, JsonGenerator jsonGenerator) throws IOException {
        if (node == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((NodeFieldSerializer) node, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
