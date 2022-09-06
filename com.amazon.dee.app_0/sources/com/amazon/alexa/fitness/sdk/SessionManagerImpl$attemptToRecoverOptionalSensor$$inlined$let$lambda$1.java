package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import com.amazon.alexa.fitness.logs.ILog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SessionManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "error", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "invoke", "com/amazon/alexa/fitness/sdk/SessionManagerImpl$attemptToRecoverOptionalSensor$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionManagerImpl$attemptToRecoverOptionalSensor$$inlined$let$lambda$1 extends Lambda implements Function1<SensorError, Unit> {
    final /* synthetic */ SessionConfiguration $config$inlined;
    final /* synthetic */ SensorProvider $sensorProvider$inlined;
    final /* synthetic */ SessionManagerImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionManagerImpl$attemptToRecoverOptionalSensor$$inlined$let$lambda$1(SessionManagerImpl sessionManagerImpl, SensorProvider sensorProvider, SessionConfiguration sessionConfiguration) {
        super(1);
        this.this$0 = sessionManagerImpl;
        this.$sensorProvider$inlined = sensorProvider;
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
        ILog iLog;
        iLog = this.this$0.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("result from recovering ");
        outline107.append(this.$sensorProvider$inlined);
        outline107.append(" - ");
        outline107.append(sensorError);
        ILog.DefaultImpls.debug$default(iLog, "SessionManager", outline107.toString(), null, 4, null);
    }
}
