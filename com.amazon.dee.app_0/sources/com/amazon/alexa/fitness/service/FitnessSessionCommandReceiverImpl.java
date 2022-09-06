package com.amazon.alexa.fitness.service;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.configuration.FitnessSessionCommandReceiverConfiguration;
import com.amazon.alexa.fitness.configuration.FitnessSessionCommandReceiverConfigurationProvider;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.fitness.model.event.Error;
import com.amazon.alexa.fitness.model.event.Operation;
import com.amazon.alexa.fitness.util.GsonUtils;
import com.amazon.alexa.fitness.util.GuavaUtilsKt;
import com.amazon.alexa.fitness.util.MetricsTrackingCallback;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.gson.JsonParseException;
import java.util.Locale;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionCommandReceiverImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001+B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\u0014\u0010 \u001a\u00020\u00162\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"H\u0002J\b\u0010#\u001a\u00020\u0016H\u0016J\u000e\u0010$\u001a\u00020%*\u0004\u0018\u00010&H\u0002J\u000e\u0010'\u001a\u00020%*\u0004\u0018\u00010&H\u0002J\u000e\u0010(\u001a\u0004\u0018\u00010)*\u00020&H\u0002J\f\u0010*\u001a\u00020\u001b*\u00020&H\u0002R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/amazon/alexa/fitness/service/FitnessSessionCommandReceiverImpl;", "Lcom/amazon/alexa/fitness/service/FitnessSessionCommandReceiver;", "configurationProvider", "Lcom/amazon/alexa/fitness/configuration/FitnessSessionCommandReceiverConfigurationProvider;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/configuration/FitnessSessionCommandReceiverConfigurationProvider;Lcom/amazon/alexa/fitness/api/afx/FeatureService;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/logs/ILog;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/alexa/fitness/configuration/FitnessSessionCommandReceiverConfiguration;", "getConfiguration", "()Lcom/amazon/alexa/fitness/configuration/FitnessSessionCommandReceiverConfiguration;", "mainHandler", "Landroid/os/Handler;", "getMainHandler", "()Landroid/os/Handler;", "handleFitnessSessionCommandReceiverUpdatedEvent", "", "message", "Lcom/amazon/alexa/eventbus/api/Message;", "handleWorkoutOperation", "operation", "Lcom/amazon/alexa/fitness/model/event/Operation;", "handleWorkoutPauseOperation", "handleWorkoutResumeOperation", "handleWorkoutStartOperation", "handleWorkoutStopOperation", "sendFitnessSessionCommandReceiverNotifyEventWithCurrentWorkoutState", "error", "Lcom/amazon/alexa/fitness/model/event/Error;", "start", "isFitnessSessionCommandReceiverRequestEvent", "", "", "isFitnessSessionCommandReceiverUpdateEvent", "parseAsFitnessSessionCommand", "Lcom/amazon/alexa/fitness/service/FitnessSessionCommandReceiverImpl$FitnessSessionCommand;", "toWorkoutOperation", "FitnessSessionCommand", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionCommandReceiverImpl implements FitnessSessionCommandReceiver {
    private final FitnessSessionCommandReceiverConfigurationProvider configurationProvider;
    private final FeatureService featureService;
    private final ILog log;
    @NotNull
    private final Handler mainHandler;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FitnessSessionCommandReceiverImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/service/FitnessSessionCommandReceiverImpl$FitnessSessionCommand;", "", "operation", "", "(Ljava/lang/String;)V", "getOperation", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class FitnessSessionCommand {
        @NotNull
        private final String operation;

        public FitnessSessionCommand(@NotNull String operation) {
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            this.operation = operation;
        }

        public static /* synthetic */ FitnessSessionCommand copy$default(FitnessSessionCommand fitnessSessionCommand, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = fitnessSessionCommand.operation;
            }
            return fitnessSessionCommand.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.operation;
        }

        @NotNull
        public final FitnessSessionCommand copy(@NotNull String operation) {
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            return new FitnessSessionCommand(operation);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof FitnessSessionCommand) && Intrinsics.areEqual(this.operation, ((FitnessSessionCommand) obj).operation);
            }
            return true;
        }

        @NotNull
        public final String getOperation() {
            return this.operation;
        }

        public int hashCode() {
            String str = this.operation;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("FitnessSessionCommand(operation="), this.operation, ")");
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Operation.values().length];

        static {
            $EnumSwitchMapping$0[Operation.START.ordinal()] = 1;
            $EnumSwitchMapping$0[Operation.STOP.ordinal()] = 2;
            $EnumSwitchMapping$0[Operation.PAUSE.ordinal()] = 3;
            $EnumSwitchMapping$0[Operation.RESUME.ordinal()] = 4;
        }
    }

    @Inject
    public FitnessSessionCommandReceiverImpl(@NotNull FitnessSessionCommandReceiverConfigurationProvider configurationProvider, @NotNull FeatureService featureService, @NotNull MetricEventFactory metricEventFactory, @NotNull MetricEventRecorder metricEventRecorder, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(configurationProvider, "configurationProvider");
        Intrinsics.checkParameterIsNotNull(featureService, "featureService");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.configurationProvider = configurationProvider;
        this.featureService = featureService;
        this.metricEventFactory = metricEventFactory;
        this.metricEventRecorder = metricEventRecorder;
        this.log = log;
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    private final FitnessSessionCommandReceiverConfiguration getConfiguration() {
        return this.configurationProvider.provideFitnessSessionCommandReceiverConfiguration();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFitnessSessionCommandReceiverUpdatedEvent(Message message) {
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("User profile update event payload: ");
        outline107.append(message.getPayloadAsString());
        ILog.DefaultImpls.debug$default(iLog, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, outline107.toString(), null, 4, null);
        if (!this.featureService.isFitnessEnabled()) {
            ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "Featuregate disabled, no-op for Update event.", null, 4, null);
            return;
        }
        String payloadAsString = message.getPayloadAsString();
        Intrinsics.checkExpressionValueIsNotNull(payloadAsString, "message.payloadAsString");
        FitnessSessionCommand parseAsFitnessSessionCommand = parseAsFitnessSessionCommand(payloadAsString);
        if (parseAsFitnessSessionCommand == null) {
            return;
        }
        try {
            handleWorkoutOperation(toWorkoutOperation(parseAsFitnessSessionCommand.getOperation()));
        } catch (IllegalArgumentException e) {
            this.log.error(MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "An error occurred in handling workout operation", e);
        }
    }

    private final void handleWorkoutOperation(Operation operation) {
        ILog iLog = this.log;
        ILog.DefaultImpls.debug$default(iLog, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "Handling operation " + operation, null, 4, null);
        int i = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
        if (i == 1) {
            handleWorkoutStartOperation();
        } else if (i == 2) {
            handleWorkoutStopOperation();
        } else if (i == 3) {
            handleWorkoutPauseOperation();
        } else if (i != 4) {
        } else {
            handleWorkoutResumeOperation();
        }
    }

    private final void handleWorkoutPauseOperation() {
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "Pausing workout from GUI", null, 4, null);
        new MetricsTrackingCallback(MetricsOperation.PAUSE_WORKOUT, this.metricEventFactory.createMetricEvent(MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER), this.metricEventRecorder);
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$handleWorkoutPauseOperation$myRunnable$1
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    private final void handleWorkoutResumeOperation() {
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "Resuming workout from GUI", null, 4, null);
        new MetricsTrackingCallback(MetricsOperation.RESUME_WORKOUT, this.metricEventFactory.createMetricEvent(MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER), this.metricEventRecorder);
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$handleWorkoutResumeOperation$myRunnable$1
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    private final void handleWorkoutStartOperation() {
        ILog.DefaultImpls.info$default(this.log, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "Starting workout from GUI", null, 4, null);
        new MetricsTrackingCallback(MetricsOperation.START_WORKOUT, this.metricEventFactory.createMetricEvent(MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER), this.metricEventRecorder);
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$handleWorkoutStartOperation$myRunnable$1
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    private final void handleWorkoutStopOperation() {
        ILog.DefaultImpls.info$default(this.log, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "Stopping workout from GUI", null, 4, null);
        new MetricsTrackingCallback(MetricsOperation.STOP_WORKOUT, this.metricEventFactory.createMetricEvent(MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER), this.metricEventRecorder);
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$handleWorkoutStopOperation$myRunnable$1
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFitnessSessionCommandReceiverRequestEvent(@Nullable String str) {
        return Intrinsics.areEqual(str, getConfiguration().getEventTypeFitnessSessionCommandReceiverRequest());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFitnessSessionCommandReceiverUpdateEvent(@Nullable String str) {
        return Intrinsics.areEqual(str, getConfiguration().getEventTypeFitnessSessionCommandReceiverUpdate());
    }

    private final FitnessSessionCommand parseAsFitnessSessionCommand(@NotNull String str) {
        FitnessSessionCommand fitnessSessionCommand;
        boolean isBlank;
        try {
            try {
                isBlank = StringsKt__StringsJVMKt.isBlank(str);
            } catch (JsonParseException e) {
                throw new IllegalArgumentException("Failed to deserialize JSON String: '" + str + Chars.QUOTE, e);
            }
        } catch (Exception e2) {
            ILog iLog = this.log;
            iLog.error(MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "Failed to parse user profile update event. Payload was " + str, e2);
            fitnessSessionCommand = null;
        }
        if (!isBlank) {
            Object fromJson = GsonUtils.Companion.getGson().fromJson(str, (Class<Object>) FitnessSessionCommand.class);
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(this, T::class.java)");
            fitnessSessionCommand = (FitnessSessionCommand) fromJson;
            if (fitnessSessionCommand != null) {
                return fitnessSessionCommand;
            }
            ILog iLog2 = this.log;
            ILog.DefaultImpls.error$default(iLog2, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, getConfiguration().getEventTypeFitnessSessionCommandReceiverUpdate() + " event payload was null.", null, 4, null);
            return null;
        }
        throw new JsonParseException("Cannot parse blank JSON String.");
    }

    private final void sendFitnessSessionCommandReceiverNotifyEventWithCurrentWorkoutState(Error error) {
        if (!this.featureService.isFitnessEnabled()) {
            ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "Featuregate disabled, not sending current fitness state.", null, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void sendFitnessSessionCommandReceiverNotifyEventWithCurrentWorkoutState$default(FitnessSessionCommandReceiverImpl fitnessSessionCommandReceiverImpl, Error error, int i, Object obj) {
        if ((i & 1) != 0) {
            error = null;
        }
        fitnessSessionCommandReceiverImpl.sendFitnessSessionCommandReceiverNotifyEventWithCurrentWorkoutState(error);
    }

    private final Operation toWorkoutOperation(@NotNull String str) {
        Locale locale = Locale.ROOT;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
        if (str != null) {
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            switch (lowerCase.hashCode()) {
                case -934426579:
                    if (lowerCase.equals("resume")) {
                        return Operation.RESUME;
                    }
                    break;
                case 3540994:
                    if (lowerCase.equals("stop")) {
                        return Operation.STOP;
                    }
                    break;
                case 106440182:
                    if (lowerCase.equals("pause")) {
                        return Operation.PAUSE;
                    }
                    break;
                case 109757538:
                    if (lowerCase.equals("start")) {
                        return Operation.START;
                    }
                    break;
            }
            throw new IllegalArgumentException(Chars.QUOTE + str + "' is an invalid workout operation string.");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @NotNull
    public final Handler getMainHandler() {
        return this.mainHandler;
    }

    @Override // com.amazon.alexa.fitness.service.Startable
    public void start() {
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, "start() invoked...", null, 4, null);
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Subscribing to ");
        outline107.append(getConfiguration().getEventTypeFitnessSessionCommandReceiverUpdate());
        outline107.append(" event type");
        ILog.DefaultImpls.debug$default(iLog, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, outline107.toString(), null, 4, null);
        Optional optional = ComponentRegistry.getInstance().get(EventBus.class);
        Intrinsics.checkExpressionValueIsNotNull(optional, "ComponentRegistry.getIns…get(EventBus::class.java)");
        UUID uuid = (UUID) GuavaUtilsKt.flatMap(optional, FitnessSessionCommandReceiverImpl$start$subscriberIdUpdateEvent$1.INSTANCE).transform(new Function<T, V>() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$start$subscriberIdUpdateEvent$2
            @Override // com.google.common.base.Function
            /* renamed from: apply */
            public final UUID mo8172apply(@Nullable MultiFilterSubscriber multiFilterSubscriber) {
                if (multiFilterSubscriber == null) {
                    Intrinsics.throwNpe();
                }
                return multiFilterSubscriber.subscribe(new MessageFilter() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$start$subscriberIdUpdateEvent$2.1
                    @Override // com.amazon.alexa.eventbus.api.MessageFilter
                    public final boolean isMatch(@NotNull Message message) {
                        boolean isFitnessSessionCommandReceiverUpdateEvent;
                        Intrinsics.checkParameterIsNotNull(message, "message");
                        isFitnessSessionCommandReceiverUpdateEvent = FitnessSessionCommandReceiverImpl.this.isFitnessSessionCommandReceiverUpdateEvent(message.getEventType());
                        return isFitnessSessionCommandReceiverUpdateEvent;
                    }
                }, new MessageHandler() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$start$subscriberIdUpdateEvent$2.2
                    @Override // com.amazon.alexa.eventbus.api.MessageHandler
                    public final void handle(@NotNull Message message) {
                        Intrinsics.checkParameterIsNotNull(message, "message");
                        FitnessSessionCommandReceiverImpl.this.handleFitnessSessionCommandReceiverUpdatedEvent(message);
                    }
                });
            }
        }).orNull();
        ILog iLog2 = this.log;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Subscribing to ");
        outline1072.append(getConfiguration().getEventTypeFitnessSessionCommandReceiverRequest());
        outline1072.append(" event type");
        ILog.DefaultImpls.debug$default(iLog2, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, outline1072.toString(), null, 4, null);
        Optional optional2 = ComponentRegistry.getInstance().get(EventBus.class);
        Intrinsics.checkExpressionValueIsNotNull(optional2, "ComponentRegistry.getIns…get(EventBus::class.java)");
        UUID uuid2 = (UUID) GuavaUtilsKt.flatMap(optional2, FitnessSessionCommandReceiverImpl$start$subscriberIdRequestEvent$1.INSTANCE).transform(new Function<T, V>() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$start$subscriberIdRequestEvent$2
            @Override // com.google.common.base.Function
            /* renamed from: apply */
            public final UUID mo8172apply(@Nullable MultiFilterSubscriber multiFilterSubscriber) {
                if (multiFilterSubscriber == null) {
                    Intrinsics.throwNpe();
                }
                return multiFilterSubscriber.subscribe(new MessageFilter() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$start$subscriberIdRequestEvent$2.1
                    @Override // com.amazon.alexa.eventbus.api.MessageFilter
                    public final boolean isMatch(@NotNull Message message) {
                        boolean isFitnessSessionCommandReceiverRequestEvent;
                        Intrinsics.checkParameterIsNotNull(message, "message");
                        isFitnessSessionCommandReceiverRequestEvent = FitnessSessionCommandReceiverImpl.this.isFitnessSessionCommandReceiverRequestEvent(message.getEventType());
                        return isFitnessSessionCommandReceiverRequestEvent;
                    }
                }, new MessageHandler() { // from class: com.amazon.alexa.fitness.service.FitnessSessionCommandReceiverImpl$start$subscriberIdRequestEvent$2.2
                    @Override // com.amazon.alexa.eventbus.api.MessageHandler
                    public final void handle(@NotNull Message message) {
                        Intrinsics.checkParameterIsNotNull(message, "<anonymous parameter 0>");
                        FitnessSessionCommandReceiverImpl.sendFitnessSessionCommandReceiverNotifyEventWithCurrentWorkoutState$default(FitnessSessionCommandReceiverImpl.this, null, 1, null);
                    }
                });
            }
        }).orNull();
        if (uuid == null) {
            ILog iLog3 = this.log;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Failed to subscribe to ");
            outline1073.append(getConfiguration().getEventTypeFitnessSessionCommandReceiverUpdate());
            outline1073.append(" event.");
            ILog.DefaultImpls.error$default(iLog3, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, outline1073.toString(), null, 4, null);
            Unit unit = Unit.INSTANCE;
        }
        if (uuid2 != null) {
            return;
        }
        ILog iLog4 = this.log;
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Failed to subscribe to ");
        outline1074.append(getConfiguration().getEventTypeFitnessSessionCommandReceiverRequest());
        outline1074.append(" event.");
        ILog.DefaultImpls.error$default(iLog4, MetricsClass.FITNESS_SESSION_COMMAND_RECEIVER, outline1074.toString(), null, 4, null);
        Unit unit2 = Unit.INSTANCE;
    }
}
