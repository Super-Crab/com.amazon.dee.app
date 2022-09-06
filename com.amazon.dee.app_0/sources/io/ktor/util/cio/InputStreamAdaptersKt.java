package io.ktor.util.cio;

import com.amazon.alexa.routing.api.RouteParameter;
import io.ktor.util.KtorExperimentalAPI;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.CoroutinesKt;
import kotlinx.coroutines.io.WriterScope;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: InputStreamAdapters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"toByteReadChannel", "Lkotlinx/coroutines/io/ByteReadChannel;", "Ljava/io/InputStream;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "context", "Lkotlin/coroutines/CoroutineContext;", RouteParameter.PARENT, "Lkotlinx/coroutines/Job;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class InputStreamAdaptersKt {
    @KtorExperimentalAPI
    @NotNull
    public static final ByteReadChannel toByteReadChannel(@NotNull InputStream receiver$0, @NotNull ObjectPool<ByteBuffer> pool, @NotNull CoroutineContext context, @NotNull Job parent) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        return CoroutinesKt.writer(CoroutineScopeKt.CoroutineScope(context), (CoroutineContext) parent, true, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new InputStreamAdaptersKt$toByteReadChannel$1(receiver$0, pool, null)).mo12310getChannel();
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ ByteReadChannel toByteReadChannel$default(InputStream inputStream, ObjectPool objectPool, CoroutineContext coroutineContext, Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            objectPool = ByteBufferPoolKt.getKtorDefaultPool();
        }
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        if ((i & 4) != 0) {
            job = JobKt__JobKt.m12240Job$default((Job) null, 1, (Object) null);
        }
        return toByteReadChannel(inputStream, objectPool, coroutineContext, job);
    }
}
