package io.ktor.util;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: NioPath.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0007\u001a\f\u0010\b\u001a\u00020\u0002*\u00020\u0002H\u0002\u001a\f\u0010\t\u001a\u00020\u0002*\u00020\u0002H\u0007\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\n"}, d2 = {"extension", "", "Ljava/nio/file/Path;", "getExtension", "(Ljava/nio/file/Path;)Ljava/lang/String;", "combineSafe", "Ljava/io/File;", "relativePath", "dropLeadingTopDirs", "normalizeAndRelativize", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class NioPathKt {
    @KtorExperimentalAPI
    @NotNull
    public static final File combineSafe(@NotNull Path receiver$0, @NotNull Path relativePath) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(relativePath, "relativePath");
        Path normalizeAndRelativize = normalizeAndRelativize(relativePath);
        if (!normalizeAndRelativize.startsWith("..")) {
            if (!normalizeAndRelativize.isAbsolute()) {
                File file = receiver$0.resolve(normalizeAndRelativize).toFile();
                Intrinsics.checkExpressionValueIsNotNull(file, "resolve(normalized).toFile()");
                return file;
            }
            throw new IllegalStateException(("Bad relative path " + relativePath).toString());
        }
        String obj = relativePath.toString();
        throw new InvalidPathException(obj, "Bad relative path " + relativePath);
    }

    private static final Path dropLeadingTopDirs(@NotNull Path path) {
        Iterator it2 = path.iterator();
        int i = 0;
        while (true) {
            if (!it2.hasNext()) {
                i = -1;
                break;
            }
            Object next = it2.next();
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            if (!Intrinsics.areEqual(((Path) next).toString(), "..")) {
                break;
            }
            i++;
        }
        if (i == 0) {
            return path;
        }
        Path subpath = path.subpath(i, path.getNameCount());
        Intrinsics.checkExpressionValueIsNotNull(subpath, "subpath(startIndex, nameCount)");
        return subpath;
    }

    @NotNull
    public static final String getExtension(@NotNull Path receiver$0) {
        String substringAfterLast$default;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        substringAfterLast$default = StringsKt__StringsKt.substringAfterLast$default(receiver$0.getFileName().toString(), ".", (String) null, 2, (Object) null);
        return substringAfterLast$default;
    }

    @KtorExperimentalAPI
    @NotNull
    public static final Path normalizeAndRelativize(@NotNull Path receiver$0) {
        Path relativize;
        Path normalize;
        Path dropLeadingTopDirs;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Path root = receiver$0.getRoot();
        if (root == null || (relativize = root.relativize(receiver$0)) == null || (normalize = relativize.normalize()) == null || (dropLeadingTopDirs = dropLeadingTopDirs(normalize)) == null) {
            Path normalize2 = receiver$0.normalize();
            Intrinsics.checkExpressionValueIsNotNull(normalize2, "normalize()");
            return dropLeadingTopDirs(normalize2);
        }
        return dropLeadingTopDirs;
    }

    @KtorExperimentalAPI
    @NotNull
    public static final File combineSafe(@NotNull File receiver$0, @NotNull Path relativePath) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(relativePath, "relativePath");
        Path normalizeAndRelativize = normalizeAndRelativize(relativePath);
        if (!normalizeAndRelativize.startsWith("..")) {
            if (!normalizeAndRelativize.isAbsolute()) {
                return new File(receiver$0, normalizeAndRelativize.toString());
            }
            throw new IllegalStateException(("Bad relative path " + relativePath).toString());
        }
        String obj = relativePath.toString();
        throw new InvalidPathException(obj, "Bad relative path " + relativePath);
    }
}
