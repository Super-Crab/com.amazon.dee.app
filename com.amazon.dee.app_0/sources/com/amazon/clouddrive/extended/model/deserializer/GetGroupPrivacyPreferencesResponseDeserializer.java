package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetGroupPrivacyPreferencesResponse;
import com.amazon.clouddrive.extended.model.MemberPreferencesResponse;
import com.amazon.clouddrive.extended.model.deserializer.MemberPreferencesResponseDeserializer;
/* loaded from: classes11.dex */
public class GetGroupPrivacyPreferencesResponseDeserializer extends AbstractDeserializer<MemberPreferencesResponse, GetGroupPrivacyPreferencesResponse> {
    public static GetGroupPrivacyPreferencesResponseDeserializer INSTANCE = new GetGroupPrivacyPreferencesResponseDeserializer();

    private GetGroupPrivacyPreferencesResponseDeserializer() {
        super(new MemberPreferencesResponseDeserializer.MemberPreferencesResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GetGroupPrivacyPreferencesResponse mo3156createValue() {
        return new GetGroupPrivacyPreferencesResponse();
    }
}
