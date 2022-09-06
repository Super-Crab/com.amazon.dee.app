package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class GroupShareResponse implements CloudDriveResponse {
    private String inviteUrl;
    private String shareToken;
    private String shareUrl;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GroupShareResponse groupShareResponse = (GroupShareResponse) obj;
        if (getShareToken() == null ? groupShareResponse.getShareToken() == null : getShareToken().equals(groupShareResponse.getShareToken())) {
            if (getShareUrl() == null ? groupShareResponse.getShareUrl() == null : getShareUrl().equals(groupShareResponse.getShareUrl())) {
                if (getInviteUrl() != null) {
                    if (getInviteUrl().equals(groupShareResponse.getInviteUrl())) {
                        return true;
                    }
                } else if (groupShareResponse.getInviteUrl() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getInviteUrl() {
        return this.inviteUrl;
    }

    public String getShareToken() {
        return this.shareToken;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((getShareToken() != null ? getShareToken().hashCode() : 0) * 31) + (getShareUrl() != null ? getShareUrl().hashCode() : 0)) * 31;
        if (getInviteUrl() != null) {
            i = getInviteUrl().hashCode();
        }
        return hashCode + i;
    }

    public void setInviteUrl(String str) {
        this.inviteUrl = str;
    }

    public void setShareToken(String str) {
        this.shareToken = str;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GroupShareResponse{shareToken='");
        GeneratedOutlineSupport1.outline176(outline107, this.shareToken, Chars.QUOTE, ", shareUrl='");
        GeneratedOutlineSupport1.outline176(outline107, this.shareUrl, Chars.QUOTE, ", inviteUrl='");
        return GeneratedOutlineSupport1.outline90(outline107, this.inviteUrl, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GroupShareResponse)) {
            return 1;
        }
        GroupShareResponse groupShareResponse = (GroupShareResponse) cloudDriveResponse;
        int compare = ObjectComparator.compare(getShareUrl(), groupShareResponse.getShareUrl());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getShareToken(), groupShareResponse.getShareToken());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getInviteUrl(), groupShareResponse.getInviteUrl());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
