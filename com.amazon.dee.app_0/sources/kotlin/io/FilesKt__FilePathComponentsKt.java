package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FilePathComponents.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u000b\u001a\u00020\f*\u00020\bH\u0002¢\u0006\u0002\b\r\u001a\u001c\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0002H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0002*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"isRooted", "", "Ljava/io/File;", "(Ljava/io/File;)Z", "root", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "rootName", "", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "getRootLength", "", "getRootLength$FilesKt__FilePathComponentsKt", "subPath", "beginIndex", "endIndex", "toComponents", "Lkotlin/io/FilePathComponents;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/io/FilesKt")
/* loaded from: classes10.dex */
public class FilesKt__FilePathComponentsKt {
    @NotNull
    public static final File getRoot(@NotNull File root) {
        Intrinsics.checkParameterIsNotNull(root, "$this$root");
        return new File(getRootName(root));
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
        r0 = kotlin.text.StringsKt__StringsKt.indexOf$default((java.lang.CharSequence) r8, r3, 2, false, 4, (java.lang.Object) null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int getRootLength$FilesKt__FilePathComponentsKt(@org.jetbrains.annotations.NotNull java.lang.String r8) {
        /*
            char r1 = java.io.File.separatorChar
            r2 = 0
            r3 = 0
            r4 = 4
            r5 = 0
            r0 = r8
            int r0 = kotlin.text.StringsKt.indexOf$default(r0, r1, r2, r3, r4, r5)
            r1 = 1
            if (r0 != 0) goto L3d
            int r0 = r8.length()
            if (r0 <= r1) goto L3c
            char r0 = r8.charAt(r1)
            char r3 = java.io.File.separatorChar
            if (r0 != r3) goto L3c
            r4 = 2
            r5 = 0
            r6 = 4
            r7 = 0
            r2 = r8
            int r0 = kotlin.text.StringsKt.indexOf$default(r2, r3, r4, r5, r6, r7)
            if (r0 < 0) goto L3c
            char r3 = java.io.File.separatorChar
            int r4 = r0 + 1
            r5 = 0
            r6 = 4
            r7 = 0
            r2 = r8
            int r0 = kotlin.text.StringsKt.indexOf$default(r2, r3, r4, r5, r6, r7)
            if (r0 < 0) goto L37
            int r0 = r0 + r1
            return r0
        L37:
            int r8 = r8.length()
            return r8
        L3c:
            return r1
        L3d:
            r2 = 58
            if (r0 <= 0) goto L4b
            int r3 = r0 + (-1)
            char r3 = r8.charAt(r3)
            if (r3 != r2) goto L4b
            int r0 = r0 + r1
            return r0
        L4b:
            r1 = -1
            r3 = 0
            if (r0 != r1) goto L5c
            r0 = 2
            r1 = 0
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r8, r2, r3, r0, r1)
            if (r0 == 0) goto L5c
            int r8 = r8.length()
            return r8
        L5c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FilePathComponentsKt.getRootLength$FilesKt__FilePathComponentsKt(java.lang.String):int");
    }

    @NotNull
    public static final String getRootName(@NotNull File rootName) {
        Intrinsics.checkParameterIsNotNull(rootName, "$this$rootName");
        String path = rootName.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        String path2 = rootName.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path2, "path");
        String substring = path.substring(0, getRootLength$FilesKt__FilePathComponentsKt(path2));
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public static boolean isRooted(@NotNull File isRooted) {
        Intrinsics.checkParameterIsNotNull(isRooted, "$this$isRooted");
        String path = isRooted.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        return getRootLength$FilesKt__FilePathComponentsKt(path) > 0;
    }

    @NotNull
    public static final File subPath(@NotNull File subPath, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(subPath, "$this$subPath");
        return toComponents(subPath).subPath(i, i2);
    }

    @NotNull
    public static final FilePathComponents toComponents(@NotNull File toComponents) {
        List<String> split$default;
        int collectionSizeOrDefault;
        List list;
        Intrinsics.checkParameterIsNotNull(toComponents, "$this$toComponents");
        String path = toComponents.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt(path);
        String substring = path.substring(0, rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        String substring2 = path.substring(rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        if (substring2.length() == 0) {
            list = CollectionsKt__CollectionsKt.emptyList();
        } else {
            split$default = StringsKt__StringsKt.split$default((CharSequence) substring2, new char[]{File.separatorChar}, false, 0, 6, (Object) null);
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (String str : split$default) {
                arrayList.add(new File(str));
            }
            list = arrayList;
        }
        return new FilePathComponents(new File(substring), list);
    }
}
