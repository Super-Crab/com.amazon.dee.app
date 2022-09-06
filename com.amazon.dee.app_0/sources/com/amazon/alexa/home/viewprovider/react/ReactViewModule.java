package com.amazon.alexa.home.viewprovider.react;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.elements.api.BridgeStatusServiceConstants;
import com.amazon.alexa.elements.api.InitializationPhase;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import java.util.UUID;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class ReactViewModule implements ViewModule {
    private static final String TAG = "ReactViewModule";
    private final BridgeStatusService bridgeStatusService;
    private final Context context;
    private final EventBus eventBus;
    private Subscriber initializationPhaseSubscriber;
    private final String module;
    private final JSONObject payload;
    private final ReactInstanceManager reactInstanceManager;
    private ReactRootView view;

    public ReactViewModule(String str, @NonNull JSONObject jSONObject, Context context, ReactInstanceManager reactInstanceManager, @NonNull BridgeStatusService bridgeStatusService, @NonNull EventBus eventBus) {
        this.module = str;
        this.payload = jSONObject;
        this.context = context;
        this.reactInstanceManager = reactInstanceManager;
        this.bridgeStatusService = bridgeStatusService;
        this.eventBus = eventBus;
    }

    private Subscriber subscribeToBridgeStatusServiceInitializationPhase(final Runnable runnable) {
        Subscriber subscriber = new Subscriber() { // from class: com.amazon.alexa.home.viewprovider.react.ReactViewModule.1
            @Override // com.amazon.alexa.eventbus.api.Subscriber
            public UUID getUUID() {
                return UUID.randomUUID();
            }

            @Override // com.amazon.alexa.eventbus.api.Subscriber
            public void onMessageReceived(@NonNull Message message) {
                if (InitializationPhase.fromString(message.getPayloadAsString()).compareTo(InitializationPhase.LOAD_ON_START) >= 0) {
                    runnable.run();
                }
            }

            @Override // com.amazon.alexa.eventbus.api.Subscriber
            public boolean supportsMessage(@NonNull Message message) {
                return BridgeStatusServiceConstants.INITIALIZATION_PHASE_EVENT_TYPE.equals(message.getEventType());
            }
        };
        this.eventBus.subscribe(subscriber);
        return subscriber;
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public void cleanUp() {
        if (this.view == null) {
            return;
        }
        this.bridgeStatusService.registerListener(new Runnable() { // from class: com.amazon.alexa.home.viewprovider.react.-$$Lambda$ReactViewModule$XbXVjNkwuvCrMZN3kbabyDcLun0
            @Override // java.lang.Runnable
            public final void run() {
                ReactViewModule.this.lambda$cleanUp$3$ReactViewModule();
            }
        });
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    @NonNull
    public View getView() {
        ReactRootView reactRootView = this.view;
        if (reactRootView != null) {
            return reactRootView;
        }
        this.view = new ReactRootView(this.context);
        final Bundle bundle = new Bundle();
        bundle.putString(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD, this.payload.toString());
        Runnable runnable = new Runnable() { // from class: com.amazon.alexa.home.viewprovider.react.-$$Lambda$ReactViewModule$z9qEsQhhwvSh4xP-C5CpcOp5SkU
            @Override // java.lang.Runnable
            public final void run() {
                ReactViewModule.this.lambda$getView$1$ReactViewModule(bundle);
            }
        };
        if (this.bridgeStatusService.getCurrentPhase().compareTo(InitializationPhase.LOAD_ON_START) >= 0) {
            runnable.run();
        } else {
            this.initializationPhaseSubscriber = subscribeToBridgeStatusServiceInitializationPhase(runnable);
        }
        return this.view;
    }

    public /* synthetic */ void lambda$cleanUp$3$ReactViewModule() {
        Context context = this.context;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.amazon.alexa.home.viewprovider.react.-$$Lambda$ReactViewModule$H1zL7dm-g0LOVztjRo3Ny4iBgwM
                @Override // java.lang.Runnable
                public final void run() {
                    ReactViewModule.this.lambda$null$2$ReactViewModule();
                }
            });
        }
    }

    public /* synthetic */ void lambda$getView$1$ReactViewModule(final Bundle bundle) {
        Context context = this.context;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.amazon.alexa.home.viewprovider.react.-$$Lambda$ReactViewModule$TSxQEiIDdqqluk9qRAwUBNZolv8
                @Override // java.lang.Runnable
                public final void run() {
                    ReactViewModule.this.lambda$null$0$ReactViewModule(bundle);
                }
            });
        }
        Subscriber subscriber = this.initializationPhaseSubscriber;
        if (subscriber != null) {
            this.eventBus.unsubscribe(subscriber);
        }
    }

    public /* synthetic */ void lambda$null$0$ReactViewModule(Bundle bundle) {
        try {
            this.view.startReactApplication(this.reactInstanceManager, this.module, bundle);
        } catch (AssertionError e) {
            Log.e(TAG, "AssertionError", e);
        }
    }

    public /* synthetic */ void lambda$null$2$ReactViewModule() {
        this.view.unmountReactApplication();
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public void prepare() {
    }
}
