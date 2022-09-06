package io.ktor.http;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ApplicationResponsePropertiesJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0000\u001a\u00020\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"expires", "", "Lio/ktor/http/HeadersBuilder;", "Ljava/time/LocalDateTime;", "lastModified", "dateTime", "Ljava/time/ZonedDateTime;", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ApplicationResponsePropertiesJvmKt {
    public static final void expires(@NotNull HeadersBuilder receiver$0, @NotNull LocalDateTime expires) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(expires, "expires");
        receiver$0.set(HttpHeaders.INSTANCE.getExpires(), HttpDateKt.toHttpDateString(expires));
    }

    public static final void lastModified(@NotNull HeadersBuilder receiver$0, @NotNull ZonedDateTime dateTime) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dateTime, "dateTime");
        receiver$0.set(HttpHeaders.INSTANCE.getLastModified(), HttpDateKt.toHttpDateString(dateTime));
    }
}
