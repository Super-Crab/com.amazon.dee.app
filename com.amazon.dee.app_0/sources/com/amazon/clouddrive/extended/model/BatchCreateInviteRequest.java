package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import lombok.NonNull;
/* loaded from: classes11.dex */
public final class BatchCreateInviteRequest implements CloudDriveRequest {
    @NonNull
    private final String groupId;
    @NonNull
    private final List<Invitee> invitees;

    public BatchCreateInviteRequest(@NonNull String str, @NonNull List<Invitee> list) {
        if (str != null) {
            if (list == null) {
                throw new IllegalArgumentException("invitees is null");
            }
            this.groupId = str;
            this.invitees = list;
            return;
        }
        throw new IllegalArgumentException("groupId is null");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BatchCreateInviteRequest)) {
            return false;
        }
        BatchCreateInviteRequest batchCreateInviteRequest = (BatchCreateInviteRequest) obj;
        String groupId = getGroupId();
        String groupId2 = batchCreateInviteRequest.getGroupId();
        if (groupId != null ? !groupId.equals(groupId2) : groupId2 != null) {
            return false;
        }
        List<Invitee> invitees = getInvitees();
        List<Invitee> invitees2 = batchCreateInviteRequest.getInvitees();
        return invitees != null ? invitees.equals(invitees2) : invitees2 == null;
    }

    @NonNull
    public String getGroupId() {
        return this.groupId;
    }

    @NonNull
    public List<Invitee> getInvitees() {
        return this.invitees;
    }

    public int hashCode() {
        String groupId = getGroupId();
        int i = 43;
        int hashCode = groupId == null ? 43 : groupId.hashCode();
        List<Invitee> invitees = getInvitees();
        int i2 = (hashCode + 59) * 59;
        if (invitees != null) {
            i = invitees.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BatchCreateInviteRequest(groupId=");
        outline107.append(getGroupId());
        outline107.append(", invitees=");
        outline107.append(getInvitees());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof BatchCreateInviteRequest)) {
            return 1;
        }
        BatchCreateInviteRequest batchCreateInviteRequest = (BatchCreateInviteRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getGroupId(), batchCreateInviteRequest.getGroupId());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getInvitees(), batchCreateInviteRequest.getInvitees());
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
