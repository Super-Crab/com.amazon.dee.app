package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CodeSigning implements Serializable {
    private String awsSignerJobId;
    private CustomCodeSigning customCodeSigning;
    private StartSigningJobParameter startSigningJobParameter;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CodeSigning)) {
            return false;
        }
        CodeSigning codeSigning = (CodeSigning) obj;
        if ((codeSigning.getAwsSignerJobId() == null) ^ (getAwsSignerJobId() == null)) {
            return false;
        }
        if (codeSigning.getAwsSignerJobId() != null && !codeSigning.getAwsSignerJobId().equals(getAwsSignerJobId())) {
            return false;
        }
        if ((codeSigning.getStartSigningJobParameter() == null) ^ (getStartSigningJobParameter() == null)) {
            return false;
        }
        if (codeSigning.getStartSigningJobParameter() != null && !codeSigning.getStartSigningJobParameter().equals(getStartSigningJobParameter())) {
            return false;
        }
        if ((codeSigning.getCustomCodeSigning() == null) ^ (getCustomCodeSigning() == null)) {
            return false;
        }
        return codeSigning.getCustomCodeSigning() == null || codeSigning.getCustomCodeSigning().equals(getCustomCodeSigning());
    }

    public String getAwsSignerJobId() {
        return this.awsSignerJobId;
    }

    public CustomCodeSigning getCustomCodeSigning() {
        return this.customCodeSigning;
    }

    public StartSigningJobParameter getStartSigningJobParameter() {
        return this.startSigningJobParameter;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getAwsSignerJobId() == null ? 0 : getAwsSignerJobId().hashCode()) + 31) * 31) + (getStartSigningJobParameter() == null ? 0 : getStartSigningJobParameter().hashCode())) * 31;
        if (getCustomCodeSigning() != null) {
            i = getCustomCodeSigning().hashCode();
        }
        return hashCode + i;
    }

    public void setAwsSignerJobId(String str) {
        this.awsSignerJobId = str;
    }

    public void setCustomCodeSigning(CustomCodeSigning customCodeSigning) {
        this.customCodeSigning = customCodeSigning;
    }

    public void setStartSigningJobParameter(StartSigningJobParameter startSigningJobParameter) {
        this.startSigningJobParameter = startSigningJobParameter;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAwsSignerJobId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("awsSignerJobId: ");
            outline1072.append(getAwsSignerJobId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getStartSigningJobParameter() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("startSigningJobParameter: ");
            outline1073.append(getStartSigningJobParameter());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCustomCodeSigning() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("customCodeSigning: ");
            outline1074.append(getCustomCodeSigning());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CodeSigning withAwsSignerJobId(String str) {
        this.awsSignerJobId = str;
        return this;
    }

    public CodeSigning withCustomCodeSigning(CustomCodeSigning customCodeSigning) {
        this.customCodeSigning = customCodeSigning;
        return this;
    }

    public CodeSigning withStartSigningJobParameter(StartSigningJobParameter startSigningJobParameter) {
        this.startSigningJobParameter = startSigningJobParameter;
        return this;
    }
}
