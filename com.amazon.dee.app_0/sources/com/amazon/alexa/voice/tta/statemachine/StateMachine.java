package com.amazon.alexa.voice.tta.statemachine;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
/* compiled from: StateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J)\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0002\b\u000bH&J)\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0002\b\u000bH&J\u0014\u0010\r\u001a\u00020\u00072\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH&J\b\u0010\u0010\u001a\u00020\u0007H&J\b\u0010\u0011\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/StateMachine;", "", "currentState", "Lcom/amazon/alexa/voice/tta/statemachine/State;", "getCurrentState", "()Lcom/amazon/alexa/voice/tta/statemachine/State;", "addInitialState", "", "state", "init", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "addState", "handle", "event", "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "start", "stop", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public interface StateMachine {
    void addInitialState(@NotNull State state, @NotNull Function1<? super State, Unit> function1);

    void addState(@NotNull State state, @NotNull Function1<? super State, Unit> function1);

    @NotNull
    State getCurrentState();

    void handle(@NotNull Event<?> event);

    void start();

    void stop();
}
