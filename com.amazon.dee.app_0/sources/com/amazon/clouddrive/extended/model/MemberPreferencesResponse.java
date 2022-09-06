package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public class MemberPreferencesResponse {
    private List<GroupPreferenceSet> preferenceSets;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MemberPreferencesResponse)) {
            return false;
        }
        MemberPreferencesResponse memberPreferencesResponse = (MemberPreferencesResponse) obj;
        return getPreferenceSets() != null ? getPreferenceSets().equals(memberPreferencesResponse.getPreferenceSets()) : memberPreferencesResponse.getPreferenceSets() == null;
    }

    public List<GroupPreferenceSet> getPreferenceSets() {
        return this.preferenceSets;
    }

    public int hashCode() {
        if (getPreferenceSets() != null) {
            return getPreferenceSets().hashCode();
        }
        return 0;
    }

    public void setPreferenceSets(List<GroupPreferenceSet> list) {
        this.preferenceSets = list;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline94(GeneratedOutlineSupport1.outline107("MemberPreferencesResponse{preferenceSets="), this.preferenceSets, JsonReaderKt.END_OBJ);
    }
}
