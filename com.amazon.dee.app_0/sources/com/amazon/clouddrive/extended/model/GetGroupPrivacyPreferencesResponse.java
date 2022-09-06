package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class GetGroupPrivacyPreferencesResponse extends MemberPreferencesResponse {
    @Override // com.amazon.clouddrive.extended.model.MemberPreferencesResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupPrivacyPreferencesResponse{} ");
        outline107.append(super.toString());
        return outline107.toString();
    }

    public GetGroupPrivacyPreferencesResponse withPreferenceSets(List<GroupPreferenceSet> list) {
        setPreferenceSets(list);
        return this;
    }
}
