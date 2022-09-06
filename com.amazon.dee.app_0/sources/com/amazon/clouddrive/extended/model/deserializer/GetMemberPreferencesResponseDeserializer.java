package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetMemberPreferencesResponse;
import com.amazon.clouddrive.extended.model.MemberPreferencesResponse;
import com.amazon.clouddrive.extended.model.deserializer.MemberPreferencesResponseDeserializer;
/* loaded from: classes11.dex */
public class GetMemberPreferencesResponseDeserializer extends AbstractDeserializer<MemberPreferencesResponse, GetMemberPreferencesResponse> {
    public static GetMemberPreferencesResponseDeserializer INSTANCE = new GetMemberPreferencesResponseDeserializer();

    private GetMemberPreferencesResponseDeserializer() {
        super(new MemberPreferencesResponseDeserializer.MemberPreferencesResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GetMemberPreferencesResponse mo3156createValue() {
        return new GetMemberPreferencesResponse();
    }
}
