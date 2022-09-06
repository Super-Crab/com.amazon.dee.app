package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class ListGroupsResponse implements CloudDriveResponse {
    private int count;
    private List<Group> groups;
    private String nextToken;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ListGroupsResponse) && compareTo((CloudDriveResponse) ((ListGroupsResponse) obj)) == 0;
    }

    public int getCount() {
        return this.count;
    }

    public List<Group> getGroups() {
        return this.groups;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int count = getCount() + 1 + (getNextToken() == null ? 0 : getNextToken().hashCode());
        if (getGroups() != null) {
            i = getGroups().hashCode();
        }
        return ((count + i) * 31) + super.hashCode();
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setGroups(List<Group> list) {
        this.groups = list;
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
        if (!(cloudDriveResponse instanceof ListGroupsResponse)) {
            return 1;
        }
        ListGroupsResponse listGroupsResponse = (ListGroupsResponse) cloudDriveResponse;
        if (getCount() > listGroupsResponse.getCount()) {
            return 1;
        }
        if (getCount() < listGroupsResponse.getCount()) {
            return -1;
        }
        int compare = ObjectComparator.compare(getNextToken(), listGroupsResponse.getNextToken());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getGroups(), listGroupsResponse.getGroups());
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
