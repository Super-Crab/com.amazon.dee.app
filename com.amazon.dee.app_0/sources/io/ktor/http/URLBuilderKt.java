package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: URLBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"DEFAULT_PORT", "", "clone", "Lio/ktor/http/URLBuilder;", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class URLBuilderKt {
    public static final int DEFAULT_PORT = 0;

    @NotNull
    public static final URLBuilder clone(@NotNull URLBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return URLUtilsKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null), receiver$0);
    }
}
