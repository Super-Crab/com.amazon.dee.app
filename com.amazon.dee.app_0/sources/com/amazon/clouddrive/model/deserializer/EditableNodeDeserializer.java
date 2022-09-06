package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.EditableNode;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class EditableNodeDeserializer implements JsonDeserializer<EditableNode> {
    public static final JsonDeserializer<EditableNode> INSTANCE = new EditableNodeDeserializer();
    private final EditableNodeFieldDeserializer mFieldHandler = new EditableNodeFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class EditableNodeFieldDeserializer implements JsonFieldDeserializer<IEditableNode> {
        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((IEditableNode) obj));
        }

        public <U extends IEditableNode> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("name".equals(str)) {
                u.setName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("description".equals(str)) {
                u.setDescription(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("parents".equals(str)) {
                u.setParents(NodeIdListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("id".equals(str)) {
                u.setId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("contentProperties".equals(str)) {
                u.setContentProperties(ContentPropertiesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("properties".equals(str)) {
                u.setProperties(PropertiesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("subKindProperties".equals(str)) {
                u.setSubKindProperties(SubKindPropertiesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME.equals(str)) {
                u.setKind(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("subKinds".equals(str)) {
                u.setSubKinds(SubKindListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("labels".equals(str)) {
                u.setLabels(LabelListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("status".equals(str)) {
                u.setStatus(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("collectionProperties".equals(str)) {
                u.setCollectionProperties(CollectionPropertiesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("parentMap".equals(str)) {
                u.setParentMap(KindObjectIdListMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("settings".equals(str)) {
                u.setSettings(SettingsDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("transforms".equals(str)) {
                u.setTransforms(TransformListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"symbolicNodeHeroId".equals(str)) {
                return false;
            } else {
                u.setSymbolicNodeHeroId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private EditableNodeDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public EditableNode mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            EditableNode editableNode = new EditableNode();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) editableNode)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return editableNode;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
