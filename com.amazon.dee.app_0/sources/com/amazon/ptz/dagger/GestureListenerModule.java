package com.amazon.ptz.dagger;

import android.content.Context;
import android.view.GestureDetector;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import com.amazon.ptz.gestures.listeners.PtzKeyEventListener;
import com.amazon.ptz.gestures.listeners.PtzListener;
import com.amazon.ptz.gestures.listeners.ScalePtzGestureListener;
import com.amazon.ptz.gestures.listeners.SimplePtzGestureListener;
import com.amazon.ptz.metrics.MetricRecorder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes13.dex */
public class GestureListenerModule {
    @Provides
    @Singleton
    public GestureDetector provideGestureDetector(Context context, SimplePtzGestureListener simplePtzGestureListener) {
        return new GestureDetector(context, simplePtzGestureListener);
    }

    @Provides
    @Singleton
    public PtzKeyEventListener providePtzKeyEventListener(@GestureHandler.Router GestureHandler gestureHandler, MetricRecorder metricRecorder, DigitalZoomState digitalZoomState) {
        return new PtzKeyEventListener(gestureHandler, metricRecorder, digitalZoomState);
    }

    @Provides
    @Singleton
    public PtzListener providePtzListener(@GestureHandler.Router GestureHandler gestureHandler, GestureDetector gestureDetector, OldScaleGestureDetector oldScaleGestureDetector, Context context) {
        return new PtzListener(gestureHandler, gestureDetector, oldScaleGestureDetector, context);
    }

    @Provides
    @Singleton
    public OldScaleGestureDetector provideScaleGestureDetector(Context context, ScalePtzGestureListener scalePtzGestureListener) {
        return new OldScaleGestureDetector(context, scalePtzGestureListener);
    }

    @Provides
    @Singleton
    public ScalePtzGestureListener provideScalePtzGestureListener(@GestureHandler.Router GestureHandler gestureHandler, MetricRecorder metricRecorder) {
        return new ScalePtzGestureListener(gestureHandler, metricRecorder);
    }

    @Provides
    @Singleton
    public SimplePtzGestureListener provideSimplePtzGestureListener(@GestureHandler.Router GestureHandler gestureHandler, MetricRecorder metricRecorder) {
        return new SimplePtzGestureListener(gestureHandler, metricRecorder);
    }
}
