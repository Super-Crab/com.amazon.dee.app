package com.amazon.alexa.devicesetup.softap.listener;

import android.util.Log;
import android.webkit.CookieManager;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.HttpRequestConstants;
import com.amazon.alexa.device.setup.echo.softap.linkcode.AuthorizationCallback;
import com.amazon.alexa.device.setup.echo.softap.linkcode.LinkCodeAuthorizer;
import com.amazon.alexa.device.setup.echo.softap.linkcode.PreAuthorizationCallback;
import com.amazon.alexa.device.setup.echo.softap.linkcode.PreAuthorizedLinkCode;
import com.amazon.alexa.devicesetup.softap.configuration.DeviceMasterServiceEndpoint;
import com.amazon.alexa.devicesetup.softap.configuration.EndpointMapUtil;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.data.registry.NativeFeatureRegistry;
import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.marketplace.api.CountryCode;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.net.HttpHeaders;
import io.reactivex.rxjava3.functions.Consumer;
import java.security.SecureRandom;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class LinkCodeAuthorizerImpl implements LinkCodeAuthorizer {
    private static final String DEFAULT_MEDIA_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";
    private static final String DMS_REGISTRATION_URL = "/api/devices/device";
    private static final String HTTPS = "https://";
    private static final String POST_METHOD_TYPE = "POST";
    private static final String TAG = "LinkCodeAuthorizer";
    private AuthorizationCallback authorizationCallback;
    private LazyComponent<EnvironmentService> environmentService;
    private LazyComponent<EventBus> eventBus;
    private LazyComponent<IdentityService> identityService;

    public LinkCodeAuthorizerImpl(LazyComponent<EnvironmentService> lazyComponent, LazyComponent<IdentityService> lazyComponent2, LazyComponent<EventBus> lazyComponent3) {
        this.environmentService = lazyComponent;
        this.identityService = lazyComponent2;
        this.eventBus = lazyComponent3;
        initializeLinkCodeCallbackListener();
    }

    private String getCsrfFromCookie(String str) {
        String[] split;
        for (String str2 : str.split(";")) {
            if (str2.contains(HttpRequestConstants.CSRF)) {
                return str2.split(Config.Compare.EQUAL_TO)[1];
            }
        }
        return String.valueOf(new SecureRandom().nextInt());
    }

    private void initializeLinkCodeCallbackListener() {
        MultiFilterSubscriber subscriber = this.eventBus.mo10268get().getSubscriber();
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_LINK_CODE_AUTHORIZED), new MessageHandler() { // from class: com.amazon.alexa.devicesetup.softap.listener.-$$Lambda$LinkCodeAuthorizerImpl$Wjl4U-Lc_oztV6vtvKaCa5imkUs
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                LinkCodeAuthorizerImpl.this.lambda$initializeLinkCodeCallbackListener$2$LinkCodeAuthorizerImpl(message);
            }
        });
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_LINK_CODE_NOT_AUTHORIZED), new MessageHandler() { // from class: com.amazon.alexa.devicesetup.softap.listener.-$$Lambda$LinkCodeAuthorizerImpl$OKP4xMpFjFqLZyzJMGfvh0tx3U4
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                LinkCodeAuthorizerImpl.this.lambda$initializeLinkCodeCallbackListener$3$LinkCodeAuthorizerImpl(message);
            }
        });
    }

    private void onLinkCodeAuthorized() {
        if (this.authorizationCallback != null) {
            Log.i(TAG, "link code is authorized");
            this.authorizationCallback.authorizationSuccess();
            this.authorizationCallback = null;
            return;
        }
        Log.e(TAG, "authorizationCallback doesn't exist");
    }

    private void onLinkCodeNotAuthorized() {
        if (this.authorizationCallback != null) {
            Log.i(TAG, "Failed on authorizing link code");
            this.authorizationCallback.authorizationFailure(new Exception("failed to authorize link code."));
            this.authorizationCallback = null;
            return;
        }
        Log.e(TAG, "authorizationCallback doesn't exist");
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.linkcode.LinkCodeAuthorizer
    public void authorizeLinkCode(String str, AuthorizationCallback authorizationCallback) {
        this.authorizationCallback = authorizationCallback;
        this.eventBus.mo10268get().publish(new Message.Builder().setEventType(IdentityEvent.IDENTITY_LINK_CODE_REQUEST).setPayload(str).build());
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.linkcode.LinkCodeAuthorizer
    public void generatePreAuthorizedLinkCode(final PreAuthorizationCallback preAuthorizationCallback) {
        ((MAPIdentityService) this.identityService.mo10268get()).generateCBLRegistrationToken().subscribe(new Consumer() { // from class: com.amazon.alexa.devicesetup.softap.listener.-$$Lambda$LinkCodeAuthorizerImpl$hYF3A413CbWc4Gs2f6LGPBhbcSw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PreAuthorizationCallback.this.authorizationSuccess(new PreAuthorizedLinkCode((String) obj));
            }
        }, new Consumer() { // from class: com.amazon.alexa.devicesetup.softap.listener.-$$Lambda$LinkCodeAuthorizerImpl$Dvrl-wF2An8y8lmE1dOFdhhOHhU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PreAuthorizationCallback.this.authorizationFailure((Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$initializeLinkCodeCallbackListener$2$LinkCodeAuthorizerImpl(Message message) {
        onLinkCodeAuthorized();
    }

    public /* synthetic */ void lambda$initializeLinkCodeCallbackListener$3$LinkCodeAuthorizerImpl(Message message) {
        onLinkCodeNotAuthorized();
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.linkcode.LinkCodeAuthorizer
    public void authorizeLinkCode(String str, OkHttpClient okHttpClient, AuthorizationCallback authorizationCallback) {
        String forStageAndMarketPlace;
        String webEndpoint = this.environmentService.mo10268get().getWebEndpoint();
        new String();
        if (this.identityService.mo10268get().getUser(TAG) != null) {
            ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
            if (((FeatureServiceV2) componentRegistry.getLazy(FeatureServiceV2.class).mo10268get()).hasAccess(NativeFeatureRegistry.DEVICE_SETUP_DATA_REGION_ANDROID, false)) {
                Log.i(TAG, "[oobe-android]: using OOBE data region changes");
                EnvironmentService environmentService = (EnvironmentService) componentRegistry.get(EnvironmentService.class).get();
                forStageAndMarketPlace = DeviceMasterServiceEndpoint.forStageAndMarketPlace(environmentService.getBuildStage(), EndpointMapUtil.countryCodeToUse(environmentService.getCoralEndpoint()));
            } else {
                Log.i(TAG, "[oobe-android]: not using OOBE data region changes");
                forStageAndMarketPlace = DeviceMasterServiceEndpoint.forStageAndMarketPlace(this.environmentService.mo10268get().getBuildStage(), this.identityService.mo10268get().getUser(TAG).getEffectiveMarketplace().getCountryCode());
            }
        } else {
            forStageAndMarketPlace = DeviceMasterServiceEndpoint.forStageAndMarketPlace(this.environmentService.mo10268get().getBuildStage(), CountryCode.US);
        }
        String outline75 = GeneratedOutlineSupport1.outline75(HTTPS, forStageAndMarketPlace, DMS_REGISTRATION_URL);
        String cookie = CookieManager.getInstance().getCookie(webEndpoint);
        String csrfFromCookie = getCsrfFromCookie(cookie);
        if (!cookie.contains("csrf=")) {
            cookie = GeneratedOutlineSupport1.outline76("csrf=", csrfFromCookie, "; ", cookie);
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("registrationId", str);
            Response execute = okHttpClient.newCall(new Request.Builder().url(outline75).method("POST", RequestBody.create(MediaType.parse(DEFAULT_MEDIA_TYPE), jSONObject.toString())).addHeader("Accept", "application/json, text/javascript, */*; q=0.01").addHeader("Content-Type", "application/json").addHeader(HttpHeaders.X_REQUESTED_WITH, "com.amazon.dee.app").addHeader("Cookie", cookie).addHeader(HttpRequestConstants.CSRF, csrfFromCookie).build()).execute();
            if (execute.code() >= 200 && execute.code() < 300) {
                authorizationCallback.authorizationSuccess();
            } else {
                authorizationCallback.authorizationFailure(new Exception("ERROR AUTHORIZING LINKCODE"));
            }
        } catch (Exception e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ERROR making doppler request ");
            outline107.append(outline75.toString());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(e.toString());
            Log.e(TAG, outline107.toString());
            authorizationCallback.authorizationFailure(e);
        }
    }
}
