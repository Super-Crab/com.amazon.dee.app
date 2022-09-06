package kotlinx.coroutines.io;

import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.io.charsets.CharsetJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteChannelCtor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\b\u0002\u0010\t\u001a\u00060\nj\u0002`\u000b\"\u001a\u0010\u0000\u001a\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"EmptyByteReadChannel", "Lkotlinx/coroutines/io/ByteReadChannel;", "EmptyByteReadChannel$annotations", "()V", "getEmptyByteReadChannel", "()Lkotlinx/coroutines/io/ByteReadChannel;", "ByteReadChannel", "text", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteChannelCtorKt {
    @NotNull
    public static final ByteReadChannel ByteReadChannel(@NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkExpressionValueIsNotNull(newEncoder, "charset.newEncoder()");
        return ByteChannelKt.ByteReadChannel$default(CharsetJVMKt.encodeToByteArray(newEncoder, text, 0, text.length()), 0, 0, 6, null);
    }

    @NotNull
    public static /* synthetic */ ByteReadChannel ByteReadChannel$default(String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return ByteReadChannel(str, charset);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ByteReadChannel.Empty instead", replaceWith = @ReplaceWith(expression = "ByteReadChannel.Empty", imports = {}))
    public static /* synthetic */ void EmptyByteReadChannel$annotations() {
    }

    @NotNull
    public static final ByteReadChannel getEmptyByteReadChannel() {
        return ByteReadChannel.Companion.getEmpty();
    }
}
