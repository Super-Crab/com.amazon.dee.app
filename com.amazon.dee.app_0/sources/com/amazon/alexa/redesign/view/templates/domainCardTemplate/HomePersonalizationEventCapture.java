package com.amazon.alexa.redesign.view.templates.domainCardTemplate;

import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.presenter.ClickToDismissHandler;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.event.personalization.InteractionEventData;
import com.amazon.alexa.viewprovider.api.event.personalization.PersonalizationData;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class HomePersonalizationEventCapture implements EventCapture {
    final ClickToDismissHandler clickToDismissHandler;
    private final HomeContract.HomeMetricsRecorder homeMetricsRecorder;
    private final PrependedViewTracker prependedViewTracker;

    public HomePersonalizationEventCapture(@NonNull HomeContract.HomeMetricsRecorder homeMetricsRecorder, @NonNull PrependedViewTracker prependedViewTracker, @NonNull ClickToDismissHandler clickToDismissHandler) {
        this.homeMetricsRecorder = homeMetricsRecorder;
        this.prependedViewTracker = prependedViewTracker;
        this.clickToDismissHandler = clickToDismissHandler;
    }

    @Override // com.amazon.alexa.viewprovider.api.event.EventCapture
    public void captureClick(@NonNull InteractionEventData interactionEventData) {
        HashMap hashMap = new HashMap();
        hashMap.put("contentId", interactionEventData.getContentId());
        hashMap.put("contentType", interactionEventData.getContentType());
        hashMap.put("contentProvider", interactionEventData.getContentProvider());
        hashMap.put("contentSource", interactionEventData.getContentSource());
        hashMap.put(JsonFields.ACTION_TYPE, interactionEventData.getActionType());
        hashMap.put("metaActionType", interactionEventData.getMetaActionType());
        PersonalizationData personalizationData = interactionEventData.getPersonalizationData();
        if (personalizationData != null) {
            hashMap.put("modelVersion", personalizationData.getModelVersion());
            hashMap.put(EntertainmentConstants.TYPE_SECTION, personalizationData.getSection());
            hashMap.put("index", Integer.valueOf(personalizationData.getIndex()));
            hashMap.put("totalNumberOfItems", Integer.valueOf(personalizationData.getTotalNumberOfItems()));
        }
        this.homeMetricsRecorder.recordClickEvent(hashMap);
        if (this.prependedViewTracker.prependView(interactionEventData)) {
            this.homeMetricsRecorder.recordViewEvent(hashMap, true);
        }
        String contentId = interactionEventData.getContentId();
        String contentProvider = interactionEventData.getContentProvider();
        String contentType = interactionEventData.getContentType();
        if (contentId == null || contentProvider == null || contentType == null) {
            return;
        }
        this.clickToDismissHandler.handleClickToDismissForCustomCards(contentId, contentProvider, contentType);
    }
}
