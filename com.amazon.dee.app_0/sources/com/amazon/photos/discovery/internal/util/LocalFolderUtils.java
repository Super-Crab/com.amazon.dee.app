package com.amazon.photos.discovery.internal.util;

import android.os.Environment;
import com.amazon.photos.discovery.model.FolderType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: LocalFolderUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/LocalFolderUtils;", "", "()V", "EXTERNAL_STORAGE_CAMERA_PATH", "", "getFolderType", "Lcom/amazon/photos/discovery/model/FolderType;", "folderPath", "getFolderType$AndroidPhotosDiscovery_release", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LocalFolderUtils {
    public static final LocalFolderUtils INSTANCE = new LocalFolderUtils();
    private static final String EXTERNAL_STORAGE_CAMERA_PATH = File.separator + Environment.DIRECTORY_DCIM + File.separator;

    private LocalFolderUtils() {
    }

    @NotNull
    public final FolderType getFolderType$AndroidPhotosDiscovery_release(@NotNull String folderPath) {
        boolean endsWith$default;
        boolean contains$default;
        Intrinsics.checkParameterIsNotNull(folderPath, "folderPath");
        String str = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
        endsWith$default = StringsKt__StringsJVMKt.endsWith$default(folderPath, str, false, 2, null);
        if (!endsWith$default) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(folderPath);
            outline107.append(File.separator);
            folderPath = outline107.toString();
        }
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) folderPath, (CharSequence) EXTERNAL_STORAGE_CAMERA_PATH, false, 2, (Object) null);
        return contains$default ? FolderType.CAMERA : FolderType.GENERIC;
    }
}
