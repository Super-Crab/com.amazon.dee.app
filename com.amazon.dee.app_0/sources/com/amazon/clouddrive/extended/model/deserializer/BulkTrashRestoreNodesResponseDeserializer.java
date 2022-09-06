package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BulkResponse;
import com.amazon.clouddrive.extended.model.BulkTrashRestoreNodesResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* loaded from: classes11.dex */
public class BulkTrashRestoreNodesResponseDeserializer extends AbstractDeserializer<BulkResponse, BulkTrashRestoreNodesResponse> {
    public static final JsonDeserializer<BulkTrashRestoreNodesResponse> INSTANCE = new BulkTrashRestoreNodesResponseDeserializer();

    private BulkTrashRestoreNodesResponseDeserializer() {
        super(new BulkResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public BulkTrashRestoreNodesResponse mo3156createValue() {
        return new BulkTrashRestoreNodesResponse();
    }
}
