package com.amazon.ptz;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.alexa.directive.AlexaDirective;
import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.ptz.PtzComponent;
import com.amazon.ptz.dagger.GestureHandlerModule;
import com.amazon.ptz.dagger.GestureHandlerModule_ProvideDigitalPtzGestureHandlerFactory;
import com.amazon.ptz.dagger.GestureHandlerModule_ProvideDigitalZoomStateFactory;
import com.amazon.ptz.dagger.GestureHandlerModule_ProvideDisplayMetricsFactory;
import com.amazon.ptz.dagger.GestureHandlerModule_ProvideGestureRouterFactory;
import com.amazon.ptz.dagger.GestureHandlerModule_ProvidePhysicalPtzGestureHandlerFactory;
import com.amazon.ptz.dagger.GestureListenerModule;
import com.amazon.ptz.dagger.GestureListenerModule_ProvideGestureDetectorFactory;
import com.amazon.ptz.dagger.GestureListenerModule_ProvidePtzKeyEventListenerFactory;
import com.amazon.ptz.dagger.GestureListenerModule_ProvidePtzListenerFactory;
import com.amazon.ptz.dagger.GestureListenerModule_ProvideScaleGestureDetectorFactory;
import com.amazon.ptz.dagger.GestureListenerModule_ProvideScalePtzGestureListenerFactory;
import com.amazon.ptz.dagger.GestureListenerModule_ProvideSimplePtzGestureListenerFactory;
import com.amazon.ptz.dagger.HandlerModule;
import com.amazon.ptz.dagger.HandlerModule_ProvideMainHandlerFactory;
import com.amazon.ptz.dagger.PhysicalPtzCacheModule;
import com.amazon.ptz.dagger.PhysicalPtzCacheModule_ProvidePhysicalPtzCommandCacheFactory;
import com.amazon.ptz.dagger.PhysicalPtzCacheModule_ProvidePtzDirectiveCacheFactory;
import com.amazon.ptz.dagger.PhysicalPtzResponseHandlerModule;
import com.amazon.ptz.dagger.PhysicalPtzResponseHandlerModule_GetEventHandlersFactory;
import com.amazon.ptz.dagger.PhysicalPtzResponseHandlerModule_ProvideChangeReportHandlerFactory;
import com.amazon.ptz.dagger.PhysicalPtzResponseHandlerModule_ProvideErrorResponseHandlerFactory;
import com.amazon.ptz.dagger.PhysicalPtzResponseHandlerModule_ProvidePhysicalPtzResponseHandlerFactory;
import com.amazon.ptz.dagger.PhysicalPtzResponseHandlerModule_ProvideSafetyErrorResponseHandlerFactory;
import com.amazon.ptz.dagger.SerializationModule;
import com.amazon.ptz.dagger.SerializationModule_ProvideRcSerializerFactory;
import com.amazon.ptz.dagger.UtilModule;
import com.amazon.ptz.dagger.UtilModule_ProvideEventBusFactory;
import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import com.amazon.ptz.gestures.listeners.PtzKeyEventListener;
import com.amazon.ptz.gestures.listeners.PtzListener;
import com.amazon.ptz.gestures.listeners.ScalePtzGestureListener;
import com.amazon.ptz.gestures.listeners.SimplePtzGestureListener;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.PhysicalPtzGestureHandler;
import com.amazon.ptz.physical.communication.PhysicalPtzCommandCache;
import com.amazon.ptz.physical.communication.PhysicalPtzDirectiveSender;
import com.amazon.ptz.physical.communication.PhysicalPtzResponseHandler;
import com.amazon.ptz.physical.communication.responses.handlers.ChangeReportHandler;
import com.amazon.ptz.physical.communication.responses.handlers.ErrorResponseHandler;
import com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler;
import com.amazon.ptz.physical.communication.responses.handlers.SafetyErrorResponseHandler;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import com.google.common.cache.Cache;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import dagger.internal.SetFactory;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes13.dex */
public final class DaggerPtzComponent implements PtzComponent {
    private Provider<List<CameraPtzInstance>> cameraPtzInstancesProvider;
    private Provider<Context> contextProvider;
    private PhysicalPtzResponseHandlerModule_GetEventHandlersFactory getEventHandlersProvider;
    private Provider<MetricRecorder> metricRecorderProvider;
    private Provider<PhysicalPtzDirectiveSender> physicalPtzDirectiveSenderProvider;
    private Provider<ChangeReportHandler> provideChangeReportHandlerProvider;
    private Provider<DigitalPtzGestureHandler> provideDigitalPtzGestureHandlerProvider;
    private Provider<DigitalZoomState> provideDigitalZoomStateProvider;
    private Provider<DisplayMetrics> provideDisplayMetricsProvider;
    private Provider<ErrorResponseHandler> provideErrorResponseHandlerProvider;
    private Provider<EventBus> provideEventBusProvider;
    private Provider<GestureDetector> provideGestureDetectorProvider;
    private Provider<GestureHandler> provideGestureRouterProvider;
    private HandlerModule_ProvideMainHandlerFactory provideMainHandlerProvider;
    private Provider<PhysicalPtzCommandCache> providePhysicalPtzCommandCacheProvider;
    private Provider<PhysicalPtzGestureHandler> providePhysicalPtzGestureHandlerProvider;
    private Provider<PhysicalPtzResponseHandler> providePhysicalPtzResponseHandlerProvider;
    private Provider<Cache<String, AlexaDirective>> providePtzDirectiveCacheProvider;
    private Provider<PtzKeyEventListener> providePtzKeyEventListenerProvider;
    private Provider<PtzListener> providePtzListenerProvider;
    private Provider<RcSerializer> provideRcSerializerProvider;
    private Provider<SafetyErrorResponseHandler> provideSafetyErrorResponseHandlerProvider;
    private Provider<OldScaleGestureDetector> provideScaleGestureDetectorProvider;
    private Provider<ScalePtzGestureListener> provideScalePtzGestureListenerProvider;
    private Provider<SimplePtzGestureListener> provideSimplePtzGestureListenerProvider;
    private Provider<Set<ResponseHandler>> setOfResponseHandlerProvider;
    private Provider<View> viewProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class Builder implements PtzComponent.Builder {
        private List<CameraPtzInstance> cameraPtzInstances;
        private Context context;
        private GestureHandlerModule gestureHandlerModule;
        private GestureListenerModule gestureListenerModule;
        private HandlerModule handlerModule;
        private MetricRecorder metricRecorder;
        private PhysicalPtzCacheModule physicalPtzCacheModule;
        private PhysicalPtzDirectiveSender physicalPtzDirectiveSender;
        private PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule;
        private SerializationModule serializationModule;
        private UtilModule utilModule;
        private View view;
        private ViewGroup viewGroup;

        private Builder() {
        }

        @Override // com.amazon.ptz.PtzComponent.Builder
        public PtzComponent build() {
            if (this.gestureListenerModule == null) {
                this.gestureListenerModule = new GestureListenerModule();
            }
            if (this.gestureHandlerModule == null) {
                this.gestureHandlerModule = new GestureHandlerModule();
            }
            if (this.physicalPtzCacheModule == null) {
                this.physicalPtzCacheModule = new PhysicalPtzCacheModule();
            }
            if (this.handlerModule == null) {
                this.handlerModule = new HandlerModule();
            }
            if (this.utilModule == null) {
                this.utilModule = new UtilModule();
            }
            if (this.serializationModule == null) {
                this.serializationModule = new SerializationModule();
            }
            if (this.physicalPtzResponseHandlerModule == null) {
                this.physicalPtzResponseHandlerModule = new PhysicalPtzResponseHandlerModule();
            }
            Preconditions.checkBuilderRequirement(this.context, Context.class);
            Preconditions.checkBuilderRequirement(this.viewGroup, ViewGroup.class);
            Preconditions.checkBuilderRequirement(this.view, View.class);
            Preconditions.checkBuilderRequirement(this.cameraPtzInstances, List.class);
            Preconditions.checkBuilderRequirement(this.physicalPtzDirectiveSender, PhysicalPtzDirectiveSender.class);
            Preconditions.checkBuilderRequirement(this.metricRecorder, MetricRecorder.class);
            return new DaggerPtzComponent(this);
        }

        @Override // com.amazon.ptz.PtzComponent.Builder
        /* renamed from: cameraPtzInstances  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ PtzComponent.Builder mo4454cameraPtzInstances(List list) {
            return mo4454cameraPtzInstances((List<CameraPtzInstance>) list);
        }

        @Override // com.amazon.ptz.PtzComponent.Builder
        /* renamed from: cameraPtzInstances */
        public Builder mo4454cameraPtzInstances(List<CameraPtzInstance> list) {
            this.cameraPtzInstances = (List) Preconditions.checkNotNull(list);
            return this;
        }

        @Override // com.amazon.ptz.PtzComponent.Builder
        /* renamed from: context */
        public Builder mo4455context(Context context) {
            this.context = (Context) Preconditions.checkNotNull(context);
            return this;
        }

        @Override // com.amazon.ptz.PtzComponent.Builder
        /* renamed from: metricRecorder */
        public Builder mo4456metricRecorder(MetricRecorder metricRecorder) {
            this.metricRecorder = (MetricRecorder) Preconditions.checkNotNull(metricRecorder);
            return this;
        }

        @Override // com.amazon.ptz.PtzComponent.Builder
        /* renamed from: physicalPtzDirectiveSender */
        public Builder mo4457physicalPtzDirectiveSender(PhysicalPtzDirectiveSender physicalPtzDirectiveSender) {
            this.physicalPtzDirectiveSender = (PhysicalPtzDirectiveSender) Preconditions.checkNotNull(physicalPtzDirectiveSender);
            return this;
        }

        @Override // com.amazon.ptz.PtzComponent.Builder
        /* renamed from: view */
        public Builder mo4458view(View view) {
            this.view = (View) Preconditions.checkNotNull(view);
            return this;
        }

        @Override // com.amazon.ptz.PtzComponent.Builder
        /* renamed from: viewGroup */
        public Builder mo4459viewGroup(ViewGroup viewGroup) {
            this.viewGroup = (ViewGroup) Preconditions.checkNotNull(viewGroup);
            return this;
        }
    }

    public static PtzComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.viewProvider = InstanceFactory.create(builder.view);
        this.provideDigitalZoomStateProvider = DoubleCheck.provider(GestureHandlerModule_ProvideDigitalZoomStateFactory.create(builder.gestureHandlerModule, this.viewProvider));
        this.metricRecorderProvider = InstanceFactory.create(builder.metricRecorder);
        this.provideDigitalPtzGestureHandlerProvider = DoubleCheck.provider(GestureHandlerModule_ProvideDigitalPtzGestureHandlerFactory.create(builder.gestureHandlerModule, this.viewProvider, this.provideDigitalZoomStateProvider, this.metricRecorderProvider));
        this.physicalPtzDirectiveSenderProvider = InstanceFactory.create(builder.physicalPtzDirectiveSender);
        this.cameraPtzInstancesProvider = InstanceFactory.create(builder.cameraPtzInstances);
        this.providePtzDirectiveCacheProvider = DoubleCheck.provider(PhysicalPtzCacheModule_ProvidePtzDirectiveCacheFactory.create(builder.physicalPtzCacheModule));
        this.provideMainHandlerProvider = HandlerModule_ProvideMainHandlerFactory.create(builder.handlerModule);
        this.provideEventBusProvider = DoubleCheck.provider(UtilModule_ProvideEventBusFactory.create(builder.utilModule));
        this.providePhysicalPtzCommandCacheProvider = DoubleCheck.provider(PhysicalPtzCacheModule_ProvidePhysicalPtzCommandCacheFactory.create(builder.physicalPtzCacheModule, this.providePtzDirectiveCacheProvider, this.provideMainHandlerProvider, this.provideEventBusProvider));
        this.contextProvider = InstanceFactory.create(builder.context);
        this.provideDisplayMetricsProvider = DoubleCheck.provider(GestureHandlerModule_ProvideDisplayMetricsFactory.create(builder.gestureHandlerModule, this.contextProvider));
        this.provideRcSerializerProvider = DoubleCheck.provider(SerializationModule_ProvideRcSerializerFactory.create(builder.serializationModule));
        this.providePhysicalPtzGestureHandlerProvider = DoubleCheck.provider(GestureHandlerModule_ProvidePhysicalPtzGestureHandlerFactory.create(builder.gestureHandlerModule, this.physicalPtzDirectiveSenderProvider, this.cameraPtzInstancesProvider, this.provideDigitalZoomStateProvider, this.providePhysicalPtzCommandCacheProvider, this.provideDisplayMetricsProvider, this.provideRcSerializerProvider, this.metricRecorderProvider));
        this.provideGestureRouterProvider = DoubleCheck.provider(GestureHandlerModule_ProvideGestureRouterFactory.create(builder.gestureHandlerModule, this.provideDigitalPtzGestureHandlerProvider, this.providePhysicalPtzGestureHandlerProvider));
        this.provideSimplePtzGestureListenerProvider = DoubleCheck.provider(GestureListenerModule_ProvideSimplePtzGestureListenerFactory.create(builder.gestureListenerModule, this.provideGestureRouterProvider, this.metricRecorderProvider));
        this.provideGestureDetectorProvider = DoubleCheck.provider(GestureListenerModule_ProvideGestureDetectorFactory.create(builder.gestureListenerModule, this.contextProvider, this.provideSimplePtzGestureListenerProvider));
        this.provideScalePtzGestureListenerProvider = DoubleCheck.provider(GestureListenerModule_ProvideScalePtzGestureListenerFactory.create(builder.gestureListenerModule, this.provideGestureRouterProvider, this.metricRecorderProvider));
        this.provideScaleGestureDetectorProvider = DoubleCheck.provider(GestureListenerModule_ProvideScaleGestureDetectorFactory.create(builder.gestureListenerModule, this.contextProvider, this.provideScalePtzGestureListenerProvider));
        this.providePtzListenerProvider = DoubleCheck.provider(GestureListenerModule_ProvidePtzListenerFactory.create(builder.gestureListenerModule, this.provideGestureRouterProvider, this.provideGestureDetectorProvider, this.provideScaleGestureDetectorProvider, this.contextProvider));
        this.providePtzKeyEventListenerProvider = DoubleCheck.provider(GestureListenerModule_ProvidePtzKeyEventListenerFactory.create(builder.gestureListenerModule, this.provideGestureRouterProvider, this.metricRecorderProvider, this.provideDigitalZoomStateProvider));
        this.provideErrorResponseHandlerProvider = DoubleCheck.provider(PhysicalPtzResponseHandlerModule_ProvideErrorResponseHandlerFactory.create(builder.physicalPtzResponseHandlerModule, this.provideEventBusProvider, this.provideRcSerializerProvider, this.metricRecorderProvider));
        this.provideSafetyErrorResponseHandlerProvider = DoubleCheck.provider(PhysicalPtzResponseHandlerModule_ProvideSafetyErrorResponseHandlerFactory.create(builder.physicalPtzResponseHandlerModule, this.provideEventBusProvider, this.provideRcSerializerProvider, this.metricRecorderProvider));
        this.provideChangeReportHandlerProvider = DoubleCheck.provider(PhysicalPtzResponseHandlerModule_ProvideChangeReportHandlerFactory.create(builder.physicalPtzResponseHandlerModule, this.provideRcSerializerProvider, this.metricRecorderProvider));
        this.getEventHandlersProvider = PhysicalPtzResponseHandlerModule_GetEventHandlersFactory.create(builder.physicalPtzResponseHandlerModule, this.provideErrorResponseHandlerProvider, this.provideSafetyErrorResponseHandlerProvider, this.provideChangeReportHandlerProvider);
        this.setOfResponseHandlerProvider = SetFactory.builder(0, 1).addCollectionProvider(this.getEventHandlersProvider).build();
        this.providePhysicalPtzResponseHandlerProvider = DoubleCheck.provider(PhysicalPtzResponseHandlerModule_ProvidePhysicalPtzResponseHandlerFactory.create(builder.physicalPtzResponseHandlerModule, this.provideRcSerializerProvider, this.providePhysicalPtzCommandCacheProvider, this.setOfResponseHandlerProvider));
    }

    @Override // com.amazon.ptz.PtzComponent
    public PtzKeyEventListener getPtzKeyEventListener() {
        return this.providePtzKeyEventListenerProvider.mo10268get();
    }

    @Override // com.amazon.ptz.PtzComponent
    public PtzListener getPtzListener() {
        return this.providePtzListenerProvider.mo10268get();
    }

    @Override // com.amazon.ptz.PtzComponent
    public PhysicalPtzResponseHandler getResponseHandler() {
        return this.providePhysicalPtzResponseHandlerProvider.mo10268get();
    }

    private DaggerPtzComponent(Builder builder) {
        initialize(builder);
    }
}
