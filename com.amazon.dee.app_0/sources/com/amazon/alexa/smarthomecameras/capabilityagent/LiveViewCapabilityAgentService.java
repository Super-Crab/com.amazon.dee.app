package com.amazon.alexa.smarthomecameras.capabilityagent;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.smarthomecameras.constants.ErrorConstants;
import com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponentProvider;
import com.amazon.alexa.smarthomecameras.model.LiveViewControllerEventPayload;
import com.amazon.alexa.smarthomecameras.model.LiveViewPayload;
import com.amazon.alexa.smarthomecameras.util.CamerasLogger;
import com.amazon.alexa.smarthomecameras.util.GsonHelper;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public class LiveViewCapabilityAgentService extends AlexaCapabilityAgentService {
    private static final String TAG = "LiveViewCptAgtService";
    private AmfLiveViewEventSender eventSender;

    private void handleStartLiveView(@NonNull AlexaDirective alexaDirective) throws JsonSyntaxException {
        LiveViewPayload liveViewPayload = (LiveViewPayload) GsonHelper.fromJson(alexaDirective.getAlexaPayload().getPayload(), (Class<Object>) LiveViewPayload.class);
        Log.i(TAG, "Starting the live view");
    }

    private void handleStopLiveView(@NonNull AlexaDirective alexaDirective) throws JsonSyntaxException {
        LiveViewPayload liveViewPayload = (LiveViewPayload) GsonHelper.fromJson(alexaDirective.getAlexaPayload().getPayload(), (Class<Object>) LiveViewPayload.class);
        Log.i(TAG, "Stopping the live view");
        if (CameraComponentProvider.getCameraComponent() != null) {
            CameraComponentProvider.getCameraComponent().cameraSessionManager().sessionEnded(liveViewPayload.getStatus().toString(), liveViewPayload.getDescription());
        } else {
            Log.i(TAG, "No active session ongoing");
        }
    }

    private void setUpEventBusListener() {
        ((EventBus) GeneratedOutlineSupport1.outline20(EventBus.class)).getSubscriber().subscribeFilter($$Lambda$LiveViewCapabilityAgentService$CB_P_6ePZopMouTXVMEMlVscFNs.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.smarthomecameras.capabilityagent.-$$Lambda$LiveViewCapabilityAgentService$RmFNUHGVNim32rCdBW2Ao0SI1tI
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                LiveViewCapabilityAgentService.this.lambda$setUpEventBusListener$1$LiveViewCapabilityAgentService(message);
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public Set<AlexaCapability> getCapabilities() {
        Log.i(TAG, "getLiveViewCapabilities ");
        HashSet hashSet = new HashSet();
        hashSet.add(AlexaCapability.create(LiveViewCapabilityAgentConstants.LIVE_VIEW_CAPABILITY_AGENT_INTERFACE_NAME, "1.0"));
        Log.i(TAG, "returning capability set of count " + hashSet.size());
        return hashSet;
    }

    public /* synthetic */ void lambda$setUpEventBusListener$1$LiveViewCapabilityAgentService(Message message) {
        LiveViewControllerEventPayload liveViewControllerEventPayload;
        if (!LiveViewCapabilityAgentConstants.LIVE_VIEW_CONTROLLER_EVENT.equals(message.getEventType()) || (liveViewControllerEventPayload = (LiveViewControllerEventPayload) new Gson().fromJson(message.getPayloadAsString(), (Class<Object>) LiveViewControllerEventPayload.class)) == null || !liveViewControllerEventPayload.getName().equals(LiveViewCapabilityAgentConstants.LIVE_VIEW_EVENT_REQUEST_START_LIVE_VIEW)) {
            return;
        }
        this.eventSender = new AmfLiveViewEventSender(new AlexaMobileFrameworkApis(this), null);
        this.eventSender.initialize();
        if (liveViewControllerEventPayload.getEndpointId() != null) {
            this.eventSender.sendRequestStartLiveViewEvent(liveViewControllerEventPayload.getEndpointId());
        }
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "WarmUp RequestStartLiveView event sent");
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        setUpEventBusListener();
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        AmfLiveViewEventSender amfLiveViewEventSender = this.eventSender;
        if (amfLiveViewEventSender != null) {
            amfLiveViewEventSender.teardown();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i(TAG, "Received start id " + i2 + RealTimeTextConstants.COLON_SPACE + intent);
        return 2;
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public boolean process(@NonNull AlexaDirective alexaDirective) {
        Log.i(TAG, "process -- Process directives from Alexa");
        String namespace = alexaDirective.getNamespace();
        String name = alexaDirective.getName();
        if (!LiveViewCapabilityAgentConstants.LIVE_VIEW_CAPABILITY_AGENT_INTERFACE_NAME.equals(namespace)) {
            Log.w(TAG, "process -- Not a Live View namespace directive. Can't handle it");
            return false;
        }
        char c = 65535;
        int hashCode = name.hashCode();
        if (hashCode != -1951145965) {
            if (hashCode == 408791027 && name.equals(LiveViewCapabilityAgentConstants.LIVE_VIEW_DIRECTIVE_STOP_LIVE_VIEW)) {
                c = 1;
            }
        } else if (name.equals(LiveViewCapabilityAgentConstants.LIVE_VIEW_DIRECTIVE_START_LIVE_VIEW)) {
            c = 0;
        }
        if (c == 0) {
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "StartLiveView directive received");
            try {
                handleStartLiveView(alexaDirective);
                return true;
            } catch (JsonSyntaxException e) {
                Log.e(TAG, e.getLocalizedMessage());
                return false;
            }
        } else if (c != 1) {
            Log.w(TAG, "process -- Not a valid Live View Directive. Can't handle it.");
            if (CameraComponentProvider.getCameraComponent() != null) {
                CameraComponentProvider.getCameraComponent().cameraSessionManager().sessionEnded(ErrorConstants.UNKNOWN_DIRECTIVE, "");
            } else {
                Log.i(TAG, "No active session ongoing");
            }
            return false;
        } else {
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "StopLiveView directive received");
            try {
                handleStopLiveView(alexaDirective);
                return true;
            } catch (JsonSyntaxException e2) {
                Log.e(TAG, e2.getLocalizedMessage());
                return false;
            }
        }
    }
}
