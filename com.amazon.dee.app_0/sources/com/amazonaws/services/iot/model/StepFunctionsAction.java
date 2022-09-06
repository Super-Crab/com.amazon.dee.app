package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class StepFunctionsAction implements Serializable {
    private String executionNamePrefix;
    private String roleArn;
    private String stateMachineName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StepFunctionsAction)) {
            return false;
        }
        StepFunctionsAction stepFunctionsAction = (StepFunctionsAction) obj;
        if ((stepFunctionsAction.getExecutionNamePrefix() == null) ^ (getExecutionNamePrefix() == null)) {
            return false;
        }
        if (stepFunctionsAction.getExecutionNamePrefix() != null && !stepFunctionsAction.getExecutionNamePrefix().equals(getExecutionNamePrefix())) {
            return false;
        }
        if ((stepFunctionsAction.getStateMachineName() == null) ^ (getStateMachineName() == null)) {
            return false;
        }
        if (stepFunctionsAction.getStateMachineName() != null && !stepFunctionsAction.getStateMachineName().equals(getStateMachineName())) {
            return false;
        }
        if ((stepFunctionsAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return stepFunctionsAction.getRoleArn() == null || stepFunctionsAction.getRoleArn().equals(getRoleArn());
    }

    public String getExecutionNamePrefix() {
        return this.executionNamePrefix;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getStateMachineName() {
        return this.stateMachineName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getExecutionNamePrefix() == null ? 0 : getExecutionNamePrefix().hashCode()) + 31) * 31) + (getStateMachineName() == null ? 0 : getStateMachineName().hashCode())) * 31;
        if (getRoleArn() != null) {
            i = getRoleArn().hashCode();
        }
        return hashCode + i;
    }

    public void setExecutionNamePrefix(String str) {
        this.executionNamePrefix = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setStateMachineName(String str) {
        this.stateMachineName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getExecutionNamePrefix() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("executionNamePrefix: ");
            outline1072.append(getExecutionNamePrefix());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getStateMachineName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("stateMachineName: ");
            outline1073.append(getStateMachineName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1074.append(getRoleArn());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StepFunctionsAction withExecutionNamePrefix(String str) {
        this.executionNamePrefix = str;
        return this;
    }

    public StepFunctionsAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public StepFunctionsAction withStateMachineName(String str) {
        this.stateMachineName = str;
        return this;
    }
}
