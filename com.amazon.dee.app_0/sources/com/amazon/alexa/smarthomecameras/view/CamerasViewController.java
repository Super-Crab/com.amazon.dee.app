package com.amazon.alexa.smarthomecameras.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.smarthomecameras.R;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent;
import com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponentProvider;
import com.amazon.alexa.smarthomecameras.dependencies.components.CameraViewScope;
import com.amazon.alexa.smarthomecameras.dependencies.components.DaggerCameraComponent;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PortraitViewModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingServiceModule;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DeviceCapabilityList;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.google.gson.Gson;
import dagger.Component;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class CamerasViewController implements ViewController {
    private static final String TAG = "CamerasViewController";
    @Inject
    AppLifecycleOwner appLifecycleOwner;
    @Inject
    AudioManager audioManager;
    private final CameraLabel cameraLabel;
    @Inject
    CameraSessionManager cameraSessionManager;
    private Context context;
    private DevicePayload devicePayload;
    private EntityId entityId;
    private final EventBus eventBus;
    private FeatureServiceV2 featureServiceV2;
    private final Gson gson = new Gson();
    private CamerasMobilyticsService metricsService;
    @Inject
    PortraitCameraView portraitCameraView;
    @Inject
    PtzListenerFactory ptzListenerFactory;
    private RoutingService routingService;
    private MultiFilterSubscriber subscriber;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(dependencies = {CameraComponent.class}, modules = {CameraModule.class, PortraitViewModule.class})
    @CameraViewScope
    /* loaded from: classes10.dex */
    public interface Injector {
        void inject(CamerasViewController camerasViewController);
    }

    public CamerasViewController(Context context, RouteContext routeContext, RoutingService routingService, CamerasMobilyticsService camerasMobilyticsService, FeatureServiceV2 featureServiceV2, EventBus eventBus) {
        this.context = context;
        this.routingService = routingService;
        this.entityId = EntityId.create(routeContext.getString("entityId"));
        this.cameraLabel = CameraLabel.create(routeContext.getString(CamerasRouteParameter.DISPLAY_NAME));
        this.metricsService = camerasMobilyticsService;
        this.eventBus = eventBus;
        this.featureServiceV2 = featureServiceV2;
        this.devicePayload = new DevicePayload(routeContext.getString(CamerasRouteParameter.MANUFACTURER_NAME), (DeviceCapabilityList) this.gson.fromJson(routeContext.getString(CamerasRouteParameter.CAPABILITIES), (Class<Object>) DeviceCapabilityList.class));
    }

    private void handleAppBackgrounded() {
        this.appLifecycleOwner.onAppBackground();
        this.portraitCameraView.releaseCameraView();
        this.cameraSessionManager.disconnectSession(this.entityId);
    }

    private void handleAppForegrounded() {
        this.appLifecycleOwner.onAppForeground();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$setupLifecycleEventBus$0(Message message) {
        return LifecycleEvent.APPLICATION_DID_FOREGROUND.name.equals(message.getEventType()) || LifecycleEvent.APPLICATION_DID_BACKGROUND.name.equals(message.getEventType());
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @SuppressLint({"ResourceType"})
    @MenuRes
    public int getMenuResourceId() {
        return R.layout.menu;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(Context context) {
        return this.cameraLabel.getValue().toUpperCase();
    }

    @VisibleForTesting
    void injectDependencies() {
        CameraComponent build = DaggerCameraComponent.builder().contextModule(new ContextModule(this.context)).routingServiceModule(new RoutingServiceModule(this.routingService)).build();
        CameraComponentProvider.setCameraComponent(build);
        DaggerCamerasViewController_Injector.builder().cameraComponent(build).cameraModule(new CameraModule(this.entityId, this.cameraLabel, this.devicePayload)).build().inject(this);
    }

    public /* synthetic */ void lambda$setupLifecycleEventBus$1$CamerasViewController(Message message) {
        if (LifecycleEvent.APPLICATION_DID_BACKGROUND.name.equals(message.getEventType())) {
            handleAppBackgrounded();
        } else if (!LifecycleEvent.APPLICATION_DID_FOREGROUND.name.equals(message.getEventType())) {
        } else {
            handleAppForegrounded();
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        Log.i(TAG, "onCreateView");
        this.portraitCameraView.initialize();
        return this.portraitCameraView;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(Context context) {
        Log.i(TAG, "onCreate");
        injectDependencies();
        setupLifecycleEventBus();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDestroy(Context context) {
        Log.i(TAG, "onDestroy");
        this.audioManager.teardown();
        this.cameraSessionManager.teardown();
        CameraComponentProvider.reset();
        this.appLifecycleOwner.teardown();
        MultiFilterSubscriber multiFilterSubscriber = this.subscriber;
        if (multiFilterSubscriber != null) {
            this.eventBus.unsubscribe(multiFilterSubscriber);
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDestroyView(View view) {
        Log.i(TAG, "onDestroyView");
        this.portraitCameraView.releaseCameraView();
        this.cameraSessionManager.disconnectSession(this.entityId);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_item_settings) {
            this.metricsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.SWITCH_TO_SETTINGS);
            this.routingService.route(RouteName.SMART_HOME_DEVICE_CONTROL).with("entityId", this.entityId.getValue()).navigate();
            return true;
        }
        return false;
    }

    @VisibleForTesting
    void setupLifecycleEventBus() {
        this.subscriber = this.eventBus.getSubscriber();
        this.subscriber.subscribeFilter($$Lambda$CamerasViewController$eD5ECuieA08zWJTWIqpetGJ7_I.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.smarthomecameras.view.-$$Lambda$CamerasViewController$l9h_9U6n8HsPSZaUTgJkwUYBfvk
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                CamerasViewController.this.lambda$setupLifecycleEventBus$1$CamerasViewController(message);
            }
        });
    }
}
