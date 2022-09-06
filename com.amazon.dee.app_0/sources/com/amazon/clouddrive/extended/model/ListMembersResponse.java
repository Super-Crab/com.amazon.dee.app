package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class ListMembersResponse implements CloudDriveResponse {
    private int count;
    private List<FeaturedMember> members;
    private String nextToken;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ListMembersResponse) && compareTo((CloudDriveResponse) ((ListMembersResponse) obj)) == 0;
    }

    public int getCount() {
        return this.count;
    }

    public List<FeaturedMember> getMembers() {
        return this.members;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int count = getCount() + 1 + (getNextToken() == null ? 0 : getNextToken().hashCode());
        if (getMembers() != null) {
            i = getMembers().hashCode();
        }
        return ((count + i) * 31) + super.hashCode();
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setMembers(List<FeaturedMember> list) {
        this.members = list;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof ListMembersResponse)) {
            return 1;
        }
        ListMembersResponse listMembersResponse = (ListMembersResponse) cloudDriveResponse;
        if (getCount() > listMembersResponse.getCount()) {
            return 1;
        }
        if (getCount() < listMembersResponse.getCount()) {
            return -1;
        }
        int compare = ObjectComparator.compare(getNextToken(), listMembersResponse.getNextToken());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getMembers(), listMembersResponse.getMembers());
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
