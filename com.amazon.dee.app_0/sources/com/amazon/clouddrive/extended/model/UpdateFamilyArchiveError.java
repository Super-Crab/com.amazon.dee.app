package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class UpdateFamilyArchiveError implements Comparable<UpdateFamilyArchiveError> {
    private String mErrorCode;
    private String mErrorMessage;
    private String mNodeId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof UpdateFamilyArchiveError) && compareTo((UpdateFamilyArchiveError) obj) == 0;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public String getNodeId() {
        return this.mNodeId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getNodeId() == null ? 0 : getNodeId().hashCode()) + 1 + (getErrorCode() == null ? 0 : getErrorCode().hashCode());
        if (getErrorMessage() != null) {
            i = getErrorMessage().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setErrorCode(String str) {
        this.mErrorCode = str;
    }

    public void setErrorMessage(String str) {
        this.mErrorMessage = str;
    }

    public void setNodeId(String str) {
        this.mNodeId = str;
    }

    public UpdateFamilyArchiveError withErrorCode(String str) {
        setErrorCode(str);
        return this;
    }

    public UpdateFamilyArchiveError withErrorMessage(String str) {
        setErrorMessage(str);
        return this;
    }

    public UpdateFamilyArchiveError withNodeId(String str) {
        setNodeId(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(UpdateFamilyArchiveError updateFamilyArchiveError) {
        if (updateFamilyArchiveError == null) {
            return -1;
        }
        if (updateFamilyArchiveError == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getNodeId(), updateFamilyArchiveError.getNodeId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getErrorCode(), updateFamilyArchiveError.getErrorCode());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getErrorMessage(), updateFamilyArchiveError.getErrorMessage());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
