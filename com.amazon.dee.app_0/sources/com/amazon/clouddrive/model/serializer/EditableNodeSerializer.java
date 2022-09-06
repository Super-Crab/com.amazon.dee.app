package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.EditableNode;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class EditableNodeSerializer implements JsonSerializer<EditableNode> {
    public static final JsonSerializer<EditableNode> INSTANCE = new EditableNodeSerializer();
    private final EditableNodeFieldSerializer mFieldSerializer = new EditableNodeFieldSerializer();

    /* loaded from: classes11.dex */
    public static class EditableNodeFieldSerializer implements JsonFieldSerializer<IEditableNode> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((EditableNodeFieldSerializer) ((IEditableNode) obj), jsonGenerator);
        }

        public <U extends IEditableNode> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("name");
            SimpleSerializers.serializeString(u.getName(), jsonGenerator);
            jsonGenerator.writeFieldName("description");
            SimpleSerializers.serializeString(u.getDescription(), jsonGenerator);
            jsonGenerator.writeFieldName("parents");
            NodeIdListSerializer.INSTANCE.serialize(u.getParents(), jsonGenerator);
            jsonGenerator.writeFieldName("id");
            SimpleSerializers.serializeString(u.getId(), jsonGenerator);
            jsonGenerator.writeFieldName("contentProperties");
            ContentPropertiesSerializer.INSTANCE.serialize(u.getContentProperties(), jsonGenerator);
            jsonGenerator.writeFieldName("properties");
            PropertiesSerializer.INSTANCE.serialize(u.getProperties(), jsonGenerator);
            jsonGenerator.writeFieldName("subKindProperties");
            SubKindPropertiesSerializer.INSTANCE.serialize(u.getSubKindProperties(), jsonGenerator);
            jsonGenerator.writeFieldName(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME);
            SimpleSerializers.serializeString(u.getKind(), jsonGenerator);
            jsonGenerator.writeFieldName("subKinds");
            SubKindListSerializer.INSTANCE.serialize(u.getSubKinds(), jsonGenerator);
            jsonGenerator.writeFieldName("labels");
            LabelListSerializer.INSTANCE.serialize(u.getLabels(), jsonGenerator);
            jsonGenerator.writeFieldName("status");
            SimpleSerializers.serializeString(u.getStatus(), jsonGenerator);
            jsonGenerator.writeFieldName("collectionProperties");
            CollectionPropertiesSerializer.INSTANCE.serialize(u.getCollectionProperties(), jsonGenerator);
            jsonGenerator.writeFieldName("parentMap");
            KindObjectIdListMapSerializer.INSTANCE.serialize(u.getParentMap(), jsonGenerator);
            jsonGenerator.writeFieldName("settings");
            SettingsSerializer.INSTANCE.serialize(u.getSettings(), jsonGenerator);
            jsonGenerator.writeFieldName("transforms");
            TransformListSerializer.INSTANCE.serialize(u.getTransforms(), jsonGenerator);
            jsonGenerator.writeFieldName("symbolicNodeHeroId");
            SimpleSerializers.serializeString(u.getSymbolicNodeHeroId(), jsonGenerator);
        }
    }

    private EditableNodeSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(EditableNode editableNode, JsonGenerator jsonGenerator) throws IOException {
        if (editableNode == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((EditableNodeFieldSerializer) editableNode, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
