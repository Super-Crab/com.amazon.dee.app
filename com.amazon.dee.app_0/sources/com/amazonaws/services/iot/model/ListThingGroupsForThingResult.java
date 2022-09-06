package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListThingGroupsForThingResult implements Serializable {
    private String nextToken;
    private List<GroupNameAndArn> thingGroups;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingGroupsForThingResult)) {
            return false;
        }
        ListThingGroupsForThingResult listThingGroupsForThingResult = (ListThingGroupsForThingResult) obj;
        if ((listThingGroupsForThingResult.getThingGroups() == null) ^ (getThingGroups() == null)) {
            return false;
        }
        if (listThingGroupsForThingResult.getThingGroups() != null && !listThingGroupsForThingResult.getThingGroups().equals(getThingGroups())) {
            return false;
        }
        if ((listThingGroupsForThingResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingGroupsForThingResult.getNextToken() == null || listThingGroupsForThingResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<GroupNameAndArn> getThingGroups() {
        return this.thingGroups;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingGroups() == null ? 0 : getThingGroups().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setThingGroups(Collection<GroupNameAndArn> collection) {
        if (collection == null) {
            this.thingGroups = null;
        } else {
            this.thingGroups = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroups() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroups: ");
            outline1072.append(getThingGroups());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingGroupsForThingResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingGroupsForThingResult withThingGroups(GroupNameAndArn... groupNameAndArnArr) {
        if (getThingGroups() == null) {
            this.thingGroups = new ArrayList(groupNameAndArnArr.length);
        }
        for (GroupNameAndArn groupNameAndArn : groupNameAndArnArr) {
            this.thingGroups.add(groupNameAndArn);
        }
        return this;
    }

    public ListThingGroupsForThingResult withThingGroups(Collection<GroupNameAndArn> collection) {
        setThingGroups(collection);
        return this;
    }
}
