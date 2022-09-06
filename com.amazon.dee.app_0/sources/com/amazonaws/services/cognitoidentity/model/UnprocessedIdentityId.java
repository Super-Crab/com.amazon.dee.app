package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UnprocessedIdentityId implements Serializable {
    private String errorCode;
    private String identityId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnprocessedIdentityId)) {
            return false;
        }
        UnprocessedIdentityId unprocessedIdentityId = (UnprocessedIdentityId) obj;
        if ((unprocessedIdentityId.getIdentityId() == null) ^ (getIdentityId() == null)) {
            return false;
        }
        if (unprocessedIdentityId.getIdentityId() != null && !unprocessedIdentityId.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        if ((unprocessedIdentityId.getErrorCode() == null) ^ (getErrorCode() == null)) {
            return false;
        }
        return unprocessedIdentityId.getErrorCode() == null || unprocessedIdentityId.getErrorCode().equals(getErrorCode());
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31;
        if (getErrorCode() != null) {
            i = getErrorCode().hashCode();
        }
        return hashCode + i;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityId: ");
            outline1072.append(getIdentityId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getErrorCode() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ErrorCode: ");
            outline1073.append(getErrorCode());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UnprocessedIdentityId withErrorCode(String str) {
        this.errorCode = str;
        return this;
    }

    public UnprocessedIdentityId withIdentityId(String str) {
        this.identityId = str;
        return this;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode.toString();
    }

    public UnprocessedIdentityId withErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode.toString();
        return this;
    }
}
