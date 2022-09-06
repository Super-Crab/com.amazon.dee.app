package io.ktor.client.response;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpResponseConfig.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/client/response/HttpResponseConfig;", "", "()V", "defaultCharset", "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "getDefaultCharset", "()Ljava/nio/charset/Charset;", "setDefaultCharset", "(Ljava/nio/charset/Charset;)V", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public class HttpResponseConfig {
    @NotNull
    private Charset defaultCharset;

    public HttpResponseConfig() {
        Charset charset;
        try {
            charset = Charset.forName("ISO_8859_1");
            Intrinsics.checkExpressionValueIsNotNull(charset, "Charset.forName(\"ISO_8859_1\")");
        } catch (Throwable unused) {
            charset = Charsets.UTF_8;
        }
        this.defaultCharset = charset;
    }

    @NotNull
    public final Charset getDefaultCharset() {
        return this.defaultCharset;
    }

    public final void setDefaultCharset(@NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "<set-?>");
        this.defaultCharset = charset;
    }
}
