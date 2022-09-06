package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.amazon.clouddrive.extended.model.NodeExtended;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.serializer.LabelListSerializer;
import com.amazon.clouddrive.model.serializer.NodeSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class NodeExtendedFieldSerializer extends NodeSerializer.NodeFieldSerializer {
    @Override // com.amazon.clouddrive.model.serializer.NodeSerializer.NodeFieldSerializer, com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer, com.amazon.clouddrive.model.serializer.JsonFieldSerializer
    public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
        serializeFields((NodeExtendedFieldSerializer) ((IEditableNode) obj), jsonGenerator);
    }

    @Override // com.amazon.clouddrive.model.serializer.NodeSerializer.NodeFieldSerializer, com.amazon.clouddrive.model.serializer.EditableNodeSerializer.EditableNodeFieldSerializer
    public <U extends IEditableNode> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
        super.serializeFields((NodeExtendedFieldSerializer) u, jsonGenerator);
        if (u instanceof NodeExtended) {
            NodeExtended nodeExtended = (NodeExtended) u;
            jsonGenerator.writeFieldName("restricted");
            SimpleSerializers.serializeBoolean(nodeExtended.isRestricted(), jsonGenerator);
            jsonGenerator.writeFieldName("shareId");
            SimpleSerializers.serializeString(nodeExtended.getShareId(), jsonGenerator);
            jsonGenerator.writeFieldName("shareURL");
            SimpleSerializers.serializeString(nodeExtended.getShareURL(), jsonGenerator);
            jsonGenerator.writeFieldName(PhotoSearchCategory.THINGS);
            LabelListSerializer.INSTANCE.serialize(nodeExtended.getThingTags(), jsonGenerator);
        }
    }
}
