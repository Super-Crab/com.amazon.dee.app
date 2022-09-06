package io.ktor.network.sockets;

import io.ktor.util.KtorExperimentalAPI;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DisposableHandle;
import org.jetbrains.annotations.NotNull;
/* compiled from: Sockets.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\n\u001a\u00020\u0005H\u0016R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lio/ktor/network/sockets/ASocket;", "Ljava/io/Closeable;", "Lkotlinx/coroutines/DisposableHandle;", "socketContext", "Lkotlinx/coroutines/Deferred;", "", "socketContext$annotations", "()V", "getSocketContext", "()Lkotlinx/coroutines/Deferred;", "dispose", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface ASocket extends Closeable, DisposableHandle {

    /* compiled from: Sockets.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void dispose(ASocket aSocket) {
            try {
                aSocket.close();
            } catch (Throwable unused) {
            }
        }

        @KtorExperimentalAPI
        public static /* synthetic */ void socketContext$annotations() {
        }
    }

    @Override // kotlinx.coroutines.DisposableHandle
    void dispose();

    @NotNull
    /* renamed from: getSocketContext */
    Deferred<Unit> mo10305getSocketContext();
}
