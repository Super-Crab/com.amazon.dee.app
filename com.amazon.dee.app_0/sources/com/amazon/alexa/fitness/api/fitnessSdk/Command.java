package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Command.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "Ljava/io/Serializable;", "()V", "AttemptRecovery", EntertainmentMetrics.Button.PAUSE, "Resume", "Start", PCCConstants.STOP_DIRECTIVE, "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$AttemptRecovery;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$Start;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$Stop;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$Pause;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$Resume;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class Command implements Serializable {

    /* compiled from: Command.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$AttemptRecovery;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "()V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class AttemptRecovery extends Command {
        public static final AttemptRecovery INSTANCE = new AttemptRecovery();

        private AttemptRecovery() {
            super(null);
        }
    }

    /* compiled from: Command.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$Pause;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "source", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;)V", "getSource", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Pause extends Command {
        @NotNull
        private final SessionCommandSource source;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Pause(@NotNull SessionCommandSource source) {
            super(null);
            Intrinsics.checkParameterIsNotNull(source, "source");
            this.source = source;
        }

        @NotNull
        public final SessionCommandSource getSource() {
            return this.source;
        }
    }

    /* compiled from: Command.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$Resume;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "source", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;)V", "getSource", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Resume extends Command {
        @NotNull
        private final SessionCommandSource source;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Resume(@NotNull SessionCommandSource source) {
            super(null);
            Intrinsics.checkParameterIsNotNull(source, "source");
            this.source = source;
        }

        @NotNull
        public final SessionCommandSource getSource() {
            return this.source;
        }
    }

    /* compiled from: Command.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$Start;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "source", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "sessionConfiguration", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;)V", "getSessionConfiguration", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "getSource", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Start extends Command {
        @NotNull
        private final SessionConfiguration sessionConfiguration;
        @NotNull
        private final SessionCommandSource source;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Start(@NotNull SessionCommandSource source, @NotNull SessionConfiguration sessionConfiguration) {
            super(null);
            Intrinsics.checkParameterIsNotNull(source, "source");
            Intrinsics.checkParameterIsNotNull(sessionConfiguration, "sessionConfiguration");
            this.source = source;
            this.sessionConfiguration = sessionConfiguration;
        }

        @NotNull
        public final SessionConfiguration getSessionConfiguration() {
            return this.sessionConfiguration;
        }

        @NotNull
        public final SessionCommandSource getSource() {
            return this.source;
        }
    }

    /* compiled from: Command.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Command$Stop;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "source", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;)V", "getSource", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Stop extends Command {
        @NotNull
        private final SessionCommandSource source;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Stop(@NotNull SessionCommandSource source) {
            super(null);
            Intrinsics.checkParameterIsNotNull(source, "source");
            this.source = source;
        }

        @NotNull
        public final SessionCommandSource getSource() {
            return this.source;
        }
    }

    private Command() {
    }

    public /* synthetic */ Command(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
