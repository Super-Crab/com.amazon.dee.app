package com.amazon.alexa.fitness.orchestrator;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmProvider;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmsManager;
import com.amazon.alexa.fitness.algorithm.StepsToDistanceAlgorithmAdapter;
import com.amazon.alexa.fitness.algorithm.StepsToDistanceAlgorithmAdapterKt;
import com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm;
import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmAdapter;
import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmAdapterKt;
import com.amazon.alexa.fitness.api.LocationCoordinate;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.afx.UISessionSummary;
import com.amazon.alexa.fitness.api.afx.UIState;
import com.amazon.alexa.fitness.api.afx.WorkoutHistory;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionTransition;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.api.fitnessSdk.WorkoutType;
import com.amazon.alexa.fitness.context.SessionSummaryProvider;
import com.amazon.alexa.fitness.context.SessionSummaryProviderDelegate;
import com.amazon.alexa.fitness.location.LocationSensorProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.message.notification.FitnessNotificationPublisher;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.metrics.SessionMetrics;
import com.amazon.alexa.fitness.model.biometric.ActivitySummary;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery;
import com.amazon.alexa.fitness.sdk.SensorProvider;
import com.amazon.alexa.fitness.sdk.SessionManager;
import com.amazon.alexa.fitness.sdk.SessionManagerDelegate;
import com.amazon.alexa.fitness.sdk.SessionManagerException;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.service.hds.HdsClient;
import com.amazon.alexa.fitness.util.ActivityLifecycleObserver;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionOrchestratorImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000®\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003BÅ\u0001\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001b\u0012\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001b\u0012\u0006\u0010!\u001a\u00020\"\u0012\u0006\u0010#\u001a\u00020$\u0012\u0006\u0010%\u001a\u00020&\u0012\u0006\u0010'\u001a\u00020(\u0012\u0006\u0010)\u001a\u00020*\u0012\u0006\u0010+\u001a\u00020,\u0012\u0006\u0010-\u001a\u00020.¢\u0006\u0002\u0010/J\u0010\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020IH\u0016J\b\u0010J\u001a\u00020KH\u0016J\b\u0010L\u001a\u00020GH\u0016J\u0010\u0010M\u001a\u00020G2\u0006\u0010N\u001a\u00020OH\u0016J1\u0010P\u001a\u00020G2'\u0010Q\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020C0\u0014¢\u0006\f\bS\u0012\b\bT\u0012\u0004\b\b(U\u0012\u0004\u0012\u00020G0RH\u0016J\b\u0010V\u001a\u00020WH\u0016J\b\u0010X\u001a\u00020GH\u0002J\u0012\u0010Y\u001a\u00020G2\b\u0010Z\u001a\u0004\u0018\u00010=H\u0016J\b\u0010[\u001a\u00020GH\u0002JJ\u0010\\\u001a\u00020G2\u0006\u0010]\u001a\u00020^28\u0010_\u001a4\u0012\u0015\u0012\u0013\u0018\u00010a¢\u0006\f\bS\u0012\b\bT\u0012\u0004\b\b(b\u0012\u0013\u0012\u00110c¢\u0006\f\bS\u0012\b\bT\u0012\u0004\b\b(d\u0012\u0004\u0012\u00020G0`H\u0016J\u0010\u0010e\u001a\u00020G2\u0006\u0010f\u001a\u00020OH\u0002J\u0016\u0010g\u001a\u00020G2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020;0:H\u0016J\u0016\u0010i\u001a\u00020G2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020;0:H\u0016J\u0018\u0010j\u001a\u00020G2\u0006\u0010k\u001a\u00020?2\u0006\u0010l\u001a\u00020OH\u0016J\u0018\u0010m\u001a\u00020G2\u0006\u0010n\u001a\u00020c2\u0006\u0010o\u001a\u00020cH\u0002J*\u0010p\u001a\u00020G2\u0006\u0010n\u001a\u00020c2\u0006\u0010q\u001a\u00020r2\u0006\u0010o\u001a\u00020c2\b\u0010b\u001a\u0004\u0018\u00010sH\u0016J%\u0010t\u001a\u00020G2\f\u0010u\u001a\b\u0012\u0004\u0012\u00020I0v2\b\u0010w\u001a\u0004\u0018\u00010OH\u0016¢\u0006\u0002\u0010xR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u00104\u001a\u000205¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0:09X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010B\u001a\n\u0012\u0004\u0012\u00020C\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006y"}, d2 = {"Lcom/amazon/alexa/fitness/orchestrator/FitnessSessionOrchestratorImpl;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "Lcom/amazon/alexa/fitness/sdk/SessionManagerDelegate;", "Lcom/amazon/alexa/fitness/context/SessionSummaryProviderDelegate;", "componentRegistry", "Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "afxMessageProcessor", "Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;", "hdsClient", "Lcom/amazon/alexa/fitness/service/hds/HdsClient;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "fitnessNotificationPublisher", "Lcom/amazon/alexa/fitness/message/notification/FitnessNotificationPublisher;", "sessionSummaryProvider", "Ldagger/Lazy;", "Lcom/amazon/alexa/fitness/context/SessionSummaryProvider;", "sessionManager", "Lcom/amazon/alexa/fitness/sdk/SessionManager;", "sensorProviders", "", "Lcom/amazon/alexa/fitness/sdk/SensorProvider;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "stepsToDistanceAlgorithmProvider", "Ljavax/inject/Provider;", "Lcom/amazon/alexa/fitness/algorithm/StepsToDistanceAlgorithmAdapter;", "caloriesAlgorithmProvider", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmAdapter;", "aggregatedDistanceAlgorithmProvider", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "sessionMetrics", "Lcom/amazon/alexa/fitness/metrics/SessionMetrics;", "metricsAggregatorRecovery", "Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecovery;", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "sessionSummaryCache", "Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;", "eventBus", "Lcom/amazon/alexa/eventbus/api/EventBus;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;Lcom/amazon/alexa/fitness/service/hds/HdsClient;Lcom/amazon/alexa/fitness/api/afx/FeatureService;Lcom/amazon/alexa/fitness/message/notification/FitnessNotificationPublisher;Ldagger/Lazy;Lcom/amazon/alexa/fitness/sdk/SessionManager;Ljava/util/List;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;Lcom/amazon/alexa/fitness/metrics/SessionMetrics;Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecovery;Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;Lcom/amazon/alexa/eventbus/api/EventBus;Lcom/amazon/alexa/fitness/logs/ILog;)V", "getComponentRegistry", "()Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "delegateHandler", "Landroid/os/Handler;", "delegateHandlerThread", "Landroid/os/HandlerThread;", "getDelegateHandlerThread", "()Landroid/os/HandlerThread;", "delegates", "", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "fitnessMetrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "lastKnownSensorAvailability", "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "lifecycleObserver", "Lcom/amazon/alexa/fitness/util/ActivityLifecycleObserver;", "uiSummaryRouteCoordinates", "Lcom/amazon/alexa/fitness/api/LocationCoordinate;", "workoutHistory", "Lcom/amazon/alexa/fitness/api/afx/WorkoutHistory;", "addNewWorkout", "", "workout", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "allRequiredSensorsAvailable", "", "deleteAllWorkouts", "deleteWorkout", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "getLocationList", "callback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "locations", "getUIState", "Lcom/amazon/alexa/fitness/api/afx/UIState;", "initSensorProviders", "onFitnessMetricsUpdated", "updatedMetrics", "onSessionEnded", "receive", "command", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "receivedHandler", "Lkotlin/Function2;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "error", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "currentState", "recordMetric", "metricName", "registerDelegate", "delegate", "removeDelegate", "sensorAvailabilityChanged", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "sensorId", "showOrHideNotificationIfNeeded", "oldState", "newState", "stateDidChange", "transition", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionTransition;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "updateWorkouts", "workouts", "", "nextToken", "([Lcom/amazon/alexa/fitness/api/afits/FitnessSession;Ljava/lang/String;)V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionOrchestratorImpl implements FitnessSessionOrchestrator, SessionManagerDelegate, SessionSummaryProviderDelegate {
    private final AfxMessageProcessor afxMessageProcessor;
    private final Provider<AggregatedDistanceAlgorithm> aggregatedDistanceAlgorithmProvider;
    private final Provider<CaloriesAlgorithmAdapter> caloriesAlgorithmProvider;
    @NotNull
    private final ComponentRegistry componentRegistry;
    private final Handler delegateHandler;
    @NotNull
    private final HandlerThread delegateHandlerThread;
    private final List<WeakReference<FitnessSessionOrchestratorDelegate>> delegates;
    private final EventBus eventBus;
    private final FeatureService featureService;
    private FitnessMetrics fitnessMetrics;
    private final FitnessNotificationPublisher fitnessNotificationPublisher;
    private final HdsClient hdsClient;
    private SensorAvailability lastKnownSensorAvailability;
    private final ActivityLifecycleObserver lifecycleObserver;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    private final MetricsAggregator metricsAggregator;
    private final SampleStore sampleStore;
    private final List<SensorProvider> sensorProviders;
    private final SessionManager sessionManager;
    private final SessionMetrics sessionMetrics;
    private final SessionSummaryCache sessionSummaryCache;
    private final Lazy<SessionSummaryProvider> sessionSummaryProvider;
    private final Provider<StepsToDistanceAlgorithmAdapter> stepsToDistanceAlgorithmProvider;
    private List<LocationCoordinate> uiSummaryRouteCoordinates;
    private final WorkoutHistory workoutHistory;

    /* compiled from: FitnessSessionOrchestratorImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/fitness/algorithm/StepsToDistanceAlgorithmAdapter;", "kotlin.jvm.PlatformType", "it", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl$2  reason: invalid class name */
    /* loaded from: classes8.dex */
    static final class AnonymousClass2 extends Lambda implements Function1<SessionConfiguration, StepsToDistanceAlgorithmAdapter> {
        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final StepsToDistanceAlgorithmAdapter mo12165invoke(@NotNull SessionConfiguration it2) {
            Intrinsics.checkParameterIsNotNull(it2, "it");
            Object mo10268get = FitnessSessionOrchestratorImpl.this.stepsToDistanceAlgorithmProvider.mo10268get();
            Intrinsics.checkExpressionValueIsNotNull(mo10268get, "stepsToDistanceAlgorithmProvider.get()");
            return (StepsToDistanceAlgorithmAdapter) mo10268get;
        }
    }

    /* compiled from: FitnessSessionOrchestratorImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmAdapter;", "kotlin.jvm.PlatformType", "it", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl$3  reason: invalid class name */
    /* loaded from: classes8.dex */
    static final class AnonymousClass3 extends Lambda implements Function1<SessionConfiguration, CaloriesAlgorithmAdapter> {
        AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final CaloriesAlgorithmAdapter mo12165invoke(@NotNull SessionConfiguration it2) {
            Intrinsics.checkParameterIsNotNull(it2, "it");
            Object mo10268get = FitnessSessionOrchestratorImpl.this.caloriesAlgorithmProvider.mo10268get();
            Intrinsics.checkExpressionValueIsNotNull(mo10268get, "caloriesAlgorithmProvider.get()");
            return (CaloriesAlgorithmAdapter) mo10268get;
        }
    }

    /* compiled from: FitnessSessionOrchestratorImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm;", "kotlin.jvm.PlatformType", "it", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl$4  reason: invalid class name */
    /* loaded from: classes8.dex */
    static final class AnonymousClass4 extends Lambda implements Function1<SessionConfiguration, AggregatedDistanceAlgorithm> {
        AnonymousClass4() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final AggregatedDistanceAlgorithm mo12165invoke(@NotNull SessionConfiguration it2) {
            Intrinsics.checkParameterIsNotNull(it2, "it");
            Object mo10268get = FitnessSessionOrchestratorImpl.this.aggregatedDistanceAlgorithmProvider.mo10268get();
            Intrinsics.checkExpressionValueIsNotNull(mo10268get, "aggregatedDistanceAlgorithmProvider.get()");
            return (AggregatedDistanceAlgorithm) mo10268get;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Inject
    public FitnessSessionOrchestratorImpl(@NotNull ComponentRegistry componentRegistry, @NotNull AfxMessageProcessor afxMessageProcessor, @NotNull HdsClient hdsClient, @NotNull FeatureService featureService, @NotNull FitnessNotificationPublisher fitnessNotificationPublisher, @NotNull Lazy<SessionSummaryProvider> sessionSummaryProvider, @NotNull SessionManager sessionManager, @NotNull List<? extends SensorProvider> sensorProviders, @NotNull MetricEventRecorder metricEventRecorder, @NotNull MetricEventFactory metricEventFactory, @NotNull Provider<StepsToDistanceAlgorithmAdapter> stepsToDistanceAlgorithmProvider, @NotNull Provider<CaloriesAlgorithmAdapter> caloriesAlgorithmProvider, @NotNull Provider<AggregatedDistanceAlgorithm> aggregatedDistanceAlgorithmProvider, @NotNull MetricsAggregator metricsAggregator, @NotNull SessionMetrics sessionMetrics, @NotNull MetricsAggregatorRecovery metricsAggregatorRecovery, @NotNull SampleStore sampleStore, @NotNull SessionSummaryCache sessionSummaryCache, @NotNull EventBus eventBus, @NotNull ILog log) {
        List<FitnessAlgorithmProvider> listOf;
        Intrinsics.checkParameterIsNotNull(componentRegistry, "componentRegistry");
        Intrinsics.checkParameterIsNotNull(afxMessageProcessor, "afxMessageProcessor");
        Intrinsics.checkParameterIsNotNull(hdsClient, "hdsClient");
        Intrinsics.checkParameterIsNotNull(featureService, "featureService");
        Intrinsics.checkParameterIsNotNull(fitnessNotificationPublisher, "fitnessNotificationPublisher");
        Intrinsics.checkParameterIsNotNull(sessionSummaryProvider, "sessionSummaryProvider");
        Intrinsics.checkParameterIsNotNull(sessionManager, "sessionManager");
        Intrinsics.checkParameterIsNotNull(sensorProviders, "sensorProviders");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(stepsToDistanceAlgorithmProvider, "stepsToDistanceAlgorithmProvider");
        Intrinsics.checkParameterIsNotNull(caloriesAlgorithmProvider, "caloriesAlgorithmProvider");
        Intrinsics.checkParameterIsNotNull(aggregatedDistanceAlgorithmProvider, "aggregatedDistanceAlgorithmProvider");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        Intrinsics.checkParameterIsNotNull(sessionMetrics, "sessionMetrics");
        Intrinsics.checkParameterIsNotNull(metricsAggregatorRecovery, "metricsAggregatorRecovery");
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        Intrinsics.checkParameterIsNotNull(sessionSummaryCache, "sessionSummaryCache");
        Intrinsics.checkParameterIsNotNull(eventBus, "eventBus");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.componentRegistry = componentRegistry;
        this.afxMessageProcessor = afxMessageProcessor;
        this.hdsClient = hdsClient;
        this.featureService = featureService;
        this.fitnessNotificationPublisher = fitnessNotificationPublisher;
        this.sessionSummaryProvider = sessionSummaryProvider;
        this.sessionManager = sessionManager;
        this.sensorProviders = sensorProviders;
        this.metricEventRecorder = metricEventRecorder;
        this.metricEventFactory = metricEventFactory;
        this.stepsToDistanceAlgorithmProvider = stepsToDistanceAlgorithmProvider;
        this.caloriesAlgorithmProvider = caloriesAlgorithmProvider;
        this.aggregatedDistanceAlgorithmProvider = aggregatedDistanceAlgorithmProvider;
        this.metricsAggregator = metricsAggregator;
        this.sessionMetrics = sessionMetrics;
        this.sampleStore = sampleStore;
        this.sessionSummaryCache = sessionSummaryCache;
        this.eventBus = eventBus;
        this.log = log;
        List<WeakReference<FitnessSessionOrchestratorDelegate>> synchronizedList = Collections.synchronizedList(new ArrayList());
        Intrinsics.checkExpressionValueIsNotNull(synchronizedList, "Collections.synchronizedList(mutableListOf())");
        this.delegates = synchronizedList;
        this.delegateHandlerThread = new HandlerThread("orchestrator_delegate");
        this.lifecycleObserver = new ActivityLifecycleObserver(metricsAggregatorRecovery, this.metricsAggregator);
        this.workoutHistory = new WorkoutHistory(null, null, false);
        this.componentRegistry.bindConcreteFactory(FitnessSessionOrchestrator.class, new ComponentRegistry.ConcreteComponentFactory<FitnessSessionOrchestrator>() { // from class: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl.1
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            @NotNull
            /* renamed from: create */
            public final FitnessSessionOrchestrator create2(Context context) {
                return FitnessSessionOrchestratorImpl.this;
            }
        });
        ILog iLog = this.log;
        ILog.DefaultImpls.info$default(iLog, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "registered " + this + " with component registry", null, 4, null);
        this.sessionManager.setDelegate(new WeakReference<>(this));
        initSensorProviders();
        FitnessAlgorithmsManager fitnessAlgorithmsManager = this.sessionManager.getFitnessAlgorithmsManager();
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new FitnessAlgorithmProvider[]{new FitnessAlgorithmProvider(StepsToDistanceAlgorithmAdapterKt.STDAlgorithmId, new AnonymousClass2()), new FitnessAlgorithmProvider(CaloriesAlgorithmAdapterKt.CaloriesAlgorithmId, new AnonymousClass3()), new FitnessAlgorithmProvider(AggregatedDistanceAlgorithm.AlgorithmId, new AnonymousClass4())});
        fitnessAlgorithmsManager.register(listOf, WorkoutType.WalkRun);
        this.afxMessageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl.5
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    FitnessSessionOrchestratorImpl.this.sessionManager.receive(Command.AttemptRecovery.INSTANCE);
                    FitnessSessionOrchestratorImpl.this.lifecycleObserver.addObserver();
                } catch (SessionManagerException e) {
                    FitnessSessionOrchestratorImpl.this.log.error(MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "error attempting recovery", e);
                }
            }
        });
        this.delegateHandlerThread.start();
        this.delegateHandler = new Handler(this.delegateHandlerThread.getLooper());
        this.sessionSummaryProvider.mo358get().setDelegate(this);
        this.eventBus.getNewSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS), new MessageHandler() { // from class: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl.6
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(@NotNull Message it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                ILog.DefaultImpls.debug$default(FitnessSessionOrchestratorImpl.this.log, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "user signed out ... clearing cache", null, 4, null);
                FitnessSessionOrchestratorImpl.this.workoutHistory.reset();
            }
        });
    }

    private final void initSensorProviders() {
        List<? extends SensorProvider> mutableList;
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.sensorProviders);
        if (!this.featureService.isLocationTrackingEnabled()) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : mutableList) {
                if (!(((SensorProvider) obj) instanceof LocationSensorProvider)) {
                    arrayList.add(obj);
                }
            }
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList);
        }
        this.sessionManager.initSensorProviders(mutableList);
    }

    private final void onSessionEnded() {
        UserProfile userProfile;
        Session session = this.sessionManager.getSession();
        if (session == null || (userProfile = session.getUserProfile()) == null) {
            return;
        }
        int processingDuration$default = SessionDataModelsKt.processingDuration$default(session, null, 1, null);
        ILog.DefaultImpls.info$default(this.log, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "preparing summary to upload", null, 4, null);
        FitnessMetrics fitnessMetrics = this.fitnessMetrics;
        if (fitnessMetrics == null) {
            ILog.DefaultImpls.error$default(this.log, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "no metrics to save", null, 4, null);
            return;
        }
        if (fitnessMetrics instanceof FitnessMetrics.StepBased) {
            if (fitnessMetrics != null) {
                ActivitySummary activitySummary = FitnessSessionOrchestratorImplKt.toActivitySummary((FitnessMetrics.StepBased) fitnessMetrics, SessionDataModelsKt.duration$default(session, null, 1, null));
                if (this.featureService.isLocationTrackingEnabled()) {
                    UUID sessionId = session.getConfiguration().getSessionId();
                    this.sampleStore.queryAllLocationSamples(sessionId, new FitnessSessionOrchestratorImpl$onSessionEnded$1(this, session, activitySummary, sessionId));
                } else {
                    this.hdsClient.uploadSession(FitnessSessionOrchestratorImplKt.toSessionSummary(activitySummary, session, userProfile), false);
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.afx.FitnessMetrics.StepBased");
            }
        }
        FitnessMetrics fitnessMetrics2 = this.fitnessMetrics;
        if (fitnessMetrics2 != null) {
            this.sessionMetrics.publishFitnessMetricsMetrics(fitnessMetrics2, processingDuration$default);
        }
        this.lifecycleObserver.calculateLifecycleMetrics(processingDuration$default);
        this.metricsAggregator.publishMetrics();
        this.lifecycleObserver.removeObserver();
    }

    private final void recordMetric(String str) {
        this.metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(this.metricEventFactory.createMetricEvent(MetricsClass.FITNESS_SESSION_ORCHESTRATOR), str, 0L, 2, null));
    }

    private final void showOrHideNotificationIfNeeded(FitnessSessionState fitnessSessionState, FitnessSessionState fitnessSessionState2) {
        if (fitnessSessionState2 == FitnessSessionState.STOPPING) {
            this.fitnessNotificationPublisher.hideNotification();
        } else if (!fitnessSessionState.isSessionInitializing()) {
        } else {
            if (fitnessSessionState2 != FitnessSessionState.ACTIVE && fitnessSessionState2 != FitnessSessionState.PAUSED) {
                return;
            }
            this.fitnessNotificationPublisher.showNotification();
        }
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    public void addNewWorkout(@NotNull FitnessSession workout) {
        Intrinsics.checkParameterIsNotNull(workout, "workout");
        WorkoutHistory workoutHistory = this.workoutHistory;
        FitnessSession[] fitnessSessionArr = {workout};
        FitnessSession[] workouts = workoutHistory.getWorkouts();
        if (workouts == null) {
            workouts = new FitnessSession[0];
        }
        workoutHistory.setWorkouts((FitnessSession[]) ArraysKt.plus((Object[]) fitnessSessionArr, (Object[]) workouts));
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    public boolean allRequiredSensorsAvailable() {
        if (this.sensorProviders.isEmpty()) {
            return false;
        }
        for (SensorProvider sensorProvider : this.sensorProviders) {
            if (sensorProvider.getRequired() && !(sensorProvider.getAvailability() instanceof SensorAvailability.Available)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    public void deleteAllWorkouts() {
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "deleting all workouts from cache", null, 4, null);
        this.workoutHistory.setWorkouts(new FitnessSession[0]);
        if (!this.workoutHistory.getHasLoadedInitialWorkouts()) {
            this.workoutHistory.setHasLoadedInitialWorkouts(true);
        }
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    public void deleteWorkout(@NotNull String sessionId) {
        FitnessSession[] fitnessSessionArr;
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        WorkoutHistory workoutHistory = this.workoutHistory;
        FitnessSession[] workouts = workoutHistory.getWorkouts();
        if (workouts != null) {
            ArrayList arrayList = new ArrayList();
            for (FitnessSession fitnessSession : workouts) {
                if (!Intrinsics.areEqual(fitnessSession.getId(), sessionId)) {
                    arrayList.add(fitnessSession);
                }
            }
            Object[] array = arrayList.toArray(new FitnessSession[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            fitnessSessionArr = (FitnessSession[]) array;
        } else {
            fitnessSessionArr = new FitnessSession[0];
        }
        workoutHistory.setWorkouts(fitnessSessionArr);
    }

    @NotNull
    public final ComponentRegistry getComponentRegistry() {
        return this.componentRegistry;
    }

    @NotNull
    public final HandlerThread getDelegateHandlerThread() {
        return this.delegateHandlerThread;
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    public void getLocationList(@NotNull Function1<? super List<LocationCoordinate>, Unit> callback) {
        SessionConfiguration configuration;
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Session session = this.sessionManager.getSession();
        UUID sessionId = (session == null || (configuration = session.getConfiguration()) == null) ? null : configuration.getSessionId();
        if (sessionId != null) {
            this.sampleStore.queryAllLocationSamples(sessionId, new FitnessSessionOrchestratorImpl$getLocationList$$inlined$let$lambda$1(new ArrayList(), this, callback));
        } else {
            ILog.DefaultImpls.error$default(this.log, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "no session to provide location data", null, 4, null);
        }
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    @NotNull
    public UIState getUIState() {
        ILog.DefaultImpls.info$default(this.log, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "UI state requested", null, 4, null);
        return new UIState(this.sessionManager.getSession(), new UISessionSummary(this.uiSummaryRouteCoordinates), this.workoutHistory);
    }

    @Override // com.amazon.alexa.fitness.context.SessionSummaryProviderDelegate
    public void onFitnessMetricsUpdated(@Nullable FitnessMetrics fitnessMetrics) {
        this.fitnessMetrics = fitnessMetrics;
        Iterator<T> it2 = this.delegates.iterator();
        while (it2.hasNext()) {
            FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate = (FitnessSessionOrchestratorDelegate) ((WeakReference) it2.next()).get();
            if (fitnessSessionOrchestratorDelegate != null) {
                fitnessSessionOrchestratorDelegate.onMetricsUpdated(fitnessMetrics);
            }
        }
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    public void receive(@NotNull final Command command, @NotNull final Function2<? super FitnessSessionOrchestrator.CommandProcessingError, ? super FitnessSessionState, Unit> receivedHandler) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        Intrinsics.checkParameterIsNotNull(receivedHandler, "receivedHandler");
        if ((command instanceof Command.Start) && ((Command.Start) command).getSessionConfiguration().getUserProfile() == null) {
            ILog.DefaultImpls.error$default(this.log, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "No user profile when starting a fitness session.", null, 4, null);
            recordMetric(MetricsConstantsKt.buildMetricName(MetricsCategory.FITNESS_PROFILE, MetricsName.MISSING));
            receivedHandler.mo12248invoke(FitnessSessionOrchestrator.CommandProcessingError.NoFitnessProfile, this.sessionManager.getState());
            Iterator<T> it2 = this.delegates.iterator();
            while (it2.hasNext()) {
                FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate = (FitnessSessionOrchestratorDelegate) ((WeakReference) it2.next()).get();
                if (fitnessSessionOrchestratorDelegate != null) {
                    fitnessSessionOrchestratorDelegate.onSessionChanged(null, FitnessSessionOrchestrator.CommandProcessingError.NoFitnessProfile);
                }
            }
            return;
        }
        this.afxMessageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl$receive$2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    FitnessSessionOrchestratorImpl.this.sessionManager.receive(command);
                    receivedHandler.mo12248invoke(null, FitnessSessionOrchestratorImpl.this.sessionManager.getState());
                } catch (SessionManagerException unused) {
                    receivedHandler.mo12248invoke(FitnessSessionOrchestrator.CommandProcessingError.InvalidCommand, FitnessSessionOrchestratorImpl.this.sessionManager.getState());
                }
            }
        });
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    public void registerDelegate(@NotNull final WeakReference<FitnessSessionOrchestratorDelegate> delegate) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        this.delegateHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl$registerDelegate$1
            @Override // java.lang.Runnable
            public final void run() {
                List list;
                SensorAvailability sensorAvailability;
                FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate;
                FitnessSessionState currentState;
                FitnessMetrics fitnessMetrics;
                FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate2;
                List list2;
                list = FitnessSessionOrchestratorImpl.this.delegates;
                boolean z = false;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator it2 = list.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (Intrinsics.areEqual((FitnessSessionOrchestratorDelegate) ((WeakReference) it2.next()).get(), (FitnessSessionOrchestratorDelegate) delegate.get())) {
                                z = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (!z) {
                    list2 = FitnessSessionOrchestratorImpl.this.delegates;
                    list2.add(delegate);
                }
                Session session = FitnessSessionOrchestratorImpl.this.sessionManager.getSession();
                if (session != null && (currentState = SessionDataModelsKt.currentState(session)) != null && currentState.isSessionInProgress()) {
                    FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate3 = (FitnessSessionOrchestratorDelegate) delegate.get();
                    if (fitnessSessionOrchestratorDelegate3 != null) {
                        FitnessSessionOrchestratorDelegate.DefaultImpls.onSessionChanged$default(fitnessSessionOrchestratorDelegate3, FitnessSessionOrchestratorImpl.this.sessionManager.getSession(), null, 2, null);
                    }
                    ILog.DefaultImpls.info$default(FitnessSessionOrchestratorImpl.this.log, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "sending updated metrics", null, 4, null);
                    fitnessMetrics = FitnessSessionOrchestratorImpl.this.fitnessMetrics;
                    if (fitnessMetrics != null && (fitnessSessionOrchestratorDelegate2 = (FitnessSessionOrchestratorDelegate) delegate.get()) != null) {
                        fitnessSessionOrchestratorDelegate2.onMetricsUpdated(fitnessMetrics);
                    }
                }
                sensorAvailability = FitnessSessionOrchestratorImpl.this.lastKnownSensorAvailability;
                if (sensorAvailability == null || (fitnessSessionOrchestratorDelegate = (FitnessSessionOrchestratorDelegate) delegate.get()) == null) {
                    return;
                }
                fitnessSessionOrchestratorDelegate.sensorAvailabilityChanged(sensorAvailability);
            }
        });
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    public void removeDelegate(@NotNull final WeakReference<FitnessSessionOrchestratorDelegate> delegate) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        this.delegateHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl$removeDelegate$1

            /* compiled from: FitnessSessionOrchestratorImpl.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "d", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "invoke"}, k = 3, mv = {1, 1, 16})
            /* renamed from: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl$removeDelegate$1$1  reason: invalid class name */
            /* loaded from: classes8.dex */
            static final class AnonymousClass1 extends Lambda implements Function1<WeakReference<FitnessSessionOrchestratorDelegate>, Boolean> {
                AnonymousClass1() {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                /* renamed from: invoke */
                public /* bridge */ /* synthetic */ Boolean mo12165invoke(WeakReference<FitnessSessionOrchestratorDelegate> weakReference) {
                    return Boolean.valueOf(invoke2(weakReference));
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final boolean invoke2(@NotNull WeakReference<FitnessSessionOrchestratorDelegate> d) {
                    Intrinsics.checkParameterIsNotNull(d, "d");
                    FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate = d.get();
                    if (fitnessSessionOrchestratorDelegate != null) {
                        return Intrinsics.areEqual(fitnessSessionOrchestratorDelegate, (FitnessSessionOrchestratorDelegate) delegate.get());
                    }
                    return false;
                }
            }

            @Override // java.lang.Runnable
            public final void run() {
                List list;
                list = FitnessSessionOrchestratorImpl.this.delegates;
                CollectionsKt__MutableCollectionsKt.removeAll((List) list, (Function1) new AnonymousClass1());
            }
        });
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManagerDelegate
    public void sensorAvailabilityChanged(@NotNull SensorAvailability availability, @NotNull String sensorId) {
        Intrinsics.checkParameterIsNotNull(availability, "availability");
        Intrinsics.checkParameterIsNotNull(sensorId, "sensorId");
        Session session = this.sessionManager.getSession();
        List<String> sensorIds = session != null ? session.getSensorIds() : null;
        if (sensorIds == null || sensorIds.isEmpty() || sensorIds.contains(sensorId)) {
            Iterator<T> it2 = this.delegates.iterator();
            while (it2.hasNext()) {
                FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate = (FitnessSessionOrchestratorDelegate) ((WeakReference) it2.next()).get();
                if (fitnessSessionOrchestratorDelegate != null) {
                    fitnessSessionOrchestratorDelegate.sensorAvailabilityChanged(availability);
                }
            }
            this.lastKnownSensorAvailability = availability;
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.SessionManagerDelegate
    public void stateDidChange(@NotNull FitnessSessionState oldState, @NotNull FitnessSessionTransition transition, @NotNull FitnessSessionState newState, @Nullable SensorError sensorError) {
        Intrinsics.checkParameterIsNotNull(oldState, "oldState");
        Intrinsics.checkParameterIsNotNull(transition, "transition");
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        if (newState == FitnessSessionState.STOPPING) {
            Session session = this.sessionManager.getSession();
            this.uiSummaryRouteCoordinates = session != null ? session.getCoordinates() : null;
            ILog iLog = this.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("captured ");
            List<LocationCoordinate> list = this.uiSummaryRouteCoordinates;
            outline107.append(list != null ? Integer.valueOf(list.size()) : null);
            outline107.append(" route coordinates on newState=");
            outline107.append(newState);
            ILog.DefaultImpls.info$default(iLog, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, outline107.toString(), null, 4, null);
        }
        this.sessionSummaryProvider.mo358get().onSessionStateChanged(newState, this.sessionManager.getSession());
        Iterator<T> it2 = this.delegates.iterator();
        while (it2.hasNext()) {
            FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate = (FitnessSessionOrchestratorDelegate) ((WeakReference) it2.next()).get();
            if (fitnessSessionOrchestratorDelegate != null) {
                FitnessSessionOrchestratorDelegate.DefaultImpls.onSessionChanged$default(fitnessSessionOrchestratorDelegate, this.sessionManager.getSession(), null, 2, null);
            }
        }
        this.lifecycleObserver.onSessionStateChanged(newState);
        if (transition == FitnessSessionTransition.STOPPED) {
            Session session2 = this.sessionManager.getSession();
            FitnessMetrics fitnessMetrics = this.fitnessMetrics;
            if (session2 != null && fitnessMetrics != null) {
                Iterator<T> it3 = this.delegates.iterator();
                while (it3.hasNext()) {
                    FitnessSessionOrchestratorDelegate fitnessSessionOrchestratorDelegate2 = (FitnessSessionOrchestratorDelegate) ((WeakReference) it3.next()).get();
                    if (fitnessSessionOrchestratorDelegate2 != null) {
                        fitnessSessionOrchestratorDelegate2.onSessionEnded(session2, fitnessMetrics);
                    }
                }
            }
        }
        showOrHideNotificationIfNeeded(oldState, newState);
        if (newState == FitnessSessionState.STOPPING) {
            onSessionEnded();
        }
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator
    public void updateWorkouts(@NotNull FitnessSession[] workouts, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(workouts, "workouts");
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_SESSION_ORCHESTRATOR, "updating workouts", null, 4, null);
        WorkoutHistory workoutHistory = this.workoutHistory;
        FitnessSession[] workouts2 = workoutHistory.getWorkouts();
        if (workouts2 == null) {
            workouts2 = new FitnessSession[0];
        }
        workoutHistory.setWorkouts((FitnessSession[]) ArraysKt.plus((Object[]) workouts2, (Object[]) workouts));
        this.workoutHistory.setNextToken(str);
    }
}
