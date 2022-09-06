package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListThingRegistrationTaskReportsResult implements Serializable {
    private String nextToken;
    private String reportType;
    private List<String> resourceLinks;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingRegistrationTaskReportsResult)) {
            return false;
        }
        ListThingRegistrationTaskReportsResult listThingRegistrationTaskReportsResult = (ListThingRegistrationTaskReportsResult) obj;
        if ((listThingRegistrationTaskReportsResult.getResourceLinks() == null) ^ (getResourceLinks() == null)) {
            return false;
        }
        if (listThingRegistrationTaskReportsResult.getResourceLinks() != null && !listThingRegistrationTaskReportsResult.getResourceLinks().equals(getResourceLinks())) {
            return false;
        }
        if ((listThingRegistrationTaskReportsResult.getReportType() == null) ^ (getReportType() == null)) {
            return false;
        }
        if (listThingRegistrationTaskReportsResult.getReportType() != null && !listThingRegistrationTaskReportsResult.getReportType().equals(getReportType())) {
            return false;
        }
        if ((listThingRegistrationTaskReportsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingRegistrationTaskReportsResult.getNextToken() == null || listThingRegistrationTaskReportsResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getReportType() {
        return this.reportType;
    }

    public List<String> getResourceLinks() {
        return this.resourceLinks;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getResourceLinks() == null ? 0 : getResourceLinks().hashCode()) + 31) * 31) + (getReportType() == null ? 0 : getReportType().hashCode())) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setReportType(String str) {
        this.reportType = str;
    }

    public void setResourceLinks(Collection<String> collection) {
        if (collection == null) {
            this.resourceLinks = null;
        } else {
            this.resourceLinks = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getResourceLinks() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("resourceLinks: ");
            outline1072.append(getResourceLinks());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getReportType() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("reportType: ");
            outline1073.append(getReportType());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1074.append(getNextToken());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingRegistrationTaskReportsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingRegistrationTaskReportsResult withReportType(String str) {
        this.reportType = str;
        return this;
    }

    public ListThingRegistrationTaskReportsResult withResourceLinks(String... strArr) {
        if (getResourceLinks() == null) {
            this.resourceLinks = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.resourceLinks.add(str);
        }
        return this;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType.toString();
    }

    public ListThingRegistrationTaskReportsResult withReportType(ReportType reportType) {
        this.reportType = reportType.toString();
        return this;
    }

    public ListThingRegistrationTaskReportsResult withResourceLinks(Collection<String> collection) {
        setResourceLinks(collection);
        return this;
    }
}
