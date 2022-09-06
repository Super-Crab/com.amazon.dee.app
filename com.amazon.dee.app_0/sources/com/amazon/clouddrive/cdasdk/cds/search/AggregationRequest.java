package com.amazon.clouddrive.cdasdk.cds.search;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.amazon.clouddrive.cdasdk.cds.common.DedupeContext;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class AggregationRequest extends CloudDriveRequest {
    @JsonProperty("aggregationContext")
    private AggregationContext aggregationContext;
    @JsonProperty("category")
    private String category;
    @JsonProperty("dedupeContext")
    private DedupeContext dedupeContext;
    @JsonProperty("groupBy")
    private String groupBy;
    @JsonProperty("language")
    private String language;
    @JsonProperty(MetricsUtil.LegacyMetricTypes.LIMIT)
    private Integer limit;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof AggregationRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AggregationRequest)) {
            return false;
        }
        AggregationRequest aggregationRequest = (AggregationRequest) obj;
        if (!aggregationRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String category = getCategory();
        String category2 = aggregationRequest.getCategory();
        if (category != null ? !category.equals(category2) : category2 != null) {
            return false;
        }
        Integer limit = getLimit();
        Integer limit2 = aggregationRequest.getLimit();
        if (limit != null ? !limit.equals(limit2) : limit2 != null) {
            return false;
        }
        DedupeContext dedupeContext = getDedupeContext();
        DedupeContext dedupeContext2 = aggregationRequest.getDedupeContext();
        if (dedupeContext != null ? !dedupeContext.equals(dedupeContext2) : dedupeContext2 != null) {
            return false;
        }
        String language = getLanguage();
        String language2 = aggregationRequest.getLanguage();
        if (language != null ? !language.equals(language2) : language2 != null) {
            return false;
        }
        String groupBy = getGroupBy();
        String groupBy2 = aggregationRequest.getGroupBy();
        if (groupBy != null ? !groupBy.equals(groupBy2) : groupBy2 != null) {
            return false;
        }
        AggregationContext aggregationContext = getAggregationContext();
        AggregationContext aggregationContext2 = aggregationRequest.getAggregationContext();
        return aggregationContext != null ? aggregationContext.equals(aggregationContext2) : aggregationContext2 == null;
    }

    public AggregationContext getAggregationContext() {
        return this.aggregationContext;
    }

    public String getCategory() {
        return this.category;
    }

    public DedupeContext getDedupeContext() {
        return this.dedupeContext;
    }

    public String getGroupBy() {
        return this.groupBy;
    }

    public String getLanguage() {
        return this.language;
    }

    public Integer getLimit() {
        return this.limit;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String category = getCategory();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (category == null ? 43 : category.hashCode());
        Integer limit = getLimit();
        int hashCode3 = (hashCode2 * 59) + (limit == null ? 43 : limit.hashCode());
        DedupeContext dedupeContext = getDedupeContext();
        int hashCode4 = (hashCode3 * 59) + (dedupeContext == null ? 43 : dedupeContext.hashCode());
        String language = getLanguage();
        int hashCode5 = (hashCode4 * 59) + (language == null ? 43 : language.hashCode());
        String groupBy = getGroupBy();
        int hashCode6 = (hashCode5 * 59) + (groupBy == null ? 43 : groupBy.hashCode());
        AggregationContext aggregationContext = getAggregationContext();
        int i2 = hashCode6 * 59;
        if (aggregationContext != null) {
            i = aggregationContext.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("aggregationContext")
    public void setAggregationContext(AggregationContext aggregationContext) {
        this.aggregationContext = aggregationContext;
    }

    @JsonProperty("category")
    public void setCategory(String str) {
        this.category = str;
    }

    @JsonProperty("dedupeContext")
    public void setDedupeContext(DedupeContext dedupeContext) {
        this.dedupeContext = dedupeContext;
    }

    @JsonProperty("groupBy")
    public void setGroupBy(String str) {
        this.groupBy = str;
    }

    @JsonProperty("language")
    public void setLanguage(String str) {
        this.language = str;
    }

    @JsonProperty(MetricsUtil.LegacyMetricTypes.LIMIT)
    public void setLimit(Integer num) {
        this.limit = num;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AggregationRequest(category=");
        outline107.append(getCategory());
        outline107.append(", limit=");
        outline107.append(getLimit());
        outline107.append(", dedupeContext=");
        outline107.append(getDedupeContext());
        outline107.append(", language=");
        outline107.append(getLanguage());
        outline107.append(", groupBy=");
        outline107.append(getGroupBy());
        outline107.append(", aggregationContext=");
        outline107.append(getAggregationContext());
        outline107.append(")");
        return outline107.toString();
    }
}
