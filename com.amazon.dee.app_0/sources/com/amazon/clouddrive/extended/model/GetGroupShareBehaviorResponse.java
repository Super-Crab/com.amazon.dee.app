package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.extended.model.BatchCreateInviteResponse;
import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public final class GetGroupShareBehaviorResponse implements CloudDriveResponse {
    private final List<BatchCreateInviteResponse.Invite> invites;
    private final String sharePrompt;

    /* loaded from: classes11.dex */
    public static class GetGroupShareBehaviorResponseBuilder {
        private List<BatchCreateInviteResponse.Invite> invites;
        private String sharePrompt;

        GetGroupShareBehaviorResponseBuilder() {
        }

        public GetGroupShareBehaviorResponse build() {
            return new GetGroupShareBehaviorResponse(this.sharePrompt, this.invites);
        }

        public GetGroupShareBehaviorResponseBuilder invites(List<BatchCreateInviteResponse.Invite> list) {
            this.invites = list;
            return this;
        }

        public GetGroupShareBehaviorResponseBuilder sharePrompt(String str) {
            this.sharePrompt = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupShareBehaviorResponse.GetGroupShareBehaviorResponseBuilder(sharePrompt=");
            outline107.append(this.sharePrompt);
            outline107.append(", invites=");
            return GeneratedOutlineSupport1.outline95(outline107, this.invites, ")");
        }
    }

    GetGroupShareBehaviorResponse(String str, List<BatchCreateInviteResponse.Invite> list) {
        this.sharePrompt = str;
        this.invites = list;
    }

    public static GetGroupShareBehaviorResponseBuilder builder() {
        return new GetGroupShareBehaviorResponseBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetGroupShareBehaviorResponse)) {
            return false;
        }
        GetGroupShareBehaviorResponse getGroupShareBehaviorResponse = (GetGroupShareBehaviorResponse) obj;
        String sharePrompt = getSharePrompt();
        String sharePrompt2 = getGroupShareBehaviorResponse.getSharePrompt();
        if (sharePrompt != null ? !sharePrompt.equals(sharePrompt2) : sharePrompt2 != null) {
            return false;
        }
        List<BatchCreateInviteResponse.Invite> invites = getInvites();
        List<BatchCreateInviteResponse.Invite> invites2 = getGroupShareBehaviorResponse.getInvites();
        return invites != null ? invites.equals(invites2) : invites2 == null;
    }

    public List<BatchCreateInviteResponse.Invite> getInvites() {
        return this.invites;
    }

    public String getSharePrompt() {
        return this.sharePrompt;
    }

    public int hashCode() {
        String sharePrompt = getSharePrompt();
        int i = 43;
        int hashCode = sharePrompt == null ? 43 : sharePrompt.hashCode();
        List<BatchCreateInviteResponse.Invite> invites = getInvites();
        int i2 = (hashCode + 59) * 59;
        if (invites != null) {
            i = invites.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupShareBehaviorResponse(sharePrompt=");
        outline107.append(getSharePrompt());
        outline107.append(", invites=");
        outline107.append(getInvites());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetGroupShareBehaviorResponse)) {
            return GetGroupShareBehaviorResponse.class.getName().compareTo(cloudDriveResponse.getClass().getName());
        }
        GetGroupShareBehaviorResponse getGroupShareBehaviorResponse = (GetGroupShareBehaviorResponse) cloudDriveResponse;
        int compare = ObjectComparator.compare(getSharePrompt(), getGroupShareBehaviorResponse.getSharePrompt());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getInvites(), getGroupShareBehaviorResponse.getInvites());
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
