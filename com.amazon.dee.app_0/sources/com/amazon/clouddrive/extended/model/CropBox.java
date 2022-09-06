package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class CropBox implements Comparable<CropBox> {
    private long cropHeight;
    private long cropWidth;
    private long offsetX;
    private long offsetY;

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof CropBox) && compareTo((CropBox) obj) == 0);
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
        return (((((((int) (getCropHeight() ^ (getCropHeight() >>> 32))) * 31) + ((int) (getCropWidth() ^ (getCropWidth() >>> 32)))) * 31) + ((int) (getOffsetX() ^ (getOffsetX() >>> 32)))) * 31) + ((int) (getOffsetY() ^ (getOffsetY() >>> 32)));
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

    public CropBox withCropHeight(long j) {
        setCropHeight(j);
        return this;
    }

    public CropBox withCropWidth(long j) {
        setCropWidth(j);
        return this;
    }

    public CropBox withOffsetX(long j) {
        setOffsetX(j);
        return this;
    }

    public CropBox withOffsetY(long j) {
        setOffsetY(j);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CropBox cropBox) {
        long j = this.offsetX;
        long j2 = cropBox.offsetX;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        long j3 = this.offsetY;
        long j4 = cropBox.offsetY;
        if (j3 < j4) {
            return -1;
        }
        if (j3 > j4) {
            return 1;
        }
        long j5 = this.cropHeight;
        long j6 = cropBox.cropHeight;
        if (j5 < j6) {
            return -1;
        }
        if (j5 > j6) {
            return 1;
        }
        long j7 = this.cropWidth;
        long j8 = cropBox.cropWidth;
        if (j7 < j8) {
            return -1;
        }
        return j7 > j8 ? 1 : 0;
    }
}
