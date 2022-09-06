package com.amazon.alexa.fitness.service;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.afx.FitnessRoutesKt;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.UserProfileRepository;
import com.amazon.alexa.fitness.api.fitnessSdk.Coaching;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionCommandSource;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import com.amazon.alexa.fitness.api.fitnessSdk.WorkoutType;
import com.amazon.alexa.fitness.configuration.AlexaFitnessCapabilityAgentServiceConfiguration;
import com.amazon.alexa.fitness.configuration.AlexaFitnessCapabilityAgentServiceConfigurationProvider;
import com.amazon.alexa.fitness.context.AlexaFitnessContextManager;
import com.amazon.alexa.fitness.context.AlexaFitnessContextsProvider;
import com.amazon.alexa.fitness.dagger.StaticReleasePackageComponentHolder;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.message.FitnessSessionEventEmitter;
import com.amazon.alexa.fitness.message.SpeechletEventEmitter;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.fitness.model.directive.AlexaFitnessDirectivePayload;
import com.amazon.alexa.fitness.model.directive.DirectiveSession;
import com.amazon.alexa.fitness.model.directive.FitnessDirective;
import com.amazon.alexa.fitness.model.directive.FitnessDirectiveKt;
import com.amazon.alexa.fitness.model.event.Error;
import com.amazon.alexa.fitness.model.event.Operation;
import com.amazon.alexa.fitness.sdk.FitnessSdk;
import com.amazon.alexa.fitness.sdk.SessionManager;
import com.amazon.alexa.fitness.session.FitnessSessionStateService;
import com.amazon.alexa.fitness.util.GsonUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonParseException;
import dagger.Subcomponent;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
/* compiled from: AlexaFitnessCapabilityAgentService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001>B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(H\u0016J*\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u000203H\u0002J \u00104\u001a\u00020+2\u0006\u0010.\u001a\u00020/2\u0006\u00102\u001a\u0002032\u0006\u0010,\u001a\u00020-H\u0002J\b\u00105\u001a\u00020+H\u0007J\b\u00106\u001a\u00020+H\u0016J\b\u00107\u001a\u00020+H\u0016J\u0010\u00108\u001a\u00020\u00182\u0006\u00109\u001a\u00020:H\u0014J\u0010\u0010;\u001a\u00020+2\u0006\u0010<\u001a\u00020=H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/amazon/alexa/fitness/service/AlexaFitnessCapabilityAgentService;", "Lcom/amazon/alexa/api/AlexaCapabilityAgentService;", "()V", "alexaFitnessContextManager", "Lcom/amazon/alexa/fitness/context/AlexaFitnessContextManager;", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/alexa/fitness/configuration/AlexaFitnessCapabilityAgentServiceConfiguration;", "getConfiguration", "()Lcom/amazon/alexa/fitness/configuration/AlexaFitnessCapabilityAgentServiceConfiguration;", "configurationProvider", "Lcom/amazon/alexa/fitness/configuration/AlexaFitnessCapabilityAgentServiceConfigurationProvider;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "fitnessSessionEventEmitter", "Lcom/amazon/alexa/fitness/message/FitnessSessionEventEmitter;", "fitnessSessionOrchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "fitnessSessionStateService", "Lcom/amazon/alexa/fitness/session/FitnessSessionStateService;", "identityManager", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "instrumentedAlexaServicesConnection", "Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnection;", "isInitialized", "", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "routingService", "Lcom/amazon/alexa/routing/api/RoutingService;", "sessionManager", "Lcom/amazon/alexa/fitness/sdk/SessionManager;", "speechletEventEmitter", "Lcom/amazon/alexa/fitness/message/SpeechletEventEmitter;", "userProfileRepository", "Lcom/amazon/alexa/fitness/api/afx/UserProfileRepository;", "getCapabilities", "", "Lcom/amazon/alexa/api/AlexaCapability;", "handleCommandResult", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "command", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "error", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "currentState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "handleInvalidStateTransition", "initializeComponents", "onCreate", "onDestroy", "process", "alexaDirective", "Lcom/amazon/alexa/api/AlexaDirective;", "recordMetric", "operation", "", "SubComponent", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AlexaFitnessCapabilityAgentService extends AlexaCapabilityAgentService {
    private AlexaFitnessContextManager alexaFitnessContextManager;
    private AlexaFitnessCapabilityAgentServiceConfigurationProvider configurationProvider;
    private FeatureService featureService;
    private FitnessSessionEventEmitter fitnessSessionEventEmitter;
    private FitnessSessionOrchestrator fitnessSessionOrchestrator;
    private FitnessSessionStateService fitnessSessionStateService;
    private IdentityManager identityManager;
    private InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection;
    private boolean isInitialized;
    private ILog log;
    private MetricEventFactory metricEventFactory;
    private MetricEventRecorder metricEventRecorder;
    private RoutingService routingService;
    private SessionManager sessionManager;
    private SpeechletEventEmitter speechletEventEmitter;
    private UserProfileRepository userProfileRepository;

    /* compiled from: AlexaFitnessCapabilityAgentService.kt */
    @Subcomponent
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0019H&J\b\u0010\u001a\u001a\u00020\u001bH&J\b\u0010\u001c\u001a\u00020\u001dH&J\b\u0010\u001e\u001a\u00020\u001fH&¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/service/AlexaFitnessCapabilityAgentService$SubComponent;", "", "alexaFitnessContextManager", "Lcom/amazon/alexa/fitness/context/AlexaFitnessContextManager;", "alexaFitnessContextsProvider", "Lcom/amazon/alexa/fitness/context/AlexaFitnessContextsProvider;", "configurationProvider", "Lcom/amazon/alexa/fitness/configuration/AlexaFitnessCapabilityAgentServiceConfigurationProvider;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "fitnessSessionEventEmitter", "Lcom/amazon/alexa/fitness/message/FitnessSessionEventEmitter;", "fitnessSessionOrchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "fitnessSessionStateManager", "Lcom/amazon/alexa/fitness/session/FitnessSessionStateService;", "iLog", "Lcom/amazon/alexa/fitness/logs/ILog;", "identityManager", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "instrumentedAlexaServicesConnection", "Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnection;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "routingService", "Lcom/amazon/alexa/routing/api/RoutingService;", "speechletEventEmitter", "Lcom/amazon/alexa/fitness/message/SpeechletEventEmitter;", "userProfileRepository", "Lcom/amazon/alexa/fitness/api/afx/UserProfileRepository;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public interface SubComponent {
        @NotNull
        AlexaFitnessContextManager alexaFitnessContextManager();

        @NotNull
        AlexaFitnessContextsProvider alexaFitnessContextsProvider();

        @NotNull
        AlexaFitnessCapabilityAgentServiceConfigurationProvider configurationProvider();

        @NotNull
        FeatureService featureService();

        @NotNull
        FitnessSessionEventEmitter fitnessSessionEventEmitter();

        @NotNull
        FitnessSessionOrchestrator fitnessSessionOrchestrator();

        @NotNull
        FitnessSessionStateService fitnessSessionStateManager();

        @NotNull
        ILog iLog();

        @NotNull
        IdentityManager identityManager();

        @NotNull
        InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection();

        @NotNull
        MetricEventFactory metricEventFactory();

        @NotNull
        MetricEventRecorder metricEventRecorder();

        @NotNull
        RoutingService routingService();

        @NotNull
        SpeechletEventEmitter speechletEventEmitter();

        @NotNull
        UserProfileRepository userProfileRepository();
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessDirective.values().length];

        static {
            $EnumSwitchMapping$0[FitnessDirective.START_WORKOUT.ordinal()] = 1;
            $EnumSwitchMapping$0[FitnessDirective.STOP_WORKOUT.ordinal()] = 2;
            $EnumSwitchMapping$0[FitnessDirective.PAUSE_WORKOUT.ordinal()] = 3;
            $EnumSwitchMapping$0[FitnessDirective.RESUME_WORKOUT.ordinal()] = 4;
        }
    }

    private final AlexaFitnessCapabilityAgentServiceConfiguration getConfiguration() {
        AlexaFitnessCapabilityAgentServiceConfigurationProvider alexaFitnessCapabilityAgentServiceConfigurationProvider = this.configurationProvider;
        if (alexaFitnessCapabilityAgentServiceConfigurationProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("configurationProvider");
        }
        return alexaFitnessCapabilityAgentServiceConfigurationProvider.provideAlexaCapabilityAgentServiceConfiguration();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleCommandResult(UUID uuid, Command command, FitnessSessionOrchestrator.CommandProcessingError commandProcessingError, FitnessSessionState fitnessSessionState) {
        if (commandProcessingError != null) {
            ILog iLog = this.log;
            if (iLog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("log");
            }
            ILog.DefaultImpls.error$default(iLog, "AlexaFitnessCapabilityAgentService", "Error processing " + command + " - " + commandProcessingError.name(), null, 4, null);
            boolean z = command instanceof Command.Start;
            if (z) {
                recordMetric(MetricsConstantsKt.buildMetricName(MetricsOperation.START_WORKOUT, "Failure"));
            } else if (command instanceof Command.Stop) {
                recordMetric(MetricsConstantsKt.buildMetricName(MetricsOperation.STOP_WORKOUT, "Failure"));
            } else if (command instanceof Command.Pause) {
                recordMetric(MetricsConstantsKt.buildMetricName(MetricsOperation.PAUSE_WORKOUT, "Failure"));
            } else if (command instanceof Command.Resume) {
                recordMetric(MetricsConstantsKt.buildMetricName(MetricsOperation.RESUME_WORKOUT, "Failure"));
            }
            if (commandProcessingError == FitnessSessionOrchestrator.CommandProcessingError.InvalidCommand) {
                handleInvalidStateTransition(command, fitnessSessionState, uuid);
            }
            if (commandProcessingError != FitnessSessionOrchestrator.CommandProcessingError.NoFitnessProfile || !z) {
                return;
            }
            RoutingService routingService = this.routingService;
            if (routingService == null) {
                Intrinsics.throwUninitializedPropertyAccessException("routingService");
            }
            RoutingService.RoutingBuilder match = routingService.match(FitnessRoutesKt.USER_PROFILE_ROUTE);
            if (match != null) {
                match.navigate();
            }
            ILog iLog2 = this.log;
            if (iLog2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("log");
            }
            ILog.DefaultImpls.info$default(iLog2, "AlexaFitnessCapabilityAgentService", "routing to user profile screen", null, 4, null);
        }
    }

    private final void handleInvalidStateTransition(Command command, FitnessSessionState fitnessSessionState, UUID uuid) {
        if (command instanceof Command.Start) {
            FitnessSessionEventEmitter fitnessSessionEventEmitter = this.fitnessSessionEventEmitter;
            if (fitnessSessionEventEmitter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionEventEmitter");
            }
            fitnessSessionEventEmitter.sendFitnessSessionError(uuid, Error.ALREADY_EXISTS, Operation.START);
            recordMetric(MetricsConstantsKt.buildMetricErrorName(MetricsOperation.START_WORKOUT, MetricsCategory.FITNESS_SESSION, MetricsName.ALREADY_EXISTS));
            ILog iLog = this.log;
            if (iLog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("log");
            }
            ILog.DefaultImpls.error$default(iLog, "AlexaFitnessCapabilityAgentService", "session already in progress when start command was received", null, 4, null);
        } else if (command instanceof Command.Stop) {
            ILog iLog2 = this.log;
            if (iLog2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("log");
            }
            ILog.DefaultImpls.error$default(iLog2, "AlexaFitnessCapabilityAgentService", "session not in progress when stop command was received", null, 4, null);
            FitnessSessionEventEmitter fitnessSessionEventEmitter2 = this.fitnessSessionEventEmitter;
            if (fitnessSessionEventEmitter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionEventEmitter");
            }
            fitnessSessionEventEmitter2.sendFitnessSessionError(uuid, Error.DOES_NOT_EXIST, Operation.STOP);
            recordMetric(MetricsConstantsKt.buildMetricErrorName(MetricsOperation.STOP_WORKOUT, MetricsCategory.FITNESS_SESSION, MetricsName.NOT_ACTIVE));
        } else if (command instanceof Command.Pause) {
            if (fitnessSessionState.isSessionInactive()) {
                ILog iLog3 = this.log;
                if (iLog3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("log");
                }
                ILog.DefaultImpls.error$default(iLog3, "AlexaFitnessCapabilityAgentService", "session not active when pause command was received", null, 4, null);
                FitnessSessionEventEmitter fitnessSessionEventEmitter3 = this.fitnessSessionEventEmitter;
                if (fitnessSessionEventEmitter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionEventEmitter");
                }
                fitnessSessionEventEmitter3.sendFitnessSessionError(uuid, Error.DOES_NOT_EXIST, Operation.PAUSE);
                recordMetric(MetricsConstantsKt.buildMetricErrorName(MetricsOperation.PAUSE_WORKOUT, MetricsCategory.FITNESS_SESSION, MetricsName.NOT_ACTIVE));
            } else if (fitnessSessionState.isSessionPaused()) {
                ILog iLog4 = this.log;
                if (iLog4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("log");
                }
                ILog.DefaultImpls.error$default(iLog4, "AlexaFitnessCapabilityAgentService", "session already paused when pause command was received", null, 4, null);
                FitnessSessionEventEmitter fitnessSessionEventEmitter4 = this.fitnessSessionEventEmitter;
                if (fitnessSessionEventEmitter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionEventEmitter");
                }
                fitnessSessionEventEmitter4.sendFitnessSessionError(uuid, Error.ALREADY_EXISTS, Operation.PAUSE);
                recordMetric(MetricsConstantsKt.buildMetricErrorName(MetricsOperation.PAUSE_WORKOUT, MetricsCategory.FITNESS_SESSION, MetricsName.ALREADY_EXISTS));
            } else {
                ILog iLog5 = this.log;
                if (iLog5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("log");
                }
                ILog.DefaultImpls.error$default(iLog5, "AlexaFitnessCapabilityAgentService", "Unexpected error while processing pause when current state is " + fitnessSessionState, null, 4, null);
            }
        } else if (!(command instanceof Command.Resume)) {
        } else {
            if (fitnessSessionState.isSessionInactive()) {
                ILog iLog6 = this.log;
                if (iLog6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("log");
                }
                ILog.DefaultImpls.error$default(iLog6, "AlexaFitnessCapabilityAgentService", "session not active when resume command was received", null, 4, null);
                FitnessSessionEventEmitter fitnessSessionEventEmitter5 = this.fitnessSessionEventEmitter;
                if (fitnessSessionEventEmitter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionEventEmitter");
                }
                fitnessSessionEventEmitter5.sendFitnessSessionError(uuid, Error.DOES_NOT_EXIST, Operation.RESUME);
                recordMetric(MetricsConstantsKt.buildMetricErrorName(MetricsOperation.RESUME_WORKOUT, MetricsCategory.FITNESS_SESSION, MetricsName.NOT_ACTIVE));
            } else if (fitnessSessionState.isSessionActive()) {
                ILog iLog7 = this.log;
                if (iLog7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("log");
                }
                ILog.DefaultImpls.error$default(iLog7, "AlexaFitnessCapabilityAgentService", "session already active when resume command was received", null, 4, null);
                FitnessSessionEventEmitter fitnessSessionEventEmitter6 = this.fitnessSessionEventEmitter;
                if (fitnessSessionEventEmitter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionEventEmitter");
                }
                fitnessSessionEventEmitter6.sendFitnessSessionError(uuid, Error.ALREADY_EXISTS, Operation.RESUME);
                recordMetric(MetricsConstantsKt.buildMetricErrorName(MetricsOperation.RESUME_WORKOUT, MetricsCategory.FITNESS_SESSION, MetricsName.ALREADY_EXISTS));
            } else {
                ILog iLog8 = this.log;
                if (iLog8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("log");
                }
                ILog.DefaultImpls.error$default(iLog8, "AlexaFitnessCapabilityAgentService", "Unexpected error while processing resume when current state is " + fitnessSessionState, null, 4, null);
            }
        }
    }

    private final void recordMetric(String str) {
        MetricEventRecorder metricEventRecorder = this.metricEventRecorder;
        if (metricEventRecorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metricEventRecorder");
        }
        MetricEventFactory metricEventFactory = this.metricEventFactory;
        if (metricEventFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metricEventFactory");
        }
        metricEventRecorder.record(MetricEvent.DefaultImpls.incrementCounter$default(metricEventFactory.createMetricEvent(MetricsClass.FITNESS_CAPABILITY_AGENT), str, 0L, 2, null));
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    @NotNull
    public Set<AlexaCapability> getCapabilities() {
        if (!this.isInitialized) {
            return SetsKt.emptySet();
        }
        ILog iLog = this.log;
        if (iLog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("log");
        }
        ILog.DefaultImpls.info$default(iLog, "AlexaFitnessCapabilityAgentService", "getCapabilities() invoked...", null, 4, null);
        return SetsKt.setOf(AlexaCapability.createWithConfigurations(getConfiguration().getAlexaCapabilityInterfaceName(), getConfiguration().getAlexaCapabilityInterfaceVersion(), new JSONObject(getConfiguration().getAlexaCapabilityInterfaceConfigurations())));
    }

    @VisibleForTesting(otherwise = 2)
    public final void initializeComponents() {
        SubComponent alexaFitnessCapabilityAgentServiceSubComponent = StaticReleasePackageComponentHolder.getPackageComponent().alexaFitnessCapabilityAgentServiceSubComponent();
        this.log = alexaFitnessCapabilityAgentServiceSubComponent.iLog();
        this.featureService = alexaFitnessCapabilityAgentServiceSubComponent.featureService();
        this.identityManager = alexaFitnessCapabilityAgentServiceSubComponent.identityManager();
        AlexaFitnessContextManager alexaFitnessContextManager = alexaFitnessCapabilityAgentServiceSubComponent.alexaFitnessContextManager();
        alexaFitnessContextManager.start();
        this.alexaFitnessContextManager = alexaFitnessContextManager;
        this.fitnessSessionOrchestrator = alexaFitnessCapabilityAgentServiceSubComponent.fitnessSessionOrchestrator();
        this.configurationProvider = alexaFitnessCapabilityAgentServiceSubComponent.configurationProvider();
        this.fitnessSessionStateService = alexaFitnessCapabilityAgentServiceSubComponent.fitnessSessionStateManager();
        this.fitnessSessionEventEmitter = alexaFitnessCapabilityAgentServiceSubComponent.fitnessSessionEventEmitter();
        this.metricEventFactory = alexaFitnessCapabilityAgentServiceSubComponent.metricEventFactory();
        this.metricEventRecorder = alexaFitnessCapabilityAgentServiceSubComponent.metricEventRecorder();
        InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection = alexaFitnessCapabilityAgentServiceSubComponent.instrumentedAlexaServicesConnection();
        instrumentedAlexaServicesConnection.start();
        this.instrumentedAlexaServicesConnection = instrumentedAlexaServicesConnection;
        SpeechletEventEmitter speechletEventEmitter = alexaFitnessCapabilityAgentServiceSubComponent.speechletEventEmitter();
        speechletEventEmitter.start();
        this.speechletEventEmitter = speechletEventEmitter;
        this.userProfileRepository = alexaFitnessCapabilityAgentServiceSubComponent.userProfileRepository();
        this.routingService = alexaFitnessCapabilityAgentServiceSubComponent.routingService();
        this.sessionManager = StaticReleasePackageComponentHolder.getPackageComponent().fitnessSdkSubComponent().sessionManager();
        this.isInitialized = true;
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        StaticReleasePackageComponentHolder.initializePackageComponent(getApplicationContext());
        FitnessSdk fitnessSdk = new FitnessSdk();
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        fitnessSdk.initialize(applicationContext);
        initializeComponents();
        ILog iLog = this.log;
        if (iLog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("log");
        }
        ILog.DefaultImpls.info$default(iLog, "AlexaFitnessCapabilityAgentService", "onCreate() completed...", null, 4, null);
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onDestroy() {
        ILog iLog = this.log;
        if (iLog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("log");
        }
        ILog.DefaultImpls.debug$default(iLog, "AlexaFitnessCapabilityAgentService", "onDestroy() invoked...", null, 4, null);
        super.onDestroy();
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    protected boolean process(@NotNull AlexaDirective alexaDirective) {
        Intrinsics.checkParameterIsNotNull(alexaDirective, "alexaDirective");
        if (this.isInitialized) {
            FeatureService featureService = this.featureService;
            if (featureService == null) {
                Intrinsics.throwUninitializedPropertyAccessException("featureService");
            }
            if (featureService.isFitnessEnabled()) {
                ILog iLog = this.log;
                if (iLog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("log");
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("start processing directive: ");
                outline107.append(alexaDirective.getNamespace());
                outline107.append('.');
                outline107.append(alexaDirective.getName());
                ILog.DefaultImpls.info$default(iLog, "AlexaFitnessCapabilityAgentService", outline107.toString(), null, 4, null);
                MetricEventFactory metricEventFactory = this.metricEventFactory;
                if (metricEventFactory == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metricEventFactory");
                }
                MetricEvent createMetricEvent = metricEventFactory.createMetricEvent(MetricsClass.FITNESS_CAPABILITY_AGENT);
                FitnessDirective asFitnessDirective = FitnessDirectiveKt.asFitnessDirective(alexaDirective.getName());
                if (asFitnessDirective == null) {
                    MetricEvent.DefaultImpls.incrementCounter$default(createMetricEvent, MetricsConstantsKt.buildMetricErrorName(MetricsOperation.PROCESS_FITNESS_DIRECTIVE, MetricsCategory.DIRECTIVE, MetricsName.INVALID), 0L, 2, null);
                    MetricEventRecorder metricEventRecorder = this.metricEventRecorder;
                    if (metricEventRecorder == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("metricEventRecorder");
                    }
                    metricEventRecorder.record(createMetricEvent);
                    ILog iLog2 = this.log;
                    if (iLog2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("log");
                    }
                    ILog.DefaultImpls.error$default(iLog2, "AlexaFitnessCapabilityAgentService", "Failed to process invalid directive.", null, 4, null);
                    return false;
                }
                AlexaPayload alexaPayload = alexaDirective.getAlexaPayload();
                Intrinsics.checkExpressionValueIsNotNull(alexaPayload, "alexaDirective.alexaPayload");
                String payload = alexaPayload.getPayload();
                Intrinsics.checkExpressionValueIsNotNull(payload, "alexaDirective.alexaPayload.payload");
                try {
                    if (!StringsKt.isBlank(payload)) {
                        Object fromJson = GsonUtils.Companion.getGson().fromJson(payload, (Class<Object>) AlexaFitnessDirectivePayload.class);
                        Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(this, T::class.java)");
                        DirectiveSession session = ((AlexaFitnessDirectivePayload) fromJson).getSession();
                        int i = WhenMappings.$EnumSwitchMapping$0[asFitnessDirective.ordinal()];
                        if (i == 1) {
                            FitnessSessionStateService fitnessSessionStateService = this.fitnessSessionStateService;
                            if (fitnessSessionStateService == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionStateService");
                            }
                            if (fitnessSessionStateService.isFitnessSessionUserPaused()) {
                                ILog iLog3 = this.log;
                                if (iLog3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("log");
                                }
                                ILog.DefaultImpls.error$default(iLog3, "AlexaFitnessCapabilityAgentService", "Received START directive but RESUMING workout since it was USER-PAUSED.", null, 4, null);
                                Command.Resume resume = new Command.Resume(SessionCommandSource.VUI);
                                FitnessSessionOrchestrator fitnessSessionOrchestrator = this.fitnessSessionOrchestrator;
                                if (fitnessSessionOrchestrator == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionOrchestrator");
                                }
                                fitnessSessionOrchestrator.receive(resume, new AlexaFitnessCapabilityAgentService$process$$inlined$run$lambda$1(this, resume, session));
                            } else {
                                SessionCommandSource sessionCommandSource = SessionCommandSource.VUI;
                                UUID id = session.getId();
                                UserProfileRepository userProfileRepository = this.userProfileRepository;
                                if (userProfileRepository == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("userProfileRepository");
                                }
                                Command.Start start = new Command.Start(sessionCommandSource, new SessionConfiguration(id, userProfileRepository.getUserProfileForCurrentUser(), WorkoutType.WalkRun, Coaching.None.INSTANCE));
                                FitnessSessionOrchestrator fitnessSessionOrchestrator2 = this.fitnessSessionOrchestrator;
                                if (fitnessSessionOrchestrator2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionOrchestrator");
                                }
                                fitnessSessionOrchestrator2.receive(start, new AlexaFitnessCapabilityAgentService$process$$inlined$run$lambda$2(this, start, session));
                            }
                        } else if (i == 2) {
                            Command.Stop stop = new Command.Stop(SessionCommandSource.VUI);
                            FitnessSessionOrchestrator fitnessSessionOrchestrator3 = this.fitnessSessionOrchestrator;
                            if (fitnessSessionOrchestrator3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionOrchestrator");
                            }
                            fitnessSessionOrchestrator3.receive(stop, new AlexaFitnessCapabilityAgentService$process$2(this, session, stop));
                        } else if (i == 3) {
                            Command.Pause pause = new Command.Pause(SessionCommandSource.VUI);
                            FitnessSessionOrchestrator fitnessSessionOrchestrator4 = this.fitnessSessionOrchestrator;
                            if (fitnessSessionOrchestrator4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionOrchestrator");
                            }
                            fitnessSessionOrchestrator4.receive(pause, new AlexaFitnessCapabilityAgentService$process$3(this, session, pause));
                        } else if (i == 4) {
                            Command.Resume resume2 = new Command.Resume(SessionCommandSource.VUI);
                            FitnessSessionOrchestrator fitnessSessionOrchestrator5 = this.fitnessSessionOrchestrator;
                            if (fitnessSessionOrchestrator5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("fitnessSessionOrchestrator");
                            }
                            fitnessSessionOrchestrator5.receive(resume2, new AlexaFitnessCapabilityAgentService$process$4(this, session, resume2));
                        }
                        ILog iLog4 = this.log;
                        if (iLog4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("log");
                        }
                        ILog.DefaultImpls.debug$default(iLog4, "AlexaFitnessCapabilityAgentService", "Finished successfully processing directive.", null, 4, null);
                        return true;
                    }
                    throw new JsonParseException("Cannot parse blank JSON String.");
                } catch (JsonParseException e) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline73("Failed to deserialize JSON String: '", payload, Chars.QUOTE), e);
                }
            }
        }
        return false;
    }
}
