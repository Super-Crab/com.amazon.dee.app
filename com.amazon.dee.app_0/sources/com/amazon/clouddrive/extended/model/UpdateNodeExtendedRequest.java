package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.UpdateNodeRequest;
import com.amazon.clouddrive.utils.Optional;
/* loaded from: classes11.dex */
public class UpdateNodeExtendedRequest extends UpdateNodeRequest {
    private Optional<String> familyId;

    public UpdateNodeExtendedRequest(String str) {
        super(str);
        this.familyId = Optional.absent();
    }

    @Override // com.amazon.clouddrive.model.UpdateNodeRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof UpdateNodeExtendedRequest) && compareTo((CloudDriveRequest) ((UpdateNodeExtendedRequest) obj)) == 0;
    }

    public Optional<String> getFamilyId() {
        return this.familyId;
    }

    @Override // com.amazon.clouddrive.model.UpdateNodeRequest
    public int hashCode() {
        return (((getFamilyId() == null ? 0 : getFamilyId().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setFamilyId(String str) {
        this.familyId = Optional.of(str);
    }

    public UpdateNodeExtendedRequest withFamilyId(String str) {
        setFamilyId(str);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.UpdateNodeRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof UpdateNodeRequest)) {
            return 1;
        }
        Optional<String> familyId = getFamilyId();
        Optional<String> familyId2 = ((UpdateNodeExtendedRequest) cloudDriveRequest).getFamilyId();
        if (familyId != familyId2) {
            if (familyId == null) {
                return -1;
            }
            if (familyId2 == null) {
                return 1;
            }
            if (familyId instanceof Comparable) {
                int compareTo = ((Comparable) familyId).compareTo(familyId2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!familyId.equals(familyId2)) {
                int hashCode = familyId.hashCode();
                int hashCode2 = familyId2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(cloudDriveRequest);
    }
}
