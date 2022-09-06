package com.here.sdk.core.engine;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import com.here.sdk.core.engine.PlatformUtils;
/* loaded from: classes3.dex */
public final class PlatformUtilsInitializer {
    private PlatformUtilsInitializer() {
    }

    public static void initialize(@NonNull Context context) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        PlatformUtils.setPlatformInformation(new PlatformUtils.PlatformInformation("Android", Build.VERSION.RELEASE, absolutePath, absolutePath, context.getCacheDir().getPath()));
        PlatformUtils.setAssetsLoader(new AndroidAssetsLoader(context));
        PlatformUtils.setLocaleFactory(new AndroidLocaleFactory());
    }
}
