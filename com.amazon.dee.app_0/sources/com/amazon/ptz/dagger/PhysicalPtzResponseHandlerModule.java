package com.amazon.ptz.dagger;

import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.communication.PhysicalPtzCommandCache;
import com.amazon.ptz.physical.communication.PhysicalPtzResponseHandler;
import com.amazon.ptz.physical.communication.responses.handlers.ChangeReportHandler;
import com.amazon.ptz.physical.communication.responses.handlers.ErrorResponseHandler;
import com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler;
import com.amazon.ptz.physical.communication.responses.handlers.SafetyErrorResponseHandler;
import com.google.common.collect.ImmutableSet;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import java.util.Set;
import javax.inject.Singleton;
import org.greenrobot.eventbus.EventBus;
@Module
/* loaded from: classes13.dex */
public class PhysicalPtzResponseHandlerModule {
    @Provides
    @ElementsIntoSet
    public Set<ResponseHandler> getEventHandlers(ErrorResponseHandler errorResponseHandler, SafetyErrorResponseHandler safetyErrorResponseHandler, ChangeReportHandler changeReportHandler) {
        return ImmutableSet.of((ChangeReportHandler) errorResponseHandler, (ChangeReportHandler) safetyErrorResponseHandler, changeReportHandler);
    }

    @Provides
    @Singleton
    public ChangeReportHandler provideChangeReportHandler(RcSerializer rcSerializer, MetricRecorder metricRecorder) {
        return new ChangeReportHandler(rcSerializer, metricRecorder);
    }

    @Provides
    @Singleton
    public ErrorResponseHandler provideErrorResponseHandler(EventBus eventBus, RcSerializer rcSerializer, MetricRecorder metricRecorder) {
        return new ErrorResponseHandler(eventBus, rcSerializer, metricRecorder);
    }

    @Provides
    @Singleton
    public PhysicalPtzResponseHandler providePhysicalPtzResponseHandler(RcSerializer rcSerializer, PhysicalPtzCommandCache physicalPtzCommandCache, Set<ResponseHandler> set) {
        return new PhysicalPtzResponseHandler(rcSerializer, physicalPtzCommandCache, set);
    }

    @Provides
    @Singleton
    public SafetyErrorResponseHandler provideSafetyErrorResponseHandler(EventBus eventBus, RcSerializer rcSerializer, MetricRecorder metricRecorder) {
        return new SafetyErrorResponseHandler(eventBus, rcSerializer, metricRecorder);
    }
}
