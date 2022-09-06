package com.amazon.clouddrive.cdasdk.cdus;

import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class InitiateMultipartResponse {
    @JsonProperty("multipartUploadExpirationTime")
    private String multipartUploadExpirationTime;
    @JsonProperty("multipartUploadStartTime")
    private String multipartUploadStartTime;
    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    private String nodeId;
    @JsonProperty("partSize")
    private Long partSize;
    @JsonProperty("totalNumberOfParts")
    private Long totalNumberOfParts;
    @JsonProperty("uploadId")
    private String uploadId;

    protected boolean canEqual(Object obj) {
        return obj instanceof InitiateMultipartResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InitiateMultipartResponse)) {
            return false;
        }
        InitiateMultipartResponse initiateMultipartResponse = (InitiateMultipartResponse) obj;
        if (!initiateMultipartResponse.canEqual(this)) {
            return false;
        }
        String nodeId = getNodeId();
        String nodeId2 = initiateMultipartResponse.getNodeId();
        if (nodeId != null ? !nodeId.equals(nodeId2) : nodeId2 != null) {
            return false;
        }
        String uploadId = getUploadId();
        String uploadId2 = initiateMultipartResponse.getUploadId();
        if (uploadId != null ? !uploadId.equals(uploadId2) : uploadId2 != null) {
            return false;
        }
        Long partSize = getPartSize();
        Long partSize2 = initiateMultipartResponse.getPartSize();
        if (partSize != null ? !partSize.equals(partSize2) : partSize2 != null) {
            return false;
        }
        Long totalNumberOfParts = getTotalNumberOfParts();
        Long totalNumberOfParts2 = initiateMultipartResponse.getTotalNumberOfParts();
        if (totalNumberOfParts != null ? !totalNumberOfParts.equals(totalNumberOfParts2) : totalNumberOfParts2 != null) {
            return false;
        }
        String multipartUploadStartTime = getMultipartUploadStartTime();
        String multipartUploadStartTime2 = initiateMultipartResponse.getMultipartUploadStartTime();
        if (multipartUploadStartTime != null ? !multipartUploadStartTime.equals(multipartUploadStartTime2) : multipartUploadStartTime2 != null) {
            return false;
        }
        String multipartUploadExpirationTime = getMultipartUploadExpirationTime();
        String multipartUploadExpirationTime2 = initiateMultipartResponse.getMultipartUploadExpirationTime();
        return multipartUploadExpirationTime != null ? multipartUploadExpirationTime.equals(multipartUploadExpirationTime2) : multipartUploadExpirationTime2 == null;
    }

    public String getMultipartUploadExpirationTime() {
        return this.multipartUploadExpirationTime;
    }

    public String getMultipartUploadStartTime() {
        return this.multipartUploadStartTime;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public Long getPartSize() {
        return this.partSize;
    }

    public Long getTotalNumberOfParts() {
        return this.totalNumberOfParts;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public int hashCode() {
        String nodeId = getNodeId();
        int i = 43;
        int hashCode = nodeId == null ? 43 : nodeId.hashCode();
        String uploadId = getUploadId();
        int hashCode2 = ((hashCode + 59) * 59) + (uploadId == null ? 43 : uploadId.hashCode());
        Long partSize = getPartSize();
        int hashCode3 = (hashCode2 * 59) + (partSize == null ? 43 : partSize.hashCode());
        Long totalNumberOfParts = getTotalNumberOfParts();
        int hashCode4 = (hashCode3 * 59) + (totalNumberOfParts == null ? 43 : totalNumberOfParts.hashCode());
        String multipartUploadStartTime = getMultipartUploadStartTime();
        int hashCode5 = (hashCode4 * 59) + (multipartUploadStartTime == null ? 43 : multipartUploadStartTime.hashCode());
        String multipartUploadExpirationTime = getMultipartUploadExpirationTime();
        int i2 = hashCode5 * 59;
        if (multipartUploadExpirationTime != null) {
            i = multipartUploadExpirationTime.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("multipartUploadExpirationTime")
    public void setMultipartUploadExpirationTime(String str) {
        this.multipartUploadExpirationTime = str;
    }

    @JsonProperty("multipartUploadStartTime")
    public void setMultipartUploadStartTime(String str) {
        this.multipartUploadStartTime = str;
    }

    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    public void setNodeId(String str) {
        this.nodeId = str;
    }

    @JsonProperty("partSize")
    public void setPartSize(Long l) {
        this.partSize = l;
    }

    @JsonProperty("totalNumberOfParts")
    public void setTotalNumberOfParts(Long l) {
        this.totalNumberOfParts = l;
    }

    @JsonProperty("uploadId")
    public void setUploadId(String str) {
        this.uploadId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InitiateMultipartResponse(nodeId=");
        outline107.append(getNodeId());
        outline107.append(", uploadId=");
        outline107.append(getUploadId());
        outline107.append(", partSize=");
        outline107.append(getPartSize());
        outline107.append(", totalNumberOfParts=");
        outline107.append(getTotalNumberOfParts());
        outline107.append(", multipartUploadStartTime=");
        outline107.append(getMultipartUploadStartTime());
        outline107.append(", multipartUploadExpirationTime=");
        outline107.append(getMultipartUploadExpirationTime());
        outline107.append(")");
        return outline107.toString();
    }
}
