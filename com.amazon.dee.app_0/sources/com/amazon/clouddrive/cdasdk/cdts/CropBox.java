package com.amazon.clouddrive.cdasdk.cdts;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class CropBox {
    private long cropHeight;
    private long cropWidth;
    private long offsetX;
    private long offsetY;

    /* loaded from: classes11.dex */
    public static class CropBoxBuilder {
        private long cropHeight;
        private long cropWidth;
        private long offsetX;
        private long offsetY;

        CropBoxBuilder() {
        }

        public CropBox build() {
            return new CropBox(this.offsetX, this.offsetY, this.cropWidth, this.cropHeight);
        }

        public CropBoxBuilder cropHeight(long j) {
            this.cropHeight = j;
            return this;
        }

        public CropBoxBuilder cropWidth(long j) {
            this.cropWidth = j;
            return this;
        }

        public CropBoxBuilder offsetX(long j) {
            this.offsetX = j;
            return this;
        }

        public CropBoxBuilder offsetY(long j) {
            this.offsetY = j;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CropBox.CropBoxBuilder(offsetX=");
            outline107.append(this.offsetX);
            outline107.append(", offsetY=");
            outline107.append(this.offsetY);
            outline107.append(", cropWidth=");
            outline107.append(this.cropWidth);
            outline107.append(", cropHeight=");
            return GeneratedOutlineSupport1.outline87(outline107, this.cropHeight, ")");
        }
    }

    CropBox(long j, long j2, long j3, long j4) {
        this.offsetX = j;
        this.offsetY = j2;
        this.cropWidth = j3;
        this.cropHeight = j4;
    }

    public static CropBoxBuilder builder() {
        return new CropBoxBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof CropBox;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CropBox)) {
            return false;
        }
        CropBox cropBox = (CropBox) obj;
        return cropBox.canEqual(this) && getOffsetX() == cropBox.getOffsetX() && getOffsetY() == cropBox.getOffsetY() && getCropWidth() == cropBox.getCropWidth() && getCropHeight() == cropBox.getCropHeight();
    }

    public long getCropHeight() {
        return this.cropHeight;
    }

    public long getCropWidth() {
        return this.cropWidth;
    }

    public long getOffsetX() {
        return this.offsetX;
    }

    public long getOffsetY() {
        return this.offsetY;
    }

    public int hashCode() {
        long offsetX = getOffsetX();
        long offsetY = getOffsetY();
        int i = ((((int) (offsetX ^ (offsetX >>> 32))) + 59) * 59) + ((int) (offsetY ^ (offsetY >>> 32)));
        long cropWidth = getCropWidth();
        int i2 = (i * 59) + ((int) (cropWidth ^ (cropWidth >>> 32)));
        long cropHeight = getCropHeight();
        return (i2 * 59) + ((int) ((cropHeight >>> 32) ^ cropHeight));
    }

    public void setCropHeight(long j) {
        this.cropHeight = j;
    }

    public void setCropWidth(long j) {
        this.cropWidth = j;
    }

    public void setOffsetX(long j) {
        this.offsetX = j;
    }

    public void setOffsetY(long j) {
        this.offsetY = j;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CropBox(offsetX=");
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
