package io.ktor.client.features;

import io.ktor.client.HttpClient;
import io.ktor.client.response.HttpResponsePipeline;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultTransformersJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"platformDefaultTransformers", "", "Lio/ktor/client/HttpClient;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DefaultTransformersJvmKt {
    public static final void platformDefaultTransformers(@NotNull HttpClient receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getParse(), new DefaultTransformersJvmKt$platformDefaultTransformers$1(null));
    }
}
