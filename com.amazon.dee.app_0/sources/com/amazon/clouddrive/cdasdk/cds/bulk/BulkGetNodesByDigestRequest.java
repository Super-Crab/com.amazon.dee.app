package com.amazon.clouddrive.cdasdk.cds.bulk;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class BulkGetNodesByDigestRequest extends CloudDriveRequest {
    @JsonProperty("digestList")
    private List<String> digestList;
    @JsonProperty("digestType")
    private DigestType digestType;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof BulkGetNodesByDigestRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BulkGetNodesByDigestRequest)) {
            return false;
        }
        BulkGetNodesByDigestRequest bulkGetNodesByDigestRequest = (BulkGetNodesByDigestRequest) obj;
        if (!bulkGetNodesByDigestRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<String> digestList = getDigestList();
        List<String> digestList2 = bulkGetNodesByDigestRequest.getDigestList();
        if (digestList != null ? !digestList.equals(digestList2) : digestList2 != null) {
            return false;
        }
        DigestType digestType = getDigestType();
        DigestType digestType2 = bulkGetNodesByDigestRequest.getDigestType();
        return digestType != null ? digestType.equals(digestType2) : digestType2 == null;
    }

    public List<String> getDigestList() {
        return this.digestList;
    }

    public DigestType getDigestType() {
        return this.digestType;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        List<String> digestList = getDigestList();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (digestList == null ? 43 : digestList.hashCode());
        DigestType digestType = getDigestType();
        int i2 = hashCode2 * 59;
        if (digestType != null) {
            i = digestType.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("digestList")
    public void setDigestList(List<String> list) {
        this.digestList = list;
    }

    @JsonProperty("digestType")
    public void setDigestType(DigestType digestType) {
        this.digestType = digestType;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BulkGetNodesByDigestRequest(digestList=");
        outline107.append(getDigestList());
        outline107.append(", digestType=");
        outline107.append(getDigestType());
        outline107.append(")");
        return outline107.toString();
    }
}
