package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class RetrieveMultipartResponse {
    @JsonProperty("completeMultipartUploadExpirationTime")
    private String completeMultipartUploadExpirationTime;
    @JsonProperty("completeMultipartUploadStartTime")
    private String completeMultipartUploadStartTime;
    @JsonProperty("missingParts")
    private List<Long> missingParts;
    @JsonProperty("multipartUploadExpirationTime")
    private String multipartUploadExpirationTime;
    @JsonProperty("multipartUploadStartTime")
    private String multipartUploadStartTime;
    @JsonProperty("partSize")
    private Long partSize;
    @JsonProperty("status")
    private String status;
    @JsonProperty("totalNumberOfParts")
    private Long totalNumberOfParts;
    @JsonProperty("uploadErrorDetails")
    private UploadErrorDetails uploadErrorDetails;

    /* loaded from: classes11.dex */
    public static class UploadErrorDetails {
        @JsonProperty("errorCode")
        private String errorCode;
        @JsonProperty("errorMessage")
        private String errorMessage;

        protected boolean canEqual(Object obj) {
            return obj instanceof UploadErrorDetails;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UploadErrorDetails)) {
                return false;
            }
            UploadErrorDetails uploadErrorDetails = (UploadErrorDetails) obj;
            if (!uploadErrorDetails.canEqual(this)) {
                return false;
            }
            String errorCode = getErrorCode();
            String errorCode2 = uploadErrorDetails.getErrorCode();
            if (errorCode != null ? !errorCode.equals(errorCode2) : errorCode2 != null) {
                return false;
            }
            String errorMessage = getErrorMessage();
            String errorMessage2 = uploadErrorDetails.getErrorMessage();
            return errorMessage != null ? errorMessage.equals(errorMessage2) : errorMessage2 == null;
        }

        public String getErrorCode() {
            return this.errorCode;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }

        public int hashCode() {
            String errorCode = getErrorCode();
            int i = 43;
            int hashCode = errorCode == null ? 43 : errorCode.hashCode();
            String errorMessage = getErrorMessage();
            int i2 = (hashCode + 59) * 59;
            if (errorMessage != null) {
                i = errorMessage.hashCode();
            }
            return i2 + i;
        }

        @JsonProperty("errorCode")
        public void setErrorCode(String str) {
            this.errorCode = str;
        }

        @JsonProperty("errorMessage")
        public void setErrorMessage(String str) {
            this.errorMessage = str;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RetrieveMultipartResponse.UploadErrorDetails(errorCode=");
            outline107.append(getErrorCode());
            outline107.append(", errorMessage=");
            outline107.append(getErrorMessage());
            outline107.append(")");
            return outline107.toString();
        }
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof RetrieveMultipartResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RetrieveMultipartResponse)) {
            return false;
        }
        RetrieveMultipartResponse retrieveMultipartResponse = (RetrieveMultipartResponse) obj;
        if (!retrieveMultipartResponse.canEqual(this)) {
            return false;
        }
        String status = getStatus();
        String status2 = retrieveMultipartResponse.getStatus();
        if (status != null ? !status.equals(status2) : status2 != null) {
            return false;
        }
        Long totalNumberOfParts = getTotalNumberOfParts();
        Long totalNumberOfParts2 = retrieveMultipartResponse.getTotalNumberOfParts();
        if (totalNumberOfParts != null ? !totalNumberOfParts.equals(totalNumberOfParts2) : totalNumberOfParts2 != null) {
            return false;
        }
        Long partSize = getPartSize();
        Long partSize2 = retrieveMultipartResponse.getPartSize();
        if (partSize != null ? !partSize.equals(partSize2) : partSize2 != null) {
            return false;
        }
        String multipartUploadStartTime = getMultipartUploadStartTime();
        String multipartUploadStartTime2 = retrieveMultipartResponse.getMultipartUploadStartTime();
        if (multipartUploadStartTime != null ? !multipartUploadStartTime.equals(multipartUploadStartTime2) : multipartUploadStartTime2 != null) {
            return false;
        }
        String multipartUploadExpirationTime = getMultipartUploadExpirationTime();
        String multipartUploadExpirationTime2 = retrieveMultipartResponse.getMultipartUploadExpirationTime();
        if (multipartUploadExpirationTime != null ? !multipartUploadExpirationTime.equals(multipartUploadExpirationTime2) : multipartUploadExpirationTime2 != null) {
            return false;
        }
        String completeMultipartUploadStartTime = getCompleteMultipartUploadStartTime();
        String completeMultipartUploadStartTime2 = retrieveMultipartResponse.getCompleteMultipartUploadStartTime();
        if (completeMultipartUploadStartTime != null ? !completeMultipartUploadStartTime.equals(completeMultipartUploadStartTime2) : completeMultipartUploadStartTime2 != null) {
            return false;
        }
        String completeMultipartUploadExpirationTime = getCompleteMultipartUploadExpirationTime();
        String completeMultipartUploadExpirationTime2 = retrieveMultipartResponse.getCompleteMultipartUploadExpirationTime();
        if (completeMultipartUploadExpirationTime != null ? !completeMultipartUploadExpirationTime.equals(completeMultipartUploadExpirationTime2) : completeMultipartUploadExpirationTime2 != null) {
            return false;
        }
        List<Long> missingParts = getMissingParts();
        List<Long> missingParts2 = retrieveMultipartResponse.getMissingParts();
        if (missingParts != null ? !missingParts.equals(missingParts2) : missingParts2 != null) {
            return false;
        }
        UploadErrorDetails uploadErrorDetails = getUploadErrorDetails();
        UploadErrorDetails uploadErrorDetails2 = retrieveMultipartResponse.getUploadErrorDetails();
        return uploadErrorDetails != null ? uploadErrorDetails.equals(uploadErrorDetails2) : uploadErrorDetails2 == null;
    }

    public String getCompleteMultipartUploadExpirationTime() {
        return this.completeMultipartUploadExpirationTime;
    }

    public String getCompleteMultipartUploadStartTime() {
        return this.completeMultipartUploadStartTime;
    }

    public List<Long> getMissingParts() {
        return this.missingParts;
    }

    public String getMultipartUploadExpirationTime() {
        return this.multipartUploadExpirationTime;
    }

    public String getMultipartUploadStartTime() {
        return this.multipartUploadStartTime;
    }

    public Long getPartSize() {
        return this.partSize;
    }

    public String getStatus() {
        return this.status;
    }

    public Long getTotalNumberOfParts() {
        return this.totalNumberOfParts;
    }

    public UploadErrorDetails getUploadErrorDetails() {
        return this.uploadErrorDetails;
    }

    public int hashCode() {
        String status = getStatus();
        int i = 43;
        int hashCode = status == null ? 43 : status.hashCode();
        Long totalNumberOfParts = getTotalNumberOfParts();
        int hashCode2 = ((hashCode + 59) * 59) + (totalNumberOfParts == null ? 43 : totalNumberOfParts.hashCode());
        Long partSize = getPartSize();
        int hashCode3 = (hashCode2 * 59) + (partSize == null ? 43 : partSize.hashCode());
        String multipartUploadStartTime = getMultipartUploadStartTime();
        int hashCode4 = (hashCode3 * 59) + (multipartUploadStartTime == null ? 43 : multipartUploadStartTime.hashCode());
        String multipartUploadExpirationTime = getMultipartUploadExpirationTime();
        int hashCode5 = (hashCode4 * 59) + (multipartUploadExpirationTime == null ? 43 : multipartUploadExpirationTime.hashCode());
        String completeMultipartUploadStartTime = getCompleteMultipartUploadStartTime();
        int hashCode6 = (hashCode5 * 59) + (completeMultipartUploadStartTime == null ? 43 : completeMultipartUploadStartTime.hashCode());
        String completeMultipartUploadExpirationTime = getCompleteMultipartUploadExpirationTime();
        int hashCode7 = (hashCode6 * 59) + (completeMultipartUploadExpirationTime == null ? 43 : completeMultipartUploadExpirationTime.hashCode());
        List<Long> missingParts = getMissingParts();
        int hashCode8 = (hashCode7 * 59) + (missingParts == null ? 43 : missingParts.hashCode());
        UploadErrorDetails uploadErrorDetails = getUploadErrorDetails();
        int i2 = hashCode8 * 59;
        if (uploadErrorDetails != null) {
            i = uploadErrorDetails.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("completeMultipartUploadExpirationTime")
    public void setCompleteMultipartUploadExpirationTime(String str) {
        this.completeMultipartUploadExpirationTime = str;
    }

    @JsonProperty("completeMultipartUploadStartTime")
    public void setCompleteMultipartUploadStartTime(String str) {
        this.completeMultipartUploadStartTime = str;
    }

    @JsonProperty("missingParts")
    public void setMissingParts(List<Long> list) {
        this.missingParts = list;
    }

    @JsonProperty("multipartUploadExpirationTime")
    public void setMultipartUploadExpirationTime(String str) {
        this.multipartUploadExpirationTime = str;
    }

    @JsonProperty("multipartUploadStartTime")
    public void setMultipartUploadStartTime(String str) {
        this.multipartUploadStartTime = str;
    }

    @JsonProperty("partSize")
    public void setPartSize(Long l) {
        this.partSize = l;
    }

    @JsonProperty("status")
    public void setStatus(String str) {
        this.status = str;
    }

    @JsonProperty("totalNumberOfParts")
    public void setTotalNumberOfParts(Long l) {
        this.totalNumberOfParts = l;
    }

    @JsonProperty("uploadErrorDetails")
    public void setUploadErrorDetails(UploadErrorDetails uploadErrorDetails) {
        this.uploadErrorDetails = uploadErrorDetails;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RetrieveMultipartResponse(status=");
        outline107.append(getStatus());
        outline107.append(", totalNumberOfParts=");
        outline107.append(getTotalNumberOfParts());
        outline107.append(", partSize=");
        outline107.append(getPartSize());
        outline107.append(", multipartUploadStartTime=");
        outline107.append(getMultipartUploadStartTime());
        outline107.append(", multipartUploadExpirationTime=");
        outline107.append(getMultipartUploadExpirationTime());
        outline107.append(", completeMultipartUploadStartTime=");
        outline107.append(getCompleteMultipartUploadStartTime());
        outline107.append(", completeMultipartUploadExpirationTime=");
        outline107.append(getCompleteMultipartUploadExpirationTime());
        outline107.append(", missingParts=");
        outline107.append(getMissingParts());
        outline107.append(", uploadErrorDetails=");
        outline107.append(getUploadErrorDetails());
        outline107.append(")");
        return outline107.toString();
    }
}
