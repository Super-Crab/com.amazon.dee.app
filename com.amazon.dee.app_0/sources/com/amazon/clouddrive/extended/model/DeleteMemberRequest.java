package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class DeleteMemberRequest extends AccessTokenRequest {
    private String groupId;
    private String memberId;

    @Override // com.amazon.clouddrive.extended.model.AccessTokenRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof DeleteMemberRequest) && compareTo((CloudDriveRequest) ((DeleteMemberRequest) obj)) == 0;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getMemberId() {
        return this.memberId;
    }

    @Override // com.amazon.clouddrive.extended.model.AccessTokenRequest
    public int hashCode() {
        int i = 0;
        int hashCode = (getGroupId() == null ? 0 : getGroupId().hashCode()) + 1;
        if (getMemberId() != null) {
            i = getMemberId().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public DeleteMemberRequest withGroupId(String str) {
        setGroupId(str);
        return this;
    }

    public DeleteMemberRequest withMemberId(String str) {
        setMemberId(str);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.AccessTokenRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof DeleteMemberRequest)) {
            return 1;
        }
        DeleteMemberRequest deleteMemberRequest = (DeleteMemberRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), deleteMemberRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getMemberId(), deleteMemberRequest.getMemberId());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
