package com.amazon.alexa.mobilytics.connector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import com.amazon.alexa.mobilytics.configuration.Endpoint;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.metadata.DCMAttributeName;
import com.amazon.alexa.mobilytics.event.metadata.EventMetadata;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.marketplace.Marketplace;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.mobilytics.util.Utils;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.google.common.base.Preconditions;
import dagger.Lazy;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public class DCMConnector implements MobilyticsConnector {
    private static final String TAG = Log.tag(DCMConnector.class);
    private final ApplicationConfiguration applicationConfiguration;
    private MobilyticsConfiguration configuration;
    private final DefaultRecordChecker defaultRecordChecker;
    private final DeviceConfiguration deviceConfiguration;
    private DCMEndpoint endpoint;
    private final Lazy<MetricsFactory> metricsFactoryLazy;
    private String name;
    private Map<String, String> pivots;
    private MobilyticsUser user;

    @Singleton
    /* loaded from: classes9.dex */
    public static class Factory {
        private final ApplicationConfiguration applicationConfiguration;
        private final DefaultRecordChecker defaultRecordChecker;
        private final DeviceConfiguration deviceConfiguration;
        private final Lazy<MetricsFactory> metricsFactoryLazy;

        @Inject
        public Factory(@NonNull DeviceConfiguration deviceConfiguration, @NonNull ApplicationConfiguration applicationConfiguration, @NonNull Lazy<MetricsFactory> lazy, @NonNull DefaultRecordChecker defaultRecordChecker) {
            this.deviceConfiguration = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration);
            this.applicationConfiguration = (ApplicationConfiguration) Preconditions.checkNotNull(applicationConfiguration);
            this.metricsFactoryLazy = (Lazy) Preconditions.checkNotNull(lazy);
            this.defaultRecordChecker = (DefaultRecordChecker) Preconditions.checkNotNull(defaultRecordChecker);
        }

        public MobilyticsConnector create(@NonNull Endpoint endpoint, @NonNull String str) {
            DCMConnector dCMConnector = new DCMConnector(this.deviceConfiguration, this.applicationConfiguration, this.metricsFactoryLazy, this.defaultRecordChecker, endpoint);
            dCMConnector.name = str;
            return dCMConnector;
        }
    }

    /* loaded from: classes9.dex */
    private static final class MetricKey {
        private static final String EVENT_NAME = "EventName";
        private static final String EVENT_TIMESTAMP = "EventTimestamp";
        private static final String LOCAL_TIMEZONE = "localTimezone";

        private MetricKey() {
        }
    }

    public DCMConnector(@NonNull DeviceConfiguration deviceConfiguration, @NonNull ApplicationConfiguration applicationConfiguration, @NonNull Lazy<MetricsFactory> lazy, @NonNull DefaultRecordChecker defaultRecordChecker, @NonNull Endpoint endpoint) {
        this.deviceConfiguration = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration);
        this.applicationConfiguration = (ApplicationConfiguration) Preconditions.checkNotNull(applicationConfiguration);
        this.metricsFactoryLazy = (Lazy) Preconditions.checkNotNull(lazy);
        this.defaultRecordChecker = (DefaultRecordChecker) Preconditions.checkNotNull(defaultRecordChecker);
        this.endpoint = (DCMEndpoint) Preconditions.checkNotNull(endpoint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$processMethods$10(EventMetadata eventMetadata, final MetricEvent metricEvent, Method method) {
        try {
            Observable.from(((Map) method.invoke(eventMetadata, new Object[0])).entrySet()).forEach(new Action1() { // from class: com.amazon.alexa.mobilytics.connector.-$$Lambda$DCMConnector$pv3FWznOGmGpKt1Ujgy8YdJbo04
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MetricEvent.this.addString((String) r2.getKey(), (String) ((Map.Entry) obj).getValue());
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Bad access to method", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$processMethods$5(MetricEvent metricEvent, EventMetadata eventMetadata, Method method) {
        try {
            metricEvent.addString(((DCMAttributeName) method.getAnnotation(DCMAttributeName.class)).value(), (String) method.invoke(eventMetadata, new Object[0]));
        } catch (Exception e) {
            Log.e(TAG, "Bad access to method", e);
        }
    }

    private void processMetadataAttributes(MobilyticsEvent mobilyticsEvent, final MetricEvent metricEvent) {
        if (mobilyticsEvent.getEventMetadata() == null || mobilyticsEvent.getEventMetadata().size() <= 0) {
            return;
        }
        Observable.from(mobilyticsEvent.getEventMetadata()).forEach(new Action1() { // from class: com.amazon.alexa.mobilytics.connector.-$$Lambda$DCMConnector$IayM6TOxMdKSRw1xqv2DSjOMDxU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DCMConnector.this.lambda$processMetadataAttributes$1$DCMConnector(metricEvent, (EventMetadata) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: processMethods */
    public void lambda$processMetadataAttributes$1$DCMConnector(final MetricEvent metricEvent, final EventMetadata eventMetadata) {
        Method[] methods = eventMetadata.getClass().getMethods();
        Observable.from(methods).filter($$Lambda$DCMConnector$Zcz9lfJTzVaPqQZwEQkNU03bH0c.INSTANCE).filter($$Lambda$DCMConnector$n9oolM_XB5nRkAIksL8XT6oCag.INSTANCE).filter($$Lambda$DCMConnector$1tlVYtZcwxaHVTIJPaX0_lHc3OA.INSTANCE).forEach(new Action1() { // from class: com.amazon.alexa.mobilytics.connector.-$$Lambda$DCMConnector$4yV4VeXN5uEGamlvr1K6-pA4etk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DCMConnector.lambda$processMethods$5(MetricEvent.this, eventMetadata, (Method) obj);
            }
        });
        Observable.from(methods).filter($$Lambda$DCMConnector$rzUjXRvSllHyxJ2PuH1IToDRvw0.INSTANCE).filter($$Lambda$DCMConnector$RZKWtiaCOvxk3NHN62fSSqKJJQg.INSTANCE).filter($$Lambda$DCMConnector$X9ka7VRGOKPUU1X4zGxTH_KWxmM.INSTANCE).forEach(new Action1() { // from class: com.amazon.alexa.mobilytics.connector.-$$Lambda$DCMConnector$IXL6w0Ldt_0k7rH0yJVokIYHb5o
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DCMConnector.lambda$processMethods$10(EventMetadata.this, metricEvent, (Method) obj);
            }
        });
    }

    private void updatePivots() {
        MobilyticsUser mobilyticsUser = this.user;
        this.pivots = Utils.computeMwsPivots((mobilyticsUser != null ? Marketplace.findMarketplaceById(mobilyticsUser.marketplaceId(), Marketplace.US) : Marketplace.US).name(), this.deviceConfiguration.country(), this.applicationConfiguration.versionName(), this.deviceConfiguration.operatingSystemType());
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public String name() {
        return this.name;
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onFinalize() {
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onInitialize(@NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        this.configuration = (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsConfiguration);
        this.user = this.configuration.userProvider().user();
        updatePivots();
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onRecordEvent(@NonNull MobilyticsEvent mobilyticsEvent) {
        Priority priority;
        Log.enter();
        if (!mobilyticsEvent.getEventType().equals(EventType.OPERATIONAL) || !this.defaultRecordChecker.shouldBeSent(this.endpoint, mobilyticsEvent)) {
            return;
        }
        MobilyticsOperationalEvent mobilyticsOperationalEvent = (MobilyticsOperationalEvent) mobilyticsEvent;
        String serviceName = this.configuration.serviceName();
        String priority2 = this.endpoint.priority();
        char c = 65535;
        int hashCode = priority2.hashCode();
        if (hashCode != -1955878649) {
            if (hashCode == 2249154 && priority2.equals(DCMEndpoint.Priority.HIGH)) {
                c = 0;
            }
        } else if (priority2.equals(DCMEndpoint.Priority.NORMAL)) {
            c = 1;
        }
        if (c == 0) {
            priority = Priority.HIGH;
            serviceName = serviceName.concat(this.endpoint.tag());
        } else if (c != 1) {
            priority = Priority.NORMAL;
        } else {
            priority = Priority.NORMAL;
        }
        final MetricEvent createMetricEvent = this.metricsFactoryLazy.mo358get().createMetricEvent(serviceName, mobilyticsEvent.getComponent());
        createMetricEvent.addString("EventName", mobilyticsEvent.getEventName());
        createMetricEvent.addString("EventTimestamp", Long.toString(mobilyticsEvent.getEventTimestamp()));
        createMetricEvent.addString("localTimezone", TimeZone.getDefault().getID());
        Observable.from(this.pivots.entrySet()).forEach(new Action1() { // from class: com.amazon.alexa.mobilytics.connector.-$$Lambda$DCMConnector$hKuqf-rGJWUiLw38gkf32rXsqDc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MetricEvent.this.addString((String) r2.getKey(), (String) ((Map.Entry) obj).getValue());
            }
        });
        if (mobilyticsOperationalEvent.getOperationalEventType().equals(OperationalEventType.COUNTER)) {
            createMetricEvent.addCounter(mobilyticsEvent.getEventName(), ((MobilyticsMetricsCounter) mobilyticsOperationalEvent).getCount());
        } else if (mobilyticsOperationalEvent.getOperationalEventType().equals(OperationalEventType.TIMER)) {
            createMetricEvent.addTimer(mobilyticsEvent.getEventName(), ((MobilyticsMetricsTimer) mobilyticsOperationalEvent).getElapsedTime());
        } else if ("error".equals(mobilyticsOperationalEvent.getOperationalEventType()) || OperationalEventType.DIAGNOSTIC.equals(mobilyticsOperationalEvent.getOperationalEventType()) || "data".equals(mobilyticsOperationalEvent.getOperationalEventType())) {
            createMetricEvent.addCounter(mobilyticsEvent.getEventName(), 1.0d);
        }
        processMetadataAttributes(mobilyticsEvent, createMetricEvent);
        Log.d(TAG, "sending DCM metrics through %s priority stream with service name %s", this.endpoint.priority(), serviceName);
        this.metricsFactoryLazy.mo358get().record(createMetricEvent, priority, Channel.ANONYMOUS);
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onSessionPause(@NonNull MobilyticsSession mobilyticsSession) {
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onSessionResume(@NonNull MobilyticsSession mobilyticsSession) {
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onSessionStart(@NonNull MobilyticsSession mobilyticsSession) {
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onSessionStop(@NonNull MobilyticsSession mobilyticsSession) {
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onUserChanged(@Nullable MobilyticsUser mobilyticsUser) {
        this.user = mobilyticsUser;
        updatePivots();
    }
}
