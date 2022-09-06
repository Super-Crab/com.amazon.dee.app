package io.ktor.network.selector;

import java.nio.channels.SelectableChannel;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import org.jetbrains.annotations.NotNull;
/* compiled from: Selectable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0010\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\u0018\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lio/ktor/network/selector/SelectableBase;", "Lio/ktor/network/selector/Selectable;", "channel", "Ljava/nio/channels/SelectableChannel;", "(Ljava/nio/channels/SelectableChannel;)V", "getChannel", "()Ljava/nio/channels/SelectableChannel;", "interestedOps", "", "getInterestedOps", "()I", "setInterestedOps", "(I)V", "suspensions", "Lio/ktor/network/selector/InterestSuspensionsMap;", "getSuspensions", "()Lio/ktor/network/selector/InterestSuspensionsMap;", "close", "", "dispose", "interestOp", "interest", "Lio/ktor/network/selector/SelectInterest;", "state", "", "Companion", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public class SelectableBase implements Selectable {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final AtomicIntegerFieldUpdater<SelectableBase> InterestedOps;
    @NotNull
    private final SelectableChannel channel;
    private volatile int interestedOps;
    @NotNull
    private final InterestSuspensionsMap suspensions;

    /* compiled from: Selectable.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/network/selector/SelectableBase$Companion;", "", "()V", "InterestedOps", "Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "Lio/ktor/network/selector/SelectableBase;", "kotlin.jvm.PlatformType", "getInterestedOps", "()Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final AtomicIntegerFieldUpdater<SelectableBase> getInterestedOps() {
            return SelectableBase.InterestedOps;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        AtomicIntegerFieldUpdater<SelectableBase> newUpdater = AtomicIntegerFieldUpdater.newUpdater(SelectableBase.class, "interestedOps");
        if (newUpdater == null) {
            Intrinsics.throwNpe();
        }
        InterestedOps = newUpdater;
    }

    public SelectableBase(@NotNull SelectableChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this.channel = channel;
        this.suspensions = new InterestSuspensionsMap();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        setInterestedOps(0);
        InterestSuspensionsMap suspensions = getSuspensions();
        for (SelectInterest selectInterest : SelectInterest.Companion.getAllInterests()) {
            CancellableContinuation<Unit> removeSuspension = suspensions.removeSuspension(selectInterest);
            if (removeSuspension != null) {
                ClosedChannelCancellationException closedChannelCancellationException = new ClosedChannelCancellationException();
                Result.Companion companion = Result.Companion;
                removeSuspension.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(closedChannelCancellationException)));
            }
        }
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        close();
    }

    @Override // io.ktor.network.selector.Selectable
    @NotNull
    /* renamed from: getChannel */
    public SelectableChannel mo10306getChannel() {
        return this.channel;
    }

    @Override // io.ktor.network.selector.Selectable
    public int getInterestedOps() {
        return this.interestedOps;
    }

    @Override // io.ktor.network.selector.Selectable
    @NotNull
    public InterestSuspensionsMap getSuspensions() {
        return this.suspensions;
    }

    @Override // io.ktor.network.selector.Selectable
    public void interestOp(@NotNull SelectInterest interest, boolean z) {
        int interestedOps;
        Intrinsics.checkParameterIsNotNull(interest, "interest");
        int flag = interest.getFlag();
        do {
            interestedOps = getInterestedOps();
        } while (!InterestedOps.compareAndSet(this, interestedOps, z ? interestedOps | flag : (~flag) & interestedOps));
    }

    public void setInterestedOps(int i) {
        this.interestedOps = i;
    }
}
