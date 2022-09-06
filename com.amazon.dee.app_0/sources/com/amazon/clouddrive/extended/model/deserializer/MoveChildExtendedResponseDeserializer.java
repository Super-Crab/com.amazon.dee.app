package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.MoveChildExtendedResponse;
import com.amazon.clouddrive.extended.model.deserializer.NodeExtendedDeserializer;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class MoveChildExtendedResponseDeserializer extends AbstractDeserializer<IEditableNode, MoveChildExtendedResponse> {
    public static final JsonDeserializer<MoveChildExtendedResponse> INSTANCE = new MoveChildExtendedResponseDeserializer();

    /* loaded from: classes11.dex */
    static class MoveChildExtendedResponseFieldDeserializer extends NodeExtendedDeserializer.NodeExtendedFieldDeserializer {
        MoveChildExtendedResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.NodeExtendedDeserializer.NodeExtendedFieldDeserializer, com.amazon.clouddrive.model.deserializer.NodeDeserializer.NodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.EditableNodeDeserializer.EditableNodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((IEditableNode) obj));
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.NodeExtendedDeserializer.NodeExtendedFieldDeserializer, com.amazon.clouddrive.model.deserializer.NodeDeserializer.NodeFieldDeserializer, com.amazon.clouddrive.model.deserializer.EditableNodeDeserializer.EditableNodeFieldDeserializer
        public <U extends IEditableNode> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (super.handleField(jsonParser, str, (String) u)) {
                return true;
            }
            if (!(u instanceof MoveChildExtendedResponse) || !"location".equals(str)) {
                return false;
            }
            ((MoveChildExtendedResponse) u).setLocation(SimpleDeserializers.deserializeString(jsonParser));
            return true;
        }
    }

    public MoveChildExtendedResponseDeserializer() {
        super(new MoveChildExtendedResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public MoveChildExtendedResponse mo3156createValue() {
        return new MoveChildExtendedResponse();
    }
}
