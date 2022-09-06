package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class UpdateStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String description;
    private List<StreamFile> files;
    private String roleArn;
    private String streamId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateStreamRequest)) {
            return false;
        }
        UpdateStreamRequest updateStreamRequest = (UpdateStreamRequest) obj;
        if ((updateStreamRequest.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (updateStreamRequest.getStreamId() != null && !updateStreamRequest.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((updateStreamRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (updateStreamRequest.getDescription() != null && !updateStreamRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((updateStreamRequest.getFiles() == null) ^ (getFiles() == null)) {
            return false;
        }
        if (updateStreamRequest.getFiles() != null && !updateStreamRequest.getFiles().equals(getFiles())) {
            return false;
        }
        if ((updateStreamRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return updateStreamRequest.getRoleArn() == null || updateStreamRequest.getRoleArn().equals(getRoleArn());
    }

    public String getDescription() {
        return this.description;
    }

    public List<StreamFile> getFiles() {
        return this.files;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public String getStreamId() {
        return this.streamId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getFiles() == null ? 0 : getFiles().hashCode())) * 31;
        if (getRoleArn() != null) {
            i = getRoleArn().hashCode();
        }
        return hashCode + i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setFiles(Collection<StreamFile> collection) {
        if (collection == null) {
            this.files = null;
        } else {
            this.files = new ArrayList(collection);
        }
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("streamId: ");
            outline1072.append(getStreamId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDescription() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("description: ");
            outline1073.append(getDescription());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getFiles() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("files: ");
            outline1074.append(getFiles());
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

    public UpdateStreamRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public UpdateStreamRequest withFiles(StreamFile... streamFileArr) {
        if (getFiles() == null) {
            this.files = new ArrayList(streamFileArr.length);
        }
        for (StreamFile streamFile : streamFileArr) {
            this.files.add(streamFile);
        }
        return this;
    }

    public UpdateStreamRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public UpdateStreamRequest withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public UpdateStreamRequest withFiles(Collection<StreamFile> collection) {
        setFiles(collection);
        return this;
    }
}
