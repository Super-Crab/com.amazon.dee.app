package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionRecoveryManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult;", "", "()V", "FailedToRecover", "NothingToRecover", "Recovered", "UnrecoverableNoData", "UnrecoverableStaleData", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$FailedToRecover;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$NothingToRecover;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$UnrecoverableNoData;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$UnrecoverableStaleData;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$Recovered;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class SessionRecoveryResult {

    /* compiled from: SessionRecoveryManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$FailedToRecover;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult;", "()V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class FailedToRecover extends SessionRecoveryResult {
        public static final FailedToRecover INSTANCE = new FailedToRecover();

        private FailedToRecover() {
            super(null);
        }
    }

    /* compiled from: SessionRecoveryManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$NothingToRecover;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult;", "()V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class NothingToRecover extends SessionRecoveryResult {
        public static final NothingToRecover INSTANCE = new NothingToRecover();

        private NothingToRecover() {
            super(null);
        }
    }

    /* compiled from: SessionRecoveryManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$Recovered;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult;", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;)V", "getSession", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Recovered extends SessionRecoveryResult {
        @NotNull
        private final Session session;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Recovered(@NotNull Session session) {
            super(null);
            Intrinsics.checkParameterIsNotNull(session, "session");
            this.session = session;
        }

        @NotNull
        public final Session getSession() {
            return this.session;
        }
    }

    /* compiled from: SessionRecoveryManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$UnrecoverableNoData;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult;", "()V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class UnrecoverableNoData extends SessionRecoveryResult {
        public static final UnrecoverableNoData INSTANCE = new UnrecoverableNoData();

        private UnrecoverableNoData() {
            super(null);
        }
    }

    /* compiled from: SessionRecoveryManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult$UnrecoverableStaleData;", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult;", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;)V", "getSession", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class UnrecoverableStaleData extends SessionRecoveryResult {
        @NotNull
        private final Session session;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnrecoverableStaleData(@NotNull Session session) {
            super(null);
            Intrinsics.checkParameterIsNotNull(session, "session");
            this.session = session;
        }

        @NotNull
        public final Session getSession() {
            return this.session;
        }
    }

    private SessionRecoveryResult() {
    }

    public /* synthetic */ SessionRecoveryResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
