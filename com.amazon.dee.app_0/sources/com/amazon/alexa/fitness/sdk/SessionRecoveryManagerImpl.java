package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.api.util.TimeInterval;
import com.amazon.alexa.fitness.configuration.RecoveryConfigurationProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.AggregatedMetricsConstants;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.fitness.repository.RecoveryRepository;
import com.amazon.alexa.fitness.sdk.SessionRecoveryResult;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionRecoveryManagerImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001aH\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\u00172\u0006\u0010)\u001a\u00020\u0014H\u0002J\b\u0010*\u001a\u00020&H\u0016Jb\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u00142\u0012\u0010-\u001a\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016j\u0002`\u00182<\u0010.\u001a8\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f0\u0019j\u0002` H\u0016JE\u0010/\u001a\u00020\u001f2\u0012\u00100\u001a\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016j\u0002`\"2'\u00101\u001a#\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001a0#j\u0002`$H\u0016J\u0018\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u0014H\u0002J\b\u00105\u001a\u00020\u001fH\u0016J\b\u00106\u001a\u00020\u001fH\u0016J\u0018\u00107\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u00142\u0006\u00108\u001a\u00020\u0017H\u0002J\u0010\u00109\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u0017H\u0002J\u0014\u0010:\u001a\u00020;*\u00020<2\u0006\u0010=\u001a\u00020<H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000Rh\u0010\u0012\u001a\\\u0012\u0004\u0012\u00020\u0014\u0012R\u0012P\u0012\u0010\u0012\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016j\u0002`\u0018\u0012:\u00128\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f0\u0019j\u0002` 0\u00150\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000RI\u0010!\u001a=\u0012\u0010\u0012\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016j\u0002`\"\u0012%\u0012#\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001a0#j\u0002`$\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionRecoveryManagerImpl;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryManager;", "recoveryRepository", "Lcom/amazon/alexa/fitness/repository/RecoveryRepository;", "configurationProvider", "Lcom/amazon/alexa/fitness/configuration/RecoveryConfigurationProvider;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/repository/RecoveryRepository;Lcom/amazon/alexa/fitness/configuration/RecoveryConfigurationProvider;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;Lcom/amazon/alexa/fitness/logs/ILog;)V", DefaultKinesisConnector.COMPONENT, "Lcom/amazon/alexa/mobilytics/Mobilytics;", "kotlin.jvm.PlatformType", "recoverableComponents", "", "", "Lkotlin/Pair;", "Lkotlin/Function0;", "", "Lcom/amazon/alexa/fitness/sdk/encodeRecoverableComponentState;", "Lkotlin/Function2;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "Lkotlin/ParameterName;", "name", "session", "recoverableState", "", "Lcom/amazon/alexa/fitness/sdk/decodeRecoverableComponentState;", "sessionRecovery", "Lcom/amazon/alexa/fitness/sdk/encodeRecoverableSession;", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/sdk/decodeRecoverableSession;", "attemptRecoveryOfRegisteredComponents", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult;", "recoveredSession", "getComponent", "key", "recoverFitnessSession", "registerComponentRecovery", "recoverableId", "encodeRecoverableState", "decodeRecoverableState", "registerSessionRecovery", "encodeRecoverableSession", "decodeRecoverableSession", "reportMetric", "metricsCategory", "metricName", "reset", "save", "saveComponent", "value", "saveSession", "isLessThan", "", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "dateTime", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionRecoveryManagerImpl implements SessionRecoveryManager {
    private final RecoveryConfigurationProvider configurationProvider;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    private final Mobilytics mobilytics;
    private Map<String, Pair<Function0<byte[]>, Function2<Session, byte[], Unit>>> recoverableComponents;
    private final RecoveryRepository recoveryRepository;
    private final SampleStore sampleStore;
    private Pair<? extends Function0<byte[]>, ? extends Function1<? super byte[], Session>> sessionRecovery;

    @Inject
    public SessionRecoveryManagerImpl(@NotNull RecoveryRepository recoveryRepository, @NotNull RecoveryConfigurationProvider configurationProvider, @NotNull MetricEventFactory metricEventFactory, @NotNull MetricEventRecorder metricEventRecorder, @NotNull SampleStore sampleStore, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(recoveryRepository, "recoveryRepository");
        Intrinsics.checkParameterIsNotNull(configurationProvider, "configurationProvider");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.recoveryRepository = recoveryRepository;
        this.configurationProvider = configurationProvider;
        this.metricEventFactory = metricEventFactory;
        this.metricEventRecorder = metricEventRecorder;
        this.sampleStore = sampleStore;
        this.log = log;
        this.recoverableComponents = new LinkedHashMap();
        this.mobilytics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    }

    private final SessionRecoveryResult attemptRecoveryOfRegisteredComponents(Session session) {
        try {
            for (Map.Entry<String, Pair<Function0<byte[]>, Function2<Session, byte[], Unit>>> entry : this.recoverableComponents.entrySet()) {
                ILog iLog = this.log;
                ILog.DefaultImpls.info$default(iLog, MetricsClass.SESSION_RECOVERY_MANAGER, "Recovering: " + entry.getKey(), null, 4, null);
                entry.getValue().getSecond().mo12248invoke(session, getComponent(entry.getKey()));
            }
            if (session.getEvents().isEmpty()) {
                ILog.DefaultImpls.info$default(this.log, MetricsClass.SESSION_RECOVERY_MANAGER, "No session events exist", null, 4, null);
                reportMetric(MetricsCategory.FITNESS_SESSION_EVENTS, MetricsName.MISSING);
                this.sampleStore.deleteAllSamples(session.getConfiguration().getSessionId());
                return SessionRecoveryResult.UnrecoverableNoData.INSTANCE;
            } else if (isLessThan(SessionDataModelsKt.lastTouched(session).plus(new TimeInterval(this.configurationProvider.provideRecoveryConfiguration().getRecoveryWindowInMillis())), DateTime.Companion.now())) {
                ILog iLog2 = this.log;
                ILog.DefaultImpls.info$default(iLog2, MetricsClass.SESSION_RECOVERY_MANAGER, "Cached session found but was last touched at: " + SessionDataModelsKt.lastTouched(session), null, 4, null);
                Mobilytics mobilytics = this.mobilytics;
                Intrinsics.checkExpressionValueIsNotNull(mobilytics, "mobilytics");
                MetricHelperKt.recordCounter$default(mobilytics, AggregatedMetricsConstants.Companion.getSTOP_TIMEOUT(), 0L, 2, null);
                reportMetric(MetricsOperation.RECOVERY, MetricsName.AUTOSTOP_RECOVERY_STALE);
                return new SessionRecoveryResult.UnrecoverableStaleData(session);
            } else {
                ILog iLog3 = this.log;
                ILog.DefaultImpls.info$default(iLog3, MetricsClass.SESSION_RECOVERY_MANAGER, "Cached recoverable session found: " + session, null, 4, null);
                reportMetric(MetricsOperation.RECOVERY, "Success");
                Mobilytics mobilytics2 = this.mobilytics;
                Intrinsics.checkExpressionValueIsNotNull(mobilytics2, "mobilytics");
                MetricHelperKt.recordCounter$default(mobilytics2, AggregatedMetricsConstants.Companion.getRECOVERY(), 0L, 2, null);
                return new SessionRecoveryResult.Recovered(session);
            }
        } catch (Exception e) {
            this.log.error(MetricsClass.SESSION_RECOVERY_MANAGER, "error during recovery", e);
            this.sampleStore.deleteAllSamples(session.getConfiguration().getSessionId());
            return SessionRecoveryResult.FailedToRecover.INSTANCE;
        }
    }

    private final byte[] getComponent(String str) {
        return this.recoveryRepository.getComponentState(str);
    }

    private final boolean isLessThan(@NotNull DateTime dateTime, DateTime dateTime2) {
        return dateTime.getEpochMilli() < dateTime2.getEpochMilli();
    }

    private final void reportMetric(String str, String str2) {
        this.metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(this.metricEventFactory.createMetricEvent(MetricsClass.SESSION_RECOVERY_MANAGER), MetricsConstantsKt.buildMetricName(str, str2), 0L, 2, null));
    }

    private final void saveComponent(String str, byte[] bArr) {
        this.recoveryRepository.saveComponentState(str, bArr);
    }

    private final void saveSession(byte[] bArr) {
        this.recoveryRepository.saveSession(bArr);
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionRecoveryManager
    @NotNull
    public SessionRecoveryResult recoverFitnessSession() {
        Function1<? super byte[], Session> second;
        Session mo12165invoke;
        SessionRecoveryResult attemptRecoveryOfRegisteredComponents;
        ILog.DefaultImpls.info$default(this.log, MetricsClass.SESSION_RECOVERY_MANAGER, "Starting session recovery", null, 4, null);
        if (this.sessionRecovery != null) {
            byte[] session = this.recoveryRepository.getSession();
            if (session == null) {
                ILog.DefaultImpls.info$default(this.log, MetricsClass.SESSION_RECOVERY_MANAGER, "nothing to recover", null, 4, null);
                reportMetric(MetricsOperation.RECOVERY, MetricsName.NOT_ACTIVE);
                return SessionRecoveryResult.NothingToRecover.INSTANCE;
            }
            try {
                Pair<? extends Function0<byte[]>, ? extends Function1<? super byte[], Session>> pair = this.sessionRecovery;
                if (pair != null && (second = pair.getSecond()) != null && (mo12165invoke = second.mo12165invoke(session)) != null && (attemptRecoveryOfRegisteredComponents = attemptRecoveryOfRegisteredComponents(mo12165invoke)) != null) {
                    return attemptRecoveryOfRegisteredComponents;
                }
                throw new Exception("got null recovered session");
            } catch (Exception e) {
                ILog.DefaultImpls.error$default(this.log, MetricsClass.SESSION_RECOVERY_MANAGER, GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("session recovery exception: ")), null, 4, null);
                reportMetric(MetricsOperation.RECOVERY, "Failure");
                return SessionRecoveryResult.FailedToRecover.INSTANCE;
            }
        }
        ILog.DefaultImpls.error$default(this.log, MetricsClass.SESSION_RECOVERY_MANAGER, "no session recovery registered", null, 4, null);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error during recovery : ");
        outline107.append(RecoveringError.NoSessionRecoveryRegistered);
        throw new Exception(outline107.toString());
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionRecoveryManager
    public void registerComponentRecovery(@NotNull String recoverableId, @NotNull Function0<byte[]> encodeRecoverableState, @NotNull Function2<? super Session, ? super byte[], Unit> decodeRecoverableState) {
        Intrinsics.checkParameterIsNotNull(recoverableId, "recoverableId");
        Intrinsics.checkParameterIsNotNull(encodeRecoverableState, "encodeRecoverableState");
        Intrinsics.checkParameterIsNotNull(decodeRecoverableState, "decodeRecoverableState");
        this.recoverableComponents.put(recoverableId, new Pair<>(encodeRecoverableState, decodeRecoverableState));
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionRecoveryManager
    public void registerSessionRecovery(@NotNull Function0<byte[]> encodeRecoverableSession, @NotNull Function1<? super byte[], Session> decodeRecoverableSession) {
        Intrinsics.checkParameterIsNotNull(encodeRecoverableSession, "encodeRecoverableSession");
        Intrinsics.checkParameterIsNotNull(decodeRecoverableSession, "decodeRecoverableSession");
        this.sessionRecovery = new Pair<>(encodeRecoverableSession, decodeRecoverableSession);
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionRecoveryManager
    public void reset() {
        ILog.DefaultImpls.info$default(this.log, MetricsClass.SESSION_RECOVERY_MANAGER, "resetting recovery cache", null, 4, null);
        this.recoveryRepository.deleteSession();
        for (Map.Entry<String, Pair<Function0<byte[]>, Function2<Session, byte[], Unit>>> entry : this.recoverableComponents.entrySet()) {
            this.recoveryRepository.deleteComponentState(entry.getKey());
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionRecoveryManager
    public void save() {
        byte[] mo12560invoke;
        Pair<? extends Function0<byte[]>, ? extends Function1<? super byte[], Session>> pair = this.sessionRecovery;
        if (pair == null) {
            ILog.DefaultImpls.error$default(this.log, MetricsClass.SESSION_RECOVERY_MANAGER, "no session recovery registered", null, 4, null);
            return;
        }
        if (pair != null && (mo12560invoke = pair.component1().mo12560invoke()) != null) {
            saveSession(mo12560invoke);
        }
        for (Map.Entry<String, Pair<Function0<byte[]>, Function2<Session, byte[], Unit>>> entry : this.recoverableComponents.entrySet()) {
            byte[] mo12560invoke2 = entry.getValue().component1().mo12560invoke();
            if (mo12560invoke2 != null) {
                saveComponent(entry.getKey(), mo12560invoke2);
            }
        }
    }
}
