package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.UploadFileExtendedResponse;
import com.amazon.clouddrive.extended.model.deserializer.NodeExtendedDeserializer;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class UploadFileExtendedResponseDeserializer extends AbstractDeserializer<IEditableNode, UploadFileExtendedResponse> {
    public static final JsonDeserializer<UploadFileExtendedResponse> INSTANCE = new UploadFileExtendedResponseDeserializer();

    /* loaded from: classes11.dex */
    static class UploadFileExtendedResponseFieldDeserializer extends NodeExtendedDeserializer.NodeExtendedFieldDeserializer {
        UploadFileExtendedResponseFieldDeserializer() {
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
            if (!(u instanceof UploadFileExtendedResponse) || !"location".equals(str)) {
                return false;
            }
            ((UploadFileExtendedResponse) u).setLocation(SimpleDeserializers.deserializeString(jsonParser));
            return true;
        }
    }

    public UploadFileExtendedResponseDeserializer() {
        super(new UploadFileExtendedResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public UploadFileExtendedResponse mo3156createValue() {
        return new UploadFileExtendedResponse();
    }
}
