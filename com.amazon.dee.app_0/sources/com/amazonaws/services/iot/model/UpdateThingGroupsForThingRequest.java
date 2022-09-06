package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class UpdateThingGroupsForThingRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean overrideDynamicGroups;
    private List<String> thingGroupsToAdd;
    private List<String> thingGroupsToRemove;
    private String thingName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateThingGroupsForThingRequest)) {
            return false;
        }
        UpdateThingGroupsForThingRequest updateThingGroupsForThingRequest = (UpdateThingGroupsForThingRequest) obj;
        if ((updateThingGroupsForThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (updateThingGroupsForThingRequest.getThingName() != null && !updateThingGroupsForThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((updateThingGroupsForThingRequest.getThingGroupsToAdd() == null) ^ (getThingGroupsToAdd() == null)) {
            return false;
        }
        if (updateThingGroupsForThingRequest.getThingGroupsToAdd() != null && !updateThingGroupsForThingRequest.getThingGroupsToAdd().equals(getThingGroupsToAdd())) {
            return false;
        }
        if ((updateThingGroupsForThingRequest.getThingGroupsToRemove() == null) ^ (getThingGroupsToRemove() == null)) {
            return false;
        }
        if (updateThingGroupsForThingRequest.getThingGroupsToRemove() != null && !updateThingGroupsForThingRequest.getThingGroupsToRemove().equals(getThingGroupsToRemove())) {
            return false;
        }
        if ((updateThingGroupsForThingRequest.getOverrideDynamicGroups() == null) ^ (getOverrideDynamicGroups() == null)) {
            return false;
        }
        return updateThingGroupsForThingRequest.getOverrideDynamicGroups() == null || updateThingGroupsForThingRequest.getOverrideDynamicGroups().equals(getOverrideDynamicGroups());
    }

    public Boolean getOverrideDynamicGroups() {
        return this.overrideDynamicGroups;
    }

    public List<String> getThingGroupsToAdd() {
        return this.thingGroupsToAdd;
    }

    public List<String> getThingGroupsToRemove() {
        return this.thingGroupsToRemove;
    }

    public String getThingName() {
        return this.thingName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingGroupsToAdd() == null ? 0 : getThingGroupsToAdd().hashCode())) * 31) + (getThingGroupsToRemove() == null ? 0 : getThingGroupsToRemove().hashCode())) * 31;
        if (getOverrideDynamicGroups() != null) {
            i = getOverrideDynamicGroups().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isOverrideDynamicGroups() {
        return this.overrideDynamicGroups;
    }

    public void setOverrideDynamicGroups(Boolean bool) {
        this.overrideDynamicGroups = bool;
    }

    public void setThingGroupsToAdd(Collection<String> collection) {
        if (collection == null) {
            this.thingGroupsToAdd = null;
        } else {
            this.thingGroupsToAdd = new ArrayList(collection);
        }
    }

    public void setThingGroupsToRemove(Collection<String> collection) {
        if (collection == null) {
            this.thingGroupsToRemove = null;
        } else {
            this.thingGroupsToRemove = new ArrayList(collection);
        }
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1072.append(getThingName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingGroupsToAdd() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingGroupsToAdd: ");
            outline1073.append(getThingGroupsToAdd());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingGroupsToRemove() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingGroupsToRemove: ");
            outline1074.append(getThingGroupsToRemove());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getOverrideDynamicGroups() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("overrideDynamicGroups: ");
            outline1075.append(getOverrideDynamicGroups());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateThingGroupsForThingRequest withOverrideDynamicGroups(Boolean bool) {
        this.overrideDynamicGroups = bool;
        return this;
    }

    public UpdateThingGroupsForThingRequest withThingGroupsToAdd(String... strArr) {
        if (getThingGroupsToAdd() == null) {
            this.thingGroupsToAdd = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.thingGroupsToAdd.add(str);
        }
        return this;
    }

    public UpdateThingGroupsForThingRequest withThingGroupsToRemove(String... strArr) {
        if (getThingGroupsToRemove() == null) {
            this.thingGroupsToRemove = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.thingGroupsToRemove.add(str);
        }
        return this;
    }

    public UpdateThingGroupsForThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public UpdateThingGroupsForThingRequest withThingGroupsToAdd(Collection<String> collection) {
        setThingGroupsToAdd(collection);
        return this;
    }

    public UpdateThingGroupsForThingRequest withThingGroupsToRemove(Collection<String> collection) {
        setThingGroupsToRemove(collection);
        return this;
    }
}
