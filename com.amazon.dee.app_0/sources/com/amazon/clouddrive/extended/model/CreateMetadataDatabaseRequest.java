package com.amazon.clouddrive.extended.model;

import java.util.Objects;
/* loaded from: classes11.dex */
public class CreateMetadataDatabaseRequest {
    private boolean mForceCreate;
    private String mSchemaName;
    private String mSchemaVersion;

    public CreateMetadataDatabaseRequest(String str, String str2, boolean z) {
        this.mSchemaName = str;
        this.mSchemaVersion = str2;
        this.mForceCreate = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CreateMetadataDatabaseRequest.class != obj.getClass()) {
            return false;
        }
        CreateMetadataDatabaseRequest createMetadataDatabaseRequest = (CreateMetadataDatabaseRequest) obj;
        return this.mForceCreate == createMetadataDatabaseRequest.mForceCreate && Objects.equals(this.mSchemaName, createMetadataDatabaseRequest.mSchemaName) && Objects.equals(this.mSchemaVersion, createMetadataDatabaseRequest.mSchemaVersion);
    }

    public String getSchemaName() {
        return this.mSchemaName;
    }

    public String getSchemaVersion() {
        return this.mSchemaVersion;
    }

    public int hashCode() {
        return Objects.hash(this.mSchemaName, this.mSchemaVersion, Boolean.valueOf(this.mForceCreate));
    }

    public boolean isForceCreate() {
        return this.mForceCreate;
    }

    public void setForceCreate(boolean z) {
        this.mForceCreate = z;
    }

    public void setSchemaName(String str) {
        this.mSchemaName = str;
    }

    public void setSchemaVersion(String str) {
        this.mSchemaVersion = str;
    }
}
