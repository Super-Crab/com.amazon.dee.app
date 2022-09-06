package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ContentSignature {
    @JsonProperty("contentSignature")
    private String contentSignature;
    @JsonProperty("contentSignatureType")
    private ContentSignatureType contentSignatureType;

    protected boolean canEqual(Object obj) {
        return obj instanceof ContentSignature;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContentSignature)) {
            return false;
        }
        ContentSignature contentSignature = (ContentSignature) obj;
        if (!contentSignature.canEqual(this)) {
            return false;
        }
        ContentSignatureType contentSignatureType = getContentSignatureType();
        ContentSignatureType contentSignatureType2 = contentSignature.getContentSignatureType();
        if (contentSignatureType != null ? !contentSignatureType.equals(contentSignatureType2) : contentSignatureType2 != null) {
            return false;
        }
        String contentSignature2 = getContentSignature();
        String contentSignature3 = contentSignature.getContentSignature();
        return contentSignature2 != null ? contentSignature2.equals(contentSignature3) : contentSignature3 == null;
    }

    public String getContentSignature() {
        return this.contentSignature;
    }

    public ContentSignatureType getContentSignatureType() {
        return this.contentSignatureType;
    }

    public int hashCode() {
        ContentSignatureType contentSignatureType = getContentSignatureType();
        int i = 43;
        int hashCode = contentSignatureType == null ? 43 : contentSignatureType.hashCode();
        String contentSignature = getContentSignature();
        int i2 = (hashCode + 59) * 59;
        if (contentSignature != null) {
            i = contentSignature.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("contentSignature")
    public void setContentSignature(String str) {
        this.contentSignature = str;
    }

    @JsonProperty("contentSignatureType")
    public void setContentSignatureType(ContentSignatureType contentSignatureType) {
        this.contentSignatureType = contentSignatureType;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ContentSignature(contentSignatureType=");
        outline107.append(getContentSignatureType());
        outline107.append(", contentSignature=");
        outline107.append(getContentSignature());
        outline107.append(")");
        return outline107.toString();
    }
}
