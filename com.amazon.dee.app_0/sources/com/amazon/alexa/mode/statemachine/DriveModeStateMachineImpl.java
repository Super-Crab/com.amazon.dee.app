package com.amazon.alexa.mode.statemachine;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.mode.statemachine.StateContext;
import com.amazon.alexa.mode.statemachine.StateTransitionHelper;
import com.amazon.alexa.mode.statemachine.command.Command;
import com.amazon.alexa.mode.statemachine.state.BackgroundState;
import com.amazon.alexa.mode.statemachine.state.BaseState;
import com.amazon.alexa.mode.statemachine.state.DriveModeState;
import com.amazon.alexa.mode.statemachine.state.MainModeState;
import com.amazon.alexa.mode.util.LogTag;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public class DriveModeStateMachineImpl implements DriveModeStateMachine, StateTransitionHelper.StateTransitionCallback {
    private static final String TAG = LogTag.forClass(DriveModeStateMachineImpl.class);
    private BaseState mCurrentState;
    @VisibleForTesting
    StateContext mStateContext;
    private StateDependencies mStateDependencies;
    @VisibleForTesting
    StateMachineEventProcessor mStateMachineEventProcessor;

    @Override // com.amazon.alexa.mode.statemachine.DriveModeStateMachine
    public String getCurrentMode() {
        if (getCurrentState() == null) {
            return ModeName.MAIN_MODE;
        }
        BaseState baseState = this.mCurrentState;
        return baseState instanceof BackgroundState ? ((BackgroundState) baseState).getPrevState() instanceof DriveModeState ? ModeName.DRIVE_MODE : ModeName.MAIN_MODE : baseState instanceof DriveModeState ? ModeName.DRIVE_MODE : ModeName.MAIN_MODE;
    }

    @Override // com.amazon.alexa.mode.statemachine.DriveModeStateMachine
    public BaseState getCurrentState() {
        return this.mCurrentState;
    }

    @Override // com.amazon.alexa.mode.statemachine.DriveModeStateMachine
    public void init(StateDependencies stateDependencies, boolean z) {
        this.mStateDependencies = stateDependencies;
        MainModeState mainModeState = new MainModeState(stateDependencies);
        if (z) {
            this.mCurrentState = new BackgroundState(stateDependencies, mainModeState);
        } else {
            this.mCurrentState = mainModeState;
        }
        this.mStateContext = new StateContext.Builder().setAccessoryConnected(false).setLaunchAppFromDeepLink(false).setNotificationSent(false).setPrimaryNotificationDisplayCount(0).setScreenOn(false).setSuppressAutoIngress(false).setManualIngressMode(false).build();
        this.mStateMachineEventProcessor = new StateMachineEventProcessor();
    }

    @Override // com.amazon.alexa.mode.statemachine.DriveModeStateMachine
    public boolean isCurrentDefaultMode() {
        StateContext stateContext = this.mStateContext;
        return stateContext != null && stateContext.isManualIngressMode();
    }

    @Override // com.amazon.alexa.mode.statemachine.DriveModeStateMachine
    public synchronized void onEvent(Event event) {
        String str = TAG;
        Log.i(str, "onEvent | currentState: " + this.mCurrentState + " | event: " + event);
        this.mStateContext = this.mStateMachineEventProcessor.processStateContext(this.mStateContext, event);
        try {
            for (Command command : this.mCurrentState.onEvent(event, this.mStateContext)) {
                String str2 = TAG;
                Log.i(str2, "onEvent | commmand: " + command);
                this.mStateContext = this.mStateMachineEventProcessor.processStateContext(this.mStateContext, command);
                command.execute();
            }
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "Error executing event: " + e);
        }
    }

    @Override // com.amazon.alexa.mode.statemachine.StateTransitionHelper.StateTransitionCallback
    public void onStateChange(BaseState baseState) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onStateChange | oldState: ");
        outline107.append(this.mCurrentState);
        outline107.append(" | newState: ");
        outline107.append(baseState);
        Log.i(str, outline107.toString());
        this.mCurrentState = baseState;
    }

    @Override // com.amazon.alexa.mode.statemachine.DriveModeStateMachine
    public void tearDown() {
        this.mCurrentState = null;
        this.mStateContext = null;
        this.mStateMachineEventProcessor = null;
        this.mStateDependencies = null;
    }
}
