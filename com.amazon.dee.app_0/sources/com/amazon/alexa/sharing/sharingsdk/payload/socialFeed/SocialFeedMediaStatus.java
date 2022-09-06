package com.amazon.alexa.sharing.sharingsdk.payload.socialFeed;
/* loaded from: classes10.dex */
public enum SocialFeedMediaStatus {
    AVAILABLE("Available"),
    FAILED_DOWNLOAD("FailedDownload"),
    DOWNLOADING("Downloading");
    
    public final String status;

    SocialFeedMediaStatus(String str) {
        this.status = str;
    }
}
