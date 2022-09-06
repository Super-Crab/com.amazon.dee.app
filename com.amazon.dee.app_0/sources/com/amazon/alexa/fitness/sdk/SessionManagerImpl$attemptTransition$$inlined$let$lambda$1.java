package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionTransition;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.SessionManagerImpl;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SessionManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"processTransitionCallback", "", "error", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "invoke", "com/amazon/alexa/fitness/sdk/SessionManagerImpl$attemptTransition$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionManagerImpl$attemptTransition$$inlined$let$lambda$1 extends Lambda implements Function1<SensorError, Unit> {
    final /* synthetic */ Ref.IntRef $callbackCount;
    final /* synthetic */ SessionConfiguration $config$inlined;
    final /* synthetic */ Ref.BooleanRef $errorRaised;
    final /* synthetic */ List $requiredSensors;
    final /* synthetic */ FitnessSessionTransition $transition$inlined;
    final /* synthetic */ SessionManagerImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionManagerImpl$attemptTransition$$inlined$let$lambda$1(Ref.BooleanRef booleanRef, Ref.IntRef intRef, List list, SessionManagerImpl sessionManagerImpl, FitnessSessionTransition fitnessSessionTransition, SessionConfiguration sessionConfiguration) {
        super(1);
        this.$errorRaised = booleanRef;
        this.$callbackCount = intRef;
        this.$requiredSensors = list;
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
    public final synchronized void invoke2(@Nullable SensorError sensorError) {
        ILog iLog;
        ILog iLog2;
        List<SensorProvider> optionalSensors;
        ILog iLog3;
        ILog iLog4;
        ILog iLog5;
        if (sensorError != null) {
            iLog4 = this.this$0.log;
            ILog.DefaultImpls.error$default(iLog4, "SessionManager", "sensor transition error: " + sensorError, null, 4, null);
            if (!this.$errorRaised.element) {
                iLog5 = this.this$0.log;
                ILog.DefaultImpls.debug$default(iLog5, "SessionManager", "calling sensorCallback with error", null, 4, null);
                this.this$0.sensorCallback(sensorError);
                this.$errorRaised.element = true;
            }
            return;
        }
        this.$callbackCount.element++;
        iLog = this.this$0.log;
        ILog.DefaultImpls.debug$default(iLog, "SessionManager", "checking processTransitionCallback count: " + this.$callbackCount.element, null, 4, null);
        if (this.$callbackCount.element == this.$requiredSensors.size()) {
            iLog2 = this.this$0.log;
            ILog.DefaultImpls.info$default(iLog2, "SessionManager", "all required providers succeeded", null, 4, null);
            optionalSensors = this.this$0.getOptionalSensors(this.$transition$inlined);
            if (optionalSensors != null) {
                for (SensorProvider sensorProvider : optionalSensors) {
                    iLog3 = this.this$0.log;
                    ILog.DefaultImpls.info$default(iLog3, "SessionManager", "attempting to handle " + this.$transition$inlined + " on " + sensorProvider, null, 4, null);
                    int i = SessionManagerImpl.WhenMappings.$EnumSwitchMapping$1[this.$transition$inlined.ordinal()];
                    if (i == 1) {
                        this.this$0.attemptToStartOptionalSensor(sensorProvider, this.$config$inlined);
                    } else if (i == 2) {
                        this.this$0.attemptToResumeOptionalSensor(sensorProvider);
                    } else if (i == 3) {
                        this.this$0.attemptToRecoverOptionalSensor(sensorProvider, this.$config$inlined);
                    }
                }
            }
            this.this$0.sensorCallback(null);
        }
    }
}
