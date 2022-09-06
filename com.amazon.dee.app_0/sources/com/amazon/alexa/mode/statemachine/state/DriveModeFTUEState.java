package com.amazon.alexa.mode.statemachine.state;

import com.amazon.alexa.mode.statemachine.Event;
import com.amazon.alexa.mode.statemachine.StateContext;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.statemachine.command.Command;
import com.amazon.alexa.mode.statemachine.command.RecordFTUEMetricsCommand;
import com.amazon.alexa.mode.statemachine.command.ShowDriverSafetyWarningCommand;
import com.amazon.alexa.mode.statemachine.command.StateChangeCommand;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class DriveModeFTUEState extends BaseState {

    /* renamed from: com.amazon.alexa.mode.statemachine.state.DriveModeFTUEState$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mode$statemachine$Event = new int[Event.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.FTUECancelledEvent.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoDeviceOOBEDriveModeAllDoneNo.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoDeviceOOBEDriveModeAllDoneYes.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.FTUECompletedDriveModeEvent.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.FTUECompletedStandaloneModeEvent.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public DriveModeFTUEState(StateDependencies stateDependencies) {
        super(stateDependencies);
    }

    @Override // com.amazon.alexa.mode.statemachine.state.BaseState
    public List<Command> onEvent(Event event, StateContext stateContext) {
        List<Command> onEvent = super.onEvent(event, stateContext);
        ArrayList arrayList = new ArrayList();
        int ordinal = event.ordinal();
        if (ordinal == 17) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new MainModeState(getStateDependencies())));
            arrayList.add(getIngressCommandOrDriverSafetyWarningCommand());
        } else if (ordinal != 18) {
            switch (ordinal) {
                case 9:
                    arrayList.add(new RecordFTUEMetricsCommand(getStateDependencies(), 1, getStateDependencies().getDriveModeFTUEHelper().getFTUEType(stateContext)));
                    break;
                case 10:
                    arrayList.add(new RecordFTUEMetricsCommand(getStateDependencies(), 3, getStateDependencies().getDriveModeFTUEHelper().getFTUEType(stateContext)));
                    break;
                case 11:
                    arrayList.add(new RecordFTUEMetricsCommand(getStateDependencies(), 2, getStateDependencies().getDriveModeFTUEHelper().getFTUEType(stateContext)));
                    arrayList.add(new StateChangeCommand(getStateDependencies(), new MainModeState(getStateDependencies())));
                    arrayList.add(new ShowDriverSafetyWarningCommand(getStateDependencies(), true, false));
                    break;
            }
        } else {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new MainModeState(getStateDependencies())));
            arrayList.add(new ShowDriverSafetyWarningCommand(getStateDependencies(), true, false));
        }
        arrayList.addAll(onEvent);
        return arrayList;
    }
}
