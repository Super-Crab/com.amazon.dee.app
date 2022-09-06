package com.amazon.alexa.redesign.view.templates.domainCardTemplate;

import androidx.annotation.NonNull;
import com.amazon.alexa.viewprovider.api.event.personalization.InteractionEventData;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes10.dex */
public class EventCache {
    private ConcurrentHashMap<String, InteractionEventData> uniqueClickEvent = new ConcurrentHashMap<>();

    private String getUniqueId(@NonNull InteractionEventData interactionEventData) {
        String str = "";
        String contentId = interactionEventData.getContentId() != null ? interactionEventData.getContentId() : str;
        String contentType = interactionEventData.getContentType() != null ? interactionEventData.getContentType() : str;
        if (interactionEventData.getContentProvider() != null) {
            str = interactionEventData.getContentProvider();
        }
        return GeneratedOutlineSupport1.outline75(contentId, contentType, str);
    }

    public void addClickEvent(@NonNull InteractionEventData interactionEventData) {
        this.uniqueClickEvent.put(getUniqueId(interactionEventData), interactionEventData);
    }

    public void clearClickEvents() {
        this.uniqueClickEvent.clear();
    }

    public boolean clickEventExists(@NonNull InteractionEventData interactionEventData) {
        return this.uniqueClickEvent.containsKey(getUniqueId(interactionEventData));
    }
}
