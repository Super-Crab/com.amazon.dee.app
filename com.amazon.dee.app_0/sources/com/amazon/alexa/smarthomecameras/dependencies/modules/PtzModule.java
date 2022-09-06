package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.View;
import android.view.WindowManager;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.directives.DirectiveSender;
import com.amazon.alexa.smarthomecameras.handlers.LiveViewPtzGestureListener;
import com.amazon.alexa.smarthomecameras.handlers.LiveViewPtzListener;
import com.amazon.alexa.smarthomecameras.metrics.PtzMetricsListener;
import com.amazon.alexa.smarthomecameras.metrics.PtzMetricsRecorder;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.ptz.GestureRouter;
import com.amazon.alexa.smarthomecameras.ptz.PhysicalPtzGestureHandler;
import com.amazon.alexa.smarthomecameras.ptz.PtzGestureListener;
import com.amazon.alexa.smarthomecameras.ptz.directives.DataChannelDirectiveSender;
import com.amazon.alexa.smarthomecameras.ptz.directives.PhysicalPtzDirectiveFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import com.amazon.ptz.gestures.listeners.ScalePtzGestureListener;
import com.amazon.ptz.gestures.listeners.SimplePtzGestureListener;
import com.amazon.ptz.metrics.MetricRecorder;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public class PtzModule {
    private final DevicePayload devicePayload;
    private final CameraViewContract.Presenter presenter;
    private final View view;

    public PtzModule(View view, CameraViewContract.Presenter presenter, DevicePayload devicePayload) {
        Preconditions.checkNotNull(view, "PtzView cannot be null");
        Preconditions.checkNotNull(presenter, "CameraViewPresenter cannot be null");
        Preconditions.checkNotNull(devicePayload, "DevicePayload cannot be null");
        this.view = view;
        this.presenter = presenter;
        this.devicePayload = devicePayload;
    }

    @Provides
    @Singleton
    public DisplayMetrics provideDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @GestureHandler.Router
    public GestureHandler provideGestureRouter(DigitalPtzGestureHandler digitalPtzGestureHandler, PhysicalPtzGestureHandler physicalPtzGestureHandler, PtzGestureListener ptzGestureListener) {
        return new GestureRouter(digitalPtzGestureHandler, physicalPtzGestureHandler, ptzGestureListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public DevicePayload providesDevicePayload() {
        return this.devicePayload;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public DigitalPtzGestureHandler providesDigitalPtzGestureHandler(View view, DigitalZoomState digitalZoomState, MetricRecorder metricRecorder) {
        return new DigitalPtzGestureHandler(view, digitalZoomState, metricRecorder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public DigitalZoomState providesDigitalZoomState(View view) {
        return new DigitalZoomState(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public DirectiveSender providesDirectiveSender(Gson gson) {
        return new DataChannelDirectiveSender(gson, this.presenter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public GestureDetector providesGestureDetector(Context context, GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        return new GestureDetector(context, simpleOnGestureListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PtzGestureListener providesLiveViewPtzGestureListener(PtzMetricsListener ptzMetricsListener, DevicePayload devicePayload) {
        return new LiveViewPtzGestureListener(ptzMetricsListener, devicePayload);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PhysicalPtzDirectiveFactory providesPhysicalPtzDirectiveFactory() {
        return new PhysicalPtzDirectiveFactory();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PhysicalPtzGestureHandler providesPhysicalPtzGestureHandler(DigitalZoomState digitalZoomState, DisplayMetrics displayMetrics, PhysicalPtzDirectiveFactory physicalPtzDirectiveFactory, DirectiveSender directiveSender, FeatureServiceV2 featureServiceV2, CamerasMobilyticsService camerasMobilyticsService, DevicePayload devicePayload) {
        return new PhysicalPtzGestureHandler(digitalZoomState, displayMetrics, physicalPtzDirectiveFactory, directiveSender, featureServiceV2, camerasMobilyticsService, devicePayload);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public LiveViewPtzListener providesPtzListener(@GestureHandler.Router GestureHandler gestureHandler, GestureDetector gestureDetector, OldScaleGestureDetector oldScaleGestureDetector, Context context, PtzGestureListener ptzGestureListener) {
        return new LiveViewPtzListener(gestureHandler, gestureDetector, oldScaleGestureDetector, context, ptzGestureListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PtzMetricsListener providesPtzMetricsListener(CamerasMobilyticsService camerasMobilyticsService) {
        return new PtzMetricsListener(camerasMobilyticsService);
    }

    @Provides
    @Singleton
    public MetricRecorder providesPtzMetricsRecorder(CamerasMobilyticsService camerasMobilyticsService) {
        return new PtzMetricsRecorder(camerasMobilyticsService);
    }

    @Provides
    @Singleton
    public View providesPtzView() {
        return this.view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public OldScaleGestureDetector providesScaleGestureDetector(Context context, ScalePtzGestureListener scalePtzGestureListener) {
        return new OldScaleGestureDetector(context, scalePtzGestureListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public ScalePtzGestureListener providesScaleGestureListener(@GestureHandler.Router GestureHandler gestureHandler, MetricRecorder metricRecorder) {
        return new ScalePtzGestureListener(gestureHandler, metricRecorder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public GestureDetector.SimpleOnGestureListener providesSimplePtzGestureListener(@GestureHandler.Router GestureHandler gestureHandler, MetricRecorder metricRecorder) {
        return new SimplePtzGestureListener(gestureHandler, metricRecorder);
    }
}
