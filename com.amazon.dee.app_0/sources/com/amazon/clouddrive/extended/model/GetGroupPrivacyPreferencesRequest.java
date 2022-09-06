package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class GetGroupPrivacyPreferencesRequest implements CloudDriveRequest {
    private final String groupId;
    private final String lang;
    private final String preferenceLevel;

    public GetGroupPrivacyPreferencesRequest(String str, String str2, String str3) {
        this.groupId = str;
        this.lang = str2;
        this.preferenceLevel = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetGroupPrivacyPreferencesRequest)) {
            return false;
        }
        GetGroupPrivacyPreferencesRequest getGroupPrivacyPreferencesRequest = (GetGroupPrivacyPreferencesRequest) obj;
        String groupId = getGroupId();
        String groupId2 = getGroupPrivacyPreferencesRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = getGroupPrivacyPreferencesRequest.getLang();
        if (lang != null ? !lang.equals(lang2) : lang2 != null) {
            return false;
        }
        String preferenceLevel = getPreferenceLevel();
        String preferenceLevel2 = getGroupPrivacyPreferencesRequest.getPreferenceLevel();
        return preferenceLevel != null ? preferenceLevel.equals(preferenceLevel2) : preferenceLevel2 == null;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLang() {
        return this.lang;
    }

    public String getPreferenceLevel() {
        return this.preferenceLevel;
    }

    public int hashCode() {
        String groupId = getGroupId();
        int i = 43;
        int hashCode = groupId == null ? 43 : groupId.hashCode();
        String lang = getLang();
        int hashCode2 = ((hashCode + 59) * 59) + (lang == null ? 43 : lang.hashCode());
        String preferenceLevel = getPreferenceLevel();
        int i2 = hashCode2 * 59;
        if (preferenceLevel != null) {
            i = preferenceLevel.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupPrivacyPreferencesRequest(groupId=");
        outline107.append(getGroupId());
        outline107.append(", lang=");
        outline107.append(getLang());
        outline107.append(", preferenceLevel=");
        outline107.append(getPreferenceLevel());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this || !(cloudDriveRequest instanceof GetGroupPrivacyPreferencesRequest)) {
            return 0;
        }
        GetGroupPrivacyPreferencesRequest getGroupPrivacyPreferencesRequest = (GetGroupPrivacyPreferencesRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), getGroupPrivacyPreferencesRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getLang(), getGroupPrivacyPreferencesRequest.getLang());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getPreferenceLevel(), getGroupPrivacyPreferencesRequest.getPreferenceLevel());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
