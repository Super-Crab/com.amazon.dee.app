package com.amazon.photos.uploader.internal.device;

import android.content.Context;
import android.content.Intent;
import com.amazon.photos.uploader.SchedulingCallback;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BatteryStateReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.photos.uploader.internal.device.BatteryStateReceiver$onReceive$1", f = "BatteryStateReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class BatteryStateReceiver$onReceive$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $action;
    final /* synthetic */ Context $context;
    final /* synthetic */ Intent $intent;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ BatteryStateReceiver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryStateReceiver$onReceive$1(BatteryStateReceiver batteryStateReceiver, String str, Intent intent, Context context, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryStateReceiver;
        this.$action = str;
        this.$intent = intent;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BatteryStateReceiver$onReceive$1 batteryStateReceiver$onReceive$1 = new BatteryStateReceiver$onReceive$1(this.this$0, this.$action, this.$intent, this.$context, completion);
        batteryStateReceiver$onReceive$1.p$ = (CoroutineScope) obj;
        return batteryStateReceiver$onReceive$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatteryStateReceiver$onReceive$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        BatteryState batteryState;
        SchedulingCallback schedulingCallback;
        BatteryState batteryState2;
        BatteryState batteryState3;
        BatteryState batteryState4;
        BatteryState batteryState5;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z = false;
            if (BatteryState.Companion.getBATTERY_STATUS_CHANGE_ACTION().contains(this.$action)) {
                batteryState5 = this.this$0.batteryState;
                z = false | batteryState5.updateAllStates$AndroidPhotosUploader_release(this.$intent, this.$context);
            }
            if (BatteryState.Companion.getBATTERY_ACTIONS().contains(this.$action)) {
                batteryState4 = this.this$0.batteryState;
                z |= batteryState4.updateBatteryPercent$AndroidPhotosUploader_release(this.$intent);
            }
            batteryState = this.this$0.batteryState;
            if (batteryState.getChargingActions().contains(this.$action)) {
                batteryState3 = this.this$0.batteryState;
                z |= batteryState3.updateChargingState$AndroidPhotosUploader_release(this.$intent);
            }
            if (Intrinsics.areEqual("android.os.action.POWER_SAVE_MODE_CHANGED", this.$action)) {
                batteryState2 = this.this$0.batteryState;
                z |= batteryState2.updatePowerState$AndroidPhotosUploader_release();
            }
            if (z) {
                schedulingCallback = this.this$0.schedulingCallback;
                schedulingCallback.reevaluateBlockers();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
