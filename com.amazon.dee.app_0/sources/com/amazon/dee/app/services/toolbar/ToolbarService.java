package com.amazon.dee.app.services.toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.dee.app.elements.ElementsRouteKeys;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class ToolbarService {
    private static final String TAG = Log.tag(ToolbarService.class);
    private final Activity activity;
    private ToolbarDelegate delegate;
    private final EventBus eventBus;
    private final Gson gson;
    private final ReactInstanceManager reactInstanceManager;
    private ReactRootView toolbarView;
    private Subscriber.SubscriberUuid eventBusSubscriptionId = null;
    private final LinkedHashMap<String, ToolbarOptions> toolbarOptions = new LinkedHashMap<>();
    private final PublishSubject<String> toolbarRegistered = PublishSubject.create();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class CloseTransportPayload {
        String fullViewName;

        private CloseTransportPayload() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class OpenTransportPayload {
        String fullViewName;
        ToolbarOptions options;

        private OpenTransportPayload() {
        }
    }

    public ToolbarService(EventBus eventBus, Gson gson, ToolbarDelegate toolbarDelegate, Activity activity, ReactInstanceManager reactInstanceManager) {
        this.eventBus = eventBus;
        this.gson = gson;
        this.delegate = toolbarDelegate;
        this.activity = activity;
        this.reactInstanceManager = reactInstanceManager;
    }

    private void unmountToolbar() {
        ReactRootView reactRootView = this.toolbarView;
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            this.toolbarView = null;
        }
    }

    public void cleanup() {
        Subscriber.SubscriberUuid subscriberUuid = this.eventBusSubscriptionId;
        if (subscriberUuid != null) {
            this.eventBus.unsubscribe(subscriberUuid);
            this.eventBusSubscriptionId = null;
        }
        this.toolbarOptions.clear();
        this.mainHandler.post(new Runnable() { // from class: com.amazon.dee.app.services.toolbar.-$$Lambda$ToolbarService$c3TRVj7I_ryfXGLVU1hTVNDYjYo
            @Override // java.lang.Runnable
            public final void run() {
                ToolbarService.this.lambda$cleanup$4$ToolbarService();
            }
        });
    }

    @VisibleForTesting
    ReactRootView createToolbarView() {
        return new ReactRootView(this.activity);
    }

    public void destroy() {
        cleanup();
        this.delegate = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, ToolbarOptions> getToolbarOptions() {
        return Collections.unmodifiableMap(this.toolbarOptions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hideToolbar() {
        ToolbarDelegate toolbarDelegate = this.delegate;
        if (toolbarDelegate == null) {
            return;
        }
        toolbarDelegate.setToolbarVisible(false);
    }

    public /* synthetic */ void lambda$cleanup$4$ToolbarService() {
        unmountToolbar();
        hideToolbar();
        ToolbarDelegate toolbarDelegate = this.delegate;
        if (toolbarDelegate != null) {
            toolbarDelegate.unmountToolbar();
        }
    }

    public /* synthetic */ void lambda$showToolbar$3$ToolbarService(String str, Map map) {
        ToolbarDelegate toolbarDelegate = this.delegate;
        if (toolbarDelegate != null) {
            toolbarDelegate.mountToolbar(mountToolbar(str, map));
        }
    }

    public /* synthetic */ void lambda$start$2$ToolbarService(Message message) {
        onToolbarRendered();
    }

    View mountToolbar(String str, Map<String, String> map) {
        if (this.toolbarView == null) {
            this.toolbarView = createToolbarView();
        }
        Bundle bundle = new Bundle();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
        }
        Bundle outline12 = GeneratedOutlineSupport1.outline12("fullViewName", str, ElementsRouteKeys.THEME, "dark");
        outline12.putParcelable("options", bundle);
        try {
            this.toolbarView.startReactApplication(this.reactInstanceManager, "ElementsToolbarComponent", outline12);
        } catch (AssertionError e) {
            new Object[1][0] = e;
        }
        return this.toolbarView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onCloseToolbar */
    public void lambda$start$1$ToolbarService(Message message) {
        CloseTransportPayload closeTransportPayload = (CloseTransportPayload) this.gson.fromJson(message.getPayloadAsString(), (Class<Object>) CloseTransportPayload.class);
        this.toolbarOptions.remove(closeTransportPayload.fullViewName);
        this.toolbarRegistered.onNext(closeTransportPayload.fullViewName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onOpenToolbar */
    public void lambda$start$0$ToolbarService(Message message) {
        OpenTransportPayload openTransportPayload = (OpenTransportPayload) this.gson.fromJson(message.getPayloadAsString(), (Class<Object>) OpenTransportPayload.class);
        this.toolbarOptions.put(openTransportPayload.fullViewName, openTransportPayload.options);
        this.toolbarRegistered.onNext(openTransportPayload.fullViewName);
    }

    public Observable<String> onToolbarRegistered() {
        return this.toolbarRegistered.hide();
    }

    void onToolbarRendered() {
        ToolbarDelegate toolbarDelegate = this.delegate;
        if (toolbarDelegate == null) {
            return;
        }
        toolbarDelegate.setToolbarVisible(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showToolbar(final String str, final Map map) {
        if (this.delegate == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.amazon.dee.app.services.toolbar.-$$Lambda$ToolbarService$f_u6_XaDk9WVxc0g71baQY7Pqfw
            @Override // java.lang.Runnable
            public final void run() {
                ToolbarService.this.lambda$showToolbar$3$ToolbarService(str, map);
            }
        });
    }

    public void start() {
        if (this.eventBusSubscriptionId == null) {
            MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
            this.eventBusSubscriptionId = subscriber.getSubscriberUuid();
            subscriber.subscribeFilter(new EventTypeMessageFilter("toolbar:open"), new MessageHandler() { // from class: com.amazon.dee.app.services.toolbar.-$$Lambda$ToolbarService$1D6k0EgFVJfM6LS753ilRJwe7gw
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    ToolbarService.this.lambda$start$0$ToolbarService(message);
                }
            });
            subscriber.subscribeFilter(new EventTypeMessageFilter("toolbar:close"), new MessageHandler() { // from class: com.amazon.dee.app.services.toolbar.-$$Lambda$ToolbarService$hvMxXPc_Bi2qs8WXAZDvp9F2Nd4
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    ToolbarService.this.lambda$start$1$ToolbarService(message);
                }
            });
            subscriber.subscribeFilter(new EventTypeMessageFilter("toolbar:rendered"), new MessageHandler() { // from class: com.amazon.dee.app.services.toolbar.-$$Lambda$ToolbarService$yGKWKK1efmdQwSf_AwRZ0N82pQg
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    ToolbarService.this.lambda$start$2$ToolbarService(message);
                }
            });
        }
    }
}
