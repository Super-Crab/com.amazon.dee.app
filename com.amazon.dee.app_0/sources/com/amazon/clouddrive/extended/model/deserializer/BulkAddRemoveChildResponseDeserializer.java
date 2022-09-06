package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BulkAddRemoveChildResponse;
import com.amazon.clouddrive.extended.model.BulkResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* loaded from: classes11.dex */
public class BulkAddRemoveChildResponseDeserializer extends AbstractDeserializer<BulkResponse, BulkAddRemoveChildResponse> {
    public static final JsonDeserializer<BulkAddRemoveChildResponse> INSTANCE = new BulkAddRemoveChildResponseDeserializer();

    private BulkAddRemoveChildResponseDeserializer() {
        super(new BulkResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public BulkAddRemoveChildResponse mo3156createValue() {
        return new BulkAddRemoveChildResponse();
    }
}
