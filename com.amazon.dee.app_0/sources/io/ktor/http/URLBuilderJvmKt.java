package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import io.ktor.http.Url;
import java.net.URI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: URLBuilderJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\u0002¨\u0006\u0005"}, d2 = {"invoke", "Lio/ktor/http/Url;", "Lio/ktor/http/Url$Companion;", "fullUrl", "", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class URLBuilderJvmKt {
    @NotNull
    public static final Url invoke(@NotNull Url.Companion receiver$0, @NotNull String fullUrl) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(fullUrl, "fullUrl");
        URLBuilder uRLBuilder = new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
        URLUtilsJvmKt.takeFrom(uRLBuilder, new URI(fullUrl));
        return uRLBuilder.build();
    }
}
