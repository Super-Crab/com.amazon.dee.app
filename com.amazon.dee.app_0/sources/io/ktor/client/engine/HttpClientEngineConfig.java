package io.ktor.client.engine;

import io.ktor.client.HttpClientDsl;
import io.ktor.client.response.HttpResponseConfig;
import io.ktor.util.KtorExperimentalAPI;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpClientEngineConfig.kt */
@HttpClientDsl
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lio/ktor/client/engine/HttpClientEngineConfig;", "", "()V", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher$annotations", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "setDispatcher", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "pipelining", "", "getPipelining", "()Z", "setPipelining", "(Z)V", "response", "Lio/ktor/client/response/HttpResponseConfig;", "getResponse", "()Lio/ktor/client/response/HttpResponseConfig;", "threadsCount", "", "threadsCount$annotations", "getThreadsCount", "()I", "setThreadsCount", "(I)V", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public class HttpClientEngineConfig {
    @Nullable
    private CoroutineDispatcher dispatcher;
    private boolean pipelining;
    private int threadsCount = 4;
    @NotNull
    private final HttpResponseConfig response = new HttpResponseConfig();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Custom dispatcher is deprecated. Consider using threadsCount instead.")
    public static /* synthetic */ void dispatcher$annotations() {
    }

    @KtorExperimentalAPI
    public static /* synthetic */ void threadsCount$annotations() {
    }

    @Nullable
    public final CoroutineDispatcher getDispatcher() {
        return this.dispatcher;
    }

    public final boolean getPipelining() {
        return this.pipelining;
    }

    @NotNull
    public final HttpResponseConfig getResponse() {
        return this.response;
    }

    public final int getThreadsCount() {
        return this.threadsCount;
    }

    public final void setDispatcher(@Nullable CoroutineDispatcher coroutineDispatcher) {
        this.dispatcher = coroutineDispatcher;
    }

    public final void setPipelining(boolean z) {
        this.pipelining = z;
    }

    public final void setThreadsCount(int i) {
        this.threadsCount = i;
    }
}
