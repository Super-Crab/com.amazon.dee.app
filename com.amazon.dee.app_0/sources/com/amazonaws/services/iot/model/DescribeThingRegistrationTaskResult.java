package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class DescribeThingRegistrationTaskResult implements Serializable {
    private Date creationDate;
    private Integer failureCount;
    private String inputFileBucket;
    private String inputFileKey;
    private Date lastModifiedDate;
    private String message;
    private Integer percentageProgress;
    private String roleArn;
    private String status;
    private Integer successCount;
    private String taskId;
    private String templateBody;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingRegistrationTaskResult)) {
            return false;
        }
        DescribeThingRegistrationTaskResult describeThingRegistrationTaskResult = (DescribeThingRegistrationTaskResult) obj;
        if ((describeThingRegistrationTaskResult.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getTaskId() != null && !describeThingRegistrationTaskResult.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getCreationDate() != null && !describeThingRegistrationTaskResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getLastModifiedDate() != null && !describeThingRegistrationTaskResult.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getTemplateBody() != null && !describeThingRegistrationTaskResult.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getInputFileBucket() == null) ^ (getInputFileBucket() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getInputFileBucket() != null && !describeThingRegistrationTaskResult.getInputFileBucket().equals(getInputFileBucket())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getInputFileKey() == null) ^ (getInputFileKey() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getInputFileKey() != null && !describeThingRegistrationTaskResult.getInputFileKey().equals(getInputFileKey())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getRoleArn() != null && !describeThingRegistrationTaskResult.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getStatus() != null && !describeThingRegistrationTaskResult.getStatus().equals(getStatus())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getMessage() != null && !describeThingRegistrationTaskResult.getMessage().equals(getMessage())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getSuccessCount() == null) ^ (getSuccessCount() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getSuccessCount() != null && !describeThingRegistrationTaskResult.getSuccessCount().equals(getSuccessCount())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getFailureCount() == null) ^ (getFailureCount() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getFailureCount() != null && !describeThingRegistrationTaskResult.getFailureCount().equals(getFailureCount())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getPercentageProgress() == null) ^ (getPercentageProgress() == null)) {
            return false;
        }
        return describeThingRegistrationTaskResult.getPercentageProgress() == null || describeThingRegistrationTaskResult.getPercentageProgress().equals(getPercentageProgress());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Integer getFailureCount() {
        return this.failureCount;
    }

    public String getInputFileBucket() {
        return this.inputFileBucket;
    }

    public String getInputFileKey() {
        return this.inputFileKey;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getPercentageProgress() {
        return this.percentageProgress;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getStatus() {
        return this.status;
    }

    public Integer getSuccessCount() {
        return this.successCount;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public String getTemplateBody() {
        return this.templateBody;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getTemplateBody() == null ? 0 : getTemplateBody().hashCode())) * 31) + (getInputFileBucket() == null ? 0 : getInputFileBucket().hashCode())) * 31) + (getInputFileKey() == null ? 0 : getInputFileKey().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getMessage() == null ? 0 : getMessage().hashCode())) * 31) + (getSuccessCount() == null ? 0 : getSuccessCount().hashCode())) * 31) + (getFailureCount() == null ? 0 : getFailureCount().hashCode())) * 31;
        if (getPercentageProgress() != null) {
            i = getPercentageProgress().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setFailureCount(Integer num) {
        this.failureCount = num;
    }

    public void setInputFileBucket(String str) {
        this.inputFileBucket = str;
    }

    public void setInputFileKey(String str) {
        this.inputFileKey = str;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setPercentageProgress(Integer num) {
        this.percentageProgress = num;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSuccessCount(Integer num) {
        this.successCount = num;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskId: ");
            outline1072.append(getTaskId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1073.append(getCreationDate());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline1074.append(getLastModifiedDate());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTemplateBody() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("templateBody: ");
            outline1075.append(getTemplateBody());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getInputFileBucket() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("inputFileBucket: ");
            outline1076.append(getInputFileBucket());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getInputFileKey() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("inputFileKey: ");
            outline1077.append(getInputFileKey());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1078.append(getRoleArn());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("status: ");
            outline1079.append(getStatus());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getMessage() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("message: ");
            outline10710.append(getMessage());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getSuccessCount() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("successCount: ");
            outline10711.append(getSuccessCount());
            outline10711.append(",");
            outline107.append(outline10711.toString());
        }
        if (getFailureCount() != null) {
            StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("failureCount: ");
            outline10712.append(getFailureCount());
            outline10712.append(",");
            outline107.append(outline10712.toString());
        }
        if (getPercentageProgress() != null) {
            StringBuilder outline10713 = GeneratedOutlineSupport1.outline107("percentageProgress: ");
            outline10713.append(getPercentageProgress());
            outline107.append(outline10713.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeThingRegistrationTaskResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public DescribeThingRegistrationTaskResult withFailureCount(Integer num) {
        this.failureCount = num;
        return this;
    }

    public DescribeThingRegistrationTaskResult withInputFileBucket(String str) {
        this.inputFileBucket = str;
        return this;
    }

    public DescribeThingRegistrationTaskResult withInputFileKey(String str) {
        this.inputFileKey = str;
        return this;
    }

    public DescribeThingRegistrationTaskResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public DescribeThingRegistrationTaskResult withMessage(String str) {
        this.message = str;
        return this;
    }

    public DescribeThingRegistrationTaskResult withPercentageProgress(Integer num) {
        this.percentageProgress = num;
        return this;
    }

    public DescribeThingRegistrationTaskResult withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public DescribeThingRegistrationTaskResult withStatus(String str) {
        this.status = str;
        return this;
    }

    public DescribeThingRegistrationTaskResult withSuccessCount(Integer num) {
        this.successCount = num;
        return this;
    }

    public DescribeThingRegistrationTaskResult withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public DescribeThingRegistrationTaskResult withTemplateBody(String str) {
        this.templateBody = str;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status.toString();
    }

    public DescribeThingRegistrationTaskResult withStatus(Status status) {
        this.status = status.toString();
        return this;
    }
}
