package com.amazon.alexa.mode.statemachine.state;

import com.amazon.alexa.mode.statemachine.Event;
import com.amazon.alexa.mode.statemachine.StateContext;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.statemachine.command.Command;
import com.amazon.alexa.mode.statemachine.command.RecordAutoEgressMetricCommand;
import com.amazon.alexa.mode.statemachine.command.RecordBackgroundForegroundMetricsCommand;
import com.amazon.alexa.mode.statemachine.command.RouteToHomeCommand;
import com.amazon.alexa.mode.statemachine.command.StateChangeCommand;
import com.amazon.alexa.mode.statemachine.command.WirelessChargingCommandV2;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class DriveModeState extends BaseState {

    /* renamed from: com.amazon.alexa.mode.statemachine.state.DriveModeState$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mode$statemachine$Event = new int[Event.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AccessoryDisconnected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoBluetoothDisconnected.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.DriveModeEgressRequested.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AppBackgrounded.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.WirelessChargingError.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.WirelessChargingNoError.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public DriveModeState(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.state.BaseState
    public List<Command> onEvent(Event event, StateContext stateContext) {
        List<Command> onEvent = super.onEvent(event, stateContext);
        ArrayList arrayList = new ArrayList();
        int ordinal = event.ordinal();
        if (ordinal == 1) {
            arrayList.add(new RouteToHomeCommand(getStateDependencies()));
        } else if (ordinal != 3) {
            if (ordinal != 5) {
                switch (ordinal) {
                    case 23:
                        if (!stateContext.isAccessoryConnected() && !stateContext.isManualIngressMode()) {
                            arrayList.add(new RouteToHomeCommand(getStateDependencies()));
                            arrayList.add(new RecordAutoEgressMetricCommand(getStateDependencies(), "AutoBluetooth"));
                            break;
                        }
                        break;
                    case 24:
                        arrayList.add(new WirelessChargingCommandV2(getStateDependencies(), true));
                        break;
                    case 25:
                        arrayList.add(new WirelessChargingCommandV2(getStateDependencies(), false));
                        break;
                }
            } else {
                arrayList.add(new StateChangeCommand(getStateDependencies(), new BackgroundState(getStateDependencies(), this)));
                arrayList.add(new RecordBackgroundForegroundMetricsCommand(getStateDependencies(), true));
            }
        } else if (!stateContext.isAutoBluetoothConnected() && !stateContext.isManualIngressMode()) {
            arrayList.add(new RouteToHomeCommand(getStateDependencies()));
            arrayList.add(new RecordAutoEgressMetricCommand(getStateDependencies(), "Accessory"));
        }
        arrayList.addAll(onEvent);
        return arrayList;
    }
}
