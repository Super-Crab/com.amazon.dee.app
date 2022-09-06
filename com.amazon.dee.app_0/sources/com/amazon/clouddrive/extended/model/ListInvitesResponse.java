package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.extended.model.BatchCreateInviteResponse;
import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public final class ListInvitesResponse implements CloudDriveResponse {
    private final int count;
    private final List<BatchCreateInviteResponse.Invite> invites;
    private final String nextToken;

    /* loaded from: classes11.dex */
    public static class ListInvitesResponseBuilder {
        private int count;
        private List<BatchCreateInviteResponse.Invite> invites;
        private String nextToken;

        ListInvitesResponseBuilder() {
        }

        public ListInvitesResponse build() {
            return new ListInvitesResponse(this.count, this.nextToken, this.invites);
        }

        public ListInvitesResponseBuilder count(int i) {
            this.count = i;
            return this;
        }

        public ListInvitesResponseBuilder invites(List<BatchCreateInviteResponse.Invite> list) {
            this.invites = list;
            return this;
        }

        public ListInvitesResponseBuilder nextToken(String str) {
            this.nextToken = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListInvitesResponse.ListInvitesResponseBuilder(count=");
            outline107.append(this.count);
            outline107.append(", nextToken=");
            outline107.append(this.nextToken);
            outline107.append(", invites=");
            return GeneratedOutlineSupport1.outline95(outline107, this.invites, ")");
        }
    }

    ListInvitesResponse(int i, String str, List<BatchCreateInviteResponse.Invite> list) {
        this.count = i;
        this.nextToken = str;
        this.invites = list;
    }

    public static ListInvitesResponseBuilder builder() {
        return new ListInvitesResponseBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListInvitesResponse)) {
            return false;
        }
        ListInvitesResponse listInvitesResponse = (ListInvitesResponse) obj;
        if (getCount() != listInvitesResponse.getCount()) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listInvitesResponse.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        List<BatchCreateInviteResponse.Invite> invites = getInvites();
        List<BatchCreateInviteResponse.Invite> invites2 = listInvitesResponse.getInvites();
        return invites != null ? invites.equals(invites2) : invites2 == null;
    }

    public int getCount() {
        return this.count;
    }

    public List<BatchCreateInviteResponse.Invite> getInvites() {
        return this.invites;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        String nextToken = getNextToken();
        int i = 43;
        int count = ((getCount() + 59) * 59) + (nextToken == null ? 43 : nextToken.hashCode());
        List<BatchCreateInviteResponse.Invite> invites = getInvites();
        int i2 = count * 59;
        if (invites != null) {
            i = invites.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListInvitesResponse(count=");
        outline107.append(getCount());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
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
        if (!(cloudDriveResponse instanceof ListInvitesResponse)) {
            return ListInvitesResponse.class.getName().compareTo(cloudDriveResponse.getClass().getName());
        }
        ListInvitesResponse listInvitesResponse = (ListInvitesResponse) cloudDriveResponse;
        int i = this.count;
        int i2 = listInvitesResponse.count;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int compare = ObjectComparator.compare(this.nextToken, listInvitesResponse.nextToken);
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(this.invites, listInvitesResponse.invites);
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
