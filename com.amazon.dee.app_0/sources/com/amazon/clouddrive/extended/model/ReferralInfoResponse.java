package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class ReferralInfoResponse {
    private final String legalPath;
    private final String legalTitle;
    private final String referralToken;
    private final String referralUrl;
    private final String shareEmailSubject;
    private final String shareMessageBody;
    private final String subtitle;
    private final String title;

    /* loaded from: classes11.dex */
    public static class ReferralInfoResponseBuilder {
        private String legalPath;
        private String legalTitle;
        private String referralToken;
        private String referralUrl;
        private String shareEmailSubject;
        private String shareMessageBody;
        private String subtitle;
        private String title;

        ReferralInfoResponseBuilder() {
        }

        public ReferralInfoResponse build() {
            return new ReferralInfoResponse(this.referralToken, this.referralUrl, this.title, this.subtitle, this.legalTitle, this.legalPath, this.shareEmailSubject, this.shareMessageBody);
        }

        public ReferralInfoResponseBuilder legalPath(String str) {
            this.legalPath = str;
            return this;
        }

        public ReferralInfoResponseBuilder legalTitle(String str) {
            this.legalTitle = str;
            return this;
        }

        public ReferralInfoResponseBuilder referralToken(String str) {
            this.referralToken = str;
            return this;
        }

        public ReferralInfoResponseBuilder referralUrl(String str) {
            this.referralUrl = str;
            return this;
        }

        public ReferralInfoResponseBuilder shareEmailSubject(String str) {
            this.shareEmailSubject = str;
            return this;
        }

        public ReferralInfoResponseBuilder shareMessageBody(String str) {
            this.shareMessageBody = str;
            return this;
        }

        public ReferralInfoResponseBuilder subtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public ReferralInfoResponseBuilder title(String str) {
            this.title = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReferralInfoResponse.ReferralInfoResponseBuilder(referralToken=");
            outline107.append(this.referralToken);
            outline107.append(", referralUrl=");
            outline107.append(this.referralUrl);
            outline107.append(", title=");
            outline107.append(this.title);
            outline107.append(", subtitle=");
            outline107.append(this.subtitle);
            outline107.append(", legalTitle=");
            outline107.append(this.legalTitle);
            outline107.append(", legalPath=");
            outline107.append(this.legalPath);
            outline107.append(", shareEmailSubject=");
            outline107.append(this.shareEmailSubject);
            outline107.append(", shareMessageBody=");
            return GeneratedOutlineSupport1.outline91(outline107, this.shareMessageBody, ")");
        }
    }

    ReferralInfoResponse(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.referralToken = str;
        this.referralUrl = str2;
        this.title = str3;
        this.subtitle = str4;
        this.legalTitle = str5;
        this.legalPath = str6;
        this.shareEmailSubject = str7;
        this.shareMessageBody = str8;
    }

    public static ReferralInfoResponseBuilder builder() {
        return new ReferralInfoResponseBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReferralInfoResponse)) {
            return false;
        }
        ReferralInfoResponse referralInfoResponse = (ReferralInfoResponse) obj;
        String referralToken = getReferralToken();
        String referralToken2 = referralInfoResponse.getReferralToken();
        if (referralToken != null ? !referralToken.equals(referralToken2) : referralToken2 != null) {
            return false;
        }
        String referralUrl = getReferralUrl();
        String referralUrl2 = referralInfoResponse.getReferralUrl();
        if (referralUrl != null ? !referralUrl.equals(referralUrl2) : referralUrl2 != null) {
            return false;
        }
        String title = getTitle();
        String title2 = referralInfoResponse.getTitle();
        if (title != null ? !title.equals(title2) : title2 != null) {
            return false;
        }
        String subtitle = getSubtitle();
        String subtitle2 = referralInfoResponse.getSubtitle();
        if (subtitle != null ? !subtitle.equals(subtitle2) : subtitle2 != null) {
            return false;
        }
        String legalTitle = getLegalTitle();
        String legalTitle2 = referralInfoResponse.getLegalTitle();
        if (legalTitle != null ? !legalTitle.equals(legalTitle2) : legalTitle2 != null) {
            return false;
        }
        String legalPath = getLegalPath();
        String legalPath2 = referralInfoResponse.getLegalPath();
        if (legalPath != null ? !legalPath.equals(legalPath2) : legalPath2 != null) {
            return false;
        }
        String shareEmailSubject = getShareEmailSubject();
        String shareEmailSubject2 = referralInfoResponse.getShareEmailSubject();
        if (shareEmailSubject != null ? !shareEmailSubject.equals(shareEmailSubject2) : shareEmailSubject2 != null) {
            return false;
        }
        String shareMessageBody = getShareMessageBody();
        String shareMessageBody2 = referralInfoResponse.getShareMessageBody();
        return shareMessageBody != null ? shareMessageBody.equals(shareMessageBody2) : shareMessageBody2 == null;
    }

    public String getLegalPath() {
        return this.legalPath;
    }

    public String getLegalTitle() {
        return this.legalTitle;
    }

    public String getReferralToken() {
        return this.referralToken;
    }

    public String getReferralUrl() {
        return this.referralUrl;
    }

    public String getShareEmailSubject() {
        return this.shareEmailSubject;
    }

    public String getShareMessageBody() {
        return this.shareMessageBody;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        String referralToken = getReferralToken();
        int i = 43;
        int hashCode = referralToken == null ? 43 : referralToken.hashCode();
        String referralUrl = getReferralUrl();
        int hashCode2 = ((hashCode + 59) * 59) + (referralUrl == null ? 43 : referralUrl.hashCode());
        String title = getTitle();
        int hashCode3 = (hashCode2 * 59) + (title == null ? 43 : title.hashCode());
        String subtitle = getSubtitle();
        int hashCode4 = (hashCode3 * 59) + (subtitle == null ? 43 : subtitle.hashCode());
        String legalTitle = getLegalTitle();
        int hashCode5 = (hashCode4 * 59) + (legalTitle == null ? 43 : legalTitle.hashCode());
        String legalPath = getLegalPath();
        int hashCode6 = (hashCode5 * 59) + (legalPath == null ? 43 : legalPath.hashCode());
        String shareEmailSubject = getShareEmailSubject();
        int hashCode7 = (hashCode6 * 59) + (shareEmailSubject == null ? 43 : shareEmailSubject.hashCode());
        String shareMessageBody = getShareMessageBody();
        int i2 = hashCode7 * 59;
        if (shareMessageBody != null) {
            i = shareMessageBody.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReferralInfoResponse(referralToken=");
        outline107.append(getReferralToken());
        outline107.append(", referralUrl=");
        outline107.append(getReferralUrl());
        outline107.append(", title=");
        outline107.append(getTitle());
        outline107.append(", subtitle=");
        outline107.append(getSubtitle());
        outline107.append(", legalTitle=");
        outline107.append(getLegalTitle());
        outline107.append(", legalPath=");
        outline107.append(getLegalPath());
        outline107.append(", shareEmailSubject=");
        outline107.append(getShareEmailSubject());
        outline107.append(", shareMessageBody=");
        outline107.append(getShareMessageBody());
        outline107.append(")");
        return outline107.toString();
    }
}
