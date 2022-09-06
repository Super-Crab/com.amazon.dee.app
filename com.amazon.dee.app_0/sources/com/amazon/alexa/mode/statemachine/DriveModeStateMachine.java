package com.amazon.alexa.mode.statemachine;

import com.amazon.alexa.mode.statemachine.state.BaseState;
/* loaded from: classes9.dex */
public interface DriveModeStateMachine {
    String getCurrentMode();

    BaseState getCurrentState();

    void init(StateDependencies stateDependencies, boolean z);

    boolean isCurrentDefaultMode();

    void onEvent(Event event);

    void tearDown();
}
