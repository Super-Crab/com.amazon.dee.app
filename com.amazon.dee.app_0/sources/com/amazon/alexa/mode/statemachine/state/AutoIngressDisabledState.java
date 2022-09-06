package com.amazon.alexa.mode.statemachine.state;

import com.amazon.alexa.mode.statemachine.Event;
import com.amazon.alexa.mode.statemachine.StateContext;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.statemachine.command.Command;
import com.amazon.alexa.mode.statemachine.command.ShowDriverSafetyWarningCommand;
import com.amazon.alexa.mode.statemachine.command.StateChangeCommand;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class AutoIngressDisabledState extends BaseState {
    private final AutoIngressDisabledReason mIngressDisabledReason;

    /* renamed from: com.amazon.alexa.mode.statemachine.state.AutoIngressDisabledState$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mode$statemachine$Event = new int[Event.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoDeviceOOBEEnded.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoDeviceOOBEDriveModeAllDoneYes.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.LoginOOBEEnded.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoDeviceOOBEDriveModeAllDoneNo.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AppBackgrounded.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public enum AutoIngressDisabledReason {
        LoginOOBEFlow,
        AutoDeviceSetup
    }

    public AutoIngressDisabledState(StateDependencies stateDependencies, AutoIngressDisabledReason autoIngressDisabledReason) {
        super(stateDependencies);
        this.mIngressDisabledReason = autoIngressDisabledReason;
    }

    public AutoIngressDisabledReason getIngressDisabledReason() {
        return this.mIngressDisabledReason;
    }

    @Override // com.amazon.alexa.mode.statemachine.state.BaseState
    public List<Command> onEvent(Event event, StateContext stateContext) {
        List<Command> onEvent = super.onEvent(event, stateContext);
        ArrayList arrayList = new ArrayList();
        int ordinal = event.ordinal();
        if (ordinal == 5) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new BackgroundState(getStateDependencies(), this)));
        } else if (ordinal == 13) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new MainModeState(getStateDependencies())));
        } else if (ordinal == 15) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new MainModeState(getStateDependencies())));
            if (stateContext.isAccessoryConnected() || stateContext.isAutoBluetoothConnected()) {
                arrayList.add(getIngressCommandBasedOnFTUEState(stateContext));
            }
        } else if (ordinal == 17) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new MainModeState(getStateDependencies())));
            arrayList.add(getIngressCommandBasedOnFTUEState(stateContext));
        } else if (ordinal == 18) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new MainModeState(getStateDependencies())));
            arrayList.add(new ShowDriverSafetyWarningCommand(getStateDependencies(), true, false));
        }
        arrayList.addAll(onEvent);
        return arrayList;
    }
}
