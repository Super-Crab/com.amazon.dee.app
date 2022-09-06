package io.ktor.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt__FilePathComponentsKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Path.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0001H\u0007\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0001H\u0002¨\u0006\b"}, d2 = {"combineSafe", "Ljava/io/File;", "dir", "relativePath", "", "dropLeadingTopDirs", "normalizeAndRelativize", "notRooted", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class PathKt {
    @KtorExperimentalAPI
    @NotNull
    public static final File combineSafe(@NotNull File receiver$0, @NotNull String relativePath) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(relativePath, "relativePath");
        return combineSafe(receiver$0, new File(relativePath));
    }

    private static final File dropLeadingTopDirs(@NotNull File file) {
        String path = file.getPath();
        if (path == null) {
            Intrinsics.throwNpe();
        }
        int length = path.length() - 1;
        int i = 0;
        while (i < length && path.charAt(i) == '.') {
            char charAt = path.charAt(i + 1);
            if (charAt != '\\' && charAt != '/') {
                if (charAt != '.') {
                    break;
                }
                int i2 = i + 2;
                if (i2 == path.length()) {
                    i = i2;
                } else {
                    char charAt2 = path.charAt(i2);
                    if (charAt2 == '/' || charAt2 == '\\') {
                        i += 3;
                    }
                }
            } else {
                i += 2;
            }
        }
        if (i == 0) {
            return file;
        }
        if (i >= path.length()) {
            return new File(".");
        }
        String substring = path.substring(i);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return new File(substring);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final File normalizeAndRelativize(@NotNull File receiver$0) {
        File normalize;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        normalize = FilesKt__UtilsKt.normalize(receiver$0);
        return dropLeadingTopDirs(notRooted(normalize));
    }

    private static final File notRooted(@NotNull File file) {
        boolean isRooted;
        String drop;
        String str;
        isRooted = FilesKt__FilePathComponentsKt.isRooted(file);
        if (!isRooted) {
            return file;
        }
        File file2 = file;
        while (true) {
            File parentFile = file2.getParentFile();
            if (parentFile == null) {
                break;
            }
            file2 = parentFile;
        }
        String path = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        drop = StringsKt___StringsKt.drop(path, file2.getName().length());
        int length = drop.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                str = "";
                break;
            }
            char charAt = drop.charAt(i);
            if (!(charAt == '\\' || charAt == '/')) {
                str = drop.substring(i);
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).substring(startIndex)");
                break;
            }
            i++;
        }
        return new File(str);
    }

    private static final File combineSafe(File file, File file2) {
        boolean startsWith;
        File normalizeAndRelativize = normalizeAndRelativize(file2);
        startsWith = FilesKt__UtilsKt.startsWith(normalizeAndRelativize, "..");
        if (!startsWith) {
            if (!normalizeAndRelativize.isAbsolute()) {
                return new File(file, normalizeAndRelativize.getPath());
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline63("Bad relative path ", file2).toString());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline63("Bad relative path ", file2));
    }
}
