package com.amazon.ptz.dagger;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.handlers.GestureRouter;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.PhysicalPtzGestureHandler;
import com.amazon.ptz.physical.communication.PhysicalPtzCommandCache;
import com.amazon.ptz.physical.communication.PhysicalPtzDirectiveSender;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.inject.Singleton;
@Module
/* loaded from: classes13.dex */
public class GestureHandlerModule {
    @Provides
    @Singleton
    public DigitalPtzGestureHandler provideDigitalPtzGestureHandler(View view, DigitalZoomState digitalZoomState, MetricRecorder metricRecorder) {
        return new DigitalPtzGestureHandler(view, digitalZoomState, metricRecorder);
    }

    @Provides
    @Singleton
    public DigitalZoomState provideDigitalZoomState(View view) {
        return new DigitalZoomState(view);
    }

    @Provides
    @Singleton
    public DisplayMetrics provideDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    @Provides
    @Singleton
    @GestureHandler.Router
    public GestureHandler provideGestureRouter(DigitalPtzGestureHandler digitalPtzGestureHandler, PhysicalPtzGestureHandler physicalPtzGestureHandler) {
        return new GestureRouter(digitalPtzGestureHandler, physicalPtzGestureHandler);
    }

    @Provides
    @Singleton
    public PhysicalPtzGestureHandler providePhysicalPtzGestureHandler(PhysicalPtzDirectiveSender physicalPtzDirectiveSender, List<CameraPtzInstance> list, DigitalZoomState digitalZoomState, PhysicalPtzCommandCache physicalPtzCommandCache, DisplayMetrics displayMetrics, RcSerializer rcSerializer, MetricRecorder metricRecorder) {
        return new PhysicalPtzGestureHandler(physicalPtzDirectiveSender, list, digitalZoomState, physicalPtzCommandCache, displayMetrics.heightPixels, displayMetrics.widthPixels, rcSerializer, metricRecorder);
    }
}
