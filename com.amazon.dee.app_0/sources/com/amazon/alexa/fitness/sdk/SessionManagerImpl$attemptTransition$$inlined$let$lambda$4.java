package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionTransition;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "invoke", "com/amazon/alexa/fitness/sdk/SessionManagerImpl$attemptTransition$1$2$3$1", "com/amazon/alexa/fitness/sdk/SessionManagerImpl$$special$$inlined$let$lambda$1", "com/amazon/alexa/fitness/sdk/SessionManagerImpl$$special$$inlined$forEach$lambda$3"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class SessionManagerImpl$attemptTransition$$inlined$let$lambda$4 extends Lambda implements Function1<SensorError, Unit> {
    final /* synthetic */ SessionConfiguration $config$inlined;
    final /* synthetic */ SessionManagerImpl$attemptTransition$$inlined$let$lambda$1 $processTransitionCallback$1$inlined;
    final /* synthetic */ SensorProvider $sensorProvider$inlined;
    final /* synthetic */ FitnessSessionTransition $transition$inlined;
    final /* synthetic */ SessionManagerImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionManagerImpl$attemptTransition$$inlined$let$lambda$4(SensorProvider sensorProvider, SessionManagerImpl$attemptTransition$$inlined$let$lambda$1 sessionManagerImpl$attemptTransition$$inlined$let$lambda$1, SessionManagerImpl sessionManagerImpl, FitnessSessionTransition fitnessSessionTransition, SessionConfiguration sessionConfiguration) {
        super(1);
        this.$sensorProvider$inlined = sensorProvider;
        this.$processTransitionCallback$1$inlined = sessionManagerImpl$attemptTransition$$inlined$let$lambda$1;
        this.this$0 = sessionManagerImpl;
        this.$transition$inlined = fitnessSessionTransition;
        this.$config$inlined = sessionConfiguration;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(SensorError sensorError) {
        invoke2(sensorError);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable SensorError sensorError) {
        this.$processTransitionCallback$1$inlined.invoke2(sensorError);
    }
}
