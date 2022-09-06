package com.amazon.clouddrive.cdasdk.dps.collections;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class CollectionsResponse {
    @JsonProperty("collectionId")
    private String collectionId;
    @JsonProperty("collectionName")
    private String collectionName;
    @JsonProperty("collectionType")
    private String collectionType;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("coverPhotoUrls")
    private List<String> coverPhotoUrls;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionsResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionsResponse)) {
            return false;
        }
        CollectionsResponse collectionsResponse = (CollectionsResponse) obj;
        if (!collectionsResponse.canEqual(this)) {
            return false;
        }
        String collectionId = getCollectionId();
        String collectionId2 = collectionsResponse.getCollectionId();
        if (collectionId != null ? !collectionId.equals(collectionId2) : collectionId2 != null) {
            return false;
        }
        String collectionName = getCollectionName();
        String collectionName2 = collectionsResponse.getCollectionName();
        if (collectionName != null ? !collectionName.equals(collectionName2) : collectionName2 != null) {
            return false;
        }
        String collectionType = getCollectionType();
        String collectionType2 = collectionsResponse.getCollectionType();
        if (collectionType != null ? !collectionType.equals(collectionType2) : collectionType2 != null) {
            return false;
        }
        List<String> coverPhotoUrls = getCoverPhotoUrls();
        List<String> coverPhotoUrls2 = collectionsResponse.getCoverPhotoUrls();
        if (coverPhotoUrls != null ? !coverPhotoUrls.equals(coverPhotoUrls2) : coverPhotoUrls2 != null) {
            return false;
        }
        Integer count = getCount();
        Integer count2 = collectionsResponse.getCount();
        return count != null ? count.equals(count2) : count2 == null;
    }

    public String getCollectionId() {
        return this.collectionId;
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public String getCollectionType() {
        return this.collectionType;
    }

    public Integer getCount() {
        return this.count;
    }

    public List<String> getCoverPhotoUrls() {
        return this.coverPhotoUrls;
    }

    public int hashCode() {
        String collectionId = getCollectionId();
        int i = 43;
        int hashCode = collectionId == null ? 43 : collectionId.hashCode();
        String collectionName = getCollectionName();
        int hashCode2 = ((hashCode + 59) * 59) + (collectionName == null ? 43 : collectionName.hashCode());
        String collectionType = getCollectionType();
        int hashCode3 = (hashCode2 * 59) + (collectionType == null ? 43 : collectionType.hashCode());
        List<String> coverPhotoUrls = getCoverPhotoUrls();
        int hashCode4 = (hashCode3 * 59) + (coverPhotoUrls == null ? 43 : coverPhotoUrls.hashCode());
        Integer count = getCount();
        int i2 = hashCode4 * 59;
        if (count != null) {
            i = count.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("collectionId")
    public void setCollectionId(String str) {
        this.collectionId = str;
    }

    @JsonProperty("collectionName")
    public void setCollectionName(String str) {
        this.collectionName = str;
    }

    @JsonProperty("collectionType")
    public void setCollectionType(String str) {
        this.collectionType = str;
    }

    @JsonProperty("count")
    public void setCount(Integer num) {
        this.count = num;
    }

    @JsonProperty("coverPhotoUrls")
    public void setCoverPhotoUrls(List<String> list) {
        this.coverPhotoUrls = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CollectionsResponse(collectionId=");
        outline107.append(getCollectionId());
        outline107.append(", collectionName=");
        outline107.append(getCollectionName());
        outline107.append(", collectionType=");
        outline107.append(getCollectionType());
        outline107.append(", coverPhotoUrls=");
        outline107.append(getCoverPhotoUrls());
        outline107.append(", count=");
        outline107.append(getCount());
        outline107.append(")");
        return outline107.toString();
    }
}
