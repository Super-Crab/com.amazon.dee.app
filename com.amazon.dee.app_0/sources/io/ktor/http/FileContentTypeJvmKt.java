package io.ktor.http;

import io.ktor.http.ContentType;
import io.ktor.util.NioPathKt;
import java.io.File;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileContentTypeJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"defaultForFile", "Lio/ktor/http/ContentType;", "Lio/ktor/http/ContentType$Companion;", "file", "Ljava/io/File;", "Ljava/nio/file/Path;", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class FileContentTypeJvmKt {
    @NotNull
    public static final ContentType defaultForFile(@NotNull ContentType.Companion receiver$0, @NotNull File file) {
        String extension;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file, "file");
        ContentType.Companion companion = ContentType.Companion;
        extension = FilesKt__UtilsKt.getExtension(file);
        return FileContentTypeKt.selectDefault(FileContentTypeKt.fromFileExtension(companion, extension));
    }

    @NotNull
    public static final ContentType defaultForFile(@NotNull ContentType.Companion receiver$0, @NotNull Path file) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(file, "file");
        return FileContentTypeKt.selectDefault(FileContentTypeKt.fromFileExtension(ContentType.Companion, NioPathKt.getExtension(file)));
    }
}
