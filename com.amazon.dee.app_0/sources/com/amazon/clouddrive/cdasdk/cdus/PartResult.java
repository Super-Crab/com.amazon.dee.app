package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class PartResult {
    @JsonProperty("eTag")
    private String eTag;
    @JsonProperty("part-number")
    private Long partNumber;
    @JsonProperty("part-size")
    private Long partSize;

    protected boolean canEqual(Object obj) {
        return obj instanceof PartResult;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PartResult)) {
            return false;
        }
        PartResult partResult = (PartResult) obj;
        if (!partResult.canEqual(this)) {
            return false;
        }
        Long partNumber = getPartNumber();
        Long partNumber2 = partResult.getPartNumber();
        if (partNumber != null ? !partNumber.equals(partNumber2) : partNumber2 != null) {
            return false;
        }
        Long partSize = getPartSize();
        Long partSize2 = partResult.getPartSize();
        if (partSize != null ? !partSize.equals(partSize2) : partSize2 != null) {
            return false;
        }
        String eTag = getETag();
        String eTag2 = partResult.getETag();
        return eTag != null ? eTag.equals(eTag2) : eTag2 == null;
    }

    public String getETag() {
        return this.eTag;
    }

    public Long getPartNumber() {
        return this.partNumber;
    }

    public Long getPartSize() {
        return this.partSize;
    }

    public int hashCode() {
        Long partNumber = getPartNumber();
        int i = 43;
        int hashCode = partNumber == null ? 43 : partNumber.hashCode();
        Long partSize = getPartSize();
        int hashCode2 = ((hashCode + 59) * 59) + (partSize == null ? 43 : partSize.hashCode());
        String eTag = getETag();
        int i2 = hashCode2 * 59;
        if (eTag != null) {
            i = eTag.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("eTag")
    public void setETag(String str) {
        this.eTag = str;
    }

    @JsonProperty("part-number")
    public void setPartNumber(Long l) {
        this.partNumber = l;
    }

    @JsonProperty("part-size")
    public void setPartSize(Long l) {
        this.partSize = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PartResult(partNumber=");
        outline107.append(getPartNumber());
        outline107.append(", partSize=");
        outline107.append(getPartSize());
        outline107.append(", eTag=");
        outline107.append(getETag());
        outline107.append(")");
        return outline107.toString();
    }
}
