package com.amazon.alexa.mode.statemachine.state;

import android.content.res.Resources;
import androidx.annotation.NonNull;
import com.amazon.alexa.mode.R;
import com.amazon.alexa.mode.statemachine.Event;
import com.amazon.alexa.mode.statemachine.StateContext;
import com.amazon.alexa.mode.statemachine.StateDependencies;
import com.amazon.alexa.mode.statemachine.command.CancelNotificationCommand;
import com.amazon.alexa.mode.statemachine.command.Command;
import com.amazon.alexa.mode.statemachine.command.PostNotificationCommand;
import com.amazon.alexa.mode.statemachine.command.PostSecondaryNotificationCommand;
import com.amazon.alexa.mode.statemachine.command.RecordAutoIngressMetricCommand;
import com.amazon.alexa.mode.statemachine.command.RecordBackgroundForegroundMetricsCommand;
import com.amazon.alexa.mode.statemachine.command.RouteToHomeCommand;
import com.amazon.alexa.mode.statemachine.command.StateChangeCommand;
import com.amazon.alexa.mode.statemachine.state.AutoIngressDisabledState;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class BackgroundState extends BaseState {
    private BaseState mPrevState;
    private boolean wasAccessoryConnectedWhileInBackground;
    private boolean wasAutoBluetoothConnectedWhileInBackground;

    /* renamed from: com.amazon.alexa.mode.statemachine.state.BackgroundState$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mode$statemachine$Event = new int[Event.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AccessoryConnected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoBluetoothConnected.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AccessoryDisconnected.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoBluetoothDisconnected.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ModeSwitchedMainMode.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ModeSwitchedDriveMode.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ScreenOn.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AppForegrounded.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.LoginOOBEEnded.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public BackgroundState(StateDependencies stateDependencies, @NonNull BaseState baseState) {
        super(stateDependencies);
        this.mPrevState = baseState;
    }

    public BaseState getPrevState() {
        return this.mPrevState;
    }

    @Override // com.amazon.alexa.mode.statemachine.state.BaseState
    public List<Command> onEvent(Event event, StateContext stateContext) {
        List<Command> onEvent = super.onEvent(event, stateContext);
        ArrayList arrayList = new ArrayList();
        Resources resources = getStateDependencies().getContext().getResources();
        int ordinal = event.ordinal();
        if (ordinal == 2) {
            if (getStateDependencies().getPrefsDialogHelper().isNotificationEnabled() && stateContext.isScreenOn() && !stateContext.isManualIngressMode()) {
                arrayList.add(new PostNotificationCommand(getStateDependencies(), resources.getString(R.string.notification_launch_mode_title), resources.getString(R.string.notification_launch_main_mode)));
                if (stateContext.getPrimaryNotificationDisplayCount() >= 3) {
                    arrayList.add(new PostSecondaryNotificationCommand(getStateDependencies()));
                }
            }
            this.wasAccessoryConnectedWhileInBackground = true;
        } else if (ordinal == 3) {
            this.wasAccessoryConnectedWhileInBackground = false;
            if ((this.mPrevState instanceof DriveModeState) && !stateContext.isManualIngressMode()) {
                arrayList.add(new RouteToHomeCommand(getStateDependencies()));
            }
        } else if (ordinal == 4) {
            arrayList.add(new StateChangeCommand(getStateDependencies(), this.mPrevState));
            if ((this.wasAccessoryConnectedWhileInBackground || this.wasAutoBluetoothConnectedWhileInBackground) && !stateContext.isManualIngressMode() && getStateDependencies().getPrefsDialogHelper().isDriveModeAutoLaunchEnabled() && !stateContext.isLaunchAppFromDeepLink()) {
                arrayList.add(new RecordAutoIngressMetricCommand(getStateDependencies(), this.wasAccessoryConnectedWhileInBackground ? "Accessory" : "AutoBluetooth"));
                arrayList.add(getIngressCommandBasedOnFTUEState(stateContext));
            }
            arrayList.add(new CancelNotificationCommand(getStateDependencies()));
            if (this.mPrevState instanceof DriveModeState) {
                arrayList.add(new RecordBackgroundForegroundMetricsCommand(getStateDependencies(), false));
            }
        } else if (ordinal == 6) {
            this.mPrevState = new DriveModeState(getStateDependencies());
        } else if (ordinal == 7) {
            this.mPrevState = new MainModeState(getStateDependencies());
        } else if (ordinal != 15) {
            if (ordinal != 19) {
                if (ordinal == 22) {
                    if (getStateDependencies().getPrefsDialogHelper().isNotificationEnabled() && stateContext.isScreenOn() && !stateContext.isManualIngressMode()) {
                        arrayList.add(new PostNotificationCommand(getStateDependencies(), resources.getString(R.string.notification_launch_mode_title), resources.getString(R.string.notification_launch_stand_alone_mode)));
                        if (stateContext.getPrimaryNotificationDisplayCount() >= 3) {
                            arrayList.add(new PostSecondaryNotificationCommand(getStateDependencies()));
                        }
                    }
                    this.wasAutoBluetoothConnectedWhileInBackground = true;
                } else if (ordinal == 23) {
                    this.wasAutoBluetoothConnectedWhileInBackground = false;
                    if ((this.mPrevState instanceof DriveModeState) && !stateContext.isManualIngressMode()) {
                        arrayList.add(new RouteToHomeCommand(getStateDependencies()));
                    }
                }
            } else if (getStateDependencies().getPrefsDialogHelper().isNotificationEnabled() && !stateContext.isManualIngressMode() && ((stateContext.isAccessoryConnected() || stateContext.isAutoBluetoothConnected()) && !stateContext.isNotificationSent())) {
                if (stateContext.isAccessoryConnected()) {
                    arrayList.add(new PostNotificationCommand(getStateDependencies(), resources.getString(R.string.notification_launch_mode_title), resources.getString(R.string.notification_launch_main_mode)));
                } else {
                    arrayList.add(new PostNotificationCommand(getStateDependencies(), resources.getString(R.string.notification_launch_mode_title), resources.getString(R.string.notification_launch_stand_alone_mode)));
                }
                if (stateContext.getPrimaryNotificationDisplayCount() >= 3) {
                    arrayList.add(new PostSecondaryNotificationCommand(getStateDependencies()));
                }
            }
        } else if ((this.mPrevState instanceof AutoIngressDisabledState) && !stateContext.isManualIngressMode() && ((AutoIngressDisabledState) this.mPrevState).getIngressDisabledReason().equals(AutoIngressDisabledState.AutoIngressDisabledReason.LoginOOBEFlow)) {
            this.wasAccessoryConnectedWhileInBackground = stateContext.isAccessoryConnected();
            this.wasAutoBluetoothConnectedWhileInBackground = stateContext.isAutoBluetoothConnected();
            this.mPrevState = new MainModeState(getStateDependencies());
        }
        if (event != Event.ModeSwitchedMainMode && event != Event.ModeSwitchedDriveMode) {
            arrayList.addAll(onEvent);
        }
        return arrayList;
    }
}
