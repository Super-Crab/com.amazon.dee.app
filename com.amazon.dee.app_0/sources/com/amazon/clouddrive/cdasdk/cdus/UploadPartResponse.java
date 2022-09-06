package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class UploadPartResponse {
    @JsonProperty("serverSidePartMD5")
    private String serverSidePartMD5;
    @JsonProperty("serverSidePartSize")
    private String serverSidePartSize;
    @JsonProperty("status")
    private String status;

    protected boolean canEqual(Object obj) {
        return obj instanceof UploadPartResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UploadPartResponse)) {
            return false;
        }
        UploadPartResponse uploadPartResponse = (UploadPartResponse) obj;
        if (!uploadPartResponse.canEqual(this)) {
            return false;
        }
        String status = getStatus();
        String status2 = uploadPartResponse.getStatus();
        if (status != null ? !status.equals(status2) : status2 != null) {
            return false;
        }
        String serverSidePartMD5 = getServerSidePartMD5();
        String serverSidePartMD52 = uploadPartResponse.getServerSidePartMD5();
        if (serverSidePartMD5 != null ? !serverSidePartMD5.equals(serverSidePartMD52) : serverSidePartMD52 != null) {
            return false;
        }
        String serverSidePartSize = getServerSidePartSize();
        String serverSidePartSize2 = uploadPartResponse.getServerSidePartSize();
        return serverSidePartSize != null ? serverSidePartSize.equals(serverSidePartSize2) : serverSidePartSize2 == null;
    }

    public String getServerSidePartMD5() {
        return this.serverSidePartMD5;
    }

    public String getServerSidePartSize() {
        return this.serverSidePartSize;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        String status = getStatus();
        int i = 43;
        int hashCode = status == null ? 43 : status.hashCode();
        String serverSidePartMD5 = getServerSidePartMD5();
        int hashCode2 = ((hashCode + 59) * 59) + (serverSidePartMD5 == null ? 43 : serverSidePartMD5.hashCode());
        String serverSidePartSize = getServerSidePartSize();
        int i2 = hashCode2 * 59;
        if (serverSidePartSize != null) {
            i = serverSidePartSize.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("serverSidePartMD5")
    public void setServerSidePartMD5(String str) {
        this.serverSidePartMD5 = str;
    }

    @JsonProperty("serverSidePartSize")
    public void setServerSidePartSize(String str) {
        this.serverSidePartSize = str;
    }

    @JsonProperty("status")
    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploadPartResponse(status=");
        outline107.append(getStatus());
        outline107.append(", serverSidePartMD5=");
        outline107.append(getServerSidePartMD5());
        outline107.append(", serverSidePartSize=");
        outline107.append(getServerSidePartSize());
        outline107.append(")");
        return outline107.toString();
    }
}
