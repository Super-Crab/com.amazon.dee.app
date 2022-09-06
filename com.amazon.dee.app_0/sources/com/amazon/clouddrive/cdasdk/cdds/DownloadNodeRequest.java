package com.amazon.clouddrive.cdasdk.cdds;

import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
/* loaded from: classes11.dex */
public class DownloadNodeRequest extends ServiceRequest {
    @JsonProperty("download")
    private Boolean download;
    @JsonProperty("id")
    private String id;
    @JsonProperty(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_DISPOSITION)
    private String responseContentDisposition;
    @JsonProperty(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_TYPE)
    private String responseContentType;
    @JsonProperty("viewBox")
    private String viewBox;

    public DownloadNodeRequest(@NonNull String str) {
        if (str != null) {
            this.id = str;
            return;
        }
        throw new NullPointerException("nodeId is marked non-null but is null");
    }

    @Override // com.amazon.clouddrive.cdasdk.cdds.ServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof DownloadNodeRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdds.ServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownloadNodeRequest)) {
            return false;
        }
        DownloadNodeRequest downloadNodeRequest = (DownloadNodeRequest) obj;
        if (!downloadNodeRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String id = getId();
        String id2 = downloadNodeRequest.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        String viewBox = getViewBox();
        String viewBox2 = downloadNodeRequest.getViewBox();
        if (viewBox != null ? !viewBox.equals(viewBox2) : viewBox2 != null) {
            return false;
        }
        Boolean download = getDownload();
        Boolean download2 = downloadNodeRequest.getDownload();
        if (download != null ? !download.equals(download2) : download2 != null) {
            return false;
        }
        String responseContentType = getResponseContentType();
        String responseContentType2 = downloadNodeRequest.getResponseContentType();
        if (responseContentType != null ? !responseContentType.equals(responseContentType2) : responseContentType2 != null) {
            return false;
        }
        String responseContentDisposition = getResponseContentDisposition();
        String responseContentDisposition2 = downloadNodeRequest.getResponseContentDisposition();
        return responseContentDisposition != null ? responseContentDisposition.equals(responseContentDisposition2) : responseContentDisposition2 == null;
    }

    public Boolean getDownload() {
        return this.download;
    }

    public String getId() {
        return this.id;
    }

    public String getResponseContentDisposition() {
        return this.responseContentDisposition;
    }

    public String getResponseContentType() {
        return this.responseContentType;
    }

    public String getViewBox() {
        return this.viewBox;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdds.ServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String id = getId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (id == null ? 43 : id.hashCode());
        String viewBox = getViewBox();
        int hashCode3 = (hashCode2 * 59) + (viewBox == null ? 43 : viewBox.hashCode());
        Boolean download = getDownload();
        int hashCode4 = (hashCode3 * 59) + (download == null ? 43 : download.hashCode());
        String responseContentType = getResponseContentType();
        int hashCode5 = (hashCode4 * 59) + (responseContentType == null ? 43 : responseContentType.hashCode());
        String responseContentDisposition = getResponseContentDisposition();
        int i2 = hashCode5 * 59;
        if (responseContentDisposition != null) {
            i = responseContentDisposition.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("download")
    public void setDownload(Boolean bool) {
        this.download = bool;
    }

    @JsonProperty("id")
    public void setId(String str) {
        this.id = str;
    }

    @JsonProperty(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_DISPOSITION)
    public void setResponseContentDisposition(String str) {
        this.responseContentDisposition = str;
    }

    @JsonProperty(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_TYPE)
    public void setResponseContentType(String str) {
        this.responseContentType = str;
    }

    @JsonProperty("viewBox")
    public void setViewBox(String str) {
        this.viewBox = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdds.ServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DownloadNodeRequest(id=");
        outline107.append(getId());
        outline107.append(", viewBox=");
        outline107.append(getViewBox());
        outline107.append(", download=");
        outline107.append(getDownload());
        outline107.append(", responseContentType=");
        outline107.append(getResponseContentType());
        outline107.append(", responseContentDisposition=");
        outline107.append(getResponseContentDisposition());
        outline107.append(")");
        return outline107.toString();
    }
}
