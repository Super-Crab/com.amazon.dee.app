package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class DescribeAccountAuditConfigurationResult implements Serializable {
    private Map<String, AuditCheckConfiguration> auditCheckConfigurations;
    private Map<String, AuditNotificationTarget> auditNotificationTargetConfigurations;
    private String roleArn;

    public DescribeAccountAuditConfigurationResult addauditCheckConfigurationsEntry(String str, AuditCheckConfiguration auditCheckConfiguration) {
        if (this.auditCheckConfigurations == null) {
            this.auditCheckConfigurations = new HashMap();
        }
        if (!this.auditCheckConfigurations.containsKey(str)) {
            this.auditCheckConfigurations.put(str, auditCheckConfiguration);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public DescribeAccountAuditConfigurationResult addauditNotificationTargetConfigurationsEntry(String str, AuditNotificationTarget auditNotificationTarget) {
        if (this.auditNotificationTargetConfigurations == null) {
            this.auditNotificationTargetConfigurations = new HashMap();
        }
        if (!this.auditNotificationTargetConfigurations.containsKey(str)) {
            this.auditNotificationTargetConfigurations.put(str, auditNotificationTarget);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public DescribeAccountAuditConfigurationResult clearauditCheckConfigurationsEntries() {
        this.auditCheckConfigurations = null;
        return this;
    }

    public DescribeAccountAuditConfigurationResult clearauditNotificationTargetConfigurationsEntries() {
        this.auditNotificationTargetConfigurations = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAccountAuditConfigurationResult)) {
            return false;
        }
        DescribeAccountAuditConfigurationResult describeAccountAuditConfigurationResult = (DescribeAccountAuditConfigurationResult) obj;
        if ((describeAccountAuditConfigurationResult.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (describeAccountAuditConfigurationResult.getRoleArn() != null && !describeAccountAuditConfigurationResult.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((describeAccountAuditConfigurationResult.getAuditNotificationTargetConfigurations() == null) ^ (getAuditNotificationTargetConfigurations() == null)) {
            return false;
        }
        if (describeAccountAuditConfigurationResult.getAuditNotificationTargetConfigurations() != null && !describeAccountAuditConfigurationResult.getAuditNotificationTargetConfigurations().equals(getAuditNotificationTargetConfigurations())) {
            return false;
        }
        if ((describeAccountAuditConfigurationResult.getAuditCheckConfigurations() == null) ^ (getAuditCheckConfigurations() == null)) {
            return false;
        }
        return describeAccountAuditConfigurationResult.getAuditCheckConfigurations() == null || describeAccountAuditConfigurationResult.getAuditCheckConfigurations().equals(getAuditCheckConfigurations());
    }

    public Map<String, AuditCheckConfiguration> getAuditCheckConfigurations() {
        return this.auditCheckConfigurations;
    }

    public Map<String, AuditNotificationTarget> getAuditNotificationTargetConfigurations() {
        return this.auditNotificationTargetConfigurations;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getAuditNotificationTargetConfigurations() == null ? 0 : getAuditNotificationTargetConfigurations().hashCode())) * 31;
        if (getAuditCheckConfigurations() != null) {
            i = getAuditCheckConfigurations().hashCode();
        }
        return hashCode + i;
    }

    public void setAuditCheckConfigurations(Map<String, AuditCheckConfiguration> map) {
        this.auditCheckConfigurations = map;
    }

    public void setAuditNotificationTargetConfigurations(Map<String, AuditNotificationTarget> map) {
        this.auditNotificationTargetConfigurations = map;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAuditNotificationTargetConfigurations() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("auditNotificationTargetConfigurations: ");
            outline1073.append(getAuditNotificationTargetConfigurations());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAuditCheckConfigurations() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("auditCheckConfigurations: ");
            outline1074.append(getAuditCheckConfigurations());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeAccountAuditConfigurationResult withAuditCheckConfigurations(Map<String, AuditCheckConfiguration> map) {
        this.auditCheckConfigurations = map;
        return this;
    }

    public DescribeAccountAuditConfigurationResult withAuditNotificationTargetConfigurations(Map<String, AuditNotificationTarget> map) {
        this.auditNotificationTargetConfigurations = map;
        return this;
    }

    public DescribeAccountAuditConfigurationResult withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }
}
