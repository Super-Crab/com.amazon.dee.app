package com.amazon.alexa.elements.api;

import com.amazon.alexa.eventing.Event;
/* loaded from: classes7.dex */
public interface BridgeStatusService {
    InitializationPhase getCurrentPhase();

    boolean getIsReady();

    Event<Boolean> onReadyChange();

    void registerListener(Runnable runnable);

    void setIsReady(boolean z);

    void setPhaseReady(InitializationPhase initializationPhase);
}
