package io.ktor.network.selector;

import io.ktor.util.KtorExperimentalAPI;
import java.io.Closeable;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.YieldKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ActorSelectorManager.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001*B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0018\u001a\u00020\u000bH\u0016J\u0011\u0010\u0019\u001a\u00020\u000bH\u0082Hø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0013H\u0016J'\u0010\u001d\u001a\u00020\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u001e\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u001e\u0010 \u001a\u00020\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u001e\u001a\u00020\u0015H\u0002J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u0013H\u0014J\u0019\u0010#\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%J\b\u0010&\u001a\u00020\u000bH\u0002J\u001d\u0010'\u001a\u0004\u0018\u00010\u0013*\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u0004\u0018\u00010\u0013*\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lio/ktor/network/selector/ActorSelectorManager;", "Lio/ktor/network/selector/SelectorManagerSupport;", "Ljava/io/Closeable;", "Lkotlinx/coroutines/CoroutineScope;", "dispatcher", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "closed", "", "continuation", "Lio/ktor/network/selector/ActorSelectorManager$ContinuationHolder;", "", "Lkotlin/coroutines/Continuation;", "coroutineContext", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "inSelect", "mb", "Lio/ktor/network/selector/LockFreeMPSCQueue;", "Lio/ktor/network/selector/Selectable;", "selectorRef", "Ljava/nio/channels/Selector;", "wakeup", "Ljava/util/concurrent/atomic/AtomicLong;", "close", "dispatchIfNeeded", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "notifyClosed", "s", "process", "selector", "(Lio/ktor/network/selector/LockFreeMPSCQueue;Ljava/nio/channels/Selector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processInterests", "publishInterest", "selectable", "select", "", "(Ljava/nio/channels/Selector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectWakeup", "receiveOrNull", "(Lio/ktor/network/selector/LockFreeMPSCQueue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNullSuspend", "ContinuationHolder", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ActorSelectorManager extends SelectorManagerSupport implements Closeable, CoroutineScope {
    private volatile boolean closed;
    private final ContinuationHolder<Unit, Continuation<Unit>> continuation;
    @NotNull
    private final CoroutineContext coroutineContext;
    private volatile boolean inSelect;
    private final LockFreeMPSCQueue<Selectable> mb;
    private volatile Selector selectorRef;
    private final AtomicLong wakeup;

    /* compiled from: ActorSelectorManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io.ktor.network.selector.ActorSelectorManager$1", f = "ActorSelectorManager.kt", i = {0}, l = {42}, m = "invokeSuspend", n = {"selector"}, s = {"L$0"})
    /* renamed from: io.ktor.network.selector.ActorSelectorManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: invoke */
        public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00a3 A[Catch: all -> 0x0069, LOOP:0: B:31:0x0095->B:33:0x00a3, LOOP_END, TRY_LEAVE, TryCatch #1 {all -> 0x0069, blocks: (B:23:0x0050, B:24:0x0065, B:31:0x0095, B:33:0x00a3, B:30:0x007f, B:29:0x006c), top: B:46:0x0008 }] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00b0 A[SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r7v19 */
        /* JADX WARN: Type inference failed for: r7v20 */
        /* JADX WARN: Type inference failed for: r7v9, types: [java.nio.channels.spi.AbstractSelector, java.nio.channels.Selector] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
            /*
                Method dump skipped, instructions count: 229
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ActorSelectorManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ&\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00028\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fH\u0086\b¢\u0006\u0002\u0010\u0010R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lio/ktor/network/selector/ActorSelectorManager$ContinuationHolder;", "R", "C", "Lkotlin/coroutines/Continuation;", "", "()V", "ref", "Ljava/util/concurrent/atomic/AtomicReference;", "resume", "", "value", "(Ljava/lang/Object;)Z", "suspendIf", "continuation", "condition", "Lkotlin/Function0;", "(Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class ContinuationHolder<R, C extends Continuation<? super R>> {
        private final AtomicReference<C> ref = new AtomicReference<>(null);

        public final boolean resume(R r) {
            C andSet = this.ref.getAndSet(null);
            if (andSet != null) {
                Result.Companion companion = Result.Companion;
                andSet.resumeWith(Result.m10378constructorimpl(r));
                return true;
            }
            return false;
        }

        @Nullable
        public final Object suspendIf(@NotNull C continuation, @NotNull Function0<Boolean> condition) {
            Object coroutine_suspended;
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            Intrinsics.checkParameterIsNotNull(condition, "condition");
            if (!condition.mo12560invoke().booleanValue()) {
                return null;
            }
            if (this.ref.compareAndSet(null, continuation)) {
                if (!condition.mo12560invoke().booleanValue() && this.ref.compareAndSet(continuation, null)) {
                    return null;
                }
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                return coroutine_suspended;
            }
            throw new IllegalStateException("Continuation is already set");
        }
    }

    public ActorSelectorManager(@NotNull CoroutineContext dispatcher) {
        Intrinsics.checkParameterIsNotNull(dispatcher, "dispatcher");
        this.wakeup = new AtomicLong();
        this.continuation = new ContinuationHolder<>();
        this.mb = new LockFreeMPSCQueue<>();
        this.coroutineContext = dispatcher.plus(new CoroutineName("selector"));
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new AnonymousClass1(null), 3, null);
    }

    private final /* synthetic */ Object dispatchIfNeeded(Continuation<? super Unit> continuation) {
        InlineMarker.mark(0);
        YieldKt.yield(continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    private final void processInterests(LockFreeMPSCQueue<Selectable> lockFreeMPSCQueue, Selector selector) {
        while (true) {
            Selectable removeFirstOrNull = lockFreeMPSCQueue.removeFirstOrNull();
            if (removeFirstOrNull != null) {
                applyInterest(selector, removeFirstOrNull);
            } else {
                return;
            }
        }
    }

    private final void selectWakeup() {
        Selector selector;
        if (this.wakeup.incrementAndGet() != 1 || !this.inSelect || (selector = this.selectorRef) == null) {
            return;
        }
        selector.wakeup();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
        this.mb.close();
        if (!this.continuation.resume(Unit.INSTANCE)) {
            selectWakeup();
        }
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.network.selector.SelectorManager
    public void notifyClosed(@NotNull Selectable s) {
        SelectionKey keyFor;
        Intrinsics.checkParameterIsNotNull(s, "s");
        cancelAllSuspensions(s, new ClosedChannelException());
        Selector selector = this.selectorRef;
        if (selector == null || (keyFor = s.mo10306getChannel().keyFor(selector)) == null) {
            return;
        }
        keyFor.cancel();
        selectWakeup();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x010d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00aa -> B:33:0x0086). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00c4 -> B:33:0x0086). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00d6 -> B:33:0x0086). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x0106 -> B:57:0x0109). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object process(@org.jetbrains.annotations.NotNull io.ktor.network.selector.LockFreeMPSCQueue<io.ktor.network.selector.Selectable> r11, @org.jetbrains.annotations.NotNull java.nio.channels.Selector r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.process(io.ktor.network.selector.LockFreeMPSCQueue, java.nio.channels.Selector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.network.selector.SelectorManagerSupport
    protected void publishInterest(@NotNull Selectable selectable) {
        Intrinsics.checkParameterIsNotNull(selectable, "selectable");
        try {
            if (this.mb.addLast(selectable)) {
                if (this.continuation.resume(Unit.INSTANCE)) {
                    return;
                }
                selectWakeup();
            } else if (!selectable.mo10306getChannel().isOpen()) {
                throw new ClosedChannelException();
            } else {
                throw new ClosedSelectorException();
            }
        } catch (Throwable th) {
            cancelAllSuspensions(selectable, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object receiveOrNull(@org.jetbrains.annotations.NotNull io.ktor.network.selector.LockFreeMPSCQueue<io.ktor.network.selector.Selectable> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.network.selector.Selectable> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.network.selector.ActorSelectorManager$receiveOrNull$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.network.selector.ActorSelectorManager$receiveOrNull$1 r0 = (io.ktor.network.selector.ActorSelectorManager$receiveOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.selector.ActorSelectorManager$receiveOrNull$1 r0 = new io.ktor.network.selector.ActorSelectorManager$receiveOrNull$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r5 = r0.L$1
            io.ktor.network.selector.LockFreeMPSCQueue r5 = (io.ktor.network.selector.LockFreeMPSCQueue) r5
            java.lang.Object r5 = r0.L$0
            io.ktor.network.selector.ActorSelectorManager r5 = (io.ktor.network.selector.ActorSelectorManager) r5
            boolean r5 = r6 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L32
            goto L59
        L32:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        L37:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3f:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L5c
            java.lang.Object r6 = r5.removeFirstOrNull()
            io.ktor.network.selector.Selectable r6 = (io.ktor.network.selector.Selectable) r6
            if (r6 == 0) goto L4c
            goto L5b
        L4c:
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r4.receiveOrNullSuspend(r5, r0)
            if (r6 != r1) goto L59
            return r1
        L59:
            io.ktor.network.selector.Selectable r6 = (io.ktor.network.selector.Selectable) r6
        L5b:
            return r6
        L5c:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.receiveOrNull(io.ktor.network.selector.LockFreeMPSCQueue, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0051 A[SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object receiveOrNullSuspend(@org.jetbrains.annotations.NotNull io.ktor.network.selector.LockFreeMPSCQueue<io.ktor.network.selector.Selectable> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.network.selector.Selectable> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1 r0 = (io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1 r0 = new io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r8 = r0.L$2
            io.ktor.network.selector.Selectable r8 = (io.ktor.network.selector.Selectable) r8
            java.lang.Object r8 = r0.L$1
            io.ktor.network.selector.LockFreeMPSCQueue r8 = (io.ktor.network.selector.LockFreeMPSCQueue) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.network.selector.ActorSelectorManager r2 = (io.ktor.network.selector.ActorSelectorManager) r2
            boolean r4 = r9 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L37
            r9 = r2
            goto L49
        L37:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r8 = r9.exception
            throw r8
        L3c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L44:
            boolean r2 = r9 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lc8
            r9 = r7
        L49:
            java.lang.Object r2 = r8.removeFirstOrNull()
            io.ktor.network.selector.Selectable r2 = (io.ktor.network.selector.Selectable) r2
            if (r2 == 0) goto L52
            return r2
        L52:
            boolean r4 = r9.closed
            r5 = 0
            if (r4 == 0) goto L58
            return r5
        L58:
            r0.L$0 = r9
            r0.L$1 = r8
            r0.L$2 = r2
            r0.label = r3
            io.ktor.network.selector.ActorSelectorManager$ContinuationHolder r2 = access$getContinuation$p(r9)
            boolean r4 = r8.isEmpty()
            r6 = 0
            if (r4 == 0) goto L73
            boolean r4 = access$getClosed$p(r9)
            if (r4 != 0) goto L73
            r4 = r3
            goto L74
        L73:
            r4 = r6
        L74:
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto L7f
            goto Laf
        L7f:
            java.util.concurrent.atomic.AtomicReference r4 = io.ktor.network.selector.ActorSelectorManager.ContinuationHolder.access$getRef$p(r2)
            boolean r4 = r4.compareAndSet(r5, r0)
            if (r4 == 0) goto Lc0
            boolean r4 = r8.isEmpty()
            if (r4 == 0) goto L96
            boolean r4 = access$getClosed$p(r9)
            if (r4 != 0) goto L96
            r6 = r3
        L96:
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto Lab
            java.util.concurrent.atomic.AtomicReference r2 = io.ktor.network.selector.ActorSelectorManager.ContinuationHolder.access$getRef$p(r2)
            boolean r2 = r2.compareAndSet(r0, r5)
            if (r2 == 0) goto Lab
            goto Laf
        Lab:
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
        Laf:
            if (r5 == 0) goto Lb2
            goto Lb4
        Lb2:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
        Lb4:
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r2) goto Lbd
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        Lbd:
            if (r5 != r1) goto L49
            return r1
        Lc0:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Continuation is already set"
            r8.<init>(r9)
            throw r8
        Lc8:
            kotlin.Result$Failure r9 = (kotlin.Result.Failure) r9
            java.lang.Throwable r8 = r9.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.receiveOrNullSuspend(io.ktor.network.selector.LockFreeMPSCQueue, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object select(@org.jetbrains.annotations.NotNull java.nio.channels.Selector r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.network.selector.ActorSelectorManager$select$1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.network.selector.ActorSelectorManager$select$1 r0 = (io.ktor.network.selector.ActorSelectorManager$select$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.selector.ActorSelectorManager$select$1 r0 = new io.ktor.network.selector.ActorSelectorManager$select$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r6 = r0.L$2
            io.ktor.network.selector.ActorSelectorManager r6 = (io.ktor.network.selector.ActorSelectorManager) r6
            java.lang.Object r6 = r0.L$1
            java.nio.channels.Selector r6 = (java.nio.channels.Selector) r6
            java.lang.Object r0 = r0.L$0
            io.ktor.network.selector.ActorSelectorManager r0 = (io.ktor.network.selector.ActorSelectorManager) r0
            boolean r1 = r7 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L36
            goto L59
        L36:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L3b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L43:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L7f
            r5.inSelect = r3
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r5
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.YieldKt.yield(r0)
            if (r7 != r1) goto L58
            return r1
        L58:
            r0 = r5
        L59:
            java.util.concurrent.atomic.AtomicLong r7 = r0.wakeup
            long r1 = r7.get()
            r3 = 0
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r1 = 0
            if (r7 != 0) goto L6f
            r2 = 500(0x1f4, double:2.47E-321)
            int r6 = r6.select(r2)
            r0.inSelect = r1
            goto L7a
        L6f:
            r0.inSelect = r1
            java.util.concurrent.atomic.AtomicLong r7 = r0.wakeup
            r7.set(r3)
            int r6 = r6.selectNow()
        L7a:
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L7f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.select(java.nio.channels.Selector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
