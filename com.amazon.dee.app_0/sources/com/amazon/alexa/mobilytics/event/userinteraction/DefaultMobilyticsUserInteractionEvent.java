package com.amazon.alexa.mobilytics.event.userinteraction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.AppStartInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ClickInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.DeepLinkClickInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.PageViewInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.SliderInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.UtteranceInteractionDetails;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ViewInteractionDetails;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
/* loaded from: classes9.dex */
public class DefaultMobilyticsUserInteractionEvent extends DefaultMobilyticsEvent<DefaultMobilyticsUserInteractionEvent> implements MobilyticsUserInteractionEvent {
    private InteractionDetails interactionDetails;
    private String interactionType;
    private String refMarker;

    public DefaultMobilyticsUserInteractionEvent(@NonNull String str, String str2, @NonNull String str3, @NonNull String str4) {
        super(str, str3, str4);
        this.interactionType = str2;
    }

    private EventDetailsProto.InteractionDetails.Builder doSerialize(AppStartInteractionDetails appStartInteractionDetails, EventDetailsProto.InteractionDetails.Builder builder) {
        String referralType = appStartInteractionDetails.getReferralType();
        String referralType2 = appStartInteractionDetails.getReferralType();
        String referralDetails = appStartInteractionDetails.getReferralDetails();
        if (referralType != null) {
            builder.setReferralType(referralType);
        }
        if (referralType2 != null) {
            builder.setReferralSource(referralType2);
        }
        if (referralDetails != null) {
            builder.setReferralDetails(referralDetails);
        }
        return builder;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @NonNull
    private EventDetailsProto.InteractionDetails.Builder serializeInteractionDetails(InteractionDetails interactionDetails) {
        char c;
        EventDetailsProto.InteractionDetails.Builder newBuilder = EventDetailsProto.InteractionDetails.newBuilder();
        String interactionType = interactionDetails.getInteractionType();
        newBuilder.setInteractionType(interactionType);
        switch (interactionType.hashCode()) {
            case -2140169871:
                if (interactionType.equals(InteractionType.UTTERANCE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -899647263:
                if (interactionType.equals(InteractionType.SLIDER)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -407634398:
                if (interactionType.equals(InteractionType.DEEP_LINK_CLICK)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3619493:
                if (interactionType.equals("view")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 94750088:
                if (interactionType.equals("click")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 859517396:
                if (interactionType.equals(InteractionType.PAGE_VIEW)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1156744897:
                if (interactionType.equals(InteractionType.APP_START)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return doSerialize((AppStartInteractionDetails) interactionDetails, newBuilder);
            case 1:
                return doSerialize((ClickInteractionDetails) interactionDetails, newBuilder);
            case 2:
                return doSerialize((DeepLinkClickInteractionDetails) interactionDetails, newBuilder);
            case 3:
                return doSerialize((PageViewInteractionDetails) interactionDetails, newBuilder);
            case 4:
                return doSerialize((SliderInteractionDetails) interactionDetails, newBuilder);
            case 5:
                return doSerialize((UtteranceInteractionDetails) interactionDetails, newBuilder);
            case 6:
                return doSerialize((ViewInteractionDetails) interactionDetails, newBuilder);
            default:
                return newBuilder;
        }
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent, com.amazon.alexa.mobilytics.event.MobilyticsEvent
    public String getEventType() {
        return EventType.USER_INTERACTION;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent
    public InteractionDetails getInteractionDetails() {
        return this.interactionDetails;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent
    public String getInteractionType() {
        return this.interactionType;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent
    public String getRefMarker() {
        return this.refMarker;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    public DefaultMobilyticsUserInteractionEvent getThis() {
        return this;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    public EventDetailsProto.Builder serialize() {
        EventDetailsProto.Builder serialize = super.serialize();
        String str = this.interactionType;
        if (str != null) {
            serialize.setInteractionType(str);
        }
        String str2 = this.refMarker;
        if (str2 != null) {
            serialize.setRefMarker(str2);
        }
        InteractionDetails interactionDetails = this.interactionDetails;
        if (interactionDetails != null) {
            serialize.setInteractionDetails(serializeInteractionDetails(interactionDetails));
        }
        return serialize;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent
    public void setInteractionDetails(@NonNull InteractionDetails interactionDetails) {
        this.interactionDetails = interactionDetails;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent
    public void setRefMarker(@NonNull String str) {
        this.refMarker = str;
    }

    /* renamed from: withInteractionDetails */
    public DefaultMobilyticsUserInteractionEvent mo1485withInteractionDetails(@Nullable InteractionDetails interactionDetails) {
        this.interactionDetails = interactionDetails;
        return this;
    }

    public DefaultMobilyticsUserInteractionEvent withRefMarker(@Nullable String str) {
        this.refMarker = str;
        return this;
    }

    public DefaultMobilyticsUserInteractionEvent(@NonNull String str, String str2, @NonNull String str3, @NonNull String str4, String str5) {
        super(str, str3, str4, str5);
        this.interactionType = str2;
    }

    private EventDetailsProto.InteractionDetails.Builder doSerialize(ClickInteractionDetails clickInteractionDetails, EventDetailsProto.InteractionDetails.Builder builder) {
        String elementType = clickInteractionDetails.getElementType();
        String actionType = clickInteractionDetails.getActionType();
        Integer index = clickInteractionDetails.getIndex();
        Integer totalNumberOfItems = clickInteractionDetails.getTotalNumberOfItems();
        if (elementType != null) {
            builder.setElementType(elementType);
        }
        if (actionType != null) {
            builder.setActionType(actionType);
        }
        if (index != null) {
            builder.setIndex(index.intValue());
        }
        if (totalNumberOfItems != null) {
            builder.setTotalNumberOfItems(totalNumberOfItems.intValue());
        }
        return builder;
    }

    private EventDetailsProto.InteractionDetails.Builder doSerialize(DeepLinkClickInteractionDetails deepLinkClickInteractionDetails, EventDetailsProto.InteractionDetails.Builder builder) {
        String destinationApp = deepLinkClickInteractionDetails.getDestinationApp();
        String destinationScreen = deepLinkClickInteractionDetails.getDestinationScreen();
        if (destinationApp != null) {
            builder.setDestinationApp(destinationApp);
        }
        if (destinationScreen != null) {
            builder.setDestinationScreen(destinationScreen);
        }
        return builder;
    }

    private EventDetailsProto.InteractionDetails.Builder doSerialize(PageViewInteractionDetails pageViewInteractionDetails, EventDetailsProto.InteractionDetails.Builder builder) {
        Long duration = pageViewInteractionDetails.duration();
        if (duration != null) {
            builder.setDuration(duration.longValue());
        }
        return builder;
    }

    private EventDetailsProto.InteractionDetails.Builder doSerialize(SliderInteractionDetails sliderInteractionDetails, EventDetailsProto.InteractionDetails.Builder builder) {
        Integer startPosition = sliderInteractionDetails.getStartPosition();
        Integer endPosition = sliderInteractionDetails.getEndPosition();
        if (startPosition != null) {
            builder.setStartPosition(startPosition.intValue());
        }
        if (endPosition != null) {
            builder.setEndPosition(endPosition.intValue());
        }
        return builder;
    }

    private EventDetailsProto.InteractionDetails.Builder doSerialize(UtteranceInteractionDetails utteranceInteractionDetails, EventDetailsProto.InteractionDetails.Builder builder) {
        String utteranceId = utteranceInteractionDetails.getUtteranceId();
        if (utteranceId != null) {
            builder.setUtteranceID(utteranceId);
        }
        return builder;
    }

    private EventDetailsProto.InteractionDetails.Builder doSerialize(ViewInteractionDetails viewInteractionDetails, EventDetailsProto.InteractionDetails.Builder builder) {
        Integer index = viewInteractionDetails.getIndex();
        Integer totalNumberOfItems = viewInteractionDetails.getTotalNumberOfItems();
        Long duration = viewInteractionDetails.getDuration();
        if (index != null) {
            builder.setIndex(index.intValue());
        }
        if (totalNumberOfItems != null) {
            builder.setTotalNumberOfItems(totalNumberOfItems.intValue());
        }
        if (duration != null) {
            builder.setDuration(duration.longValue());
        }
        return builder;
    }
}
