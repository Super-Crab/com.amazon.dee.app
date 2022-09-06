package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeJobResult implements Serializable {
    private String documentSource;
    private Job job;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeJobResult)) {
            return false;
        }
        DescribeJobResult describeJobResult = (DescribeJobResult) obj;
        if ((describeJobResult.getDocumentSource() == null) ^ (getDocumentSource() == null)) {
            return false;
        }
        if (describeJobResult.getDocumentSource() != null && !describeJobResult.getDocumentSource().equals(getDocumentSource())) {
            return false;
        }
        if ((describeJobResult.getJob() == null) ^ (getJob() == null)) {
            return false;
        }
        return describeJobResult.getJob() == null || describeJobResult.getJob().equals(getJob());
    }

    public String getDocumentSource() {
        return this.documentSource;
    }

    public Job getJob() {
        return this.job;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getDocumentSource() == null ? 0 : getDocumentSource().hashCode()) + 31) * 31;
        if (getJob() != null) {
            i = getJob().hashCode();
        }
        return hashCode + i;
    }

    public void setDocumentSource(String str) {
        this.documentSource = str;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDocumentSource() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("documentSource: ");
            outline1072.append(getDocumentSource());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getJob() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("job: ");
            outline1073.append(getJob());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeJobResult withDocumentSource(String str) {
        this.documentSource = str;
        return this;
    }

    public DescribeJobResult withJob(Job job) {
        this.job = job;
        return this;
    }
}
