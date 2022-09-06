package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class StartThingRegistrationTaskRequest extends AmazonWebServiceRequest implements Serializable {
    private String inputFileBucket;
    private String inputFileKey;
    private String roleArn;
    private String templateBody;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StartThingRegistrationTaskRequest)) {
            return false;
        }
        StartThingRegistrationTaskRequest startThingRegistrationTaskRequest = (StartThingRegistrationTaskRequest) obj;
        if ((startThingRegistrationTaskRequest.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (startThingRegistrationTaskRequest.getTemplateBody() != null && !startThingRegistrationTaskRequest.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((startThingRegistrationTaskRequest.getInputFileBucket() == null) ^ (getInputFileBucket() == null)) {
            return false;
        }
        if (startThingRegistrationTaskRequest.getInputFileBucket() != null && !startThingRegistrationTaskRequest.getInputFileBucket().equals(getInputFileBucket())) {
            return false;
        }
        if ((startThingRegistrationTaskRequest.getInputFileKey() == null) ^ (getInputFileKey() == null)) {
            return false;
        }
        if (startThingRegistrationTaskRequest.getInputFileKey() != null && !startThingRegistrationTaskRequest.getInputFileKey().equals(getInputFileKey())) {
            return false;
        }
        if ((startThingRegistrationTaskRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return startThingRegistrationTaskRequest.getRoleArn() == null || startThingRegistrationTaskRequest.getRoleArn().equals(getRoleArn());
    }

    public String getInputFileBucket() {
        return this.inputFileBucket;
    }

    public String getInputFileKey() {
        return this.inputFileKey;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getTemplateBody() {
        return this.templateBody;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getTemplateBody() == null ? 0 : getTemplateBody().hashCode()) + 31) * 31) + (getInputFileBucket() == null ? 0 : getInputFileBucket().hashCode())) * 31) + (getInputFileKey() == null ? 0 : getInputFileKey().hashCode())) * 31;
        if (getRoleArn() != null) {
            i = getRoleArn().hashCode();
        }
        return hashCode + i;
    }

    public void setInputFileBucket(String str) {
        this.inputFileBucket = str;
    }

    public void setInputFileKey(String str) {
        this.inputFileKey = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTemplateBody() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("templateBody: ");
            outline1072.append(getTemplateBody());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getInputFileBucket() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("inputFileBucket: ");
            outline1073.append(getInputFileBucket());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getInputFileKey() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("inputFileKey: ");
            outline1074.append(getInputFileKey());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1075.append(getRoleArn());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StartThingRegistrationTaskRequest withInputFileBucket(String str) {
        this.inputFileBucket = str;
        return this;
    }

    public StartThingRegistrationTaskRequest withInputFileKey(String str) {
        this.inputFileKey = str;
        return this;
    }

    public StartThingRegistrationTaskRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public StartThingRegistrationTaskRequest withTemplateBody(String str) {
        this.templateBody = str;
        return this;
    }
}
