package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class ListChildrenRequest extends PaginatedCloudDriveRequest {
    private String assetMapping;
    private String id;
    private Boolean tempLink;

    public ListChildrenRequest(String str) {
        this.id = str;
    }

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ListChildrenRequest) && compareTo((CloudDriveRequest) ((ListChildrenRequest) obj)) == 0;
    }

    public String getAssetMapping() {
        return this.assetMapping;
    }

    public String getId() {
        return this.id;
    }

    public Boolean getTempLink() {
        return this.tempLink;
    }

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public int hashCode() {
        int i = 0;
        int hashCode = (getAssetMapping() == null ? 0 : getAssetMapping().hashCode()) + 1;
        if (getId() != null) {
            i = getId().hashCode();
        }
        return ((hashCode + i + (getTempLink().booleanValue() ? 1 : 0)) * 31) + super.hashCode();
    }

    public void setAssetMapping(String str) {
        this.assetMapping = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTempLink(Boolean bool) {
        this.tempLink = bool;
    }

    public ListChildrenRequest withAssetMapping(String str) {
        setAssetMapping(str);
        return this;
    }

    public ListChildrenRequest withFields(String str) {
        setFields(str);
        return this;
    }

    public ListChildrenRequest withFilters(String str) {
        setFilters(str);
        return this;
    }

    public ListChildrenRequest withLimit(Integer num) {
        setLimit(num);
        return this;
    }

    public ListChildrenRequest withOffset(Integer num) {
        setOffset(num);
        return this;
    }

    public ListChildrenRequest withSort(String str) {
        setSort(str);
        return this;
    }

    public ListChildrenRequest withStartToken(String str) {
        setStartToken(str);
        return this;
    }

    public ListChildrenRequest withTempLink(boolean z) {
        setTempLink(Boolean.valueOf(z));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof ListChildrenRequest)) {
            return 1;
        }
        ListChildrenRequest listChildrenRequest = (ListChildrenRequest) cloudDriveRequest;
        String assetMapping = getAssetMapping();
        String assetMapping2 = listChildrenRequest.getAssetMapping();
        if (assetMapping != assetMapping2) {
            if (assetMapping == null) {
                return -1;
            }
            if (assetMapping2 == null) {
                return 1;
            }
            int compareTo = assetMapping.compareTo(assetMapping2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String id = getId();
        String id2 = listChildrenRequest.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int compareTo2 = id.compareTo(id2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        Boolean tempLink = getTempLink();
        Boolean tempLink2 = listChildrenRequest.getTempLink();
        if (tempLink != tempLink2) {
            if (tempLink == null) {
                return -1;
            }
            if (tempLink2 == null) {
                return 1;
            }
            int compareTo3 = tempLink.compareTo(tempLink2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        return super.compareTo(cloudDriveRequest);
    }
}
