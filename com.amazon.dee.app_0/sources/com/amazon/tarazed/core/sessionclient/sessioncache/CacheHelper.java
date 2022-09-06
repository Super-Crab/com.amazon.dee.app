package com.amazon.tarazed.core.sessionclient.sessioncache;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CacheHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/CacheHelper;", "", "()V", "exists", "", "context", "Landroid/content/Context;", "fileName", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CacheHelper {
    public static final CacheHelper INSTANCE = new CacheHelper();

    private CacheHelper() {
    }

    public final boolean exists(@NotNull Context context, @NotNull String fileName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        StringBuilder sb = new StringBuilder();
        File cacheDir = context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
        sb.append(cacheDir.getAbsolutePath());
        sb.append('/');
        sb.append(fileName);
        sb.append('/');
        sb.append(fileName);
        return new File(sb.toString()).exists();
    }
}
