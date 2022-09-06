package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeleteMetricFilterRequest extends AmazonWebServiceRequest implements Serializable {
    private String filterName;
    private String logGroupName;

    public DeleteMetricFilterRequest() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteMetricFilterRequest)) {
            return false;
        }
        DeleteMetricFilterRequest deleteMetricFilterRequest = (DeleteMetricFilterRequest) obj;
        if ((deleteMetricFilterRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (deleteMetricFilterRequest.getLogGroupName() != null && !deleteMetricFilterRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((deleteMetricFilterRequest.getFilterName() == null) ^ (getFilterName() == null)) {
            return false;
        }
        return deleteMetricFilterRequest.getFilterName() == null || deleteMetricFilterRequest.getFilterName().equals(getFilterName());
    }

    public String getFilterName() {
        return this.filterName;
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31;
        if (getFilterName() != null) {
            i = getFilterName().hashCode();
        }
        return hashCode + i;
    }

    public void setFilterName(String str) {
        this.filterName = str;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getFilterName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("filterName: ");
            outline1073.append(getFilterName());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteMetricFilterRequest withFilterName(String str) {
        this.filterName = str;
        return this;
    }

    public DeleteMetricFilterRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public DeleteMetricFilterRequest(String str, String str2) {
        setLogGroupName(str);
        setFilterName(str2);
    }
}
