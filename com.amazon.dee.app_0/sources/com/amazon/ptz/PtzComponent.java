package com.amazon.ptz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.ptz.dagger.GestureHandlerModule;
import com.amazon.ptz.dagger.GestureListenerModule;
import com.amazon.ptz.dagger.HandlerModule;
import com.amazon.ptz.dagger.PhysicalPtzCacheModule;
import com.amazon.ptz.dagger.PhysicalPtzResponseHandlerModule;
import com.amazon.ptz.dagger.SerializationModule;
import com.amazon.ptz.dagger.UtilModule;
import com.amazon.ptz.gestures.listeners.PtzKeyEventListener;
import com.amazon.ptz.gestures.listeners.PtzListener;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.communication.PhysicalPtzDirectiveSender;
import com.amazon.ptz.physical.communication.PhysicalPtzResponseHandler;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import dagger.BindsInstance;
import dagger.Component;
import java.util.List;
import javax.annotation.Nonnull;
import javax.inject.Singleton;
@Component(modules = {HandlerModule.class, GestureHandlerModule.class, GestureListenerModule.class, SerializationModule.class, PhysicalPtzResponseHandlerModule.class, PhysicalPtzCacheModule.class, UtilModule.class})
@Singleton
/* loaded from: classes13.dex */
public interface PtzComponent {

    @Component.Builder
    /* loaded from: classes13.dex */
    public interface Builder {
        PtzComponent build();

        @BindsInstance
        /* renamed from: cameraPtzInstances */
        Builder mo4454cameraPtzInstances(@Nonnull List<CameraPtzInstance> list);

        @BindsInstance
        /* renamed from: context */
        Builder mo4455context(@Nonnull Context context);

        @BindsInstance
        /* renamed from: metricRecorder */
        Builder mo4456metricRecorder(@Nonnull MetricRecorder metricRecorder);

        @BindsInstance
        /* renamed from: physicalPtzDirectiveSender */
        Builder mo4457physicalPtzDirectiveSender(@Nonnull PhysicalPtzDirectiveSender physicalPtzDirectiveSender);

        @BindsInstance
        /* renamed from: view */
        Builder mo4458view(@Nonnull View view);

        @BindsInstance
        /* renamed from: viewGroup */
        Builder mo4459viewGroup(@Nonnull ViewGroup viewGroup);
    }

    PtzKeyEventListener getPtzKeyEventListener();

    PtzListener getPtzListener();

    PhysicalPtzResponseHandler getResponseHandler();
}
