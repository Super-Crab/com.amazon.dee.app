package com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails;

import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
/* loaded from: classes9.dex */
public class DefaultPageViewInteractionDetails implements PageViewInteractionDetails {
    private final Long duration;
    private final String interactionType = InteractionType.PAGE_VIEW;

    public DefaultPageViewInteractionDetails(Long l) {
        this.duration = l;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.PageViewInteractionDetails
    public Long duration() {
        return this.duration;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails
    public String getInteractionType() {
        return InteractionType.PAGE_VIEW;
    }
}
