package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class PatchMemberPreferencesRequest extends MemberRequest implements CloudDriveRequest {
    private String groupId;
    private String lang;
    private List<GroupPreferenceSetUpdate> preferenceSets;

    @Override // com.amazon.clouddrive.extended.model.MemberRequest
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatchMemberPreferencesRequest) || !super.equals(obj)) {
            return false;
        }
        PatchMemberPreferencesRequest patchMemberPreferencesRequest = (PatchMemberPreferencesRequest) obj;
        if (getPreferenceSets() == null ? patchMemberPreferencesRequest.getPreferenceSets() == null : getPreferenceSets().equals(patchMemberPreferencesRequest.getPreferenceSets())) {
            if (getGroupId() == null ? patchMemberPreferencesRequest.getGroupId() == null : getGroupId().equals(patchMemberPreferencesRequest.getGroupId())) {
                if (getLang() != null) {
                    if (getLang().equals(patchMemberPreferencesRequest.getLang())) {
                        return true;
                    }
                } else if (patchMemberPreferencesRequest.getLang() == null) {
                    return true;
                }
            }
        }
        return false;
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

    @Override // com.amazon.clouddrive.extended.model.MemberRequest
    public int hashCode() {
        int i = 0;
        int hashCode = ((((super.hashCode() * 31) + (getPreferenceSets() != null ? getPreferenceSets().hashCode() : 0)) * 31) + (getGroupId() != null ? getGroupId().hashCode() : 0)) * 31;
        if (getLang() != null) {
            i = getLang().hashCode();
        }
        return hashCode + i;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setPreferenceSets(List<GroupPreferenceSetUpdate> list) {
        this.preferenceSets = list;
    }

    public PatchMemberPreferencesRequest withGroupId(String str) {
        setGroupId(str);
        return this;
    }

    public PatchMemberPreferencesRequest withLang(String str) {
        setLang(str);
        return this;
    }

    public PatchMemberPreferencesRequest withMemberId(String str) {
        setMemberId(str);
        return this;
    }

    public PatchMemberPreferencesRequest withPreferenceSets(List<GroupPreferenceSetUpdate> list) {
        setPreferenceSets(list);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (cloudDriveRequest == null || !(cloudDriveRequest instanceof PatchMemberPreferencesRequest)) {
            return -1;
        }
        PatchMemberPreferencesRequest patchMemberPreferencesRequest = (PatchMemberPreferencesRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), patchMemberPreferencesRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getMemberId(), patchMemberPreferencesRequest.getMemberId());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getPreferenceSets(), patchMemberPreferencesRequest.getPreferenceSets());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getLang(), patchMemberPreferencesRequest.getLang());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }
}
