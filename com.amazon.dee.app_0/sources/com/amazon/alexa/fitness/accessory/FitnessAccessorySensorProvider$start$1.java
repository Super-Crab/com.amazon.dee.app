package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: FitnessAccessorySensorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes7.dex */
final class FitnessAccessorySensorProvider$start$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Function1 $callback;
    final /* synthetic */ UUID $sessionId;
    final /* synthetic */ FitnessAccessorySensorProvider this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessAccessorySensorProvider$start$1(FitnessAccessorySensorProvider fitnessAccessorySensorProvider, UUID uuid, Function1 function1) {
        super(0);
        this.this$0 = fitnessAccessorySensorProvider;
        this.$sessionId = uuid;
        this.$callback = function1;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12560invoke() {
        mo12560invoke();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final void mo12560invoke() {
        ILog iLog;
        iLog = this.this$0.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No connected accessories when processing start for ");
        outline107.append(this.$sessionId);
        ILog.DefaultImpls.error$default(iLog, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, outline107.toString(), null, 4, null);
        this.this$0.postResultCallbackToAfx(this.$callback, new SensorError.Unavailable());
    }
}
