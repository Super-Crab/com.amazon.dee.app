package com.amazon.alexa.viewprovider.api.event.personalization;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes.dex */
public class InteractionEventData {
    private String actionType;
    private String contentId;
    private String contentProvider;
    private String contentSource;
    private String contentType;
    private String metaActionType;
    @SerializedName("navigationEvent")
    private boolean navigateEvent = true;
    private PersonalizationData personalizationData;

    /* loaded from: classes.dex */
    public static final class InteractionEventDataBuilder {
        private String actionType;
        private String contentId;
        private String contentProvider;
        private String contentSource;
        private String contentType;
        private String metaActionType;
        private boolean navigateEvent = true;
        private PersonalizationData personalizationData;

        public InteractionEventData build() {
            InteractionEventData interactionEventData = new InteractionEventData();
            interactionEventData.navigateEvent = this.navigateEvent;
            interactionEventData.contentSource = this.contentSource;
            interactionEventData.personalizationData = this.personalizationData;
            interactionEventData.contentId = this.contentId;
            interactionEventData.contentProvider = this.contentProvider;
            interactionEventData.contentType = this.contentType;
            interactionEventData.metaActionType = this.metaActionType;
            interactionEventData.actionType = this.actionType;
            return interactionEventData;
        }

        public InteractionEventDataBuilder withActionType(String str) {
            this.actionType = str;
            return this;
        }

        public InteractionEventDataBuilder withContentId(String str) {
            this.contentId = str;
            return this;
        }

        public InteractionEventDataBuilder withContentProvider(String str) {
            this.contentProvider = str;
            return this;
        }

        public InteractionEventDataBuilder withContentSource(String str) {
            this.contentSource = str;
            return this;
        }

        public InteractionEventDataBuilder withContentType(String str) {
            this.contentType = str;
            return this;
        }

        public InteractionEventDataBuilder withMetaActionType(String str) {
            this.metaActionType = str;
            return this;
        }

        public InteractionEventDataBuilder withNavigateEvent(boolean z) {
            this.navigateEvent = z;
            return this;
        }

        public InteractionEventDataBuilder withPersonalizationData(PersonalizationData personalizationData) {
            this.personalizationData = personalizationData;
            return this;
        }
    }

    public String getActionType() {
        return this.actionType;
    }

    public String getContentId() {
        return this.contentId;
    }

    public String getContentProvider() {
        return this.contentProvider;
    }

    public String getContentSource() {
        return this.contentSource;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getMetaActionType() {
        return this.metaActionType;
    }

    public PersonalizationData getPersonalizationData() {
        return this.personalizationData;
    }

    public boolean isNavigateEvent() {
        return this.navigateEvent;
    }
}
