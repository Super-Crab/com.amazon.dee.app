package com.amazon.tarazed.core.utility;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BrowserPresenceDetector.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J \u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lcom/amazon/tarazed/core/utility/BrowserPresenceDetector;", "", "()V", "isBrowserPresent", "", "timeout", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setPongTimer", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;", "deferred", "Lkotlinx/coroutines/CompletableDeferred;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class BrowserPresenceDetector {
    @Nullable
    public abstract Object isBrowserPresent(long j, @NotNull Continuation<? super Boolean> continuation);

    @NotNull
    public final Job setPongTimer(@NotNull CoroutineScope setPongTimer, long j, @NotNull CompletableDeferred<Boolean> deferred) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(setPongTimer, "$this$setPongTimer");
        Intrinsics.checkParameterIsNotNull(deferred, "deferred");
        launch$default = BuildersKt__Builders_commonKt.launch$default(setPongTimer, null, null, new BrowserPresenceDetector$setPongTimer$1(j, deferred, null), 3, null);
        return launch$default;
    }
}
