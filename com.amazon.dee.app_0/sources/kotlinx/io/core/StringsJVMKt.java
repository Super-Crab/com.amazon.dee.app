package kotlinx.io.core;

import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* compiled from: StringsJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\f\b\u0002\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0086\b\u001a\u001c\u0010\n\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0000¨\u0006\u000f"}, d2 = {"String", "", "bytes", "", "offset", "", "length", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "getCharsInternal", "", "dst", "", "dstOffset", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StringsJVMKt {
    @NotNull
    public static final String String(@NotNull byte[] bytes, int i, int i2, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return new String(bytes, i, i2, charset);
    }

    @NotNull
    public static /* synthetic */ String String$default(byte[] bytes, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bytes.length;
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return new String(bytes, i, i2, charset);
    }

    public static final void getCharsInternal(@NotNull String receiver$0, @NotNull char[] dst, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.getChars(0, receiver$0.length(), dst, i);
    }
}
