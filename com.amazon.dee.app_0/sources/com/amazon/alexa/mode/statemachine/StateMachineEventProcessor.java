package com.amazon.alexa.mode.statemachine;

import com.amazon.alexa.mode.statemachine.StateContext;
import com.amazon.alexa.mode.statemachine.command.Command;
import com.amazon.alexa.mode.statemachine.command.PostNotificationCommand;
import com.amazon.alexa.mode.statemachine.command.PostSecondaryNotificationCommand;
/* loaded from: classes9.dex */
public class StateMachineEventProcessor {

    /* renamed from: com.amazon.alexa.mode.statemachine.StateMachineEventProcessor$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mode$statemachine$Event = new int[Event.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AccessoryConnected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AccessoryDisconnected.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ModeSwitchedDriveMode.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ScreenOn.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ScreenOff.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.DeepLinkAppLaunch.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AppForegrounded.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoBluetoothConnected.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoBluetoothDisconnected.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ManualIngressStarted.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ModeSwitchedMainMode.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.ActivityDestroyed.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.FTUECancelledEvent.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mode$statemachine$Event[Event.AutoDeviceOOBEDriveModeAllDoneNo.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    public StateContext processStateContext(StateContext stateContext, Event event) {
        StateContext.Builder builder = new StateContext.Builder(stateContext);
        int ordinal = event.ordinal();
        if (ordinal == 2) {
            builder.setAccessoryConnected(true);
        } else if (ordinal == 3) {
            builder.setAccessoryConnected(false);
            builder.setNotificationSent(false);
        } else if (ordinal != 4) {
            if (ordinal != 6) {
                if (ordinal != 7 && ordinal != 11 && ordinal != 16) {
                    if (ordinal != 26) {
                        switch (ordinal) {
                            case 19:
                                builder.setScreenOn(true);
                                break;
                            case 20:
                                builder.setScreenOn(false);
                                break;
                            case 21:
                                builder.setLaunchAppFromDeepLink(true);
                                break;
                            case 22:
                                builder.setAutoBluetoothConnected(true);
                                break;
                            case 23:
                                builder.setAutoBluetoothConnected(false);
                                break;
                        }
                    } else {
                        builder.setManualIngressMode(true);
                    }
                }
                builder.setManualIngressMode(false);
            } else {
                builder.setPrimaryNotificationDisplayCount(0);
            }
        } else if (stateContext.isLaunchAppFromDeepLink()) {
            builder.setLaunchAppFromDeepLink(false);
        }
        return builder.build();
    }

    public StateContext processStateContext(StateContext stateContext, Command command) {
        StateContext.Builder builder = new StateContext.Builder(stateContext);
        if (command instanceof PostNotificationCommand) {
            builder.setNotificationSent(true);
            builder.setPrimaryNotificationDisplayCount(stateContext.getPrimaryNotificationDisplayCount() + 1);
        } else if (command instanceof PostSecondaryNotificationCommand) {
            builder.setPrimaryNotificationDisplayCount(0);
        }
        return builder.build();
    }
}
