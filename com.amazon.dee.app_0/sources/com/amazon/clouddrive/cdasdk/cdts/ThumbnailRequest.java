package com.amazon.clouddrive.cdasdk.cdts;

import com.android.tools.r8.GeneratedOutlineSupport1;
import lombok.NonNull;
/* loaded from: classes11.dex */
public class ThumbnailRequest {
    private CropBox cropBox;
    private FitType fitType;
    private String groupShareToken;
    @NonNull
    private String nodeId;
    private String ownerId;
    private ViewBox viewBox;

    /* loaded from: classes11.dex */
    public static class ThumbnailRequestBuilder {
        private CropBox cropBox;
        private FitType fitType;
        private String groupShareToken;
        private String nodeId;
        private String ownerId;
        private ViewBox viewBox;

        ThumbnailRequestBuilder() {
        }

        public ThumbnailRequest build() {
            return new ThumbnailRequest(this.nodeId, this.ownerId, this.groupShareToken, this.viewBox, this.fitType, this.cropBox);
        }

        public ThumbnailRequestBuilder cropBox(CropBox cropBox) {
            this.cropBox = cropBox;
            return this;
        }

        public ThumbnailRequestBuilder fitType(FitType fitType) {
            this.fitType = fitType;
            return this;
        }

        public ThumbnailRequestBuilder groupShareToken(String str) {
            this.groupShareToken = str;
            return this;
        }

        public ThumbnailRequestBuilder nodeId(@NonNull String str) {
            if (str != null) {
                this.nodeId = str;
                return this;
            }
            throw new NullPointerException("nodeId is marked non-null but is null");
        }

        public ThumbnailRequestBuilder ownerId(String str) {
            this.ownerId = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ThumbnailRequest.ThumbnailRequestBuilder(nodeId=");
            outline107.append(this.nodeId);
            outline107.append(", ownerId=");
            outline107.append(this.ownerId);
            outline107.append(", groupShareToken=");
            outline107.append(this.groupShareToken);
            outline107.append(", viewBox=");
            outline107.append(this.viewBox);
            outline107.append(", fitType=");
            outline107.append(this.fitType);
            outline107.append(", cropBox=");
            outline107.append(this.cropBox);
            outline107.append(")");
            return outline107.toString();
        }

        public ThumbnailRequestBuilder viewBox(ViewBox viewBox) {
            this.viewBox = viewBox;
            return this;
        }
    }

    ThumbnailRequest(@NonNull String str, String str2, String str3, ViewBox viewBox, FitType fitType, CropBox cropBox) {
        if (str != null) {
            this.nodeId = str;
            this.ownerId = str2;
            this.groupShareToken = str3;
            this.viewBox = viewBox;
            this.fitType = fitType;
            this.cropBox = cropBox;
            return;
        }
        throw new NullPointerException("nodeId is marked non-null but is null");
    }

    public static ThumbnailRequestBuilder builder() {
        return new ThumbnailRequestBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ThumbnailRequest;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ThumbnailRequest)) {
            return false;
        }
        ThumbnailRequest thumbnailRequest = (ThumbnailRequest) obj;
        if (!thumbnailRequest.canEqual(this)) {
            return false;
        }
        String nodeId = getNodeId();
        String nodeId2 = thumbnailRequest.getNodeId();
        if (nodeId != null ? !nodeId.equals(nodeId2) : nodeId2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = thumbnailRequest.getOwnerId();
        if (ownerId != null ? !ownerId.equals(ownerId2) : ownerId2 != null) {
            return false;
        }
        String groupShareToken = getGroupShareToken();
        String groupShareToken2 = thumbnailRequest.getGroupShareToken();
        if (groupShareToken != null ? !groupShareToken.equals(groupShareToken2) : groupShareToken2 != null) {
            return false;
        }
        ViewBox viewBox = getViewBox();
        ViewBox viewBox2 = thumbnailRequest.getViewBox();
        if (viewBox != null ? !viewBox.equals(viewBox2) : viewBox2 != null) {
            return false;
        }
        FitType fitType = getFitType();
        FitType fitType2 = thumbnailRequest.getFitType();
        if (fitType != null ? !fitType.equals(fitType2) : fitType2 != null) {
            return false;
        }
        CropBox cropBox = getCropBox();
        CropBox cropBox2 = thumbnailRequest.getCropBox();
        return cropBox != null ? cropBox.equals(cropBox2) : cropBox2 == null;
    }

    public CropBox getCropBox() {
        return this.cropBox;
    }

    public FitType getFitType() {
        return this.fitType;
    }

    public String getGroupShareToken() {
        return this.groupShareToken;
    }

    @NonNull
    public String getNodeId() {
        return this.nodeId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public ViewBox getViewBox() {
        return this.viewBox;
    }

    public int hashCode() {
        String nodeId = getNodeId();
        int i = 43;
        int hashCode = nodeId == null ? 43 : nodeId.hashCode();
        String ownerId = getOwnerId();
        int hashCode2 = ((hashCode + 59) * 59) + (ownerId == null ? 43 : ownerId.hashCode());
        String groupShareToken = getGroupShareToken();
        int hashCode3 = (hashCode2 * 59) + (groupShareToken == null ? 43 : groupShareToken.hashCode());
        ViewBox viewBox = getViewBox();
        int hashCode4 = (hashCode3 * 59) + (viewBox == null ? 43 : viewBox.hashCode());
        FitType fitType = getFitType();
        int hashCode5 = (hashCode4 * 59) + (fitType == null ? 43 : fitType.hashCode());
        CropBox cropBox = getCropBox();
        int i2 = hashCode5 * 59;
        if (cropBox != null) {
            i = cropBox.hashCode();
        }
        return i2 + i;
    }

    public void setCropBox(CropBox cropBox) {
        this.cropBox = cropBox;
    }

    public void setFitType(FitType fitType) {
        this.fitType = fitType;
    }

    public void setGroupShareToken(String str) {
        this.groupShareToken = str;
    }

    public void setNodeId(@NonNull String str) {
        if (str != null) {
            this.nodeId = str;
            return;
        }
        throw new NullPointerException("nodeId is marked non-null but is null");
    }

    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    public void setViewBox(ViewBox viewBox) {
        this.viewBox = viewBox;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ThumbnailRequest(nodeId=");
        outline107.append(getNodeId());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(", groupShareToken=");
        outline107.append(getGroupShareToken());
        outline107.append(", viewBox=");
        outline107.append(getViewBox());
        outline107.append(", fitType=");
        outline107.append(getFitType());
        outline107.append(", cropBox=");
        outline107.append(getCropBox());
        outline107.append(")");
        return outline107.toString();
    }
}
