package com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails;

import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
/* loaded from: classes9.dex */
public class UtteranceInteractionDetails implements InteractionDetails {
    private final String interactionType = InteractionType.UTTERANCE;
    private String utteranceID;

    public UtteranceInteractionDetails(@Nullable String str) {
        this.utteranceID = str;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails
    public String getInteractionType() {
        return InteractionType.UTTERANCE;
    }

    public String getUtteranceId() {
        return this.utteranceID;
    }

    public void setUtteranceId(@Nullable String str) {
        this.utteranceID = str;
    }
}
