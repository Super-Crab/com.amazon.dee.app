package io.ktor.network.selector;

import io.ktor.util.InternalAPI;
import io.ktor.util.KtorExperimentalAPI;
import java.io.Closeable;
import java.nio.channels.SelectableChannel;
import kotlin.Metadata;
import kotlinx.coroutines.DisposableHandle;
import org.jetbrains.annotations.NotNull;
/* compiled from: Selectable.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\f8&X§\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lio/ktor/network/selector/Selectable;", "Ljava/io/Closeable;", "Lkotlinx/coroutines/DisposableHandle;", "channel", "Ljava/nio/channels/SelectableChannel;", "getChannel", "()Ljava/nio/channels/SelectableChannel;", "interestedOps", "", "getInterestedOps", "()I", "suspensions", "Lio/ktor/network/selector/InterestSuspensionsMap;", "suspensions$annotations", "()V", "getSuspensions", "()Lio/ktor/network/selector/InterestSuspensionsMap;", "interestOp", "", "interest", "Lio/ktor/network/selector/SelectInterest;", "state", "", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface Selectable extends Closeable, DisposableHandle {

    /* compiled from: Selectable.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @InternalAPI
        public static /* synthetic */ void suspensions$annotations() {
        }
    }

    @NotNull
    /* renamed from: getChannel */
    SelectableChannel mo10306getChannel();

    int getInterestedOps();

    @NotNull
    InterestSuspensionsMap getSuspensions();

    void interestOp(@NotNull SelectInterest selectInterest, boolean z);
}
