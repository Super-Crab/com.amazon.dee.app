package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class OTAUpdateInfo implements Serializable {
    private Map<String, String> additionalParameters;
    private String awsIotJobArn;
    private String awsIotJobId;
    private AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig;
    private Date creationDate;
    private String description;
    private ErrorInfo errorInfo;
    private Date lastModifiedDate;
    private String otaUpdateArn;
    private List<OTAUpdateFile> otaUpdateFiles;
    private String otaUpdateId;
    private String otaUpdateStatus;
    private String targetSelection;
    private List<String> targets;

    public OTAUpdateInfo addadditionalParametersEntry(String str, String str2) {
        if (this.additionalParameters == null) {
            this.additionalParameters = new HashMap();
        }
        if (!this.additionalParameters.containsKey(str)) {
            this.additionalParameters.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public OTAUpdateInfo clearadditionalParametersEntries() {
        this.additionalParameters = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OTAUpdateInfo)) {
            return false;
        }
        OTAUpdateInfo oTAUpdateInfo = (OTAUpdateInfo) obj;
        if ((oTAUpdateInfo.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getOtaUpdateId() != null && !oTAUpdateInfo.getOtaUpdateId().equals(getOtaUpdateId())) {
            return false;
        }
        if ((oTAUpdateInfo.getOtaUpdateArn() == null) ^ (getOtaUpdateArn() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getOtaUpdateArn() != null && !oTAUpdateInfo.getOtaUpdateArn().equals(getOtaUpdateArn())) {
            return false;
        }
        if ((oTAUpdateInfo.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getCreationDate() != null && !oTAUpdateInfo.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((oTAUpdateInfo.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getLastModifiedDate() != null && !oTAUpdateInfo.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((oTAUpdateInfo.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getDescription() != null && !oTAUpdateInfo.getDescription().equals(getDescription())) {
            return false;
        }
        if ((oTAUpdateInfo.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getTargets() != null && !oTAUpdateInfo.getTargets().equals(getTargets())) {
            return false;
        }
        if ((oTAUpdateInfo.getAwsJobExecutionsRolloutConfig() == null) ^ (getAwsJobExecutionsRolloutConfig() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getAwsJobExecutionsRolloutConfig() != null && !oTAUpdateInfo.getAwsJobExecutionsRolloutConfig().equals(getAwsJobExecutionsRolloutConfig())) {
            return false;
        }
        if ((oTAUpdateInfo.getTargetSelection() == null) ^ (getTargetSelection() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getTargetSelection() != null && !oTAUpdateInfo.getTargetSelection().equals(getTargetSelection())) {
            return false;
        }
        if ((oTAUpdateInfo.getOtaUpdateFiles() == null) ^ (getOtaUpdateFiles() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getOtaUpdateFiles() != null && !oTAUpdateInfo.getOtaUpdateFiles().equals(getOtaUpdateFiles())) {
            return false;
        }
        if ((oTAUpdateInfo.getOtaUpdateStatus() == null) ^ (getOtaUpdateStatus() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getOtaUpdateStatus() != null && !oTAUpdateInfo.getOtaUpdateStatus().equals(getOtaUpdateStatus())) {
            return false;
        }
        if ((oTAUpdateInfo.getAwsIotJobId() == null) ^ (getAwsIotJobId() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getAwsIotJobId() != null && !oTAUpdateInfo.getAwsIotJobId().equals(getAwsIotJobId())) {
            return false;
        }
        if ((oTAUpdateInfo.getAwsIotJobArn() == null) ^ (getAwsIotJobArn() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getAwsIotJobArn() != null && !oTAUpdateInfo.getAwsIotJobArn().equals(getAwsIotJobArn())) {
            return false;
        }
        if ((oTAUpdateInfo.getErrorInfo() == null) ^ (getErrorInfo() == null)) {
            return false;
        }
        if (oTAUpdateInfo.getErrorInfo() != null && !oTAUpdateInfo.getErrorInfo().equals(getErrorInfo())) {
            return false;
        }
        if ((oTAUpdateInfo.getAdditionalParameters() == null) ^ (getAdditionalParameters() == null)) {
            return false;
        }
        return oTAUpdateInfo.getAdditionalParameters() == null || oTAUpdateInfo.getAdditionalParameters().equals(getAdditionalParameters());
    }

    public Map<String, String> getAdditionalParameters() {
        return this.additionalParameters;
    }

    public String getAwsIotJobArn() {
        return this.awsIotJobArn;
    }

    public String getAwsIotJobId() {
        return this.awsIotJobId;
    }

    public AwsJobExecutionsRolloutConfig getAwsJobExecutionsRolloutConfig() {
        return this.awsJobExecutionsRolloutConfig;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getDescription() {
        return this.description;
    }

    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getOtaUpdateArn() {
        return this.otaUpdateArn;
    }

    public List<OTAUpdateFile> getOtaUpdateFiles() {
        return this.otaUpdateFiles;
    }

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public String getOtaUpdateStatus() {
        return this.otaUpdateStatus;
    }

    public String getTargetSelection() {
        return this.targetSelection;
    }

    public List<String> getTargets() {
        return this.targets;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((((((((getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode()) + 31) * 31) + (getOtaUpdateArn() == null ? 0 : getOtaUpdateArn().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getTargets() == null ? 0 : getTargets().hashCode())) * 31) + (getAwsJobExecutionsRolloutConfig() == null ? 0 : getAwsJobExecutionsRolloutConfig().hashCode())) * 31) + (getTargetSelection() == null ? 0 : getTargetSelection().hashCode())) * 31) + (getOtaUpdateFiles() == null ? 0 : getOtaUpdateFiles().hashCode())) * 31) + (getOtaUpdateStatus() == null ? 0 : getOtaUpdateStatus().hashCode())) * 31) + (getAwsIotJobId() == null ? 0 : getAwsIotJobId().hashCode())) * 31) + (getAwsIotJobArn() == null ? 0 : getAwsIotJobArn().hashCode())) * 31) + (getErrorInfo() == null ? 0 : getErrorInfo().hashCode())) * 31;
        if (getAdditionalParameters() != null) {
            i = getAdditionalParameters().hashCode();
        }
        return hashCode + i;
    }

    public void setAdditionalParameters(Map<String, String> map) {
        this.additionalParameters = map;
    }

    public void setAwsIotJobArn(String str) {
        this.awsIotJobArn = str;
    }

    public void setAwsIotJobId(String str) {
        this.awsIotJobId = str;
    }

    public void setAwsJobExecutionsRolloutConfig(AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig) {
        this.awsJobExecutionsRolloutConfig = awsJobExecutionsRolloutConfig;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
    }

    public void setOtaUpdateFiles(Collection<OTAUpdateFile> collection) {
        if (collection == null) {
            this.otaUpdateFiles = null;
        } else {
            this.otaUpdateFiles = new ArrayList(collection);
        }
    }

    public void setOtaUpdateId(String str) {
        this.otaUpdateId = str;
    }

    public void setOtaUpdateStatus(String str) {
        this.otaUpdateStatus = str;
    }

    public void setTargetSelection(String str) {
        this.targetSelection = str;
    }

    public void setTargets(Collection<String> collection) {
        if (collection == null) {
            this.targets = null;
        } else {
            this.targets = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getOtaUpdateId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("otaUpdateId: ");
            outline1072.append(getOtaUpdateId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getOtaUpdateArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("otaUpdateArn: ");
            outline1073.append(getOtaUpdateArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1074.append(getCreationDate());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline1075.append(getLastModifiedDate());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getDescription() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("description: ");
            outline1076.append(getDescription());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getTargets() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("targets: ");
            outline1077.append(getTargets());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getAwsJobExecutionsRolloutConfig() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("awsJobExecutionsRolloutConfig: ");
            outline1078.append(getAwsJobExecutionsRolloutConfig());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getTargetSelection() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("targetSelection: ");
            outline1079.append(getTargetSelection());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getOtaUpdateFiles() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("otaUpdateFiles: ");
            outline10710.append(getOtaUpdateFiles());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getOtaUpdateStatus() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("otaUpdateStatus: ");
            outline10711.append(getOtaUpdateStatus());
            outline10711.append(",");
            outline107.append(outline10711.toString());
        }
        if (getAwsIotJobId() != null) {
            StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("awsIotJobId: ");
            outline10712.append(getAwsIotJobId());
            outline10712.append(",");
            outline107.append(outline10712.toString());
        }
        if (getAwsIotJobArn() != null) {
            StringBuilder outline10713 = GeneratedOutlineSupport1.outline107("awsIotJobArn: ");
            outline10713.append(getAwsIotJobArn());
            outline10713.append(",");
            outline107.append(outline10713.toString());
        }
        if (getErrorInfo() != null) {
            StringBuilder outline10714 = GeneratedOutlineSupport1.outline107("errorInfo: ");
            outline10714.append(getErrorInfo());
            outline10714.append(",");
            outline107.append(outline10714.toString());
        }
        if (getAdditionalParameters() != null) {
            StringBuilder outline10715 = GeneratedOutlineSupport1.outline107("additionalParameters: ");
            outline10715.append(getAdditionalParameters());
            outline107.append(outline10715.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public OTAUpdateInfo withAdditionalParameters(Map<String, String> map) {
        this.additionalParameters = map;
        return this;
    }

    public OTAUpdateInfo withAwsIotJobArn(String str) {
        this.awsIotJobArn = str;
        return this;
    }

    public OTAUpdateInfo withAwsIotJobId(String str) {
        this.awsIotJobId = str;
        return this;
    }

    public OTAUpdateInfo withAwsJobExecutionsRolloutConfig(AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig) {
        this.awsJobExecutionsRolloutConfig = awsJobExecutionsRolloutConfig;
        return this;
    }

    public OTAUpdateInfo withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public OTAUpdateInfo withDescription(String str) {
        this.description = str;
        return this;
    }

    public OTAUpdateInfo withErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
        return this;
    }

    public OTAUpdateInfo withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public OTAUpdateInfo withOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
        return this;
    }

    public OTAUpdateInfo withOtaUpdateFiles(OTAUpdateFile... oTAUpdateFileArr) {
        if (getOtaUpdateFiles() == null) {
            this.otaUpdateFiles = new ArrayList(oTAUpdateFileArr.length);
        }
        for (OTAUpdateFile oTAUpdateFile : oTAUpdateFileArr) {
            this.otaUpdateFiles.add(oTAUpdateFile);
        }
        return this;
    }

    public OTAUpdateInfo withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }

    public OTAUpdateInfo withOtaUpdateStatus(String str) {
        this.otaUpdateStatus = str;
        return this;
    }

    public OTAUpdateInfo withTargetSelection(String str) {
        this.targetSelection = str;
        return this;
    }

    public OTAUpdateInfo withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public void setOtaUpdateStatus(OTAUpdateStatus oTAUpdateStatus) {
        this.otaUpdateStatus = oTAUpdateStatus.toString();
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
    }

    public OTAUpdateInfo withOtaUpdateStatus(OTAUpdateStatus oTAUpdateStatus) {
        this.otaUpdateStatus = oTAUpdateStatus.toString();
        return this;
    }

    public OTAUpdateInfo withTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
        return this;
    }

    public OTAUpdateInfo withOtaUpdateFiles(Collection<OTAUpdateFile> collection) {
        setOtaUpdateFiles(collection);
        return this;
    }

    public OTAUpdateInfo withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }
}
