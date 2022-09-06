package com.amazon.deecomms.calling.incallexperiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class ApplicationAction {
    @JsonProperty("actionId")
    String actionId;
    @JsonProperty("thumbnail")
    String thumbnailUrl;

    public String getActionId() {
        return this.actionId;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setActionId(String str) {
        this.actionId = str;
    }

    public void setThumbnailUrl(String str) {
        this.thumbnailUrl = str;
    }
}
