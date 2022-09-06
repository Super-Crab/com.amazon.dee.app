package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BatchNodeResponse;
import com.amazon.clouddrive.extended.model.BatchRemoveNodeResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* loaded from: classes11.dex */
public class BatchRemoveNodeResponseDeserializer extends AbstractDeserializer<BatchNodeResponse, BatchRemoveNodeResponse> {
    public static final JsonDeserializer<BatchRemoveNodeResponse> INSTANCE = new BatchRemoveNodeResponseDeserializer();

    private BatchRemoveNodeResponseDeserializer() {
        super(new BatchNodeResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public BatchRemoveNodeResponse mo3156createValue() {
        return new BatchRemoveNodeResponse();
    }
}
