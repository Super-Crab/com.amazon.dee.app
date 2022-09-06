package io.ktor.client.engine;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.scheduling.ExperimentalCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpClientJvmEngine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/coroutines/CoroutineContext;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpClientJvmEngine$coroutineContext$2 extends Lambda implements Function0<CoroutineContext> {
    final /* synthetic */ String $engineName;
    final /* synthetic */ HttpClientJvmEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClientJvmEngine$coroutineContext$2(HttpClientJvmEngine httpClientJvmEngine, String str) {
        super(0);
        this.this$0 = httpClientJvmEngine;
        this.$engineName = str;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final CoroutineContext mo12560invoke() {
        CompletableDeferred completableDeferred;
        ExperimentalCoroutineDispatcher mo10270getDispatcher = this.this$0.mo10270getDispatcher();
        completableDeferred = this.this$0.clientContext;
        return mo10270getDispatcher.plus(completableDeferred).plus(new CoroutineName(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.$engineName, "-context")));
    }
}
