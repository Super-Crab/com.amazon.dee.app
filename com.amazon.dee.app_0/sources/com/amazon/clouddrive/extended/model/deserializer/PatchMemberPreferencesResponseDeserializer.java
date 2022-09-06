package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.MemberPreferencesResponse;
import com.amazon.clouddrive.extended.model.PatchMemberPreferencesResponse;
import com.amazon.clouddrive.extended.model.deserializer.MemberPreferencesResponseDeserializer;
/* loaded from: classes11.dex */
public class PatchMemberPreferencesResponseDeserializer extends AbstractDeserializer<MemberPreferencesResponse, PatchMemberPreferencesResponse> {
    public static PatchMemberPreferencesResponseDeserializer INSTANCE = new PatchMemberPreferencesResponseDeserializer();

    private PatchMemberPreferencesResponseDeserializer() {
        super(new MemberPreferencesResponseDeserializer.MemberPreferencesResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public PatchMemberPreferencesResponse mo3156createValue() {
        return new PatchMemberPreferencesResponse();
    }
}
