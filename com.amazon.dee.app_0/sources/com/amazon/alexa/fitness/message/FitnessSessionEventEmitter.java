package com.amazon.alexa.fitness.message;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.metrics.MetricsCategory;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.model.event.EndedReason;
import com.amazon.alexa.fitness.model.event.Error;
import com.amazon.alexa.fitness.model.event.FitnessSessionEventName;
import com.amazon.alexa.fitness.model.event.FitnessSessionEventPayload;
import com.amazon.alexa.fitness.model.event.Operation;
import com.amazon.alexa.fitness.util.Callback;
import com.amazon.alexa.fitness.util.CallbackAccumulator;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.amazon.alexa.fitness.util.StubCallback;
import com.amazon.deecomms.common.Constants;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionEventEmitter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004J,\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a0\u0019J \u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J$\u0010 \u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a0\u0019J$\u0010!\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a0\u0019J$\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a0\u0019R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/amazon/alexa/fitness/message/FitnessSessionEventEmitter;", "", "initialReceivers", "", "Lcom/amazon/alexa/fitness/message/FitnessSessionEventReceiver;", "([Lcom/amazon/alexa/fitness/message/FitnessSessionEventReceiver;)V", "fitnessSessionEventReceivers", "", "createAlexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "fitnessSessionEventName", "Lcom/amazon/alexa/fitness/model/event/FitnessSessionEventName;", "fitnessSessionEventPayload", "Lcom/amazon/alexa/fitness/model/event/FitnessSessionEventPayload;", "deregisterReceiver", "", Constants.BUNDLE_RECEIVER_TAG, "registerReceiver", "sendFitnessSessionEnded", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/alexa/fitness/model/event/EndedReason;", "callback", "Lcom/amazon/alexa/fitness/util/Callback;", "", "sendFitnessSessionError", "error", "Lcom/amazon/alexa/fitness/model/event/Error;", "operation", "Lcom/amazon/alexa/fitness/model/event/Operation;", "sendFitnessSessionPaused", "sendFitnessSessionResumed", "sendFitnessSessionStarted", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class FitnessSessionEventEmitter {
    private final Set<FitnessSessionEventReceiver> fitnessSessionEventReceivers;

    public FitnessSessionEventEmitter(@NotNull FitnessSessionEventReceiver... initialReceivers) {
        List asList;
        Intrinsics.checkParameterIsNotNull(initialReceivers, "initialReceivers");
        asList = ArraysKt___ArraysJvmKt.asList(initialReceivers);
        this.fitnessSessionEventReceivers = new CopyOnWriteArraySet(asList);
    }

    private final AlexaEvent createAlexaEvent(FitnessSessionEventName fitnessSessionEventName, FitnessSessionEventPayload fitnessSessionEventPayload) {
        AlexaHeader.Builder builder = AlexaHeader.builder();
        builder.setNamespace("Alexa.Health.Fitness");
        builder.setName(fitnessSessionEventName.getValue());
        return new AlexaEvent(builder.build(), new AlexaPayload(GsonUtilsKt.toJson(fitnessSessionEventPayload)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void sendFitnessSessionEnded$default(FitnessSessionEventEmitter fitnessSessionEventEmitter, UUID uuid, EndedReason endedReason, Callback callback, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                callback = new StubCallback();
            }
            fitnessSessionEventEmitter.sendFitnessSessionEnded(uuid, endedReason, callback);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendFitnessSessionEnded");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void sendFitnessSessionPaused$default(FitnessSessionEventEmitter fitnessSessionEventEmitter, UUID uuid, Callback callback, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                callback = new StubCallback();
            }
            fitnessSessionEventEmitter.sendFitnessSessionPaused(uuid, callback);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendFitnessSessionPaused");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void sendFitnessSessionResumed$default(FitnessSessionEventEmitter fitnessSessionEventEmitter, UUID uuid, Callback callback, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                callback = new StubCallback();
            }
            fitnessSessionEventEmitter.sendFitnessSessionResumed(uuid, callback);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendFitnessSessionResumed");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void sendFitnessSessionStarted$default(FitnessSessionEventEmitter fitnessSessionEventEmitter, UUID uuid, Callback callback, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                callback = new StubCallback();
            }
            fitnessSessionEventEmitter.sendFitnessSessionStarted(uuid, callback);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendFitnessSessionStarted");
    }

    public final boolean deregisterReceiver(@NotNull FitnessSessionEventReceiver receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "receiver");
        return this.fitnessSessionEventReceivers.remove(receiver);
    }

    public final boolean registerReceiver(@NotNull FitnessSessionEventReceiver receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "receiver");
        return this.fitnessSessionEventReceivers.add(receiver);
    }

    public final void sendFitnessSessionEnded(@NotNull UUID sessionId, @NotNull EndedReason reason, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (this.fitnessSessionEventReceivers.isEmpty()) {
            Callback.DefaultImpls.onError$default(callback, MetricsConstantsKt.buildMetricName(MetricsCategory.FITNESS_SESSION_EVENT_RECEIVER, MetricsName.MISSING), null, 2, null);
            return;
        }
        AlexaEvent createAlexaEvent = createAlexaEvent(FitnessSessionEventName.ENDED, FitnessSessionEventPayload.Companion.fitnessSessionEnded(sessionId, reason));
        CallbackAccumulator callbackAccumulator = new CallbackAccumulator(callback, this.fitnessSessionEventReceivers.size());
        for (FitnessSessionEventReceiver fitnessSessionEventReceiver : this.fitnessSessionEventReceivers) {
            fitnessSessionEventReceiver.onFitnessSessionEnded(createAlexaEvent, callbackAccumulator);
        }
    }

    public void sendFitnessSessionError(@NotNull UUID sessionId, @NotNull Error error, @NotNull Operation operation) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        AlexaEvent createAlexaEvent = createAlexaEvent(FitnessSessionEventName.ERROR, FitnessSessionEventPayload.Companion.fitnessSessionError(sessionId, error, operation));
        for (FitnessSessionEventReceiver fitnessSessionEventReceiver : this.fitnessSessionEventReceivers) {
            fitnessSessionEventReceiver.onFitnessSessionError(createAlexaEvent, error, operation);
        }
    }

    public final void sendFitnessSessionPaused(@NotNull UUID sessionId, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (this.fitnessSessionEventReceivers.isEmpty()) {
            callback.onError(MetricsConstantsKt.buildMetricName(MetricsCategory.FITNESS_SESSION_EVENT_RECEIVER, MetricsName.MISSING), null);
            return;
        }
        AlexaEvent createAlexaEvent = createAlexaEvent(FitnessSessionEventName.PAUSED, FitnessSessionEventPayload.Companion.fitnessSessionPaused(sessionId));
        CallbackAccumulator callbackAccumulator = new CallbackAccumulator(callback, this.fitnessSessionEventReceivers.size());
        for (FitnessSessionEventReceiver fitnessSessionEventReceiver : this.fitnessSessionEventReceivers) {
            fitnessSessionEventReceiver.onFitnessSessionPaused(createAlexaEvent, callbackAccumulator);
        }
    }

    public final void sendFitnessSessionResumed(@NotNull UUID sessionId, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (this.fitnessSessionEventReceivers.isEmpty()) {
            callback.onError(MetricsConstantsKt.buildMetricName(MetricsCategory.FITNESS_SESSION_EVENT_RECEIVER, MetricsName.MISSING), null);
            return;
        }
        AlexaEvent createAlexaEvent = createAlexaEvent(FitnessSessionEventName.RESUMED, FitnessSessionEventPayload.Companion.fitnessSessionResumed(sessionId));
        CallbackAccumulator callbackAccumulator = new CallbackAccumulator(callback, this.fitnessSessionEventReceivers.size());
        for (FitnessSessionEventReceiver fitnessSessionEventReceiver : this.fitnessSessionEventReceivers) {
            fitnessSessionEventReceiver.onFitnessSessionResumed(createAlexaEvent, callbackAccumulator);
        }
    }

    public final void sendFitnessSessionStarted(@NotNull UUID sessionId, @NotNull Callback<Unit, Throwable> callback) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (this.fitnessSessionEventReceivers.isEmpty()) {
            Callback.DefaultImpls.onError$default(callback, MetricsConstantsKt.buildMetricName(MetricsCategory.FITNESS_SESSION_EVENT_RECEIVER, MetricsName.MISSING), null, 2, null);
            return;
        }
        AlexaEvent createAlexaEvent = createAlexaEvent(FitnessSessionEventName.STARTED, FitnessSessionEventPayload.Companion.fitnessSessionStarted(sessionId));
        CallbackAccumulator callbackAccumulator = new CallbackAccumulator(callback, this.fitnessSessionEventReceivers.size());
        for (FitnessSessionEventReceiver fitnessSessionEventReceiver : this.fitnessSessionEventReceivers) {
            fitnessSessionEventReceiver.onFitnessSessionStarted(createAlexaEvent, callbackAccumulator);
        }
    }
}
