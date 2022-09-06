package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.MemberPreferencesResponse;
import com.amazon.clouddrive.extended.model.PatchGroupPrivacyPreferencesResponse;
import com.amazon.clouddrive.extended.model.deserializer.MemberPreferencesResponseDeserializer;
/* loaded from: classes11.dex */
public class PatchGroupPrivacyPreferencesResponseDeserializer extends AbstractDeserializer<MemberPreferencesResponse, PatchGroupPrivacyPreferencesResponse> {
    public static PatchGroupPrivacyPreferencesResponseDeserializer INSTANCE = new PatchGroupPrivacyPreferencesResponseDeserializer();

    private PatchGroupPrivacyPreferencesResponseDeserializer() {
        super(new MemberPreferencesResponseDeserializer.MemberPreferencesResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public PatchGroupPrivacyPreferencesResponse mo3156createValue() {
        return new PatchGroupPrivacyPreferencesResponse();
    }
}
