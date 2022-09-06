package com.amazon.alexa.voice.tta.statemachine;

import android.util.Log;
import androidx.core.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StateMachineImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0015¢\u0006\u0002\b\u0016H\u0016J)\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0015¢\u0006\u0002\b\u0016H\u0016J\u0014\u0010\u0018\u001a\u00020\u00122\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0012H\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0016R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@RX\u0096.¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/StateMachineImpl;", "Lcom/amazon/alexa/voice/tta/statemachine/StateMachine;", "name", "", "(Ljava/lang/String;)V", "<set-?>", "Lcom/amazon/alexa/voice/tta/statemachine/State;", "currentState", "getCurrentState", "()Lcom/amazon/alexa/voice/tta/statemachine/State;", "initialState", "isRunning", "", "getName", "()Ljava/lang/String;", "states", "", "addInitialState", "", "state", "init", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "addState", "handle", "event", "Lcom/amazon/alexa/voice/tta/statemachine/Event;", "start", "stop", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class StateMachineImpl implements StateMachine {
    @NotNull
    private State currentState;
    private State initialState;
    private boolean isRunning;
    @NotNull
    private final String name;
    private final Set<State> states;

    public StateMachineImpl(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.states = new LinkedHashSet();
    }

    public static final /* synthetic */ State access$getInitialState$p(StateMachineImpl stateMachineImpl) {
        State state = stateMachineImpl.initialState;
        if (state == null) {
            Intrinsics.throwUninitializedPropertyAccessException("initialState");
        }
        return state;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.StateMachine
    public void addInitialState(@NotNull State state, @NotNull Function1<? super State, Unit> init) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(init, "init");
        init.mo12165invoke(state);
        this.initialState = state;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.StateMachine
    public void addState(@NotNull State state, @NotNull Function1<? super State, Unit> init) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(init, "init");
        init.mo12165invoke(state);
        this.states.add(state);
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.StateMachine
    @NotNull
    public State getCurrentState() {
        State state = this.currentState;
        if (state == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentState");
        }
        return state;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.StateMachine
    public void handle(@NotNull Event<?> event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (!getCurrentState().isEventSupported(event)) {
            String str = this.name;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("event is not supported for ");
            outline107.append(getCurrentState().getName());
            outline107.append(" state");
            Log.e(str, outline107.toString());
            return;
        }
        this.currentState = getCurrentState().handle(event);
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.StateMachine
    public void start() {
        Preconditions.checkState(!this.isRunning, this.name + " state machine has already started");
        boolean z = this.initialState != null;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("There is no initial state to start from in ");
        outline107.append(this.name);
        outline107.append(" state machine");
        Preconditions.checkState(z, outline107.toString());
        String str = "Starting " + this.name + " state machine";
        State state = this.initialState;
        if (state == null) {
            Intrinsics.throwUninitializedPropertyAccessException("initialState");
        }
        this.currentState = state;
        this.isRunning = true;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.StateMachine
    public void stop() {
        if (this.isRunning) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Stopping ");
            outline107.append(this.name);
            outline107.append(" state machine");
            outline107.toString();
            this.isRunning = false;
        }
    }
}
