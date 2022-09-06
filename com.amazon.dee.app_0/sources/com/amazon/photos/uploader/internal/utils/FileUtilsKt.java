package com.amazon.photos.uploader.internal.utils;

import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import java.io.File;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"EXTENSION_JPEG", "", "", "isJpeg", "", "Ljava/io/File;", "AndroidPhotosUploader_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FileUtilsKt {
    private static final Set<String> EXTENSION_JPEG;

    static {
        Set<String> of;
        of = SetsKt__SetsKt.setOf((Object[]) new String[]{"jpeg", ImageType.JPG});
        EXTENSION_JPEG = of;
    }

    public static final boolean isJpeg(@NotNull File isJpeg) {
        String extension;
        Intrinsics.checkParameterIsNotNull(isJpeg, "$this$isJpeg");
        Set<String> set = EXTENSION_JPEG;
        extension = FilesKt__UtilsKt.getExtension(isJpeg);
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        if (extension != null) {
            String lowerCase = extension.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            return set.contains(lowerCase);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
