package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class ListNodesRequest extends PaginatedCloudDriveRequest {
    private String assetMapping;
    private String dedupeContext;
    private Boolean tempLink;

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ListNodesRequest) && compareTo((CloudDriveRequest) ((ListNodesRequest) obj)) == 0;
    }

    public String getAssetMapping() {
        return this.assetMapping;
    }

    public String getDedupeContext() {
        return this.dedupeContext;
    }

    public Boolean getTempLink() {
        return this.tempLink;
    }

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public int hashCode() {
        int i = 0;
        int hashCode = (getTempLink().booleanValue() ? 1 : 0) + 1 + (getAssetMapping() == null ? 0 : getAssetMapping().hashCode());
        if (getDedupeContext() != null) {
            i = getDedupeContext().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setAssetMapping(String str) {
        this.assetMapping = str;
    }

    public void setDedupeContext(String str) {
        this.dedupeContext = str;
    }

    public void setTempLink(Boolean bool) {
        this.tempLink = bool;
    }

    public ListNodesRequest withAssetMapping(String str) {
        setAssetMapping(str);
        return this;
    }

    public ListNodesRequest withDedupeContext(String str) {
        setDedupeContext(str);
        return this;
    }

    public ListNodesRequest withFields(String str) {
        setFields(str);
        return this;
    }

    public ListNodesRequest withFilters(String str) {
        setFilters(str);
        return this;
    }

    public ListNodesRequest withLimit(Integer num) {
        setLimit(num);
        return this;
    }

    public ListNodesRequest withOffset(Integer num) {
        setOffset(num);
        return this;
    }

    public ListNodesRequest withSort(String str) {
        setSort(str);
        return this;
    }

    public ListNodesRequest withStartToken(String str) {
        setStartToken(str);
        return this;
    }

    public ListNodesRequest withTempLink(boolean z) {
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
        if (!(cloudDriveRequest instanceof ListNodesRequest)) {
            return 1;
        }
        ListNodesRequest listNodesRequest = (ListNodesRequest) cloudDriveRequest;
        Boolean tempLink = getTempLink();
        Boolean tempLink2 = listNodesRequest.getTempLink();
        if (tempLink != tempLink2) {
            if (tempLink == null) {
                return -1;
            }
            if (tempLink2 == null) {
                return 1;
            }
            int compareTo = tempLink.compareTo(tempLink2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String assetMapping = getAssetMapping();
        String assetMapping2 = listNodesRequest.getAssetMapping();
        if (assetMapping != assetMapping2) {
            if (assetMapping == null) {
                return -1;
            }
            if (assetMapping2 == null) {
                return 1;
            }
            int compareTo2 = assetMapping.compareTo(assetMapping2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String dedupeContext = getDedupeContext();
        String dedupeContext2 = listNodesRequest.getDedupeContext();
        if (dedupeContext != dedupeContext2) {
            if (dedupeContext == null) {
                return -1;
            }
            if (dedupeContext2 == null) {
                return 1;
            }
            int compareTo3 = dedupeContext.compareTo(dedupeContext2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        return super.compareTo(cloudDriveRequest);
    }
}
