package com.amazon.clouddrive.cdasdk.prompto.photos;

import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class PhotoResponse {
    @JsonProperty("cropBox")
    private CropBoxResponse cropBox;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    private String nodeId;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    private String ownerId;
    @JsonProperty("url")
    private String url;
    @JsonProperty("width")
    private Integer width;

    protected boolean canEqual(Object obj) {
        return obj instanceof PhotoResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PhotoResponse)) {
            return false;
        }
        PhotoResponse photoResponse = (PhotoResponse) obj;
        if (!photoResponse.canEqual(this)) {
            return false;
        }
        String nodeId = getNodeId();
        String nodeId2 = photoResponse.getNodeId();
        if (nodeId != null ? !nodeId.equals(nodeId2) : nodeId2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = photoResponse.getOwnerId();
        if (ownerId != null ? !ownerId.equals(ownerId2) : ownerId2 != null) {
            return false;
        }
        CropBoxResponse cropBox = getCropBox();
        CropBoxResponse cropBox2 = photoResponse.getCropBox();
        if (cropBox != null ? !cropBox.equals(cropBox2) : cropBox2 != null) {
            return false;
        }
        Integer width = getWidth();
        Integer width2 = photoResponse.getWidth();
        if (width != null ? !width.equals(width2) : width2 != null) {
            return false;
        }
        Integer height = getHeight();
        Integer height2 = photoResponse.getHeight();
        if (height != null ? !height.equals(height2) : height2 != null) {
            return false;
        }
        String url = getUrl();
        String url2 = photoResponse.getUrl();
        return url != null ? url.equals(url2) : url2 == null;
    }

    public CropBoxResponse getCropBox() {
        return this.cropBox;
    }

    public Integer getHeight() {
        return this.height;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getUrl() {
        return this.url;
    }

    public Integer getWidth() {
        return this.width;
    }

    public int hashCode() {
        String nodeId = getNodeId();
        int i = 43;
        int hashCode = nodeId == null ? 43 : nodeId.hashCode();
        String ownerId = getOwnerId();
        int hashCode2 = ((hashCode + 59) * 59) + (ownerId == null ? 43 : ownerId.hashCode());
        CropBoxResponse cropBox = getCropBox();
        int hashCode3 = (hashCode2 * 59) + (cropBox == null ? 43 : cropBox.hashCode());
        Integer width = getWidth();
        int hashCode4 = (hashCode3 * 59) + (width == null ? 43 : width.hashCode());
        Integer height = getHeight();
        int hashCode5 = (hashCode4 * 59) + (height == null ? 43 : height.hashCode());
        String url = getUrl();
        int i2 = hashCode5 * 59;
        if (url != null) {
            i = url.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("cropBox")
    public void setCropBox(CropBoxResponse cropBoxResponse) {
        this.cropBox = cropBoxResponse;
    }

    @JsonProperty("height")
    public void setHeight(Integer num) {
        this.height = num;
    }

    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    public void setNodeId(String str) {
        this.nodeId = str;
    }

    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    @JsonProperty("url")
    public void setUrl(String str) {
        this.url = str;
    }

    @JsonProperty("width")
    public void setWidth(Integer num) {
        this.width = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PhotoResponse(nodeId=");
        outline107.append(getNodeId());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(", cropBox=");
        outline107.append(getCropBox());
        outline107.append(", width=");
        outline107.append(getWidth());
        outline107.append(", height=");
        outline107.append(getHeight());
        outline107.append(", url=");
        outline107.append(getUrl());
        outline107.append(")");
        return outline107.toString();
    }
}
