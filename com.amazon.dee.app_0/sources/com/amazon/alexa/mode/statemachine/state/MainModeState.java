package com.amazon.alexa.mode.statemachine.state;

import com.amazon.alexa.mode.statemachine.Event;
import com.amazon.alexa.mode.statemachine.StateContext;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.statemachine.command.Command;
import com.amazon.alexa.mode.statemachine.command.RecordAutoIngressMetricCommand;
import com.amazon.alexa.mode.statemachine.command.RecordDefaultIngressMetricCommand;
import com.amazon.alexa.mode.statemachine.command.RecordFTUEMetricsCommand;
import com.amazon.alexa.mode.statemachine.command.RouteToHomeCommand;
import com.amazon.alexa.mode.statemachine.command.StateChangeCommand;
import com.amazon.alexa.mode.statemachine.state.AutoIngressDisabledState;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class MainModeState extends BaseState {

    /* renamed from: com.amazon.alexa.mode.statemachine.state.MainModeState$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mode$statemachine$Event = new int[Event.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.DriveModeIngressRequested.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.FTUEStartedEvent.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AppBackgrounded.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.LoginOOBEStarted.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoDeviceOOBEStarted.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AccessoryConnected.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoBluetoothConnected.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AccessoryDisconnected.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ManualIngressStarted.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public MainModeState(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.state.BaseState
    public List<Command> onEvent(Event event, StateContext stateContext) {
        List<Command> onEvent = super.onEvent(event, stateContext);
        ArrayList arrayList = new ArrayList();
        int ordinal = event.ordinal();
        if (ordinal != 0) {
            if (ordinal == 5) {
                arrayList.add(new StateChangeCommand(getStateDependencies(), new BackgroundState(getStateDependencies(), this)));
            } else if (ordinal == 8) {
                arrayList.add(new RecordFTUEMetricsCommand(getStateDependencies(), 0, getStateDependencies().getDriveModeFTUEHelper().getFTUEType(stateContext)));
                arrayList.add(new StateChangeCommand(getStateDependencies(), new DriveModeFTUEState(getStateDependencies())));
            } else if (ordinal == 12) {
                arrayList.add(new StateChangeCommand(getStateDependencies(), new AutoIngressDisabledState(getStateDependencies(), AutoIngressDisabledState.AutoIngressDisabledReason.AutoDeviceSetup)));
            } else if (ordinal == 14) {
                arrayList.add(new StateChangeCommand(getStateDependencies(), new AutoIngressDisabledState(getStateDependencies(), AutoIngressDisabledState.AutoIngressDisabledReason.LoginOOBEFlow)));
            } else if (ordinal != 22) {
                if (ordinal == 26) {
                    arrayList.add(new RecordDefaultIngressMetricCommand(getStateDependencies()));
                    arrayList.add(getIngressCommandBasedOnFTUEState(stateContext));
                } else if (ordinal != 2) {
                    if (ordinal == 3 && getStateDependencies().getRoutingService().getCurrentRoute() != null && getStateDependencies().getRoutingService().getCurrentRoute().getRoute().getName().equals("drive-mode/driver-interaction-warning")) {
                        arrayList.add(new RouteToHomeCommand(getStateDependencies()));
                    }
                } else if (getStateDependencies().getPrefsDialogHelper().isDriveModeAutoLaunchEnabled()) {
                    arrayList.add(new RecordAutoIngressMetricCommand(getStateDependencies(), "Accessory"));
                    arrayList.add(getIngressCommandBasedOnFTUEState(stateContext));
                }
            } else if (getStateDependencies().getPrefsDialogHelper().isDriveModeAutoLaunchEnabled()) {
                arrayList.add(new RecordAutoIngressMetricCommand(getStateDependencies(), "AutoBluetooth"));
                arrayList.add(getIngressCommandBasedOnFTUEState(stateContext));
            }
        } else if (stateContext.isAccessoryConnected() || stateContext.isAutoBluetoothConnected()) {
            arrayList.add(getIngressCommandBasedOnFTUEState(stateContext));
        }
        arrayList.addAll(onEvent);
        return arrayList;
    }
}
