package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.sdk.EchoBudSampleData;
import com.amazon.alexa.fitness.sdk.Sample;
import java.lang.ref.WeakReference;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FitnessAccessorySensorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "sampleData", "Lcom/amazon/alexa/fitness/sdk/EchoBudSampleData;", "invoke", "com/amazon/alexa/fitness/accessory/FitnessAccessorySensorProvider$subscribeToNotifications$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAccessorySensorProvider$subscribeToNotifications$$inlined$apply$lambda$1 extends Lambda implements Function1<EchoBudSampleData, Unit> {
    final /* synthetic */ AccessorySession $accessorySession$inlined;
    final /* synthetic */ FitnessAccessorySensorProvider this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessAccessorySensorProvider$subscribeToNotifications$$inlined$apply$lambda$1(FitnessAccessorySensorProvider fitnessAccessorySensorProvider, AccessorySession accessorySession) {
        super(1);
        this.this$0 = fitnessAccessorySensorProvider;
        this.$accessorySession$inlined = accessorySession;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(EchoBudSampleData echoBudSampleData) {
        invoke2(echoBudSampleData);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull EchoBudSampleData sampleData) {
        WeakReference weakReference;
        ILog iLog;
        UUID uuid;
        ILog iLog2;
        ILog iLog3;
        Intrinsics.checkParameterIsNotNull(sampleData, "sampleData");
        weakReference = this.this$0.weakThis;
        if (((FitnessAccessorySensorProvider) weakReference.get()) != null) {
            uuid = this.this$0.currentSessionID;
            if (uuid == null) {
                iLog2 = this.this$0.log;
                ILog.DefaultImpls.error$default(iLog2, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "missing current session ID while consuming accessory data", null, 4, null);
                return;
            }
            String sensorInUse = this.this$0.getSensorInUse();
            if (sensorInUse != null) {
                this.this$0.publishSample(new Sample.EchoBudSample(uuid, sensorInUse, System.currentTimeMillis(), sampleData.getCollectionTimestamp(), sampleData.getActivity(), sampleData.getSteps(), sampleData.getCadence()));
                return;
            }
            iLog3 = this.this$0.log;
            ILog.DefaultImpls.error$default(iLog3, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "missing sensor ID while consuming accessory data", null, 4, null);
            return;
        }
        iLog = this.this$0.log;
        ILog.DefaultImpls.error$default(iLog, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "no accessory sensor provider instance to consume accessory data", null, 4, null);
    }
}
