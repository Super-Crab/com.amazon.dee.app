package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class PatchGroupPrivacyPreferencesRequest implements CloudDriveRequest {
    private final String groupId;
    private String lang;
    private final List<GroupPreferenceSetUpdate> preferenceSets;

    public PatchGroupPrivacyPreferencesRequest(String str, List<GroupPreferenceSetUpdate> list, String str2) {
        this.groupId = str;
        this.preferenceSets = list;
        this.lang = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof PatchGroupPrivacyPreferencesRequest) && compareTo((CloudDriveRequest) ((PatchGroupPrivacyPreferencesRequest) obj)) == 0;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLang() {
        return this.lang;
    }

    public List<GroupPreferenceSetUpdate> getPreferenceSets() {
        return this.preferenceSets;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((getGroupId() != null ? getGroupId().hashCode() : 0) * 31) + (getPreferenceSets() != null ? getPreferenceSets().hashCode() : 0)) * 31;
        if (getLang() != null) {
            i = getLang().hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PatchGroupPrivacyPreferencesRequest{groupId='");
        GeneratedOutlineSupport1.outline176(outline107, this.groupId, Chars.QUOTE, "preferenceSets=");
        outline107.append(this.preferenceSets);
        outline107.append("lang=");
        return GeneratedOutlineSupport1.outline89(outline107, this.lang, JsonReaderKt.END_OBJ);
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this || !(cloudDriveRequest instanceof PatchGroupPrivacyPreferencesRequest)) {
            return 0;
        }
        PatchGroupPrivacyPreferencesRequest patchGroupPrivacyPreferencesRequest = (PatchGroupPrivacyPreferencesRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), patchGroupPrivacyPreferencesRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getPreferenceSets(), patchGroupPrivacyPreferencesRequest.getPreferenceSets());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compare2 = ObjectComparator.compare(getLang(), patchGroupPrivacyPreferencesRequest.getLang());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
