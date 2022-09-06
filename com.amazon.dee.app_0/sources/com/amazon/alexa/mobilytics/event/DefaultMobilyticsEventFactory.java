package com.amazon.alexa.mobilytics.event;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.MobilyticsException;
import com.amazon.alexa.mobilytics.event.metadata.CommsMetadata;
import com.amazon.alexa.mobilytics.event.metadata.EntertainmentMetadata;
import com.amazon.alexa.mobilytics.event.metadata.EventMetadata;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsErrorEvent;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mobilytics.event.userinteraction.DefaultMobilyticsUserInteractionEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ClickInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.DeepLinkClickInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.DefaultPageViewInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.PageViewInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.SliderInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.UtteranceInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ViewInteractionDetails;
import com.google.common.primitives.Doubles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public final class DefaultMobilyticsEventFactory implements MobilyticsEventFactory {
    static final String ACTION_TYPE_KEY = "actionType";
    static final String CALL_TYPE_KEY = "callType";
    static final String CHANNEL_NAME_KEY = "channelName";
    static final String CONTENT_ID_KEY = "contentId";
    static final String CONTENT_PROVIDER_KEY = "contentProvider";
    static final String CONTENT_TYPE_KEY = "contentType";
    static final String CONTENT_VERSION_KEY = "contentVersion";
    static final String COUNTER_VALUE_KEY = "CounterValue";
    static final String DATA_KEY = "Data";
    static final String DESTINATION_APP_KEY = "destinationApp";
    static final String DESTINATION_SCREEN_KEY = "destinationScreen";
    static final String DIALOG_ID = "dialogID";
    static final String DIALOG_TURN_ID = "dialogTurnID";
    static final String DIRECTION_KEY = "direction";
    static final String DURATION_KEY = "duration";
    static final String ELEMENT_TYPE_KEY = "elementType";
    static final String END_POSITION_KEY = "endPosition";
    static final String ERROR_KEY = "Error";
    static final String ERROR_SOURCE_KEY = "errorSource";
    static final String EVENT_TIMESTAMP_KEY = "EventTimestamp";
    static final String EVENT_VALUE_KEY = "EventValue";
    static final String INDEX_KEY = "index";
    static final String INPUT_TYPE_KEY = "inputType";
    static final String INTENT_NAME = "intentName";
    static final String INTERACTION_TYPE_KEY = "interactionType";
    static final String ITEM_ID_KEY = "itemID";
    static final String ITEM_INDEX_KEY = "itemIndex";
    static final String LOCAL_EXECUTE_REASON = "localExecReason";
    static final String MEDIA_TYPE_KEY = "mediaType";
    static final String MESSAGE_TYPE_KEY = "messageType";
    static final String OPERATIONAL_EVENT_TYPE_KEY = "operationalEventType";
    static final String OWNER_IDENTIFIER_KEY = "ownerIdentifier";
    static final String PAGE_ID_KEY = "pageID";
    static final String REFMARKER_KEY = "refMarker";
    static final String REQUEST_ID_KEY = "requestId";
    static final String SECTION_ID_KEY = "sectionID";
    static final String SECTION_INDEX_KEY = "sectionIndex";
    static final String SIZE_KEY = "size";
    static final String SOURCE_CONTEXT_KEY = "sourceContext";
    static final String START_POSITION_KEY = "startPosition";
    static final String STATUS_CODE_KEY = "statusCode";
    static final String SUBCOMPONENT_KEY = "subComponent";
    static final String TARGET_TYPE_KEY = "targetType";
    static final String TIMER_VALUE_KEY = "TimerValue";
    static final String TOTAL_ELEMENTS_KEY = "totalElements";
    static final String TRANSPORT_KEY = "transport";
    static final String UTTERANCE_ID = "utteranceID";
    static final String VOICE_REQUEST_ID = "voiceReqID";

    private String getChannelFromString(@Nullable String str) {
        return TextUtils.isEmpty(str) ? "unknown" : str;
    }

    @Nullable
    private ClickInteractionDetails getClickDetailsFromMap(@NonNull Map<?, ?> map) {
        return new ClickInteractionDetails(safeGetStringFromMap(map, "elementType"), safeGetStringFromMap(map, "actionType"), safeGetIntegerFromMap(map, INDEX_KEY), safeGetIntegerFromMap(map, TOTAL_ELEMENTS_KEY));
    }

    @Nullable
    private CommsMetadata getCommsMetadataFromMap(@NonNull Map<?, ?> map) {
        String safeGetStringFromMap = safeGetStringFromMap(map, "interactionType");
        if (safeGetStringFromMap == null || (!safeGetStringFromMap.equalsIgnoreCase(InteractionType.COMMS_CALL) && !safeGetStringFromMap.equalsIgnoreCase(InteractionType.COMMS_MESSAGE))) {
            return null;
        }
        return new CommsMetadata().withCallType(safeGetStringFromMap(map, "callType")).withTargetType(safeGetStringFromMap(map, "targetType")).withMediaType(safeGetStringFromMap(map, "mediaType")).withMessageType(safeGetStringFromMap(map, "messageType")).withDirection(safeGetStringFromMap(map, "direction")).withRequestId(safeGetStringFromMap(map, "requestId")).withTransport(safeGetStringFromMap(map, "transport")).withSize(safeGetLongFromMap(map, "size")).withStatusCode(safeGetLongFromMap(map, "statusCode")).withDuration(safeGetLongFromMap(map, "duration"));
    }

    @Nullable
    private DeepLinkClickInteractionDetails getDeepLinkClickDetailsFromMap(@NonNull Map<?, ?> map) {
        String safeGetStringFromMap = safeGetStringFromMap(map, DESTINATION_APP_KEY);
        String safeGetStringFromMap2 = safeGetStringFromMap(map, DESTINATION_SCREEN_KEY);
        if (!TextUtils.isEmpty(safeGetStringFromMap) || !TextUtils.isEmpty(safeGetStringFromMap2)) {
            return new DeepLinkClickInteractionDetails(safeGetStringFromMap, safeGetStringFromMap2);
        }
        return null;
    }

    @Nullable
    private EntertainmentMetadata getEntertainmentMetadataFromMap(@NonNull Map<?, ?> map) {
        String safeGetStringFromMap = safeGetStringFromMap(map, "interactionType");
        if (safeGetStringFromMap != null) {
            if (!safeGetStringFromMap.equalsIgnoreCase("click") && !safeGetStringFromMap.equalsIgnoreCase("view") && !safeGetStringFromMap.equalsIgnoreCase(InteractionType.SLIDER)) {
                return null;
            }
            String safeGetStringFromMap2 = safeGetStringFromMap(map, PAGE_ID_KEY);
            String safeGetStringFromMap3 = safeGetStringFromMap(map, SECTION_ID_KEY);
            String safeGetStringFromMap4 = safeGetStringFromMap(map, ITEM_ID_KEY);
            if (TextUtils.isEmpty(safeGetStringFromMap2) && TextUtils.isEmpty(safeGetStringFromMap3) && TextUtils.isEmpty(safeGetStringFromMap4)) {
                return null;
            }
            return new EntertainmentMetadata().withPageID(safeGetStringFromMap2).withSectionID(safeGetStringFromMap3).withItemID(safeGetStringFromMap4).withSectionIndex(safeGetLongFromMap(map, SECTION_INDEX_KEY)).withItemIndex(safeGetLongFromMap(map, ITEM_INDEX_KEY));
        }
        return null;
    }

    @Nullable
    private Collection<EventMetadata> getEventMetadataFromMap(@NonNull Map<?, ?> map) {
        CommsMetadata commsMetadataFromMap = getCommsMetadataFromMap(map);
        EntertainmentMetadata entertainmentMetadataFromMap = getEntertainmentMetadataFromMap(map);
        ArrayList arrayList = new ArrayList();
        if (commsMetadataFromMap != null) {
            arrayList.add(commsMetadataFromMap);
        }
        if (entertainmentMetadataFromMap != null) {
            arrayList.add(entertainmentMetadataFromMap);
        }
        if (arrayList.size() != 0) {
            return Collections.unmodifiableList(arrayList);
        }
        return null;
    }

    @Nullable
    private InteractionDetails getInteractionDetailsFromMap(@NonNull Map<?, ?> map, @NonNull String str) {
        if (!str.equalsIgnoreCase("click") && !str.equalsIgnoreCase(InteractionType.LONG_PRESS)) {
            if (str.equalsIgnoreCase("view")) {
                return getViewDetailsFromMap(map);
            }
            if (str.equalsIgnoreCase(InteractionType.SLIDER)) {
                return getSliderDetailsFromMap(map);
            }
            if (str.equalsIgnoreCase(InteractionType.DEEP_LINK_CLICK)) {
                return getDeepLinkClickDetailsFromMap(map);
            }
            if (str.equalsIgnoreCase(InteractionType.PAGE_VIEW)) {
                return getPageViewInterationDetailsFromMap(map);
            }
            if (!str.equalsIgnoreCase(InteractionType.UTTERANCE)) {
                return null;
            }
            return getUtteranceInteractionDetailsFromMap(map);
        }
        return getClickDetailsFromMap(map);
    }

    private PageViewInteractionDetails getPageViewInterationDetailsFromMap(Map<?, ?> map) {
        return new DefaultPageViewInteractionDetails(safeGetLongFromMap(map, "duration"));
    }

    @Nullable
    private SliderInteractionDetails getSliderDetailsFromMap(@NonNull Map<?, ?> map) {
        Integer safeGetIntegerFromMap = safeGetIntegerFromMap(map, START_POSITION_KEY);
        Integer safeGetIntegerFromMap2 = safeGetIntegerFromMap(map, END_POSITION_KEY);
        if (safeGetIntegerFromMap == null && safeGetIntegerFromMap2 == null) {
            return null;
        }
        return new SliderInteractionDetails(safeGetIntegerFromMap, safeGetIntegerFromMap2);
    }

    private UtteranceInteractionDetails getUtteranceInteractionDetailsFromMap(Map<?, ?> map) {
        return new UtteranceInteractionDetails(safeGetStringFromMap(map, UTTERANCE_ID));
    }

    @Nullable
    private ViewInteractionDetails getViewDetailsFromMap(@NonNull Map<?, ?> map) {
        Integer safeGetIntegerFromMap = safeGetIntegerFromMap(map, INDEX_KEY);
        Integer safeGetIntegerFromMap2 = safeGetIntegerFromMap(map, TOTAL_ELEMENTS_KEY);
        Long safeGetLongFromMap = safeGetLongFromMap(map, "duration");
        if (safeGetIntegerFromMap == null && safeGetIntegerFromMap2 == null) {
            return null;
        }
        return new ViewInteractionDetails(safeGetIntegerFromMap, safeGetIntegerFromMap2, safeGetLongFromMap);
    }

    @Nullable
    private Double safeGetDoubleFromMap(@NonNull Map<?, ?> map, @NonNull String str) {
        if (map.get(str) != null) {
            return Doubles.tryParse(map.get(str).toString());
        }
        return null;
    }

    @Nullable
    private Integer safeGetIntegerFromMap(@NonNull Map<?, ?> map, @NonNull String str) {
        Double tryParse = map.get(str) != null ? Doubles.tryParse(map.get(str).toString()) : null;
        if (tryParse != null) {
            return Integer.valueOf(tryParse.intValue());
        }
        return null;
    }

    @Nullable
    private Long safeGetLongFromMap(@NonNull Map<?, ?> map, @NonNull String str) {
        Double tryParse = map.get(str) != null ? Doubles.tryParse(map.get(str).toString()) : null;
        if (tryParse != null) {
            return Long.valueOf(tryParse.longValue());
        }
        return null;
    }

    @Nullable
    private String safeGetStringFromMap(@NonNull Map<?, ?> map, @NonNull String str) {
        if (map.get(str) != null) {
            return map.get(str).toString();
        }
        return null;
    }

    private void updateDebugInfo(Map<String, Object> map, String str, DefaultMobilyticsOperationalEvent defaultMobilyticsOperationalEvent) {
        String safeGetStringFromMap = safeGetStringFromMap(map, str);
        if (!TextUtils.isEmpty(safeGetStringFromMap)) {
            HashMap hashMap = new HashMap();
            hashMap.put("EventValue", safeGetStringFromMap);
            defaultMobilyticsOperationalEvent.withDebugInfo(hashMap);
        }
    }

    @Override // com.amazon.alexa.mobilytics.event.MobilyticsEventFactory
    @Nullable
    public MobilyticsOperationalEvent getOperationalEvent(@Nullable String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        DefaultMobilyticsOperationalEvent defaultMobilyticsOperationalEvent;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && map != null && map.size() != 0) {
            String safeGetStringFromMap = safeGetStringFromMap(map, "subComponent");
            String safeGetStringFromMap2 = safeGetStringFromMap(map, "operationalEventType");
            if (!TextUtils.isEmpty(safeGetStringFromMap2)) {
                if (safeGetStringFromMap2.equalsIgnoreCase(OperationalEventType.COUNTER)) {
                    Double safeGetDoubleFromMap = safeGetDoubleFromMap(map, "CounterValue");
                    if (safeGetDoubleFromMap != null) {
                        defaultMobilyticsOperationalEvent = new DefaultMobilyticsMetricsCounter(str, str2, safeGetStringFromMap, safeGetDoubleFromMap.longValue());
                        updateDebugInfo(map, "EventValue", defaultMobilyticsOperationalEvent);
                    } else {
                        throw new MobilyticsException("Counter value missing");
                    }
                } else if (safeGetStringFromMap2.equalsIgnoreCase(OperationalEventType.TIMER)) {
                    Double safeGetDoubleFromMap2 = safeGetDoubleFromMap(map, "TimerValue");
                    if (safeGetDoubleFromMap2 != null) {
                        DefaultMobilyticsMetricsTimer defaultMobilyticsMetricsTimer = new DefaultMobilyticsMetricsTimer(str, str2, safeGetStringFromMap, (long) (safeGetDoubleFromMap2.doubleValue() * 1000.0d), false);
                        updateDebugInfo(map, "EventValue", defaultMobilyticsMetricsTimer);
                        defaultMobilyticsOperationalEvent = defaultMobilyticsMetricsTimer;
                    } else {
                        throw new MobilyticsException("Timer value missing");
                    }
                } else if (safeGetStringFromMap2.equalsIgnoreCase("error")) {
                    defaultMobilyticsOperationalEvent = new DefaultMobilyticsErrorEvent(str, LogLevel.CRITICAL, str, str2, safeGetStringFromMap).withShortMessage(safeGetStringFromMap(map, "errorSource"));
                    updateDebugInfo(map, "Error", defaultMobilyticsOperationalEvent);
                } else if (safeGetStringFromMap2.equalsIgnoreCase("data")) {
                    defaultMobilyticsOperationalEvent = new DefaultMobilyticsOperationalEvent(str, "data", str2, safeGetStringFromMap);
                    updateDebugInfo(map, "Data", defaultMobilyticsOperationalEvent);
                } else if (safeGetStringFromMap2.equalsIgnoreCase(OperationalEventType.DIAGNOSTIC)) {
                    defaultMobilyticsOperationalEvent = new DefaultMobilyticsOperationalEvent(str, OperationalEventType.DIAGNOSTIC, str2, safeGetStringFromMap);
                    updateDebugInfo(map, "EventValue", defaultMobilyticsOperationalEvent);
                } else {
                    throw new MobilyticsException(String.format("Invalid operational event type: %s", safeGetStringFromMap2));
                }
                Double safeGetDoubleFromMap3 = safeGetDoubleFromMap(map, "EventTimestamp");
                if (safeGetDoubleFromMap3 != null) {
                    defaultMobilyticsOperationalEvent.setEventTimestamp((long) (safeGetDoubleFromMap3.doubleValue() * 1000.0d));
                }
                defaultMobilyticsOperationalEvent.withChannelName(getChannelFromString(safeGetStringFromMap(map, "channelName"))).withSourceContext(safeGetStringFromMap(map, "sourceContext")).withInputType(safeGetStringFromMap(map, "inputType")).withContentId(safeGetStringFromMap(map, "contentId")).withContentType(safeGetStringFromMap(map, "contentType")).withContentVersion(safeGetStringFromMap(map, "contentVersion")).withContentProvider(safeGetStringFromMap(map, "contentProvider")).withOwnerIdentifier(safeGetStringFromMap(map, "ownerIdentifier"));
                return defaultMobilyticsOperationalEvent;
            }
            throw new MobilyticsException("Type missing in operational event");
        }
        throw new MobilyticsException("Bad arguments");
    }

    @Override // com.amazon.alexa.mobilytics.event.MobilyticsEventFactory
    @Nullable
    public MobilyticsUserInteractionEvent getUserInteractionEvent(@Nullable String str, @Nullable String str2, @Nullable Map<String, Object> map) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && map != null) {
            String safeGetStringFromMap = safeGetStringFromMap(map, "subComponent");
            String safeGetStringFromMap2 = safeGetStringFromMap(map, "interactionType");
            String safeGetStringFromMap3 = safeGetStringFromMap(map, "channelName");
            if (!TextUtils.isEmpty(safeGetStringFromMap) && !TextUtils.isEmpty(safeGetStringFromMap2)) {
                return new DefaultMobilyticsUserInteractionEvent(str, safeGetStringFromMap2, str2, safeGetStringFromMap).withChannelName(getChannelFromString(safeGetStringFromMap3)).withRefMarker(safeGetStringFromMap(map, REFMARKER_KEY)).withSourceContext(safeGetStringFromMap(map, "sourceContext")).withInputType(safeGetStringFromMap(map, "inputType")).withContentId(safeGetStringFromMap(map, "contentId")).withContentType(safeGetStringFromMap(map, "contentType")).withContentVersion(safeGetStringFromMap(map, "contentVersion")).withContentProvider(safeGetStringFromMap(map, "contentProvider")).mo1485withInteractionDetails(getInteractionDetailsFromMap(map, safeGetStringFromMap2)).withEventMetadata(getEventMetadataFromMap(map)).withOwnerIdentifier(safeGetStringFromMap(map, "ownerIdentifier"));
            }
        }
        return null;
    }
}
