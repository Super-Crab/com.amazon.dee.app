package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.tarazed.dmps.DMPSHandlerDefault;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.amazon.dee.app.ui.voiceenrollment.VoiceEnrollmentLauncher;
import com.amazon.dee.app.ui.web.OOBEBridge;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class OOBEBridge extends JavaScriptBridge {
    private static final int OOBE_ERROR = 1;
    private static final int OOBE_SIGNOUT = 2;
    static final String POST_OOBE_FTUE_ROUTENAME = "alexa-oobe/postOobeFtue";
    public static final int REQUEST_CODE_MASK = 65535;
    public static final String SHOWN_OOBE_TOKEN = "comms-has-shown-oobe";
    static final String SHOWN_OOBE_VALUE = "true";
    static final String TAG = "OOBEBridge";
    AccountService accountService;
    Activity activity;
    Lazy<CommsServiceV2> commsServiceV2;
    Lazy<ConversationService> conversationService;
    Runnable currentTachyonOobeCompletion;
    EnvironmentService environmentService;
    EventBus eventBus;
    HandsFreeSetup handsFreeSetup;
    IdentityService identityService;
    ArrayMap<String, JavaScriptBridgeMethod> methods;
    MetricsService metricsService;
    RoutingService routingService;
    WebViewDelegate webViewDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class HasTachyonMethod implements JavaScriptBridgeMethod {
        HasTachyonMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) throws JSONException {
            OOBEBridge.this.webViewDelegate.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$OOBEBridge$HasTachyonMethod$RI-a4nYFzlMsJsH1qEFZl9NpekE
                @Override // java.lang.Runnable
                public final void run() {
                    OOBEBridge.HasTachyonMethod.this.lambda$execute$0$OOBEBridge$HasTachyonMethod(javaScriptResponse);
                }
            });
        }

        public /* synthetic */ void lambda$execute$0$OOBEBridge$HasTachyonMethod(JavaScriptResponse javaScriptResponse) {
            UserIdentity user = OOBEBridge.this.identityService.getUser(OOBEBridge.TAG);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(DMPSHandlerDefault.SET_PERMISSION_PAYLOAD_HAS_ACCESS_KEY, user != null);
                javaScriptResponse.setResponse(jSONObject);
            } catch (JSONException unused) {
                javaScriptResponse.setError(true);
            }
            OOBEBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class StartTachyonOOBEMethod implements JavaScriptBridgeMethod {
        StartTachyonOOBEMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(final JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) throws JSONException {
            OOBEBridge.this.webViewDelegate.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$OOBEBridge$StartTachyonOOBEMethod$OTh0LHxqb4W6oUBQgbi0bBnwC_E
                @Override // java.lang.Runnable
                public final void run() {
                    OOBEBridge.StartTachyonOOBEMethod.this.lambda$execute$1$OOBEBridge$StartTachyonOOBEMethod(jSONObject, javaScriptResponse);
                }
            });
        }

        public /* synthetic */ void lambda$execute$1$OOBEBridge$StartTachyonOOBEMethod(final JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) {
            OOBEBridge.this.handsFreeSetup.launchHandsFreeSetupIfSupported(new HandsFreeSetup.AfterSetupTransitionCallback() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$OOBEBridge$StartTachyonOOBEMethod$qjWPwSkkMPKR08iepUCcwMccrVs
                @Override // com.amazon.alexa.voice.handsfree.HandsFreeSetup.AfterSetupTransitionCallback
                public final void onTransitionOutOfSetup() {
                    OOBEBridge.StartTachyonOOBEMethod.this.lambda$null$0$OOBEBridge$StartTachyonOOBEMethod(jSONObject, javaScriptResponse);
                }
            });
        }

        public /* synthetic */ void lambda$launchCommsOobeIfNeeded$2$OOBEBridge$StartTachyonOOBEMethod(JavaScriptResponse javaScriptResponse) {
            String.format("The response being returned is:\n%s", javaScriptResponse.toString());
            OOBEBridge.this.completeRequest(javaScriptResponse);
        }

        /* renamed from: launchCommsOobeIfNeeded */
        public void lambda$null$0$OOBEBridge$StartTachyonOOBEMethod(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) {
            boolean optBoolean = jSONObject.optBoolean("isDeviceOOBECompleted");
            boolean optBoolean2 = jSONObject.optBoolean("forced", false);
            Object[] objArr = new Object[1];
            objArr[0] = optBoolean ? "yes" : "no";
            String.format("isDeviceOOBECompleted: %s", objArr);
            OOBEBridge oOBEBridge = OOBEBridge.this;
            if (oOBEBridge.currentTachyonOobeCompletion != null) {
                Log.e(OOBEBridge.TAG, "currentTachyonOobeCompletion is not null; request is already in progress");
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason("Request is already in progress");
                OOBEBridge.this.completeRequest(javaScriptResponse);
            } else if (oOBEBridge.commsServiceV2.mo358get().oobeService().isCommsOoobeCompleted()) {
                Log.e(OOBEBridge.TAG, "OOBE was already executed");
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason("OOBE was already executed.");
                OOBEBridge.this.completeRequest(javaScriptResponse);
            } else {
                UserIdentity user = OOBEBridge.this.identityService.getUser(OOBEBridge.TAG);
                if (user == null) {
                    Log.e(OOBEBridge.TAG, "No access to comms feature as userIdentity is null");
                    javaScriptResponse.setError(true);
                    javaScriptResponse.setErrorReason("No Access.");
                    OOBEBridge.this.completeRequest(javaScriptResponse);
                } else if ("true".equals(user.getToken(OOBEBridge.SHOWN_OOBE_TOKEN))) {
                    Log.e(OOBEBridge.TAG, "OOBE has already been shown.");
                    javaScriptResponse.setErrorReason("OOBE has already been shown.");
                    OOBEBridge.this.completeRequest(javaScriptResponse);
                } else if (OOBEBridge.this.commsServiceV2.mo358get().oobeService().getSkippedCommsOobe() && !optBoolean2) {
                    Log.e(OOBEBridge.TAG, "OOBE was already skipped and is not being forced");
                    javaScriptResponse.setError(true);
                    javaScriptResponse.setErrorReason("OOBE was already skipped");
                    OOBEBridge.this.completeRequest(javaScriptResponse);
                } else {
                    Intent intent = new Intent(OOBEBridge.this.activity, OOBEActivity.class);
                    intent.putExtra(OOBEActivity.GO_TO_USER_SELECTION, optBoolean);
                    intent.putExtra(OOBEActivity.OOBE_INTENT, OOBEActivity.OOBE_SIGNING_IN);
                    OOBEBridge oOBEBridge2 = OOBEBridge.this;
                    oOBEBridge2.currentTachyonOobeCompletion = new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$OOBEBridge$StartTachyonOOBEMethod$EH8byD5bsmBitS8ZDhdRB4xwb9A
                        @Override // java.lang.Runnable
                        public final void run() {
                            OOBEBridge.StartTachyonOOBEMethod.this.lambda$launchCommsOobeIfNeeded$2$OOBEBridge$StartTachyonOOBEMethod(javaScriptResponse);
                        }
                    };
                    oOBEBridge2.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.COMMS_LAUNCH_NEW_USER_OOBE, AlexaMetricsConstants.MetricsComponents.OOBE, null);
                    OOBEBridge.this.activity.startActivityForResult(intent, 5);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class StartVoiceEnrollment implements JavaScriptBridgeMethod {
        JSONObject requestParams;
        JavaScriptResponse response;
        Subscriber.SubscriberUuid voiceEnrollmentCompletedEventId = null;

        StartVoiceEnrollment() {
        }

        public void enrollmentComplete(Message message) {
            Log.i(OOBEBridge.TAG, "Received enrollment complete event from voice enrollment");
            Subscriber.SubscriberUuid subscriberUuid = this.voiceEnrollmentCompletedEventId;
            if (subscriberUuid != null) {
                OOBEBridge.this.eventBus.unsubscribe(subscriberUuid);
                this.voiceEnrollmentCompletedEventId = null;
            }
            OOBEBridge.this.completeRequest(this.response);
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            this.requestParams = jSONObject;
            this.response = javaScriptResponse;
            OOBEBridge.this.webViewDelegate.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$OOBEBridge$StartVoiceEnrollment$71L-C47Pw6-GQYFUz9gGZvx67fc
                @Override // java.lang.Runnable
                public final void run() {
                    OOBEBridge.StartVoiceEnrollment.this.lambda$execute$0$OOBEBridge$StartVoiceEnrollment();
                }
            });
        }

        /* renamed from: launchVoiceEnrollment */
        public void lambda$execute$0$OOBEBridge$StartVoiceEnrollment() {
            if (VoiceEnrollmentLauncher.canShowVoiceEnrollment(OOBEBridge.this.identityService.getUser(OOBEBridge.TAG), OOBEBridge.this.handsFreeSetup)) {
                OOBEBridge.this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.VOICE_ENROLLMENT_START_NEW_USER_OOBE, AlexaMetricsConstants.MetricsComponents.OOBE, null);
                OOBEBridge.this.routingService.route("voice-enrollment-oobe").with("enrollmentContext", "VOX_ENROLLMENT_FROM_COMMS_OOBE").navigate();
                if (this.voiceEnrollmentCompletedEventId != null) {
                    return;
                }
                MultiFilterSubscriber subscriber = OOBEBridge.this.eventBus.getSubscriber();
                this.voiceEnrollmentCompletedEventId = subscriber.getSubscriberUuid();
                subscriber.subscribeFilter(new EventTypeMessageFilter("voice:enrollment:completed"), new MessageHandler() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$dTwX9auP8wZDcXAfHicrH69uSRY
                    @Override // com.amazon.alexa.eventbus.api.MessageHandler
                    public final void handle(Message message) {
                        OOBEBridge.StartVoiceEnrollment.this.enrollmentComplete(message);
                    }
                });
                return;
            }
            OOBEBridge.this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.VOICE_ENROLLMENT_SKIP_NEW_USER_OOBE, AlexaMetricsConstants.MetricsComponents.OOBE, null);
            OOBEBridge.this.completeRequest(this.response);
        }
    }

    /* loaded from: classes12.dex */
    class WillShowDeviceOOBE implements JavaScriptBridgeMethod {
        WillShowDeviceOOBE() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            LatencyService.override(LatencyService.Completion.OOBE);
            LatencyService.complete(LatencyService.Component.WEB, LatencyService.Completion.OOBE);
            OOBEBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public OOBEBridge(Activity activity, Lazy<ConversationService> lazy, IdentityService identityService, EnvironmentService environmentService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, WebViewDelegate webViewDelegate, AccountService accountService, MetricsService metricsService, RoutingService routingService, EventBus eventBus, HandsFreeSetup handsFreeSetup, Lazy<CommsServiceV2> lazy2) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.currentTachyonOobeCompletion = null;
        this.activity = activity;
        this.conversationService = lazy;
        this.identityService = identityService;
        this.environmentService = environmentService;
        this.webViewDelegate = webViewDelegate;
        this.accountService = accountService;
        this.metricsService = metricsService;
        this.routingService = routingService;
        this.eventBus = eventBus;
        this.handsFreeSetup = handsFreeSetup;
        this.commsServiceV2 = lazy2;
        this.methods = new ArrayMap<>();
        this.methods.put("startTachyonOOBE", new StartTachyonOOBEMethod());
        this.methods.put("hasTachyon", new HasTachyonMethod());
        this.methods.put("willShowDeviceOobe", new WillShowDeviceOOBE());
        this.methods.put("startVoiceEnrollment", new StartVoiceEnrollment());
    }

    private boolean handleRouteIfNecessary(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return false;
        }
        if (Constants.ACCESSORY_OOBE.equals(extras.getString(Constants.OOBE_STARTED_FROM))) {
            String string = extras.getString(Constants.ROUTE_NAME);
            if (string != null) {
                if (string.equals(POST_OOBE_FTUE_ROUTENAME)) {
                    this.routingService.route("v2/alexa-oobe/postOobeFtue").with("deviceType", extras.getString("deviceType")).with("deviceSerialNumber", extras.getString("deviceSerialNumber")).with("isNewDevice", extras.getString("isNewDevice")).clearBackStack().addToBackStack().navigate();
                    return true;
                }
                this.routingService.route("web").with(RouteParameter.ROUTE, string).clearBackStack().addToBackStack().navigate();
                return true;
            }
            Log.e(TAG, "routeName null! Unable to route");
        } else {
            Log.i(TAG, "Default OOBE flow! Do not handle navigation");
        }
        return false;
    }

    static /* synthetic */ void lambda$onActivityResult$0(Void r0) {
    }

    private boolean shouldShowComms(UserIdentity userIdentity) {
        return userIdentity != null && !this.commsServiceV2.mo358get().oobeService().hasSelectedProfile();
    }

    private int unmaskRequestCode(int i) {
        return i & 65535;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.methods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return TAG;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        int unmaskRequestCode = unmaskRequestCode(i);
        if (i == 5 || unmaskRequestCode == 120) {
            UserIdentity user = this.identityService.getUser(TAG);
            Log.i(TAG, "OOBEBridge.onActivityResult");
            if (i2 == -1) {
                RouteContext currentRoute = this.routingService.getCurrentRoute();
                String route = currentRoute != null ? currentRoute.getRoute().toString() : null;
                if (!Boolean.valueOf(route != null && "v2/comms/conversation-list".equals(route)).booleanValue()) {
                    this.commsServiceV2.mo358get().oobeService().setCommsOobeJustFinished(true);
                }
                this.conversationService.mo358get().initialize();
            } else if (shouldShowComms(user)) {
                this.routingService.route("conversations").with("arguments", new Bundle()).addToBackStack().forceNavigate();
                return;
            }
            if (user != null) {
                IdentityService identityService = this.identityService;
                if (identityService instanceof MAPIdentityService) {
                    ((MAPIdentityService) identityService).putUserToken(SHOWN_OOBE_TOKEN, "true");
                }
            }
            if (i2 == 2) {
                String str = TAG;
                this.currentTachyonOobeCompletion = null;
                this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.COMMS_SIGN_OUT, str, null);
                this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.SIGN_OUT_ATTEMPT, TAG, null);
                this.accountService.signOut().subscribe($$Lambda$OOBEBridge$J_Qsa8P7suwARcNo6TCmeqXENQ.INSTANCE, $$Lambda$OOBEBridge$k7BB9REmaqO5qhlL2LFnTGd6C9s.INSTANCE);
            } else {
                Message build = new Message.Builder().setEventType(IdentityEvent.IDENTITY_OOBE_PROFILE_SELECTED).build();
                Log.i(TAG, "Sending event");
                try {
                    this.eventBus.publish(build);
                } catch (EventBusException e) {
                    Log.w(TAG, String.format("Failed to send the '%s' event. %s", IdentityEvent.IDENTITY_OOBE_PROFILE_SELECTED, e.getMessage()));
                }
                boolean handleRouteIfNecessary = i == 120 ? handleRouteIfNecessary(intent) : false;
                String.format("resultCode=%s", Integer.valueOf(i2));
                if (!handleRouteIfNecessary) {
                    this.routingService.route(RouteName.HOME).clearBackStack().addToBackStack().navigate();
                }
            }
            Runnable runnable = this.currentTachyonOobeCompletion;
            if (runnable != null) {
                runnable.run();
                this.currentTachyonOobeCompletion = null;
                return;
            }
            Log.w(TAG, String.format("ActivityRequestCode was %s", Integer.valueOf(i)));
        }
    }
}
