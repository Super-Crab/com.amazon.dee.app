package com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails;

import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
/* loaded from: classes9.dex */
public class DeepLinkClickInteractionDetails implements InteractionDetails {
    private String destinationApp;
    private String destinationScreen;
    private final String interactionType = InteractionType.DEEP_LINK_CLICK;

    public DeepLinkClickInteractionDetails(@Nullable String str, @Nullable String str2) {
        this.destinationApp = str;
        this.destinationScreen = str2;
    }

    @Nullable
    public String getDestinationApp() {
        return this.destinationApp;
    }

    @Nullable
    public String getDestinationScreen() {
        return this.destinationScreen;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails
    public String getInteractionType() {
        return InteractionType.DEEP_LINK_CLICK;
    }

    public void setDestinationApp(@Nullable String str) {
        this.destinationApp = str;
    }

    public void setDestinationScreen(@Nullable String str) {
        this.destinationScreen = str;
    }

    public DeepLinkClickInteractionDetails(@Nullable String str) {
        this.destinationApp = str;
    }
}
