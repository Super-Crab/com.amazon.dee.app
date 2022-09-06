package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SensorError.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "Ljava/io/Serializable;", "()V", "InvalidCommand", "NullFitnessRepository", "SetStateFailed", DriveModeMetrics.NetworkStatus.UNAVAILABLE, "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError$Unavailable;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError$SetStateFailed;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError$NullFitnessRepository;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError$InvalidCommand;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class SensorError implements Serializable {

    /* compiled from: SensorError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError$InvalidCommand;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "()V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class InvalidCommand extends SensorError {
        public InvalidCommand() {
            super(null);
        }
    }

    /* compiled from: SensorError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError$NullFitnessRepository;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "()V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class NullFitnessRepository extends SensorError {
        public NullFitnessRepository() {
            super(null);
        }
    }

    /* compiled from: SensorError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError$SetStateFailed;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "throwable", "", "(Ljava/lang/Throwable;)V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class SetStateFailed extends SensorError {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SetStateFailed(@NotNull Throwable throwable) {
            super(null);
            Intrinsics.checkParameterIsNotNull(throwable, "throwable");
        }
    }

    /* compiled from: SensorError.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError$Unavailable;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "()V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Unavailable extends SensorError {
        public Unavailable() {
            super(null);
        }
    }

    private SensorError() {
    }

    public /* synthetic */ SensorError(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
