package io.ktor.network.selector;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import io.ktor.util.KtorExperimentalAPI;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SelectorManagerSupport.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001:\u0001/B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0011H\u0004J\u0018\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 H\u0004J\u001a\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0004J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u0013H\u0004J$\u0010#\u001a\u00020\u00192\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00130%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0004J \u0010(\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0011H\u0004J\u0010\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u0011H$J!\u0010+\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010.R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR,\u0010\u0012\u001a\u0004\u0018\u00010\u0011*\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lio/ktor/network/selector/SelectorManagerSupport;", "Lio/ktor/network/selector/SelectorManager;", "()V", "cancelled", "", "getCancelled", "()I", "setCancelled", "(I)V", "pending", "getPending", "setPending", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER, "Ljava/nio/channels/spi/SelectorProvider;", "getProvider", "()Ljava/nio/channels/spi/SelectorProvider;", "newValue", "Lio/ktor/network/selector/Selectable;", "subject", "Ljava/nio/channels/SelectionKey;", "getSubject", "(Ljava/nio/channels/SelectionKey;)Lio/ktor/network/selector/Selectable;", "setSubject", "(Ljava/nio/channels/SelectionKey;Lio/ktor/network/selector/Selectable;)V", "applyInterest", "", "selector", "Ljava/nio/channels/Selector;", "s", "cancelAllSuspensions", "attachment", "t", "", "handleSelectedKey", "key", "handleSelectedKeys", "selectedKeys", "", QuickTimeAtomTypes.ATOM_KEYS, "", "notifyClosedImpl", "publishInterest", "selectable", "select", "interest", "Lio/ktor/network/selector/SelectInterest;", "(Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectInterest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ClosedSelectorCancellationException", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class SelectorManagerSupport implements SelectorManager {
    private int cancelled;
    private int pending;
    @NotNull
    private final SelectorProvider provider;

    /* compiled from: SelectorManagerSupport.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/network/selector/SelectorManagerSupport$ClosedSelectorCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "()V", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class ClosedSelectorCancellationException extends CancellationException {
        public ClosedSelectorCancellationException() {
            super("Closed selector");
        }
    }

    public SelectorManagerSupport() {
        SelectorProvider provider = SelectorProvider.provider();
        Intrinsics.checkExpressionValueIsNotNull(provider, "SelectorProvider.provider()");
        this.provider = provider;
    }

    private final Selectable getSubject(@NotNull SelectionKey selectionKey) {
        Object attachment = selectionKey.attachment();
        if (!(attachment instanceof Selectable)) {
            attachment = null;
        }
        return (Selectable) attachment;
    }

    private final void setSubject(@NotNull SelectionKey selectionKey, Selectable selectable) {
        selectionKey.attach(selectable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void applyInterest(@NotNull Selector selector, @NotNull Selectable s) {
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        Intrinsics.checkParameterIsNotNull(s, "s");
        try {
            SelectableChannel mo10306getChannel = s.mo10306getChannel();
            SelectionKey keyFor = mo10306getChannel.keyFor(selector);
            int interestedOps = s.getInterestedOps();
            if (keyFor == null) {
                if (interestedOps != 0) {
                    mo10306getChannel.register(selector, interestedOps, s);
                }
            } else if (keyFor.interestOps() != interestedOps) {
                keyFor.interestOps(interestedOps);
            }
            if (interestedOps == 0) {
                return;
            }
            this.pending++;
        } catch (Throwable th) {
            SelectionKey keyFor2 = s.mo10306getChannel().keyFor(selector);
            if (keyFor2 != null) {
                keyFor2.cancel();
            }
            cancelAllSuspensions(s, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void cancelAllSuspensions(@NotNull Selectable attachment, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(attachment, "attachment");
        Intrinsics.checkParameterIsNotNull(t, "t");
        InterestSuspensionsMap suspensions = attachment.getSuspensions();
        for (SelectInterest selectInterest : SelectInterest.Companion.getAllInterests()) {
            CancellableContinuation<Unit> removeSuspension = suspensions.removeSuspension(selectInterest);
            if (removeSuspension != null) {
                Result.Companion companion = Result.Companion;
                removeSuspension.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(t)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getCancelled() {
        return this.cancelled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getPending() {
        return this.pending;
    }

    @Override // io.ktor.network.selector.SelectorManager
    @NotNull
    public final SelectorProvider getProvider() {
        return this.provider;
    }

    protected final void handleSelectedKey(@NotNull SelectionKey key) {
        CancellableContinuation<Unit> removeSuspension;
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            int readyOps = key.readyOps();
            int interestOps = key.interestOps();
            Selectable subject = getSubject(key);
            if (subject == null) {
                key.cancel();
                this.cancelled++;
                return;
            }
            Unit unit = Unit.INSTANCE;
            InterestSuspensionsMap suspensions = subject.getSuspensions();
            int[] flags = SelectInterest.Companion.getFlags();
            int length = flags.length;
            for (int i = 0; i < length; i++) {
                if ((flags[i] & readyOps) != 0 && (removeSuspension = suspensions.removeSuspension(i)) != null) {
                    Result.Companion companion = Result.Companion;
                    removeSuspension.resumeWith(Result.m10378constructorimpl(unit));
                }
            }
            int i2 = (~readyOps) & interestOps;
            if (i2 != interestOps) {
                key.interestOps(i2);
            }
            if (i2 == 0) {
                return;
            }
            this.pending++;
        } catch (Throwable th) {
            key.cancel();
            this.cancelled++;
            Selectable subject2 = getSubject(key);
            if (subject2 == null) {
                return;
            }
            cancelAllSuspensions(subject2, th);
            setSubject(key, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void handleSelectedKeys(@NotNull Set<SelectionKey> selectedKeys, @NotNull Set<? extends SelectionKey> keys) {
        Intrinsics.checkParameterIsNotNull(selectedKeys, "selectedKeys");
        Intrinsics.checkParameterIsNotNull(keys, "keys");
        int size = selectedKeys.size();
        this.pending = keys.size() - size;
        this.cancelled = 0;
        if (size > 0) {
            Iterator<SelectionKey> it2 = selectedKeys.iterator();
            while (it2.hasNext()) {
                handleSelectedKey(it2.next());
                it2.remove();
            }
        }
    }

    protected final void notifyClosedImpl(@NotNull Selector selector, @NotNull SelectionKey key, @NotNull Selectable attachment) {
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(attachment, "attachment");
        cancelAllSuspensions(attachment, new ClosedChannelException());
        setSubject(key, null);
        selector.wakeup();
    }

    protected abstract void publishInterest(@NotNull Selectable selectable);

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    @Override // io.ktor.network.selector.SelectorManager
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object select(@org.jetbrains.annotations.NotNull io.ktor.network.selector.Selectable r5, @org.jetbrains.annotations.NotNull io.ktor.network.selector.SelectInterest r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.ktor.network.selector.SelectorManagerSupport$select$1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.network.selector.SelectorManagerSupport$select$1 r0 = (io.ktor.network.selector.SelectorManagerSupport$select$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.selector.SelectorManagerSupport$select$1 r0 = new io.ktor.network.selector.SelectorManagerSupport$select$1
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r5 = r0.L$2
            io.ktor.network.selector.SelectInterest r5 = (io.ktor.network.selector.SelectInterest) r5
            java.lang.Object r5 = r0.L$1
            io.ktor.network.selector.Selectable r5 = (io.ktor.network.selector.Selectable) r5
            java.lang.Object r5 = r0.L$0
            io.ktor.network.selector.SelectorManagerSupport r5 = (io.ktor.network.selector.SelectorManagerSupport) r5
            boolean r5 = r7 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L36
            goto L90
        L36:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        L3b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L43:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L9f
            int r7 = r5.getInterestedOps()
            int r2 = r6.getFlag()
            r7 = r7 & r2
            if (r7 == 0) goto L54
            r7 = r3
            goto L55
        L54:
            r7 = 0
        L55:
            if (r7 == 0) goto L93
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r7 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r7.<init>(r2, r3)
            io.ktor.network.selector.SelectorManagerSupport$select$$inlined$suspendCancellableCoroutine$lambda$1 r2 = new io.ktor.network.selector.SelectorManagerSupport$select$$inlined$suspendCancellableCoroutine$lambda$1
            r2.<init>(r4, r5, r6)
            r7.invokeOnCancellation(r2)
            io.ktor.network.selector.InterestSuspensionsMap r2 = r5.getSuspensions()
            r2.addSuspension(r6, r7)
            boolean r6 = r7.isCancelled()
            if (r6 != 0) goto L80
            r4.publishInterest(r5)
        L80:
            java.lang.Object r5 = r7.getResult()
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto L8d
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L8d:
            if (r5 != r1) goto L90
            return r1
        L90:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L93:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Failed requirement."
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L9f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.SelectorManagerSupport.select(io.ktor.network.selector.Selectable, io.ktor.network.selector.SelectInterest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    protected final void setCancelled(int i) {
        this.cancelled = i;
    }

    protected final void setPending(int i) {
        this.pending = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void cancelAllSuspensions(@NotNull Selector selector, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        if (th == null) {
            th = new ClosedSelectorCancellationException();
        }
        Set<SelectionKey> keys = selector.keys();
        Intrinsics.checkExpressionValueIsNotNull(keys, "selector.keys()");
        for (SelectionKey k : keys) {
            try {
                Intrinsics.checkExpressionValueIsNotNull(k, "k");
                if (k.isValid()) {
                    k.interestOps(0);
                }
            } catch (CancelledKeyException unused) {
            }
            Object attachment = k.attachment();
            if (!(attachment instanceof Selectable)) {
                attachment = null;
            }
            Selectable selectable = (Selectable) attachment;
            if (selectable != null) {
                cancelAllSuspensions(selectable, th);
            }
            k.cancel();
        }
    }
}
