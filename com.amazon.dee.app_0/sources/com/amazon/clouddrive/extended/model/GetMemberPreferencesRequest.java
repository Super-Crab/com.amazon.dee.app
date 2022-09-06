package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GetMemberPreferencesRequest extends MemberRequest implements CloudDriveRequest {
    private String groupId;
    private String lang;

    @Override // com.amazon.clouddrive.extended.model.MemberRequest
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetMemberPreferencesRequest.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        GetMemberPreferencesRequest getMemberPreferencesRequest = (GetMemberPreferencesRequest) obj;
        String str = this.groupId;
        if (str == null ? getMemberPreferencesRequest.groupId != null : !str.equals(getMemberPreferencesRequest.groupId)) {
            return false;
        }
        String str2 = this.lang;
        String str3 = getMemberPreferencesRequest.lang;
        return str2 != null ? str2.equals(str3) : str3 == null;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getLang() {
        return this.lang;
    }

    @Override // com.amazon.clouddrive.extended.model.MemberRequest
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.groupId;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.lang;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public GetMemberPreferencesRequest withGroupId(String str) {
        setGroupId(str);
        return this;
    }

    public GetMemberPreferencesRequest withLang(String str) {
        setLang(str);
        return this;
    }

    public GetMemberPreferencesRequest withMemberId(String str) {
        setMemberId(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (cloudDriveRequest == null || !(cloudDriveRequest instanceof GetMemberPreferencesRequest)) {
            return -1;
        }
        GetMemberPreferencesRequest getMemberPreferencesRequest = (GetMemberPreferencesRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), getMemberPreferencesRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getMemberId(), getMemberPreferencesRequest.getMemberId());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getLang(), getMemberPreferencesRequest.getLang());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
