package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.EditableNodeRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.amazon.clouddrive.model.UploadFileRequest;
import java.io.InputStream;
import java.util.Objects;
/* loaded from: classes11.dex */
public class UploadFileExtendedRequest extends UploadFileRequest {
    private boolean mAddToFamilyArchive;
    private String mContentSignature;
    private String mContentSignatureType;

    public UploadFileExtendedRequest(String str, InputStream inputStream, long j) {
        super(str, inputStream, j);
    }

    public boolean addToFamilyArchive() {
        return this.mAddToFamilyArchive;
    }

    @Override // com.amazon.clouddrive.model.UploadFileRequest, com.amazon.clouddrive.model.PostNodeRequest, com.amazon.clouddrive.model.EditableNodeRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof UploadFileExtendedRequest) && compareTo((EditableNodeRequest) ((UploadFileExtendedRequest) obj)) == 0;
    }

    public String getContentSignature() {
        return this.mContentSignature;
    }

    public String getContentSignatureType() {
        return this.mContentSignatureType;
    }

    @Override // com.amazon.clouddrive.model.UploadFileRequest, com.amazon.clouddrive.model.PostNodeRequest, com.amazon.clouddrive.model.EditableNodeRequest
    public int hashCode() {
        return (Objects.hash(Boolean.valueOf(addToFamilyArchive()), getContentSignatureType(), getContentSignature()) * 31) + super.hashCode();
    }

    public void setAddToFamilyArchive(boolean z) {
        this.mAddToFamilyArchive = z;
    }

    public void setContentSignature(String str) {
        this.mContentSignature = str;
    }

    public void setContentSignatureType(String str) {
        this.mContentSignatureType = str;
    }

    public UploadFileExtendedRequest withAddToFamilyArchive(boolean z) {
        setAddToFamilyArchive(z);
        return this;
    }

    public UploadFileExtendedRequest withContentSignature(String str) {
        setContentSignature(str);
        return this;
    }

    public UploadFileExtendedRequest withContentSignatureType(String str) {
        setContentSignatureType(str);
        return this;
    }

    @Override // com.amazon.clouddrive.model.EditableNodeRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof UploadFileExtendedRequest)) {
            return 1;
        }
        UploadFileExtendedRequest uploadFileExtendedRequest = (UploadFileExtendedRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(Boolean.valueOf(addToFamilyArchive()), Boolean.valueOf(uploadFileExtendedRequest.addToFamilyArchive()));
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getContentSignatureType(), uploadFileExtendedRequest.getContentSignatureType());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getContentSignature(), uploadFileExtendedRequest.getContentSignature());
        return compare3 != 0 ? compare3 : super.compareTo(cloudDriveRequest);
    }
}
