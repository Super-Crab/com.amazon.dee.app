package com.amazon.clouddrive.cdasdk.cds.common;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.deecomms.common.network.AppUrl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class PaginatedCloudDriveRequest extends CloudDriveRequest {
    @JsonProperty("fieldList")
    private List<String> fieldList;
    @JsonProperty("fields")
    private String fields;
    @JsonProperty("filters")
    private String filters;
    @JsonProperty(MetricsUtil.LegacyMetricTypes.LIMIT)
    private String limit;
    @JsonProperty("lowResThumbnail")
    private Boolean lowResThumbnail;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("resourceVersion")
    private ResourceVersion resourceVersion;
    @JsonProperty(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER)
    private String sort;
    @JsonProperty("startToken")
    private String startToken;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof PaginatedCloudDriveRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PaginatedCloudDriveRequest)) {
            return false;
        }
        PaginatedCloudDriveRequest paginatedCloudDriveRequest = (PaginatedCloudDriveRequest) obj;
        if (!paginatedCloudDriveRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String startToken = getStartToken();
        String startToken2 = paginatedCloudDriveRequest.getStartToken();
        if (startToken != null ? !startToken.equals(startToken2) : startToken2 != null) {
            return false;
        }
        Integer offset = getOffset();
        Integer offset2 = paginatedCloudDriveRequest.getOffset();
        if (offset != null ? !offset.equals(offset2) : offset2 != null) {
            return false;
        }
        String limit = getLimit();
        String limit2 = paginatedCloudDriveRequest.getLimit();
        if (limit != null ? !limit.equals(limit2) : limit2 != null) {
            return false;
        }
        String filters = getFilters();
        String filters2 = paginatedCloudDriveRequest.getFilters();
        if (filters != null ? !filters.equals(filters2) : filters2 != null) {
            return false;
        }
        String sort = getSort();
        String sort2 = paginatedCloudDriveRequest.getSort();
        if (sort != null ? !sort.equals(sort2) : sort2 != null) {
            return false;
        }
        String fields = getFields();
        String fields2 = paginatedCloudDriveRequest.getFields();
        if (fields != null ? !fields.equals(fields2) : fields2 != null) {
            return false;
        }
        List<String> fieldList = getFieldList();
        List<String> fieldList2 = paginatedCloudDriveRequest.getFieldList();
        if (fieldList != null ? !fieldList.equals(fieldList2) : fieldList2 != null) {
            return false;
        }
        ResourceVersion resourceVersion = getResourceVersion();
        ResourceVersion resourceVersion2 = paginatedCloudDriveRequest.getResourceVersion();
        if (resourceVersion != null ? !resourceVersion.equals(resourceVersion2) : resourceVersion2 != null) {
            return false;
        }
        Boolean lowResThumbnail = getLowResThumbnail();
        Boolean lowResThumbnail2 = paginatedCloudDriveRequest.getLowResThumbnail();
        return lowResThumbnail != null ? lowResThumbnail.equals(lowResThumbnail2) : lowResThumbnail2 == null;
    }

    public List<String> getFieldList() {
        return this.fieldList;
    }

    public String getFields() {
        return this.fields;
    }

    public String getFilters() {
        return this.filters;
    }

    public String getLimit() {
        return this.limit;
    }

    public Boolean getLowResThumbnail() {
        return this.lowResThumbnail;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public ResourceVersion getResourceVersion() {
        return this.resourceVersion;
    }

    public String getSort() {
        return this.sort;
    }

    public String getStartToken() {
        return this.startToken;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String startToken = getStartToken();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (startToken == null ? 43 : startToken.hashCode());
        Integer offset = getOffset();
        int hashCode3 = (hashCode2 * 59) + (offset == null ? 43 : offset.hashCode());
        String limit = getLimit();
        int hashCode4 = (hashCode3 * 59) + (limit == null ? 43 : limit.hashCode());
        String filters = getFilters();
        int hashCode5 = (hashCode4 * 59) + (filters == null ? 43 : filters.hashCode());
        String sort = getSort();
        int hashCode6 = (hashCode5 * 59) + (sort == null ? 43 : sort.hashCode());
        String fields = getFields();
        int hashCode7 = (hashCode6 * 59) + (fields == null ? 43 : fields.hashCode());
        List<String> fieldList = getFieldList();
        int hashCode8 = (hashCode7 * 59) + (fieldList == null ? 43 : fieldList.hashCode());
        ResourceVersion resourceVersion = getResourceVersion();
        int hashCode9 = (hashCode8 * 59) + (resourceVersion == null ? 43 : resourceVersion.hashCode());
        Boolean lowResThumbnail = getLowResThumbnail();
        int i2 = hashCode9 * 59;
        if (lowResThumbnail != null) {
            i = lowResThumbnail.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("fieldList")
    public void setFieldList(List<String> list) {
        this.fieldList = list;
    }

    @JsonProperty("fields")
    public void setFields(String str) {
        this.fields = str;
    }

    @JsonProperty("filters")
    public void setFilters(String str) {
        this.filters = str;
    }

    @JsonProperty(MetricsUtil.LegacyMetricTypes.LIMIT)
    public void setLimit(String str) {
        this.limit = str;
    }

    @JsonProperty("lowResThumbnail")
    public void setLowResThumbnail(Boolean bool) {
        this.lowResThumbnail = bool;
    }

    @JsonProperty("offset")
    public void setOffset(Integer num) {
        this.offset = num;
    }

    @JsonProperty("resourceVersion")
    public void setResourceVersion(ResourceVersion resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    @JsonProperty(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER)
    public void setSort(String str) {
        this.sort = str;
    }

    @JsonProperty("startToken")
    public void setStartToken(String str) {
        this.startToken = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PaginatedCloudDriveRequest(startToken=");
        outline107.append(getStartToken());
        outline107.append(", offset=");
        outline107.append(getOffset());
        outline107.append(", limit=");
        outline107.append(getLimit());
        outline107.append(", filters=");
        outline107.append(getFilters());
        outline107.append(", sort=");
        outline107.append(getSort());
        outline107.append(", fields=");
        outline107.append(getFields());
        outline107.append(", fieldList=");
        outline107.append(getFieldList());
        outline107.append(", resourceVersion=");
        outline107.append(getResourceVersion());
        outline107.append(", lowResThumbnail=");
        outline107.append(getLowResThumbnail());
        outline107.append(")");
        return outline107.toString();
    }
}
