package com.amazon.clouddrive.cdasdk.cds.bulk;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/* loaded from: classes11.dex */
public class BulkGetNodesByDigestResponse {
    @JsonProperty("digestToNodeMap")
    private Map<String, NodeMatch> digestToNodeMap;
    @JsonProperty("digestType")
    private String digestType;

    protected boolean canEqual(Object obj) {
        return obj instanceof BulkGetNodesByDigestResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BulkGetNodesByDigestResponse)) {
            return false;
        }
        BulkGetNodesByDigestResponse bulkGetNodesByDigestResponse = (BulkGetNodesByDigestResponse) obj;
        if (!bulkGetNodesByDigestResponse.canEqual(this)) {
            return false;
        }
        Map<String, NodeMatch> digestToNodeMap = getDigestToNodeMap();
        Map<String, NodeMatch> digestToNodeMap2 = bulkGetNodesByDigestResponse.getDigestToNodeMap();
        if (digestToNodeMap != null ? !digestToNodeMap.equals(digestToNodeMap2) : digestToNodeMap2 != null) {
            return false;
        }
        String digestType = getDigestType();
        String digestType2 = bulkGetNodesByDigestResponse.getDigestType();
        return digestType != null ? digestType.equals(digestType2) : digestType2 == null;
    }

    public Map<String, NodeMatch> getDigestToNodeMap() {
        return this.digestToNodeMap;
    }

    public String getDigestType() {
        return this.digestType;
    }

    public int hashCode() {
        Map<String, NodeMatch> digestToNodeMap = getDigestToNodeMap();
        int i = 43;
        int hashCode = digestToNodeMap == null ? 43 : digestToNodeMap.hashCode();
        String digestType = getDigestType();
        int i2 = (hashCode + 59) * 59;
        if (digestType != null) {
            i = digestType.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("digestToNodeMap")
    public void setDigestToNodeMap(Map<String, NodeMatch> map) {
        this.digestToNodeMap = map;
    }

    @JsonProperty("digestType")
    public void setDigestType(String str) {
        this.digestType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BulkGetNodesByDigestResponse(digestToNodeMap=");
        outline107.append(getDigestToNodeMap());
        outline107.append(", digestType=");
        outline107.append(getDigestType());
        outline107.append(")");
        return outline107.toString();
    }
}
