package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class Destination implements Serializable {
    private S3Destination s3Destination;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Destination)) {
            return false;
        }
        Destination destination = (Destination) obj;
        if ((destination.getS3Destination() == null) ^ (getS3Destination() == null)) {
            return false;
        }
        return destination.getS3Destination() == null || destination.getS3Destination().equals(getS3Destination());
    }

    public S3Destination getS3Destination() {
        return this.s3Destination;
    }

    public int hashCode() {
        return 31 + (getS3Destination() == null ? 0 : getS3Destination().hashCode());
    }

    public void setS3Destination(S3Destination s3Destination) {
        this.s3Destination = s3Destination;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getS3Destination() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("s3Destination: ");
            outline1072.append(getS3Destination());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Destination withS3Destination(S3Destination s3Destination) {
        this.s3Destination = s3Destination;
        return this;
    }
}
