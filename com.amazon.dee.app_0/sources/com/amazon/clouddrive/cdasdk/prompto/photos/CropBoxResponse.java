package com.amazon.clouddrive.cdasdk.prompto.photos;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class CropBoxResponse {
    @JsonProperty("cropHeight")
    private Integer cropHeight;
    @JsonProperty("cropWidth")
    private Integer cropWidth;
    @JsonProperty("offsetX")
    private Integer offsetX;
    @JsonProperty("offsetY")
    private Integer offsetY;

    protected boolean canEqual(Object obj) {
        return obj instanceof CropBoxResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CropBoxResponse)) {
            return false;
        }
        CropBoxResponse cropBoxResponse = (CropBoxResponse) obj;
        if (!cropBoxResponse.canEqual(this)) {
            return false;
        }
        Integer offsetX = getOffsetX();
        Integer offsetX2 = cropBoxResponse.getOffsetX();
        if (offsetX != null ? !offsetX.equals(offsetX2) : offsetX2 != null) {
            return false;
        }
        Integer offsetY = getOffsetY();
        Integer offsetY2 = cropBoxResponse.getOffsetY();
        if (offsetY != null ? !offsetY.equals(offsetY2) : offsetY2 != null) {
            return false;
        }
        Integer cropWidth = getCropWidth();
        Integer cropWidth2 = cropBoxResponse.getCropWidth();
        if (cropWidth != null ? !cropWidth.equals(cropWidth2) : cropWidth2 != null) {
            return false;
        }
        Integer cropHeight = getCropHeight();
        Integer cropHeight2 = cropBoxResponse.getCropHeight();
        return cropHeight != null ? cropHeight.equals(cropHeight2) : cropHeight2 == null;
    }

    public Integer getCropHeight() {
        return this.cropHeight;
    }

    public Integer getCropWidth() {
        return this.cropWidth;
    }

    public Integer getOffsetX() {
        return this.offsetX;
    }

    public Integer getOffsetY() {
        return this.offsetY;
    }

    public int hashCode() {
        Integer offsetX = getOffsetX();
        int i = 43;
        int hashCode = offsetX == null ? 43 : offsetX.hashCode();
        Integer offsetY = getOffsetY();
        int hashCode2 = ((hashCode + 59) * 59) + (offsetY == null ? 43 : offsetY.hashCode());
        Integer cropWidth = getCropWidth();
        int hashCode3 = (hashCode2 * 59) + (cropWidth == null ? 43 : cropWidth.hashCode());
        Integer cropHeight = getCropHeight();
        int i2 = hashCode3 * 59;
        if (cropHeight != null) {
            i = cropHeight.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("cropHeight")
    public void setCropHeight(Integer num) {
        this.cropHeight = num;
    }

    @JsonProperty("cropWidth")
    public void setCropWidth(Integer num) {
        this.cropWidth = num;
    }

    @JsonProperty("offsetX")
    public void setOffsetX(Integer num) {
        this.offsetX = num;
    }

    @JsonProperty("offsetY")
    public void setOffsetY(Integer num) {
        this.offsetY = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CropBoxResponse(offsetX=");
        outline107.append(getOffsetX());
        outline107.append(", offsetY=");
        outline107.append(getOffsetY());
        outline107.append(", cropWidth=");
        outline107.append(getCropWidth());
        outline107.append(", cropHeight=");
        outline107.append(getCropHeight());
        outline107.append(")");
        return outline107.toString();
    }
}
