package com.amazon.tarazed.sessionmanager;

import android.content.Context;
import android.content.Intent;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.notification.type.TarazedLaunchRequest;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedController$resumeSuspendedSession$1", f = "TarazedController.kt", i = {0}, l = {182}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedController$resumeSuspendedSession$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedController$resumeSuspendedSession$1(TarazedController tarazedController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedController$resumeSuspendedSession$1 tarazedController$resumeSuspendedSession$1 = new TarazedController$resumeSuspendedSession$1(this.this$0, completion);
        tarazedController$resumeSuspendedSession$1.p$ = (CoroutineScope) obj;
        return tarazedController$resumeSuspendedSession$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedController$resumeSuspendedSession$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        TarazedResourceManager tarazedResourceManager;
        TarazedSessionLogger tarazedSessionLogger;
        SessionNotificationManager sessionNotificationManager;
        Context context;
        Context context2;
        TarazedController.Companion unused;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            tarazedResourceManager = this.this$0.resourceManager;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = tarazedResourceManager.getCachedLaunchRequest$TarazedAndroidLibrary_release(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        String str = (String) obj;
        if (str == null) {
            tarazedSessionLogger = this.this$0.logger;
            unused = TarazedController.Companion;
            tarazedSessionLogger.w("TarazedSessionControl", "No active launch request is cached, dropping resume suspended session request");
            sessionNotificationManager = this.this$0.getSessionNotificationManager();
            sessionNotificationManager.notifySessionStarting();
            context = this.this$0.getContext();
            Intent intent = new Intent(context, TarazedSessionAndroidService.class);
            context2 = this.this$0.getContext();
            context2.stopService(intent);
            return Unit.INSTANCE;
        }
        this.this$0.launchSession((TarazedLaunchRequest) Json.Default.getNonstrict().parse(TarazedLaunchRequest.Companion.serializer(), str), true);
        this.this$0.clearCachedLaunchRequest();
        return Unit.INSTANCE;
    }
}
