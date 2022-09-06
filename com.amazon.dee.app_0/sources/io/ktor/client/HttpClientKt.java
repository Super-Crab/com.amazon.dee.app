package io.ktor.client;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.engine.HttpClientEngineFactory;
import io.ktor.client.request.HttpRequestData;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.UnsafeHeaderException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0007\u001aA\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\u001f\b\u0002\u0010\u0004\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0007\u001a\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"HttpClient", "Lio/ktor/client/HttpClient;", "engine", "Lio/ktor/client/engine/HttpClientEngine;", "block", "Lkotlin/Function1;", "Lio/ktor/client/HttpClientConfig;", "", "Lkotlin/ExtensionFunctionType;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/client/engine/HttpClientEngineConfig;", "engineFactory", "Lio/ktor/client/engine/HttpClientEngineFactory;", "validateHeaders", "request", "Lio/ktor/client/request/HttpRequestData;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpClientKt {
    @HttpClientDsl
    @NotNull
    public static final <T extends HttpClientEngineConfig> HttpClient HttpClient(@NotNull HttpClientEngineFactory<? extends T> engineFactory, @NotNull Function1<? super HttpClientConfig<T>, Unit> block) {
        Intrinsics.checkParameterIsNotNull(engineFactory, "engineFactory");
        Intrinsics.checkParameterIsNotNull(block, "block");
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        block.mo12165invoke(httpClientConfig);
        return new HttpClient(engineFactory.create(httpClientConfig.getEngineConfig$ktor_client_core()), httpClientConfig);
    }

    @HttpClientDsl
    @NotNull
    public static /* synthetic */ HttpClient HttpClient$default(HttpClientEngineFactory httpClientEngineFactory, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = HttpClientKt$HttpClient$2.INSTANCE;
        }
        return HttpClient(httpClientEngineFactory, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateHeaders(HttpRequestData httpRequestData) {
        String[] unsafeHeaders;
        Headers headers = httpRequestData.getHeaders();
        for (String str : HttpHeaders.INSTANCE.getUnsafeHeaders()) {
            if (headers.contains(str)) {
                throw new UnsafeHeaderException(str);
            }
        }
    }

    @HttpClientDsl
    @NotNull
    public static final HttpClient HttpClient(@NotNull HttpClientEngine engine, @NotNull Function1<? super HttpClientConfig<?>, Unit> block) {
        Intrinsics.checkParameterIsNotNull(engine, "engine");
        Intrinsics.checkParameterIsNotNull(block, "block");
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        block.mo12165invoke(httpClientConfig);
        return new HttpClient(engine, httpClientConfig);
    }
}
