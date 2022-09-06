package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListThingRegistrationTasksResult implements Serializable {
    private String nextToken;
    private List<String> taskIds;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingRegistrationTasksResult)) {
            return false;
        }
        ListThingRegistrationTasksResult listThingRegistrationTasksResult = (ListThingRegistrationTasksResult) obj;
        if ((listThingRegistrationTasksResult.getTaskIds() == null) ^ (getTaskIds() == null)) {
            return false;
        }
        if (listThingRegistrationTasksResult.getTaskIds() != null && !listThingRegistrationTasksResult.getTaskIds().equals(getTaskIds())) {
            return false;
        }
        if ((listThingRegistrationTasksResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingRegistrationTasksResult.getNextToken() == null || listThingRegistrationTasksResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<String> getTaskIds() {
        return this.taskIds;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTaskIds() == null ? 0 : getTaskIds().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setTaskIds(Collection<String> collection) {
        if (collection == null) {
            this.taskIds = null;
        } else {
            this.taskIds = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskIds() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskIds: ");
            outline1072.append(getTaskIds());
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

    public ListThingRegistrationTasksResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingRegistrationTasksResult withTaskIds(String... strArr) {
        if (getTaskIds() == null) {
            this.taskIds = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.taskIds.add(str);
        }
        return this;
    }

    public ListThingRegistrationTasksResult withTaskIds(Collection<String> collection) {
        setTaskIds(collection);
        return this;
    }
}
