package com.amazon.alexa.presence.service;
/* loaded from: classes9.dex */
public interface PresenceFeatureSuggestionInterface {
    String getFeatureName();

    void sendPresenceFeatureSuggestNotification();

    boolean shouldNotify();
}
