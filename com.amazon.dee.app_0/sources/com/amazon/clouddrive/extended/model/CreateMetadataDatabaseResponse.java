package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class CreateMetadataDatabaseResponse {
    private String mStatusUri;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && CreateMetadataDatabaseResponse.class == obj.getClass()) {
            return this.mStatusUri.equals(((CreateMetadataDatabaseResponse) obj).mStatusUri);
        }
        return false;
    }

    public String getStatusUri() {
        return this.mStatusUri;
    }

    public int hashCode() {
        return this.mStatusUri.hashCode();
    }

    public void setStatusUri(String str) {
        this.mStatusUri = str;
    }
}
