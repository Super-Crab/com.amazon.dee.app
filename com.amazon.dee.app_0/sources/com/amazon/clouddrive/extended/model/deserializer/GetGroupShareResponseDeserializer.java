package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetGroupShareResponse;
import com.amazon.clouddrive.extended.model.GroupShareResponse;
import com.amazon.clouddrive.extended.model.deserializer.GroupShareResponseDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* loaded from: classes11.dex */
public class GetGroupShareResponseDeserializer extends AbstractDeserializer<GroupShareResponse, GetGroupShareResponse> {
    public static final JsonDeserializer<GetGroupShareResponse> INSTANCE = new GetGroupShareResponseDeserializer();

    private GetGroupShareResponseDeserializer() {
        super(new GroupShareResponseDeserializer.GroupShareResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GetGroupShareResponse mo3156createValue() {
        return new GetGroupShareResponse();
    }
}
