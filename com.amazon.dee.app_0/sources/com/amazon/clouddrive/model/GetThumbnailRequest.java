package com.amazon.clouddrive.model;

import java.io.OutputStream;
/* loaded from: classes11.dex */
public class GetThumbnailRequest implements CloudDriveRequest {
    private FitType mFitType;
    private String mId;
    private OutputStream mOutputStream;
    private int mViewBoxWidth = -1;
    private int mViewBoxHeight = -1;
    private int mBlockSize = 32768;

    public GetThumbnailRequest(String str, OutputStream outputStream) {
        this.mId = str;
        this.mOutputStream = outputStream;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetThumbnailRequest) && compareTo((CloudDriveRequest) ((GetThumbnailRequest) obj)) == 0;
    }

    public int getBlockSize() {
        return this.mBlockSize;
    }

    public FitType getFitType() {
        return this.mFitType;
    }

    public String getId() {
        return this.mId;
    }

    public OutputStream getOutputStream() {
        return this.mOutputStream;
    }

    public int getViewBoxHeight() {
        return this.mViewBoxHeight;
    }

    public int getViewBoxWidth() {
        return this.mViewBoxWidth;
    }

    public boolean hasFitType() {
        return this.mFitType != null;
    }

    public boolean hasViewBoxSet() {
        return this.mViewBoxWidth > 0 && this.mViewBoxHeight > 0;
    }

    public int hashCode() {
        int i = 0;
        int viewBoxHeight = getViewBoxHeight() + getViewBoxWidth() + (getId() == null ? 0 : getId().hashCode()) + 1;
        if (getFitType() != null) {
            i = getFitType().hashCode();
        }
        return this.mOutputStream.hashCode() + viewBoxHeight + i;
    }

    public void setBlockSize(int i) {
        if (i >= 1) {
            this.mBlockSize = i;
            return;
        }
        throw new IllegalArgumentException("The block size must be at the very least 1.");
    }

    public void setFitType(FitType fitType) {
        this.mFitType = fitType;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setViewBox(int i) {
        if (i > 0) {
            this.mViewBoxWidth = i;
            this.mViewBoxHeight = i;
            return;
        }
        throw new IllegalArgumentException("viewBox must be greater than 0");
    }

    public GetThumbnailRequest withBlockSize(int i) {
        setBlockSize(i);
        return this;
    }

    public GetThumbnailRequest withFitType(FitType fitType) {
        setFitType(fitType);
        return this;
    }

    public GetThumbnailRequest withViewBox(int i) {
        setViewBox(i);
        return this;
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
        if (!(cloudDriveRequest instanceof GetThumbnailRequest)) {
            return 1;
        }
        GetThumbnailRequest getThumbnailRequest = (GetThumbnailRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getId(), getThumbnailRequest.getId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(Integer.valueOf(getViewBoxWidth()), Integer.valueOf(getThumbnailRequest.getViewBoxWidth()));
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(Integer.valueOf(getViewBoxHeight()), Integer.valueOf(getThumbnailRequest.getViewBoxHeight()));
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getFitType(), getThumbnailRequest.getFitType());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getOutputStream(), getThumbnailRequest.getOutputStream());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(Integer.valueOf(getBlockSize()), Integer.valueOf(getThumbnailRequest.getBlockSize()));
        if (compare6 == 0) {
            return 0;
        }
        return compare6;
    }

    public GetThumbnailRequest withViewBox(int i, int i2) {
        setViewBox(i, i2);
        return this;
    }

    public void setViewBox(int i, int i2) {
        if (i > 0) {
            if (i2 > 0) {
                this.mViewBoxWidth = i;
                this.mViewBoxHeight = i2;
                return;
            }
            throw new IllegalArgumentException("viewBox height must be greater than 0");
        }
        throw new IllegalArgumentException("viewBox width must be greater than 0");
    }
}
