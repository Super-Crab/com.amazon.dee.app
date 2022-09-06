package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class GetMetadataDatabaseStatusRequest {
    private String mStatusURI;

    public GetMetadataDatabaseStatusRequest(String str) {
        this.mStatusURI = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && GetMetadataDatabaseStatusRequest.class == obj.getClass()) {
            return this.mStatusURI.equals(((GetMetadataDatabaseStatusRequest) obj).mStatusURI);
        }
        return false;
    }

    public String getStatusURI() {
        return this.mStatusURI;
    }

    public int hashCode() {
        return this.mStatusURI.hashCode();
    }

    public void setStatusURI(String str) {
        this.mStatusURI = str;
    }
}
