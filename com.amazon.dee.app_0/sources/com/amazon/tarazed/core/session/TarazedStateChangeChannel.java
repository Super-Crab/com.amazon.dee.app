package com.amazon.tarazed.core.session;

import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.tarazed.core.session.sessionEvents.SessionEvents;
import kotlin.Metadata;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedStateChangeChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\u000e\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0011\u0010\u0011\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013H\u0086\u0002J\u001f\u0010\u0014\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lcom/amazon/tarazed/core/session/TarazedStateChangeChannel;", "", "()V", "channelMutex", "Lkotlinx/coroutines/sync/Mutex;", "isChannelOpen", "", "isChannelOpen$TarazedMobileCore_release", "()Z", "setChannelOpen$TarazedMobileCore_release", "(Z)V", "stateChangeChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeRequest;", DeviceStateModule.CANCEL, "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "processFirst", SessionEvents.STATE_CHANGE, "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "send", "event", "(Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedStateChangeChannel {
    private final Channel<TarazedSessionStateChangeRequest> stateChangeChannel = ChannelKt.Channel(Integer.MAX_VALUE);
    private final Mutex channelMutex = MutexKt.Mutex$default(false, 1, null);
    private boolean isChannelOpen = true;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0052 A[Catch: all -> 0x0062, TryCatch #0 {all -> 0x0062, blocks: (B:18:0x004e, B:20:0x0052, B:21:0x005a), top: B:27:0x004e }] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object cancel(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.tarazed.core.session.TarazedStateChangeChannel$cancel$1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.tarazed.core.session.TarazedStateChangeChannel$cancel$1 r0 = (com.amazon.tarazed.core.session.TarazedStateChangeChannel$cancel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedStateChangeChannel$cancel$1 r0 = new com.amazon.tarazed.core.session.TarazedStateChangeChannel$cancel$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedStateChangeChannel r0 = (com.amazon.tarazed.core.session.TarazedStateChangeChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = r1
            goto L4e
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3b:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.sync.Mutex r6 = r5.channelMutex
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r0 = r6.lock(r4, r0)
            if (r0 != r1) goto L4d
            return r1
        L4d:
            r0 = r5
        L4e:
            boolean r1 = r0.isChannelOpen     // Catch: java.lang.Throwable -> L62
            if (r1 == 0) goto L5a
            kotlinx.coroutines.channels.Channel<com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest> r1 = r0.stateChangeChannel     // Catch: java.lang.Throwable -> L62
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default(r1, r4, r3, r4)     // Catch: java.lang.Throwable -> L62
            r1 = 0
            r0.isChannelOpen = r1     // Catch: java.lang.Throwable -> L62
        L5a:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L62
            r6.unlock(r4)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L62:
            r0 = move-exception
            r6.unlock(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedStateChangeChannel.cancel(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0052 A[Catch: all -> 0x0062, TryCatch #0 {all -> 0x0062, blocks: (B:18:0x004e, B:20:0x0052, B:21:0x005a), top: B:27:0x004e }] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object close(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.tarazed.core.session.TarazedStateChangeChannel$close$1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.tarazed.core.session.TarazedStateChangeChannel$close$1 r0 = (com.amazon.tarazed.core.session.TarazedStateChangeChannel$close$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedStateChangeChannel$close$1 r0 = new com.amazon.tarazed.core.session.TarazedStateChangeChannel$close$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedStateChangeChannel r0 = (com.amazon.tarazed.core.session.TarazedStateChangeChannel) r0
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = r1
            goto L4e
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3b:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.sync.Mutex r6 = r5.channelMutex
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r0 = r6.lock(r4, r0)
            if (r0 != r1) goto L4d
            return r1
        L4d:
            r0 = r5
        L4e:
            boolean r1 = r0.isChannelOpen     // Catch: java.lang.Throwable -> L62
            if (r1 == 0) goto L5a
            kotlinx.coroutines.channels.Channel<com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest> r1 = r0.stateChangeChannel     // Catch: java.lang.Throwable -> L62
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r1, r4, r3, r4)     // Catch: java.lang.Throwable -> L62
            r1 = 0
            r0.isChannelOpen = r1     // Catch: java.lang.Throwable -> L62
        L5a:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L62
            r6.unlock(r4)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L62:
            r0 = move-exception
            r6.unlock(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedStateChangeChannel.close(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean isChannelOpen$TarazedMobileCore_release() {
        return this.isChannelOpen;
    }

    @NotNull
    public final ChannelIterator<TarazedSessionStateChangeRequest> iterator() {
        return this.stateChangeChannel.iterator();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object processFirst(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<kotlin.Unit> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.amazon.tarazed.core.session.TarazedStateChangeChannel$processFirst$1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.tarazed.core.session.TarazedStateChangeChannel$processFirst$1 r0 = (com.amazon.tarazed.core.session.TarazedStateChangeChannel$processFirst$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedStateChangeChannel$processFirst$1 r0 = new com.amazon.tarazed.core.session.TarazedStateChangeChannel$processFirst$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 != r4) goto L38
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r1 = r0.L$1
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedStateChangeChannel r0 = (com.amazon.tarazed.core.session.TarazedStateChangeChannel) r0
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
            r6 = r1
            goto L54
        L38:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L40:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.sync.Mutex r7 = r5.channelMutex
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r4
            java.lang.Object r0 = r7.lock(r3, r0)
            if (r0 != r1) goto L54
            return r1
        L54:
            r6.mo12560invoke()     // Catch: java.lang.Throwable -> L5f
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L5f
            r7.unlock(r3)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L5f:
            r6 = move-exception
            r7.unlock(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedStateChangeChannel.processFirst(kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006e A[Catch: all -> 0x0039, TryCatch #0 {all -> 0x0039, blocks: (B:13:0x0035, B:29:0x007f, B:24:0x006a, B:26:0x006e), top: B:34:0x0023 }] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v1, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v4, types: [kotlinx.coroutines.sync.Mutex] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object send(@org.jetbrains.annotations.NotNull com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.amazon.tarazed.core.session.TarazedStateChangeChannel$send$1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.tarazed.core.session.TarazedStateChangeChannel$send$1 r0 = (com.amazon.tarazed.core.session.TarazedStateChangeChannel$send$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.core.session.TarazedStateChangeChannel$send$1 r0 = new com.amazon.tarazed.core.session.TarazedStateChangeChannel$send$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L53
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.sync.Mutex r7 = (kotlinx.coroutines.sync.Mutex) r7
            java.lang.Object r1 = r0.L$1
            com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest r1 = (com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest) r1
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.core.session.TarazedStateChangeChannel r0 = (com.amazon.tarazed.core.session.TarazedStateChangeChannel) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L39
            goto L7f
        L39:
            r8 = move-exception
            goto L87
        L3b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L43:
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.sync.Mutex r7 = (kotlinx.coroutines.sync.Mutex) r7
            java.lang.Object r2 = r0.L$1
            com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest r2 = (com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest) r2
            java.lang.Object r4 = r0.L$0
            com.amazon.tarazed.core.session.TarazedStateChangeChannel r4 = (com.amazon.tarazed.core.session.TarazedStateChangeChannel) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6a
        L53:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.sync.Mutex r8 = r6.channelMutex
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r4
            java.lang.Object r2 = r8.lock(r5, r0)
            if (r2 != r1) goto L67
            return r1
        L67:
            r4 = r6
            r2 = r7
            r7 = r8
        L6a:
            boolean r8 = r4.isChannelOpen     // Catch: java.lang.Throwable -> L39
            if (r8 == 0) goto L7f
            kotlinx.coroutines.channels.Channel<com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest> r8 = r4.stateChangeChannel     // Catch: java.lang.Throwable -> L39
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L39
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L39
            r0.L$2 = r7     // Catch: java.lang.Throwable -> L39
            r0.label = r3     // Catch: java.lang.Throwable -> L39
            java.lang.Object r8 = r8.send(r2, r0)     // Catch: java.lang.Throwable -> L39
            if (r8 != r1) goto L7f
            return r1
        L7f:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L39
            r7.unlock(r5)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L87:
            r7.unlock(r5)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.session.TarazedStateChangeChannel.send(com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void setChannelOpen$TarazedMobileCore_release(boolean z) {
        this.isChannelOpen = z;
    }
}
