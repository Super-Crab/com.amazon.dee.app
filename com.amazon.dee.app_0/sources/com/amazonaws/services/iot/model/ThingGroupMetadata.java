package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
public class ThingGroupMetadata implements Serializable {
    private Date creationDate;
    private String parentGroupName;
    private List<GroupNameAndArn> rootToParentThingGroups;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingGroupMetadata)) {
            return false;
        }
        ThingGroupMetadata thingGroupMetadata = (ThingGroupMetadata) obj;
        if ((thingGroupMetadata.getParentGroupName() == null) ^ (getParentGroupName() == null)) {
            return false;
        }
        if (thingGroupMetadata.getParentGroupName() != null && !thingGroupMetadata.getParentGroupName().equals(getParentGroupName())) {
            return false;
        }
        if ((thingGroupMetadata.getRootToParentThingGroups() == null) ^ (getRootToParentThingGroups() == null)) {
            return false;
        }
        if (thingGroupMetadata.getRootToParentThingGroups() != null && !thingGroupMetadata.getRootToParentThingGroups().equals(getRootToParentThingGroups())) {
            return false;
        }
        if ((thingGroupMetadata.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return thingGroupMetadata.getCreationDate() == null || thingGroupMetadata.getCreationDate().equals(getCreationDate());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getParentGroupName() {
        return this.parentGroupName;
    }

    public List<GroupNameAndArn> getRootToParentThingGroups() {
        return this.rootToParentThingGroups;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getParentGroupName() == null ? 0 : getParentGroupName().hashCode()) + 31) * 31) + (getRootToParentThingGroups() == null ? 0 : getRootToParentThingGroups().hashCode())) * 31;
        if (getCreationDate() != null) {
            i = getCreationDate().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setParentGroupName(String str) {
        this.parentGroupName = str;
    }

    public void setRootToParentThingGroups(Collection<GroupNameAndArn> collection) {
        if (collection == null) {
            this.rootToParentThingGroups = null;
        } else {
            this.rootToParentThingGroups = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getParentGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("parentGroupName: ");
            outline1072.append(getParentGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRootToParentThingGroups() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("rootToParentThingGroups: ");
            outline1073.append(getRootToParentThingGroups());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1074.append(getCreationDate());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingGroupMetadata withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public ThingGroupMetadata withParentGroupName(String str) {
        this.parentGroupName = str;
        return this;
    }

    public ThingGroupMetadata withRootToParentThingGroups(GroupNameAndArn... groupNameAndArnArr) {
        if (getRootToParentThingGroups() == null) {
            this.rootToParentThingGroups = new ArrayList(groupNameAndArnArr.length);
        }
        for (GroupNameAndArn groupNameAndArn : groupNameAndArnArr) {
            this.rootToParentThingGroups.add(groupNameAndArn);
        }
        return this;
    }

    public ThingGroupMetadata withRootToParentThingGroups(Collection<GroupNameAndArn> collection) {
        setRootToParentThingGroups(collection);
        return this;
    }
}
