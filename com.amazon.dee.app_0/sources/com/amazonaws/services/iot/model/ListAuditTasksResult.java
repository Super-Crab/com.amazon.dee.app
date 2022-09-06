package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListAuditTasksResult implements Serializable {
    private String nextToken;
    private List<AuditTaskMetadata> tasks;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditTasksResult)) {
            return false;
        }
        ListAuditTasksResult listAuditTasksResult = (ListAuditTasksResult) obj;
        if ((listAuditTasksResult.getTasks() == null) ^ (getTasks() == null)) {
            return false;
        }
        if (listAuditTasksResult.getTasks() != null && !listAuditTasksResult.getTasks().equals(getTasks())) {
            return false;
        }
        if ((listAuditTasksResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listAuditTasksResult.getNextToken() == null || listAuditTasksResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<AuditTaskMetadata> getTasks() {
        return this.tasks;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTasks() == null ? 0 : getTasks().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setTasks(Collection<AuditTaskMetadata> collection) {
        if (collection == null) {
            this.tasks = null;
        } else {
            this.tasks = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTasks() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("tasks: ");
            outline1072.append(getTasks());
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

    public ListAuditTasksResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListAuditTasksResult withTasks(AuditTaskMetadata... auditTaskMetadataArr) {
        if (getTasks() == null) {
            this.tasks = new ArrayList(auditTaskMetadataArr.length);
        }
        for (AuditTaskMetadata auditTaskMetadata : auditTaskMetadataArr) {
            this.tasks.add(auditTaskMetadata);
        }
        return this;
    }

    public ListAuditTasksResult withTasks(Collection<AuditTaskMetadata> collection) {
        setTasks(collection);
        return this;
    }
}
