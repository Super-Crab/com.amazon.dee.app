package com.amazon.alexa.fitness.service.hds;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.metrics.TimeshiftMetrics;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.service.hds.model.SessionSummary;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.network.NetworkService;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import rx.functions.Action1;
/* compiled from: HdsRetryHandlerImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HdsRetryHandlerImpl;", "Lcom/amazon/alexa/fitness/service/hds/HdsRetryHandler;", "hdsClient", "Lcom/amazon/alexa/fitness/service/hds/HdsClient;", "hdsThreadHandler", "Lcom/amazon/alexa/fitness/service/hds/HdsThreadHandler;", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "networkService", "Lcom/amazon/alexa/protocols/network/NetworkService;", "sessionSummaryCache", "Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/service/hds/HdsClient;Lcom/amazon/alexa/fitness/service/hds/HdsThreadHandler;Lcom/amazon/alexa/mobilytics/Mobilytics;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/protocols/network/NetworkService;Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;Lcom/amazon/alexa/fitness/logs/ILog;)V", "retryPendingUploads", "", "retryPendingUploadsWithoutMetrics", "", "start", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HdsRetryHandlerImpl implements HdsRetryHandler {
    private final HdsClient hdsClient;
    private final HdsThreadHandler hdsThreadHandler;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    private final Mobilytics metrics;
    private final NetworkService networkService;
    private final SessionSummaryCache sessionSummaryCache;

    @Inject
    public HdsRetryHandlerImpl(@NotNull HdsClient hdsClient, @NotNull HdsThreadHandler hdsThreadHandler, @NotNull Mobilytics metrics, @NotNull MetricEventFactory metricEventFactory, @NotNull MetricEventRecorder metricEventRecorder, @NotNull NetworkService networkService, @NotNull SessionSummaryCache sessionSummaryCache, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(hdsClient, "hdsClient");
        Intrinsics.checkParameterIsNotNull(hdsThreadHandler, "hdsThreadHandler");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(networkService, "networkService");
        Intrinsics.checkParameterIsNotNull(sessionSummaryCache, "sessionSummaryCache");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.hdsClient = hdsClient;
        this.hdsThreadHandler = hdsThreadHandler;
        this.metrics = metrics;
        this.metricEventFactory = metricEventFactory;
        this.metricEventRecorder = metricEventRecorder;
        this.networkService = networkService;
        this.sessionSummaryCache = sessionSummaryCache;
        this.log = log;
    }

    private final boolean retryPendingUploadsWithoutMetrics() {
        try {
            Set<SessionSummary> pendingSessions = this.sessionSummaryCache.getPendingSessions();
            MetricHelperKt.recordCounter(this.metrics, TimeshiftMetrics.Upload.INSTANCE.getPendingSessions(), pendingSessions.size());
            for (SessionSummary sessionSummary : pendingSessions) {
                this.hdsClient.uploadSession(sessionSummary, true);
            }
            return true;
        } catch (Throwable th) {
            this.log.error("HdsRetryHandlerImpl", "Caught exception while trying to retry the pending HDS uploads", th);
            return false;
        }
    }

    @Override // com.amazon.alexa.fitness.service.hds.HdsRetryHandler
    public void retryPendingUploads() {
        MetricEvent createMetricEvent = this.metricEventFactory.createMetricEvent(MetricsClass.HDS_RETRY_HANDLER);
        String buildMetricName = MetricsConstantsKt.buildMetricName("Retry", MetricsCategory.PENDING_SESSION_SUMMARY, MetricsName.LATENCY);
        String buildMetricName2 = MetricsConstantsKt.buildMetricName("Retry", MetricsCategory.PENDING_SESSION_SUMMARY, "Success");
        createMetricEvent.startTimer(buildMetricName);
        boolean retryPendingUploadsWithoutMetrics = retryPendingUploadsWithoutMetrics();
        createMetricEvent.stopTimer(buildMetricName);
        if (retryPendingUploadsWithoutMetrics) {
            createMetricEvent.setCounter(buildMetricName2, 1L);
        } else {
            createMetricEvent.setCounter(buildMetricName2, 0L);
        }
        this.metricEventRecorder.record(createMetricEvent);
    }

    @Override // com.amazon.alexa.fitness.service.hds.HdsRetryHandler, com.amazon.alexa.fitness.service.Startable
    public void start() {
        this.hdsThreadHandler.postRetry(new HdsRetryHandlerImpl$start$1(this));
        this.networkService.onConnectivityChanged().subscribe(new Action1<Boolean>() { // from class: com.amazon.alexa.fitness.service.hds.HdsRetryHandlerImpl$start$2

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: HdsRetryHandlerImpl.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.alexa.fitness.service.hds.HdsRetryHandlerImpl$start$2$1  reason: invalid class name */
            /* loaded from: classes8.dex */
            public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
                AnonymousClass1() {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                /* renamed from: invoke */
                public /* bridge */ /* synthetic */ Unit mo12560invoke() {
                    mo12560invoke();
                    return Unit.INSTANCE;
                }

                @Override // kotlin.jvm.functions.Function0
                /* renamed from: invoke  reason: collision with other method in class */
                public final void mo12560invoke() {
                    HdsRetryHandlerImpl.this.retryPendingUploads();
                }
            }

            @Override // rx.functions.Action1
            public final void call(Boolean hasConnection) {
                ILog iLog;
                HdsThreadHandler hdsThreadHandler;
                iLog = HdsRetryHandlerImpl.this.log;
                ILog.DefaultImpls.debug$default(iLog, "HdsRetryHandlerImpl", "On connectivity changed event received, hasConnection: " + hasConnection, null, 4, null);
                Intrinsics.checkExpressionValueIsNotNull(hasConnection, "hasConnection");
                if (hasConnection.booleanValue()) {
                    hdsThreadHandler = HdsRetryHandlerImpl.this.hdsThreadHandler;
                    hdsThreadHandler.postRetry(new AnonymousClass1());
                }
            }
        });
    }
}
