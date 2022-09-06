package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class Destination implements Serializable {
    private String accessPolicy;
    private String arn;
    private Long creationTime;
    private String destinationName;
    private String roleArn;
    private String targetArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Destination)) {
            return false;
        }
        Destination destination = (Destination) obj;
        if ((destination.getDestinationName() == null) ^ (getDestinationName() == null)) {
            return false;
        }
        if (destination.getDestinationName() != null && !destination.getDestinationName().equals(getDestinationName())) {
            return false;
        }
        if ((destination.getTargetArn() == null) ^ (getTargetArn() == null)) {
            return false;
        }
        if (destination.getTargetArn() != null && !destination.getTargetArn().equals(getTargetArn())) {
            return false;
        }
        if ((destination.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (destination.getRoleArn() != null && !destination.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((destination.getAccessPolicy() == null) ^ (getAccessPolicy() == null)) {
            return false;
        }
        if (destination.getAccessPolicy() != null && !destination.getAccessPolicy().equals(getAccessPolicy())) {
            return false;
        }
        if ((destination.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        if (destination.getArn() != null && !destination.getArn().equals(getArn())) {
            return false;
        }
        if ((destination.getCreationTime() == null) ^ (getCreationTime() == null)) {
            return false;
        }
        return destination.getCreationTime() == null || destination.getCreationTime().equals(getCreationTime());
    }

    public String getAccessPolicy() {
        return this.accessPolicy;
    }

    public String getArn() {
        return this.arn;
    }

    public Long getCreationTime() {
        return this.creationTime;
    }

    public String getDestinationName() {
        return this.destinationName;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getTargetArn() {
        return this.targetArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getDestinationName() == null ? 0 : getDestinationName().hashCode()) + 31) * 31) + (getTargetArn() == null ? 0 : getTargetArn().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getAccessPolicy() == null ? 0 : getAccessPolicy().hashCode())) * 31) + (getArn() == null ? 0 : getArn().hashCode())) * 31;
        if (getCreationTime() != null) {
            i = getCreationTime().hashCode();
        }
        return hashCode + i;
    }

    public void setAccessPolicy(String str) {
        this.accessPolicy = str;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public void setCreationTime(Long l) {
        this.creationTime = l;
    }

    public void setDestinationName(String str) {
        this.destinationName = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setTargetArn(String str) {
        this.targetArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDestinationName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("destinationName: ");
            outline1072.append(getDestinationName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTargetArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("targetArn: ");
            outline1073.append(getTargetArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getRoleArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1074.append(getRoleArn());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getAccessPolicy() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("accessPolicy: ");
            outline1075.append(getAccessPolicy());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getArn() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("arn: ");
            outline1076.append(getArn());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getCreationTime() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("creationTime: ");
            outline1077.append(getCreationTime());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Destination withAccessPolicy(String str) {
        this.accessPolicy = str;
        return this;
    }

    public Destination withArn(String str) {
        this.arn = str;
        return this;
    }

    public Destination withCreationTime(Long l) {
        this.creationTime = l;
        return this;
    }

    public Destination withDestinationName(String str) {
        this.destinationName = str;
        return this;
    }

    public Destination withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public Destination withTargetArn(String str) {
        this.targetArn = str;
        return this;
    }
}
