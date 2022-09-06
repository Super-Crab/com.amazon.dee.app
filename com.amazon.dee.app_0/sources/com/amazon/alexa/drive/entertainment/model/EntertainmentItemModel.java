package com.amazon.alexa.drive.entertainment.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class EntertainmentItemModel {
    @SerializedName("contentToken")
    @Expose
    private String contentToken;
    @SerializedName("providerId")
    @Expose
    private String providerId;
    @SerializedName("subtitle")
    @Expose
    private String subTitle;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_IMAGES)
    @Expose
    private List<Object> images = null;
    @SerializedName(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS)
    @Expose
    private List<Object> actions = null;

    public List<Object> getActions() {
        return this.actions;
    }

    public String getContentToken() {
        return this.contentToken;
    }

    public List<Object> getImages() {
        return this.images;
    }

    public String getProviderId() {
        return this.providerId;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getTitle() {
        return this.title;
    }
}
