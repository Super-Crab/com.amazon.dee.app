package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class FileLocation implements Serializable {
    private S3Location s3Location;
    private Stream stream;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FileLocation)) {
            return false;
        }
        FileLocation fileLocation = (FileLocation) obj;
        if ((fileLocation.getStream() == null) ^ (getStream() == null)) {
            return false;
        }
        if (fileLocation.getStream() != null && !fileLocation.getStream().equals(getStream())) {
            return false;
        }
        if ((fileLocation.getS3Location() == null) ^ (getS3Location() == null)) {
            return false;
        }
        return fileLocation.getS3Location() == null || fileLocation.getS3Location().equals(getS3Location());
    }

    public S3Location getS3Location() {
        return this.s3Location;
    }

    public Stream getStream() {
        return this.stream;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStream() == null ? 0 : getStream().hashCode()) + 31) * 31;
        if (getS3Location() != null) {
            i = getS3Location().hashCode();
        }
        return hashCode + i;
    }

    public void setS3Location(S3Location s3Location) {
        this.s3Location = s3Location;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStream() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("stream: ");
            outline1072.append(getStream());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getS3Location() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("s3Location: ");
            outline1073.append(getS3Location());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public FileLocation withS3Location(S3Location s3Location) {
        this.s3Location = s3Location;
        return this;
    }

    public FileLocation withStream(Stream stream) {
        this.stream = stream;
        return this;
    }
}
