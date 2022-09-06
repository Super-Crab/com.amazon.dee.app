package com.amazon.alexa.mobilytics.event.userinteraction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails;
/* loaded from: classes9.dex */
public interface MobilyticsUserInteractionEvent extends MobilyticsEvent {
    @Nullable
    InteractionDetails getInteractionDetails();

    String getInteractionType();

    @Nullable
    String getRefMarker();

    void setInteractionDetails(@NonNull InteractionDetails interactionDetails);

    void setRefMarker(@NonNull String str);
}
