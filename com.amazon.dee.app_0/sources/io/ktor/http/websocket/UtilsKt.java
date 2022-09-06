package io.ktor.http.websocket;

import io.ktor.util.Base64JvmKt;
import io.ktor.util.CryptoKt;
import io.ktor.util.KtorExperimentalAPI;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Utils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"WEBSOCKET_SERVER_ACCEPT_TAIL", "", "websocketServerAccept", "nonce", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class UtilsKt {
    private static final String WEBSOCKET_SERVER_ACCEPT_TAIL = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

    @KtorExperimentalAPI
    @NotNull
    public static final String websocketServerAccept(@NotNull String nonce) {
        CharSequence trim;
        Intrinsics.checkParameterIsNotNull(nonce, "nonce");
        StringBuilder sb = new StringBuilder();
        trim = StringsKt__StringsKt.trim((CharSequence) nonce);
        sb.append(trim.toString());
        sb.append("258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
        String sb2 = sb.toString();
        Charset charset = Charsets.ISO_8859_1;
        if (sb2 != null) {
            byte[] bytes = sb2.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            return Base64JvmKt.encodeBase64(CryptoKt.sha1(bytes));
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
