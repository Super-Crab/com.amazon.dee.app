package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class StartSigningJobParameter implements Serializable {
    private Destination destination;
    private String signingProfileName;
    private SigningProfileParameter signingProfileParameter;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StartSigningJobParameter)) {
            return false;
        }
        StartSigningJobParameter startSigningJobParameter = (StartSigningJobParameter) obj;
        if ((startSigningJobParameter.getSigningProfileParameter() == null) ^ (getSigningProfileParameter() == null)) {
            return false;
        }
        if (startSigningJobParameter.getSigningProfileParameter() != null && !startSigningJobParameter.getSigningProfileParameter().equals(getSigningProfileParameter())) {
            return false;
        }
        if ((startSigningJobParameter.getSigningProfileName() == null) ^ (getSigningProfileName() == null)) {
            return false;
        }
        if (startSigningJobParameter.getSigningProfileName() != null && !startSigningJobParameter.getSigningProfileName().equals(getSigningProfileName())) {
            return false;
        }
        if ((startSigningJobParameter.getDestination() == null) ^ (getDestination() == null)) {
            return false;
        }
        return startSigningJobParameter.getDestination() == null || startSigningJobParameter.getDestination().equals(getDestination());
    }

    public Destination getDestination() {
        return this.destination;
    }

    public String getSigningProfileName() {
        return this.signingProfileName;
    }

    public SigningProfileParameter getSigningProfileParameter() {
        return this.signingProfileParameter;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getSigningProfileParameter() == null ? 0 : getSigningProfileParameter().hashCode()) + 31) * 31) + (getSigningProfileName() == null ? 0 : getSigningProfileName().hashCode())) * 31;
        if (getDestination() != null) {
            i = getDestination().hashCode();
        }
        return hashCode + i;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setSigningProfileName(String str) {
        this.signingProfileName = str;
    }

    public void setSigningProfileParameter(SigningProfileParameter signingProfileParameter) {
        this.signingProfileParameter = signingProfileParameter;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSigningProfileParameter() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("signingProfileParameter: ");
            outline1072.append(getSigningProfileParameter());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSigningProfileName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("signingProfileName: ");
            outline1073.append(getSigningProfileName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDestination() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("destination: ");
            outline1074.append(getDestination());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public StartSigningJobParameter withDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public StartSigningJobParameter withSigningProfileName(String str) {
        this.signingProfileName = str;
        return this;
    }

    public StartSigningJobParameter withSigningProfileParameter(SigningProfileParameter signingProfileParameter) {
        this.signingProfileParameter = signingProfileParameter;
        return this;
    }
}
