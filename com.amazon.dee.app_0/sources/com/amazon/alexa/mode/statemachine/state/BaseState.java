package com.amazon.alexa.mode.statemachine.state;

import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.mode.statemachine.Event;
import com.amazon.alexa.mode.statemachine.StateContext;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.statemachine.command.CancelNotificationCommand;
import com.amazon.alexa.mode.statemachine.command.Command;
import com.amazon.alexa.mode.statemachine.command.IngressDefaultModeFTUECommand;
import com.amazon.alexa.mode.statemachine.command.IngressDriveModeCommand;
import com.amazon.alexa.mode.statemachine.command.IngressDriveModeFTUECommand;
import com.amazon.alexa.mode.statemachine.command.IngressStandaloneModeFTUECommand;
import com.amazon.alexa.mode.statemachine.command.PublishDriveModeIngressCard;
import com.amazon.alexa.mode.statemachine.command.RecordAccessoryConnectedDisconnectedMetric;
import com.amazon.alexa.mode.statemachine.command.RemoveDriveModeIngressCard;
import com.amazon.alexa.mode.statemachine.command.SetFTUECompletionFlagCommand;
import com.amazon.alexa.mode.statemachine.command.SetStandaloneModeFTUECompletionFlagCommand;
import com.amazon.alexa.mode.statemachine.command.ShowDriverSafetyWarningCommand;
import com.amazon.alexa.mode.statemachine.command.StateChangeCommand;
import com.amazon.alexa.mode.util.DriveModeFTUEHelper;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public abstract class BaseState {
    private final StateDependencies mStateDependencies;

    /* renamed from: com.amazon.alexa.mode.statemachine.state.BaseState$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mode$statemachine$Event = new int[Event.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ModeSwitchedDriveMode.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ModeSwitchedMainMode.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ActivityDestroyed.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AccessoryConnected.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoBluetoothConnected.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AccessoryDisconnected.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoBluetoothDisconnected.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.FTUECompletedDriveModeEvent.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.FTUECompletedStandaloneModeEvent.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public BaseState(StateDependencies stateDependencies) {
        this.mStateDependencies = stateDependencies;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Command getIngressCommandBasedOnFTUEState(StateContext stateContext) {
        DriveModeFTUEHelper driveModeFTUEHelper = getStateDependencies().getDriveModeFTUEHelper();
        DriveModeFTUEHelper.FTUEType fTUEType = driveModeFTUEHelper.getFTUEType(stateContext);
        if (fTUEType == DriveModeFTUEHelper.FTUEType.DefaultFTUE) {
            if (driveModeFTUEHelper.shouldEnterStandAloneModeFTUE()) {
                return new IngressDefaultModeFTUECommand(getStateDependencies());
            }
        } else if (fTUEType == DriveModeFTUEHelper.FTUEType.AccessoryFTUE) {
            if (driveModeFTUEHelper.shouldEnterDriveModeFTUE()) {
                return new IngressDriveModeFTUECommand(getStateDependencies());
            }
        } else if (driveModeFTUEHelper.shouldEnterStandAloneModeFTUE()) {
            return new IngressStandaloneModeFTUECommand(getStateDependencies());
        }
        return getIngressCommandOrDriverSafetyWarningCommand();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Command getIngressCommandOrDriverSafetyWarningCommand() {
        if (getStateDependencies().getIdentityServiceHelper().getUserMarketPlace().equals(Marketplace.JAPAN)) {
            return new ShowDriverSafetyWarningCommand(getStateDependencies(), true, true);
        }
        return new IngressDriveModeCommand(getStateDependencies());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StateDependencies getStateDependencies() {
        return this.mStateDependencies;
    }

    public List<Command> onEvent(Event event, StateContext stateContext) {
        ArrayList arrayList = new ArrayList();
        int ordinal = event.ordinal();
        if (ordinal == 2) {
            if (!stateContext.isAutoBluetoothConnected()) {
                arrayList.add(new PublishDriveModeIngressCard(getStateDependencies()));
            }
            arrayList.add(new RecordAccessoryConnectedDisconnectedMetric(getStateDependencies(), true));
        } else if (ordinal == 3) {
            if (!stateContext.isAutoBluetoothConnected()) {
                arrayList.add(new RemoveDriveModeIngressCard(getStateDependencies()));
                arrayList.add(new CancelNotificationCommand(getStateDependencies()));
            }
            arrayList.add(new RecordAccessoryConnectedDisconnectedMetric(getStateDependencies(), false));
        } else if (ordinal == 6) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new DriveModeState(getStateDependencies())));
        } else if (ordinal == 7) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new MainModeState(getStateDependencies())));
        } else if (ordinal == 9) {
            arrayList.add(new SetFTUECompletionFlagCommand(getStateDependencies()));
        } else if (ordinal == 10) {
            arrayList.add(new SetStandaloneModeFTUECompletionFlagCommand(getStateDependencies()));
        } else if (ordinal != 16) {
            if (ordinal != 22) {
                if (ordinal == 23 && !stateContext.isAccessoryConnected()) {
                    arrayList.add(new RemoveDriveModeIngressCard(getStateDependencies()));
                    arrayList.add(new CancelNotificationCommand(getStateDependencies()));
                }
            } else if (!stateContext.isAccessoryConnected()) {
                arrayList.add(new PublishDriveModeIngressCard(getStateDependencies()));
            }
        } else if (!(this instanceof BackgroundState)) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), new BackgroundState(getStateDependencies(), this)));
        }
        return arrayList;
    }
}
