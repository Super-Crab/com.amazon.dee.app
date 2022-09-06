package com.amazon.alexa.fitness.api.fitnessSdk;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType;", "Ljava/io/Serializable;", "()V", "StateChangeEvent", "Touch", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType$StateChangeEvent;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType$Touch;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class SessionEventType implements Serializable {

    /* compiled from: SessionDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType$StateChangeEvent;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType;", "state", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "transition", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionTransition;", "command", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "error", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionTransition;Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;)V", "getCommand", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "getError", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "getState", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "getTransition", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionTransition;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class StateChangeEvent extends SessionEventType {
        @Nullable
        private final Command command;
        @Nullable
        private final SensorError error;
        @NotNull
        private final FitnessSessionState state;
        @NotNull
        private final FitnessSessionTransition transition;

        public /* synthetic */ StateChangeEvent(FitnessSessionState fitnessSessionState, FitnessSessionTransition fitnessSessionTransition, Command command, SensorError sensorError, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(fitnessSessionState, fitnessSessionTransition, (i & 4) != 0 ? null : command, (i & 8) != 0 ? null : sensorError);
        }

        @Nullable
        public final Command getCommand() {
            return this.command;
        }

        @Nullable
        public final SensorError getError() {
            return this.error;
        }

        @NotNull
        public final FitnessSessionState getState() {
            return this.state;
        }

        @NotNull
        public final FitnessSessionTransition getTransition() {
            return this.transition;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StateChangeEvent(@NotNull FitnessSessionState state, @NotNull FitnessSessionTransition transition, @Nullable Command command, @Nullable SensorError sensorError) {
            super(null);
            Intrinsics.checkParameterIsNotNull(state, "state");
            Intrinsics.checkParameterIsNotNull(transition, "transition");
            this.state = state;
            this.transition = transition;
            this.command = command;
            this.error = sensorError;
        }
    }

    /* compiled from: SessionDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType$Touch;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType;", "()V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Touch extends SessionEventType {
        public static final Touch INSTANCE = new Touch();

        private Touch() {
            super(null);
        }
    }

    private SessionEventType() {
    }

    public /* synthetic */ SessionEventType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
