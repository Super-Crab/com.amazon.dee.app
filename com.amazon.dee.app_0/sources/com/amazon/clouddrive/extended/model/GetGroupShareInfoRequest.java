package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class GetGroupShareInfoRequest implements CloudDriveRequest {
    private final String lang;
    private final String shareToken;

    /* loaded from: classes11.dex */
    public static class GetGroupShareInfoRequestBuilder {
        private String lang;
        private String shareToken;

        GetGroupShareInfoRequestBuilder() {
        }

        public GetGroupShareInfoRequest build() {
            return new GetGroupShareInfoRequest(this.shareToken, this.lang);
        }

        public GetGroupShareInfoRequestBuilder lang(String str) {
            this.lang = str;
            return this;
        }

        public GetGroupShareInfoRequestBuilder shareToken(String str) {
            this.shareToken = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupShareInfoRequest.GetGroupShareInfoRequestBuilder(shareToken=");
            outline107.append(this.shareToken);
            outline107.append(", lang=");
            return GeneratedOutlineSupport1.outline91(outline107, this.lang, ")");
        }
    }

    GetGroupShareInfoRequest(String str, String str2) {
        this.shareToken = str;
        this.lang = str2;
    }

    public static GetGroupShareInfoRequestBuilder builder() {
        return new GetGroupShareInfoRequestBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetGroupShareInfoRequest)) {
            return false;
        }
        GetGroupShareInfoRequest getGroupShareInfoRequest = (GetGroupShareInfoRequest) obj;
        String shareToken = getShareToken();
        String shareToken2 = getGroupShareInfoRequest.getShareToken();
        if (shareToken != null ? !shareToken.equals(shareToken2) : shareToken2 != null) {
            return false;
        }
        String lang = getLang();
        String lang2 = getGroupShareInfoRequest.getLang();
        return lang != null ? lang.equals(lang2) : lang2 == null;
    }

    public String getLang() {
        return this.lang;
    }

    public String getShareToken() {
        return this.shareToken;
    }

    public int hashCode() {
        String shareToken = getShareToken();
        int i = 43;
        int hashCode = shareToken == null ? 43 : shareToken.hashCode();
        String lang = getLang();
        int i2 = (hashCode + 59) * 59;
        if (lang != null) {
            i = lang.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupShareInfoRequest(shareToken=");
        outline107.append(getShareToken());
        outline107.append(", lang=");
        outline107.append(getLang());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetGroupShareInfoRequest)) {
            return GetGroupShareInfoRequest.class.getName().compareTo(cloudDriveRequest.getClass().getName());
        }
        GetGroupShareInfoRequest getGroupShareInfoRequest = (GetGroupShareInfoRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getShareToken(), getGroupShareInfoRequest.getShareToken());
        return compare != 0 ? compare : ObjectComparator.compare(getLang(), getGroupShareInfoRequest.getLang());
    }
}
