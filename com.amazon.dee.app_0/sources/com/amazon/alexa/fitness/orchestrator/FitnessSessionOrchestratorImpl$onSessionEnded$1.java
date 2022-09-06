package com.amazon.alexa.fitness.orchestrator;

import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.model.biometric.ActivitySummary;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SensorProvider;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.fitness.service.afits.WorkoutSessionUploader;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FitnessSessionOrchestratorImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "route", "", "Lcom/amazon/alexa/fitness/sdk/Sample$LocationSample;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionOrchestratorImpl$onSessionEnded$1 extends Lambda implements Function1<List<? extends Sample.LocationSample>, Unit> {
    final /* synthetic */ ActivitySummary $activitySummary;
    final /* synthetic */ Session $session;
    final /* synthetic */ UUID $sessionId;
    final /* synthetic */ FitnessSessionOrchestratorImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FitnessSessionOrchestratorImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImpl$onSessionEnded$1$2  reason: invalid class name */
    /* loaded from: classes8.dex */
    public static final class AnonymousClass2 extends Lambda implements Function1<FitnessSession, Unit> {
        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(FitnessSession fitnessSession) {
            invoke2(fitnessSession);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull FitnessSession it2) {
            SampleStore sampleStore;
            Intrinsics.checkParameterIsNotNull(it2, "it");
            sampleStore = FitnessSessionOrchestratorImpl$onSessionEnded$1.this.this$0.sampleStore;
            sampleStore.deleteAllSamples(FitnessSessionOrchestratorImpl$onSessionEnded$1.this.$sessionId);
            if (FitnessSessionOrchestratorImpl$onSessionEnded$1.this.this$0.getUIState().getHistory().getHasLoadedInitialWorkouts()) {
                FitnessSessionOrchestratorImpl$onSessionEnded$1.this.this$0.addNewWorkout(it2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessSessionOrchestratorImpl$onSessionEnded$1(FitnessSessionOrchestratorImpl fitnessSessionOrchestratorImpl, Session session, ActivitySummary activitySummary, UUID uuid) {
        super(1);
        this.this$0 = fitnessSessionOrchestratorImpl;
        this.$session = session;
        this.$activitySummary = activitySummary;
        this.$sessionId = uuid;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(List<? extends Sample.LocationSample> list) {
        invoke2((List<Sample.LocationSample>) list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull List<Sample.LocationSample> route) {
        List list;
        Object obj;
        SessionSummaryCache sessionSummaryCache;
        boolean z;
        Intrinsics.checkParameterIsNotNull(route, "route");
        list = this.this$0.sensorProviders;
        Iterator it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            SensorProvider sensorProvider = (SensorProvider) obj;
            if (!sensorProvider.getRequired() || !(sensorProvider instanceof FitnessAccessorySensorProvider)) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (z) {
                break;
            }
        }
        if (obj != null) {
            Session session = this.$session;
            ActivitySummary activitySummary = this.$activitySummary;
            sessionSummaryCache = this.this$0.sessionSummaryCache;
            new WorkoutSessionUploader(route, (FitnessAccessorySensorProvider) obj, session, activitySummary, sessionSummaryCache).upload(new AnonymousClass2());
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider");
    }
}
