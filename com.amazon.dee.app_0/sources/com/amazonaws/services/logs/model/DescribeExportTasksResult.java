package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DescribeExportTasksResult implements Serializable {
    private List<ExportTask> exportTasks;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeExportTasksResult)) {
            return false;
        }
        DescribeExportTasksResult describeExportTasksResult = (DescribeExportTasksResult) obj;
        if ((describeExportTasksResult.getExportTasks() == null) ^ (getExportTasks() == null)) {
            return false;
        }
        if (describeExportTasksResult.getExportTasks() != null && !describeExportTasksResult.getExportTasks().equals(getExportTasks())) {
            return false;
        }
        if ((describeExportTasksResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return describeExportTasksResult.getNextToken() == null || describeExportTasksResult.getNextToken().equals(getNextToken());
    }

    public List<ExportTask> getExportTasks() {
        return this.exportTasks;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getExportTasks() == null ? 0 : getExportTasks().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setExportTasks(Collection<ExportTask> collection) {
        if (collection == null) {
            this.exportTasks = null;
        } else {
            this.exportTasks = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getExportTasks() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("exportTasks: ");
            outline1072.append(getExportTasks());
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

    public DescribeExportTasksResult withExportTasks(ExportTask... exportTaskArr) {
        if (getExportTasks() == null) {
            this.exportTasks = new ArrayList(exportTaskArr.length);
        }
        for (ExportTask exportTask : exportTaskArr) {
            this.exportTasks.add(exportTask);
        }
        return this;
    }

    public DescribeExportTasksResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeExportTasksResult withExportTasks(Collection<ExportTask> collection) {
        setExportTasks(collection);
        return this;
    }
}
