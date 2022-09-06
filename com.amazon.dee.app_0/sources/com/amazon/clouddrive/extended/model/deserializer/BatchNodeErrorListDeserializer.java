package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BatchNodeError;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
/* loaded from: classes11.dex */
public class BatchNodeErrorListDeserializer extends ListDeserializer<BatchNodeError> {
    public static final ListDeserializer<BatchNodeError> INSTANCE = new BatchNodeErrorListDeserializer();

    private BatchNodeErrorListDeserializer() {
        super(BatchNodeErrorDeserializer.INSTANCE);
    }
}
