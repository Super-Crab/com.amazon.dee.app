package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.fitness.util.ErrorReporting;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.dee.app.cachemanager.CacheMetadata;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rx.functions.Action1;
/* compiled from: RecoveryRepository.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0013H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/fitness/repository/RecoveryRepositoryImpl;", "Lcom/amazon/alexa/fitness/repository/RecoveryRepository;", "cacheProvider", "Lcom/amazon/alexa/fitness/repository/CacheProvider;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/repository/CacheProvider;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/logs/ILog;)V", "cacheMetaData", "Lcom/dee/app/cachemanager/CacheMetadata;", "deleteComponentState", "", "key", "", "deleteSession", "getComponentState", "", "getSession", "saveComponentState", "state", "saveSession", "session", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class RecoveryRepositoryImpl implements RecoveryRepository {
    private final CacheMetadata cacheMetaData;
    private final CacheProvider cacheProvider;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;

    @Inject
    public RecoveryRepositoryImpl(@NotNull CacheProvider cacheProvider, @NotNull MetricEventFactory metricEventFactory, @NotNull MetricEventRecorder metricEventRecorder, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(cacheProvider, "cacheProvider");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.cacheProvider = cacheProvider;
        this.metricEventFactory = metricEventFactory;
        this.metricEventRecorder = metricEventRecorder;
        this.log = log;
        this.cacheMetaData = new CacheMetadata(1);
    }

    @Override // com.amazon.alexa.fitness.repository.RecoveryRepository
    public void deleteComponentState(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            this.cacheProvider.getRecoveryCache().remove(key, this.cacheMetaData).doOnError(new Action1<Throwable>() { // from class: com.amazon.alexa.fitness.repository.RecoveryRepositoryImpl$deleteComponentState$1
                @Override // rx.functions.Action1
                public final void call(Throwable th) {
                    MetricEventRecorder metricEventRecorder;
                    MetricEventFactory metricEventFactory;
                    metricEventRecorder = RecoveryRepositoryImpl.this.metricEventRecorder;
                    metricEventFactory = RecoveryRepositoryImpl.this.metricEventFactory;
                    metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.RECOVERY_REPOSITORY), MetricsConstantsKt.buildMetricName(MetricsOperation.DELETE_COMPONENT_STATE, "Failure"), 0L, 2, null));
                }
            }).toBlocking().singleOrDefault(null);
            ILog iLog = this.log;
            ILog.DefaultImpls.info$default(iLog, MetricsClass.RECOVERY_REPOSITORY, "Component state for " + key + " deleted", null, 4, null);
        } catch (Throwable th) {
            this.log.error(MetricsClass.RECOVERY_REPOSITORY, "error deleting component state", th);
            ErrorReporting.Companion.reportThrowable(th);
        }
    }

    @Override // com.amazon.alexa.fitness.repository.RecoveryRepository
    public void deleteSession() {
        try {
            this.cacheProvider.getRecoveryCache().remove(MetricsConfiguration.SESSION_ID, this.cacheMetaData).doOnError(new Action1<Throwable>() { // from class: com.amazon.alexa.fitness.repository.RecoveryRepositoryImpl$deleteSession$1
                @Override // rx.functions.Action1
                public final void call(Throwable th) {
                    MetricEventRecorder metricEventRecorder;
                    MetricEventFactory metricEventFactory;
                    metricEventRecorder = RecoveryRepositoryImpl.this.metricEventRecorder;
                    metricEventFactory = RecoveryRepositoryImpl.this.metricEventFactory;
                    metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.RECOVERY_REPOSITORY), MetricsConstantsKt.buildMetricName(MetricsOperation.DELETE_SESSION, "Failure"), 0L, 2, null));
                }
            }).toBlocking().singleOrDefault(null);
            ILog.DefaultImpls.info$default(this.log, MetricsClass.RECOVERY_REPOSITORY, "Session deleted.", null, 4, null);
        } catch (Throwable th) {
            this.log.error(MetricsClass.RECOVERY_REPOSITORY, "error deleting session", th);
            ErrorReporting.Companion.reportThrowable(th);
        }
    }

    @Override // com.amazon.alexa.fitness.repository.RecoveryRepository
    @Nullable
    public byte[] getComponentState(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            byte[] orNull = this.cacheProvider.getRecoveryCache().get(key, this.cacheMetaData).doOnError(new Action1<Throwable>() { // from class: com.amazon.alexa.fitness.repository.RecoveryRepositoryImpl$getComponentState$1
                @Override // rx.functions.Action1
                public final void call(Throwable th) {
                    MetricEventRecorder metricEventRecorder;
                    MetricEventFactory metricEventFactory;
                    metricEventRecorder = RecoveryRepositoryImpl.this.metricEventRecorder;
                    metricEventFactory = RecoveryRepositoryImpl.this.metricEventFactory;
                    metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.RECOVERY_REPOSITORY), MetricsConstantsKt.buildMetricName(MetricsOperation.GET_COMPONENT_STATE, "Failure"), 0L, 2, null));
                }
            }).toBlocking().single().orNull();
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, MetricsClass.RECOVERY_REPOSITORY, orNull + " retrieved for " + key, null, 4, null);
            return orNull;
        } catch (Throwable th) {
            this.log.error(MetricsClass.RECOVERY_REPOSITORY, "error getting component state", th);
            ErrorReporting.Companion.reportThrowable(th);
            return null;
        }
    }

    @Override // com.amazon.alexa.fitness.repository.RecoveryRepository
    @Nullable
    public byte[] getSession() {
        try {
            byte[] orNull = this.cacheProvider.getRecoveryCache().get(MetricsConfiguration.SESSION_ID, this.cacheMetaData).doOnError(new Action1<Throwable>() { // from class: com.amazon.alexa.fitness.repository.RecoveryRepositoryImpl$getSession$1
                @Override // rx.functions.Action1
                public final void call(Throwable th) {
                    MetricEventRecorder metricEventRecorder;
                    MetricEventFactory metricEventFactory;
                    metricEventRecorder = RecoveryRepositoryImpl.this.metricEventRecorder;
                    metricEventFactory = RecoveryRepositoryImpl.this.metricEventFactory;
                    metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.RECOVERY_REPOSITORY), MetricsConstantsKt.buildMetricName(MetricsOperation.GET_SESSION, "Failure"), 0L, 2, null));
                }
            }).toBlocking().single().orNull();
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, MetricsClass.RECOVERY_REPOSITORY, orNull + " retrieved as Session", null, 4, null);
            return orNull;
        } catch (Throwable th) {
            this.log.error(MetricsClass.RECOVERY_REPOSITORY, "error getting session", th);
            ErrorReporting.Companion.reportThrowable(th);
            return null;
        }
    }

    @Override // com.amazon.alexa.fitness.repository.RecoveryRepository
    public void saveComponentState(@NotNull String key, @NotNull byte[] state) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(state, "state");
        try {
            this.cacheProvider.getRecoveryCache().put(key, state, this.cacheMetaData).doOnError(new Action1<Throwable>() { // from class: com.amazon.alexa.fitness.repository.RecoveryRepositoryImpl$saveComponentState$1
                @Override // rx.functions.Action1
                public final void call(Throwable th) {
                    MetricEventRecorder metricEventRecorder;
                    MetricEventFactory metricEventFactory;
                    metricEventRecorder = RecoveryRepositoryImpl.this.metricEventRecorder;
                    metricEventFactory = RecoveryRepositoryImpl.this.metricEventFactory;
                    metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.RECOVERY_REPOSITORY), MetricsConstantsKt.buildMetricName(MetricsOperation.SAVE_COMPONENT_STATE, "Failure"), 0L, 2, null));
                }
            }).toBlocking().singleOrDefault(null);
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, MetricsClass.RECOVERY_REPOSITORY, "Component state saved for " + key + " - " + state, null, 4, null);
        } catch (Throwable th) {
            this.log.error(MetricsClass.RECOVERY_REPOSITORY, "error saving component state", th);
            ErrorReporting.Companion.reportThrowable(th);
        }
    }

    @Override // com.amazon.alexa.fitness.repository.RecoveryRepository
    public void saveSession(@NotNull byte[] session) {
        Intrinsics.checkParameterIsNotNull(session, "session");
        try {
            this.cacheProvider.getRecoveryCache().put(MetricsConfiguration.SESSION_ID, session, this.cacheMetaData).doOnError(new Action1<Throwable>() { // from class: com.amazon.alexa.fitness.repository.RecoveryRepositoryImpl$saveSession$1
                @Override // rx.functions.Action1
                public final void call(Throwable th) {
                    MetricEventRecorder metricEventRecorder;
                    MetricEventFactory metricEventFactory;
                    metricEventRecorder = RecoveryRepositoryImpl.this.metricEventRecorder;
                    metricEventFactory = RecoveryRepositoryImpl.this.metricEventFactory;
                    metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.RECOVERY_REPOSITORY), MetricsConstantsKt.buildMetricName(MetricsOperation.SAVE_SESSION, "Failure"), 0L, 2, null));
                }
            }).toBlocking().singleOrDefault(null);
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, MetricsClass.RECOVERY_REPOSITORY, "Session saved - " + session, null, 4, null);
        } catch (Throwable th) {
            this.log.error(MetricsClass.RECOVERY_REPOSITORY, "error saving session", th);
            ErrorReporting.Companion.reportThrowable(th);
        }
    }
}
