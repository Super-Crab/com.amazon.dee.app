package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public abstract class PaginatedCloudDriveRequest implements CloudDriveRequest {
    private String fields;
    private String filters;
    private Integer limit;
    private Integer offset;
    private String sort;
    private String startToken;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof PaginatedCloudDriveRequest) && compareTo((CloudDriveRequest) ((PaginatedCloudDriveRequest) obj)) == 0;
    }

    public String getFields() {
        return this.fields;
    }

    public String getFilters() {
        return this.filters;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public String getSort() {
        return this.sort;
    }

    public String getStartToken() {
        return this.startToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getFields() == null ? 0 : getFields().hashCode()) + 1 + (getStartToken() == null ? 0 : getStartToken().hashCode()) + (getFilters() == null ? 0 : getFilters().hashCode()) + (getOffset() == null ? 0 : getOffset().hashCode()) + (getLimit() == null ? 0 : getLimit().hashCode());
        if (getSort() != null) {
            i = getSort().hashCode();
        }
        return hashCode + i;
    }

    public void setFields(String str) {
        this.fields = str;
    }

    public void setFilters(String str) {
        this.filters = str;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setOffset(Integer num) {
        this.offset = num;
    }

    public void setSort(String str) {
        this.sort = str;
    }

    public void setStartToken(String str) {
        this.startToken = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof PaginatedCloudDriveRequest)) {
            return 1;
        }
        PaginatedCloudDriveRequest paginatedCloudDriveRequest = (PaginatedCloudDriveRequest) cloudDriveRequest;
        String fields = getFields();
        String fields2 = paginatedCloudDriveRequest.getFields();
        if (fields != fields2) {
            if (fields == null) {
                return -1;
            }
            if (fields2 == null) {
                return 1;
            }
            int compareTo = fields.compareTo(fields2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String startToken = getStartToken();
        String startToken2 = paginatedCloudDriveRequest.getStartToken();
        if (startToken != startToken2) {
            if (startToken == null) {
                return -1;
            }
            if (startToken2 == null) {
                return 1;
            }
            int compareTo2 = startToken.compareTo(startToken2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String filters = getFilters();
        String filters2 = paginatedCloudDriveRequest.getFilters();
        if (filters != filters2) {
            if (filters == null) {
                return -1;
            }
            if (filters2 == null) {
                return 1;
            }
            int compareTo3 = filters.compareTo(filters2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        Integer offset = getOffset();
        Integer offset2 = paginatedCloudDriveRequest.getOffset();
        if (offset != offset2) {
            if (offset == null) {
                return -1;
            }
            if (offset2 == null) {
                return 1;
            }
            int compareTo4 = offset.compareTo(offset2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        Integer limit = getLimit();
        Integer limit2 = paginatedCloudDriveRequest.getLimit();
        if (limit != limit2) {
            if (limit == null) {
                return -1;
            }
            if (limit2 == null) {
                return 1;
            }
            int compareTo5 = limit.compareTo(limit2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        String sort = getSort();
        String sort2 = paginatedCloudDriveRequest.getSort();
        if (sort != sort2) {
            if (sort == null) {
                return -1;
            }
            if (sort2 == null) {
                return 1;
            }
            int compareTo6 = sort.compareTo(sort2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        return 0;
    }
}
