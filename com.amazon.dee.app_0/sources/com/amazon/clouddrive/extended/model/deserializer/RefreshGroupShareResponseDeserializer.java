package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupShareResponse;
import com.amazon.clouddrive.extended.model.RefreshGroupShareResponse;
import com.amazon.clouddrive.extended.model.deserializer.GroupShareResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* loaded from: classes11.dex */
public class RefreshGroupShareResponseDeserializer extends AbstractDeserializer<GroupShareResponse, RefreshGroupShareResponse> {
    public static final JsonDeserializer<RefreshGroupShareResponse> INSTANCE = new RefreshGroupShareResponseDeserializer();

    private RefreshGroupShareResponseDeserializer() {
        super(new GroupShareResponseDeserializer.GroupShareResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public RefreshGroupShareResponse mo3156createValue() {
        return new RefreshGroupShareResponse();
    }
}
