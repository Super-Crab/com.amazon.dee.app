package io.ktor.http;

import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.charsets.CharsetJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ContentTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002*\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\n\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002¨\u0006\u0006"}, d2 = {HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "Lio/ktor/http/HeaderValueWithParameters;", "withCharset", "Lio/ktor/http/ContentType;", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ContentTypesKt {
    @Nullable
    public static final Charset charset(@NotNull HeaderValueWithParameters receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String parameter = receiver$0.parameter(HttpAuthHeader.Parameters.Charset);
        if (parameter != null) {
            return Charset.forName(parameter);
        }
        return null;
    }

    @NotNull
    public static final ContentType withCharset(@NotNull ContentType receiver$0, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return receiver$0.withParameter(HttpAuthHeader.Parameters.Charset, CharsetJVMKt.getName(charset));
    }
}
