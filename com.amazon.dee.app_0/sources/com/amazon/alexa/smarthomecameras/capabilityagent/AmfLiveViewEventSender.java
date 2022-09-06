package com.amazon.alexa.smarthomecameras.capabilityagent;

import android.util.Log;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.amazon.alexa.smarthomecameras.model.LiveViewPayload;
import com.amazon.alexa.smarthomecameras.model.LiveViewTarget;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.util.CamerasLogger;
import com.amazon.alexa.smarthomecameras.util.GsonHelper;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes10.dex */
public class AmfLiveViewEventSender implements LiveViewEventSender {
    private static final String TAG = "LiveViewEventSender";
    private AlexaMobileFrameworkApis amfApis;
    private CamerasMobilyticsService mobilyticsService;

    public AmfLiveViewEventSender(AlexaMobileFrameworkApis alexaMobileFrameworkApis, CamerasMobilyticsService camerasMobilyticsService) {
        this.amfApis = alexaMobileFrameworkApis;
        this.mobilyticsService = camerasMobilyticsService;
    }

    @Override // com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender
    public void initialize() {
        Log.i(TAG, "Initializing AmfApis");
        this.amfApis.start();
    }

    @Override // com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender
    public void sendLiveViewStartedEvent(String str, String str2) {
        Log.i(TAG, "Sending the live view started event");
        this.amfApis.getEventSender().send(new AlexaEvent(AlexaHeader.create(LiveViewCapabilityAgentConstants.LIVE_VIEW_CAPABILITY_AGENT_INTERFACE_NAME, LiveViewCapabilityAgentConstants.LIVE_VIEW_EVENT_LIVE_VIEW_STARTED), new AlexaPayload(GsonHelper.toJson(new LiveViewPayload(str, new LiveViewTarget(LiveViewCapabilityAgentConstants.LIVE_VIEW_TARGET_TYPE_CHR, str2))))));
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.START_COMPLETED);
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "LiveViewStarted event sent");
    }

    @Override // com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender
    public void sendLiveViewStoppedEvent(String str, String str2) {
        Log.i(TAG, "Sending the live view stopped event");
        this.amfApis.getEventSender().send(new AlexaEvent(AlexaHeader.create(LiveViewCapabilityAgentConstants.LIVE_VIEW_CAPABILITY_AGENT_INTERFACE_NAME, LiveViewCapabilityAgentConstants.LIVE_VIEW_EVENT_LIVE_VIEW_STOPPED), new AlexaPayload(GsonHelper.toJson(new LiveViewPayload(str, new LiveViewTarget(LiveViewCapabilityAgentConstants.LIVE_VIEW_TARGET_TYPE_CHR, str2))))));
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.STOP_COMPLETED);
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "LiveViewStopped event sent");
    }

    @Override // com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender
    public void sendRequestStartLiveViewEvent(String str) {
        Log.i(TAG, "Sending the request start live view event");
        this.amfApis.getEventSender().send(new AlexaEvent(AlexaHeader.create(LiveViewCapabilityAgentConstants.LIVE_VIEW_CAPABILITY_AGENT_INTERFACE_NAME, LiveViewCapabilityAgentConstants.LIVE_VIEW_EVENT_REQUEST_START_LIVE_VIEW), new AlexaPayload(GsonHelper.toJson(new LiveViewPayload(new LiveViewTarget(LiveViewCapabilityAgentConstants.LIVE_VIEW_TARGET_TYPE_CHR, str))))));
        CamerasMobilyticsService camerasMobilyticsService = this.mobilyticsService;
        if (camerasMobilyticsService != null) {
            camerasMobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.START_REQUESTED);
        }
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "RequestStartLiveView event sent");
    }

    @Override // com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender
    public void sendRequestStopLiveViewEvent(String str, String str2) {
        Log.i(TAG, "Sending the request stop live view event");
        this.amfApis.getEventSender().send(new AlexaEvent(AlexaHeader.create(LiveViewCapabilityAgentConstants.LIVE_VIEW_CAPABILITY_AGENT_INTERFACE_NAME, LiveViewCapabilityAgentConstants.LIVE_VIEW_EVENT_REQUEST_STOP_LIVE_VIEW), new AlexaPayload(GsonHelper.toJson(new LiveViewPayload(str, new LiveViewTarget(LiveViewCapabilityAgentConstants.LIVE_VIEW_TARGET_TYPE_CHR, str2))))));
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.STOP_REQUESTED);
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "RequestStopLiveView event sent");
    }

    @Override // com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender
    public void teardown() {
        Log.i(TAG, "Tearing down");
        this.amfApis.stop();
        this.amfApis.destroy();
    }
}
