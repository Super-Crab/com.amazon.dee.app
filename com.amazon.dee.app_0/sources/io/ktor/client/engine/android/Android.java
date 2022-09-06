package io.ktor.client.engine.android;

import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Android.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tH\u0016¨\u0006\n"}, d2 = {"Lio/ktor/client/engine/android/Android;", "Lio/ktor/client/engine/HttpClientEngineFactory;", "Lio/ktor/client/engine/android/AndroidEngineConfig;", "()V", "create", "Lio/ktor/client/engine/HttpClientEngine;", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-client-android"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class Android implements HttpClientEngineFactory<AndroidEngineConfig> {
    public static final Android INSTANCE = new Android();

    private Android() {
    }

    @Override // io.ktor.client.engine.HttpClientEngineFactory
    @NotNull
    public HttpClientEngine create(@NotNull Function1<? super AndroidEngineConfig, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        AndroidEngineConfig androidEngineConfig = new AndroidEngineConfig();
        block.mo12165invoke(androidEngineConfig);
        return new AndroidClientEngine(androidEngineConfig);
    }
}
