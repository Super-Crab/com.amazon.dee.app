package com.amazon.alexa.smarthomecameras.capabilityagent;
/* loaded from: classes10.dex */
public final class LiveViewCapabilityAgentConstants {
    public static final String LIVE_VIEW_CAPABILITY_AGENT_INTERFACE_NAME = "Alexa.Camera.LiveViewController";
    public static final String LIVE_VIEW_CAPABILITY_AGENT_INTERFACE_VERSION = "1.0";
    public static final String LIVE_VIEW_CONTEXT_STATE_NAME = "LiveViewControllerState";
    public static final String LIVE_VIEW_CONTROLLER_EVENT = "shcameras::liveViewControllerEvent";
    public static final String LIVE_VIEW_DIRECTIVE_START_LIVE_VIEW = "StartLiveView";
    public static final String LIVE_VIEW_DIRECTIVE_STOP_LIVE_VIEW = "StopLiveView";
    public static final String LIVE_VIEW_EVENT_LIVE_VIEW_STARTED = "LiveViewStarted";
    public static final String LIVE_VIEW_EVENT_LIVE_VIEW_STOPPED = "LiveViewStopped";
    public static final String LIVE_VIEW_EVENT_REQUEST_START_LIVE_VIEW = "RequestStartLiveView";
    public static final String LIVE_VIEW_EVENT_REQUEST_STOP_LIVE_VIEW = "RequestStopLiveView";
    public static final String LIVE_VIEW_TARGET_TYPE_CHR = "CHR_ENDPOINT";

    /* loaded from: classes10.dex */
    public enum StopLiveViewStatuses {
        STOP_LIVE_VIEW_REQUESTED,
        MEDIA_SOURCE_NOT_FOUND,
        UNAUTHORIZED,
        BATTERY_LEVEL_TOO_LOW,
        MEDIA_SOURCE_ASLEEP,
        MEDIA_SOURCE_TURNED_OFF,
        UNKNOWN_TO_SDK_VERSION
    }

    private LiveViewCapabilityAgentConstants() {
        throw new IllegalStateException("Live View Constants Utility class");
    }
}
