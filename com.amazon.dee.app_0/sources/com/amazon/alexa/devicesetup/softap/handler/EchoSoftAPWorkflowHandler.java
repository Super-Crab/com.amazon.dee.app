package com.amazon.alexa.devicesetup.softap.handler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.setup.echo.softap.linkcode.LinkCodeAuthorizer;
import com.amazon.alexa.device.setup.echo.softap.workflow.manager.SoftAPResponseCallback;
import com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowStateManager;
import com.amazon.alexa.devicesetup.softap.listener.LinkCodeAuthorizerImpl;
import com.amazon.alexa.devicesetup.softap.listener.LocaleAndEndpointConfiguratorImpl;
import com.amazon.alexa.devicesetup.softap.listener.SoftAPResponseCallbackImpl;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.device.setup.thrift.APConnectExtendedInfo;
import com.amazon.device.setup.thrift.APConnectInfo;
import com.google.gson.Gson;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class EchoSoftAPWorkflowHandler implements EchoSetupHandler {
    private static final String TAG = "SOFTAP::";
    private final Context context;
    private final MultiFilterSubscriber echoSoftAPWorkflowSubscriber;
    private final LazyComponent<EventBus> eventBus;
    private final Gson gson = new Gson();
    LazyComponent<IdentityService> identityService;
    private final LinkCodeAuthorizer linkCodeAuthorizer;
    private final LocaleAndEndpointConfiguratorImpl localeAndEndpointConfigurator;
    private final MessageFilter messageFilter;
    private final SoftAPResponseCallback softAPResponseCallback;
    private WorkflowStateManager workflowStateManager;

    public EchoSoftAPWorkflowHandler(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        this.eventBus = componentGetter.get(EventBus.class);
        LazyComponent lazyComponent = componentGetter.get(EnvironmentService.class);
        this.identityService = componentGetter.get(IdentityService.class);
        this.linkCodeAuthorizer = new LinkCodeAuthorizerImpl(lazyComponent, this.identityService, this.eventBus);
        this.softAPResponseCallback = new SoftAPResponseCallbackImpl(this.gson, componentGetter.get(EventBus.class));
        this.localeAndEndpointConfigurator = new LocaleAndEndpointConfiguratorImpl(lazyComponent, this.identityService, context);
        this.context = context;
        this.messageFilter = new EventTypeMessageFilter("echosoftap*");
        this.echoSoftAPWorkflowSubscriber = new SimpleMultiFilterSubscriber();
    }

    @Override // com.amazon.alexa.devicesetup.softap.handler.EchoSetupHandler
    @SuppressLint({"NewApi"})
    public void handleEvent(Message message) {
        String eventType = message.getEventType();
        if (eventType.equals("echosoftap::exception") || eventType.contains("response") || this.workflowStateManager == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(message.getPayloadAsString());
            char c = 65535;
            switch (eventType.hashCode()) {
                case -1938397063:
                    if (eventType.equals("echosoftap::connectToAPRequest")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1908829705:
                    if (eventType.equals("echosoftap::setPreferredLocale")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -1107686083:
                    if (eventType.equals("echosoftap::getWifiNetworksRequest")) {
                        c = 1;
                        break;
                    }
                    break;
                case -441679694:
                    if (eventType.equals("echosoftap::autoConnectToEchoRequest")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -197787194:
                    if (eventType.equals("echosoftap::connectToAPExRequest")) {
                        c = 3;
                        break;
                    }
                    break;
                case 128764771:
                    if (eventType.equals("echosoftap::connectToEchoRequest")) {
                        c = 0;
                        break;
                    }
                    break;
                case 774151875:
                    if (eventType.equals("echosoftap::stopEvaluatingCaptivePortalRequest")) {
                        c = 7;
                        break;
                    }
                    break;
                case 1314975292:
                    if (eventType.equals("echosoftap::evaluateHotSpotDeviceNetworkDisconnect")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 1667364516:
                    if (eventType.equals("echosoftap::evaluateCaptivePortalRequest")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1847515498:
                    if (eventType.equals("echosoftap::cancelSetupRequest")) {
                        c = 6;
                        break;
                    }
                    break;
                case 2029581397:
                    if (eventType.equals("echosoftap::forgetAPRequest")) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.workflowStateManager.connectToEcho();
                    return;
                case 1:
                    this.workflowStateManager.getWifiNetworks();
                    return;
                case 2:
                    String string = jSONObject.getString("apConnectInfo");
                    this.workflowStateManager.connectToWifiNetwork((APConnectInfo) this.gson.fromJson(string, (Class<Object>) APConnectInfo.class));
                    return;
                case 3:
                    String string2 = jSONObject.getString("apConnectInfo");
                    String string3 = jSONObject.getString("apConnectExtendedInfo");
                    this.workflowStateManager.connectToWifiNetwork((APConnectInfo) this.gson.fromJson(string2, (Class<Object>) APConnectInfo.class), (APConnectExtendedInfo) this.gson.fromJson(string3, (Class<Object>) APConnectExtendedInfo.class));
                    return;
                case 4:
                    jSONObject.getString("ssid");
                    return;
                case 5:
                    this.workflowStateManager.evaluateCaptivePortal();
                    return;
                case 6:
                    this.workflowStateManager.cancelSetup();
                    return;
                case 7:
                    this.workflowStateManager.clearCurrentRequest();
                    return;
                case '\b':
                    this.workflowStateManager.evaluateHotSpotDeviceNetworkDisconnect();
                    return;
                case '\t':
                    this.workflowStateManager.autoConnectToEcho();
                    return;
                case '\n':
                    this.localeAndEndpointConfigurator.setPreferredLocale(jSONObject.getString("locale"));
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override // com.amazon.alexa.devicesetup.softap.handler.EchoSetupHandler
    public void startListening() {
        this.workflowStateManager = new WorkflowStateManager(this.context, this.linkCodeAuthorizer, this.localeAndEndpointConfigurator, this.softAPResponseCallback, this.identityService);
        this.echoSoftAPWorkflowSubscriber.subscribe(new EventTypeMessageFilter("echosoftap*"), new MessageHandler() { // from class: com.amazon.alexa.devicesetup.softap.handler.-$$Lambda$XsL07i9C5aBVzwg-FSom4hcr8d8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EchoSoftAPWorkflowHandler.this.handleEvent(message);
            }
        });
        this.eventBus.mo10268get().subscribe(this.echoSoftAPWorkflowSubscriber);
    }
}
