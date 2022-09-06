package com.amazon.tarazed.sessionmanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache;
import com.amazon.tarazed.sessionmanager.TarazedResourceManager;
import com.amazon.tarazed.utility.ContextHelper;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedResourceManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedResourceManager$getSessionCacheServiceConnection$2", f = "TarazedResourceManager.kt", i = {0}, l = {585}, m = "invokeSuspend", n = {"$this$withTimeoutOrNull"}, s = {"L$0"})
/* loaded from: classes13.dex */
public final class TarazedResourceManager$getSessionCacheServiceConnection$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedResourceManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedResourceManager$getSessionCacheServiceConnection$2(TarazedResourceManager tarazedResourceManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedResourceManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedResourceManager$getSessionCacheServiceConnection$2 tarazedResourceManager$getSessionCacheServiceConnection$2 = new TarazedResourceManager$getSessionCacheServiceConnection$2(this.this$0, completion);
        tarazedResourceManager$getSessionCacheServiceConnection$2.p$ = (CoroutineScope) obj;
        return tarazedResourceManager$getSessionCacheServiceConnection$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedResourceManager$getSessionCacheServiceConnection$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Continuation intercepted;
        Object coroutine_suspended2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.p$;
            this.L$1 = this;
            this.label = 1;
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(this);
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
            this.this$0.setSessionCacheServiceConnection$TarazedAndroidLibrary_release(new ServiceConnection() { // from class: com.amazon.tarazed.sessionmanager.TarazedResourceManager$getSessionCacheServiceConnection$2$invokeSuspend$$inlined$suspendCancellableCoroutine$lambda$1
                @Override // android.content.ServiceConnection
                public void onServiceConnected(@NotNull ComponentName className, @NotNull IBinder service) {
                    Intrinsics.checkParameterIsNotNull(className, "className");
                    Intrinsics.checkParameterIsNotNull(service, "service");
                    this.this$0.setCacheServiceBound$TarazedAndroidLibrary_release(true);
                    TarazedResourceManager tarazedResourceManager = this.this$0;
                    ISessionClientCache asInterface = ISessionClientCache.Stub.asInterface(service);
                    Intrinsics.checkExpressionValueIsNotNull(asInterface, "ISessionClientCache.Stub.asInterface(service)");
                    tarazedResourceManager.setSessionClientCacheService$TarazedAndroidLibrary_release(asInterface);
                    TarazedSessionLogger logger$TarazedAndroidLibrary_release = this.this$0.getLogger$TarazedAndroidLibrary_release();
                    TarazedResourceManager.Companion unused = TarazedResourceManager.Companion;
                    logger$TarazedAndroidLibrary_release.i("TarazedResourceManager", "Session cache service is bound");
                    if (CancellableContinuation.this.isActive()) {
                        CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                        Unit unit = Unit.INSTANCE;
                        Result.Companion companion = Result.Companion;
                        cancellableContinuation.resumeWith(Result.m10378constructorimpl(unit));
                    }
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(@NotNull ComponentName arg0) {
                    Intrinsics.checkParameterIsNotNull(arg0, "arg0");
                    this.this$0.setCacheServiceBound$TarazedAndroidLibrary_release(false);
                }
            });
            if (!this.this$0.isCacheServiceBound$TarazedAndroidLibrary_release()) {
                TarazedSessionLogger logger$TarazedAndroidLibrary_release = this.this$0.getLogger$TarazedAndroidLibrary_release();
                TarazedResourceManager.Companion unused = TarazedResourceManager.Companion;
                logger$TarazedAndroidLibrary_release.i("TarazedResourceManager", "Binding session cache service to session manager");
                Intent intent = new Intent(this.this$0.getContext$TarazedAndroidLibrary_release(), SessionClientCacheService.class);
                ContextHelper.startService$default(ContextHelper.INSTANCE, this.this$0.getContext$TarazedAndroidLibrary_release(), intent, false, 4, null);
                Context context$TarazedAndroidLibrary_release = this.this$0.getContext$TarazedAndroidLibrary_release();
                ServiceConnection sessionCacheServiceConnection$TarazedAndroidLibrary_release = this.this$0.getSessionCacheServiceConnection$TarazedAndroidLibrary_release();
                if (sessionCacheServiceConnection$TarazedAndroidLibrary_release == null) {
                    Intrinsics.throwNpe();
                }
                context$TarazedAndroidLibrary_release.bindService(intent, sessionCacheServiceConnection$TarazedAndroidLibrary_release, 64);
            } else {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.m10378constructorimpl(unit));
            }
            Object result = cancellableContinuationImpl.getResult();
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (result == coroutine_suspended2) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            if (result == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            TarazedResourceManager$getSessionCacheServiceConnection$2 tarazedResourceManager$getSessionCacheServiceConnection$2 = (TarazedResourceManager$getSessionCacheServiceConnection$2) this.L$1;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
