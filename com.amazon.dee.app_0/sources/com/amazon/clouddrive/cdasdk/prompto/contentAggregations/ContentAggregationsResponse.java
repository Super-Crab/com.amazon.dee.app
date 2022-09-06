package com.amazon.clouddrive.cdasdk.prompto.contentAggregations;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ContentAggregationsResponse {
    @JsonProperty("albumCount")
    private Long albumCount;
    @JsonProperty("photoCount")
    private Long photoCount;
    @JsonProperty("reactionCount")
    private Long reactionCount;
    @JsonProperty("videoCount")
    private Long videoCount;

    protected boolean canEqual(Object obj) {
        return obj instanceof ContentAggregationsResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContentAggregationsResponse)) {
            return false;
        }
        ContentAggregationsResponse contentAggregationsResponse = (ContentAggregationsResponse) obj;
        if (!contentAggregationsResponse.canEqual(this)) {
            return false;
        }
        Long videoCount = getVideoCount();
        Long videoCount2 = contentAggregationsResponse.getVideoCount();
        if (videoCount != null ? !videoCount.equals(videoCount2) : videoCount2 != null) {
            return false;
        }
        Long photoCount = getPhotoCount();
        Long photoCount2 = contentAggregationsResponse.getPhotoCount();
        if (photoCount != null ? !photoCount.equals(photoCount2) : photoCount2 != null) {
            return false;
        }
        Long albumCount = getAlbumCount();
        Long albumCount2 = contentAggregationsResponse.getAlbumCount();
        if (albumCount != null ? !albumCount.equals(albumCount2) : albumCount2 != null) {
            return false;
        }
        Long reactionCount = getReactionCount();
        Long reactionCount2 = contentAggregationsResponse.getReactionCount();
        return reactionCount != null ? reactionCount.equals(reactionCount2) : reactionCount2 == null;
    }

    public Long getAlbumCount() {
        return this.albumCount;
    }

    public Long getPhotoCount() {
        return this.photoCount;
    }

    public Long getReactionCount() {
        return this.reactionCount;
    }

    public Long getVideoCount() {
        return this.videoCount;
    }

    public int hashCode() {
        Long videoCount = getVideoCount();
        int i = 43;
        int hashCode = videoCount == null ? 43 : videoCount.hashCode();
        Long photoCount = getPhotoCount();
        int hashCode2 = ((hashCode + 59) * 59) + (photoCount == null ? 43 : photoCount.hashCode());
        Long albumCount = getAlbumCount();
        int hashCode3 = (hashCode2 * 59) + (albumCount == null ? 43 : albumCount.hashCode());
        Long reactionCount = getReactionCount();
        int i2 = hashCode3 * 59;
        if (reactionCount != null) {
            i = reactionCount.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("albumCount")
    public void setAlbumCount(Long l) {
        this.albumCount = l;
    }

    @JsonProperty("photoCount")
    public void setPhotoCount(Long l) {
        this.photoCount = l;
    }

    @JsonProperty("reactionCount")
    public void setReactionCount(Long l) {
        this.reactionCount = l;
    }

    @JsonProperty("videoCount")
    public void setVideoCount(Long l) {
        this.videoCount = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ContentAggregationsResponse(videoCount=");
        outline107.append(getVideoCount());
        outline107.append(", photoCount=");
        outline107.append(getPhotoCount());
        outline107.append(", albumCount=");
        outline107.append(getAlbumCount());
        outline107.append(", reactionCount=");
        outline107.append(getReactionCount());
        outline107.append(")");
        return outline107.toString();
    }
}
