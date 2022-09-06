package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class CreateOTAUpdateRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, String> additionalParameters;
    private AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig;
    private String description;
    private List<OTAUpdateFile> files;
    private String otaUpdateId;
    private String roleArn;
    private String targetSelection;
    private List<String> targets;

    public CreateOTAUpdateRequest addadditionalParametersEntry(String str, String str2) {
        if (this.additionalParameters == null) {
            this.additionalParameters = new HashMap();
        }
        if (!this.additionalParameters.containsKey(str)) {
            this.additionalParameters.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public CreateOTAUpdateRequest clearadditionalParametersEntries() {
        this.additionalParameters = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateOTAUpdateRequest)) {
            return false;
        }
        CreateOTAUpdateRequest createOTAUpdateRequest = (CreateOTAUpdateRequest) obj;
        if ((createOTAUpdateRequest.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getOtaUpdateId() != null && !createOTAUpdateRequest.getOtaUpdateId().equals(getOtaUpdateId())) {
            return false;
        }
        if ((createOTAUpdateRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getDescription() != null && !createOTAUpdateRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createOTAUpdateRequest.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getTargets() != null && !createOTAUpdateRequest.getTargets().equals(getTargets())) {
            return false;
        }
        if ((createOTAUpdateRequest.getTargetSelection() == null) ^ (getTargetSelection() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getTargetSelection() != null && !createOTAUpdateRequest.getTargetSelection().equals(getTargetSelection())) {
            return false;
        }
        if ((createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig() == null) ^ (getAwsJobExecutionsRolloutConfig() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig() != null && !createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig().equals(getAwsJobExecutionsRolloutConfig())) {
            return false;
        }
        if ((createOTAUpdateRequest.getFiles() == null) ^ (getFiles() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getFiles() != null && !createOTAUpdateRequest.getFiles().equals(getFiles())) {
            return false;
        }
        if ((createOTAUpdateRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getRoleArn() != null && !createOTAUpdateRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((createOTAUpdateRequest.getAdditionalParameters() == null) ^ (getAdditionalParameters() == null)) {
            return false;
        }
        return createOTAUpdateRequest.getAdditionalParameters() == null || createOTAUpdateRequest.getAdditionalParameters().equals(getAdditionalParameters());
    }

    public Map<String, String> getAdditionalParameters() {
        return this.additionalParameters;
    }

    public AwsJobExecutionsRolloutConfig getAwsJobExecutionsRolloutConfig() {
        return this.awsJobExecutionsRolloutConfig;
    }

    public String getDescription() {
        return this.description;
    }

    public List<OTAUpdateFile> getFiles() {
        return this.files;
    }

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getTargetSelection() {
        return this.targetSelection;
    }

    public List<String> getTargets() {
        return this.targets;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode()) + 31) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getTargets() == null ? 0 : getTargets().hashCode())) * 31) + (getTargetSelection() == null ? 0 : getTargetSelection().hashCode())) * 31) + (getAwsJobExecutionsRolloutConfig() == null ? 0 : getAwsJobExecutionsRolloutConfig().hashCode())) * 31) + (getFiles() == null ? 0 : getFiles().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31;
        if (getAdditionalParameters() != null) {
            i = getAdditionalParameters().hashCode();
        }
        return hashCode + i;
    }

    public void setAdditionalParameters(Map<String, String> map) {
        this.additionalParameters = map;
    }

    public void setAwsJobExecutionsRolloutConfig(AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig) {
        this.awsJobExecutionsRolloutConfig = awsJobExecutionsRolloutConfig;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setFiles(Collection<OTAUpdateFile> collection) {
        if (collection == null) {
            this.files = null;
        } else {
            this.files = new ArrayList(collection);
        }
    }

    public void setOtaUpdateId(String str) {
        this.otaUpdateId = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
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
        if (getDescription() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("description: ");
            outline1073.append(getDescription());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getTargets() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("targets: ");
            outline1074.append(getTargets());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTargetSelection() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("targetSelection: ");
            outline1075.append(getTargetSelection());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getAwsJobExecutionsRolloutConfig() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("awsJobExecutionsRolloutConfig: ");
            outline1076.append(getAwsJobExecutionsRolloutConfig());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getFiles() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("files: ");
            outline1077.append(getFiles());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1078.append(getRoleArn());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getAdditionalParameters() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("additionalParameters: ");
            outline1079.append(getAdditionalParameters());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateOTAUpdateRequest withAdditionalParameters(Map<String, String> map) {
        this.additionalParameters = map;
        return this;
    }

    public CreateOTAUpdateRequest withAwsJobExecutionsRolloutConfig(AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig) {
        this.awsJobExecutionsRolloutConfig = awsJobExecutionsRolloutConfig;
        return this;
    }

    public CreateOTAUpdateRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public CreateOTAUpdateRequest withFiles(OTAUpdateFile... oTAUpdateFileArr) {
        if (getFiles() == null) {
            this.files = new ArrayList(oTAUpdateFileArr.length);
        }
        for (OTAUpdateFile oTAUpdateFile : oTAUpdateFileArr) {
            this.files.add(oTAUpdateFile);
        }
        return this;
    }

    public CreateOTAUpdateRequest withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }

    public CreateOTAUpdateRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public CreateOTAUpdateRequest withTargetSelection(String str) {
        this.targetSelection = str;
        return this;
    }

    public CreateOTAUpdateRequest withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
    }

    public CreateOTAUpdateRequest withTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
        return this;
    }

    public CreateOTAUpdateRequest withFiles(Collection<OTAUpdateFile> collection) {
        setFiles(collection);
        return this;
    }

    public CreateOTAUpdateRequest withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }
}
