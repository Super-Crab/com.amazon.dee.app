package io.ktor.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeJvmKt;
import io.ktor.http.FileContentTypeKt;
import io.ktor.util.NioPathKt;
import io.ktor.util.PathKt;
import java.io.File;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LocalFileContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\b2\b\b\u0002\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"LocalFileContent", "Lio/ktor/http/content/LocalFileContent;", "baseDir", "Ljava/io/File;", "relativePath", "", "contentType", "Lio/ktor/http/ContentType;", "Ljava/nio/file/Path;", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class LocalFileContentKt {
    @NotNull
    public static final LocalFileContent LocalFileContent(@NotNull File baseDir, @NotNull String relativePath, @NotNull ContentType contentType) {
        Intrinsics.checkParameterIsNotNull(baseDir, "baseDir");
        Intrinsics.checkParameterIsNotNull(relativePath, "relativePath");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        return new LocalFileContent(PathKt.combineSafe(baseDir, relativePath), contentType);
    }

    @NotNull
    public static /* synthetic */ LocalFileContent LocalFileContent$default(File file, String str, ContentType contentType, int i, Object obj) {
        if ((i & 4) != 0) {
            contentType = FileContentTypeKt.defaultForFilePath(ContentType.Companion, str);
        }
        return LocalFileContent(file, str, contentType);
    }

    @NotNull
    public static final LocalFileContent LocalFileContent(@NotNull Path baseDir, @NotNull Path relativePath, @NotNull ContentType contentType) {
        Intrinsics.checkParameterIsNotNull(baseDir, "baseDir");
        Intrinsics.checkParameterIsNotNull(relativePath, "relativePath");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        return new LocalFileContent(NioPathKt.combineSafe(baseDir, relativePath), contentType);
    }

    @NotNull
    public static /* synthetic */ LocalFileContent LocalFileContent$default(Path path, Path path2, ContentType contentType, int i, Object obj) {
        if ((i & 4) != 0) {
            contentType = FileContentTypeJvmKt.defaultForFile(ContentType.Companion, path2);
        }
        return LocalFileContent(path, path2, contentType);
    }
}
