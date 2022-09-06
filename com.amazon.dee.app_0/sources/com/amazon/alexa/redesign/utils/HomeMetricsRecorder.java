package com.amazon.alexa.redesign.utils;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ClickInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ViewInteractionDetails;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class HomeMetricsRecorder implements HomeContract.HomeMetricsRecorder {
    public static final String APP_COMPONENT = "home_android_2_0";
    @VisibleForTesting
    static final String CARD_CLICK_EVENT = "CardClick";
    @VisibleForTesting
    static final String CARD_VIEW_EVENT = "CardView";
    @VisibleForTesting
    private static final String CHANNEL = "home";
    @VisibleForTesting
    static final String DEFAULT_SUBCOMPONENT = "default_page";
    @VisibleForTesting
    static final String ELEMENT_TYPE = "UI_ELEMENT";
    @VisibleForTesting
    static final String ENTER_EVENT = "Enter";
    @VisibleForTesting
    static final String ENTER_FOREGROUND_EVENT = "Foreground";
    @VisibleForTesting
    static final String EXIT_BACKGROUND_EVENT = "Background";
    @VisibleForTesting
    static final String EXIT_EVENT = "Exit";
    private static final String HOME_CHANNEL_OWNER_IDENTIFIER = "b3d450dc-27f9-4658-8cce-2784e86f06e9";
    @VisibleForTesting
    static final String PREPENDED_VIEW = "PrependedView";
    @VisibleForTesting
    static final String TAG = "HomeChannelMobilytics";
    private Mobilytics mobilytics;

    public HomeMetricsRecorder(Mobilytics mobilytics) {
        this.mobilytics = mobilytics;
    }

    public static String getFieldForEvent(Map<String, Object> map, String str) {
        return map.get(str) instanceof String ? (String) map.get(str) : "";
    }

    @VisibleForTesting
    ClickInteractionDetails createClickInteractionDetails(Map<String, Object> map) {
        ClickInteractionDetails clickInteractionDetails = new ClickInteractionDetails("", "");
        try {
            if (map.containsKey(JsonFields.ACTION_TYPE)) {
                clickInteractionDetails.setActionType((String) map.get(JsonFields.ACTION_TYPE));
            }
            if (map.containsKey(JsonFields.ELEMENT_TYPE)) {
                clickInteractionDetails.setElementType((String) map.get(JsonFields.ELEMENT_TYPE));
            }
            if (map.containsKey("index")) {
                clickInteractionDetails.setIndex((Integer) map.get("index"));
            }
            if (map.containsKey("totalNumberOfItems")) {
                clickInteractionDetails.setTotalNumberOfItems((Integer) map.get("totalNumberOfItems"));
            }
        } catch (ClassCastException e) {
            Log.e(TAG, e.toString());
        }
        return clickInteractionDetails;
    }

    @VisibleForTesting
    MobilyticsUserInteractionEvent createMobilyticsUserInteractionEvent(String str, String str2, InteractionDetails interactionDetails, Map<String, Object> map) {
        MobilyticsUserInteractionEvent createUserInteractionEvent = this.mobilytics.createUserInteractionEvent(str, str2, APP_COMPONENT, DEFAULT_SUBCOMPONENT, "b3d450dc-27f9-4658-8cce-2784e86f06e9");
        createUserInteractionEvent.setChannelName("home");
        createUserInteractionEvent.setInteractionDetails(interactionDetails);
        return map != null ? getEventWithCardData(map, createUserInteractionEvent) : createUserInteractionEvent;
    }

    @VisibleForTesting
    ViewInteractionDetails createViewInteractionDetails(Map<String, Object> map) {
        if (map.containsKey("index") && map.containsKey("totalNumberOfItems")) {
            try {
                int intValue = ((Integer) map.get("index")).intValue();
                int intValue2 = ((Integer) map.get("totalNumberOfItems")).intValue();
                return new ViewInteractionDetails(Integer.valueOf(intValue), Integer.valueOf(intValue2), (Long) map.get("duration"));
            } catch (ClassCastException e) {
                Log.e(TAG, e.toString());
            }
        }
        return new ViewInteractionDetails(null, null, null);
    }

    @VisibleForTesting
    MobilyticsUserInteractionEvent getEventWithCardData(Map<String, Object> map, MobilyticsUserInteractionEvent mobilyticsUserInteractionEvent) {
        if (map.containsKey(EntertainmentConstants.TYPE_SECTION)) {
            mobilyticsUserInteractionEvent.setSubComponent(getFieldForEvent(map, EntertainmentConstants.TYPE_SECTION));
        }
        if (map.containsKey("contentId")) {
            mobilyticsUserInteractionEvent.setContentId(getFieldForEvent(map, "contentId"));
        }
        if (map.containsKey("contentType")) {
            mobilyticsUserInteractionEvent.setContentType(getFieldForEvent(map, "contentType"));
        }
        if (map.containsKey("contentProvider")) {
            mobilyticsUserInteractionEvent.setContentProvider(getFieldForEvent(map, "contentProvider"));
        }
        if (map.containsKey("metaActionType")) {
            mobilyticsUserInteractionEvent.setSourceContext(getFieldForEvent(map, "metaActionType"));
        }
        if (map.containsKey("modelVersion")) {
            mobilyticsUserInteractionEvent.setContentVersion(getFieldForEvent(map, "modelVersion"));
        }
        if (map.containsKey("refMarker")) {
            mobilyticsUserInteractionEvent.setRefMarker(getFieldForEvent(map, "refMarker"));
        }
        return mobilyticsUserInteractionEvent;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.HomeMetricsRecorder
    public long recordClickEvent(Map<String, Object> map) {
        if (map != null) {
            map.put(JsonFields.ELEMENT_TYPE, ELEMENT_TYPE);
            MobilyticsUserInteractionEvent createMobilyticsUserInteractionEvent = createMobilyticsUserInteractionEvent(CARD_CLICK_EVENT, "click", createClickInteractionDetails(map), map);
            this.mobilytics.recordUserInteractionEvent(createMobilyticsUserInteractionEvent);
            return createMobilyticsUserInteractionEvent.getEventTimestamp();
        }
        return -1L;
    }

    @Override // com.amazon.alexa.redesign.HomeContract.HomeMetricsRecorder
    public void recordClickEventFromCards(HashMap<String, Object> hashMap) {
        MobilyticsUserInteractionEvent createMobilyticsUserInteractionEvent = createMobilyticsUserInteractionEvent(CARD_CLICK_EVENT, "click", createClickInteractionDetails(hashMap), null);
        createMobilyticsUserInteractionEvent.setContentId((String) hashMap.get("contentId"));
        createMobilyticsUserInteractionEvent.setContentProvider((String) hashMap.get("contentProvider"));
        createMobilyticsUserInteractionEvent.setContentType((String) hashMap.get("contentType"));
        this.mobilytics.recordUserInteractionEvent(createMobilyticsUserInteractionEvent);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.HomeMetricsRecorder
    public void recordDeepLink(Map<String, Object> map) {
    }

    @Override // com.amazon.alexa.redesign.HomeContract.HomeMetricsRecorder
    public void recordEnterHome(boolean z) {
        this.mobilytics.recordUserInteractionEvent(createMobilyticsUserInteractionEvent(z ? "Foreground" : "Enter", "view", null, null));
    }

    @Override // com.amazon.alexa.redesign.HomeContract.HomeMetricsRecorder
    public void recordExitHome(boolean z) {
        this.mobilytics.recordUserInteractionEvent(createMobilyticsUserInteractionEvent(z ? "Background" : "Exit", "view", null, null));
    }

    @Override // com.amazon.alexa.redesign.HomeContract.HomeMetricsRecorder
    public void recordModalToggle(boolean z, RecyclerViewItem recyclerViewItem, int i, int i2) {
    }

    @Override // com.amazon.alexa.redesign.HomeContract.HomeMetricsRecorder
    public void recordViewEvent(Map<String, Object> map, boolean z) {
        if (map != null) {
            MobilyticsUserInteractionEvent createMobilyticsUserInteractionEvent = createMobilyticsUserInteractionEvent(CARD_VIEW_EVENT, "view", createViewInteractionDetails(map), map);
            if (z) {
                createMobilyticsUserInteractionEvent.setRefMarker(PREPENDED_VIEW);
            }
            this.mobilytics.recordUserInteractionEvent(createMobilyticsUserInteractionEvent);
        }
    }
}
