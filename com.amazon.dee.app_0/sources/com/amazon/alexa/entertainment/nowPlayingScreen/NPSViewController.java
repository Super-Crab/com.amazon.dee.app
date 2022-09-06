package com.amazon.alexa.entertainment.nowPlayingScreen;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.amazon.alexa.entertainment.nowPlayingScreen.NPSViewController;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.dee.app.elements.ElementsRouteKeys;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import java.util.UUID;
/* loaded from: classes7.dex */
public class NPSViewController implements ViewController {
    private static final String TAG = "NPSViewController";
    public EventBus eventBus;
    Handler mainHandler = new Handler(Looper.getMainLooper());
    ReactRootView npsView;
    ReactInstanceManager reactInstanceManager;
    Subscriber subscriber;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.entertainment.nowPlayingScreen.NPSViewController$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements Subscriber {
        RoutingService routingService;
        private UUID uuid = UUID.randomUUID();

        AnonymousClass1() {
        }

        public RoutingService getRoutingService() {
            if (this.routingService == null) {
                this.routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
            }
            return this.routingService;
        }

        @Override // com.amazon.alexa.eventbus.api.Subscriber
        public UUID getUUID() {
            return this.uuid;
        }

        public /* synthetic */ void lambda$onMessageReceived$0$NPSViewController$1() {
            this.routingService.navigateBackward();
        }

        @Override // com.amazon.alexa.eventbus.api.Subscriber
        public void onMessageReceived(@NonNull Message message) {
            this.routingService = getRoutingService();
            if (this.routingService.getCurrentRoute().getRoute().getName().equals("now-playing-screen")) {
                NPSViewController.this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.entertainment.nowPlayingScreen.-$$Lambda$NPSViewController$1$2xGmJo2HaEQnBw_XF3vGFgPzBOc
                    @Override // java.lang.Runnable
                    public final void run() {
                        NPSViewController.AnonymousClass1.this.lambda$onMessageReceived$0$NPSViewController$1();
                    }
                });
            }
        }

        @Override // com.amazon.alexa.eventbus.api.Subscriber
        public boolean supportsMessage(@NonNull Message message) {
            return message.getEventType().equals("close::NowPlayingScreen");
        }
    }

    public NPSViewController(ReactInstanceManager reactInstanceManager) {
        this.reactInstanceManager = reactInstanceManager;
    }

    private synchronized EventBus getEventBus() {
        if (this.eventBus == null) {
            this.eventBus = (EventBus) ComponentRegistry.getInstance().get(EventBus.class).get();
        }
        return this.eventBus;
    }

    public Subscriber getSubscriber() {
        if (this.subscriber == null) {
            this.subscriber = new AnonymousClass1();
        }
        return this.subscriber;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return "";
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.npsView = new ReactRootView(viewGroup.getContext());
        return this.npsView;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        this.subscriber = getSubscriber();
        this.eventBus = getEventBus();
        this.eventBus.subscribe(this.subscriber);
        Bundle bundle = new Bundle();
        bundle.putString("fullViewName", "media-components/now-playing-screen");
        bundle.putString(ElementsRouteKeys.THEME, "dark");
        try {
            this.npsView.startReactApplication(this.reactInstanceManager, "NowPlayingScreenComponent", bundle);
        } catch (AssertionError unused) {
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        this.npsView.unmountReactApplication();
        this.eventBus.unsubscribe(getSubscriber().getUUID());
    }
}
