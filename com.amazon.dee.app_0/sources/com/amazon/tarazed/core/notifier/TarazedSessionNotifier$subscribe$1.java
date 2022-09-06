package com.amazon.tarazed.core.notifier;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
import org.apache.commons.net.telnet.TelnetCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSessionNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.notifier.TarazedSessionNotifier$subscribe$1", f = "TarazedSessionNotifier.kt", i = {0, 0, 0}, l = {TelnetCommand.NOP}, m = "invokeSuspend", n = {"$this$launch", "listenerPriorityPair", "$this$withLock$iv"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes13.dex */
public final class TarazedSessionNotifier$subscribe$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $onReceive;
    final /* synthetic */ ListenerPriority $priority;
    final /* synthetic */ boolean $unsubscribeOnSessionEnd;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TarazedSessionNotifier this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSessionNotifier$subscribe$1(TarazedSessionNotifier tarazedSessionNotifier, Function2 function2, ListenerPriority listenerPriority, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tarazedSessionNotifier;
        this.$onReceive = function2;
        this.$priority = listenerPriority;
        this.$unsubscribeOnSessionEnd = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TarazedSessionNotifier$subscribe$1 tarazedSessionNotifier$subscribe$1 = new TarazedSessionNotifier$subscribe$1(this.this$0, this.$onReceive, this.$priority, this.$unsubscribeOnSessionEnd, completion);
        tarazedSessionNotifier$subscribe$1.p$ = (CoroutineScope) obj;
        return tarazedSessionNotifier$subscribe$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarazedSessionNotifier$subscribe$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Pair<Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object>, ListenerPriority> pair;
        Mutex mutex;
        Mutex mutex2;
        AtomicBoolean atomicBoolean;
        Set set;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            pair = new Pair<>(this.$onReceive, this.$priority);
            mutex = this.this$0.subscribersMutex;
            this.L$0 = coroutineScope;
            this.L$1 = pair;
            this.L$2 = mutex;
            this.label = 1;
            if (mutex.lock(null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutex2 = mutex;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            mutex2 = (Mutex) this.L$2;
            pair = (Pair) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            this.this$0.getSubscribers$TarazedMobileCore_release().add(pair);
            List<Pair<Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object>, ListenerPriority>> subscribers$TarazedMobileCore_release = this.this$0.getSubscribers$TarazedMobileCore_release();
            if (subscribers$TarazedMobileCore_release.size() > 1) {
                CollectionsKt__MutableCollectionsJVMKt.sortWith(subscribers$TarazedMobileCore_release, new Comparator<T>() { // from class: com.amazon.tarazed.core.notifier.TarazedSessionNotifier$subscribe$1$$special$$inlined$sortBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        int compareValues;
                        compareValues = ComparisonsKt__ComparisonsKt.compareValues((ListenerPriority) ((Pair) t).getSecond(), (ListenerPriority) ((Pair) t2).getSecond());
                        return compareValues;
                    }
                });
            }
            if (!this.$unsubscribeOnSessionEnd) {
                set = this.this$0.permanentSubscribers;
                set.add(pair);
            }
            Unit unit = Unit.INSTANCE;
            mutex2.unlock(null);
            atomicBoolean = this.this$0.isListening;
            if (!atomicBoolean.getAndSet(true)) {
                this.this$0.startListeningForEvents();
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex2.unlock(null);
            throw th;
        }
    }
}
