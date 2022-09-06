package io.ktor.client.engine;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.scheduling.ExperimentalCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClientJvmEngine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0018\u001a\u00020\tH\u0016J\b\u0010\u0019\u001a\u00020\u000bH\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\n\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR!\u0010\u0012\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0011\u0012\u0004\b\u0014\u0010\r\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lio/ktor/client/engine/HttpClientJvmEngine;", "Lio/ktor/client/engine/HttpClientEngine;", "engineName", "", "(Ljava/lang/String;)V", "callSupervisor", "Lkotlinx/coroutines/Job;", "clientContext", "Lkotlinx/coroutines/CompletableDeferred;", "", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext$delegate", "Lkotlin/Lazy;", "dispatcher", "Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "dispatcher$annotations", "getDispatcher", "()Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "dispatcher$delegate", "close", "createCallContext", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class HttpClientJvmEngine implements HttpClientEngine {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(HttpClientJvmEngine.class), "dispatcher", "getDispatcher()Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(HttpClientJvmEngine.class), "coroutineContext", "getCoroutineContext()Lkotlin/coroutines/CoroutineContext;"))};
    private final Job callSupervisor;
    private final CompletableDeferred<Unit> clientContext;
    @NotNull
    private final Lazy coroutineContext$delegate;
    @NotNull
    private final Lazy dispatcher$delegate;

    public HttpClientJvmEngine(@NotNull String engineName) {
        Lazy lazy;
        Lazy lazy2;
        Intrinsics.checkParameterIsNotNull(engineName, "engineName");
        this.clientContext = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.callSupervisor = SupervisorKt.SupervisorJob((Job) this.clientContext);
        lazy = LazyKt__LazyJVMKt.lazy(new HttpClientJvmEngine$dispatcher$2(this));
        this.dispatcher$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new HttpClientJvmEngine$coroutineContext$2(this, engineName));
        this.coroutineContext$delegate = lazy2;
    }

    public static /* synthetic */ void coroutineContext$annotations() {
    }

    public static /* synthetic */ void dispatcher$annotations() {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.callSupervisor.mo12309cancel();
        this.callSupervisor.invokeOnCompletion(new HttpClientJvmEngine$close$1(this));
        this.clientContext.invokeOnCompletion(new HttpClientJvmEngine$close$2(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final CoroutineContext createCallContext() {
        return getCoroutineContext().plus(CompletableDeferredKt.CompletableDeferred(this.callSupervisor));
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        Lazy lazy = this.coroutineContext$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (CoroutineContext) lazy.getValue();
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    @NotNull
    /* renamed from: getDispatcher  reason: collision with other method in class */
    public ExperimentalCoroutineDispatcher mo10270getDispatcher() {
        Lazy lazy = this.dispatcher$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ExperimentalCoroutineDispatcher) lazy.getValue();
    }
}
