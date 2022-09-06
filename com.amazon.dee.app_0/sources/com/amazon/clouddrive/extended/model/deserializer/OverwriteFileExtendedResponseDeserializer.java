package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.OverwriteFileExtendedResponse;
import com.amazon.clouddrive.extended.model.deserializer.NodeExtendedDeserializer;
import com.amazon.clouddrive.model.IEditableNode;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* loaded from: classes11.dex */
public class OverwriteFileExtendedResponseDeserializer extends AbstractDeserializer<IEditableNode, OverwriteFileExtendedResponse> {
    public static final JsonDeserializer<OverwriteFileExtendedResponse> INSTANCE = new OverwriteFileExtendedResponseDeserializer();

    public OverwriteFileExtendedResponseDeserializer() {
        super(new NodeExtendedDeserializer.NodeExtendedFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public OverwriteFileExtendedResponse mo3156createValue() {
        return new OverwriteFileExtendedResponse();
    }
}
