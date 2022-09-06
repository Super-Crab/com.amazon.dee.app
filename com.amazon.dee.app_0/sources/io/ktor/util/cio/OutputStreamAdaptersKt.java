package io.ktor.util.cio;

import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.util.KtorExperimentalAPI;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.ByteWriteChannelKt;
import kotlinx.coroutines.io.jvm.javaio.BlockingKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: OutputStreamAdapters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0007\u001a'\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u0016\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"bufferedWriter", "Ljava/io/BufferedWriter;", "Lkotlinx/coroutines/io/ByteWriteChannel;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "write", "", "string", "", "(Lkotlinx/coroutines/io/ByteWriteChannel;Ljava/lang/String;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writer", "Ljava/io/Writer;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class OutputStreamAdaptersKt {
    @KtorExperimentalAPI
    @NotNull
    public static final BufferedWriter bufferedWriter(@NotNull ByteWriteChannel receiver$0, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(BlockingKt.toOutputStream$default(receiver$0, null, 1, null), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ BufferedWriter bufferedWriter$default(ByteWriteChannel byteWriteChannel, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return bufferedWriter(byteWriteChannel, charset);
    }

    @KtorExperimentalAPI
    @Nullable
    public static final Object write(@NotNull ByteWriteChannel byteWriteChannel, @NotNull String str, @NotNull Charset charset, @NotNull Continuation<? super Unit> continuation) {
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            return ByteWriteChannelKt.writeFully(byteWriteChannel, bytes, continuation);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @KtorExperimentalAPI
    @Nullable
    public static /* synthetic */ Object write$default(ByteWriteChannel byteWriteChannel, String str, Charset charset, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return write(byteWriteChannel, str, charset, continuation);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final Writer writer(@NotNull ByteWriteChannel receiver$0, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return new OutputStreamWriter(BlockingKt.toOutputStream$default(receiver$0, null, 1, null), charset);
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ Writer writer$default(ByteWriteChannel byteWriteChannel, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return writer(byteWriteChannel, charset);
    }
}
