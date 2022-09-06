package io.ktor.util.cio;

import io.ktor.util.KtorExperimentalAPI;
import java.io.File;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileChannelsAtNioPath.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¨\u0006\u0006"}, d2 = {"readChannel", "Lkotlinx/coroutines/io/ByteReadChannel;", "Ljava/nio/file/Path;", "start", "", "endInclusive", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class FileChannelsAtNioPathKt {
    @KtorExperimentalAPI
    @NotNull
    public static final ByteReadChannel readChannel(@NotNull Path receiver$0, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        File file = receiver$0.toFile();
        Intrinsics.checkExpressionValueIsNotNull(file, "toFile()");
        return FileChannelsKt.readChannel$default(file, j, j2, null, 4, null);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final ByteReadChannel readChannel(@NotNull Path receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        File file = receiver$0.toFile();
        Intrinsics.checkExpressionValueIsNotNull(file, "toFile()");
        return FileChannelsKt.readChannel$default(file, 0L, 0L, null, 7, null);
    }
}
