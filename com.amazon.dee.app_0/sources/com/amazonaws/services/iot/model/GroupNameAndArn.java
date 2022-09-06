package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GroupNameAndArn implements Serializable {
    private String groupArn;
    private String groupName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GroupNameAndArn)) {
            return false;
        }
        GroupNameAndArn groupNameAndArn = (GroupNameAndArn) obj;
        if ((groupNameAndArn.getGroupName() == null) ^ (getGroupName() == null)) {
            return false;
        }
        if (groupNameAndArn.getGroupName() != null && !groupNameAndArn.getGroupName().equals(getGroupName())) {
            return false;
        }
        if ((groupNameAndArn.getGroupArn() == null) ^ (getGroupArn() == null)) {
            return false;
        }
        return groupNameAndArn.getGroupArn() == null || groupNameAndArn.getGroupArn().equals(getGroupArn());
    }

    public String getGroupArn() {
        return this.groupArn;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getGroupName() == null ? 0 : getGroupName().hashCode()) + 31) * 31;
        if (getGroupArn() != null) {
            i = getGroupArn().hashCode();
        }
        return hashCode + i;
    }

    public void setGroupArn(String str) {
        this.groupArn = str;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("groupName: ");
            outline1072.append(getGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getGroupArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("groupArn: ");
            outline1073.append(getGroupArn());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GroupNameAndArn withGroupArn(String str) {
        this.groupArn = str;
        return this;
    }

    public GroupNameAndArn withGroupName(String str) {
        this.groupName = str;
        return this;
    }
}
