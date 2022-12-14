package com.amazon.alexa.mode.statemachine;
/* loaded from: classes9.dex */
public enum Event {
    DriveModeIngressRequested,
    DriveModeEgressRequested,
    AccessoryConnected,
    AccessoryDisconnected,
    AppForegrounded,
    AppBackgrounded,
    ModeSwitchedDriveMode,
    ModeSwitchedMainMode,
    FTUEStartedEvent,
    FTUECompletedDriveModeEvent,
    FTUECompletedStandaloneModeEvent,
    FTUECancelledEvent,
    AutoDeviceOOBEStarted,
    AutoDeviceOOBEEnded,
    LoginOOBEStarted,
    LoginOOBEEnded,
    ActivityDestroyed,
    AutoDeviceOOBEDriveModeAllDoneYes,
    AutoDeviceOOBEDriveModeAllDoneNo,
    ScreenOn,
    ScreenOff,
    DeepLinkAppLaunch,
    AutoBluetoothConnected,
    AutoBluetoothDisconnected,
    WirelessChargingError,
    WirelessChargingNoError,
    ManualIngressStarted
}
