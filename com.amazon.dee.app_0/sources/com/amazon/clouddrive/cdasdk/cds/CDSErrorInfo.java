package com.amazon.clouddrive.cdasdk.cds;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Nullable;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes11.dex */
public class CDSErrorInfo {
    @JsonProperty("fields")
    @Nullable
    private String fields;
    @JsonProperty("invalidIds")
    @Nullable
    private String invalidIds;
    @JsonProperty(MetricsUtil.LegacyMetricTypes.LIMIT)
    @Nullable
    private Integer limit;
    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    @Nullable
    private String nodeId;
    @JsonProperty(Message.SERIALIZED_NAME_PARENT_ID)
    @Nullable
    private String parentId;

    protected boolean canEqual(Object obj) {
        return obj instanceof CDSErrorInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CDSErrorInfo)) {
            return false;
        }
        CDSErrorInfo cDSErrorInfo = (CDSErrorInfo) obj;
        if (!cDSErrorInfo.canEqual(this)) {
            return false;
        }
        String nodeId = getNodeId();
        String nodeId2 = cDSErrorInfo.getNodeId();
        if (nodeId != null ? !nodeId.equals(nodeId2) : nodeId2 != null) {
            return false;
        }
        String parentId = getParentId();
        String parentId2 = cDSErrorInfo.getParentId();
        if (parentId != null ? !parentId.equals(parentId2) : parentId2 != null) {
            return false;
        }
        String invalidIds = getInvalidIds();
        String invalidIds2 = cDSErrorInfo.getInvalidIds();
        if (invalidIds != null ? !invalidIds.equals(invalidIds2) : invalidIds2 != null) {
            return false;
        }
        Integer limit = getLimit();
        Integer limit2 = cDSErrorInfo.getLimit();
        if (limit != null ? !limit.equals(limit2) : limit2 != null) {
            return false;
        }
        String fields = getFields();
        String fields2 = cDSErrorInfo.getFields();
        return fields != null ? fields.equals(fields2) : fields2 == null;
    }

    @Nullable
    public String getFields() {
        return this.fields;
    }

    @Nullable
    public String getInvalidIds() {
        return this.invalidIds;
    }

    @Nullable
    public Integer getLimit() {
        return this.limit;
    }

    @Nullable
    public String getNodeId() {
        return this.nodeId;
    }

    @Nullable
    public String getParentId() {
        return this.parentId;
    }

    public int hashCode() {
        String nodeId = getNodeId();
        int i = 43;
        int hashCode = nodeId == null ? 43 : nodeId.hashCode();
        String parentId = getParentId();
        int hashCode2 = ((hashCode + 59) * 59) + (parentId == null ? 43 : parentId.hashCode());
        String invalidIds = getInvalidIds();
        int hashCode3 = (hashCode2 * 59) + (invalidIds == null ? 43 : invalidIds.hashCode());
        Integer limit = getLimit();
        int hashCode4 = (hashCode3 * 59) + (limit == null ? 43 : limit.hashCode());
        String fields = getFields();
        int i2 = hashCode4 * 59;
        if (fields != null) {
            i = fields.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("fields")
    public void setFields(@Nullable String str) {
        this.fields = str;
    }

    @JsonProperty("invalidIds")
    public void setInvalidIds(@Nullable String str) {
        this.invalidIds = str;
    }

    @JsonProperty(MetricsUtil.LegacyMetricTypes.LIMIT)
    public void setLimit(@Nullable Integer num) {
        this.limit = num;
    }

    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    public void setNodeId(@Nullable String str) {
        this.nodeId = str;
    }

    @JsonProperty(Message.SERIALIZED_NAME_PARENT_ID)
    public void setParentId(@Nullable String str) {
        this.parentId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CDSErrorInfo(nodeId=");
        outline107.append(getNodeId());
        outline107.append(", parentId=");
        outline107.append(getParentId());
        outline107.append(", invalidIds=");
        outline107.append(getInvalidIds());
        outline107.append(", limit=");
        outline107.append(getLimit());
        outline107.append(", fields=");
        outline107.append(getFields());
        outline107.append(")");
        return outline107.toString();
    }
}
