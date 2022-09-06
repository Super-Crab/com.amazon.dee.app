package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BatchAddNodeResponse;
import com.amazon.clouddrive.extended.model.BatchNodeResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* loaded from: classes11.dex */
public class BatchAddNodeResponseDeserializer extends AbstractDeserializer<BatchNodeResponse, BatchAddNodeResponse> {
    public static final JsonDeserializer<BatchAddNodeResponse> INSTANCE = new BatchAddNodeResponseDeserializer();

    private BatchAddNodeResponseDeserializer() {
        super(new BatchNodeResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public BatchAddNodeResponse mo3156createValue() {
        return new BatchAddNodeResponse();
    }
}
