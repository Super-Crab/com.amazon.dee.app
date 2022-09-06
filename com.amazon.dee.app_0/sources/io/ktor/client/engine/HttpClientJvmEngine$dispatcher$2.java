package io.ktor.client.engine;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.scheduling.ExperimentalCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClientJvmEngine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class HttpClientJvmEngine$dispatcher$2 extends Lambda implements Function0<ExperimentalCoroutineDispatcher> {
    final /* synthetic */ HttpClientJvmEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClientJvmEngine$dispatcher$2(HttpClientJvmEngine httpClientJvmEngine) {
        super(0);
        this.this$0 = httpClientJvmEngine;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final ExperimentalCoroutineDispatcher mo12560invoke() {
        return new ExperimentalCoroutineDispatcher(this.this$0.mo10273getConfig().getThreadsCount(), 0, null, 6, null);
    }
}
