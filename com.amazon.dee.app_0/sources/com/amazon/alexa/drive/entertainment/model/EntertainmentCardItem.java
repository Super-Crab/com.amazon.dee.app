package com.amazon.alexa.drive.entertainment.model;

import java.util.List;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class EntertainmentCardItem {
    private List<JSONObject> mActions;
    private List<JSONObject> mAlbumArtUrls;
    private boolean mIsLoading;
    private PlayPayload mPlayPayload;
    private String mPlayUri;
    private String mProviderId;
    private String mQueueId;
    private String mSubTitle;
    private String mTitle;

    public List<JSONObject> getActions() {
        return this.mActions;
    }

    public List<JSONObject> getAlbumArtUrls() {
        return this.mAlbumArtUrls;
    }

    public PlayPayload getPlayPayload() {
        return this.mPlayPayload;
    }

    public String getPlayUri() {
        return this.mPlayUri;
    }

    public String getProviderId() {
        return this.mProviderId;
    }

    public String getQueueId() {
        return this.mQueueId;
    }

    public String getSubTitle() {
        return this.mSubTitle;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public void setActions(List<JSONObject> list) {
        this.mActions = list;
    }

    public void setAlbumArtUrls(List<JSONObject> list) {
        this.mAlbumArtUrls = list;
    }

    public void setIsLoading(boolean z) {
        this.mIsLoading = z;
    }

    public void setPlayPayload(PlayPayload playPayload) {
        this.mPlayPayload = playPayload;
    }

    public void setPlayUri(String str) {
        this.mPlayUri = str;
    }

    public void setProviderId(String str) {
        this.mProviderId = str;
    }

    public void setQueueId(String str) {
        this.mQueueId = str;
    }

    public void setSubTitle(String str) {
        this.mSubTitle = str;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }
}
