package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.sessionmanager.TarazedController;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedController$sessionNotificationHandler$1", f = "TarazedController.kt", i = {0, 1}, l = {437, 440}, m = "invokeSuspend", n = {"it", "it"}, s = {"L$0", "L$0"})
/* loaded from: classes13.dex */
public final class TarazedController$sessionNotificationHandler$1 extends SuspendLambda implements Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private TarazedNotificationEvent p$0;
    final /* synthetic */ TarazedController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedController$sessionNotificationHandler$1(TarazedController tarazedController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedController$sessionNotificationHandler$1 tarazedController$sessionNotificationHandler$1 = new TarazedController$sessionNotificationHandler$1(this.this$0, completion);
        tarazedController$sessionNotificationHandler$1.p$0 = (TarazedNotificationEvent) obj;
        return tarazedController$sessionNotificationHandler$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(TarazedNotificationEvent tarazedNotificationEvent, Continuation<? super Unit> continuation) {
        return ((TarazedController$sessionNotificationHandler$1) create(tarazedNotificationEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        DeviceInfoUtility deviceInfoUtility;
        TarazedResourceManager tarazedResourceManager;
        TarazedSessionLogger tarazedSessionLogger;
        DeviceInfoUtility deviceInfoUtility2;
        TarazedResourceManager tarazedResourceManager2;
        TarazedSessionLogger tarazedSessionLogger2;
        TarazedController.Companion unused;
        TarazedController.Companion unused2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TarazedNotificationEvent tarazedNotificationEvent = this.p$0;
            int i2 = TarazedController.WhenMappings.$EnumSwitchMapping$0[tarazedNotificationEvent.ordinal()];
            if (i2 == 1) {
                this.this$0.handleMediaConnected();
            } else if (i2 == 2) {
                TarazedController tarazedController = this.this$0;
                this.L$0 = tarazedNotificationEvent;
                this.label = 1;
                if (tarazedController.tearDownSession(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 == 3) {
                TarazedController tarazedController2 = this.this$0;
                this.L$0 = tarazedNotificationEvent;
                this.label = 2;
                if (tarazedController2.handleSessionEnd(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 == 4) {
                deviceInfoUtility = this.this$0.deviceInfo;
                if (!deviceInfoUtility.is1PDevice()) {
                    tarazedResourceManager = this.this$0.resourceManager;
                    if (!tarazedResourceManager.hasSessionRequestedPermission$TarazedAndroidLibrary_release()) {
                        tarazedSessionLogger = this.this$0.logger;
                        unused2 = TarazedController.Companion;
                        tarazedSessionLogger.i("TarazedSessionControl", "3P app has requested to start while app in foreground, starting session");
                        this.this$0.startSession$TarazedAndroidLibrary_release();
                    }
                }
            } else if (i2 == 5) {
                deviceInfoUtility2 = this.this$0.deviceInfo;
                if (!deviceInfoUtility2.is1PDevice()) {
                    tarazedResourceManager2 = this.this$0.resourceManager;
                    if (!tarazedResourceManager2.hasSessionRequestedPermission$TarazedAndroidLibrary_release()) {
                        tarazedSessionLogger2 = this.this$0.logger;
                        unused = TarazedController.Companion;
                        tarazedSessionLogger2.i("TarazedSessionControl", "3P app has requested to start while app in background, start session connection in background");
                        this.this$0.startBackgroundSession();
                    }
                }
            }
        } else if (i != 1 && i != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            TarazedNotificationEvent tarazedNotificationEvent2 = (TarazedNotificationEvent) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
