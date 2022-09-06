package io.ktor.client;

import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.features.ExpectSuccess;
import io.ktor.client.features.HttpPlainText;
import io.ktor.client.features.HttpRedirect;
import io.ktor.client.features.HttpSend;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.client.response.HttpReceivePipeline;
import io.ktor.client.response.HttpResponsePipeline;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import java.io.Closeable;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\b\u0010.\u001a\u00020/H\u0016J#\u0010\u0010\u001a\u00020\u00002\u001b\u00100\u001a\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020/01¢\u0006\u0002\b2J\u0019\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0086@ø\u0001\u0000¢\u0006\u0002\u00107R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u00168FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0016\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00068"}, d2 = {"Lio/ktor/client/HttpClient;", "Lkotlinx/coroutines/CoroutineScope;", "Ljava/io/Closeable;", "Lkotlinx/io/core/Closeable;", "engine", "Lio/ktor/client/engine/HttpClientEngine;", "userConfig", "Lio/ktor/client/HttpClientConfig;", "Lio/ktor/client/engine/HttpClientEngineConfig;", "(Lio/ktor/client/engine/HttpClientEngine;Lio/ktor/client/HttpClientConfig;)V", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "closed", "Lkotlinx/atomicfu/AtomicBoolean;", "config", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher$annotations", "()V", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "engineConfig", "getEngineConfig", "()Lio/ktor/client/engine/HttpClientEngineConfig;", "receivePipeline", "Lio/ktor/client/response/HttpReceivePipeline;", "getReceivePipeline", "()Lio/ktor/client/response/HttpReceivePipeline;", "requestPipeline", "Lio/ktor/client/request/HttpRequestPipeline;", "getRequestPipeline", "()Lio/ktor/client/request/HttpRequestPipeline;", "responsePipeline", "Lio/ktor/client/response/HttpResponsePipeline;", "getResponsePipeline", "()Lio/ktor/client/response/HttpResponsePipeline;", "sendPipeline", "Lio/ktor/client/request/HttpSendPipeline;", "getSendPipeline", "()Lio/ktor/client/request/HttpSendPipeline;", "close", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "execute", "Lio/ktor/client/call/HttpClientCall;", "builder", "Lio/ktor/client/request/HttpRequestBuilder;", "(Lio/ktor/client/request/HttpRequestBuilder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpClient implements CoroutineScope, Closeable {
    private static final AtomicIntegerFieldUpdater closed$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClient.class, "closed");
    @NotNull
    private final Attributes attributes;
    private volatile int closed;
    private final HttpClientConfig<HttpClientEngineConfig> config;
    private final HttpClientEngine engine;
    @NotNull
    private final HttpClientEngineConfig engineConfig;
    @NotNull
    private final HttpReceivePipeline receivePipeline;
    @NotNull
    private final HttpRequestPipeline requestPipeline;
    @NotNull
    private final HttpResponsePipeline responsePipeline;
    @NotNull
    private final HttpSendPipeline sendPipeline;
    private final HttpClientConfig<? extends HttpClientEngineConfig> userConfig;

    public HttpClient(@NotNull HttpClientEngine engine, @NotNull HttpClientConfig<? extends HttpClientEngineConfig> userConfig) {
        Intrinsics.checkParameterIsNotNull(engine, "engine");
        Intrinsics.checkParameterIsNotNull(userConfig, "userConfig");
        this.engine = engine;
        this.userConfig = userConfig;
        this.closed = 0;
        this.requestPipeline = new HttpRequestPipeline();
        this.responsePipeline = new HttpResponsePipeline();
        HttpSendPipeline httpSendPipeline = new HttpSendPipeline();
        httpSendPipeline.intercept(HttpSendPipeline.Phases.getEngine(), new HttpClient$$special$$inlined$apply$lambda$1(null, this));
        this.sendPipeline = httpSendPipeline;
        this.receivePipeline = new HttpReceivePipeline();
        this.attributes = AttributesJvmKt.Attributes$default(false, 1, null);
        this.engineConfig = this.engine.mo10273getConfig();
        this.config = new HttpClientConfig<>();
        HttpClientConfig<? extends HttpClientEngineConfig> httpClientConfig = this.userConfig;
        if (httpClientConfig.getUseDefaultTransformers()) {
            HttpClientConfig.install$default(this.config, HttpPlainText.Feature, null, 2, null);
            this.config.install("DefaultTransformers", HttpClient$1$1.INSTANCE);
        }
        if (httpClientConfig.getExpectSuccess()) {
            HttpClientConfig.install$default(this.config, ExpectSuccess.Companion, null, 2, null);
        }
        HttpClientConfig.install$default(this.config, HttpSend.Feature, null, 2, null);
        if (httpClientConfig.getFollowRedirects()) {
            HttpClientConfig.install$default(this.config, HttpRedirect.Feature, null, 2, null);
        }
        this.config.plusAssign(httpClientConfig);
        this.config.install(this);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "[dispatcher] is deprecated. Use coroutineContext instead.", replaceWith = @ReplaceWith(expression = "coroutineContext", imports = {}))
    public static /* synthetic */ void dispatcher$annotations() {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!closed$FU.compareAndSet(this, 0, 1)) {
            return;
        }
        Iterator<T> it2 = this.attributes.getAllKeys().iterator();
        while (it2.hasNext()) {
            AttributeKey attributeKey = (AttributeKey) it2.next();
            Attributes attributes = this.attributes;
            if (attributeKey != null) {
                Object obj = attributes.get(attributeKey);
                if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type io.ktor.util.AttributeKey<kotlin.Any>");
            }
        }
        this.engine.close();
    }

    @NotNull
    public final HttpClient config(@NotNull Function1<? super HttpClientConfig<?>, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        HttpClientEngine httpClientEngine = this.engine;
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        httpClientConfig.plusAssign(this.userConfig);
        block.mo12165invoke(httpClientConfig);
        return new HttpClient(httpClientEngine, httpClientConfig);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object execute(@org.jetbrains.annotations.NotNull io.ktor.client.request.HttpRequestBuilder r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.ktor.client.call.HttpClientCall> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.HttpClient$execute$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.HttpClient$execute$1 r0 = (io.ktor.client.HttpClient$execute$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.HttpClient$execute$1 r0 = new io.ktor.client.HttpClient$execute$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r5 = r0.L$1
            io.ktor.client.request.HttpRequestBuilder r5 = (io.ktor.client.request.HttpRequestBuilder) r5
            java.lang.Object r5 = r0.L$0
            io.ktor.client.HttpClient r5 = (io.ktor.client.HttpClient) r5
            boolean r5 = r6 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L32
            goto L56
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
            if (r2 != 0) goto L63
            io.ktor.client.request.HttpRequestPipeline r6 = r4.requestPipeline
            java.lang.Object r2 = r5.getBody()
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.execute(r5, r2, r0)
            if (r6 != r1) goto L56
            return r1
        L56:
            if (r6 == 0) goto L5b
            io.ktor.client.call.HttpClientCall r6 = (io.ktor.client.call.HttpClientCall) r6
            return r6
        L5b:
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type io.ktor.client.call.HttpClientCall"
            r5.<init>(r6)
            throw r5
        L63:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.HttpClient.execute(io.ktor.client.request.HttpRequestBuilder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final Attributes getAttributes() {
        return this.attributes;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.engine.getCoroutineContext();
    }

    @NotNull
    public final CoroutineDispatcher getDispatcher() {
        return this.engine.mo10270getDispatcher();
    }

    @NotNull
    public final HttpClientEngineConfig getEngineConfig() {
        return this.engineConfig;
    }

    @NotNull
    public final HttpReceivePipeline getReceivePipeline() {
        return this.receivePipeline;
    }

    @NotNull
    public final HttpRequestPipeline getRequestPipeline() {
        return this.requestPipeline;
    }

    @NotNull
    public final HttpResponsePipeline getResponsePipeline() {
        return this.responsePipeline;
    }

    @NotNull
    public final HttpSendPipeline getSendPipeline() {
        return this.sendPipeline;
    }

    public /* synthetic */ HttpClient(HttpClientEngine httpClientEngine, HttpClientConfig httpClientConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(httpClientEngine, (i & 2) != 0 ? new HttpClientConfig() : httpClientConfig);
    }
}
