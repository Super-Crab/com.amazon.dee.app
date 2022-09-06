package com.amazon.alexa.fitness.model.event;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/model/event/FitnessSessionEventPayload;", "", "session", "Lcom/amazon/alexa/fitness/model/event/EventSession;", "(Lcom/amazon/alexa/fitness/model/event/EventSession;)V", "getSession", "()Lcom/amazon/alexa/fitness/model/event/EventSession;", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionEventPayload {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final EventSession session;

    /* compiled from: FitnessSessionEvent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/model/event/FitnessSessionEventPayload$Companion;", "", "()V", "fitnessSessionEnded", "Lcom/amazon/alexa/fitness/model/event/FitnessSessionEventPayload;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/alexa/fitness/model/event/EndedReason;", "fitnessSessionError", "error", "Lcom/amazon/alexa/fitness/model/event/Error;", "operation", "Lcom/amazon/alexa/fitness/model/event/Operation;", "fitnessSessionPaused", "fitnessSessionResumed", "fitnessSessionStarted", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final FitnessSessionEventPayload fitnessSessionEnded(@NotNull UUID sessionId, @NotNull EndedReason reason) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            Intrinsics.checkParameterIsNotNull(reason, "reason");
            return new FitnessSessionEventPayload(new EventSessionEnded(sessionId, reason.getValue()), null);
        }

        @NotNull
        public final FitnessSessionEventPayload fitnessSessionError(@NotNull UUID sessionId, @NotNull Error error, @NotNull Operation operation) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            Intrinsics.checkParameterIsNotNull(error, "error");
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            return new FitnessSessionEventPayload(new EventSessionError(sessionId, error.getValue(), operation.getValue()), null);
        }

        @NotNull
        public final FitnessSessionEventPayload fitnessSessionPaused(@NotNull UUID sessionId) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            return new FitnessSessionEventPayload(new EventSession(sessionId), null);
        }

        @NotNull
        public final FitnessSessionEventPayload fitnessSessionResumed(@NotNull UUID sessionId) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            return new FitnessSessionEventPayload(new EventSession(sessionId), null);
        }

        @NotNull
        public final FitnessSessionEventPayload fitnessSessionStarted(@NotNull UUID sessionId) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            return new FitnessSessionEventPayload(new EventSession(sessionId), null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private FitnessSessionEventPayload(EventSession eventSession) {
        this.session = eventSession;
    }

    @NotNull
    public final EventSession getSession() {
        return this.session;
    }

    public /* synthetic */ FitnessSessionEventPayload(EventSession eventSession, DefaultConstructorMarker defaultConstructorMarker) {
        this(eventSession);
    }
}
