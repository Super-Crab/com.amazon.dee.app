package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
class PreverificationHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(26)
    public boolean shouldUseHardwareBitmapConfig(@Nullable Bitmap.Config config) {
        return config == Bitmap.Config.HARDWARE;
    }
}
