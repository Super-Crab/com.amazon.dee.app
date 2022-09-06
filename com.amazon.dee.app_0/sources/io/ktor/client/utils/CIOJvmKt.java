package io.ktor.client.utils;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: CIOJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001a\u0010\u0000\u001a\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\n\"\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"HTTP_CLIENT_DEFAULT_DISPATCHER", "Lkotlinx/coroutines/CoroutineDispatcher;", "HTTP_CLIENT_DEFAULT_DISPATCHER$annotations", "()V", "getHTTP_CLIENT_DEFAULT_DISPATCHER", "()Lkotlinx/coroutines/CoroutineDispatcher;", "HTTP_CLIENT_THREAD_COUNT", "", "HTTP_CLIENT_THREAD_COUNT$annotations", "getHTTP_CLIENT_THREAD_COUNT", "()I", "HttpClientDefaultPool", "Lio/ktor/client/utils/ByteBufferPool;", "getHttpClientDefaultPool", "()Lio/ktor/client/utils/ByteBufferPool;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CIOJvmKt {
    private static final int HTTP_CLIENT_THREAD_COUNT = 2;
    @NotNull
    private static final ByteBufferPool HttpClientDefaultPool = new ByteBufferPool();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static /* synthetic */ void HTTP_CLIENT_DEFAULT_DISPATCHER$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static /* synthetic */ void HTTP_CLIENT_THREAD_COUNT$annotations() {
    }

    @NotNull
    public static final ByteBufferPool getHttpClientDefaultPool() {
        return HttpClientDefaultPool;
    }
}
