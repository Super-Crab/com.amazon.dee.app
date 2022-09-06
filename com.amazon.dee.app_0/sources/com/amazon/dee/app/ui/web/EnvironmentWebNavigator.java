package com.amazon.dee.app.ui.web;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.util.WebUtils;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class EnvironmentWebNavigator implements WebNavigator {
    static final String TAG = "EnvironmentWebNavigator";
    EnvironmentService environmentService;
    EventBus eventBus;
    MultiFilterSubscriber eventBusSubscriber;
    IdentityService identityService;
    MultiFilterSubscriber.FilterUuid identitySubscription;
    JavaScriptInjector javaScriptInjector;
    String uri;
    WebViewDelegate webViewDelegate;

    public EnvironmentWebNavigator(WebViewDelegate webViewDelegate, JavaScriptInjector javaScriptInjector, EnvironmentService environmentService, IdentityService identityService, EventBus eventBus) {
        this.webViewDelegate = webViewDelegate;
        this.javaScriptInjector = javaScriptInjector;
        this.environmentService = environmentService;
        this.identityService = identityService;
        this.eventBus = eventBus;
        this.eventBusSubscriber = eventBus.getSubscriber();
    }

    @Override // com.amazon.dee.app.ui.web.WebNavigator
    @Nullable
    public String getUri() {
        return this.uri;
    }

    public void initialize() {
        this.identitySubscription = this.eventBusSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$EnvironmentWebNavigator$Dx6XyMsEhmeK6U6s4KdlZzH53eU
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EnvironmentWebNavigator.this.lambda$initialize$0$EnvironmentWebNavigator(message);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$0$EnvironmentWebNavigator(Message message) {
        if (this.identityService.getUser(TAG) == null) {
            resetUrl();
        } else if (WebUtils.ABOUT_BLANK_PAGE.equals(this.uri)) {
        } else {
            loadUri(this.uri);
        }
    }

    void loadUri(String str) {
        if (!TextUtils.isEmpty(str)) {
            UserIdentity user = this.identityService.getUser(TAG);
            String webIndex = this.environmentService.getWebIndex();
            if (user != null && user.getFeatures().contains("TCOMM_JS_ANDROID")) {
                webIndex = GeneratedOutlineSupport1.outline72(webIndex, "?tcomm=disabled");
            }
            this.webViewDelegate.loadUrl(GeneratedOutlineSupport1.outline75(webIndex, MqttTopic.MULTI_LEVEL_WILDCARD, str));
        }
    }

    @Override // com.amazon.dee.app.ui.web.WebNavigator
    public void navigate(@NonNull String str) {
        if (!TextUtils.equals(this.uri, str)) {
            this.uri = str;
            loadUri(str);
        }
    }

    public void notifyUriChanged(@NonNull String str) {
        this.uri = str;
    }

    public void release() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.identitySubscription;
        if (filterUuid != null) {
            this.eventBusSubscriber.unsubscribeFilter(filterUuid);
            this.identitySubscription = null;
        }
        resetUrl();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetUrl() {
        this.webViewDelegate.loadUrl(WebUtils.ABOUT_BLANK_PAGE);
        this.webViewDelegate.clearCache();
        this.webViewDelegate.clearHistory();
    }
}
