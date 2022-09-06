package com.amazon.alexa.redesign.view.templates.domainCardTemplate;

import androidx.annotation.NonNull;
import com.amazon.alexa.viewprovider.api.event.personalization.InteractionEventData;
/* loaded from: classes10.dex */
public class PrependedViewTracker {
    private final EventCache eventCache;

    public PrependedViewTracker(@NonNull EventCache eventCache) {
        this.eventCache = eventCache;
    }

    public boolean prependView(@NonNull InteractionEventData interactionEventData) {
        if (interactionEventData.isNavigateEvent() || !this.eventCache.clickEventExists(interactionEventData)) {
            this.eventCache.addClickEvent(interactionEventData);
            return false;
        }
        return true;
    }
}
