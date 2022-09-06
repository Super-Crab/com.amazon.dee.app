package kotlinx.coroutines.experimental.io;

import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.routing.api.RouteParameter;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.ByteChannelCtorKt;
import kotlinx.coroutines.io.ByteChannelKt;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.CoroutinesKt;
import kotlinx.coroutines.io.ReaderJob;
import kotlinx.coroutines.io.ReaderScope;
import kotlinx.coroutines.io.WriterJob;
import kotlinx.coroutines.io.WriterScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Migration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u0007\u001a\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\f\b\u0002\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0007\u001aW\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152'\u0010\u0016\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0017¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001aU\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152'\u0010\u0016\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0017¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001aW\u0010 \u001a\u00020!2\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152'\u0010\u0016\u001a#\b\u0001\u0012\u0004\u0012\u00020\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0017¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0000¢\u0006\u0002\u0010#\u001aU\u0010 \u001a\u00020!2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152'\u0010\u0016\u001a#\b\u0001\u0012\u0004\u0012\u00020\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0017¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0000¢\u0006\u0002\u0010$*&\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\u0018\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\n\b(\u0012\u0006\b\n0)8**\u001a\b\u0007\u0010\u0004\"\u00020\u00052\u00020\u0005B\f\b%\u0012\b\b&\u0012\u0004\b\b('*\u001a\b\u0007\u0010+\"\u00020,2\u00020,B\f\b%\u0012\b\b&\u0012\u0004\b\b('**\b\u0007\u0010-\"\u0002`.2\u00060/j\u0002`.B\u0018\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\n\b(\u0012\u0006\b\n0)8**&\b\u0007\u00100\"\u00020\u00112\u00020\u0011B\u0018\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\n\b(\u0012\u0006\b\n0)8**&\b\u0007\u00101\"\u00020\u00182\u00020\u0018B\u0018\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\n\b(\u0012\u0006\b\n0)8**&\b\u0007\u00102\"\u00020!2\u00020!B\u0018\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\n\b(\u0012\u0006\b\n0)8**&\b\u0007\u00103\"\u00020\"2\u00020\"B\u0018\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\n\b(\u0012\u0006\b\n0)8*\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"ByteChannel", "Lkotlinx/coroutines/io/ByteChannel;", "autoFlush", "", "ByteReadChannel", "Lkotlinx/coroutines/io/ByteReadChannel;", "content", "", "offset", "", "length", "text", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "reader", "Lkotlinx/coroutines/io/ReaderJob;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", RouteParameter.PARENT, "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/io/ReaderScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;ZLkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/ReaderJob;", "channel", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/io/ByteChannel;Lkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/ReaderJob;", "writer", "Lkotlinx/coroutines/io/WriterJob;", "Lkotlinx/coroutines/io/WriterScope;", "(Lkotlin/coroutines/CoroutineContext;ZLkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/WriterJob;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/io/ByteChannel;Lkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/io/WriterJob;", "Lkotlin/Deprecated;", "message", "Use the same type from different package", ModelTransformer.KEY_BATTERY_LEVEL, "Lkotlin/DeprecationLevel;", "ERROR", "ByteWriteChannel", "Lkotlinx/coroutines/io/ByteWriteChannel;", "CancellationException", "Lkotlinx/coroutines/CancellationException;", "Ljava/util/concurrent/CancellationException;", "ReaderJob", "ReaderScope", "WriterJob", "WriterScope", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class MigrationKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same function from different package", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.ByteChannel(false)", imports = {"kotlinx.coroutines.io.ByteChannel"}))
    @NotNull
    public static final ByteChannel ByteChannel(boolean z) {
        return ByteChannelKt.ByteChannel(z);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same type from different package")
    public static /* synthetic */ void ByteChannel$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same function from different package", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.ByteChannel(false)", imports = {"kotlinx.coroutines.io.ByteChannel"}))
    @NotNull
    public static /* synthetic */ ByteChannel ByteChannel$default(boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return ByteChannel(z);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same function from different package", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.ByteReadChannel(content, offset, length)", imports = {"kotlinx.coroutines.io.ByteReadChannel"}))
    @NotNull
    public static final ByteReadChannel ByteReadChannel(@NotNull byte[] content, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        return ByteChannelKt.ByteReadChannel(content, i, i2);
    }

    @Deprecated(message = "Use the same type from different package")
    public static /* synthetic */ void ByteReadChannel$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same function from different package", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.ByteReadChannel(content, offset, length)", imports = {"kotlinx.coroutines.io.ByteReadChannel"}))
    @NotNull
    public static /* synthetic */ ByteReadChannel ByteReadChannel$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return ByteReadChannel(bArr, i, i2);
    }

    @Deprecated(message = "Use the same type from different package")
    public static /* synthetic */ void ByteWriteChannel$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same type from different package")
    public static /* synthetic */ void CancellationException$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same type from different package")
    public static /* synthetic */ void ReaderJob$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same type from different package")
    public static /* synthetic */ void ReaderScope$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same type from different package")
    public static /* synthetic */ void WriterJob$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same type from different package")
    public static /* synthetic */ void WriterScope$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.reader instead", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.reader(coroutineContext, channel, parent, block)", imports = {"kotlinx.coroutines.io.reader"}))
    @NotNull
    public static final ReaderJob reader(@NotNull CoroutineContext coroutineContext, @NotNull ByteChannel channel, @Nullable Job job, @NotNull Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return CoroutinesKt.reader(coroutineContext, channel, job, block);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.reader instead", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.reader(coroutineContext, channel, parent, block)", imports = {"kotlinx.coroutines.io.reader"}))
    @NotNull
    public static /* synthetic */ ReaderJob reader$default(CoroutineContext coroutineContext, ByteChannel byteChannel, Job job, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            job = null;
        }
        return reader(coroutineContext, byteChannel, job, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.writer instead", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.writer(coroutineContext, channel, parent, block)", imports = {"kotlinx.coroutines.io.writer"}))
    @NotNull
    public static final WriterJob writer(@NotNull CoroutineContext coroutineContext, @NotNull ByteChannel channel, @Nullable Job job, @NotNull Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return CoroutinesKt.writer(coroutineContext, channel, job, block);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.writer instead", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.writer(coroutineContext, channel, parent, block)", imports = {"kotlinx.coroutines.io.writer"}))
    @NotNull
    public static /* synthetic */ WriterJob writer$default(CoroutineContext coroutineContext, ByteChannel byteChannel, Job job, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            job = null;
        }
        return writer(coroutineContext, byteChannel, job, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same function from different package", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.ByteReadChannel(text, charset)", imports = {"kotlinx.coroutines.io.ByteReadChannel"}))
    @NotNull
    public static final ByteReadChannel ByteReadChannel(@NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return ByteChannelCtorKt.ByteReadChannel(text, charset);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use the same function from different package", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.ByteReadChannel(text, charset)", imports = {"kotlinx.coroutines.io.ByteReadChannel"}))
    @NotNull
    public static /* synthetic */ ByteReadChannel ByteReadChannel$default(String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return ByteReadChannel(str, charset);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.reader instead", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.reader(coroutineContext, autoFlush, parent, block)", imports = {"kotlinx.coroutines.io.reader"}))
    @NotNull
    public static final ReaderJob reader(@NotNull CoroutineContext coroutineContext, boolean z, @Nullable Job job, @NotNull Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return CoroutinesKt.reader(coroutineContext, z, job, block);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.reader instead", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.reader(coroutineContext, autoFlush, parent, block)", imports = {"kotlinx.coroutines.io.reader"}))
    @NotNull
    public static /* synthetic */ ReaderJob reader$default(CoroutineContext coroutineContext, boolean z, Job job, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            job = null;
        }
        return reader(coroutineContext, z, job, function2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.writer instead", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.writer(coroutineContext, autoFlush, parent, block)", imports = {"kotlinx.coroutines.io.writer"}))
    @NotNull
    public static final WriterJob writer(@NotNull CoroutineContext coroutineContext, boolean z, @Nullable Job job, @NotNull Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "coroutineContext");
        Intrinsics.checkParameterIsNotNull(block, "block");
        return CoroutinesKt.writer(coroutineContext, z, job, block);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use scope.writer instead", replaceWith = @ReplaceWith(expression = "kotlinx.coroutines.io.writer(coroutineContext, autoFlush, parent, block)", imports = {"kotlinx.coroutines.io.writer"}))
    @NotNull
    public static /* synthetic */ WriterJob writer$default(CoroutineContext coroutineContext, boolean z, Job job, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            job = null;
        }
        return writer(coroutineContext, z, job, function2);
    }
}
