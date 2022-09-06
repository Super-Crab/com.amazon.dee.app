package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ApplicationResponseProperties.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0012\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"contentLength", "", "Lio/ktor/http/HeadersBuilder;", "length", "", "contentType", "Lio/ktor/http/ContentType;", "etag", "entityTag", "", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ApplicationResponsePropertiesKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Content-Length need to be passed in OutgoingContent.contentLength")
    public static final void contentLength(@NotNull HeadersBuilder receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        throw new NotImplementedError(GeneratedOutlineSupport1.outline72("An operation is not implemented: ", "Not supported anymore"));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Content-Type need to be passed in OutgoingContent.contentType")
    public static final void contentType(@NotNull HeadersBuilder receiver$0, @NotNull ContentType contentType) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        throw new NotImplementedError(GeneratedOutlineSupport1.outline72("An operation is not implemented: ", "Not supported anymore"));
    }

    public static final void etag(@NotNull HeadersBuilder receiver$0, @NotNull String entityTag) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(entityTag, "entityTag");
        receiver$0.set(HttpHeaders.INSTANCE.getETag(), entityTag);
    }
}
