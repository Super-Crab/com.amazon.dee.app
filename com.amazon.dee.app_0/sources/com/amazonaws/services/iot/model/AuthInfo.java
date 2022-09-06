package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class AuthInfo implements Serializable {
    private String actionType;
    private List<String> resources;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthInfo)) {
            return false;
        }
        AuthInfo authInfo = (AuthInfo) obj;
        if ((authInfo.getActionType() == null) ^ (getActionType() == null)) {
            return false;
        }
        if (authInfo.getActionType() != null && !authInfo.getActionType().equals(getActionType())) {
            return false;
        }
        if ((authInfo.getResources() == null) ^ (getResources() == null)) {
            return false;
        }
        return authInfo.getResources() == null || authInfo.getResources().equals(getResources());
    }

    public String getActionType() {
        return this.actionType;
    }

    public List<String> getResources() {
        return this.resources;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getActionType() == null ? 0 : getActionType().hashCode()) + 31) * 31;
        if (getResources() != null) {
            i = getResources().hashCode();
        }
        return hashCode + i;
    }

    public void setActionType(String str) {
        this.actionType = str;
    }

    public void setResources(Collection<String> collection) {
        if (collection == null) {
            this.resources = null;
        } else {
            this.resources = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getActionType() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("actionType: ");
            outline1072.append(getActionType());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getResources() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("resources: ");
            outline1073.append(getResources());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AuthInfo withActionType(String str) {
        this.actionType = str;
        return this;
    }

    public AuthInfo withResources(String... strArr) {
        if (getResources() == null) {
            this.resources = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.resources.add(str);
        }
        return this;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType.toString();
    }

    public AuthInfo withActionType(ActionType actionType) {
        this.actionType = actionType.toString();
        return this;
    }

    public AuthInfo withResources(Collection<String> collection) {
        setResources(collection);
        return this;
    }
}
